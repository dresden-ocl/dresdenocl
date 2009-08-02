/*
Copyright (C) 2009 by Michael Thiele and Claas Wilke (info@claaswilke.de)

This file is part of the Java Standard Library of Dresden 
OCL2 for Eclipse.

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

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.apache.log4j.Logger;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclEnumLiteral;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclObject;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.factory.IOclInstanceAdapterFactory;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceBoolean;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceEnumerationLiteral;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceInteger;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceObject;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceReal;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceString;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceTypeObject;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclBag;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclBoolean;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclEnumLiteral;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclInteger;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclOrderedSet;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclReal;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclSequence;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclSet;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclString;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclType;

/**
 * Java implementation for the {@link IOclInstanceAdapterFactory}.
 * 
 * @author Michael Thiele
 * @author Claas Wilke
 */
public class JavaOclInstanceAdapterFactory implements
		IOclInstanceAdapterFactory {

	/** The singleton instance of the {@link JavaOclInstanceAdapterFactory}. */
	public static JavaOclInstanceAdapterFactory INSTANCE =
			new JavaOclInstanceAdapterFactory();

	/** The Logger for this class. */
	private static final Logger LOGGER =
			Logger.getLogger(JavaOclInstanceAdapterFactory.class);

	/**
	 * Contains the already adapted {@link IModelObject}s. <strong>This is a
	 * {@link WeakHashMap}! Adapted objects whose adaptee does not exist anymore
	 * can be disposed</strong></p> .
	 */
	private Map<IModelObject, OclRoot> myCachedAdapters =
			new WeakHashMap<IModelObject, OclRoot>();

	/**
	 * Contains the already adapted {@link IModelInstanceTypeObject}s.
	 * <strong>This is a {@link WeakHashMap}! Adapted objects whose adaptee does
	 * not exist anymore can be disposed</strong></p> .
	 */
	private Map<IModelInstanceTypeObject, OclRoot> myCachedAdaptedTypes =
			new WeakHashMap<IModelInstanceTypeObject, OclRoot>();

	/**
	 * <p>
	 * Private constructor for singleton pattern.
	 * </p>
	 */
	private JavaOclInstanceAdapterFactory() {

		/* Do nothing. */
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.IOclInstanceAdapterFactory
	 * #createOclBoolean(tudresden.ocl20.pivot.modelbus.modelinstance.
	 * IModelInstanceBoolean)
	 */
	public OclBoolean createOclBoolean(IModelInstanceBoolean modelInstanceBoolean) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createOclBoolean(" + modelInstanceBoolean + ") - start");
		}

		OclBoolean result;

		/* Check if the object has already been adapted. */
		result = (OclBoolean) this.myCachedAdapters.get(modelInstanceBoolean);

		/* Else create the adapter. */
		if (result == null) {
			Boolean adaptedBoolean = modelInstanceBoolean.getBoolean();
			result = JavaOclBoolean.getInstance(adaptedBoolean);

			/* Cache the result. */
			this.myCachedAdapters.put(modelInstanceBoolean, result);
		}
		// no else.

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createOclBoolean(" + modelInstanceBoolean
					+ ") - end - result = " + result);
		}

		return result;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.IOclInstanceAdapterFactory
	 * #createOclCollection(tudresden.ocl20.pivot.modelbus.modelinstance.
	 * IModelInstanceCollection)
	 */
	@SuppressWarnings("unchecked")
	public OclCollection<OclRoot> createOclCollection(
			IModelInstanceCollection modelInstanceCollection) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createOclCollection(" + modelInstanceCollection
					+ ") - start");
		}

		OclCollection<OclRoot> result;

		/* Check if the object has already been adapted. */
		result =
				(OclCollection<OclRoot>) this.myCachedAdapters
						.get(modelInstanceCollection);

		/* Else create the adapter. */
		if (result == null) {

			boolean isOrdered = modelInstanceCollection.isOrdered();
			boolean isUnique = modelInstanceCollection.isUnique();

			Collection<OclRoot> containedElements;
			if (!isOrdered && isUnique)
				containedElements = new HashSet<OclRoot>();
			else
				containedElements = new LinkedList<OclRoot>();

			// convert contained elements to OCL instances
			for (IModelObject modelObject : modelInstanceCollection
					.getContainedElements()) {
				containedElements.add(this.createOclRoot(modelObject));
			}

			if (isOrdered && isUnique) {
				result =
						new JavaOclOrderedSet<OclRoot>((List<OclRoot>) containedElements);
			}
			else if (isOrdered && !isUnique) {
				result =
						new JavaOclSequence<OclRoot>((List<OclRoot>) containedElements);
			}
			else if (!isOrdered && isUnique) {
				result = new JavaOclSet<OclRoot>((Set<OclRoot>) containedElements);
			}
			else { // (!isOrdered && !isUnique)
				result = new JavaOclBag<OclRoot>((List<OclRoot>) containedElements);
			}

			/* Cache the result. */
			this.myCachedAdapters.put(modelInstanceCollection, result);
		}
		// no else.

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createOclCollection(" + modelInstanceCollection
					+ ") - end - result = " + result);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.IOclInstanceAdapterFactory
	 * #createOclEnumLiteral(tudresden.ocl20.pivot.modelbus.modelinstance.
	 * IModelInstanceEnumerationLiteral)
	 */
	public OclEnumLiteral createOclEnumLiteral(
			IModelInstanceEnumerationLiteral modelInstanceEnumerationLiteral) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createOclEnumLiteral(" + modelInstanceEnumerationLiteral
					+ ") - start");
		}

		OclEnumLiteral result;

		/* Check if the object has already been adapted. */
		result =
				(OclEnumLiteral) this.myCachedAdapters
						.get(modelInstanceEnumerationLiteral);

		/* Else create the adapter. */
		if (result == null) {
			Enum<?> adaptedEnumLiteral = modelInstanceEnumerationLiteral.getLiteral();
			result = new JavaOclEnumLiteral(adaptedEnumLiteral);

			/* Cache the result. */
			this.myCachedAdapters.put(modelInstanceEnumerationLiteral, result);
		}
		// no else.

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createOclEnumLiteral(" + modelInstanceEnumerationLiteral
					+ ") - end - result = " + result);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.IOclInstanceAdapterFactory
	 * #createOclInteger(tudresden.ocl20.pivot.modelbus.modelinstance.
	 * IModelInstanceInteger)
	 */
	public OclInteger createOclInteger(IModelInstanceInteger modelInstanceInteger) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createOclInteger(" + modelInstanceInteger + ") - start");
		}

		OclInteger result;

		/* Check if the object has already been adapted. */
		result = (OclInteger) this.myCachedAdapters.get(modelInstanceInteger);

		/* Else create the adapter. */
		if (result == null) {
			Long adaptedInteger = modelInstanceInteger.getInteger();
			result = new JavaOclInteger(adaptedInteger.intValue());

			/* Cache the result. */
			this.myCachedAdapters.put(modelInstanceInteger, result);
		}
		// no else.

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createOclInteger(" + modelInstanceInteger
					+ ") - end - result = " + result);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.IOclInstanceAdapterFactory
	 * #createOclObject(tudresden.ocl20.pivot.modelbus.IModelObject)
	 */
	public OclObject createOclObject(IModelInstanceObject modelInstanceObject) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createOclObject(" + modelInstanceObject + ") - start");
		}

		OclObject result;

		/* Check if the object has already been adapted. */
		result = (OclObject) this.myCachedAdapters.get(modelInstanceObject);

		/* Else create the adapter. */
		if (result == null) {
			Object adaptedObject = modelInstanceObject.getAdaptedObject();

			result =
					(OclObject) JavaStandardLibraryFactory.INSTANCE
							.createOclObject(adaptedObject);

			/* Cache the result. */
			this.myCachedAdapters.put(modelInstanceObject, result);
		}
		// no else.

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createOclObject(" + modelInstanceObject
					+ ") - end - result = " + result);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.IOclInstanceAdapterFactory
	 * #
	 * createOclReal(tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceReal
	 * )
	 */
	public OclReal createOclReal(IModelInstanceReal modelInstanceReal) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createOclReal(" + modelInstanceReal + ") - start");
		}

		OclReal result;

		/* Check if the object has already been adapted. */
		result = (OclReal) this.myCachedAdapters.get(modelInstanceReal);

		/* Else create the adapter. */
		if (result == null) {
			Double adaptedReal = modelInstanceReal.getReal();
			result = new JavaOclReal(adaptedReal);

			/* Cache the result. */
			this.myCachedAdapters.put(modelInstanceReal, result);
		}
		// no else.

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createOclReal(" + modelInstanceReal + ") - end - result = "
					+ result);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.IOclInstanceAdapterFactory
	 * #createOclString(tudresden.ocl20.pivot.modelbus.modelinstance.
	 * IModelInstanceString)
	 */
	public OclString createOclString(IModelInstanceString modelInstanceString) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createOclString(" + modelInstanceString + ") - start");
		}

		OclString result;

		/* Check if the object has already been adapted. */
		result = (OclString) this.myCachedAdapters.get(modelInstanceString);

		/* Else create the adapter. */
		if (result == null) {
			String adaptedString = modelInstanceString.getString();
			result = new JavaOclString(adaptedString);

			/* Cache the result. */
			this.myCachedAdapters.put(modelInstanceString, result);
		}
		// no else.

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createOclString(" + modelInstanceString
					+ ") - end - result = " + result);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.factory.
	 * IOclInstanceAdapterFactory
	 * #createOclType(tudresden.ocl20.pivot.modelbus.modelinstance
	 * .IModelInstanceTypeObject)
	 */
	public OclType createOclType(IModelInstanceTypeObject modelInstanceTypeObject) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createOclType(" + modelInstanceTypeObject + ") - start");
		}

		OclType result;

		/* Check if the object has already been adapted. */
		result = (OclType) this.myCachedAdaptedTypes.get(modelInstanceTypeObject);

		/* Else create the adapter. */
		if (result == null) {
			Class<?> adaptedClass;
			adaptedClass = modelInstanceTypeObject.getAdaptedType();

			result = new JavaOclType(adaptedClass);

			/* Cache the result. */
			this.myCachedAdaptedTypes.put(modelInstanceTypeObject, result);
		}
		// no else.

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createOclType(" + modelInstanceTypeObject
					+ ") - end - result = " + result);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.IOclInstanceAdapterFactory
	 * #createOclRoot(tudresden.ocl20.pivot.modelbus.IModelObject)
	 */
	public OclRoot createOclRoot(IModelObject modelInstanceObject) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createOclRoot(" + modelInstanceObject + ") - start");
		}

		OclRoot result;

		/* Check if the object has already been adapted. */
		result = (OclRoot) this.myCachedAdapters.get(modelInstanceObject);

		/* Else create the adapter. */
		if (result == null) {

			if (modelInstanceObject instanceof IModelInstanceBoolean) {
				result = createOclBoolean((IModelInstanceBoolean) modelInstanceObject);
			}
			else if (modelInstanceObject instanceof IModelInstanceCollection) {
				result =
						createOclCollection((IModelInstanceCollection) modelInstanceObject);
			}
			else if (modelInstanceObject instanceof IModelInstanceEnumerationLiteral) {
				result =
						createOclEnumLiteral((IModelInstanceEnumerationLiteral) modelInstanceObject);
			}
			else if (modelInstanceObject instanceof IModelInstanceInteger) {
				result = createOclInteger((IModelInstanceInteger) modelInstanceObject);
			}
			else if (modelInstanceObject instanceof IModelInstanceReal) {
				result = createOclReal((IModelInstanceReal) modelInstanceObject);
			}
			else if (modelInstanceObject instanceof IModelInstanceString) {
				result = createOclString((IModelInstanceString) modelInstanceObject);
			}
			else if (modelInstanceObject instanceof IModelInstanceObject) {
				result = createOclObject((IModelInstanceObject) modelInstanceObject);
			}

			/* Cache the result. */
			this.myCachedAdapters.put(modelInstanceObject, result);
		}
		// no else.

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createOclRoot(" + modelInstanceObject
					+ ") - end - result: " + result);
		}

		return result;
	}

}
