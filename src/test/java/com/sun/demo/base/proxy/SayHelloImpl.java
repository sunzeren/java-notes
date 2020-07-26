package com.sun.demo.base.proxy;

/**
 * @Author: ZeRen.
 * @Date: 2020/4/7 15:20
 */
public class SayHelloImpl implements SayHello {

    @Override
    public void say(String text) {
        System.out.println("you say :" + text);
    }
}
