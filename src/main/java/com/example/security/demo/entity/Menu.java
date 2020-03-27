package com.example.security.demo.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Menu {

    private Integer id;
    private String pattern;
    private List<OauthRole> roles;
}
