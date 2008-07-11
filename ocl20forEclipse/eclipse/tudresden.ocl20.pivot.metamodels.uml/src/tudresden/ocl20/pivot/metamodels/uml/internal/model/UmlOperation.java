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

import tudresden.ocl20.core.jmi.uml15.core.ModelElement;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractOperation;

/**
 * An implementation of the Pivot Model {@link Operation} concept for
 * UML metamodel in MDR.
 * 
 * @author Ronny Brandt
 * @version 1.0 09.05.2007
 */
public class UmlOperation extends AbstractOperation implements Operation {
	
	/** Logger for this class. */
	private static final Logger logger = Logger.getLogger(UmlOperation.class);
	
	//the adapted Operation
	private tudresden.ocl20.core.jmi.uml15.core.Operation operation;

	/**
	 * Creates a new <code>UmlOperation</code> instance.
	 * 
	 * @param operation the {@link tudresden.ocl20.core.jmi.uml15.core.Operation}
	 * adapted by this class
	 */
	public UmlOperation(tudresden.ocl20.core.jmi.uml15.core.Operation operation) {
		if (logger.isDebugEnabled()) {
			logger
					.debug("UmlOperation(tudresden.ocl20.core.jmi.uml15.core.Operation operation="
							+ operation + ") - enter");
		}

		this.operation = operation;

		if (logger.isDebugEnabled()) {
			logger
					.debug("UmlOperation(tudresden.ocl20.core.jmi.uml15.core.Operation) - exit");
		}
	}

	/* (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractOperation#getName()
	 */
	@Override
	public String getName() {
		if (logger.isDebugEnabled()) {
			logger.debug("getName() - enter");
		}

		String returnString = operation.getName();
		if (logger.isDebugEnabled()) {
			logger.debug("getName() - exit - return value=" + returnString);
		}
		return returnString;
	}
	
	/* (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.OperationImpl#getInputParameter()
	 */
	public List<Parameter> getInputParameter() {
		if (logger.isDebugEnabled()) {
			logger.debug("getInputParameter() - enter");
		}

		List<Parameter> inputParameter = new ArrayList<Parameter>();

		Iterator it = operation.getInParametersA().iterator();
		
		while(it.hasNext()) {
			ModelElement me = (ModelElement)it.next();
			if (me instanceof tudresden.ocl20.core.jmi.uml15.core.Parameter)
				inputParameter.add(UmlAdapterFactory.INSTANCE.
						createParameter((tudresden.ocl20.core.jmi.uml15.core.Parameter)me));
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getInputParameter() - exit - return value="
					+ inputParameter);
		}
		return inputParameter;
	}

	/* (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.OperationImpl#getOutputParameter()
	 */
	public List<Parameter> getOutputParameter() {
		if (logger.isDebugEnabled()) {
			logger.debug("getOutputParameter() - enter");
		}

		List<Parameter> outputParameter = new ArrayList<Parameter>();

		Iterator it = operation.getOutParametersA().iterator();
		
		while(it.hasNext()) {
			ModelElement me = (ModelElement)it.next();
			if (me instanceof tudresden.ocl20.core.jmi.uml15.core.Parameter)
				outputParameter.add(UmlAdapterFactory.INSTANCE.
						createParameter((tudresden.ocl20.core.jmi.uml15.core.Parameter)me));
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getOutputParameter() - exit - return value="
					+ outputParameter);
		}
		return outputParameter;
	}
	
	/* (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.OperationImpl#getReturnParameter()
	 */
	public Parameter getReturnParameter() {
		if (logger.isDebugEnabled()) {
			logger.debug("getReturnParameter() - enter");
		}

		Parameter returnParameter = operation.getReturnParameterA() == null ? null
				: UmlAdapterFactory.INSTANCE
						.createParameter((tudresden.ocl20.core.jmi.uml15.core.Parameter) operation
								.getReturnParameterA());
		if (logger.isDebugEnabled()) {
			logger.debug("getReturnParameter() - exit - return value="
					+ returnParameter);
		}
		return returnParameter;
	}

	/* (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractOperation#getOwnedParameter()
	 */
	@Override
	public List<Parameter> getOwnedParameter() {
		if (logger.isDebugEnabled()) {
			logger.debug("getOwnedParameter() - enter");
		}

		List<Parameter> ownedParameter = new ArrayList<Parameter>();
		
		Iterator it = operation.getParameter().iterator();
		
		while(it.hasNext()) {
			ModelElement me = (ModelElement)it.next();
			if (me instanceof tudresden.ocl20.core.jmi.uml15.core.Parameter)
				ownedParameter.add(UmlAdapterFactory.INSTANCE.
						createParameter((tudresden.ocl20.core.jmi.uml15.core.Parameter)me));
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getOwnedParameter() - exit - return value="
					+ ownedParameter);
		}
		return ownedParameter;
	}

	/* (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractOperation#getOwningType()
	 */
	@Override
	public Type getOwningType() {
		if (logger.isDebugEnabled()) {
			logger.debug("getOwningType() - enter");
		}

		Type returnType = UmlAdapterFactory.INSTANCE.createType(operation
				.getOwner());
		if (logger.isDebugEnabled()) {
			logger.debug("getOwningType() - exit - return value=" + returnType);
		}
		return returnType;
	}
	
	/* (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.FeatureImpl#isStatic()
	 */
	public boolean isStatic() {
		if (logger.isDebugEnabled()) {
			logger.debug("isStatic() - enter");
		}

		boolean returnboolean = !operation.isInstanceLevelA();
		if (logger.isDebugEnabled()) {
			logger.debug("isStatic() - exit - return value=" + returnboolean);
		}
		return returnboolean;
	}
}
