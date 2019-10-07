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
