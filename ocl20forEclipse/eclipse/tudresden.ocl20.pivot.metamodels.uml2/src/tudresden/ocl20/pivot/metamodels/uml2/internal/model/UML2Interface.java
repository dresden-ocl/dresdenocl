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
package tudresden.ocl20.pivot.metamodels.uml2.internal.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.uml2.uml.Classifier;

import tudresden.ocl20.pivot.metamodels.uml2.UML2MetamodelPlugin;
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
 * 
 * @generated
 */
public class UML2Interface extends AbstractType implements Type {

	/**
	 * <p>
	 * Logger for this class
	 * </p>
	 * 
	 * @generated NOT
	 */
	private static final Logger LOGGER =
			UML2MetamodelPlugin.getLogger(UML2Interface.class);

	/**
	 * <p>
	 * the adapted org.eclipse.uml2.uml.Interface class
	 * </p>
	 * 
	 * @generated
	 */
	private org.eclipse.uml2.uml.Interface dslInterface;

	/**
	 * <p>
	 * Creates a new <code>UML2Interface</code> instance.
	 * </p>
	 * 
	 * @param dslInterface
	 *          the {@link org.eclipse.uml2.uml.Interface} that is adopted by this
	 *          class
	 * 
	 * @generated
	 */
	public UML2Interface(org.eclipse.uml2.uml.Interface dslInterface) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("UML2Interface(dslInterface=" + dslInterface + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// initialize
		this.dslInterface = dslInterface;

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("UML2Interface() - exit"); //$NON-NLS-1$
		}
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getName()
	 * 
	 * @generated NOT
	 */
	@Override
	public String getName() {

		return this.dslInterface.getName();
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getNamespace()
	 * 
	 * @generated
	 */
	@Override
	public Namespace getNamespace() {

		return UML2AdapterFactory.INSTANCE.createNamespace(dslInterface
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

		for (org.eclipse.uml2.uml.Property property : this.dslInterface
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

		for (org.eclipse.uml2.uml.Operation operation : this.dslInterface
				.getOwnedOperations()) {

			result.add(UML2AdapterFactory.INSTANCE.createOperation(operation));
		}

		return result;
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getSuperTypeImpl()
	 * 
	 * @generated
	 */
	@Override
	protected List<Type> getSuperTypeImpl() {

		List<Type> result;

		result = new ArrayList<Type>();

		for (Classifier classifier : dslInterface.allParents()) {
			result.add(UML2AdapterFactory.INSTANCE.createType(classifier));
		}

		return result;
	}
}