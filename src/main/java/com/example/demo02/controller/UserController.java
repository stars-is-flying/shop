package com.example.demo02.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo02.model.LoginRequest;
import com.example.demo02.model.Res;
import com.example.demo02.model.User;
import com.example.demo02.tool.MD5;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class UserController {

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @GetMapping("/user/list")
    public List<User> getUserList() {
        String sql = "select * from user";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));

        return users;
    }

    @GetMapping("/user/delete")
    public Res deleteUser(@RequestParam("id") int id) {
        String sql = "delete from user where id = ?";
        int status = jdbcTemplate.update(sql, new Object[]{id});

        return new Res(status, "delete user");
    }

    @PostMapping("/user/register")
    public Res addUser(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        String hash = MD5.getMD5Hash(password);
        String sql = String.format("insert into user(username, password) values(?, ?)", username, hash);
        int status = jdbcTemplate.update(sql, new Object[]{username, hash});

        return (new Res(status, "add user"));
    }

    @PostMapping("/user/login")
    public Res login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
    String username = loginRequest.getUsername();
    String password = loginRequest.getPassword();
    String hash = MD5.getMD5Hash(password); // Assuming MD5.getMD5Hash() is a valid method.

    String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
    @SuppressWarnings("deprecation")
    List<User> users = jdbcTemplate.query(sql, new Object[]{username, hash}, new BeanPropertyRowMapper<>(User.class));

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
