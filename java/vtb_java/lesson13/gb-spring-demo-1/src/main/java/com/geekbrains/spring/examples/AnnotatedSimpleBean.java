package com.geekbrains.spring.examples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component // ("annotatedSimpleBean")
// @Scope("prototype")
public class AnnotatedSimpleBean {
    @Autowired
    private ClassNameGenerator classNameGenerator;

    @Value("initial string")
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ClassNameGenerator getClassNameGenerator() {
        return classNameGenerator;
    }

//    @Autowired
    public void setClassNameGenerator(ClassNameGenerator classNameGenerator) {
        this.classNameGenerator = classNameGenerator;
    }

    @Autowired
    public AnnotatedSimpleBean(ClassNameGenerator classNameGenerator) {
        this.classNameGenerator = classNameGenerator;
    }

    public void printRandomClassName() {
        System.out.println(classNameGenerator.getRandomClassName());
    }
}
