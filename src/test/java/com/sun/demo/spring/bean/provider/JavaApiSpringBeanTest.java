package com.sun.demo.spring.bean.provider;

import com.sun.pojo.Company;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import javax.annotation.Resource;

/**
 * Author by Sun, Date on 2020/2/4.
 * PS: Not easy to write code, please indicate.
 * 基于 java api 的底层 spring 依赖注入
 */
public class JavaApiSpringBeanTest {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition("company", getDefaultCompanyBeanDefinitionBuilder());

        Company bean = (Company) beanFactory.getBean("company");
        System.out.println("bean = " + bean);
    }

    private static BeanDefinition getDefaultCompanyBeanDefinitionBuilder() {
        BeanDefinitionBuilder definitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(Company.class);
        definitionBuilder.addPropertyValue("childCompany", getDefaultCompany());
        // 以下这种可以直接引用 将一个已注册的bean对象注入进某个字段
        // 类似于 xml 的 ref 注入(这个方法即是底层xml ref 引用的方法)
        // definitionBuilder.addPropertyReference("name", "beanName");
        return definitionBuilder.getBeanDefinition();
    }


    @Resource
    private static Company getDefaultCompany() {
        Company company = new Company();
        company.setId(0);
        company.setName("DEFAULT");
        return company;
    }
}
