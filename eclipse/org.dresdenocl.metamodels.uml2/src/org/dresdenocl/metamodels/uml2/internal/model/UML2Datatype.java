/*
 * Copyright (C) 2008-2011 by Michael Thiele & Claas Wilke (claas.wilke@tu-dresden.de)
 * This file is part of the UML2 Metamodel of Dresden OCL. Dresden
 * OCL is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version. Dresden OCL2 for Eclipse is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
 * the GNU Lesser General Public License for more details. You should have
 * received a copy of the GNU Lesser General Public License along with Dresden
 * OCL. If not, see <http://www.gnu.org/licenses/>.
 */
package tudresden.ocl20.pivot.metamodels.uml2.internal.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;

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
 * @author Claas Wilke
 * @generated NOT
 */
public class UML2Datatype extends AbstractType implements Type {

	/**
	 * <p>
	 * Logger for this class
	 * </p>
	 * 
	 * @generated NOT
	 */
	private static final Logger lLOGGER = UML2MetamodelPlugin
			.getLogger(UML2Datatype.class);

	/** The adapted {@link org.eclipse.uml2.uml.Class} class. */
	private org.eclipse.uml2.uml.DataType dslDataType;

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
	 * Creates a new {@link UML2Datatype} instance.
	 * </p>
	 * 
	 * @param dslDataType
	 *            the {@link DataType} that is adopted by this class
	 * @param factory
	 *            The {@link UML2AdapterFactory} used to create nested elements.
	 * 
	 * @generated
	 */
	public UML2Datatype(DataType dslDataType, UML2AdapterFactory factory) {

		if (lLOGGER.isDebugEnabled()) {
			lLOGGER.debug("UML2Datatype(dslDataType = " + dslDataType + "factory = " + factory + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// initialize
		this.dslDataType = dslDataType;
		this.factory = factory;

		if (lLOGGER.isDebugEnabled()) {
			lLOGGER.debug("UML2Datatype() - exit"); //$NON-NLS-1$
		}
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getName()
	 * 
	 * @generated NOT
	 */
	@Override
	public String getName() {

		return this.dslDataType.getName();
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getNamespace()
	 * 
	 * @generated NOT
	 */
	@Override
	public Namespace getNamespace() {

		return this.factory.createNamespace(dslDataType.getPackage());
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

		for (org.eclipse.uml2.uml.Property property : this.dslDataType
				.getOwnedAttributes()) {

			result.add(this.factory.createProperty(property));
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

		for (org.eclipse.uml2.uml.Operation operation : this.dslDataType
				.getOwnedOperations()) {

			result.add(this.factory.createOperation(operation));
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

		for (Classifier classifier : this.dslDataType.getRedefinedClassifiers()) {
			result.add(this.factory.createType(classifier));
		}

		return result;
	}
}