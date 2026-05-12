package org.aston.carsorting.sort;

import org.aston.carsorting.comparator.TotalComparator;
import org.aston.carsorting.model.CarArrayList;

public class BubbleSortStrategy implements SortStrategy {

    @Override
    public void sort(CarArrayList cars) {
        TotalComparator comparator = new TotalComparator();

        for (int i = 0; i < cars.size() - 1; i++) {
            boolean hasUnsorted = false;
            for (int j = 0; j < cars.size() - i - 1; j++) {
                if (comparator.compare(cars.get(j), cars.get(j + 1)) > 0) {
                    cars.swap(j, j + 1);
                    hasUnsorted = true;
                }
            }
            if (!hasUnsorted) {
                break;
            }
        }
    }
}