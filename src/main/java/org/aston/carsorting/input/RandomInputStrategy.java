package org.aston.carsorting.input;

import org.aston.carsorting.validation.CarValidator;
import org.aston.carsorting.model.CarList;
import org.aston.carsorting.model.CarArrayList;
import org.aston.carsorting.model.Car;
import org.aston.carsorting.util.CarModel;

import java.util.Random;

public class RandomInputStrategy implements InputStrategy {
    private final int size;
    private final Random random = new Random();

    public RandomInputStrategy(int size) {
        this.size = size;
    }

    @Override
    public CarList getData() {
        CarList collection = new CarArrayList();
        CarModel[] models = CarModel.values();

        for (int i = 0; i < size; i++) {
            int power = CarValidator.MIN_POWER + random.nextInt(CarValidator.MAX_POWER -  CarValidator.MIN_POWER + 1);
            int year = CarValidator.MIN_YEAR + random.nextInt(CarValidator.getMaxYear() -  CarValidator.MIN_YEAR + 1);
            Car car = new Car.CarBuilder()
                    .setModel(models[random.nextInt(models.length)])
                    .setPower(power)
                    .setYear(year)
                    .build();

            collection.add(car);
        }

        return collection;
    }
}