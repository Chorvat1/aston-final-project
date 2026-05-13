package org.aston.carsorting.input;

import org.aston.carsorting.Main;
import org.aston.carsorting.validation.CarValidator;
import org.aston.carsorting.model.CarList;
import org.aston.carsorting.model.CarArrayList;
import org.aston.carsorting.model.Car;
import org.aston.carsorting.util.CarModel;

import java.util.Random;
import java.util.stream.IntStream;

public class RandomInputStrategy implements InputStrategy {
    private final int size;
    private final Random random = new Random();

    public RandomInputStrategy(int size) {
        this.size = size;
    }

    @Override
    public CarList getData() {
        CarList collection = new CarArrayList();
        if (Main.dop3){
            IntStream.range(0, size)
                    .mapToObj(this::carFromRandom)
                    .forEach(collection::add);
        } else {
            for (int i = 0; i < size; i++) {
                collection.add(carFromRandom(i));
            }
        }

        return collection;
    }

    private Car carFromRandom(int i){
        CarModel[] models = CarModel.values();
        int power = CarValidator.MIN_POWER + random.nextInt(CarValidator.MAX_POWER -  CarValidator.MIN_POWER + 1);
        int year = CarValidator.MIN_YEAR + random.nextInt(CarValidator.getMaxYear() -  CarValidator.MIN_YEAR + 1);
        return new Car.CarBuilder()
                .setModel(models[random.nextInt(models.length)])
                .setPower(power)
                .setYear(year)
                .build();
    }
}