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

import java.util.EventObject;

import tudresden.ocl20.pivot.tools.template.ITemplateEngine;
import tudresden.ocl20.pivot.tools.template.ITemplateEngineRegistry;

/**
 * <p>
 * Represents events fired by the {@link ITemplateEngineRegistry} if a new {@link ITemplateEngine}
 * is added or removed.
 * </p>
 * 
 * @author Bjoern Freitag
 */
public class TemplateEngineRegistryEvent extends EventObject {

	/** Generated serial version id. */
	private static final long serialVersionUID = -5135930083763176962L;

	/** The affected {@link ITemplateEngine}. */
	private ITemplateEngine affectedTemplateEngine;

	/**
	 * <p>
	 * Creates a new {@link TemplateEngineRegistryEvent}.
	 * </p>
	 * 
	 * @param source
	 *          The {@link IModelRegistry} that is the source of this event.
	 * @param affectedTemplateEngine
	 *          The {@link ITemplateEngine} affected by the operation that caused this
	 *          event.
	 */
	public TemplateEngineRegistryEvent(ITemplateEngineRegistry source, ITemplateEngine affectedTemplateEngine) {

		super(source);
		this.affectedTemplateEngine = affectedTemplateEngine;
	}

	/**
	 * <p>
	 * Returns the {@link ITemplateEngineRegistry} that is the source of this event.
	 * </p>
	 * 
	 * @see java.util.EventObject#getSource()
	 */
	@Override
	public ITemplateEngineRegistry getSource() {
	
		return (ITemplateEngineRegistry) super.getSource();
	}

	/**
	 * <p>
	 * Returns the {@link ITemplateEngine} that is affected by the operation that caused
	 * this event.
	 * </p>
	 * 
	 * @return An {@link ITemplateEngine} instance.
	 */
	public ITemplateEngine getAffectedTemplateEngine() {

		return this.affectedTemplateEngine;
	}

	/**
	 * <p>
	 * Does the same as {@link #getSource()}, but has a more concise name.
	 * </p>
	 * 
	 * @return The {@link ITemplateEngineRegistry} that is the source of this event.
	 */
	public ITemplateEngineRegistry getTemplateEngineRegistry() {

		return getSource();
	}
}