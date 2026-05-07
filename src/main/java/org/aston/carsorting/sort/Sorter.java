package org.aston.carsorting.sort;

import org.aston.carsorting.model.Car;

import java.util.ArrayList;

public class Sorter {
	private SortStrategy sortStrategy;

	public Sorter(SortStrategy strategy) {
		this.sortStrategy = strategy;
	}

	public void sort(ArrayList<Car> cars, boolean dop1) {
		if (!dop1) {
			sortStrategy.sort(cars);
		} else {
			ArrayList<Car> evenCars = new ArrayList<>();
			for (Car car : cars) {
				if (car.getPower() % 2 == 0) {
					evenCars.add(car);
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
