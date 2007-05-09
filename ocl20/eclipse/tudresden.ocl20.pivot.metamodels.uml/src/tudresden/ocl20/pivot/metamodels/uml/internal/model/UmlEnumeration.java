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
 * $Id: UmlEnumeration.java,v 1.1 2007/05/09 17:49:03 robra81 Exp $
 */
package tudresden.ocl20.pivot.metamodels.uml.internal.model;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tudresden.ocl20.core.jmi.uml15.core.Enumeration;
import tudresden.ocl20.core.jmi.uml15.core.ModelElement;
import tudresden.ocl20.core.jmi.uml15.modelmanagement.Package;
import tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractEnumeration;

/**
 * An implementation of the Pivot Model {@link Type} concept for
 * UML metamodel in MDR.
 * 
 * @author Ronny Brandt
 * @version 1.0 09.05.2007
 */
public class UmlEnumeration extends AbstractEnumeration implements Type {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UmlEnumeration.class);

	// the adapted UML enumeration
	private Enumeration enumeration;
	
  /**
   * Creates a new <code>UmlEnumeration</code> instance.
   * 
   * @param enumeration the UML <code>Enumeration</code> adapted by this object
   */
	public UmlEnumeration(Enumeration enumeration) {
		if (logger.isDebugEnabled()) {
			logger.debug("UmlEnumeration(Enumeration enumeration=" + enumeration
					+ ") - enter");
		}

		this.enumeration = enumeration;

		if (logger.isDebugEnabled()) {
			logger.debug("UmlEnumeration(Enumeration) - exit");
		}
	}

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractEnumeration#getName()
   */
	@Override
	public String getName() {
		if (logger.isDebugEnabled()) {
			logger.debug("getName() - enter");
		}

		String returnString = enumeration.getName();
		if (logger.isDebugEnabled()) {
			logger.debug("getName() - exit - return value=" + returnString);
		}
		return returnString;
	}

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractEnumeration#getNamespace()
   */
	@Override
	public Namespace getNamespace() {
		if (logger.isDebugEnabled()) {
			logger.debug("getNamespace() - enter");
		}

		Namespace returnNamespace = UmlAdapterFactory.INSTANCE
				.createNamespace((Package) enumeration.getNamespace());
		if (logger.isDebugEnabled()) {
			logger.debug("getNamespace() - exit - return value=" + returnNamespace);
		}
		return returnNamespace;
	}

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractEnumeration#getOwnedLiteral()
   */
	@Override
	public List<EnumerationLiteral> getOwnedLiteral() {
		if (logger.isDebugEnabled()) {
			logger.debug("getOwnedLiteral() - enter");
		}

		List<EnumerationLiteral> ownedLiteral = new ArrayList<EnumerationLiteral>();
		
		Iterator it = enumeration.getLiteral().iterator();
		
		while(it.hasNext()) {
			ModelElement me = (ModelElement)it.next();
			if (me instanceof tudresden.ocl20.core.jmi.uml15.core.Parameter)
				ownedLiteral.add(UmlAdapterFactory.INSTANCE.
						createEnumerationLiteral((tudresden.ocl20.core.jmi.uml15.core.EnumerationLiteral)me));
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getOwnedLiteral() - exit - return value=" + ownedLiteral);
		}
		return ownedLiteral;
	}
}
