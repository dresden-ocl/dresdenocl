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
package org.dresdenocl.ocl2parser.testcasegenerator.visitors;

import java.util.ArrayList;
import java.util.List;

import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IAbstractModel;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IAsSetElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.ICollectionKindElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IConstraintElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IConstraintKindElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IEnumerationLiteralElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IEssentialOclElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IListElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IModelExpression;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.INamespaceElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IOperationElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IOperationNewElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IOperationStaticElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IParameterElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IParameterKindElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IPropertyElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IPropertyNewElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IPropertyStaticElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.ITest;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.ITestSuite;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.ITestcase;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.ITestcaseElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.ITypeElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IVariable;
import org.dresdenocl.ocl2parser.testcasegenerator.util.BuildingASMException;
import org.dresdenocl.ocl2parser.testcasegenerator.util.IEnvironment;

public class VariableResolution extends BasisVisitorImpl {

	public void visitAbstractModel(IAbstractModel node, IEnvironment env) throws BuildingASMException {
		/* Get the variable of the environment. This variable is at that point in the time
		 * empty.
		 */
		List<IVariable> examVariables = env.getVariables();
		
		/* With this for-loop we examine whether the variables are unique. We put the
		 * the variables into the variables list of the environment. If a variable
		 * exists already in the variable list then this means, that this
		 * variable was declared at least twice.
		 */
		for(IVariable var : node.getVariables()) {
			
			// If the variable exists already then the variable was declared at least twice. 
			if (env.lookupVariable(var) != null) throw new BuildingASMException("The variable is not unique.", var.getToken());
			
			// Put the variable into the variable list of the environment.
			examVariables.add(var);
		}
		
		/*
		 * The abstract model must have at least the 'model' variable.
		 */
		if (node.getVariables().size() == 0) throw new BuildingASMException("An abstract model must have at least one variable.", node.getToken());
		
		/*
		 * The variable computation begins with the variable 'model' and proceeds
		 * with the variables in the model expression (if any) of the model variable.
		 * This will be done till the computation reaches the leaves.
		 * So we need to start at the 'model' variable only. All other definition
		 * will be computed so far.
		 * 
		 */
		node.getVariables().get(0).accept(this, env);
		
		/* With this for-loop we compute the variable resolution. We do this
		 * with the method call on the model expression. All variables are found in
		 * the environment.
		 */
		
		/*for(Variable var : variables) {
			var.getModelExpression().computeVariables(env);
		}*/
		
		/*
		 * Here we examine whether all variables are used or not. We create a new list of
		 * unused variables. The ones that are unused are placed there. If any unused variable
		 * exists, we write a message on the console with the involved variables.
		 */
		examVariables.clear();
		List<IVariable> unusedVariables = new ArrayList<IVariable>();
		for(IVariable var : node.getVariables()) {
			if (var.isUsed()) {
				examVariables.add(var);
			} else {
				unusedVariables.add(var);
			}
		}
		
		node.setVariables(examVariables);
		
		if (unusedVariables.size() > 0) {
			System.out.println("Hint: There are unsed variables.");
			for(IVariable var : unusedVariables) {
				System.out.println(var);
			}
		}

	}

	public void visitAsSetElement(IAsSetElement node, IEnvironment env) throws BuildingASMException {
		traverseParameters(node.getParameter(), env);
	}

	public void visitCollectionKindElement(ICollectionKindElement node, IEnvironment env) throws BuildingASMException {
		traverseParameters(node.getParameter(), env);
	}

	public void visitConstraintElement(IConstraintElement node, IEnvironment env) throws BuildingASMException {
		traverseParameters(node.getParameter(), env);

	}

	public void visitConstraintKindElement(IConstraintKindElement node, IEnvironment env) throws BuildingASMException {
		traverseParameters(node.getParameter(), env);
	}

	public void visitEnumerationLiteralElement(IEnumerationLiteralElement node, IEnvironment env) throws BuildingASMException {
		traverseParameters(node.getParameter(), env);
	}

	public void visitEssentialOclElement(IEssentialOclElement node, IEnvironment env) throws BuildingASMException {
		traverseParameters(node.getParameter(), env);
	}

	public void visitListElement(IListElement node, IEnvironment env) throws BuildingASMException {
		traverseParameters(node.getParameter(), env);
	}

	public void visitNamespaceElement(INamespaceElement node, IEnvironment env) throws BuildingASMException {
		traverseParameters(node.getParameter(), env);
	}

	public void visitOperationNewElement(IOperationNewElement node, IEnvironment env) throws BuildingASMException {
		traverseParameters(node.getParameter(), env);
	}

	public void visitOperationStaticElement(IOperationStaticElement node, IEnvironment env) throws BuildingASMException {
		traverseParameters(node.getParameter(), env);
	}
	
	public void visitOperationElement(IOperationElement node, IEnvironment env) throws BuildingASMException {
		traverseParameters(node.getParameter(), env);
	}

	public void visitParameterElement(IParameterElement node, IEnvironment env) throws BuildingASMException {
		traverseParameters(node.getParameter(), env);
	}

	public void visitParameterKindElement(IParameterKindElement node, IEnvironment env) throws BuildingASMException {
		traverseParameters(node.getParameter(), env);
	}

	public void visitPropertyNewElement(IPropertyNewElement node, IEnvironment env) throws BuildingASMException {
		traverseParameters(node.getParameter(), env);
	}
	
	public void visitPropertyElement(IPropertyElement node, IEnvironment env) throws BuildingASMException {
		traverseParameters(node.getParameter(), env);
	}

	public void visitPropertyStaticElement(IPropertyStaticElement node, IEnvironment env) throws BuildingASMException {
		traverseParameters(node.getParameter(), env);
	}

	public void visitTestSuite(ITestSuite node, IEnvironment env) throws BuildingASMException {
		for(ITest test : node.getTestElements()) {
			test.accept(this, env.nestedEnvironment());
		}
	}

	public void visitTestcase(ITestcase node, IEnvironment env) throws BuildingASMException {
		// We compute the variables of the elements in the testcase.
		for(ITestcaseElement elem : node.getTestcaseElements()) {
			IEnvironment testcaseEnv = env.nestedEnvironment();
			
			elem.accept(this, testcaseEnv);
		}
	}

	public void visitTestcaseElement(ITestcaseElement node, IEnvironment env) throws BuildingASMException {
		node.getResult().accept(this, env);
	}

	public void visitTypeElement(ITypeElement node, IEnvironment env) throws BuildingASMException {
		traverseParameters(node.getParameter(), env);
	}

	public void visitVariable(IVariable node, IEnvironment env) throws BuildingASMException {
		boolean isUsed = node.isUsed();
		boolean isChecked = node.isChecked();
		/*
		 * In the variable definition it is allowed to define variables that are never used. For
		 * example:
		 * 
		 * 	model := OperationCallExp(a, b, c)
		 *  a := Type('A')
		 *  b := Type('B')
		 *  c := Property('a')
		 *  x := Type('X')
		 *  
		 * Here the variable 'x' is never used, but if don't detect this we generate
		 * useless code. So we add a variable 'isUsed' in the variable class. If the
		 * 'visitVariable'-method in this visitor is called we set the flag on true. After the computation
		 * we collect all variables that are used and removed the others from the variable list
		 * of the 'AbstractModel'-class.
		 */
		node.setUsed(true);
		
		/*
		 * In this class we have the attribute 'isChecked'. This attribute is used to
		 * identify cycles in the definitions of model expressions. The grammar can't check this.
		 * For example, we have the following rules:
		 * 
		 * a = PropertyCallExp(b, 'propertyName', null)
		 * b = VariableExp(a)
		 * 
		 * This example contains a cycle, that we must check.
		 * We make the variable resolution and the cycle check in one step. This visit-method is called
		 * from another model expression. If we visit this variable the first time, then
		 * the 'isChecked'-attribute is false. We set it and we compute the model expression
		 * that is linked to this variable (the model expression itself can be a variable again). 
		 * If we visit this variable again, then we know that we must have a cycle and we throw
		 * an exception. If the computation of the model expression succeeds then we must unset
		 * the 'isChecked' attribute. Why? We can have the following configuration of definition rules:
		 * 
		 *   a = PropertyCallExp(b, b, null)
		 *   b = IntegerLiteralExp(1)
		 * 
		 * Beside that this rules are nonsense they show the problem. If we set the 'isCheck'-attribute
		 * only once and if we don't unset it then the computation will show us a cycle in the example
		 * above, but there is no one. The computation will start in the first rule and computes the first 'b'-variable. The
		 * 'isChecked'-attribute of the variable will be set. When the call returns it leaves checked and
		 * when we examine the next 'b'-variable we see that the 'isChecked'-attribute is set, so we conclude we
		 * have a cycle, but this is wrong.
		 * So we must unset the attribute after the computation is finished.
		 */
		if (isChecked == true) throw new BuildingASMException("A cycle was detected.", node.getToken());
		
		node.setChecked(true);
				
		/*
		 * Here we replaces all variables objects of the right side of a variable definition by the real variable object
		 * of the left side of the definition. But this is only true for complex elements. The next lines shows
		 * an example of a definition that is not covered by the 'ComplexElement'-implementation:
		 *  
		 *  model := Variable(a)
		 *  a := b
		 *  b := Type('A')
		 *  
		 *  Here we have the assignment 'a := b'. The variable computation start at the 'model'
		 *  variable. The 'Variable' ident is a complex element. So the 'a' in this expression
		 *  will be replaced by the 'a' in the second line (at object level). If this is done the computation
		 *  goes further and would like to compute the 'b', but 'b' was not replaced so far. This causes a
		 *  null pointer exception because the object has no model expression, but a computation on this
		 *  model expression is performed.
		 *  So we must look up for the model expression and must examine whether this is
		 *  a variable. If so, we must look for a model expression in this object. If no one is found,
		 *  we must replace the variable (model expression) of this variable by the variable in the environment.
		 */
		IModelExpression ref = node.getReference();
		
		/*if (ref == null) {
			IVariable variable = env.lookupVariable(node);
			if (variable == null) throw new BuildingASMException("An internal error ocurred. The variable wasn't declared before.", node.getToken());
			
			node.setReference(variable.getReference());
			variable.getReference().accept(this, env);
		}*/
		
		if (ref instanceof IVariable) {
			IVariable var = (IVariable) ref;
			if (var.getReference() == null) {
				// Look up for the variable (name) in the environment.
				IVariable realVar = env.lookupVariable(var);
				
				// If the variable (name) was not found, then the variable was not defined before, so it is an error.
				if (realVar == null) throw new BuildingASMException("The variable was not defined before.", ref.getToken());
				
				// Replace the model expression of the variable by the variable found in the environment.
				node.setReference(realVar);
			}
		}
		node.getReference().accept(this, env);
				
		node.setChecked(false);
	}
	
	/**
	 * This method traverses a list of parameters. These parameters are replaced - if the parameter
	 * is of type <i>IVariable<i/> - by a <i>IVariable</i> instance that is located in the environment.
	 * Such an instance has a reference, not the original variable that is appeared as parameter.
	 * @param list a list of paramters
	 * @param env the environment that is used
	 * @throws BuildingASMException is thrown if a variable was not declared so far or when other errors
	 * during the computation occur.
	 */
	private void traverseParameters(List<IModelExpression> list, IEnvironment env) throws BuildingASMException {
		/*
		 * The list can be null. This can be happen if no parameter for an element
		 * exists. For example: suppose we have the expression List(). So this
		 * expression has no parameter. 
		 */
		if (list == null) return;
		
		/*
		 * We iterate over the whole list.
		 */
		for(int i = 0; i < list.size(); i++) {
			/*
			 * Get the ith element.
			 */
			IModelExpression param = list.get(i);
			
			/*
			 * An instance that will save us the variable
			 * if the parameter is one.
			 */
			IVariable variable = null;
			
			/*
			 * If the parameter is a variable then we
			 * must replace this variable by a variable
			 * of the environment.
			 */
			if (param instanceof IVariable) {
				/*
				 * Type cast the parameter to the variable.
				 * Note: this is safe because we have have examine the before.
				 */
				IVariable paramVariable = (IVariable) param;
				
				/*
				 * Look up the variable(name) in the environment.
				 */
				variable = env.lookupVariable(paramVariable);
				
				/*
				 * If no variable with this name exists, this variable was not declared.
				 * So it is an error.
				 */
				if (variable == null) throw new BuildingASMException("The variable was not declared.", param.getToken());
				
				/*
				 * Here we replace the variable (parameter) through the variable of the environment.
				 */
				list.set(i, variable);
				
				/*
				 * The computation will go further with this variable.
				 */
				variable.accept(this, env);
			} else { // The parameter is not a variable. So we simple go further in the computation. 
				param.accept(this, env);
			}
			
		}
	}
}
