package com.example.demo.service;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;

/**
 * 1.replace-method
 * 2.cglib代理，原始代码不可更改，但需要对方法加权限校验
 * 3.aop
 */
public class MenuReplaceService implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MenuServiceImpl.class);
        CglibCallackFilter filter = new CglibCallackFilter();
        enhancer.setCallbackFilter(filter);
        AddMenuInterceptor addMenuInterceptor = new AddMenuInterceptor();
        enhancer.setCallbacks(new Callback[]{addMenuInterceptor});

        MenuServiceImpl menuService = (MenuServiceImpl) enhancer.create();

        return menuService;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
