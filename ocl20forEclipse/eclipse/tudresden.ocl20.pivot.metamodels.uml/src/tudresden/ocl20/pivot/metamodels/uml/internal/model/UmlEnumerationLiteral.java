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
 *
 * $Id: UmlEnumerationLiteral.java,v 1.1 2007/05/09 17:49:03 robra81 Exp $
 */
package tudresden.ocl20.pivot.metamodels.uml.internal.model;

import org.apache.log4j.Logger;

import tudresden.ocl20.pivot.pivotmodel.Enumeration;
import tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractEnumerationLiteral;

/**
 * An implementation of the Pivot Model {@link EnumerationLiteral} concept for
 * UML metamodel in MDR.
 * 
 * @author Ronny Brandt
 * @version 1.0 09.05.2007
 */
public class UmlEnumerationLiteral extends AbstractEnumerationLiteral implements
		EnumerationLiteral {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(UmlEnumerationLiteral.class);

	// the adapted UML enumeration literal
	private tudresden.ocl20.core.jmi.uml15.core.EnumerationLiteral enumerationLiteral;
	
  /**
   * Creates a new <code>UmlEnumerationLiteral</code> instance.
   * 
   * @param enumerationLiteral the UML <code>EnumerationLiteral</code> adapted 
   * by this object
   */
	public UmlEnumerationLiteral(
			tudresden.ocl20.core.jmi.uml15.core.EnumerationLiteral enumerationLiteral) {
		if (logger.isDebugEnabled()) {
			logger
					.debug("UmlEnumerationLiteral(tudresden.ocl20.core.jmi.uml15.core.EnumerationLiteral enumerationLiteral="
							+ enumerationLiteral + ") - enter");
		}
		
		this.enumerationLiteral = enumerationLiteral;

		if (logger.isDebugEnabled()) {
			logger
					.debug("UmlEnumerationLiteral(tudresden.ocl20.core.jmi.uml15.core.EnumerationLiteral) - exit");
		}
	}

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractEnumerationLiteral#getEnumeration()
   */
	@Override
	public Enumeration getEnumeration() {
		if (logger.isDebugEnabled()) {
			logger.debug("getEnumeration() - enter");
		}

		Enumeration returnEnumeration = (Enumeration) UmlAdapterFactory.INSTANCE
				.createEnumeration(enumerationLiteral.getEnumeration());
		if (logger.isDebugEnabled()) {
			logger.debug("getEnumeration() - exit - return value="
					+ returnEnumeration);
		}
		return returnEnumeration;
	}

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractEnumerationLiteral#getName()
   */
	@Override
	public String getName() {
		if (logger.isDebugEnabled()) {
			logger.debug("getName() - enter");
		}

		String returnString = this.enumerationLiteral.getName();
		if (logger.isDebugEnabled()) {
			logger.debug("getName() - exit - return value=" + returnString);
		}
		return returnString;
	}
}
