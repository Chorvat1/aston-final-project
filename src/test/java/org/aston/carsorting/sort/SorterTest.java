package org.aston.carsorting.sort;

import org.aston.carsorting.sort.*;
import org.aston.carsorting.comparator.TotalComparator;
import org.aston.carsorting.model.Car;
import org.aston.carsorting.model.CarArrayList;
import org.aston.carsorting.model.CarList;
import org.aston.carsorting.util.CarModel;

import java.util.ArrayList;
import java.util.Comparator;

public class SorterTest {
	public static void main(String[] args) {
		Before before = new Before();
		After after = new After();
		Assert asserter = new Assert();

		Comparator<Car> cascadingComparator = Comparator
				.comparing(Car::getModel)
				.thenComparingDouble(Car::getPower)
				.thenComparingInt(Car::getYear);

		//Things to test:
		//1. Zero Element Array --- Guaranteed by input parser
		//2. One Element Array
		//3. Sort Ascending
		//4. Sort Descending --- No option for that for user
		//5. Sort Repeated Values (bruh, ok)
		//6. Sort Large Array (size = 10000)

		//Test 1 One Element Sort
		System.out.println("#1 One Element Sort");
		CarArrayList test1 = before.setUp(1, true);
		ArrayList<Car> correctTest1 = before.setUpArrayListFrom(test1);
		correctTest1.sort(cascadingComparator);
		asserter.compare(correctTest1, test1, "#1 One Element Sort");
		after.setDown();

		//Test 2 Sort Ascending
		System.out.println("#2 Sort Ascending");
		CarArrayList test2 = before.setUp(20, false);
		ArrayList<Car> correctTest2 = before.setUpArrayListFrom(test2);
		correctTest2.sort(cascadingComparator);
		asserter.compare(correctTest2, test2, "#2 Sort Ascending");
		after.setDown();

		//Test 3 Sort Repeated Values
		System.out.println("#3 Sort Repeated Values");
		CarArrayList test3 = before.setUp(20, true);
		ArrayList<Car> correctTest3 = before.setUpArrayListFrom(test3);
		correctTest3.sort(cascadingComparator);
		asserter.compare(correctTest3, test3, "#3 Sort Repeated Values");
		after.setDown();

		//Test 4 Sort Large Array
		System.out.println("#4 Sort Large Array");
		CarArrayList test4 = before.setUp(100, false);
		ArrayList<Car> correctTest4 = before.setUpArrayListFrom(test4);
		correctTest4.sort(cascadingComparator);
		asserter.compare(correctTest4, test4, "#4 Sort Large Array");
		after.setDown();
	}
}

class Before {
	public CarArrayList setUp(int amount, boolean hasRepeatedValues) {
		CarArrayList carList = new CarArrayList();
		CarModel carModel = CarModel.VOLVO;
		int carPower = 2000;
		int carYear = 2000;
		for (int i = 0; i < amount; i++) {
			if (hasRepeatedValues) {
				carList.add(new Car.CarBuilder().setModel(carModel).setPower(carPower).setYear(carYear).build());
			} else {
				if (i % 5 == 0){
					carPower -= 1;
				}
				if (i % 3 == 0){
					carYear -= 2;
				}
				if (i % 7 == 0){
					carModel = CarModel.HYUNDAI;
				} else {
					carModel = CarModel.VOLVO;
				}
				carList.add(new Car.CarBuilder().setModel(carModel).setPower(carPower).setYear(carYear).build());
			}

		}
		return carList;
	}
	public ArrayList<Car> setUpArrayListFrom(CarArrayList fromList) {
		ArrayList<Car> list = new ArrayList<>();
		for (int i = 0; i < fromList.size(); i++) {
			list.add(fromList.get(i));
		}
		return list;
	}
}

class After {
	public void setDown(){
		System.out.println("_________________________________________");
	}
}

class Assert {
	public boolean compare(ArrayList<Car> list1, CarList list2, String test_name) {
		boolean result = true;
		Sorter sorter = new Sorter(new BubbleSortStrategy());
		CarList bubbleSorted = new CarArrayList();
		for (int i = 0; i < list2.size(); i++) {
			bubbleSorted.add(list2.get(i));
		}
		sorter.sort(bubbleSorted, false);
		for (int i = 0; i < list1.size(); i++) {
			if (TotalComparator.INSTANCE.compare(list1.get(i),bubbleSorted.get(i))!= 0){
				System.out.println("X " + test_name + " test has Failed! (At BubbleSort sorting)");
				result = false;
				break;
			}
		}

		sorter.setNewStrategy(new InsertionSortStrategy());
		CarList insertionSorted = new CarArrayList();
		for (int i = 0; i < list2.size(); i++) {
			insertionSorted.add(list2.get(i));
		}
		sorter.sort(insertionSorted, false);
		for (int i = 0; i < list1.size(); i++) {
			if (TotalComparator.INSTANCE.compare(list1.get(i),insertionSorted.get(i))!= 0){
				System.out.println("X " + test_name + " test has Failed! (At InsertionSort sorting)");
				result = false;
				break;
			}
		}

		sorter.setNewStrategy(new SelectionSortStrategy());
		CarList selectionSorted = new CarArrayList();
		for (int i = 0; i < list2.size(); i++) {
			selectionSorted.add(list2.get(i));
		}
		sorter.sort(selectionSorted, false);
		for (int i = 0; i < list1.size(); i++) {
			if (TotalComparator.INSTANCE.compare(list1.get(i),selectionSorted.get(i))!= 0){
				System.out.println("X " + test_name + " test has Failed! (At SelectionSort sorting)");
				result = false;
				break;
			}
		}

		if (result) {
			System.out.println("✓ " + test_name + " test has Passed!");
		}
		return result;
	}
}

