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
package tudresden.ocl20.pivot.modelbus;

import java.util.List;

import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * 
 * 
 * @author Matthias Braeuer
 * @version 1.0 30.03.2007
 */
public interface IModel {

  /**
   * Returns a name that can be used to render a visual representation of the model. This may be the
   * file name or another characteristic string.
   * 
   * @return a <code>String</code> that identifies this model
   */
  String getDisplayName();

  /**
   * Returns the {@link IMetamodel metamodel} for this model.
   * 
   * @return an <code>IMetamodel</code> instance
   */
  IMetamodel getMetamodel();

  /**
   * Returns the root {@link Namespace namespace} in the model. This <code>Namespace</code>
   * represents the root of the model hierachy. It is not itself part of the model, but merely
   * serves as a container for other model elements (including other namespaces and
   * {@link Type types}). In particular, this namespace is not taken into account when looking for
   * namespaces and types using {@link #findNamespace(List)} or {@link #findType(List)},
   * respectively.
   * 
   * @return a <code>Namespace</code> instance
   * 
   * @throws ModelAccessException if the root namespace cannot be retrieved from the model
   */
  Namespace getRootNamespace() throws ModelAccessException;

  /**
   * This operation allows to find a {@link Type} anywhere in the corresponding model. It is an
   * additional operation defined in the OCL Specification, Section 12.12. The specification states
   * that "the pathName need not be a fully qualified name (it may be), as long as it can uniquely
   * identify the [type] somewhere in the [..] model. If a [type] name occurs more than once, it
   * needs to be qualified with its owning [namespace] (recursively) until the qualified name is
   * unique. If more than one [type] is found, the operation returns [null]" (UML-specific
   * references in the quotation have been adapted to fit the Pivot Model).
   * 
   * @param pathName a path name that uniquely identifies a type
   * 
   * @return a <code>Type</code> instance or <code>null</code>
   * 
   * @throws ModelAccessException if an error occurs when accessing the adapted model
   */
  Type findType(List<String> pathName) throws ModelAccessException;

  /**
   * This operation allows to find a {@link Namespace} anywhere in the corresponding model. It is an
   * additional operation defined in the OCL Specification, Section 12.12. The path name needs to be
   * fully qualified. If no namespace with this path name is found, <code>null</code> is returned.
   * An empty path name results in the {@link #getRootNamespace() root namespace}.
   * 
   * @param pathName a fully qualified name identifying a namespace
   * 
   * @return a <code>Namespace</code> instance or <code>null</code>
   * 
   * @throws ModelAccessException if an error occurs when accessing the adapted model
   */
  Namespace findNamespace(List<String> pathName) throws ModelAccessException;

  /**
   * @return
   */
  IModelFactory getFactory();

  /**
   * @return
   */
  IOclLibraryProvider getOclLibraryProvider();

  /**
   * @return
   */
  IModelInstanceProvider getModelInstanceProvider();

}
