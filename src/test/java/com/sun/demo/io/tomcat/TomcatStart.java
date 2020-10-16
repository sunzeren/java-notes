package com.sun.demo.io.tomcat;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TomcatStart {
    private static final int port = 8080;
    private static ServerSocket server;
    private static final Map<String, ParentServlet> servletMapping = new HashMap<>();
    private static final Properties webProperties = new Properties();

    public static void main(String[] args) {
        //启动
        new TomcatStart().start();
    }

    private void init() {
        try {
            String WEB_INF = this.getClass().getResource("/").getPath();
            FileInputStream fis = new FileInputStream(WEB_INF + "web.properties");
            webProperties.load(fis);
            for (Object k : webProperties.keySet()) {
                String key = k.toString();
                if (key.endsWith(".url")) {
                    String servletName = key.replaceAll("\\.url$", "");
                    String url = webProperties.getProperty(key);
                    String className = webProperties.getProperty(servletName + ".className");
                    //单实例  多线程
                    ParentServlet obj = (ParentServlet) Class.forName(className).newInstance();
                    servletMapping.put(url, obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        //1.加载配置类，初始化servletMapping
        init();
        try {
            //2.绑定端口启动
            server = new ServerSocket(port);
            System.out.println("Tomcat 已启动，监听端口是：" + port);
            //3.等待用户请求，用一个死循环
            while (true) {
                Socket client = server.accept();
                //4.http 请求
                process(client);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void process(Socket client) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = client.getInputStream();
            os = client.getOutputStream();
            // 5.Request(inputstream) Response (outputstream)

            // 此处暂未知为何使用如下方法时，会阻塞线程
//            List<String> lines = IOUtils.readLines(is, "UTF-8");
//            System.out.println(lines);

            ParentRequest request = new ParentRequest(is);
            ParentResponse response = new ParentResponse(os);
            //6.从协议内容中获取url 映射相应的servlet
            String url = request.getUrl();
            if (servletMapping.containsKey(url)) {
                //7.调用实例化对象的service方法
                servletMapping.get(url).service(request, response);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("HTTP/1.1 404 Not Found\n")
                    .append("Content-Type: text/html\n")
                    .append("\r\n");
                response.write(sb.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                os.flush();
                os.close();
            }
            if (is != null) {
                is.close();
            }
            client.close();
        }
    }
}
