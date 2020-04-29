package com.heycm.config;

import com.heycm.dto.UserRolePermissionDTO;
import com.heycm.enums.CommEnum;
import com.heycm.model.Permission;
import com.heycm.model.Role;
import com.heycm.utils.JwtUtil;
import com.heycm.utils.RedisUtil;
import com.heycm.utils.date.DateUtil;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @Description 专门用于验证 jwtToken 的 realm
 * @Author heycm@qq.com
 * @Date 2020-03-27 15:56
 */
@Slf4j
public class JwtRealm extends AuthorizingRealm {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisUtil redisUtil;


    /**
     * 自定义realm名称
     */
    public void setName(String name) {
        super.setName("jwtRealm");
    }

    /*
     * 多重写一个support
     * 标识这个Realm是专门用来验证JwtToken（支持自定义 AuthenticationToken）
     * 不负责验证其他的token（UsernamePasswordToken）
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        //这个token就是从过滤器中传入的jwtToken
        return token instanceof JwtToken;
    }

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        log.info("[授权][时间:{}][结束]", DateUtil.getStringYMDHMS());

        // 1.获取token（从认证传过来的）
        String token = principalCollection.toString();
        // 2.去Redis拿来用户的角色权限信息
        UserRolePermissionDTO userRolePermissionDTO = (UserRolePermissionDTO) redisUtil.get(token);
        List<Role> roles = userRolePermissionDTO.getRoles();
        Map<Integer, List<Permission>> permissionMap = userRolePermissionDTO.getPermissionMap();
        // 3.授权，如下示例
        // roles:["roleName1", "roleName2", "roleName3"]
        // perms:["roleName1:permName", "roleName2:permName", "roleName3:permName"]
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        for (Role role : roles) {
            info.addRole(role.getRoleName());
            List<Permission> permissionList = permissionMap.get(role.getId());
            for (Permission permission : permissionList) {
                info.addStringPermission(role.getRoleName() + ":" + permission.getPermName());
            }
        }
        return info;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        log.info("[认证][时间:{}][结束]", DateUtil.getStringYMDHMS());

        JwtToken jwtToken = (JwtToken) authenticationToken;
        String token = (String) jwtToken.getPrincipal();

        if (StringUtils.isEmpty(token)) {
            throw new AuthenticationException(CommEnum.AUTH_401_NOT.getMsg());
        }
        Claims claims = null;
        try {
            claims = jwtUtil.parseJwtToken(token);
        } catch (ExpiredJwtException e) {
            throw new AuthenticationException("JWT超时");
        } catch (UnsupportedJwtException e) {
            throw new AuthenticationException("不支持该JWT");
        } catch (MalformedJwtException e) {
            throw new AuthenticationException("JWT格式错误");
        } catch (SignatureException e) {
            throw new AuthenticationException("签名异常");
        } catch (IllegalArgumentException e) {
            throw new AuthenticationException("JWT参数错误");
        }
        if (claims == null) {
            throw new AuthenticationException("JWT无效");
        }
        String username = claims.getSubject();
        if (StringUtils.isEmpty(username)) {
            throw new AuthenticationException("JWT无效");
        }
        UserRolePermissionDTO userRolePermissionDTO = (UserRolePermissionDTO) redisUtil.get(token);
        if (userRolePermissionDTO == null){
            throw new AuthenticationException("用户不存在");
        }
        return new SimpleAuthenticationInfo(token, token, getName());
    }
}
