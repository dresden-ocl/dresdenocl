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
import java.util.Set;

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
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclObject;
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

		Boolean adaptedBoolean = modelInstanceBoolean.getBoolean();
		OclBoolean oclBoolean = JavaOclBoolean.getInstance(adaptedBoolean);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createOclBoolean(" + modelInstanceBoolean
					+ ") - end - result = " + oclBoolean);
		}

		return oclBoolean;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.IOclInstanceAdapterFactory
	 * #createOclCollection(tudresden.ocl20.pivot.modelbus.modelinstance.
	 * IModelInstanceCollection)
	 */
	public OclCollection<OclRoot> createOclCollection(
			IModelInstanceCollection modelInstanceCollection) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createOclCollection(" + modelInstanceCollection
					+ ") - start");
		}

		boolean isOrdered = modelInstanceCollection.isOrdered();
		boolean isUnique = modelInstanceCollection.isUnique();

		OclCollection<OclRoot> oclCollection = null;

		Collection<OclRoot> containedElements;
		if (!isOrdered && isUnique)
			containedElements = new HashSet<OclRoot>();
		else
			containedElements = new LinkedList<OclRoot>();

		// convert contained elements to OCL instances
		for (IModelObject modelObject : modelInstanceCollection
				.getContainedElements()) {
			containedElements.add(createOclRoot(modelObject));
		}

		if (isOrdered && isUnique) {
			oclCollection =
					new JavaOclOrderedSet<OclRoot>((List<OclRoot>) containedElements);
		}
		else if (isOrdered && !isUnique) {
			oclCollection =
					new JavaOclSequence<OclRoot>((List<OclRoot>) containedElements);
		}
		else if (!isOrdered && isUnique) {
			oclCollection = new JavaOclSet<OclRoot>((Set<OclRoot>) containedElements);
		}
		else { // (!isOrdered && !isUnique)
			oclCollection =
					new JavaOclBag<OclRoot>((List<OclRoot>) containedElements);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createOclCollection(" + modelInstanceCollection
					+ ") - end - result = " + oclCollection);
		}

		return oclCollection;
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

		Enum<?> adaptedEnumLiteral = modelInstanceEnumerationLiteral.getLiteral();
		OclEnumLiteral oclEnumLiteral = new JavaOclEnumLiteral(adaptedEnumLiteral);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createOclEnumLiteral(" + modelInstanceEnumerationLiteral
					+ ") - end - result = " + oclEnumLiteral);
		}

		return oclEnumLiteral;

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

		Long adaptedInteger = modelInstanceInteger.getInteger();
		OclInteger oclInteger = new JavaOclInteger(adaptedInteger.intValue());

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createOclInteger(" + modelInstanceInteger
					+ ") - end - result = " + oclInteger);
		}

		return oclInteger;
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

		Object adaptedObject = modelInstanceObject.getAdaptedObject();
		OclObject oclObject = new JavaOclObject(adaptedObject);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createOclObject(" + modelInstanceObject
					+ ") - end - result = " + oclObject);
		}

		return oclObject;
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

		Double adaptedReal = modelInstanceReal.getReal();
		OclReal oclReal = new JavaOclReal(adaptedReal);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createOclReal(" + modelInstanceReal + ") - end - result = "
					+ oclReal);
		}

		return oclReal;
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

		String adaptedString = modelInstanceString.getString();
		OclString oclString = new JavaOclString(adaptedString);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createOclString(" + modelInstanceString
					+ ") - end - result = " + oclString);
		}

		return oclString;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.factory.
	 * IOclInstanceAdapterFactory
	 * #createOclType(tudresden.ocl20.pivot.modelbus.modelinstance
	 * .IModelInstanceTypeObject)
	 */
	public OclType createOclType(IModelInstanceTypeObject modelInstanceTypeObject) {

		OclType result;
		Class<?> adaptedClass;

		adaptedClass = modelInstanceTypeObject.getAdaptedType();

		result = new JavaOclType(adaptedClass);

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

		if (modelInstanceObject instanceof IModelInstanceBoolean) {

			OclRoot result =
					createOclBoolean((IModelInstanceBoolean) modelInstanceObject);

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("createOclRoot(" + modelInstanceObject
						+ ") - end - result: " + result);
			}
			return result;
		}
		else if (modelInstanceObject instanceof IModelInstanceCollection) {
			OclRoot result =
					createOclCollection((IModelInstanceCollection) modelInstanceObject);

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("createOclRoot(" + modelInstanceObject
						+ ") - end - result: " + result);
			}
			return result;
		}
		else if (modelInstanceObject instanceof IModelInstanceEnumerationLiteral) {
			OclRoot result =
					createOclEnumLiteral((IModelInstanceEnumerationLiteral) modelInstanceObject);

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("createOclRoot(" + modelInstanceObject
						+ ") - end - result: " + result);
			}
			return result;
		}
		else if (modelInstanceObject instanceof IModelInstanceInteger) {
			OclRoot result =
					createOclInteger((IModelInstanceInteger) modelInstanceObject);

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("createOclRoot(" + modelInstanceObject
						+ ") - end - result: " + result);
			}
			return result;
		}
		else if (modelInstanceObject instanceof IModelInstanceReal) {
			OclRoot result = createOclReal((IModelInstanceReal) modelInstanceObject);

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("createOclRoot(" + modelInstanceObject
						+ ") - end - result: " + result);
			}
			return result;
		}
		else if (modelInstanceObject instanceof IModelInstanceString) {
			OclRoot result =
					createOclString((IModelInstanceString) modelInstanceObject);

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("createOclRoot(" + modelInstanceObject
						+ ") - end - result: " + result);
			}
			return result;
		}
		else if (modelInstanceObject instanceof IModelInstanceObject) {
			OclRoot result =
					createOclObject((IModelInstanceObject) modelInstanceObject);

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("createOclRoot(" + modelInstanceObject
						+ ") - end - result: " + result);
			}
			return result;
		}

		return null;
	}

}
