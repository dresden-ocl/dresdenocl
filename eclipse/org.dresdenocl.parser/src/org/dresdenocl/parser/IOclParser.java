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

import java.io.Reader;
import java.util.List;

import org.eclipse.emf.common.util.URI;

import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.pivotmodel.Constraint;

/**
 * 
 * 
 * @author Matthias Braeuer
 * @version 1.0 17.04.2007
 */
public interface IOclParser {

	/**
	 * <p>
	 * Parses OCL expressions from the given {@link Reader} and adds them to the
	 * given {@link IModel}. The {@link Reader} should point contain a file or
	 * other resource that can be interpreted by a parser implementing this
	 * interface.
	 * </p>
	 * 
	 * @param model
	 *          The {@link IModel} for that the OCL expressions shall be parsed.
	 * @param uri
	 *          A {@link URI} from where to load OCL expressions.
	 * 
	 * @throws ParseException
	 *           When an error occurs, reasons include an invalid URL, a malformed
	 *           OCL expression or problems relating to the {@link IModel}.
	 */
	public List<Constraint> doParse(IModel model, URI uri) throws ParseException;

	/**
	 * <p>
	 * Parses OCL expressions from the given {@link Reader} and adds them to the
	 * given {@link IModel} if indicated (<code>addToModel</code>). The
	 * {@link Reader} should point contain a file or other resource that can be
	 * interpreted by a parser implementing this interface.
	 * </p>
	 * 
	 * @param model
	 *          The {@link IModel} for that the OCL expressions shall be parsed.
	 * @param uri
	 *          A {@link URI} from where to load OCL expressions.
	 * @param addToModel
	 *          Indicates whether or not the parsed {@link Constraint}s, its
	 *          defined fields and functions to the given {@link IModel}.
	 * 
	 * @throws ParseException
	 *           When an error occurs, reasons include an invalid URL, a malformed
	 *           OCL expression or problems relating to the {@link IModel}.
	 */
	public List<Constraint> doParse(IModel model, URI uri, boolean addToModel)
			throws ParseException;
}
