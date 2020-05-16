package com.sun.demo.converter;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;

enum MyEnum {
    V1, V2, V3, @JsonEnumDefaultValue Default
}

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

    /* 以下为SpringBoot配置方法  */

    /**
     * http 消息映射转换配置
     *
     * @param objectMapper
     * @return
     */
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(ObjectMapper objectMapper) {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter(objectMapper);
        // 配置当枚举转换失败时使用默认值
        objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE, true);
        return jsonConverter;
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

