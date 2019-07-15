package com.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;


@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
    Logger logger= LoggerFactory.getLogger(RedisConfig.class);

//    @Value("${spring.redis.host}")
//    private String host;
//
//    @Value("${spring.redis.port}")
//    private int port;
//
// @Value("${spring.redis.timeout}")
// private int timeout;
//
//  @Value("${spring.redis.pool.max-idle}")
//    private int maxIdle;
//
//    @Value("${spring.redis.pool.max-wait}")
//    private long maxWaitMillis;

//    @Value("${spring.redis.password}")
//    private String password;

//    @Bean
//    public JedisPool redisPoolFactory(){
//        logger.info("JedisPool注入成功");
//        JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
//
//        JedisPool jedisPool=new JedisPool(jedisPoolConfig,host,port,timeout,password);
//
//        return jedisPool;
//    }
//
//    @Bean
//    public RedisTemplate<String,String> redisTemplate(RedisConnectionFactory cf){
//        RedisTemplate<String,String> redisTemplate= new RedisTemplate<String, String>();
//        redisTemplate.setConnectionFactory(cf);
//        return redisTemplate;
//    }

//    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory1) {
        StringRedisTemplate template = new StringRedisTemplate(factory1);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

}
