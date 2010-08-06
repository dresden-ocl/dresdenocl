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

package tudresden.ocl20.pivot.tools.codegen.ui.impl.wizards;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.tools.codegen.IOcl2Code;
import tudresden.ocl20.pivot.tools.codegen.exception.Ocl2CodeException;

public abstract class CodegenJob extends Job {

	/** A logger for this class. */
	protected Logger logger;

	private String pluginid;

	public String getPluginid() {

		return pluginid;
	}

	public void setPluginid(String pluginid) {

		this.pluginid = pluginid;
	}

	/**
	 * The {@link Constraint}s that shall be interpreted or <code>null</code> if
	 * all {@link Constraint}s shall be used
	 */
	protected List<Constraint> constraints;

	/** The {@link IOcl2Code} used for code generation. */
	protected IOcl2Code<?> codeGenerator;

	/**
	 * <p>
	 * Creates a new {@link CodegenJob}.
	 * </p>
	 * 
	 * @param constraints
	 *          The {@link Constraint}s for that code shall be generated.
	 * @param codeGenerator
	 *          The {@link IOcl2Code} used for code generation.
	 */
	public CodegenJob(List<Constraint> constraints, IOcl2Code<?> codeGenerator) {

		super("Generating Code ...");

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

	/*
	 * (non-Javadoc)
	 * @seeorg.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.
	 * IProgressMonitor)
	 */
	@Override
	protected IStatus run(IProgressMonitor monitor) {

		IStatus result;

		try {
			monitor.beginTask("Transform AspectJ Code ...", 100);

			runCodeGenerator(this.constraints);

			monitor.worked(100);
			monitor.done();

			/* FIXME Claas: Evtl. refresh the altered workspace automatically. */

			result =
					new Status(IStatus.OK, this.getPluginid(),
							"Code Transformation finished successfully.");
		}

		catch (Ocl2CodeException e) {

			String errorMsg = "An error occured during code generation.";
			logger.error(errorMsg, e);

			result = new Status(IStatus.ERROR, this.pluginid, errorMsg);
		}

		return result;
	}

	abstract protected void runCodeGenerator(List<Constraint> constraints)
			throws Ocl2CodeException;
}