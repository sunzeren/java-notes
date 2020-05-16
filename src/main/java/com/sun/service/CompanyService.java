package com.sun.service;

import com.sun.pojo.Company;

import java.util.List;

/**
 * Author by Sun, Date on 2018/9/3.
 * PS: Not easy to write code, please indicate.
 */
public interface CompanyService {
    List<Company> selectAllCompany();

    int removeByCompanyID(int companyId);

    void addCompany(Company company);
}
