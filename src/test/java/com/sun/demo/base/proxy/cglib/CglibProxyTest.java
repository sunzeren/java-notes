package com.sun.demo.base.proxy.cglib;

import com.sun.demo.base.proxy.SayHello;
import com.sun.demo.base.proxy.SayHelloImpl;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @Author: ZeRen.
 * @Date: 2020/4/7 15:16
 * 动态代理实现
 * 不依赖于仅可代理接口,可直接代理实例对象
 */
public class CglibProxyTest {

    public static void main(String[] args) {

        // 返回代理对象
        Object o = null;


        //Enhancer e = new Enhancer();
        //e.setSuperclass(SayHelloImpl.class);
        //e.setCallback(new MyMethodInterceptor(new SayHelloImpl()));
        //o = e.create();
        // 官方推荐使用以上写法,因为此静态方法生成代理对象有局限性
        o = Enhancer.create(SayHelloImpl.class, new MyMethodInterceptor(new SayHelloImpl()));

        if (o instanceof SayHello) {
            ((SayHello) o).say("I love you");
        }

    }

}
