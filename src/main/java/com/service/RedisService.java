package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
@Component
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 写入缓存
     * @param  key
     * @param value
     * @return
     */
    public boolean set (final String key,Object value){
        boolean result=false;
        try {
            ValueOperations<Serializable,Object> operations=redisTemplate.opsForValue();
            operations.set(key,value);
            result=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存设置时效时间
     * @param key
     * @param value
     * @param expiretime
     * @return
     */
    public boolean set(final String key,Object value,Long expiretime){
        boolean result=false;
        try {
            ValueOperations<Serializable,Object> operations=redisTemplate.opsForValue();
            operations.set(key,value);
            redisTemplate.expire(key,expiretime, TimeUnit.SECONDS);
            result=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  result;
    }

    /**
     * 批量删除对应的value
     * @param keys
     */
    public void remove(final String... keys){
        for (String key: keys){
            remove(key);
        }
    }
    /**
     * 删除对应的value
     * @param key
     */
    public  void remove(String key){
        if (exists(key)){
            redisTemplate.delete(key);
        }
    }
    /**
     * 判断缓存中是否有对应的value
     * @param key
     * return
     */
    public boolean exists(final String key){
        return redisTemplate.hasKey(key);
    }
    /**
     * 读取缓存
     * @param key
     * @return
     */
    public Object get(final String key){
        Object result=null;
        ValueOperations<Serializable,Object> operations=redisTemplate.opsForValue();
        result=operations.get(key);
        return result;
    }
    /**
     * 哈希 添加
     * @param key
     * @param hashkey
     * @param value
     */
    public void hmset(String key,Object hashkey,Object value){
        HashOperations<String,Object,Object> hash=redisTemplate.opsForHash();
        hash.put(key,hashkey,value);
    }
    /**
     * 哈希获取数据
     * @param key
     * @param hashkey
     * @return
     */
    public Object hmGet(String key,Object hashkey){
        HashOperations<String,Object,Object> hash=redisTemplate.opsForHash();
        return hash.get(key,hashkey);
    }
    /**
     * 列表添加
     * @param k
     * @param v
     */
    public void lpush(String k,String v){
        ListOperations<String,Object> listOperations=redisTemplate.opsForList();
        listOperations.rightPush(k,v);
    }
    /**
     *列表获取
     * @param k
     * @param l
     * @param l1
     * @return
     */
    public List<Object> lRange(String k, long l, long l1){
        ListOperations<String,Object> listOperations=redisTemplate.opsForList();
        return listOperations.range(k,l,l1);
    }
    /**
     * 集合添加
     * @param key
     * @param value
     */
    public void add(String key,Object value){
        SetOperations setOperations=redisTemplate.opsForSet();
        setOperations.add(key,value);
    }
    /**
     * 集合获取
     * @param key
     * @return
     */
    public Set<Object> setMembera(String key){
        SetOperations<String,Object> set=redisTemplate.opsForSet();
        return set.members(key);
    }
    /**
     * 有序集合添加
     * @param key
     * @param value
     * @param scoure
     */
    public void zAdd(String key,Object value,double scoure){
        ZSetOperations zSetOperations=redisTemplate.opsForZSet();
        zSetOperations.add(key,value,scoure);
    }
    /**
     * 有序集合获取
     * @param key
     * @param scoure
     * @param scoure1
     * @return
     */
    public Set<Object> rangeByScore(String key,double scoure,double scoure1){
        ZSetOperations<String,Object> zSetOperations=redisTemplate.opsForZSet();
        return zSetOperations.rangeByScore(key,scoure,scoure1);
    }
}
