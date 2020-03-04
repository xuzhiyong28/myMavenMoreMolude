package xom.xzy.mvmoremodule;

public class MyTestModel {
    public String name;
    public String age;

    public MyTestModel(String name, String age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "TestModel{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
