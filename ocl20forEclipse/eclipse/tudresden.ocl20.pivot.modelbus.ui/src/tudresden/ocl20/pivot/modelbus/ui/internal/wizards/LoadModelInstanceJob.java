/*
Copyright (C) 2010 by Claas Wilke (claaswilke@gmx.net).

This file is part of the Model Bus GUI of Dresden OCL2 for Eclipse.

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

package tudresden.ocl20.pivot.modelbus.ui.internal.wizards;

import java.io.File;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.ui.ModelBusUIPlugin;
import tudresden.ocl20.pivot.modelbus.ui.ModelBusUIUtility;
import tudresden.ocl20.pivot.modelbus.ui.internal.ModelBusUIMessages;
import tudresden.ocl20.pivot.modelbus.ui.internal.views.ModelInstancesView;
import tudresden.ocl20.pivot.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelinstance.IModelInstanceProvider;
import tudresden.ocl20.pivot.modelinstance.IModelInstanceRegistry;
import tudresden.ocl20.pivot.modelinstance.IModelInstanceType;

/**
 * <p>
 * A {@link Job} to load an {@link IModelInstance}.
 * </p>
 * 
 * @author Claas Wilke
 */
public class LoadModelInstanceJob extends Job {

	/** A logger for this class. */
	private static final Logger LOGGER = ModelBusUIPlugin
			.getLogger(LoadModelInstanceJob.class);

	/** The {@link IModel} of the {@link IModelInstance} that shall be imported. */
	private IModel model;

	/**
	 * The {@link File} of the {@link IModelInstance} that shall be imported.
	 */
	private File modelInstanceFile;

	/**
	 * The {@link IModelInstanceType} of the {@link IModelInstance} that shall
	 * be imported.
	 */
	private IModelInstanceType modelInstanceType;

	/**
	 * <p>
	 * Creates a new {@link LoadModelInstanceJob}.
	 * </p>
	 * 
	 * @param model
	 *            The {@link IModel} of the {@link IModelInstance} that shall be
	 *            imported.
	 * @param modelInstanceType
	 *            The {@link IModelInstanceType} of the {@link IModelInstance}
	 *            that shall be imported.
	 * @param modelInstanceFile
	 *            The {@link File} of the {@link IModelInstance} that shall be
	 *            imported.
	 */
	public LoadModelInstanceJob(IModel model,
			IModelInstanceType modelInstanceType, File modelInstanceFile) {
		super("Open Model Instance ...");

		if (model == null) {
			throw new IllegalArgumentException(
					"Parameter 'model' must not be null.");
		}

		else if (modelInstanceType == null) {
			throw new IllegalArgumentException(
					"Parameter 'modelInstanceType' must not be null.");
		}

		else if (modelInstanceFile == null) {
			throw new IllegalArgumentException(
					"Parameter 'modelInstanceFile' must not be null.");
		}
		// no else.

		this.model = model;
		this.modelInstanceType = modelInstanceType;
		this.modelInstanceFile = modelInstanceFile;
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

		IModelInstance modelInstance;
		IModelInstanceProvider modelInstanceProvider;

		modelInstanceProvider = this.modelInstanceType
				.getModelInstanceProvider();

		/* Try to load the model instance. */
		try {
			monitor.beginTask("Import Model Instance " + this.modelInstanceFile
					+ " ...", 100);

			modelInstance = modelInstanceProvider.getModelInstance(
					this.modelInstanceFile, this.model);
			monitor.worked(95);

			/*
			 * Add the successfully loaded model instance to the model instance
			 * registry.
			 */
			IModelInstanceRegistry modelInstanceRegistry;
			modelInstanceRegistry = ModelBusPlugin.getModelInstanceRegistry();
			modelInstanceRegistry.addModelInstance(modelInstance);
			modelInstanceRegistry.setActiveModelInstance(model, modelInstance);

			monitor.worked(4);

			/* Try to activate the ModelInstanceView. */
			ModelBusUIUtility.setActiveView(ModelInstancesView.ID);
			monitor.worked(1);
			
			monitor.done();

			result = new Status(IStatus.OK, ModelBusUIPlugin.ID,
					"Imported Model Instance successfully.");
		}

		catch (ModelAccessException e) {

			String msg;
			msg = ModelBusUIMessages.LoadModelInstanceWizard_ErrorOccured
					+ e.getMessage();

			LOGGER.error(msg, e);
			result = new Status(IStatus.ERROR, ModelBusUIPlugin.ID, msg);
		}
		// end catch.

		return result;
	}
}