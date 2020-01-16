package com.sun.demo.converter;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * 转换不存在的枚举使用默认值替代
 */
public class EnumTest {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE, true);
        String json = "{\"myEnum\":\"V4\"}";
        EnumData enumData = objectMapper.readValue(json, EnumData.class);
        System.out.println(enumData);
    }
}

class EnumData {
    public MyEnum myEnum;

    @Override
    public String toString() {
        return "EnumData{" +
                "myEnum=" + myEnum +
                '}';
    }
}

enum MyEnum {
    V1, V2, V3, @JsonEnumDefaultValue Default
}

