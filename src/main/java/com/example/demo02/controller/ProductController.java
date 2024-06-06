package com.example.demo02.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo02.model.Product;
import com.example.demo02.model.Res;

@RestController
public class ProductController {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/user/shop")
    public List<Product> listProduct() {
        String sql = "select * from product";
        List<Product> products = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));
        return products;
    }

    @PostMapping("/product/add")
    public Res productAdd(@RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("price") int price, @RequestParam("url") String url) {
        String sql = "insert into product(name, description, price, url) values(?, ?,?,?,?)";
        int status = jdbcTemplate.update(sql, new Object[]{name, description, price, url});

        return new Res(status, "add product");
    }
}
