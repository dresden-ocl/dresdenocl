/* Copyright (C) 2012 by Bjoern Freitag (bjoern.freitag@mailbox.tu-dresden.de)
 * 
 * This file is part of the CWM Meta Model of Dresden OCL2 for Eclipse. Dresden
 * OCL2 for Eclipse is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version. Dresden OCL2 for Eclipse is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
 * the GNU Lesser General Public License for more details. You should have
 * received a copy of the GNU Lesser General Public License along with Dresden
 * OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>.
 */
package org.dresdenocl.metamodels.cwmrelational.internal.model;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.dresdenocl.metamodels.cwmrelational.CWMMetamodelPlugin;
import org.dresdenocl.model.IModel;
import org.dresdenocl.model.ModelAccessException;
import org.dresdenocl.model.ModelConstants;
import org.dresdenocl.model.base.AbstractModel;
import org.dresdenocl.model.metamodel.IMetamodel;
import org.dresdenocl.pivotmodel.Namespace;
import org.dresdenocl.pivotmodel.PivotModelFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.osgi.util.NLS;
import orgomg.cwm.objectmodel.core.ModelElement;
import orgomg.cwm.resource.relational.Catalog;
import orgomg.cwm.resource.relational.Schema;

/**
 * <p>
 * If the root of the model is a single {@link Catalog}, a
 * corresponding {@link CWMPackage} adapter will be created. If there are
 * several root packages or a single {@link Schema}, a new "virtual" root namespace will be created.
 * </p>
 * 
 * @author Bjoern Freitag
 * 
 * @generated NOT
 */
public class CWMModel extends AbstractModel implements IModel {

	/**
	 * <p>
	 * A {@link Logger} for this class.
	 * </p>
	 * 
	 * @generated NOT
	 */
	private static final Logger LOGGER = CWMMetamodelPlugin
			.getLogger(CWMModel.class);

	/** The resource containing the corresponding CWM model. */
	private Resource resource;

	/** The adapter for the top package of the associated CWM model. */
	private Namespace rootNamespace;

	/** The {@link CWMAdapterFactory} of this {@link CWMModel}. */
	private CWMAdapterFactory factory;
	/**
	 * <p>
	 * Creates a new {@link CWMModel} adapting the given
	 * {@link orgomg.cwm.resource.relational.Schema}.
	 * 
	 * @param resource
	 *            The {@link Resource} containing the model.
	 * 
	 * @generated NOT
	 */
	public CWMModel(Resource resource, IMetamodel metamodel) {

		super(resource.getURI().toString(), metamodel);

		/* Initialize. */
		this.resource = resource;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.model.IModel#dispose()
	 */
	public void dispose() {
		/* Unload referenced resources and the resource. */
		Set<Resource> resourcesToUnload = new HashSet<Resource>();
		resourcesToUnload.add(this.resource);

		for (EObject eObject : EcoreUtil.ExternalCrossReferencer.find(
				this.resource).keySet()) {
			resourcesToUnload.add(eObject.eResource());
		}
		// end for.

		for (Resource resource : resourcesToUnload) {
			if (resource != null && resource.isLoaded())
				resource.unload();
			// no else.
		}
		// end for.

		/* Reset the root name space to avoid caching. */
		this.rootNamespace = null;
		/* Reset the factory as well. */
		this.factory = null;
	}

	/**
	 * <p>
	 * This method lazily creates a {@link Namespace} adapter for the virtual
	 * root package in the associated UML2 model. Thus, any possible resource
	 * loading errors will not happen until this method is called for the first
	 * time.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 *             if an error occurs when creating the adapter for the top
	 *             namespace
	 * 
	 * @see tudresden.ocl20.pivot.model.IModel#getRootNamespace()
	 * 
	 * @generated
	 */
	public Namespace getRootNamespace() throws ModelAccessException {

		if (rootNamespace == null) {
			rootNamespace = createRootNamespace();
		}

		return rootNamespace;
	}

	/**
	 * <p>
	 * Overridden to base equality check on the URI of the associated resource.
	 * </p>
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 * 
	 * @generated NOT
	 */
	@Override
	public boolean equals(Object anObject) {

		boolean result;

		/* Check if the given Object is a UML2Model. */
		if (anObject instanceof CWMModel) {
			result = resource.getURI().equals(
					((CWMModel) anObject).resource.getURI());
		}

		else {
			result = false;
		}

		return result;
	}

	/**
	 * <p>
	 * Overridden to base the hash code on the URI of the associated resource.
	 * </p>
	 * 
	 * @see java.lang.Object#hashCode()
	 * 
	 * @generated NOT
	 */
	@Override
	public int hashCode() {

		return this.resource.getURI().hashCode();
	}

	/**
	 * <p>
	 * Returns a String representation of this {@link CWMModel}.
	 * </p>
	 * 
	 * @see java.lang.Object#toString()
	 * 
	 * @generated NOT
	 */
	@Override
	public String toString() {

		return this.resource.getURI().toString();
	}

	/**
	 * <p>
	 * Returns the {@link CWMAdapterFactory} of this {@link CWMModel}.</p<
	 * 
	 * @return The {@link CWMAdapterFactory} of this {@link CWMModel}.
	 */
	public CWMAdapterFactory getFactory() {
		return this.factory;
	}


	/**
	 * <p>
	 * Helper method that creates the adapter for the root namespace. If there
	 * is only one top-level namespace possible, then this method should just
	 * return the adapter for the top-level namespace, else it should create a
	 * new "virtual" root namespace.
	 * </p>
	 * 
	 * @return A {@link Namespace} instance
	 * 
	 * @throws ModelAccessException
	 *             If an error occurs while loading the adapted CWM model.
	 * 
	 * @generated NOT
	 */
	private Namespace createRootNamespace() throws ModelAccessException {

		if (this.rootNamespace == null) {

			/** load the resource. */
			if (!resource.isLoaded()) {

				if (LOGGER.isInfoEnabled()) {
					LOGGER.info(NLS.bind(
							CWMModelMessages.CWMModel_LoadingCWMModel,
							resource.getURI()));
				}

				/* Try to load the resource. */
				try {
					resource.load(null);
				}

				catch (IOException e) {
					throw new ModelAccessException(
							"Error while loading resource from " + resource.getURI(), e); //$NON-NLS-1$
				}

			}
			// no else.

			/* Resolve all probably contained proxies of the resource. */
			EcoreUtil.resolveAll(resource);

			/* Get the root packages. */
			List<EObject> rootPackages;
			rootPackages = resource.getContents();

			/* Create a new package to serve as the root package. */
			this.rootNamespace = PivotModelFactory.eINSTANCE.createNamespace();
			this.rootNamespace.setName(ModelConstants.ROOT_PACKAGE_NAME);

			this.factory = new CWMAdapterFactory(this.rootNamespace);

			/** Add all sub-packages and sub-types to the new root package. */
			for (EObject eObject : rootPackages) {

				/*
				 * Models should not be added themselves. Add their contained
				 * packages instead.
				 */
				if (eObject instanceof Schema) {
					Namespace namespace = this.factory.createNamespace((Schema) eObject, this.rootNamespace);
					this.rootNamespace.addNestedNamespace(namespace);
					// end for.
				}
				else if (eObject instanceof Catalog) {
					for (ModelElement cwmType : ((Catalog) eObject).getOwnedElement()) {
						if (cwmType instanceof Schema) {
							Namespace namespace = this.factory.createNamespace(((Schema) cwmType),this.rootNamespace);
							this.rootNamespace.addNestedNamespace(namespace);
						}
					}
				}
			}
			// end for.
		}
		// no else.

		return this.rootNamespace;
	}
}