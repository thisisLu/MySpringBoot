package com.example.demo.controller;

import com.example.demo.model.BUsers;
import com.example.demo.redis.RedisTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    RedisTemplateService redisTemplateService;

    @RequestMapping("/test")
    public Object Redistest(){
        BUsers bUsers = new BUsers();
        bUsers.setUid(1);
        bUsers.setUsername("小鹿");
        bUsers.setPassword("123456");
        bUsers.setJointime(new Date(System.currentTimeMillis()));
        bUsers.setIPsource("0.0.0.0");
        bUsers.setState(0);

        redisTemplateService.set("key1",bUsers);


        BUsers bUsers1 = redisTemplateService.get("key1",BUsers.class);
        return  bUsers1;

    }



}
