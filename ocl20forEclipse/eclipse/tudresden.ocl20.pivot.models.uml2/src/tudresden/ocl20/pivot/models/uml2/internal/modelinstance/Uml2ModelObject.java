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

import java.util.List;

import org.eclipse.core.runtime.Platform;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclObject;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.modelbus.base.AbstractModelObject;

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
	 * @param object
	 *            The {@link Object} for which an {@link Uml2ModelObject} shall
	 *            be created.
	 * @param name
	 *            The name of the created {@link Uml2ModelObject}.
	 * @param qualifiedName
	 *            The canonical name of the created {@link Uml2ModelObject}.
	 */
	public Uml2ModelObject(Object object, String name,
			List<String> qualifiedName) {

		this.name = name;
		this.myAdaptedObject = object;
		this.qualifiedName = qualifiedName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelObject#getOclObject()
	 */
	public OclRoot getOclObject() {

		/* Eventually initialize the OCL Object. */
		if (this.oclObject == null) {
			this.oclObject = (OclObject) Platform.getAdapterManager()
					.getAdapter((Object) myAdaptedObject, OclObject.class);
		}
		// no else.

		return this.oclObject;
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