package com.sun.demo.current.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Author by Sun, Date on 2020/7/28.
 * PS: Not easy to write code, please indicate.
 * <https://www.jianshu.com/p/ac2bcf2f9d48>
 */
public class ParallelStreamTest {
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

        // 使用并行流
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
