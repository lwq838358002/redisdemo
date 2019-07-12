package com.demo;

import com.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
//        redisService.set("dahuang","shuaibi", (long) 10000);
//        Object data=redisService.get("dahuang");
//        System.err.println(data);
    }

}
