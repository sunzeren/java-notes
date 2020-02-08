package com.sun.demo.spring.bean;

import com.sun.pojo.Company;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Author by Sun, Date on 2020/2/4.
 * PS: Not easy to write code, please indicate.
 * 基于xml的spring依赖注入
 */
public class XmlSpringBeanTest {

    public static void main(String[] args) {
        ConfigurableApplicationContext beanFactory = new ClassPathXmlApplicationContext("classpath:xml/spring-bean-config.xml");
        // 依赖查找
        Company companyBean = (Company) beanFactory.getBean("companyBean");
        // 依赖注入
        BeanFactory autowireBeanFactory = companyBean.getBeanFactory();

        System.out.println(beanFactory == autowireBeanFactory);
        System.out.println(beanFactory.getBeanFactory() == autowireBeanFactory);
    }

}
