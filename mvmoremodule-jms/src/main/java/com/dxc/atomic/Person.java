package com.dxc.atomic;/**
 * Created by Administrator on 2018-06-08.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-06-08-15:06
 */
public class Person {
    private String name;
    private int age;



    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

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

    public String toString() {
        return "[name: " + this.name + ", age: " + this.age + "]";
    }

}
