package xzy.java8.optional;

import java.util.Optional;

/**
 * @author xuzhiyong
 * @createDate 2019-09-04-21:33
 */
public class Car {
    private Optional<Car> car;

    public Optional<Car> getCar() {
        return car;
    }

    public void setCar(Optional<Car> car) {
        this.car = car;
    }
}
