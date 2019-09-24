package com.sun.controller;

import com.sun.service.CacheService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ZeRen.
 * @Date: 2019/9/24 15:07
 */
@RestController
public class CacheController {

    @Autowired
    CacheService cacheService;

    @GetMapping(value = "/cache")
    @ApiOperation(value = "缓存测试", notes = "测试缓存")
    public String cacheTest(String id) {
        return cacheService.getOrder(id);
    }


}
