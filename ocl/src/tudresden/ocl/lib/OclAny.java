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
// FILE: d:/java/classes/de/tudresden/ocl/OclAny.java

package tudresden.ocl.lib;
import java.util.*;

/** This class represents the part of the OCL type OclAny common to basic
 *  types and application types, i.e. the availablity of certain properties.
 */
public abstract class OclAny implements OclRoot {

  public static OclAny UNDEFINED=OclAnyImpl.UNDEFINED;

  public abstract OclBoolean isEqualTo(Object o);

  /** @return the negated result of isEqualTo
   */
  public OclBoolean isNotEqualTo(Object o) {
    return isEqualTo(o).not();
  }

  /** This method returns true if the OclType given as parameter is a type
   *  of the object whose method is called or a supertype of such a type.
   */
  public OclBoolean oclIsKindOf(OclType t) {
    if (isUndefined() || t.isUndefined()) return OclBoolean.UNDEFINED;
    if (t.equals(oclType())) return OclBoolean.TRUE; // just for performance ...
    return oclType().allSupertypes().includes(t);
  }

  /** This method returns true if the OclType given as parameter is a "type"
   *  of this object. "Type" is to be understood in the UML/OCL sense of the
   *  word, meaning that a Java object has exactly one type (its class) and
   *  this types supertypes are not types of the object.
   */
  public OclBoolean oclIsTypeOf(OclType t) {
    if (isUndefined() || t.isUndefined()) return OclBoolean.UNDEFINED;
    if (t.equals(oclType())) {
      return OclBoolean.TRUE;
    } else  {
      return OclBoolean.FALSE;
    }
  }

  /** This property is no longer present in OCL 1.3. In spite of this, the
   *  library contains this method. The problems that lead to its cancellation
   *  do not occur in this Java implementation.
   */
  public OclType oclType() {
    if (isUndefined()) return OclType.UNDEFINED;
    return OclType.typeAny;
  }

  public OclAny oclAsType(OclType t) {
    return this;
  }

  /** This operation is implemented to access application object attributes in
   *  <CODE>OclAnyImpl</CODE> and to throw exceptions in the basic types.
   */
  public abstract OclRoot getFeature(String attributeName);

  /** Please consult the documentation of <CODE>OclRoot.getFeatureAsCollection
   *  </CODE> for a detailed explanation.
   *
   *  @see OclRoot#getFeatureAsCollection(String name)
   */
  public OclCollection getFeatureAsCollection(String name) {
    if (isUndefined()) return OclSet.UNDEFINED;
    OclRoot or=getFeature(name);
    if (or instanceof OclCollection) {
      return (OclCollection) or;
    } else {
      if (or.isUndefined()) {
        return OclSet.getEmptyOclSet();
      } else {
        HashSet set=new HashSet();
        set.add(or);
        return new OclSet(set);
      }
    }
  }

  public OclBoolean oclInState(OclState state) {
    return Ocl.objectInState(this, state);
  }

} /* end class OclAny */

