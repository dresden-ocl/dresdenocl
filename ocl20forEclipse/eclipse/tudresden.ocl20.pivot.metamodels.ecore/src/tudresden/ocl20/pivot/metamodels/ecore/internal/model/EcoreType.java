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

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;

import tudresden.ocl20.pivot.metamodels.ecore.EcoreMetamodelPlugin;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractType;

/**
 * <p>
 * An implementation of the Pivot Model {@link Type} concept for Ecore.
 * </p>
 * 
 * @author Matthias Braeuer
 */
public class EcoreType extends AbstractType implements Type {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			EcoreMetamodelPlugin.getLogger(EcoreType.class);

	/** The adapted {@link EClass}. */
	private EClass eClass;

	/**
	 * <p>
	 * Creates a new {@link EcoreType} instance.
	 * </p>
	 * 
	 * @param eClass
	 *          The adapted {@link EClass}.
	 */
	public EcoreType(EClass eClass) {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "EcoreType(";
			msg += "eClass = " + eClass;
			msg += ") - enter";

			LOGGER.debug(msg); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		/* Initialize adapted EClass. */
		this.eClass = eClass;

		/* Eventually log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "EcoreType() - exit";

			LOGGER.debug(msg); //$NON-NLS-1$
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getName()
	 */
	@Override
	public String getName() {

		return this.eClass.getName();
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getNamespace()
	 */
	@Override
	public Namespace getNamespace() {

		return EcoreAdapterFactory.INSTANCE.createNamespace(this.eClass
				.getEPackage());
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getOwnedOperationImpl ()
	 */
	@Override
	protected List<Operation> getOwnedOperationImpl() {

		List<Operation> result;

		result = new BasicEList<Operation>();

		for (EOperation eOperation : this.eClass.getEOperations()) {
			result.add(EcoreAdapterFactory.INSTANCE.createOperation(eOperation));
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getOwnedPropertyImpl()
	 */
	@Override
	protected List<Property> getOwnedPropertyImpl() {

		List<Property> result;

		result = new ArrayList<Property>();

		for (EStructuralFeature eStructuralFeature : this.eClass
				.getEStructuralFeatures()) {
			result.add(EcoreAdapterFactory.INSTANCE
					.createProperty(eStructuralFeature));
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getSuperTypeImpl()
	 */
	@Override
	protected List<Type> getSuperTypeImpl() {

		List<Type> result;

		result = new ArrayList<Type>();

		for (EClass eSuperType : this.eClass.getESuperTypes()) {
			result.add(EcoreAdapterFactory.INSTANCE.createType(eSuperType));
		}

		return result;
	}
}