package com.heycm.config;

import com.heycm.filter.JwtFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SubjectFactory;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;

/** Shiro配置类
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-03-24 17:46
 */

@Configuration
public class ShiroConfig{

    // 3.配置 ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);



        // shiro整合jwt配置
        // 1.添加jwt过滤器到shiroFilterFactoryBean
        HashMap<String, Filter> map = new HashMap<>();
        map.put("jwt", new JwtFilter());
        shiroFilterFactoryBean.setFilters(map);
        // 2.设置拦截规则
        LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();
        // 放行静态资源
        filterMap.put("/static/**", "anon");
        filterMap.put("/doc.html","anon");
        filterMap.put("/swagger/**","anon");
        filterMap.put("/webjars/**", "anon");
        filterMap.put("/v2/**", "anon");
        filterMap.put("/swagger-resources/**","anon");
        filterMap.put("/favicon.ico","anon");
        filterMap.put("/**.js","anon");
        // 所有请求全部进jwtFilter，包括openApi，在jwtFilter统一支持跨域
        filterMap.put("/**", "jwt");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    // 2.配置 DefaultWebSecurityManager
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(
            // @Qualifier("userRealm") UserRealm userRealm,
            // @Qualifier("rememberMeManager") CookieRememberMeManager rememberMeManager,
            @Qualifier("jwtRealm") JwtRealm jwtRealm
    ){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        // 单 shiro 配置
        // 关联userRealm
        // securityManager.setRealm(userRealm);
        // 关联记住我管理器
        // 用法：new UsernamePasswordToken(username, password, rememberMe)
        // securityManager.setRememberMeManager(rememberMeManager);


        // shiro 整合 jwt 配置
        securityManager.setRealm(jwtRealm);
        // 关闭 ShiroDAO 功能
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        // 不需要将 Shiro Session 中的东西存到任何地方（包括 Http Session 中）
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        return securityManager;
    }

    // 开启注解支持
    // @RequiresPermissions("add")
    // @RequiresPermissions(logical = Logical.OR, value = {"add", "delete"})
    // @RequiresRoles("user")
    // @RequiresRoles(logical = Logical.OR, value = {"user", "vip"})
    // 使用注解模式时，当权限/角色不满足时，不会跳转，而是抛出异常
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
            @Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    // 解决shiro整合jwt出现不执行授权方法的问题
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }


    // 1.配置 Realm，需要自定义
    // @Bean(name = "userRealm")
    // public UserRealm userRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher hashedCredentialsMatcher){
    //     UserRealm userRealm = new UserRealm();
    //     // 配置加密器
    //     userRealm.setCredentialsMatcher(hashedCredentialsMatcher);
    //     userRealm.setCachingEnabled(false);
    //     return userRealm;
    // }

    @Bean(name = "jwtRealm")
    public JwtRealm jwtRealm() {
        return new JwtRealm();
    }



    // -------- shiro 以下为可选配置 ---------

    // 密码加密配置
    // 注册时选加密后存数据库
    // @Bean(name = "hashedCredentialsMatcher")
    // public HashedCredentialsMatcher hashedCredentialsMatcher(){
    //     HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
    //     // 散列算法:这里使用MD5算法;
    //     hashedCredentialsMatcher.setHashAlgorithmName("md5");
    //     // 散列的次数，比如散列2次，相当于 md5(md5(""));
    //     hashedCredentialsMatcher.setHashIterations(1024);
    //     // storedCredentialsHexEncoded默认是true，此时用的是密码加密用的是Hex编码；false时用Base64编码
    //     hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
    //     return hashedCredentialsMatcher;
    // }

    // 配置Cookie对象
    // @Bean(name = "rememberMeCookie")
    // public SimpleCookie rememberMeCookie(){
    //     SimpleCookie cookie = new SimpleCookie();
    //     // 设置cookie名称，对应login.html页面的<input type="checkbox" name="rememberMe"/>
    //     cookie.setName("rememberMe");
    //     // 设置cookie的过期时间，单位为秒
    //     cookie.setMaxAge(rememberMeMaxAge);
    //     return cookie;
    // }
    //
    // // 配置rememberMe Cookie管理器
    // @Bean(name = "rememberMeManager")
    // public CookieRememberMeManager rememberMeManager(@Qualifier("rememberMeCookie") SimpleCookie rememberMeCookie) {
    //     CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
    //     rememberMeManager.setCookie(rememberMeCookie);
    //     // rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
    //     rememberMeManager.setCipherKey(Base64.decode(rememberMeCipherKey));
    //     return rememberMeManager;
    // }


}
