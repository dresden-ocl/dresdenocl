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
package tudresden.ocl20.pivot.tools.codegen.code.impl;

import tudresden.ocl20.pivot.tools.codegen.code.ITransformedCode;
import tudresden.ocl20.pivot.tools.codegen.code.ITransformedType;

/**
 * <p>
 * Implements the interface {@link ITransformedCode}.
 * </p>
 * 
 * <p>
 * Represents a Type of the target language transformed during code
 * transformation.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TransformedTypeImpl implements ITransformedType {

	protected String typeName;

	protected ITransformedType genericType;

	/**
	 * <p>
	 * Creates a new {@link TransformedTypeImpl} with a given Type's name.
	 * </p>
	 * 
	 * @param aTypeName
	 *            The Type which was generated.
	 */
	public TransformedTypeImpl(String aTypeName) {
		this.typeName = aTypeName;
		this.genericType = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.ocl22code.IGeneratedType#getType()
	 */
	public String getTypeName() {
		return this.typeName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.ocl22code.IGeneratedType#getGenericResultType()
	 */
	public ITransformedType getGenericType() {
		return this.genericType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.ocl22code.IGeneratedType#isResultTypeCollection()
	 */
	public boolean isGenericType() {
		return (this.genericType != null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.ocl22code.IGeneratedType#setType(java.lang.String)
	 */
	public void setTypeName(String aTypeName) {
		this.typeName = aTypeName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.ocl22code.IGeneratedType#setGenericResultType(tudresden
	 * .ocl20.pivot.ocl22code.IGeneratedType)
	 */
	public void setGenericType(ITransformedType aType) {

		this.genericType = aType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {

		String result;

		result = this.typeName;

		if (this.isGenericType()
				&& this.genericType.toString().length() > 0) {

			result += "<";
			result += this.getGenericType().toString();
			result += ">";
		}

		return result;
	}

}
