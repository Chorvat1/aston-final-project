package org.aston.carsorting.sort;

import org.aston.carsorting.model.Car;
//TODO: Включить когда будет сделан блок компараторов
//import org.aston.carsorting.comparator.Comparator;

import java.util.ArrayList;
import java.util.Collections;

//TODO: Удалить
//Заглушка
class Comparator {
	public static int compare(Car car1, Car car2){
		return 0;
	}
}

public class BubbleSortStrategy implements SortStrategy {

	@Override
	public void sort(ArrayList<Car> cars) {
		for (int i = 0; i < cars.size() - 1; i++) {
			boolean hasUnsorted = false;
			for(int j = 0; j < cars.size() - i - 1; j++) {
				if(Comparator.compare(cars.get(j), cars.get(j + 1)) > 0) {
					Collections.swap(cars, j, j + 1);
					hasUnsorted = true;
				}
			}
			if(!hasUnsorted) {
				break;
			}
		}
	}
}
