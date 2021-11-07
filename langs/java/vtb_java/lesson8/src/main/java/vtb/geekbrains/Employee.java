package vtb.geekbrains;

public class Employee {
    private final String name;
    private final Integer age;
    private final Integer salary;

    public Employee(String name, Integer age, Integer salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public Integer getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
