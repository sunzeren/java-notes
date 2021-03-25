package com.sun.demo.io.log.bean;

import lombok.Getter;

@Getter
public enum PlatformLogType {
    // 请求日志 URL
    GATEWAY_LOG("https://log.can-dao.com/log/rsplog", "网关日志"),
    // 业务日志 URL
    BUSINESS_LOG("https://log.can-dao.com/log/syslog", "业务日志"),

    ;

    PlatformLogType(String value, String memo) {
        this.value = value;
        this.memo = memo;
    }

    private final String value;
    private final String memo;

    public String getValue() {
        return value;
    }
}