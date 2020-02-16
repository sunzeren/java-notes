package com.sun.demo.spring.bean;

import com.sun.pojo.Company;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * Author by Sun, Date on 2020/2/4.
 * PS: Not easy to write code, please indicate.
 * 基于注解的spring 依赖注入
 */
public class AnnotationSpringBeanTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext beanFactory = new AnnotationConfigApplicationContext();
        beanFactory.register(AnnotationSpringBeanTest.class);
        beanFactory.registerBeanDefinition("MyBeanDefinition", getBeanDefinition());
        beanFactory.refresh();

        // 以上等同于
        // AnnotationConfigApplicationContext beanFactory = new AnnotationConfigApplicationContext(AnnotationSpringBeanTest.class);


        // 依赖查找
        Company company = (Company) beanFactory.getBean("company");
        System.out.println("bean = " + company);

        Company companyOfBeanDefinition = (Company) beanFactory.getBean("MyBeanDefinition");
        System.out.println("companyOfBeanDefinition = " + companyOfBeanDefinition);

        Company factoryBean = (Company) beanFactory.getBean("factoryBeanTest");
        System.out.println("factoryBean = " + factoryBean);

        // 关闭应用上下文
        beanFactory.close();
    }

    /**
     * 通过 BeanDefinition 注册bean
     */
    private static BeanDefinition getBeanDefinition() {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(Company.class);
        builder.addPropertyValue("id", "10");
        builder.addPropertyValue("name", "Sun");
        return builder.getBeanDefinition();
    }

    /**
     * 依赖注入
     */
    // 此注解定义Bean的作用域,加prototype 属性时候,代表每次获取Bean都会返回一个新的Bean
    // 但是Company中注入的内建Bean,仍相同(即仍是Spring的内建Bean的引用)
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Bean(autowire = Autowire.BY_TYPE)
    public Company company() {
        Company company = new Company();
        company.setId(1);
        company.setName("Lao Sun");
        return company;
    }

    /**
     * 依赖注入
     */
    @Bean
    public Company.InnerBean innerBean() {
        Company.InnerBean innerBean = new Company.InnerBean();
        innerBean.setId("1");
        return innerBean;
    }

    /**
     * FactoryBean 依赖注入
     */
    @Bean
    public FactoryBeanTest factoryBeanTest() {
        return new FactoryBeanTest();
    }

}
