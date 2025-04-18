package com.dousheng.message.common.biz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtils implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextUtils.context = applicationContext;
    }

    public static <T> T getBean(Class<T> requiredType) {
        return context.getBean(requiredType);
    }
}
