package com.heycm.utils;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-04-29 1:11
 */
@Component
public class SubjectUtil {

    @Resource
    JwtUtil jwtUtil;

    /**
     * 获取当前用户ID：接口必须需要角色或权限才可以使用，开放接口不可用
     * @return 当前用户ID
     */
    public Integer getCurrentUserId() {
        String token = SecurityUtils.getSubject().getPrincipal().toString();
        return jwtUtil.getUserId(token);
    }

    /**
     * 获取当前用户类型：接口必须需要角色或权限才可以使用，开放接口不可用
     * @return 当前用户类型
     */
    public Integer getCurrentUserType() {
        String token = SecurityUtils.getSubject().getPrincipal().toString();
        return jwtUtil.getUserType(token);
    }
}
