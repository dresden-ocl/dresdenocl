/*
Copyright (C) 2010 by Claas Wilke (claaswilke@gmx.net)

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

package org.dresdenocl.tools.codegen.ocl2java.ui.internal.wizards;

import java.util.List;

import org.dresdenocl.pivotmodel.Constraint;
import org.dresdenocl.tools.codegen.IOcl2Code;
import org.dresdenocl.tools.codegen.exception.Ocl2CodeException;
import org.dresdenocl.tools.codegen.ocl2java.IOcl2Java;
import org.dresdenocl.tools.codegen.ocl2java.ui.Ocl2JavaUIPlugIn;
import org.dresdenocl.tools.codegen.ui.impl.wizards.CodegenJob;

public class CodegenJavaJob extends CodegenJob {

	/**
	 * <p>
	 * Creates a new {@link CodegenJavaJob}.
	 * </p>
	 * 
	 * @param constraints
	 *          The {@link Constraint}s for that code shall be generated.
	 * @param codeGenerator
	 *          The {@link IOcl2Code} used for code generation.
	 */
	public CodegenJavaJob(List<Constraint> constraints, IOcl2Code<?> codeGenerator) {

		super(constraints, codeGenerator);
		logger = Ocl2JavaUIPlugIn.getLogger(CodegenJavaJob.class);

		if (constraints == null) {
			throw new IllegalArgumentException(
					"Parameter 'constraints' must not be null.");
		}

		else if (codeGenerator == null) {
			throw new IllegalArgumentException(
					"Parameter 'codeGenerator' must not be null.");
		}
		// no else.

		this.constraints = constraints;
		this.codeGenerator = codeGenerator;
	}

	protected void runCodeGenerator(List<Constraint> constraints)
			throws Ocl2CodeException {

		((IOcl2Java) this.codeGenerator).transformInstrumentationCode(constraints);
	}

}