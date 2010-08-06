/*
Copyright (C) 2008-2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the OCL 2 Java Code Generator of Dresden OCL2 for Eclipse.

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
package tudresden.ocl20.pivot.tools.codegen.code;

/**
 * <p>
 * Represents a Type of the target language transformed during code
 * transformation.
 * </p>
 * 
 * @author Claas Wilke
 */
public interface ITransformedType {

	/**
	 * @return The transformed type represented as a {@link String}.
	 */
	public String getTypeName();

	/**
	 * @param aType
	 *          The Type which was generated.
	 */
	public void setTypeName(String aType);

	/**
	 * @return True if the Type is a {@link CollectionType}.
	 */
	public boolean isGenericType();

	/**
	 * @returns The Generic Type of this {@link ITransformedType} if any exists.
	 */
	public ITransformedType getGenericType();

	/**
	 * <p>
	 * Sets the generic Type of this {@link ITransformedType}.
	 * </p>
	 * 
	 * @param aType
	 *          The generic type this {@link ITransformedType}.
	 */
	public void setGenericType(ITransformedType aType);

}
