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
public class JavaModelObject extends AbstractModelObject implements
		IModelObject {

	/** The Java {@link Object} adapted by this {@link JavaModelObject}. */
	protected Object myAdaptedObject;

	/**
	 * <p>
	 * Creates a new {@link JavaModelObject}.
	 * </p>
	 * 
	 * @param anObject
	 *            The {@link Object} for which an {@link JavaModelObject} shall
	 *            be created.
	 * @param aType
	 *            The {@link Type} this {@link IModelObject} belongs to.
	 */
	public JavaModelObject(Object anObject, Type aType) {

		this.myAdaptedObject = anObject;
		this.myType = aType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelObject#getOclObject()
	 */
	public OclRoot getOclObject() {

		/* Eventually initialize the OCL Object. */
		if (this.myOclObject == null) {
			this.myOclObject = (OclObject) Platform.getAdapterManager()
					.getAdapter((Object) myAdaptedObject, OclObject.class);
		}
		// no else.

		return this.myOclObject;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {

		String result;

		result = "JavaModelObject(";
		result += this.myAdaptedObject.toString();
		result += ")";

		return result;
	}
}