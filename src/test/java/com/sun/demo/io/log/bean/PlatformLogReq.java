package com.sun.demo.io.log.bean;

import lombok.Data;
import lombok.Getter;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

@Data
public class PlatformLogReq {

    /**
     * 日志查询开始时间
     */
    private LocalDateTime startTime;
    /**
     * 日志查询结束时间
     */
    private LocalDateTime endTime;
    /**
     * 日志id
     */
    private String logId;
    /**
     * 关键字
     */
    private String keyword;
    /**
     * 花费时间
     */
    private String costTime;
    /**
     * 系统id
     */
    private String sid;
    private String flag;
    /**
     * 日志级别
     */
    private LogLevelEnum logLevel;
    /**
     * 当前页数
     */
    private int pageNow;
    /**
     * 分页页数大小
     */
    private int pageSize;
    /**
     * 查询系统
     */
    private SysTypeEnum sysType;

    private String subEnvName;
    private String gray;

    private PlatformLogReq() {
    }

    public static PlatformLogReq getInstance(LocalDateTime startTime, LocalDateTime endTime, SysTypeEnum sysType) {
        PlatformLogReq req = new PlatformLogReq();
        req.setStartTime(startTime);
        req.setEndTime(endTime);
        req.setPageNow(1);
        req.setPageSize(20);
        req.setSysType(sysType);
        return req;
    }

    public String getUrlParameters() {
        Field[] allFields = FieldUtils.getAllFields(this.getClass());

        StringJoiner joiner = new StringJoiner("&", "?", "");
        try {
            for (Field field : allFields) {
                Object declaredField;
                if (Convertible.class.isAssignableFrom(field.getType())) {
                    Convertible convertible = (Convertible) FieldUtils.readField(field, this, true);
                    if (convertible == null) {
                        continue;
                    }
                    declaredField = convertible.getValue();
                } else if (LocalDateTime.class.isAssignableFrom(field.getType())) {
                    LocalDateTime convertible = (LocalDateTime) FieldUtils.readField(field, this, true);
                    if (convertible == null) {
                        continue;
                    }
                    declaredField = convertible.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
                } else {
                    declaredField = FieldUtils.readField(field, this, true);
                }
                if (declaredField == null) {
                    continue;
                }
                joiner.add(field.getName() + "=" + declaredField);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        return joiner.toString();
    }

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    @Getter
    public enum LogLevelEnum implements Convertible {
        INFO("INFO"),
        WARN("WARN"),
        ERROR("ERROR"),
        DEBUG("DEBUG"),

        ;

        LogLevelEnum(String value) {
            this.value = value;
        }

        private final String value;

        public String getValue() {
            return value;
        }
    }

    @Getter
    public enum SysTypeEnum implements Convertible {
        DMS_PRO("cddms", "DMS标准版"),
        DMS_MCD("dms", "DMS麦中版"),

        ;

        SysTypeEnum(String value, String memo) {
            this.value = value;
            this.memo = memo;
        }

        private final String value;
        private final String memo;

        public String getValue() {
            return value;
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PlatformLogReq.class.getSimpleName() + "[", "]")
                .add("startTime=" + startTime)
                .add("endTime=" + endTime)
                .add("keyword='" + keyword + "'")
                .add("pageNow=" + pageNow)
                .add("pageSize=" + pageSize)
                .add("sysType=" + sysType)
                .toString();
    }
}
