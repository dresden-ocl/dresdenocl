package tudresden.ocl20.pivot.tracer.test.package01;

import java.util.ArrayList;
import java.util.List;

public class Instance01 {

	public static List<Object> getModelObjects() {

		List<Object> result;
		result = new ArrayList<Object>(1);

		Class01 class1;
		class1 = new Class01();

		class1.intProperty = 0;

		result.add(class1);
		return result;
	}

}
