package org.aston.carsorting.sort;

import org.aston.carsorting.model.Car;
import org.aston.carsorting.model.CarArrayList;

public interface SortStrategy {
	public void sort(CarArrayList cars);
}
