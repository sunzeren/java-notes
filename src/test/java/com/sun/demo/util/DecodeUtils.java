package com.sun.demo.util;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ZeRen.
 * @Date: 2019/11/7 10:21
 */
public class DecodeUtils {

    @Test
    public void decode() {

        String text = "sysid=1902271423530473681&v=2.0&timestamp=2019-11-06+16%3A34%3A01&sign=tla9pB415XmAeXNkDYFruSYc%2B8ZysyWIT%2Bq8Vi%2Bs7IXxL9HVGZDCJZ3Vv8KcsSwGwLJBuhvyO2Mlv%2BAobxP4r5fVYSKPNninLvpItJZ55%2FVn8f7iq%2FQ2ngnkjTST2u0klf94ZaUqMd84U%2F0eIXX0ZY09sAyj5LKCx%2F0Q2yO24dw%3D&req=%7B%22method%22%3A%22createMember%22%2C%22param%22%3A%7B%22bizUserId%22%3A%22901828d433ff4e92900bae7bbce917f6%22%2C%22memberType%22%3A2%2C%22source%22%3A2%7D%2C%22service%22%3A%22MemberService%22%7D&";

        try {
            String decode = URLDecoder.decode(text, "utf-8");
            System.out.println("decode = " + decode);
            Map<String, String> param = paramToMap(decode);
            String jsonString = JSON.toJSONString(param);
            System.out.println("jsonString = " + jsonString);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> paramToMap(String paramStr) {
        String[] params = paramStr.split("&");
        Map<String, String> resMap = new HashMap<>();
        for (int i = 0; i < params.length; i++) {
            String[] param = params[i].split("=");
            if (param.length >= 2) {
                String key = param[0];
                String value = param[1];
                for (int j = 2; j < param.length; j++) {
                    value += "=" + param[j];
                }
                resMap.put(key, value);
            }
        }
        return resMap;
    }
}
