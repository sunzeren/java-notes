package com.sun.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Author by Sun, Date on 2018/9/3.
 * PS: Not easy to write code, please indicate.
 */
@Data
public class Company {
    private int id;
    private String name;
    private String legalPerson;
    private double registeredCipital;
    private Date registrationTime;
    private Date phone;
    private String address;
}
