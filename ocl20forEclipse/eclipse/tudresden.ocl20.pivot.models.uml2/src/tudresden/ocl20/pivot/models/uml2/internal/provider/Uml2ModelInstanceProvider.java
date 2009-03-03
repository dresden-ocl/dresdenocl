/*
Copyright (C) 2008-2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the UML2 Meta Model of Dresden OCL2 for Eclipse.

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
package tudresden.ocl20.pivot.models.uml2.internal.provider;

import java.net.URL;

import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelInstance;
import tudresden.ocl20.pivot.modelbus.IModelInstanceProvider;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.base.AbstractModelInstanceProvider;
import tudresden.ocl20.pivot.models.uml2.internal.modelinstance.FileClassLoader;
import tudresden.ocl20.pivot.models.uml2.internal.modelinstance.Uml2ModelInstance;

/**
 * <p>
 * Provides methods to load or get {@link IModelInstance}s of the UML2 meta
 * model.
 * 
 * @author Claas Wilke
 */
public class Uml2ModelInstanceProvider extends AbstractModelInstanceProvider
		implements IModelInstanceProvider {

	/** The UML2 {@link IModel}. */
	private IModel myModel;

	/**
	 * <p>
	 * Creates a new {@link Uml2ModelInstanceProvider}.
	 * </p>
	 * 
	 * @param model
	 *            The UML2 {@link IModel} of the {@link Uml2ModelInstance} which
	 *            shall be provided.
	 */
	public Uml2ModelInstanceProvider(IModel model) {

		this.myModel = model;
	}

	/**
	 * <p>
	 * Loads a model instance to a given URL.
	 * </p>
	 * 
	 * @param instanceURL
	 *            the {@link URL} of the model instance which shall be loaded.
	 */
	public IModelInstance getModelInstance(URL instanceURL)
			throws ModelAccessException {

		IModelInstance result;

		FileClassLoader classLoader;
		Class<?> instanceProviderClass;

		String instancePath;
		String packagePath;
		String className;

		int index;

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
				classLoader = new FileClassLoader(packagePath.substring(1)
						+ "/");
				instanceProviderClass = classLoader.findClass(className);
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
			instanceProviderClass.getDeclaredMethod("getModelObjects", new Class[0]);
		}

		catch (Exception e) {
			String msg;

			msg = "Class " + instanceProviderClass;
			msg += " doesn't provide the needed method getModelObjects().";

			throw new ModelAccessException(msg);
		}

		result = new Uml2ModelInstance(instanceProviderClass, this.myModel);

		return result;
	}
}