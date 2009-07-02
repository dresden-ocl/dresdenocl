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
package tudresden.ocl20.pivot.metamodels.uml2.internal.model;

import org.apache.log4j.Logger;

import tudresden.ocl20.pivot.metamodels.uml2.UML2MetamodelPlugin;
import tudresden.ocl20.pivot.pivotmodel.Enumeration;
import tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractEnumerationLiteral;

/**
 * <p>
 * An implementation of the Pivot Model {@link EnumerationLiteral} concept for
 * UML2.
 * </p>
 * 
 * @author Michael Thiele
 * 
 * @generated NOT
 */
public class UML2EnumerationLiteral extends AbstractEnumerationLiteral
		implements EnumerationLiteral {

	/**
	 * <p>
	 * Logger for this class
	 * </p>
	 * 
	 * @generated NOT
	 */
	private static final Logger LOGGER =
			UML2MetamodelPlugin.getLogger(UML2EnumerationLiteral.class);

	/**
	 * <p>
	 * The adapted {@link org.eclipse.uml2.uml.EnumerationLiteral} data type.
	 * </p>
	 * 
	 * @generated
	 */
	private org.eclipse.uml2.uml.EnumerationLiteral dslEnumerationLiteral;

	/**
	 * <p>
	 * Creates a new <code>UML2EnumerationLiteral</code> instance.
	 * </p>
	 * 
	 * @param dslEnumerationLiteral
	 *          the {@link org.eclipse.uml2.uml.EnumerationLiteral} that is
	 *          adopted by this class
	 * 
	 * @generated
	 */
	public UML2EnumerationLiteral(
			org.eclipse.uml2.uml.EnumerationLiteral dslEnumerationLiteral) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("UML2EnumerationLiteral(dslEnumerationLiteral=" + dslEnumerationLiteral + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// initialize
		this.dslEnumerationLiteral = dslEnumerationLiteral;

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("org.eclipse.uml2.uml.EnumerationLiteral() - exit"); //$NON-NLS-1$
		}
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractEnumerationLiteral#getName()
	 * 
	 * @generated NOT
	 */
	@Override
	public String getName() {

		return this.dslEnumerationLiteral.getName();
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractEnumerationLiteral#getEnumeration()
	 * 
	 * @generated NOT
	 */
	@Override
	public Enumeration getEnumeration() {

		return UML2AdapterFactory.INSTANCE.createEnumeration(dslEnumerationLiteral
				.getEnumeration());
	}
}