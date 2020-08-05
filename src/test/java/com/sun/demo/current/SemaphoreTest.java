package com.sun.demo.current;

import java.util.concurrent.Semaphore;

/**
 * Author by Sun, Date on 2020/7/29.
 * PS: Not easy to write code, please indicate.
 */
public class SemaphoreTest {

    public static final Semaphore semaphore = new Semaphore(10);

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(SemaphoreTest::doSomething).start();
        }
    }

    public static void doSomething() {
        try {
            semaphore.acquire();
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " done");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

}
