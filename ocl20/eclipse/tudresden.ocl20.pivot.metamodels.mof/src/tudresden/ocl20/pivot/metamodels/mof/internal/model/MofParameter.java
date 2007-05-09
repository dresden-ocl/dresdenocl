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

import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractParameter;

/**
 * An implementation of the Pivot Model {@link Parameter} concept for
 * MOF metamodel in MDR.
 * 
 * @author Ronny Brandt
 * @version 1.0 09.05.2007
 */
public class MofParameter extends AbstractParameter implements Parameter {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MofParameter.class);

	// the adapted MOF parameter
	private tudresden.ocl20.core.jmi.mof14.model.Parameter parameter;
	
	/**
	 * Creates a new <code>MofParameter</code> instance.
	 * 
	 * @param parameter the MOF {@link tudresden.ocl20.core.jmi.mof14.model.Parameter} adapted
	 * by this class
	 */
	public MofParameter(tudresden.ocl20.core.jmi.mof14.model.Parameter parameter) {
		if (logger.isDebugEnabled()) {
			logger
					.debug("MofParameter(tudresden.ocl20.core.jmi.mof14.model.Parameter parameter="
							+ parameter + ") - enter");
		}

		this.parameter = parameter;

		if (logger.isDebugEnabled()) {
			logger
					.debug("MofParameter(tudresden.ocl20.core.jmi.mof14.model.Parameter) - exit");
		}
	}

	/* (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractParameter#getName()
	 */
	@Override
	public String getName() {
		if (logger.isDebugEnabled()) {
			logger.debug("getName() - enter");
		}

		String returnString = parameter.getName();
		if (logger.isDebugEnabled()) {
			logger.debug("getName() - exit - return value=" + returnString);
		}
		return returnString;
	}

	/* (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractParameter#getOperation()
	 */
	@Override
	public Operation getOperation() {
		if (logger.isDebugEnabled()) {
			logger.debug("getOperation() - enter");
		}

		//TODO: getOperation überprüfen
		Operation returnOperation = MofAdapterFactory.INSTANCE
				.createOperation((tudresden.ocl20.core.jmi.mof14.model.Operation) parameter
						.getContainer());
		if (logger.isDebugEnabled()) {
			logger.debug("getOperation() - exit - return value=" + returnOperation);
		}
		return returnOperation;
	}

	/* (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractParameter#getType()
	 */
	@Override
	public Type getType() {
		if (logger.isDebugEnabled()) {
			logger.debug("getType() - enter");
		}

		Type returnType = MofAdapterFactory.INSTANCE
				.createType(parameter.getType());
		if (logger.isDebugEnabled()) {
			logger.debug("getType() - exit - return value=" + returnType);
		}
		return returnType;
	}

}
