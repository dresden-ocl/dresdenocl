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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.AssociationClass;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Profile;

import org.dresdenocl.essentialocl.EssentialOclPlugin;
import org.dresdenocl.metamodels.uml2.UML2MetamodelPlugin;
import org.dresdenocl.pivotmodel.Enumeration;
import org.dresdenocl.pivotmodel.EnumerationLiteral;
import org.dresdenocl.pivotmodel.AssociationProperty;
import org.dresdenocl.pivotmodel.NamedElement;
import org.dresdenocl.pivotmodel.Namespace;
import org.dresdenocl.pivotmodel.Operation;
import org.dresdenocl.pivotmodel.Parameter;
import org.dresdenocl.pivotmodel.PrimitiveType;
import org.dresdenocl.pivotmodel.PrimitiveTypeKind;
import org.dresdenocl.pivotmodel.Property;
import org.dresdenocl.pivotmodel.Type;

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
	 * @generated NOT
	 */
	private static final Logger LOGGER = UML2MetamodelPlugin
			.getLogger(UML2AdapterFactory.class);

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
	 * A cache for previously created {@link Namespace}s stored by their
	 * qualified name.
	 * </p>
	 * 
	 * @generated NOT
	 */
	private Map<String, UML2Package> adaptedNamespaces = new HashMap<String, UML2Package>();

	/**
	 * <p>
	 * A cache for previously created void return parameters.
	 * </p>
	 * 
	 * @generated NOT
	 */
	private Map<org.eclipse.uml2.uml.Operation, Parameter> adaptedVoidReturnParameters;

	/**
	 * <p>
	 * The root {@link Namespace} belonging to the {@link UML2Model} of this
	 * {@link UML2AdapterFactory}.
	 * </p>
	 * 
	 * @generated NOT
	 */
	private Namespace rootNamespace;

	/**
	 * <p>
	 * Creates a new {@link UML2AdapterFactory}.
	 * </p>
	 * 
	 * @generated NOT
	 */
	public UML2AdapterFactory(Namespace rootNamespace) {

		this.adapters = new HashMap<org.eclipse.uml2.uml.NamedElement, NamedElement>();

		this.adaptedVoidReturnParameters = new HashMap<org.eclipse.uml2.uml.Operation, Parameter>();

		this.rootNamespace = rootNamespace;
	}

	/**
	 * <p>
	 * Creates a {@link Namespace} adapter for an
	 * {@link org.eclipse.uml2.uml.Package}.
	 * </p>
	 * 
	 * <p>
	 * This method should only be used to get {@link Namespace}s that have
	 * already been created and are cached. Since otherwise the nesting
	 * {@link Namespace} will be set to <code>null</code>.
	 * </p>
	 * 
	 * @param dslPackage
	 *            The {Package} that shall be adapted.
	 * @generated NOT
	 */
	public Namespace createNamespace(org.eclipse.uml2.uml.Package dslPackage) {

		/* Probably log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createNamespace(dslPackage=" + dslPackage
					+ ") - enter");
		}
		// no else.

		Namespace result = this.createNamespace(dslPackage, null);

		/* Probably log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createNamespace() - exit - return value=" + result);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Creates a {@link Namespace} adapter for an
	 * {@link org.eclipse.uml2.uml.Package}.
	 * </p>
	 * 
	 * @param dslPackage
	 *            The {Package} that shall be adapted.
	 * @param nestingNamespace
	 *            The nesting {@link Namespace} of the {@link UML2Package}.
	 * @generated NOT
	 */
	public Namespace createNamespace(org.eclipse.uml2.uml.Package dslPackage,
			Namespace nestingNamespace) {

		/* Probably log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createNamespace(dslPackage = " + dslPackage
					+ "nestingNamespace = " + nestingNamespace + ") - enter");
		}
		// no else.

		Namespace result;

		/*
		 * A Package can be null or a model or profile, if the method
		 * getNestingNamespace is accessed on imported model elements. Thus,
		 * return the root name space instead.
		 */
		if (dslPackage == null || dslPackage instanceof Model
				|| dslPackage instanceof Profile) {
			result = this.rootNamespace;
		}

		/* Else adapt or return an already adapted UML2Package. */
		else {
			UML2Package umlPackageResult;

			//TODO: remove because if/else
			/* Check if the dsl package is null. */
			//if (dslPackage == null) {
			//	throw new IllegalArgumentException(
			//			"The Package to be adpated cannot be null.");
			//}
			// no else.

			/* Probably reuse or merge with other package. */
			if (this.adaptedNamespaces.containsKey(dslPackage
					.getQualifiedName())) {
				umlPackageResult = this.adaptedNamespaces.get(dslPackage
						.getQualifiedName());
				umlPackageResult.mergePackage(dslPackage);
			}

			/* Else create a new package. */
			else {
				if (nestingNamespace == null) {
					LOGGER.warn("Created UML2Package "
							+ dslPackage.getQualifiedName()
							+ " without nesting name space.");
				}
				// no else.

				umlPackageResult = new UML2Package(dslPackage,
						nestingNamespace, this);

				/* Cache the create name space. */
				this.adaptedNamespaces.put(dslPackage.getQualifiedName(),
						umlPackageResult);
			}
			// no else.

			result = umlPackageResult;
		}

		/* Probably log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createNamespace() - exit - return value=" + result);
		}
		// no else.

		return result;
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

		/* Check if the given type is null. */
		if (dslType == null) {
			result = EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
					.getOclVoid();
		}

		/* Else if the given type is a class, adapt to Type. */
		else if (dslType instanceof Class) {
			result = createType((Class) dslType);
		}

		/* If the given type is a primitive Type. */
		else if (dslType instanceof org.eclipse.uml2.uml.PrimitiveType) {

			/* Check if the type can be adapted to a primitive type. */
			if (!UML2PrimitiveType.getKind(dslType).equals(
					PrimitiveTypeKind.UNKNOWN)) {
				result = createPrimitiveType((org.eclipse.uml2.uml.PrimitiveType) dslType);
			}

			/* Else adapt to Type. */
			else {
				result = createTypePrimitiveType((org.eclipse.uml2.uml.PrimitiveType) dslType);
			}
		}

		else if (dslType instanceof org.eclipse.uml2.uml.Enumeration) {
			result = createEnumeration((org.eclipse.uml2.uml.Enumeration) dslType);
		}

		else if (dslType instanceof Interface) {
			result = createType((Interface) dslType);
		}

		/* Else if the given type is a datatype, adapt to Type. */
		else if (dslType instanceof DataType) {
			result = createType((DataType) dslType);
		}

		/* Check if aType is an association class. */
		/*
		 * FIXME Claas: Discussion with Micha: should we support association
		 * classes?
		 */
		else if (dslType instanceof AssociationClass) {

			AssociationClass anAssociationClass;
			List<org.eclipse.uml2.uml.Property> allEnds;

			/* Cast to AssociationClass. */
			anAssociationClass = (AssociationClass) dslType;

			/* Get all association ends. */
			allEnds = anAssociationClass.getOwnedEnds();

			/* Add all other ends to each navigable end. */
			this.addNavigableAssociationEnds(allEnds);
		}

		/* Else check if aType is another kind of association. */
		else if (dslType instanceof Association) {

			Association anAssociation;

			List<org.eclipse.uml2.uml.Property> allEnds;

			/* Cast to association. */
			anAssociation = (Association) dslType;

			/* Get all association ends. */
			allEnds = anAssociation.getOwnedEnds();

			/* Add all other ends to each navigable end. */
			this.addNavigableAssociationEnds(allEnds);
		}
		// no else.

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

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createEnumeration(dslEnumeration=" + dslEnumeration
					+ ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (dslEnumeration == null) {
			if (LOGGER.isDebugEnabled())
				LOGGER
						.debug("createEnumeration() - exit: dslEnumeration is null");
			return null;
		}

		Enumeration enumeration = (Enumeration) adapters.get(dslEnumeration);

		if (enumeration == null) {
			enumeration = new UML2Enumeration(dslEnumeration, this);
			adapters.put(dslEnumeration, enumeration);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER
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

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createEnumerationLiteral(dslEnumerationLiteral="
					+ dslEnumerationLiteral + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (dslEnumerationLiteral == null) {
			if (LOGGER.isDebugEnabled())
				LOGGER
						.debug("createEnumerationLiteral() - exit: dslEnumerationLiteral is null");
			return null;
		}

		EnumerationLiteral literal = (EnumerationLiteral) adapters
				.get(dslEnumerationLiteral);

		if (literal == null) {
			literal = new UML2EnumerationLiteral(dslEnumerationLiteral, this);
			adapters.put(dslEnumerationLiteral, literal);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("createEnumerationLiteral() - exit - return value=" + literal); //$NON-NLS-1$
		}

		return literal;
	}

	/**
	 * <p>
	 * Creates a {@link AssociationProperty} adapter for a
	 * {@link org.eclipse.uml2.uml.Property}.
	 * </p>
	 * 
	 * @generated NOT
	 */
	public AssociationProperty createAssociationProperty(
			org.eclipse.uml2.uml.Property dslProperty) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("createAssociationProperty(dslProperty=" + dslProperty + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (dslProperty == null) {
			if (LOGGER.isDebugEnabled())
				LOGGER
						.debug("createAssociationProperty() - exit: dslProperty is null");
			return null;
		}

		AssociationProperty property = (AssociationProperty) adapters
				.get(dslProperty);

		if (property == null) {
			property = new UML2AssociationProperty(dslProperty, this);
			adapters.put(dslProperty, property);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("createAssociationProperty() - exit - return value=" + property); //$NON-NLS-1$
		}

		return property;
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

		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("createProperty(dslProperty=" + dslProperty + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (dslProperty == null) {
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("createProperty() - exit: dslProperty is null");
			return null;
		}

		Property property = (Property) adapters.get(dslProperty);

		if (property == null) {
			property = new UML2Property(dslProperty, this);
			adapters.put(dslProperty, property);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createProperty() - exit - return value=" + property); //$NON-NLS-1$
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

		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("createProperty(dslProperty=" + dslProperty + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (dslProperty == null) {
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("createProperty() - exit: dslProperty is null");
			return null;
		}

		Property property = (Property) adapters.get(dslProperty);

		if (property == null) {
			property = new UML2Association(dslProperty, this);
			adapters.put(dslProperty, property);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createProperty() - exit - return value=" + property); //$NON-NLS-1$
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

		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("createOperation(dslOperation=" + dslOperation + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (dslOperation == null) {
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("createOperation() - exit: dslOperation is null");
			return null;
		}

		Operation operation = (Operation) adapters.get(dslOperation);

		if (operation == null) {
			operation = new UML2Operation(dslOperation, this);
			adapters.put(dslOperation, operation);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER
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

		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("createParameter(dslParameter=" + dslParameter + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (dslParameter == null) {
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("createParameter() - exit: dslParameter is null");
			return null;
		}

		Parameter parameter = (Parameter) adapters.get(dslParameter);

		if (parameter == null) {
			parameter = new UML2Parameter(dslParameter, this);
			adapters.put(dslParameter, parameter);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("createParameter() - exit - return value=" + parameter); //$NON-NLS-1$
		}

		return parameter;
	}

	/**
	 * <p>
	 * Creates a return parameter with the <code>void</code> {@link Type} for a
	 * given {@link org.eclipse.uml2.uml.Operation} that has no return
	 * parameter.
	 * </p>
	 * 
	 * @param dslOperation
	 *            The {@link org.eclipse.uml2.uml.Operation} whose void return
	 *            {@link Parameter} shall be created.
	 * @return The created {@link Parameter}.
	 * 
	 * @generated NOT
	 */
	public Parameter createVoidReturnParameter(
			org.eclipse.uml2.uml.Operation dslOperation) {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createVoidReturnParameter("; //$NON-NLS-1$ //$NON-NLS-2$
			msg += "dslOperation = " + dslOperation; //$NON-NLS-1$ //$NON-NLS-2$
			msg += ") - enter"; //$NON-NLS-1$ //$NON-NLS-2$

			LOGGER.debug(msg);
		}
		// no else.

		Parameter result;

		/* Check if the given operation is null. */
		if (dslOperation == null) {

			String msg;

			msg = "createVoidReturnParameter(): dslOperation is null.";

			LOGGER.error(msg);

			result = null;
		}

		else {
			/* Probably get a cached result. */
			result = this.adaptedVoidReturnParameters.get(dslOperation);

			/* Else create the result. */
			if (result == null) {

				result = new UML2VoidReturnParameter(dslOperation, this);

				/* Cache the result. */
				this.adaptedVoidReturnParameters.put(dslOperation, result);
			}
		}

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createVoidReturnParameter() - exit "; //$NON-NLS-1$
			msg += " - return value = " + result; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Creates a {@link PrimitiveType} adapter for a
	 * {@link org.eclipse.uml2.uml.PrimitiveType}.
	 * </p>
	 * 
	 * @generated NOT
	 */
	private PrimitiveType createPrimitiveType(
			org.eclipse.uml2.uml.PrimitiveType dslPrimitiveType) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createPrimitiveType(dslPrimitiveType="
					+ dslPrimitiveType + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (dslPrimitiveType == null) {
			if (LOGGER.isDebugEnabled())
				LOGGER
						.debug("createPrimitiveType() - exit: dslPrimitiveType is null");
			return null;
		}

		PrimitiveType primitiveType = (PrimitiveType) adapters
				.get(dslPrimitiveType);

		if (primitiveType == null) {
			primitiveType = new UML2PrimitiveType(dslPrimitiveType, this);
			adapters.put(dslPrimitiveType, primitiveType);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("createPrimitiveType() - exit - return value=" + primitiveType); //$NON-NLS-1$
		}

		return primitiveType;
	}

	/**
	 * <p>
	 * Creates a {@link Type} adapter for a {@link org.eclipse.uml2.uml.Class}.
	 * </p>
	 * 
	 * @generated NOT
	 */
	private Type createType(org.eclipse.uml2.uml.Class dslClass) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createType(dslClass=" + dslClass + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (dslClass == null) {
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("createType() - exit: dslClass is null");
			return null;
		}

		Type type = (Type) adapters.get(dslClass);

		if (type == null) {
			type = new UML2Class(dslClass, this);
			adapters.put(dslClass, type);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createType() - exit - return value=" + type); //$NON-NLS-1$
		}

		return type;
	}

	/**
	 * <p>
	 * Creates a {@link Type} adapter for a {@link DataType} .
	 * </p>
	 * 
	 * @generated NOT
	 */
	private Type createType(DataType dslDataType) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createType(dslDataType=" + dslDataType + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (dslDataType == null) {
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("createType() - exit: dslInterface is null");
			return null;
		}

		Type type = (Type) adapters.get(dslDataType);

		if (type == null) {
			type = new UML2Datatype(dslDataType, this);
			adapters.put(dslDataType, type);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createType() - exit - return value=" + type); //$NON-NLS-1$
		}

		return type;
	}
	
	/**
	 * <p>
	 * Creates a {@link Type} adapter for a
	 * {@link org.eclipse.uml2.uml.Interface} .
	 * </p>
	 * 
	 * @generated NOT
	 */
	private Type createType(org.eclipse.uml2.uml.Interface dslInterface) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("createType(dslInterface=" + dslInterface + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (dslInterface == null) {
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("createType() - exit: dslInterface is null");
			return null;
		}

		Type type = (Type) adapters.get(dslInterface);

		if (type == null) {
			type = new UML2Interface(dslInterface, this);
			adapters.put(dslInterface, type);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createType() - exit - return value=" + type); //$NON-NLS-1$
		}

		return type;
	}

	/**
	 * <p>
	 * Creates a {@link Type} adapter for a
	 * {@link org.eclipse.uml2.uml.PrimitiveType} that cannot be mapped to a
	 * {@link PrimitiveType}.
	 * </p>
	 * 
	 * @generated NOT
	 */
	private Type createTypePrimitiveType(
			org.eclipse.uml2.uml.PrimitiveType dslPrimitiveType) {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createTypePrimitiveType("; //$NON-NLS-1$ //$NON-NLS-2$
			msg += "dslPrimitiveType = " + dslPrimitiveType; //$NON-NLS-1$ //$NON-NLS-2$
			msg += ") - enter"; //$NON-NLS-1$ //$NON-NLS-2$

			LOGGER.debug(msg);
		}
		// no else.

		Type result;

		/* Probably get a cached result. */
		result = (Type) adapters.get(dslPrimitiveType);

		/* If the type has not been adapted before, create a new adaptation. */
		if (result == null) {
			result = new UML2TypePrimitiveType(dslPrimitiveType, this);

			/* Cache the result. */
			adapters.put(dslPrimitiveType, result);
		}
		// no else.

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createPrimitiveType() - exit";
			msg += " - return value=" + result;

			LOGGER.debug(msg); //$NON-NLS-1$
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * A helper method that iterates through a {@link List} of
	 * {@link org.eclipse.uml2.uml.Property}s and adds all other
	 * {@link org.eclipse.uml2.uml.Property}s to each
	 * {@link org.eclipse.uml2.uml.Property}, if it is navigable.
	 * </p>
	 * 
	 * @param properties
	 *            The {@link List} of {@link org.eclipse.uml2.uml.Property}s.
	 */
	private void addNavigableAssociationEnds(
			List<org.eclipse.uml2.uml.Property> properties) {

		boolean allArentNavigable;

		List<AssociationProperty> adaptedAssociations = new LinkedList<AssociationProperty>();

		/* Check if all properties aren't navigable. */
		allArentNavigable = true;
		int size = 0;

		for (org.eclipse.uml2.uml.Property aProperty : properties) {
			allArentNavigable &= !aProperty.isNavigable();
			if (aProperty.isNavigable())
				++size;
		}
		// end for.

		/*
		 * If all properties aren't navigable, the association is n-directional.
		 * All properties know all others.
		 */
		if (allArentNavigable) {
			for (org.eclipse.uml2.uml.Property aProperty : properties) {

				adaptedAssociations.addAll(this.addAllOtherAssociationEnds(
						aProperty, properties, true));
			}
			// end for.
		}

		/*
		 * Else check for each property if it is navigable and eventually add
		 * the other properties to their fields.
		 */
		else {
			for (org.eclipse.uml2.uml.Property aProperty : properties) {
				if (aProperty.isNavigable()) {


					adaptedAssociations.addAll(this.addAllOtherAssociationEnds(
							aProperty, properties, (size > 1)));
				}
				// no else.
			}
			// end for.
		}
		// end else.

		/*
		 * Add all association ends to the other properties.
		 */
		for (AssociationProperty prop : adaptedAssociations) {
			prop.addAssociations(adaptedAssociations);
		}
		// end for.
		
	}

	/**
	 * <p>
	 * A helper method which adds all {@link org.eclipse.uml2.uml.Property}s of
	 * a given {@link List} to a given {@link org.eclipse.uml2.uml.Property}'s
	 * {@link Type} as {@link Property}s.
	 * </p>
	 * 
	 * @param anOwner
	 *            The {@link org.eclipse.uml2.uml.Property} which shall know all
	 *            given {@link org.eclipse.uml2.uml.Property}s.
	 * @param allProperties
	 *            The {@link List} of {@link org.eclipse.uml2.uml.Property}s
	 *            which shall be added.
	 * @param association
	 *            If the parameter true, then created
	 *            {@link AssociationProperty}s, otherwise {@link Property}s.
	 * @return a list of all added {@link AssociationProperty}s.
	 */
	private List<AssociationProperty> addAllOtherAssociationEnds(
			org.eclipse.uml2.uml.Property anOwner,
			List<org.eclipse.uml2.uml.Property> allProperties,
			boolean association) {

		List<AssociationProperty> result = new LinkedList<AssociationProperty>();
		
		org.dresdenocl.pivotmodel.Property adaptedProperty;
		/* Create or get the property. */
		if (association) {
			adaptedProperty = this
					.createAssociationProperty(anOwner);
			result.add((AssociationProperty) adaptedProperty);
		}

		else {
			adaptedProperty = this.createProperty(anOwner);
		}
		
		for (org.eclipse.uml2.uml.Property aProperty : allProperties) {

			/* Do not add the property to itself, but to all other properties. */
			if (anOwner != aProperty) {


				org.dresdenocl.pivotmodel.Type ownerType;
				
				/* Create or get the owner's Type. */
				ownerType = this.createType(aProperty.getType());		

				/*
				 * Check if the property has already been added (could happen
				 * for bidirectional associations between the same type).
				 */
				if (!ownerType.getOwnedProperty().contains(adaptedProperty)) {
					/* Else add the property. */
					ownerType.addProperty(adaptedProperty);
				}
				// no else.
			}
			// no else.
		}
		// end for.
		return result;
	}
}