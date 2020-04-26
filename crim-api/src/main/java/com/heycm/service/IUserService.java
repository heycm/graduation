package com.heycm.service;

import com.heycm.model.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.heycm.query.UserQuery;
import com.heycm.utils.response.ResponseMessage;

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
}
