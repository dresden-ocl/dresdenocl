/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Ronny Brandt (Ronny_Brandt@web.de).            *
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
 * $Id: UmlAdapterFactory.java,v 1.1 2007/05/09 17:49:03 robra81 Exp $
 */
package tudresden.ocl20.pivot.metamodels.uml.internal.model;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import tudresden.ocl20.core.jmi.uml15.core.AssociationEnd;
import tudresden.ocl20.core.jmi.uml15.core.Attribute;
import tudresden.ocl20.core.jmi.uml15.core.Classifier;
import tudresden.ocl20.core.jmi.uml15.core.DataType;
import tudresden.ocl20.core.jmi.uml15.core.Enumeration;
import tudresden.ocl20.core.jmi.uml15.core.Interface;
import tudresden.ocl20.core.jmi.uml15.core.ModelElement;
import tudresden.ocl20.core.jmi.uml15.core.UmlClass;
import tudresden.ocl20.core.jmi.uml15.modelmanagement.Package;
import tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral;
import tudresden.ocl20.pivot.pivotmodel.NamedElement;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * 
 * 
 * @author Ronny Brandt
 * @version 1.0 09.05.2007
 */
public class UmlAdapterFactory {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(UmlAdapterFactory.class);

  /**
   * The Singleton instance of the factory.
   */
	public static UmlAdapterFactory INSTANCE = new UmlAdapterFactory();
	
  // a cache for previously created adapters
	Map<ModelElement, NamedElement> adapters;
	
  /**
   * Clients are not supposed to instantiate this class.
   */
	private UmlAdapterFactory() {
		adapters = new HashMap<ModelElement, NamedElement>();
	}

  /**
   * Creates a {@link Namespace} adapter for an {@link Package}.
   */
	public Namespace createNamespace(Package umlPackage) {
		if (logger.isDebugEnabled()) {
			logger.debug("createNamespace(Package umlPackage=" + umlPackage
					+ ") - enter");
		}

		Namespace namespace = (Namespace)adapters.get(umlPackage);
		
		if (namespace == null) {
			namespace = new UmlNamespace(umlPackage);
			adapters.put(umlPackage, namespace);
		}
		

		if (logger.isDebugEnabled()) {
			logger.debug("createNamespace(Package) - exit - return value="
					+ namespace);
		}
		return namespace;
	}

	/**
	 * 
	 * @param umlClass
	 * @return
	 */
	public Type createType(UmlClass umlClass) {
		if (logger.isDebugEnabled()) {
			logger.debug("createType(UmlClass umlClass=" + umlClass + ") - enter");
		}

		Type type = (Type)adapters.get(umlClass);
		
		if (type == null) {
			type = new UmlType(umlClass);
			adapters.put(umlClass, type);
		}
		

		if (logger.isDebugEnabled()) {
			logger.debug("createType(UmlClass) - exit - return value=" + type);
		}
		return type;
	}

	/**
	 * 
	 * @param umlInterface
	 * @return
	 */
	public Type createType(Interface umlInterface) {
		if (logger.isDebugEnabled()) {
			logger.debug("createType(Interface umlInterface=" + umlInterface
					+ ") - enter");
		}

		Type type = (Type)adapters.get(umlInterface);
		
		if (type == null) {
			type = new UmlType(umlInterface);
			adapters.put(umlInterface, type);
		}
		

		if (logger.isDebugEnabled()) {
			logger.debug("createType(Interface) - exit - return value=" + type);
		}
		return type;
	}

	/**
	 * 
	 * @param classifier
	 * @return
	 */
	public Type createType(Classifier classifier) {
		if (logger.isDebugEnabled()) {
			logger.debug("createType(Classifier classifier=" + classifier
					+ ") - enter");
		}

		Type type = null;
		
		if (classifier instanceof UmlClass)
			type = createType((UmlClass)classifier);
		else if (classifier instanceof Interface)
			type = createType((Interface)classifier);
		else if (classifier instanceof Enumeration)
			type = createEnumeration((Enumeration) classifier);
		else if (classifier instanceof DataType)
			type = createPrimitiveType((DataType) classifier);
		else
			throw new IllegalArgumentException("Unknown Uml Classifier type: " + classifier.getClass()); 
		


		if (logger.isDebugEnabled()) {
			logger.debug("createType(Classifier) - exit - return value=" + type);
		}
		return type;
	}
	
	/**
	 * 
	 * @param enumeration
	 * @return
	 */
	public Type createEnumeration(Enumeration enumeration) {
		if (logger.isDebugEnabled()) {
			logger.debug("createEnumeration(Enumeration enumeration=" + enumeration
					+ ") - enter");
		}

		Type pEnumeration = (Type) adapters.get(enumeration);
		
		if (pEnumeration == null) {
			pEnumeration = new UmlEnumeration(enumeration);
			adapters.put(enumeration, pEnumeration);
		}
		

		if (logger.isDebugEnabled()) {
			logger.debug("createEnumeration(Enumeration) - exit - return value="
					+ pEnumeration);
		}
		return pEnumeration;
	}

	/**
	 * 
	 * @param dataType
	 * @return
	 */
	public Type createPrimitiveType(DataType dataType) {
		if (logger.isDebugEnabled()) {
			logger.debug("createPrimitiveType(DataType dataType=" + dataType
					+ ") - enter");
		}

		Type type = (Type)adapters.get(dataType);
		
		if (type == null) {
			type = new UmlPrimitiveType(dataType);
			adapters.put(dataType, type);
		}
		

		if (logger.isDebugEnabled()) {
			logger.debug("createPrimitiveType(DataType) - exit - return value="
					+ type);
		}
		return type;
	}

	/**
	 * 
	 * @param operation
	 * @return
	 */
	public Operation createOperation(
			tudresden.ocl20.core.jmi.uml15.core.Operation operation) {
		if (logger.isDebugEnabled()) {
			logger
					.debug("createOperation(tudresden.ocl20.core.jmi.uml15.core.Operation operation="
							+ operation + ") - enter");
		}

		Operation pOperation = (Operation)adapters.get(operation);
		
		if (pOperation == null) {
			pOperation = new UmlOperation(operation);
			adapters.put(operation, pOperation);
		}
		

		if (logger.isDebugEnabled()) {
			logger
					.debug("createOperation(tudresden.ocl20.core.jmi.uml15.core.Operation) - exit - return value="
							+ pOperation);
		}
		return pOperation;
	}

	/**
	 * 
	 * @param attribute
	 * @return
	 */
	public Property createProperty(Attribute attribute) {
		if (logger.isDebugEnabled()) {
			logger.debug("createProperty(Attribute attribute=" + attribute
					+ ") - enter");
		}

		Property property = (Property)adapters.get(attribute);
		
		if (property == null) {
			property = new UmlProperty(attribute);
			adapters.put(attribute, property);
		}
		

		if (logger.isDebugEnabled()) {
			logger.debug("createProperty(Attribute) - exit - return value="
					+ property);
		}
		return property;
	}

	/**
	 * 
	 * @param parameter
	 * @return
	 */
	public Parameter createParameter(
			tudresden.ocl20.core.jmi.uml15.core.Parameter parameter) {
		if (logger.isDebugEnabled()) {
			logger
					.debug("createParameter(tudresden.ocl20.core.jmi.uml15.core.Parameter parameter="
							+ parameter + ") - enter");
		}
		
		Parameter pParameter = (Parameter)adapters.get(parameter);
		
		if (pParameter == null) {
			pParameter = new UmlParameter(parameter);
			adapters.put(parameter, pParameter);
		}
		

		if (logger.isDebugEnabled()) {
			logger
					.debug("createParameter(tudresden.ocl20.core.jmi.uml15.core.Parameter) - exit - return value="
							+ pParameter);
		}
		return pParameter;
	}

	/**
	 * 
	 * @param enumerationLiteral
	 * @return
	 */
	public EnumerationLiteral createEnumerationLiteral(
			tudresden.ocl20.core.jmi.uml15.core.EnumerationLiteral enumerationLiteral) {
		if (logger.isDebugEnabled()) {
			logger
					.debug("createEnumerationLiteral(tudresden.ocl20.core.jmi.uml15.core.EnumerationLiteral enumerationLiteral="
							+ enumerationLiteral + ") - enter");
		}

		EnumerationLiteral pEnumerationLiteral = (EnumerationLiteral)adapters.get(enumerationLiteral);
		
		if (pEnumerationLiteral == null) {
			pEnumerationLiteral = new UmlEnumerationLiteral(enumerationLiteral);
			adapters.put(enumerationLiteral, pEnumerationLiteral);
		}
		

		if (logger.isDebugEnabled()) {
			logger
					.debug("createEnumerationLiteral(tudresden.ocl20.core.jmi.uml15.core.EnumerationLiteral) - exit - return value="
							+ pEnumerationLiteral);
		}
		return pEnumerationLiteral;
	}

	/**
	 * 
	 * @param associationEnd
	 * @return
	 */
	public Property createProperty(AssociationEnd associationEnd) {
		if (logger.isDebugEnabled()) {
			logger.debug("createProperty(AssociationEnd associationEnd="
					+ associationEnd + ") - enter");
		}

		Property property = (Property) adapters.get(associationEnd);
		
		if (property == null) {
			property = new UmlAssociationEnd(associationEnd);
			adapters.put(associationEnd, property);
		}
		

		if (logger.isDebugEnabled()) {
			logger.debug("createProperty(AssociationEnd) - exit - return value="
					+ property);
		}
		return property;
	}
}
