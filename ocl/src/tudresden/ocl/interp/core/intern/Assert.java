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

package tudresden.ocl.interp.core.intern;


/**
 * This class does implement the same functionality of the Java assert statment. 
 * It is introduced to be downwards compatible. Furthermore the assert statment
 * cannot be used with the coverage checker JCover2.<br>
 * 
 * Assertions are used to check expressions that can only be false in case a
 * development error happend. E.g. they are used to check paramerters of
 * internal (!) methods. You should not check parameters of external methods 
 * with the assertTrue statment.<br>
 * 
 * You should never try to chatch the exception unless you have a general
 * exception handling in your project.
 */
public class Assert {

  /**
   * Use the method with an expression that you want to validate. If the
   * expression evaluates to false an exception is thrown.
   * 
   * @param arg the expression
   * @throws AssertionException if arg = false
   */
  public static void assertTrue(boolean arg) throws AssertionException {
    if (!arg) {
      throw new AssertionException();
    }
  }

  /**
   * Throws a NullPointerException if a null is passed. This is meand for
   * argument checking and not turned of when not in debug mode any more.<br>
   * 
   * You can use the method for checking parameters of external methods as 
   * well.
   * 
   * @param o the argument to be checked
   * @param methodName the method calling for the exception message
   * @param paramName the parameter checked for the exception message
   * @throws NullPointerException if o==null
   */
  public static void notNull(Object o, String methodName, String paramName) {
    if (o == null) {
      String msg = "A null Argument has been passed to the method " + 
                   methodName + " in the parameter " + paramName + ".";
      throw new NullPointerException(msg);
    }
  }
}