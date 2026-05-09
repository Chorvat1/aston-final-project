package org.aston.carsorting.input;

import org.aston.carsorting.model.CarList;
import org.aston.carsorting.model.CarArrayList;
import org.aston.carsorting.model.Car;
import org.aston.carsorting.util.CarParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileInputStrategy implements InputStrategy {
    private final String filePath;
    private final CarParser parser = new CarParser();

    public FileInputStrategy(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public CarList getData() {
        CarList collection = new CarArrayList();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();

                if (line.isEmpty()) continue;
                try {
                    Car car = parser.fromString(line);
                    collection.add(car);
                } catch (IllegalArgumentException e) {
                    System.out.println("Ошибка в файле: строка " + lineNumber + "невалидна");
                    System.out.println(e.getMessage());
                    System.out.println("Загрузка файла прекращена.");
                    return new CarArrayList();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения файла: " + filePath, e);
        }

        System.out.println("Загружено автомобилей: " + collection.size());
        return collection;
    }
}