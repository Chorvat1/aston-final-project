package org.aston.carsorting.sort;

import org.aston.carsorting.model.Car;

import java.util.ArrayList;

public interface SortStrategy {
	public void sort(ArrayList<Car> cars);
}
