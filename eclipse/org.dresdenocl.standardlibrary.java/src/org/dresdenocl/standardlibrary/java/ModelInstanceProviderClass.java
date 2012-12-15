package org.dresdenocl.standardlibrary.java;

import java.util.ArrayList;
import java.util.List;

import org.dresdenocl.modelinstance.IModelInstance;
import org.dresdenocl.standardlibrary.java.factory.JavaStandardLibraryFactory;

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
		result = new ArrayList<Object>();

		result.add(JavaStandardLibraryFactory.INSTANCE.createOclString("Test"));

		return result;
	}
}