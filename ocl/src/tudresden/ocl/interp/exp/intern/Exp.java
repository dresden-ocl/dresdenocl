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

package tudresden.ocl.interp.exp.intern;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import tudresden.ocl.check.types.Basic;
import tudresden.ocl.check.types.Collection;
import tudresden.ocl.check.types.Type;

import tudresden.ocl.interp.core.intern.Assert;
import tudresden.ocl.interp.core.intern.ReflectionUtil;

import tudresden.ocl.lib.*;


/**
 * This call is the Root-Class to the expression-tree. Expression-Trees are
 * the result of calling the Interpreter.expEvaluate. They can be used with 
 * the evaluate method to check wether the current state from an instanceFacade 
 * does sattisfy the ocl-constraint.<br>
 * Subclasses have to implement the computation of the result in the method
 * evaluateInternal.
 */
public abstract class Exp {
  private Class returnType;

  /**
   * The only method to be called form a pontential client. Evaluates the
   * Ocl-Constraint in the instanceFacade and returns the result to the user.
   */
  public OclRoot evaluate() {
    OclRoot result = evaluateInternal();

    if (result != null && result.isUndefined()) {
      String undefinedReason = result.getUndefinedReason();
      try {
        Constructor c = getReturnType().getConstructor(
                              new Class[] {int.class, String.class});
        result = (OclRoot)c.newInstance(
                       new Object[] {new Integer(0), undefinedReason});
      } catch (Exception e) {
        throw new RuntimeException();
      }
    }

    Assert.assertTrue(result == null || getReturnType().isInstance(result));
    return result;
  }

  /**
   * Implement this method to give the result of the node back ...
   * Type-Checking is done in this class.<br>
   * For most of the nodes this is done in three steps:
   * <ul>
   * <li>call the evaluate methods of all subnodes (if existing)
   * <li>do some computation (e.g. adding)
   * <li>give the result back
   * </ul>
   */
  protected abstract OclRoot evaluateInternal();

  /**
   * The returnType has to be set by the Interpreter for type conversion and
   * checking ...
   */
  public Class getReturnType() {
    return returnType;
  }

  /**
   * The returnType has to be set by the Interpreter for type conversion and
   * checking ...
   */
  public void setReturnType(Type type) {
    setReturnType(getOclType(type));
  }

  /**
   * The returnType has to be set by the Interpreter for type conversion and
   * checking ...
   */
  public void setReturnType(Class returnType) {
    Assert.assertTrue(returnType != null);
    this.returnType = returnType;
  }

  /**
   * For Gui-Use only
   */
  public Object[] children() {
    return new Object[0];
  }

  /**
   * For Gui-Use only
   */
  public String toString() {
    String cName = getClass().toString();
    cName = cName.substring(cName.lastIndexOf(".") + 1);
    cName = cName + "(" + getReturnType() + ")";
    return cName;
  }

  /**
   * Does the same functionalty like dynamic method lookup from Java. Here the
   * lookup is static, we give the base class and the types of the parameters 
   * before and get the most specific method back.<br>
   * 
   * In this method the Exp-Objects are asked for their return-classes and this
   * classes are used to get the result calling the ReflectionUtil.
   * 
   * @see ReflectionUtil#findMethod(Class, String, Class[])
   */
  protected static Method findMethod(Exp exp, String methodName, 
                                     Object[] params) {
    if (params == null) {
      params = new Object[0];
    }

    Class[] classParams = new Class[params.length];
    for (int i = 0; i < params.length; i++) {

      // It is intended to leave the classParams[i] null if the params[0]
      // is null
      if (params[i] instanceof Exp) {
        classParams[i] = ((Exp)params[i]).getReturnType();
      } else if (params[i] instanceof Class) {
        classParams[i] = (Class)params[i];
      } else {

        // We do not call anything with null-values when this changes we
        // need to pass an Object.class value for every null we have
        Assert.assertTrue(params[i] != null);
        classParams[i] = params[i].getClass();
      }
    }

    try {
      return ReflectionUtil.findMethod(exp.getReturnType(), methodName, 
                                       classParams);
    } catch (NoSuchMethodException e) {
      throw new RuntimeException();
    }
  }

  /**
   * Helper Method for subclasses. Call a method that we got via findMethod.
   */
  protected static OclRoot call(OclRoot oclBase, Method method, 
                                Object[] oclParams) {
    try {
      return (OclRoot)ReflectionUtil.call(oclBase, method, oclParams);
    } catch (IllegalAccessException e) {
      throw new RuntimeException("No access permission while invoking");
    }
     catch (InvocationTargetException e) {
      if (e.getTargetException() instanceof RuntimeException) {
        throw (RuntimeException)e.getTargetException();
      } else {
        throw new RuntimeException("Method called throws a non runtime exception");
      }
    }
  }

  /**
   * Get the OclType of a Parser-Type
   */
  protected static Class getOclType(Type t) {
    if (t instanceof Basic) {
      if (t == Basic.INTEGER) {
        return OclInteger.class;
      } else if (t == Basic.REAL) {
        return OclReal.class;
      } else if (t == Basic.BOOLEAN) {
        return OclBoolean.class;
      } else {
        Assert.assertTrue(t == Basic.STRING);
        return OclString.class;
      }
    } else if (t instanceof Collection) {
      Collection c = (Collection)t;
      switch (c.getCollectionKind()) {
        case Collection.SET:
          return OclSet.class;
        case Collection.BAG:
          return OclBag.class;
        case Collection.SEQUENCE:
          return OclSequence.class;
        case Collection.COLLECTION:
          return OclCollection.class;
        default:
          throw new RuntimeException("illegal collection type");
      }
    } else if (t instanceof tudresden.ocl.check.types.OclType) {
      return OclType.class;
    } else {

      // application type
      return OclAnyImpl.class;
    }
  }
}