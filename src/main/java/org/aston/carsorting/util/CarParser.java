package org.aston.carsorting.util;

import org.aston.carsorting.model.Car;
import org.aston.carsorting.validation.CarValidator;

public class CarParser {

    public Car fromString(String line) {
        String[] parts = line.split(";");

        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid format: " + line +
                    ". Expected: MODEL;power;year");
        }

        String modelStr = parts[0].trim().toUpperCase();
        if (!CarValidator.isValidModel(modelStr)) {
            throw new IllegalArgumentException("Invalid model: " + modelStr +
                    ". Available: " + getAvailableModels());
        }

        int power;
        try {
            power = Integer.parseInt(parts[1].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid power format: " + parts[1]);
        }
        if (!CarValidator.isValidPower(power)) {
            throw new IllegalArgumentException("Invalid power: " + power +
                    ". Must be between " + CarValidator.MIN_POWER + " and " + CarValidator.MAX_POWER);
        }

        int year;
        try {
            year = Integer.parseInt(parts[2].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid year format: " + parts[2]);
        }
        if (!CarValidator.isValidYear(year)) {
            throw new IllegalArgumentException("Invalid year: " + year +
                    ". Must be between " + CarValidator.MIN_YEAR + " and " + CarValidator.getMaxYear());
        }

        return new Car.CarBuilder()
                .setModel(CarModel.valueOf(modelStr))
                .setPower(power)
                .setYear(year)
                .build();
    }

    public String toString(Car car) {
        return car.getModel().name() + ";" + car.getPower() + ";" + car.getYear();
    }

    private String getAvailableModels() {
        CarModel[] models = CarModel.values();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < models.length; i++) {
            if (i > 0) sb.append(", ");
            sb.append(models[i].name());
        }
        return sb.toString();
    }
}