package tudresden.ocl20.pivot.examples.simple.instance;

import java.util.ArrayList;
import java.util.List;

import tudresden.ocl20.pivot.examples.simple.Person;
import tudresden.ocl20.pivot.examples.simple.SimpleFactory;

/**
 * <p>
 * A class which creates a simple model instance of the simple UML model.
 * </p>
 * 
 * @author Claas Wilke
 */
public class ModelInstanceProviderClass {

	/**
	 * FIXME Claas: Test interpretation of the provided instances when the interpreter has been refactored.
	 * 
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

		person1 = SimpleFactory.eINSTANCE.createPerson();
		person1.setName("Student Work-a-lot");
		person1.setAge(22);
		result.add(person1);

		person2 = SimpleFactory.eINSTANCE.createProfessor();
		person2.setName("Prof. Invalid");
		person2.setAge(-42);
		result.add(person2);

		person3 = SimpleFactory.eINSTANCE.createStudent();
		person3.setName("Person Unspecific");
		person3.setAge(25);
		result.add(person3);

		return result;
	}
}