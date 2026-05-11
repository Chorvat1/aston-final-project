package org.aston.carsorting.model;

import org.aston.carsorting.util.CarModel;

public class Car {
    private int power;
    private CarModel model;
    private int year;

    private Car(CarBuilder carBuilder) {
        this.power = carBuilder.power;
        this.model = carBuilder.model;
        this.year = carBuilder.year;
    }

    public int getPower() {
        return power;
    }

    public CarModel getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Car{" +
                "power=" + power +
                ", model='" + model + '\'' +
                ", year=" + year +
                '}';
    }

    public static class CarBuilder {
        private int power;
        private CarModel model;
        private int year;

        public CarBuilder() {}

        public CarBuilder setPower (int power) {
            this.power = power;
            return this;
        }

        public CarBuilder setModel (CarModel model) {
            this.model = model;
            return this;
        }

        public CarBuilder setYear (int year) {
            this.year = year;
            return this;
        }

        public Car build() {
            return new Car(this);
        }

    }
}