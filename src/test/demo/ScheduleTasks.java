package com.example.test;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class ScheduleTasks {

    @Resource
    private StringRedisTemplate redisTemplate;

    @Scheduled(cron = "*/5 * * * * ?")//每隔5秒执行一次
    public void runTestTask() {
        System.out.println("初始化任务1"+(new Date()));
        redisTemplate.opsForValue().set("liwenqiang","dashuaige");
        System.out.println("结束任务1"+(new Date()));
    }
}