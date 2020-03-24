package com.sun.demo.spring.bean.provider;

import com.sun.pojo.Company;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * Author by Sun, Date on 2020/2/4.
 * PS: Not easy to write code, please indicate.
 * 基于注解的spring 依赖注入
 */
public class ProviderSpringBeanTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext beanFactory = new AnnotationConfigApplicationContext();
        beanFactory.register(ProviderSpringBeanTest.class);
        beanFactory.refresh();

        // 依赖查找
        ObjectProvider<Company> beanProvider = beanFactory.getBeanProvider(Company.class);
        // 获取Bean,如不存在则根据提供的 Supplier工厂方法构建Bean
        Company ifAvailable = beanProvider.getIfAvailable(ProviderSpringBeanTest::getDefaultCompany);
        System.out.println("ifAvailable = " + ifAvailable);

        // 关闭应用上下文
        beanFactory.close();
    }

    private static Company getDefaultCompany() {
        Company company = new Company();
        company.setId(0);
        company.setName("DEFAULT");
        return company;
    }


    @Bean
    private static Company company() {
        Company company = new Company();
        company.setId(0);
        company.setName("DEFAULT");
        return company;
    }

}
