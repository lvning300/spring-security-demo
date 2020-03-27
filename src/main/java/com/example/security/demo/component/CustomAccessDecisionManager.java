package com.example.security.demo.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Objects;

@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {


    @Autowired
    RoleHierarchy roleHierarchy;

    // 该方法判断当前登录的用户是否具备当前请求URL所需要的角色信息
    @Override
    public void decide(Authentication auth,
                       Object object,
                       Collection<ConfigAttribute> ca) {
        Collection<? extends GrantedAuthority> auths = auth.getAuthorities();

        if (object instanceof FilterInvocation) {
            final String requestUrl = ((FilterInvocation) object).getRequestUrl();
            if (Objects.equals(requestUrl, "/login_page")) {
                return;
            }
        }
        // 如果具备权限，则不做任何事情即可
        for (ConfigAttribute configAttribute : ca) {
            // 如果需要的角色是ROLE_LOGIN，说明当前请求的URL用户登录后即可访问
            // 如果auth是UsernamePasswordAuthenticationToken的实例，说明当前用户已登录，该方法到此结束
            if ("ROLE_LOGIN".equals(configAttribute.getAttribute())
                    && auth instanceof UsernamePasswordAuthenticationToken) {
                return;
            }

            final Collection<GrantedAuthority> grantedAuthorities = (Collection<GrantedAuthority>) roleHierarchy.getReachableGrantedAuthorities(auths);
            // 否则进入正常的判断流程
            for (GrantedAuthority authority : grantedAuthorities) {
                // 如果当前用户具备当前请求需要的角色，那么方法结束。
                if (configAttribute.getAttribute().equals(authority.getAuthority())) {
                    return;
                }
            }


        }

        // 如果不具备权限，就抛出AccessDeniedException异常
        throw new AccessDeniedException("权限不足");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
