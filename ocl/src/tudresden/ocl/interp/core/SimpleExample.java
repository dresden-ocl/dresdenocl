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

import tudresden.ocl.check.types.ModelFacade;

import tudresden.ocl.interp.types.InstanceFacade;
import tudresden.ocl.interp.types.reflect.MetaReflectionFacade;
import tudresden.ocl.interp.types.reflect.ReflectionInstanceFacade;


/**
 * This is a very simple example to run the OCL-Interpreter. It just creates
 * the facades nessary an one instance of a class and does the check then.
 */
class SimpleExample {
  public static void main(String[] args) {

    // Create the Person
    Person p1 = new Person();
    p1.age = 18;

    // Get the nessary datasources (= facades)
    ModelFacade mf = new MetaReflectionFacade(
                           new String[] {"tudresden.ocl.interp.core"});
    InstanceFacade insf = new ReflectionInstanceFacade(mf, p1);

    // Call the Interpreter
    ExpTree e = Interpreter.expEvaluate("context Person inv: age > 17", mf);
    ExpResult result = e.check(insf);

    // Print the result
    System.out.println(result);
    if (result.isTrue()) {
      System.out.println("They are all older than 17");
    }
  }
}

class Person {
  public int age;
}