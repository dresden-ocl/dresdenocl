/*
Copyright (C) 2008-2009 by Michael Thiele & Claas Wilke (claaswilke@gmx.net)

This file is part of the UML2 Meta Model of Dresden OCL2 for Eclipse.

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
package tudresden.ocl20.pivot.metamodels.uml2.internal.model;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.osgi.util.NLS;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;

import tudresden.ocl20.pivot.metamodels.uml2.UML2MetamodelPlugin;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelInstanceProvider;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.base.AbstractModel;
import tudresden.ocl20.pivot.models.uml2.internal.provider.Uml2ModelInstanceProvider;
import tudresden.ocl20.pivot.pivotmodel.Namespace;

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

	/** A {@link Logger} for this class. */
	private static final Logger logger = Logger.getLogger(UML2Model.class);

	/** The resource containing the corresponding UML2 model. */
	private Resource resource;

	/** The adapter for the top package of the associated UML2 model. */
	private Namespace rootNamespace;

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
	public UML2Model(Resource resource) {

		super(resource.getURI().toString(), ModelBusPlugin
				.getMetamodelRegistry().getMetamodel(UML2MetamodelPlugin.ID));

		/* Initialize. */
		this.resource = resource;
	}

	/**
	 * @see tudresden.ocl20.pivot.modelbus.IModel#getModelInstanceProvider()
	 * 
	 * @generated NOT
	 */
	public IModelInstanceProvider getModelInstanceProvider() {
		return new Uml2ModelInstanceProvider(this);
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
	 * @see tudresden.ocl20.pivot.modelbus.IModel#getRootNamespace()
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
	protected Namespace createRootNamespace() throws ModelAccessException {

		List<EObject> rootPackages;
		org.eclipse.uml2.uml.Package rootPackage;

		/** load the resource. */
		if (!resource.isLoaded()) {

			if (logger.isInfoEnabled()) {
				logger.info(NLS.bind(
						UML2ModelMessages.UML2Model_LoadingUML2Model, resource
								.getURI()));
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

		/* Get the root packages. */
		rootPackages = resource.getContents();

		/* Create a new package to serve as the root package. */
		rootPackage = UMLFactory.eINSTANCE.createPackage();
		rootPackage.setName("root");

		/** Add all sub-packages and subtypes to the new root package. */
		for (EObject eObject : rootPackages) {

			if (eObject instanceof Package) {
				rootPackage.getNestedPackages().add((Package) eObject);
			}

			else if (eObject instanceof Type) {
				rootPackage.getOwnedMembers().add((Type) eObject);
			}
			// no else.
		}
		// end for.

		this.convertAssociations(rootPackage);

		return UML2AdapterFactory.INSTANCE.createNamespace(rootPackage);
	}

	/**
	 * <p>
	 * Processes all UML Associations: since they are treated as Types in the
	 * UML meta model, they have to be mapped to Properties in the Pivot Model.
	 * </p>
	 * 
	 * <p>
	 * Precondition: binary associations
	 * </p>
	 * 
	 * @param rootPackage
	 *            the containing package (namespace)
	 */
	protected void convertAssociations(Package rootPackage) {

		// associations in all nested packages have to converted
		for (Package aPackage : rootPackage.getNestedPackages()) {
			convertAssociations(aPackage);
		}
		for (Type type : rootPackage.getOwnedTypes()) {
			if (type instanceof Association) {
				Association association = (Association) type;

				Property property1 = association.getOwnedEnds().get(0);
				Property property2 = association.getOwnedEnds().get(1);
				// search for navigable elements -> opposite is owner
				if (property1.isNavigable()) {
					UML2AdapterFactory.INSTANCE.createType(property2.getType())
							.addProperty(
									UML2AdapterFactory.INSTANCE
											.createProperty(property1));
				}
				if (property2.isNavigable()) {
					UML2AdapterFactory.INSTANCE.createType(property1.getType())
							.addProperty(
									UML2AdapterFactory.INSTANCE
											.createProperty(property2));
				}
				// all other associations are not specified for navigation ->
				// bidirectional
				if (!(property1.isNavigable()) && !(property2.isNavigable())) {
					UML2AdapterFactory.INSTANCE.createType(property2.getType())
							.addProperty(
									UML2AdapterFactory.INSTANCE
											.createProperty(property1));
					UML2AdapterFactory.INSTANCE.createType(property1.getType())
							.addProperty(
									UML2AdapterFactory.INSTANCE
											.createProperty(property2));
				}
			}
		}
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
}