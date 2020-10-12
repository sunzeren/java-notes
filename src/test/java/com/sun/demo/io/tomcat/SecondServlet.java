package com.sun.demo.io.tomcat;

public class SecondServlet extends ParentServlet {
    @Override
    protected void doPost(ParentRequest request, ParentResponse response) throws Exception {
        response.write("this is the second");
    }

    @Override
    protected void doGet(ParentRequest request, ParentResponse response) throws Exception {
        this.doPost(request, response);
    }
}