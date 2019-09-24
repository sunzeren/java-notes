package com.sun.config;

import com.sun.interceptor.FirstInterceptor;
import com.sun.interceptor.SecondInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: ZeRen.
 * @Date: 2019/9/24 13:47
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private FirstInterceptor firstInterceptor;
    @Autowired
    private SecondInterceptor secondInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //拦截器的顺序和添加顺序有关,addInterceptor()方法中使用 List实现的

        // /**表示添加所有拦截路径
        // 如需匹配不拦截某个请求可使用excludePathPatterns()方法,如下表是不拦截 /api路径,下,以notify.json结尾的请求
        registry.addInterceptor(firstInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/api/**/*notify.json");

        registry.addInterceptor(secondInterceptor)
                .addPathPatterns("/**");
    }
}
