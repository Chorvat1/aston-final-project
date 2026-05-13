package org.aston.carsorting.input;

import org.aston.carsorting.Main;
import org.aston.carsorting.model.CarList;
import org.aston.carsorting.model.CarArrayList;
import org.aston.carsorting.model.Car;
import org.aston.carsorting.util.CarParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

public class FileInputStrategy implements InputStrategy {
    private final String filePath;
    private final CarParser parser = new CarParser();

    public FileInputStrategy(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public CarList getData() {
        CarList collection = new CarArrayList();
        AtomicBoolean hasError = new AtomicBoolean(false);

        if (Main.dop3){
            try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
                lines.map(String::trim)
                        .filter(line -> !line.isEmpty())
                        .takeWhile(line -> !hasError.get())
                        .forEach(line -> {
                            try {
                                Car car = parser.fromString(line);
                                collection.add(car);
                            } catch (IllegalArgumentException e) {
                                System.out.println("Ошибка в файле.");
                                System.out.println(e.getMessage());
                                System.out.println("Загрузка файла прекращена.");
                                hasError.set(true);
                            }
                        });
            } catch(IOException e) {
                throw new RuntimeException("Ошибка чтения файла: " + filePath, e);
            }
            if (hasError.get()) {
                return new CarArrayList();
            }
        } else {
            //Ориг
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
        }

        System.out.println("Загружено автомобилей: " + collection.size());
        return collection;
    }
}