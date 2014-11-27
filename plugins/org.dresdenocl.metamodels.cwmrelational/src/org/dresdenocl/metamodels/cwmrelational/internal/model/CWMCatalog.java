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

import java.util.ArrayList;
import java.util.List;

import org.dresdenocl.pivotmodel.Namespace;

import orgomg.cwm.objectmodel.core.ModelElement;
import orgomg.cwm.objectmodel.core.Package;
import orgomg.cwm.resource.relational.Catalog;

public class CWMCatalog extends CWMPackage<Catalog> implements Namespace {

	/**
	 * <p>
	 * Creates a new <code>CWMCatalog</code> instance.
	 * </p>
	 * 
	 * @param dslPackage
	 *            the {@link Catalog} that is adopted by this
	 *            class
	 * @param factory
	 *            The {@link CWMAdapterFactory} used to create nested elements.
	 * 
	 * @generated
	 */
	public CWMCatalog(Catalog dslPackage, Namespace nestingNamespace,
			CWMAdapterFactory factory) {

		super(dslPackage,nestingNamespace,CWMCatalog.class,factory);
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

		for (orgomg.cwm.objectmodel.core.Namespace mergedPackage : this.mergedPackages) {

			for (ModelElement nestedDslNamespace : mergedPackage.getOwnedElement()) {

				if (nestedDslNamespace instanceof orgomg.cwm.objectmodel.core.Namespace) result.add(this.factory.createNamespace((orgomg.cwm.objectmodel.core.Namespace) nestedDslNamespace,
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
	 * {@link CWMPackage}. This can only be done if the new {@link Package} has
	 * the same qualified name as all {@link Package} already merged into this
	 * {@link CWMPackage}.
	 * </p>
	 * 
	 * @param cwmPackage
	 *            The {@link Package} to be merged.
	 * @throws IllegalArgumentException
	 *             Thrown, if the given {@link Package} cannot be merged into
	 *             this {@link CWMPackage}.
	 */
	protected void mergePackage(Package cwmPackage) {

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