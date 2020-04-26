package com.heycm.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heycm.config.CustomConfig;
import com.heycm.config.JwtToken;
import com.heycm.enums.CommEnum;
import com.heycm.enums.Const;
import com.heycm.enums.UserEnum;
import com.heycm.mapper.UserMapper;
import com.heycm.model.User;
import com.heycm.query.UserQuery;
import com.heycm.service.IUserService;
import com.heycm.utils.JwtUtil;
import com.heycm.utils.PasswordUtils;
import com.heycm.utils.RedisUtil;
import com.heycm.utils.Tools;
import com.heycm.utils.date.DateUtil;
import com.heycm.utils.response.ResponseMessage;
import com.heycm.utils.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-04-26
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    RedisUtil redisUtil;
    @Resource
    UserMapper userMapper;
    @Resource
    JwtUtil jwtUtil;
    @Resource
    CustomConfig customConfig;

    /**
     * 企业用户注册方法
     * @param userQuery phone/password/smsCode
     * @return ResponseMessage
     */
    @Override
    public ResponseMessage companyRegister(UserQuery userQuery) {
        // 1.入参校验
        if (userQuery == null || StringUtils.isEmpty(userQuery.getPhone())
            || StringUtils.isEmpty(userQuery.getPassword()) || StringUtils.isEmpty(userQuery.getSmsCode())){
            return Result.error(UserEnum.PARAM_EMPTY);
        }
        if (!Tools.checkPhone(userQuery.getPhone())) {
            return Result.error(UserEnum.PHONE_INVALID);
        }
        // TODO 去Redis找到验证码，并比对
        // // 2.Redis中查找短信验证码  key:sms_code_phone
        // String smsCode = (String) redisUtil.get(Const.PREFIX_REDIS_SMS_CODE.getValue() + req.getPhone());
        // if (StringUtils.isEmpty(smsCode)){
        //     return Result.error(UserEnum.SMS_CODE_EXPIRED);
        // }
        // // 3.比对验证码
        // if (!userQuery.getSmsCode().equals(smsCode)){
        //     return Result.error(UserEnum.SMS_CODE_INVALID);
        // }
        // 4.检查用户是否存在
        LambdaQueryWrapper<User> qw = new QueryWrapper<User>().lambda().eq(User::getUsername, userQuery.getPhone());
        User one = getOne(qw);
        if (one != null) {
            return Result.error(UserEnum.USER_EXIST);
        }
        // 5.获取盐值
        String salt = PasswordUtils.generateSalt();
        // 6.加密加密
        String encrypt = PasswordUtils.encrypt(userQuery.getPassword(), salt);
        // 7.创建User并保存
        User user = new User();
        user.setUsername(userQuery.getPhone());
        user.setPassword(encrypt);
        user.setSalt(salt);
        user.setType(2);
        user.setStatus(1);
        boolean save = save(user);
        if (!save) {
            log.error("[异常][时间:{}][企业用户注册][失败][入参信息:{}][结束]", DateUtil.getStringYMDHMS(), JSON.toJSONString(userQuery));
            Result.error(UserEnum.REGISTER_ERROR);
        }
        return Result.ok(UserEnum.REGISTER_SUCCESS);
    }

    /**
     * 根据用户名和密码登录
     * @param userQuery 用户名、密码、验证码
     * @return ResponseMessage
     */
    @Override
    public ResponseMessage loginByUsernameAndPassword(UserQuery userQuery) {
        // 1.入参校验
        if (userQuery == null || StringUtils.isEmpty(userQuery.getUsername())
                || StringUtils.isEmpty(userQuery.getPassword()) || StringUtils.isEmpty(userQuery.getVCode())){
            return Result.error(UserEnum.PARAM_EMPTY);
        }
        // 2.到Redis查询图片验证码，并比对
        String vCode = (String) redisUtil.get(Const.PREFIX_REDIS_V_CODE.getValue() + userQuery.getUsername());
        if (!userQuery.getVCode().equals(vCode)) {
            return Result.error(UserEnum.V_CODE_INVALID);
        }
        // 3.根据用户名查找用户
        LambdaQueryWrapper<User> eq = new QueryWrapper<User>().lambda().eq(User::getUsername, userQuery.getUsername());
        User user = getOne(eq);
        if (user == null) {
            return Result.error(UserEnum.USER_NOT_EXIST);
        }
        // 4.明文密码加密，并与数据库中用户密码比对
        String encrypt = PasswordUtils.encrypt(userQuery.getUsername(), user.getSalt());
        if (!encrypt.equals(user.getPassword())) {
            return Result.error(UserEnum.PASSWORD_ERROR);
        }
        // 5.根据用户id和用户名生成jwt
        String token = jwtUtil.generateJwtToken(user.getId(), user.getUsername());
        // 6.以token为键，将用户存入Redis，并设置与jwt相同过期时间
        Boolean set = redisUtil.set(token, user, (customConfig.getJwtTtl()/30));
        if(!set) {
            return Result.error(CommEnum.REDIS_SAVE_ERROR);
        }
        // 7.为了刚登陆完成就将token写入shiro的principal，执行一次shiro认证授权操作
        SecurityUtils.getSubject().login(new JwtToken(token));
        // 8.登录成功，返回jwt
        return Result.ok(token);
    }
}
