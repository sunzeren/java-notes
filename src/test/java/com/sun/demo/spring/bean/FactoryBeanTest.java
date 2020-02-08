package com.sun.demo.spring.bean;

import com.sun.pojo.Company;
import org.springframework.beans.factory.FactoryBean;

/**
 * Author by Sun, Date on 2020/2/5.
 * PS: Not easy to write code, please indicate.
 * FactoryBean是Bean的一种实现,当容器通过getBean获取此bean时返回getObject()方法所返回的对象
 */
public class FactoryBeanTest implements FactoryBean<Company> {

    @Override
    public Company getObject() throws Exception {
        return new Company();
    }

    @Override
    public Class<?> getObjectType() {
        return Company.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

}
