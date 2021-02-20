package com.geekbrains.spring.current.simple;

import com.geekbrains.spring.current.AnnotatedBean;
import com.geekbrains.spring.current.AppConfig;
import com.geekbrains.spring.current.CodeCreator;
import com.geekbrains.spring.current.SimpleBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ShopService shopService = context.getBean("shopService", ShopService.class);
        shopService.buy();
        ((AnnotationConfigApplicationContext) context).close();
    }
}
