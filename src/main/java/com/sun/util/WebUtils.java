package com.sun.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Utils - Web
 */
public final class WebUtils {

    private static String cookiePath = "/";
    private static String cookieDomain = "";

    /**
     * 不可实例化
     */
    private WebUtils() {
    }

    /**
     * 添加cookie
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param name     Cookie名称
     * @param value    Cookie值
     * @param maxAge   有效期(单位: 秒)
     * @param path     路径
     * @param domain   域
     * @param secure   是否启用加密
     */
    public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, Integer maxAge, String path, String domain, Boolean secure) {
        Assert.notNull(request);
        Assert.notNull(response);
        Assert.hasText(name);
        Assert.hasText(value);

        try {
            name = URLEncoder.encode(name, "UTF-8");
            value = URLEncoder.encode(value, "UTF-8");
            Cookie cookie = new Cookie(name, value);
            if (maxAge != null) {
                cookie.setMaxAge(maxAge);
            }
            if (StringUtils.isNotEmpty(path)) {
                cookie.setPath(path);
            }
            if (StringUtils.isNotEmpty(domain)) {
                cookie.setDomain(domain);
            }
            if (secure != null) {
                cookie.setSecure(secure);
            }
            response.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 添加cookie
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param name     Cookie名称
     * @param value    Cookie值
     * @param maxAge   有效期(单位: 秒)
     */
    public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, Integer maxAge) {
        Assert.notNull(request);
        Assert.notNull(response);
        Assert.hasText(name);
        Assert.hasText(value);

        addCookie(request, response, name, value, maxAge, cookiePath, cookieDomain, null);
    }

    /**
     * 添加cookie
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param name     Cookie名称
     * @param value    Cookie值
     */
    public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value) {
        Assert.notNull(request);
        Assert.notNull(response);
        Assert.hasText(name);
        Assert.hasText(value);

        addCookie(request, response, name, value, null, cookiePath, cookieDomain, null);
    }

    /**
     * 获取cookie
     *
     * @param request HttpServletRequest
     * @param name    Cookie名称
     * @return Cookie值，若不存在则返回null
     */
    public static String getCookie(HttpServletRequest request, String name) {
        Assert.notNull(request);
        Assert.hasText(name);

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            try {
                name = URLEncoder.encode(name, "UTF-8");
                for (Cookie cookie : cookies) {
                    if (name.equals(cookie.getName())) {
                        return URLDecoder.decode(cookie.getValue(), "UTF-8");
                    }
                }
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        return null;
    }

    /**
     * 移除cookie
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param name     Cookie名称
     * @param path     路径
     * @param domain   域
     */
    public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name, String path, String domain) {
        Assert.notNull(request);
        Assert.notNull(response);
        Assert.hasText(name);

        try {
            name = URLEncoder.encode(name, "UTF-8");
            Cookie cookie = new Cookie(name, null);
            cookie.setMaxAge(0);
            if (StringUtils.isNotEmpty(path)) {
                cookie.setPath(path);
            }
            if (StringUtils.isNotEmpty(domain)) {
                cookie.setDomain(domain);
            }
            response.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 移除cookie
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param name     Cookie名称
     */
    public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Assert.notNull(request);
        Assert.notNull(response);
        Assert.hasText(name);

        removeCookie(request, response, name, cookiePath, cookieDomain);
    }


}
