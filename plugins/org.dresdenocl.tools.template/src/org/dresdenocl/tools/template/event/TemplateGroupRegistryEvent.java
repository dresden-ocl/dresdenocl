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

package org.dresdenocl.tools.template.event;

import java.util.EventObject;

import org.dresdenocl.tools.template.ITemplateGroup;
import org.dresdenocl.tools.template.ITemplateGroupRegistry;

/**
 * <p>
 * Represents events fired by the {@link ITemplateGroupRegistry} if a new
 * {@link ITemplateGroup} is added or removed.
 * </p>
 * 
 * @author Bjoern Freitag
 */
public class TemplateGroupRegistryEvent extends EventObject {

	/** Generated serial version id. */
	private static final long serialVersionUID = -5135930083763176962L;

	/** The affected {@link ITemplateGroup}. */
	private ITemplateGroup affectedTemplateGroup;

	/**
	 * <p>
	 * Creates a new {@link TemplateGroupRegistryEvent}.
	 * </p>
	 * 
	 * @param source
	 *          The {@link IModelRegistry} that is the source of this event.
	 * @param affectedTemplateGroup
	 *          The {@link ITemplateGroup} affected by the operation that caused
	 *          this event.
	 */
	public TemplateGroupRegistryEvent(ITemplateGroupRegistry source,
			ITemplateGroup affectedTemplateGroup) {

		super(source);
		this.affectedTemplateGroup = affectedTemplateGroup;
	}

	/**
	 * <p>
	 * Returns the {@link ITemplateGroupRegistry} that is the source of this
	 * event.
	 * </p>
	 * 
	 * @see java.util.EventObject#getSource()
	 */
	@Override
	public ITemplateGroupRegistry getSource() {

		return (ITemplateGroupRegistry) super.getSource();
	}

	/**
	 * <p>
	 * Returns the {@link ITemplateGroup} that is affected by the operation that
	 * caused this event.
	 * </p>
	 * 
	 * @return An {@link ITemplateGroup} instance.
	 */
	public ITemplateGroup getAffectedTemplateGroup() {

		return this.affectedTemplateGroup;
	}

	/**
	 * <p>
	 * Does the same as {@link #getSource()}, but has a more concise name.
	 * </p>
	 * 
	 * @return The {@link ITemplateGroupRegistry} that is the source of this
	 *         event.
	 */
	public ITemplateGroupRegistry getTemplateGroupRegistry() {

		return getSource();
	}
}