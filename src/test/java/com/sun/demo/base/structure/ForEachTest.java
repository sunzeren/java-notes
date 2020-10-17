package com.sun.demo.base.structure;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Author by Sun, Date on 2020/10/17.
 * PS: Not easy to write code, please indicate.
 * 循环测试
 * 测试结果: LinkedList使用下标循环时,速度很慢,不及使用 增强for循环或迭代器来进行遍历
 */
public class ForEachTest {

    private List<Integer> data;

    @Before
    public void init() {
        data = new LinkedList<>();
    }

    @Test
    public void linkedTest() {
        int temp = 0;

        for (int i = 0; i < 1000; i++) {
            data.add(i);
        }
        System.out.println("数据加载完毕");

        StopWatch stopWatch = new StopWatch();

        stopWatch.start("迭代器遍历");
        Iterator<Integer> iterator = data.iterator();
        while (iterator.hasNext()) {
            temp += iterator.next();
        }
        stopWatch.stop();

        stopWatch.start("forEach遍历");
        for (Integer datum : data) {
            temp += datum;
        }
        stopWatch.stop();

        stopWatch.start("for index遍历");
        for (int i = 0; i < data.size(); i++) {
            Integer tempData = data.get(i);
            temp += tempData;
        }
        stopWatch.stop();


        System.out.println(stopWatch.prettyPrint());
        System.out.println(temp);
    }
}
