/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Ronny Brandt (Ronny_Brandt@web.de).                    *
 * All rights reserved.                                                      *
 *                                                                           *
 * This work was done as a project at the Chair for Software Technology,     *
 * Dresden University Of Technology, Germany (http://st.inf.tu-dresden.de).  *
 * It is understood that any modification not identified as such is not      *
 * covered by the preceding statement.                                       *
 *                                                                           *
 * This work is free software; you can redistribute it and/or modify it      *
 * under the terms of the GNU Library General Public License as published    *
 * by the Free Software Foundation; either version 2 of the License, or      *
 * (at your option) any later version.                                       *
 *                                                                           *
 * This work is distributed in the hope that it will be useful, but WITHOUT  *
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or     *
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Library General Public     *
 * License for more details.                                                 *
 *                                                                           *
 * You should have received a copy of the GNU Library General Public License *
 * along with this library; if not, you can view it online at                *
 * http://www.fsf.org/licensing/licenses/gpl.html.                           *
 *                                                                           *
 * To submit a bug report, send a comment, or get the latest news on this    *
 * project, please visit the website: http://dresden-ocl.sourceforge.net.    *
 * For more information on OCL and related projects visit the OCL Portal:    *
 * http://st.inf.tu-dresden.de/ocl                                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package tudresden.ocl20.pivot.modelbus.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelBusConstants;
import tudresden.ocl20.pivot.modelbus.IModelInstance;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceTypeObject;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * An abstract implementation of {@link IModelInstance}.
 * </p>
 * 
 * @author Ronny Brandt: Implemented first version
 * @author Claas Wilke: Refactored and added Java-Doc.
 */
public abstract class AbstractModelInstance implements IModelInstance {

	/**
	 * Contains the operation names which are different in the standard library
	 * than in the OCL specification. The names are separated in sub maps
	 * depending on their number of arguments.
	 */
	protected static Map<Integer, Map<String, String>> operationNames =
			new HashMap<Integer, Map<String, String>>();

	/* Initializes the map. */
	static {
		Map<String, String> unaryOperations;
		Map<String, String> binaryOperations;

		unaryOperations = new HashMap<String, String>();
		unaryOperations.put("-", "negative");
		unaryOperations.put("oclIsUndefined", "isOclUndefined");
		operationNames.put(1, unaryOperations);

		binaryOperations = new HashMap<String, String>();
		binaryOperations.put("<=", "isLessEqual");
		binaryOperations.put("<", "isLessThan");
		binaryOperations.put("=", "isEqualTo");
		binaryOperations.put("<>", "isNotEqualTo");
		binaryOperations.put(">=", "isGreaterEqual");
		binaryOperations.put(">", "isGreaterThan");
		binaryOperations.put("-", "subtract");
		binaryOperations.put("+", "add");
		binaryOperations.put("*", "multiply");
		binaryOperations.put("/", "divide");
		binaryOperations.put(".", "getPropertyValue");
		binaryOperations.put("->", "asSet");
		operationNames.put(2, binaryOperations);
	}

	/** The name of the model instance. */
	protected String myInstanceName;

	/** The {@link IModel} of this {@link IModelInstance}. */
	protected IModel myModel;

	/** Contains all {@link Object}s of this model instance. */
	protected List<IModelObject> myModelObjects = new ArrayList<IModelObject>();

	/**
	 * <p>
	 * Contains all {@link IModelObject}s of this model instance ordered by their
	 * type's name.
	 * </p>
	 */
	protected Map<List<String>, List<IModelObject>> myModelObjectsByType =
			new HashMap<List<String>, List<IModelObject>>();

	/**
	 * <p>
	 * Contains all {@link IModelInstanceTypeObject} of this
	 * {@link IModelInstance} identified by their adapted {@link Type}.
	 * </p>
	 * <p>
	 * <strong>This map is a {@link WeakHashMap}. If the adapted {@link Type} does
	 * not exist any more, the adapter is also disposed.</strong>
	 * </p>
	 */
	protected Map<Type, IModelInstanceTypeObject> myModelTypeObjects =
			new WeakHashMap<Type, IModelInstanceTypeObject>();

	/** The root {@link Namespace} of the meta model. */
	protected Namespace myRootNamespace;

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelInstance#findModelTypeObject(tudresden
	 * .ocl20.pivot.pivotmodel.Type)
	 */
	public IModelInstanceTypeObject findModelTypeObject(Type type) {

		IModelInstanceTypeObject result;

		result = this.myModelTypeObjects.get(type);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelInstance#getObjectsOfType(java.util
	 * .List)
	 */
	public List<IModelObject> getObjectsOfType(List<String> typePath) {

		return this.myModelObjectsByType.get(typePath);
	}

	/**
	 * <p>
	 * <strong>Has to be called after the initialization of a subclass.
	 * Initializes cache {@link #myModelObjectsByType}.</strong>
	 * </p>
	 */
	protected void initializeCache() {

		for (IModelObject modelObject : myModelObjects) {
			for (Type type : modelObject.getTypes()) {

				final List<String> qualifiedNameList = type.getQualifiedNameList();

				if (myModelObjectsByType.containsKey(qualifiedNameList)) {
					myModelObjectsByType.get(qualifiedNameList).add(modelObject);
				}
				else {
					List<IModelObject> modelObjects = new ArrayList<IModelObject>();
					modelObjects.add(modelObject);
					myModelObjectsByType.put(qualifiedNameList, modelObjects);
				}

			}
			// end for.
		}
		// end for.
	}

	/** FIXME Claas: REFACTORED_TILL_HERE */
	private static final int REFACTORED_TILL_HERE = 0;

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstance#getDisplayName()
	 */
	public String getDisplayName() {

		return this.myInstanceName;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstance#getModel()
	 */
	public IModel getModel() {

		return this.myModel;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstance#getObjects()
	 */
	public List<IModelObject> getObjects() {

		return this.myModelObjects;
	}

	/**
	 * @return A {@link Set} containing {@link Type}s in this model instance.
	 */
	public Set<Type> getObjectTypes() {

		Set<Type> retSet = new HashSet<Type>();

		for (IModelObject modelObject : myModelObjects) {
			retSet.addAll(modelObject.getTypes());
		}

		return retSet;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelInstance#getOperationName(java.lang
	 * .String, int)
	 */
	public String getOperationName(String name, int operatorCount) {

		Map<String, String> opMap = operationNames.get(operatorCount);
		if (opMap != null) {
			String ret = opMap.get(name);
			if (ret != null)
				return ret;
		}
		return name;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelInstance#isInstanceOf(tudresden.ocl20
	 * .pivot.modelbus.IModel)
	 */
	public boolean isInstanceOf(IModel aModel) {

		return this.myModel.equals(aModel);
	}

	/**
	 * <p>
	 * A helper methods used to find the {@link Type} to a given path in a given
	 * {@link Namespace} of this {@link JavaModelInstance}.
	 * </p>
	 * 
	 * @param aPackagePath
	 *          The path of the {@link Type} which shall be searched for.
	 * @param aNamespace
	 *          The {@link Namespace} in which shall be searched for the
	 *          {@link Type}.
	 * @return the found {@link Type} or null.
	 * 
	 */
	protected Type findTypeInNamespace(List<String> aPackagePath,
			Namespace aNamespace) {

		Type result;

		result = null;

		/* Check if the searched type is located in a nested name space. */
		if (aPackagePath.size() > 1) {

			List<Namespace> nestedNamespaces;
			String currentPackage;

			nestedNamespaces = aNamespace.getNestedNamespace();
			currentPackage = aPackagePath.remove(0);

			/* Check if any nested name space matches the actual package's name. */
			for (Namespace aNestedNamespace : nestedNamespaces) {

				String aNestedNamespacesName;

				aNestedNamespacesName = aNestedNamespace.getName();

				if (aNestedNamespacesName.equals(currentPackage)) {

					result = this.findTypeInNamespace(aPackagePath, aNestedNamespace);
					break;
				}
				// no else.
			}
		}

		/* Else search for the type in this name space. */
		else {

			List<Type> ownedTypes;

			ownedTypes = aNamespace.getOwnedType();

			/* Check if any type matches the type's name. */
			for (Type aType : ownedTypes) {

				String aTypesName;

				aTypesName = aType.getName();

				if (aTypesName.equals(aPackagePath.get(0))) {
					result = aType;
					break;
				}
				// no else.
			}
			// end for.
		}
		// end else.

		return result;
	}

	/**
	 * <p>
	 * Checks if a given model object (as a {@link List} of {@link String}s
	 * representing its canonical name) is object of the given model.
	 * </p>
	 * 
	 * @param pathName
	 *          The canonical name of the model object as a {@link List} of
	 *          {@link String}s.
	 * @return True if the given object is an object of the given model.
	 */
	protected boolean isObjectOfModel(List<String> pathName) {

		boolean result;
		List<String> modifiedPathname;

		result = true;

		modifiedPathname = new ArrayList<String>(pathName);

		/* If the rootNamespace is contained in the pathName, remove him. */
		if (this.myRootNamespace.getName().equals(modifiedPathname.get(0))) {
			modifiedPathname.remove(0);
		}
		// no else.

		if (this.myRootNamespace != null) {

			Namespace aPackage;
			int index;

			index = 0;
			aPackage = this.myRootNamespace;

			while (result && index < modifiedPathname.size()) {

				String packageName;

				result = false;

				packageName = modifiedPathname.get(index);

				if (index < modifiedPathname.size() - 1) {

					List<Namespace> nestedNamespaces;

					nestedNamespaces = aPackage.getNestedNamespace();

					/* Search the next sub package. */
					for (Namespace aNamespace : nestedNamespaces) {

						if (aNamespace.getName().equals(packageName)) {
							aPackage = aNamespace;
							result = true;
							break;
						}
					}
				}

				else {
					List<Type> ownedTypes;

					ownedTypes = aPackage.getOwnedType();

					/* Search the next sub package. */
					for (Type aType : ownedTypes) {

						if (aType.getName().equals(packageName)) {
							result = true;
							break;
						}
					}
				}

				index++;
			}
		}

		else {
			result = false;
		}

		return result;
	}

	/**
	 * <p>
	 * Convert a given path (as a {@link List} of {@link String}s) into a Java
	 * canonical class name.
	 * </p>
	 * 
	 * @param path
	 *          The path which shall be converted.
	 * @return A canonical name.
	 */
	protected String toCanonicalName(List<String> path) {

		String result;

		result = "";

		/* Clone the given path. */
		path = new ArrayList<String>(path);

		/* Eventually remove the root package. */
		if (path.size() > 0
				&& path.get(0).equals(IModelBusConstants.ROOT_PACKAGE_NAME)) {
			path.remove(0);
		}
		// no else.

		/* Iterate through the packages and generate the path. */
		for (int index = 0; index < path.size() - 1; index++) {
			result += path.get(index) + ".";
		}

		if (path.size() > 0) {
			result += path.get(path.size());
		}
		// no else.

		return result;
	}
}