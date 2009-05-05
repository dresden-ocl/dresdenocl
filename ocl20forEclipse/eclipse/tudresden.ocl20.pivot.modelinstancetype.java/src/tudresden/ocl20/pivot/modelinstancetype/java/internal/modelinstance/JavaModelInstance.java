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

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.AdapterFactory;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclEnumLiteral;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclEnumType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.StandardlibraryAdapterFactory;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelInstance;
import tudresden.ocl20.pivot.modelbus.IModelInstanceFactory;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.base.AbstractModelInstance;
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

	/**
	 * The {@link AdapterFactory} used to adapt the model instance to the
	 * standard library.
	 */
	protected static StandardlibraryAdapterFactory DEFAULTSLAF = JavaStandardlibraryAdapterFactory
			.getInstance();

	/**
	 * The {@link ClassLoader} used to load types of the {@link IModelInstance}.
	 */
	protected ClassLoader myClassLoader;

	/**
	 * <p>
	 * Creates and initializes a new {@link JavaModelInstance}.
	 * </p>
	 * 
	 * @param providerClass
	 *            The provider class used to get all type instances of this
	 *            model instance.
	 * @param model
	 *            The {@link IModel} of this {@link IModelInstance}.s
	 * @throws ModelAccessException
	 */
	@SuppressWarnings("unchecked")
	public JavaModelInstance(Class<?> providerClass, IModel model)
			throws ModelAccessException {

		this.myModel = model;
		this.myRootNamespace = model.getRootNamespace();

		this.allMyObjectsByType = new HashMap<String, List<IModelObject>>();
		this.allMyObjects = new ArrayList<IModelObject>();
		this.allMyObjectKinds = new ArrayList<List<String>>();
		this.myKnownTypes = new HashMap<Class<?>, OclType>();

		this.myInstanceName = providerClass.getCanonicalName();
		this.myClassLoader = providerClass.getClassLoader();

		/*
		 * Register the adapters to enable successful adaption to OclObject in
		 * the standard library.
		 */
		this.myCurrentSlAF = DEFAULTSLAF;
		this.myCurrentSlAF.registerAdapters();

		try {
			Method providerMethod;
			List<Object> providedObjects;

			/* Get the provider method and its model objects. */
			providerMethod = providerClass.getDeclaredMethod("getModelObjects",
					new Class[0]);

			providedObjects = (List<Object>) providerMethod.invoke(null,
					new Object[0]);
			this.addObjects(providedObjects);
		}

		catch (Exception e) {

			String msg;

			msg = "Problem during retrieving of ModelObjects. Reason: ";
			msg += e.getMessage();

			throw new ModelAccessException(msg, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
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
				result = (OclEnumLiteral) anEnumerationType
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
	 * 
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelInstance#findEnumType(java.util.
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

			aModelsEnum = this.findTypeInNamespace(aTypesPath,
					this.myRootNamespace);
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
			JavaModelObject anUml2ModelObject;

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
			anUml2ModelObject = (JavaModelObject) aModelObject;

			modelClassLoader = anUml2ModelObject.myAdaptedObject.getClass()
					.getClassLoader();

			/* Try to load the enumeration's class. */
			try {
				anEnumerationsClass = modelClassLoader
						.loadClass(anEnumsCanonicalName);
			}

			catch (ClassNotFoundException e) {
				anEnumerationsClass = null;
			}

			/*
			 * If the found type is an enumeration, try to get the needed
			 * literal.
			 */
			if (anEnumerationsClass.isEnum()) {

				result = (OclEnumType) Platform.getAdapterManager().getAdapter(
						anEnumerationsClass, OclEnumType.class);
			}
			// no else.
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstance#getFactory()
	 */
	public IModelInstanceFactory getFactory() {

		if (this.myModelInstanceFactory == null) {
			this.myModelInstanceFactory = new JavaModelInstanceFactory();
		}
		// no else.

		return this.myModelInstanceFactory;
	}

	/**
	 * @param pathName
	 *            A {@link List} of {@link String}s describing the canonical
	 *            name of an {@link OclType} from the root package to class
	 *            name).
	 * @return The {@link OclType} of a given Type (as a {@link List} of
	 *         {@link String}s describing its canonical name from the root
	 *         package to class name).
	 */
	public OclType findType(List<String> pathName) {

		Class<?> typeClass;
		OclType result;
		ArrayList<String> packagelist;

		typeClass = null;
		result = null;
		packagelist = new ArrayList<String>(pathName);

		/* Check if the given pathName leads to a type in the model. */
		if (this.isObjectOfModel(pathName)) {

			String path;
			Iterator<String> pathIterator;

			/* Eventually remove the rootPackage's name from the pathName. */
			if (this.myRootNamespace.getName().equals(packagelist.get(0))) {
				packagelist.remove(0);
			}
			// no else.

			path = packagelist.remove(0);
			pathIterator = packagelist.iterator();

			while (pathIterator.hasNext()) {
				path = path + "." + pathIterator.next();
			}

			/* Try to load the class for the given path. */
			try {
				typeClass = this.myClassLoader.loadClass(path);

				/* Check if the loaded types is already known. */
				result = this.myKnownTypes.get(typeClass);

				/* Check if the loaded type is already known; else load him. */
				if (result == null) {
					result = (OclType) Platform.getAdapterManager().getAdapter(
							typeClass, OclType.class);
					this.myKnownTypes.put(typeClass, result);
				}
				// no else.
			}

			catch (ClassNotFoundException e) {
				/* Do nothing; return null. */
			}

		}

		return result;
	}

	/**
	 * <p>
	 * Adds a given {@link Object} to the objects {@link List}.
	 * </p>
	 * 
	 * @param anObject
	 *            The {@link Object} which shall be added to the objects
	 *            {@link List}.
	 * @throws ModelAccessException
	 *             Thrown, if the given {@link Object} does not math to the
	 *             model.
	 */
	private void addObject(Object anObject) throws ModelAccessException {

		String key;
		List<IModelObject> objectList;

		JavaModelObject newObject;
		List<String> qualifiedName;

		Type aType;
		List<String> aTypesQualifiedName;

		/* Try to get the canonical name of the related type in the model. */
		key = this.getTypeNameInModel(anObject.getClass());

		if (key == null) {
			String msg;

			msg = "ModelInstance doesn't match to model. ";
			msg += "Type for " + anObject.getClass();
			msg += " not found in model " + myModel.getDisplayName();

			throw new ModelAccessException(msg);
		}
		// no else.

		/* Get objects to the given key. */
		objectList = this.allMyObjectsByType.get(key);

		/* Eventually create a new object list. */
		if (objectList == null) {
			objectList = new ArrayList<IModelObject>();
		}
		// no else.

		qualifiedName = Arrays
				.asList((this.myRootNamespace.getName() + "." + key)
						.split("[.]"));

		/* Find the type which belongs to this model object. */
		aTypesQualifiedName = new ArrayList<String>(qualifiedName);
		aTypesQualifiedName.remove(0);

		aType = this.findTypeInNamespace(aTypesQualifiedName,
				this.myRootNamespace);

		newObject = new JavaModelObject(anObject, aType);
		objectList.add(newObject);

		/* Add the new created Object to the object list. */
		this.allMyObjectsByType.put(key, objectList);
		this.allMyObjects.add(newObject);

		/* Eventually add the qualified name to the object kinds. */
		if (!this.allMyObjectKinds.contains(qualifiedName)) {
			this.allMyObjectKinds.add(qualifiedName);
		}
		// no else.
	}

	/**
	 * <p>
	 * Adds a {@link List} of given {@link Object}s to the objects {@link List}.
	 * </p>
	 * 
	 * @param objects
	 *            A {@link List} of {@link Object}s which shall be added to the
	 *            objects {@link List}.
	 * @throws ModelAccessException
	 *             Thrown, if the given {@link Object} does not math to the
	 *             model.
	 */
	private void addObjects(List<Object> objects) throws ModelAccessException {

		Iterator<Object> objectIterator;

		objectIterator = objects.iterator();

		while (objectIterator.hasNext()) {
			this.addObject(objectIterator.next());
		}
	}

	/**
	 * <p>
	 * A helper method returning a {@link Set} containing all interface
	 * {@link Class}es a given {@link Class} or one of its super {@link Class}es
	 * implements.
	 * </p>
	 * 
	 * @param aClass
	 *            The Class whose interface {@link Class}es shall be returned.
	 * @return A {@link Set} of interface {@link Class}es.
	 */
	private Set<Class<?>> collectInterfaces(Class<?> aClass) {

		Set<Class<?>> result;
		Class<?> superClass;

		Class<?>[] aClassInterfaces;
		Class<?> anInterface;

		superClass = aClass.getSuperclass();

		/* Eventually get the interfaces of the super class. */
		if (superClass != null) {
			result = this.collectInterfaces(superClass);
		}

		/* Else create a new set. */
		else {
			result = new HashSet<Class<?>>();
		}

		aClassInterfaces = aClass.getInterfaces();

		/* Add all interfaces of the class to the result. */
		for (int index = 0; index < aClassInterfaces.length; index++) {

			anInterface = aClassInterfaces[index];

			/* Add the interface. */
			result.add(anInterface);
			/* Add all extended interfaces. */
			result.addAll(this.collectInterfaces(anInterface));
		}

		return result;
	}

	/**
	 * <p>
	 * A helper method checking whether or not a given {@link Class} is part of
	 * the active {@link IModel} or is a sub {@link Type} of a {@link Type} in
	 * the active {@link IModel}. Returns the canonical name of the {@link Type}
	 * in the {@link IModel} or <code>null</code>.
	 * </p>
	 * 
	 * @param objectClass
	 *            The {@link Class} which shall be checked.
	 * @return The canonical name of the {@link Type} in the {@link IModel} or
	 *         <code>null</code>.
	 */
	private String getTypeNameInModel(Class<?> objectClass) {

		String result;

		Class<?> aClass;

		String[] canonicalName;
		List<String> packageList;

		result = null;
		aClass = objectClass;

		/*
		 * Check the class itself and eventually the class inheritance path
		 * until java.lang.Object.
		 */
		while (aClass != null) {

			/* Get the canonicalName of the given Class. */
			canonicalName = aClass.getCanonicalName().split("[.]");
			packageList = new ArrayList<String>(Arrays.asList(canonicalName));

			/* Check if the packageList belongs to the model. */
			if (this.isObjectOfModel(packageList)) {
				result = aClass.getCanonicalName();
				break;
			}

			/* Else get the super class. */
			else {
				aClass = aClass.getSuperclass();
			}
		}

		/*
		 * If no related model type has been found yet, try to check the class'
		 * interfaces.
		 */
		if (result == null) {

			Set<Class<?>> interfaces;

			/*
			 * Get the interface classes of the object class and its super
			 * classes.
			 */
			interfaces = this.collectInterfaces(objectClass);

			/*
			 * Iterate through the interfaces and check if they are part of the
			 * model.
			 */
			for (Class<?> anInterface : interfaces) {

				/* Get the canonicalName of the given Class. */
				canonicalName = anInterface.getCanonicalName().split("[.]");
				packageList = new ArrayList<String>(Arrays
						.asList(canonicalName));

				/* Check if the packageList belongs to the model. */
				if (this.isObjectOfModel(packageList)) {
					result = anInterface.getCanonicalName();
					break;
				}
				// no else.
			}
			// end for.
		}
		// no else.

		return result;
	}
}