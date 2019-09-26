package com.sun.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ZeRen.
 * @Date: 2019/9/25 10:42
 * @title @Cacheable 注解并没有给可提供键过期时间的操作, 只可使用CacheManger来管理有过期值的键
 * 该配置文件,大部分使用SpringBoot默认配置,仅加入了有期限缓存的键
 */
@Configuration
public class CacheConfig {

    /**
     * 分析:在使用中,使用下面被注释的代码时,出现了序列化异常问题,但不配置CacheConfig却能正常运行,
     * 但项目中有需求为配置有过期时间的key,所以思路就是使用SpringBoot默认的配置,来排查原因
     */

    /**
     * CacheConfig中仅保留 --- 分析 --- 中的代码,运行后,即可查看CacheManager的默认SpringBoot 注入的Bean的配置方式
     * ide 点击 CacheManager 左侧的绿色圆圈里有箭头的按钮,进入SpringBoot 配置Bean的默认方法
     * 观察得知,须有一个 ResourceLoader 传入,在 RedisCacheConfiguration 中的 valueSerializationPair ,即序列化键的value时使用此ClassLoader
     * 以上,问题得以解决
     * 总结: 本次经历虽耗费半天时间,但却体现了个SpringBoot配置问题的解决思路,仅此记录,望抛砖引玉
     */

    //------------------- 分析--------------------
    @Autowired
    CacheManager cacheManager;

    @PostConstruct
    public void viewCacheManager() {
        RedisCacheManager redisCacheManager = (RedisCacheManager) this.cacheManager;
        System.out.println(redisCacheManager);
    }
    //------------------- 分析--------------------

    @Autowired
    ResourceLoader resourceLoader;

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(getDefaultCacheConfiguration())//默认的缓存配置(没有配置键的key均使用此配置)
                .withInitialCacheConfigurations(getCacheConfigurations())
                .transactionAware() //在spring事务正常提交时才缓存数据
                .build();
    }

    private Map<String, RedisCacheConfiguration> getCacheConfigurations() {
        Map<String, RedisCacheConfiguration> configurationMap = new HashMap<>();

        //缓存键,且30秒后过期,再次调用方法时需要重新缓存
        configurationMap.put("expireKey", this.getDefaultCacheConfiguration(30));

        return configurationMap;
    }

    private RedisCacheConfiguration getDefaultCacheConfiguration() {
        // 注意此构造函数为 spring-data-redis-2.1.9 及以上拥有,经试验 已知spring-data-redis-2.0.9及以下版本没有此构造函数
        // 但观察源码可得核心不过是在值序列化器(valueSerializationPair)的构造中注入 ClassLoader 即可
        return RedisCacheConfiguration.defaultCacheConfig(resourceLoader.getClassLoader());
    }

    private RedisCacheConfiguration getDefaultCacheConfiguration(int seconds) {
        //设置键过期的时间,用 java.time 下的Duration表示持续时间,进入entryTtl()方法的源码中可看到
        //当设置为 0 即 Duration.ZERO 时表示键无过期时间,默认配置
        return getDefaultCacheConfiguration().entryTtl(Duration.ofSeconds(seconds));
    }


    /**
     * ------------------------
     * 使用以下配置时,在真实场景下出现了序列化问题ClassCase异常
     * 现已更改为以上配置,代码更加简洁,除了添加了一个可过期的key,其余均使用springboot的默认配置
     * ------------------------
     */

    /*
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        return new RedisCacheManager(
                RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
                // 默认策略，未配置的 key 会使用这个,当设置为0时表示没有过期时间
                this.getRedisCacheConfigurationWithTtl(0),
                this.getRedisCacheConfigurationMap() // 指定 key 策略
        );
    }

    public Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap() {
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
        redisCacheConfigurationMap.put("expireKey", this.getRedisCacheConfigurationWithTtl(30));

        return redisCacheConfigurationMap;
    }

    private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Integer seconds) {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        redisCacheConfiguration = redisCacheConfiguration.serializeValuesWith(
                RedisSerializationContext
                        .SerializationPair
                        .fromSerializer(jackson2JsonRedisSerializer)
        ).entryTtl(Duration.ofSeconds(seconds));

        return redisCacheConfiguration;
    }
     */
}
