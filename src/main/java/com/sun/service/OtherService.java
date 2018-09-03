package com.sun.service;

import com.sun.pojo.BeanTest;
import com.sun.util.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author by Sun, Date on 2018/9/3.
 * PS: Not easy to write code, please indicate.
 */
@Component
public class OtherService {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public void someService(){
        /** */
    }
    public String getNowTime(){
        Date date = new Date();
        return "时间---->"+sdf.format(date);
    }

    public BeanTest getYhy(){
        BeanTest bean = (BeanTest) BeanUtils.getBean("yhy");
        System.out.println(getNowTime()+bean);
        return  bean;
    }
}
