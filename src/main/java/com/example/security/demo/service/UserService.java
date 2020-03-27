package com.example.security.demo.service;

import com.example.security.demo.dao.OauthUserMapper;
import com.example.security.demo.entity.OauthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    OauthUserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final OauthUser oauthUser = userMapper.loadUserByUsername(username);

        if (Objects.isNull(oauthUser)) {
            throw new UsernameNotFoundException("账户不存在!");
        }
        oauthUser.setRoles(userMapper.getUserRolesByUid(oauthUser.getId()));

        return oauthUser;
    }

    public static String encodePasswod(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        return encoder.encode(password);
    }


}
