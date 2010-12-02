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

import java.io.IOException;

import tudresden.ocl.OclTree;

import tudresden.ocl.check.OclTypeException;
import tudresden.ocl.check.types.ModelFacade;

import tudresden.ocl.interp.core.intern.Assert;
import tudresden.ocl.interp.core.intern.InterpreterVisitor;
import tudresden.ocl.interp.lib.intern.InstanceOclFactory;

import tudresden.ocl.lib.Ocl;


/**
 * The core class of the whoole package tudresden.ocl.interp. With the 
 * interpreter you do check OCL-Constraints on a given data-source.<br>
 * 
 * Given you have a class Person with the public attribute int age this is a
 * simple example to do a check:<br><br>
 * 
 * <code>
 *   // Create the Person<br>
 *   Person p1 = new Person();<br>
 *   p1.age = 18;<br>
 *   <br>
 *   // Get the nessary datasources (= facades)<br>
 *   ModelFacade mf = new MetaReflectionFacade(<br>
 *                          new String[] {"tudresden.ocl.interp.core"});<br>
 *   InstanceFacade insf = new ReflectionInstanceFacade(mf, p1);<br>
 *   <br>
 *   // Call the Interpreter<br>
 *   ExpTree e = Interpreter.expEvaluate("context Person inv: age > 17", mf);<br>
 *   OclBoolean result = e.check(insf);<br>
 *   <br>
 *   // Print the result<br>
 *   System.out.println(result);<br>
 * </code><br><br>
 * 
 * This package contains the private class SimpleExample, that contains exactly 
 * the presented source-code.<br><br>
 * 
 */
public class Interpreter {

  /**
   * Interpreter is a static class. We do not want anybody to get an instance
   * of it.
   */
  private Interpreter(){}

  private static OclTree buildTree(String oclConstraint, 
                                   ModelFacade modelFacade)
                            throws OclTypeException {
    try {
      OclTree tree = OclTree.createTree(oclConstraint, modelFacade);
      tree.applyDefaultNormalizations();
      return tree;
    } catch (IOException e) {

      // I belive, that the IOException would be caused by the parser that get
      // an bad stream. As we give the parser a String as this stream the
      // IOException should not be thrown.
      throw new RuntimeException(e.getMessage());
    }
  }

  /**
   * Build an Expression Tree out of the OCL-Constraint. You can check the
   * constraint just by evaluation the resulting Expression Tree.<br><br>
   * 
   * For standard usage you may want to use an instance of 
   * MetaReflectionFacade as the modelFacade. Look into the example above as 
   * well.
   * 
   * @param oclConstraint the constraint to be put into an evaluation tree.
   * @param modelFacade gives information about a class structure
   * @return an evaluation tree representing the constraint
   * @throws OclTypeException if the oclConstraint has a syntax or semantic
   *         error in it. It is strongly recomended to catch this exception.
   * @throws NullPointerException if a parameter is null
   */
  public static ExpTree expEvaluate(String oclConstraint, 
                                    ModelFacade modelFacade)
                             throws OclTypeException {
    Assert.notNull(oclConstraint, "expEvaluate", "oclConstraint");
    Assert.notNull(modelFacade, "expEvaluate", "modelFacade");
    Ocl.setFactory(new InstanceOclFactory(modelFacade));
    OclTree tree = buildTree(oclConstraint, modelFacade);

    InterpreterVisitor jcg = new InterpreterVisitor(modelFacade);
    return (ExpTree)jcg.getExp(tree);
  }
}