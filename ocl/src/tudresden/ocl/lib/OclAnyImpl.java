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
import java.lang.reflect.*;

/** This class represents the OCL type OclAny. It implements the properties
 *  defined for this type and gives access to fields of application objects
 *  using Java Reflection.
 *
 *  @author Frank Finger
 */
public class OclAnyImpl extends OclAny {

  /** the application object that is encapsulated by this instance of OclAnyImpl
   */
  private Object applicationObject;
  protected boolean bUndefined;

  public static OclAnyImpl UNDEFINED=new OclAnyImpl();

  /** A public constructor for OclAnyImpl.
   *  Applications using the OCL class library should use the factory methods
   *  of the class Ocl to create instances of OclAnyImpl.
   */
  public OclAnyImpl(Object o) {
    applicationObject=o;
  }

  /** private constructor for the undefined OclAnyImpl */
  private OclAnyImpl() {
    bUndefined=true;
  }

  /** two OclAnyImpl objects are equal if their encapsulated application objects
   *  are identical (NOT equal)
   *
   *  @throws OclClassCastException if the parameter o is not of type
   *          OclAnyImpl and Ocl.STRICT_CHECKING is <CODE>true</CODE>
   */
  public OclBoolean isEqualTo(Object o) {
    if ( !(o instanceof OclAnyImpl) ) {
      if (Ocl.STRICT_CHECKING) {
        throw new OclClassCastException(
          "OclAnyImpl isEqualTo() is called with a non-OclAnyImpl parameter"
        );
      } else {
        return OclBoolean.FALSE;
      }
    }
    OclAnyImpl other=(OclAnyImpl)o;
    if (this.isUndefined() || other.isUndefined()) return OclBoolean.UNDEFINED;
    if (this.applicationObject==other.applicationObject) {
      return OclBoolean.TRUE;
    } else {
      return OclBoolean.FALSE;
    }
  }

  /** @return the negated result of <CODE>isEqualTo</CODE>
   */
  public OclBoolean isNotEqualTo(Object o) {
    return isEqualTo(o).not();
  }

  /** The attributes of application objects can be queried through this method.
   *  Due to restrictions of the Java language, only <CODE>public</CODE> fields
   *  can be queried.
   *
   *  @param attributeName the name of the feature, as a java.lang.String
   */
  public OclRoot getFeature(String attributeName) {
    if (isUndefined()) return OclAnyImpl.UNDEFINED;
    if (applicationObject==null) {
      if (Ocl.STRICT_CHECKING==true) {
        throw new OclException("feature "+attributeName+" of null-object requested");
      } else {
        return OclAnyImpl.UNDEFINED;
      }
    }
    try {
      Field f=null;
      String[] possibleNames=Ocl.getNames(attributeName);
      for (int i=0; f==null && i<possibleNames.length; i++) {
        Class searchClass=applicationObject.getClass();
        while (searchClass!=null && f==null) {
          try {
            f=searchClass.getDeclaredField(possibleNames[i]);
          } catch (NoSuchFieldException nsfe) {
            f=null;
            searchClass=searchClass.getSuperclass();
          }
        }
      }
      if (f==null) {
        if (! Ocl.TOLERATE_NONEXISTENT_FIELDS) {
          throw new OclException(
            "non-existent field "+attributeName+" of object \""+applicationObject+
            "\" ("+applicationObject.getClass()+") queried"
          );
        } else {
          return UNDEFINED;
        }
      } else {
        f.setAccessible(true);
        Object feature=f.get(applicationObject);
        if (feature==null) {
          String typeName=f.getType().getName();
          if (typeName.equals("java.lang.String")) {
            return new OclString(null);
          } else {
            return OclAnyImpl.UNDEFINED;
          }
        } else {
          return Ocl.getOclRepresentationFor(feature);
        }
      }
    } catch (IllegalAccessException iae) {
      if (Ocl.STRICT_CHECKING) {
        throw new OclException(
          "tried to query non-public field "+attributeName+" of object "+applicationObject
        );
      } else {
        return UNDEFINED;
      }
    }
  }

  /** To call side-effect free methods of an object, invoke this method with
   *  the method name as String and the appropriate parameters. This method
   *  does not enforce that only side-effect free methods are called.
   *
   *  <p>If an exception occurs in the called method or in
   *  accessing it, OclAnyImpl.UNDEFINED
   *  is returned if Ocl.STRICT_CHECKING is false. An OclExcpetion is raised
   *  if Ocl.STRICT_CHECKING is true.
   */
  public OclRoot getFeature(String methodName, Object[] params) {
    if (isUndefined() || applicationObject==null) {
      return OclAnyImpl.UNDEFINED;
    }
    try {
      Class[] paramTypes=null;
      if (params!=null) {
        paramTypes=new Class[params.length];
        for (int i=0; i<params.length; i++) {
          paramTypes[i]=params[i].getClass();
        }
      }
      
      // this is very similar to tudresden.ocl.check.types.ClassAny.navigateParameterized
      // if you find a bug here, it it probably there as well.
      Method foundmethod=null;
      classloop: for(Class iclass=applicationObject.getClass(); iclass!=null; iclass=iclass.getSuperclass())
      {
        Method[] methods=iclass.getDeclaredMethods();
        methodloop: for(int i=0; i<methods.length; i++)
        {
          if(!methodName.equals(methods[i].getName()))
            continue;
          Class[] parametertypes=methods[i].getParameterTypes();
          if(paramTypes.length!=parametertypes.length)
            continue;
          for(int j=0; j<parametertypes.length; j++)
            if(!parametertypes[j].isAssignableFrom(paramTypes[j]))
              continue methodloop;
          foundmethod=methods[i];
          break classloop;
        }
      }
      
      if (foundmethod==null) {
        if (! Ocl.TOLERATE_NONEXISTENT_FIELDS) {
          throw new OclException(
            "non-existent method "+methodName+" of object \""+applicationObject+
            "\" ("+applicationObject.getClass()+") queried"
          );
        } else {
          return UNDEFINED;
        }
      } else {
        foundmethod.setAccessible(true);
        Object feature=foundmethod.invoke(applicationObject, params);
        return Ocl.getOclRepresentationFor(feature);
      }
    }
    catch (IllegalArgumentException iae) {
      if (Ocl.STRICT_CHECKING) {
        throw new OclException(
          "tried to invoke method "+methodName+" of object "+applicationObject+
          "with illegal parameters"
        );
      } else {
        return UNDEFINED;
      }
    }
    catch (InvocationTargetException ite) {
      if (Ocl.STRICT_CHECKING) {
        throw new OclException(
          "an exception occured as result of the invokation of the method "
          +methodName+" of object "+applicationObject
        );
      } else {
        return UNDEFINED;
      }
    }
    catch (IllegalAccessException iae) {
      if (Ocl.STRICT_CHECKING) {
        throw new OclException(
          "tried to invoke non-public method "+methodName+" of object "+applicationObject
        );
      } else {
        return UNDEFINED;
      }
    }
  }

  public OclSequence getFeatureAsSequence(String name) {
    if (isUndefined() || applicationObject==null) return OclSequence.UNDEFINED;
    try {
      Field f=null;
      String[] possibleNames=Ocl.getNames(name);
      for (int i=0; f==null && i<possibleNames.length; i++) {
        try {
          f=applicationObject.getClass().getField(possibleNames[i]);
        } catch (NoSuchFieldException nsfe) {
          f=null;
        }
      }
      if (f==null) {
        if (Ocl.STRICT_CHECKING) {
          throw new OclException(
            "non-existent field "+name+" of object "+applicationObject+
            " queried"
          );
        } else {
          return OclSequence.UNDEFINED;
        }
      } else {
        Object feature=f.get(applicationObject);
        if (feature==null) {
          return OclSequence.UNDEFINED;
        } else {
          return Ocl.getOclSequenceFor(feature);
        }
      }
    } catch (IllegalAccessException iae) {
      if (Ocl.STRICT_CHECKING) {
        throw new OclException(
          "tried to query non-public field "+name+" of object "+applicationObject
        );
      } else {
        return OclSequence.UNDEFINED;
      }
    }
  }

  /** @return <CODE>true</CODE> if this instance of OclAnyImpl is the result of an undefined
   *          OCL expression
   */
  public boolean isUndefined() {
    return bUndefined;
  }

  public boolean equals(Object o) {
    try {
      return isEqualTo(o).isTrue();
    } catch (OclException e) {
      return false;
    }
  }

  public int hashCode() {
    if (applicationObject == null)
      return 0;
    else
      return applicationObject.hashCode();
  }

  public String toString() {
    String s= applicationObject==null ? "" : applicationObject.toString();
    return "OclAnyImpl<"+s+">";
  }

  /** This property is no longer present in OCL 1.3. In spite of this, the
   *  library contains this method. The problems that lead to its cancellation
   *  do not occur in this Java implementation.
   */
  public OclType oclType() {
    if (isUndefined()) return OclType.UNDEFINED;
    return new OclType(applicationObject.getClass());
  }

  /** @return the application object encapsulated by this OclAnyImpl
   */
  public Object getObject() {
    return applicationObject;
  }


} /* end class OclAnyImpl */

