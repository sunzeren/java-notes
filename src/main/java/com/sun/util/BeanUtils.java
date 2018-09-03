package com.sun.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class BeanUtils implements ApplicationContextAware {
	
	private static ApplicationContext applicationContext;

	/**
	 * @Author SuZeRen
	 * @Description 该方法主要用于获取Spring管理的Bean
	 * @Date 14:58 2018/9/3
	 * @Param [beanName]
	 * @return java.lang.Object
	 **/
	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}

	public static <T> T getBean(String beanName, Class<T> clazs) {
		return clazs.cast(getBean(beanName));
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		BeanUtils.applicationContext = applicationContext;
	}
}
