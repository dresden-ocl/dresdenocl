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
// FILE: d:/java/classes/de/tudresden/ocl/OclString.java

package tudresden.ocl.lib;
import java.util.*;

/** This class represents the basic OCL type String.
 *
 *  @author Frank Finger
 */
public class OclString extends OclAny implements OclSizable {

  public static OclString UNDEFINED=new OclString();

  /** The value of this OclString, stored as a java.lang.String. It may contain
   *  a <code>null</code> reference.
   */
  private String sValue;

  /** is this OclString the result of an undefined OCL expression? */
  private boolean bUndefined;

  /** package-visible constructor for OclString
   *
   *  @param str the String to be represented; may be <code>null</code>
   */
  public OclString(String str) {
    sValue=str;
    bUndefined=false;
  }

  /** private constructor for undefined OclString */
  private OclString() {
    bUndefined=true;
  }

  /** Two OclStrings are equal if their java.lang.String values are equal
   *  and none of them is undefined.
   */
  public OclBoolean isEqualTo(Object o) {
    if ( !(o instanceof OclString) ) {
      if (Ocl.STRICT_CHECKING) {
        throw new OclClassCastException(
          "OclString.isEqualTo() called with non-OclString parameter"
        );
      } else {
        return OclBoolean.FALSE;
      }
    } else {
      OclString os=(OclString) o;
      if (this.isUndefined() || os.isUndefined()) {
        return OclBoolean.UNDEFINED;
      }
      if ( sValue==null ) {
        return Ocl.getOclRepresentationFor(os.sValue==null);
      } else {
        if ( os.sValue.equals(this.sValue) ) {
          return OclBoolean.TRUE;
        } else {
          return OclBoolean.FALSE;
        }
      }
    }
  }

  public int hashCode() {
    return sValue.hashCode();
  }

  public boolean equals(Object o) {
    try {
      return isEqualTo(o).isTrue();
    } catch (OclException e) {
      return false;
    }
  }

  /** @return the negateted result of isEqualTo
   *  @see #isEqualTo(Object o)
   */
  public OclBoolean isNotEqualTo(Object o) {
    return isEqualTo(o).not();
  }

  /** @return the number of characters of this OclString, or 0 if the
   *          OclStrings value is <code>null</code>, or an undefined
   *          Integer value if this string is undefined
   */
  public OclInteger size() {
    if (isUndefined())
      return OclInteger.UNDEFINED;
    else {
      int i=(sValue==null) ? 0 : sValue.length();
      return new OclInteger(i);
    }
  }

  /** @return the concatenation of this OclString and the operations parameter,
   *          or the OclString with the value <code>null</code> if at least
   *          one of both has the value <code>null</code>
   */
  public OclString concat(OclString s) {
    if (this.isUndefined() || s.isUndefined()) return UNDEFINED;
    if (sValue==null || s.sValue==null)
      return new OclString(null);
    else {
      String concat=sValue.concat(s.sValue);
      return new OclString(concat);
    }
  }

  /** @return an OclString like this with all characters converted to upper case
   */
  public OclString toUpper() {
    if (isUndefined()) {
      return UNDEFINED;
    } else if (sValue==null) {
      return this;
    } else {
      String upper=sValue.toUpperCase();
      return new OclString(upper);
    }
  }

  /** @return an OclString like this with all characters converted to upper case
   */
  public OclString toLower() {
    if (isUndefined()) {
      return UNDEFINED;
    } else if (sValue==null) {
      return this;
    } else {
      String lower=sValue.toLowerCase();
      return new OclString(lower);
    }
  }

  /** @return the substring of this OclString starting at character number
   *          <code>lower</code>, up to and including character number
   *          <code>upper</code>; the first character of an OclString has
   *          the number 1
   *  @throws OclRuntimeException
   */
  public OclString substring(OclInteger lower, OclInteger upper) {
    if (isUndefined() || sValue==null) {
      return UNDEFINED;
    } else {
      try {
        int start=lower.getInt();
        int end=upper.getInt();
        String sub=sValue.substring(start-1, end);
        return new OclString(sub);
      } catch (IndexOutOfBoundsException ex) {
        return UNDEFINED;
      }
    }
  }

  /** This method either throws a runtime exception or returns an undefined
   *  value, depending on OCL.STRICT_CHECKING.
   *
   *  @see Ocl#STRICT_CHECKING
   */
  public OclRoot getFeature(String name) {
    if (Ocl.STRICT_CHECKING) {
      throw new OclException("feature "+name+" of OclString requested");
    }
    return UNDEFINED;
  }

  public boolean isUndefined() {
    return bUndefined;
  }

  public String getString() {
    return sValue;
  }

  public String toString() {
    return "OclString<"+sValue+">";
  }

  /** @see OclAny#oclIsKindOf(OclType type)
   */
  public OclBoolean oclIsKindOf(OclType type) {
    if (isUndefined()) return OclBoolean.UNDEFINED;
    return oclIsTypeOf(type).or(super.oclIsKindOf(type));
  }

  /** This property is no longer present in OCL 1.3. In spite of this, the
   *  library contains this method. The problems that lead to its cancellation
   *  do not occur in this Java implementation.
   */
  public OclType oclType() {
    if (isUndefined()) return OclType.UNDEFINED;
    return OclType.typeString;
  }

} /* end class OclString */

