/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * OCL Interpreter                                                   *
 * Copyright (C) 2002 Nikolai Krambrock (nikk@gmx.de)                *
 * All rights reserved.                                              *
 *                                                                   *
 * This work was done as a diploma project at the Chair for Software *
 * Construction, University Of Technology Aachen, Germany            *
 * (http://www-lufgi3.informatik.rwth-aachen.de).                    *
 * It was done in co-operation with Software & Design and Managment  *
 * Troisdorf, Germany (http://www.sdm.de)                            *
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
 * this project, please visit the project home page:                 *
 * http://dresden-ocl.sourceforge.net                                * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package tudresden.ocl.interp.types.reflect.intern;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import tudresden.ocl.check.types.Basic;
import tudresden.ocl.check.types.Collection;
import tudresden.ocl.check.types.DefaultTypeFactory;
import tudresden.ocl.check.types.ModelFacade;
import tudresden.ocl.check.types.Type;
import tudresden.ocl.check.types.TypeFactory;

import tudresden.ocl.interp.core.intern.Assert;
import tudresden.ocl.interp.core.intern.ReflectionUtil;
import tudresden.ocl.interp.types.Instance;

import tudresden.ocl.lib.Ocl;


/**
 * Implementation of the Instance interface to query the system for 
 * informations about the encapsulated objects via reflection.
 */
public class ReflectionInstance implements Instance {
  private Object applicationObject;// never null
  private ModelFacade modelFacade;// never null
  private TypeFactory typeFactory;// never null

  /**
   * @param modelFacade not null
   * @param applicationObject not null
   */
  public ReflectionInstance(ModelFacade modelFacade, Object applicationObject) {
    Assert.assertTrue(applicationObject != null);
    this.modelFacade = modelFacade;
    this.typeFactory = new DefaultTypeFactory(modelFacade);
    this.applicationObject = applicationObject;
  }

  public Type getType() {

    // FIXME: not quite sure about this types
    if (applicationObject instanceof Map) {
      return typeFactory.get("Set");
    } else if (applicationObject instanceof Set) {
      return typeFactory.get("Set");
    } else if (applicationObject instanceof java.util.Collection) {
      return typeFactory.get("Sequence");
    } else if (applicationObject instanceof Object[]) {
      return typeFactory.get("Sequence");
    }

    String className = applicationObject.getClass().getName();
    className = ReflectionUtil.toColons(className);

    // className = className.substring(className.lastIndexOf(".") + 1);
    return typeFactory.get(className);
  }

  public Object getBasicValue() {
    Assert.assertTrue(getType() instanceof Basic);
    return applicationObject;
  }

  public java.util.Collection getCollection() {
    Collection t = (Collection)getType();
    java.util.Collection newColl = null;

    if (t.getCollectionKind() == Collection.SET) {
      newColl = new HashSet();
    } else {
      Assert.assertTrue(t.getCollectionKind() == Collection.SEQUENCE);
      newColl = new ArrayList();
    }

    if (applicationObject instanceof Map) {
      java.util.Collection entries = ((Map)applicationObject).entrySet();
      for (Iterator iterator = entries.iterator(); iterator.hasNext();) {
        newColl.add(new ReflectionInstance(modelFacade, 
                                           ((Map.Entry)iterator.next()).getValue()));
      }
    } else if (applicationObject instanceof Object[]) {
      Object[] array = (Object[])applicationObject;
      for (int i = 0; i < array.length; i++) {
        newColl.add(new ReflectionInstance(modelFacade, array[i]));
      }
    } else {
      for (Iterator iterator = ((java.util.Collection)applicationObject).iterator();
           iterator.hasNext();) {
        newColl.add(new ReflectionInstance(modelFacade, iterator.next()));
      }
    }

    return newColl;

  }

  /**
   * This method is relativly complicated but has the advantage that it does 
   * reflect non-public attributes as well.
   * 
   * @see tudresden.ocl.interp.types.Instance#navigateQualified(String, Object[])
   */
  public Instance navigateQualified(String attributeName, Object[] qualifier)
                             throws IllegalAccessException {
    Assert.assertTrue(qualifier == null || qualifier.length == 0);

    Field f = null;
    String[] possibleNames = Ocl.getNames(attributeName);
    for (int i = 0; f == null && i < possibleNames.length; i++) {
      Class searchClass = applicationObject.getClass();
      while (searchClass != null && f == null) {
        try {
          f = searchClass.getDeclaredField(possibleNames[i]);
        } catch (NoSuchFieldException nsfe) {
          f = null;
          searchClass = searchClass.getSuperclass();
        }
      }
    }
    if (f == null) {
      String msg = "non-existent field " + attributeName + " of object \"" + 
                   applicationObject + "\" (" + applicationObject.getClass() + 
                   ") queried";
      throw new IllegalAccessException(msg);

    } else {
      f.setAccessible(true);
      Object feature = f.get(applicationObject);

      if (feature == null) {
        return null;
      } else {
        return new ReflectionInstance(modelFacade, feature);
      }
    }
  }

  /**
   * This does work with public methods only (can be changed)
   * 
   * @see tudresden.ocl.interp.types.Instance#navigateParameterized(String, Object[])
   */
  public Instance navigateParameterized(String name, Object[] params)
                                 throws IllegalAccessException {
    try {
      Object newAppObj = ReflectionUtil.call(applicationObject, name, params);
      if (newAppObj == null) {
        return null;
      } else {
        return new ReflectionInstance(modelFacade, newAppObj);
      }
    } catch (NoSuchMethodException e) {

      // This should not happen
      throw new RuntimeException();
    }
     catch (InvocationTargetException e) {

      // This is a user error
      String msg = "Method " + name + " of type " + getType().toString() + 
                   " failed with exception :" + 
                   e.getTargetException().getMessage();
      throw new IllegalAccessException(msg);
    }
  }

  /**
   * Two instances are equal if the underlying application-objects are
   * identical "==".
   */
  public boolean equals(Object o) {
    if (!(o instanceof ReflectionInstance)) {
      return false;
    } else {
      return ((ReflectionInstance)o).applicationObject == applicationObject;
    }
  }
}