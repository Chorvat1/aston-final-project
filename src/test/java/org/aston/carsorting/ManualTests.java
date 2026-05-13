package org.aston.carsorting;

import org.aston.carsorting.model.Car;
import org.aston.carsorting.util.CarModel;
import org.aston.carsorting.validation.CarValidator;
import org.aston.carsorting.util.CarParser;
import org.aston.carsorting.comparator.PowerComparator;
import org.aston.carsorting.comparator.YearComparator;
import org.aston.carsorting.comparator.ModelComparator;
import org.aston.carsorting.comparator.TotalComparator;

public class ManualTests {
    public static void main(String[] args) {
        testBuilder();
        testValidator();
        testParser();
        testComparators();
        // testSorts();
    }

    private static void testBuilder() {
        System.out.println("=== ТЕСТ: Builder ===");

        Car car = new Car.CarBuilder()
                .setModel(CarModel.BMW)
                .setPower(300)
                .setYear(2020)
                .build();

        check("model == BMW", car.getModel() == CarModel.BMW);
        check("power == 300", car.getPower() == 300);
        check("year == 2020", car.getYear() == 2020);

        System.out.println("---");

        Car emptyCar = new Car.CarBuilder().build();

        check("empty power == 0", emptyCar.getPower() == 0);
        check("empty model == null", emptyCar.getModel() == null);
        check("empty year == 0", emptyCar.getYear() == 0);

        System.out.println("---");
    }

    private static void testValidator() {
        System.out.println("=== ТЕСТ: Validator ===");

        check("power 100 valid", CarValidator.isValidPower(100));
        check("power 0 invalid", !CarValidator.isValidPower(0));
        check("power -10 invalid", !CarValidator.isValidPower(-10));
        check("power 1000 invalid", !CarValidator.isValidPower(1000));

        System.out.println("---");

        check("year 2020 valid", CarValidator.isValidYear(2020));
        check("year 1960 invalid", !CarValidator.isValidYear(1960));
        check("year too big invalid", !CarValidator.isValidYear(CarValidator.getMaxYear() + 1));

        System.out.println("---");

        check("model BMW valid", CarValidator.isValidModel("BMW"));
        check("model TOYOTA valid", CarValidator.isValidModel("TOYOTA"));
        check("model UNKNOWN invalid", !CarValidator.isValidModel("UNKNOWN"));
        check("model abc invalid", !CarValidator.isValidModel("abc"));
        check("empty model invalid", !CarValidator.isValidModel(""));

        System.out.println("---");
    }

    private static void testParser() {
        System.out.println("=== ТЕСТ: Parser ===");

        CarParser parser = new CarParser();

        // Тест 1: нормальная строка
        Car car = parser.fromString("BMW;300;2020");
        check("parsed model == BMW", car.getModel() == CarModel.BMW);
        check("parsed power == 300", car.getPower() == 300);
        check("parsed year == 2020", car.getYear() == 2020);

        System.out.println("---");

        // Тест 2: невалидная модель
        try {
            parser.fromString("UNKNOWN;300;2020");
            check("unknown model throws", false);
        } catch (IllegalArgumentException e) {
            check("unknown model throws", true);
        }

        // Тест 3: невалидный формат мощности
        try {
            parser.fromString("BMW;abc;2020");
            check("bad power throws", false);
        } catch (IllegalArgumentException e) {
            check("bad power throws", true);
        }

        // Тест 4: неполная строка
        try {
            parser.fromString("BMW;300");
            check("incomplete line throws", false);
        } catch (IllegalArgumentException e) {
            check("incomplete line throws", true);
        }

        System.out.println("---");
    }

    private static void testComparators() {
        System.out.println("=== ТЕСТ: Comparators ===");

        Car weak = new Car.CarBuilder().setModel(CarModel.AUDI).setPower(100).setYear(2020).build();
        Car strong = new Car.CarBuilder().setModel(CarModel.AUDI).setPower(300).setYear(2020).build();
        Car oldCar = new Car.CarBuilder().setModel(CarModel.BMW).setPower(200).setYear(2000).build();
        Car newCar = new Car.CarBuilder().setModel(CarModel.BMW).setPower(200).setYear(2023).build();
        Car audi = new Car.CarBuilder().setModel(CarModel.AUDI).setPower(200).setYear(2020).build();
        Car toyota = new Car.CarBuilder().setModel(CarModel.TOYOTA).setPower(200).setYear(2020).build();

        // PowerComparator
        PowerComparator powerComp = new PowerComparator();
        check("weak < strong", powerComp.compare(weak, strong) < 0);
        check("strong > weak", powerComp.compare(strong, weak) > 0);
        check("same power == 0", powerComp.compare(weak, weak) == 0);

        System.out.println("---");

        // YearComparator
        YearComparator yearComp = new YearComparator();
        check("old < new", yearComp.compare(oldCar, newCar) < 0);
        check("new > old", yearComp.compare(newCar, oldCar) > 0);

        System.out.println("---");

        // ModelComparator
        ModelComparator modelComp = new ModelComparator();
        check("AUDI < TOYOTA", modelComp.compare(audi, toyota) < 0);
        check("TOYOTA > AUDI", modelComp.compare(toyota, audi) > 0);

        System.out.println("---");

        // TotalComparator (каскад)
        TotalComparator totalComp = TotalComparator.INSTANCE;

        // Разные модели → сортировка по модели
        check("total: AUDI < TOYOTA", totalComp.compare(audi, toyota) < 0);

        // Одинаковая модель, разная мощность
        check("total: weak < strong (same model)", totalComp.compare(weak, strong) < 0);

        // Одинаковая модель и мощность, разный год
        Car same1 = new Car.CarBuilder().setModel(CarModel.BMW).setPower(200).setYear(2020).build();
        Car same2 = new Car.CarBuilder().setModel(CarModel.BMW).setPower(200).setYear(2023).build();
        check("total: 2020 < 2023 (same model+power)", totalComp.compare(same1, same2) < 0);

        System.out.println("---");
    }

    private static void testSorts() {
        // ...
    }

    private static void check(String testName, boolean condition) {
        if (condition) {
            System.out.println(testName + " — PASSED");
        } else {
            System.out.println(testName + " — FAILED");
        }
    }
}