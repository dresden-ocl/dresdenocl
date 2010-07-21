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

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.emftext.language.forms.Option;

import tudresden.ocl20.pivot.metamodels.forms.FormsMetaModelPlugin;
import tudresden.ocl20.pivot.pivotmodel.Enumeration;
import tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractEnumerationLiteral;

/**
 * <p>
 * Adapts {@link Option}s as {@link EnumerationLiteral}s to the pivot model.
 * </p>
 * 
 * @author Claas Wilke
 */
public class OptionEnumerationLiteral extends AbstractEnumerationLiteral
		implements EnumerationLiteral {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER = FormsMetaModelPlugin
			.getLogger(OptionEnumerationLiteral.class);

	/** The adapted {@link Option}. */
	private Option option;

	/** The owning {@link Enumeration} of this {@link OptionEnumerationLiteral}. */
	private Enumeration owningEnumeration;

	/**
	 * <p>
	 * Creates a new {@link OptionEnumerationLiteral}.
	 * </p>
	 * 
	 * @param option
	 *            The adapted {@link EEnumLiteral}.
	 * @param owningEnumeration
	 *            The owning {@link Enumeration} of this
	 *            {@link OptionEnumerationLiteral}.
	 */
	public OptionEnumerationLiteral(Option option, Enumeration owningEnumeration) {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "OptionEnumerationLiteral(";
			msg += "option = " + option;
			msg += ", owningEnumeration = " + owningEnumeration;
			msg += ") - enter";

			LOGGER.debug(msg); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		/* Initialize adapted enumeration literal. */
		this.option = option;

		/* Eventually log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "OptionEnumerationLiteral(Option, Enumeration) - exit";

			LOGGER.debug(msg); //$NON-NLS-1$
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seetudresden.ocl20.pivot.pivotmodel.base.AbstractEnumerationLiteral#
	 * getEnumeration()
	 */
	public Enumeration getEnumeration() {

		return this.owningEnumeration;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.pivotmodel.base.AbstractEnumerationLiteral#getName
	 * ()
	 */
	public String getName() {

		/* Returning the caption without white spaces and points. */
		return this.option.getText().replace(" ", "").replace(".", "");
	}
}