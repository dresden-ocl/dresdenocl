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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tudresden.ocl20.core.jmi.mof14.model.Association;
import tudresden.ocl20.core.jmi.mof14.model.Classifier;
import tudresden.ocl20.core.jmi.mof14.model.EnumerationType;
import tudresden.ocl20.core.jmi.mof14.model.Import;
import tudresden.ocl20.core.jmi.mof14.model.ModelElement;
import tudresden.ocl20.core.jmi.mof14.model.MofPackage;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractNamespace;

/**
 * An implementation of the Pivot Model {@link Namespace} concept for
 * MOF metamodel in MDR.
 * 
 * @author Ronny Brandt
 * @version 1.0 09.05.2007
 */
public class MofNamespace extends AbstractNamespace implements Namespace {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MofNamespace.class);

	// the adapted MOF package
	private MofPackage mofPackage; 
	
	/**
	 * Creates a new <code>MofNamespace</code> instance.
	 * 
	 * @param mofPackage the {@link MofPackage} adapted by this class
	 */
	public MofNamespace(MofPackage mofPackage) {
		if (logger.isDebugEnabled()) {
			logger.debug("MofNamespace(MofPackage mofPackage=" + mofPackage
					+ ") - enter");
		}

		this.mofPackage = mofPackage;

		if (logger.isDebugEnabled()) {
			logger.debug("MofNamespace(MofPackage) - exit");
		}
	}

	/* (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractNamespace#getName()
	 */
	@Override
	public String getName() {
		if (logger.isDebugEnabled()) {
			logger.debug("getName() - enter");
		}

		String returnString = mofPackage.getName();
		if (logger.isDebugEnabled()) {
			logger.debug("getName() - exit - return value=" + returnString);
		}
		return returnString;
	}

	/* (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractNamespace#getNestedNamespaceImpl()
	 */
	@Override
	protected List<Namespace> getNestedNamespaceImpl() {
		if (logger.isDebugEnabled()) {
			logger.debug("getNestedNamespaceImpl() - enter");
		}

		List<Namespace> nestedNamespace = new ArrayList<Namespace>();
		
		Iterator<ModelElement> it = mofPackage.getContents().iterator();
		
		while (it.hasNext()) {
			ModelElement me = it.next();
			if (me instanceof Import)
				nestedNamespace.add(MofAdapterFactory.INSTANCE.createNamespace((MofPackage)((Import)me).getImportedNamespace()));
			if (me instanceof MofPackage)
				nestedNamespace.add(MofAdapterFactory.INSTANCE.createNamespace((MofPackage)me));
		}
		

		if (logger.isDebugEnabled()) {
			logger.debug("getNestedNamespaceImpl() - exit - return value="
					+ nestedNamespace);
		}
		return nestedNamespace;
	}

	/* (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractNamespace#getNestingNamespace()
	 */
	@Override
	public Namespace getNestingNamespace() {
		if (logger.isDebugEnabled()) {
			logger.debug("getNestingNamespace() - enter");
		}

		Namespace returnNamespace = mofPackage.refImmediateComposite() == null ? null
				: MofAdapterFactory.INSTANCE.createNamespace((MofPackage) mofPackage
						.getContainer());
		if (logger.isDebugEnabled()) {
			logger.debug("getNestingNamespace() - exit - return value="
					+ returnNamespace);
		}
		return returnNamespace;
	}

	/* (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractNamespace#getOwnedType()
	 */
	@Override
	public List<Type> getOwnedType() {
		if (logger.isDebugEnabled()) {
			logger.debug("getOwnedType() - enter");
		}

		List<Type> ownedType = new ArrayList<Type>();
		
		Iterator<ModelElement> it = mofPackage.getContents().iterator();
		
		while (it.hasNext()) {
			ModelElement me = it.next();
			
			if (me instanceof Classifier)
				if (!(me instanceof Association))
					ownedType.add(MofAdapterFactory.INSTANCE.createType((Classifier)me));
		}
		

		if (logger.isDebugEnabled()) {
			logger.debug("getOwnedType() - exit - return value=" + ownedType);
		}
		return ownedType;
	}
}
