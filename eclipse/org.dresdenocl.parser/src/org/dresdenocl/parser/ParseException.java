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
package org.dresdenocl.parser;


/**
 * A <code>ParseException</code> is thrown when an error occurs while parsing an OCL expression.
 * In general, this will probably be due to another exception, so clients are advised to check
 * the cause of the <code>ParseException</code>.
 *
 * @author Matthias Braeuer
 * @version 1.0 17.04.2007
 */
public class ParseException extends Exception {

  // generated serial version id
  private static final long serialVersionUID = -6627532559140885236L;

  /**
   * Creates a new <code>ParseException</code> with an error message.
   * 
   * @param message the message
   */
  public ParseException(String message) {
    super(message);
  }

  /**
   * Creates a new <code>ParseException</code> with an error message and a
   * <code>Throwable</code> that is the root cause of this exception.
   * 
   * @param message the message
   * @param cause the cause of this exception
   */
  public ParseException(String message, Throwable cause) {
    super(message,cause);
  }

}
