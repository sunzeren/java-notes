package com.sun.pojo;

import io.swagger.annotations.Api;
import lombok.Data;
import org.springframework.beans.factory.BeanFactory;

import java.util.Date;

/**
 * Author by Sun, Date on 2018/9/3.
 * PS: Not easy to write code, please indicate.
 */
@Data
@Api(value = "Company实体类")
public class Company {
    private int id;
    private String name;
    private String legalPerson;
    private double registeredCipital;
    private Date registrationTime;
    private String phone;
    private String address;

    private Company childCompany;
    private BeanFactory beanFactory;

    public Company() {
        System.out.println("创建了Company实例");
    }

    public Company(int id) {
        this.id = id;
    }

    @Data
    public static class InnerBean {
        private String id;
    }

}
