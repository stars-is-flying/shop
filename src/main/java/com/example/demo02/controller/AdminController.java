package com.example.demo02.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo02.model.Admin;
import com.example.demo02.model.LoginRequest;
import com.example.demo02.model.Res;
import com.example.demo02.tool.MD5;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AdminController {

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @PostMapping("/admin/login")
    public Res login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
    String username = loginRequest.getUsername();
    String password = loginRequest.getPassword();
    String hash = MD5.getMD5Hash(password); // Assuming MD5.getMD5Hash() is a valid method.

    String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
    @SuppressWarnings("deprecation")
    List<Admin> users = jdbcTemplate.query(sql, new Object[]{username, hash}, new BeanPropertyRowMapper<>(Admin.class));

    if (users.isEmpty()) {
        return new Res(0, "Login Failed");
    } else {
        Cookie cookie = new Cookie("username", username);
        cookie.setPath("/");
        cookie.setMaxAge(7 * 24 * 60 * 60); // 1 week
        cookie.setHttpOnly(true);
        
        response.addCookie(cookie);
        return new Res(1, "Login Success");
    }
}

}
