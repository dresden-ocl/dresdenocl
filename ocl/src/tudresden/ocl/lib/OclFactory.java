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
// FILE: d:/java/classes/de/tudresden/ocl/OclFactory.java

package tudresden.ocl.lib;
import java.util.*;

/** A interface defining operations expected of a OCL representation
 *  factory class. Such a factory can transform any piece of application data
 *  (Java basic values and objects) into the corresponding representation for
 *  the OCL class library. This interface has to be implemented directly or,
 *  more usually, through <code>DefaultOclFactory</code>, to adapt the class
 *  library to "different" application code. Application code can be "different"
 *  for example in the way it implements non-unary associations.
 *
 *  <p>Such an adaption may be necessary if the class library is used with an
 *  other code generator, but
 *  usually the implementation given in <code>DefaultOclFactory</code>
 *  should be sufficient.
 *
 *  @see DefaultOclFactory
 *  @author Frank Finger
 */
public interface OclFactory {

  /** A sensible implementing class will implement this operation
   *  using the <CODE>instanceof</CODE> operator to return the correct
   *  OCL representation for the object depending on its type.
   *  Collections, Strings, and application objects will propably result in
   *  OclCollection, OclString and OclAny respectively. It might be necessary
   *  to take special care of arrays of different type.
   *
   *  @see OclAny
   *  @see OclCollection
   *  @see OclString
   */
  public OclRoot getOclRepresentationFor(Object o);

  /** In contrast to all other methods, this one is restricted to return
   *  <CODE>OclBoolean</CODE> objects.
   */
  public OclBoolean getOclRepresentationFor(boolean b);

  public OclRoot getOclRepresentationFor(byte b);

  public OclRoot getOclRepresentationFor(short s);

  public OclRoot getOclRepresentationFor(int i);

  public OclRoot getOclRepresentationFor(long l);

  public OclRoot getOclRepresentationFor(float f);

  public OclRoot getOclRepresentationFor(double d);

  public OclRoot getOclRepresentationFor(char c);

  /** @see DefaultOclFactory#getOclStateFor(String s)
   */
  public OclState getOclStateFor(String s);

  /** re-transform oclObject into an object of an application type; an
   *  implementing class should adapt this method to the transformation
   *  implemented in other methods of this class
   *
   *  @return an object of type targetType
   */
  public abstract Object reconvert(Class targetType, OclRoot oclObject);

  /** @return an OclSequence representation for the given Object
   */
  public OclSequence getOclSequenceFor(Object o);

} /* end interface OclFactory */

