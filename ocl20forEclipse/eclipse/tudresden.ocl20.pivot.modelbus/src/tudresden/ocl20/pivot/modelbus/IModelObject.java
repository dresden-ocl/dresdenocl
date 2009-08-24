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
package tudresden.ocl20.pivot.modelbus;

import java.util.Set;

import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceBoolean;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceInteger;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceReal;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceString;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Represents an instance of a domain specific type in an {@link IModel}.
 * </p>
 * 
 * @author Ronny Brandt: Developed the first version.
 * @author Claas Wilke: Did refactoring and added Javadoc.
 */
public interface IModelObject {

	/**
	 * <p>
	 * Returns the {@link Type}s of that this IModelObject is an instance.
	 * </p>
	 * 
	 * <p>
	 * <strong>Please be aware, that the {@link Type}s of an {@link IModelObject}
	 * are only set, if the {@link IModelObject} represents at least one Type in
	 * the {@link IModel}. The {@link IModelObject}s of the kinds
	 * {@link IModelInstanceCollection}, {@link IModelInstanceBoolean},
	 * {@link IModelInstanceInteger}, {@link IModelInstanceReal}, and
	 * {@link IModelInstanceString} do not have a {@link Type}.</strong>
	 * </p>
	 * 
	 * @return The {@link Type}s of which this IModelObject is an instance. </p>
	 */
	Set<Type> getTypes();

	/**
	 * <p>
	 * Returns the name of the {@link IModelObject}.
	 * </p>
	 * 
	 * @return The name of the {@link IModelObject}.
	 */
	String getName();

	/**
	 * <p>
	 * Returns the qualified name as of the {@link IModelObject} as {@link String}
	 * . Needed for more efficiently use in InterpreterView.
	 * </p>
	 * 
	 * @return The qualified name of the {@link IModelObject} as {@link String}.
	 */
	String getQualifiedName();

	/**
	 * <p>
	 * Returns true if this {@link IModelObject} is an instance of the given
	 * {@link Type} in the {@link IModel}.
	 * </p>
	 * 
	 * @param aType
	 *          The {@link Type} which shall be checked.
	 * @return True, if this {@link IModelObject} is an instance of the given
	 *         {@link Type}.
	 */
	boolean isInstanceOf(Type aType);
}
