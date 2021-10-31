package com.geekbrains.spring.current.simple;

import org.springframework.stereotype.Component;

@Component
public class AdvBeanInterfaceImpl implements BeanInterface {
    public void doSomething() {
        System.out.println(2);
    }
}
