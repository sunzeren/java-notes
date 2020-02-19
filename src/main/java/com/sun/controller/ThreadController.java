package com.sun.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author by Sun, Date on 2020/2/19.
 * PS: Not easy to write code, please indicate.
 */
@RestController
@RequestMapping("/thread")
public class ThreadController {

    /**
     * 此来测试,当不显视的关闭 线程时,是否会被GC回收
     * ps:当运行完毕后,如无引用会被GC回收
     *
     * @param count 启动线程数量
     */
    @GetMapping("startThread")
    public String startThread(int count) {
        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000 * 15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "新建的线程").start();
        }
        return String.format("启动线程数量:[%s]", count);
    }

}
