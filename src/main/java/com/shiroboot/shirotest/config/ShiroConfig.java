package com.shiroboot.shirotest.config;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /**
     * 将自己的验证方式加入容器
     *
     * @return
     */
    @Bean
    public MyRealm myShiroRealm() {
        MyRealm myShiroRealm = new MyRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
        // 合并分支
        // 其他部分的修改
        // 主分支修改部分
        // 主分支自己修改部分

        /**
         * 分支新添加内容
         * 测试用
          */

    }

    /**
     * 权限管理，配置主要是Realm的管理认证
     *
     * @return
     */
    @Bean
    public SecurityManager securityManager(RememberMeManager rememberMeManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        securityManager.setRememberMeManager(rememberMeManager);
        return securityManager;
    }

    /**
     * Filter工厂，设置对应的过滤条件和跳转条件
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> map = new HashMap<String, String>();
        //登出
        map.put("/logout", "logout");
        //对所有用户认证
        map.put("/**", "user");
        map.put("/regest", "anon");
        map.put("/regestPage", "anon");

        //登录
        shiroFilterFactoryBean.setLoginUrl("/login");
        //首页
        shiroFilterFactoryBean.setSuccessUrl("/index");

        //错误页面，认证不通过跳转
        shiroFilterFactoryBean.setUnauthorizedUrl("/error");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    /**
     * 配置加密匹配器, 可以对二次加密的密码进行匹配
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setHashIterations(1024);

        return hashedCredentialsMatcher;
    }

    /**
     * 加入注解的使用，不加入这个注解不生效
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        Class<SessionManager> sessionManagerClass = SessionManager.class;
        return authorizationAttributeSourceAdvisor;
    }

    /*================以下为RememberMe功能所需的配置========================*/
    /*=====以下配置结束后, 还要再SecurityManager中配置RememberMe管理器======*/

    /**
     * 设置Cookie
     *
     * @return
     */
    @Bean
    public SimpleCookie simpleCookie() {
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60 * 8);
        cookie.setPath("/");
        return cookie;
    }

    /**
     * 设置管理器, 其中可以对Cookie进行二次加密
     *
     * @param
     * @return
     */
    @Bean
    public RememberMeManager rememberMeManager(SimpleCookie cookie) {
        CookieRememberMeManager manager = new CookieRememberMeManager();
        manager.setCookie(cookie);
        // 加密的密钥, 可以自己指定
        manager.setCipherKey(Base64.decode("U3ByaW5nQmxhZGUAAAAAAA=="));
        return manager;
    }

    public static void main(String[] args) {
        byte[] decode = Base64.decode("U3BAbW5nQmxhZGUAAAAAAA==");
        System.out.println(decode.length);


    }
}
