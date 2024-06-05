package com.example.demo02.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo02.model.Order;
import com.example.demo02.model.Res;
import com.example.demo02.tool.GetUsernameByCookie;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class OrderController {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/user/order/submit")
    public Res submitOrder(HttpServletRequest request, @RequestParam("sum") int sum) {
        String username = GetUsernameByCookie.get(request);
        String sql = "insert into `order` (username, sum) values(?,?)";
        int status = jdbcTemplate.update(sql, new Object[]{username, sum});

        return new Res(status, "submit order");
    }

    @GetMapping("/user/order/list")
    public List<Order> getOrderList() {
        String sql = "select * from `order`";
        List<Order> orders = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Order.class));

        return orders;
    }

    @GetMapping("/user/order/delete")
    public Res deleteOrder(@RequestParam("id") int id) {
        String sql = "delete from `order` where id = ?";
        int status = jdbcTemplate.update(sql, new Object[]{id});

        return new Res(status, "delete order");
    }



}
