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
package tudresden.ocl20.pivot.modelbus.util;

import tudresden.ocl20.pivot.essentialocl.expressions.TypeLiteralExp;
import tudresden.ocl20.pivot.modelbus.IModelInstance;


/**
 * This class contains String constants with the names of the predefined OCL primitive types.
 * These are intended to be used as hashtable keys in implementations of {@link IModelInstance}.
 * Thus, an OCL engine can find the correct primitive type if it encounters a {@link TypeLiteralExp}
 * in the abstract syntax. Ultimately, these constants should be placed in external configuration
 * files to ensure maximum flexibility when the OCL Standard Library definition changes.
 *
 * @author Matthias Braeuer
 * @version 1.0 30.03.2007
 */
public class OclPrimitiveTypes {

  /**
   * This class is not meant to be instantiated.
   */
  private OclPrimitiveTypes() {
    // no implementation necessary
  }
  
  /**
   * The name of the predefined <code>Boolean</code> type.
   */
  public static final String BOOLEAN = "Boolean"; //$NON-NLS-1$
  
  /**
   * The name of the predefined <code>String</code> type.
   */
  public static final String STRING = "String"; //$NON-NLS-1$
  
  /**
   * The name of the predefined <code>Integer</code> type.
   */
  public static final String INTEGER = "Integer"; //$NON-NLS-1$
  
  /**
   * The name of the predefined <code>Real</code> type.
   */
  public static final String REAL = "Real"; //$NON-NLS-1$
  
}
