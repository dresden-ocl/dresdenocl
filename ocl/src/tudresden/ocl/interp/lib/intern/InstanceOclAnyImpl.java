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

package tudresden.ocl.interp.lib.intern;

import tudresden.ocl.check.types.ModelFacade;

import tudresden.ocl.interp.core.intern.Assert;
import tudresden.ocl.interp.types.Instance;
import tudresden.ocl.interp.types.MetaAny;

import tudresden.ocl.lib.*;


/**
 * This class is oriented on the superclass OclAnyImpl. The main difference is
 * that InstanceOclAnyImpl does work on top of the interface Instance. So it
 * gets the information about the object that it is representing though the
 * interface and not directly through Java reflection like OclAnyImpl does.<br>
 * 
 * Therefore this class inserts anotherlayer of indirection.<br>
 * The featureListener is made inactive as we do not need it. 
 * The possibility to get Sequences back fromfeature-calls is uneffected as well.
 */
class InstanceOclAnyImpl extends OclAnyImpl {
  private ModelFacade mf;
  private Instance instance;

  /**
   * @param instance must not be null
   * @param mf must not be null
   */
  public InstanceOclAnyImpl(Instance instance, ModelFacade mf) {
    super(null);
    this.instance = instance;
    this.mf = mf;
    Assert.assertTrue(instance != null);
  }

  /**
   * To call side-effect free methods of an object, invoke this method with
   * the method name as String and the appropriate parameters. This method
   * does not enforce that only side-effect free methods are called.<p> If
   * an exception occurs in the called method or in accessing it, an
   * undefined value is returned.
   */
  public OclRoot getFeature(String methodName, Object[] params) {
    Object[] javaParams = new Object[params.length];
    for (int i = 0; i < params.length; i++) {
      javaParams[i] = Ocl.reconvert(Object.class, (OclRoot)params[i]);
    }

    Instance newInstance = null;
    try {
      newInstance = instance.navigateParameterized(methodName, javaParams);
    } catch (IllegalAccessException e) {
      return new OclAnyImpl(0, e.getMessage());

      // throw new OclException(e.getMessage());
    }

    if (newInstance == null) {
      return null;
    } else {
      return Ocl.getOclRepresentationFor(newInstance);
    }
  }

  /**
   * The attributes of application objects can be queried through this method.
   * Due to restrictions of the Java language, only <CODE>public</CODE>
   * fields can be queried.
   * 
   * @param attributeName the name of the feature, as a java.lang.String
   */
  public OclRoot getFeatureQualified(String attributeName, Object qualifier) {
    Assert.assertTrue(qualifier == null);

    Instance newInstance = null;
    try {
      newInstance = instance.navigateQualified(attributeName, null);
    } catch (IllegalAccessException e) {
      return new OclAnyImpl(0, e.getMessage());

      // throw new OclException(e.getMessage());
    }

    if (newInstance == null) {
      return null;
    } else {
      return Ocl.getOclRepresentationFor(newInstance);
    }
  }

  /**
   * two OclAnyImpl objects are equal if their encapsulated application
   * objects are identical (NOT equal)
   */
  public OclBoolean isEqualTo(Object o) {
    if (!(o instanceof InstanceOclAnyImpl)) {
      String msg = "InstanceOclAnyImpl isEqualTo() is called with a non-InstanceOclAnyImpl parameter";
      System.out.println(msg);
      return OclBoolean.FALSE;
    }

    if (this.instance.equals(((InstanceOclAnyImpl)o).instance)) {
      return OclBoolean.TRUE;
    } else {
      return OclBoolean.FALSE;
    }
  }

  /**
   * This property is no longer present in OCL 1.3. In spite of this, the
   * library contains this method. The problems that lead to its cancellation
   * do not occur in this Java implementation.
   */
  public OclType oclType() {
    if (!(instance.getType() instanceof MetaAny)) {
      String msg = "getType used but MetaAny not implmented in the reflection facade";
      return new OclType(0, msg);
    }
    MetaAny any = (MetaAny)instance.getType();
    return InstanceOclType.getOclTypeFor(any.getName(), mf);
  }

  /**
   * This method is not called. As it exists in the OclAnyImpl we need to
   * implement it here as well due to the fact that it does use reflection
   * which we want to do through the Instance-Adapter.
   */
  public OclSequence getFeatureAsSequence(String name) {
    throw new RuntimeException();
  }
}