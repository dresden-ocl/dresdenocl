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
// FILE: d:/projekte/diplom/classes/tudresden/ocl/check/types/TypeFactory.java

package tudresden.ocl.check.types;

import java.util.*;
import tudresden.ocl.parser.node.*;

/** The class TypeChecker depends on the package types, not only on this
 *  interface. If it is necessary to exchange the whole type system
 *  representation and not only the ModelFacade (which does not seem probable)
 *  this involves changes at least in TypeChecker and
 *  tudresden.ocl.normalize.TypeInformationInsertion.
 *
 *  Adaptions to new model information sources (such as CASE tool repositories)
 *  is easily possible without
 *  exchanging the complete type representation system, see ModelFacade.
 *
 *  @see ModelFacade
 */
public interface TypeFactory {

  public Type getBoolean();

  public Type getInteger();

  public Type getReal();

  public Type getString();

  public Type getSet();

  public Type getSet(Type param);

  public Type getBag();

  public Type getBag(Type param);

  public Type getSequence();

  public Type getSequence(Type param);

  public Type getCollection();

  public Type getCollection(Type param);

  public Type getEnumerationElement();

  /** @param ofWhichType the type that will be the element type of the set
   *         returned by <code>allInstances</code>
   */
  public Type getOclType(Type ofWhichType);

  public Type getOclAny();

  public Type get(String name);

  public boolean conforms(Type found, Type expected);

  public void assertTrue(Type found, Type expected, Node where);
} /* end class TypeFactory */

