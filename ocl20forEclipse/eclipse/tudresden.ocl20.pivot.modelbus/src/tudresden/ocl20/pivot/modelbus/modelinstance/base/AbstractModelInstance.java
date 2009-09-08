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
package tudresden.ocl20.pivot.modelbus.modelinstance.base;

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
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceEnumerationLiteral;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceFactory;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceTypeObject;
import tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral;
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

	/** The {@link IModel} of this {@link IModelInstance}. */
	protected IModel myModel;

	/** Contains all {@link IModelInstanceElement}s of this model instance. */
	protected Set<IModelInstanceElement> myModelInstanceElements =
			new HashSet<IModelInstanceElement>();

	/**
	 * <p>
	 * Contains all {@link IModelInstanceElement}s of this model instance ordered
	 * by their type's name.
	 * </p>
	 * <strong>This map is a {@link WeakHashMap}. If the adapted {@link Type} does
	 * not exist any more, the adapter is also disposed.</strong>
	 */
	protected Map<Type, Set<IModelInstanceElement>> myModelInstanceElementsByType =
			new WeakHashMap<Type, Set<IModelInstanceElement>>();

	/**
	 * The {@link IModelInstanceFactory} used to created adapters for the
	 * {@link IModelInstanceElement}s.
	 */
	protected IModelInstanceFactory myModelInstanceFactory;

	/** The name of the model instance. */
	protected String myName;

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance#getAllInstances
	 * (tudresden.ocl20.pivot.pivotmodel.Type)
	 */
	public Set<IModelInstanceElement> getAllInstances(Type type) {

		Set<IModelInstanceElement> result;

		/* If the type has been found, return all implementations. */
		result = this.myModelInstanceElementsByType.get(type);

		if (result == null) {
			result = new HashSet<IModelInstanceElement>();
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstance#getDisplayName()
	 */
	public String getDisplayName() {

		return this.myName;
	}

	/**
	 * <p>
	 * Adds a given {@link IModelInstanceElement} to the {@link Type} mapping of
	 * this {@link AbstractModelInstance}.
	 * </p>
	 * 
	 * @param modelInstanceElement
	 *          The {@link IModelInstanceElement} that shall be added to the
	 *          {@link Type} mapping.
	 */
	protected void addModelInstanceObjectToCache(
			IModelInstanceElement modelInstanceElement) {

		/* Iterate through all types of the object. */
		for (Type type : modelInstanceElement.getTypes()) {

			if (this.myModelInstanceElementsByType.containsKey(type)) {
				this.myModelInstanceElementsByType.get(type).add(modelInstanceElement);
			}

			else {
				Set<IModelInstanceElement> modelObjects;

				modelObjects = new HashSet<IModelInstanceElement>();
				modelObjects.add(modelInstanceElement);

				myModelInstanceElementsByType.put(type, modelObjects);
			}

		}
		// end for.
	}

	/**
	 * <p>
	 * A helper method that adds all adapted {@link IModelInstanceObject} of this
	 * {@link AbstractModelInstance} contained in the filed
	 * {@link AbstractModelInstance#myModelInstanceElements} to the {@link Type}
	 * mapping of this {@link AbstractModelInstance}.
	 * </p>
	 */
	protected void initializeTypeMapping() {

		for (IModelInstanceElement modelInstanceElement : this.myModelInstanceElements) {
			this.addModelInstanceObjectToCache(modelInstanceElement);
		}
	}

	/** FIXME Claas: REFACTORED_TILL_HERE. */
	private static final int REFACTORED_TILL_HERE = 0;

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

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelInstance#findEnumerationLiteral(tudresden
	 * .ocl20.pivot.pivotmodel.EnumerationLiteral)
	 */
	public IModelInstanceEnumerationLiteral findEnumerationLiteral(
			EnumerationLiteral literal) {

		IModelInstanceEnumerationLiteral result;
		Set<IModelInstanceElement> allLiteralsOfEnumeration;

		result = null;

		Type enumeration = (Type) literal.getOwner();
		allLiteralsOfEnumeration =
				this.myModelInstanceElementsByType.get(enumeration);

		for (IModelInstanceElement anObject : allLiteralsOfEnumeration) {
			if (anObject instanceof IModelInstanceEnumerationLiteral) {
				IModelInstanceEnumerationLiteral aLiteral;
				aLiteral = (IModelInstanceEnumerationLiteral) anObject;

				if (aLiteral.getLiteral().name().equals(literal.getName())) {
					result = aLiteral;
					break;
				}
				// no else.
			}
			// no else.
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelInstance#findModelTypeObject(tudresden
	 * .ocl20.pivot.pivotmodel.Type)
	 */
	public IModelInstanceTypeObject findModelTypeObject(Type type) {

		return this.myModelTypeObjects.get(type);
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstance#getObjects()
	 */
	public List<IModelInstanceElement> getAllElements() {

		return new ArrayList<IModelInstanceElement>(this.myModelInstanceElements);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance#getElementTypes
	 * ()
	 */
	public Set<Type> getAllImplementedTypes() {

		Set<Type> result = new HashSet<Type>();

		for (IModelInstanceElement modelObject : this.myModelInstanceElements) {
			result.addAll(modelObject.getTypes());
		}

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
	 * @seetudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance#
	 * getModelInstanceFactory()
	 */
	public IModelInstanceFactory getModelInstanceFactory() {

		return this.myModelInstanceFactory;
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
	 * FIXME Claas: This has to be moved into the standard library.
	 * 
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

	/*
	 * FIXME Claas: This method should be located in the standard library.
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
}