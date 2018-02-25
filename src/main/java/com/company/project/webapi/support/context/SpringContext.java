package com.company.project.webapi.support.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Spring上下文
 *
 * @author wangzhj
 */
public class SpringContext implements ApplicationContextAware {

    private static ApplicationContext CXT;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CXT = applicationContext;
    }

    /**
     * 获取Bean
     *
     * @param name
     * @return T
     */
    public static <T> T getBean(String name) {
        T bean = (T) CXT.getBean(name);
        return bean;
    }

    /**
     * 获取Bean
     *
     * @param clazz
     * @return T
     */
    public static <T> T getBean(Class<T> clazz) {
        T bean = (T) CXT.getBean(clazz);
        return bean;
    }

    /**
     * 是否存在Bean
     */
    public static boolean containsBean(String name) {
        return CXT.containsBean(name);
    }

    public static boolean containsBean(Class<?> clazz) {
        boolean isExist = false;
        if (CXT.getBean(clazz) != null) {
            isExist = true;
        }
        return isExist;
    }
}
