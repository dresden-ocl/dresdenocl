/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Ronny Brandt (Ronny_Brandt@web.de).                    *
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
 */
package tudresden.ocl20.interpreter;

import tudresden.ocl20.interpreter.event.IInterpreterRegistryListener;

/**
 * <p>
 * Provides the interface for an registry which contains listeners which observe
 * an {@link IOclInterpreter}.
 * </p>
 * 
 * @author Ronny Brandt
 */
public interface IInterpreterRegistry {

	/**
	 * <p>
	 * Adds an {@link IInterpreterRegistryListener}.
	 * </p>
	 * 
	 * @param listener
	 *          The {@link IInterpreterRegistryListener} which shall be added.
	 */
	public void addInterpreterRegistryListener(
			IInterpreterRegistryListener listener);

	/**
	 * <p>
	 * Fires an interpretation finished event.
	 * </p>
	 * 
	 * @param interpretationResult
	 *          The {@link IInterpretationResult} that shall be sent.
	 */
	public void fireInterpretationFinished(
			IInterpretationResult interpretationResult);

	/**
	 * <p>
	 * Removes an {@link IInterpreterRegistryListener}.
	 * 
	 * @param listener
	 *          The {@link IInterpreterRegistryListener} which shall be removed.
	 */
	public void removeInterpreterRegistryListener(
			IInterpreterRegistryListener listener);
}