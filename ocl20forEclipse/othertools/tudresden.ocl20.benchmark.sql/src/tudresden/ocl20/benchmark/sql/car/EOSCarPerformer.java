package tudresden.ocl20.benchmark.sql.car;

import tudresden.ocl20.benchmark.sql.util.EOSPerformer;
import core.IEOS;

/**
 * 
 * @author Bjoern Freitag
 */
public class EOSCarPerformer extends EOSPerformer implements ICarPerformer {

	/**
	 * Create the eos performer for model car
	 */
	public EOSCarPerformer() {

		super();
		createCarState();
	}

	private void createCarState() {

		// 1A.- Create an instance of EOS.
		ieos = new IEOS();

		// 2.- Create a model
		ieos.createClassDiagram();

		// 3.- Insert each element into the model.
		this.ieos.insertClass("Person");
		this.ieos.insertAttribute("Person", "name", "String");
		this.ieos.insertAttribute("Person", "age", "String");
		this.ieos.insertAttribute("Person", "phoneno", "String");
		this.ieos.insertClass("Car");
		this.ieos.insertAttribute("Car", "model", "String");
		this.ieos.insertAttribute("Car", "color", "String");
		this.ieos
				.insertAssociation("Person", "owner", "*", "*", "ownedCars", "Car");

		// 4.- Close the model
		ieos.closeClassDiagram();

		// 5.- Create an scenario
		ieos.createObjectDiagram();
	}

	public void addCar(String carName, String model, String color) {

		this.ieos.insertObject("Car", carName);
		this.ieos.insertValue("Car", "model", carName, model);
		this.ieos.insertValue("Car", "color", carName, color);
	}

	public void addPerson(String personName, int age, int phoneno) {

		this.ieos.insertObject("Person", personName);
		this.ieos.insertValue("Person", "name", personName, personName);
		this.ieos.insertValue("Person", "age", personName, "" + age);
		this.ieos.insertValue("Person", "phoneno", personName, "" + phoneno);
	}

	public void addAssociation(String personName, String carName) {

		this.ieos.insertLink("Person", personName, "owner", "ownedCars", carName,
				"Car");
	}

}
