package org.aston.carsorting.model;

public class Car {
    private int power;
    private String model;
    private int year;

    private Car() {
    }

    public int getPower() {
        return power;
    }

    public String getModel() {
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

    public class CarBuilder {
        private CarBuilder() {}

        public CarBuilder setPower (int power) {
            Car.this.power = power;
            return this;
        }

        public CarBuilder setModel (String model) {
            Car.this.model = model;
            return this;
        }

        public CarBuilder setYear (int year) {
            Car.this.year = year;
            return this;
        }

        public Car build() {
            return Car.this;
        }

    }
}
