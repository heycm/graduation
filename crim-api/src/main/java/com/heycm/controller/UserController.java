package com.heycm.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.heycm.enums.CommEnum;
import com.heycm.enums.Const;
import com.heycm.model.UserRole;
import com.heycm.param.Param;
import com.heycm.service.IUserRoleService;
import com.heycm.service.IUserService;
import com.heycm.model.User;
import com.heycm.query.UserQuery;
import com.heycm.utils.PasswordUtils;
import com.heycm.utils.RedisUtil;
import com.heycm.utils.SubjectUtil;
import com.heycm.utils.response.ResponseMessage;
import com.heycm.utils.response.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 用户控制器
 *
 * @author heycm@qq.com
 * @since 2020-04-26
 */
@RestController
@RequestMapping("/api/v1/user")
@Api(tags = "02 - 用户控制器 User")
@Transactional
public class UserController {

    @Autowired
    public IUserService userService;
    @Autowired
    public IUserRoleService userRoleService;
    @Autowired
    public SubjectUtil subjectUtil;
    @Autowired
    public RedisUtil redisUtil;

    /**
     * 企业用户注册接口
     *
     * @param userQuery CompanyRegisterReq
     * @return ResponseMessage
     */
    @ApiOperation(value = "1 - 企业注册", notes = "企业用户注册接口")
    @ApiOperationSupport(order = 1)
    @PostMapping("/open/register")
    public ResponseMessage companyRegister(@RequestBody UserQuery userQuery) {
        return userService.companyRegister(userQuery);
    }

    /**
     * 根据用户名和密码登录
     *
     * @param userQuery username,password,vCode
     * @return ResponseMessage
     */
    @ApiOperation(value = "2 - 密码登录", notes = "根据用户名和密码登录")
    @ApiOperationSupport(order = 2)
    @PostMapping("/open/loginByUsernameAndPassword")
    public ResponseMessage loginByUsernameAndPassword(@RequestBody UserQuery userQuery) {
        return userService.loginByUsernameAndPassword(userQuery);
    }

    /**
     * 根据手机短信验证码登录
     *
     * @param userQuery phone/smsCode
     * @return ResponseMessage
     */
    @ApiOperation(value = "3 - 短信验证码登录", notes = "根据手机短信验证码登录")
    @ApiOperationSupport(order = 3)
    @PostMapping("/open/loginBySmsCode")
    public ResponseMessage loginBySmsCode(@RequestBody UserQuery userQuery) {
        return userService.loginBySmsCode(userQuery);
    }

    /**
     * 获取用户角色权限信息
     *
     * @return ResponseMessage
     */
    @ApiOperation(value = "4 - 获取用户角色和权限", notes = "获取用户角色和权限")
    @ApiOperationSupport(order = 4)
    @GetMapping("/getRoleAndPermission")
    @RequiresRoles(logical = Logical.OR, value = {"company", "student", "school", "school-child"})
    // @RequiresPermissions(logical = Logical.OR, value = {"company:select", "student:select", "school:select", "school-child:select"})
    public ResponseMessage getRoleAndPermission() {
        return userService.getRoleAndPermission();
    }

    @ApiOperation(value = "5 - 注销登录", notes = "注销登录")
    @ApiOperationSupport(order = 5)
    @GetMapping(value = "/open/logout")
    public ResponseMessage logout(HttpServletRequest request) {
        return userService.logout(request);
    }


    @ApiOperation(value = "5 - 重置密码", notes = "重置密码")
    @ApiOperationSupport(order = 5)
    @PostMapping("/password")
    @RequiresRoles(logical = Logical.OR, value = {"company", "school", "school-child"})
    public ResponseMessage password(String password) {
        // 1.入参校验
        if (StringUtils.isEmpty(password)) {
            return Result.error("新密码不能是空");
        }
        // 2.获取当前登录用户id
        Integer currentUserId = subjectUtil.getCurrentUserId();
        // 3.生成新盐值，加密新密码
        String salt = PasswordUtils.generateSalt();
        String encrypt = PasswordUtils.encrypt(password, salt);
        // 4.创建用户实体
        User user = new User();
        user.setId(currentUserId);
        user.setSalt(salt);
        user.setPassword(encrypt);
        // 5.根据ID修改数据
        userService.updateById(user);
        // 6.用户登出操作
        String userToken = subjectUtil.getCurrentUserToken();
        subjectUtil.logout();
        redisUtil.del(userToken);
        // 7.返回
        return Result.ok();
    }

    @ApiOperation(value = "6 - 学校用户重置子用户密码", notes = "学校用户重置子用户密码")
    @ApiOperationSupport(order = 6)
    @PostMapping("/password/{id}")
    @RequiresRoles("school")
    public ResponseMessage password(@PathVariable("id") Integer id, String password) {
        if (StringUtils.isEmpty(password)) {
            return Result.error("新密码不能是空");
        }
        String salt = PasswordUtils.generateSalt();
        String encrypt = PasswordUtils.encrypt(password, salt);
        User user = new User();
        user.setId(id);
        user.setSalt(salt);
        user.setPassword(encrypt);
        userService.updateById(user);
        return Result.ok();
    }

    @ApiOperation(value = "7 - 学校用户重置子用户角色", notes = "学校用户重置子用户角色")
    @ApiOperationSupport(order = 7)
    @GetMapping("/perm/{userId}/{roleId}")
    @RequiresRoles("school")
    public ResponseMessage perm(@PathVariable("userId") Integer userId, @PathVariable("roleId") Integer roleId) {
        if (userId == null || roleId == null) {
            return Result.error("参数不能是空");
        }
        LambdaQueryWrapper<UserRole> eq = new QueryWrapper<UserRole>().lambda().eq(UserRole::getUserId, userId);
        UserRole one = userRoleService.getOne(eq);
        one.setRoleId(roleId);
        userRoleService.updateById(one);
        return Result.ok();
    }

    @ApiOperation(value = "8 - 学校删除子账户", notes = "学校删除子账户")
    @ApiOperationSupport(order = 8)
    @DeleteMapping("/{id}")
    @RequiresRoles("school")
    public ResponseMessage del(@PathVariable("id") Integer id) {
        if (id == null) {
            return Result.error("参数不能是空");
        }
        userService.removeById(id);
        return Result.ok();
    }

    @ApiOperation(value = "9 - 学校用户重置学生用户密码", notes = "学校用户重置学生用户密码")
    @ApiOperationSupport(order = 9)
    @RequiresRoles("school")
    @GetMapping("/resetPwd/{id}")
    public ResponseMessage resetPwd(@PathVariable("id") Integer id) {
        if (id == null) {
            return Result.error("参数不能是空");
        }
        User byId = userService.getById(id);
        if (byId == null) {
            return Result.error("用户不存在");
        }
        String username = byId.getUsername();
        String salt = PasswordUtils.generateSalt();
        String encrypt = PasswordUtils.encrypt(username, salt);
        byId.setSalt(salt);
        byId.setPassword(encrypt);
        userService.updateById(byId);
        return Result.ok();
    }

    @ApiOperation(value = "10 - 学生根据原密码重置密码", notes = "学生根据原密码重置密码")
    @ApiOperationSupport(order = 10)
    @RequiresRoles("student")
    @PostMapping("/stu/pwd")
    public ResponseMessage stuResetPwd(@RequestBody UserQuery userQuery) {
        // 1.入参必须带有 原密码、新密码、确认密码
        if (userQuery == null || StringUtils.isBlank(userQuery.getOldPwd())
                || StringUtils.isBlank(userQuery.getNewPwd()) || StringUtils.isBlank(userQuery.getSurePwd())) {
            return Result.error("参数不能是空");
        }
        // 2.新密码与确认密码匹配
        if (!userQuery.getNewPwd().equals(userQuery.getSurePwd())) {
            return Result.error("确认密码不匹配");
        }
        // 3.查找当前用户
        User user = userService.getById(subjectUtil.getCurrentUserId());
        // 4.拿到当前用户的 原密码盐值 与 原密码密文
        String salt = user.getSalt();
        String password = user.getPassword();
        // 5.入参原密码加密，与原密码密文匹配
        String oldEncrypt = PasswordUtils.encrypt(userQuery.getOldPwd(), salt);
        if (!password.equals(oldEncrypt)) {
            return Result.error("原密码错误");
        }
        // 6.生成新的盐值，加密新密码，保存
        String newSalt = PasswordUtils.generateSalt();
        String newEncrypt = PasswordUtils.encrypt(userQuery.getNewPwd(), newSalt);
        user.setSalt(newSalt);
        user.setPassword(newEncrypt);
        userService.updateById(user);
        // 7-1.如果当前用户是子用户，设置父用户密码与当前用户密码一致
        if (user.getPid() != null) {
            User pUser = userService.getById(user.getPid());
            pUser.setSalt(newSalt);
            pUser.setPassword(newEncrypt);
            userService.updateById(pUser);
        }
        // 7-2.如果当前用户是父用户，设置子用户密码与当前用户密码一致
        else {
            User child = userService.getOne(new QueryWrapper<User>().lambda().eq(User::getPid, user.getId()));
            child.setSalt(newSalt);
            child.setPassword(newEncrypt);
            userService.updateById(child);
        }
        // 8.用户登出
        redisUtil.del(subjectUtil.getCurrentUserToken());
        subjectUtil.logout();
        return Result.ok();
    }

    @ApiOperation(value = "10 - 学生根据短信验证码重置密码", notes = "学生根据短信验证码重置密码")
    @ApiOperationSupport(order = 10)
    @RequiresRoles("student")
    @PostMapping("/stu/pwd/sms")
    public ResponseMessage stuResetPwdBySms(@RequestBody UserQuery userQuery) {
        // 1.入参必须带有 原密码、新密码、确认密码
        if (userQuery == null || StringUtils.isBlank(userQuery.getNewPwd()) || StringUtils.isBlank(userQuery.getSmsCode())) {
            return Result.error("参数不能是空");
        }
        // 2.查找当前用户
        User user = userService.getById(subjectUtil.getCurrentUserId());
        // 3.如果当前账户是主账户，需要找到其子账户，获取子账户用户名（手机号）
        if (user.getPid() == null) {
            user = userService.getOne(new QueryWrapper<User>().lambda().eq(User::getPid, user.getId()));
        }
        // 4.拿到用户手机号，去redis查验证码
        String phone = user.getUsername();
        String smsCode = (String) redisUtil.get(Const.PREFIX_REDIS_SMS_CODE.getValue() + phone);
        // 5.验证码过期或不匹配
        if (StringUtils.isBlank(smsCode)) {
            return Result.error(CommEnum.SMS_CODE_INVALID);
        }
        // 6.生成新的盐值，加密新密码
        String newSalt = PasswordUtils.generateSalt();
        String newEncrypt = PasswordUtils.encrypt(userQuery.getNewPwd(), newSalt);
        // 7.更新子账户盐值和密码
        user.setSalt(newSalt);
        user.setPassword(newEncrypt);
        userService.updateById(user);
        // 9.更新父账户密码
        User pUser = userService.getById(user.getPid());
        pUser.setSalt(newSalt);
        pUser.setPassword(newEncrypt);
        userService.updateById(pUser);
        // 10.用户登出
        redisUtil.del(subjectUtil.getCurrentUserToken());
        subjectUtil.logout();
        return Result.ok();
    }


    /**
     * 保存、修改 【区分id即可】
     *
     * @param param param
     * @return ResponseMessage
     */
    @PostMapping("/save")
    public ResponseMessage save(@RequestBody Param<User> param) {
        if (param == null) {
            return Result.error("1000", "参数为NUll");
        }
        userService.saveOrUpdate(param.getData());
        return Result.ok();
    }

    /**
     * 根据IDs批量删除
     *
     * @param param param
     * @return ResponseMessage
     */
    @PostMapping("/batchDel")
    public ResponseMessage delete(@RequestBody Param param) {
        if (param == null) {
            return Result.error("1000", "参数为NUll");
        }
        userService.removeByIds(param.getIds());
        return Result.ok();
    }

    /**
     * 根据ID获取对象信息
     *
     * @param id 对象id
     * @return ResponseMessage
     */
    @GetMapping("/{id}")
    public ResponseMessage get(@PathVariable("id") Long id) {
        if (id == null) {
            return Result.error("1000", "参数为NUll");
        }
        User entity = userService.getById(id);
        return Result.ok(entity);
    }


    //查看所有的信息
    @GetMapping("/list")
    public ResponseMessage list() {
        List<User> entityList = userService.list();
        return Result.ok(entityList);
    }


    /**
     * 分页查询数据：
     *
     * @param param 查询对象
     * @return ResponseMessage
     */
    @PostMapping("/pageList")
    public ResponseMessage pageList(@RequestBody Param<UserQuery> param) {
        if (param == null) {
            return Result.error("1000", "参数为NUll");
        }
        Page<User> page = new Page<User>(param.getPage(), param.getRows());
        IPage iPage = userService.page(page, null);
        return Result.ok(iPage);
    }
}
