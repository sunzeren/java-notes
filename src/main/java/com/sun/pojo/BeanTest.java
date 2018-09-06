package com.sun.pojo;

import io.swagger.annotations.Api;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Author by Sun, Date on 2018/9/3.
 * PS: Not easy to write code, please indicate.
 */
@Component("yhy")
@Data
@Api(value = "这个类主要用于测试通过BeanUtil直接调用Bean")
public class BeanTest {
    private String name = "杨海洋";
    private String gender = "未知";
    private String Other = "胖";
}
