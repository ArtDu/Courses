package com.geekbrains.spring.examples;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass {
//    public static void main(String[] args) {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//
//        CodeCreator javaCC = context.getBean("javaCodeCreator", CodeCreator.class);
//        System.out.println(javaCC.getClassExample());
//
//        CodeCreator cppCC = context.getBean("cppCodeCreator", CodeCreator.class);
//        System.out.println(cppCC.getClassExample());
//
//        SimpleBean simpleBean = context.getBean("simpleBean", SimpleBean.class);
//        simpleBean.setData("Z");
//        System.out.println(simpleBean.getData());
//
//        SimpleBean simpleBean2 = context.getBean("simpleBean", SimpleBean.class);
//        System.out.println(simpleBean2.getData());
//
//        AnnotatedSimpleBean annotatedSimpleBean = context.getBean("annotatedSimpleBean", AnnotatedSimpleBean.class);
//        annotatedSimpleBean.setData("A");
//        System.out.println(annotatedSimpleBean.getData());
//        annotatedSimpleBean.printRandomClassName();
//
//        context.close();
//    }
//
//    public static void main(String[] args) {
//        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        CodeCreator javaCC = context.getBean("javaCodeCreator", CodeCreator.class);
//        System.out.println(javaCC.getClassExample());
//        ((AnnotationConfigApplicationContext) context).close();
//    }
}
