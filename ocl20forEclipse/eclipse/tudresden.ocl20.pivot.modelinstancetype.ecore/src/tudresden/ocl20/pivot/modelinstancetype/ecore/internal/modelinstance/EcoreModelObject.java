/*
Copyright (C) 2007-2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the EMF Ecore Model Instance Type of Dresden OCL2 for Eclipse.

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
package tudresden.ocl20.pivot.modelinstancetype.ecore.internal.modelinstance;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclObject;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.base.AbstractModelObject;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Represents {@link IModel} {@link Type} instances of the EMF Ecore model instance
 * type.
 * </p>
 * 
 * @author Claas Wilke
 */
public class EcoreModelObject extends AbstractModelObject implements
		IModelInstanceElement {

	/** The adapted {@link EObject} of this {@link EcoreModelObject}. */
	private EObject myObject;

	/**
	 * <p>
	 * Creates a new {@link EcoreModelObject}.
	 * </p>
	 * 
	 * @param anEObject
	 *            The {@link EObject} which shall be adapted by this
	 *            {@link EcoreModelObject}.
	 * @param aType
	 *            The {@link Type} of the {@link IModel} of which this
	 *            {@link EcoreModelObject} is an instance.
	 */
	public EcoreModelObject(EObject anEObject, Type aType) {
		this.myObject = anEObject;
		this.myType = aType;
	}

	/**
	 * <p>
	 * Returns the {@link OclRoot} object of this {@link EcoreModelObject}.
	 * </p>
	 * 
	 * @return The {@link OclRoot} object of this {@link EcoreModelObject}.
	 */
	public OclRoot getOclObject() {

		/* Eventually initialize the OCL object. */
		if (this.myOclObject == null) {
			this.myOclObject = (OclObject) Platform.getAdapterManager()
					.getAdapter((Object) this.myObject, OclObject.class);
		}
		// no else.

		return this.myOclObject;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EObject(" + this.myObject.toString() + ")";
	}
}