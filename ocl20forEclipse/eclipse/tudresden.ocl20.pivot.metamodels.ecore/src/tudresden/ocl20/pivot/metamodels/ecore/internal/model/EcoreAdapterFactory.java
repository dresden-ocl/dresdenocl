/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Matthias Braeuer (braeuer.matthias@web.de).            *
 * All rights reserved.                                                      *
 *                                                                           *
 * This work was done as a project at the Chair for Software Technology      *
 * Dresden University Of Technology, Germany (http://st.inf.tu-dresden.de).  *
 * It is understood that any modification not identified as such is not      *
 * covered by the preceding statement.                                       *
 *                                                                           *
 * This work is free software; you can redistribute it and/or modify it      *
 * under the terms of the GNU Library General Public License as published    *
 * by the Free Software Foundation; either version 2 of the License, or      *
 * (at your option) any later version.                                       *
 *                                                                           *
 * This work is distributed in the hope that it will be useful, but WITHOUT  *
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or     *
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Library General Public     *
 * License for more details.                                                 *
 *                                                                           *
 * You should have received a copy of the GNU Library General Public License *
 * along with this library; if not, you can view it online at                *
 * http://www.fsf.org/licensing/licenses/gpl.html.                           *
 *                                                                           *
 * To submit a bug report, send a comment, or get the latest news on this    *
 * project, please visit the website: http://dresden-ocl.sourceforge.net.    *
 * For more information on OCL and related projects visit the OCL Portal:    *
 * http://st.inf.tu-dresden.de/ocl                                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * 
 * $Id$
 */
package tudresden.ocl20.pivot.metamodels.ecore.internal.model;

import java.util.WeakHashMap;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;

import tudresden.ocl20.pivot.metamodels.ecore.EcoreMetamodelPlugin;
import tudresden.ocl20.pivot.modelbus.model.IModel;
import tudresden.ocl20.pivot.pivotmodel.Enumeration;
import tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral;
import tudresden.ocl20.pivot.pivotmodel.NamedElement;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;
import tudresden.ocl20.pivot.pivotmodel.ParameterDirectionKind;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * The {@link EcoreAdapterFactory} is responsible for the adaptation of ecore
 * meta model objects to the pivot model.
 * </p>
 * 
 * @author Matthias Braeuer
 */
public class EcoreAdapterFactory {

	/** The Singleton instance of the {@link EcoreAdapterFactory}. */
	public static EcoreAdapterFactory INSTANCE = new EcoreAdapterFactory();

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			EcoreMetamodelPlugin.getLogger(EcoreAdapterFactory.class);

	/**
	 * A cache for previously created adapters. It is realized as a
	 * {@link WeakHashMap} because pivot model elements which are not referenced
	 * by any {@link IModel} anymore can be cleared by the garbage collector.
	 */
	private WeakHashMap<EModelElement, NamedElement> myCachedAdapters;

	/**
	 * A cache for previously created adapters for the {@link Parameter} of the
	 * {@link ParameterDirectionKind#RETURN} for {@link EOperation}s. It is
	 * realized as a {@link WeakHashMap} because pivot model elements which are
	 * not referenced by any {@link IModel} anymore can be cleared by the garbage
	 * collector.
	 */
	private WeakHashMap<EModelElement, NamedElement> myCachedReturnParameterAdapters;

	/**
	 * <p>
	 * Clients are not supposed to instantiate an {@link EcoreAdapterFactory}.
	 * </p>
	 */
	private EcoreAdapterFactory() {

		this.myCachedAdapters = new WeakHashMap<EModelElement, NamedElement>();
		this.myCachedReturnParameterAdapters =
				new WeakHashMap<EModelElement, NamedElement>();
	}

	/**
	 * <p>
	 * Creates an {@link Enumeration} for a given {@link EEnum}.
	 * </p>
	 * 
	 * @param eEnum
	 *          The {@link EEnum} which shall be adapted.
	 * @return The created {@link Enumeration}.
	 */
	public Enumeration createEnumeration(EEnum eEnum) {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createEnumeration(";
			msg += "eEnum = " + eEnum;
			msg += ") - enter";

			LOGGER.debug(msg); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		Enumeration result;

		/* Eventually use a cached result. */
		if (this.myCachedAdapters.containsKey(eEnum)) {
			result = (Enumeration) this.myCachedAdapters.get(eEnum);
		}

		/* Else create the Enumeration. */
		else {
			result = new EcoreEnumeration(eEnum);

			/* Cache the result. */
			myCachedAdapters.put(eEnum, result);
		}

		/* Eventually log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createEnumeration() - exit";
			msg += " - return value = " + result;

			LOGGER.debug(msg); //$NON-NLS-1$
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Creates an {@link EnumerationLiteral} for a given {@link EEnumLiteral}.
	 * </p>
	 * 
	 * @param eEnumLiteral
	 *          The {@link EEnumLiteral} which shall be adapted.
	 * 
	 * @return The created {@link EnumerationLiteral}.
	 */
	public EnumerationLiteral createEnumerationLiteral(EEnumLiteral eEnumLiteral) {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createEnumerationLiteral(";
			msg += "eEnumLiteral = " + eEnumLiteral;
			msg += ") - enter";

			LOGGER.debug(msg); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		EnumerationLiteral result;

		/* Eventually use a cached result. */
		if (this.myCachedAdapters.containsKey(eEnumLiteral)) {
			result = (EnumerationLiteral) this.myCachedAdapters.get(eEnumLiteral);
		}

		/* Else create the Type. */
		else {
			result = new EcoreEnumerationLiteral(eEnumLiteral);

			/* Cache the result. */
			this.myCachedAdapters.put(eEnumLiteral, result);
		}

		/* Eventually log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createEnumerationLiteral() - exit";
			msg += " - return value = " + result;

			LOGGER.debug(msg); //$NON-NLS-1$
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Creates a {@link Namespace} adapter for an {@link EPackage}.
	 * </p>
	 * 
	 * @param ePackage
	 *          The {@link EPackage} that shall be adapted.
	 */
	public Namespace createNamespace(EPackage ePackage) {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createNamespace(";
			msg += "ePackage = " + ePackage;
			msg += ") - enter";

			LOGGER.debug(msg); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		Namespace result;

		/* Eventually use a cached result. */
		if (this.myCachedAdapters.containsKey(ePackage)) {
			result = (Namespace) this.myCachedAdapters.get(ePackage);
		}

		/* Else create the name space. */
		else {
			result = new EcoreNamespace(ePackage);

			/* Cache the result. */
			this.myCachedAdapters.put(ePackage, result);
		}

		/* Eventually log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createNamespace() - exit";
			msg += " - return value = " + result;

			LOGGER.debug(msg); //$NON-NLS-1$
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Creates an {@link Operation} for a given {@link EOperation}.
	 * </p>
	 * 
	 * @param eOperation
	 *          The {@link EOperation} which shall be adapted.
	 * @return The created {@link Operation}.
	 */
	public Operation createOperation(EOperation eOperation) {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createOperation(";
			msg += "eOperation = " + eOperation;
			msg += ") - enter";

			LOGGER.debug(msg); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		Operation result;

		/* Eventually use a cached result. */
		if (this.myCachedAdapters.containsKey(eOperation)) {
			result = (Operation) this.myCachedAdapters.get(eOperation);
		}

		/* Else create the Type. */
		else {
			result = new EcoreOperation(eOperation);

			/* Cache the result. */
			this.myCachedAdapters.put(eOperation, result);
		}

		/* Eventually log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createOperation() - exit";
			msg += " - return value = " + result;

			LOGGER.debug(msg); //$NON-NLS-1$
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Creates a {@link Parameter} for a given {@link EParameter}.
	 * </p>
	 * 
	 * @param eOperation
	 *          The {@link EParameter} which shall be adapted.
	 * @return The created {@link Parameter}.
	 */
	public Parameter createParameter(EParameter eParameter) {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createParameter(";
			msg += "eParameter = " + eParameter;
			msg += ") - enter";

			LOGGER.debug(msg); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		Parameter result;

		/* Eventually use a cached result. */
		if (this.myCachedAdapters.containsKey(eParameter)) {
			result = (Parameter) this.myCachedAdapters.get(eParameter);
		}

		/* Else create the Type. */
		else {
			result = new EcoreParameter(eParameter);

			/* Cache the result. */
			this.myCachedAdapters.put(eParameter, result);
		}

		/* Eventually log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createParameter() - exit";
			msg += " - return value = " + result;

			LOGGER.debug(msg); //$NON-NLS-1$
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Creates a {@link Property} for a given {@link EStructuralFeature}.
	 * </p>
	 * 
	 * @param eStructuralFeature
	 * @return
	 */
	public Property createProperty(EStructuralFeature eStructuralFeature) {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createProperty(";
			msg += "eStructuralFeature = " + eStructuralFeature;
			msg += ") - enter";

			LOGGER.debug(msg); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		Property result;

		/* Eventually use a cached result. */
		if (this.myCachedAdapters.containsKey(eStructuralFeature)) {
			result = (Property) this.myCachedAdapters.get(eStructuralFeature);
		}

		/* Else create the Type. */
		else {
			result = new EcoreProperty(eStructuralFeature);

			/* Cache the result. */
			this.myCachedAdapters.put(eStructuralFeature, result);
		}

		/* Eventually log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createProperty() - exit";
			msg += " - return value = " + result;

			LOGGER.debug(msg); //$NON-NLS-1$
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Creates a {@link Parameter} of the {@link ParameterDirectionKind#RETURN}
	 * for a given {@link EOperation}.
	 * </p>
	 * 
	 * @param eOperation
	 *          The {@link EOperation} for which a return {@link Parameter} shall
	 *          be adapted.
	 * @return The created {@link Parameter}.
	 */
	public Parameter createReturnParameter(EOperation eOperation) {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createReturnParameter(";
			msg += "eOperation = " + eOperation;
			msg += ") - enter";

			LOGGER.debug(msg); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		Parameter result;

		/* Eventually use a cached result. */
		if (this.myCachedReturnParameterAdapters.containsKey(eOperation)) {
			result = (Parameter) this.myCachedReturnParameterAdapters.get(eOperation);
		}

		/* Else create the Type. */
		else {
			result = new EcoreReturnParameter(eOperation);

			/* Cache the result. */
			this.myCachedReturnParameterAdapters.put(eOperation, result);
		}

		/* Eventually log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createParameter() - exit";
			msg += " - return value = " + result;

			LOGGER.debug(msg); //$NON-NLS-1$
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Creates an {@link Type} for a given {@link EClassifier}.
	 * </p>
	 * 
	 * @param eClassifier
	 *          The {@link EClassifier} which shall be adapted.
	 * @return The created {@link Type}.
	 * 
	 * @generated NOT
	 */
	public Type createType(EClassifier eClassifier) {

		Type result;

		/* Check if the classifier is null. */
		if (eClassifier == null) {
			result = EcoreVoidType.getInstance();
		}

		/* Else check if the classifier is an EClass. */
		else if (eClassifier instanceof EClass) {
			result = this.createType((EClass) eClassifier);
		}

		/* Else check if the classifier is an EEnum. */
		else if (eClassifier instanceof EEnum) {
			result = this.createEnumeration((EEnum) eClassifier);
		}

		/* Else check if the classifier is an EDataType. */
		else if (eClassifier instanceof EDataType) {
			result = this.createType((EDataType) eClassifier);
		}

		/* Else throw an exception. */
		else {
			String msg;

			msg = "Unknown Ecore EClassifier type: " + eClassifier;

			throw new IllegalArgumentException(msg); //$NON-NLS-1$
		}

		return result;
	}

	/**
	 * <p>
	 * Creates a {@link Type} for a given {@link EClass}.
	 * </p>
	 * 
	 * @param eClass
	 *          The {@link EClass} which shall be adapted.
	 * @return The created {@link Type}.
	 */
	private Type createType(EClass eClass) {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createType(";
			msg += "eClass = " + eClass;
			msg += ") - enter";

			LOGGER.debug(msg); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		Type result;

		/* Eventually use a cached result. */
		if (this.myCachedAdapters.containsKey(eClass)) {
			result = (Type) this.myCachedAdapters.get(eClass);
		}

		/* Else create the Type. */
		else {
			result = new EcoreType(eClass);

			/* Cache the result. */
			this.myCachedAdapters.put(eClass, result);
		}

		/* Eventually log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createType() - exit";
			msg += " - return value = " + result;

			LOGGER.debug(msg); //$NON-NLS-1$
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Creates a Type for a given {@link EDataType}.
	 * </p>
	 * 
	 * @param eDataType
	 *          The {@link EDataType} which shall be adapted.
	 * @return The created {@link Type}.
	 */
	private Type createType(EDataType eDataType) {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createType(";
			msg += "eDataType = " + eDataType;
			msg += ") - enter";

			LOGGER.debug(msg); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		Type result;

		/* Eventually use a cached result. */
		if (this.myCachedAdapters.containsKey(eDataType)) {
			result = (Type) this.myCachedAdapters.get(eDataType);
		}

		/* Else create the Type. */
		else {

			/* Check if the data type represents a primitive type. */
			if (!EcorePrimitiveType.getKind(eDataType).equals(
					PrimitiveTypeKind.UNKNOWN)) {
				result = new EcorePrimitiveType(eDataType);
			}

			/* Else create a non-primitive data type adaptation. */
			else {
				result = new EcoreDataType(eDataType);
			}

			/* Cache the result. */
			this.myCachedAdapters.put(eDataType, result);
		}

		/* Eventually log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createType() - exit";
			msg += " - return value = " + result;

			LOGGER.debug(msg); //$NON-NLS-1$
		}
		// no else.

		return result;
	}
}