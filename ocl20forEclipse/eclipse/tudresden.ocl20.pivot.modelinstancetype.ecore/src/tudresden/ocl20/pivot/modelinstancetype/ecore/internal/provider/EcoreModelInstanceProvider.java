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
package tudresden.ocl20.pivot.modelinstancetype.ecore.internal.provider;

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
import tudresden.ocl20.pivot.modelinstancetype.ecore.internal.modelinstance.EcoreModelInstance;

/**
 * <p>
 * Provides methods to load or get {@link IModelInstance}s of the EMF Ecore
 * model instance type.
 * 
 * @author Claas Wilke
 */
public class EcoreModelInstanceProvider extends AbstractModelInstanceProvider
		implements IModelInstanceProvider {

	/**
	 * <p>
	 * Creates a new {@link EcoreModelInstanceProvider}.
	 * </p>
	 */
	public EcoreModelInstanceProvider() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelInstanceProvider#getModelInstance
	 * (java.net.URL, tudresden.ocl20.pivot.modelbus.IModel)
	 */
	public IModelInstance getModelInstance(URL modelInstanceUrl, IModel model)
			throws ModelAccessException {

		URI modelURI;
		IModelInstance modelInstance;
		
		Resource resource;
		ResourceSet resourceSet;

		/* Try to create a URI. */
		try {
			modelURI = URI.createURI(modelInstanceUrl.toString());
		}

		catch (IllegalArgumentException e) {
			throw new ModelAccessException("Invalid URL: " + modelInstanceUrl,
					e);
		}

		/* Get the resource. */
		resourceSet = new ResourceSetImpl();
		resource = resourceSet.getResource(modelURI, false);

		if (resource == null) {
			// we only want to create the resource, not load it
			resource = resourceSet.createResource(modelURI);
		}

		modelInstance = new EcoreModelInstance(resource, model);

		return modelInstance;
	}
}