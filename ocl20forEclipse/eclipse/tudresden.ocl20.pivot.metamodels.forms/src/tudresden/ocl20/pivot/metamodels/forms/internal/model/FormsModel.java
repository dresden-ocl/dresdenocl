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
package tudresden.ocl20.pivot.metamodels.forms.internal.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.eclipse.emf.ecore.EObject;
import org.emftext.language.forms.Form;
import org.emftext.language.forms.resource.forms.mopp.FormsResource;

import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.model.ModelConstants;
import tudresden.ocl20.pivot.model.base.AbstractModel;
import tudresden.ocl20.pivot.model.metamodel.IMetamodel;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.PivotModelFactory;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Represents Forms Models which are adapted to the Pivot Model.
 * </p>
 * 
 * @author Claas Wilke
 */
public class FormsModel extends AbstractModel implements IModel {

	/** The {@link FormsResource} containing the corresponding Forms model. */
	private FormsResource resource;

	/** The adapter for the top package of the associated Forms model. */
	private Namespace rootNamespace;

	/**
	 * <p>
	 * Creates a new {@link FormsModel} adapting the given {@link FormsResource}
	 * .
	 * </p>
	 * 
	 * @param resource
	 *            The {@link FormsResource} containing the model.
	 * @param metamodel
	 *            The {@link IMetamodel} of the {@link FormsModel}.
	 */
	public FormsModel(FormsResource resource, IMetamodel metamodel) {

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
	 * This method lazily creates a {@link Namespace} adapter for the virtual
	 * root package in the associated Forms model. Thus, any possible resource
	 * loading errors will not happen until this method is called for the first
	 * time.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 *             If an error occurs when creating the adapter for the top name
	 *             space.
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

	/**
	 * <p>
	 * A helper method that creates the adapter for the root {@link Namespace}.
	 * </p>
	 * 
	 * @return A {@link Namespace} instance.
	 * 
	 * @throws ModelAccessException
	 *             If an error occurs while loading the adapted Ecore model.
	 */
	private Namespace createRootNamespace() throws ModelAccessException {

		this.rootNamespace = PivotModelFactory.eINSTANCE.createNamespace();
		this.rootNamespace.setName(ModelConstants.ROOT_PACKAGE_NAME);

		for (EObject eObject : this.resource.getContents()) {

			if (eObject instanceof Form) {
				Form form = (Form) eObject;
				this.rootNamespace
						.addNestedNamespace(FormsAdapterFactory.INSTANCE
								.createNamespace(form, this.rootNamespace));
			}
		}
		// end for.

		this.floodNamespace(this.rootNamespace);

		return this.rootNamespace;
	}

	/**
	 * <p>
	 * Accesses all {@link Namespace}s and {@link Type}s of the given
	 * {@link Namespace} once to avoid lazy loading of {@link Type}s.
	 * </p>
	 * 
	 * @param namespace
	 *            The {@link Namespace} to be flooded.
	 */
	private void floodNamespace(Namespace namespace) {

		for (Namespace nestedNamespace : namespace.getNestedNamespace()) {
			this.floodNamespace(nestedNamespace);
		}
		// end for.

		for (Type type : namespace.getOwnedType()) {
			for (Property property : type.getOwnedProperty()) {
				property.getType();
			}
			// end for.
		}
		// end for.
	}
}