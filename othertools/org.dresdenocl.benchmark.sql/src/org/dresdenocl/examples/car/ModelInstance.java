package org.dresdenocl.examples.car;

import java.util.ArrayList;
import java.util.List;

public class ModelInstance {

	private static int NUM_CARS_PER_PERSONS = 10;

	private static int NUM_PERSONS = 100;

	public static List<Object> getModelObjects() {

		List<Object> result = new ArrayList<Object>();
		for (int i = 0; i < NUM_PERSONS; i++) {
			String personS = "" + (i + 1);
			int age = (i % 100);
			Person person = new Person(personS, age, i);
			result.add(person);

			for (int k = 0; k < NUM_CARS_PER_PERSONS; k++) {
				String carS = "" + (k + 1 + i * NUM_CARS_PER_PERSONS);
				Car car = new Car(carS, carS);
				result.add(car);
				car.getOwner().add(person);
				person.getOwnedCars().add(car);
			}
		}
		return result;
	}

}
