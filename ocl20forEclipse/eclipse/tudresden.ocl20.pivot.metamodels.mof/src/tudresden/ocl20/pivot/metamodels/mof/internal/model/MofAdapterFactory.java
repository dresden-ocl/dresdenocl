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
 */
package tudresden.ocl20.pivot.metamodels.mof.internal.model;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import tudresden.ocl20.core.jmi.mof14.model.AliasType;
import tudresden.ocl20.core.jmi.mof14.model.AssociationEnd;
import tudresden.ocl20.core.jmi.mof14.model.Attribute;
import tudresden.ocl20.core.jmi.mof14.model.Classifier;
import tudresden.ocl20.core.jmi.mof14.model.Constant;
import tudresden.ocl20.core.jmi.mof14.model.EnumerationType;
import tudresden.ocl20.core.jmi.mof14.model.ModelElement;
import tudresden.ocl20.core.jmi.mof14.model.MofClass;
import tudresden.ocl20.core.jmi.mof14.model.MofPackage;
import tudresden.ocl20.core.jmi.mof14.model.PrimitiveType;
import tudresden.ocl20.core.jmi.mof14.model.StructureField;
import tudresden.ocl20.core.jmi.mof14.model.StructureType;
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
public class MofAdapterFactory {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(MofAdapterFactory.class);

  /**
   * The Singleton instance of the factory.
   */
	public static MofAdapterFactory INSTANCE = new MofAdapterFactory();
	
  // a cache for previously created adapters
	private Map<Object, NamedElement> adapters;

  /**
   * Clients are not supposed to instantiate this class.
   */
	private MofAdapterFactory() {
		adapters = new HashMap<Object, NamedElement>();
	}
	
  /**
   * Creates a {@link Namespace} adapter for an {@link MofPackage}.
   */
	public Namespace createNamespace(MofPackage mofPackage) {
		if (logger.isDebugEnabled()) {
			logger.debug("createNamespace(MofPackage mofPackage=" + mofPackage
					+ ") - enter");
		}

		Namespace namespace = (Namespace) adapters.get(mofPackage);
		
		if (namespace == null) {
			namespace = new MofNamespace(mofPackage);
			adapters.put(mofPackage, namespace);
		}
		

		if (logger.isDebugEnabled()) {
			logger.debug("createNamespace(MofPackage) - exit - return value="
					+ namespace);
		}
		return namespace;
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

		Type type;
		if (classifier instanceof MofClass)
			type = createType((MofClass)classifier);
		else if (classifier instanceof PrimitiveType)
			type = createPrimitiveType((PrimitiveType)classifier);
		else if (classifier instanceof StructureType)
			type = createStructureType((StructureType)classifier);
		else if (classifier instanceof AliasType)
			type = createAliasType((AliasType)classifier);
		else if (classifier instanceof EnumerationType)
			type = createEnumerationType((EnumerationType)classifier);
		else
			throw new IllegalArgumentException("Unknown Mof Classifier type: " + classifier.getClass()); 
		


		if (logger.isDebugEnabled()) {
			logger.debug("createType(Classifier) - exit - return value=" + type);
		}
		return type;
	}

	/**
	 * 
	 * @param enumerationType
	 * @return
	 */
	public Type createEnumerationType(EnumerationType enumerationType) {
		if (logger.isDebugEnabled()) {
			logger.debug("createEnumerationType(EnumerationType enumerationType="
					+ enumerationType + ") - enter");
		}

		Type type = (Type)adapters.get(enumerationType);
		
		if (type == null) {
			type = new MofEnumeration(enumerationType);
			adapters.put(enumerationType, type);
		}
		

		if (logger.isDebugEnabled()) {
			logger
					.debug("createEnumerationType(EnumerationType) - exit - return value="
							+ type);
		}
		return type;
	}

	/**
	 * 
	 * @param aliasType
	 * @return
	 */
	private Type createAliasType(AliasType aliasType) {
		if (logger.isDebugEnabled()) {
			logger.debug("createAliasType(AliasType aliasType=" + aliasType
					+ ") - enter");
		}

		Type type = (Type) adapters.get(aliasType);
		
		if (type == null) {
			type = new MofAliasType(aliasType);
			adapters.put(aliasType, type);
		}
		

		if (logger.isDebugEnabled()) {
			logger.debug("createAliasType(AliasType) - exit - return value=" + type);
		}
		return type;
	}

	/**
	 * 
	 * @param primitiveType
	 * @return
	 */
	private Type createPrimitiveType(PrimitiveType primitiveType) {
		if (logger.isDebugEnabled()) {
			logger.debug("createPrimitiveType(PrimitiveType primitiveType="
					+ primitiveType + ") - enter");
		}

		Type type = (Type) adapters.get(primitiveType);
		
		if (type == null) {
			type = new MofPrimitiveType(primitiveType);
			adapters.put(primitiveType, type);
		}
		

		if (logger.isDebugEnabled()) {
			logger.debug("createPrimitiveType(PrimitiveType) - exit - return value="
					+ type);
		}
		return type;
	}

	/**
	 * 
	 * @param mofClass
	 * @return
	 */
	public Type createType(MofClass mofClass) {
		if (logger.isDebugEnabled()) {
			logger.debug("createType(MofClass mofClass=" + mofClass + ") - enter");
		}

		Type type = (Type) adapters.get(mofClass);
		
		if (type == null) {
			type = new MofType(mofClass);
			adapters.put(mofClass, type);
		}
		

		if (logger.isDebugEnabled()) {
			logger.debug("createType(MofClass) - exit - return value=" + type);
		}
		return type;
	}

	/**
	 * 
	 * @param structureType
	 * @return
	 */
	public Type createStructureType(StructureType structureType) {
		if (logger.isDebugEnabled()) {
			logger.debug("createStructureType(StructureType structureType="
					+ structureType + ") - enter");
		}

		Type type = (Type) adapters.get(structureType);
		
		if (type == null) {
			type = new MofStructureType(structureType);
			adapters.put(structureType, type);
		}
		

		if (logger.isDebugEnabled()) {
			logger.debug("createStructureType(StructureType) - exit - return value="
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
			tudresden.ocl20.core.jmi.mof14.model.Operation operation) {
		if (logger.isDebugEnabled()) {
			logger
					.debug("createOperation(tudresden.ocl20.core.jmi.mof14.model.Operation operation="
							+ operation + ") - enter");
		}
		
		Operation pOperation = (Operation) adapters.get(operation);
		
		if (pOperation == null) {
			pOperation = new MofOperation(operation);
			adapters.put(operation, pOperation);
		}

		if (logger.isDebugEnabled()) {
			logger
					.debug("createOperation(tudresden.ocl20.core.jmi.mof14.model.Operation) - exit - return value="
							+ pOperation);
		}
		return pOperation;
	}

	/**
	 * 
	 * @param parameter
	 * @return
	 */
	public Parameter createParameter(
			tudresden.ocl20.core.jmi.mof14.model.Parameter parameter) {
		if (logger.isDebugEnabled()) {
			logger
					.debug("createParameter(tudresden.ocl20.core.jmi.mof14.model.Parameter parameter="
							+ parameter + ") - enter");
		}
		
		Parameter pParameter = (Parameter) adapters.get(parameter);
		
		if (pParameter == null) {
			pParameter = new MofParameter(parameter);
			adapters.put(parameter, pParameter);
		}
		

		if (logger.isDebugEnabled()) {
			logger
					.debug("createParameter(tudresden.ocl20.core.jmi.mof14.model.Parameter) - exit - return value="
							+ pParameter);
		}
		return pParameter;
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

		Property property = (Property) adapters.get(attribute);
		
		if (property == null) {
			property = new MofProperty(attribute);
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
	 * @param structureField
	 * @return
	 */
	public Property createStructureField(StructureField structureField) {
		if (logger.isDebugEnabled()) {
			logger.debug("createStructureField(StructureField structureField="
					+ structureField + ") - enter");
		}

		Property property = (Property) adapters.get(structureField);
		
		if (property == null) {
			property = new MofStructureField(structureField);
			adapters.put(structureField, property);
		}
		

		if (logger.isDebugEnabled()) {
			logger
					.debug("createStructureField(StructureField) - exit - return value="
							+ property);
		}
		return property;
	}

	/**
	 * 
	 * @param ae
	 * @return
	 */
	public Property createProperty(AssociationEnd ae) {
		if (logger.isDebugEnabled()) {
			logger.debug("createProperty(AssociationEnd ae=" + ae + ") - enter");
		}

		Property property = (Property)adapters.get(ae);
		
		if (property == null) {
			property = new MofAssociationEnd(ae);
			adapters.put(ae, property);
		}
		

		if (logger.isDebugEnabled()) {
			logger.debug("createProperty(AssociationEnd) - exit - return value="
					+ property);
		}
		return property;
	}

	/**
	 * 
	 * @param constant
	 * @return
	 */
	public Property createProperty(Constant constant) {
		if (logger.isDebugEnabled()) {
			logger
					.debug("createProperty(Constant constant=" + constant + ") - enter");
		}

		Property property = (Property)adapters.get(constant);
		
		if (property == null) {
			property = new MofConstant(constant);
			adapters.put(constant, property);
		}
		

		if (logger.isDebugEnabled()) {
			logger
					.debug("createProperty(Constant) - exit - return value=" + property);
		}
		return property;
	}

	/**
	 * 
	 * @param enumerationType
	 * @param label
	 * @return
	 */
	public EnumerationLiteral createEnumerationLiteral(EnumerationType enumerationType, String label) {
		if (logger.isDebugEnabled()) {
			logger.debug("createEnumerationLiteral(EnumerationType enumerationType="
					+ enumerationType + ", String label=" + label + ") - enter");
		}

		EnumerationLiteral enumerationLiteral = (EnumerationLiteral)adapters.get(label);
		
		if (enumerationLiteral == null) {
			enumerationLiteral = new MofEnumerationLiteral(enumerationType, label);
			adapters.put(label, enumerationLiteral);
		}
		

		if (logger.isDebugEnabled()) {
			logger
					.debug("createEnumerationLiteral(EnumerationType, String) - exit - return value="
							+ enumerationLiteral);
		}
		return enumerationLiteral;
	}
}
