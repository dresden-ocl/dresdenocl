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
// FILE: d:/java/classes/de/tudresden/ocl/NameAdapter.java

package tudresden.ocl.lib;
import java.util.*;

/** A case tools code generator may convert names, especially of
 *  unnamed association ends. For example, the unnamed association
 *  end connecting to a class "Person" is refered to as "person" in
 *  OCL, but may be represented as "myPerson", "thePerson" or "p0001"
 *  in the generated source code. This interface can be implemented according
 *  to the rules of a specific code generator.
 *
 *  This interface is used for attribute and association end names only, not for
 *  class or operation names.
 *
 *  @see Ocl#setNameAdapter(NameAdapter nc)
 *  @author Frank Finger
 */
public interface NameAdapter {

  /** This operation takes a name as found in an OCL expression ("person" in
   *  the example above) and returns an array of possible representation names,
   *  propably including the OCL name itself, sorted for priority.
   */
  public String[] getNames(String n);

  /** This method is used to reconvert implementation names into possible names
   *  of the corresponding association ends.
   */
  public String[] getPossibleAssociationNames(String n);


} /* end interface NameAdapter */

