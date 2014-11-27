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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.dresdenocl.metamodels.cwmrelational.CWMMetamodelPlugin;
import org.dresdenocl.pivotmodel.Namespace;
import org.dresdenocl.pivotmodel.Type;
import org.dresdenocl.pivotmodel.base.AbstractNamespace;
import org.eclipse.emf.common.util.BasicEList;
import orgomg.cwm.objectmodel.core.ModelElement;
import orgomg.cwm.objectmodel.core.Package;
import orgomg.cwm.resource.relational.NamedColumnSet;
import orgomg.cwm.resource.relational.SQLDistinctType;
import orgomg.cwm.resource.relational.SQLSimpleType;

/**
 * <p>
 * An abstract implementation of the Pivot Model {@link Namespace} concept for UML2.
 * </p>
 * 
 * <p>
 * This implementation does not wraps {@link T}s directly but manages them
 * in a collection. This is necessary to avoid duplicate wrapping of
 * {@link T}s when they are contained in two models that are imported
 * into each other.
 * </p>
 * 
 * @author Bjoern Freitag
 * @generated NOT
 */
public abstract class CWMPackage<T extends Package> extends AbstractNamespace implements Namespace {

	/**
	 * <p>
	 * Logger for this class
	 * </p>
	 * 
	 * @generated NOT
	 */
	protected Logger LOGGER; 

	/** The adapted {@link T} class. */
	protected T sqlPackage;

	
	/**
	 * The {@link Package}s representing this {@link CWMPackage}. This is a
	 * {@link Set} because multiple {@link Package}s from different CWM models
	 * can be merge to one {@link Namespace} in DresdenOCL.
	 * 
	 * @generated NOT
	 */
	protected Set<orgomg.cwm.objectmodel.core.Namespace> mergedPackages = new HashSet<orgomg.cwm.objectmodel.core.Namespace>();

	/**
	 * The nesting {@link Namespace} of this {@link CWMPackage}.
	 * 
	 * @generated NOT
	 */
	private Namespace nestingNamespace = null;
	
	/**
	 * <p>
	 * The {@link CWMAdapterFactory} used to create nested elements.
	 * </p>
	 * 
	 * @generated NOT
	 */
	protected CWMAdapterFactory factory;

	/**
	 * <p>
	 * Is the constructor for <code>CWMPackage</code>.
	 * </p>
	 * 
	 * @param sqlPackage
	 *            the {@link T} that is adopted by this
	 *            class
	 * @param factory
	 *            The {@link CWMAdapterFactory} used to create nested elements.
	 * @param nestingNamespace
	 *            The nesting {@link Namespace} of this {@link CWMPackage}.
	 * @param CWMNamedColumnSet
	 * 				the subclass of this class.
	 * 
	 * @generated NoT
	 */
	public CWMPackage(T sqlPackage, Namespace nestingNamespace,Class<? extends CWMPackage<T>> aCWMPackage,
			CWMAdapterFactory factory ) {
		LOGGER = CWMMetamodelPlugin
				.getLogger(aCWMPackage);

		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug(aCWMPackage.getName()+"(namedColumnSet = " + sqlPackage + "factory = " + factory + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// initialize
		this.mergedPackages.add(sqlPackage);
		this.nestingNamespace = nestingNamespace;
		this.factory = factory;

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(aCWMPackage.getName()+"() - exit"); //$NON-NLS-1$
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractNamespace#getName()
	 */
	@Override
	public String getName() {
		return this.mergedPackages.iterator().next().getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.pivotmodel.base.AbstractNamespace#getNestingNamespace
	 * ()
	 */
	@Override
	public Namespace getNestingNamespace() {
		return this.nestingNamespace;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.pivotmodel.base.AbstractNamespace#getOwnedType()
	 */
	@Override
	public List<Type> getOwnedType() {

		List<Type> result = new BasicEList<Type>();

		for (orgomg.cwm.objectmodel.core.Namespace mergedPackage : this.mergedPackages) {

			for (ModelElement containedType : mergedPackage.getOwnedElement()) {
			
				if (containedType instanceof NamedColumnSet || containedType instanceof SQLDistinctType
						|| (containedType instanceof SQLSimpleType && ((SQLSimpleType) containedType).getStructuralFeature().size()>0) )  {
				Type type = this.factory.createType(containedType);

				if (type != null) {
					result.add(type);
				}
				}
			}
			// end for.
		}
		// end for.

		return result;
	}
	
	/**
	 * <p>
	 * This method can be used to merge a second {@link orgomg.cwm.objectmodel.core.Namespace} into this
	 * {@link CWMPackage}. This can only be done if the new {@link orgomg.cwm.objectmodel.core.Namespace} has
	 * the same qualified name as all {@link orgomg.cwm.objectmodel.core.Namespace} already merged into this
	 * {@link CWMPackage}.
	 * </p>
	 * 
	 * @param cwmPackage
	 *            The {@link orgomg.cwm.objectmodel.core.Namespace} to be merged.
	 * @throws IllegalArgumentException
	 *             Thrown, if the given {@link Package} cannot be merged into
	 *             this {@link CWMPackage}.
	 */
	protected void mergePackage(orgomg.cwm.objectmodel.core.Namespace cwmPackage) {

		if (!this.mergedPackages.contains(cwmPackage)) {

			/* Check for name conformance. */
			if (this.mergedPackages.iterator().next().getName()
					.equals(cwmPackage.getName())) {
				this.mergedPackages.add(cwmPackage);
			}

			else {
				throw new IllegalArgumentException("Cannot merge Package "
						+ cwmPackage.getName()
						+ " to Package "
						+ this.mergedPackages.iterator().next()
								.getName() + ".");
			}
		}
		// no else (already merged).
	}
}