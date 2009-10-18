/*
Copyright (C) 2009 by Claas Wilke (info@claaswilke.de)

This file is part of the OCL Standard Library of Dresden OCL2 for Eclipse.

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
package tudresden.ocl20.pivot.essentialocl.standardlibrary.factory;

import java.util.Collection;
import java.util.List;
import java.util.Set;

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
import tudresden.ocl20.pivot.essentialocl.types.CollectionType;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceBoolean;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceEnumerationLiteral;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceInteger;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceReal;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceString;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.BasisJavaModelInstanceFactory;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.JavaModelInstanceBoolean;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.JavaModelInstanceCollection;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.JavaModelInstanceInteger;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.JavaModelInstanceReal;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.JavaModelInstanceString;
import tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * The {@link IStandardLibraryFactory} provides methods to create {@link OclAny}
 * objects required during OCL interpretation.
 * </p>
 * 
 * <p>
 * All {@link OclAny} types - except {@link OclModelInstanceObject} - can be
 * created inside an OCL expression and therefore offer two versions of factory
 * methods:
 * <ul>
 * <li>The first one takes an {@link IModelInstanceElement} as argument and
 * creates the {@link OclAny} wrapper for it. This is the standard way for
 * elements that are return values of invoked library or model operation calls,
 * i.e. elements that are already adapted, since they are part of the
 * {@link IModelInstance}.</li>
 * <li>The second version takes a special subtype of {@link Object} as argument
 * and creates the {@link OclAny} wrapper for the standard java implementation (
 * {@link BasisJavaModelInstanceFactory}) of {@link IModelInstanceElement}s.
 * This is used for {@link OclAny} objects that are created inside OCL
 * expressions, e.g. <br>
 * <code>context Person<br>
 * Set{self.getName(), self.getFirstName()}->includes("aba")</code><br>
 * where the <code>Set</code> is created by the standard library without
 * accessing the model and therefore no {@link IModelInstanceCollection} exists
 * for that element.</li>
 * </ul>
 * </p>
 * 
 * @author Claas Wilke
 * @author Michael Thiele
 */
public interface IStandardLibraryFactory {

	/**
	 * <p>
	 * Convenience method to create subtypes of {@link OclAny}.
	 * </p>
	 * 
	 * @param modelInstanceElement
	 *          the {@link IModelInstanceElement} that shall be adapted to an
	 *          subtype of {@link OclAny}.
	 * @return the created specific {@link OclAny}.
	 */
	OclAny createOclAny(final IModelInstanceElement modelInstanceElement);

	/**
	 * <p>
	 * Creates a new {@link OclBag} for a given {@link IModelInstanceCollection}.
	 * 
	 * @param <T>
	 *          the generic Type of the created {@link OclBag}.
	 * @param elements
	 *          the {@link IModelInstanceCollection} to be wrapped
	 * @return the created {@link OclBag}.
	 */
	<T extends OclAny> OclBag<T> createOclBag(
			final IModelInstanceCollection<IModelInstanceElement> elements);

	/**
	 * <p>
	 * Creates a new {@link OclBag} for a given {@link Collection}.
	 * 
	 * @param <T>
	 *          The generic Type of the created {@link OclBag}.
	 * @param elements
	 *          the {@link Collection} to be wrapped by the
	 *          {@link JavaModelInstanceCollection} that is created by this method
	 *          and is wrapped by the {@link OclBag} that is created as the return
	 *          value.
	 * @return the created {@link OclBag}.
	 */
	<T extends OclAny> OclBag<T> createOclBag(final List<?> elements);

	/**
	 * <p>
	 * Creates a new {@link OclBoolean} instance for a given
	 * {@link IModelInstanceBoolean}.
	 * </p>
	 * 
	 * @param value
	 *          the {@link IModelInstanceBoolean} to be wrapped
	 * @return the created {@link OclBoolean}.
	 */
	OclBoolean createOclBoolean(final IModelInstanceBoolean value);

	/**
	 * <p>
	 * Creates a new {@link OclBoolean} instance for a given boolean value.
	 * </p>
	 * 
	 * @param value
	 *          the boolean value to be wrapped by the
	 *          {@link JavaModelInstanceBoolean} that is created by this method
	 *          and is wrapped by the {@link OclBoolean} that is created as the
	 *          return value
	 * @return the created {@link OclBoolean}.
	 */
	OclBoolean createOclBoolean(final Boolean value);

	/**
	 * Creates an empty {@link OclCollection} of the given {@link CollectionType}.
	 * 
	 * @param collectionType
	 *          the type of collection to create (Bag, OrderedSet, Sequence, Set).
	 * @return an empty {@link OclCollection} of the given {@link CollectionType}
	 */
	OclCollection<OclAny> createOclCollection(CollectionType collectionType);

	/**
	 * <p>
	 * Creates a new {@link OclEnumLiteral} instance for a given
	 * {@link IModelInstanceEnumerationLiteral}.
	 * </p>
	 * 
	 * @param value
	 *          the {@link IModelInstanceEnumerationLiteral} to be wrapped
	 * @return the created {@link OclEnumLiteral}.
	 */
	OclEnumLiteral createOclEnumLiteral(
			final IModelInstanceEnumerationLiteral value);

	/**
	 * <p>
	 * Creates a new {@link OclEnumLiteral} instance for a given enumeration
	 * literal value.
	 * </p>
	 * 
	 * @param value
	 *          the enumeration literal value to be wrapped by the
	 *          {@link JavaModelInstanceEnumerationLiteral} that is created by
	 *          this method and is wrapped by the {@link OclEnumLiteral} that is
	 *          created as the return value
	 * @return the created {@link OclEnumLiteral}.
	 */
	OclEnumLiteral createOclEnumLiteral(final EnumerationLiteral value);

	/**
	 * <p>
	 * Creates a new {@link OclInteger} instance for a given
	 * {@link IModelInstanceInteger}.
	 * </p>
	 * 
	 * @param value
	 *          the {@link IModelInstanceInteger} to be wrapped
	 * @return the created {@link OclInteger}.
	 */
	OclInteger createOclInteger(final IModelInstanceInteger value);

	/**
	 * <p>
	 * Creates a new {@link OclInteger} instance for a given {@link Long}.
	 * </p>
	 * 
	 * @param value
	 *          the integer value to be wrapped by the
	 *          {@link JavaModelInstanceInteger} that is created by this method
	 *          and is wrapped by the {@link OclInteger} that is created as the
	 *          return value
	 * @return the created {@link OclInteger}.
	 */
	OclInteger createOclInteger(final Long value);

	/**
	 * <p>
	 * Creates a new {@link OclObject} for a given {@link IModelInstanceObject}.
	 * </p>
	 * 
	 * @param modelInstanceObject
	 *          the {@link IModelInstanceObject} that shall be wrapped.
	 * @return the created {@link OclObject}.
	 */
	OclModelInstanceObject createOclModelInstanceObject(
			final IModelInstanceObject modelInstanceObject);

	/**
	 * <p>
	 * Creates a new {@link OclOrderedSet} for a given
	 * {@link IModelInstanceCollection}.
	 * 
	 * @param <T>
	 *          the generic Type of the created {@link OclOrderedSet}.
	 * @param elements
	 *          the {@link IModelInstanceCollection} to be wrapped
	 * @return the created {@link OclOrderedSet}.
	 */
	<T extends OclAny> OclOrderedSet<T> createOclOrderedSet(
			final IModelInstanceCollection<IModelInstanceElement> elements);

	/**
	 * <p>
	 * Creates a new {@link OclOrderedSet} for a given {@link List}.
	 * 
	 * @param <T>
	 *          The generic Type of the created {@link OclOrderedSet}.
	 * @param elements
	 *          the {@link List} to be wrapped by the
	 *          {@link JavaModelInstanceCollection} that is created by this method
	 *          and is wrapped by the {@link OclOrderedSet} that is created as the
	 *          return value.
	 * @return the created {@link OclOrderedSet}.
	 */
	<T extends OclAny> OclOrderedSet<T> createOclOrderedSet(final List<?> elements);

	/**
	 * <p>
	 * Creates a new {@link OclReal} instance for a given
	 * {@link IModelInstanceReal}.
	 * </p>
	 * 
	 * @param value
	 *          the {@link IModelInstanceReal} to be wrapped
	 * @return the created {@link OclReal}.
	 */
	OclReal createOclReal(final IModelInstanceReal value);

	/**
	 * <p>
	 * Creates a new {@link OclReal} instance for a given {@link Number}.
	 * </p>
	 * 
	 * @param value
	 *          the real value to be wrapped by the {@link JavaModelInstanceReal}
	 *          that is created by this method and is wrapped by the
	 *          {@link OclReal} that is created as the return value
	 * @return the created {@link OclReal}.
	 */
	OclReal createOclReal(final Number value);

	/**
	 * <p>
	 * Creates a new {@link OclSequence} for a given
	 * {@link IModelInstanceCollection}.
	 * 
	 * @param <T>
	 *          the generic Type of the created {@link OclSequence}.
	 * @param elements
	 *          the {@link IModelInstanceCollection} to be wrapped
	 * @return the created {@link OclSequence}.
	 */
	<T extends OclAny> OclSequence<T> createOclSequence(
			final IModelInstanceCollection<IModelInstanceElement> elements);

	/**
	 * <p>
	 * Creates a new {@link OclSequence} for a given {@link List}.
	 * 
	 * @param <T>
	 *          The generic Type of the created {@link OclSequence}.
	 * @param elements
	 *          the {@link List} to be wrapped by the
	 *          {@link JavaModelInstanceCollection} that is created by this method
	 *          and is wrapped by the {@link OclSequence} that is created as the
	 *          return value.
	 * @return the created {@link OclSequence}.
	 */
	<T extends OclAny> OclSequence<T> createOclSequence(final List<?> elements);

	/**
	 * <p>
	 * Creates a new {@link OclSet} for a given {@link IModelInstanceCollection}.
	 * 
	 * @param <T>
	 *          the generic Type of the created {@link OclSet}.
	 * @param elements
	 *          the {@link IModelInstanceCollection} to be wrapped
	 * @return the created {@link OclSet}.
	 */
	<T extends OclAny> OclSet<T> createOclSet(
			final IModelInstanceCollection<IModelInstanceElement> elements);

	/**
	 * <p>
	 * Creates a new {@link OclSet} for a given {@link Set}.
	 * 
	 * @param <T>
	 *          The generic Type of the created {@link OclSet}.
	 * @param elements
	 *          the {@link List} to be wrapped by the
	 *          {@link JavaModelInstanceCollection} that is created by this method
	 *          and is wrapped by the {@link OclSet} that is created as the return
	 *          value.
	 * @return the created {@link OclSet}.
	 */
	<T extends OclAny> OclSet<T> createOclSet(final Set<?> elements);

	/**
	 * <p>
	 * Creates a new {@link OclString} instance for a given
	 * {@link IModelInstanceString}.
	 * </p>
	 * 
	 * @param value
	 *          the {@link IModelInstanceString} to be wrapped
	 * @return the created {@link OclString}.
	 */
	OclString createOclString(final IModelInstanceString value);

	/**
	 * <p>
	 * Creates a new {@link OclString} instance for a given {@link String}.
	 * </p>
	 * 
	 * @param value
	 *          the string value to be wrapped by the
	 *          {@link JavaModelInstanceString} that is created by this method and
	 *          is wrapped by the {@link OclString} that is created as the return
	 *          value
	 * @return the created {@link OclString}.
	 */
	OclString createOclString(final String value);

	/**
	 * <p>
	 * Creates a new OclTuple for a given {@link List} of
	 * {@link IModelInstanceString}s as the elements' names and a given
	 * {@link List} of {@link IModelInstanceElement}s as the element's values.
	 * </p>
	 * 
	 * <p>
	 * <strong>Note:</strong> Because of type erasure, use
	 * {@link #createOclTupleObject(List, List)
	 * createOclTuple<strong>Object</strong>(List, List)} for tuples that are
	 * created inside OCL expressions.
	 * </p>
	 * 
	 * @param names
	 *          the names of the elements as an {@link List} of
	 *          {@link IModelInstanceString}s.
	 * @param values
	 *          the values of the elements as an {@link List} of
	 *          {@link IModelInstanceElement}s.
	 * @return the created {@link OclTuple}.
	 */
	OclTuple createOclTuple(final List<IModelInstanceString> names,
			final List<IModelInstanceElement> values, Type type);

	/**
	 * <p>
	 * Creates or returns an {@link OclType} for a given {@link Type} and
	 * {@link OclAny}.
	 * </p>
	 * 
	 * @param type
	 *          The {@link Type} this {@link OclType} should wrap.
	 * @param oclAny
	 *          The {@link OclAny} for what the {@link OclType} should be
	 *          returned.
	 * @return The created {@link OclType}.
	 */
	public <T extends OclAny> OclType<T> createOclType(final Type type);

	/**
	 * <p>
	 * Creates an undefined instance of an {@link OclAny} of the given
	 * {@link Type}.
	 * </p>
	 * 
	 * @param type
	 *          The {@link Type} of the undefined {@link OclAny} that shall be
	 *          created.
	 * @param reason
	 *          The reason why this {@link OclAny} is undefined.
	 * @return The created undefined {@link OclAny}.
	 */
	OclAny createOclUndefined(final Type type, final String reason);

	/**
	 * <p>
	 * Creates an invalid instance of an {@link OclAny} of the given {@link Type}.
	 * </p>
	 * 
	 * @param type
	 *          The {@link Type} of the undefined {@link OclAny} that shall be
	 *          created.
	 * @param cause
	 *          The {@link Throwable} that caused this to be invalid
	 * @return The created invalid {@link OclAny}.
	 */
	OclAny createOclInvalid(final Type type, final Throwable cause);
}