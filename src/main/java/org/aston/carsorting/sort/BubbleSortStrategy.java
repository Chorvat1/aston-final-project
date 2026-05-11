package org.aston.carsorting.sort;

import org.aston.carsorting.comparator.TotalComparator;
import org.aston.carsorting.model.Car;

import java.util.ArrayList;
import java.util.Collections;

public class BubbleSortStrategy implements SortStrategy {

	@Override
	public void sort(ArrayList<Car> cars) {
		TotalComparator Comparator = new TotalComparator();
		for (int i = 0; i < cars.size() - 1; i++) {
			boolean hasUnsorted = false;
			for(int j = 0; j < cars.size() - i - 1; j++) {
				if(Comparator.compare(cars.get(j), cars.get(j + 1)) > 0) {
					Collections.swap(cars, j, j + 1);
					hasUnsorted = true;
				}
			}
			if(!hasUnsorted) {
				break;
			}
		}
	}
}
