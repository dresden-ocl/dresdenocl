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

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Package;

import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractNamespace;

/**
 * <p>
 * An implementation of the Pivot Model {@link Namespace} concept for UML2.
 * </p>
 * 
 * @generated NOT
 */
public class UML2Package extends AbstractNamespace implements Namespace {

	/**
	 * <p>
	 * Logger for this class
	 * </p>
	 * 
	 * @generated
	 */
	private static final Logger logger = Logger.getLogger(UML2Package.class);

	/**
	 * <p>
	 * the adapted org.eclipse.uml2.uml.Package data type
	 * </p>
	 * 
	 * @generated
	 */
	private org.eclipse.uml2.uml.Package dslPackage;

	/**
	 * <p>
	 * Creates a new <code>UML2Package</code> instance.
	 * </p>
	 * 
	 * @param dslPackage
	 *            the {@link org.eclipse.uml2.uml.Package} that is adopted by
	 *            this class
	 * 
	 * @generated
	 */
	public UML2Package(org.eclipse.uml2.uml.Package dslPackage) {

		if (logger.isDebugEnabled()) {
			logger.debug("UML2Package(dslPackage=" + dslPackage + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// initialize
		this.dslPackage = dslPackage;

		if (logger.isDebugEnabled()) {
			logger.debug("org.eclipse.uml2.uml.Package() - exit"); //$NON-NLS-1$
		}
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractNamespace#getName()
	 * 
	 * @generated NOT
	 */
	@Override
	public String getName() {
		return this.dslPackage.getName();
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractNamespace#getNestedNamespaceImpl()
	 * 
	 * @generated NOT
	 */
	@Override
	protected List<Namespace> getNestedNamespaceImpl() {

		List<Namespace> result;

		result = new ArrayList<Namespace>();

		for (Package nestedDslNamespace : this.dslPackage.getNestedPackages()) {

			result.add(UML2AdapterFactory.INSTANCE
					.createNamespace(nestedDslNamespace));
		}

		return result;
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractNamespace#getNestingNamespace()
	 * 
	 * @generated NOT
	 */
	@Override
	public Namespace getNestingNamespace() {

		Namespace result;

		if (this.dslPackage.getNestingPackage() != null) {

			result = UML2AdapterFactory.INSTANCE
					.createNamespace(this.dslPackage.getNestingPackage());
		}

		else {
			result = null;
		}

		return result;
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractNamespace#getOwnedTypeImpl()
	 * 
	 * @generated NOT
	 */
	@Override
	public List<Type> getOwnedType() {

		List<Type> result;

		result = new ArrayList<Type>();

		for (org.eclipse.uml2.uml.Type dslOwnedType : this.dslPackage
				.getOwnedTypes()) {
			/*
			 * Associations are Types in the UML model, but are translated into
			 * Properties in
			 * tudresden.ocl20.pivot.metamodels.uml2.internal.model
			 * .UML2Model#createRootNamespace(), so they can be ignored.
			 */
			if (!(dslOwnedType instanceof Association))
				result
						.add(UML2AdapterFactory.INSTANCE
								.createType(dslOwnedType));
		}

		return result;
	}
}