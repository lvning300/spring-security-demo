package com.example.security.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.security.demo.entity.OauthRole;
import com.example.security.demo.entity.OauthUser;

import java.util.List;

/**
 * @author author
 */
public interface OauthUserMapper extends BaseMapper<OauthUser> {

    OauthUser loadUserByUsername(String username);
    List<OauthRole> getUserRolesByUid(Integer id);

}