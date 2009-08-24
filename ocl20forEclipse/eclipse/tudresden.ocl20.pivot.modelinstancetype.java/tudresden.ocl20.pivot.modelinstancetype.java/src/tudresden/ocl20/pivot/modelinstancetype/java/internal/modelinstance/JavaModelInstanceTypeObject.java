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

import org.apache.log4j.Logger;

import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceTypeObject;
import tudresden.ocl20.pivot.modelinstancetype.java.JavaModelInstanceTypePlugin;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Represents an adaptation for {@link Class}es of a {@link JavaModelInstance}.
 * </p>
 * 
 * @author Claas Wilke
 */
public class JavaModelInstanceTypeObject implements IModelInstanceTypeObject {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			JavaModelInstanceTypePlugin.getLogger(JavaModelInstanceTypeObject.class);

	/** The adapted {@link Class} of this {@link JavaModelInstanceTypeObject}. */
	private Class<?> myClass;

	/** The adapted {@link Type} of this {@link JavaModelInstanceTypeObject}. */
	private Type myType;

	/**
	 * <p>
	 * Creates a new {@link JavaModelInstanceTypeObject}.
	 * </p>
	 * 
	 * @param aClass
	 *          The adapted {@link Class} of this
	 *          {@link JavaModelInstanceTypeObject}.
	 * @param type
	 *          The {@link Type} of the {@link IModel} that is represented by this
	 *          {@link JavaModelInstanceTypeObject}.
	 */
	public JavaModelInstanceTypeObject(Class<?> aClass, Type type) {

		/* Eventually debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "JavaModelInstanceTypeObject("; //$NON-NLS-1$
			msg += "aClass = " + aClass; //$NON-NLS-1$
			msg += ", type = " + type; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		this.myClass = aClass;
		this.myType = type;

		/* Eventually debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "JavaModelInstanceTypeObject(Class, Type) - exit"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceTypeObject#
	 * getAdaptedType()
	 */
	public Class<?> getAdaptedType() {

		return this.myClass;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceTypeObject#
	 * getModelType()
	 */
	public Type getModelType() {

		return this.myType;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {

		String result;

		result = JavaModelInstanceTypeObject.class.getSimpleName();
		result += "[";
		result += this.myClass;
		result += "]";

		return result;
	}
}