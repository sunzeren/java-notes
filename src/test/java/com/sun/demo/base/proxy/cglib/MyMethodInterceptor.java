package com.sun.demo.base.proxy.cglib;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: ZeRen.
 * @Date: 2020/4/7 16:10
 */
@Slf4j
public class MyMethodInterceptor implements MethodInterceptor {


    // 被代理的目标对象
    private Object target;

    public MyMethodInterceptor(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        log.info("调用方法:{},方法参数:{}", method.getName(), args);
        return method.invoke(target, args);
    }

}
