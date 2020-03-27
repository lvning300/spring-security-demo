package com.example.security.demo.component;

import com.example.security.demo.dao.MenuMapper;
import com.example.security.demo.entity.Menu;
import com.example.security.demo.entity.OauthRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {


    @Autowired
    MenuMapper menuMapper;

    // 创建一个AnipathMatcher，主要用来实现ant风格的URL匹配。
    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

        // 从参数中提取出当前请求的URL
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        // 从数据库中获取所有的资源信息，即本案例中的menu表以及menu所对应的role
        // 在真实项目环境中，开发者可以将资源信息缓存在Redis或者其他缓存数据库中。
        List<Menu> allMenus = menuMapper.getAllMenus();
        // 遍历资源信息，遍历过程中获取当前请求的URL所需要的角色信息并返回。
        for (Menu menu : allMenus) {
            if (antPathMatcher.match(menu.getPattern(), requestUrl)) {
                List<OauthRole> roles = menu.getRoles();
                String[] roleArr = new String[roles.size()];
                for (int i = 0; i < roleArr.length; i++) {
                    roleArr[i] = roles.get(i).getName();
                }
                if (Objects.nonNull(roleArr) && roleArr.length > 0) {
                    return SecurityConfig.createList(roleArr);
                }
            }
        }

        // 如果当前请求的URL在资源表中不存在相应的模式，就假设该请求登录后即可访问，即直接返回 ROLE_LOGIN.
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    // 该方法用来返回所有定义好的权限资源，Spring Security在启动时会校验相关配置是否正确。
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    // supports方法返回类对象是否支持校验。
    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
