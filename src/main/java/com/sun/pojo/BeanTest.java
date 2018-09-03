package com.sun.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Author by Sun, Date on 2018/9/3.
 * PS: Not easy to write code, please indicate.
 */
@Component("yhy")
@Data
public class BeanTest {
    private String name = "杨海洋";
    private String gender = "未知";
    private String Other = "胖";
}
