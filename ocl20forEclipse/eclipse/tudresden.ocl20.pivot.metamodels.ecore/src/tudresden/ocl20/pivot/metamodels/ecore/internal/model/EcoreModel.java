/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Matthias Braeuer (braeuer.matthias@web.de).            *
 * All rights reserved.                                                      *
 *                                                                           *
 * This work was done as a project at the Chair for Software Technology      *
 * Dresden University Of Technology, Germany (http://st.inf.tu-dresden.de).  *
 * It is understood that any modification not identified as such is not      *
 * covered by the preceding statement.                                       *
 *                                                                           *
 * This work is free software; you can redistribute it and/or modify it      *
 * under the terms of the GNU Library General Public License as published    *
 * by the Free Software Foundation; either version 2 of the License, or      *
 * (at your option) any later version.                                       *
 *                                                                           *
 * This work is distributed in the hope that it will be useful, but WITHOUT  *
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or     *
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Library General Public     *
 * License for more details.                                                 *
 *                                                                           *
 * You should have received a copy of the GNU Library General Public License *
 * along with this library; if not, you can view it online at                *
 * http://www.fsf.org/licensing/licenses/gpl.html.                           *
 *                                                                           *
 * To submit a bug report, send a comment, or get the latest news on this    *
 * project, please visit the website: http://dresden-ocl.sourceforge.net.    *
 * For more information on OCL and related projects visit the OCL Portal:    *
 * http://st.inf.tu-dresden.de/ocl                                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * 
 * $Id$
 */
package tudresden.ocl20.pivot.metamodels.ecore.internal.model;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.osgi.util.NLS;

import tudresden.ocl20.pivot.metamodels.ecore.EcoreMetamodelPlugin;
import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.model.ModelConstants;
import tudresden.ocl20.pivot.model.base.AbstractModel;
import tudresden.ocl20.pivot.model.metamodel.IMetamodel;
import tudresden.ocl20.pivot.pivotmodel.Namespace;

/**
 * <p>
 * Represents EMF Ecore Models which are adapted to the Pivot Model.
 * </p>
 * 
 * <p>
 * If the root of the model is a single {@link EPackage}, a corresponding
 * {@link EcoreNamespace} adapter will be created. If there are several root
 * packages, a new "virtual" root namespace will be created.
 * </p>
 * 
 * @author Matthias Braeuer
 */
public class EcoreModel extends AbstractModel implements IModel {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER = EcoreMetamodelPlugin
			.getLogger(EcoreModel.class);

	/** The {@link Resource} containing the corresponding Ecore model. */
	private Resource resource;

	/** The adapter for the top package of the associated Ecore model. */
	private Namespace rootNamespace;

	/** Keep track what EClasses have already been considered by resolving. */
	private Set<EClass> resolvedClasses = new HashSet<EClass>();

	/**
	 * <p>
	 * Creates a new {@link EcoreModel} adapting the given {@link EPackage}.
	 * </p>
	 * 
	 * @param resource
	 *          The {@link Resource} containing the model.
	 */
	public EcoreModel(Resource resource, IMetamodel metamodel) {

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
		/* Unload the resource to remove external contents. */
		this.resource.unload();
		/* Reset the root name space to avoid caching. */
		this.rootNamespace = null;
	}

	/**
	 * <p>
	 * This method lazily creates a {@link Namespace} adapter for the virtual root
	 * package in the associated Ecore model. Thus, any possible resource loading
	 * errors will not happen until this method is called for the first time.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 *           If an error occurs when creating the adapter for the top name
	 *           space.
	 * 
	 * @see tudresden.ocl20.pivot.model.IModel#getRootNamespace()
	 */
	public Namespace getRootNamespace() throws ModelAccessException {

		if (this.rootNamespace == null) {
			this.rootNamespace = this.createRootNamespace();
		}
		// no else.

		return this.rootNamespace;
	}

	/**
	 * <p>
	 * Overridden to base equality check on the URI of the associated resource.
	 * </p>
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object anObject) {

		boolean result;

		if (anObject instanceof EcoreModel) {

			EcoreModel anEcoreModel;

			anEcoreModel = (EcoreModel) anObject;

			result = this.resource.getURI().equals(anEcoreModel.resource.getURI());
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
	 */
	@Override
	public int hashCode() {

		return this.resource.getURI().hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		String result;

		result = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.append("resource", this.resource.getURI()).toString();

		return result;
	}

	private Set<EPackage> resolvePackages(EClass eClass) {

		if (resolvedClasses.contains(eClass))
			return Collections.emptySet();

		Set<EPackage> result = new HashSet<EPackage>();

		resolvedClasses.add(eClass);

		if (eClass.eIsProxy()) {
			EcoreUtil.resolve(eClass, resource);
		}
		EPackage ePackage;
		ePackage = eClass.getEPackage();

		if (ePackage != null) {
			while (ePackage.getESuperPackage() != null)
				ePackage = ePackage.getESuperPackage();

			result.add(ePackage);
		}

		for (EReference eReference : eClass.getEReferences()) {
			EClassifier eReferenceType = eReference.getEType();
			if (eReferenceType instanceof EClass) {
				result.addAll(resolvePackages((EClass) eReferenceType));
			}
		}
		for (EOperation eOperation : eClass.getEOperations()) {
			EClassifier eOperationType = eOperation.getEType();
			if (eOperationType instanceof EClass) {
				result.addAll(resolvePackages((EClass) eOperationType));
			}
		}

		return result;
	}

	/**
	 * <p>
	 * Computes the {@link EPackage}s from referenced Ecore models of this
	 * {@link EcoreModel} and adds them to the root {@link Namespace} as well.
	 * </p>
	 * 
	 * @param rootPackage
	 *          The {@link EPackage} that represents the root {@link Namespace} of
	 *          this {@link EcoreModel}.
	 */
	private void addNamespacesForReferencedPackages(EPackage rootPackage) {

		/* Collect EPackages that have to be added. */
		Set<EPackage> packagesToBeAdded;
		packagesToBeAdded = new HashSet<EPackage>();

		/* Iterate through all references EClassifier. */
		TreeIterator<EObject> iter = resource.getAllContents();
		while (iter.hasNext()) {
			EObject eObject = iter.next();
			if (eObject instanceof EClass) {
				packagesToBeAdded.addAll(this.resolvePackages((EClass) eObject));
			}
			// no else.
		}

		// for (EObject eObject : EcoreUtil.ExternalCrossReferencer.find(
		// eClassifierList).keySet()) {
		//
		// /* Check the containing package of each EClassifier. */
		// if (eObject instanceof EClassifier) {
		//
		// EClassifier eClassifier;
		// eClassifier = (EClassifier) eObject;
		//
		// if (eClassifier.eIsProxy()) {
		// EcoreUtil.resolve(eClassifier, resource.getResourceSet());
		// }
		//
		// EPackage containerPackage;
		// containerPackage = eClassifier.getEPackage();
		//
		// /* Can happen if proxy resolving fails. */
		// if (containerPackage != null) {
		// /* Check if the classifier belongs to a package. */
		// while (containerPackage.getESuperPackage() != null) {
		// containerPackage = containerPackage.getESuperPackage();
		// }
		//
		// if (!rootPackage.getESubpackages().contains(containerPackage)
		// && !packagesToBeAdded.contains(containerPackage)
		// && !containerPackage.equals(rootPackage)) {
		//
		// /*
		// * Do not add the package directly because a copy is required.
		// * Afterwards, the containment check fails for the copy and the
		// * package may be added multiple times.
		// */
		// packagesToBeAdded.add(containerPackage);
		// }
		// // no else.
		// }
		//
		// else {
		// LOGGER.warn("Could not resolve proxy for referenced element "
		// + eObject + ". Model may be incomplete.");
		// }
		// }
		// // no else.
		// }
		/*
		 * Now copy and add the packages. Copy is required to avoid bi-directional
		 * references.
		 */
		for (EPackage ePackage : packagesToBeAdded) {
			if (ePackage != rootPackage)
				rootPackage.getESubpackages().add((EPackage) EcoreUtil.copy(ePackage));
		}
		// end for.

		/*
		 * now we can safely forget what EClasses have already been resolved, as
		 * this is only called once.
		 */
		this.resolvedClasses.clear();
	}

	/**
	 * <p>
	 * A helper method that creates the adapter for the root {@link Namespace}.
	 * </p>
	 * 
	 * @return A {@link Namespace} instance.
	 * 
	 * @throws ModelAccessException
	 *           If an error occurs while loading the adapted Ecore model.
	 */
	private Namespace createRootNamespace() throws ModelAccessException {

		EPackage rootPackage;
		List<EObject> rootPackages;

		/* Probably try to load the resource. */
		if (!resource.isLoaded()) {

			/* Probably inform the logger. */
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info(NLS.bind(EcoreModelMessages.EcoreModel_LoadingEcoreModel,
						this.resource.getURI()));
			}
			// no else.

			try {
				this.resource.load(null);
			}

			catch (IOException e) {
				String msg;

				msg = "Error while loading resource from " + resource.getURI();

				throw new ModelAccessException(msg, e); //$NON-NLS-1$
			}
		}
		// no else.

		/* Get the root packages. */
		rootPackages = this.resource.getContents();

		/* Create a new package to serve as the root package. */
		rootPackage = EcoreFactory.eINSTANCE.createEPackage();
		rootPackage.setName(ModelConstants.ROOT_PACKAGE_NAME);

		/* Add all sub-packages and sub-types to the new root package. */
		for (EObject eObject : rootPackages) {

			if (eObject instanceof EPackage) {
				rootPackage.getESubpackages().add((EPackage) eObject);
			}

			else if (eObject instanceof EClassifier) {
				rootPackage.getEClassifiers().add((EClassifier) eObject);
			}

			/* Else throw an exception. */
			else {
				throw new ModelAccessException(
						"Unknown kind of EObject. Expected EPackage or EClassifier but was "
								+ eObject.getClass().getSimpleName());
			}
		}
		// end for.

		this.addNamespacesForReferencedPackages(rootPackage);

		return EcoreAdapterFactory.INSTANCE.createNamespace(rootPackage);
	}
}