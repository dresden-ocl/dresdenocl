/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2010 Bjoern Freitag (bjoernfreitag@googlemail.com).         *
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

package tudresden.ocl20.pivot.tools.template.event;

import tudresden.ocl20.pivot.tools.template.ITemplateEngine;
import tudresden.ocl20.pivot.tools.template.ITemplateEngineRegistry;

/**
 * An {@link ITemplateEngineRegistryListener} is informed about activities in an
 * {@link ITemplateEngineRegistry}, such as loading templateEngines.
 * 
 * @author Bjoern Freitag
 */
public interface ITemplateEngineRegistryListener {

	/**
	 * <p>
	 * This method is called when a {@link ITemplateEngine templateEngine} has
	 * been added to a {@link ITemplateEngineRegistry}.
	 * </p>
	 * 
	 * @param event
	 *          A {@link TemplateEngineRegistryEvent} object with more details.
	 */
	void templateEngineAdded(TemplateEngineRegistryEvent event);

	/**
	 * <p>
	 * This method is called when a {@link ITemplateEngine templateEngine} has
	 * been removed from a {@link ITemplateEngineRegistry}.
	 * </p>
	 * 
	 * @param event
	 *          A {@link TemplateEngineRegistryEvent} object with more details.
	 */
	void templateEngineRemoved(TemplateEngineRegistryEvent event);
}