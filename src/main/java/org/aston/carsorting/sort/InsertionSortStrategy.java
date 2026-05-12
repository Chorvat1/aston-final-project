package org.aston.carsorting.sort;

import org.aston.carsorting.comparator.TotalComparator;
import org.aston.carsorting.model.Car;
import org.aston.carsorting.model.CarList;

public class InsertionSortStrategy implements SortStrategy {

	@Override
	public void sort(CarList cars) {
		for (int i = 1; i < cars.size(); i++) {
			Car temp = cars.get(i);
			int j = i;
			while (j > 0 && TotalComparator.INSTANCE.compare(cars.get(j-1),temp) > 0){
				cars.set(j,cars.get(j-1));
				j--;
			}
			cars.set(j,temp);
		}
	}
}
