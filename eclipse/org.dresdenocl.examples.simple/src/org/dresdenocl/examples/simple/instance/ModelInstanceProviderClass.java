package tudresden.ocl20.pivot.examples.simple.instance;

import java.util.ArrayList;
import java.util.List;

import tudresden.ocl20.pivot.examples.simple.Person;
import tudresden.ocl20.pivot.examples.simple.Professor;
import tudresden.ocl20.pivot.examples.simple.Student;

/**
 * <p>
 * A class which creates a simple model instance of the simple UML model.
 * </p>
 * 
 * @author Claas Wilke
 */
public class ModelInstanceProviderClass {

	/**
	 * <p>
	 * Returns a {@link List} of {@link Object}s that are part of the
	 * {@link IModelInstance}.
	 * </p>
	 * 
	 * @return A {@link List} of {@link Object}s that are part of the
	 *         {@link IModelInstance}.
	 */
	public static List<Object> getModelObjects() {

		List<Object> result;

		Person person1;
		Person person2;
		Person person3;

		result = new ArrayList<Object>();

		person1 = new Person();
		person1.setName("Person Unspecific");
		person1.setAge(25);
		result.add(person1);

		person2 = new Professor();
		person2.setName("Prof. Invalid");
		person2.setAge(-42);
		result.add(person2);

		person3 = new Student();
		person3.setName("Student Work-a-lot");
		person3.setAge(23);
		result.add(person3);
		
		return result;
	}
}