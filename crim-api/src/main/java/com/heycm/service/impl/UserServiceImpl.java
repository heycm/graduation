package com.heycm.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heycm.config.CustomConfig;
import com.heycm.config.JwtToken;
import com.heycm.dto.UserLoginDTO;
import com.heycm.dto.UserRolePermissionDTO;
import com.heycm.enums.CommEnum;
import com.heycm.enums.Const;
import com.heycm.enums.UserEnum;
import com.heycm.mapper.UserMapper;
import com.heycm.model.*;
import com.heycm.query.UserQuery;
import com.heycm.service.*;
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
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
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
    JwtUtil jwtUtil;
    @Resource
    CustomConfig customConfig;
    @Autowired
    IUserRoleService userRoleService;
    @Autowired
    IRoleService roleService;
    @Autowired
    IRolePermissionService rolePermissionService;
    @Autowired
    IPermissionService permissionService;

    /**
     * 企业用户注册方法
     *
     * @param userQuery phone/password/smsCode
     * @return ResponseMessage
     */
    @Override
    public ResponseMessage companyRegister(UserQuery userQuery) {
        // 1.入参校验
        if (userQuery == null || StringUtils.isEmpty(userQuery.getPhone())
                || StringUtils.isEmpty(userQuery.getPassword()) || StringUtils.isEmpty(userQuery.getSmsCode())) {
            return Result.error(UserEnum.PARAM_EMPTY);
        }
        if (!Tools.checkPhone(userQuery.getPhone())) {
            return Result.error(CommEnum.PHONE_INVALID);
        }
        // 2.Redis中查找短信验证码  key:sms_code_phone
        String smsCode = (String) redisUtil.get(Const.PREFIX_REDIS_SMS_CODE.getValue() + userQuery.getPhone());
        if (StringUtils.isEmpty(smsCode)) {
            return Result.error(CommEnum.SMS_CODE_EXPIRED);
        }
        // 3.比对验证码，比对成功则删除redis中的验证码
        if (!userQuery.getSmsCode().equals(smsCode)) {
            return Result.error(CommEnum.SMS_CODE_INVALID);
        }
        redisUtil.del(Const.PREFIX_REDIS_SMS_CODE.getValue() + userQuery.getPhone());
        // 4.检查用户是否存在
        LambdaQueryWrapper<User> qw = new QueryWrapper<User>().lambda().eq(User::getUsername, userQuery.getPhone());
        User one = getOne(qw);
        if (one != null) {
            return Result.error(UserEnum.USER_EXIST);
        }
        // 5.生成盐值
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
        save(user);
        // 8.分配角色
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(3);
        userRoleService.save(userRole);
        // 9.注册成功
        log.info("[企业用户注册][成功][时间:{}][注册信息:{}][结束]", DateUtil.getStringYMDHMS(), JSON.toJSONString(user));
        return Result.ok(UserEnum.REGISTER_SUCCESS);
    }

    /**
     * 根据用户名和密码登录
     *
     * @param userQuery 用户名、密码、验证码
     * @return ResponseMessage
     */
    @Override
    public ResponseMessage loginByUsernameAndPassword(UserQuery userQuery) {
        // 1.入参校验
        if (userQuery == null || StringUtils.isEmpty(userQuery.getUsername())
                || StringUtils.isEmpty(userQuery.getPassword()) || StringUtils.isEmpty(userQuery.getVcode())) {
            return Result.error(UserEnum.PARAM_EMPTY);
        }
        // 2.从redis中拿随机验证码，每一个验证码只能存取一次
        String vCode = (String) redisUtil.get(Const.PREFIX_REDIS_V_CODE.getValue() + userQuery.getVcode().toLowerCase());
        if (StringUtils.isEmpty(vCode)) {
            return Result.error(CommEnum.V_CODE_INVALID);
        }
        redisUtil.del(Const.PREFIX_REDIS_V_CODE.getValue() + userQuery.getVcode().toLowerCase());
        // 3.根据用户名查找用户
        LambdaQueryWrapper<User> eq = new QueryWrapper<User>().lambda().eq(User::getUsername, userQuery.getUsername());
        User user = getOne(eq);
        if (user == null) {
            return Result.error(UserEnum.USER_NOT_EXIST);
        }
        if (user.getStatus().equals(2)){
            return Result.error(UserEnum.USER_DISABLE);
        }
        // 4.明文密码加密，并与数据库中用户密码比对
        String encrypt = PasswordUtils.encrypt(userQuery.getPassword(), user.getSalt());
        if (!encrypt.equals(user.getPassword())) {
            return Result.error(UserEnum.PASSWORD_ERROR);
        }
        // 5.登录成功，创建token，写redis缓存，返回jwt
        return loginSuccess(user);
    }

    /**
     * 根据手机短信验证码登录
     *
     * @param userQuery 手机号、短信验证码
     * @return ResponseMessage
     */
    @Override
    public ResponseMessage loginBySmsCode(UserQuery userQuery) {
        // 1.入参校验
        if (userQuery == null || StringUtils.isEmpty(userQuery.getPhone()) || StringUtils.isEmpty(userQuery.getSmsCode())) {
            return Result.error(UserEnum.PARAM_EMPTY);
        }
        if (!Tools.checkPhone(userQuery.getPhone())){
            return Result.error(CommEnum.PHONE_INVALID);
        }
        // 2.去redis找验证码，并比对
        String smsCode = (String) redisUtil.get(Const.PREFIX_REDIS_SMS_CODE.getValue() + userQuery.getPhone());
        if (StringUtils.isEmpty(smsCode)) {
            // 验证码过期
            return Result.error(CommEnum.SMS_CODE_EXPIRED);
        }
        if (!smsCode.equals(userQuery.getSmsCode())) {
            // 验证码无效
            return Result.error(CommEnum.SMS_CODE_INVALID);
        }
        // 3.短信验证成功，删除redis中短信缓存
        redisUtil.del(Const.PREFIX_REDIS_SMS_CODE.getValue() + userQuery.getPhone());
        // 4.查询用户数据
        LambdaQueryWrapper<User> eq = new QueryWrapper<User>().lambda().eq(User::getUsername, userQuery.getPhone());
        User user = getOne(eq);
        if (user == null) {
            return Result.error(UserEnum.USER_NOT_EXIST);
        }
        if (user.getStatus().equals(2)){
            return Result.error(UserEnum.USER_DISABLE);
        }
        // 5.登录成功，创建token，写redis缓存，返回jwt
        return loginSuccess(user);
    }

    /**
     * 用户登出
     * @return ResponseMessage
     */
    @Override
    public ResponseMessage logout(HttpServletRequest request) {
        // shiro登出
        SecurityUtils.getSubject().logout();
        // 拿token，去redis删除
        String token = request.getHeader("Authorization");
        if (StringUtils.isEmpty(token)) {
            return Result.ok(UserEnum.LOGOUT_SUCCESS);
        }
        if (jwtUtil.verify(token)){
            redisUtil.del(token);
        }
        return Result.ok(UserEnum.LOGOUT_SUCCESS);
    }

    /**
     * 登录成功，创建token，写redis缓存
     *
     * @param user 登录用户
     * @return ResponseMessage
     */
    private ResponseMessage loginSuccess(User user) {
        // 1.根据用户id和用户名生成jwt
        String token = jwtUtil.generateJwtToken(user.getId(), user.getType());
        // 2.查询用户的角色权限对应关系
        UserRolePermissionDTO userRoleAndPermission = getUserRoleAndPermission(user);
        // 3.以token为键，将用户角色权限关系存入Redis，并设置与jwt相同过期时间
        redisUtil.set(token, userRoleAndPermission, (customConfig.getJwtTtl() / 60));
        List<Role> roles = userRoleAndPermission.getRoles();
        ArrayList<String> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(role.getRoleName());
        }
        // 4.构造登录回传数据
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setToken(token);
        userLoginDTO.setRoles(list);
        userLoginDTO.setIsAudit(user.getStatus());
        // 5.返回
        log.info("[用户登录][成功][时间:{}][用户信息:{}][结束]", DateUtil.getStringYMDHMS(), JSON.toJSONString(userRoleAndPermission));
        return Result.ok(userLoginDTO);
    }

    /**
     * 去mysql查询用户权限角色信息，登录成功需要将用户权限角色信息写入redis缓存
     *
     * @param user id
     * @return UserRolePermissionDTO
     */
    private UserRolePermissionDTO getUserRoleAndPermission(User user) {
        // 1.定义角色集合
        List<Role> roleList = new ArrayList<>();
        // 2.定义权限集合 {roleId:[permissionList], roleId:[permissionList],}
        Map<Integer, List<Permission>> permissionMap = new HashMap<>();
        // 3.根据用户id查询用户与角色的关系列表
        LambdaQueryWrapper<UserRole> userRoleQW = new QueryWrapper<UserRole>().lambda().eq(UserRole::getUserId, user.getId());
        List<UserRole> userRoleList = userRoleService.list(userRoleQW);
        // 4.根据用户与角色的关系列表，查询每一个角色的信息，装入角色集合
        for (UserRole userRole : userRoleList) {
            LambdaQueryWrapper<Role> roleQW = new QueryWrapper<Role>().lambda().eq(Role::getId, userRole.getRoleId());
            Role one = roleService.getOne(roleQW);
            roleList.add(one);
        }
        // 5.遍历角色集合，查询每一个角色对应的角色与权限关系列表
        for (Role role : roleList) {
            LambdaQueryWrapper<RolePermission> rolePermissionQW = new QueryWrapper<RolePermission>().lambda().eq(RolePermission::getRoleId, role.getId());
            List<RolePermission> rolePermissionList = rolePermissionService.list(rolePermissionQW);
            // 6.遍历角色与权限关系列表，查询角色所拥有的的权限列表
            List<Permission> list = new ArrayList<>();
            for (RolePermission rolePermission : rolePermissionList) {
                LambdaQueryWrapper<Permission> permissionQW = new QueryWrapper<Permission>().lambda().eq(Permission::getId, rolePermission.getPermId());
                Permission one = permissionService.getOne(permissionQW);
                list.add(one);
            }
            // 以每个角色的id为键，每个角色的权限列表为值，装入权限集合
            permissionMap.put(role.getId(), list);
        }
        // 注意此处置空用户属性将影响到上级传入的user的属性值
        user.setPassword("");
        user.setSalt("");
        UserRolePermissionDTO userRolePermissionDTO = new UserRolePermissionDTO();
        userRolePermissionDTO.setUser(user);
        userRolePermissionDTO.setRoles(roleList);
        userRolePermissionDTO.setPermissionMap(permissionMap);
        return userRolePermissionDTO;
    }


    /**
     * redis中获取用户权限角色信息
     *
     * @return ResponseMessage
     */
    @Override
    public ResponseMessage getRoleAndPermission() {
        // 从shiro中拿出token
        String token = SecurityUtils.getSubject().getPrincipal().toString();
        UserRolePermissionDTO userRolePermissionDTO = (UserRolePermissionDTO) redisUtil.get(token);
        return Result.ok(userRolePermissionDTO);
    }

}
