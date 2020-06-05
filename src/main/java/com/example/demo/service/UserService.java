package com.example.demo.service;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * bean初始化，PostConstruct, afterPropertiesSet(直接)执行，initMethod(反射执行)执行
 *
 *Constructor > @PostConstruct > InitializingBean > init-method
 */
@Service
public class UserService implements InitializingBean, DisposableBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("===UserService====afterPropertiesSet===");
    }

    @PostConstruct
    public void selectUser(){
        System.out.println("查询用户");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("UserService实例销毁");
    }
}
