package com.sun.demo.base.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Author by Sun, Date on 2020/8/23.
 * PS: Not easy to write code, please indicate.
 */
public class ReflectTest {

    public static void main(String[] args) throws Exception {
        Class<?> aClass = Class.forName("com.sun.demo.base.reflect.ReflectTest");
        // 对于一个类 可以知道它的所有字段
        Field[] classAllFields = aClass.getFields();
        // 对于一个类 可以知道它的所有方法
        Method[] classAllMethod = aClass.getMethods();

        Object obj = aClass.newInstance();
        for (Method method : classAllMethod) {
            if (method.getName().contains("method")) {
                method.invoke(obj);
            }
        }
    }

    public void method1() {
        System.out.println("is method 1");
    }

    public void method2() {
        System.out.println("is method 2");
    }

    public void method3() {
        System.out.println("is method 3");
    }

}
