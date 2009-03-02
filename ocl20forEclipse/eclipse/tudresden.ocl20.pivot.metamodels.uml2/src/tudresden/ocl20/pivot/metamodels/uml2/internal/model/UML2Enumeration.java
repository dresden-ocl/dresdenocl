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

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import tudresden.ocl20.pivot.pivotmodel.Enumeration;
import tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractEnumeration;

/**
 * <p>
 * An implementation of the Pivot Model {@link Enumeration} concept for UML2.
 * </p>
 * 
 * @author Michael Thiele
 * 
 * @generated NOT
 */
public class UML2Enumeration extends AbstractEnumeration implements Enumeration {

	/**
	 * <p>
	 * Logger for this class
	 * </p>
	 * 
	 * @generated
	 */
	private static final Logger logger = Logger
			.getLogger(UML2Enumeration.class);

	/**
	 * <p>
	 * the adapted org.eclipse.uml2.uml.Enumeration data type
	 * </p>
	 * 
	 * @generated
	 */
	private org.eclipse.uml2.uml.Enumeration dslEnumeration;

	/**
	 * <p>
	 * Creates a new <code>UML2Enumeration</code> instance.
	 * </p>
	 * 
	 * @param dslEnumeration
	 *            the {@link org.eclipse.uml2.uml.Enumeration} that is adopted
	 *            by this class
	 * 
	 * @generated
	 */
	public UML2Enumeration(org.eclipse.uml2.uml.Enumeration dslEnumeration) {

		if (logger.isDebugEnabled()) {
			logger
					.debug("UML2Enumeration(dslEnumeration=" + dslEnumeration + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// initialize
		this.dslEnumeration = dslEnumeration;

		if (logger.isDebugEnabled()) {
			logger.debug("UML2Enumeration() - exit"); //$NON-NLS-1$
		}
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractEnumeration#getName()
	 * 
	 * @generated NOT
	 */
	@Override
	public String getName() {
		return this.dslEnumeration.getName();
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractEnumeration#getNamespace()
	 * 
	 * @generated NOT
	 */
	@Override
	public Namespace getNamespace() {
		return UML2AdapterFactory.INSTANCE.createNamespace(dslEnumeration
				.getPackage());
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractEnumeration#getOwnedLiteral()
	 * 
	 * @generated NOT
	 */
	@Override
	public List<EnumerationLiteral> getOwnedLiteral() {

		List<EnumerationLiteral> result;

		result = new ArrayList<EnumerationLiteral>();

		for (org.eclipse.uml2.uml.EnumerationLiteral dslEnumerationLiteral : this.dslEnumeration
				.getOwnedLiterals()) {

			result.add(UML2AdapterFactory.INSTANCE
					.createEnumerationLiteral(dslEnumerationLiteral));
		}

		return result;
	}
}