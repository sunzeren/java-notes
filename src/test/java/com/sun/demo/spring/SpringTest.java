package com.sun.demo.spring;

import com.sun.constant.AppSetting;
import com.sun.demo.DemoApplicationTests;
import com.sun.service.OtherService;
import com.sun.util.BeanUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: ZeRen.
 * @Date: 2019/10/7 13:50
 */
public class SpringTest extends DemoApplicationTests {

    @Autowired
    private OtherService otherService;

    /**
     * 获取一个Spring注入的Bean
     */
    @Test
    public void getBean() {
        /**调用🐖*/
        otherService.getYhy();

        System.out.println(BeanUtils.getBean("otherService"));

    }

    @Test
    public void getAppSetting() {
        System.out.println(AppSetting.APP_NAME);
        System.out.println(AppSetting.PORT);
        AppSetting.PROFILES_ACTIVE.forEach(System.out::println);
    }
}
