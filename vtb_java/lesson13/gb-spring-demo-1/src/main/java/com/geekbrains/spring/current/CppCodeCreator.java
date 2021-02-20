package com.geekbrains.spring.current;

public class CppCodeCreator implements CodeCreator {
    ClassNameGenerator classNameGenerator;

    public void setClassNameGenerator(ClassNameGenerator classNameGenerator) {
        this.classNameGenerator = classNameGenerator;
    }

    public String getClassExample() {
        return "public class " + classNameGenerator.generateClassName() + " {\n\n}";
    }

    public CppCodeCreator(ClassNameGenerator classNameGenerator) {
        this.classNameGenerator = classNameGenerator;
    }
}
