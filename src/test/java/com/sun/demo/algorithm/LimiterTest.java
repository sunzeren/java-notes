package com.sun.demo.algorithm;

import com.google.common.util.concurrent.RateLimiter;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * 限流算法
 *
 * @author Link Sun
 * 2021/5/19 11:58
 */
public class LimiterTest {


    @Test
    @SuppressWarnings("all")
    public void 令牌桶限流() {
        // 设置 ForkJoinPool.common 的线程池子大小,默认为cpu核数-1
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "128");

        // 提供一个每秒产生n个令牌的限流桶
        RateLimiter limiter = RateLimiter.create(10);
        AtomicInteger counter = new AtomicInteger(1);

        Stream<Integer> infiniteStream = Stream.generate(counter::incrementAndGet);
        infiniteStream
                .parallel()
                .forEach((i) -> {
                    double acquire = limiter.acquire(1);
                    System.out.println(i + ":" + "线程:" + Thread.currentThread().getName() + " | " + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
                });
    }

}
