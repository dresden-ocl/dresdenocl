/*
 * Copyright (C) 2012 by Bjoern Freitag (bjoern.freitag@inf.tu-dresden.de)
 * This file is part of the CWM Meta Model of Dresden OCL2 for Eclipse. Dresden
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
package org.dresdenocl.metamodels.cwmrelational.internal.model;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dresdenocl.essentialocl.EssentialOclPlugin;
import org.dresdenocl.metamodels.cwmrelational.CWMMetamodelPlugin;
import org.dresdenocl.pivotmodel.AssociationProperty;
import org.dresdenocl.pivotmodel.NamedElement;
import org.dresdenocl.pivotmodel.Namespace;
import org.dresdenocl.pivotmodel.PrimitiveTypeKind;
import org.dresdenocl.pivotmodel.Property;
import org.dresdenocl.pivotmodel.Type;
import orgomg.cwm.objectmodel.core.ModelElement;
import orgomg.cwm.resource.relational.Catalog;
import orgomg.cwm.resource.relational.Column;
import orgomg.cwm.resource.relational.ForeignKey;
import orgomg.cwm.resource.relational.SQLDistinctType;
import orgomg.cwm.resource.relational.SQLSimpleType;
import orgomg.cwm.resource.relational.Schema;
import orgomg.cwm.resource.relational.Table;
import orgomg.cwm.resource.relational.View;

/**
 * <p>
 * A factory to create Pivot Model types for this meta model.
 * </p>
 * 
 * @author Bjoern Freitag
 * @generated NOT
 */
public class CWMAdapterFactory {

	/**
	 * logger for this class
	 * 
	 * @generated NOT
	 */
	private static final Logger LOGGER = CWMMetamodelPlugin
			.getLogger(CWMAdapterFactory.class);

	/**
	 * <p>
	 * A cache for previously created adapters.
	 * </p>
	 * 
	 * @generated
	 */
	private Map<ModelElement, NamedElement> adapters;
	
	/**
	 * <p>
	 * The root {@link Namespace} belonging to the {@link CWMModel} of this
	 * {@link CWMAdapterFactory}.
	 * </p>
	 * 
	 * @generated NOT
	 */
	private Namespace rootNamespace;
	
	/**
	 * <p>
	 * A cache for previously created {@link Namespace}s stored by their
	 * qualified name.
	 * </p>
	 * 
	 * @generated NOT
	 */
	private Map<String, CWMPackage<?>> adaptedNamespaces = new HashMap<String, CWMPackage<?>>();

	
	public CWMAdapterFactory(Namespace rootNamespace) {
		this.adapters = new HashMap<ModelElement, NamedElement>();

		this.rootNamespace = rootNamespace;
	}

	public Type createType(ModelElement dslModelElement) {
		Type result;

		result = null;

		/* Check if the given type is null. */
		if (dslModelElement == null) {
			result = EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
					.getOclVoid();
		}
		
		/* Else if the given type is a table, adapt to Type. */
		else if (dslModelElement instanceof Table) {
			result = createType((Table) dslModelElement);
		}
		/* If the given type is a primitive Type. */
		else if (dslModelElement instanceof SQLDistinctType) {

			/* Check if the type can be adapted to a primitive type. */
			if (!CWMPrimitiveType.getKind(((SQLDistinctType) dslModelElement).getSqlSimpleType()).equals(
					PrimitiveTypeKind.UNKNOWN)) {
				result = createPrimitiveType((SQLDistinctType) dslModelElement);
			}

			/* Else adapt to Type. */
			else {
				result = createTypePrimitiveType((SQLDistinctType) dslModelElement);
			}
		}
		
		/* If the given type is a primitive Type. */
		else if (dslModelElement instanceof SQLSimpleType) {

			/* Check if the type can be adapted to a primitive type. */
			if (!CWMPrimitiveType.getKind(((SQLSimpleType) dslModelElement)).equals(
					PrimitiveTypeKind.UNKNOWN)) {
				result = createPrimitiveType((SQLSimpleType) dslModelElement);
			}

			/* Else adapt to Type. */
			else {
				result = createTypePrimitiveType((SQLSimpleType) dslModelElement);
			}
		}
		/* Else if the given type is a view, adapt to Type. */
		else if (dslModelElement instanceof View) {
			result = createType((View) dslModelElement);
		}
		
		else {
			/* Should not happen. */
			throw new IllegalArgumentException("Unknown Type: " + dslModelElement);
		}
		
		return result;
	}
	
	
	/**
	 * <p>
	 * Creates a {@link Type} adapter for a {@link SQLSimpleType}.
	 * </p>
	 * 
	 * @generated NOT
	 */
	private Type createTypePrimitiveType(SQLSimpleType dslType) {
		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createTypePrimitiveType("; //$NON-NLS-1$ //$NON-NLS-2$
			msg += "dslType = " + dslType; //$NON-NLS-1$ //$NON-NLS-2$
			msg += ") - enter"; //$NON-NLS-1$ //$NON-NLS-2$

			LOGGER.debug(msg);
		}
		// no else.

		Type result;

		/* Probably get a cached result. */
		result = (Type) adapters.get(dslType);

		/* If the type has not been adapted before, create a new adaptation. */
		if (result == null) {
			result = new CWMTypeSimpleType(dslType, this);

			/* Cache the result. */
			adapters.put(dslType, result);
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
	 * Creates a {@link Type} adapter for a {@link SQLDistinctType}.
	 * </p>
	 * 
	 * @generated NOT
	 */
	private Type createTypePrimitiveType(SQLDistinctType dslType) {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createTypePrimitiveType("; //$NON-NLS-1$ //$NON-NLS-2$
			msg += "dslType = " + dslType; //$NON-NLS-1$ //$NON-NLS-2$
			msg += ") - enter"; //$NON-NLS-1$ //$NON-NLS-2$

			LOGGER.debug(msg);
		}
		// no else.

		Type result;

		/* Probably get a cached result. */
		result = (Type) adapters.get(dslType);

		/* If the type has not been adapted before, create a new adaptation. */
		if (result == null) {
			result = new CWMTypeDistinctType(dslType, this);

			/* Cache the result. */
			adapters.put(dslType, result);
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
	 * Creates a {@link Type} adapter for a {@link SQLDistinctType}.
	 * </p>
	 * 
	 * @generated NOT
	 */
	private Type createPrimitiveType(SQLDistinctType dslModelElement) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createPrimitiveType(SQLDistinctType dslModelElement=" + dslModelElement + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (dslModelElement == null) {
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("createType() - exit: dslClass is null");
			return null;
		}

		Type type = (Type) adapters.get(dslModelElement);

		if (type == null) {
			type = new CWMDistinctType(dslModelElement, this);
			adapters.put(dslModelElement, type);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createPrimitiveType() - exit - return value=" + type); //$NON-NLS-1$
		}

		return type;
	}
	
	/**
	 * <p>
	 * Creates a {@link Type} adapter for a {@link SQLSimpleType}.
	 * </p>
	 * 
	 * @generated NOT
	 */
	private Type createPrimitiveType(SQLSimpleType dslModelElement) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createPrimitiveType(SQLSimpleType dslModelElement=" + dslModelElement + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (dslModelElement == null) {
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("createType() - exit: dslClass is null");
			return null;
		}

		Type type = (Type) adapters.get(dslModelElement);

		if (type == null) {
			type = new CWMSimpleType(dslModelElement, this);
			adapters.put(dslModelElement, type);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createPrimitiveType() - exit - return value=" + type); //$NON-NLS-1$
		}

		return type;
	}

	/**
	 * <p>
	 * Creates a {@link Type} adapter for a {@link Table}.
	 * </p>
	 * 
	 * @generated NOT
	 */
	private Type createType(Table dslTable) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createType(dslTable=" + dslTable + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (dslTable == null) {
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("createType() - exit: dslClass is null");
			return null;
		}

		Type type = (Type) adapters.get(dslTable);

		if (type == null) {
			type = new CWMTable(dslTable, this);
			adapters.put(dslTable, type);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createType() - exit - return value=" + type); //$NON-NLS-1$
		}

		return type;
	}
	
	/**
	 * <p>
	 * Creates a {@link Type} adapter for a {@link View}.
	 * </p>
	 * 
	 * @generated NOT
	 */
	private Type createType(View dslView) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createType(dslView=" + dslView + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (dslView == null) {
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("createType() - exit: dslClass is null");
			return null;
		}

		Type type = (Type) adapters.get(dslView);

		if (type == null) {
			type = new CWMView(dslView, this);
			adapters.put(dslView, type);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createType() - exit - return value=" + type); //$NON-NLS-1$
		}

		return type;
	}
	
	/**
	 * <p>
	 * Creates a {@link orgomg.cwm.objectmodel.core.Namespace} adapter for an
	 * {@link Package}.
	 * </p>
	 * 
	 * <p>
	 * This method should only be used to get {@link orgomg.cwm.objectmodel.core.Namespace}s that have
	 * already been created and are cached. Since otherwise the nesting
	 * {@link Namespace} will be set to <code>null</code>.
	 * </p>
	 * 
	 * @param dslPackage
	 *            The {Package} that shall be adapted.
	 * @generated NOT
	 */
	public Namespace createNamespace(orgomg.cwm.objectmodel.core.Namespace dslPackage) {
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
	 * Creates a {@link Property} adapter for a
	 * {@link Column}.
	 * </p>
	 * 
	 * @generated NOT
	 */
	public Property createColumn(Column dslColumn) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("createProperty(dslProperty=" + dslColumn + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (dslColumn == null) {
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("createProperty() - exit: dslProperty is null");
			return null;
		}

		Property property = (Property) adapters.get(dslColumn);

		if (property == null) {
			property = new CWMColumn(dslColumn, this);
			adapters.put(dslColumn, property);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createProperty() - exit - return value=" + property); //$NON-NLS-1$
		}

		return property;
	}


	/**
	 * <p>
	 * Creates a {@link Namespace} adapter for an
	 * {@link orgomg.cwm.objectmodel.core.Namespace}.
	 * </p>
	 * 
	 * @param dslPackage
	 *            The {Namespace} that shall be adapted.
	 * @param nestingNamespace
	 *            The nesting {@link Namespace} of the {@link UML2Package}.
	 * @generated NOT
	 */
	public Namespace createNamespace(orgomg.cwm.objectmodel.core.Namespace dslPackage,
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
		if (dslPackage == null) {
			result = this.rootNamespace;
		}

		/* Else adapt or return an already adapted CWMPackage. */
		else {
			CWMPackage<?> cwmPackageResult;


			/* Probably reuse or merge with other package. */
			if (this.adaptedNamespaces.containsKey(dslPackage.getName())) {
				cwmPackageResult = this.adaptedNamespaces.get(dslPackage.getName());
				cwmPackageResult.mergePackage(dslPackage);
			}

			/* Else create a new package. */
			else {
				if (nestingNamespace == null) {
					LOGGER.warn("Created CWMPackage "
							+ dslPackage.getName()
							+ " without nesting name space.");
				}
				// no else.
				if (dslPackage instanceof Schema) {
					cwmPackageResult = new CWMSchema((Schema) dslPackage,nestingNamespace, this);
				} else {
					cwmPackageResult = new CWMCatalog((Catalog) dslPackage,nestingNamespace, this);
				}

				/* Cache the create name space. */
				this.adaptedNamespaces.put(dslPackage.getName(),
						cwmPackageResult);
			}
			// no else.

			result = cwmPackageResult;
		}

		/* Probably log the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createNamespace() - exit - return value=" + result);
		}
		// no else.

		return result;
	}

	public AssociationProperty createForeignKey(ForeignKey dslForeignKey) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("createForeignKey(dslForeignKey=" + dslForeignKey + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (dslForeignKey == null) {
			if (LOGGER.isDebugEnabled())
				LOGGER
						.debug("createForeignKey() - exit: dslForeignKey is null");
			return null;
		}

		AssociationProperty property = (AssociationProperty) adapters
				.get(dslForeignKey);

		if (property == null) {
			property = new CWMForeignKey(dslForeignKey, this);
			adapters.put(dslForeignKey, property);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("createForeignKey() - exit - return value=" + property); //$NON-NLS-1$
		}

		return property;
	}
	
	public Namespace getRootNamespace() {
		return this.rootNamespace;
	}
}