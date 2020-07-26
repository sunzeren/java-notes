package com.sun.listener;

import com.google.common.collect.Lists;
import com.sun.constant.AppSetting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @Author: ZeRen.
 * @Date: 2019/10/7 13:35
 * <p>
 * 环境参数监听器,当SpringBoot读取配置后启动前会进行此操作(需要在DemoApplication中设置listener)
 * 也有其他很多实现 ApplicationListener 接口的类,可使用
 * 如:ApplicationReadyEvent
 * 此事件为应用程序启动完毕时所执行的事件,此时Bean已装载完毕,可以进行@Autowired注入service,进行数据库操作等
 * 可用于检查程序中是否存在某些数据,并做一些操作(如:检查某个表是否存在某条记录,如不存在则添加等)
 */
@Slf4j
public class EnvironmentListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment environment = event.getEnvironment();
        String[] profiles = environment.getActiveProfiles();
        if (profiles != null) {
            AppSetting.PROFILES_ACTIVE = Lists.newArrayList(profiles);
        }

        String applicationName = environment.getProperty("spring.application.name");
        if (applicationName != null) {
            AppSetting.APP_NAME = applicationName;
        }

        String serverPort = environment.getProperty("server.port");
        if (serverPort != null) {
            AppSetting.PORT = serverPort;
        }
        log.info("AppSetting 已设置完毕!");
    }
}
