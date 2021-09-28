package com.geekbrains.spring.examples;

import org.springframework.beans.factory.annotation.Qualifier;

public class CppCodeCreator implements CodeCreator {
    private ClassNameGenerator classNameGenerator;

    public CppCodeCreator(ClassNameGenerator classNameGenerator) {
        this.classNameGenerator = classNameGenerator;
    }

    public String getClassExample() {
        return "public class " + classNameGenerator.getRandomClassName() + " {\nprivate:\n   String name;\n   ...\n}";
    }
}
