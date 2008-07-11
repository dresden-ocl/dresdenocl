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
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EcorePackage;

import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType;

/**
 * An implementation of the Pivot Model {@link PrimitiveType} concept for Ecore. 
 * 
 * @author Matthias Braeuer
 * @version 1.0 30.03.2007
 */
public class EcorePrimitiveType extends AbstractPrimitiveType implements PrimitiveType {

  /**
   * Logger for this class
   */
  private static final Logger logger = Logger.getLogger(EcorePrimitiveType.class);

  // the adapted Ecore datatype
  private EDataType eDataType;

  /**
   * Creates a new <code>EcorePrimitiveType</code> instance.
   * 
   * @param eDataType the {@link EDataType} that is adapted by this class
   */
  public EcorePrimitiveType(EDataType eDataType) {
    super();

    if (logger.isDebugEnabled()) {
      logger.debug("EcorePrimitiveType(eDataType=" + eDataType + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    // initialize adapted datatype
    this.eDataType = eDataType;

    if (logger.isDebugEnabled()) {
      logger.debug("EcorePrimitiveType() - exit"); //$NON-NLS-1$
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType#getName()
   */
  @Override
  public String getName() {
    return eDataType.getName();
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType#getNamespace()
   */
  @Override
  public Namespace getNamespace() {
    return EcoreAdapterFactory.INSTANCE.createNamespace(eDataType.getEPackage());
  }

  /**
   * This method implements a type mapping from Ecore types to the predefined primitive tyoes of the
   * Pivot Model.
   */
  @Override
  public PrimitiveTypeKind getKind() {

    switch (eDataType.getClassifierID()) {

      case EcorePackage.EBYTE:
      case EcorePackage.EBYTE_OBJECT:
      case EcorePackage.ESHORT:
      case EcorePackage.ESHORT_OBJECT:
      case EcorePackage.EINT:
      case EcorePackage.EINTEGER_OBJECT:
      case EcorePackage.ELONG:
      case EcorePackage.ELONG_OBJECT:
      case EcorePackage.EBIG_INTEGER:
      case EcorePackage.EBIG_DECIMAL:
        return PrimitiveTypeKind.INTEGER;

      case EcorePackage.EFLOAT:
      case EcorePackage.EFLOAT_OBJECT:
      case EcorePackage.EDOUBLE:
      case EcorePackage.EDOUBLE_OBJECT:
        return PrimitiveTypeKind.REAL;

      case EcorePackage.EBOOLEAN:
      case EcorePackage.EBOOLEAN_OBJECT:
        return PrimitiveTypeKind.BOOLEAN;

      case EcorePackage.ECHAR:
      case EcorePackage.ECHARACTER_OBJECT:
      case EcorePackage.ESTRING:
        return PrimitiveTypeKind.STRING;

      default:
        return PrimitiveTypeKind.UNKNOWN;
    }

  }

}
