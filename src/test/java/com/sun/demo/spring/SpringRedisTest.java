package com.sun.demo.spring;

import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @Author: ZeRen.
 * @Date: 2020/1/2 18:56
 * 此类探究的是,在main方法中如何极简得使用RedisTemplate(归功于SpringBoot及其约定大于配置的设计思想)
 */
public class SpringRedisTest {

    private static final LettuceConnectionFactory factory = new LettuceConnectionFactory();
    private static final StringRedisTemplate redisTemplate = new StringRedisTemplate(factory);

    static {
        factory.afterPropertiesSet();
    }

    public static void main(String[] args) {
        final String key = "111";
        setVal(key, "222");
        System.out.println(getVal(key));
    }

    private static void setVal(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    private static String getVal(String key) {
        return redisTemplate.opsForValue().get(key);
    }

}
