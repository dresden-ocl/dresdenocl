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
package tudresden.ocl20.pivot.tools.codegen.declarativ.ocl2sql;

import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.tools.codegen.IOcl2CodeSettings;
import tudresden.ocl20.pivot.tools.codegen.declarativ.IOcl2DeclCode;
import tudresden.ocl20.pivot.tools.codegen.declarativ.IOcl2DeclSettings;
import tudresden.ocl20.pivot.tools.codegen.declarativ.ocl2sql.internal.Ocl2Sql;
import tudresden.ocl20.pivot.tools.codegen.exception.Ocl2CodeException;

/**
 * <p>
 * This class provides methods to create SQL code generators for code generation
 * from loaded {@link IModel}s.
 * </p>
 * 
 * @author Bjoern Freitag
 */
public class Ocl2SQLFactory {

	private static Ocl2SQLFactory myInstance;

	/**
	 * <p>
	 * A private constructor to enforce the Singleton.
	 * </p>
	 */
	private Ocl2SQLFactory() {

		/* Remains empty. */
	}

	/**
	 * @return The only instance of {@link Ocl2SQLFactory}.
	 */
	public static Ocl2SQLFactory getInstance() {

		if (myInstance == null) {
			myInstance = new Ocl2SQLFactory();
		}
		// no else.

		return myInstance;
	}

	/**
	 * <p>
	 * Creates an {@link IOcl2Java} code generator.
	 * </p>
	 * 
	 * @return A code generator which generates Java code for loaded OCL
	 *         expressions.
	 * @throws Ocl22CodeException
	 *           Thrown if the initialization of a new Java code generator fails.
	 */
	public IOcl2Sql createSQLCodeGenerator() {

		IOcl2Sql result;

		result = new Ocl2Sql();

		return result;
	}

	/**
	 * <p>
	 * Creates an {@link IOcl2DeclCode} code generator with given
	 * {@link IOcl2CodeSettings}.
	 * </p>
	 * 
	 * @return A code generator which generates sql code for loaded OCL
	 *         expressions.
	 * @throws Ocl2CodeException
	 *           Thrown if the initialization of a new sql code generator fails.
	 */
	public IOcl2Sql createSQLCodeGenerator(IOcl2DeclSettings settings) {

		IOcl2Sql result;

		result = new Ocl2Sql();
		result.setSettings(settings);

		return result;
	}

}