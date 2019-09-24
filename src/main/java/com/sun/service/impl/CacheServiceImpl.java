package com.sun.service.impl;

import com.sun.service.CacheService;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ZeRen.
 * @Date: 2019/9/24 13:59
 * 使用 @EnableCaching 注解开启缓存,当该注解注释在 主方法即
 * @link DemoApplication 类上时,即表示全局开启缓存注解
 */
@Service
@EnableCaching
public class CacheServiceImpl implements CacheService {


    private static Map<String, String> orders = new HashMap<>();

    static {
        orders.put("1", "order = [ id = 1 ... origin]");
        orders.put("2", "order = [ id = 2 ... origin]");
        orders.put("3", "order = [ id = 3 ... origin]");
    }

    @Override
    @Cacheable(cacheNames = "CacheServiceImpl.getOrder", key = "#id")
    public String getOrder(String id) {
        //进入order查询方法体,模拟执行查询sql操作
        System.out.println("select * from order where id = " + id);
        return orders.get(id);
    }

    @Override
    @CachePut(cacheNames = "CacheServiceImpl.getOrder", key = "#id")
    public String update(String id, String content) {
        //进入order更改方法体,模拟执行查询sql update操作
        System.out.println("update order set content = " + content + " where id = " + id);
        orders.put(id, content);
        return orders.get(id);
    }

}
