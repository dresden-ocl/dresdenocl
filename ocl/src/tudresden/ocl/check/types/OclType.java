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

import tudresden.ocl.check.OclTypeException;

public class OclType implements Any {

  protected Type type;

  public OclType(Type type) {
    this.type=type;
  }

  public Type navigateQualified(String name, Type[] qualifiers) {
    if (qualifiers!=null && qualifiers.length>0) {
      throw new OclTypeException(
        "feature \""+name+"\" of type "+this+" cannot be accessed with qualifier"
      );
    }
    Type ret;
    if (name.equals("name")) {
      ret=Basic.STRING;
    } else if (name.equals("attributes")) {
      ret=new Collection(Collection.SET, Basic.STRING);
    } else if (name.equals("associationEnds")) {
      ret=new Collection(Collection.SET, Basic.STRING);
    } else if (name.equals("operations")) {
      ret=new Collection(Collection.SET, Basic.STRING);
    } else if (name.equals("supertypes")) {
      ret=new Collection(Collection.SET, new OclType(null));
    } else if (name.equals("allSupertypes")) {
      ret=new Collection(Collection.SET, new OclType(null));
    } else if (name.equals("allInstances")) {
      ret=new Collection(Collection.SET, type);
    } else {
      ret=null;
    }
    if (ret==null) {
      throw new OclTypeException("nonexistent feature "+name+" of OclType queried");
    }
    return ret;
  }

  public Type navigateParameterized(String name, Type[] params) {
    Type ret=null;
    if (ret==null) {
      throw new OclTypeException("nonexistent feature "+name+"(...) of OclType queried");
    }
    return ret;
  }

  public boolean hasState(String stateName) {
    return false;
  }

  public boolean conformsTo(Type t) {
    return (t instanceof OclType);
  }

  public Type getType() {
    return type;
  }

  public String toString() {
    return "OclType";
  }

  public boolean equals(Object o) {
    if (o instanceof OclType) {
      OclType other=(OclType)o;
      return other.type.equals(type);
    } else {
      return false;
    }
  }

  public int hashCode() {
    return type.hashCode();
  }

}



