package com.geekbrains.spring.current;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.geekbrains.spring.current")
public class AppConfig {
    /*
    <bean id="javaCodeCreator" class="com.geekbrains.spring.current.JavaCodeCreator">
        <property name="classNameGenerator">
            <ref bean="animalsClassNameGenerator" />
        </property>
    </bean>

    <bean id="simpleBean" class="com.geekbrains.spring.current.SimpleBean"/>
     */

    @Bean
    public CodeCreator javaCodeCreator() {
        JavaCodeCreator codeCreator = new JavaCodeCreator();
        codeCreator.setClassNameGenerator(animalsClassNameGenerator());
        return codeCreator;
    }

    @Bean
    public ClassNameGenerator animalsClassNameGenerator() {
        ClassNameGenerator classNameGenerator = new AnimalsClassNameGenerator();
        return classNameGenerator;
    }

    @Bean
    public SimpleBean simpleBean() {
        SimpleBean simpleBean = new SimpleBean();
        return simpleBean;
    }
}
