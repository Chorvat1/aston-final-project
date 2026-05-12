package org.aston.carsorting.sort;

import org.aston.carsorting.model.CarArrayList;
import org.aston.carsorting.model.CarList;


public class Sorter {
	private SortStrategy sortStrategy;

	public Sorter(SortStrategy strategy) {
		this.sortStrategy = strategy;
	}

	public void setNewStrategy (SortStrategy strategy) {
		this.sortStrategy = strategy;
	}

	public void sort(CarList cars, boolean dop1_isActive) {
		if (!dop1_isActive) {
			sortStrategy.sort(cars);
		} else {
			CarList evenCars = new CarArrayList();
			for (int i = 0; i < cars.size(); i++) {
				if (cars.get(i).getPower() % 2 == 0) {
					evenCars.add(cars.get(i));
				}
			}

			sortStrategy.sort(evenCars);

			int j = 0;
			for (int i = 0; i < cars.size(); i++) {
				if (cars.get(i).getPower() % 2 == 0) {
					cars.set(i, evenCars.get(j++));
				}
			}
		}
	}
}
