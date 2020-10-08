package com.sun.demo.io.tomcat;

/**
 * Author by Sun, Date on 2020/10/8.
 * PS: Not easy to write code, please indicate.
 */
public enum RequestMethodEnum {
    GET("GET"),
    POST("POST");
    public String code;

    RequestMethodEnum(String code) {
        this.code = code;
    }
}
