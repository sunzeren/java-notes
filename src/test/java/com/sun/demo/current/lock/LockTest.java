package com.sun.demo.current.lock;

import com.sun.demo.current.ThreadTest;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: ZeRen.
 * @Date: 2020/7/27 14:58
 */
public class LockTest extends ThreadTest {

    // 可重入锁
    private Lock reentrantLock = new ReentrantLock();

    @Test
    public void lockInterruptiblyTest() {
        final Thread thread_1 = new Thread(() -> {
            reentrantLock.lock();

            //try {
            //    reentrantLock.lockInterruptibly();
            //} catch (InterruptedException e) {
            //    log("被其他线程中断了!");
            //    reentrantLock.unlock();
            //}

            log("获取了锁");
            //sleep(10,TimeUnit.SECONDS);
        });
        thread_1.start();

        sleep(1, TimeUnit.SECONDS);

        reentrantLock.lock();

        try {
            log("尝试获取锁");
            if (reentrantLock.tryLock(2, TimeUnit.SECONDS)) {
                log("获取了锁");
            } else {
                log("等了会儿仍没有获取到锁,将执行中断");
                thread_1.interrupt();
                reentrantLock.lock();
                log("获取了锁");
                sleep(1, TimeUnit.SECONDS);
                reentrantLock.unlock();
                log("释放了锁");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("is done");
    }

}
