/*
Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the Java Meta Model of Dresden OCL2 for Eclipse.

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
package org.dresdenocl.metamodels.java.internal.model;

import org.dresdenocl.model.IModel;
import org.dresdenocl.model.IModelProvider;
import org.dresdenocl.model.ModelAccessException;
import org.dresdenocl.model.base.AbstractModel;
import org.dresdenocl.model.metamodel.IMetamodel;
import org.dresdenocl.pivotmodel.Namespace;
import org.dresdenocl.pivotmodel.Operation;
import org.dresdenocl.pivotmodel.Parameter;
import org.dresdenocl.pivotmodel.Property;
import org.dresdenocl.pivotmodel.Type;

/**
 * <p>
 * Represents Models imported from a Java class or interface.
 * </p>
 * 
 * @author Claas Wilke
 */
public class JavaModel extends AbstractModel implements IModel {

	/** The {@link Class} describing the model of this {@link JavaModel}. */
	private Class<?> myModelClass;

	/** The {@link JavaAdapterFactory} of this JavaModel. */
	private JavaAdapterFactory myAdapterFactory;

	/** The adapted root {@link Namespace} for the model. */
	private Namespace myRootNamespace;

	/**
	 * <p>
	 * Creates a new {@link JavaModel} adapting the given {@link Class}.
	 * 
	 * @param class The {@link Class} containing the model.
	 */
	public JavaModel(Class<?> aClass, IMetamodel metamodel,
			IModelProvider provider) {

		super((aClass == null) ? "" : aClass.getCanonicalName(), metamodel,
				provider);

		if (aClass == null) {
			throw new IllegalArgumentException(
					"The parameter 'aClass' must not be null.");
		}
		// no else.

		/* Initialize. */
		this.myModelClass = aClass;

		this.myAdapterFactory = new JavaAdapterFactory();
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.model.IModel#dispose()
	 */
	public void dispose() {

		/* Reset the root name space to avoid caching. */
		this.myRootNamespace = null;
		/* Reset the adapter factory to clear the cache. */
		this.myAdapterFactory = new JavaAdapterFactory();
		
		super.dispose();
	}

	/**
	 * <p>
	 * Overridden to base equality check on the URI of the associated resource.
	 * </p>
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object anObject) {

		boolean result;

		/* Check if the given Object is a JavaModel. */
		if (anObject instanceof JavaModel) {
			JavaModel aModel;

			aModel = (JavaModel) anObject;

			result = this.myModelClass.equals(aModel.myModelClass);
		}

		else {
			result = false;
		}

		return result;
	}

	/**
	 * <p>
	 * Overridden to base the hash code on the associated {@link Class}.
	 * </p>
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		return this.myModelClass.hashCode();
	}

	/**
	 * <p>
	 * Returns a String representation of this {@link JavaModel}.
	 * </p>
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return this.myModelClass.getCanonicalName();
	}

	/**
	 * <p>
	 * Returns a {@link Namespace} adapter for the virtual root package in the
	 * associated {@link IModel}.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 *           Thrown if an error occurs when creating the adapter for the top
	 *           {@link Namespace}.
	 * 
	 * @see org.dresdenocl.model.IModel#getRootNamespace()
	 * 
	 * @generated NOT
	 */
	public Namespace getRootNamespace() throws ModelAccessException {

		/* Probably create the adaptation of the root name space. */
		if (this.myRootNamespace == null) {

			Type mainType;

			mainType = this.getAdapterFactory().createType(this.myModelClass);

			/*
			 * Invoke some operations to register dependent types in the model
			 * adaptation.
			 */
			for (Operation anOperation : mainType.getOwnedOperation()) {
				anOperation.getType();

				for (Parameter aParameter : anOperation.getOwnedParameter()) {
					aParameter.getType();
				}
			}

			for (Property aProperty : mainType.getOwnedProperty()) {
				aProperty.getType();
			}

			mainType.getSuperType();

			this.myRootNamespace = mainType.getNamespace();

			/* Navigate to the root name space. */
			while (this.myRootNamespace.getNestingNamespace() != null) {
				this.myRootNamespace = this.myRootNamespace.getNestingNamespace();
			}

		}
		// no else.

		return this.myRootNamespace;
	}

	/**
	 * <p>
	 * Returns the {@link JavaAdapterFactory} of this JavaModel.
	 * </p>
	 * 
	 * @return The {@link JavaAdapterFactory} of this JavaModel.
	 */
	public JavaAdapterFactory getAdapterFactory() {

		return myAdapterFactory;
	}
}