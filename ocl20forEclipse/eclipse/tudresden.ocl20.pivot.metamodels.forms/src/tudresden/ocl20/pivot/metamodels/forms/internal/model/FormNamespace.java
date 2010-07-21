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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.emftext.language.forms.Form;
import org.emftext.language.forms.Group;

import tudresden.ocl20.pivot.metamodels.forms.FormsMetaModelPlugin;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractNamespace;

/**
 * <p>
 * An implementation of the Pivot Model {@link Namespace} concept for Forms.
 * </p>
 * 
 * @author Class Wilke
 */
public class FormNamespace extends AbstractNamespace implements Namespace {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER = FormsMetaModelPlugin
			.getLogger(FormNamespace.class);

	/** The adapted {@link Form}. */
	private Form form;

	/** The nesting {@link Namespace} of this {@link FormNamespace}. */
	private Namespace nestingNamespace;

	/** Types of {@link Property}s that are added manually later on. */
	private Set<Type> manuallyAddedTypes = new HashSet<Type>();

	/**
	 * <p>
	 * Creates a new {@link FormNamespace} instance.
	 * </p>
	 * 
	 * @param form
	 *            The {@link Form} that is adapted by this class.
	 * @param nestingNamespace
	 *            The nesting {@link Namespace} of this {@link FormNamespace}.
	 */
	public FormNamespace(Form form, Namespace nestingNamespace) {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "FormsNamespace(";
			msg += "form = " + form;
			msg += "nestingNamespace = " + nestingNamespace;
			msg += ") - enter";

			LOGGER.debug(msg);
		}
		// no else.

		/* Initialize adapted Form. */
		this.form = form;

		/* Eventually log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "FormsNamespace(Form, Namespace) - exit";

			LOGGER.debug(msg);
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractNamespace#getName()
	 */
	@Override
	public String getName() {

		/* Returning the caption without white spaces and points. */
		return this.form.getCaption().replace(" ", "").replace(".", "");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.pivotmodel.base.AbstractNamespace#getNestingNamespace
	 * ()
	 */
	@Override
	public Namespace getNestingNamespace() {

		return this.nestingNamespace;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.pivotmodel.base.AbstractNamespace#getOwnedTypeImpl
	 * ()
	 */
	@Override
	public List<Type> getOwnedType() {

		List<Type> result;
		result = new ArrayList<Type>();

		/* Adapt groups that contain properties here. */
		for (Group group : this.form.getGroups()) {
			result.add(FormsAdapterFactory.INSTANCE.createType(group, this));
		}
		// end for.

		result.addAll(this.manuallyAddedTypes);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.pivotmodel.impl.NamespaceImpl#addType(tudresden
	 * .ocl20.pivot.pivotmodel.Type)
	 */
	@Override
	public Namespace addType(Type type) {

		this.manuallyAddedTypes.add(type);

		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seetudresden.ocl20.pivot.pivotmodel.base.AbstractNamespace#
	 * getNestedNamespaceImpl()
	 */
	@Override
	protected List<Namespace> getNestedNamespaceImpl() {

		return Collections.emptyList();
	}
}