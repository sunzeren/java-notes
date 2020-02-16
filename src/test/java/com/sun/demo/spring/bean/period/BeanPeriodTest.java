package com.sun.demo.spring.bean.period;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * Author by Sun, Date on 2020/2/16.
 * PS: Not easy to write code, please indicate.
 * Bean 周期Test
 * <p>
 * 周期顺序:
 * java基础注解->接口方法->自定义方法
 */
public class BeanPeriodTest {
    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext beanFactory = new AnnotationConfigApplicationContext();
        // 注册配置类 (@Configuration)
        beanFactory.register(BeanPeriodTest.class);
        // 加载,启动应用上下文
        beanFactory.refresh();

        // 关闭应用上下文
        beanFactory.close();

        // 显式调用 gc,用于观察 springBean是否被回收
        System.gc();
        Thread.sleep(5 * 1000);
    }


    /**
     * 测试初始化生命周期 bean
     *
     * @Lazy 注解为当使用此Bean时才装配
     */
    @Bean(initMethod = "doInitMethod", destroyMethod = "doDestroy")
    public BeanPeriodDemo periodDemo() {
        return new BeanPeriodDemo();
    }


}
