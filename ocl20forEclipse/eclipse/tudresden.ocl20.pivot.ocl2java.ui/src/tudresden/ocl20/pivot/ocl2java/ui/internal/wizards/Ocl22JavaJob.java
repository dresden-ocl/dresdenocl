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

package tudresden.ocl20.pivot.ocl2java.ui.internal.wizards;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.IViewActionDelegate;

import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.ocl2java.IOcl22Code;
import tudresden.ocl20.pivot.ocl2java.Ocl22JavaPlugin;
import tudresden.ocl20.pivot.ocl2java.exception.Ocl22CodeException;
import tudresden.ocl20.pivot.ocl2java.ui.Ocl2JavaUIPlugIn;
import tudresden.ocl20.pivot.pivotmodel.Constraint;

public class Ocl22JavaJob extends Job {

	/** A logger for this class. */
	private static final Logger LOGGER = Ocl2JavaUIPlugIn
			.getLogger(Ocl22JavaJob.class);

	/**
	 * The {@link Constraint}s that shall be interpreted or <code>null</code> if
	 * all {@link Constraint}s shall be used
	 */
	private List<Constraint> constraints;

	/** The {@link IOcl22Code} used for code generation. */
	private IOcl22Code codeGenerator;

	/**
	 * <p>
	 * Creates a new {@link Ocl22JavaJob}.
	 * </p>
	 * 
	 * @param modelObjects
	 *            The {@link IModelInstanceElement}s that shall be interpreted
	 *            or <code>null</code> if all {@link IModelInstanceElement}s
	 *            shall be used.
	 * @param constraints
	 *            The {@link Constraint}s for that code shall be generated.
	 * @param interpreterView
	 *            The {@link IViewActionDelegate} this {@link Ocl22JavaJob}
	 *            belongs to.
	 */
	public Ocl22JavaJob(List<Constraint> constraints, IOcl22Code codeGenerator) {
		super("Interpreting Constraints ...");

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
	 * 
	 * @seeorg.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.
	 * IProgressMonitor)
	 */
	@Override
	protected IStatus run(IProgressMonitor monitor) {

		IStatus result;

		try {
			monitor.beginTask("Transform AspectJ Code ...", 100);

			this.codeGenerator.transformInstrumentationCode(this.constraints);

			monitor.worked(100);
			monitor.done();
			
			/* FIXME Claas: Evtl. refresh the altered workspace automatically. */

			result = new Status(IStatus.OK, Ocl22JavaPlugin.PLUGIN_ID,
					"Code Transformation finished successfully.");
		}

		catch (Ocl22CodeException e) {

			String errorMsg = "An error occured during code generation.";
			LOGGER.error(errorMsg, e);

			result = new Status(IStatus.ERROR, Ocl22JavaPlugin.PLUGIN_ID,
					errorMsg);
		}

		return result;
	}
}