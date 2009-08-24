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
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * The {@link IStandardLibraryFactory} provides methods to create
 * {@link OclRoot} required during OCL interpretation.
 * </p>
 */
public interface IStandardLibraryFactory {

	/**
	 * <p>
	 * Creates a new {@link OclBag} for a given {@link Collection} of
	 * {@link OclRoot}s as contained elements.
	 * 
	 * @param <T>
	 *          The generic Type of the created {@link OclBag}.
	 * @param elements
	 *          The elements contained in the created {@link OclBag}.
	 * @return The created {@link OclBag}.
	 */
	public <T extends OclRoot> OclBag<T> createOclBag(List<T> elements);

	/**
	 * <p>
	 * Creates a new {@link OclBoolean} instance for a given value.
	 * </p>
	 * 
	 * @param The
	 *          value the new {@link OclBoolean} should have.
	 * @return The new {@link OclBoolean} instance for a given value.
	 */
	public OclBoolean createOclBoolean(boolean value);

	/**
	 * <p>
	 * Creates a new {@link OclEnumLiteral} for a given {@link Enum}.
	 * 
	 * @param values
	 *          The {@link Enum} the {@link OclEnumLiteral} shall be created for.
	 * @return The created {@link OclEnumLiteral}.
	 */
	public OclEnumLiteral createOclEnumLiteral(Enum<?> value);

	/**
	 * <p>
	 * Creates a new {@link OclInteger} instance for a given value.
	 * </p>
	 * 
	 * @param The
	 *          value the new {@link OclInteger} should have.
	 * @return The new {@link OclInteger} instance for a given value.
	 */
	public OclInteger createOclInteger(int value);

	/**
	 * <p>
	 * Creates or returns the {@link OclInvalid} instance.
	 * </p>
	 * 
	 * @param reasons
	 *          The reason, why the expression resulted in {@link OclInvalid}.
	 * @return The {@link OclInvalid} instance.
	 */
	public OclInvalid createOclInvalid(String reason);

	/**
	 * <p>
	 * Creates a new {@link OclObject} for a given {@link Object}.
	 * </p>
	 * 
	 * @param object
	 *          The {@link Object} that shall be adapted.
	 * @return The created {@link OclObject}.
	 */
	public OclObject createOclObject(Object object);

	/**
	 * <p>
	 * Creates a new {@link OclOrderedSet} for a given {@link List} of
	 * {@link OclRoot}s as contained elements.
	 * </p>
	 * 
	 * @param <T>
	 *          The generic Type of the created {@link OclOrderedSet}.
	 * @param elements
	 *          The elements contained in the created {@link OclOrderedSet}.
	 * @return The created {@link OclOrderedSet}.
	 */
	public <T extends OclRoot> OclOrderedSet<T> createOclOrderedSet(
			List<T> elements);

	/**
	 * <p>
	 * Creates a new {@link OlcRoot} for a given {@link Object}.
	 * 
	 * @param elements
	 *          The {@link Object} that shall be adapted to an {@link OclRoot}.
	 * @return The created {@link OclRoot}.
	 */
	public OclRoot createOclRoot(Object object);

	/**
	 * <p>
	 * Creates a new {@link OclReal} instance for a given value.
	 * </p>
	 * 
	 * @param The
	 *          value the new {@link OclReal} should have.
	 * @return The new {@link OclReal} instance for a given value.
	 */
	public OclReal createOclReal(Float value);

	/**
	 * <p>
	 * Creates a new {@link OclSequence} for a given {@link List} of
	 * {@link OclRoot}s as contained elements.
	 * 
	 * @param <T>
	 *          The generic Type of the created {@link OclSequence}.
	 * @param elements
	 *          The elements contained in the created {@link OclSequence}.
	 * @return The created {@link OclSequence}.
	 */
	public <T extends OclRoot> OclSequence<T> createOclSequence(List<T> elements);

	/**
	 * <p>
	 * Creates a new {@link OclSet} for a given {@link List} of {@link OclRoot}s
	 * as contained elements.
	 * 
	 * @param <T>
	 *          The generic Type of the created {@link OclSet}.
	 * @param elements
	 *          The elements contained in the created {@link OclSet}.
	 * @return The created {@link OclSet}.
	 */
	public <T extends OclRoot> OclSet<T> createOclSet(List<T> elements);

	/**
	 * <p>
	 * Creates a new {@link OclString} instance for a given value.
	 * </p>
	 * 
	 * @param The
	 *          value the new {@link OclString} should have.
	 * @return The new {@link OclString} instance for a given value.
	 */
	public OclString createOclString(String value);

	/**
	 * <p>
	 * Creates a new OclTuple for a given {@link List} of {@link String}s as the
	 * elements' names and a given {@link List} of {@link OclRoot}s as the
	 * element's values.
	 * </p>
	 * 
	 * @param names
	 *          The names of the elements as an {@link List} of {@link String}s.
	 * @param values
	 *          The values of the elements as an {@link List} of {@link OclRoot}s.
	 * @return The created {@link OclTuple}.
	 */
	public OclTuple createOclTuple(List<String> names, List<OclRoot> values);

	/**
	 * <p>
	 * Creates or returns an {@link OclType} for a given {@link Type}.
	 * </p>
	 * 
	 * @param type
	 *          The {@link Type} that shall be returned as {@link OclType}.
	 * @param modelInstance
	 *          The {@link IModelInstance} that contains the implementations of
	 *          non-primitive nor collection nor enumeration {@link Type}s.
	 * @return The created {@link OclType}.
	 */
	public OclType createOclType(Type type, IModelInstance modelInstance);

	/**
	 * <p>
	 * Creates an undefined instance of an {@link OclRoot} of the given
	 * {@link Type}.
	 * </p>
	 * 
	 * @param type
	 *          The {@link Type} of the undefined {@link OclRoot} that shall be
	 *          created.
	 * @param reason
	 *          The reason why this {@link OclRoot} is undefined.
	 * @return The creates undefined {@link OclRoot}.
	 */
	public OclRoot createOclUndefined(Type type, String reason);
}