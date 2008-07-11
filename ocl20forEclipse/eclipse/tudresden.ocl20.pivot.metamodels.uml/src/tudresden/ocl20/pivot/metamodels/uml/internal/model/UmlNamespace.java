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
package tudresden.ocl20.pivot.metamodels.uml.internal.model;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tudresden.ocl20.core.jmi.uml15.core.Classifier;
import tudresden.ocl20.core.jmi.uml15.core.ModelElement;
import tudresden.ocl20.core.jmi.uml15.modelmanagement.Package;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractNamespace;

/**
 * An implementation of the Pivot Model {@link Namespace} concept for 
 * UML metamodel in MDR.
 * 
 * @author Ronny Brandt
 * @version 1.0 09.05.2007
 */
public class UmlNamespace extends AbstractNamespace implements Namespace {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UmlNamespace.class);

  // the adapted UML package
	private Package umlPackage;
	
	/**
	 * Creates a new <code>UmlNamespace</code> instance.
	 * 
	 * @param umlPackage the {@link Package} that is adapted by this class
	 */
	public UmlNamespace(Package umlPackage) {
		if (logger.isDebugEnabled()) {
			logger.debug("UmlNamespace(Package umlPackage=" + umlPackage
					+ ") - enter");
		}

		this.umlPackage = umlPackage;

		if (logger.isDebugEnabled()) {
			logger.debug("UmlNamespace(Package) - exit");
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

		String returnString = umlPackage.getName();
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
		
		Iterator it = umlPackage.getOwnedElement().iterator();
		
		while(it.hasNext()) {
			Object next = it.next();
			ModelElement me = (ModelElement)next;
			if (me instanceof Package)
			{
				nestedNamespace.add(UmlAdapterFactory.INSTANCE.
						createNamespace((Package) me));
			}
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

		Namespace nestingNamespace = null;
		if (umlPackage.refImmediateComposite() != null)
			if (umlPackage.refImmediateComposite() instanceof tudresden.ocl20.core.jmi.uml15.core.Namespace)
				nestingNamespace = UmlAdapterFactory.INSTANCE.createNamespace(
						(Package) umlPackage.refImmediateComposite());

		if (logger.isDebugEnabled()) {
			logger.debug("getNestingNamespace() - exit - return value="
					+ nestingNamespace);
		}
		return nestingNamespace;
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
		
		Iterator it = umlPackage.getOwnedElement().iterator();
		
		while(it.hasNext()) {
			ModelElement me = (ModelElement)it.next();
			if (me instanceof Classifier)
				ownedType.add(UmlAdapterFactory.INSTANCE.createType((Classifier)me));
		}
		

		if (logger.isDebugEnabled()) {
			logger.debug("getOwnedType() - exit - return value=" + ownedType);
		}
		return ownedType;
	}

}
