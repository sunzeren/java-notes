package com.sun.controller;

import com.sun.util.WebUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: ZeRen.
 * @Date: 2019/9/24 15:07
 */
@RestController
@RequestMapping("/cookie")
public class CookieController {

    private static final String cookieName = "userId";

    @GetMapping("/add")
    @ApiOperation(value = "添加cookie", notes = "添加cookie测试")
    public String addCookie(HttpServletRequest request, HttpServletResponse response) {
        String cookieValue = "123456789";
        WebUtils.addCookie(request, response, cookieName, cookieValue);
        return cookieValue;
    }

    @GetMapping("/add-header")
    @ApiOperation(value = "通过Header添加cookie", notes = "通过添加响应的Header来添加cookie的测试")
    public String addHeaderByHeader(HttpServletRequest request, HttpServletResponse response) {
        String cookieValue = "cookie_=theCookieAddByHeader; Path=/";
        response.addHeader(HttpHeaders.SET_COOKIE, cookieValue);
        return cookieValue;
    }

    @GetMapping("/query")
    @ApiOperation(value = "查询cookie", notes = "查询cookie测试")
    public void queryTest(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println("cookie = " + cookie.getName() + ":" + cookie.getValue());
        }
        System.out.println("===== cookie是通过Header来传递的 =====");
        String headerOfCookies = request.getHeader(HttpHeaders.COOKIE);
        System.out.println("headerOfCookies = " + headerOfCookies);
        if (StringUtils.isNotEmpty(headerOfCookies)) {
            String[] cookie = headerOfCookies.split(";");
            for (String c : cookie) {
                System.out.println("c = " + c);
            }
        }
    }
}
