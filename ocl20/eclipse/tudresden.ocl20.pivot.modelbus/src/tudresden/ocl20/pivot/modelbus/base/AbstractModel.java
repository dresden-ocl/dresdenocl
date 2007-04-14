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
package tudresden.ocl20.pivot.modelbus.base;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import tudresden.ocl20.pivot.modelbus.IMetamodel;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelFactory;
import tudresden.ocl20.pivot.modelbus.IOclLibraryProvider;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.internal.ModelFactory;
import tudresden.ocl20.pivot.modelbus.internal.OclLibraryProvider;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * Abstract base implementation of the {@link IModel} interface.
 * 
 * @author Matthias Braeuer
 * @version 1.0 03.04.2007
 */
public abstract class AbstractModel implements IModel {

  /**
   * Logger for this class
   */
  private static final Logger logger = Logger.getLogger(AbstractModel.class);

  // a cached instance of the OCL Library provider
  private IOclLibraryProvider oclLibraryProvider;

  // cached instance of the model factory
  private IModelFactory modelFactory;

  // this model's name as displayed to clients
  private String displayName;
  
  // the metamodel of this model
  private IMetamodel metamodel;

  /**
   * Constructor to be called by subclasses. The <code>displayName</code> is a name that should be
   * used to identify this model in a graphical user interface. This may be the file name or another
   * identifier.
   * 
   * @param displayName a name for this model
   * @param metamodel the metamodel for this model
   */
  protected AbstractModel(String displayName, IMetamodel metamodel) {
    
    // use an empty string if display name is null
    this.displayName = StringUtils.defaultString(displayName);
    
    if (metamodel == null) {
      throw new IllegalArgumentException("The metamodel reference must not be null."); //$NON-NLS-1$
    }
    
    this.metamodel = metamodel;
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModel#getDisplayName()
   */
  public String getDisplayName() {
    return displayName;
  }

  
  /* (non-Javadoc)
   * @see tudresden.ocl20.pivot.modelbus.IModel#getMetamodel()
   */
  public IMetamodel getMetamodel() {
    return metamodel;
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModel#getFactory()
   */
  public IModelFactory getFactory() {

    // lazily create the model factory
    if (modelFactory == null) {
      modelFactory = new ModelFactory(this);
    }

    return modelFactory;
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModel#getOclLibraryProvider()
   */
  public IOclLibraryProvider getOclLibraryProvider() {

    // lazily create an OCL Library provider
    if (oclLibraryProvider == null) {
      oclLibraryProvider = new OclLibraryProvider();
    }

    return oclLibraryProvider;
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModel#findNamespace(java.util.List)
   */
  public Namespace findNamespace(List<String> pathName) throws ModelAccessException {
    if (logger.isDebugEnabled()) {
      logger.debug("findNamespace(pathName=" + pathName + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    Namespace namespace;

    if (pathName == null || pathName.size() == 0) {
      throw new IllegalArgumentException("The path name must not be null or empty."); //$NON-NLS-1$
    }

    // start with the top namespace
    namespace = getRootNamespace();

    for (String namespaceName : pathName) {
      namespace = namespace.lookupNamespace(namespaceName);

      if (namespace == null) {
        break;
      }
    }

    if (logger.isDebugEnabled()) {
      logger.debug("findNamespace() - exit - return value=" + namespace); //$NON-NLS-1$
    }

    return namespace;
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModel#findType(java.util.List)
   */
  public Type findType(List<String> pathName) throws ModelAccessException {
    if (logger.isDebugEnabled()) {
      logger.debug("findType(pathName=" + pathName + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    if (pathName == null || pathName.size() == 0) {
      throw new IllegalArgumentException("The path name must not be null or empty."); //$NON-NLS-1$
    }

    Type type;
    
    // find all types that match this path name
    List<Type> types = findTypeHere(getRootNamespace(),pathName,true);
    
    // check if several types were found
    if (types.size() > 1) {
      logger.warn("More than one type with path name " + pathName + " were found: " + types);  //$NON-NLS-1$//$NON-NLS-2$
      type = null;
    }
    
    else if (types.size() == 0) {
      type = null;
    }
    
    else {
      type = types.get(0);
    }
    
    if (logger.isDebugEnabled()) {
      logger.debug("findType() - exit - return value=" + type); //$NON-NLS-1$
    }
    
    return type;
  }

  /**
   * Helper method that recursively looks for a type with the given path name in the given
   * namespace.
   * 
   * @param namespace the <code>Namespace</code> to start the search in
   * @param pathName the path name to look for
   * @param searchAllNestedNamespaces indicates whether a recursive search in all nested namespaces
   *          with the full path name is required (for non-fully-qualified path names)
   * 
   * @return
   */
  protected List<Type> findTypeHere(Namespace namespace, List<String> pathName,
      boolean searchAllNestedNamespaces) {
    if (logger.isDebugEnabled()) {
      logger.debug("findTypeHere(namespace=" + namespace + ", pathName=" + pathName //$NON-NLS-1$ //$NON-NLS-2$
          + ", searchAllNestedNamespaces=" + searchAllNestedNamespaces + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    List<Type> types = new LinkedList<Type>();

    // try all nested namespaces with the given path name (in case it was not fully qualified)
    if (searchAllNestedNamespaces) {
      for (Namespace nestedNamespace : namespace.getNestedNamespace()) {
        types.addAll(findTypeHere(nestedNamespace,pathName,true));
      }
    }

    // get the first path segment
    String firstPathSegment = pathName.get(0);

    // look for a type in this namespace if no more name segments left
    if (pathName.size() == 1) {
      Type type = namespace.lookupType(firstPathSegment);

      if (type != null) {
        types.add(type);
      }
    }

    // else recursively look in nested namespace
    else {
      namespace = namespace.lookupNamespace(firstPathSegment);

      if (namespace != null) {
        types.addAll(findTypeHere(namespace,pathName.subList(1,pathName.size()),false));
      }
    }

    if (logger.isDebugEnabled()) {
      logger.debug("findTypeHere() - exit - return value=" + types); //$NON-NLS-1$
    }
    
    return types;
  }

}
