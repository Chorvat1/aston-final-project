package org.aston.carsorting.sort;

import org.aston.carsorting.comparator.TotalComparator;
import org.aston.carsorting.model.CarArrayList;

public class SelectionSortStrategy implements SortStrategy {

	@Override
	public void sort(CarArrayList cars) {
		TotalComparator Comparator = new TotalComparator();
		for (int i = 0; i < cars.size() - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < cars.size(); j++) {
				if (Comparator.compare(cars.get(minIndex), cars.get(j)) > 0) {
					minIndex = j;
				}
			}
			if (i != minIndex) {
				cars.swap(i, minIndex);
			}
		}
	}
}
