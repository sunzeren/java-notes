package com.sun;

import com.sun.listener.EnvironmentListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer {

    /**
     * 带有参数的启动方式
     * 此处可设置监听器,拦截器,类加载器等
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(DemoApplication.class);
        application.addListeners(new EnvironmentListener());
        application.run(args);
    }

    ///**

    /**
     * 重写此方法用于打包
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DemoApplication.class);
    }
    // * 极简的启动方式
    // *
    // * @param args
    // */
    //public static void main(String[] args) {
    //    SpringApplication.run(DemoApplication.class, args);
    //}

}
