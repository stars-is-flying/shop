package com.example.demo02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo02.model.Res;
import com.example.demo02.tool.GetUsernameByCookie;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class AddressController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/user/addr/get")
    public Res getAddr(HttpServletRequest request) {
        String username = GetUsernameByCookie.get(request);
        String sql = "select address from addr where username = ?";
        try {
            @SuppressWarnings("deprecation")
            String address = jdbcTemplate.queryForObject(sql, new Object[]{username}, String.class);
            return new Res(1, address); 
        } catch (Exception e) {
            e.printStackTrace();
            return new Res(-1, "Error retrieving address");
        }
    }

    @PostMapping("/user/addr/new")
    public Res newAddr(HttpServletRequest request, @RequestParam("address") String address) {
        System.out.println("Received address: " + address);  // Add this line to debug
        String username = GetUsernameByCookie.get(request);
        String sql = "insert into addr(username, address) values (?, ?)";
        int status = jdbcTemplate.update(sql, username, address);
        return new Res(status, "new address");
    }

    @PostMapping("/user/addr/ch")
    public Res chAddr(HttpServletRequest request, @RequestParam("address") String address) {
        String username = GetUsernameByCookie.get(request);
        String sql = "update addr set address = ? where username = ?";
        int status = jdbcTemplate.update(sql, address, username);

        return new Res(status, "change address");
    }

    
}
