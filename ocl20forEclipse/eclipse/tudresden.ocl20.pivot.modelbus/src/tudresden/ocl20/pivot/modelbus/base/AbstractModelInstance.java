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
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceTypeObject;
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
	protected Set<IModelInstanceElement> myModelObjects = new HashSet<IModelInstanceElement>();

	/**
	 * <p>
	 * Contains all {@link IModelInstanceElement}s of this model instance ordered by their
	 * type's name.
	 * </p>
	 */
	protected Map<List<String>, Set<IModelInstanceElement>> myModelObjectsByType =
			new HashMap<List<String>, Set<IModelInstanceElement>>();

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
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstance#getDisplayName()
	 */
	public String getDisplayName() {

		return this.myInstanceName;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstance#getObjects()
	 */
	public List<IModelInstanceElement> getObjects() {

		return new ArrayList<IModelInstanceElement>(this.myModelObjects);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelInstance#getObjectsOfType(java.util
	 * .List)
	 */
	public List<IModelInstanceElement> getObjectsOfType(List<String> typePath) {

		List<IModelInstanceElement> result;
		Set<IModelInstanceElement> resultSet;

		resultSet = this.myModelObjectsByType.get(typePath);

		if (resultSet != null) {
			result = new ArrayList<IModelInstanceElement>(resultSet);
		}

		else {
			result = new ArrayList<IModelInstanceElement>();
		}

		return result;
	}

	/**
	 * @return A {@link Set} containing {@link Type}s in this model instance.
	 */
	public Set<Type> getObjectTypes() {

		Set<Type> result = new HashSet<Type>();

		for (IModelInstanceElement modelObject : myModelObjects) {
			result.addAll(modelObject.getTypes());
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelInstance#getOperationName(java.lang
	 * .String, int)
	 */
	public String getOperationName(String name, int operatorCount) {

		String result;
		Map<String, String> operationMap;

		result = null;
		operationMap = operationNames.get(operatorCount);

		if (operationMap != null) {

			result = operationMap.get(name);
		}
		// no else.

		if (result == null) {
			result = name;
		}
		// no else.

		return result;
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
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelInstance#isInstanceOf(tudresden.ocl20
	 * .pivot.modelbus.IModel)
	 */
	public boolean isInstanceOf(IModel aModel) {

		return this.myModel.equals(aModel);
	}

	/**
	 * <p>
	 * <strong>Has to be called after the initialization of a subclass.
	 * Initializes cache {@link #myModelObjectsByType}.</strong>
	 * </p>
	 */
	protected void initializeCache() {

		for (IModelInstanceElement modelObject : myModelObjects) {

			for (Type type : modelObject.getTypes()) {

				final List<String> qualifiedNameList = type.getQualifiedNameList();

				if (myModelObjectsByType.containsKey(qualifiedNameList)) {
					myModelObjectsByType.get(qualifiedNameList).add(modelObject);
				}

				else {
					Set<IModelInstanceElement> modelObjects = new HashSet<IModelInstanceElement>();
					modelObjects.add(modelObject);

					myModelObjectsByType.put(qualifiedNameList, modelObjects);
				}

			}
			// end for.
		}
		// end for.
	}
}