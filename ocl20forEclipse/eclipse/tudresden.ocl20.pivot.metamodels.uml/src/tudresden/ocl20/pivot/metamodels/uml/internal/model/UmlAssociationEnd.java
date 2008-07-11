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

import java.util.List;

import tudresden.ocl20.core.jmi.uml15.core.AssociationEnd;
import tudresden.ocl20.core.jmi.uml15.core.Classifier;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractProperty;

/**
 * An implementation of the Pivot Model {@link Property} concept for
 * AssociationEnd of UML metamodel in MDR.
 * 
 * @author Ronny Brandt
 * @version 1.0 09.05.2007
 */
public class UmlAssociationEnd extends AbstractProperty implements Property {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(UmlAssociationEnd.class);

  // the adapted AssociationEnd
	private AssociationEnd associationEnd;
	
  /**
   * Creates a new <code>UmlAssociationEnd</code> instance.
   * 
   * @param associationEnd the UML <code>AssociationEnd</code> adapted by this object
   */
	public UmlAssociationEnd(AssociationEnd associationEnd) {
		if (logger.isDebugEnabled()) {
			logger.debug("UmlAssociationEnd(AssociationEnd associationEnd="
					+ associationEnd + ") - enter");
		}

		this.associationEnd = associationEnd;

		if (logger.isDebugEnabled()) {
			logger.debug("UmlAssociationEnd(AssociationEnd) - exit");
		}
	}

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractProperty#getName()
   */
	@Override
	public String getName() {
		if (logger.isDebugEnabled()) {
			logger.debug("getName() - enter");
		}

		String returnString = associationEnd.getName() != null ? associationEnd.getName() : 
			associationEnd.getParticipant().getName().substring(0, 1).toLowerCase().
			concat(associationEnd.getParticipant().getName().substring(1));
		if (logger.isDebugEnabled()) {
			logger.debug("getName() - exit - return value=" + returnString);
		}
		return returnString;
	}

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractProperty#getOwningType()
   */
	@Override
	public Type getOwningType() {
		if (logger.isDebugEnabled()) {
			logger.debug("getOwningType() - enter");
		}

		List<AssociationEnd> aeList = associationEnd.getAssociation().getConnection();

		if (aeList.get(0).getParticipant() == associationEnd.getParticipant())
		{
			Type returnType = UmlAdapterFactory.INSTANCE.createType(aeList.get(1)
					.getParticipant());
			if (logger.isDebugEnabled()) {
				logger.debug("getOwningType() - exit - return value=" + returnType);
			}
			return returnType;
		}
		else
		{
			Type returnType = UmlAdapterFactory.INSTANCE.createType(aeList.get(0)
					.getParticipant());
			if (logger.isDebugEnabled()) {
				logger.debug("getOwningType() - exit - return value=" + returnType);
			}
			return returnType;
		}
	}

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractProperty#getType()
   */
	@Override
	public Type getType() {
		if (logger.isDebugEnabled()) {
			logger.debug("getType() - enter");
		}

		Type returnType = UmlAdapterFactory.INSTANCE
				.createType((Classifier) associationEnd.getTypeA());
		if (logger.isDebugEnabled()) {
			logger.debug("getType() - exit - return value=" + returnType);
		}
		return returnType;
	}

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.impl.PropertyImpl#isUnique()
   */
	public boolean isUnique() {
		if (logger.isDebugEnabled()) {
			logger.debug("isUnique() - enter");
		}

		boolean returnboolean = associationEnd.isUniqueA();
		if (logger.isDebugEnabled()) {
			logger.debug("isUnique() - exit - return value=" + returnboolean);
		}
		return returnboolean;
	}

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.impl.PropertyImpl#isOrdered()
   */
	public boolean isOrdered() {
		if (logger.isDebugEnabled()) {
			logger.debug("isOrdered() - enter");
		}

		boolean returnboolean = associationEnd.isOrderedA();
		if (logger.isDebugEnabled()) {
			logger.debug("isOrdered() - exit - return value=" + returnboolean);
		}
		return returnboolean;
	}
	
  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.impl.PropertyImpl#isMultiple()
   */
	public boolean isMultiple() {
		if (logger.isDebugEnabled()) {
			logger.debug("isMultiple() - enter");
		}

		boolean returnboolean = associationEnd.isMultipleA();
		if (logger.isDebugEnabled()) {
			logger.debug("isMultiple() - exit - return value=" + returnboolean);
		}
		return returnboolean;
	}
}
