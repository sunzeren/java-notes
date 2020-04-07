package com.sun.demo.base.proxy.jdk;

/**
 * @Author: ZeRen.
 * @Date: 2020/4/7 15:20
 */
public interface SayHello {

    default void say(String text) {
        // nothing
    }
}
