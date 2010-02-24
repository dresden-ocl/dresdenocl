/*
Copyright (C) 2010 by Claas Wilke (claaswilke@gmx.net)

This file is part of the Java Model Instance implementation of Dresden OCL2
for Eclipse.

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
package tudresden.ocl20.pivot.modelbus.modelinstance.types.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import tudresden.ocl20.pivot.modelbus.model.IModel;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractType;

/**
 * <p>
 * A {@link ComplexType} can encapsulate multiple {@link Type}s. This is
 * necessary, if an {@link IModelInstanceObject} implements multiple
 * {@link Type}s that are defined in its {@link IModel} but its own {@link Type}
 * is not part of the {@link IModel}.
 * </p>
 * 
 * @author Claas Wilke
 */
public final class ComplexType extends AbstractType {

	/** The {@link Type}s this {@link ComplexType} implements. */
	private Set<Type> myTypes;

	/** The name of this Type. */
	private String myName = null;

	/**
	 * <p>
	 * Creates a new {@link ComplexType} that implements a {@link Set} of given
	 * {@link Type}s.
	 * </p>
	 * 
	 * @param types
	 *          The {@link Type}s this {@link ComplexType} implements.
	 *          <strong>Must contain at least one {@link Type}!</strong>
	 */
	public ComplexType(Set<Type> types) {

		if (types == null) {
			throw new IllegalArgumentException(
					"The parameter 'types' must not be null or empty.");
		}
		// no else.

		this.myTypes = types;
	}

	@Override
	public String getName() {

		/* Probably compute the name from all contained types. */
		if (this.myName == null) {
			List<Type> types;
			types = this.getSuperTypeImpl();

			Collections.sort(types, new Comparator<Type>() {

				public int compare(Type first, Type second) {

					return first.getName().compareTo(second.getName());
				}
			});

			StringBuffer nameBuffer;
			nameBuffer = new StringBuffer();

			for (Type type : types) {
				nameBuffer.append(type.getName());
			}
			// end for.

			this.myName = nameBuffer.toString();
		}
		// no else.

		return this.myName;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getNamespace()
	 */
	@Override
	public Namespace getNamespace() {

		/* FIXME Claas: Which namespace shall a complex type have? */
		// throw new
		// IllegalAccessError("A ComplexType does not have a name space.");
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getOwnedOperationImpl()
	 */
	@Override
	protected List<Operation> getOwnedOperationImpl() {

		/* A ComplexType does not define own operations. */
		return Collections.emptyList();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getOwnedPropertyImpl()
	 */
	@Override
	protected List<Property> getOwnedPropertyImpl() {

		/* A ComplexType does not define own properties. */
		return Collections.emptyList();
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getSuperTypeImpl()
	 */
	@Override
	protected List<Type> getSuperTypeImpl() {

		return new ArrayList<Type>(this.myTypes);
	}

	/**
	 * <p>
	 * Returns the {@link Type}s this {@link ComplexType} implements.
	 * </p>
	 * 
	 * @return The {@link Type}s this {@link ComplexType} implements.
	 */
	public Set<Type> getImplementedTypes() {

		return this.myTypes;
	}
}