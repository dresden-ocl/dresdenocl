/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Matthias Braeuer (braeuer.matthias@web.de).            *
 * All rights reserved.                                                      *
 *                                                                           *
 * This work was done as a project at the Chair for Software Technology,     *
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

import java.util.HashMap;
import java.util.Map;

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
import org.eclipse.osgi.util.NLS;

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
 * 
 * 
 * @author Matthias Braeuer
 * @version 1.0 30.03.2007
 */
public class EcoreAdapterFactory {

  // logger for this class
  private static final Logger logger = Logger.getLogger(EcoreAdapterFactory.class);

  /**
   * The Singleton instance of the factory.
   */
  public static EcoreAdapterFactory INSTANCE = new EcoreAdapterFactory();

  // a cache for previously created adapters
  private Map<EModelElement, NamedElement> adapters;

  /**
   * Clients are not supposed to instantiate this class.
   */
  private EcoreAdapterFactory() {
    adapters = new HashMap<EModelElement, NamedElement>();
  }

  /**
   * Creates a {@link Namespace} adapter for an {@link EPackage}.
   */
  public Namespace createNamespace(EPackage ePackage) {
    if (logger.isDebugEnabled()) {
      logger.debug("createNamespace(ePackage=" + ePackage + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    Namespace namespace = (Namespace) adapters.get(ePackage);

    if (namespace == null) {

      if (logger.isInfoEnabled()) {
        logger.info(NLS.bind(EcoreModelMessages.EcoreAdapterFactory_CreatingPivotModelAdapter,
            "EPackage",ePackage.getName())); //$NON-NLS-1$
      }

      namespace = new EcoreNamespace(ePackage);
      adapters.put(ePackage,namespace);
    }

    if (logger.isDebugEnabled()) {
      logger.debug("createNamespace() - exit - return value=" + namespace); //$NON-NLS-1$
    }

    return namespace;
  }

  /**
   * @param eClassifier
   * @return
   */
  public Type createType(EClassifier eClassifier) {
    Type type;
    
    if (eClassifier == null) {
      return null;
    }

    if (eClassifier instanceof EClass) {
      type = createType((EClass) eClassifier);
    }

    else if (eClassifier instanceof EEnum) {
      type = createEnumeration((EEnum) eClassifier);
    }

    else if (eClassifier instanceof EDataType) {
      type = createPrimitiveType((EDataType) eClassifier);
    }

    else {
      // should not happen
      throw new IllegalArgumentException("Unknown Ecore EClassifier type: " + eClassifier); //$NON-NLS-1$
    }

    return type;
  }

  /**
   * @param eClass
   * @return
   */
  public Type createType(EClass eClass) {
    if (logger.isDebugEnabled()) {
      logger.debug("createType(eClass=" + eClass + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    Type type = (Type) adapters.get(eClass);

    if (type == null) {
      type = new EcoreType(eClass);
      adapters.put(eClass,type);
    }

    if (logger.isDebugEnabled()) {
      logger.debug("createType() - exit - return value=" + type); //$NON-NLS-1$
    }

    return type;
  }

  /**
   * @param eEnum
   * @return
   */
  public Enumeration createEnumeration(EEnum eEnum) {
    if (logger.isDebugEnabled()) {
      logger.debug("createEnumeration(eEnum=" + eEnum + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    Enumeration enumeration = (Enumeration) adapters.get(eEnum);

    if (enumeration == null) {
      enumeration = new EcoreEnumeration(eEnum);
      adapters.put(eEnum,enumeration);
    }

    if (logger.isDebugEnabled()) {
      logger.debug("createEnumeration() - exit - return value=" + enumeration); //$NON-NLS-1$
    }

    return enumeration;
  }

  /**
   * @param eEnumLiteral
   * 
   * @return
   */
  public EnumerationLiteral createEnumerationLiteral(EEnumLiteral eEnumLiteral) {
    if (logger.isDebugEnabled()) {
      logger.debug("createEnumerationLiteral(eEnumLiteral=" + eEnumLiteral + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    EnumerationLiteral literal = (EnumerationLiteral) adapters.get(eEnumLiteral);

    if (literal == null) {
      literal = new EcoreEnumerationLiteral(eEnumLiteral);
      adapters.put(eEnumLiteral,literal);
    }

    if (logger.isDebugEnabled()) {
      logger.debug("createEnumerationLiteral() - exit - return value=" + literal); //$NON-NLS-1$
    }

    return literal;
  }

  /**
   * @param eDataType
   * @return
   */
  public Type createPrimitiveType(EDataType eDataType) {
    if (logger.isDebugEnabled()) {
      logger.debug("createPrimitiveType(eDataType=" + eDataType + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    PrimitiveType primitiveType = (PrimitiveType) adapters.get(eDataType);

    if (primitiveType == null) {
      primitiveType = new EcorePrimitiveType(eDataType);
      adapters.put(eDataType,primitiveType);
    }

    if (logger.isDebugEnabled()) {
      logger.debug("createPrimitiveType() - exit - return value=" + primitiveType); //$NON-NLS-1$
    }

    return primitiveType;
  }

  /**
   * @param eStructuralFeature
   * @return
   */
  public Property createProperty(EStructuralFeature eStructuralFeature) {
    if (logger.isDebugEnabled()) {
      logger.debug("createProperty(eStructuralFeature=" + eStructuralFeature + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    Property property = (Property) adapters.get(eStructuralFeature);

    if (property == null) {
      property = new EcoreProperty(eStructuralFeature);
      adapters.put(eStructuralFeature,property);
    }

    if (logger.isDebugEnabled()) {
      logger.debug("createProperty() - exit - return value=" + property); //$NON-NLS-1$
    }

    return property;
  }

  /**
   * @param eOperation
   * @return
   */
  public Operation createOperation(EOperation eOperation) {
    if (logger.isDebugEnabled()) {
      logger.debug("createOperation(eOperation=" + eOperation + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    Operation operation = (Operation) adapters.get(eOperation);

    if (operation == null) {
      operation = new EcoreOperation(eOperation);
      adapters.put(eOperation,operation);
    }

    if (logger.isDebugEnabled()) {
      logger.debug("createOperation() - exit - return value=" + operation); //$NON-NLS-1$
    }

    return operation;
  }

  /**
   * @param parameter
   * @return
   */
  public Parameter createParameter(EParameter eParameter) {
    if (logger.isDebugEnabled()) {
      logger.debug("createParameter(eParameter=" + eParameter + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    Parameter parameter = (Parameter) adapters.get(eParameter);

    if (parameter == null) {
      parameter = new EcoreParameter(eParameter);
      adapters.put(eParameter,parameter);
    }

    if (logger.isDebugEnabled()) {
      logger.debug("createParameter() - exit - return value=" + parameter); //$NON-NLS-1$
    }

    return parameter;
  }

}
