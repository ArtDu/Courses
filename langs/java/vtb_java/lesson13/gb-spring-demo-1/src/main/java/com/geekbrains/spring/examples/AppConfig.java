package com.geekbrains.spring.examples;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.geekbrains.spring")
public class AppConfig {
    /*
    <bean id="javaCodeCreator" class="com.geekbrains.spring.examples.JavaCodeCreator">
        <property name="classNameGenerator">
            <ref bean="animalsClassNameGenerator" />
        </property>
    </bean>

    <bean id="animalsClassNameGenerator" class="com.geekbrains.spring.examples.AnimalsClassNameGenerator">

    </bean>
     */

    @Bean("animalsClassNameGenerator")
    public ClassNameGenerator animalsClassNameGenerator() {
        ClassNameGenerator classNameGenerator = new AnimalsClassNameGenerator();
        return classNameGenerator;
    }

    @Bean("javaCodeCreator")
    public CodeCreator javaCodeCreator() {
        CodeCreator codeCreator = new JavaCodeCreator();
        ((JavaCodeCreator) codeCreator).setClassNameGenerator(animalsClassNameGenerator());
        return codeCreator;
    }
}
