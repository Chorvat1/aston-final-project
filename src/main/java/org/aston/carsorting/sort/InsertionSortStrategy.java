package org.aston.carsorting.sort;

import org.aston.carsorting.model.Car;

import java.util.ArrayList;

public class InsertionSortStrategy implements SortStrategy {

	@Override
	public void sort(ArrayList<Car> cars) {
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
