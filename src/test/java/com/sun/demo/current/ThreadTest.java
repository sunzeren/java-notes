package com.sun.demo.current;

import java.util.concurrent.TimeUnit;

/**
 * @Author: ZeRen.
 * @Date: 2020/7/27 15:29
 */
public class ThreadTest {

    protected static void sleep(int t, TimeUnit u) {
        try {
            u.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void log(String message) {
        System.out.println(Thread.currentThread().getName() + ":" + message);
    }

}
