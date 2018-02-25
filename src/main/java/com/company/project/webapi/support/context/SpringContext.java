package com.company.project.webapi.support.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring上下文
 *
 * @author wangzhj
 */
@Component
public class SpringContext implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    /**
     * 获取Bean
     */
    public static <T> T getBean(String name) {
        T bean = (T) context.getBean(name);
        return bean;
    }

    public static <T> T getBean(Class<T> clazz) {
        T bean = (T) context.getBean(clazz);
        return bean;
    }

    /**
     * 是否存在Bean
     */
    public static boolean containsBean(String name) {
        return context.containsBean(name);
    }

    public static boolean containsBean(Class<?> clazz) {
        boolean isExist = false;
        if (context.getBean(clazz) != null) {
            isExist = true;
        }
        return isExist;
    }
}
