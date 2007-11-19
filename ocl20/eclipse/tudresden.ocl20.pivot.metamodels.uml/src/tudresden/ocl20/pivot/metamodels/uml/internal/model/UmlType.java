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

import tudresden.ocl20.core.jmi.uml15.core.Abstraction;
import tudresden.ocl20.core.jmi.uml15.core.Association;
import tudresden.ocl20.core.jmi.uml15.core.AssociationEnd;
import tudresden.ocl20.core.jmi.uml15.core.Classifier;
import tudresden.ocl20.core.jmi.uml15.core.Generalization;
import tudresden.ocl20.core.jmi.uml15.core.Interface;
import tudresden.ocl20.core.jmi.uml15.core.ModelElement;
import tudresden.ocl20.core.jmi.uml15.core.UmlClass;
import tudresden.ocl20.core.jmi.uml15.modelmanagement.Package;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractType;

/**
 * An implementation of the Pivot Model {@link Type} concept for UML metamodel
 * in MDR.
 * 
 * @author Ronny Brandt
 * @version 1.0 09.05.2007
 */
public class UmlType extends AbstractType implements Type {

	/** Logger for this class. */
	private static final Logger logger = Logger.getLogger(UmlType.class);

	// the adapted UML classifier
	private Classifier classifier;

	/**
	 * Creates a new <code>UmlType</code> instance.
	 * 
	 * @param umlClass
	 *            the {@link UmlClass} adapted by this class
	 */
	public UmlType(UmlClass umlClass) {
		if (logger.isDebugEnabled()) {
			logger.debug("UmlType(UmlClass umlClass=" + umlClass + ") - enter");
		}

		this.classifier = umlClass;

		if (logger.isDebugEnabled()) {
			logger.debug("UmlType(UmlClass) - exit");
		}
	}

	/**
	 * Creates a new <code>UmlType</code> instance.
	 * 
	 * @param umlInterface
	 *            the {@link Interface} adapted by this class
	 */
	public UmlType(Interface umlInterface) {
		if (logger.isDebugEnabled()) {
			logger.debug("UmlType(Interface umlInterface=" + umlInterface
					+ ") - enter");
		}

		this.classifier = umlInterface;

		if (logger.isDebugEnabled()) {
			logger.debug("UmlType(Interface) - exit");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getName()
	 */
	@Override
	public String getName() {
		return classifier.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getNamespace()
	 */
	@Override
	public Namespace getNamespace() {
		return UmlAdapterFactory.INSTANCE.createNamespace((Package) classifier
				.getNamespace());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getOwnedOperationImpl()
	 */
	@Override
	protected List<Operation> getOwnedOperationImpl() {
		List<Operation> ownedOperation = new ArrayList<Operation>();

		Iterator it = classifier.allOperations().iterator();

		while (it.hasNext()) {
			ModelElement me = (ModelElement) it.next();
			if (me instanceof tudresden.ocl20.core.jmi.uml15.core.Operation)
				ownedOperation
						.add(UmlAdapterFactory.INSTANCE
								.createOperation((tudresden.ocl20.core.jmi.uml15.core.Operation) me));
		}
		return ownedOperation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getOwnedPropertyImpl()
	 */
	@Override
	protected List<Property> getOwnedPropertyImpl() {
		List<Property> ownedProperty = new ArrayList<Property>();

		Iterator it = classifier.allAttributes().iterator();

		while (it.hasNext()) {
			ModelElement me = (ModelElement) it.next();
			if (me instanceof tudresden.ocl20.core.jmi.uml15.core.Attribute)
				ownedProperty
						.add(UmlAdapterFactory.INSTANCE
								.createProperty((tudresden.ocl20.core.jmi.uml15.core.Attribute) me));
			else
				System.out.println("AttributeType: " + me.getClass());
		}

		ownedProperty.addAll(collectAssociations(classifier.getNamespace()));

		return ownedProperty;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getSuperTypeImpl()
	 */
	@Override
	protected List<Type> getSuperTypeImpl() {
		if (logger.isDebugEnabled()) {
			logger.debug("getSuperTypeImpl() - enter");
		}

		List<Type> superType = new ArrayList<Type>();

		Iterator<Generalization> it = classifier.getGeneralization().iterator();

		while (it.hasNext()) {
			Generalization gen = it.next();
			if (gen.getParent() instanceof Classifier)
				superType.add(UmlAdapterFactory.INSTANCE
						.createType((Classifier) gen.getParent()));
		}

		Iterator<Abstraction> it2 = classifier.getClientDependency().iterator();

		while (it2.hasNext()) {
			Iterator<Interface> it3 = it2.next().getSupplier().iterator();
			while (it3.hasNext())
				superType
						.add(UmlAdapterFactory.INSTANCE.createType(it3.next()));
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getSuperTypeImpl() - exit - return value="
					+ superType);
		}
		return superType;
	}

	private List<Property> collectAssociations(
			tudresden.ocl20.core.jmi.uml15.core.Namespace namespace) {
		List<Property> temp = new ArrayList<Property>();

		Iterator<ModelElement> it = namespace.getOwnedElement().iterator();
		while (it.hasNext()) {
			ModelElement me = it.next();
			if (me instanceof Association) {
				List<AssociationEnd> aeList = ((Association) me)
						.getConnection();

				AssociationEnd ae1 = aeList.get(0);
				AssociationEnd ae2 = aeList.get(1);

				if (ae1 != null && ae2.isNavigable()
						&& ae1.getParticipant() == classifier) {
					temp.add(UmlAdapterFactory.INSTANCE.createProperty(aeList
							.get(1)));
				} else if (ae2 != null && ae1.isNavigable()
						&& ae2.getParticipant() == classifier) {
					temp.add(UmlAdapterFactory.INSTANCE.createProperty(aeList
							.get(0)));
				}
			}
		}

		tudresden.ocl20.core.jmi.uml15.core.Namespace superNamespace = namespace
				.getNamespace();
		if (superNamespace != null && superNamespace != namespace)
			temp.addAll(collectAssociations(superNamespace));

		return temp;
	}
}
