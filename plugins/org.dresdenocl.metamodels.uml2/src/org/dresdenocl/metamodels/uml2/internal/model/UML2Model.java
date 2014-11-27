/*
 * Copyright (C) 2008-2009 by Michael Thiele & Claas Wilke (claaswilke@gmx.net)
 * This file is part of the UML2 Meta Model of Dresden OCL2 for Eclipse. Dresden
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
package org.dresdenocl.metamodels.uml2.internal.model;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.osgi.util.NLS;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Type;

import org.dresdenocl.metamodels.uml2.UML2MetamodelPlugin;
import org.dresdenocl.model.IModel;
import org.dresdenocl.model.ModelAccessException;
import org.dresdenocl.model.ModelConstants;
import org.dresdenocl.model.base.AbstractModel;
import org.dresdenocl.model.metamodel.IMetamodel;
import org.dresdenocl.pivotmodel.Namespace;
import org.dresdenocl.pivotmodel.PivotModelFactory;

/**
 * <p>
 * If the root of the model is a single {@link org.eclipse.uml2.uml.Package}, a
 * corresponding {@link UML2Namespace} adapter will be created. If there are
 * several root packages, a new "virtual" root namespace will be created.
 * </p>
 * 
 * @author Michael Thiele
 * 
 * @generated NOT
 */
public class UML2Model extends AbstractModel implements IModel {

	/**
	 * <p>
	 * A {@link Logger} for this class.
	 * </p>
	 * 
	 * @generated NOT
	 */
	private static final Logger LOGGER = UML2MetamodelPlugin
			.getLogger(UML2Model.class);

	/** The resource containing the corresponding UML2 model. */
	private Resource resource;

	/** The adapter for the top package of the associated UML2 model. */
	private Namespace rootNamespace;

	/** The {@link UML2AdapterFactory} of this {@link UML2Model}. */
	private UML2AdapterFactory factory;

	/**
	 * <p>
	 * Creates a new {@link UML2Model} adapting the given
	 * {@link org.eclipse.uml2.uml.Package}.
	 * 
	 * @param resource
	 *            The {@link Resource} containing the model.
	 * 
	 * @generated NOT
	 */
	public UML2Model(Resource resource, IMetamodel metamodel) {

		super(resource.getURI().toString(), metamodel);

		/* Initialize. */
		this.resource = resource;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.model.IModel#dispose()
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
	 * @see org.dresdenocl.model.IModel#getRootNamespace()
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
		if (anObject instanceof UML2Model) {
			result = resource.getURI().equals(
					((UML2Model) anObject).resource.getURI());
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
	 * Returns a String representation of this {@link UML2Model}.
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
	 * Returns the {@link UML2AdapterFactory} of this {@link UML2Model}.</p<
	 * 
	 * @return The {@link UML2AdapterFactory} of this {@link UML2Model}.
	 */
	public UML2AdapterFactory getFactory() {
		return this.factory;
	}

	/**
	 * <p>
	 * Computes the {@link Package}s from referenced UML models of this
	 * {@link UML2Model} and adds them to the root {@link Namespace} as well.
	 * </p>
	 */
	private void addNamespacesForReferencedPackages() {

		/* Iterate through all referenced EObjects. */
		for (EObject eObject : EcoreUtil.ExternalCrossReferencer.find(resource)
				.keySet()) {

			EcoreUtil.resolveAll(eObject);

			/* Check the containing package of each EClassifier. */
			if (eObject instanceof Classifier) {

				Classifier classifier;
				classifier = (Classifier) eObject;

				Package containerPackage;
				containerPackage = classifier.getNearestPackage();

				if (containerPackage == null
						|| containerPackage instanceof Model
						|| containerPackage instanceof Profile) {

					// FIXME Claas: This will probably cause problems. Types do
					// not know their right name space now if they should be
					// located in the root name space directly. */

					/* Adapt the type. */
					org.dresdenocl.pivotmodel.Type importedType;
					importedType = this.factory.createType(classifier);

					/* Only add the type if it has not been added yet. */
					if (!this.rootNamespace.getOwnedType().contains(
							importedType)) {
						this.rootNamespace.addType(importedType);
					}
					// no else.

				}

				else {
					while (containerPackage.getNestingPackage() != null
							&& !(containerPackage.getNestingPackage() instanceof Model)
							&& !(containerPackage.getNestingPackage() instanceof Profile)) {
						containerPackage = containerPackage.getNestingPackage();
					}

					/*
					 * Adapt the package. If a package with the same name
					 * already exists, the packages are merged.
					 */
					Namespace importedNamespace;
					importedNamespace = this.factory.createNamespace(
							containerPackage, this.rootNamespace);

					/*
					 * Only add the name space if it has not been added yet (for
					 * merged name space, the name space has not to be added
					 * again).
					 */
					if (!this.rootNamespace.getNestedNamespace().contains(
							importedNamespace)) {
						this.rootNamespace
								.addNestedNamespace(importedNamespace);
					}
					// no else.
				}
			}

			/*
			 * Else check if the EObject is a package (can be imported via
			 * import statement in UML.
			 */
			else if (eObject instanceof Package) {

				/* If the package is a model or a profile only add its content. */
				if (eObject instanceof Model || eObject instanceof Profile) {

					for (Package umlPackage : ((Package) eObject)
							.getNestedPackages()) {
						Namespace adaptedNamespace = this.factory
								.createNamespace(umlPackage, this.rootNamespace);
						if (adaptedNamespace != null
								&& !this.rootNamespace.equals(adaptedNamespace) && !this.rootNamespace.getNestedNamespace()
										.contains(adaptedNamespace))
							this.rootNamespace
									.addNestedNamespace(adaptedNamespace);
						// no else.
					}
					// end for.

					for (Type umlType : ((Package) eObject).getOwnedTypes()) {
						org.dresdenocl.pivotmodel.Type adaptedType = this.factory
								.createType(umlType);
						if (adaptedType != null
								&& !this.rootNamespace.getOwnedType().contains(
										adaptedType))
							this.rootNamespace.addType(adaptedType);
						// no else.
					}
					// end for.
				}

				else {
					Package importedPackage;
					importedPackage = (Package) eObject;

					if (importedPackage.getNestingPackage() == null
							|| importedPackage.getNestingPackage() instanceof Model
							|| importedPackage.getNestingPackage() instanceof Profile) {

						/*
						 * Adapt the package. If a package with the same name
						 * already exists, the packages are merged.
						 */
						Namespace importedNamespace;
						importedNamespace = this.factory.createNamespace(
								importedPackage, this.rootNamespace);

						/*
						 * Only add the name space if it has not been added yet
						 * (for merged name space, the name space has not to be
						 * added again).
						 */
						if (!this.rootNamespace.getNestedNamespace().contains(
								importedNamespace)) {
							this.rootNamespace
									.addNestedNamespace(importedNamespace);
						}
						// no else.
					}
					// no else.
				}
				// end else.
			}
			// no else.
		}
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
	 *             If an error occurs while loading the adapted UML2 model.
	 * 
	 * @generated NOT
	 */
	private Namespace createRootNamespace() throws ModelAccessException {

		if (this.rootNamespace == null) {

			/** load the resource. */
			if (!resource.isLoaded()) {

				if (LOGGER.isInfoEnabled()) {
					LOGGER.info(NLS.bind(
							UML2ModelMessages.UML2Model_LoadingUML2Model,
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

			this.factory = new UML2AdapterFactory(this.rootNamespace);

			/** Add all sub-packages and sub-types to the new root package. */
			for (EObject eObject : rootPackages) {

				/*
				 * Models should not be added themselves. Add their contained
				 * packages instead.
				 */
				if (eObject instanceof Model) {

					for (Package umlPackage : ((Model) eObject)
							.getNestedPackages()) {
						Namespace adaptedNamespace = this.factory
								.createNamespace(umlPackage, this.rootNamespace);
						if (adaptedNamespace != null)
							this.rootNamespace
									.addNestedNamespace(adaptedNamespace);
						// no else.
					}
					// end for.

					for (Type umlType : ((Model) eObject).getOwnedTypes()) {
						org.dresdenocl.pivotmodel.Type adaptedType = this.factory
								.createType(umlType);
						if (adaptedType != null)
							this.rootNamespace.addType(adaptedType);
						// no else.
					}
					// end for.
				}

				/*
				 * Profiles should not be added themselves. Add their contained
				 * packages instead.
				 */
				else if (eObject instanceof Profile) {

					for (Package umlPackage : ((Profile) eObject)
							.getNestedPackages()) {
						Namespace adaptedNamespace = this.factory
								.createNamespace(umlPackage, this.rootNamespace);
						if (adaptedNamespace != null)
							this.rootNamespace
									.addNestedNamespace(adaptedNamespace);
						// no else.
					}
					// end for.

					for (Type umlType : ((Profile) eObject).getOwnedTypes()) {
						org.dresdenocl.pivotmodel.Type adaptedType = this.factory
								.createType(umlType);
						if (adaptedType != null)
							this.rootNamespace.addType(adaptedType);
						// no else.
					}
					// end for.
				}

				else if (eObject instanceof Package) {
					Namespace adaptedNamespace = this.factory.createNamespace(
							(Package) eObject, this.rootNamespace);
					if (adaptedNamespace != null)
						this.rootNamespace.addNestedNamespace(adaptedNamespace);
					// no else.
				}

				else if (eObject instanceof Type) {
					org.dresdenocl.pivotmodel.Type adaptedType = this.factory
							.createType((Type) eObject);
					if (adaptedType != null)
						this.rootNamespace.addType(adaptedType);
					// no else.
				}
				// no else.
			}
			// end for.

			this.addNamespacesForReferencedPackages();
		}
		// no else.

		return this.rootNamespace;
	}
}