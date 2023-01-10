package com.example.demo.config.shiro;

// import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
// import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import lombok.extern.slf4j.Slf4j;

// import java.util.Set;

import com.example.demo.domain.User;
import com.example.demo.mapper.UserMapper;

@Slf4j
public class ShiroRealm extends AuthorizingRealm {
    @Lazy
    @Autowired
    private UserMapper userMapper;

    // TO-DO
    // Perhaps we can add a role-based database


    // 验证用户身份
    // @param authenticationToken
    // @return AuthenticationInfo
    // @throws AuthenticationException

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        Long userId = userMapper.findUserByName(username);
        log.info("find userId %ld", userId);
        if(userId==null){
            throw new UnknownAccountException("No such user stored");
        }

        User user = userMapper.findOne(userId);
        log.info("User is %v", user);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, null, getName());
        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("do nothing here");
        return null;
    }
}
