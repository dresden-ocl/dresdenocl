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
import org.emftext.language.forms.FreeText;

import tudresden.ocl20.pivot.metamodels.forms.FormsMetaModelPlugin;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType;

/**
 * <p>
 * An implementation of the Pivot Model {@link PrimitiveType} concept for
 * {@link FreeText}s.
 * </p>
 * 
 * @author Claas Wilke
 */
public class FreeTextPrimitiveType extends AbstractPrimitiveType implements
		PrimitiveType {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER = FormsMetaModelPlugin
			.getLogger(FreeTextPrimitiveType.class);

	/** The nesting {@link Namespace} of this {@link FreeTextPrimitiveType}. */
	private Namespace nestingNamespace;

	/**
	 * <p>
	 * Creates a new {@link FreeTextPrimitiveType} instance.
	 * </p>
	 * 
	 * @param nestingNamespace
	 *            The nesting {@link Namespace} of this
	 *            {@link FreeTextPrimitiveType}.
	 */
	public FreeTextPrimitiveType(Namespace nestingNamespace) {

		super();

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "FreeTextPrimitiveType(";
			msg += "nestingNamespace = " + nestingNamespace;
			msg += ") - enter";

			LOGGER.debug(msg);
		}
		// no else.

		this.nestingNamespace = nestingNamespace;
		this.nestingNamespace.addType(this);

		/* Eventually log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "FreeTextPrimitiveType(Namespace) - exit";

			LOGGER.debug(msg);
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType#getKind()
	 */
	@Override
	public PrimitiveTypeKind getKind() {

		return PrimitiveTypeKind.STRING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType#getName()
	 */
	@Override
	public String getName() {

		return "FreeText";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType#getNamespace
	 * ()
	 */
	@Override
	public Namespace getNamespace() {

		return this.nestingNamespace;
	}
}