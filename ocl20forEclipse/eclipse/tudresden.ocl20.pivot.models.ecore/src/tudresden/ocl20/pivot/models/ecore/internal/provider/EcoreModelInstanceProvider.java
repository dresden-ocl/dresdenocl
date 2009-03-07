/*
Copyright (C) 2007-2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the Ecore Meta Model of Dresden OCL2 for Eclipse.

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
package tudresden.ocl20.pivot.models.ecore.internal.provider;

import java.net.URL;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelInstance;
import tudresden.ocl20.pivot.modelbus.IModelInstanceProvider;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.base.AbstractModelInstanceProvider;
import tudresden.ocl20.pivot.models.ecore.internal.modelinstance.EcoreModelInstance;

/**
 * <p>
 * Provides methods to load or get {@link IModelInstance}s of the Ecore meta
 * model.
 * 
 * @author Claas Wilke
 */
public class EcoreModelInstanceProvider extends AbstractModelInstanceProvider
		implements IModelInstanceProvider {

	/** The {@link IModel} of this {@link EcoreModelInstanceProvider}. */
	private IModel myModel;

	/**
	 * The {@link Resource} representing the meta model of this
	 * {@link EcoreModelInstanceProvider}.
	 */
	private Resource myMetaModelResource;

	/**
	 * <p>
	 * Creates a new EcoreModelInstanceProvider.
	 * </p>
	 * 
	 * @param metaModelResource
	 *            The meta model {@link Resource} of this
	 *            {@link EcoreModelInstanceProvider}.
	 * @param myModel
	 *            The {@link IModel} of this {@link EcoreModelInstanceProvider}.
	 */
	public EcoreModelInstanceProvider(Resource metaModelResource, IModel myModel) {
	
		this.myMetaModelResource = metaModelResource;
		this.myModel = myModel;
	}

	private ResourceSet resourceSet;

	public IModelInstance getModelInstance(URL modelURL)
			throws ModelAccessException {

		URI modelURI;
		Resource resource;
		IModelInstance modelInstance;

		// try to create a URI
		try {
			modelURI = URI.createURI(modelURL.toString());
		} catch (IllegalArgumentException e) {
			throw new ModelAccessException("Invalid URL: " + modelURL, e); //$NON-NLS-1$
		}

		// get the resource
		resource = getResourceSet().getResource(modelURI, false);

		if (resource == null) {
			// we only want to create the resource, not load it
			resource = getResourceSet().createResource(modelURI);
		}

		modelInstance = new EcoreModelInstance(resource, myMetaModelResource, this.myModel);

		return modelInstance;
	}

	/**
	 * Helper method that lazily creates a resource set.
	 * 
	 * @return
	 */
	protected ResourceSet getResourceSet() {

		if (resourceSet == null) {
			resourceSet = new ResourceSetImpl();
		}

		return resourceSet;
	}
}
