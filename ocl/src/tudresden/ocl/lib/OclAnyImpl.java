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

  /** A public constructor for OclAnyImpl.
   *  Applications using the OCL class library should use the factory methods
   *  of the class Ocl to create instances of OclAnyImpl.
   */
  public OclAnyImpl(Object o) {
    applicationObject=o;
  }

  /** constructor for the undefined OclAnyImpl */
  public OclAnyImpl(int dummy, String reason) {
    super(dummy, reason);
  }

  /** two OclAnyImpl objects are equal if their encapsulated application objects
   *  are identical (NOT equal)
   */
  public OclBoolean isEqualTo(Object o) {
    if ( !(o instanceof OclAnyImpl) ) {
      System.out.println("OclAnyImpl isEqualTo() is called with a non-OclAnyImpl parameter");
      return OclBoolean.FALSE;
    }
    OclAnyImpl other=(OclAnyImpl)o;
    if (isUndefined())
      return new OclBoolean(0,getUndefinedReason());
    else if(other.isUndefined())
      return new OclBoolean(0,other.getUndefinedReason());
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

  /**
     Calls getFeatureQualified without qualifiers.
     @see #getFeatureQualified
  */
  public OclRoot getFeature(String attributeName) 
  {
    return getFeatureQualified(attributeName, null);
  }
  
  /** The attributes of application objects can be queried through this method.
   *  Due to restrictions of the Java language, only <CODE>public</CODE> fields
   *  can be queried.
   *
   *  @param attributeName the name of the feature, as a java.lang.String
   */
  public OclRoot getFeatureQualified(String attributeName, Object qualifier) {
    if (isUndefined()) 
      return this; // no need to create a new one
    if (applicationObject==null) {
      return new OclAnyImpl(0,"feature "+attributeName+" of null-object requested");
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
        String message=
          "non-existent field "+attributeName+" of object \""+applicationObject+
          "\" ("+applicationObject.getClass()+") queried";
        if (! Ocl.TOLERATE_NONEXISTENT_FIELDS) {
          throw new OclException(message);
        } else {
          return new OclAnyImpl(0,message);
        }
      } else {
				if(featurelistener!=null)
					featurelistener.onField(f, applicationObject);
				f.setAccessible(true);
				Object feature=f.get(applicationObject);

        if(qualifier!=null)
        {
          if(feature instanceof Map)
          {
            feature=((Map)feature).get(qualifier);
            if(feature==null)
            {
              return new OclAnyImpl(0,
                "field "+attributeName+" of object \""+applicationObject+
                "\" ("+applicationObject.getClass()+") contains no object qualified by \""+qualifier+'"');
            }
          }
          else
            throw new OclException("object "+feature+" cannot be qualified by "+qualifier);
        }
           
        if(feature!=null) 
          return Ocl.getOclRepresentationFor(feature);
        else 
        {
          OclRoot oclrep=Ocl.getOclRepresentationForNull(f.getType());
          if(oclrep!=null)
    	      return oclrep;
	        else 
          {
            return new OclAnyImpl(0,
              "field "+attributeName+" of object \""+applicationObject+
              "\" ("+applicationObject.getClass()+") is null");
          }
        }
      }
    } catch (IllegalAccessException iae) {
      return new OclAnyImpl(0,"tried to query non-public field "+attributeName+" of object "+applicationObject);
    }
  }

  public static Method findMethod(Class myclass, String name, Class[] params)
  {
    // this is very similar to tudresden.ocl.check.types.ClassAny.navigateParameterized
    // if you find a bug here, it it probably there as well.
    for(Class iclass=myclass; iclass!=null; iclass=iclass.getSuperclass())
    {
      Method[] methods=iclass.getDeclaredMethods();
      Method foundmethod=null;
      for(int i=0; i<methods.length; i++)
      {
        if(!name.equals(methods[i].getName()))
          continue;
        Class[] methodparams=methods[i].getParameterTypes();
        if(params.length!=methodparams.length)
          continue;
        for(int j=0; j<methodparams.length; j++)
          if(!methodparams[j].isAssignableFrom(params[j]))
            continue;
        if(foundmethod==null)
          foundmethod=methods[i];
        else
          throw new OclException("ambigious method "+name+" of "+myclass+") queried.");
      }
      if(foundmethod!=null)
        return foundmethod;
    }
    return null;
  }

  /** To call side-effect free methods of an object, invoke this method with
   *  the method name as String and the appropriate parameters. This method
   *  does not enforce that only side-effect free methods are called.
   *
   *  <p>If an exception occurs in the called method or in
   *  accessing it, an undefined value is returned.
   */
  public OclRoot getFeature(String methodName, Object[] params) {
    if(isUndefined())
      return this;
    if(applicationObject==null)
      return new OclAnyImpl(0,"OclAnyImpl.getFeature for null-Object called");

    try {
      if(params==null)
        params=new Object[0];
    
      Class[] paramTypes=new Class[params.length];
      for(int i=0; i<params.length; i++) 
        paramTypes[i]=params[i].getClass();

      Method foundmethod=findMethod(applicationObject.getClass(), methodName, paramTypes);

      if (foundmethod==null) {
        String message=
          "non-existent method "+methodName+" of object \""+applicationObject+
          "\" ("+applicationObject.getClass()+") queried";
        if (! Ocl.TOLERATE_NONEXISTENT_FIELDS) {
          throw new OclException(message);
        } else {
          return new OclAnyImpl(0,message);
        }
      } else {
        if(featurelistener!=null)
          featurelistener.onMethod(foundmethod, applicationObject);
        foundmethod.setAccessible(true);
        Object feature=foundmethod.invoke(applicationObject, params);
        return Ocl.getOclRepresentationFor(feature);
      }
    }
    catch (IllegalArgumentException iae) {
      return new OclAnyImpl(0,
        "tried to invoke method "+methodName+" of object "+applicationObject+
        "with illegal parameters"
      );
    }
    catch (InvocationTargetException ite) {
      return new OclAnyImpl(0,
        "an exception occured as result of the invokation of the method "
        +methodName+" of object "+applicationObject
      );
    }
    catch (IllegalAccessException iae) {
      return new OclAnyImpl(0,
        "tried to invoke non-public method "+methodName+" of object "+applicationObject
      );
    }
  }

  public OclSequence getFeatureAsSequence(String name) {
    if(isUndefined())
      return new OclSequence(0,getUndefinedReason());
    if(applicationObject==null)
      return new OclSequence(0,"OclAnyImpl.getFeatureAsSequence for null-Object called");
    
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
        return new OclSequence(0,
          "non-existent field "+name+" of object "+applicationObject+
          " queried"
        );
      } else {
        if(featurelistener!=null)
          featurelistener.onField(f, applicationObject);
        Object feature=f.get(applicationObject);
        if (feature==null) {
          return new OclSequence(0,"non-existent field "+name+" of object "+applicationObject+
            " is null"
        );
        } else {
          return Ocl.getOclSequenceFor(feature);
        }
      }
    } catch (IllegalAccessException iae) {
      return new OclSequence(0,
        "tried to query non-public field "+name+" of object "+applicationObject
      );
    }
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
    if(isUndefined()) 
      return new OclType(0,getUndefinedReason());
    return new OclType(applicationObject.getClass());
  }

  /** @return the application object encapsulated by this OclAnyImpl
   */
  public Object getObject() {
    return applicationObject;
  }


  private static FeatureListener featurelistener=null;
  
  public static void setFeatureListener(FeatureListener f)
  {
    if(f==null)
      throw new RuntimeException();
    if(featurelistener!=null)
      throw new RuntimeException();
    featurelistener=f;
  }
  
  public static void clearFeatureListener()
  {
    if(featurelistener==null)
      throw new RuntimeException();
    featurelistener=null;
  }
  
} /* end class OclAnyImpl */

