/*
 * Copyright (C) 2008-2009 by Michael Thiele & Claas Wilke (claaswilke@gmx.net)
 * This file is part of the UML2 Meta Model of Dresden OCL2 for Eclipse. Dresden
 * OCL2 for Eclipse is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version. Dresden OCL2 for Eclipse is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
 * the GNU Lesser General Public License for more details. You should have
 * received a copy of the GNU Lesser General Public License along with Dresden
 * OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>.
 */
package org.dresdenocl.metamodels.uml2.internal.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Stereotype;

import org.dresdenocl.metamodels.uml2.UML2MetamodelPlugin;
import org.dresdenocl.pivotmodel.Namespace;
import org.dresdenocl.pivotmodel.Operation;
import org.dresdenocl.pivotmodel.Property;
import org.dresdenocl.pivotmodel.Type;
import org.dresdenocl.pivotmodel.base.AbstractType;

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
	 * 
	 * @generated NOT
	 */
	private static final Logger lLOGGER = UML2MetamodelPlugin
			.getLogger(UML2Class.class);

	/** The adapted {@link org.eclipse.uml2.uml.Class} class. */
	private org.eclipse.uml2.uml.Class dslClass;

	/**
	 * <p>
	 * The {@link UML2AdapterFactory} used to create nested elements.
	 * </p>
	 * 
	 * @generated NOT
	 */
	private UML2AdapterFactory factory;

	/**
	 * <p>
	 * Creates a new <code>UML2Class</code> instance.
	 * </p>
	 * 
	 * @param dslClass
	 *            the {@link org.eclipse.uml2.uml.Class} that is adopted by this
	 *            class
	 * @param factory
	 *            The {@link UML2AdapterFactory} used to create nested elements.
	 * 
	 * @generated
	 */
	public UML2Class(org.eclipse.uml2.uml.Class dslClass,
			UML2AdapterFactory factory) {

		if (lLOGGER.isDebugEnabled()) {
			lLOGGER
					.debug("UML2Class(dslClass = " + dslClass + "factory = " + factory + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// initialize
		this.dslClass = dslClass;
		this.factory = factory;

		if (lLOGGER.isDebugEnabled()) {
			lLOGGER.debug("UML2Class() - exit"); //$NON-NLS-1$
		}
	}

	/**
	 * @see org.dresdenocl.pivotmodel.base.AbstractType#getName()
	 * 
	 * @generated NOT
	 */
	@Override
	public String getName() {

		return this.dslClass.getName();
	}

	/**
	 * @see org.dresdenocl.pivotmodel.base.AbstractType#getNamespace()
	 * 
	 * @generated NOT
	 */
	@Override
	public Namespace getNamespace() {

		return this.factory.createNamespace(dslClass.getPackage());
	}

	/**
	 * @see org.dresdenocl.pivotmodel.base.AbstractType#getOwnedPropertyImpl()
	 * 
	 * @generated NOT
	 */
	@Override
	protected List<Property> getOwnedPropertyImpl() {

		List<Property> result;

		result = new ArrayList<Property>();

		for (org.eclipse.uml2.uml.Property property : this.dslClass
				.getOwnedAttributes()) {

			result.add(this.factory.createProperty(property));
		}

		return result;
	}

	/**
	 * @see org.dresdenocl.pivotmodel.base.AbstractType#getOwnedOperationImpl()
	 * 
	 * @generated NOT
	 */
	@Override
	protected List<Operation> getOwnedOperationImpl() {

		List<Operation> result;

		result = new ArrayList<Operation>();

		for (org.eclipse.uml2.uml.Operation operation : this.dslClass
				.getOwnedOperations()) {

			result.add(this.factory.createOperation(operation));
		}

		return result;
	}

	/**
	 * @see org.dresdenocl.pivotmodel.base.AbstractType#getSuperTypeImpl()
	 * 
	 * @generated NOT
	 */
	@Override
	protected List<Type> getSuperTypeImpl() {

		List<Type> result;

		result = new ArrayList<Type>();

		for (Class clazz : this.dslClass.getSuperClasses()) {
			result.add(this.factory.createType(clazz));
		}

		for (Interface interfaze : this.dslClass.getAllImplementedInterfaces()) {
			result.add(this.factory.createType(interfaze));
		}
		
		for (Stereotype stereotype : this.dslClass.getAppliedStereotypes()) {
			result.add(this.factory.createType(stereotype));
		}

		return result;
	}
}