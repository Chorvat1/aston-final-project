package org.aston.carsorting.comparator;

import java.util.Comparator;

import org.aston.carsorting.model.Car;

public class TotalComparator implements Comparator<Car> {

    public static final TotalComparator INSTANCE = new TotalComparator();

    private static final Comparator<Car> DELEGATE = Comparator
            .comparing((Car::getModel))
            .thenComparing(Car::getPower)
            .thenComparing(Car::getYear);

    private TotalComparator() {
    }

    @Override
    public int compare(Car o1, Car o2) {
        return DELEGATE.compare(o1, o2);
    }

}
