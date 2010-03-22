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

import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.metamodel.IMetamodel;
import tudresden.ocl20.pivot.modelbus.model.IModel;
import tudresden.ocl20.pivot.modelbus.model.IModelProvider;
import tudresden.ocl20.pivot.modelbus.model.IModelRegistry;
import tudresden.ocl20.pivot.modelbus.ui.ModelBusUIPlugin;
import tudresden.ocl20.pivot.modelbus.ui.ModelBusUIUtility;
import tudresden.ocl20.pivot.modelbus.ui.internal.views.ModelsView;

/**
 * <p>
 * A {@link Job} to load an {@link IModel}.
 * </p>
 * 
 * @author Claas Wilke
 */
public class LoadModelJob extends Job {

	/** A logger for this class. */
	private static final Logger LOGGER = ModelBusUIPlugin
			.getLogger(LoadModelJob.class);

	/** The {@link IMetamodel} of the {@link IModel} that shall be imported. */
	private IMetamodel metamodel;

	/** The {@link File} of the {@link IModel} that shall be imported. */
	private File modelFile;

	/**
	 * <p>
	 * Creates a new {@link LoadModelJob}.
	 * </p>
	 * 
	 * @param metamodel
	 *            The {@link IMetamodel} of the {@link IModel} that shall be
	 *            imported.
	 * @param modelFile
	 *            The {@link File} of the {@link IModel} that shall be imported.
	 */
	public LoadModelJob(IMetamodel metamodel, File modelFile) {
		super("Open Model ...");

		if (metamodel == null) {
			throw new IllegalArgumentException(
					"Parameter 'metamodel' must not be null.");
		}

		else if (modelFile == null) {
			throw new IllegalArgumentException(
					"Parameter 'modelFile' must not be null.");
		}
		// no else.

		this.metamodel = metamodel;
		this.modelFile = modelFile;
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

		IModelProvider modelProvider;
		IModel model = null;

		/* Load the model. */
		modelProvider = metamodel.getModelProvider();

		try {
			monitor
					.beginTask("Importing model " + this.modelFile + " ...",
							100);
			model = modelProvider.getModel(this.modelFile);
			monitor.worked(95);

			/* Add the successfully loaded model to the model registry. */
			IModelRegistry modelRegistry;
			modelRegistry = ModelBusPlugin.getModelRegistry();

			/* Try to add the model to the registry. */
			modelRegistry.addModel(model);
			modelRegistry.setActiveModel(model);
			monitor.worked(4);

			/* Activate the Model Browser View. */
			ModelBusUIUtility.setActiveView(ModelsView.ID);
			monitor.worked(1);

			monitor.done();

			result = new Status(IStatus.OK, ModelBusUIPlugin.ID,
					"Imported Model successfully.");

		}

		catch (ModelAccessException e) {

			/* Log and display the exception. */
			String errorMsg = "An error occured during importing a Model.";
			LOGGER.error(errorMsg, e);

			result = new Status(IStatus.ERROR, ModelBusUIPlugin.ID, errorMsg);
		}

		return result;
	}
}