package org.aston.carsorting.comparator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.aston.carsorting.model.Car;
import org.aston.carsorting.util.CarModel;

public class TotalComparator implements Comparator<Car>{

	@Override
	public int compare(Car o1, Car o2){

		Comparator<Car> totalComparator = new ModelComparator()
				.thenComparing(new PowerComparator())
				.thenComparing(new YearComparator());

		return totalComparator.compare(o1, o2);
	}

}
