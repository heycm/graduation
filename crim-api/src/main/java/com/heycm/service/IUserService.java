package com.heycm.service;

import com.heycm.dto.UserRolePermissionDTO;
import com.heycm.model.Permission;
import com.heycm.model.Role;
import com.heycm.model.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.heycm.query.UserQuery;
import com.heycm.utils.response.ResponseMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-04-26
 */
public interface IUserService extends IService<User> {

    // 企业用户注册接口
    ResponseMessage companyRegister(UserQuery userQuery);

    // 根据用户名和密码登录
    ResponseMessage loginByUsernameAndPassword(UserQuery userQuery);

    // 去redis查询用户权限角色信息
    ResponseMessage getRoleAndPermission();

    // 手机短信验证码登录
    ResponseMessage loginBySmsCode(UserQuery userQuery);

    // 注销登录
    ResponseMessage logout(HttpServletRequest request);
}
