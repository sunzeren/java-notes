package com.sun.service.impl;

import com.sun.mapper.CompanyMapper;
import com.sun.pojo.Company;
import com.sun.service.CompanyService;
import com.sun.service.OtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author by Sun, Date on 2018/9/3.
 * PS: Not easy to write code, please indicate.
 */

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyMapper companyMapper;
    @Autowired
    OtherService otherService;

    @Override
    public List<Company> selectAllCompany() {
        return companyMapper.selectAllCompany();
    }

    @Override
    public int removeByCompanyID(int companyId) {
        return companyMapper.removeByCompanyID(companyId);
    }

    @Override
    public void addCompany(Company company) {
        companyMapper.addCompany(company);
    }


    /**
     * @return
     * @Author SuZeRen
     * @Description //TODO service进行 业务逻辑处理
     * @Date 11:55 2018/9/3
     * @Param
     **/
    public void someService() {
        otherService.someService();
    }
}
