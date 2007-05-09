/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Ronny Brandt (Ronny_Brandt@web.de).            *
 * All rights reserved.                                                      *
 *                                                                           *
 * This work was done as a project at the Chair for Software Technology,     *
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
 */
package tudresden.ocl20.pivot.metamodels.mof.internal.model;

import org.apache.log4j.Logger;

import tudresden.ocl20.core.jmi.mof14.model.EnumerationType;
import tudresden.ocl20.pivot.pivotmodel.Enumeration;
import tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractEnumerationLiteral;

/**
/**
 * An implementation of the Pivot Model {@link EnumerationLiteral} concept for
 * MOF metamodel in MDR.
 * 
 * @author Ronny Brandt
 * @version 1.0 09.05.2007
 */
public class MofEnumerationLiteral extends AbstractEnumerationLiteral implements
		EnumerationLiteral {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(MofEnumerationLiteral.class);
	
	// the MOF enumeration type containing the adapted enumeration literal
	private EnumerationType enumerationType;
	
	// the adapted enumeration literal
	private String label;

	/**
	 * Creates a new <code>MofEnumerationLiteral</code> instance.
	 * 
	 * @param enumerationType the MOF {@link EnumerationType} containing the adapted 
	 * enumeration literal. Needed because
	 * @param label the label is only a {@link String} without reference to 
	 * the {@link EnumerationType}
	 */
	public MofEnumerationLiteral(EnumerationType enumerationType, String label) {
		if (logger.isDebugEnabled()) {
			logger.debug("MofEnumerationLiteral(EnumerationType enumerationType="
					+ enumerationType + ", String label=" + label + ") - enter");
		}

		this.enumerationType = enumerationType;
		this.label = label;

		if (logger.isDebugEnabled()) {
			logger.debug("MofEnumerationLiteral(EnumerationType, String) - exit");
		}
	}

	/* (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractEnumerationLiteral#getEnumeration()
	 */
	@Override
	public Enumeration getEnumeration() {
		if (logger.isDebugEnabled()) {
			logger.debug("getEnumeration() - enter");
		}

		Enumeration returnEnumeration = (Enumeration) MofAdapterFactory.INSTANCE
				.createEnumerationType(enumerationType);
		if (logger.isDebugEnabled()) {
			logger.debug("getEnumeration() - exit - return value="
					+ returnEnumeration);
		}
		return returnEnumeration;
	}

	/* (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractEnumerationLiteral#getName()
	 */
	@Override
	public String getName() {
		if (logger.isDebugEnabled()) {
			logger.debug("getName() - enter");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getName() - exit - return value=" + label);
		}
		return label;
	}

}
