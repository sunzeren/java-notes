package com.sun.demo.base.proxy;

/**
 * @Author: ZeRen.
 * @Date: 2020/4/7 15:20
 * 将要被代理对象
 */
public class ProxyObject implements SayHello, OtherInterface {

    @Override
    public void say(String text) {
        System.out.println("you say :" + text);
    }

    @Override
    public void testMethod() {
        System.out.println("is test method");
    }
}
