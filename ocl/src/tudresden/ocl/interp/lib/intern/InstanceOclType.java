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

import java.util.HashSet;
import java.util.Set;

import tudresden.ocl.check.types.ModelFacade;

import tudresden.ocl.interp.core.intern.Assert;
import tudresden.ocl.interp.core.intern.ReflectionUtil;
import tudresden.ocl.interp.types.MetaAny;

import tudresden.ocl.lib.Ocl;
import tudresden.ocl.lib.OclBoolean;
import tudresden.ocl.lib.OclException;
import tudresden.ocl.lib.OclSet;
import tudresden.ocl.lib.OclString;
import tudresden.ocl.lib.OclType;


/**
 * This class does the same job like the OclType that it is extended  from.
 * The differece is the data source:<br>
 * 
 * While OclType uses reflection to get the information it needs
 * InstanceOclType uses the Interface MetaAny to query the information
 * indirectly.
 */
class InstanceOclType extends OclType {
  MetaAny metaAny;

  protected InstanceOclType(MetaAny type) {
    super(Object.class);
    this.metaAny = type;
    Assert.assertTrue(type != null);
  }

  /**
   * Get the OclType from a class-name and the modelFacade that knows about
   * the class-structure.<br>
   * This does only work if the ModelFacade gives information about classes
   * in the MetaAny class, otherwise the information is not avaiable and an
   * exception is thrown.
   * 
   * @throws OclException
   */
  public static OclType getOclTypeFor(String name, ModelFacade modelFacade) {
    try {
      return getPredefinedTypeFor(name);
    } catch (OclException e) {

      // We did not get the predefined type
    }

    name = ReflectionUtil.toColons(name);
    if (!(modelFacade.getClassifier(name) instanceof MetaAny)) {
      String msg = "getType used but MetaAny not implmented in the reflection facade";
      return new OclType(0, msg);
    }

    return new InstanceOclType((MetaAny)modelFacade.getClassifier(name));

  }

  /**
   * Uneffecting the methods of the superclass
   * @throws RuntimeException
   */
  public static OclType getOclTypeFor(String name) {
    throw new RuntimeException();
  }

  /**
   * Uneffecting the methods of the superclass
   * @throws RuntimeException
   */
  public static OclType getOclTypeFor(Object context, String name) {
    throw new RuntimeException();
  }

  public OclBoolean isEqualTo(Object o) {
    if (!(o instanceof InstanceOclType)) {
      String msg = "InctanceOclType isEqualTo() is called with a non-InctanceOclType parameter";
      System.out.println(msg);
      return OclBoolean.FALSE;
    }
    if (this == o || this.metaAny.equals(((InstanceOclType)o).metaAny)) {
      return OclBoolean.TRUE;
    } else {
      return OclBoolean.FALSE;
    }
  }

  public boolean equals(Object o) {
    return isEqualTo(o).isTrue();
  }

  public int hashCode() {
    return metaAny.hashCode();
  }

  public String toString() {
    return "OclType<" + metaAny.getName() + ">";
  }

  /**
   * Get the name of the Type
   */
  public OclString name() {
    String name = metaAny.getName();
    name = name.substring(name.lastIndexOf(".") + 1);
    return new OclString(name);
  }

  /**
   * Get the attributes of the type
   */
  public OclSet attributes() {
    Set result = new HashSet();

    String[] fields = metaAny.getFields();
    for (int i = 0; i < fields.length; i++) {
      result.add(new OclString(fields[i]));
    }
    return new OclSet(result);
  }

  /**
   * Get the associationEnds of the type. Are the names of all outgoing 
   * relationships to other types.
   */
  public OclSet associationEnds() {
    Set result = new HashSet();

    String[] fields = metaAny.getFields();
    for (int i = 0; i < fields.length; i++) {
      String[] posNames = Ocl.getPossibleAssociationNames(fields[i]);
      for (int j = 0; j < posNames.length; j++) {
        result.add(new OclString(posNames[j]));
      }
    }
    return new OclSet(result);
  }

  /**
   * @return an OclSet containing the names of all public methods of the Java
   *         class encapsulated by this OclType object, represented as
   *         OclStrings
   */
  public OclSet operations() {
    Set result = new HashSet();
    String[] methods = metaAny.getOperations();
    for (int i = 0; i < methods.length; i++) {
      result.add(new OclString(methods[i]));
    }
    return new OclSet(result);
  }

  /**
   * @return an OclSet that contains OclType objects for this class'
   *         superclass and all interfaces
   */
  public OclSet supertypes() {
    Set result = new HashSet();
    MetaAny[] types = metaAny.getSupertypes();
    for (int i = 0; i < types.length; i++) {
      result.add(new InstanceOclType(types[i]));
    }
    return new OclSet(result);
  }

  /**
   * @return an OclSet that contains OclType objects for this class'
   *         superclasses and all interfaces
   */
  public OclSet allSupertypes() {
    Set result = new HashSet();
    MetaAny[] types = metaAny.getAllSupertypes();
    for (int i = 0; i < types.length; i++) {
      result.add(new InstanceOclType(types[i]));
    }
    return new OclSet(result);
  }

  /**
     * We do not want the interpreter to cause exceptions on user failure
     * So we do not throw an exception but give an undefined value back
     */
  public OclSet allInstances() {
    try {
      return super.allInstances();
    } catch (OclException e) {
      return new OclSet(0, e.getMessage());
    }
  }
}