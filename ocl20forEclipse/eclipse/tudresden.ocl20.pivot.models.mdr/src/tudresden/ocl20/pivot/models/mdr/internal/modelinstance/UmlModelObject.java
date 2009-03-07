/*
Copyright (C) 2008-2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the UML Meta Model of Dresden OCL2 for Eclipse.

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
package tudresden.ocl20.pivot.models.mdr.internal.modelinstance;

import org.eclipse.core.runtime.Platform;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclObject;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.modelbus.base.AbstractModelObject;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Represents instances of {@link Type}s in {@link IModel}s which use the UML1.5
 * (Netbeans MDR) meta model.
 * </p>
 * 
 * @author Claas Wilke
 */
public class UmlModelObject extends AbstractModelObject implements IModelObject {

	/** The adapted model Object of this {@link UmlModelObject}. */
	private Object myAdaptedObject;

	/**
	 * <p>
	 * Creates a new {@link UmlModelObject}.
	 * </p>
	 * 
	 * @param anObject
	 *            The {@link Object} for which an {@link UmlModelObject} shall
	 *            be created.
	 * @param aType
	 *            The {@link Type} this {@link IModelObject} belongs to.
	 */
	public UmlModelObject(Object object, Type aType) {
		this.myAdaptedObject = object;
		this.myType = aType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelObject#getOclObject()
	 */
	public OclRoot getOclObject() {

		/* Eventually initialize the OCL object. */
		if (this.myOclObject == null) {
			this.myOclObject = (OclRoot) Platform.getAdapterManager()
					.getAdapter(this.myAdaptedObject, OclObject.class);
		}

		return this.myOclObject;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UmlModelObject(" + myAdaptedObject.toString() + ")";
	}
}
