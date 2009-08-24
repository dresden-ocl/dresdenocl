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

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject;
import tudresden.ocl20.pivot.modelinstancetype.java.JavaModelInstanceTypePlugin;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Represents model objects of a {@link JavaModelInstance}.
 * </p>
 * 
 * @author Claas Wilke
 */
public class JavaModelInstanceObject extends AbstractModelObject implements
		IModelInstanceObject {

	private static final int REFACTORED_TILL_HERE = 0;

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			JavaModelInstanceTypePlugin.getLogger(JavaModelInstanceObject.class);

	/** The Java {@link Object} adapted by this {@link JavaModelInstanceObject}. */
	protected Object myAdaptedObject;

	/**
	 * <p>
	 * Creates a new {@link JavaModelInstanceObject}.
	 * </p>
	 * 
	 * @param anObject
	 *          The {@link Object} for which an {@link JavaModelInstanceObject}
	 *          shall be created.
	 * @param types
	 *          The {@link Type}s this {@link IModelInstanceElement} belongs to.
	 */
	public JavaModelInstanceObject(Object anObject, Set<Type> types) {

		/* Eventually debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "JavaModelInstanceObject("; //$NON-NLS-1$
			msg += "anObject = " + anObject; //$NON-NLS-1$
			msg += ", types = " + types; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		this.myAdaptedObject = anObject;
		this.myTypes = types;

		/* Eventually debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "JavaModelInstanceObject(Object, "; //$NON-NLS-1$
			msg += "Set<Type>) - exit"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceObject#
	 * getAdaptedObject()
	 */
	public Object getObject() {

		return this.myAdaptedObject;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {

		String result;

		result = JavaModelInstanceObject.class.getSimpleName();
		result += "[";
		result += this.myAdaptedObject;
		result += "]";

		return result;
	}

	public IModelInstanceElement getProperty(Property property) {

		// TODO Auto-generated method stub
		return null;
	}

	public IModelInstanceElement invokeOperation(Operation operation,
			List<IModelInstanceElement> args) {

		// TODO Auto-generated method stub
		return null;
	}

	public IModelInstanceElement asType(Type type) {

		// TODO Auto-generated method stub
		return null;
	}

	public Object deepCopy() {

		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {

		// TODO Auto-generated method stub
		return null;
	}

	public Set<Type> getTypes() {

		// TODO Auto-generated method stub
		return null;
	}

	public boolean isInstanceOf(Type type) {

		// TODO Auto-generated method stub
		return false;
	}
}