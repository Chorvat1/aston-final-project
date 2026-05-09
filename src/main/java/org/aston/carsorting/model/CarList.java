package org.aston.carsorting.model;

import java.util.stream.Stream;

//to create array use CarList carList = new CarArrayList

public interface CarList {
    Car get(int index);
    void add(Car car);
    boolean remove(Car car);
    boolean removeAt (int index);
    int size();
    void clear();
    void set(int index, Car car);
    void swap(int index1, int index2);
    Stream<Car> stream();
    Stream<Car> parallelStream();
    void printArray();
}
