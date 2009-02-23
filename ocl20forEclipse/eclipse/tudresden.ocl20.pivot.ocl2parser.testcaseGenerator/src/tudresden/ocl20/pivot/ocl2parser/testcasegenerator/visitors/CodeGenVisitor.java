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
package tudresden.ocl20.pivot.ocl2parser.testcasegenerator.visitors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IAbstractModel;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IAsSetElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.ICollectionKindElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IConstraintElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IConstraintKindElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IEnumerationLiteralElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IErrorElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IEssentialOclElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IIntegerElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IListElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IMetamodelReference;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IModelExpression;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IModelReference;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.INamespaceElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.INullElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IOperationElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IOperationNewElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IOperationStaticElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IPackageDeclaration;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IParameterElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IParameterKindElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IPivotmodelReferenceElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IPropertyElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IPropertyNewElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IPropertyStaticElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IRealElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IStringElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.ITest;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.ITestSuite;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.ITestcase;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.ITestcaseElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.ITypeElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IVariable;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.util.BuildingASMException;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.util.ICodeBuilder;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.util.BuildingCodeUtilClass;

/**
 * This visitor traverses the object model of the test model and generates the code for the
 * test case. The generated code is hold in the variable <i>code</i> and will be processed by the
 * code builder, that is transferred to this visitor. 
 * @author Nils
 *
 */
public class CodeGenVisitor implements IVisitorCodeGen {
	private List<IVariable> iteratedVariables = null;
	
	/* 
	 * This number will be used for all generated elements, that are created newly
	 * during this code generation to make the elements unique.
	 */
	private int elementNumber = 0;
	
	private StringBuffer variableDeclaration = null;
	
	// The code builder that is used for writing the code out to a plugin.
	private ICodeBuilder codeBuilder = null;
	
	/**
	 * Initialize the visitor with a code builder.
	 * @param codeBuilder the code builder that is used to generate the test plugin
	 */
	public CodeGenVisitor(ICodeBuilder codeBuilder) {
		iteratedVariables = new ArrayList<IVariable>();
		
		variableDeclaration = new StringBuffer();
		this.codeBuilder = codeBuilder;
	}

	public void visitAbstractModel(IAbstractModel node, StringBuffer buffer) throws BuildingASMException {
		IVariable modelVariable = node.getVariables().get(0);
		StringBuffer localBuf = new StringBuffer();
		
		modelVariable.accept(this, localBuf);
		
		codeBuilder.addCodeSnippet("model = " + localBuf + ";\n");

	}

	public void visitAsSetElement(IAsSetElement node, StringBuffer buffer) throws BuildingASMException {
		// Get the first and only parameter of the asSet element.
		IModelExpression param = node.getParameter().get(0);
		
		// Create a new local buffer that will save the result of the further computation.
		StringBuffer localBuf = new StringBuffer();
		
		// Begin the computation of the parameter with the local buffer variable.
		param.accept(this, localBuf);
		
		// The caller gets the code of the parameter computation plus the method call asSet()
		buffer.append(localBuf.toString() + ".withAsSet()");
	}

	public void visitCollectionKindElement(ICollectionKindElement node, StringBuffer buffer) throws BuildingASMException {
		IModelExpression exp = node.getParameter().get(0);
		StringBuffer localBuf = new StringBuffer();
		exp.accept(this, localBuf);
		
		// The string element will return a localBuf with quotes, so we must remove them.
		String bufString = localBuf.toString();
		bufString = BuildingCodeUtilClass.deleteChar(bufString, '"');
		
		buffer.append("CollectionKind." + bufString);

	}

	public void visitConstraintElement(IConstraintElement node,	StringBuffer buffer) throws BuildingASMException {
		List<StringBuffer> localBuffers = new ArrayList<StringBuffer>();
		
		for(int i = 0; i < node.getParameter().size(); i++) {
			StringBuffer localBuf = new StringBuffer();
			localBuffers.add(localBuf);
			node.getParameter().get(i).accept(this, localBuf);
		}
		
		// We save us the actual element number.
		int localElementNumber = elementNumber;
		
		// We create a new name for the constraint variable.
		String constraintVarName = "constraint$" + localElementNumber;
		
		codeBuilder.addCodeSnippet("Constraint " + constraintVarName + " = pivotFactory.createConstraint();\n");
		codeBuilder.addCodeSnippet(constraintVarName + ".setName("+localBuffers.get(0) + ");\n");
		codeBuilder.addCodeSnippet(constraintVarName + ".setKind(" + localBuffers.get(1) + ");\n");
		codeBuilder.addCodeSnippet(constraintVarName + ".setSpecification(" + localBuffers.get(2) + ");\n");
		codeBuilder.addCodeSnippet("for(ConstrainableElement constraint$new : " + localBuffers.get(3) + ") {\n");
		codeBuilder.addCodeSnippet(constraintVarName + ".addConstrainedElement(constraint$new);\n");
		codeBuilder.addCodeSnippet("}\n");
		codeBuilder.addCodeSnippet(constraintVarName + ".setDefinedFeature(" + localBuffers.get(4) + ");\n");
		
		codeBuilder.addCodeSnippet("\n");
		
		buffer.append(constraintVarName);
		
		elementNumber++;

	}

	public void visitConstraintKindElement(IConstraintKindElement node, StringBuffer buffer) throws BuildingASMException {
		IModelExpression exp = node.getParameter().get(0);
		StringBuffer localBuf = new StringBuffer();
		exp.accept(this, localBuf);
		
		// The call to the string element will return a string with quotes, but we must remove them.
		String bufString = localBuf.toString();
		
		bufString = BuildingCodeUtilClass.deleteChar(bufString, '"');
		buffer.append("ConstraintKind." + bufString);

	}

	public void visitEnumerationLiteralElement(IEnumerationLiteralElement node, StringBuffer buffer) throws BuildingASMException {
		// Get the first and only parameter of the element literal element.
		IModelExpression param = node.getParameter().get(0);
		
		// If the parameter is a variable then take the last element of the variable chain.
		if (param instanceof IVariable) param = BuildingCodeUtilClass.transferLastElementOfVariableChainList((IVariable) param);
		
		// The last element of the variable chain must be a string.
		if (!(param instanceof IStringElement)) throw new BuildingASMException("The parameter of an EnumerationLiteral must be string.", param.getToken());
		
		// Get the value of the string element. It is a path name.
		String literalString = param.getToken().getValue();
		
		// Transform the pathname into a list of strings.
		List<String> pathName = BuildingCodeUtilClass.transformPathName2List(literalString);
		
		// Generate the code that builds the name of the enumeration path name.
		codeBuilder.addCodeSnippet("List<String> enumName$" + elementNumber + " = new ArrayList<String>();\n");
		for(int i = 0; i < pathName.size()-1; i++) {
			codeBuilder.addCodeSnippet("enumName$" + elementNumber + ".add(\"" + pathName.get(i) + "\");\n");
		}
		
		// Generate the code that try to find the enumeration in the model.
		codeBuilder.addCodeSnippet("Enumeration enum$" + elementNumber + " = (Enumeration) referencedModel.findType(enumName$" + elementNumber + ");\n");
		
		// Generate the error code for the case that the enumeration wasn't found.
		codeBuilder.addCodeSnippet("if (enum$" + elementNumber + " == null) throw new BuildModelException(\"The enumeration was not found while building the compare model. EnumerationName: " + literalString +"\");\n");
		
		// Generate the code that looks up in the enumeration for the enumeration literal.
		codeBuilder.addCodeSnippet("EnumerationLiteral enumLiteral$" + elementNumber + " = enum$" + elementNumber + ".lookup(\"" + pathName.get(pathName.size()-1) + "\");\n");
		
		// Generate the error code for the case that the enumeration literal doesn't exist.
		codeBuilder.addCodeSnippet("if(enumLiteral$" + elementNumber + " == null) throw BuildingModelException(\"The literal was not found. Literalname: "+ literalString + ");\n");
		
		// Give the variable that denotes the enumeration literal to the caller.		
		buffer.append("enumLiteral$" + elementNumber);
		
		// We must increment the element number for further processing.
		elementNumber++;

	}

	public void visitErrorElement(IErrorElement node, StringBuffer buffer) throws BuildingASMException {
		/*
		 * The code builder is instructed to generate code from which is expected that will fail.
		 */
		codeBuilder.setErrorElement(true);

	}

	public void visitEssentialOclElement(IEssentialOclElement node, StringBuffer buffer) throws BuildingASMException {
		List<StringBuffer> localBuffers = new ArrayList<StringBuffer>();
		for(int i = 0; i < node.getParameter().size(); i++) {
			StringBuffer localBuf = new StringBuffer();
			localBuffers.add(localBuf);
			node.getParameter().get(i).accept(this, localBuf);
		}
		
		if (node.isAtPre()) {
			buffer.append("modelFactory.create" + node.getToken().getValue() + "(" + BuildingCodeUtilClass.concatElementsOfStringBufferList(localBuffers) + ").withAtPre()");
		} else {
			buffer.append("modelFactory.create" + node.getToken().getValue() + "(" + BuildingCodeUtilClass.concatElementsOfStringBufferList(localBuffers) + ")");
		}
		

	}

	public void visitIntegerElement(IIntegerElement node, StringBuffer buffer) throws BuildingASMException {
		buffer.append(node.getToken().getValue());

	}

	public void visitListElement(IListElement node, StringBuffer buffer) throws BuildingASMException {
		// First we generate the code for the parameters. We need a list of string buffers.
		List<StringBuffer> localBuffers = new ArrayList<StringBuffer>();
		
		boolean arrayList = node.isArray();
		
		// We iterate over all parameters and generate the code for them.
		for(int i = 0; i < node.getParameter().size(); i++) {
			StringBuffer localBuf = new StringBuffer();
			localBuffers.add(localBuf);
			node.getParameter().get(i).accept(this, localBuf);
		}
		
		// We should generate a java.util.List
		if (arrayList == false) {
			// If the list has no elements, it has no type.
			if (node.getParameter().size() == 0) {
				codeBuilder.addCodeSnippet("List list$" + elementNumber + " = new ArrayList();\n");
			} else {
				// Generate the code for the creation of the list. Take the list type of the list element as generic parameter.
				codeBuilder.addCodeSnippet("List<" + node.getFullQualifiedListTypeName() + "> list$" + elementNumber + " = new ArrayList<" + node.getFullQualifiedListTypeName() + ">();\n");
			}
			
			// Iterate over all parameters and generate the code for it for the list.
			for(int i = 0; i < node.getParameter().size(); i++) {
				IModelExpression param = node.getParameter().get(i);
				
				// Generate code to add the element to the list.
				codeBuilder.addCodeSnippet("list$" + elementNumber + ".add(" + localBuffers.get(i).toString() + ");\n");
			}
		} else { // We should generate an array.
			
			// Generate the code for the creation of the array.
			codeBuilder.addCodeSnippet(node.getFullQualifiedListTypeName() + "[] list$" + elementNumber + " = new " + node.getFullQualifiedListTypeName() + "[" + node.getParameter().size() + "];\n");
			
			/*
			 * Iterate over all parameters and generate the code for it.
			 */
			for(int i = 0; i < node.getParameter().size(); i++) {
				IModelExpression param = node.getParameter().get(i);
				
				// Generate the code to add the element the array.
				codeBuilder.addCodeSnippet("list$" + elementNumber + "[" + i + "] = " + localBuffers.get(i).toString() + ";\n");
			}
		}
		
		// The caller gets the list name.
		buffer.append("list$" + elementNumber);
		
		// We must increment the element number for other code generation.
		elementNumber++;

	}

	public void visitMetamodelReference(IMetamodelReference node, StringBuffer buffer) throws BuildingASMException {
		codeBuilder.setMetamodel(node.getToken().getValue());

	}

	public void visitModelReference(IModelReference node, StringBuffer buffer) throws BuildingASMException {
		codeBuilder.setModelFile(node.getToken().getValue());

	}

	public void visitNamespaceElement(INamespaceElement node, StringBuffer buffer) throws BuildingASMException {
		/*
		 * First we process the first parameter of the namespace element.
		 * This is a string that denotes the full qualified namespace name.
		 * But the parameter can also be a variable. If it is a variable we get the
		 * associated string element of this variable chain. The original variable
		 * name will not occur in the generated code.
		 */
		IModelExpression param1 = node.getParameter().get(0);
		if (param1 instanceof IVariable) param1 = BuildingCodeUtilClass.transferLastElementOfVariableChainList((IVariable) param1);
		
		if (!(param1 instanceof IStringElement)) throw new BuildingASMException("The first parameter of the namespace element must be a string.", param1.getToken());
		
		// Cast the first parameter to the string element.
		IStringElement paramString = (IStringElement) param1;
		
		// Make a string list of the full qualified namespace name.
		List<String> pathName = BuildingCodeUtilClass.transformPathName2List(paramString.getToken().getValue());
		
		// Notice the first free number.
		int localNumber = elementNumber;
		
		// The return value will be the first namespace variable.
		buffer.append("namespace$" + localNumber);
		
		/*
		 * Iterate over all elements of the path name and add two lines of codes.
		 * The first line creates the namespace variable and the second set the name
		 * of the namespace. At the end we must increment the localNumber variable.
		 */
		for(String st : pathName) {
			codeBuilder.addCodeSnippet("Namespace namespace$" + localNumber + " = null;");
			codeBuilder.addCodeSnippet("namespace$" + localNumber + " = pivotFactory.createNamespace();\n");
			codeBuilder.addCodeSnippet("namespace$" + localNumber + ".setName(\"" + st + "\");\n");
			localNumber++;
		}
		
		// Add a new line two the code for better readability.
		codeBuilder.addCodeSnippet("\n");
		
		/*
		 * Now we must build a tree of the even created namespaces. The schema is the following:
		 * namespace$1.addNestedNamespace(namespace$2);
		 * namespace$2.addNestedNamespace(namespace$3);
		 * ...
		 * Note: the last namespace has no nested namespace, so the loop make a traverse
		 * only to the second last element.
		 * Here increment the real element number. Note: in the first for-loop we use the
		 * localNumber-variable. So here the number begins from the beginning.
		 * The elementNumber variable will be incremented one too much. So we don't need
		 * to increment this variable (the next caller need a new fresh variable).
		 */
		for(int i = 0; i < pathName.size()-1; i++) {
			codeBuilder.addCodeSnippet("namespace$" + elementNumber + ".addNestedNamespace(namespace$" + (elementNumber+1) + ");\n");
			elementNumber++;
		}
		
		/*
		 * Now we process the second parameter of the namespace.
		 * This is the list of constraints.
		 * In the localBuf variable we will get the name of the list.
		 */
		StringBuffer localBuf = new StringBuffer();
		node.getParameter().get(1).accept(this, localBuf);
		
		/*
		 * We generate a for-loop with the for-variable constraint%toBeAdded. The list name
		 * is localBuf. The (localNumber-1) variable is the last used number and so
		 * the last namespace is so named. To the last namespace we will add the constraints.
		 */
		codeBuilder.addCodeSnippet("for(Constraint constraint$toBeAdded : " + localBuf.toString() + ") {\n");
		codeBuilder.addCodeSnippet("namespace$" + (localNumber-1) + ".addRule(constraint$toBeAdded);\n");
		codeBuilder.addCodeSnippet("}\n");
	}

	public void visitNullElement(INullElement node, StringBuffer buffer) throws BuildingASMException {
		buffer.append("null");
	}
	
	private void generateCodeForOperation(IPivotmodelReferenceElement node, StringBuffer buffer) throws BuildingASMException {
		// We save us the current element number.
		int localElementNumber = elementNumber;
		
		// Get the first parameter that denotes the full qualified operation name.
		IModelExpression param1 = node.getParameter().get(0);
		
		// If the first parameter is a variable then we take the last element of the variable chain.
		if (param1 instanceof IVariable) param1 = BuildingCodeUtilClass.transferLastElementOfVariableChainList((IVariable) param1);
		
		// If the last element of the variable chain isn't a string, it is an error.
		if (!(param1 instanceof IStringElement)) throw new BuildingASMException("The first parameter of the operation" +
				" must be a string that denotes the full qualified operation name.", param1.getToken());
		
		// Transform the string given by the user into a list of strings.
		List<String> operationPathname = BuildingCodeUtilClass.transformPathName2List(param1.getToken().getValue());
		
		// The last element of the operation pathname is the operation name itself.
		String operationName = operationPathname.get(operationPathname.size()-1);
		
		/* 
		 * The first elements without the last element of the operation pathname is the type name that
		 * references to the type that contains the operation.
		 */
		List<String> operationTypename = operationPathname.subList(0, operationPathname.size()-1);
		
		// Transform the operation type into a string with double colons. It is used to construct the error messages.
		String operationTypenameString = BuildingCodeUtilClass.concatElementsWithDblColon(operationTypename);
		
		// Generate code that creates a string list that will contain the elements of the operation type path name.
		codeBuilder.addCodeSnippet("List<String> operationTypename$" + localElementNumber + " = new ArrayList<String>();\n");
		
		// Generate the code for adding the element of the type path name to the newly created list.
		for(int i = 0; i < operationTypename.size(); i++) {
			codeBuilder.addCodeSnippet("operationTypename$" + localElementNumber + ".add(\"" + operationTypename.get(i) + "\");\n");
		}
		
		// Create a new string buffer that will save the result of the second parameter computation.
		StringBuffer localBuf = new StringBuffer();
		
		// Generate the code for the second parameter. It should be a java.util.List of types.
		node.getParameter().get(1).accept(this, localBuf);
		
		// Generate the code that save the parameters of the operation.
		codeBuilder.addCodeSnippet("List<Type> parameters$" + localElementNumber + " = " + localBuf.toString() +";\n");
		
		// Generate code that finds the type of the operation.
		codeBuilder.addCodeSnippet("Type operationType$" + localElementNumber + " = referencedModel.findType(operationTypename$" + localElementNumber + ");\n");
		
		// Generate code for the case that the operation type isn't found. Here we use the string with the double colons.
		codeBuilder.addCodeSnippet("if (operationType$" + localElementNumber + " == null) throw new BuildModelException(\"The type of the operation was not found: " + operationTypenameString + ".\");\n");
		
		// Generate the code for finding the operation in the type. Here we use the parameter list.
		codeBuilder.addCodeSnippet("Operation operation$" + localElementNumber + " = operationType$" + localElementNumber + ".lookupOperation(\"" + operationName + "\", parameters$" + localElementNumber + ");\n");
		
		// Generate code for the case that the operation isn't found for the type.
		codeBuilder.addCodeSnippet("if (operation$" + localElementNumber + " == null) throw new BuildModelException(\"The operation " + operationName + " was not found in the type " + operationTypenameString + ".\");\n" );
		
		// The caller gets the operation variable.
		buffer.append("operation$" + localElementNumber);
		
		// We must increment the element number for further code generation.		
		elementNumber++;
	}

	public void visitOperationElement(IOperationElement node, StringBuffer buffer) throws BuildingASMException {
		// Forward the code generation.
		generateCodeForOperation(node, buffer);
	}

	public void visitOperationNewElement(IOperationNewElement node,	StringBuffer buffer) throws BuildingASMException {
		// We save the current element number.
		int localElementNumber = elementNumber;
		
		// Get the first parameter.
		IModelExpression param1 = node.getParameter().get(0);
		
		// If the first parameter is a variable then we take the last element of the variable chain.
		if (param1 instanceof IVariable) param1 = BuildingCodeUtilClass.transferLastElementOfVariableChainList((IVariable) param1);
		
		// If the first parameter isn't a string, then it is an error.
		if (!(param1 instanceof IStringElement)) throw new BuildingASMException("The first parameter of a new operation must be a string that denotes the full qualified operation name.", param1.getToken());
		
		/* 
		 * The first parameter denotes the full qualified path name to the new operation separated by double colons.
		 * So we transfer this path name into a list of strings.
		 */
		List<String> operationPathname = BuildingCodeUtilClass.transformPathName2List(param1.getToken().getValue());
		
		// The operation type name consists of the all elements of the operation name except the last element that denotes the operation name itself.
		List<String> operationTypename = operationPathname.subList(0, operationPathname.size()-1);
		
		// We need the operation typename separated by double colons for error messages.
		String operationTypenameString = BuildingCodeUtilClass.concatElementsWithDblColon(operationTypename);
		
		// The operation name.
		String operationName = operationPathname.get(operationPathname.size()-1);
		
		// Generate code that creates a list of strings that will hold the type name.
		codeBuilder.addCodeSnippet("List<String> operationTypename$" + localElementNumber + " = new ArrayList<String>();\n");
		
		// Iterate over all parts of the operation type name to generate code that add each part of it to the list.
		for(int i = 0; i < operationTypename.size(); i++) {
			codeBuilder.addCodeSnippet("operationTypename$" + localElementNumber + ".add(\"" + operationTypename.get(i) + "\");\n");
		}
		
		// Generate code that search for the type in the model.
		codeBuilder.addCodeSnippet("Type operationType$" + localElementNumber + " = referencedModel.findType(operationTypename$" + localElementNumber + ");\n");
		
		// Generate code for the case that the type isn't found in the corresponding model.
		codeBuilder.addCodeSnippet("if (operationType$" + localElementNumber + " == null) throw new BuildModelException(\"The type of the operation was not found: " + operationTypenameString + ".\");\n");
		
		// Generate code that creates the new operation by the pivotmodel factory.
		codeBuilder.addCodeSnippet("Operation newOperation$" + localElementNumber + " = pivotFactory.createOperation();\n");
		
		// Generate code that sets the operation name of the new operation.
		codeBuilder.addCodeSnippet("newOperation$" + localElementNumber + ".setName(\"" + operationName + "\");\n");
		
		// Create a local buffer to store the result of the second parameter computation.
		StringBuffer localBuf = new StringBuffer();
		
		// Compute the second parameter (it is a list of parameters).
		node.getParameter().get(1).accept(this, localBuf);
		
		// Generate code that transfer the parameter list to a new variable.
		codeBuilder.addCodeSnippet("List<Parameter> parameters$" + localElementNumber + " = " + localBuf.toString() +";\n");
		
		// Generate a for-loop that iterates over all parameters and add each to the new operation.
		codeBuilder.addCodeSnippet("for(Parameter param$new : parameters$" + localElementNumber + ") {\n");
		codeBuilder.addCodeSnippet("newOperation$" + localElementNumber + ".addParameter(param$new);\n");
		codeBuilder.addCodeSnippet("}\n");
		
		// Generate code that add the new operation to the operation type.
		codeBuilder.addCodeSnippet("operationType$" + localElementNumber + ".addOperation(newOperation$" + localElementNumber + ");\n");
		
		// The caller gets the operation name of the new operation.
		buffer.append("newOperation$" + localElementNumber);
		
		// We must increment the element number for further code generation.
		elementNumber++;

	}

	public void visitOperationStaticElement(IOperationStaticElement node, StringBuffer buffer) throws BuildingASMException {
		// Forward the code generation.
		generateCodeForOperation(node, buffer);
		
		/* 
		 * We generate code that examines whether the operation is static. If not, it is an error.
		 * In the buffer variable the name of the operation variable is stored.
		 */
		codeBuilder.addCodeSnippet("if (!" + buffer.toString() + ".isStatic()) throw new" +
				" BuildModelException(\"The operation was found, but it is not static. Operation name: \" + " + buffer.toString() + ".getName() + \".\");\n");

	}

	public void visitPackageDeclaration(IPackageDeclaration node, StringBuffer buffer) throws BuildingASMException {
		// The code builder get the name of the package.
		codeBuilder.setPackagename(node.getToken().getValue());

	}

	public void visitParameterElement(IParameterElement node, StringBuffer buffer) throws BuildingASMException {
		// We create a list of new string buffers that will be used to store the results of the parameter computations
		List<StringBuffer> localBuffers = new ArrayList<StringBuffer>();
		
		// Iterate over all parameter of the parameter element.
		for(IModelExpression param : node.getParameter()) {
			// Create a new string buffer.
			StringBuffer buf = new StringBuffer();
			
			// Add the new string buffer to the list.
			localBuffers.add(buf);
			
			// Make the computation on the parameter with the new string buffer.
			param.accept(this, buf);
		}
		
		// The name of the variable that represents the parameter.
		String parameterName = "parameter$" + elementNumber;
		
		// Generate code that creates the parameter and sets its properties.
		codeBuilder.addCodeSnippet("Parameter " + parameterName + " = pivotFactory.createParameter();\n");
		codeBuilder.addCodeSnippet(parameterName + ".setName(" + localBuffers.get(0) + ");\n");
		codeBuilder.addCodeSnippet(parameterName + ".setKind(" + localBuffers.get(1) + ");\n");
		codeBuilder.addCodeSnippet(parameterName + ".setType(" + localBuffers.get(2) + ");\n");
		codeBuilder.addCodeSnippet("\n");
		
		// The caller gets the parameter variable.
		buffer.append(parameterName);
		
		// We must increment the element number for further processing.
		elementNumber++;
		

	}

	public void visitParameterKindElement(IParameterKindElement node, StringBuffer buffer) throws BuildingASMException {
		StringBuffer localBuf = new StringBuffer();
		IModelExpression exp = node.getParameter().get(0);
		exp.accept(this, localBuf);
		
		// The string element will return a string with quotes, so we must remove them.
		String bufString = localBuf.toString();
		bufString = BuildingCodeUtilClass.deleteChar(bufString, '"');
		
		buffer.append("ParameterDirectionKind." + bufString);

	}
	
	private void generateCodeForProperty(IPivotmodelReferenceElement node, StringBuffer buffer) throws BuildingASMException {
		// Get the first and only element of the property element.
		IModelExpression param1 = node.getParameter().get(0);
		
		// If the parameter is a variable then we take the last element of this variable chain.
		if (param1 instanceof IVariable) param1 = BuildingCodeUtilClass.transferLastElementOfVariableChainList((IVariable) param1);
		
		// The last element of the variable chain must be a string that denotes the path to the property.
		if (!(param1 instanceof IStringElement)) throw new BuildingASMException("The first parameter of a property must be a string that denotes the path to the poperty.", param1.getToken());
		
		// Transform the path name given by the user to a list of strings.
		List<String> pathNameProperty = BuildingCodeUtilClass.transformPathName2List(param1.getToken().getValue());
		
		// Generate the code that creates a string list that should represent the path name to the type of the property.
		codeBuilder.addCodeSnippet("List<String> propertyTypename$" + elementNumber + " = new ArrayList<String>();\n");
		
		/* 
		 * Generate the code for the adding the type elements to the generated list. Note: the last element of
		 * property path name denotes the property itself. So it will not be added.
		 */
		for(int i = 0; i < pathNameProperty.size() -1; i++) {
			codeBuilder.addCodeSnippet("propertyTypename$" + elementNumber + ".add(\"" + pathNameProperty.get(i) + "\");\n");
		}
		
		// Generate the code that searches for the type of the property in the corresponding model.
		codeBuilder.addCodeSnippet("Type propertyType$" + elementNumber + " = referencedModel.findType(propertyTypename$" + elementNumber + ");\n");
		
		// Reconstruct the type name of the property from the property path name. It will be used for the error message to be generated.
		String typeName = BuildingCodeUtilClass.concatElementsWithDblColon(pathNameProperty.subList(0, pathNameProperty.size()-1));
		
		// Reconstruct the property name from the property path name (it is the last element). It will be used for the error message to be generated.
		String propertyName = pathNameProperty.get(pathNameProperty.size()-1);
		
		// Generate the code for the error message for the case that the type wasn't found. Here we use the typename and the property name.
		codeBuilder.addCodeSnippet("if (propertyType$" + elementNumber + " == null) throw new BuildModelException(\"The type " + typeName + " for the property " + propertyName +" was not found.\");\n");
		
		// Generate the code for searching the property in the found type.
		codeBuilder.addCodeSnippet("Property property$" + elementNumber + " = propertyType$" + elementNumber + ".lookupProperty(\"" + propertyName + "\");\n");
		
		// Generate the code for the case that the property was not found in the type.
		codeBuilder.addCodeSnippet("if (property$" + elementNumber + " == null) throw new BuildModelException(\"The property " + propertyName + " doesn't exist for the type " + typeName + ".\");\n");
		
		// The caller gets the name of the property variable.
		buffer.append("property$" + elementNumber);
		
		// We increment the element number for other elements to be generated.
		elementNumber++;

	}
	
	public void visitPropertyElement(IPropertyElement node, StringBuffer buffer) throws BuildingASMException {
		// Generate the code for the property.
		generateCodeForProperty(node, buffer);
	}
	
	public void visitPropertyNewElement(IPropertyNewElement node, StringBuffer buffer) throws BuildingASMException {
		// We save us the current element number.
		int localElementNumber = elementNumber;
		
		// Get the first parameter.
		IModelExpression param1 = node.getParameter().get(0);
		
		// If the first parameter is a variable then we take the last element of the variable chain.
		if (param1 instanceof IVariable) param1 = BuildingCodeUtilClass.transferLastElementOfVariableChainList((IVariable) param1);
		
		// If the first parameter isn't a string then it is an error.
		if (!(param1 instanceof IStringElement)) throw new BuildingASMException("The first parameter of a property" +
				" (that should be created) must be string that denotes the full qualified path name of the new property.", param1.getToken());
		
		// Transform the string given by the user (separated by double colons) into a list of strings.
		List<String> propertyPathname = BuildingCodeUtilClass.transformPathName2List(param1.getToken().getValue());
		
		// Generate the code that creates the list of the property typename.
		codeBuilder.addCodeSnippet("List<String> propertyTypename$" + localElementNumber + " = new ArrayList<String>();\n");
		
		/* 
		 * Generate the code that adds the parts of the type path name to the list.
		 * Note that we leave out the last element of the property pathname because this
		 * element is the name of the property and doesn't belong to the type.
		 */
		for(int i = 0; i < propertyPathname.size()-1; i++) {
			codeBuilder.addCodeSnippet("propertyTypename$" + localElementNumber + ".add(\"" + propertyPathname.get(i) + "\");\n");
		}
		
		// Reconstruct the typename from the property path name. It will be used for error messages.
		String typeName = BuildingCodeUtilClass.concatElementsWithDblColon(propertyPathname.subList(0, propertyPathname.size()-1));
		
		// Reconstruct the property name (it is the last element of the path name) from the property path name. It will be used for error messages.
		String propertyName = propertyPathname.get(propertyPathname.size()-1);
		
		// Generate code search for the type to what the property should be added.
		codeBuilder.addCodeSnippet("Type propertyType$" + localElementNumber + " = referencedModel.findType(propertyTypename$" + localElementNumber + ");\n");
		
		// Generate the code for the case that the type doesn't exist.
		codeBuilder.addCodeSnippet("if (propertyType$" + localElementNumber + " == null) throw new BuildModelException(\"The type " + typeName + " was not found.\");\n");
		
		// Get the second parameter of the property element.
		IModelExpression param2 = node.getParameter(1);
		
		// Create a string buffer to store the result of the computation of the second parameter.
		StringBuffer localBuf = new StringBuffer();
		
		// Make code generation on the second parameter.
		param2.accept(this, localBuf);
		
		// Generate the code to create a property through the pivotmodel factory.
		codeBuilder.addCodeSnippet("Property property$" + localElementNumber + " = pivotFactory.createProperty();\n");
		
		// Generate the code that set the name of the property (it is the last element of the path name).
		codeBuilder.addCodeSnippet("property$" + localElementNumber + ".setName(\"" + propertyName + "\");\n");
		
		// Generate the code that sets the type of the property (it is the second parameter).
		codeBuilder.addCodeSnippet("property$" + localElementNumber + ".setType(" + localBuf.toString() + ");\n");
		
		// Generate the code that add the newly created property to the type.
		codeBuilder.addCodeSnippet("propertyType$" + localElementNumber + ".addProperty(property$" + localElementNumber + ");\n");
		
		// The caller gets the property variable.
		buffer.append("property$" + localElementNumber);
		
		// Increment the element number for further code generation.
		elementNumber++;
	}

	public void visitPropertyStaticElement(IPropertyStaticElement node, StringBuffer buffer) throws BuildingASMException {
		// Create a new local buffer.
		StringBuffer localBuf = new StringBuffer();
		
		// Generate code for the property.
		generateCodeForProperty(node, localBuf);
		
		/* 
		 * The local buffer now contains the name of the new property variable.
		 * Now we must generate code that examines whether the found property is static.
		 */
		codeBuilder.addCodeSnippet("if (!(" + localBuf + ".isStatic)) throw new BuildModelException(\"The property isn't static.\");\n");
		
		// The caller gets the same variable name.
		buffer.append(localBuf.toString());

	}

	public void visitRealElement(IRealElement node, StringBuffer buffer) throws BuildingASMException {
		buffer.append(node.getToken().getValue());

	}

	public void visitStringElement(IStringElement node, StringBuffer buffer) throws BuildingASMException {
		buffer.append("\"" + node.getToken().getValue() + "\"");

	}

	public void visitTestSuite(ITestSuite node, StringBuffer buffer) throws BuildingASMException {
		// We initialize the code builder for a new test suite.
		codeBuilder.newTestSuite();
		
		/* 
		 * First we save the package name that is stored in the code builder.
		 * This set us in a situation in where we can restore the old package name. This is useful
		 * because the testsuites/ testcase should inherit the package name from the parent.
		 * If the parent has one package name and the child has also one, we must use the child ones name
		 * and must restore the old.
		 */
		String oldPackageName = (String) codeBuilder.getPackagename();
		
		// We set the old package name to the current.
		String currentPackageName = oldPackageName;
		
		// If the test suite has a package declaration then will we use this.
		if (node.getPackageDeclaration() != null) {
			currentPackageName = node.getPackageDeclaration().getToken().getValue();
		}
		
		/* 
		 * The key musn't be set. So in this case, we set the current package name
		 * to the empty string.
		 */
		if (currentPackageName == null) {
			currentPackageName = new String("");
		}
		
		/* 
		 * The package name is set in the code builder.
		 */
		codeBuilder.setPackagename(currentPackageName);
		
		// We set the name of the test suite in the code builder.
		codeBuilder.setTestsuiteName(node.getToken().getValue());
		
		
		// This list will contain all names of the test classes (testcase or testsuite).
		List<String> testClassNames = new ArrayList<String>();
		for(ITest test : node.getTestElements()) {
			// We get the package declaration of the sub test.
			IPackageDeclaration testPackageDecl = test.getPackageDeclaration();
			
			/*
			 * If the package declaration of the sub test is null then we take
			 * the current package name and set this before the class name of
			 * the sub test.
			 */
			
			String className = null;
			if (testPackageDecl == null) {
				if (currentPackageName.equals("")) {
					className = test.getToken().getValue() + ".class";
				} else {
					className = currentPackageName + "." + test.getToken().getValue() + ".class";
				}
				
			} else { // If the sub test has its own package declaration then we take this as prefix for the class name.
				className = testPackageDecl.getToken().getValue() + "." + test.getToken().getValue() + ".class";
			}
			
			// We add the class name to the code builder.
			codeBuilder.addTestClassName(className);
			
		}
		
		// Generate the code for the testsuite.
		try {
			codeBuilder.generateTestsuite();
		}catch(Exception ex) {
			throw new BuildingASMException("An error occurred while building a test suite. The testsuite was: " + node.getToken().getValue(), ex);
		}
		
		
		
		// Process the tests that are associated with this testsuite.
		for(ITest test: node.getTestElements()) {
			/*
			 *  This actual map is a container for the next test element to transfer the package name
			 *  for the case that the sub test has no package declaration.
			 */
			//actualMap.put("packagename", currentPackageName);
			test.accept(this, new StringBuffer());
		}
		
		// We set in the code builder the old package name to revert the old state.
		codeBuilder.setPackagename(oldPackageName);
	}

	public void visitTestcase(ITestcase node, StringBuffer buffer) throws BuildingASMException {
		// We must tell the code builder that a new test case is build.
		codeBuilder.newTestCase();
		
		String currentPackageName;
		/*
		 * The testcase can have a package declaration. If it has one, we take it and save it in the code builder.
		 *
		 */
		if (node.getPackageDeclaration() != null) {
			currentPackageName = node.getPackageDeclaration().getToken().getValue();
		} else { // No package name exists. In this case we look up in the code builder for one. A parent test suite have set one.
			currentPackageName = codeBuilder.getPackagename();
			if (currentPackageName == null) {
				currentPackageName = new String("");
			}
		}
		codeBuilder.setPackagename(currentPackageName);
		
		// Set the name of the test in the code builder.
		codeBuilder.setTestName(node.getToken().getValue());
		
		// Here we compute the information that is necessary for the metamodel reference.
		node.getMetamodelReference().accept(this, null);
		
		// Here we compute the information that is necessary for the model reference.
		node.getModelReference().accept(this, null);
		
		
		
		/*
		 * Now we have compute all information that are important for the whole
		 * testcase. But we must also compute the information for each of the test-methods,
		 * especially the code. All visit-methods involved in this process add elements
		 * to the map 'actualMap' and to the string buffer 'code'. But the actualMap denotes
		 * currently the testcaseMap but we need a new map for each of the test-methods (ITestcaseElement).
		 * So we create a list for saving all these maps and create a new map for each test-method that
		 * is added to this list.
		 */
		
		// Here we create the list of maps.
		List<HashMap> testcaseElementsMap = new ArrayList<HashMap>();
		
		
		// Here we iterate over all ITestcaseElements (test-methods).		
		for(ITestcaseElement element : node.getTestcaseElements()) {
			// Each testcase element is transformed to a testcase method. So we create one testcase method.
			codeBuilder.newTestMethod();
					
			/*
			 * We must clear the iterated variables. Each test method has its own
			 * variables and these variables can have the same name. With the
			 * iterated variable list we save us all variables that we have visited.
			 * But the new ones wasn't visited so far.
			 */
			iteratedVariables.clear();
			
			/* 
			 * Here we compute the new test-method. This adds code to the
			 * 'code' variable.
			 */
			element.accept(this, null);
		}
		
		/*
		 * Here we try to generate the code for the testcase.
		 */
		try {
			codeBuilder.generateTestcase();
		}catch(Exception ex) {
			throw new BuildingASMException("An error occurred while generating code for a testcase. The testcase name is: " + node.getToken().getValue() + ".", ex);
			
		}
		
	}

	public void visitTestcaseElement(ITestcaseElement node, StringBuffer buffer) throws BuildingASMException {
		codeBuilder.setTestcaseName(node.getToken().getValue());
				
		String transformedOclExpression = BuildingCodeUtilClass.transformStringToExplicitNewLines(node.getOclExpression().getValue());
		codeBuilder.setOclExpression(transformedOclExpression);
		node.getResult().accept(this, null);
	}

	public void visitTypeElement(ITypeElement node, StringBuffer buffer) throws BuildingASMException {
		// Get the only parameter.
		IModelExpression param1 = node.getParameter().get(0);
		
		// If the parameter is a variable, get the last element of the variable chain.
		if (param1 instanceof IVariable) param1 = BuildingCodeUtilClass.transferLastElementOfVariableChainList((IVariable) param1);
		
		// If the last element of the variable chain isn't a string element, it is an error.
		if (!(param1 instanceof IStringElement)) throw new BuildingASMException("The first parameter of a type element must be a string.", param1.getToken());
		
		// Get the token of the string element.
		String paramString = param1.getToken().getValue();
		
		// The string value is path name. So we transform this into a string list.
		List<String> pathName = BuildingCodeUtilClass.transformPathName2List(paramString);
		
		// We save the actual element number.
		int localNumber = elementNumber;
		
		// Add the code to create a string list for the name.
		codeBuilder.addCodeSnippet("List<String> typeName$" + localNumber + " = new ArrayList<String>();\n");
		
		// Add all parts of the path name to the generated list.
		for(String pathElement : pathName) {
			codeBuilder.addCodeSnippet("typeName$" + localNumber +".add(\"" + pathElement + "\");\n");
		}
		
		codeBuilder.addCodeSnippet("\n");
		
		// Generate the code, to find the type in the corresponding model.
		codeBuilder.addCodeSnippet("Type type$" + localNumber + " = null;\n");
		codeBuilder.addCodeSnippet("try {\n");
		codeBuilder.addCodeSnippet("type$" + localNumber + " = referencedModel.getTypeResolver().findType(typeName$" + localNumber + ");\n");
		codeBuilder.addCodeSnippet("} catch(TypeNotFoundException ex) {\n");
		codeBuilder.addCodeSnippet("throw new BuildModelException(\"The type was not found while building the compare model. TypeName: \" + typeName$" + localNumber +");\n");
		codeBuilder.addCodeSnippet("}\n");
		
		// Generate the exception for the case that the type isn't found.
		codeBuilder.addCodeSnippet("if (type$" + localNumber + " == null) throw new BuildModelException(\"The type was not found while building the compare model. TypeName: \" + typeName$" + localNumber +");\n");
		
		codeBuilder.addCodeSnippet("\n");
		
		// The caller gets the type name of the new generated type variables.
		buffer.append("type$" + localNumber);
		
		// Increment the element number for further processing.
		elementNumber++;

	}

	public void visitVariable(IVariable node, StringBuffer buffer) throws BuildingASMException {
		buffer.append(node.getToken().getValue());
		if (!(iteratedVariables.contains(node))) {
			IModelExpression ref = node.getReference();
			StringBuffer localBuf = new StringBuffer();
			ref.accept(this, localBuf);
			/*
			 * Here we add the current variable to the variables
			 * that are already be performed. So the next time we
			 * see this variable, we do nothing.
			 */
			iteratedVariables.add(node);
			
			/*
			 * If we have a list, we must add a generic to the variable definition.
			 */
			if (node.getTypename().equals("List")) {
				IListElement listElement = (IListElement) BuildingCodeUtilClass.transferLastElementOfVariableChainList(node);
				
				// If the list is an array, it must handled be different.
				if (listElement.isArray()) {
					codeBuilder.addVarDeclSnippet(listElement.getFullQualifiedListTypeName() + "[]" + node.getToken().getValue() + " = null;\n");
				} else {
					codeBuilder.addVarDeclSnippet(node.getFullQualifiedTypename() + "<" + listElement.getFullQualifiedListTypeName() + "> " + node.getToken().getValue() + " = null;\n");
				}
				
			} else {
				codeBuilder.addVarDeclSnippet(node.getFullQualifiedTypename() + " " + node.getToken().getValue() + " = null;\n");
			}
			codeBuilder.addCodeSnippet(node.getToken().getValue() + " = " + localBuf + ";\n");
		}

	}
}
