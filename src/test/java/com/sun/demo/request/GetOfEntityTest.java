package com.sun.demo.request;

import com.alibaba.fastjson.JSONObject;
import com.sun.controller.MainController;
import com.sun.pojo.Company;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * @Author: ZeRen.
 * @Date: 2020/1/10 14:13
 */
public class GetOfEntityTest {

    /**
     * 用于测试get请求,携带请求正文参数
     *
     * @param args
     * @see MainController#getTest(com.sun.pojo.Company)
     */
    public static void main(String[] args) {
        final String uri = "http://localhost:8080/getOfBody";
        final CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        final HttpEntityEnclosingRequestBase httpGet = new HttpGetOfBody(uri);

        final InputStream inputStream = IOUtils.toInputStream(getInputString(), Charset.defaultCharset());
        httpGet.addHeader("Content-Type", "application/json");
        httpGet.setEntity(new InputStreamEntity(inputStream));
        try {
            final CloseableHttpResponse response = httpClient.execute(httpGet);
            System.out.println("response = " + IOUtils.toString(response.getEntity().getContent(), Charset.defaultCharset()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getInputString() {
        final Company company = new Company();
        company.setId(1);
        company.setName("ZeRen");
        company.setLegalPerson("");
        company.setRegisteredCipital(0.0D);
        company.setRegistrationTime(new Date());
        company.setPhone("14311110000");
        company.setAddress("");

        return JSONObject.toJSONString(company);
    }
}
