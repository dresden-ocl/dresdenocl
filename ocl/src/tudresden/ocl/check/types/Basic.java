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
// FILE: d:/projekte/diplom/classes/tudresden/ocl/check/types/Basic.java
package tudresden.ocl.check.types;
import java.util.*;
import tudresden.ocl.check.OclTypeException;
/** Instances of this class are used to represent the OCL types
 *  Integer, Real, Boolean, String, and Enumeration.
 *
 *  @author Frank Finger
 */
public class Basic implements Any {

  public static final Basic INTEGER=new Basic("Integer");
  public static final Basic REAL=new Basic("Real");
  public static final Basic BOOLEAN=new Basic("Boolean");
  public static final Basic STRING=new Basic("String");
  public static final Basic ENUMERATION=new Basic("Enumeration");
  public static final Basic OCLANY=new Basic("OclAny");
  protected String name;
  private Basic(String name) {
    this.name=name;
  }
  public Type navigateQualified(String name, Type[] qualifiers) {
    if (qualifiers!=null && qualifiers.length>0) {
      throw new OclTypeException(
        "feature \""+name+"\" of type "+this+" cannot be accessed with qualifier"
      );
    }
    Type ret=null;
    ret=navigateAnyQualified(name, this, qualifiers);
    if (ret==null) {
      if (this==REAL) {
        ret=navigateReal(name);
      } else if (this==INTEGER) {
        ret=navigateInteger(name);
        if (ret==null) ret=navigateReal(name);
      } else if (this==STRING) {
        ret=navigateString(name);
      }
    }
    // else branch for ENUMERATION not necessary, doesn't define anything
    // beyond OclAny properties handled in relational_expression (=, <>)
    if (ret==null) {
      throw new OclTypeException(
        "nonexistent feature \""+name+"\" of type "+this+" accessed"
      );
    }
    return ret;
  }

  public Type navigateParameterized(String name, Type[] params) {
    Type ret=null;
    ret=navigateAnyParameterized(name, params);
    if (ret==null) {
      if (this==REAL) {
        ret=navigateReal(name, params);
      } else if (this==INTEGER) {
        ret=navigateInteger(name, params);
        if (ret==null) ret=navigateReal(name, params);
      } else if (this==STRING) {
        ret=navigateString(name, params);
      }
    }
    if (ret==null) {
      throw new OclTypeException(
        "nonexistent feature \""+name+"\"(...) of type "+this+" accessed"
      );
    }
    return ret;
  }

  public boolean conformsTo(Type t) {
    if (this==INTEGER && t==REAL) return true;
    return t==this;
  }

  public boolean hasState(String stateName) {
    return false;
  }

  public String toString() {
    return name;
  }

  protected Type navigateReal(String name) {
    if (name.equals("abs")) {
      return REAL;
    } else if (name.equals("floor")) {
      return INTEGER;
    } else if (name.equals("round")) {
      return INTEGER;
    } else {
      return null;
    }
  }

  protected Type navigateReal(String name, Type[] params) {
    if (name.equals("max") && params.length==1 && params[0]==REAL) {
      return REAL;
    } else if (name.equals("min") && params.length==1 && params[0]==REAL) {
      return REAL;
    } else {
      return null;
    }
  }

  protected Type navigateInteger(String name) {
    if (name.equals("abs")) {
      return INTEGER;
    } else {
      return null;
    }
  }

  protected Type navigateInteger(String name, Type[] params) {
    if (name.equals("div") && params.length==1 && params[0]==INTEGER) {
      return INTEGER;
    } else if (name.equals("mod") && params.length==1 && params[0]==INTEGER) {
      return INTEGER;
    } else if (name.equals("max") && params.length==1 && params[0]==INTEGER) {
      return INTEGER;
    } else if (name.equals("min") && params.length==1 && params[0]==INTEGER) {
      return INTEGER;
    } else {
      return null;
    }
  }

  protected Type navigateString(String name) {
    if (name.equals("size")) {
      return INTEGER;
    } else if (name.equals("toUpper")) {
      return STRING;
    } else if (name.equals("toLower")) {
      return STRING;
    } else {
      return null;
    }
  }

  protected Type navigateString(String name, Type[] params) {
    if (name.equals("concat") && params.length==1 && params[0]==STRING) {
      return STRING;
    } else if (name.equals("substring") && params.length==2 &&
               params[0]==INTEGER && params[1]==INTEGER) {
      return STRING;
    } else {
      return null;
    }
  }

  public static Type navigateAnyQualified(String name, Any any, Type[] qualifiers) {
    /* uncommenting these lines allows evaluation of oclType
      if (name.equals("oclType")) {
        return new OclType(any);
      }
    */
    if (name.equals("oclIsNew") && qualifiers==null) {
      return Basic.BOOLEAN;
    }
    return null;
  }

  public static Type navigateAnyParameterized(String name, Type[] params) {
    if (params.length!=1) return null;
    if (name.equals("oclIsKindOf") && params[0] instanceof OclType) {
      return Basic.BOOLEAN;
    } else if (name.equals("oclIsTypeOf") && params[0] instanceof OclType) {
      return Basic.BOOLEAN;
    } else if (name.equals("oclAsType") && params[0] instanceof OclType) {
      OclType ot=(OclType)params[0];
      return ot.getType();
    } else if (name.equals("oclInState") && params[0]==Basic.STRING) {
      return Basic.BOOLEAN;
    }
    return null;
  }



} /* end class Basic */
