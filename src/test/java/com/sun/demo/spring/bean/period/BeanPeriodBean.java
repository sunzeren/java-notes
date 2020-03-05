package com.sun.demo.spring.bean.period;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Author by Sun, Date on 2020/2/16.
 * PS: Not easy to write code, please indicate.
 * Bean 生命周期Demo
 */
public class BeanPeriodBean implements InitializingBean, DisposableBean {


    /**
     * Java 基础注解
     */
    @PostConstruct
    public void initMethod() {
        System.out.println("@PostConstruct 初始化Bean");
    }

    /**
     * InitializingBean 接口实现 初始化方法
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet() 初始化Bean");
    }

    /**
     * 自定义初始化方法
     */
    public void doInitMethod() {
        System.out.println("自定义方法 doInitMethod() 初始化Bean");
    }


    /**
     * java基础注解,实际指GC销毁前调用
     */
    @PreDestroy
    public void destroyDoc() {
        System.out.println("@PreDestroy 销毁Bean");
    }

    /**
     * DisposableBean#destroy() 实现销毁方法
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean#destroy() 销毁Bean");
    }

    /**
     * 自定义销毁 Bean
     */
    public void doDestroy() throws Exception {
        System.out.println("自定义doDestroy() 销毁Bean");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("正在被回收...");
    }
}
