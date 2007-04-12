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

import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;

import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractOperation;


/**
 * An implementation of the Pivot Model {@link Operation} concept for Ecore. 
 *
 * @author Matthias Braeuer
 * @version 1.0 12.04.2007
 */
public class EcoreOperation extends AbstractOperation implements Operation {

  //  Logger for this class
  private static final Logger logger = Logger.getLogger(EcoreOperation.class);

  // the adapted EOperation
  private EOperation eOperation;
  
  public EcoreOperation(EOperation eOperation) {
    if (logger.isDebugEnabled()) {
      logger.debug("EcoreOperation(eOperation=" + eOperation + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    
    // initialize
    this.eOperation = eOperation;

    if (logger.isDebugEnabled()) {
      logger.debug("EcoreOperation() - exit"); //$NON-NLS-1$
    }
 }
  
  /* (non-Javadoc)
   * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractOperation#getName()
   */
  @Override
  public String getName() {
    return eOperation.getName();
  }

  /* (non-Javadoc)
   * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractOperation#getOwnedParameter()
   */
  @Override
  public List<Parameter> getOwnedParameter() {
    List<Parameter> ownedParameter = new ArrayList<Parameter>();
    
    for (EParameter parameter : eOperation.getEParameters()) {
      ownedParameter.add(EcoreAdapterFactory.INSTANCE.createParameter(parameter));
    }
    
    return ownedParameter;
  }

  /* (non-Javadoc)
   * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractOperation#getOwningType()
   */
  @Override
  public Type getOwningType() {
    return EcoreAdapterFactory.INSTANCE.createType(eOperation.getEContainingClass());
  }

  /* (non-Javadoc)
   * @see tudresden.ocl20.pivot.pivotmodel.impl.OperationImpl#isMultiple()
   */
  @Override
  public boolean isMultiple() {
    return eOperation.isMany();
  }

  /* (non-Javadoc)
   * @see tudresden.ocl20.pivot.pivotmodel.impl.OperationImpl#isOrdered()
   */
  @Override
  public boolean isOrdered() {
    return eOperation.isOrdered();
  }

  /* (non-Javadoc)
   * @see tudresden.ocl20.pivot.pivotmodel.impl.OperationImpl#isUnique()
   */
  @Override
  public boolean isUnique() {
    return eOperation.isUnique();
  }
  
  

}
