package com.sun.demo.io.tomcat;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class ParentRequest {
    private String method;
    private String url;

    private List<String> httpRequest;

    public ParentRequest(InputStream in) {
        try {
            String httpRequest = "";
            byte[] httpRequestBytes = new byte[1024];
            int length = 0;
            if ((length = in.read(httpRequestBytes)) > 0) {
                httpRequest = new String(httpRequestBytes, 0, length);
            }
            if (StringUtils.isNotEmpty(httpRequest)) {
                this.httpRequest = Arrays.asList(StringUtils.split(httpRequest, "\n"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (CollectionUtils.isNotEmpty(this.httpRequest)) {
            String head = httpRequest.get(0);
            String[] http = head.split(" ");

            this.method = http[0];
            this.url = http[1];
        }
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

}
