package com.geekbrains.spring.examples;

public class JavaCodeCreator implements CodeCreator {
    private ClassNameGenerator classNameGenerator;

    public ClassNameGenerator getClassNameGenerator() {
        return classNameGenerator;
    }

    public void setClassNameGenerator(ClassNameGenerator classNameGenerator) {
        this.classNameGenerator = classNameGenerator;
    }

    public String getClassExample() {
        return "public class " + classNameGenerator.getRandomClassName() + " {\n   private String name;\n   ...\n}";
    }
}
