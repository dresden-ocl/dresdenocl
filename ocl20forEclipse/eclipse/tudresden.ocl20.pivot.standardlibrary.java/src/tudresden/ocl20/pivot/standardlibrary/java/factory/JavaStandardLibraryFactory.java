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
package tudresden.ocl20.pivot.standardlibrary.java.factory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.eclipse.emf.common.util.UniqueEList;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclEnumLiteral;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclModelInstanceObject;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclTuple;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.factory.IStandardLibraryFactory;
import tudresden.ocl20.pivot.essentialocl.types.BagType;
import tudresden.ocl20.pivot.essentialocl.types.CollectionType;
import tudresden.ocl20.pivot.essentialocl.types.OrderedSetType;
import tudresden.ocl20.pivot.essentialocl.types.SequenceType;
import tudresden.ocl20.pivot.essentialocl.types.SetType;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceBoolean;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceEnumerationLiteral;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceInteger;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceReal;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceString;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceTuple;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.BasisJavaModelInstanceFactory;
import tudresden.ocl20.pivot.modelbus.util.OclCollectionTypeKind;
import tudresden.ocl20.pivot.pivotmodel.Enumeration;
import tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.standardlibrary.java.exceptions.InvalidException;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclBag;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclBoolean;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclEnumLiteral;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclInteger;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclModelInstanceObject;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclOrderedSet;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclReal;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclSequence;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclSet;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclString;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclTuple;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclVoid;

// FIXME Michael: remove factory methods that get an IMICollection as parameter

/**
 * <p>
 * The {@link JavaStandardLibraryFactory} provides methods to create
 * {@link OclAny} required during OCL interpretation.
 * </p>
 */
public class JavaStandardLibraryFactory implements IStandardLibraryFactory {

	private static JavaStandardLibraryFactory instance;

	private static JavaStandardLibraryFactory getInstance() {

		if (instance == null)
			instance = new JavaStandardLibraryFactory();
		return instance;
	}

	/** The singleton instance of the {@link JavaStandardLibraryFactory}. */
	public static JavaStandardLibraryFactory INSTANCE = getInstance();

	private BasisJavaModelInstanceFactory basisJavaModelInstanceFactory =
			new BasisJavaModelInstanceFactory();

	// TODO Michael: Caching for IModelInstanceElements is done; for anything
	// else?
	/**
	 * Contains the already adapted {@link OclAny} identified by their adapted
	 * {@link Object}. <strong>This is a {@link WeakHashMap}! If an {@link Object}
	 * is disposed, its adapter can also be disposed.</strong>
	 */
	private Map<Object, OclAny> myCachedAdaptedObjects =
			new WeakHashMap<Object, OclAny>();

	/**
	 * Private Singleton constructor.
	 */
	private JavaStandardLibraryFactory() {

	}

	@SuppressWarnings("unchecked")
	public OclAny createOclAny(final IModelInstanceElement modelInstanceElement) {

		OclAny result;

		if (modelInstanceElement == null) {
			throw new IllegalArgumentException(
					"Cannot create OclAny with \'null\' as given IModelInstanceElement.");
		}

		else if (modelInstanceElement instanceof IModelInstanceInteger) {
			result = new JavaOclInteger((IModelInstanceInteger) modelInstanceElement);
		}

		else if (modelInstanceElement instanceof IModelInstanceReal) {
			result = new JavaOclReal((IModelInstanceReal) modelInstanceElement);
		}

		else if (modelInstanceElement instanceof IModelInstanceBoolean) {
			result =
					JavaOclBoolean
							.getInstance(((IModelInstanceBoolean) modelInstanceElement)
									.getBoolean());
		}

		else if (modelInstanceElement instanceof IModelInstanceString) {
			result = new JavaOclString((IModelInstanceString) modelInstanceElement);
		}

		else if (modelInstanceElement instanceof IModelInstanceEnumerationLiteral) {
			result =
					new JavaOclEnumLiteral(
							(IModelInstanceEnumerationLiteral) modelInstanceElement);
		}

		else if (modelInstanceElement instanceof IModelInstanceCollection<?>) {

			IModelInstanceCollection<IModelInstanceElement> collection =
					(IModelInstanceCollection<IModelInstanceElement>) modelInstanceElement;

			result = this.createOclCollection(collection);
		}

		else if (modelInstanceElement instanceof IModelInstanceObject) {
			result =
					new JavaOclModelInstanceObject(
							(IModelInstanceObject) modelInstanceElement);
		}

		else {
			throw new IllegalArgumentException(
					"Cannot create an OclAny with the given IModelInstanceElement "
							+ modelInstanceElement.getName());
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.factory.
	 * IStandardLibraryFactory#createOclBag(java.util.List)
	 */
	public <T extends OclAny> OclBag<T> createOclBag(final List<?> elements) {

		List<IModelInstanceElement> imiElements;
		imiElements = new ArrayList<IModelInstanceElement>();

		for (Object element : elements) {

			if (element instanceof IModelInstanceElement) {

				imiElements.add((IModelInstanceElement) element);
			}
			else if (element instanceof OclAny) {

				imiElements.add(((OclAny) element).getModelInstanceElement());
			}
			else {
				throw new IllegalArgumentException("Cannot create JavaOclSet for "
						+ elements);
			}
		}

		IModelInstanceCollection<IModelInstanceElement> imiCollection =
				basisJavaModelInstanceFactory.createModelInstanceCollection(
						imiElements, OclCollectionTypeKind.BAG);

		return new JavaOclBag<T>(imiCollection);
	}

	public <T extends OclAny> OclBag<T> createOclBag(
			IModelInstanceCollection<IModelInstanceElement> elements) {

		OclBag<T> result;

		if (elements.isOrdered() || elements.isUnique()) {
			Collection<IModelInstanceElement> bag =
					new UniqueEList<IModelInstanceElement>(elements.getCollection());

			IModelInstanceCollection<IModelInstanceElement> imiResult =
					basisJavaModelInstanceFactory.createModelInstanceCollection(bag,
							OclCollectionTypeKind.BAG);

			result = new JavaOclBag<T>(imiResult);
		}
		else {
			result = new JavaOclBag<T>(elements);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.factory.
	 * IStandardLibraryFactory#createOclBoolean(java.lang.Boolean)
	 */
	public OclBoolean createOclBoolean(final Boolean value) {

		return JavaOclBoolean.getInstance(value);
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.factory.
	 * IStandardLibraryFactory
	 * #createOclBoolean(tudresden.ocl20.pivot.modelbus.modelinstance
	 * .types.IModelInstanceBoolean)
	 */
	public OclBoolean createOclBoolean(final IModelInstanceBoolean value) {

		return JavaOclBoolean.getInstance(value.getBoolean());
	}

	protected OclCollection<OclAny> createOclCollection(
			IModelInstanceCollection<IModelInstanceElement> imiCollection) {

		OclCollection<OclAny> result;

		if (imiCollection.isOrdered()) {
			if (imiCollection.isUnique()) {
				result = new JavaOclOrderedSet<OclAny>(imiCollection);
			}
			else {
				result = new JavaOclSequence<OclAny>(imiCollection);
			}
		}
		else { // not ordered
			if (imiCollection.isUnique()) {
				result = new JavaOclSet<OclAny>(imiCollection);
			}
			else {
				result = new JavaOclBag<OclAny>(imiCollection);
			}
		}

		return result;

	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.factory.
	 * IStandardLibraryFactory#createOclEnumLiteral(java.lang.Enum)
	 */
	public OclEnumLiteral createOclEnumLiteral(final EnumerationLiteral value) {

		IModelInstanceEnumerationLiteral imiEnumLiteral =
				BasisJavaModelInstanceFactory
						.createModelInstanceEnumerationLiteral(value);

		return new JavaOclEnumLiteral(imiEnumLiteral);
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.factory.
	 * IStandardLibraryFactory
	 * #createOclEnumLiteral(tudresden.ocl20.pivot.modelbus.
	 * modelinstance.types.IModelInstanceEnumerationLiteral)
	 */
	public OclEnumLiteral createOclEnumLiteral(
			final IModelInstanceEnumerationLiteral value) {

		return new JavaOclEnumLiteral(value);
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.factory.
	 * IStandardLibraryFactory#createOclInteger(java.lang.Long)
	 */
	public OclInteger createOclInteger(final Long value) {

		IModelInstanceInteger imiInteger =
				BasisJavaModelInstanceFactory.createModelInstanceInteger(value);

		return new JavaOclInteger(imiInteger);
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.factory.
	 * IStandardLibraryFactory
	 * #createOclInteger(tudresden.ocl20.pivot.modelbus.modelinstance
	 * .types.IModelInstanceInteger)
	 */
	public OclInteger createOclInteger(final IModelInstanceInteger value) {

		return new JavaOclInteger(value);
	}

	public OclModelInstanceObject createOclModelInstanceObject(
			IModelInstanceObject modelInstanceObject) {

		OclModelInstanceObject result;

		if (myCachedAdaptedObjects.containsKey(modelInstanceObject)) {
			result =
					(OclModelInstanceObject) myCachedAdaptedObjects
							.get(modelInstanceObject);
		}
		else {
			result = new JavaOclModelInstanceObject(modelInstanceObject);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.factory.
	 * IStandardLibraryFactory#createOclOrderedSet(java.util.List)
	 */
	public <T extends OclAny> OclOrderedSet<T> createOclOrderedSet(
			final List<?> elements) {

		List<IModelInstanceElement> imiElements;
		imiElements = new UniqueEList<IModelInstanceElement>();

		for (Object element : elements) {

			if (element instanceof IModelInstanceElement) {

				imiElements.add((IModelInstanceElement) element);
			}
			else if (element instanceof OclAny) {

				imiElements.add(((OclAny) element).getModelInstanceElement());
			}
			else {
				throw new IllegalArgumentException("Cannot create JavaOclSet for "
						+ elements);
			}
		}

		IModelInstanceCollection<IModelInstanceElement> imiCollection =
				basisJavaModelInstanceFactory.createModelInstanceCollection(
						imiElements, OclCollectionTypeKind.ORDEREDSET);

		return new JavaOclOrderedSet<T>(imiCollection);
	}

	public <T extends OclAny> OclOrderedSet<T> createOclOrderedSet(
			IModelInstanceCollection<IModelInstanceElement> elements) {

		OclOrderedSet<T> result;

		if (!elements.isOrdered() || !elements.isUnique()) {
			Collection<IModelInstanceElement> orderedSet =
					new UniqueEList<IModelInstanceElement>(elements.getCollection());

			IModelInstanceCollection<IModelInstanceElement> imiResult =
					basisJavaModelInstanceFactory.createModelInstanceCollection(
							orderedSet, OclCollectionTypeKind.ORDEREDSET);

			result = new JavaOclOrderedSet<T>(imiResult);
		}
		else {
			result = new JavaOclOrderedSet<T>(elements);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.factory.
	 * IStandardLibraryFactory#createOclReal(java.lang.Number)
	 */
	public OclReal createOclReal(final Number value) {

		IModelInstanceReal imiReal =
				BasisJavaModelInstanceFactory.createModelInstanceReal(value);

		return new JavaOclReal(imiReal);
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.factory.
	 * IStandardLibraryFactory
	 * #createOclReal(tudresden.ocl20.pivot.modelbus.modelinstance
	 * .types.IModelInstanceReal)
	 */
	public OclReal createOclReal(final IModelInstanceReal value) {

		return new JavaOclReal(value);
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.factory.
	 * IStandardLibraryFactory#createOclSequence(java.util.List)
	 */
	public <T extends OclAny> OclSequence<T> createOclSequence(
			final List<?> elements) {

		List<IModelInstanceElement> imiElements;
		imiElements = new ArrayList<IModelInstanceElement>();

		for (Object element : elements) {

			if (element instanceof IModelInstanceElement) {

				imiElements.add((IModelInstanceElement) element);
			}
			else if (element instanceof OclAny) {

				imiElements.add(((OclAny) element).getModelInstanceElement());
			}
			else {
				throw new IllegalArgumentException("Cannot create JavaOclSet for "
						+ elements);
			}
		}

		IModelInstanceCollection<IModelInstanceElement> imiCollection =
				basisJavaModelInstanceFactory.createModelInstanceCollection(
						imiElements, OclCollectionTypeKind.SEQUENCE);

		return new JavaOclSequence<T>(imiCollection);
	}

	public <T extends OclAny> OclSequence<T> createOclSequence(
			IModelInstanceCollection<IModelInstanceElement> elements) {

		OclSequence<T> result;

		if (!elements.isOrdered() || elements.isUnique()) {
			Collection<IModelInstanceElement> orderedSet =
					new ArrayList<IModelInstanceElement>(elements.getCollection());

			IModelInstanceCollection<IModelInstanceElement> imiResult =
					basisJavaModelInstanceFactory.createModelInstanceCollection(
							orderedSet, OclCollectionTypeKind.SEQUENCE);

			result = new JavaOclSequence<T>(imiResult);
		}
		else {
			result = new JavaOclSequence<T>(elements);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.factory.
	 * IStandardLibraryFactory#createOclSet(java.util.List)
	 */
	public <T extends OclAny> OclSet<T> createOclSet(final Set<?> elements) {

		Set<IModelInstanceElement> imiElements;
		imiElements = new HashSet<IModelInstanceElement>();

		for (Object element : elements) {

			if (element instanceof IModelInstanceElement) {

				imiElements.add((IModelInstanceElement) element);
			}
			else if (element instanceof OclAny) {

				imiElements.add(((OclAny) element).getModelInstanceElement());
			}
			else {
				throw new IllegalArgumentException("Cannot create JavaOclSet for "
						+ elements);
			}
		}

		IModelInstanceCollection<IModelInstanceElement> imiCollection =
				basisJavaModelInstanceFactory.createModelInstanceCollection(
						imiElements, OclCollectionTypeKind.SET);

		return new JavaOclSet<T>(imiCollection);
	}

	public <T extends OclAny> OclSet<T> createOclSet(
			IModelInstanceCollection<IModelInstanceElement> elements) {

		OclSet<T> result;

		if (!elements.isUnique() || elements.isOrdered()) {
			Collection<IModelInstanceElement> set =
					new HashSet<IModelInstanceElement>(elements.getCollection());

			IModelInstanceCollection<IModelInstanceElement> imiResult =
					basisJavaModelInstanceFactory.createModelInstanceCollection(set,
							OclCollectionTypeKind.SET);

			result = new JavaOclSet<T>(imiResult);
		}
		else {
			result = new JavaOclSet<T>(elements);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.factory.
	 * IStandardLibraryFactory#createOclString(java.lang.String)
	 */
	public OclString createOclString(final String value) {

		IModelInstanceString imiString =
				BasisJavaModelInstanceFactory.createModelInstanceString(value);

		return new JavaOclString(imiString);
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.factory.
	 * IStandardLibraryFactory
	 * #createOclString(tudresden.ocl20.pivot.modelbus.modelinstance
	 * .types.IModelInstanceString)
	 */
	public OclString createOclString(final IModelInstanceString value) {

		return new JavaOclString(value);
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.factory.
	 * IStandardLibraryFactory#createOclTuple(java.util.List, java.util.List)
	 */
	public OclTuple createOclTuple(final List<IModelInstanceString> names,
			final List<IModelInstanceElement> values, Type type) {

		OclTuple result;

		IModelInstanceTuple imiTuple =
				basisJavaModelInstanceFactory.createModelInstanceTuple(names, values,
						type);

		result = new JavaOclTuple(imiTuple);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.factory.
	 * IStandardLibraryFactory
	 * #createOclType(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny)
	 */
	public <T extends OclAny> OclType<T> createOclType(final Type type) {

		OclType<T> result;

		result = new BaseOclType<T>() {

			public Type getType() {

				return type;
			}
		};

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.factory.
	 * IStandardLibraryFactory
	 * #createOclUndefined(tudresden.ocl20.pivot.pivotmodel.Type)
	 */
	public OclAny createOclUndefined(final Type type, final String reason) {

		OclAny result;

		result = null;

		/* Check if the given Type is a primitive type. */
		if (type instanceof PrimitiveType) {
			PrimitiveType primitiveType;
			PrimitiveTypeKind primitiveTypeKind;
			primitiveType = (PrimitiveType) type;
			primitiveTypeKind = primitiveType.getKind();

			if (primitiveTypeKind.equals(PrimitiveTypeKind.BOOLEAN)) {
				result = new JavaOclBoolean(reason);
			}

			else if (primitiveTypeKind.equals(PrimitiveTypeKind.INTEGER)) {
				result = new JavaOclInteger(reason);
			}

			else if (primitiveTypeKind.equals(PrimitiveTypeKind.REAL)) {
				result = new JavaOclReal(reason);
			}

			else if (primitiveTypeKind.equals(PrimitiveTypeKind.STRING)) {
				result = new JavaOclString(reason);
			}

			else if (primitiveTypeKind.equals(PrimitiveTypeKind.VOID)) {
				result = JavaOclVoid.INSTANCE;
			}

			/* unknown primitive type */
			else {
				throw new IllegalArgumentException("Primitive type " + type
						+ " is unknown.");
			}
		}

		/* collection type */
		else if (type instanceof CollectionType) {

			if (type instanceof BagType) {
				result = new JavaOclBag<OclAny>(reason);
			}

			else if (type instanceof OrderedSetType) {
				result = new JavaOclOrderedSet<OclAny>(reason);
			}

			else if (type instanceof SequenceType) {
				result = new JavaOclSequence<OclAny>(reason);
			}

			else if (type instanceof SetType) {
				result = new JavaOclSet<OclAny>(reason);
			}
		}

		/* Check if the given Type is an enumeration. */
		else if (type instanceof Enumeration) {
			result = new JavaOclEnumLiteral(reason);
		}

		/* If no result has been created yet, create a JavaOclObject. */
		if (result == null) {
			result = new JavaOclModelInstanceObject(reason);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.factory.
	 * IStandardLibraryFactory
	 * #createOclInvalid(tudresden.ocl20.pivot.pivotmodel.Type,
	 * java.lang.Throwable)
	 */
	public OclAny createOclInvalid(final Type type, final Throwable invalidReason) {

		OclAny result;

		result = null;

		/* Check if the given Type is a primitive type. */
		if (type instanceof PrimitiveType) {
			PrimitiveType primitiveType;
			PrimitiveTypeKind primitiveTypeKind;
			primitiveType = (PrimitiveType) type;
			primitiveTypeKind = primitiveType.getKind();

			if (primitiveTypeKind.equals(PrimitiveTypeKind.BOOLEAN)) {
				result = new JavaOclBoolean(invalidReason);
			}

			else if (primitiveTypeKind.equals(PrimitiveTypeKind.INTEGER)) {
				result = new JavaOclInteger(invalidReason);
			}

			else if (primitiveTypeKind.equals(PrimitiveTypeKind.REAL)) {
				result = new JavaOclReal(invalidReason);
			}

			else if (primitiveTypeKind.equals(PrimitiveTypeKind.STRING)) {
				result = new JavaOclString(invalidReason);
			}

			else if (primitiveTypeKind.equals(PrimitiveTypeKind.VOID)) {
				result = new JavaOclVoid(invalidReason);
			}

			else {
				throw new IllegalArgumentException("Primitive type " + type
						+ " is unknown.");
			}
		}

		/* collection type */
		else if (type instanceof CollectionType) {

			if (type instanceof BagType) {
				result = new JavaOclBag<OclAny>(invalidReason);
			}

			else if (type instanceof OrderedSetType) {
				result = new JavaOclOrderedSet<OclAny>(invalidReason);
			}

			else if (type instanceof SequenceType) {
				result = new JavaOclSequence<OclAny>(invalidReason);
			}

			else if (type instanceof SetType) {
				result = new JavaOclSet<OclAny>(invalidReason);
			}
		}

		/* Check if the given Type is an enumeration. */
		else if (type instanceof Enumeration) {
			result = new JavaOclEnumLiteral(invalidReason);
		}

		/* If no result has been created yet, create a JavaOclObject. */
		if (result == null) {
			result = new JavaOclModelInstanceObject(invalidReason);
		}

		return result;
	}

	private abstract class BaseOclType<U extends OclAny> implements OclType<U> {

		public <T extends OclAny> OclSet<T> asSet() {

			throw new InvalidException(new UnsupportedOperationException(
					"asSet() is not supported on meta-type OclType"));
		}

		public String getUndefinedreason() {

			return null;
		}

		public OclAny invokeOperation(Operation operation, OclAny... parameters) {

			throw new InvalidException(
					new UnsupportedOperationException(
							"invokeOperation(Operation operation, OclAny... parameters) is not supported on meta-type OclType"));
		}

		/**
		 * Overridden to allow equality tests on OclTypes.
		 */
		public OclBoolean isEqualTo(OclType<OclAny> type2) {

			return createOclBoolean(this.getType().equals(type2.getType()));
		}

		/**
		 * Overridden to allow equality tests on OclTypes.
		 */
		public OclBoolean isNotEqualTo(OclType<OclAny> type2) {

			return isEqualTo(type2).not();
		}

		public OclBoolean oclIsUndefined() {

			return JavaOclBoolean.getInstance(false);
		}

		public <T extends OclAny> T oclAsType(OclType<T> type) {

			throw new InvalidException(new UnsupportedOperationException(
					"oclAsType(OclType<T> type) is not supported on meta-type OclType"));
		}

		public OclBoolean oclIsInvalid() {

			return JavaOclBoolean.getInstance(false);
		}

		public <T extends OclAny> OclBoolean oclIsKindOf(OclType<T> typespec) {

			throw new InvalidException(
					new UnsupportedOperationException(
							"oclIsKindOf(OclType<T> typespec) is not supported on meta-type OclType"));
		}

		public <T extends OclAny> OclBoolean oclIsTypeOf(OclType<T> typespec) {

			throw new InvalidException(
					new UnsupportedOperationException(
							"oclIsTypeOf(OclType<T> typespec) is not supported on meta-type OclType"));
		}

		public OclBoolean isEqualTo(OclAny object2) {

			throw new InvalidException(new UnsupportedOperationException(
					"isEqualTo(OclAny object2) is not supported on meta-type OclType"));
		}

		public OclBoolean isNotEqualTo(OclAny object2) {

			throw new InvalidException(new UnsupportedOperationException(
					"isNotEqualTo(OclAny object2) is not supported on meta-type OclType"));
		}

		public IModelInstanceElement getModelInstanceElement() {

			throw new InvalidException(new UnsupportedOperationException(
					"getModelInstanceElement() is not supported on meta-type OclType"));
		}

		public Throwable getInvalidReason() {

			return null;
		}

	}
}