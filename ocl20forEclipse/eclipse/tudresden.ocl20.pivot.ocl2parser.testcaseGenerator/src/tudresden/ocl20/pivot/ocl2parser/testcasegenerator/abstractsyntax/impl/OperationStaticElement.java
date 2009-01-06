/*
    Copyright (C) 2008  Nils (s0006383@inf.tu-dresden.de)

    This file is part of the testcase generator for OCL parser of the Dresden OCL2 for Eclipse.

    The testcase generator is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    The testcase generator is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with the testcase generator.  If not, see <http://www.gnu.org/licenses/>.
.
*/
package tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl;

import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IOperationStaticElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.ITokenAS;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.util.BuildingASMException;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.util.IEnvironment;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.visitors.IVisitor;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.visitors.IVisitorCodeGen;
import tudresden.ocl20.pivot.pivotmodel.Operation;

public class OperationStaticElement extends PivotmodelReferenceElement implements IOperationStaticElement {
	//private TokenAS operationNameToken = null;
	//private ModelExpression parameter;
	private Operation operation;
	
	public OperationStaticElement() {
		super(Messages.getString("OperationName"));
	}
	
	public OperationStaticElement(ITokenAS token) {
		super(token, Messages.getString("OperationName"));
	}
	/*public OperationStaticElement() {
		super(null, "Operation");
	}
	
	public OperationStaticElement(TokenAS name) {
		super(name, "Operation");
		
	}*/

	/*public void computeTypeConformance(Environment env) throws BuildingASMException {
		
		List<String> pathName = UtilClass.transformPathName2List(this.value.getValue());
		
		List<String> pathNameType = pathName.subList(0, pathName.size());
		
		List<Type> parameterList = new ArrayList<Type>();
		for(ModelExpression mExpression : parameter) {
			//Type returnType = mExpression.semanticCheck(env);
			//parameterList.add(returnType);
		}
		
		Operation oper = null;
		try {
			Type type = env.getModel().findType(pathNameType);
			oper = type.lookupOperation(pathName.get(pathName.size()-1), parameterList);
		} catch (ModelAccessException e) {
			throw new BuildingASMException("The operation was not found in the model.", value, e);
		}
		
		if (oper == null) throw new BuildingASMException("The operation was not found in the model.", value);
	}*/
	
	/**
	 * This method computes the variable resolution of the {@link ListElement}.
	 * This is method is unattractive because this method is also implemented in the class
	 * {@link ComplexElement}, but we can't move this class to the complex-elements
	 * hierarchy because of the code generation process.
	 */
//	public void computeVariables(Environment env) throws BuildingASMException {
//		/*
//		 * We iterate over all list elements.
//		 */
//		for(int i = 0; i < parameter.size(); i++) {
//			// Get the model expression.
//			ModelExpression exp = parameter.get(i);
//			/* If the actual element of the model elements is a variable
//			 * we must (if applicable) replace this object with an object
//			 * of the environment variable list.
//			 */
//			if (exp instanceof Variable) {
//				// Look for the variable in the environment.
//				Variable var = env.lookupVariableName((Variable)exp);
//				
//				/*
//				 * If no variable was found, it means that we have found a variable
//				 * that was not declared before. Remind: all variable are already in the
//				 * environment.
//				 */
//				if (var == null) throw new BuildingASMException("The variable was not declared.", exp.value);
//				
//				// Replace the actual variable through the found one. 
//				parameter.set(i, var);
//				
//				// Start the computation of variable resolution.
//				var.computeVariables(env);
//			} else { // The model element isn't a variable.
//				// We start the variable resolution of the model expression.
//				exp.computeVariables(env);
//			}
//		}
//		
//	}

	public void accept(IVisitor visitor, IEnvironment env) throws BuildingASMException {
		visitor.visitOperationStaticElement(this, env);
	}

	public void accept(IVisitorCodeGen visitor, StringBuffer buffer)
			throws BuildingASMException {
		visitor.visitOperationStaticElement(this, buffer);
		
	}

	/*public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public ModelExpression getParameter() {
		return parameter;
	}

	public void setParameter(ModelExpression parameter) {
		this.parameter = parameter;
	}

	public TokenAS getOperationNameToken() {
		return operationNameToken;
	}

	public void setOperationNameToken(TokenAS operationNameToken) {
		this.operationNameToken = operationNameToken;
	}*/
}
