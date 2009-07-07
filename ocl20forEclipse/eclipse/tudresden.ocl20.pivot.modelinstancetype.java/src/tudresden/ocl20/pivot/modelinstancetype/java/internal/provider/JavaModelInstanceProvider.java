/*
Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the Java Model Instance Plug-in of Dresden OCL2 for Eclipse.

Dresden OCL2 for Eclipse is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL2 for Eclipse is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>.
 */
package tudresden.ocl20.pivot.modelinstancetype.java.internal.provider;

import java.net.URL;

import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelInstance;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.base.AbstractModelInstanceProvider;
import tudresden.ocl20.pivot.modelinstancetype.java.internal.modelinstance.FileClassLoader;
import tudresden.ocl20.pivot.modelinstancetype.java.internal.modelinstance.JavaModelInstance;

/**
 * <p>
 * Provides methods to load or get {@link JavaModelInstance}s of a model.
 * 
 * @author Claas Wilke
 */
public class JavaModelInstanceProvider extends AbstractModelInstanceProvider {

	/** The model of the model instance which shall be loaded {@link IModel}. */
	private IModel myModel;

	/**
	 * <p>
	 * Creates a new {@link JavaModelInstanceProvider}.
	 * </p>
	 */
	public JavaModelInstanceProvider() {
	}

	/**
	 * <p>
	 * Loads a model instance to a given URL.
	 * </p>
	 * 
	 * @param instanceURL
	 *            the {@link URL} of the model instance which shall be loaded.
	 * @param model
	 *            The UML2 {@link IModel} of the {@link JavaModelInstance} which
	 *            shall be loaded.
	 */
	public IModelInstance getModelInstance(URL instanceURL, IModel model)
			throws ModelAccessException {

		IModelInstance result;

		FileClassLoader classLoader;
		Class<?> instanceProviderClass;

		String instancePath;
		String packagePath;
		String className;

		int index;

		this.myModel = model;

		/* If no class has been found, throw an exception. */
		if (!instanceURL.getFile().endsWith(".class")) {
			String msg;

			msg = "The given class is no class file. ";
			msg += "Only files with the suffix '.class' are allowed.";

			throw new ModelAccessException(msg);
		}
		// no else.

		result = null;

		instancePath = instanceURL.getFile();
		instanceProviderClass = null;

		/* Split the path into class name and directory path. */
		index = instancePath.lastIndexOf('/');
		packagePath = instancePath;

		/*
		 * Try to load the class directory for directory, decoding the canonical
		 * class name.
		 */
		while (instanceProviderClass == null && index > 0) {

			/* Try to load the class. */
			try {
				packagePath = instancePath.substring(0, index);

				className = instancePath.substring(index + 1);
				className = className.replaceAll("/", ".");

				/* Remove the end '.class' from the class name. */
				className = className.substring(0, className.length() - 6);

				/* Try to load the class. */
				// FIXME (Michael): removed .substring(1); with *nix systems the
				// FileClassLoader needs the root
				classLoader = new FileClassLoader(packagePath + "/");
				instanceProviderClass = classLoader.findClass(className);
				;
			}

			catch (ClassNotFoundException e) {
				/* Ignored because the canonical name may not be fully decoded. */
			}

			catch (NoClassDefFoundError e) {
				/* Ignored because the canonical name may not be fully decoded. */
			}

			/* Jump to the next upper directory. */
			finally {
				index = packagePath.lastIndexOf('/');
			}
		}
		// end while.

		/* If no class has been found, throw an exception. */
		if (instanceProviderClass == null) {
			String msg;

			msg = "ModelProviderClass could not be found.";
			throw new ModelAccessException(msg);
		}
		// no else.

		/* Try to execute method which provides all model objects. */
		try {
			instanceProviderClass.getDeclaredMethod("getModelObjects",
					new Class[0]);
		}

		catch (Exception e) {
			String msg;

			msg = "Class " + instanceProviderClass;
			msg += " doesn't provide the needed method getModelObjects().";

			throw new ModelAccessException(msg);
		}

		result = new JavaModelInstance(instanceProviderClass, this.myModel);

		return result;
	}
}