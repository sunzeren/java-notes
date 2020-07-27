package com.sun.demo.current.lock;

import com.sun.demo.current.ThreadTest;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: ZeRen.
 * @Date: 2020/7/27 14:58
 *
 * ReentrantLock 即可重入锁
 * 与java的synchronized关键字,极其相似,不过其提供更多灵活操作
 * 如在构造方法中可指定,是否为公平锁(即总是等待时间最长的线程获取此锁),默认为非公平锁
 */
public class ReentrantLockTest extends ThreadTest {

    private static final Object lock = new Object();
    // 可重入锁
    private Lock reentrantLock = new ReentrantLock();

    @Test
    public void lockTest() {

        final Thread thread_1 = new Thread(() -> {
            reentrantLock.lock();
            log("第一次获取锁");
            try {
                // 因为是可重入锁, 当前线程持有锁时,会二次加锁时会立即返回,不会导致线程等待
                reentrantLock.lock();
                log("第二次获取锁");
                sleep(2, TimeUnit.SECONDS);
            } finally {
                reentrantLock.unlock();
                // 此处注意,加锁时是holds的增量操作,
                // 所以加了几次锁,就要释放几次锁 否则可能导致其他线程无法获取到此锁
                // 如果注释下面这一行,则会导致主线程无法获取锁,而永远等待下去
                reentrantLock.unlock();
                log("释放了锁");
            }
        });
        thread_1.start();

        sleep(1, TimeUnit.SECONDS);


        reentrantLock.lock();
        log("获取了锁");
        try {
            sleep(1, TimeUnit.SECONDS);
        } finally {
            reentrantLock.unlock();
            log("释放了锁");
        }

        System.out.println("is done");
    }

    @Test
    public void syncTest() {
        final Thread thread_1 = new Thread(() -> {
            synchronized (lock) {
                log("第一次获取锁");
                synchronized (lock) {
                    log("第二次获取锁");
                    sleep(2, TimeUnit.SECONDS);
                }
                // synchronized代码块 执行完毕后会自动释放锁
                log("释放了锁");
            }
            log("释放了锁");
        });
        thread_1.start();

        sleep(1, TimeUnit.SECONDS);


        //region main blocking
        synchronized (lock) {
            log("获取了锁");
            sleep(1, TimeUnit.SECONDS);
        }
        log("释放了锁");
        //endregion

        System.out.println("is done");

    }

}
