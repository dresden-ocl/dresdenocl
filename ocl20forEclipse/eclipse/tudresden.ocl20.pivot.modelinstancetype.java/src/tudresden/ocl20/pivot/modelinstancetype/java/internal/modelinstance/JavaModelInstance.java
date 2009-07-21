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
package tudresden.ocl20.pivot.modelinstancetype.java.internal.modelinstance;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.osgi.util.NLS;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclEnumLiteral;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclEnumType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.StandardlibraryAdapterFactory;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelInstance;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.base.AbstractModelInstance;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelinstancetype.java.JavaModelInstanceTypePlugin;
import tudresden.ocl20.pivot.modelinstancetype.java.internal.msg.JavaModelInstanceTypeMessages;
import tudresden.ocl20.pivot.pivotmodel.Enumeration;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.standardlibrary.java.internal.factory.JavaStandardlibraryAdapterFactory;

/**
 * <p>
 * Represents instances of {@link IModel}s represented by Java classes.
 * </p>
 * 
 * @author Claas Wilke
 */
public class JavaModelInstance extends AbstractModelInstance implements
		IModelInstance {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			JavaModelInstanceTypePlugin.getLogger(JavaModelInstance.class);

	/**
	 * The default {@link AdapterFactory} used to adapt the {@link IModelInstance}
	 * to the standard library.
	 */
	protected static StandardlibraryAdapterFactory DEFAULTSLAF =
			JavaStandardlibraryAdapterFactory.getInstance();

	/**
	 * <p>
	 * Creates and initializes a new {@link JavaModelInstance}.
	 * </p>
	 * 
	 * @param providerClass
	 *          The provider class used to get all type instances of this model
	 *          instance.
	 * @param model
	 *          The {@link IModel} of this {@link IModelInstance}.s
	 * @throws ModelAccessException
	 */
	@SuppressWarnings("unchecked")
	public JavaModelInstance(Class<?> providerClass, IModel model)
			throws ModelAccessException {

		/* Initialize the instance. */
		this.myModel = model;
		this.myRootNamespace = model.getRootNamespace();
		this.myInstanceName = providerClass.getCanonicalName();
		this.myClassLoader = providerClass.getClassLoader();

		this.myModelInstanceObjectFactory =
				new JavaModelInstanceObjectFactory(this.myModel);

		/* Try to load the model instance objects. */
		try {
			Method providerMethod;
			List<Object> providedObjects;

			/* Get the provider method and its model objects. */
			providerMethod =
					providerClass.getDeclaredMethod("getModelObjects", new Class[0]);
			providedObjects =
					(List<Object>) providerMethod.invoke(null, new Object[0]);

			/* Adapt all provided objects. */
			this.addObjects(providedObjects);

			/* Initialize the caching maps for the operations getObjectsOfType etc. */
			this.initializeCache();
		}

		catch (NoSuchMethodException e) {
			String msg;

			msg =
					JavaModelInstanceTypeMessages.JavaModelInstance_ProviderMethodNotFound;
			msg = NLS.bind(msg, providerClass);

			throw new ModelAccessException(msg, e);
		}

		catch (IllegalArgumentException e) {
			String msg;

			msg =
					JavaModelInstanceTypeMessages.JavaModelInstance_ProviderMethodInvocationError;
			msg = NLS.bind(msg, providerClass);

			throw new ModelAccessException(msg, e);
		}

		catch (IllegalAccessException e) {
			String msg;

			msg =
					JavaModelInstanceTypeMessages.JavaModelInstance_ProviderMethodInvocationError;
			msg = NLS.bind(msg, providerClass);

			throw new ModelAccessException(msg, e);
		}

		catch (InvocationTargetException e) {
			String msg;

			msg =
					JavaModelInstanceTypeMessages.JavaModelInstance_ProviderMethodInvocationError;
			msg = NLS.bind(msg, providerClass);

			throw new ModelAccessException(msg, e);
		}

	}

	/**
	 * <p>
	 * Adds a {@link List} of given {@link Object}s to the objects {@link List}.
	 * </p>
	 * 
	 * @param objects
	 *          A {@link List} of {@link Object}s which shall be added to the
	 *          objects {@link List}.
	 * @throws ModelAccessException
	 *           Thrown, if the given {@link Object} does not math to the model.
	 */
	private void addObjects(List<Object> objects) throws ModelAccessException {

		/* Iterate through the given objects and add them as model instance objects. */
		for (Object anObject : objects) {
			this.addObject(anObject);
		}
	}

	/**
	 * <p>
	 * Adds a given {@link Object} to the {@link List} of {@link IModelObject}s.
	 * </p>
	 * 
	 * @param anObject
	 *          The {@link Object} which shall be added to the {@link List} of
	 *          {@link IModelObject}s.
	 * @throws ModelAccessException
	 *           Thrown, if the given {@link Object} does not math to the
	 *           {@link IModel} of this {@link IModelInstance}.
	 */
	private void addObject(Object anObject) throws ModelAccessException {

		IModelObject newObject;

		newObject =
				this.myModelInstanceObjectFactory.createModelInstanceObject(anObject);

		/* If no type of the object has been found, throw an exception. */
		if (newObject == null) {
			String msg;

			msg =
					JavaModelInstanceTypeMessages.JavaModelInstance_ObjectDoesNoMatchToModel;
			msg = NLS.bind(msg, anObject.getClass(), this.myModel.getDisplayName());

			LOGGER.error(msg);
			throw new ModelAccessException(msg);
		}
		// no else.

		this.addObject(newObject);
	}

	/**
	 * <p>
	 * Adds a given {@link IModelObject} to the {@link List} of
	 * {@link IModelObject}s. Eventually contained elements are added as well.
	 * </p>
	 * 
	 * @param modelObject
	 *          The {@link IModelObject} which shall be added to the {@link List}
	 *          of {@link IModelObject}s.
	 */
	private void addObject(IModelObject modelObject) {

		if (modelObject != null) {
			this.allMyObjects.add(modelObject);

			/* Probably add contained elements as well. */
			if (modelObject instanceof IModelInstanceCollection) {
				IModelInstanceCollection aCollection;
				aCollection = (IModelInstanceCollection) modelObject;

				for (IModelObject anElement : aCollection.getContainedElements()) {
					this.addObject(anElement);
				}
			}
			// no else.
		}
		// no else.
	}

	/**
	 * FIXME Claas: REFACTORED_TILL_HERE
	 */
	private static final int REFACTORED_TILL_HERE = 0;

	/**
	 * The {@link ClassLoader} used to load types of the {@link IModelInstance}.
	 */
	protected ClassLoader myClassLoader;

	private JavaModelInstanceObjectFactory myModelInstanceObjectFactory;

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelInstance#findEnumLiteral(java.util
	 * .List)
	 */
	public OclEnumLiteral findEnumLiteral(List<String> pathName) {

		OclEnumLiteral result;
		OclEnumType anEnumerationType;

		List<String> anEnumsPath;
		String anEnumLiteralsName;

		result = null;

		/* Split the path into the enum's type path and the name of the literal. */
		anEnumsPath = new ArrayList<String>(pathName);
		anEnumLiteralsName = anEnumsPath.remove(anEnumsPath.size() - 1);

		/* Try to find the enum's type from the model. */
		anEnumerationType = this.findEnumType(anEnumsPath);

		/* Check if the enum's type has been found. */
		if (anEnumerationType != null) {

			/* Try to get the enumeration literal. */
			try {
				result =
						(OclEnumLiteral) anEnumerationType
								.getPropertyValue(anEnumLiteralsName);
			}

			catch (NoSuchFieldException e) {
				/* Do nothing, return null. */
			}

			catch (IllegalAccessException e) {
				/* Do nothing, return null. */
			}
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstance#findEnumType(java.util.
	 * List)
	 */
	public OclEnumType findEnumType(List<String> anEnumsPath) {

		OclEnumType result;
		Type aModelsEnum;

		result = null;

		/* Try to find the enum's type from the model. */
		if (this.myRootNamespace.getName().equals(anEnumsPath.get(0))) {

			List<String> aTypesPath;

			/* Clone the path list because it will be modified. */
			aTypesPath = new ArrayList<String>(anEnumsPath);
			aTypesPath.remove(0);

			aModelsEnum = this.findTypeInNamespace(aTypesPath, this.myRootNamespace);
		}

		else {
			aModelsEnum = null;
		}

		/*
		 * Check if the enum's type has been found and if the found type is an
		 * enumeration.
		 */
		if (aModelsEnum != null && aModelsEnum instanceof Enumeration) {

			Class<?> anEnumerationsClass;

			IModelObject aModelObject;
			JavaModelInstanceObject anUml2ModelObject;

			ClassLoader modelClassLoader;

			String anEnumsCanonicalName;

			/* Convert the enum's package list into a canonical name. */
			anEnumsCanonicalName = null;

			for (String aPackageName : anEnumsPath) {

				if (aPackageName.equals("root") && anEnumsCanonicalName == null) {
					/* Do nothing, ignore the root package. */
				}

				else if (anEnumsCanonicalName == null) {
					anEnumsCanonicalName = aPackageName;
				}

				else {
					anEnumsCanonicalName += "." + aPackageName;
				}
			}

			/* Get the model instances' class loader. */
			aModelObject = this.allMyObjects.get(0);
			anUml2ModelObject = (JavaModelInstanceObject) aModelObject;

			modelClassLoader =
					anUml2ModelObject.myAdaptedObject.getClass().getClassLoader();

			/* Try to load the enumeration's class. */
			try {
				anEnumerationsClass = modelClassLoader.loadClass(anEnumsCanonicalName);
			}

			catch (ClassNotFoundException e) {
				anEnumerationsClass = null;
			}

			/*
			 * If the found type is an enumeration, try to get the needed literal.
			 */
			if (anEnumerationsClass.isEnum()) {

				result =
						(OclEnumType) Platform.getAdapterManager().getAdapter(
								anEnumerationsClass, OclEnumType.class);
			}
			// no else.
		}
		// no else.

		return result;
	}

	/**
	 * FIXME Claas: Propably this method could be moved to
	 * {@link AbstractModelInstance}.
	 * 
	 * @param pathName
	 *          A {@link List} of {@link String}s describing the canonical name of
	 *          an {@link OclType} from the root package to class name).
	 * @return The {@link OclType} of a given Type (as a {@link List} of
	 *         {@link String}s describing its canonical name from the root package
	 *         to class name).
	 */
	public OclType findType(List<String> pathName) {

		OclType result;

//		if (this.allMyObjectsByType.get(pathName) != null
//				&& this.allMyObjectsByType.get(pathName).size() > 0) {
//			result =
//					this.allMyObjectsByType.get(pathName).get(0).getOclObject().getType();
//		}
//
//		else {
			result = null;
//		}

		return result;
	}

	/**
	 * @param pathName
	 *          A {@link List} of {@link String}s describing the canonical name of
	 *          an {@link OclType} from the root package to class name).
	 * @return The {@link OclType} of a given Type (as a {@link List} of
	 *         {@link String}s describing its canonical name from the root package
	 *         to class name).
	 */
	public OclType findType_old(List<String> pathName) {

		// Class<?> typeClass;
		// OclType result;
		// ArrayList<String> packagelist;
		//	
		// typeClass = null;
		// result = null;
		// packagelist = new ArrayList<String>(pathName);
		//	
		// /* Check if the given pathName leads to a type in the model. */
		// if (this.isObjectOfModel(pathName)) {
		//	
		// String path;
		// Iterator<String> pathIterator;
		//	
		// /* Eventually remove the rootPackage's name from the pathName. */
		// if (this.myRootNamespace.getName().equals(packagelist.get(0))) {
		// packagelist.remove(0);
		// }
		// // no else.
		//	
		// path = packagelist.remove(0);
		// pathIterator = packagelist.iterator();
		//	
		// while (pathIterator.hasNext()) {
		// path = path + "." + pathIterator.next();
		// }
		//	
		// /* Try to load the class for the given path. */
		// try {
		// typeClass = this.myClassLoader.loadClass(path);
		//	
		// /* Check if the loaded types is already known. */
		// result = this.myKnownTypes.get(typeClass);
		//	
		// /* Check if the loaded type is already known; else load him. */
		// if (result == null) {
		// result =
		// (OclType) Platform.getAdapterManager().getAdapter(typeClass,
		// OclType.class);
		// this.myKnownTypes.put(typeClass, result);
		// }
		// // no else.
		// }
		//	
		// catch (ClassNotFoundException e) {
		// /* Do nothing; return null. */
		// }
		//	
		// }
		//	
		// return result;
		return null;
	}
}