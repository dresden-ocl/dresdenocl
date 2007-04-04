/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Matthias Braeuer (braeuer.matthias@web.de).            *
 * All rights reserved.                                                      *
 *                                                                           *
 * This work was done as a project at the Chair for Software Technology      *
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
package tudresden.ocl20.pivot.pivotmodel.base;

import java.util.ArrayList;
import java.util.List;

import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.impl.NamespaceImpl;

/**
 * This class is meant as a base class for DSL- and/or repository-specific implementations of the
 * Pivot Model {@link Namespace} concept. It defines skeletons for the most essential operations
 * that need to be supported by subclasses. These methods mostly forward to abstract versions
 * suffixed with 'Impl' and combine the result with the contents of the EMF-managed fields from the
 * superclass. Thus, it is possible to create a view of the domain-specific repository with
 * additional transient elements contributed via the <code>add</code> methods in the superclass.
 * Clients should override the abstract methods and return appropriate adapters for the elements
 * corresponding to the respective Pivot Model concepts. This class currently does not use any
 * caching or other performance optimizations in order to support repositories with alternating
 * content. Clients may implement their own caching behavior or override the implementation in this
 * class altogether. Of course, an entirely new implementation of the <code>Namespace</code>
 * interface is possible as well.
 * 
 * @author Matthias Braeuer
 * @version 1.0 29.03.2007
 */
public abstract class AbstractNamespace extends NamespaceImpl implements Namespace {

  /**
   * Subclasses should return the name of the adapted {@link Namespace} concept.
   */
  @Override
  public abstract String getName();

  /**
   * Subclasses should return an adapter for the {@link Namespace} that contains this
   * <code>Namespace</code>.
   */
  @Override
  public abstract Namespace getNestingNamespace();

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.impl.NamespaceImpl#getNestedNamespace()
   */
  @Override
  public List<Namespace> getNestedNamespace() {
    List<Namespace> nestedNamespace = new ArrayList<Namespace>();
    nestedNamespace.addAll(getNestedNamespaceImpl());
    nestedNamespace.addAll(getNestedNamespaceGen());
    return nestedNamespace;
  }

  /**
   * Subclasses should return a list of adapters for their nested {@link Namespace}s.
   */
  protected abstract List<Namespace> getNestedNamespaceImpl();

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.impl.NamespaceImpl#getOwnedType()
   */
  @Override
  public List<Type> getOwnedType() {
    List<Type> ownedType = new ArrayList<Type>();
    ownedType.addAll(getOwnedTypeImpl());
    ownedType.addAll(getOwnedTypeGen());
    return ownedType;
  }

  /**
   * Subclasses should return a list for adapters for the {@link Type}s contained in this
   * <code>Namespace</code>.
   */
  protected abstract List<Type> getOwnedTypeImpl();

}
