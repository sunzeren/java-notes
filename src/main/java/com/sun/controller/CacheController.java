package com.sun.controller;

import com.sun.service.CacheService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ZeRen.
 * @Date: 2019/9/24 15:07
 * 缓存测试控制器
 */
@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    CacheService cacheService;
    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping(value = "/find")
    @ApiOperation(value = "缓存测试", notes = "测试缓存")
    public String cacheTest(String id) {
        return cacheService.getOrder(id);
    }

    @GetMapping(value = "/update")
    @ApiOperation(value = "缓存测试", notes = "测试缓存")
    public String cacheTest(String id, String content) {
        return cacheService.update(id, content);
    }

    @GetMapping(value = "/expire")
    @ApiOperation(value = "缓存过期测试", notes = "测试缓存")
    public String cacheExpiredTest(String id) {
        String expire = cacheService.expire(id);
        try {
            Thread.sleep(1000 * 5);
            String after_5seconds = cacheService.expire(id);
            System.out.println(after_5seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return expire;
    }

}
