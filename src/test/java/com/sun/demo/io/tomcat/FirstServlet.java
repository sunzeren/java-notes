package com.sun.demo.io.tomcat;

public class FirstServlet extends ParentServlet {
    @Override
    protected void doPost(ParentRequest request, ParentResponse response) throws Exception {
        response.write("This is the first");
    }

    @Override
    protected void doGet(ParentRequest request, ParentResponse response) throws Exception {
        this.doPost(request, response);
    }
}