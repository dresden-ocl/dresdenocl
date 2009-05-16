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

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.osgi.util.NLS;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Interface;

import tudresden.ocl20.pivot.pivotmodel.Enumeration;
import tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral;
import tudresden.ocl20.pivot.pivotmodel.NamedElement;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * A factory to create Pivot Model types for this meta model.
 * </p>
 * 
 * @author Michael Thiele
 * @generated NOT
 */
public class UML2AdapterFactory {

	/**
	 * logger for this class
	 * 
	 * @generated
	 */
	private static final Logger logger = Logger
			.getLogger(UML2AdapterFactory.class);

	/**
	 * <p>
	 * The Singleton instance of the factory.
	 * </p>
	 * 
	 * @generated
	 */
	public static UML2AdapterFactory INSTANCE = new UML2AdapterFactory();

	/**
	 * <p>
	 * A cache for previously created adapters.
	 * </p>
	 * 
	 * @generated
	 */
	private Map<org.eclipse.uml2.uml.NamedElement, NamedElement> adapters;

	/**
	 * <p>
	 * Clients are not supposed to instantiate this class.
	 * </p>
	 * 
	 * @generated
	 */
	private UML2AdapterFactory() {
		adapters = new HashMap<org.eclipse.uml2.uml.NamedElement, NamedElement>();
	}

	/**
	 * <p>
	 * Creates a {@link Namespace} adapter for an
	 * {@link org.eclipse.uml2.uml.Package}.
	 * </p>
	 * 
	 * @generated NOT
	 */
	public Namespace createNamespace(org.eclipse.uml2.uml.Package dslPackage) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("createNamespace(dslPackage=" + dslPackage
					+ ") - enter");
		}
		// no else.

		Namespace result;

		/* Check if the dsl package is null. */
		if (dslPackage == null) {
			result = null;
		}

		/* Else try to get the name space. */
		else {

			result = (Namespace) this.adapters.get(dslPackage);

			/* Check if the result is null. */
			if (result == null) {

				/* Eventually log this information. */
				if (logger.isInfoEnabled()) {
					logger
							.info(NLS
									.bind(
											UML2ModelMessages.UML2AdapterFactory_CreatingPivotModelAdapter,
											"org.eclipse.uml2.uml.Package",
											dslPackage.getName()));
				}
				// no else.

				result = new UML2Package(dslPackage);
				this.adapters.put(dslPackage, result);
			}
			// no else.
		}
		// end else.

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("createNamespace() - exit - return value=" + result);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Creates a {@link Type} adapter for a {@link org.eclipse.uml2.uml.Class}.
	 * </p>
	 * 
	 * @generated
	 */
	public Type createType(org.eclipse.uml2.uml.Class dslClass) {
		if (logger.isDebugEnabled()) {
			logger.debug("createType(dslClass=" + dslClass + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (dslClass == null) {
			if (logger.isDebugEnabled())
				logger.debug("createType() - exit: dslClass is null");
			return null;
		}

		Type type = (Type) adapters.get(dslClass);

		if (type == null) {
			type = new UML2Class(dslClass);
			adapters.put(dslClass, type);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("createType() - exit - return value=" + type); //$NON-NLS-1$
		}

		return type;
	}

	/**
	 * <p>
	 * Creates a {@link Type} adapter for a
	 * {@link org.eclipse.uml2.uml.Interface}.
	 * </p>
	 * 
	 * @generated
	 */
	public Type createType(org.eclipse.uml2.uml.Interface dslInterface) {
		if (logger.isDebugEnabled()) {
			logger.debug("createType(dslInterface=" + dslInterface
					+ ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (dslInterface == null) {
			if (logger.isDebugEnabled())
				logger.debug("createType() - exit: dslInterface is null");
			return null;
		}

		Type type = (Type) adapters.get(dslInterface);

		if (type == null) {
			type = new UML2Interface(dslInterface);
			adapters.put(dslInterface, type);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("createType() - exit - return value=" + type); //$NON-NLS-1$
		}

		return type;
	}

	/**
	 * <p>
	 * Creates a {@link Type} adapter for a {@link DataType}.
	 * </p>
	 * 
	 * @generated
	 */
	public Type createType(DataType dataType) {
		if (logger.isDebugEnabled()) {
			logger.debug("createType(dataType=" + dataType + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (dataType == null) {
			if (logger.isDebugEnabled())
				logger.debug("createType() - exit: dataType is null");
			return null;
		}

		Type type = (Type) adapters.get(dataType);

		if (type == null) {
			type = new UML2DataType(dataType);
			adapters.put(dataType, type);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("createType() - exit - return value=" + type); //$NON-NLS-1$
		}

		return type;
	}

	/**
	 * <p>
	 * Creates a specific Type.
	 * </p>
	 * 
	 * @param dslType
	 *            The type which shall be created.
	 * 
	 * @return A specific created Type
	 * 
	 * @generated NOT
	 */
	public Type createType(org.eclipse.uml2.uml.Type dslType) {

		Type result;

		result = null;

		if (dslType == null) {
			result = null;
		}

		else if (dslType instanceof Class) {
			result = createType((Class) dslType);
		}

		else if (dslType instanceof org.eclipse.uml2.uml.PrimitiveType) {
			result = createPrimitiveType((org.eclipse.uml2.uml.PrimitiveType) dslType);
		}

		else if (dslType instanceof org.eclipse.uml2.uml.Enumeration) {
			result = createEnumeration((org.eclipse.uml2.uml.Enumeration) dslType);
		}

		else if (dslType instanceof DataType) {
			result = createType((DataType) dslType);
		}

		else if (dslType instanceof Interface) {
			result = createType((Interface) dslType);
		}

		else {
			/* Should not happen. */
			throw new IllegalArgumentException("Unknown Type: " + dslType);
		}

		return result;
	}

	/**
	 * <p>
	 * Creates an {@link Enumeration} adapter for an
	 * {@link org.eclipse.uml2.uml.Enumeration}.
	 * </p>
	 * 
	 * @generated
	 */
	public Enumeration createEnumeration(
			org.eclipse.uml2.uml.Enumeration dslEnumeration) {
		if (logger.isDebugEnabled()) {
			logger.debug("createEnumeration(dslEnumeration=" + dslEnumeration
					+ ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (dslEnumeration == null) {
			if (logger.isDebugEnabled())
				logger
						.debug("createEnumeration() - exit: dslEnumeration is null");
			return null;
		}

		Enumeration enumeration = (Enumeration) adapters.get(dslEnumeration);

		if (enumeration == null) {
			enumeration = new UML2Enumeration(dslEnumeration);
			adapters.put(dslEnumeration, enumeration);
		}

		if (logger.isDebugEnabled()) {
			logger
					.debug("createEnumeration() - exit - return value=" + enumeration); //$NON-NLS-1$
		}

		return enumeration;
	}

	/**
	 * <p>
	 * Creates an {@link EnumerationLiteral} adapter for an
	 * {@link org.eclipse.uml2.uml.EnumerationLiteral}.
	 * </p>
	 * 
	 * @generated
	 */
	public EnumerationLiteral createEnumerationLiteral(
			org.eclipse.uml2.uml.EnumerationLiteral dslEnumerationLiteral) {
		if (logger.isDebugEnabled()) {
			logger.debug("createEnumerationLiteral(dslEnumerationLiteral="
					+ dslEnumerationLiteral + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (dslEnumerationLiteral == null) {
			if (logger.isDebugEnabled())
				logger
						.debug("createEnumerationLiteral() - exit: dslEnumerationLiteral is null");
			return null;
		}

		EnumerationLiteral literal = (EnumerationLiteral) adapters
				.get(dslEnumerationLiteral);

		if (literal == null) {
			literal = new UML2EnumerationLiteral(dslEnumerationLiteral);
			adapters.put(dslEnumerationLiteral, literal);
		}

		if (logger.isDebugEnabled()) {
			logger
					.debug("createEnumerationLiteral() - exit - return value=" + literal); //$NON-NLS-1$
		}

		return literal;
	}

	/**
	 * <p>
	 * Creates a {@link PrimitiveType} adapter for a
	 * {@link org.eclipse.uml2.uml.PrimitiveType}.
	 * </p>
	 * 
	 * @generated
	 */
	public PrimitiveType createPrimitiveType(
			org.eclipse.uml2.uml.PrimitiveType dslPrimitiveType) {
		if (logger.isDebugEnabled()) {
			logger.debug("createPrimitiveType(dslPrimitiveType="
					+ dslPrimitiveType + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (dslPrimitiveType == null) {
			if (logger.isDebugEnabled())
				logger
						.debug("createPrimitiveType() - exit: dslPrimitiveType is null");
			return null;
		}

		PrimitiveType primitiveType = (PrimitiveType) adapters
				.get(dslPrimitiveType);

		if (primitiveType == null) {
			primitiveType = new UML2PrimitiveType(dslPrimitiveType);
			adapters.put(dslPrimitiveType, primitiveType);
		}

		if (logger.isDebugEnabled()) {
			logger
					.debug("createPrimitiveType() - exit - return value=" + primitiveType); //$NON-NLS-1$
		}

		return primitiveType;
	}

	/**
	 * <p>
	 * Creates a {@link Property} adapter for a
	 * {@link org.eclipse.uml2.uml.Property}.
	 * </p>
	 * 
	 * @generated
	 */
	public Property createProperty(org.eclipse.uml2.uml.Property dslProperty) {
		if (logger.isDebugEnabled()) {
			logger.debug("createProperty(dslProperty=" + dslProperty
					+ ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (dslProperty == null) {
			if (logger.isDebugEnabled())
				logger.debug("createProperty() - exit: dslProperty is null");
			return null;
		}

		Property property = (Property) adapters.get(dslProperty);

		if (property == null) {
			property = new UML2Property(dslProperty);
			adapters.put(dslProperty, property);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("createProperty() - exit - return value=" + property); //$NON-NLS-1$
		}

		return property;
	}

	/**
	 * <p>
	 * Creates a {@link Property} adapter for a
	 * {@link org.eclipse.uml2.uml.Association}.
	 * </p>
	 * 
	 * @generated
	 */
	public Property createProperty(org.eclipse.uml2.uml.Association dslProperty) {
		if (logger.isDebugEnabled()) {
			logger.debug("createProperty(dslProperty=" + dslProperty
					+ ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (dslProperty == null) {
			if (logger.isDebugEnabled())
				logger.debug("createProperty() - exit: dslProperty is null");
			return null;
		}

		Property property = (Property) adapters.get(dslProperty);

		if (property == null) {
			property = new UML2Association(dslProperty);
			adapters.put(dslProperty, property);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("createProperty() - exit - return value=" + property); //$NON-NLS-1$
		}

		return property;
	}

	/**
	 * <p>
	 * Creates an {@link Operation} adapter for an
	 * {@link org.eclipse.uml2.uml.Operation}.
	 * </p>
	 * 
	 * @generated
	 */
	public Operation createOperation(org.eclipse.uml2.uml.Operation dslOperation) {
		if (logger.isDebugEnabled()) {
			logger.debug("createOperation(dslOperation=" + dslOperation
					+ ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (dslOperation == null) {
			if (logger.isDebugEnabled())
				logger.debug("createOperation() - exit: dslOperation is null");
			return null;
		}

		Operation operation = (Operation) adapters.get(dslOperation);

		if (operation == null) {
			operation = new UML2Operation(dslOperation);
			adapters.put(dslOperation, operation);
		}

		if (logger.isDebugEnabled()) {
			logger
					.debug("createOperation() - exit - return value=" + operation); //$NON-NLS-1$
		}

		return operation;
	}

	/**
	 * <p>
	 * Creates a {@link Parameter} adapter for a
	 * {@link org.eclipse.uml2.uml.Parameter}.
	 * </p>
	 * 
	 * @generated
	 */
	public Parameter createParameter(org.eclipse.uml2.uml.Parameter dslParameter) {
		if (logger.isDebugEnabled()) {
			logger.debug("createParameter(dslParameter=" + dslParameter
					+ ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (dslParameter == null) {
			if (logger.isDebugEnabled())
				logger.debug("createParameter() - exit: dslParameter is null");
			return null;
		}

		Parameter parameter = (Parameter) adapters.get(dslParameter);

		if (parameter == null) {
			parameter = new UML2Parameter(dslParameter);
			adapters.put(dslParameter, parameter);
		}

		if (logger.isDebugEnabled()) {
			logger
					.debug("createParameter() - exit - return value=" + parameter); //$NON-NLS-1$
		}

		return parameter;
	}
}