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
package tudresden.ocl20.pivot.modelbus.metamodel;

import tudresden.ocl20.pivot.modelbus.model.IModelProvider;

/**
 * 
 * 
 * @author Matthias Braeuer
 * @version 1.0 30.03.2007
 */
public interface IMetamodel {

  /**
   * Returns a unique identifier for this metamodel under which it can be registered in an
   * {@link IMetamodelRegistry}.
   * 
   * @return a <code>String</code> identifier
   */
  String getId();

  /**
   * Returns a human-readable and possibly translatable name of this metamodel that may be used to
   * display it in a user interface.
   * 
   * @return a <code>String</code> representing the name of the metamodel
   */
  String getName();

  /**
   * Returns the {@link IModelProvider} that can be used to load instances of this metamodel.
   * 
   * @return an <code>IModelProvider</code> instance
   */
  IModelProvider getModelProvider();

}
