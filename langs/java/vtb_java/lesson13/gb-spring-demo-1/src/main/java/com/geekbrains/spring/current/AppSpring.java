package com.geekbrains.spring.current;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppSpring {
    public static void main(String[] args) {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("myContext.xml");
//
//        SimpleBean simpleBean1 = context.getBean("simpleBean", SimpleBean.class);
//        SimpleBean simpleBean2 = context.getBean("simpleBean", SimpleBean.class);
//        simpleBean1.doSomething();
//        simpleBean2.doSomething();
//        simpleBean2.setValue(10);
//        simpleBean1.doSomething();
//        simpleBean2.doSomething();
//
//        CodeCreator codeCreator = context.getBean("javaCodeCreator", CodeCreator.class);
//        System.out.println(codeCreator.getClassExample());
//
//        CodeCreator cppCodeCreator = context.getBean("cppCodeCreator", CodeCreator.class);
//        System.out.println(cppCodeCreator.getClassExample());
//
//        context.close();

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        CodeCreator javaCC = context.getBean("javaCodeCreator", CodeCreator.class);
//        System.out.println(javaCC.getClassExample());

        SimpleBean simpleBean = context.getBean("simpleBean", SimpleBean.class);
        simpleBean.doSomething();

        CodeCreator javaCodeCreator = context.getBean("javaCodeCreator", CodeCreator.class);
        System.out.println(javaCodeCreator.getClassExample());

        AnnotatedBean annotatedBean = context.getBean("annotatedBean", AnnotatedBean.class);
        annotatedBean.example();

        ((AnnotationConfigApplicationContext) context).close();
    }
}
