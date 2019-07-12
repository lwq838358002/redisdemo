package com.controller;

import com.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisContoller {


    @Autowired
    private RedisService redisService;

    @RequestMapping(value = "/first",method = RequestMethod.GET)
    public Object firstredis(){
        redisService.set("dahuang","shuaibi", (long) 100);
        redisService.set("dahuanging","dahuangzhong");
        Object data=redisService.get("dahuang");
        System.err.println(data);
        System.err.println(redisService.get("dahuanging"));
        return "hello redis";
    }
}
