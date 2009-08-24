/*
Copyright (C) 2009 by Claas Wilke (info@claaswilke.de)

This file is part of the Java OCL Standard Library of Dresden OCL2 for Eclipse.

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
package tudresden.ocl20.pivot.standardlibrary.java.internal.factory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclEnumLiteral;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInvalid;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclObject;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclTuple;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.factory.IStandardLibraryFactory;
import tudresden.ocl20.pivot.essentialocl.types.CollectionType;
import tudresden.ocl20.pivot.essentialocl.types.TupleType;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceTypeObject;
import tudresden.ocl20.pivot.pivotmodel.Enumeration;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclBag;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclBoolean;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclCollectionType;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclEnumLiteral;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclEnumType;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclInteger;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclInvalid;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclObject;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclOrderedSet;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclPrimitiveType;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclReal;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclSequence;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclSet;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclString;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclTuple;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclTupleType;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclType;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclVoid;

/**
 * <p>
 * The {@link JavaStandardLibraryFactory} provides methods to create
 * {@link OclRoot} required during OCL interpretation.
 * </p>
 */
public class JavaStandardLibraryFactory implements IStandardLibraryFactory {

	/** The singleton instance of the {@link JavaStandardLibraryFactory}. */
	public static JavaStandardLibraryFactory INSTANCE =
			new JavaStandardLibraryFactory();

	/**
	 * Contains the already adapted {@link OclRoot} identified by their adapted
	 * {@link Object}. <strong>This is a {@link WeakHashMap}! If an {@link Object}
	 * is disposed, its adapter can also be disposed.</strong>
	 */
	private Map<Object, OclRoot> myCachedAdaptedObjects =
			new WeakHashMap<Object, OclRoot>();

	/**
	 * <p>
	 * Pritvate constructor for singleton pattern.
	 * </p>
	 */
	private JavaStandardLibraryFactory() {

		/* No implementation required. */
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.factory.
	 * IStandardLibraryFactory#createOclBag(java.util.List)
	 */
	public <T extends OclRoot> OclBag<T> createOclBag(List<T> elements) {

		return new JavaOclBag<T>(elements);
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.factory.
	 * IStandardLibraryFactory#createOclBoolean(boolean)
	 */
	public OclBoolean createOclBoolean(boolean value) {

		return JavaOclBoolean.getInstance(value);
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.factory.
	 * IStandardLibraryFactory#createOclEnumLiteral(java.lang.Enum)
	 */
	public OclEnumLiteral createOclEnumLiteral(Enum<?> value) {

		return new JavaOclEnumLiteral(value);
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.factory.
	 * IStandardLibraryFactory#createOclInteger(int)
	 */
	public OclInteger createOclInteger(int value) {

		return new JavaOclInteger(value);
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.factory.
	 * IStandardLibraryFactory#createOclInvalid(java.lang.String)
	 */
	public OclInvalid createOclInvalid(String reason) {

		OclInvalid result;

		result = JavaOclInvalid.getInstance();
		result.setUndefinedreason(reason);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.factory.
	 * IStandardLibraryFactory#createOclObject(java.lang.Object)
	 */
	public OclObject createOclObject(Object object) {

		OclObject result;

		/* Eventually use a cached result. */
		result = (OclObject) this.myCachedAdaptedObjects.get(object);

		/* Else create a new object. */
		if (result == null) {
			result = new JavaOclObject(object);

			/* Cache the created object. */
			this.myCachedAdaptedObjects.put(object, result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.factory.
	 * IStandardLibraryFactory#createOclOrderedSet(java.util.List)
	 */
	public <T extends OclRoot> OclOrderedSet<T> createOclOrderedSet(
			List<T> elements) {

		OclOrderedSet<T> result;
		List<T> elementList;

		elementList = new ArrayList<T>();

		for (T anElment : elements) {
			if (!elementList.contains(anElment)) {
				elementList.add(anElment);
			}
		}

		result = new JavaOclOrderedSet<T>(elementList);

		return result;
	}

	public OclRoot createOclRoot(Object object) {

		OclRoot result;

		if (object == null) {
			result = JavaOclVoid.getInstance();
		}

		else if (object instanceof Integer) {
			result = this.createOclInteger((Integer) object);
		}

		else if (object instanceof Float) {
			result = this.createOclReal((Float) object);
		}

		else if (object instanceof Boolean) {
			result = this.createOclBoolean((Boolean) object);
		}

		else if (object instanceof String) {
			result = this.createOclString((String) object);
		}

		else if (object.getClass().isEnum()) {
			result = this.createOclEnumLiteral((Enum<?>) object);
		}

		/*
		 * If the property is a collection, each element must be adapted for itself.
		 */
		else if (object instanceof Collection<?>) {

			Collection<?> aCollection;
			List<OclRoot> elementList;

			aCollection = (Collection<?>) object;
			elementList = new ArrayList<OclRoot>();

			for (Object anElement : aCollection) {

				OclRoot adaptedElement;

				/* Adapt anElement to OclRoot. */
				adaptedElement = this.createOclRoot(anElement);

				elementList.add(adaptedElement);
			}

			result = new JavaOclBag<OclRoot>(elementList);
		}

		else if (object instanceof OclRoot) {
			result = (OclRoot) object;
		}

		else {
			result = this.createOclObject(object);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.factory.
	 * IStandardLibraryFactory#createOclReal(java.lang.Float)
	 */
	public OclReal createOclReal(Float value) {

		return new JavaOclReal(value);
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.factory.
	 * IStandardLibraryFactory#createOclSequence(java.util.List)
	 */
	public <T extends OclRoot> OclSequence<T> createOclSequence(List<T> elements) {

		return new JavaOclSequence<T>(elements);
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.factory.
	 * IStandardLibraryFactory#createOclSet(java.util.List)
	 */
	public <T extends OclRoot> OclSet<T> createOclSet(List<T> elements) {

		return new JavaOclSet<T>(new HashSet<T>(elements));
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.factory.
	 * IStandardLibraryFactory#createOclString(java.lang.String)
	 */
	public OclString createOclString(String value) {

		return new JavaOclString(value);
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.factory.
	 * IStandardLibraryFactory#createOclTuple(java.util.List, java.util.List)
	 */
	public OclTuple createOclTuple(List<String> names, List<OclRoot> values) {

		OclTuple result;
		Map<String, OclRoot> elements;

		elements = new HashMap<String, OclRoot>();

		for (int index = 0; index < Math.min(names.size(), values.size()); index++) {
			elements.put(names.get(index), values.get(index));
		}

		result = new JavaOclTuple(elements);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.factory.
	 * IStandardLibraryFactory
	 * #createOclType(tudresden.ocl20.pivot.pivotmodel.Type,
	 * tudresden.ocl20.pivot.modelbus.IModelInstance)
	 */
	public OclType createOclType(Type type, IModelInstance modelInstance) {

		OclType result;

		/*
		 * Check if the referred type is a type of the OCL standard library and try
		 * to adapt it.
		 */
		if (type instanceof CollectionType) {
			result = JavaOclCollectionType.getType(type.getName());
		}

		else if (type instanceof Enumeration) {
			result = JavaOclEnumType.getType(type.getName());
		}

		else if (type instanceof PrimitiveType) {
			result = JavaOclPrimitiveType.getPrimitiveType(type.getName());
		}

		else if (type instanceof TupleType) {
			result = JavaOclTupleType.getType(type.getName());
		}

		/* Else try to get a user defined type from the model. */
		else {
			IModelInstanceTypeObject modelInstanceTypeObject;
			modelInstanceTypeObject = modelInstance.findModelTypeObject(type);

			if (modelInstanceTypeObject != null) {
				result = new JavaOclType(modelInstanceTypeObject.getAdaptedType());
			}

			else {
				result = null;
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.factory.
	 * IStandardLibraryFactory
	 * #createOclUndefined(tudresden.ocl20.pivot.pivotmodel.Type)
	 */
	public OclRoot createOclUndefined(Type type, String reason) {

		OclRoot result;

		result = null;

		/* Check if the given Type is a primitive type. */
		if (type instanceof PrimitiveType) {
			PrimitiveType primitiveType;
			PrimitiveTypeKind primitiveTypeKind;
			primitiveType = (PrimitiveType) type;
			primitiveTypeKind = primitiveType.getKind();

			if (primitiveTypeKind.equals(PrimitiveTypeKind.BOOLEAN)) {
				result = JavaOclBoolean.getInstance(null);
			}

			else if (primitiveTypeKind.equals(PrimitiveTypeKind.INTEGER)) {
				result = new JavaOclInteger(null);
			}

			else if (primitiveTypeKind.equals(PrimitiveTypeKind.REAL)) {
				result = new JavaOclReal(null);
			}

			else if (primitiveTypeKind.equals(PrimitiveTypeKind.STRING)) {
				result = new JavaOclString(null);
			}

			else if (primitiveTypeKind.equals(PrimitiveTypeKind.VOID)) {
				result = JavaOclVoid.getInstance();
			}

			else {
				result = null;
			}
		}

		/* Check if the given Type is an enumeration. */
		else if (type instanceof Enumeration) {
			result = new JavaOclEnumLiteral(null);
		}

		/* If no result has been created yet, create a JavaOclObject. */
		if (result == null) {
			result = new JavaOclObject(null);
		}

		result.setUndefinedreason(reason);

		return result;
	}
}