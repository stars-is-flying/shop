package com.example.demo02.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo02.model.Box;
import com.example.demo02.model.Product;
import com.example.demo02.model.Res;
import com.example.demo02.tool.GetUsernameByCookie;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class BoxController {
    

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/user/box/list")
    public List<Box> listBox(HttpServletRequest request) {
        String username = GetUsernameByCookie.get(request);
        String sql = "select * from box where username = ?";
        @SuppressWarnings("deprecation")
        List<Box> boxs = jdbcTemplate.query(sql, new Object[]{username}, new BeanPropertyRowMapper<>(Box.class));
        return boxs;
    }

    @GetMapping("/user/box/add")
    public Res addBox(@RequestParam("name") String name, HttpServletRequest request) {
        String usename = GetUsernameByCookie.get(request);
        int price = getPriceByName(name);

        String sql = "insert into box(username, name, price) values(?,?,?)";
        int res = jdbcTemplate.update(sql, new Object[]{usename, name, price});

        return new Res(res, "add box");
    }

    private int getPriceByName(String name) {
        String sql = "select * from product where name = ?";
        @SuppressWarnings("deprecation")
        List<Product> products = jdbcTemplate.query(sql, new Object[]{name}, new BeanPropertyRowMapper<>(Product.class));

        return products.get(0).getPrice();
    }
    
    @GetMapping("/user/box/delete")
    public  Res deleteBox(@RequestParam("id") int id) {
        String sql = "delete from box where id = ?";
        int status = jdbcTemplate.update(sql, new Object[]{id});

        return new Res(status, "delete box");
    }

    @GetMapping("/user/box/deleteall")
    public  Res deleteBox(HttpServletRequest request) {
        String username = GetUsernameByCookie.get(request);
        String sql = "delete from box where username = ?";
        int status = jdbcTemplate.update(sql, new Object[]{username});

        return new Res(status, "delete all box");
    }
    
}
