package com.sun.demo.current.pool;

import com.sun.demo.io.serialize.ObjectSerializeTest;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Author by Sun, Date on 2020/8/22.
 * PS: Not easy to write code, please indicate.
 * 本类主要探寻 ， 线程池内存溢出时 发生的 OutOfMemoryError 异常后，线程的处理
 * // TODO: 2020/8/23 待完善探讨
 */
public class ThreadPoolExceptionTest {

    public static final ArrayList<ObjectSerializeTest.ByteDataModel> modelDataList = new ArrayList<>();

    public static List<Throwable> error = new ArrayList<>();


    /**
     * 此测试类 ， 主要用于观察 ，当发生 内存溢出异常时，线程池如何处理
     *
     * @param args
     */
    public static void main(String[] args) {
        BasicThreadFactory factory = new BasicThreadFactory.Builder()
                .namingPattern("scheduled-executor-%d")
                .uncaughtExceptionHandler((thread, throwable) -> {
                    // 值得一提的是，发生异常时，虽然走到了这里，但并没有输出到控制台
                    System.err.println(String.format("线程:%s,发生了异常:%s", thread.getName(), thread));
                    error.add(throwable);
                })
                .build();
        Runnable runnable = () -> {
            for (int i = 0; i < 100000; i++) {
                modelDataList.add(new ObjectSerializeTest.ByteDataModel("title:" + i, new Date(), null));
            }
            System.out.println(modelDataList.size());

            if (modelDataList.size() == 100000 * 2) {
                throw new RuntimeException("测试异常! 数量过大:" + modelDataList.size());
            }
        };


        runTask(factory, runnable);

        boolean throwErrorFlag = false;
        while (true) {
            if (!throwErrorFlag && (!error.isEmpty())) {
                System.err.println("main- 发生异常:" + error.get(0));
                throwErrorFlag = true;
            }
        }
    }

    private static void runTask(BasicThreadFactory factory, Runnable runnable) {
        // runOfScheduled(factory, runnable);
        runOfFixPool(factory, runnable);
    }

    private static void runOfFixPool(BasicThreadFactory factory, Runnable runnable) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1, factory);
        while (true) {
            fixedThreadPool.submit(runnable);
        }
    }


    /**
     * 此线程池遇到任何异常都会取消后续任务的执行
     *
     * @param factory
     * @param runnable
     */
    private static void runOfScheduled(BasicThreadFactory factory, Runnable runnable) {
        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(1, factory);
        scheduledExecutor.scheduleWithFixedDelay(runnable, 5, 100, TimeUnit.MILLISECONDS);
    }
}
