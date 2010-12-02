/*
Copyright (C) 2010 by Bjoern Freitag (bjoern.freitag@inf.tu-dresden.de)

This file is part of the OCL 2 SQL Code Generator of Dresden OCL2 for Eclipse.

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

package tudresden.ocl20.pivot.tools.codegen.declarativ.ocl2sql.ui.internal.wizards;

import java.util.List;

import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.tools.codegen.IOcl2Code;
import tudresden.ocl20.pivot.tools.codegen.declarativ.ocl2sql.IOcl2Sql;
import tudresden.ocl20.pivot.tools.codegen.declarativ.ocl2sql.Ocl2SqlPlugin;
import tudresden.ocl20.pivot.tools.codegen.declarativ.ocl2sql.ui.Ocl2SQLUIPlugIn;
import tudresden.ocl20.pivot.tools.codegen.exception.Ocl2CodeException;
import tudresden.ocl20.pivot.tools.codegen.ui.impl.wizards.CodegenJob;

public class CodegenSQLJob extends CodegenJob {

	/**
	 * <p>
	 * Creates a new {@link CodegenSQLJob}.
	 * </p>
	 * 
	 * @param constraints
	 *          The {@link Constraint}s for that code shall be generated.
	 * @param codeGenerator
	 *          The {@link IOcl2Code} used for code generation.
	 */
	public CodegenSQLJob(List<Constraint> constraints, IOcl2Code<?> codeGenerator) {

		super(constraints, codeGenerator);
		logger = Ocl2SQLUIPlugIn.getLogger(CodegenSQLJob.class);
		this.setPluginid(Ocl2SqlPlugin.ID);
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

		((IOcl2Sql) this.codeGenerator).setInputModel(ModelBusPlugin
				.getModelRegistry().getActiveModel());
		((IOcl2Sql) this.codeGenerator).transformFragmentCode(constraints);
	}

}