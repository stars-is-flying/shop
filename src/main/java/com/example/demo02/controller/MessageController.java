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

import com.example.demo02.model.Message;
import com.example.demo02.model.MessageRequest;
import com.example.demo02.model.Res;
import com.example.demo02.tool.GetUsernameByCookie;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class MessageController {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostMapping("/user/message/add")
    public Res add(@RequestBody MessageRequest messageRequest, HttpServletRequest request) {
    String message = messageRequest.getMessage();
    String username = GetUsernameByCookie.get(request);
    String sql = "insert into message(username, content) values(?, ?)";
    int status = jdbcTemplate.update(sql, new Object[]{username, message});

    return new Res(status, "add message");
    }

    @GetMapping("/user/message/list")
    public List<Message> listMessage() {
        String sql = "select * from message";
        List<Message> messages = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Message.class));
        return messages;
    }

    @GetMapping("/user/message/delete")
    public Res deleteMessage(@RequestParam("id") int id) {
        String sql = "delete from message where id = ?";
        int status = jdbcTemplate.update(sql, new Object[]{id});
        return new Res(status, "delete message");
    }

}
