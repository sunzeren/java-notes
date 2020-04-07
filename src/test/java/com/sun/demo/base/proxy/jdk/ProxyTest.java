package com.sun.demo.base.proxy.jdk;

import java.lang.reflect.Proxy;

/**
 * @Author: ZeRen.
 * @Date: 2020/4/7 15:16
 * 动态代理实现
 */
public class ProxyTest {

    public static void main(String[] args) {

        // 创建一个代理实现实例
        final ProxyInvocationHandlerTest proxyInvocationHandlerTest = new ProxyInvocationHandlerTest(new SayHello() {
            @Override
            public void say(String text) {
                System.out.println("You say:" + text);
            }
        });

        // 返回代理对象
        final Object o = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class<?>[]{SayHello.class, OtherInterface.class}, proxyInvocationHandlerTest);

        if (o instanceof SayHello) {
            ((SayHello) o).say("I love you");
        }

        if (o instanceof OtherInterface) {
            //proxyInvocationHandlerTest.changeTarget((OtherInterface) () -> { });
            ((OtherInterface) o).textMethod();
        }
    }

}
