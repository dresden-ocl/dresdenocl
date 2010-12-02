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

import tudresden.ocl.check.types.Type;


/**
 * This class repesents an Object to be checked by the interpreter.
 * It is meand to support the same navigation like the corresponding
 * Type on the concrete instance level.<br><br>
 * 
 * The two main methods of the class are:<br>
 * navigateQualified which is an abstrcat attribute-call<br>
 * navigateParameterized which is an abstract methode-call.<br>
 * 
 */
public interface Instance {
  public Type getType();

  /**
   * If getType() instanceof Basic this method can be called to get the 
   * value of this basic object. It is given back as a Java object e.g.
   * Boolean, Integer. <br>
   * If !(getType() instanceof Basic) the behavior is unspecified.
   * 
   * @return the Java representation of this base-type
   */
  public Object getBasicValue();

  /**
   * If getType instanceof Collection this method can be called. It gives 
   * an handle on new instances that are part of the collection. All the 
   * elements are of type Instance.<br>
   * If !(getType() instanceof Collection) the behavior is unspecified.
   * 
   * @return the Java Collection holding all instances that are in the 
   *        collection
   */
  public java.util.Collection getCollection();

  /**
   * This representes an access to an attribute or an relationship.
   * 
   * @param name of the attribute or realationship
   * @param qualifiers to access an element of the attribute, e.g. for arrays 
   *       or maps
   * @return the value of the attribute as a new instance. Returns null if
   *         the attribute is null.
   * @throws IllegalAccessException if the access of the attribute does not 
   *         work. This can happens if the attribute does not exist or if 
   *         there are internal problems to access the attribute.
   * 
   */
  public Instance navigateQualified(String name, Object[] qualifiers)
                             throws IllegalAccessException;

  /**
   * This representes an method-call. This should only be done for 
   * quering-methods that do not alter the state of the object.
   * 
   * @param name of the method
   * @param params of the call
   * @return the return value of the call as a new instance
   * @throws IllegalAccessException if the invoking of the method does not 
   *         work. This can happens if the method does not exist, if the method 
   *         throws an exception or if internal problems to invorke the method
   *         occure.
   */
  public Instance navigateParameterized(String name, Object[] params)
                                 throws IllegalAccessException;
}