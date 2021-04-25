package com.landian.mashangxiadan.shiro;

import com.landian.mashangxiadan.pojo.UserInfo;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

/** 自定义Realm
 * @author Yu w
 * @date 2020/4/27 11:14
 */
public class UserRealm extends AuthorizingRealm {
    @Override
    /**
     * 执行授权逻辑
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        return null;
    }

    @Override
    /**
     * 执行认证逻辑
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        System.out.println("执行认证逻辑");
        //编写shiro判断逻辑 判断用户名和密码】、
        //1.判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        //UserList user = userListService.findByNaAndPa(token.getUsername());
        UserInfo userInfo = new UserInfo();
        userInfo.setUser_name("admin");
        userInfo.setUser_pass("123456");
        //2.判断密码
        return new SimpleAuthenticationInfo("admin","123456","");
    }
}
