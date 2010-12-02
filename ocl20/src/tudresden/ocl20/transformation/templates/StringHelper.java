package tudresden.ocl20.transformation.templates;

import java.util.Collection;

public class StringHelper {

	public static String getValuesCommaSeperated(Collection<String> stringCollection) {
		String string = "";
		for (String c : stringCollection) {
			string += c + ",";
		}
		string = string.substring(0, string.length() - 1);
		
		return string;
		
	}

}
