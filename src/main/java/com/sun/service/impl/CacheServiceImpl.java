package com.sun.service.impl;

import com.sun.service.CacheService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ZeRen.
 * @Date: 2019/9/24 13:59
 */
@Service
@EnableCaching
public class CacheServiceImpl implements CacheService {


    private static Map<String, String> orders = new HashMap<>();

    static {
        orders.put("1", "order = [ id = 1 ...]");
        orders.put("2", "order = [ id = 2 ...]");
        orders.put("3", "order = [ id = 3 ...]");
    }

    @Override
    @Cacheable(cacheNames = "CacheServiceImpl.getOrder")
    public String getOrder(String id) {
        //进入order查询方法体,模拟执行查询sql操作
        System.out.println("select * from order where id = " + id);
        return orders.get(id);
    }


}
