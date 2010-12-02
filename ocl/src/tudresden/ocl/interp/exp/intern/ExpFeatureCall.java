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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tudresden.ocl.lib.OclRoot;


/**
 * Reperesents a method-call in the ocl-constrain. Calls the method of an 
 * encapsulated user object via reflection on evaluation.
 */
public class ExpFeatureCall extends ExpComposite {
  public ExpFeatureCall(Exp base, String feature, Exp[] params) {
    super(base, "getFeature", new Object[] {feature, params});
  }

  public OclRoot evaluate() {
    OclRoot oclBase;
    Object[] oclParams = new Object[2];

    oclBase = base.evaluate();
    oclParams[0] = params[0];
    Exp[] callParams = (Exp[])params[1];
    OclRoot[] oclCallParams = new OclRoot[callParams.length];
    oclParams[1] = oclCallParams;

    for (int i = 0; i < callParams.length; i++) {
      oclCallParams[i] = callParams[i].evaluate();
    }

    return call(oclBase, runMethod, oclParams);
  }

  public Object[] children() {
    List result = new ArrayList();
    result.add(base);
    result.add(new StringBuffer((String)params[0]));
    result.addAll(Arrays.asList((Exp[])params[1]));
    return result.toArray();
  }
}