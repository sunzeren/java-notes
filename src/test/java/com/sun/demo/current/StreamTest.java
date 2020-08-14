package com.sun.demo.current;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * @Author: ZeRen.
 * @Date: 2020/7/28 18:30
 *
 * 使用并行流来进行 并发模拟操作
 *
 */
public class StreamTest {
    public static void main(String[] args) {
        learnStream();
    }


    private static void learnStream() {
        List<Integer> lists = new ArrayList<>();
        lists.add(1);
        lists.add(2);
        lists.add(3);
        lists.add(4);
        lists.add(5);
        lists.add(6);

        Optional<Integer> sum = lists.parallelStream().reduce((a, b) -> a + b);//这里把stream()换成了parallelStream（）
        //21
        sum.ifPresent(integer -> System.out.println("list的总和为:" + integer));
        //<====> lists.stream().reduce((a, b) -> a + b).ifPresent(System.out::println);

        Integer sum2 = lists.stream().reduce(0, (a, b) -> a + b);//21
        System.out.println("list的总和为:" + sum2);

        Optional<Integer> product = lists.stream().reduce((a, b) -> a * b);
        //720
        product.ifPresent(integer -> System.out.println("list的积为:" + integer));

        Integer product2 = lists.parallelStream().reduce(1, (a, b) -> a * b);//这里把stream()换成了parallelStream（）
        System.out.println("list的积为:" + product2);//720
    }


    @Test
    public void blockingQueue() {
        final List<String> elementList = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            elementList.add(String.valueOf(i + 1));
        }
        //ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(1000000, false, elementList);
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(elementList);
        AtomicInteger counter = new AtomicInteger(1);
        Stream.generate(counter::incrementAndGet).limit(1000000).parallel().forEach((e) -> {
            try {
                final String element = queue.take();
                System.out.println("出队序号:" + e + "线程:" + Thread.currentThread().getName() + ",出队元素:" + element);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
    }
}
