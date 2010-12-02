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

import java.util.Map;

import tudresden.ocl.check.types.ModelFacade;

import tudresden.ocl.interp.core.intern.Assert;
import tudresden.ocl.interp.lib.intern.InstanceOclFactory;

import tudresden.ocl.lib.OclRoot;


/**
 * Represents a call on a variable.<p>
 * During evaluation there are two possibilities:
 * <ul>
 * <li>If the variable is in the enviorment, it is just givenback.
 * <li>If the variable is a Type the OclType is created and given back.
 * </ul>
 */
public class ExpVar extends Exp {
  Map varMap;
  String name;
  boolean isType;
  ModelFacade modelFacade;

  public ExpVar(Map varMap, String name, boolean isType, 
                ModelFacade modelFacade) {
    this.varMap = varMap;
    this.name = name;
    this.isType = isType;
    this.modelFacade = modelFacade;
  }

  public OclRoot evaluateInternal() {
    OclRoot oclVar;
    if (isType) {
      oclVar = InstanceOclFactory.getOclTypeFor(name, modelFacade);
    } else {

      // The result of oclVar can be null, but the variable has to be in the
      // map. Otherwise something went wrong.
      Assert.assertTrue(varMap.containsKey(name));
      oclVar = (OclRoot)varMap.get(name);
    }
    return oclVar;
  }

  public String toString() {
    return super.toString() + ": " + name;
  }
}