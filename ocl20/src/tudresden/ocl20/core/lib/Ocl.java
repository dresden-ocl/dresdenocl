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

/* redesigned for decoupling from Java Reflection.  
 *
 *
 */

// FILE: d:/java/classes/de/tudresden/ocl/Ocl.java

package tudresden.ocl20.lib;
import java.util.*;

/**
 * This class provides static methods for type casting instances of OclRoot.
 *This methods must be used for this purpose to achieve proper handling of OclUndefined.
 */
public final class Ocl {

  // -------------------- converter methods -----------------------------------

  /** use this method instead of a Java cast to downcast objects of type
   *  OclRoot to OclSizable;
   *  if casting fails, this method will return an appropriate undefined
   *  value.
   */
  public static OclSizable toOclSizable(OclRoot or) {
    if(or.isUndefined())
      return new OclString(0,or.getUndefinedReason());
    try {
      return (OclSizable) or;
    } catch (ClassCastException e) {
      return new OclSequence(0,"tried to cast "+or+" to OclSizable");
    }
  }

  /** use this method instead of a Java cast to downcast objects of type
   *  OclRoot to OclCollection;
   *  if casting fails, this method will return an appropriate undefined
   *  value.
   */
  public static OclCollection toOclCollection(OclRoot or) {
    if(or.isUndefined())
      return new OclSet(0,or.getUndefinedReason());
    try {
      return (OclCollection) or;
    } catch (ClassCastException e) {
      return new OclSequence(0,"tried to cast "+or+" to OclCollection");
    }
  }

  /** use this method instead of a Java cast to downcast objects of type
   *  OclRoot to OclSubtractable;
   *  if casting fails, this method will return an appropriate undefined
   *  value.
   */
  public static OclSubtractable toOclSubtractable(OclRoot or) {
    if(or.isUndefined())
      return new OclInteger(0,or.getUndefinedReason());
    try {
      return (OclSubtractable) or;
    } catch (ClassCastException e) {
      return new OclReal(0,"tried to cast "+or+" to OclSubtractable");
    }
  }

  /** use this method instead of a Java cast to downcast objects of type
   *  OclRoot to OclUnsortedCollection;
   *  if casting fails, this method will return an appropriate undefined
   *  value.
   */
  public static OclUnsortedCollection toOclUnsortedCollection(OclRoot or) {
    if(or.isUndefined())
      return new OclSet(0,or.getUndefinedReason());
    try {
      return (OclUnsortedCollection) or;
    } catch (ClassCastException e) {
      return new OclSet(0,"tried to cast "+or+" to OclUnsortedCollection");
    }
  }

  /** use this method instead of a Java cast to downcast objects of type
   *  OclRoot to OclSet;
   *  if casting fails, this method will return an appropriate undefined
   *  value.
   */
  public static OclSet toOclSet(OclRoot or) {
    if(or.isUndefined())
      return new OclSet(0,or.getUndefinedReason());
    try {
      return (OclSet) or;
    } catch (ClassCastException e) {
      return new OclSet(0,"tried to cast "+or+" to OclSet");
    }
  }

  /** use this method instead of a Java cast to downcast objects of type
   *  OclRoot to OclBag;
   *  if casting fails, this method will return an appropriate undefined
   *  value.
   */
  public static OclBag toOclBag(OclRoot or) {
    if(or.isUndefined())
      return new OclBag(0,or.getUndefinedReason());
    try {
      return (OclBag) or;
    } catch (ClassCastException e) {
      return new OclBag(0,"tried to cast "+or+" to OclBag");
    }
  }

  /** use this method instead of a Java cast to downcast objects of type
   *  OclRoot to OclSequence;
   *  if casting fails, this method will return an appropriate undefined
   *  value.
   */
  public static OclSequence toOclSequence(OclRoot or) {
    if(or.isUndefined())
      return new OclSequence(0,or.getUndefinedReason());
    try {
      return (OclSequence) or;
    } catch (ClassCastException e) {
      return new OclSequence(0,"tried to cast "+or+" to OclSequence");
    }
  }

  /** use this method instead of a Java cast to downcast objects of type
   *  OclRoot to OclString;
   *  if casting fails, this method will return an appropriate undefined
   *  value.
   */
  public static OclString toOclString(OclRoot or) {
    if(or.isUndefined())
      return new OclString(0,or.getUndefinedReason());
    try {
      return (OclString) or;
    } catch (ClassCastException e) {
      return new OclString(0,"tried to cast "+or+" to OclString");
    }
  }

  /** use this method instead of a Java cast to downcast objects of type
   *  OclRoot to OclBoolean;
   *  if casting fails, this method will return an appropriate undefined
   *  value.
   */
  public static OclBoolean toOclBoolean(OclRoot or) {
    if(or.isUndefined())
      return new OclBoolean(0,or.getUndefinedReason());
    try {
      return (OclBoolean) or;
    } catch (ClassCastException e) {
      return new OclBoolean(0,"tried to cast "+or+" to OclBoolean");
    }
  }

  /** use this method instead of a Java cast to downcast objects of type
   *  OclRoot to OclComparable;
   *  if casting fails, this method will return an appropriate undefined
   *  value.
   */
  public static OclComparable toOclComparable(OclRoot or) {
    if(or.isUndefined())
      return new OclInteger(0,or.getUndefinedReason());
    try {
      return (OclComparable) or;
    } catch (ClassCastException e) {
      return new OclReal(0,"tried to cast "+or+" to OclComparable");
    }
  }

  /** use this method instead of a Java cast to downcast objects of type
   *  OclRoot to OclAddable;
   *  if casting fails, this method will return an appropriate undefined
   *  value.
   */
  public static OclAddable toOclAddable(OclRoot or) {
    if(or.isUndefined())
      return new OclInteger(0,or.getUndefinedReason());
    try {
      return (OclAddable) or;
    } catch (ClassCastException e) {
      return new OclReal(0,"tried to cast "+or+" to OclAddable");
    }
  }

  /** use this method instead of a Java cast to downcast objects of type
   *  OclRoot to OclMultiplyable;
   *  if casting fails, this method will return an appropriate undefined
   *  value.
   */
  public static OclMultiplyable toOclMultiplyable(OclRoot or) {
    if(or.isUndefined())
      return new OclInteger(0,or.getUndefinedReason());
    try {
      return (OclMultiplyable) or;
    } catch (ClassCastException e) {
      return new OclReal(0,"tried to cast "+or+" to OclMultiplyable");
    }
  }

  /** use this method instead of a Java cast to downcast objects of type
   *  OclRoot to OclReal;
   *  if casting fails, this method will return an appropriate undefined
   *  value.
   */
  public static OclReal toOclReal(OclRoot or) {
    if(or.isUndefined())
      return new OclInteger(0,or.getUndefinedReason());
    try {
      return (OclReal) or;
    } catch (ClassCastException e) {
      return new OclReal(0,"tried to cast "+or+" to OclReal");
    }
  }

  /** use this method instead of a Java cast to downcast objects of type
   *  OclRoot to OclInteger;
   *  if casting fails, this method will return an appropriate undefined
   *  value.
   */
  public static OclInteger toOclInteger(OclRoot or) {
    if(or.isUndefined())
      return new OclInteger(0,or.getUndefinedReason());
    try {
      return (OclInteger) or;
    } catch (ClassCastException e) {
      return new OclInteger(0,"tried to cast "+or+" to OclInteger");
    }
  }

  /** use this method instead of a Java cast to downcast objects of type
   *  OclRoot to OclAny;
   *  if casting fails, this method will return an appropriate undefined
   *  value.
   */
  public static OclAny toOclAny(OclRoot or) {
    if(or.isUndefined())
      return new OclInteger(0,or.getUndefinedReason());
    try {
      return (OclAny) or;
    } catch (ClassCastException e) {
      throw new OclException("tried to cast "+or+" to OclAny");
    }
  }


  public static OclModelObject toOclModelObject(OclRoot or) {
    if(or.isUndefined())
      return new OclModelObject(or.getUndefinedReason());
    try {
      return (OclModelObject) or;
    } catch (ClassCastException e) {
      throw new OclException("tried to cast "+or+" to OclModelObject");
    }
  }
  
  public static OclEnumLiteral toOclEnumLiteral(OclRoot or) {
    if(or.isUndefined())
      return new OclEnumLiteral(or.getUndefinedReason());
    try {
      return (OclEnumLiteral) or;
    } catch (ClassCastException e) {
      throw new OclException("tried to cast "+or+" to OclEnumLiteral");
    }
  }
  
  public static OclTuple toOclTuple(OclRoot or) {
    if(or.isUndefined())
      return new OclTuple(or.getUndefinedReason());
    try {
      return (OclTuple) or;
    } catch (ClassCastException e) {
      throw new OclException("tried to cast "+or+" to OclTuple");
    }
  }

 


} /* end class Ocl */

