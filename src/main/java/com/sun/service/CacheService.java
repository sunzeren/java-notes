package com.sun.service;

import com.sun.config.CacheConfig;

/**
 * @Author: ZeRen.
 * @Date: 2019/9/24 13:59
 */
public interface CacheService {

    /**
     * 查询 id 订单,并缓存
     *
     * @param id
     * @return
     */
    String getOrder(String id);

    /**
     * 更新订单,并更新缓存
     *
     * @param id
     * @param content
     * @return
     */
    String update(String id, String content);

    /**
     * 设置一个有过期时间的缓存
     * <p>
     * 可在此处配置过期时间
     *
     * @see CacheConfig#getRedisCacheConfigurationMap()
     */
    String expire(String id);
}
