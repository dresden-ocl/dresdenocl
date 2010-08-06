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

/**
 * <p>
 * A Class for transformed code. An {@link TransformedCodeImpl} contains the
 * Transformed code and an Expressions which describes the result of the
 * transformed code (e.g.) an VariableCallExp.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TransformedCodeImpl implements ITransformedCode {

	protected String code;
	protected String resultExp;

	/**
	 * <p>
	 * Creates a new, empty {@link TransformedCodeImpl}.
	 * </p>
	 */
	public TransformedCodeImpl() {

		this.code = "";
		this.resultExp = "";
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.ocl22code.IGeneratedCode#addLogicalCode(java.lang
	 * .String)
	 */
	public void addCode(String someCode) {

		if (someCode.length() > 0) {
			if (this.code.length() > 0) {
				this.code += "\n";
			}
			// no else.

			this.code += someCode;
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.ocl22code.IGeneratedCode#hasMoreThanOneLine()
	 */
	public boolean containsCode() {

		boolean result;

		result = (this.code.length() > 0);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.ocl22code.IGeneratedCode#getGeneratedCode()
	 */
	public String getCode() {

		return this.code;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.ocl22code.IGeneratedCode#getResultVariable()
	 */
	public String getResultExp() {

		return this.resultExp;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.ocl22code.IGeneratedCode#setResultVariable(java
	 * .lang.String)
	 */
	public void setResultExp(String someCode) {

		this.resultExp = someCode;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object anObject) {

		boolean result;

		result = false;

		if (anObject instanceof ITransformedCode) {
			ITransformedCode aCode;

			aCode = (ITransformedCode) anObject;

			result = this.toString().equals(aCode.toString());
		}
		// no else.

		return result;
	}
}
