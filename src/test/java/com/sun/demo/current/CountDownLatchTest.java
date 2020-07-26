package com.sun.demo.current;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: ZeRen.
 * @Date: 2020/7/20 16:09
 * <p>
 * 使用 CountDownLatchTest ,实现多个线程,同时运行执行
 * 且主线程等待所有线程执行完毕后,再结束运行
 * <p>
 * 可用于测试并发
 */
public class CountDownLatchTest {

    private final CountDownLatch taskLatch;
    private final CountDownLatch mainLatch;
    protected int taskCount;
    private AtomicInteger safeCount = new AtomicInteger(0);
    private int notSafeCount = 0;
    private Runnable runnable;

    public CountDownLatchTest(int count) {
        taskCount = count;
        runnable = this::exec;
        taskLatch = new CountDownLatch(count);
        mainLatch = new CountDownLatch(count);
    }

    public CountDownLatchTest(int count, Runnable task) {
        taskCount = count;
        runnable = task;
        taskLatch = new CountDownLatch(count);
        mainLatch = new CountDownLatch(count);
    }

    public static void main(String[] args) {
        final CountDownLatchTest latchTest = new CountDownLatchTest(1000);
        latchTest.run();
    }

    public void run() {
        for (int i = 0; i < taskCount; i++) {
            new Thread(() -> {
                try {
                    taskLatch.await();
                    runnable.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    mainLatch.countDown();
                }
            }).start();
            taskLatch.countDown();
        }
        System.out.println("所有线程启动完成");

        try {
            System.out.println("主线程等待所有线程执行完成");
            mainLatch.await();
            System.out.println("所有线程执行完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("安全的计数Count:" + safeCount);
        System.out.println("不安全的计数Count:" + notSafeCount);
        System.out.println("执行完毕");

        // 适用于 多个异步任务同时执行, 其可拿到最先执行的结果
        final ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<>(Executors.newCachedThreadPool());
        // 调用此方法,会导致调用的线程阻塞,且返回的是最先执行完任务的结果
        try {
            final Integer integer = completionService.take().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


    }

    public void exec() {
        try {
            // 模拟任务执行
            Thread.sleep(1000);
            for (int i = 0; i < 1000; i++) {
                safeCount.incrementAndGet();
                notSafeCount++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
