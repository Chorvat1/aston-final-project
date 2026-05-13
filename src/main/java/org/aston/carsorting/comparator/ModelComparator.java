package org.aston.carsorting.comparator;

import java.util.Comparator;

import org.aston.carsorting.model.Car;

public class ModelComparator implements Comparator<Car> {

	@Override
	public int compare(Car o1, Car o2) {
		return o1.getModel().compareTo((o2.getModel()));
	}
}
