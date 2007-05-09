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

import tudresden.ocl20.core.jmi.mof14.model.Classifier;
import tudresden.ocl20.core.jmi.mof14.model.Constant;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractProperty;

/**
 * An implementation of the Pivot Model {@link Property} concept for
 * Constant of MOF metamodel in MDR.
 * 
 * @author Ronny Brandt
 * @version 1.0 09.05.2007
 */
public class MofConstant extends AbstractProperty implements Property {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MofConstant.class);

	// the adapted MOF constant
	private Constant constant;
	
	/**
	 * Creates a new <code>MofConstant</code> instance.
	 * 
	 * @param constant the MOF {@link Constant} adapted by this class
	 */
	public MofConstant(Constant constant) {
		if (logger.isDebugEnabled()) {
			logger.debug("MofConstant(Constant constant=" + constant + ") - enter");
		}

		this.constant = constant;

		if (logger.isDebugEnabled()) {
			logger.debug("MofConstant(Constant) - exit");
		}
	}

	/* (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractProperty#getName()
	 */
	@Override
	public String getName() {
		if (logger.isDebugEnabled()) {
			logger.debug("getName() - enter");
		}

		String returnString = constant.getName();
		if (logger.isDebugEnabled()) {
			logger.debug("getName() - exit - return value=" + returnString);
		}
		return returnString;
	}

	/* (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractProperty#getOwningType()
	 */
	@Override
	public Type getOwningType() {
		if (logger.isDebugEnabled()) {
			logger.debug("getOwningType() - enter");
		}

		Type returnType = MofAdapterFactory.INSTANCE
				.createType((Classifier) constant.getContainer());
		if (logger.isDebugEnabled()) {
			logger.debug("getOwningType() - exit - return value=" + returnType);
		}
		return returnType;
	}

	/* (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractProperty#getType()
	 */
	@Override
	public Type getType() {
		if (logger.isDebugEnabled()) {
			logger.debug("getType() - enter");
		}

		Type returnType = MofAdapterFactory.INSTANCE.createType(constant.getType());
		if (logger.isDebugEnabled()) {
			logger.debug("getType() - exit - return value=" + returnType);
		}
		return returnType;
	}

}
