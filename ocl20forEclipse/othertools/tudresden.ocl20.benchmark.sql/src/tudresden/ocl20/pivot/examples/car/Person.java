package tudresden.ocl20.pivot.examples.car;

import java.util.ArrayList;
import java.util.List;

public class Person {

	protected String name;

	protected int age;

	protected int phoneno;

	protected List<Car> ownedCars;

	public Person(String name, int age, int phoneno) {

		ownedCars = new ArrayList<Car>();
		this.name = name;
		this.age = age;
		this.phoneno = phoneno;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public int getAge() {

		return age;
	}

	public void setAge(int age) {

		this.age = age;
	}

	public int getPhoneno() {

		return phoneno;
	}

	public void setPhoneno(int phoneno) {

		this.phoneno = phoneno;
	}

	public List<Car> getOwnedCars() {

		return ownedCars;
	}

	public void setOwnedCars(List<Car> ownedCars) {

		this.ownedCars = ownedCars;
	}

}
