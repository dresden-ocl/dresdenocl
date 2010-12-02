package tudresden.ocl20.pivot.examples.car;

import java.util.ArrayList;
import java.util.List;

public class Car {

	protected String model;

	protected String color;

	protected List<Person> owner;

	public Car(String model, String color) {

		owner = new ArrayList<Person>();
		this.model = model;
		this.color = color;
	}

	public String getModel() {

		return model;
	}

	public void setModel(String model) {

		this.model = model;
	}

	public String getColor() {

		return color;
	}

	public void setColor(String color) {

		this.color = color;
	}

	public List<Person> getOwner() {

		return owner;
	}

	public void setOwner(List<Person> owner) {

		this.owner = owner;
	}

}
