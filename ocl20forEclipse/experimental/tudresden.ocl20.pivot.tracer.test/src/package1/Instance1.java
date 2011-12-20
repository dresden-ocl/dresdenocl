package package1;

import java.util.ArrayList;
import java.util.List;

public class Instance1 {

	public static List<Object> getModelObjects() {

		List<Object> result;
		result = new ArrayList<Object>(1);

		Class1 class1;
		class1 = new Class1();

		class1.intProperty = 0;

		result.add(class1);
		return result;
	}

}
