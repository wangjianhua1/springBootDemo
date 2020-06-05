package com.example.demo.service;

import org.springframework.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

public class CglibCallackFilter implements CallbackFilter {
    @Override
    public int accept(Method method) {
        return 0;//返回第一个
    }
}
