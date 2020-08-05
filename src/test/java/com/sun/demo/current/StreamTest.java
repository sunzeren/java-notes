package com.sun.demo.current;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
}
