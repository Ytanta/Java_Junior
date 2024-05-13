package javajun;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

public class Homework {

  /**
   * Используя классы Person и Department, реализовать методы ниже:
   */

  private static class Person {
    private String name;
    private int age;
    private double salary;
    private Department department;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getAge() {
      return age;
    }

    public void setAge(int age) {
      this.age = age;
    }

    public double getSalary() {
      return salary;
    }

    public void setSalary(double salary) {
      this.salary = salary;
    }

    public Department getDepartment() {
      return department;
    }

    public void setDepartment(Department department) {
      this.department = department;
    }

    @Override
    public String toString() {
      return "Person{" +
        "name='" + name + '\'' +
        ", age=" + age +
        ", salary=" + salary +
        ", department=" + department +
        '}';
    }
  }

  private static class Department {
    private String name;

    public Department(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }

    @Override
    public String toString() {
      return "Department{" +
        "name='" + name + '\'' +
        '}';
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Department that = (Department) o;
      return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
      return Objects.hash(name);
    }
  }

  /**
   * Найти количество сотрудников, старше x лет с зарплатой больше, чем d
   */
  static int countPersons(List<Person> persons, int x, double d) {
    // TODO: Реализовать метод
    persons.stream()
            .filter(age -> age.getAge() > x )
            .filter(salary -> salary.getSalary() > d)
            .forEach(System.out::println);
    throw new UnsupportedOperationException();
  }

  /**
   * Найти среднюю зарплату сотрудников, которые работают в департаменте X
   */
  static OptionalDouble averageSalary(List<Person> persons, int x) {
    // TODO: Реализовать метод
     persons.stream()
            .filter(person -> person.getDepartment().getName().equals(x))
            .mapToDouble(Person::getSalary)
            .average()
            .orElse(Double.NaN);
    throw new UnsupportedOperationException();
  }

  /**
   * Сгруппировать сотрудников по департаментам
   */
  static Map<Department, List<Person>> groupByDepartment(List<Person> persons) {
    // TODO: Реализовать метод
    persons.stream()
            .collect(groupingBy(Person::getDepartment));
    throw new UnsupportedOperationException();
  }

  /**
   * Найти максимальные зарплаты по отделам
   */
  static Map<Department, Double> maxSalaryByDepartment(List<Person> persons) {
    // TODO: Реализовать метод
    persons.stream()
            .collect(Collectors.toMap(
                    e -> e.department,
                    e -> e,
                    BinaryOperator.maxBy(Comparator.comparingInt(e -> (int) e.salary))
            ));
    throw new UnsupportedOperationException();
  }

  /**
   * ** Сгруппировать имена сотрудников по департаментам
   */
  static Map<Department, List<String>> groupPersonNamesByDepartment(List<Person> persons) {
    // TODO: Реализовать метод
    persons.stream()
            .collect(Collectors.groupingBy(
                    Person::getDepartment,
                    Collectors.mapping(Person::getName,
                            Collectors.toList())));
    throw new UnsupportedOperationException();
  }

  /**
   * ** Найти сотрудников с минимальными зарплатами в своем отделе
   */
  static List<Person> minSalaryPersons(List<Person> persons) {
    // TODO: Реализовать метод
    persons.stream()
            .collect(Collectors.groupingBy(Person::getDepartment,
            Collectors.groupingBy(Person::getSalary,
                    TreeMap::new, Collectors.toList())))
            .entrySet().stream()
            .collect(Collectors.toMap(e -> e.getKey(),
                    e -> e.getValue().firstEntry().getValue()));
    // В каждом департаменте ищем сотрудника с минимальной зарплатой.
    // Всех таких сотрудников собираем в список и возвращаем из метода.
    throw new UnsupportedOperationException();
  }

}
