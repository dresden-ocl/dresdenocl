/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * OCL Compiler                                                      *
 * Copyright (C) 2001 Steffen Zschaler (sz9@inf.tu-dresden.de).       *
 *                                                                   *
 * This work is free software; you can redistribute it and/or        *
 * modify it under the terms of the GNU Library General Public       *
 * License as published by the Free Software Foundation; either      *
 * version 2 of the License, or (at your option) any later version.  *
 *                                                                   *
 * This work is distributed in the hope that it will be useful,      *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of    *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU *
 * Library General Public License for more details.                  *
 *                                                                   *
 * You should have received a copy of the GNU Library General Public *
 * License along with this library; if not, write to the             *
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,      *
 * Boston, MA  02111-1307, USA.                                      *
 *                                                                   *
 * To submit a bug report, send a comment, or get the latest news on *
 * this project and other projects, please visit the web site:       *
 * http://www-st.inf.tu-dresden.de/ (Chair home page) or             *
 * http://www-st.inf.tu-dresden.de/ocl/ (project home page)          *
 *                                                                   *
 * See CREDITS file for further details.                             *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package tudresden.ocl.check.types;

import tudresden.ocl.check.OclTypeException;

/**
 * This interface is version two of the {@link Type} interface. It provides an
 * additional method for more fine-grained type-checking. Implementing this
 * interface is recommended, but not mandatory. The method documentation
 * explains which Type - method will be invoked instead, if only Type is
 * implemented.
 *
 *  @see Basic
 *  @see Collection
 *  @see OclTypeException
 *
 *  @author Steffen Zschaler
 */
public interface Type2 extends Type {

  /**
   * Navigate to the result type of the query operation <i>name</i>. This may
   * throw an exception, if the specified operation is not a query. If only
   * {@link Type} is implemented, {@link Type#navigateParameterized} is called
   * instead.
   *
   * @param params the actual argument types
   */
  public Type navigateParameterizedQuery (String name, Type[] params) throws OclTypeException;
}