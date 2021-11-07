package vtb.geekbrains;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // 1
        List<String> words = new ArrayList<>(
                Arrays.asList("Hello", "Привет", "Hello", "Bye", "Привет", "Пока", "Hello"));
        System.out.println(
                words.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                        .entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue())
                        .get());


        // 2
        List<Employee> employees = new ArrayList<>(
                Arrays.asList(
                        new Employee("Art", 22, 101),
                        new Employee("Den", 21, 150),
                        new Employee("Yaroslav", 18, 140),
                        new Employee("Danila", 19, 90),
                        new Employee("Vlad", 20, 90)
                ));
        System.out.println(employees.stream().mapToDouble(Employee::getSalary).average());

        // 3
        System.out.println(nOldest(3, employees));
        System.out.println(nOldest(2, employees));
    }

    static String nOldest(Integer n, List<Employee> employees) {
        return employees.stream()
                .sorted(Comparator.comparing(Employee::getAge).reversed())
                .limit(n)
                .map(Employee::getName)
                .collect(Collectors.joining(", ", n + "самых старших сотрудников зовут: ", " ;"));
    }

}
