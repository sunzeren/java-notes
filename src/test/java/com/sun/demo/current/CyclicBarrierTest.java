package com.sun.demo.current;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Author by Sun, Date on 2020/7/29.
 * PS: Not easy to write code, please indicate.
 * <p>
 * CyclicBarrier 适用于几个线程之间的互相等待
 * 且 parties值会自动重置
 */
public class CyclicBarrierTest extends ThreadTest {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> {
            System.out.println(" ok ");
        });

        new Thread(() -> {
            try {
                log("start");
                Thread.sleep(1000);
                cyclicBarrier.await();
                log("done");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

        }).start();

        new Thread(() -> {
            try {
                log("start");
                Thread.sleep(3000);
                cyclicBarrier.await();
                log("done");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

        }).start();


    }
}
