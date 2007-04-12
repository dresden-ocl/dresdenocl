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

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;

import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractNamespace;

/**
 * An implementation of the Pivot Model {@link Namespace} concept for Ecore.
 * 
 * @author Matthias Braeuer
 * @version 1.0 30.03.2007
 */
public class EcoreNamespace extends AbstractNamespace implements Namespace {

  // a logger for this class
  private static final Logger logger = Logger.getLogger(EcoreNamespace.class);

  // the adapted Ecore package
  private EPackage ePackage;

  /**
   * Creates a new <code>EcoreNamespace</code> instance.
   * 
   * @param ePackage the {@link EPackage} that is adapted by this class
   */
  public EcoreNamespace(EPackage ePackage) {
    if (logger.isDebugEnabled()) {
      logger.debug("EcoreNamespace(ePackage=" + ePackage + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    // initialize adapted EPackage
    this.ePackage = ePackage;

    if (logger.isDebugEnabled()) {
      logger.debug("EcoreNamespace() - exit"); //$NON-NLS-1$
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractNamespace#getName()
   */
  @Override
  public String getName() {
    return ePackage.getName();
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractNamespace#getNestedNamespaceImpl()
   */
  @Override
  protected List<Namespace> getNestedNamespaceImpl() {
    List<Namespace> nestedNamespace = new ArrayList<Namespace>();

    for (EPackage subPackage : ePackage.getESubpackages()) {
      nestedNamespace.add(EcoreAdapterFactory.INSTANCE.createNamespace(subPackage));
    }

    return nestedNamespace;
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractNamespace#getNestingNamespace()
   */
  @Override
  public Namespace getNestingNamespace() {
    EPackage eSuperPackage = ePackage.getESuperPackage();
    return eSuperPackage != null ? EcoreAdapterFactory.INSTANCE.createNamespace(eSuperPackage)
        : null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractNamespace#getOwnedTypeImpl()
   */
  @Override
  public List<Type> getOwnedType() {
    List<Type> ownedType = new ArrayList<Type>();

    for (EClassifier eClassifier : ePackage.getEClassifiers()) {
      ownedType.add(EcoreAdapterFactory.INSTANCE.createType(eClassifier));
    }

    return ownedType;
  }

}
