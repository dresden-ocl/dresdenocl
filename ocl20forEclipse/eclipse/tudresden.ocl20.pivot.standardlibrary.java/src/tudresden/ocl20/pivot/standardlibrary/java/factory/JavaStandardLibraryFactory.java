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
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
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
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.TypeNotFoundInModelException;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceBoolean;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceEnumerationLiteral;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceInteger;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceReal;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceString;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.BasisJavaModelInstanceFactory;
import tudresden.ocl20.pivot.modelbus.util.OclCollectionTypeKind;
import tudresden.ocl20.pivot.pivotmodel.Enumeration;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.standardlibrary.java.exceptions.InvalidException;
import tudresden.ocl20.pivot.standardlibrary.java.exceptions.UndefinedException;
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

/**
 * <p>
 * The {@link JavaStandardLibraryFactory} provides methods to create
 * {@link OclAny} required during OCL interpretation.
 * </p>
 */
public class JavaStandardLibraryFactory implements IStandardLibraryFactory {

	/** The singleton instance of the {@link JavaStandardLibraryFactory}. */
	public static JavaStandardLibraryFactory INSTANCE =
			new JavaStandardLibraryFactory();

	private BasisJavaModelInstanceFactory modelInstanceFactory =
			new BasisJavaModelInstanceFactory();

	// TODO Michael: Caching!
	/**
	 * Contains the already adapted {@link OclAny} identified by their adapted
	 * {@link Object}. <strong>This is a {@link WeakHashMap}! If an {@link Object}
	 * is disposed, its adapter can also be disposed.</strong>
	 */
	private Map<Object, OclAny> myCachedAdaptedObjects =
			new WeakHashMap<Object, OclAny>();

	/**
	 * <p>
	 * Pritvate constructor for singleton pattern.
	 * </p>
	 */
	private JavaStandardLibraryFactory() {

		/* No implementation required. */
	}

	@SuppressWarnings("unchecked")
	public OclAny createOclAny(final IModelInstanceElement modelInstanceElement) {

		OclAny result;

		// TODO Michael: OclVoid is handled internally -> what should one do here?
		if (modelInstanceElement == null) {
			throw new UndefinedException(null);
		}

		else if (modelInstanceElement instanceof IModelInstanceInteger) {
			result =
					this.createOclInteger((IModelInstanceInteger) modelInstanceElement);
		}

		else if (modelInstanceElement instanceof IModelInstanceReal) {
			result = this.createOclReal((IModelInstanceReal) modelInstanceElement);
		}

		else if (modelInstanceElement instanceof IModelInstanceBoolean) {
			result =
					this.createOclBoolean((IModelInstanceBoolean) modelInstanceElement);
		}

		else if (modelInstanceElement instanceof IModelInstanceString) {
			result =
					this.createOclString((IModelInstanceString) modelInstanceElement);
		}

		else if (modelInstanceElement instanceof IModelInstanceEnumerationLiteral) {
			result =
					this
							.createOclEnumLiteral((IModelInstanceEnumerationLiteral) modelInstanceElement);
		}

		/*
		 * If the property is a collection, each element must be adapted for itself.
		 */
		else if (modelInstanceElement instanceof IModelInstanceCollection<?>) {

			IModelInstanceCollection<IModelInstanceElement> collection =
					(IModelInstanceCollection<IModelInstanceElement>) modelInstanceElement;

			result = this.createOclCollection(collection);
		}

		else if (modelInstanceElement instanceof IModelInstanceObject) {
			result =
					this
							.createOclModelInstanceObject((IModelInstanceObject) modelInstanceElement);
		}

		else {
			throw new UndefinedException(null);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.factory.
	 * IStandardLibraryFactory#createOclBag(java.util.List)
	 */
	public <T extends OclAny, U> OclBag<T> createOclBag(
			final Collection<U> elements) throws TypeNotFoundInModelException {

		OclBag<T> result;

		List<IModelInstanceElement> imiElements =
				new LinkedList<IModelInstanceElement>();

		for (U element : elements) {
			imiElements.add(modelInstanceFactory.createModelInstanceElement(element));
		}

		IModelInstanceCollection<IModelInstanceElement> imiCollection =
				modelInstanceFactory.createModelInstanceCollection(imiElements,
						OclCollectionTypeKind.BAG);

		result = new JavaOclBag<T>(imiCollection);

		return result;
	}

	public <T extends OclAny> OclBag<T> createOclBag(
			final IModelInstanceCollection<IModelInstanceElement> elements) {

		return new JavaOclBag<T>(elements);
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
	public OclEnumLiteral createOclEnumLiteral(final Enum<?> value) {

		// FIXME Michael: create ModelInstanceEnumLiteral? -> Method superfluous?
		IModelInstanceEnumerationLiteral imiEnumLiteral =
				BasisJavaModelInstanceFactory.createModelInstanceInteger(value);

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

		return new JavaOclModelInstanceObject(modelInstanceObject);
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.factory.
	 * IStandardLibraryFactory#createOclOrderedSet(java.util.List)
	 */
	public <T extends OclAny, U> OclOrderedSet<T> createOclOrderedSet(
			final List<U> elements) throws TypeNotFoundInModelException {

		OclOrderedSet<T> result;

		List<IModelInstanceElement> imiElements =
				new LinkedList<IModelInstanceElement>();

		for (U element : elements) {
			imiElements.add(modelInstanceFactory.createModelInstanceElement(element));
		}

		IModelInstanceCollection<IModelInstanceElement> imiCollection =
				modelInstanceFactory.createModelInstanceCollection(imiElements,
						OclCollectionTypeKind.ORDEREDSET);

		result = new JavaOclOrderedSet<T>(imiCollection);

		return result;
	}

	public <T extends OclAny> OclOrderedSet<T> createOclOrderedSet(
			IModelInstanceCollection<IModelInstanceElement> elements) {

		OclOrderedSet<T> result;

		if (!elements.isOrdered() || !elements.isUnique()) {
			// TODO Michael: copy here or in model instance?
			Collection<IModelInstanceElement> orderedSet =
					new UniqueEList<IModelInstanceElement>(elements.getCollection());

			IModelInstanceCollection<IModelInstanceElement> imiResult =
					modelInstanceFactory.createModelInstanceCollection(orderedSet,
							OclCollectionTypeKind.ORDEREDSET);

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
	public <T extends OclAny, U> OclSequence<T> createOclSequence(
			final List<U> elements) throws TypeNotFoundInModelException {

		OclSequence<T> result;

		List<IModelInstanceElement> imiElements =
				new LinkedList<IModelInstanceElement>();

		for (U element : elements) {
			imiElements.add(modelInstanceFactory.createModelInstanceElement(element));
		}

		IModelInstanceCollection<IModelInstanceElement> imiCollection =
				modelInstanceFactory.createModelInstanceCollection(imiElements,
						OclCollectionTypeKind.SEQUENCE);

		result = new JavaOclSequence<T>(imiCollection);

		return result;
	}

	public <T extends OclAny> OclSequence<T> createOclSequence(
			IModelInstanceCollection<IModelInstanceElement> elements) {

		OclSequence<T> result;

		if (!elements.isOrdered()) {
			// TODO Michael: copy here or in model instance?
			Collection<IModelInstanceElement> orderedSet =
					new ArrayList<IModelInstanceElement>(elements.getCollection());

			IModelInstanceCollection<IModelInstanceElement> imiResult =
					modelInstanceFactory.createModelInstanceCollection(orderedSet,
							OclCollectionTypeKind.SEQUENCE);

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
	public <T extends OclAny, U> OclSet<T> createOclSet(final Set<U> elements)
			throws TypeNotFoundInModelException {

		OclSet<T> result;

		List<IModelInstanceElement> imiElements =
				new LinkedList<IModelInstanceElement>();

		for (U element : elements) {
			imiElements.add(modelInstanceFactory.createModelInstanceElement(element));
		}

		IModelInstanceCollection<IModelInstanceElement> imiCollection =
				modelInstanceFactory.createModelInstanceCollection(imiElements,
						OclCollectionTypeKind.SET);

		result = new JavaOclSet<T>(imiCollection);

		return result;
	}

	public <T extends OclAny> OclSet<T> createOclSet(
			IModelInstanceCollection<IModelInstanceElement> elements) {

		OclSet<T> result;

		if (!elements.isUnique()) {
			// TODO Michael: copy here or in model instance?
			Collection<IModelInstanceElement> orderedSet =
					new HashSet<IModelInstanceElement>(elements.getCollection());

			IModelInstanceCollection<IModelInstanceElement> imiResult =
					modelInstanceFactory.createModelInstanceCollection(orderedSet,
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
			final List<IModelInstanceElement> values) {

		OclTuple result;
		Map<IModelInstanceString, IModelInstanceElement> elements =
				new HashMap<IModelInstanceString, IModelInstanceElement>();

		for (int index = 0; index < Math.min(names.size(), values.size()); index++) {
			elements.put(names.get(index), values.get(index));
		}

		result = new JavaOclTuple(elements);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.factory.
	 * IStandardLibraryFactory#createOclTupleObject(java.util.List,
	 * java.util.List)
	 */
	public OclTuple createOclTupleObject(final List<String> names,
			final List<Object> values) {

		OclTuple result;
		Map<IModelInstanceString, IModelInstanceElement> elements =
				new HashMap<IModelInstanceString, IModelInstanceElement>();

		for (int index = 0; index < Math.min(names.size(), values.size()); index++) {
			IModelInstanceString imiString =
					BasisJavaModelInstanceFactory.createModelInstanceString(names
							.get(index));
			IModelInstanceElement imiElement =
					modelInstanceFactory.createModelInstanceElement(values.get(index));
			elements.put(imiString, imiElement);
		}

		result = new JavaOclTuple(elements);

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
	// FIXME Michael: change method signature; undefinedReason is missing
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
				// TODO Michael: what type does OclVoid have?
				result = JavaOclVoid.getInstance();
			}

			else {
				throw new IllegalArgumentException("Primitive type " + type + " is unknown.");
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
				result = JavaOclVoid.getInstance();
			}

			else {
				throw new IllegalArgumentException("Primitive type " + type + " is unknown.");
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

			throw new UnsupportedOperationException(
					"asSet() is not supported on meta-type OclType");
		}

		public String getUndefinedreason() {

			throw new UnsupportedOperationException(
					"getUndefinedreason() is not supported on meta-type OclType");
		}

		public OclAny invokeOperation(Operation operation, OclAny... parameters) {

			throw new UnsupportedOperationException(
					"invokeOperation(Operation operation, OclAny... parameters) is not supported on meta-type OclType");
		}

		/**
		 * Overridden to allow equality tests on OclTypes.
		 */
		public OclBoolean isEqualTo(OclType<OclAny> type2) {

			return createOclBoolean(this.getType().equals(type2));
		}

		/**
		 * Overridden to allow equality tests on OclTypes.
		 */
		public OclBoolean isNotEqualTo(OclType<OclAny> type2) {

			return isEqualTo(type2).not();
		}

		public OclBoolean oclIsUndefined() {

			throw new UnsupportedOperationException(
					"isOclUndefined() is not supported on meta-type OclType");
		}

		public <T extends OclAny> T oclAsType(OclType<T> type) {

			throw new UnsupportedOperationException(
					"oclAsType(OclType<T> type) is not supported on meta-type OclType");
		}

		public OclBoolean oclIsInvalid() {

			throw new UnsupportedOperationException(
					"oclIsInvalid() is not supported on meta-type OclType");
		}

		public <T extends OclAny> OclBoolean oclIsKindOf(OclType<T> typespec) {

			throw new UnsupportedOperationException(
					"oclIsKindOf(OclType<T> typespec) is not supported on meta-type OclType");
		}

		public <T extends OclAny> OclBoolean oclIsTypeOf(OclType<T> typespec) {

			throw new UnsupportedOperationException(
					"oclIsTypeOf(OclType<T> typespec) is not supported on meta-type OclType");
		}

		public void setUndefinedreason(String undefinedreason) {

			throw new UnsupportedOperationException(
					"setUndefinedreason(String undefinedreason) is not supported on meta-type OclType");
		}

		public OclBoolean isEqualTo(OclAny object2) {

			throw new UnsupportedOperationException(
					"isEqualTo(OclAny object2) is not supported on meta-type OclType");
		}

		public OclBoolean isNotEqualTo(OclAny object2) {

			throw new UnsupportedOperationException(
					"isNotEqualTo(OclAny object2) is not supported on meta-type OclType");
		}

		public IModelInstanceElement getModelInstanceElement() {

			throw new UnsupportedOperationException(
					"getModelInstanceElement() is not supported on meta-type OclType");
		}

		public Throwable getInvalidReason() {

			throw new UnsupportedOperationException(
					"getInvalidReason() is not supported on meta-type OclType");
		}

		public void setInvalidReason(Throwable cause) {

			throw new UnsupportedOperationException(
					"setInvalidReason(Throwable cause) is not supported on meta-type OclType");
		}
	}
}