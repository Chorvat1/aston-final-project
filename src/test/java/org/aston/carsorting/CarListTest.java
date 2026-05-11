package org.aston.carsorting;

import org.aston.carsorting.model.Car;
import org.aston.carsorting.model.CarArrayList;
import org.aston.carsorting.model.CarList;
import org.aston.carsorting.util.CarModel;


public class CarListTest {


    public static void main(String[] args) throws Exception {
        Before before = new Before();
        After after = new After();
        Assert asserts = new Assert();

        //Test 1  whenElementRemovedByIndexThenSizeMustBeDecreased
        System.out.println("#1 removeAt()");
        CarList test1 = before.setUP();
        asserts.asserTrue(test1.removeAt(2));
        asserts.assertEquals(9, test1.size());
        after.setDown();

        //Test 2  whenElementRemovedThenSizeMustBeDecreased
        System.out.println("#2 remove()");
        CarList test2 = before.setUP();
        Car carTest2 = new Car.CarBuilder().setModel(CarModel.TOYOTA).setYear(2010).setPower(233).build();
        test2.add(carTest2);
        asserts.assertEquals(11, test2.size());
        asserts.asserTrue(test2.remove(carTest2));
        asserts.assertEquals(10, test2.size());
        after.setDown();

        //Test 3 whenNonExistentElementRemovedThenReturnFalse
        System.out.println("#3 remove() throws IndexOutOfBoundsException");
        CarList test3 = before.setUP();
        try {test3.removeAt(15);}
        catch (IndexOutOfBoundsException e) {
            System.out.println("Test Passed");
        }
        asserts.assertEquals(10, test3.size());
        after.setDown();

        //Test 4 methodGetReturnedRightValue
        System.out.println("#4 get()");
        CarList test4 = before.setUP();
        Car carTest4 = test4.get(0);
        asserts.assertEquals(CarModel.VOLVO.ordinal(), carTest4.getModel().ordinal());
        after.setDown();

        //Test 5 methodAddIncreaseArrayLength
        System.out.println("#5 add()");
        CarList test5 = before.setUP();
        for (int i = 0; i < 50; i++) {
            test5.add(new Car.CarBuilder().build());
        }
        asserts.assertEquals(60, test5.size());
        after.setDown();

        //Test 6 methodSetRemoveElementByIndexToNewElement
        System.out.println("#6 set()");
        CarList test6 = before.setUP();
        Car carTest6 = test6.get(0);
        test6.set(0, new Car.CarBuilder().build());
        if (carTest6.equals(test6.get(0))) {
            System.out.println("Test Failed");
        } else  {
            System.out.println("Test Passed");
        }
        after.setDown();

        //Test 7 methodSwapElementByElement
        System.out.println("#7 swap()");
        CarList test7 = before.setUP();
        Car car1Test7 = test7.get(0);
        Car car2Test7 = test7.get(1);
        test7.swap(0, 1);
        asserts.assertEquals(car1Test7.toString(), test7.get(1).toString());
        asserts.assertEquals(car2Test7.toString(), test7.get(0).toString());
        after.setDown();
    }
}

class Before {
    public CarList setUP() throws Exception {
        CarList carList = new CarArrayList();
        for (int i = 0; i < 10; i++) {
            carList.add(new Car.CarBuilder().setModel(CarModel.VOLVO).setPower(200 + 100*i).setYear(2000 + i).build());
        } return carList;
    }
}

class After {
    String string = "_______";
    public void setDown() {
        System.out.println(string);
    }
}

class Assert {
    public void assertEquals(int expected, int actual) {
        if (expected == actual) {
            System.out.println("Test Passed");

        }
        else System.out.println("Test Failed");
    }

    public void assertEquals(String expected, String actual) {
        if (expected.equals(actual)) {
            System.out.println("Test Passed");

        }
        else System.out.println("Test Failed");
    }

    public void asserTrue(boolean expected) {
        if (expected) {
            System.out.println("Test Passed");
        } else System.out.println("Test Failed");
    }
}

