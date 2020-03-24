package com.sun.demo.spring.converter;

import org.springframework.beans.SimpleTypeConverter;
import org.springframework.beans.TypeConverter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author by Sun, Date on 2020/3/19.
 * PS: Not easy to write code, please indicate.
 */
public class TypeConverterTest {

    public static void main(String[] args) {
        Map<String, Integer> data = new HashMap<>();
        data.put("1", 1);
        data.put("2", 2);
        data.put("3", 3);

        TypeConverter converter = new SimpleTypeConverter();
        // List list = converter.convertIfNecessary(data.keySet(), List.class);
        // List list = converter.convertIfNecessary(new String[]{"1", "2", "3"}, List.class);
        List list = converter.convertIfNecessary(data.values(), List.class);
        System.out.println("list = " + list);
    }
}
