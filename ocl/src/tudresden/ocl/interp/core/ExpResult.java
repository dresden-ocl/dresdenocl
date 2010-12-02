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

package tudresden.ocl.interp.core;

import tudresden.ocl.lib.OclException;


/**
 * Objects of this interface are given back as an result of evaluating an 
 * ExpTree. It is the result of the evaluation of the an OCL constraint.
 * It may have one of the following three states:
 * 
 * <ul>
 * <li>true
 * <li>false
 * <li>undefined
 * </ul>
 * 
 * In case the value is undefined you can get the reason for this.
 */
public interface ExpResult {

  /**
   * Get whether the constraint evaluated to false or true
   * 
   * @throws OclException if isUndefined() == true
   * @return <code>true</code> if the constraint passed, <code>false</code>
   *         otherwise
   */
  public boolean isTrue() throws OclException;

  /**
   * Get wether the state of the object is undefined. This happens in case it
   * was not possible to evaluate the expression due to null objects or
   * objects with the wrong class.
   * 
   * @return if the result is undefined
   */
  public boolean isUndefined();

  /**
   * The reason why the result is undefined.
   * 
   * @throws OclException if isUndefined() == false
   * @return the reason why the result is undefined
   */
  public String getUndefinedReason() throws OclException;

  /**
   * Shows all three states in a readable format.
   * 
   * @return all three states in a readable format.
   */
  public String toString();
}