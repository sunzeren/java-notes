package com.sun.controller;

import com.sun.constant.AppSetting;
import com.sun.pojo.ValidatorBean;
import com.sun.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Author by Sun, Date on 2018/8/30.
 * PS: Not easy to write code, please indicate.
 */
@RestController
@Api("SpringBoot测试控制器")
public class MainController {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    CompanyService companyService;

    @GetMapping(value = "/testForJDBC")
    @ApiOperation(value = "查询所有Company", notes = "返回模型和视图")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        List list = companyService.selectAllCompany();
        mv.addObject("list", list);
        mv.setViewName("login");
        return mv;
    }


    @GetMapping(value = "/validateBean")
    @ApiOperation(value = "测试Validator", notes = "测试校验")
    public void validatorBeanTest(@Validated ValidatorBean bean) {
        System.out.println("bean = " + bean);
    }

    @GetMapping(value = "/getAppSetting")
    @ApiOperation(value = "获取应用配置信息", notes = "获取应用配置信息")
    public void getAppSetting() {
        if (AppSetting.PORT == null) {
            throw new IllegalArgumentException("请在DemoApplication中,添加EnvironmentListener监听");
        }
        System.out.println(AppSetting.PORT);
        System.out.println(AppSetting.APP_NAME);
        AppSetting.PROFILES_ACTIVE.forEach(System.out::println);
    }


}
