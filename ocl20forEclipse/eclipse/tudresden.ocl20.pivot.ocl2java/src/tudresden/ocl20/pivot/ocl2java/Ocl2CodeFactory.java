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
package tudresden.ocl20.pivot.ocl2java;

import tudresden.ocl20.pivot.modelbus.model.IModel;
import tudresden.ocl20.pivot.ocl2java.code.impl.TransformedCodeImpl;
import tudresden.ocl20.pivot.ocl2java.exception.Ocl2CodeException;
import tudresden.ocl20.pivot.ocl2java.internal.Ocl2Java;

/**
 * <p>
 * This class provides methods to create code generators for code generation
 * from loaded {@link IModel}s.
 * 
 * @author Claas Wilke
 */
public class Ocl2CodeFactory {

	private static Ocl2CodeFactory myInstance;

	/**
	 * <p>
	 * A private constructor to enforce the Singleton.
	 * </p>
	 */
	private Ocl2CodeFactory() {

	}

	/**
	 * @return The only instance of {@link Ocl2CodeFactory}.
	 */
	public static Ocl2CodeFactory getInstance() {

		if (myInstance == null) {
			myInstance = new Ocl2CodeFactory();
		}
		// no else.

		return myInstance;
	}

	/**
	 * @return A code generator which generates Java code for loaded OCL
	 *         expressions.
	 * @throws Ocl2CodeException
	 *             Thrown if the initialization of a new Java code generator
	 *             fails.
	 */
	public Ocl2Java createJavaCodeGenerator() throws Ocl2CodeException {

		Ocl2Java result;

		result = new Ocl2Java();

		return result;
	}

	/**
	 * @return A {@link TransformedCodeImpl} which does not contain any code.
	 */
	public TransformedCodeImpl createTransformedCode() {

		TransformedCodeImpl result;

		result = new TransformedCodeImpl();

		return result;
	}

}
