package com.sun.pojo;

import lombok.Data;

/**
 * Author by Sun, Date on 2020/3/19.
 * PS: Not easy to write code, please indicate.
 */
@Data
public class SuperCompany extends Company {

    private String role;
    private String superCompanyName;

    public SuperCompany() {
        super();
        this.role = "拥有所有权限的超级Company";
    }
}
