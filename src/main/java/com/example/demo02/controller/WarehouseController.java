package com.example.demo02.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo02.model.Warehouse;

@RestController
public class WarehouseController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/third/warehouse/list")
    public List<Warehouse> list() {
        String sql = "select * from warehouse";
        List<Warehouse> warehouses = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Warehouse.class));

        return warehouses;
    }

    
}
