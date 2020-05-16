package com.sun.mapper;

import com.sun.pojo.Company;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author by Sun, Date on 2018/9/3.
 * PS: Not easy to write code, please indicate.
 */
@Repository
public class CompanyMapper {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Company> selectAllCompany() {
        return jdbcTemplate.query("select * from tb_company", new BeanPropertyRowMapper<>(Company.class));
    }

    public int removeByCompanyID(int companyId) {
        /** 直接执行sql语句 */
        /** jdbcTemplate.execute("delete from tb_company where id ="+companyId); */
        return jdbcTemplate.update("delete from tb_company where id =" + companyId);
    }

    /**
     * 注解执行sql语句
     */
    @Insert("insert into tb_company(id, name, legalPerson, registeredCipital, registrationTime,phone,address) values (#{id}, #{name}, #{legalPerson}, #{registeredCipital}, #{registrationTime},#{phone},#{address})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void addCompany(Company company) {
    }
}
