package tudresden.ocl20.benchmark.sql.car;

import tudresden.ocl20.benchmark.sql.util.IPerformer;

public interface ICarPerformer extends IPerformer {

	/**
	 * add a new car to database
	 * 
	 * @param carName
	 *          the name of the car
	 * @param model
	 *          the model of the car
	 * @param color
	 *          the color of the car
	 */
	public void addCar(String carName, String model, String color);

	/**
	 * add a new person to database
	 * 
	 * @param personName
	 *          the name of the person
	 * @param age
	 *          the age of the person
	 * @param phoneno
	 *          phone number of the person
	 */
	public void addPerson(String personName, int age, int phoneno);

	/**
	 * add the association between a car a person
	 * 
	 * @param personName
	 *          the name of person
	 * @param carName
	 *          the name of car
	 */
	public void addAssociation(String personName, String carName);

}
