package org.aston.carsorting.sort;

import org.aston.carsorting.model.Car;

import java.util.ArrayList;

public class Sorter {
	private SortStrategy sortStrategy;

	public Sorter(SortStrategy strategy) {
		this.sortStrategy = strategy;
	}

	public void sort(ArrayList<Car> cars) {
		sortStrategy.sort(cars);
	}
}
