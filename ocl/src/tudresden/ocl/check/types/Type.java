/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * OCL Compiler                                                      *
 * Copyright (C) 1999, 2000 Frank Finger (frank@finger.org).         *
 * All rights reserved.                                              *
 *                                                                   *
 * This work was done as a diploma project at the Chair for Software *
 * Technology, Dresden University Of Technology, Germany             *
 * (http://www-st.inf.tu-dresden.de).  It is understood that any     *
 * modification not identified as such is not covered by the         *
 * preceding statement.                                              *
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
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
// FILE: d:/projekte/diplom/classes/tudresden/ocl/check/Type.java

package tudresden.ocl.check.types;

import tudresden.ocl.check.OclTypeException;

/** This interface is used by the type checker to handle type representations.
 *  Classes implementing this interface will have their navigate methods return
 *  the type representation of the type of the association end with the name
 *  <I>name</I> or the type of the property <I>name</I>. In both cases the
 *  result can either be a application-defined type or a predefined type (basic
 *  type like <I>Integer</I> or collection type). Take care to return
 *  <i>Sequences</i> for navigation over ordered associations. If the type has
 *  no association end or property with the given <I>name</I>, a
 *  <CODE>OclTypeException</CODE> is thrown.
 *
 *  Classes implementing this interface should also overwrite <CODE>equals</CODE>,
 *  <CODE>toString</CODE> and
 *  <CODE>hashCode</CODE> appropriately. The <CODE>toString</CODE> method should
 *  return the type name as it would be expected in an OCL expression (e.g.
 *  &quot;Person&quot; and not things like &quot;application defined type:
 *  Person&quot;).
 *
 *
 *  @see Basic
 *  @see Collection
 *  @see OclTypeException
 *
 *  @author Frank Finger
 */
public interface Type {

  /** navigate to the association end or attribute <i>name</i>, possibly with
   *  qualifier types; unnamed association ends must by made available by
   *  implementing methods with the name of the association end's type, but
   *  beginning with a lower case character (see OCL specification for more
   *  details)
   *
   *  @param qualifiers the qualifier types; may (and will in most cases) be
   *                    <CODE>null</CODE>, but never an array with length 0
   */
  public Type navigateQualified(String name, Type[] qualifiers) throws OclTypeException;

  /** navigate to the result type of the operation <i>name</i>
   *
   *  @param params the actual argument types
   */
  public Type navigateParameterized(String name, Type[] params) throws OclTypeException;

  /** @return true if this type has a state with the given name
   */
  public boolean hasState(String name);

  /** @return true if an instance of the type represented by the called object
   *               can replace an instance of the type given as parameter
   */
  public boolean conformsTo(Type t);

  public boolean equals(Object o);

  /** It will be nessary to redefine the hashCode if equality of types is not
   *  equality of representation objects.
   *
   *  @see java.lang.Object#hashCode()
   */
  public int hashCode();

  public String toString();


} /* end interface Type */

