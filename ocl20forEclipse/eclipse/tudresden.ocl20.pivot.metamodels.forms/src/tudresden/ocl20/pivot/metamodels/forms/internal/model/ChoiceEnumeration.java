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
import java.util.List;

import org.apache.log4j.Logger;
import org.emftext.language.forms.Choice;
import org.emftext.language.forms.Option;

import tudresden.ocl20.pivot.metamodels.forms.FormsMetaModelPlugin;
import tudresden.ocl20.pivot.pivotmodel.Enumeration;
import tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractEnumeration;

/**
 * <p>
 * Adapts {@link Choice}s as {@link Enumeration}s to the pivot model.
 * </p>
 * 
 * @author Claas Wilke
 */
public class ChoiceEnumeration extends AbstractEnumeration implements
		Enumeration {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER = FormsMetaModelPlugin
			.getLogger(ChoiceEnumeration.class);

	/** The adapted {@link Choice}. */
	private Choice choice;

	/** The nesting {@link Namespace} of this {@link ChoiceEnumeration}. */
	private Namespace nestingNamespace;

	/** The name of this choice. */
	private String name;

	/**
	 * <p>
	 * Creates a new {@link ChoiceEnumeration} instance.
	 * </p>
	 * 
	 * @param choice
	 *            The {@link Choice} adapted by this object.
	 * @param nestingNamespace
	 *            The nesting {@link Namespace} of this
	 *            {@link ChoiceEnumeration}.
	 * @param name
	 *            The name of this choice.
	 */
	public ChoiceEnumeration(Choice choice, Namespace nestingNamespace,
			String name) {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "ChoiceEnumeration(";
			msg += "eEnum = " + choice;
			msg += ", nestingNamespace = nestingNamespace";
			msg += "name = " + name;
			msg += ") - enter";

			LOGGER.debug(msg);
		}
		// no else.

		/* Initialize adapted enumeration. */
		this.choice = choice;
		this.nestingNamespace = nestingNamespace;
		this.nestingNamespace.addType(this);

		/* Probably convert the name's first character to an upper character. */
		if (name.length() > 0) {
			name = name.substring(0, 1).toUpperCase()
					+ name.substring(1, name.length());
		}
		// no else.

		/* Remove white spaces and points. */
		this.name = this.name.replace(" ", "").replace(".", "");

		this.name = "Choice" + name;

		/* Eventually log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "ChoiceEnumeration(Choice, Namespace, String) - exit";

			LOGGER.debug(msg);
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractEnumeration#getName()
	 */
	public String getName() {

		return this.name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.pivotmodel.base.AbstractEnumeration#getNamespace()
	 */
	@Override
	public Namespace getNamespace() {

		return this.nestingNamespace;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.pivotmodel.base.AbstractEnumeration#getOwnedLiteral
	 * ()
	 */
	@Override
	public List<EnumerationLiteral> getOwnedLiteral() {

		List<EnumerationLiteral> result;

		result = new ArrayList<EnumerationLiteral>();

		for (Option option : choice.getOptions()) {
			result.add(FormsAdapterFactory.INSTANCE.createEnumerationLiteral(
					option, this));
		}

		return result;
	}
}