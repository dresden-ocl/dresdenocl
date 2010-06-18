/*
 * Copyright (C) 2008-2010 by Michael Thiele & Claas Wilke (claaswilke@gmx.net)
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.uml2.uml.Package;

import tudresden.ocl20.pivot.metamodels.uml2.UML2MetamodelPlugin;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractNamespace;

/**
 * <p>
 * An implementation of the Pivot Model {@link Namespace} concept for UML2.
 * </p>
 * 
 * <p>
 * This implementation does not wraps {@link Package}s directly but manages them
 * in a collection. This is necessary to avoid duplicate wrapping of
 * {@link Package}s when they are contained in two models that are imptorded
 * into each other.
 * </p>
 * 
 * @author Claas Wilke
 * @generated NOT
 */
public class UML2Package extends AbstractNamespace implements Namespace {

	/**
	 * <p>
	 * Logger for this class
	 * </p>
	 * 
	 * @generated NOT
	 */
	private static final Logger LOGGER = UML2MetamodelPlugin
			.getLogger(UML2Package.class);

	/**
	 * <p>
	 * The {@link UML2AdapterFactory} used to create nested elements.
	 * </p>
	 */
	private UML2AdapterFactory factory;

	/**
	 * The {@link Package}s representing this {@link UML2Package}. This is a
	 * {@link Set} because multiple {@link Package}s from different UML models
	 * can be merge to one {@link Namespace} in DresdenOCL.
	 * 
	 * @generated NOT
	 */
	private Set<Package> mergedPackages = new HashSet<Package>();

	/**
	 * The nesting {@link Namespace} of this {@link UML2Package}.
	 * 
	 * @generated NOT
	 */
	private Namespace nestingNamespace = null;

	/**
	 * <p>
	 * Creates a new UML2Package instance.
	 * </p>
	 * 
	 * @param dslPackage
	 *            The {Package} that is adapted by this class.
	 * 
	 * @param nestingNamespace
	 *            The nesting {@link Namespace} of this {@link UML2Package}.
	 * @param factory
	 *            The {@link UML2AdapterFactory} used to create nested elements.
	 * @generated NOT
	 */
	public UML2Package(Package dslPackage, Namespace nestingNamespace,
			UML2AdapterFactory factory) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("UML2Package(dslPackage = " + dslPackage + "factory = " + factory + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// initialize
		this.mergedPackages.add(dslPackage);
		this.nestingNamespace = nestingNamespace;
		this.factory = factory;

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("org.eclipse.uml2.uml.Package() - exit"); //$NON-NLS-1$
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

		List<Type> result = new ArrayList<Type>();

		for (Package mergedPackage : this.mergedPackages) {

			for (org.eclipse.uml2.uml.Type containedType : mergedPackage
					.getOwnedTypes()) {

				Type type = this.factory.createType(containedType);

				// FIXME
				if (type != null) {
					result.add(type);
				}
			}
			// end for.
		}
		// end for.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seetudresden.ocl20.pivot.pivotmodel.base.AbstractNamespace#
	 * getNestedNamespaceImpl()
	 */
	@Override
	protected List<Namespace> getNestedNamespaceImpl() {

		List<Namespace> result = new ArrayList<Namespace>();

		for (Package mergedPackage : this.mergedPackages) {

			for (Package nestedDslNamespace : mergedPackage.getNestedPackages()) {

				result.add(this.factory.createNamespace(nestedDslNamespace,
						this));
			}
			// end for.
		}
		// end for.

		return result;
	}

	/**
	 * <p>
	 * This method can be used to merge a second {@link Package} into this
	 * {@link UML2Package}. This can only be done if the new {@link Package} has
	 * the same qualified name as all {@link Package} already merged into this
	 * {@link UML2Package}.
	 * </p>
	 * 
	 * @param umlPackage
	 *            The {@link Package} to be merged.
	 * @throws IllegalArgumentException
	 *             Thrown, if the given {@link Package} cannot be merged into
	 *             this {@link UML2Package}.
	 */
	protected void mergePackage(Package umlPackage) {

		if (!this.mergedPackages.contains(umlPackage)) {

			/* Check for name conformance. */
			if (this.mergedPackages.iterator().next().getQualifiedName()
					.equals(umlPackage.getQualifiedName())) {
				this.mergedPackages.add(umlPackage);
			}

			else {
				throw new IllegalArgumentException("Cannot merge Package "
						+ umlPackage.getQualifiedName()
						+ " to Package "
						+ this.mergedPackages.iterator().next()
								.getQualifiedName() + ".");
			}
		}
		// no else (already merged).
	}
}