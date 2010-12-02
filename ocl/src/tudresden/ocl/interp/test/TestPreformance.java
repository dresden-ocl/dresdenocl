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

package tudresden.ocl.interp.test;

import java.io.IOException;

import junit.framework.TestCase;

import tudresden.ocl.check.types.ModelFacade;

import tudresden.ocl.interp.core.ExpTree;
import tudresden.ocl.interp.core.Interpreter;
import tudresden.ocl.interp.types.InstanceFacade;


public class TestPreformance extends TestCase {
  protected InstanceFacade instanceFacade;
  protected ModelFacade modelFacade;

  public TestPreformance(String s) {
    super(s);
    initFacades();
  }

  public void initFacades() {
    instanceFacade = SpecFacadeFactory.getInstanceFacade();
    modelFacade = SpecFacadeFactory.getModelFacade();
  }

  public void testCompany() throws IOException {
    final int count = 6000;
    final int times = 20;

    instanceFacade = SpecFacadeFactory.getPreformanceFacade(modelFacade, count);

    long time1 = System.currentTimeMillis();

    ExpTree exp = Interpreter.expEvaluate("context Person inv: age > 10", 
                                          modelFacade);

    long time2 = System.currentTimeMillis();

    for (int i = 0; i < times; i++) {
      exp.check(instanceFacade);
    }

    long time3 = System.currentTimeMillis();

    System.out.println("Constructing the Exp: " + (time2 - time1));
    System.out.println(
          "Exec the Exp in average on " + count + " Elements: " + 
          ((time3 - time2) / times));

  }
}