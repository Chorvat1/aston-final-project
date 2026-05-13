package org.aston.carsorting.input;

import org.aston.carsorting.Main;
import org.aston.carsorting.model.CarList;
import org.aston.carsorting.model.CarArrayList;
import org.aston.carsorting.model.Car;
import org.aston.carsorting.util.CarModel;
import org.aston.carsorting.validation.CarValidator;

import java.util.Scanner;
import java.util.stream.IntStream;

public class ManualInputStrategy implements InputStrategy {

    private final Scanner scanner = new Scanner(System.in);

    private int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка! Введите целое число.");
            }
        }
    }

    @Override
    public CarList getData() {
        int n = getIntInput("Enter number of cars: ");

        CarList collection = new CarArrayList();


        if (Main.dop3){
            IntStream.range(0, n)
                    .mapToObj(this::carFromInput)
                    .forEach(collection::add);
        } else {
            for (int i = 0; i < n; i++) {
                collection.add(carFromInput(i));
            }
        }

        return collection;
    }

    private Car carFromInput(int i){
        CarModel[] models = CarModel.values();
        System.out.println("\nCar " + (i + 1) + ":");

        System.out.println("Select model:");
        for (int j = 0; j < models.length; j++) {
            System.out.println((j + 1) + ". " + models[j].name());
        }

        int modelChoice;
        while (true) {
            modelChoice = getIntInput("Enter number (1-" + models.length + "): ");
            if (modelChoice >= 1 && modelChoice <= models.length) {
                break;
            }
            System.out.println("Invalid choice! Enter number between 1 and " + models.length);
        }
        CarModel model = models[modelChoice - 1];

        int power;
        while (true) {
            power = getIntInput("Power (" + CarValidator.MIN_POWER + "-" + CarValidator.MAX_POWER + "): ");
            if (CarValidator.isValidPower(power)) {
                break;
            }
            System.out.println("Invalid power! Must be between " +
                    CarValidator.MIN_POWER + " and " + CarValidator.MAX_POWER + ".");
        }

        int year;
        while (true) {
            year = getIntInput("Year (" + CarValidator.MIN_YEAR + "-" + CarValidator.getMaxYear() + "): ");
            if (CarValidator.isValidYear(year)) {
                break;
            }
            System.out.println("Invalid year! Must be between " +
                    CarValidator.MIN_YEAR + " and " + CarValidator.getMaxYear() + ".");
        }

        return new Car.CarBuilder()
                .setModel(model)
                .setPower(power)
                .setYear(year)
                .build();
    }
}