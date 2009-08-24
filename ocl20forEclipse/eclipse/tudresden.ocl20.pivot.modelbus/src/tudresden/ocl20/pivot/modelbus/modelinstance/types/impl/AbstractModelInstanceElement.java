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
package tudresden.ocl20.pivot.modelbus.modelinstance.types.impl;

import java.util.Set;

import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * An abstract implementation of {@link IModelInstanceElement}.
 * </p>
 * 
 * @author Ronny Brandt: Built the first version.
 * @author Claas Wilke: Did re-factoring and added Java-doc.
 */
public abstract class AbstractModelInstanceElement implements IModelInstanceElement {

	/**
	 * The {@link Type}s of the {@link IModel} of which this {@link IModelInstanceElement} is an
	 * instance.
	 */
	protected Set<Type> myTypes;

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstanceElement#getName()
	 */
	public String getName() {

		StringBuffer result;

		/* Construct a name of all implemented types. */
		result = new StringBuffer();
		result.append("[");

		for (Type aType : this.getTypes()) {

			if (result.length() == 1) {
				result.append(",");
			}
			// no else.

			result.append(aType.getName());
		}

		result.append("]");

		return result.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstanceElement#getTypes()
	 */
	public Set<Type> getTypes() {

		return this.myTypes;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelInstanceElement#isInstanceOf(tudresden.ocl20
	 * .pivot.pivotmodel.Type)
	 */
	public boolean isInstanceOf(Type type) {

		boolean result;

		result = false;

		for (Type aType : this.myTypes) {

			if (aType.conformsTo(type)) {
				result = true;
				break;
			}
		}

		return result;
	}
}