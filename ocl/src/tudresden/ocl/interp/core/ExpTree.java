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

import tudresden.ocl.interp.types.InstanceFacade;


/**
 * The evaluation tree that the interpreter gives back. It represents an 
 * OCL-Constraint and the logic to compute it.
 */
public interface ExpTree {

  /**
   * This method evaluates an Expression Tree. The Expression Tree reprsents
   * the OCL-Constraint it was created from. Information for the execution is
   * taken from the instanceFacade.
   * 
   * For standard usage you may want to use an instance of 
   * ReflectionInstanceFacade as the instanceFacade. Look into the example in 
   * the class Interpreter as well.
   * 
   * @param instanceFacade gives instances to be checked. The instanceFacade
   *        has to fit to the modelFacade this tree was created with.
   * @return an ExpResult value representing whether the Ocl-Constraint is
   *         fullfilled or not. It is an undifined value if there was a
   *         problem  during execution
   * @throws NullPointerException if instanceFacade == null
   */
  public ExpResult check(InstanceFacade instanceFacade);
}