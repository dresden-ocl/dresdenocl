package resource.package01;

import java.util.ArrayList;
import java.util.List;

public class TestModelInstance {

	public static List<Object> getModelObjects() {

		List<Object> result = new ArrayList<Object>();
		result.add(new TestClass());
		return result;
	}
}
