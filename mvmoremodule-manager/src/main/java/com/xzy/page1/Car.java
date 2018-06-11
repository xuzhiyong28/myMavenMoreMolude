package com.xzy.page1;/**
 * Created by Administrator on 2018-04-18.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-04-18-8:53
 */
public class Car {
    public String brand;
    public String color;
    public int maxSpeed;

    public Car(){}

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", maxSpeed=" + maxSpeed +
                '}';
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}
