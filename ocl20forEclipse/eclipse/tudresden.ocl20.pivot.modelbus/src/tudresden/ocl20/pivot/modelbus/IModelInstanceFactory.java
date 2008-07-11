/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Matthias Braeuer (braeuer.matthias@web.de).            *
 * All rights reserved.                                                      *
 *                                                                           *
 * This work was done as a project at the Chair for Software Technology,     *
 * Dresden University Of Technology, Germany (http://st.inf.tu-dresden.de).  *
 * It is understood that any modification not identified as such is not      *
 * covered by the preceding statement.                                       *
 *                                                                           *
 * This work is free software; you can redistribute it and/or modify it      *
 * under the terms of the GNU Library General Public License as published    *
 * by the Free Software Foundation; either version 2 of the License, or      *
 * (at your option) any later version.                                       *
 *                                                                           *
 * This work is distributed in the hope that it will be useful, but WITHOUT  *
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or     *
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Library General Public     *
 * License for more details.                                                 *
 *                                                                           *
 * You should have received a copy of the GNU Library General Public License *
 * along with this library; if not, you can view it online at                *
 * http://www.fsf.org/licensing/licenses/gpl.html.                           *
 *                                                                           *
 * To submit a bug report, send a comment, or get the latest news on this    *
 * project, please visit the website: http://dresden-ocl.sourceforge.net.    *
 * For more information on OCL and related projects visit the OCL Portal:    *
 * http://st.inf.tu-dresden.de/ocl                                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * $Id$
 */
package tudresden.ocl20.pivot.modelbus;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclTuple;


/**
 * 
 *
 * @author Matthias Braeuer
 * @version 1.0 30.03.2007
 */
public interface IModelInstanceFactory {

  /**
   * @param booleanLiteral
   * @return
   */
  OclBoolean createOclBoolean(boolean booleanLiteral);
  
  /**
   * @param integerLiteral
   * @return
   */
  OclInteger createOclInteger(int integerLiteral);
  
  /**
   * @param realLiteral
   * @return
   */
  OclReal createOclReal(float realLiteral);
  
  /**
   * @param stringLiteral
   * @return
   */
  OclString createOclString(String stringLiteral);
  
  /**
   * @param partNames
   * @param partValues
   * @return
   */
  OclTuple createOclTuple(String[] partNames, OclRoot[] partValues);
  
  /**
   * @param <T>
   * @param parts
   * @return
   */
  <T extends OclRoot> OclSequence<T> createOclSequence(T[] parts);
  
  /**
   * @param <T>
   * @param parts
   * @return
   */
  <T extends OclRoot> OclBag<T> createOclBag(T[] parts);

  /**
   * @param <T>
   * @param parts
   * @return
   */
  <T extends OclRoot> OclSet<T> createOclSet(T[] parts);

  /**
   * @param <T>
   * @param parts
   * @return
   */
  <T extends OclRoot> OclOrderedSet<T> createOclOrderedSet(T[] parts);

}
