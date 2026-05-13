package org.aston.carsorting.comparator;

import java.util.Comparator;
import org.aston.carsorting.model.Car;

public class YearComparator implements Comparator<Car> {
    @Override
    public int compare(Car o1, Car o2) {
        return Integer.compare(o1.getYear(), o2.getYear());
    }
}