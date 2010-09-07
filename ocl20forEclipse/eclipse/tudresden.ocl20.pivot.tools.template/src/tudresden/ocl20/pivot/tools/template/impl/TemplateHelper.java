package tudresden.ocl20.pivot.tools.template.impl;

import java.util.Collection;

/**
 * A helper class for using the Templates
 * 
 * @author Bjoern Freitag
 * 
 */
public class TemplateHelper {

	/**
	 * Creates from a collection of strings a comma separated string
	 * 
	 * @param stringCollection
	 *          a collection of strings
	 * @return one string with comma separated strings from the collection.
	 */
	public static String getValuesCommaSeparated(
			Collection<String> stringCollection) {

		String string = "";
		for (String c : stringCollection) {
			string += c + ",";
		}
		if (string.length() > 0) {
			string = string.substring(0, string.length() - 1);
		}

		return string;

	}

}
