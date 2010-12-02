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

package tudresden.ocl.check.types;

/** This interface is required for compile-time type checking with the class
 *  ReflectionFacade. It is comparable to tudresden.ocl.lib.OclFactory but
 *  operates on meta level. In many cases consistency between the used
 *  implementation of this interface and the implementation of OclFactory
 *  will be desired.
 *
 *  <p>
 *  A homomorphism between OCL types, the type Boolean and the relation
 *  <i>oclConformsTo:OCL-Type, OCL-Type -&gt; Boolean</i> on the one hand
 *  and Java types, the type Boolean and
 *  <i>javaConformsTo:Java-Type, Java-Type -&gt; Boolean</i> on the other hand
 *  is necessary
 *  for <code>ReflectionFacade</code> to work properly; it may be broken only for
 *  the Java type <i>Integer</i> is mapped to which does not need to conform
 *  to the Java type <i>Real</i> is mapped to.
 *
 *  @see tudresden.ocl.lib.OclFactory
 *  @see ReflectionFacade
 */
public interface ReflectionAdapter {

  /*
   *  @return the Type object corresponding to the given class (e.g.
   *          tudresden.ocl.check.types.Basic.INTEGER for Integer.TYPE) if
   *          the class implements a predefined OCL type, or
   *          <code>null</code> for application types
   */
  public Type getTypeForClass(Class c);

  /**
     Determines, whether the given class is a map.
     Maps may have a @key-type tag.
  */
  public boolean isMap(Class c);

}
