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
package tudresden.ocl20.pivot.parser;

import java.net.URL;

import org.apache.commons.lang.NullArgumentException;

import tudresden.ocl20.pivot.modelbus.model.IModel;

/**
 * 
 * 
 * @author Matthias Braeuer
 * @version 1.0 17.04.2007
 */
public interface IOclParser {

  /**
   * Parses OCL expressions from the given <code>URL</code>. The URL should
   * point to a file or other resource that can be interpreted by a parser
   * implementing this interface.
   * 
   * @param url a <code>URL</code> from where to load OCL expressions
   * 
   * @throws ParseException when an error occurs, reasons include an invalid
   *           URL, a malformed OCL expression or problems relating to the model
   * @throws IllegalStateException if no model has been set yet
   */
  void parse(URL url) throws ParseException;

  /**
   * Returns the model this parser uses as a reference for evaluating OCL
   * expressions. Returns <code>null</code> if no model has been set yet.
   * 
   * @return an <code>IModel</code> instance or <code>null</code>
   */
  IModel getModel();

  /**
   * Sets the {@link IModel model} to be used for evaluating expressions.
   * 
   * @param model the model, must not be <code>null</code>
   * 
   * @exception NullArgumentException if <code>model</code> is
   *              <code>null</code>
   */
  void setModel(IModel model);

  /**
   * Dispose any resources held by this parser.
   */
  void dispose();

}
