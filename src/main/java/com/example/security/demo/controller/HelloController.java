package com.example.security.demo.controller;

import com.example.security.demo.service.MethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class HelloController {

    @Autowired
    MethodService methodService;


    @GetMapping("/hello")
    public String hello() {
        return "欢迎访问 lvning.com";
    }

   /* @GetMapping("/admin/hello")
    public String admin() {
        return "hello admin";
    }

    @GetMapping("/user/hello")
    public String user() {
        return "hello user";
    }

    @GetMapping("/db/hello")
    public String db() {
        return "hello db";
    }
*/


    @GetMapping("/admin")
    public String admin() {
        return methodService.admin();
    }

    @GetMapping("/dba")
    public String dba() {
        return methodService.dba();
    }

    @GetMapping("/user")
    public String user() {
        return methodService.user();
    }



    @GetMapping("/login")
    public void login(HttpServletResponse response) throws IOException {
        response.sendRedirect("login_page");
    }
}
