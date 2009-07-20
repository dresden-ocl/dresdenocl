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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Platform;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollectionType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInvalid;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclPrimitiveType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclTupleType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclVoid;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.StandardlibraryAdapterFactory;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelBusConstants;
import tudresden.ocl20.pivot.modelbus.IModelInstance;
import tudresden.ocl20.pivot.modelbus.IModelInstanceFactory;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.modelbus.util.OclCollectionTypeKind;
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

	/** The default StandardlibraryAdapterFactory for model instances. */
	protected static StandardlibraryAdapterFactory DEFAULTSLAF = null;

	/**
	 * Contains the qualified names of all types contained in this model instance.
	 */
	protected List<List<String>> allMyObjectKinds = new ArrayList<List<String>>();

	/** Contains all {@link Object}s of this model instance. */
	protected List<IModelObject> allMyObjects;

	/**
	 * <p>
	 * Contains all {@link Object}s of this model instance ordered by their type's
	 * name.
	 * </p>
	 * 
	 * <strong>The name follows the naming scheme of Java canonical names! E.g.
	 * 'root::aPackage::anObject' must be stored as 'aPackage.anObject'</strong>
	 */
	protected Map<String, List<IModelObject>> allMyObjectsByType;

	/** The current StandardlibraryAdapterFactory for this instance */
	protected StandardlibraryAdapterFactory myCurrentSlAF = null;

	/** The name of the model instance. */
	protected String myInstanceName;

	/**
	 * Contains all OclTypes which have already been loaded associated to an
	 * indentifier like their corresponding model object or a {@link Class}
	 * object.
	 */
	protected Map<Class<?>, OclType> myKnownTypes;

	/** The {@link IModel} of this {@link IModelInstance}. */
	protected IModel myModel;

	/**
	 * The {@link IModelInstanceFactory} used to create the model instance
	 * objects.
	 */
	protected IModelInstanceFactory myModelInstanceFactory;

	/** The root {@link Namespace} of the meta model. */
	protected Namespace myRootNamespace;

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

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelInstance#getCollectionType(tudresden
	 * .ocl20.pivot.modelbus.util.OclCollectionTypeKind,
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType)
	 */
	public OclCollectionType getCollectionType(OclCollectionTypeKind kind,
			OclType elementType) {

		// TODO This method is not used by the standard library and thus not
		// implemented yet.

		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstance#getCurrentSlAF()
	 */
	public StandardlibraryAdapterFactory getCurrentSlAF() {

		return myCurrentSlAF;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstance#getDefaultSlAF()
	 */
	public StandardlibraryAdapterFactory getDefaultSlAF() {

		return DEFAULTSLAF;
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

		return this.allMyObjects;
	}

	/**
	 * @return A {@link List} containing {@link List}s of {@link String}s
	 *         representing the canonical names of all Object kinds which have
	 *         instances in this model instance.
	 */
	public List<List<String>> getObjectKinds() {

		return this.allMyObjectKinds;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelInstance#getObjectsOfKind(java.util
	 * .List)
	 */
	public List<IModelObject> getObjectsOfKind(List<String> typePath) {

		List<IModelObject> result;
		Iterator<String> typePathIterator;

		if (typePath != null) {

			List<String> path;
			Iterator<String> pathIterator;

			String current;
			String type;

			path = new ArrayList<String>();
			typePathIterator = typePath.iterator();

			/* Convert the type path and remove elements containing '('. */
			while (typePathIterator.hasNext()) {

				current = typePathIterator.next();

				if (!current.contains("(")) {
					path.add(current);
				}
				else {
					break;
				}
			}

			/*
			 * Check if the path is an object of the model and eventually remove the
			 * top package from the path.
			 */
			if (!this.isObjectOfModel(path)) {
				while (path.size() > 0
						&& !path.get(0).equals(this.myRootNamespace.getName())) {
					path.remove(0);
				}
			}
			// no else.

			if (path.get(0).equals(this.myRootNamespace.getName())) {
				path.remove(0);
			}
			// no else.

			type = path.remove(0);
			pathIterator = path.iterator();

			/* Transform the path into a canonical name. */
			while (pathIterator.hasNext()) {
				type = type + "." + pathIterator.next();
			}

			/* Get all objects of the transformed type. */
			result = this.allMyObjectsByType.get(type);

			if (result == null) {
				result = new ArrayList<IModelObject>();
			}
			// no else.
		}

		else {
			throw new IllegalArgumentException("TypePath must not be null");
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
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstance#getAnyType()
	 */
	public OclType getAnyType() {

		return (OclType) Platform.getAdapterManager().getAdapter("OclAny",
				OclType.class);
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstance#getInvalid()
	 */
	public OclInvalid getInvalid() {

		return (OclInvalid) Platform.getAdapterManager().getAdapter("Invalid",
				OclInvalid.class);
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstance#getInvalidType()
	 */
	public OclType getInvalidType() {

		return (OclType) Platform.getAdapterManager().getAdapter("OclInvalid",
				OclType.class);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelInstance#getPrimitiveType(java.lang
	 * .String)
	 */
	public OclPrimitiveType getPrimitiveType(String name) {

		return (OclPrimitiveType) Platform.getAdapterManager().getAdapter(name,
				OclPrimitiveType.class);
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstance#getTupleType(java.lang.
	 * String[], tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType[])
	 */
	public OclTupleType getTupleType(String[] partNames, OclType[] partTypes) {

		// TODO This method is not used by the standard library and thus not
		// implemented yet.

		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstance#getTypeType()
	 */
	public OclType getTypeType() {

		return (OclType) Platform.getAdapterManager().getAdapter("OclType",
				OclType.class);
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstance#getUndefined()
	 */
	public OclVoid getUndefined() {

		return (OclVoid) Platform.getAdapterManager().getAdapter("Undefined",
				OclVoid.class);
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstance#getVoidType()
	 */
	public OclType getVoidType() {

		return (OclType) Platform.getAdapterManager().getAdapter("OclVoid",
				OclType.class);
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

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstance#setCurrentSlAF(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.StandardlibraryAdapterFactory)
	 */
	public void setCurrentSlAF(StandardlibraryAdapterFactory slAF) {

		this.myCurrentSlAF = slAF;
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