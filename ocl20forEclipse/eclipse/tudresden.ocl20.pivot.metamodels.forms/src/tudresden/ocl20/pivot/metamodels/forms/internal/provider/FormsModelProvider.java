/*
 * Copyright (C) 2010 by Claas Wilke (claas.wilke@tudresden.de)
 *
 * This file is part of the Forms Meta-Model of DresdenOCL.
 *
 * DresdenOCL is free software: you can redistribute it and/or modify 
 * it under the terms of the GNU Lesser General Public License as published by the 
 * Free Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * DresdenOCL is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along 
 * with DresdenOCL. If not, see <http://www.gnu.org/licenses/>.
 */
package tudresden.ocl20.pivot.metamodels.forms.internal.provider;

import java.io.File;
import java.net.URL;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.emftext.language.forms.resource.forms.mopp.FormsResource;
import org.emftext.language.forms.resource.forms.util.FormsTextResourceUtil;

import tudresden.ocl20.pivot.metamodels.forms.FormsMetaModelPlugin;
import tudresden.ocl20.pivot.metamodels.forms.internal.model.FormsModel;
import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.IModelProvider;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.model.base.AbstractModelProvider;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;

/**
 * <p>
 * Implementation of the {@link IModelProvider} interface for Forms models. This
 * implementation will create an {@link FormsModel} instance.
 * </p>
 * 
 * @author Claas Wilke
 */
public class FormsModelProvider extends AbstractModelProvider implements
		IModelProvider {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER = FormsMetaModelPlugin
			.getLogger(FormsModelProvider.class);

	/** A cached copy of the resource set used for loading models. */
	protected ResourceSet resourceSet;

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelProvider#getModel(java.net.URL)
	 */
	public IModel getModel(URL modelURL) throws ModelAccessException {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getModel(modelURL=" + modelURL + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		FormsResource resource;
		IModel result;

		File formsFile;
		formsFile = new File(modelURL.getFile());

		if (!formsFile.exists()) {
			throw new ModelAccessException("File '" + modelURL.getFile()
					+ "' does not exist.");
		}
		// no else.

		/* Parse the forms file. */
		resource = FormsTextResourceUtil.getResource(formsFile);

		if (resource.getErrors().size() > 0) {
			throw new ModelAccessException("The form '" + formsFile.toString()
					+ "' contains errors.");
		}
		// no else.

		/* Create the model from the resource. */
		result = new FormsModel(resource, ModelBusPlugin.getMetamodelRegistry()
				.getMetamodel(FormsMetaModelPlugin.ID));

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getModel() - exit - return value=" + result); //$NON-NLS-1$
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * A helper method that lazily creates a resource set.
	 * </p>
	 * 
	 * @return The created {@link ResourceSet}.
	 */
	protected ResourceSet getResourceSet() {

		if (this.resourceSet == null) {
			this.resourceSet = new ResourceSetImpl();
		}
		// no else.

		return this.resourceSet;
	}
}