package com.sun.demo.spring;

import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @Author: ZeRen.
 * @Date: 2020/1/2 18:56
 * 此类探究的是,在main方法中如何极简得使用RedisTemplate(归功于SpringBoot及其约定大于配置的设计思想)
 */
public class SpringRedisTest {

    // SpringBoot提供的原生的Redis连接工厂
    private static final LettuceConnectionFactory factory = new LettuceConnectionFactory();
    // 一般我们使用 StringRedisTemplate,因为默认的RedisTemplate其键值序列化方式为JdkSerializationRedisSerializer
    // 其键值序列化为默认jdk序列化存储,不利于阅读
    private static final StringRedisTemplate redisTemplate = new StringRedisTemplate(factory);

    static {
        // 初始化操作
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
