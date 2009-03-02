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

import org.apache.log4j.Logger;

import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType;

/**
 * <p>
 * An implementation of the Pivot Model {@link PrimitiveType} concept for UML2.
 * </p>
 * 
 * @author Michael Thiele
 * 
 * @generated
 */
public class UML2PrimitiveType extends AbstractPrimitiveType implements
		PrimitiveType {

	/**
	 * <p>
	 * Logger for this class
	 * </p>
	 * 
	 * @generated
	 */
	private static final Logger logger = Logger
			.getLogger(UML2PrimitiveType.class);

	/**
	 * <p>
	 * the adapted org.eclipse.uml2.uml.PrimitiveType data type
	 * </p>
	 * 
	 * @generated
	 */
	private org.eclipse.uml2.uml.PrimitiveType dslPrimitiveType;

	/**
	 * <p>
	 * Creates a new <code>UML2PrimitiveType</code> instance.
	 * </p>
	 * 
	 * @param dslPrimitiveType
	 *            the {@link org.eclipse.uml2.uml.PrimitiveType} that is adopted
	 *            by this class
	 * 
	 * @generated
	 */
	public UML2PrimitiveType(org.eclipse.uml2.uml.PrimitiveType dslPrimitiveType) {

		if (logger.isDebugEnabled()) {
			logger
					.debug("UML2PrimitiveType(dslPrimitiveType=" + dslPrimitiveType + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// initialize
		this.dslPrimitiveType = dslPrimitiveType;

		if (logger.isDebugEnabled()) {
			logger.debug("org.eclipse.uml2.uml.PrimitiveType() - exit"); //$NON-NLS-1$
		}
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType#getName()
	 * 
	 * @generated NOT
	 */
	@Override
	public String getName() {
		return this.dslPrimitiveType.getName();
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType#getNamespace()
	 * 
	 * @generated NOT
	 */
	@Override
	public Namespace getNamespace() {
		return UML2AdapterFactory.INSTANCE
				.createNamespace(this.dslPrimitiveType.getPackage());
	}

	/**
	 * <p>
	 * This method implements a type mapping from
	 * {@link org.eclipse.uml2.uml.PrimitiveType} types to the predefined
	 * primitive types of the Pivot Model.
	 * </p>
	 * 
	 * @generated NOT
	 */
	@Override
	public PrimitiveTypeKind getKind() {

		PrimitiveTypeKind result;

		String integerNames[];
		String realNames[];
		String primitiveTypeName;

		integerNames = new String[] { "byte", "int", "integer", "long", "short" };
		realNames = new String[] { "double", "float", "real" };
		primitiveTypeName = dslPrimitiveType.getName();

		result = null;

		if (primitiveTypeName.equalsIgnoreCase("boolean")) {
			result = PrimitiveTypeKind.BOOLEAN;
		}

		else if (primitiveTypeName.equalsIgnoreCase("string")) {
			result = PrimitiveTypeKind.STRING;
		}

		else {

			for (String integerName : integerNames) {

				if (primitiveTypeName.equalsIgnoreCase(integerName)) {
					result = PrimitiveTypeKind.INTEGER;
				}
				// no else.
			}
		}

		if (result == null) {

			for (String realName : realNames) {

				if (primitiveTypeName.equalsIgnoreCase(realName)) {
					result = PrimitiveTypeKind.REAL;
				}
				// no else.
			}
		}
		// no else.

		if (result == null) {
			result = PrimitiveTypeKind.UNKNOWN;
		}
		// no else.

		return result;
	}
}