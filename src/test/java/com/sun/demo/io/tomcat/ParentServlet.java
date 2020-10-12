package com.sun.demo.io.tomcat;

public abstract class ParentServlet {
    public void service(ParentRequest request, ParentResponse response) throws Exception {
        //service 方法决定调用doGet、doPost；
        if (RequestMethodEnum.GET.code.equalsIgnoreCase(request.getMethod())) {
            doGet(request, response);
        } else {
            doPost(request, response);
        }
    }

    protected abstract void doPost(ParentRequest request, ParentResponse response) throws Exception;

    protected abstract void doGet(ParentRequest request, ParentResponse response) throws Exception;
}