package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

//    public DemoApplication(){
//        redisTemplate.opsForValue().set("Hello","World");
//        String ans=redisTemplate.opsForValue().get("Hello");
//        System.err.println(ans);
//        Assert.isTrue("World".equals(ans));
//    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
