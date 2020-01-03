package com.sun.demo.queue;

import java.util.LinkedList;

/**
 * @Author: ZeRen.
 * @Date: 2020/1/3 14:51
 */
public class QueueTest {

    private static final LinkedList<Integer> queue = new LinkedList<>();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            queue.add(i);
        }

        while (queue.peek() != null) {
            final Integer poll = queue.poll();
            System.out.println(poll);
        }
    }
}
