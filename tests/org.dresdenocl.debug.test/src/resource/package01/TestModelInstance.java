package resource.package01;

import java.util.ArrayList;
import java.util.List;

public class TestModelInstance {

	public static List<Object> getModelObjects() {

		List<Object> result = new ArrayList<Object>();
		
		TestClass testClass = new TestClass();
		testClass.parent = new TestClass();
		
		result.add(testClass);
		return result;
	}
}
