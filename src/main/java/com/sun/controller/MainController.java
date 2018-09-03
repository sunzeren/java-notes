package com.sun.controller;

import com.sun.service.CompanyService;
import com.sun.service.OtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Author by Sun, Date on 2018/8/30.
 * PS: Not easy to write code, please indicate.
 */
@Controller
public class MainController {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    CompanyService companyService;
    @Autowired
    OtherService otherService;

    @RequestMapping("/testForJDBC")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView();
        List list = companyService.selectAllCompany();
        mv.addObject("list",list );
        mv.setViewName("login");

        /**调用🐖*/
        otherService.getYhy();
        return mv;
    }

}
