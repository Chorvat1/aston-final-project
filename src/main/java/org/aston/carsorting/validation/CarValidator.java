package org.aston.carsorting.validation;

import org.aston.carsorting.util.CarModel;

public class CarValidator {

    public static final int MIN_POWER = 50;
    public static final int MAX_POWER = 400;
    public static final int MIN_YEAR = 1970;

    public static int getMaxYear() {
        return java.time.Year.now().getValue() + 1;
    }

    public static boolean isValidPower(int power) {
        return power >= MIN_POWER && power <= MAX_POWER;
    }

    public static boolean isValidYear(int year) {
        return year >= MIN_YEAR && year <= getMaxYear();
    }

    public static boolean isValidModel(String modelStr) {
        try {
            CarModel.valueOf(modelStr.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}