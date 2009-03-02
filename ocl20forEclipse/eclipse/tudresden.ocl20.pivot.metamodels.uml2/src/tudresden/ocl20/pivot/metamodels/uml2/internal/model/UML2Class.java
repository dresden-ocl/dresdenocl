/*
Copyright (C) 2008-2009 by Michael Thiele & Claas Wilke (claaswilke@gmx.net)

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
package tudresden.ocl20.pivot.metamodels.uml2.internal.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.uml2.uml.Class;

import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractType;

/**
 * <p>
 * An implementation of the Pivot Model {@link Type} concept for UML2.
 * </p>
 * 
 * @author Michael Thiele
 * @generated NOT
 */
public class UML2Class extends AbstractType implements Type {

	/**
	 * <p>
	 * Logger for this class
	 * </p>
	 */
	private static final Logger logger = Logger.getLogger(UML2Class.class);

	/** The adapted {@link org.eclipse.uml2.uml.Class} class. */
	private org.eclipse.uml2.uml.Class dslClass;

	/**
	 * <p>
	 * Creates a new <code>UML2Class</code> instance.
	 * </p>
	 * 
	 * @param dslClass
	 *            the {@link org.eclipse.uml2.uml.Class} that is adopted by this
	 *            class
	 * 
	 * @generated
	 */
	public UML2Class(org.eclipse.uml2.uml.Class dslClass) {

		if (logger.isDebugEnabled()) {
			logger.debug("UML2Class(dslClass=" + dslClass + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// initialize
		this.dslClass = dslClass;

		if (logger.isDebugEnabled()) {
			logger.debug("UML2Class() - exit"); //$NON-NLS-1$
		}
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getName()
	 * 
	 * @generated NOT
	 */
	@Override
	public String getName() {
		return this.dslClass.getName();
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getNamespace()
	 * 
	 * @generated NOT
	 */
	@Override
	public Namespace getNamespace() {
		return UML2AdapterFactory.INSTANCE.createNamespace(dslClass
				.getPackage());
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getOwnedPropertyImpl()
	 * 
	 * @generated NOT
	 */
	@Override
	protected List<Property> getOwnedPropertyImpl() {

		List<Property> result;

		result = new ArrayList<Property>();

		for (org.eclipse.uml2.uml.Property property : this.dslClass
				.getOwnedAttributes()) {

			result.add(UML2AdapterFactory.INSTANCE.createProperty(property));
		}

		return result;
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getOwnedOperationImpl()
	 * 
	 * @generated NOT
	 */
	@Override
	protected List<Operation> getOwnedOperationImpl() {

		List<Operation> result;

		result = new ArrayList<Operation>();

		for (org.eclipse.uml2.uml.Operation operation : this.dslClass
				.getOwnedOperations()) {

			result.add(UML2AdapterFactory.INSTANCE.createOperation(operation));
		}

		return result;
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getSuperTypeImpl()
	 * 
	 * @generated NOT
	 */
	@Override
	protected List<Type> getSuperTypeImpl() {

		List<Type> result;

		result = new ArrayList<Type>();

		for (Class clazz : this.dslClass.getSuperClasses()) {

			result.add(UML2AdapterFactory.INSTANCE.createType(clazz));
		}

		return result;
	}
}