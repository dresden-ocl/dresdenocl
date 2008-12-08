package tudresden.ocl20.pivot.examples.simple;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * A class which creates a simple model instance of the simple UML model.
 * </p>
 * 
 * @author Claas Wilke
 */
public class ModelProviderClass {

	/**
	 * @return A simple model instance of the simple UML model.
	 */
	public static List<Object> getModelObjects() {
		List<Object> result;

		Person person1;
		Person person2;

		result = new ArrayList<Object>();

		person1 = new Student();
		person1.setName("Student Work-a-lot");
		person1.setAge(22);
		result.add(person1);

		person2 = new Professor();
		person2.setName("Prof. Invalid");
		person2.setAge(-42);
		result.add(person2);

		return result;
	}
}
