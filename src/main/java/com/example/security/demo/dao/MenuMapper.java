package com.example.security.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.security.demo.entity.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> getAllMenus();
}
