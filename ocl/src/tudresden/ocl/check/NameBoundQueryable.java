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
// FILE: d:/projekte/diplom/classes/tudresden/ocl/parser/types/NameBoundQueryable.java

package tudresden.ocl.check;

import java.util.*;
import tudresden.ocl.parser.node.*;

/** A interface defining operations necessary for simple normalization steps.
 *  It allows to ask if a name is bound to some OCL variable (self is
 *  considered a variable) and to query the default context (i.e. "self"
 *  or some iterator).
 *
 *  @author Frank Finger
 */
public interface NameBoundQueryable extends Switch {

  /** @param  NameSpace an object denoting the name space (operation or
   *          variable names)
   *  @return true if the given String denotes a OCL variable in the given
   *          node and namespace
   */
  public boolean isNameBound(String name, Node node);

  /** @return all names that are bound in the given node
   */
  public HashSet getBoundNames(Node n);

  /** @return a String representing the default context in the given node,
   *          which is either "self" or the name of an iterator; returns null
   *          if there is no default context, e.g. the iterator is not named
   */
  public String getDefaultContext(Node n);

  /** notify the NameBoundQueryable that the ASTs subtree starting at the
   *  node given as parameter may have changed
   */
  public void changeNotify(Node subtree);

} /* end interface NameBoundQueryable */

