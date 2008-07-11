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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import tudresden.ocl20.core.jmi.mof14.model.AssociationEnd;
import tudresden.ocl20.core.jmi.mof14.model.Attribute;
import tudresden.ocl20.core.jmi.mof14.model.Classifier;
import tudresden.ocl20.core.jmi.mof14.model.Constant;
import tudresden.ocl20.core.jmi.mof14.model.ModelElement;
import tudresden.ocl20.core.jmi.mof14.model.MofClass;
import tudresden.ocl20.core.jmi.mof14.model.MofPackage;
import tudresden.ocl20.core.jmi.mof14.model.Reference;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractType;

/**
 * An implementation of the Pivot Model {@link Type} concept for
 * MOF metamodel in MDR.
 * 
 * @author Ronny Brandt
 * @version 1.0 09.05.2007
 */
public class MofType extends AbstractType implements Type {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MofType.class);

	// the adapted MOF class
	private MofClass mofClass;
	
	/**
	 * Creates a new <code>MofType</code> instance.
	 * 
	 * @param mofClass the {@link MofClass} adapted by this class
	 */
	public MofType(MofClass mofClass) {
		if (logger.isDebugEnabled()) {
			logger.debug("MofType(MofClass mofClass=" + mofClass + ") - enter");
		}

		this.mofClass = mofClass;

		if (logger.isDebugEnabled()) {
			logger.debug("MofType(MofClass) - exit");
		}
	}

	/* (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getName()
	 */
	@Override
	public String getName() {
		if (logger.isDebugEnabled()) {
			logger.debug("getName() - enter");
		}

		String returnString = mofClass.getName();
		if (logger.isDebugEnabled()) {
			logger.debug("getName() - exit - return value=" + returnString);
		}
		return returnString;
	}

	/* (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getNamespace()
	 */
	@Override
	public Namespace getNamespace() {
		if (logger.isDebugEnabled()) {
			logger.debug("getNamespace() - enter");
		}

		Namespace returnNamespace = MofAdapterFactory.INSTANCE
				.createNamespace((MofPackage) mofClass.getContainer());
		if (logger.isDebugEnabled()) {
			logger.debug("getNamespace() - exit - return value=" + returnNamespace);
		}
		return returnNamespace;
	}

	/* (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getOwnedOperationImpl()
	 */
	@Override
	protected List<Operation> getOwnedOperationImpl() {
		if (logger.isDebugEnabled()) {
			logger.debug("getOwnedOperationImpl() - enter");
		}

		List<Operation> ownedOperation = new ArrayList<Operation>();
		
		Iterator<tudresden.ocl20.core.jmi.mof14.model.Operation> it =
			mofClass.allOperations().iterator();
		
		while(it.hasNext()) {
			tudresden.ocl20.core.jmi.mof14.model.Operation operation = it.next();
			ownedOperation.add(MofAdapterFactory.INSTANCE.createOperation(operation));
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getOwnedOperationImpl() - exit - return value="
					+ ownedOperation);
		}
		return ownedOperation;
	}

	/** The unsupported properties. */
	private static Set unsupportedProperties = new HashSet();;
	
	/* (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getOwnedPropertyImpl()
	 */
	@Override
	protected List<Property> getOwnedPropertyImpl() {
		if (logger.isDebugEnabled()) {
			logger.debug("getOwnedPropertyImpl() - enter");
		}

		List<Property> ownedProperty = new ArrayList<Property>();
		
		Iterator<ModelElement> it = mofClass.getContents().iterator();
		
		while(it.hasNext()) {
			ModelElement me = it.next();
			if (me instanceof Attribute)
			{
				ownedProperty.add(MofAdapterFactory.INSTANCE.createProperty((Attribute)me));
			}
			else if (me instanceof Reference)
			{
				AssociationEnd ae = ((Reference)me).getReferencedEnd();
				ownedProperty.add(MofAdapterFactory.INSTANCE.createProperty(ae));
			}
			else if (me instanceof Constant)
			{
				ownedProperty.add(MofAdapterFactory.INSTANCE.createProperty((Constant)me));
			}
			else if (!(me instanceof tudresden.ocl20.core.jmi.mof14.model.Operation))
			{
				if (!(unsupportedProperties.contains(me.getClass())))
				{
					unsupportedProperties.add(me.getClass());
					System.out.println("Property not yet supported (necessary?): " + me.getClass());
				}
			}
		}
		

		if (logger.isDebugEnabled()) {
			logger.debug("getOwnedPropertyImpl() - exit - return value="
					+ ownedProperty);
		}
		return ownedProperty;
	}

	/* (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getSuperTypeImpl()
	 */
	@Override
	protected List<Type> getSuperTypeImpl() {
		if (logger.isDebugEnabled()) {
			logger.debug("getSuperTypeImpl() - enter");
		}

		List<Type> superType = new ArrayList<Type>();
		
		Iterator<ModelElement> it = mofClass.allSupertypes().iterator();
		
		while(it.hasNext()) {
			ModelElement me = it.next();
			if (me instanceof Classifier)
				superType.add(MofAdapterFactory.INSTANCE.createType((Classifier)me));
		}
		

		if (logger.isDebugEnabled()) {
			logger.debug("getSuperTypeImpl() - exit - return value=" + superType);
		}
		return superType;
	}
}
