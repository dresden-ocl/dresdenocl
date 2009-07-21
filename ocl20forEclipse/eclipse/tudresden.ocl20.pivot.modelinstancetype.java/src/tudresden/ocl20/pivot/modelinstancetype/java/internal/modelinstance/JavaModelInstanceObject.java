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

import java.util.Set;

import org.eclipse.core.runtime.Platform;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclObject;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.modelbus.base.AbstractModelObject;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Represents model objects of a {@link JavaModelInstance}.
 * </p>
 * 
 * @author Claas Wilke
 */
public class JavaModelInstanceObject extends AbstractModelObject implements
		IModelObject {

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
	 *          The {@link Type}s this {@link IModelObject} belongs to.
	 */
	public JavaModelInstanceObject(Object anObject, Set<Type> types) {

		this.myAdaptedObject = anObject;
		this.myTypes = types;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.modelbus.IModelObject#getAdaptedObject()
	 */
	public Object getAdaptedObject() {
	
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
}