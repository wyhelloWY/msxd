package com.landian.mashangxiadan.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**配置类
 * @author Yu w
 * @date 2020/4/27 11:09
 */
@Configuration
public class ShiroConfig {

    /**
     * 创建ShiroFilterFantoryBean
     *
     */
    @Bean
    public ShiroFilterFactoryBean ShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        /**
         * Shiro内置过滤器，实现权限相关的拦截器
         * anno:无需认证（登陆）可以访问
         * authc:必须认证才能访问
         * user：如果使用rememberMe的功能可以直接访问
         * perms:该资源必须得到资源权限可以访问
         * role:该资源必须得到角色权限才能访问
         */
        Map<String,String> filterMap = new LinkedHashMap<String,String>();
        filterMap.put("/layuistate/**","anon");
        filterMap.put("/page/index","anon");
        filterMap.put("/user/login","anon");
        shiroFilterFactoryBean.setUnauthorizedUrl("/error/");
        filterMap.put("/**","authc");
        shiroFilterFactoryBean.setLoginUrl("/page/index");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }
    /**
     *创建DefaultWebSecurityManager
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDeafDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    /**
     *创建Realm
     */
    @Bean(name = "userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }
}
