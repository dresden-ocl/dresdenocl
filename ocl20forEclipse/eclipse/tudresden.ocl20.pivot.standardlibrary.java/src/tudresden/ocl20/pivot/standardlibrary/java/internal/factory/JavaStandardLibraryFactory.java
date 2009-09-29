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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclEnumLiteral;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
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
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceReal;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceString;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceTypeObject;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.BasisJavaModelInstanceFactory;
import tudresden.ocl20.pivot.modelbus.util.OclCollectionTypeKind;
import tudresden.ocl20.pivot.pivotmodel.Enumeration;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclBag;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclBoolean;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclEnumLiteral;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclInteger;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclObject;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclReal;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclSequence;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclSet;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclString;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclTuple;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclType;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclVoid;

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

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.factory.
	 * IStandardLibraryFactory#createOclBag(java.util.List)
	 */
	public <T extends OclAny, U> OclBag<T> createOclBag(final List<U> elements)
			throws TypeNotFoundInModelException {

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

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.factory.
	 * IStandardLibraryFactory#createOclOrderedSet(java.util.List)
	 */
	public <T extends OclAny, U> OclOrderedSet<T> createOclOrderedSet(
			final List<U> elements) {

		OclOrderedSet<T> result;

		List<IModelInstanceElement> imiElements =
				new LinkedList<IModelInstanceElement>();

		for (U element : elements) {
			imiElements.add(modelInstanceFactory.createModelInstanceElement(element));
		}

		IModelInstanceCollection<IModelInstanceElement> imiCollection =
				modelInstanceFactory.createModelInstanceCollection(imiElements,
						OclCollectionTypeKind.BAG);

		result = new JavaOrderedSet<T>(imiCollection);

		return result;
	}

	@SuppressWarnings("unchecked")
	public OclAny createOclAny(final IModelInstanceElement modelInstanceElement) {

		OclAny result;

		// TODO Michael: OclVoid is handled internally -> what should one do here?
		if (modelInstanceElement == null) {
			result = JavaOclVoid.getInstance();
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

			result = new JavaOclBag<OclAny>(collection.getCollection());
		}

		// TODO Michael: Should model elements considered as well?
		// else {
		// result = this.createOclObject(modelInstanceElement);
		// }

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
			final List<U> elements) {

		OclSequence<T> result;

		List<IModelInstanceElement> imiElements =
				new LinkedList<IModelInstanceElement>();

		for (U element : elements) {
			imiElements.add(modelInstanceFactory.createModelInstanceElement(element));
		}

		IModelInstanceCollection<IModelInstanceElement> imiCollection =
				modelInstanceFactory.createModelInstanceCollection(imiElements,
						OclCollectionTypeKind.BAG);

		result = new JavaOclSequence<T>(imiCollection);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.factory.
	 * IStandardLibraryFactory#createOclSet(java.util.List)
	 */
	public <T extends OclAny, U> OclSet<T> createOclSet(final List<U> elements) {

		OclSet<T> result;

		List<IModelInstanceElement> imiElements =
				new LinkedList<IModelInstanceElement>();

		for (U element : elements) {
			imiElements.add(modelInstanceFactory.createModelInstanceElement(element));
		}

		IModelInstanceCollection<IModelInstanceElement> imiCollection =
				modelInstanceFactory.createModelInstanceCollection(imiElements,
						OclCollectionTypeKind.BAG);

		result = new JavaOclSet<T>(imiCollection);

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
	public <T extends OclAny> OclType<T> createOclType(final Type type,
			final T oclAny) {

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

		public OclBoolean isOclUndefined() {

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
	}
}