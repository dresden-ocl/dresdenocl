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

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;

import tudresden.ocl20.pivot.metamodels.ecore.EcoreMetamodelPlugin;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractNamespace;

/**
 * <p>
 * An implementation of the Pivot Model {@link Namespace} concept for Ecore.
 * </p>
 * 
 * @author Matthias Braeuer
 */
public class EcoreNamespace extends AbstractNamespace implements Namespace {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER = EcoreMetamodelPlugin
			.getLogger(EcoreNamespace.class);

	/** The adapted {@link EPackage}. */
	private EPackage ePackage;
	
	/**
	 * <p>
	 * The {@link EcoreAdapterFactory} used to create nested elements.
	 * </p>
	 */
	private EcoreAdapterFactory factory;

	/**
	 * <p>
	 * Creates a new {@link EcoreNamespace} instance.
	 * </p>
	 * 
	 * @param ePackage
	 *            The {@link EPackage} that is adapted by this class.
	 * @param factory
	 *            The {@link EcoreAdapterFactory} used to create nested elements.
	 */
	public EcoreNamespace(EPackage ePackage,EcoreAdapterFactory factory) {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "EcoreNamespace(";
			msg += "ePackage = " + ePackage;
			msg += ") - enter";

			LOGGER.debug(msg); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		/* Initialize adapted EPackage. */
		this.ePackage = ePackage;
		this.factory = factory;
		/* Eventually log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "EcoreNamespace() - exit";

			LOGGER.debug(msg); //$NON-NLS-1$
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractNamespace#getName()
	 */
	@Override
	public String getName() {

		return this.ePackage.getName();
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

		Namespace result;
		EPackage eSuperPackage;

		eSuperPackage = this.ePackage.getESuperPackage();

		if (eSuperPackage != null) {
			result = factory
					.createNamespace(eSuperPackage);
		}

		else {
			result = null;
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.pivotmodel.base.AbstractNamespace#getOwnedTypeImpl
	 * ()
	 */
	@Override
	public List<Type> getOwnedType() {

		List<Type> result;

		result = new BasicEList<Type>();

		for (EClassifier eClassifier : this.ePackage.getEClassifiers()) {
			result.add(factory.createType(eClassifier));
		}

		/* Improves display of model. */
		Collections.sort(result, new Comparator<Type>() {

			public int compare(Type first, Type second) {

				return first.getName().compareTo(second.getName());
			}
		});

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

		List<Namespace> result;

		result = new BasicEList<Namespace>();

		for (EPackage subPackage : this.ePackage.getESubpackages()) {
			result.add(factory.createNamespace(subPackage));
		}

		/* Improves display of model. */
		Collections.sort(result, new Comparator<Namespace>() {

			public int compare(Namespace first, Namespace second) {

				return first.getName().compareTo(second.getName());
			}
		});

		return result;
	}
}