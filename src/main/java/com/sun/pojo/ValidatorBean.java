package com.sun.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Author: ZeRen.
 * @Date: 2019/9/23 11:51
 * 用于测试 spring-mvc的验证注解验证
 * spring-mvc自带的javax.validation
 * 使用的是org.hibernate.validator,也就是 hibernate 的验证框架
 */
@Data
public class ValidatorBean {

    @NotNull
    private String name;
    @NotEmpty
    @Length(max = 2, min = 1)
    private String gender;
    private String idCard;
}
