package xzy.java8;

public class Employee {
    private String name;
    private int salary;

    public Employee() {
        this.salary = 4000;
    }

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("name:").append(name)
                .append(",salary:").append(salary)
                .toString();
    }
}
