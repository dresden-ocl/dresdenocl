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

package tudresden.ocl.interp.types;

import tudresden.ocl.check.types.Any;


/**
 * This Interface extends the Interface that is used in ocl.check.types with 
 * nessary information that is needed to get the information used for OclType.
 * <br>
 * This is nessary because the interpreter does get the type-information for 
 * the checks out of the MetaAny interface where the compiler uses reflection 
 * directly.
 * 
 * @see Any
 */
public interface MetaAny extends Any {

  /** 
   * Get the name of the type.<br>
   * This is corresponding to: clazz.getName()
   * 
   * @return the name of the class with leading package name
   */
  public String getName();

  /**
   * Get the names of the fields of the class regardingless wether they are
   * assosications or attributes.<br>
   * 
   * This is corresponding to: clazz.getFields()
   * @return the names of all assosications and attributes
   */
  public String[] getFields();

  /**
   * Get the names of the operations that this type provides.<br>
   * 
   * This is corresponding to: clazz.getMethods()
   * @return the names of all operations
   */
  public String[] getOperations();

  /**
   * Returns the direct supertypes of the type.<br>
   * 
   * This is corresponding to: clazz.getSuperclass() + clazz.getInterfaces() 
   * 
   * @return the direct supertypes of this type
   */
  public MetaAny[] getSupertypes();

  /** 
   * Returns the all supertypes of the type.<br>
   * 
   * This is corresponding to: accumilation of the supertypes with 
   *                           getSupertypes()
   * 
   * @return all supertypes of this type
   */
  public MetaAny[] getAllSupertypes();
}