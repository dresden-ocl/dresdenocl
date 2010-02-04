/*
Copyright (C) 2008-2010 by Claas Wilke (claaswilke@gmx.net)

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
import tudresden.ocl20.pivot.ocl2java.code.ITransformedCode;
import tudresden.ocl20.pivot.ocl2java.code.impl.TransformedCodeImpl;
import tudresden.ocl20.pivot.ocl2java.exception.Ocl22CodeException;
import tudresden.ocl20.pivot.ocl2java.internal.Ocl22Java;
import tudresden.ocl20.pivot.ocl2java.internal.Ocl22JavaSettings;

/**
 * <p>
 * This class provides methods to create Java code generators for code
 * generation from loaded {@link IModel}s.
 * </p>
 * 
 * @author Claas Wilke
 */
public class Ocl22JavaFactory {

	private static Ocl22JavaFactory myInstance;

	/**
	 * <p>
	 * A private constructor to enforce the Singleton.
	 * </p>
	 */
	private Ocl22JavaFactory() {

		/* Remains empty. */
	}

	/**
	 * @return The only instance of {@link Ocl22JavaFactory}.
	 */
	public static Ocl22JavaFactory getInstance() {

		if (myInstance == null) {
			myInstance = new Ocl22JavaFactory();
		}
		// no else.

		return myInstance;
	}

	/**
	 * <p>
	 * Creates an {@link IOcl22Code} code generator.
	 * </p>
	 * 
	 * @return A code generator which generates Java code for loaded OCL
	 *         expressions.
	 * @throws Ocl22CodeException
	 *           Thrown if the initialization of a new Java code generator fails.
	 */
	public IOcl22Code createJavaCodeGenerator() throws Ocl22CodeException {

		IOcl22Code result;

		result = new Ocl22Java();

		return result;
	}

	/**
	 * <p>
	 * Creates an {@link IOcl22Code} code generator with given
	 * {@link IOcl22CodeSettings}.
	 * </p>
	 * 
	 * @return A code generator which generates Java code for loaded OCL
	 *         expressions.
	 * @throws Ocl22CodeException
	 *           Thrown if the initialization of a new Java code generator fails.
	 */
	public IOcl22Code createJavaCodeGenerator(IOcl22CodeSettings settings)
			throws Ocl22CodeException {

		IOcl22Code result;

		result = new Ocl22Java();
		result.setSettings(settings);

		return result;
	}

	/**
	 * <p>
	 * Creates {@link IOcl22CodeSettings} that can be used to configure a Java
	 * code generator.
	 * </p>
	 * 
	 * @return {@link IOcl22CodeSettings} that can be used to configure a Java
	 *         code generator.
	 */
	public IOcl22CodeSettings createJavaCodeGeneratorSettings() {

		IOcl22CodeSettings result;

		result = new Ocl22JavaSettings();

		return result;
	}

	/**
	 * @return An {@link ITransformedCode} which does not contain any code.
	 */
	public ITransformedCode createTransformedCode() {

		ITransformedCode result;

		result = new TransformedCodeImpl();

		return result;
	}
}