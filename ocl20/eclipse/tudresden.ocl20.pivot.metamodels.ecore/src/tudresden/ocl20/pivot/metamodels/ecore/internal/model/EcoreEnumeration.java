/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Matthias Braeuer (braeuer.matthias@web.de).            *
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
 * $Id$
 */
package tudresden.ocl20.pivot.metamodels.ecore.internal.model;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;

import tudresden.ocl20.pivot.pivotmodel.Enumeration;
import tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractEnumeration;

/**
 * 
 * 
 * @author Matthias Braeuer
 * @version 1.0 12.04.2007
 */
public class EcoreEnumeration extends AbstractEnumeration implements Enumeration {

  // Logger for this class
  private static final Logger logger = Logger.getLogger(EcoreEnumeration.class);

  // the adapted Ecore enum
  private EEnum eEnum;

  /**
   * Creates a new <code>EcoreEnumeration</code> instance.
   * 
   * @param eEnum the Ecore <code>EEnum</code> adapted by this object
   */
  public EcoreEnumeration(EEnum eEnum) {
    if (logger.isDebugEnabled()) {
      logger.debug("EcoreEnumeration(eEnum=" + eEnum + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    // initialize adapted enum
    this.eEnum = eEnum;

    if (logger.isDebugEnabled()) {
      logger.debug("EcoreEnumeration() - exit"); //$NON-NLS-1$
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractEnumeration#getName()
   */
  @Override
  public String getName() {
    return eEnum.getName();
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractEnumeration#getNamespace()
   */
  @Override
  public Namespace getNamespace() {
    return EcoreAdapterFactory.INSTANCE.createNamespace(eEnum.getEPackage());
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractEnumeration#getOwnedLiteral()
   */
  @Override
  public List<EnumerationLiteral> getOwnedLiteral() {
    List<EnumerationLiteral> literals = new ArrayList<EnumerationLiteral>();

    for (EEnumLiteral literal : eEnum.getELiterals()) {
      literals.add(EcoreAdapterFactory.INSTANCE.createEnumerationLiteral(literal));
    }

    return literals;
  }

}
