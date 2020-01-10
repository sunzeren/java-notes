package com.sun.demo.request;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

import java.net.URI;

/**
 * @Author: ZeRen.
 * @Date: 2020/1/10 11:23
 */
public class HttpGetOfBody extends HttpEntityEnclosingRequestBase {


    public HttpGetOfBody(final String uri) {
        super();
        setURI(URI.create(uri));
    }

    @Override
    public String getMethod() {
        return "GET";
    }
}
