package com.sun.demo.spring.bean.provider;

import com.sun.pojo.Company;
import com.sun.pojo.SuperCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;

import java.util.List;

/**
 * Author by Sun, Date on 2020/2/4.
 * PS: Not easy to write code, please indicate.
 * 基于注解的spring 依赖注入
 */
public class AutowireProviderTest {

    @Autowired
    @Lazy
    private Company lazyCompany;
    @Autowired
    private Company company;
    @Autowired
    private List<Company> companyList;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext beanFactory = new AnnotationConfigApplicationContext();
        beanFactory.register(AutowireProviderTest.class);
        beanFactory.refresh();

        beanFactory.registerBeanDefinition("superCompany", getSuperCompany());

        AutowireProviderTest bean = beanFactory.getBean(AutowireProviderTest.class);
        System.out.println("company = " + bean.lazyCompany);


        SuperCompany superCompanyBean = beanFactory.getBean(SuperCompany.class);
        System.out.println("superCompanyBean = " + superCompanyBean);


        // 关闭应用上下文
        beanFactory.close();
    }


    public static BeanDefinition getSuperCompany() {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(SuperCompany.class);
        // beanDefinitionBuilder.setParentName("defaultCompany");
        beanDefinitionBuilder.addPropertyValue("phone", "1374441311");
        beanDefinitionBuilder.addPropertyValue("superCompanyName", "阿里巴巴");
        return beanDefinitionBuilder.getBeanDefinition();
    }

    /**
     * 依赖注入
     */
    // @Bean
    // @Primary
    // public SuperCompany superCompany() {
    //     SuperCompany company = new SuperCompany();
    //     company.setId(999);
    //     company.setName("ALI");
    //     return company;
    // }

    /**
     * 依赖注入
     */
    @Bean
    @Primary
    public Company defaultCompany() {
        Company company = new Company();
        company.setId(1);
        company.setName("默认的Company");
        return company;
    }

}
