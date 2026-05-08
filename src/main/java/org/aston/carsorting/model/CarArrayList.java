package org.aston.carsorting.model;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public class CarArrayList implements CarList {

    private final int intCapacity;
    private Car[] array;
    private int size = 0;

    public CarArrayList() {
        this.intCapacity = 10;
        this.array = new Car[intCapacity];
    }

    public CarArrayList(int intCapacity) {
        this.intCapacity = intCapacity;
        this.array = new Car[intCapacity];
    }

    @Override
    public Car get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public void add(Car car) {
        increaseArray();
        array[size] = car;
        size++;

    }

    @Override
    public boolean remove(Car car) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(car)) {
                return removeAt(i);
            }
        }
        return false;
    }

    @Override
    public boolean removeAt(int index) {
        checkIndex(index);
        System.arraycopy(array, index + 1, array, index, size - index);
        size--;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        array = new Car[intCapacity];
        size = 0;
    }

    @Override
    public Car[] set(int index, Car car) {
        checkIndex(index);
        array[index] = car;
        return array;
    }

    @Override
    public void swap(int index1, int index2) {
        checkIndex(index1);
        checkIndex(index2);
        Car e1 = array[index1];
        Car e2 = array[index2];
        array[index1] = e2;
        array[index2] = e1;
    }

    @Override
    public Stream stream() {
        return Arrays.stream(array).toList().stream();
    }

    @Override
    public Stream parallelStream() {
        return Arrays.stream(array).parallel().toList().parallelStream();
    }

    @Override
    public void printArray() {
        Arrays.stream(array).filter(Objects::nonNull).forEach(System.out::println);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void increaseArray() {
        if (size >= array.length) {
            array = Arrays.copyOf(array, array.length * 2);
        }
    }

}
