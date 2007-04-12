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

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.osgi.util.NLS;

import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelInstanceProvider;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.base.AbstractModel;
import tudresden.ocl20.pivot.pivotmodel.Namespace;

/**
 * If the root of the model is a single {@link EPackage}, a corresponding {@link EcoreNamespace}
 * adapter will be created. If there are several root packages, a new "virtual" root namespace will
 * be created.
 * 
 * @author Matthias Braeuer
 * @version 1.0 12.04.2007
 */
public class EcoreModel extends AbstractModel implements IModel {

  // a logger for this class
  private static final Logger logger = Logger.getLogger(EcoreModel.class);

  // the resource containing the corresponding Ecore model
  private Resource resource;

  // the adapter for the top package of the associated Ecore model
  private Namespace topNamespace;

  /**
   * Creates a new <code>EcoreModel</code> adapting the given {@link EPackage}.
   * 
   * @param resource the recource containing the model
   * 
   */
  public EcoreModel(Resource resource) {
    super(resource.getURI().toString());

    if (logger.isDebugEnabled()) {
      logger.debug("EcoreModel(resource=" + resource + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    // initialize
    this.resource = resource;

    if (logger.isDebugEnabled()) {
      logger.debug("EcoreModel() - exit"); //$NON-NLS-1$
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModel#getModelInstanceProvider()
   */
  public IModelInstanceProvider getModelInstanceProvider() {
    // TODO: implement model instance provider
    return null;
  }

  /**
   * This method lazily creates the adapter for the root package in the associated Ecore model.
   * Thus, any possible resource loading errors will not happen until this method is called for the
   * first time.
   * 
   * @throws ModelAccessException if an error occurs when creating the adapter for the top namespace
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModel#getTopNamespace()
   */
  public Namespace getTopNamespace() throws ModelAccessException {

    if (topNamespace == null) {
      topNamespace = createTopNamespace();
    }

    return topNamespace;
  }

  /**
   * Helper method that creates the adapter for the top namespace.
   * 
   * @return a <code>Namespace</code> instance
   * 
   * @throws ModelAccessException if an error occurs while loading the adapted Ecore model
   */
  protected Namespace createTopNamespace() throws ModelAccessException {
    EPackage rootPackage;

    // load the resource
    if (!resource.isLoaded()) {

      if (logger.isInfoEnabled()) {
        logger.info(NLS.bind(EcoreModelMessages.EcoreModel_LoadingEcoreModel,resource.getURI()));
      }

      try {
        resource.load(null);
      }
      catch (IOException e) {
        throw new ModelAccessException("Error while loading resource from " + resource.getURI(),e); //$NON-NLS-1$
      }

    }
    
    // get the root packages
    List<EObject> roots = resource.getContents();

    // if there is only one root package we simply use it
    if (roots.size() == 1 && roots.get(0) instanceof EPackage) {
      rootPackage = (EPackage) roots.get(0);
    }

    // otherwise create a new package to serve as the root package
    else {
      rootPackage = EcoreFactory.eINSTANCE.createEPackage();

      // add all sub-packages to the new root package
      for (EObject object : roots) {
        if (object instanceof EPackage) {
          rootPackage.getESubpackages().add((EPackage) object);
        }
      }
    }

    return EcoreAdapterFactory.INSTANCE.createNamespace(rootPackage);
  }

  /**
   * Overridden to base equality check on the URI of the associated resource.
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {

    if (obj instanceof EcoreModel) {
      return resource.getURI().equals(((EcoreModel) obj).resource.getURI());
    }

    return false;
  }

  /**
   * Overridden to base the hash code on the URI of the associated resource.
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    return resource.getURI().hashCode();
  }

  /**
   * Returns a String representation of this <code>EcoreModel</code>.
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE).append("resource", //$NON-NLS-1$
        resource.getURI()).toString();
  }
}
