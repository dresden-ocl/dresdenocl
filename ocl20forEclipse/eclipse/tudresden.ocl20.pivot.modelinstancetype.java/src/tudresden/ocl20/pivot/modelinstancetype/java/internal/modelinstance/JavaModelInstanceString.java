/*
Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the Java Model Instance Plug-in of Dresden OCL2 for Eclipse.

Dresden OCL2 for Eclipse is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL2 for Eclipse is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>.
 */
package tudresden.ocl20.pivot.modelinstancetype.java.internal.modelinstance;

import java.util.HashSet;

import org.apache.log4j.Logger;

import tudresden.ocl20.pivot.modelbus.base.AbstractModelObject;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceString;
import tudresden.ocl20.pivot.modelinstancetype.java.JavaModelInstanceTypePlugin;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Represents an adaptation for {@link String}s of a {@link JavaModelInstance}.
 * </p>
 * 
 * @author Claas Wilke
 */
public class JavaModelInstanceString extends AbstractModelObject implements
		IModelInstanceString {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			JavaModelInstanceTypePlugin.getLogger(JavaModelInstanceString.class);

	/** The adapted {@link Object} of this {@link JavaModelInstanceString}. */
	private Object myString;

	/**
	 * <p>
	 * Creates a new {@link JavaModelInstanceString}.
	 * </p>
	 * 
	 * @param string
	 *          The {@link Object} that shall be adapted by this
	 *          {@link JavaModelInstanceString}.
	 */
	public JavaModelInstanceString(Object string) {

		/* Eventually debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "JavaModelInstanceString("; //$NON-NLS-1$
			msg += "string = " + string; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		this.myString = string;
		this.myTypes = new HashSet<Type>();

		/* Eventually debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "JavaModelInstanceString(Object) - exit"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceString#getString
	 * ()
	 */
	public String getString() {

		return this.myString.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {

		String result;

		result = JavaModelInstanceString.class.getSimpleName();
		result += "[";
		result += this.myString;
		result += "]";

		return result;
	}
}