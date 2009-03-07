/*
Copyright (C) 2008-2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the UML2 Meta Model of Dresden OCL2 for Eclipse.

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
package tudresden.ocl20.pivot.models.uml2.internal.modelinstance;

import org.eclipse.core.runtime.Platform;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclObject;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.modelbus.base.AbstractModelObject;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Represents model objects of a UML2 model instance.
 * </p>
 * 
 * @author Claas Wilke
 */
public class Uml2ModelObject extends AbstractModelObject implements
		IModelObject {

	/** The Java {@link Object} adapted by this {@link Uml2ModelObject}. */
	protected Object myAdaptedObject;

	/**
	 * <p>
	 * Creates a new {@link Uml2ModelObject}.
	 * </p>
	 * 
	 * @param anObject
	 *            The {@link Object} for which an {@link Uml2ModelObject} shall
	 *            be created.
	 * @param aType
	 *            The {@link Type} this {@link IModelObject} belongs to.
	 */
	public Uml2ModelObject(Object anObject, Type aType) {

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

		result = "Uml2ModelObject(";
		result += this.myAdaptedObject.toString();
		result += ")";

		return result;
	}
}