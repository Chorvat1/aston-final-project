package org.aston.carsorting.sort;

import org.aston.carsorting.model.CarArrayList;


public class Sorter {
	private SortStrategy sortStrategy;

	public Sorter(SortStrategy strategy) {
		this.sortStrategy = strategy;
	}

	public void sort(CarArrayList cars, boolean dop1) {
		if (!dop1) {
			sortStrategy.sort(cars);
		} else {
			CarArrayList evenCars = new CarArrayList();
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
