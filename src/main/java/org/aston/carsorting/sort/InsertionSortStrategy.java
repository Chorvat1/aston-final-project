package org.aston.carsorting.sort;

import org.aston.carsorting.comparator.TotalComparator;
import org.aston.carsorting.model.Car;
import org.aston.carsorting.model.CarArrayList;

public class InsertionSortStrategy implements SortStrategy {

	@Override
	public void sort(CarArrayList cars) {
		TotalComparator Comparator = new TotalComparator();
//		TotalComparator Comparator = TotalComparator.INSTANCE;
		for (int i = 1; i < cars.size() - 1; i++) {
			Car temp = cars.get(i);
			int j = i;
			while (j > 0 && Comparator.compare(cars.get(j-1),temp) > 0){
				cars.set(j,cars.get(j-1));
				j--;
			}
			cars.set(j,temp);
		}
	}
}
