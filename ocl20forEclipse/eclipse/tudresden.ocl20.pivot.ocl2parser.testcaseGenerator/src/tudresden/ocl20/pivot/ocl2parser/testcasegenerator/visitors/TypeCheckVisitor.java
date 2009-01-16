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

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import tudresden.ocl20.pivot.essentialocl.expressions.CollectionKind;
import tudresden.ocl20.pivot.modelbus.IMetamodel;
import tudresden.ocl20.pivot.modelbus.IMetamodelRegistry;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.TypeNotFoundException;
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
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.ITokenAS;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.ITypeElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IVariable;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.util.BuildingASMException;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.util.IEnvironment;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.util.BuildingCodeUtilClass;
import tudresden.ocl20.pivot.pivotmodel.ConstraintKind;
import tudresden.ocl20.pivot.pivotmodel.Enumeration;
import tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.ParameterDirectionKind;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

public class TypeCheckVisitor implements IVisitor {

	public void visitAbstractModel(IAbstractModel node, IEnvironment env) throws BuildingASMException {
		/*
		 *  We only need to traverse the first element - the model element - because at this element
		 *  the whole tree/gaph is hanged.
		 */
		node.getVariables().get(0).accept(this, env);
	}

	public void visitEssentialOclElement(IEssentialOclElement node, IEnvironment env)  throws BuildingASMException{
		// We get the token of this node.
		ITokenAS className = node.getToken();
		
		// In this instance we will save the IModelFactory class instance.
		Class modelFactoryClass = null;
		try {
			// Look up for the IModelFactory class instance.
			 modelFactoryClass = Class.forName("tudresden.ocl20.pivot.modelbus.IModelFactory");
		} catch (ClassNotFoundException e) {
			throw new BuildingASMException("An internal error occurred. The interface IModelFactory was not found.");
		}
		
		// Get all methods of the interface.
		Method[] methods = modelFactoryClass.getMethods();
		
		// Indicate whether a method exist that end on the name of the token value. 
		//boolean compareResult = false;
		
		/*
		 * In this list we save all methods that we have found with the name.
		 */
		List<Method> foundMethods = new ArrayList<Method>();
		
		// Iterate over all interface methods ...
		for(Method me : methods) {
			// ... and get the name of each method.
			String methodName = me.getName();
			
			/* 
			 * Examine whether this method name ends on the token value.
			 * If so, set compareResult to true, because we have
			 * found a method that creates an essential ocl element
			 * with the name that is given from the user. We
			 * leave the loop.
			 */ 
			if (methodName.endsWith(className.getValue())) {
				foundMethods.add(me);
			}
		}
		
		/*
		 * If the comparison was not successful we throw an exception.
		 */
		//if (compareResult == false) throw new BuildingASMException("The essential ocl type doesn't exist.", className);
		if (foundMethods.size() == 0) throw new BuildingASMException("The essential ocl type doesn't exist.", className);
		
		
		/*
		 * We must make the element resolution also on the parameters of the essential
		 * ocl element.
		 */
		for(IModelExpression param: node.getParameter()) {
			param.accept(this, env);
		}
		
		// We keep a successful variable.
		boolean successful = false;
		
		// Is at least one method of the factory valid?
		boolean validMethod = false;
		
		// The error position of the parameter that doesn't match.
		int errorPosition = 0;
		
		/*
		 * We iterate over all methods that was found in the factory.
		 */
		for (Method me : foundMethods) {
			/*
			 * If the number of the parameters of the method and the number of parameter of this
			 * model expression doesn't match, we take the next method.
			 */
			if (me.getParameterTypes().length != node.getParameter().size()) continue;
			
			/*
			 * At least one method can be valid.
			 */
			validMethod = true;
			
			/*
			 * We iterate over all parameters.
			 */
			for(int i = 0; i < node.getParameter().size(); i++) {
				/*
				 * Take the ith parameter of the method.
				 */
				Class parameterType = me.getParameterTypes()[i];
				
				/*
				 * Take the ith parameter of the model expression.
				 */
				IModelExpression exp = node.getParameter().get(i);
				
				/*
				 * The parameter of the model expression can be a list.
				 * If it is a list the corresponding parameter of the method
				 * can be of type Collection or can be an array.
				 */
				if (exp.getTypename().equals("List")) {
					/*
					 * We take the list of the parameter of the model expression.
					 * The parameter can be a variable and we must take the last element
					 * of the variable chain.
					 */
					IListElement list = BuildingCodeUtilClass.transferList(exp);
					
					/*
					 * If the last element of the variable chain isn't a list element
					 * it is very strange.
					 */
					if (list == null) throw new BuildingASMException("An internal occurred. An IModelExpression has typename" +
							" 'List', but at the end no List is found.", node.getToken());
					
					
					/*
					 * Get the full qualified type name of the list.
					 */
					String typeName = list.getFullQualifiedListTypeName();
					
					/*
					 * The parameter of the method denotes an array.
					 */
					if (parameterType.isArray()) {
						// The parameter of the essential ocl factory method is an array, so the list type must also be an array.
						list.setArray(true);
						
						/*
						 * Make a comparison of the type name of the list with the type of the array
						 * (ComponentType).
						 */
						Class typeClass = null;
						try {
							typeClass = Class.forName(typeName);
						}catch(ClassNotFoundException ex) {
							throw new BuildingASMException("A class could not be found. This error is at this" +
									" point very strange because the types are set explicity in the abstract syntax. The class name was: " + typeName);
						}
						if (parameterType.getComponentType().isAssignableFrom(typeClass)) {
							/*
							 * If this comparison is successful we set the successful flag to true.
							 */
							successful = true;
						} else {
							/*
							 * If the comparison isn't successful, we set the successful flag to false,
							 * set the error position to the actual parameter position that we currently
							 * consider and we break the for-loop that iterates over all the parameter.
							 * The remaining parameters don't be consider.
							 */
							successful = false;
							errorPosition = i;
							break;
						}
					} else { // The parameter of the method denotes a Collection.
						Class collectionInterface = null;
						try {
							/*
							 * We search for the collection interface and make a class object.
							 */
							collectionInterface = Class.forName("java.util.Collection");
						} catch(ClassNotFoundException ex) {
							throw new BuildingASMException("An internal occurred while searching for the" +
									" collection interface in the java library. This is very strange because the Collection" +
									" is referenced explicitly.", node.getToken());
						}
						
						/*
						 * Here we check if the type of the parameter of the method is
						 * compatible with the type of the collection type.
						 */
						if (collectionInterface.isAssignableFrom(parameterType)) {
							// The parameter isn't an array.
							list.setArray(false);
							
						} else { // The parameter of the method denotes neither a collection nor an array.
							/*
							 * We set the successful flag to false, the error position to the
							 * actual considered parameter and leave the for-loop that iterates
							 * over all parameters.
							 */
							successful = false;
							errorPosition = i;
							break;
						}
					}
				}else { // The parameter of the model expression in not a list.
					
					/*
					 * Here we must test whether the parameter type of the factory method is supertype of
					 * the parameter type. If so, the type check is successful, otherwise not.
					 * The null element is type conform to each type.
					 */
					
					if (exp.getTypename().equals("null")) {
						successful = true;
						continue;
					}
					
					Class compareType = null;
					if (exp.getTypename().equals("Integer")) {
						compareType = Integer.TYPE;
					} else
					if (exp.getTypename().equals("Real")){
						compareType = Float.TYPE;
					} else {
						try {
							compareType = Class.forName(exp.getFullQualifiedTypename());
						} catch(ClassNotFoundException ex) {
							throw new BuildingASMException("The type " + exp.getFullQualifiedTypename() + " was not found. This error" +
									" is strange because all typenames was explicitly specified in the abstract syntax.");
						
						}
					}
					
					
					
					if (!parameterType.isAssignableFrom(compareType)) {
						successful = false;
						break;
					} else {
						successful = true;
					}
				}
			}
			/*
			 * If at the end of the parameter for-loop the flag successful is set then we have
			 * found a matching method signature and we can leave the for-loop iterating
			 * over all methods.
			 */
			if (successful == true) break;
		}
		
		/*
		 * Here we construct a string that shows which parameters types are valid.
		 */
		String message = new String();
		/*
		 * We iterate over all found methods.
		 */
		for(Method me : foundMethods) {
			/*
			 * We iterate over all parameters of each method.
			 */
			List<String> parameterMessage = new ArrayList<String>();
			for(Class param: me.getParameterTypes()) {
				// Get the type name of the parameter.
				String parameterClassName = param.getName();
				
				// Get the index of the last point in the full qualified typename.
				int lastIndexOfPoint = parameterClassName.lastIndexOf(".");
				
				// Get only the last element of the package name.
				String lastElement = parameterClassName.substring(lastIndexOfPoint+1, parameterClassName.length());
				
				// Add the parameter name to the end of the parameter message.
				parameterMessage.add(lastElement);
			}
						
			message = message + "\nOne possible parameter configuration to this element: " + BuildingCodeUtilClass.concatElements(parameterMessage);
		}
		
		/*
		 *  If no valid method (a method with the same parameter size as the number of
		 *  parameters of the model expression) is found, we throw an exception with the hint
		 *  which parameter types are valid.
		 */
		if (validMethod == false) throw new BuildingASMException("The construct is not valid.\n" + message + "\n", node.getToken());
		
		/*
		 * If the search was not successful we throw an exception that gives the user a hint which parameter
		 * types are valid. And we can give the hint at which position the match doesn't fit.
		 */
		if (successful == false) throw new BuildingASMException("The types of the essential ocl element doesn't match" +
				" with the expected.\n"+message + "\nThe type mismatch occurred at parameter position " + errorPosition + "\n", node.getToken());
		
	}
	
	/**
	 * Returns a list that contains all class and interface types
	 * that the <i>type</i> has. If the <i>type</i> is a root interfaces,
	 * so it has no super interfaces or super class, then the list will
	 * be empty. Note, this method returns always a valid, but maybe empty, list.
	 * @param type the type from which all superclass and superinterfaces will be determined
	 * @return a list with all super interfaces and super class types.
	 */
	public List<Class> collectSuperTypes(Class type) {
		// We create a list.
		List<Class> resultList = new ArrayList<Class>();
		
		// If the type is null, we return the empty list.
		if (type == null) return resultList;
		
		// Otherwise we collect all super interfaces and super classes in the list.
		internalCollectSuperTypes(type, resultList);
		
		// Return the list.
		return resultList;
	}
	
	/**
	 * Collects all super interfaces and super class of <i>type</i> and add
	 * these types to the list <i>typeList</i>.
	 * @param type the type from which to compute all super types
	 * @param typeList the list in that all type will be collected
	 */
	private void internalCollectSuperTypes(Class type, List<Class> typeList) {
		// If the type is null, we do nothing.
		if (type == null) return;
		
		/* 
		 * Get the super class of the type. All
		 * classes in java has the super class 'Object'. If this
		 * class is reached, the 'getSuperClass()'-method
		 * will return null. So at this point the method will called
		 * the last time. That means, it terminates.
		 */
		Class superType = type.getSuperclass();
		
		// Add the given type to the type list.
		typeList.add(type);
		
		// Collect recursively all super class in the list.
		internalCollectSuperTypes(superType, typeList);
		
		
		/*
		 * At this point we collect all interfaces. So get all interface
		 * types of the type.
		 */
		Class[] interfaces = type.getInterfaces();
		
		/*
		 * We transform the array into a list-
		 */
		List<Class> interfaceList = new ArrayList<Class>();
		for(Class intType : interfaces) {
			interfaceList.add(intType);
		}
		
		/*
		 * Interface relations can be cyclic. For example: we have three interfaces
		 * A, B, C that are in the following relationship with each other.
		 * A->B->C->A where 'X->Y' means that 'X implements Y'. To avoid these cycles
		 * we look in our type list. This list contains all types that we have already visited.
		 * We now have a new interfaces list with interfaces that must potentially be added to that
		 * list. But this list can have elements that we have already visited. So we delete these elements.
		 * At the end a list of interfaces results that contains no element that are element of
		 * the type list.
		 */
		for(int i = 0; i < typeList.size(); i++) {
			/*
			 *  We must use our own contains method because the class 'Class'
			 *  compare only on references not on the name.
			 */
			if (containsClass(interfaceList, typeList.get(i))) {
				interfaceList.remove(interfaceList.indexOf(typeList.get(i)));
			}
		}
		
		/*
		 * At this point the interface list has elements that we haven't already visited.
		 * These interfaces will computed recursively.
		 */
		for(Class intType: interfaceList) {
			internalCollectSuperTypes(intType, typeList);
		}
	}
	
	/**
	 * Returns true if the <i>type</i> is element of the <i>list</i>.
	 * An element is type of this list if it there is an class element with the
	 * same name as the <i>type</i>.
	 * If <i>type</i> or <i>list</i> is null, the method will return false.
	 * @param list the list that must be compared for
	 * @param type the type to which searched for
	 * @return true if <i>type</i> is element of that list, otherwise false
	 */
	private boolean containsClass(List<Class> list, Class type) {
		if (type == null) return false;
		if (list == null) return false;
		
		for(Class compareClass : list) {
			if (compareClass.getName().equals(type.getName())) return true;
		}
		
		return false;
	}
	
	public void visitListElement(IListElement node, IEnvironment env)  throws BuildingASMException{
		for(IModelExpression exp : node.getParameter()) {
			exp.accept(this, env);
		}
	}

	/**
	 * Examine whether the namespace - given by the user - exist. If so, the namespace will be set
	 * in the node and the type name. Otherwise an exception is thrown.
	 */
	public void visitNamespaceElement(INamespaceElement node, IEnvironment env) throws BuildingASMException {
		// Get the token.
		ITokenAS value = node.getToken();
		
		for(IModelExpression exp : node.getParameter()) {
			exp.accept(this, env);
		}
		
		if (node.getParameter().size() != 2) throw new BuildingASMException("A namespace must have exactly two parameters.", value);
		
		IModelExpression firstParam = node.getParameter().get(0);
		if (!(firstParam instanceof IStringElement)) throw new BuildingASMException("The first parameter of" +
				" the given namespace must a string that denotes the namespace.", value);
		
		/*
		 * The first parameter contains the referenced namespace name. But the first parameter
		 * can be a variable. And this variable can assigned to another variable and so on.
		 * So we must traverse this chain to the last element that is the string element.
		 */
		IModelExpression exp = firstParam; 
		if (node.getParameter().get(0) instanceof IVariable) exp = BuildingCodeUtilClass.transferLastElementOfVariableChainList((IVariable)firstParam);
		
		/*
		 * We must examine whether the last element of the chain is of type IStringLiteral.
		 * If not, it is an error.
		 */
		if (!(exp instanceof IStringElement)) throw new BuildingASMException("The first paramter must be a string literal.", value);
		
		/*
		 * At this point we know that 'exp' is of type IStringLiteral. So
		 * take the token of this and the value of this token. This represents
		 * the user input, in this case a string that references the namespace.
		 * But the elements of the namespace are separated by the double colon '::'.
		 * So we must convert this representation to a list.
		 * With this list we search in the model for that namespace name.
		 * 
		 */
		List<String> pathName = BuildingCodeUtilClass.transformPathName2List(exp.getToken().getValue());
		
		Namespace rootModelNamespace;
		try {
			rootModelNamespace = env.getModel().getRootNamespace();
		} catch (ModelAccessException e) {
			throw new BuildingASMException("An error occurred while accessing the model. An access to the root namespace wasn't possible.");
		}
		
		/* 
		 * If the user has specified the namespace with the top element of the model then we must
		 * remove this top element name from the path name because of the implementation of the
		 * IModel.foundNamespace method, that searches from the top element.
		 */
		if ((pathName.size() != 0) && (pathName.get(0).equals(rootModelNamespace.getName()))) pathName.remove(0);
		
		// The instance which will eventually represents the denoted namespace.
		Namespace namespace = null;
		try {
			// Try to find the namespace.
			namespace = env.getModel().findNamespace(pathName);
		}catch(ModelAccessException ex) {
			// If an access exception is caught, throw an exception.
			throw new BuildingASMException("An error occurred while accessing the model to find a namespace.", value);
		}
		
		// If the namespace was not found it is an error.
		if (namespace == null) throw new BuildingASMException("The namespace was not found.", value);
	}

	public void visitOperationNewElement(IOperationNewElement node, IEnvironment env) throws BuildingASMException{
		// First we make a type check on all parameters.
		for(IModelExpression param : node.getParameter()) {
			param.accept(this, env);
		}
		
		if (node.getParameter().size() != 2) throw new BuildingASMException("The new operation declaration must have exactly two parameters (String, List<Parameter>).", node.getToken());
		
		/*
		 * The first parameter contains the referenced operation name. But the first parameter
		 * can be a variable. And this variable can assigned to another variable and so on.
		 * So we must traverse this chain to the last element that is the string element.
		 */
		IModelExpression firstParam = node.getParameter().get(0);
		if (!(firstParam.getTypename().equals("String"))) throw new BuildingASMException("The first parameter must be string.", firstParam.getToken());
		
		if (firstParam instanceof IVariable) firstParam = BuildingCodeUtilClass.transferLastElementOfVariableChainList((IVariable)firstParam);
		
		// We get the value of the token.
		String operationValue = firstParam.getToken().getValue();
		
		// We transform the path name into a list of strings.
		List<String> operationName = BuildingCodeUtilClass.transformPathName2List(operationValue);
		
		// If the path name has less than two elements, it denotes not a new operation name.
		if (operationName.size() < 2) throw new BuildingASMException("The new operation name" +
				" must be described by a path name with at least two elements.", firstParam.getToken());
		
		// Get the elements of the list without the last element (this denotes the name of the new operation).
		List<String> typeName = operationName.subList(0, operationName.size()-1);
		
		// Try to lookup for the type in the model.
		Type type;
		try {
			type = env.getModel().findType(typeName);
		} catch (ModelAccessException e) {
			// This exception can occur when a problem is determined while accessing the model.
			throw new BuildingASMException("An error occurred while accessing the model (we have tried to find" +
					" the type of the new operation).", firstParam.getToken());
		}
		
		// If the type was not found, it is an error.
		if (type == null) throw new BuildingASMException("The type was not found to which the new operation should belong.", node.getToken());
		
		IModelExpression secondParam = node.getParameter().get(1);
		if (!(secondParam.getTypename().equals("List"))) throw new BuildingASMException("The second parameter" +
				" of the new operation declaration must be a list of parameters.", secondParam.getToken());
		
		if (secondParam instanceof IVariable) secondParam = BuildingCodeUtilClass.transferLastElementOfVariableChainList((IVariable) secondParam);
		
		IListElement listElement = (IListElement) secondParam;
		
		// The list for the parameters is a java.util.List.
		listElement.setArray(false);
		
		if (!(listElement.getListTypename().equals("Parameter"))) throw new BuildingASMException("The type of the list must be Parameter.", secondParam.getToken());
		
	}

	public void visitOperationStaticElement(IOperationStaticElement node, IEnvironment env) throws BuildingASMException {
		Operation oper = typeCheckOperation(node, env);
		
		/*
		 *  Finally we must examine whether the operation is static, because with the construct 'Operation(...)' in the
		 *  specification file we denote solely static operations. 
		 */
		
		if (!(oper.isStatic())) throw new BuildingASMException("An operation with the given signature was found but it is not static.", node.getToken());
		
	}
	
	public void visitOperationElement(IOperationElement node, IEnvironment env) throws BuildingASMException {
		typeCheckOperation(node, env);
	}
	
	/**
	 * This method makes a type check on an operation element. The examination whether the operation is static is outsourced
	 * the appropriate method.
	 * @param node the node to examine
	 * @param env the environment
	 * @return Operation if one is found
	 * @throws BuildingASMException is thrown if the <i>node</i> parameter is not of type IOperationElement or IOperationStaticElement
	 * or if any other error occurred while determining if the operation is found in the model 
	 */
	private Operation typeCheckOperation(IPivotmodelReferenceElement node, IEnvironment env) throws BuildingASMException {
		if (!(node instanceof IOperationElement) || (node instanceof IOperationStaticElement)) throw new BuildingASMException("An" +
				" internal error occurred. This method must have called only with a IOperationElement or IOperationStaticElement instance.");
		
		for(IModelExpression param : node.getParameter()) {
			param.accept(this, env);
		}
		
		// The operation element must have exactly two parameters.
		if (node.getParameter().size() != 2) throw new BuildingASMException("A static operation reference must have exactly two parameters.", node.getToken());
		
		/*
		 * The first parameter contains the referenced operation name. But the first parameter
		 * can be a variable. And this variable can assigned to another variable and so on.
		 * So we must traverse this chain to the last element that is the string element.
		 */
		IModelExpression firstParam = node.getParameter().get(0);
		if (firstParam instanceof IVariable) firstParam = BuildingCodeUtilClass.transferLastElementOfVariableChainList((IVariable)firstParam);
		
		/*
		 * We must examine whether the last element of the chain is of type IStringElement. If not
		 * it is an error.
		 */
		if (!(firstParam instanceof IStringElement)) throw new BuildingASMException("The first parameter must be of type string.", node.getToken());
		
		// The second parameter must be a list.
		IModelExpression secondParam = node.getParameter().get(1);
		if (!(secondParam.getTypename().equals("List") || secondParam.getTypename().equals("null"))) throw new BuildingASMException("The second parameter must be a list of types or null.", node.getToken());
		
		/*
		 * If the second parameter is null, we replace this parameter through an empty list.
		 */
		if (secondParam.getTypename().equals("null")) {
			// Create the new list element.
			IListElement nullList = env.getFactory().createListElement(secondParam.getToken());
			
			// The list of parameters must be a java.util.List.
			nullList.setArray(false);
			
			// Add an empty parameter list to the list.
			nullList.setParameter(new ArrayList<IModelExpression>());
			
			// Set the null list in the node.
			node.setParameter(nullList, 1);
			
			// The second parameter is the null list.
			secondParam = nullList;
		}
		// If the second parameter is a variable we get the last element of the variable chain.
		if (secondParam instanceof IVariable) secondParam = BuildingCodeUtilClass.transferLastElementOfVariableChainList((IVariable) secondParam);
		
		// The last element of the variable chain must be a list.
		if (!(secondParam instanceof IListElement)) throw new BuildingASMException("This error is very strange" +
				" because a variable says that it has a list but at the end of this variable (list) there is no list.", node.getToken());
		
		// We convert the second parameter to a list element.
		IListElement typeList = (IListElement) secondParam;
		
		// The type list of the operation must be java.util.List.
		typeList.setArray(false);
		
		/*
		 * The type check on the second parameter makes only sense
		 * if the type list contains at least one element.
		 */
		if (typeList.getParameter().size() != 0) {
			/*
			 * The list must have as list type the type interface or a sub type of the type interface.
			 * To determine this we load the type interface of the pivotmodel, then the list type and
			 * then we examine whether the type interface is super interface of the list type.
			 */
			Class typeClass = null;
			try {
				// We try to get the type interface.
				typeClass = Class.forName("tudresden.ocl20.pivot.pivotmodel.Type");
			}catch(ClassNotFoundException ex) {
				/*
				 *  If we can't get the type interface, it is strange. One possible explanation can be
				 *  that the plugin is unable to access the pivot model package.
				 */
				throw new BuildingASMException("We have tried to load the 'Type' interface of the pivotmodel," +
						" but this fails. This is strange because the interface location was set explicitly. One" +
						" explanation can be that the plugin is unable to access the pivot model package.", node.getToken());
			}
			
			Class listTypeClass = null;
			try {
				/*
				 * The list element is tried to determine the common super type of all
				 * types in the list. But this can be failed. So null will returned by the
				 * getTypeListName-methods. 
				 */
				String fullQualifiedTypeListName = typeList.getFullQualifiedListTypeName();
				if (fullQualifiedTypeListName == null) throw new BuildingASMException("It was unable to determine a common super type of the list (second parameter).", node.getToken());
				
				// Here we try to get the list type.
				listTypeClass = Class.forName(fullQualifiedTypeListName);
			}catch(ClassNotFoundException ex) {
				/*
				 *  If we can't get the type interface, it is strange, because all parameter
				 *  types was computed so far recursively. So the types must exist. Then the
				 *  common super type of all these types is computed in the IListElement. We have
				 *  already examined whether a common super types exists or not. 
				 */
				throw new BuildingASMException("We have tried to load the common super type of the list" +
						" but this fails. This is strange because we have already done this loading.", node.getToken());
			}
			
			// Here we examine whether the type interface is super type of the list type. 
			if (!(typeClass.isAssignableFrom(listTypeClass))) throw new BuildingASMException("All elements of the list (second parameter) must be a type.", node.getToken());
		}
		
		
		
		/*
		 * At this point we try to extract all types of the parameter list. All elements
		 * of the list must be of type ITypeElement.
		 */
		
		// A list that will hold the parameters types.
		List<Type> parameterTypes = new ArrayList<Type>();
		
		// We iterate over all elements of the list.
		for(int i = 0; i < typeList.getParameter().size(); i++) {
			// Get the ith element of the list.
			IModelExpression exp = typeList.getParameter().get(i);
			
			// If the element is a variable get the last element of the variable chain.
			if (exp instanceof IVariable) exp = BuildingCodeUtilClass.transferLastElementOfVariableChainList((IVariable) exp);
			
			// The last element of the variable chain must be a type.
			if (!(exp instanceof ITypeElement)) throw new BuildingASMException("The parameter denotes not a" +
					" type (it is used in the expression with the value "+ node.getToken().getValue() + " at line "
					+ node.getToken().getLine() + " and column " + node.getToken().getColumn() +".", exp.getToken());
			
			// Make a cast.
			ITypeElement typeParam = (ITypeElement) exp;
			
			/* 
			 * Get the type name that is associated the type element.
			 */
			List<String> typeName = BuildingCodeUtilClass.transformPathName2List(typeParam.getToken().getValue());
			
			/*
			 * We look for the type in the model.
			 * Note: we know at this point that the type must exist because
			 * we make the type check recursively on the parameters so far
			 * (see the first lines of this method).
			 */
			Type foundType = null;
			try {
				foundType = env.getModel().findType(typeName);
			} catch (ModelAccessException e) {
				throw new BuildingASMException("An error occurred while accessing the model.", node.getToken());
			}
			
			// At the found type to the parameter list.
			parameterTypes.add(foundType);
		}
		
		/*
		 * Now we must care about the operation name that is given by the user.
		 * First we must determine the type of the operation and then we
		 * look in this type for the operation name.
		 */
		
		// Transform the token value into a list of strings.
		List<String> operationName = BuildingCodeUtilClass.transformPathName2List(firstParam.getToken().getValue());
		
		// The operation name must have at least two elements.
		if (operationName.size() < 2) throw new BuildingASMException("The full qualified operation name must have at least two elements.", node.getToken());
		
		// Take the first elements of the list without the last element. These elements denotes the type to which the operation belongs.
		List<String> operationTypeName = operationName.subList(0, operationName.size()-1);
		
		// Try to find the type.
		Type operationType = null;
		try {
			operationType = env.getModel().findType(operationTypeName);
		} catch (ModelAccessException e) {
			throw new BuildingASMException("An error occurred while accessing the model.", node.getToken());
		}
		
		// If the type of the operation was not found, it is an error.
		if (operationType == null) throw new BuildingASMException("The type of the static operation was not found (first parameter).", node.getToken());
		
		/*
		 * Finally we have ingredients altogether to look up for the operation:
		 * the type, the operation name and the type list of the parameters.
		 * 
		 */
		
		// Look up for the operation with the given name and the parameter types.
		Operation oper = operationType.lookupOperation(operationName.get(operationName.size()-1), parameterTypes);
		
		// If no such operation exists it is an error.
		if (oper == null) throw new BuildingASMException("The static operation was not found.", node.getToken());
		
		return oper;
	}
	
	

	/**
	 * We make the type check of the parameter element. A parameter has exactly three
	 * parameters. The first specifies the name of the parameter, the second the kind of
	 * the parameter and the third specifies the type.
	 */
	public void visitParameterElement(IParameterElement node, IEnvironment env) throws BuildingASMException {
		/*
		 * First we iterate over all parameters of this element.
		 */
		for(IModelExpression param: node.getParameter()) {
			param.accept(this, env);
		}
		
		// The parameter element must have exactly three parameters.
		if (node.getParameter().size() != 3) throw new BuildingASMException("A parameter must have exactly three parameters.", node.getToken());
		
		// The first parameter must denote a string.
		IModelExpression firstParam = node.getParameter().get(0);
		if (!(firstParam.getTypename().equals("String") || firstParam.getTypename().equals("null"))) throw new BuildingASMException("The first parameter must be of type string.", node.getToken());
		
		// The second parameter must denote the kind.
		IModelExpression secondParam = node.getParameter().get(1);
		
		/*
		 * It is possible to write a simple string instead of the ParameterKind
		 * element in the concrete syntax. This string must packed into a
		 * ParameterKindElement.
		 */
		if (secondParam.getTypename().equals("String")) {
			/*
			 * Here we create the new parameter kind element.
			 */
			IParameterKindElement parameterKindElement = env.getFactory().createParameterKindElement(secondParam.getToken());
			
			// The parameter kind element need a list of parameters.
			List<IModelExpression> paramList = new ArrayList<IModelExpression>();
			
			// The second parameter can be string element.
			if (secondParam instanceof IStringElement) {
				// We must replace the old parameter through the new one
				node.setParameter(parameterKindElement, 1);
				
				// Add the original string element as the parameter of the parameter kind element.
				paramList.add(secondParam);
				
				// Set the list of the parameter kind element.
				parameterKindElement.setParameter(paramList);
				
				// The second parameter is now the parameter kind element.
				secondParam = parameterKindElement;
			} else
				// The second parameter can also be a variable.
				if (secondParam instanceof IVariable) {
					// In this case we get the last variable of a possible variable chain.
					IVariable lastVariable = BuildingCodeUtilClass.transferLastVariableOfVariableChainList((IVariable) secondParam);
					
					// Get the last element of the variable chain.
					IModelExpression lastElement = lastVariable.getReference();
					
					// Set this last element as the parameter of the parameter kind element.
					paramList.add(lastElement);
					
					// Set the parameter list of the parameter kind element.
					parameterKindElement.setParameter(paramList);
					
					// And then we replace the reference through the new parameter kind element. 
					lastVariable.setReference(parameterKindElement);
				}
			
			// The parameter kind element must be checked because the user can give an invalid parameter kind.
			parameterKindElement.accept(this, env);
		}
		if (!(secondParam.getTypename().equals("ParameterDirectionKind"))) throw new BuildingASMException("The second parameter" +
				" must specify the kind of the parameter.", node.getToken());
		
		// The third parameter must denote the type.
		IModelExpression thridParam = node.getParameter().get(2);
		if (!(thridParam.getTypename().equals("Type"))) throw new BuildingASMException("The thrid parameter must specify a type.");
	}

	/**
	 * Look up for the parameter kind element given by the user. If the element is valid,
	 * the type name of the node and the kind attribute will be set, otherwise a {@link BuildingASMException}
	 * will be thrown.
	 */
	public void visitParameterKindElement(IParameterKindElement node, IEnvironment env) throws BuildingASMException {
		// Get the token.
		ITokenAS value = node.getToken();
		
		// The parameter kind element must have exactly one parameter.
		if (node.getParameter().size() != 1) throw new BuildingASMException("The parameter kind must have exactly one parameter.", value);
		
		// Get this parameter.
		IModelExpression exp = node.getParameter().get(0);
		
		// If the parameter is a variable then we transfer the last element of the variable chain to exp.
		if (exp instanceof IVariable) exp = BuildingCodeUtilClass.transferLastElementOfVariableChainList((IVariable)exp);
		
		// The parameter must be of type string.
		if (!(exp instanceof IStringElement)) throw new BuildingASMException("The parameter must be of type string.", value);
		
		// Try to look up the parameter kind given by the user.
		ParameterDirectionKind kind = ParameterDirectionKind.get(exp.getToken().getValue().toLowerCase());
		
		// If this is not found, throw an exception.
		if (kind == null) throw new BuildingASMException("The parameter direction kind doesn't exist.", value);
	}

	public void visitPropertyNewElement(IPropertyNewElement node, IEnvironment env) throws BuildingASMException {
		// Get the token of the node.
		ITokenAS value = node.getToken();
		
		/*
		 * We must traverse all parameters of the static property reference.
		 * Note that we make here no type check with the qualifier elements.
		 */
		for(IModelExpression elem: node.getParameter()) {
			elem.accept(this, env);
		}
		
		/*
		 * The static property reference must have exactly two parameters.
		 */
		if(node.getParameter().size() != 2) throw new BuildingASMException("The new property must have exactly two parameters (a name and a type).", value);
		
		/*
		 * The first parameter must denote a string literal.
		 */
		IModelExpression firstParam = node.getParameter().get(0);
				
		/*
		 * The first parameter contains the referenced property name. But the first parameter
		 * can be a variable. And this variable can assigned to another variable and so on.
		 * So we must traverse this chain to the last element that is the string element.
		 */
		IModelExpression exp = firstParam;
		if(firstParam instanceof IVariable) exp = BuildingCodeUtilClass.transferLastElementOfVariableChainList((IVariable) firstParam);
		
		/*
		 * We must examine whether the last element of the chain is of type IStringElement.
		 */
		if (!(exp instanceof IStringElement)) throw new BuildingASMException("The property reference must have a string literal as parameter.", value);
		
		/*
		 * The second parameter must be a type.
		 */
		IModelExpression secondParam = node.getParameter().get(1);
		if (secondParam instanceof IVariable) secondParam = BuildingCodeUtilClass.transferLastElementOfVariableChainList((IVariable)secondParam);
		
		if (!(secondParam instanceof ITypeElement)) throw new BuildingASMException("The second parameter must specify a type.", value);
		
		/*
		 * At this point we know that we have a IStringElement instance, but the token value of this
		 * instance must be converted to a list of strings because the value is a list of separated
		 * string literals by the double colon '::'.
		 */
		List<String> propertyName = BuildingCodeUtilClass.transformPathName2List(exp.getToken().getValue());
		
		// The pathname part that represents the type name.
		List<String> pathNameType = null;
		try {
			/* 
			 * The type name is the part of the whole pathname but without the last element
			 * that denotes the property name.
			 */
			pathNameType = propertyName.subList(0, propertyName.size()-1);
		}catch(IndexOutOfBoundsException ex) {
			// The case can happen if the value string has the length 0.
			throw new BuildingASMException("A property reference must have at least two elements.", value);
		}
		
		/*
		 * If the pathname type has the length 0, then the user has give us an incomplete
		 * property name.
		 */ 
		if (pathNameType.size() == 0) throw new BuildingASMException("A property path name must have at least two elements.", value);
		
		// The instance that will hold the found property.
		Property prop = null;
		try {
			// Look up for the type in the model.
			Type type = env.getModel().findType(pathNameType);
			
			// The type was not found.
			if (type == null) throw new BuildingASMException("The type of this property was not found.", value);
			
			// Look for the property in the founded type.
			prop = type.lookupProperty(propertyName.get(propertyName.size()-1));
		} catch (ModelAccessException e) {
			throw new BuildingASMException("The property was not found in the model.", value, e);
		}
		
		// The property was not found.
		if (prop != null) throw new BuildingASMException("The property already exists. So it can't be added to the type.", value);
	}
	
	private Property typeCheckProperty(IPivotmodelReferenceElement node, IEnvironment env) throws BuildingASMException {
		// Get the token of the node.
		ITokenAS value = node.getToken();
		
		/*
		 * We must traverse all parameters of the static property reference.
		 * Note that we make here no type check with the qualifier elements.
		 */
		for(IModelExpression elem: node.getParameter()) {
			elem.accept(this, env);
		}
		
		/*
		 * The static property reference must have exactly one parameter.
		 */
		if(node.getParameter().size() != 1) throw new BuildingASMException("The static property reference must have exactly one parameter.", value);
		
		/*
		 * The first parameter contains the referenced property name. But the first parameter
		 * can be a variable. And this variable can assigned to another variable and so on.
		 * So we must traverse this chain to the last element that is the string element.
		 */
		IModelExpression exp = node.getParameter().get(0);
		if(exp instanceof IVariable) exp = BuildingCodeUtilClass.transferLastElementOfVariableChainList((IVariable) exp);
		
		/*
		 * We must examine whether the last element of the chain is of type IStringElement.
		 */
		if (!(exp instanceof IStringElement)) throw new BuildingASMException("The property reference must have a string literal as parameter.", value);
		
		
		/*
		 * At this point we know that we have a IStringElement instance, but the token value of this
		 * instance must be converted to a list of strings because the value is a list of separated
		 * string literals by the double colon '::'.
		 */
		List<String> propertyName = BuildingCodeUtilClass.transformPathName2List(exp.getToken().getValue());
		
		// The pathname part that represents the type name.
		List<String> pathNameType = null;
		try {
			/* 
			 * The type name is the part of the whole pathname but without the last element
			 * that denotes the property name.
			 */
			pathNameType = propertyName.subList(0, propertyName.size()-1);
		}catch(IndexOutOfBoundsException ex) {
			// The case can happen if the value string has the length 0.
			throw new BuildingASMException("A property reference must have at least two elements.", value);
		}
		
		/*
		 * If the pathname type has the length 0, then the user has give us an incomplete
		 * property name.
		 */ 
		if (pathNameType.size() == 0) throw new BuildingASMException("A property reference must have at least two elements.", value);
		
		// The instance that will hold the found property.
		Property prop = null;
		try {
			// Look up for the type in the model.
			Type type = env.getModel().findType(pathNameType);
			
			// The type was not found.
			if (type == null) throw new BuildingASMException("The type of this property was not found.", value);
			
			// Look for the property in the founded type.
			prop = type.lookupProperty(propertyName.get(propertyName.size()-1));
		} catch (ModelAccessException e) {
			throw new BuildingASMException("The property was not found in the model.", value, e);
		}
		
		// The property was not found.
		if (prop == null) throw new BuildingASMException("The property was not found in the model.", value);
		
		return prop;
	}
	
	public void visitPropertyElement(IPropertyElement node, IEnvironment env) throws BuildingASMException {
		typeCheckProperty(node, env);
	}

	/**
	 * Look up for the property that is given by the user. If the property is found,
	 * set it in the node, otherwise throw a {@link BuildingASMException}.
	 */
	public void visitPropertyStaticElement(IPropertyStaticElement node, IEnvironment env) throws BuildingASMException {
		Property prop = typeCheckProperty(node, env);
		
		// The property must be static.
		if (!prop.isStatic()) throw new BuildingASMException("The addressed property must be static.", node.getToken());
	}

	public void visitRealElement(IRealElement node, IEnvironment env) throws BuildingASMException {
		ITokenAS realToken = node.getToken();
		
		try {
			Float.valueOf(realToken.getValue());
		} catch(NumberFormatException ex) {
			throw new BuildingASMException("The value isn't a real/float value.", realToken);
		}
		return;
	}

	/**
	 * Iterate over all tests and make the element resolution. Note that
	 * this can only be done after executing the cycle checking and the
	 * resolution of the testcase/ testsuites. A test suite references to
	 * a list of files. This references are only a list of strings when
	 * the parser built the graph. This strings must converted to the elements
	 * of type ITest. After that this visitor can be executed.
	 */
	public void visitTestSuite(ITestSuite node, IEnvironment env) throws BuildingASMException {
		for(ITest test : node.getTestElements()) {
			test.accept(this, env);
		}
	}

	/**
	 * Iterate over all testcase elements and make the element resolution.
	 */
	public void visitTestcase(ITestcase node, IEnvironment env) throws BuildingASMException {
		node.getMetamodelReference().accept(this, env);
		node.getModelReference().accept(this, env);
		for(ITestcaseElement elem : node.getTestcaseElements()) {
			elem.accept(this, env);
		}
	}

	/**
	 * Perform the element resolution on the result element.
	 */
	public void visitTestcaseElement(ITestcaseElement node, IEnvironment env) throws BuildingASMException {
		node.getResult().accept(this, env);
	}

	/**
	 * Look up for the type given by the user. If the type is found, it will be set in the
	 * node and the type name of the node will be set to 'Type'. If the type isn't found,
	 * a {@link BuildingASMException} will be thrown.
	 */
	public void visitTypeElement(ITypeElement node, IEnvironment env) throws BuildingASMException {
		// Get the token.
		ITokenAS value = node.getToken();
		
		if (node.getParameter().size() != 1) throw new BuildingASMException("The type reference must have exactly one parameter (that is a string literal).", value);
		
		IModelExpression firstParam = node.getParameter().get(0);
		
		/*
		 * If the first parameter is variable then we must get the
		 * last element of the chain.
		 */
		IModelExpression exp = firstParam;
		if (firstParam instanceof IVariable) exp = BuildingCodeUtilClass.transferLastElementOfVariableChainList((IVariable)firstParam);
		
		/*
		 * If the last element of the chain isn't a string literal, it is an error.
		 */
		if (!(exp instanceof IStringElement)) throw new BuildingASMException("The first parameter must reference a string.", value);
		
		// Transform the string of the token into a path list.
		List<String> pathName = BuildingCodeUtilClass.transformPathName2List(exp.getToken().getValue());
		
		// The instance that will hold the type.
		Type type = null;
		try {
			// Look up for the type.
			type = env.getModel().getTypeResolver().findType(pathName);
		}catch(ModelAccessException ex) {
			// Throw an exception if an error occurred.
			throw new BuildingASMException("An error occurred while accessing the model. Maybe the type is not found.", value);
		} catch(TypeNotFoundException ex) {
			throw new BuildingASMException("The type was not found.", exp.getToken());
		}
		
		// The type was not found, throw an exception.
		if (type == null) throw new BuildingASMException("The type was not found.", exp.getToken());
	}

	/**
	 * Process the variable.
	 */
	public void visitVariable(IVariable node, IEnvironment env) throws BuildingASMException {
		// Make an element resolution on the expression that is hold by this variable.
		node.getReference().accept(this, env);
	}

	public void visitAsSetElement(IAsSetElement node, IEnvironment env)	throws BuildingASMException {
		// The asSet element has exactly one parameter.
		if (node.getParameter().size() != 1) throw new BuildingASMException("The asSet element must have exactly one parameter.", node.getToken());
		
		// Get the parameter.
		IModelExpression firstParam = node.getParameter().get(0);
		
		if (firstParam instanceof IVariable) firstParam = BuildingCodeUtilClass.transferLastElementOfVariableChainList((IVariable)firstParam);
		
		/*
		 * The asSet element must have an essential ocl element as type, with the following exceptions:
		 * Variable, CollectionRange, CollectionItem, ExpressionInOcl and TupleLiteralPart.
		 */
		if (!(firstParam instanceof IEssentialOclElement)) throw new BuildingASMException("The first parameter" +
				" must specify an element of the essential ocl.", node.getToken());
		
		if (firstParam.getTypename().equals("Variable")) throw new BuildingASMException("It is not allowed to set a variable here.", node.getToken());
		
		if (firstParam.getTypename().equals("CollectionItem")) throw new BuildingASMException("It is not allowed to set a collection item here.", node.getToken());
		
		if (firstParam.getTypename().equals("CollectionRange")) throw new BuildingASMException("It is not allowed to set a collection range here.", node.getToken());
		
		if (firstParam.getTypename().equals("ExpressionInOcl")) throw new BuildingASMException("It is not allowed to set a ExpressionInOcl here.", node.getToken());
		
		if (firstParam.getTypename().equals("TupleLiteralPart")) throw new BuildingASMException("It is not allowed to set a TupleLiteralPart item here.", node.getToken());
		
		
		for(IModelExpression param: node.getParameter()) {
			param.accept(this, env);
		}
		
	}

	public void visitCollectionKindElement(ICollectionKindElement node, IEnvironment env) throws BuildingASMException {
		// Get the token for error handling.
		ITokenAS value = node.getToken();
		
		// The collection kind element must have exactly one parameter.
		if (node.getParameter().size() != 1) throw new BuildingASMException("The collection kind must have exactly one parameter.", value);
		
		// Get the single parameter.
		IModelExpression firstParam = node.getParameter().get(0);
		
		// If the parameter is a variable, we must get the last element of the chain.
		if (firstParam instanceof IVariable) firstParam = BuildingCodeUtilClass.transferLastElementOfVariableChainList((IVariable) firstParam);
		
		// If the last element of the chain isn't a string, it is an error.
		if (!(firstParam instanceof IStringElement)) throw new BuildingASMException("The parameter must be a string.");
		
		// We try to convert the string into a real literal.
		CollectionKind kind = CollectionKind.get(firstParam.getToken().getValue());
		
		// If the literal isn't found, it is an error.
		if (kind == null) throw new BuildingASMException("The collection kind type doesn't exit.", value);
	}

	public void visitConstraintElement(IConstraintElement node, IEnvironment env) throws BuildingASMException {
		// First we iterate over all parameters.
		for(IModelExpression param: node.getParameter()) {
			param.accept(this, env);
		}
		
		// The constraint element must have exactly five parameters.
		if (node.getParameter().size() != 5) throw new BuildingASMException("The constraint must have exactly five parameters.", node.getToken());
		
		/*
		 * In the following we examine each of the parameters.
		 */
		
		// Get the first parameter.
		IModelExpression firstParam = node.getParameter().get(0);
		
		// The first parameter can be a string or null.
		if (!(firstParam.getTypename().equals("String") || firstParam.getTypename().equals("null"))) throw new BuildingASMException("The first" +
				" parameter must be a string or null.", node.getToken());
		
		// Get the second parameter.
		IModelExpression secondParam = node.getParameter().get(1);
		
		/*
		 * It is possible to write a simple string instead of the ConstraintKind
		 * element in the concrete syntax. This string must packed into a
		 * ConstraintKindElement.
		 */
		if (secondParam.getTypename().equals("String")) {
			/*
			 * Here we create the new constraint kind element.
			 */
			IConstraintKindElement constraintKindElement = env.getFactory().createConstraintKindElement(secondParam.getToken());
			
			// The constraint kind element need a parameter list.
			List<IModelExpression> paramList = new ArrayList<IModelExpression>();
			
			// The second parameter can be string element.
			if (secondParam instanceof IStringElement) {
				// We must replace the old parameter through the new one
				node.setParameter(constraintKindElement, 1);
				
				// We add the old string element to the parameter list of the constraint kind element.
				paramList.add(secondParam);
				
				// Set the parameter list of the constraint kind element.
				constraintKindElement.setParameter(paramList);
				
				// The second parameter is now the constraint kind element.
				secondParam = constraintKindElement;
			} else
				// The second parameter can also be a variable.
				if (secondParam instanceof IVariable) {
					// In this case we get the last variable of a possible variable chain.
					IVariable lastVariable = BuildingCodeUtilClass.transferLastVariableOfVariableChainList((IVariable) secondParam);
					
					// Get the last element of the variable chain.
					IModelExpression lastElement = lastVariable.getReference();
					
					// Add this element to the parameter list of the constraint kind element.
					paramList.add(lastElement);
					
					// Set the parameter list of the constraint kind.
					constraintKindElement.setParameter(paramList);
					
					// And then we replace the reference through the new constraint kind element. 
					lastVariable.setReference(constraintKindElement);
				}
			
			// The constraint kind element must be checked because the user can give an invalid constraint kind.
			constraintKindElement.accept(this, env);
		}
		// The second parameter must be a constraint kind.
		if (!(secondParam.getTypename().equals("ConstraintKind"))) throw new BuildingASMException("The second parameter" +
				" must specify the constraint kind.", node.getToken());
		
		/*
		 * The third parameter must be of type Expression a super interface.
		 * So we take this super interface and make the decision whether the
		 * given type (from the user) is a subtype of Expression.
		 */
		
		// Get the third parameter.
		IModelExpression thirdParam = node.getParameter().get(2);
		
		// Get the full name of the type given by the user.
		String fullTypename = thirdParam.getFullQualifiedTypename();
		
		/*
		 *  Try to find this type. This must be correct because we have
		 *  already visited the parameters.
		 */
		Class typeClass = null;
		try {
			typeClass = Class.forName(fullTypename);
		}catch(ClassNotFoundException ex) {
			throw new BuildingASMException("The class was not found: " + fullTypename, node.getToken());
		}
		
		// Search for the expression interface.
		Class expressionType = null;
		try {
			expressionType = Class.forName("tudresden.ocl20.pivot.pivotmodel.Expression");
		}catch(ClassNotFoundException ex) {
			throw new BuildingASMException("The Expression interface was not found. Normally," +
					" this error can't happen because the Expression interface was set explicitly." +
					" So one reason for this can be that the package tudresden.ocl20.pivot.pivotmodel" +
					" can't be accessed.", node.getToken());
		}
		
		// Here we look whether the expression interface is a super type of the given type.
		if (!(expressionType.isAssignableFrom(typeClass))) throw new BuildingASMException("The third parameter must" +
				" be of type expression/ExpressionInOcl.", node.getToken());
				
		// Get the fourth parameter.
		IModelExpression fourthParam = node.getParameter().get(3);
		
		// If the parameter is a variable, so we take the last element of this variable chain.
		if (fourthParam instanceof IVariable) fourthParam = BuildingCodeUtilClass.transferLastElementOfVariableChainList((IVariable) fourthParam);
		
		// The last element must be a list.
		if (!(fourthParam.getTypename().equals("List"))) throw new BuildingASMException("The fourth parameter must be a list.", node.getToken());
		
		/*
		 * This listtype must be a subtype of the interface ConstrainableElement.
		 * So we take the full list typename, look for the class object. Then we
		 * look for the ConstrainableElement interface. Finally we examine whether the
		 * type given by the user is a subtype of ConstainableElement.
		 */
		
		IListElement listElement = (IListElement) fourthParam;
		
		// The list of the constrainable elements must be an array.
		listElement.setArray(true);
		
		// Get the full list typename.
		String listTypename = listElement.getFullQualifiedListTypeName();
		
		/* 
		 * Try to look for the type. This is correct because we have this
		 * already examined (parameters are computed so far).
		 */ 
		Class listTypeClass = null;
		try {
			listTypeClass = Class.forName(listTypename);
		}catch(ClassNotFoundException ex) {
			throw new BuildingASMException("The class was not found: " + listTypename, node.getToken());
		}
		
		// Take the ConstrainableElement interface.
		Class constrainableElementClass = null;
		try {
			constrainableElementClass = Class.forName("tudresden.ocl20.pivot.pivotmodel.ConstrainableElement");
		}catch(ClassNotFoundException ex) {
			throw new BuildingASMException("The ConstrainableElement interface was not found. Normally," +
					" this error can't happen because the ConstrainableElement interface was set explicitly." +
					" So one reason for this can be that the package tudresden.ocl20.pivot.pivotmodel" +
					" can't be accessed.", node.getToken());
		}
		
		// Examine whether the ConstrainableElement interface is a super type of the user type.
		if (!(constrainableElementClass.isAssignableFrom(listTypeClass))) throw new BuildingASMException("The fourth parameter must be a list of" +
				" a constrainable element (Type, Property, Operation).", node.getToken());
		
		// Get the fifth parameter.
		IModelExpression fifthParam = node.getParameter().get(4);
		
		// If the parameter is a variable, we get the last element of this chain.
		if (fifthParam instanceof IVariable) fifthParam = BuildingCodeUtilClass.transferLastElementOfVariableChainList((IVariable) fifthParam);
		
		// The last element must be a new property or a new operation.
		if (!((fifthParam instanceof IPropertyNewElement) || (fifthParam instanceof IOperationNewElement) || (fifthParam instanceof INullElement))) throw new BuildingASMException("The" +
				" fifth parameter must be a new operation or a new property.", node.getToken());
		
		
	}

	public void visitConstraintKindElement(IConstraintKindElement node, IEnvironment env) throws BuildingASMException {
		ITokenAS value = node.getToken();
		
		// The constraint kind element must have exactly one parameter.
		if (node.getParameter().size() != 1) throw new BuildingASMException("The constraint kind must have exactly one parameter.", value);
		
		// Get the single parameter.
		IModelExpression firstParam = node.getParameter().get(0);
		
		// If the parameter is a variable, we get the last element of the variable chain.
		if (firstParam instanceof IVariable) firstParam = BuildingCodeUtilClass.transferLastElementOfVariableChainList((IVariable) firstParam);
		
		// The last parameter must be a string.
		if (!(firstParam instanceof IStringElement)) throw new BuildingASMException("The parameter must be a string.", value);
		
		// Try  to convert this string into a literal.
		ConstraintKind kind = ConstraintKind.get(firstParam.getToken().getValue().toLowerCase());
		
		// If this fails, the literal dosn't exist.
		if (kind == null) throw new BuildingASMException("The constraint kind type doesn't exist", value);
	}

	public void visitIntegerElement(IIntegerElement node, IEnvironment env)
			throws BuildingASMException {
		ITokenAS integerValue = node.getToken();
		try {
			Integer.valueOf(integerValue.getValue());
		} catch(NumberFormatException ex) {
			throw new BuildingASMException("An internal ocurred. The integer value isn't an integer.", integerValue);
		}
		return;
		
	}

	public void visitEnumerationLiteralElement(IEnumerationLiteralElement node,	IEnvironment env) throws BuildingASMException {
		// Get the token of the node.
		ITokenAS value = node.getToken();
		
		if (node.getParameter().size() != 1) throw new BuildingASMException("The enumeration literal" +
				" reference must have exactly one prameter (that is a string).", value);
		
		IModelExpression firstParam = node.getParameter().get(0);
		
		IModelExpression exp = firstParam;
		if (firstParam instanceof IVariable) exp = BuildingCodeUtilClass.transferLastElementOfVariableChainList((IVariable) firstParam);
		
		if (!(exp instanceof IStringElement)) throw new BuildingASMException("The parameter of the enumeration" +
				" literal reference must be a string literal.", value);
		
		// Transform the value of the token into a pathname list.
		List<String> pathName = BuildingCodeUtilClass.transformPathName2List(exp.getToken().getValue());
		
		// The pathname part that represents the enumeration name.
		List<String> pathNameType = null;
		try {
			/* 
			 * The enumeration name is the part of the whole pathname but without the last element
			 * that denotes the enumeration literal name.
			 */
			pathNameType = pathName.subList(0, pathName.size()-1);
		}catch(IndexOutOfBoundsException ex) {
			// The case can happen if the value string has the length 0.
			throw new BuildingASMException("An enumeration literal must have at least two elements.", value);
		}
		
		/*
		 * If the pathname type has the length 0, then the user has give us an incomplete
		 * enumeration literal name.
		 */ 
		
		if (pathNameType.size() == 0) throw new BuildingASMException("An enumeration literal must have at least two elements.", value);
		
		// The instance that will hold the found property.
		EnumerationLiteral literal = null;
		try {
			// Look up for the type in the model.
			Type type = env.getModel().findType(pathNameType);
			
			// The type was not found.
			if (type == null) throw new BuildingASMException("The enumeration of this enumeration literal was not found.", value);
			
			// Currently we have only the type, but we want an Enumeration instance.
			if (!(type instanceof Enumeration)) throw new BuildingASMException("An element with the given name was found, but it isn't an enumeration.", value);
			
			// Here we cast the type to an enumeration.
			Enumeration enumeration = (Enumeration) type;
			
			// Here we look for the specified enumeration literal.
			literal = enumeration.lookupLiteral(pathName.get(pathName.size()-1));
		} catch (ModelAccessException e) {
			throw new BuildingASMException("The property was not found in the model.", value, e);
		}
		
		// The enumeration literal was not found.
		if (literal == null) throw new BuildingASMException("The enumeration literal was not found in the the corresponding enumeration.", value);
		
	}

	

	/**
	 * The metamodel, given by the user, will be searched. If the metamodel is found, it
	 * will be set in the environment and in the node. If the metamodel isn't
	 * found a {@link BuildingASMException} will be thrown.
	 */
	public void visitMetamodelReference(IMetamodelReference node, IEnvironment env) throws BuildingASMException {
		// Get the metamodel registry.
		IMetamodelRegistry reg = env.getMetamodelReg();
		
		// Look for the metamodel.
		IMetamodel metamodel = reg.getMetamodel(node.getToken().getValue());
		
		// If the metamodel was not found, throw an exception.
		if (metamodel == null) throw new BuildingASMException("The metamodel was not found.", node.getToken());
		
		// Set the metamodel in the environment.
		env.setMetamodel(metamodel);
		
	}

	/**
	 * The model file name, given by the user, will be searched. If the file is found,
	 * it is tried to load the model inside. If both is successful the model will be
	 * set up in the environment and in the node itself. Otherwise a {@link BuildingASMException}
	 * will be thrown.
	 */
	public void visitModelReference(IModelReference node, IEnvironment env)
			throws BuildingASMException {
		// Get the token.
		ITokenAS modelFileName = node.getToken();
		
		// Create a file instance with the given file name.
		File modelFile = new File(modelFileName.getValue());
				
		// Exist the model file? If not so, we will throw an exception.
		if (!modelFile.exists()) throw new BuildingASMException("The model file doesn't exists.", modelFileName);
		
		// The instance that will hold the model.
		IModel model = null;
		try {
			// Try to load the model from the file.
			model = env.getMetamodel().getModelProvider().getModel(modelFile);
		} catch(ModelAccessException ex) {
			// If this isn't possible, we throw an exception.
			throw new BuildingASMException("The model file contains no model.", modelFileName, ex);
		}
		
		// If the model instance is still null, the model was not found.
		if (model == null) throw new BuildingASMException("The model file contains no model.", modelFileName);
		
		// The model is set in the environment.
		env.setModel(model);
		
	}

	public void visitNullElement(INullElement node, IEnvironment env) throws BuildingASMException {
		// TODO Auto-generated method stub
		
	}

	public void visitPackageDeclaration(IPackageDeclaration node, IEnvironment env) throws BuildingASMException {
		// TODO Auto-generated method stub
		
	}

	public void visitStringElement(IStringElement node, IEnvironment env) throws BuildingASMException {
		// TODO Auto-generated method stub
	}
	
	public void visitErrorElement(IErrorElement node, IEnvironment env)	throws BuildingASMException {
		// TODO Auto-generated method stub
		
	}
}
