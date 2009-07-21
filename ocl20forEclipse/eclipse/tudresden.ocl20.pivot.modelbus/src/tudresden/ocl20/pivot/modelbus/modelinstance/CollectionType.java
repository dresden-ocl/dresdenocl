/*
Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the Model Bus Plug-in of Dresden OCL2 for Eclipse.

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
package tudresden.ocl20.pivot.modelbus.modelinstance;

import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType;

/**
 * <p>
 * A {@link PrimitiveType} that represents the {@link Type} for
 * {@link IModelInstanceCollection}s.
 * </p>
 * 
 * @author Claas Wilke
 * 
 */
public class CollectionType extends AbstractPrimitiveType {

	/** The only instance of {@link CollectionType}. */
	public static final CollectionType INSTANCE = new CollectionType();

	/**
	 * <p>
	 * Private constructor for singleton pattern.
	 * </p>
	 */
	private CollectionType() {

		/* Remains empty. */
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType#getKind()
	 */
	public PrimitiveTypeKind getKind() {

		return PrimitiveTypeKind.UNKNOWN;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType#getName()
	 */
	public String getName() {

		return CollectionType.class.getSimpleName();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType#getNamespace()
	 */
	public Namespace getNamespace() {

		return null;
	}
}