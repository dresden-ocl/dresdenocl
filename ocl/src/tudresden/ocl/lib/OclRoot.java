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
// FILE: d:/java/classes/de/tudresden/ocl/OclRoot.java

package tudresden.ocl.lib;
import java.util.*;

/** This interface is implemented by those classes of the library representing
 *  Predefined OCL Types, and is therefore the return type of all factory
 *  methods in <CODE>Ocl</CODE> and <CODE>OclFactory</CODE>.
 *  <CODE>OclRoot</CODE> defines operations available for all
 *  Predefined Types, i.e. tests for equality, definedness and the operation
 *  <CODE>getFeature</CODE>.
 *
 *  @see #getFeature(String s)
 *  @author Frank Finger
 */
public interface OclRoot extends Cloneable {

  /** The operation <CODE>getFeature</CODE> represents both the query of
   *  a property of an application object and the shorthand for collect,
   *  depending on the implementing class. This is to faciliate Java code
   *  generation in the case of an OCL expression like "expr.property",
   *  where it is not possible to decide without model information if
   *  "expr" denotes a Set or Bag, making the result of the expression
   *  (that is then the shorthand notation for collect)
   *  a Bag, or is it denotes a single object, making the result a Set or
   *  some other object. In both cases, a call to this method will be
   *  the correct transformation to Java.
   *
   *  @param name the name of the queried attribute
   *
   *  @see OclCollection#collect(OclIterator iter, OclRootEvaluatable eval)
   */
  public OclRoot getFeature(String name);

  /** OCL allows to treat association ends of maximal multiplicity one as
   *  Sets. This is useful to check if optional association ends
   *  (multiplicity 0..1) are navigable. Therefore, the result type of some
   *  navigation expressions can be both OclAny (in this library OclAnyImpl)
   *  and Set (OclSet in this library). Which result type is desired can only
   *  be derived from the operation applied to the result.
   *  <p>This method should be called instead of <CODE>getFeature</CODE>
   *  whenever a collection operation, easily recognizable by the "<CODE>->
   *  </CODE>", is applied to the result of a subexpression. It is implemented
   *  in <CODE>OclCollection</CODE>, <CODE>OclAny</CODE> and
   *  <CODE>OclContainer</CODE> to call
   *  <CODE>getFeature</CODE> and check if the result is an instance of
   *  <CODE>OclCollection</CODE>. If it is, it just returns the result; if
   *  not, a new <CODE>OclCollection</CODE> containing only that result is
   *  returned.
   *
   *  @see #getFeature(String s)
   */
  public OclCollection getFeatureAsCollection(String name);

  /** Returns true if this object is equal to the object given as parameter.
   *  For definitions of equality for the different types, see the
   *  implementing methods. Generally, all basic and collection objects are
   *  considered equal if they contain the same value(s), application
   *  objects are considered equal if they are identical (<CODE>==</CODE>, not
   *  <CODE>equal</CODE>).
   */
  public OclBoolean isEqualTo(Object o);

  /** Returns the negated result of <CODE>isEqualTo</CODE>.
   *
   *  @see #isEqualTo(Object o)
   */
  public OclBoolean isNotEqualTo(Object o);

  /** Returns true if this object is the result of an undefined OCL
   *  expression.
   */
  public boolean isUndefined();
  
  /**
     Returns the reason, why this undefined ocl object has been created.
     @throws RuntimeException if the onject is not undefined
  */
  public String getUndefinedReason();

} /* end interface OclRoot */

