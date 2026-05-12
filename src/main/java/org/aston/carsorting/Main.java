package org.aston.carsorting;

import org.aston.carsorting.comparator.TotalComparator;
import org.aston.carsorting.input.FileInputStrategy;
import org.aston.carsorting.input.InputStrategy;
import org.aston.carsorting.input.ManualInputStrategy;
import org.aston.carsorting.input.RandomInputStrategy;
import org.aston.carsorting.model.CarList;
import org.aston.carsorting.sort.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DEFAULT_FILE_PATH = "src/main/resources/cars.txt";
    private static CarList currentCars;

    public static void main(String[] args) {

        while (true) {
            printMenu();
            int command = getIntInput("Выберите действие: ");

            if (command == 0) {
                System.out.println("Выход...");
                break;
            }

            processCommand(command);
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("--- ГЛАВНОЕ МЕНЮ ---");
        System.out.println("1. Заполнить данные вручную");
        System.out.println("2. Сгенерировать данные случайно");
        System.out.println("3. Загрузить автомобили из файла");
        System.out.println("4. Показать текущий список автомобилей");
        System.out.println("5. Отсортировать текущий список");
        System.out.println("0. Выход");
    }

    private static void processCommand(int command) {
        InputStrategy inputStrategy = null;

        switch (command) {
            case 1:
                inputStrategy = new ManualInputStrategy();
                currentCars = inputStrategy.getData();
                if (currentCars == null || currentCars.size() == 0) {
                    System.out.println("Не удалось загрузить данные.");
                    return;
                }
                System.out.println("Данные успешно загружены!");
                break;
            case 2:
                int randomSize = getPositiveIntInput("Введите количество автомобилей: ");
                inputStrategy = new RandomInputStrategy(randomSize);
                currentCars = inputStrategy.getData();
                if (currentCars == null || currentCars.size() == 0) {
                    System.out.println("Не удалось загрузить данные.");
                    return;
                }
                System.out.println("Данные успешно загружены!");
                break;
            case 3:
                inputStrategy = new FileInputStrategy(DEFAULT_FILE_PATH);
                currentCars = inputStrategy.getData();
                if (currentCars == null || currentCars.size() == 0) {
                    System.out.println("Не удалось загрузить данные.");
                    return;
                }
                System.out.println("Данные успешно загружены!");
                break;
            case 4:
                if (currentCars == null || currentCars.size() == 0) {
                    System.out.println("Список автомобилей пуст.");
                    return;
                }
                System.out.println("--- ТЕКУЩИЙ СПИСОК АВТОМОБИЛЕЙ ---");
                currentCars.printArray();
                break;
            case 5:
                if (currentCars == null || currentCars.size() == 0) {
                    System.out.println("Список автомобилей пуст.");
                    return;
                }
                chooseAndApplySorting(currentCars);
                break;
            default:
                System.out.println("Неизвестная команда. Операция прервана.");
                return;
        }
    }

    private static void chooseAndApplySorting(CarList carList) {
        System.out.println("--- ВЫБОР АЛГОРИТМА СОРТИРОВКИ ---");
        System.out.println("1. Сортировка пузырьком");
        System.out.println("2. Сортировка вставками");
        System.out.println("3. Сортировка выбором");

        int sortCommand = getIntInput("Выберите алгоритм: ");

        SortStrategy strategy = null;

        switch (sortCommand) {
            case 1:
                strategy = new BubbleSortStrategy();
                break;
            case 2:
                strategy = new InsertionSortStrategy();
                break;
            case 3:
                strategy = new SelectionSortStrategy();
                break;
            default:
                System.out.println("Неверный выбор.");
                return;
        }

        Sorter sorter = new Sorter(strategy);

        System.out.println("Сортируем...");
        //Use:
        sorter.sort(carList, false); //true если нужна сортировка по доп_заданию 1
        //sorter.sort(carList, new TotalComparator());

        System.out.println("--- ОТСОРТИРОВАННЫЙ СПИСОК АВТОМОБИЛЕЙ ---");
        carList.printArray();
        printToFile(carList);
    }

    private static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка! Введите целое число");
            }
        }
    }

    private static int getPositiveIntInput(String prompt) {
        while (true) {
            int value = getIntInput(prompt);
            if (value > 0) {
                return value;
            }
            System.out.println("Ошибка! Введите число больше 0.");
        }
    }

    private static boolean printToFile(CarList carList) {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter("src/main/resources/output.txt"))) {
            carList.stream().forEach(printWriter::println);
        } catch (IOException e){
            System.out.println("Ошибка при записи в файл " + e.getMessage());
            return false;
        }
        return true;
    }
}