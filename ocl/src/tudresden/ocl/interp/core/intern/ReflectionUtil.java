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

package tudresden.ocl.interp.core.intern;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.StringTokenizer;


/**
 * This Util-Class implements satic method-lookup for classes.  For a given
 * class, a method name and parameter classes you can get the most specific
 * method available.<br>
 * 
 * You can also directly invoke the method (slow).
 */
public class ReflectionUtil {

  /**
   * Do a method-call on an object with the given parameters.
   * 
   * @param params must be != null
   */
  public static Object call(Object o, String methodName, Object[] params)
                     throws NoSuchMethodException, IllegalAccessException, 
                            InvocationTargetException {
    Assert.assertTrue(params != null);

    Class[] classParams = new Class[params.length];
    for (int i = 0; i < params.length; i++) {
      if (params[i] != null) {
        classParams[i] = params[i].getClass();
      } else {
        classParams[i] = Object.class;
      }
    }

    return call(o, findMethod(o.getClass(), methodName, classParams), params);

  }

  public static Object call(Object o, Method method, Object[] params)
                     throws IllegalAccessException, InvocationTargetException {
    method.setAccessible(true);
    return method.invoke(o, params);
  }

  /**
   * @param params may not be null and must not cotain null values
   */
  public static Method findMethod(Class c, String methodName, Class[] params)
                           throws NoSuchMethodException {
    Method[] methods = c.getMethods();
    Method result = null;

    for (int i = 0; i < methods.length; i++) {
      if (methods[i].getName().equals(methodName) && 
          methods[i].getParameterTypes().length == params.length) {
        boolean paramsFit = true;
        for (int j = 0; j < params.length; j++) {
          boolean thisParam = false;
          Assert.assertTrue(params[j] != null);
          thisParam = thisParam || 
                      methods[i].getParameterTypes()[j].isAssignableFrom(
                            params[j]);
          thisParam = thisParam || 
                      (methods[i].getParameterTypes()[j] == int.class && 
                      params[j] == Integer.class);
          thisParam = thisParam || 
                      (methods[i].getParameterTypes()[j] == boolean.class && 
                      params[j] == Boolean.class);
          thisParam = thisParam || 
                      (methods[i].getParameterTypes()[j] == double.class && 
                      params[j] == Double.class);
          paramsFit = paramsFit && thisParam;
        }

        if (paramsFit) {
          if (result == null) {
            result = methods[i];
          } else {
            boolean isBetter = true;
            for (int j = 0; j < params.length; j++) {
              boolean thisParam = false;
              thisParam = thisParam || 
                          methods[i].getParameterTypes()[j].isPrimitive();
              thisParam = thisParam || 
                          result.getParameterTypes()[j].isAssignableFrom(
                                methods[i].getParameterTypes()[j]);
              isBetter = isBetter && thisParam;
            }
            if (isBetter) {
              result = methods[i];
            }
          }
        }
      }
    }

    if (result == null) {
      throw new NoSuchMethodException("Method " + methodName + 
                                      " not found while invoking");
    } else {
      return result;
    }
  }

  /**
   * Convert an java ClassName with path to an
   * ocl ClassName with path.
   * @param e.g. "tudresden.sample.Company"
   * @return e.g. "tudresden::sample::Company"
   */
  public static String toColons(String pathName) {
    StringTokenizer st = new StringTokenizer(pathName, ".");
    String result;

    result = st.nextToken();
    while (st.hasMoreTokens()) {
      result = result + "::" + st.nextToken();
    }
    return result;
  }

  /**
   * Convert an ocl ClassName with path to an
   * java ClassName with path.
   * @param e.g. "tudresden::sample::Company"
   * @return e.g. "tudresden.sample.Company"
   */
  public static String toDots(String pathName) {
    String result = "";

    while (pathName.indexOf("::") != -1) {
      result = result + pathName.substring(0, pathName.indexOf("::")).trim() + 
               ".";
      pathName = pathName.substring(pathName.indexOf("::") + 2);
    }
    result = result + pathName.trim();

    return result;
  }
}