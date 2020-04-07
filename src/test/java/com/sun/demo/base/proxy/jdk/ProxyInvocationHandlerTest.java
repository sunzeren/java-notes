package com.sun.demo.base.proxy.jdk;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: ZeRen.
 * @Date: 2020/4/7 15:15
 * 创建一个调用处理实现类
 * 用于增强一个类对象,将某个类的对象中的某个方法全部记录入职
 */
@Slf4j
public class ProxyInvocationHandlerTest implements InvocationHandler {

    // 被代理的目标对象
    private Object target;

    public ProxyInvocationHandlerTest(Object target) {
        this.target = target;
    }

    public void changeTarget(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        log.info("调用方法:{},方法参数:{}", method.getName(), args);
        return method.invoke(target, args);
    }

}
