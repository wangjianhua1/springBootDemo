package com.example.demo.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * dataSource找不到
 */
public class MyBeanFactory {
    private static ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/spring-common.xml");

    /**
     * 通过beanName获取对象
     *
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
        return ctx.getBean(beanName);
    }

}
