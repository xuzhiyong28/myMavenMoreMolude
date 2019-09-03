package xzy.java8.lambda;

public class Apple {
    //颜色
    private String color;
    //重量
    private int weight;
    //价格
    private int price;

    public Apple(String color, int weight, int price) {
        this.color = color;
        this.weight = weight;
        this.price = price;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                '}';
    }
}
