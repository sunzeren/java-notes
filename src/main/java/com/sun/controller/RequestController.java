package com.sun.controller;

import com.sun.pojo.Company;
import com.sun.util.RequestTool;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * @Author: ZeRen.
 * @Date: 2019/9/24 15:07
 */
@RestController
public class RequestController {

    /**
     * -------------
     * 以下两个请求中请求路径一致,根据请求头中的Content-Type来进行不同的业务操作
     * -------------
     */

    @PostMapping(headers = "Content-Type=application/json", value = "/post")
    @ApiOperation(value = "post请求测试", notes = "post请求测试")
    public Company postTest(@RequestBody Company company) {
        System.out.println("进入 @RequestBody 注解注入的方法");

        return company;
    }

    @PostMapping(headers = "Content-Type=" + MediaType.APPLICATION_FORM_URLENCODED_VALUE, value = "/post")
    @ApiOperation(value = "post请求,application/x-www-form-urlencoded 请求头测试", notes = "post请求头测试")
    public Company postTest_(Company company) {
        System.out.println("进入 header为 Content-Type=application/x-www-form-urlencoded 的方法");

        HttpServletRequest request = RequestTool.getCurrentServletRequest();
        Map<String, String[]> parameterMap = request.getParameterMap();
        System.out.println("parameterMap = " + parameterMap);
        return company;
    }

    @PostMapping("/postByBody")
    @ApiOperation(value = "post请求,测试body", notes = "post请求测试body")
    public void bodyTest() {
        //与上同理
        //public void bodyTest(@RequestBody Company company) {
        HttpServletRequest request = RequestTool.getCurrentServletRequest();
        StringBuilder requestBody = new StringBuilder();
        try (ServletInputStream inputStream = request.getInputStream()) {
            int read = inputStream.read();
            while (read != -1) {
                requestBody.append((char) read);
                read = inputStream.read();
            }
            System.out.println("requestBody = " + requestBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
