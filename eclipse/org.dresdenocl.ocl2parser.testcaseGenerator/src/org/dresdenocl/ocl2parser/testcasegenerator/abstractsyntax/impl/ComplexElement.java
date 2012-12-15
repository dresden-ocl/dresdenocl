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
package org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IComplexElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IModelExpression;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.ITokenAS;

public abstract class ComplexElement extends ModelExpression implements IComplexElement {
	protected List<IModelExpression> parameter;
	
	public ComplexElement() {
		super();
		parameter = new ArrayList<IModelExpression>();
	}
	
	public ComplexElement(String typename) {
		super(typename);
		parameter = new ArrayList<IModelExpression>();
	}
	
	public ComplexElement(ITokenAS token, String typename) {
		super(token, typename);
		parameter = new ArrayList<IModelExpression>();
	}
	
	public ComplexElement(ITokenAS token) {
		super(token);
		parameter = new ArrayList<IModelExpression>();
	}
	
	public IModelExpression getParameter(int index) {
		return parameter.get(index);
	}
	
	public void setParameter(IModelExpression param, int index) {
		parameter.set(index, param);
	}
	
	
	
	/*public ComplexElement(TokenAS name) {
		super(name);
	}*/
	
	/*public ComplexElement() {
		super();
	}*/
	
	/*public ComplexElement(TokenAS value, String className) {
		super(value, className);
	}*/
	
	/*public ComplexElement(String className) {
		super(className);
	}*/

	public List<IModelExpression> getParameter() {
		return parameter;
	}

	public void setParameter(List<IModelExpression> parameter) {
		this.parameter = parameter;
	}
	
	/**
	 * This method computes the variable resolution of the {@link ListElement}.
	 */
//	public void computeVariables(Environment env) throws BuildingASMException {
//		/*
//		 * We iterate over all list elements.
//		 */
//		for(int i = 0; i < modelElements.size(); i++) {
//			// Get the model expression.
//			ModelExpression exp = modelElements.get(i);
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
//				//if (var == null) throw new BuildingASMException("The variable was not declared.", exp.getValue());
//				
//				// Replace the actual variable through the found one. 
//				modelElements.set(i, var);
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
	
	public boolean conformsType(Class type, Class testType, List<String> comparedClasses) {
		if (type.getName().equals(testType.getName())) return true;
		
		if (comparedClasses == null) comparedClasses = new ArrayList<String>();
		
		/* If both types are arrays then we examine the conformation of the array types.
		 * Otherwise we return false.
		 */
		
		// The first type is an array type.
		if (type.isArray()) {
			// The second type is an array type.
			if (testType.isArray()) {
				return conformsType(type.getComponentType(), testType.getComponentType(), comparedClasses);
			}
			
			// The first type is an array, but the second not. So it can't be conform, so we return null.
			return false;
		} else  { // The first type is not an array.
			
			// The second type is an array. So we return false,
			if (testType.isArray()) return false;
		}
		
		Class[] superInterfaces = testType.getInterfaces();
		if (superInterfaces.length == 0) return false;
		
		
		for(Class superInterf : superInterfaces) {
			if (comparedClasses.contains(superInterf.getName())) return false;
			if (conformsType(type, superInterf, comparedClasses)) return true;
			comparedClasses.add(superInterf.getName());
		}
		
		Class superClass = testType.getSuperclass();
		if (superClass == null) return false;
		if (comparedClasses.contains(superClass.getName())) return false;
		return conformsType(type, superClass, comparedClasses);
	}
	
	public boolean coformsType2(Class type, Class testType) {
		String testTypeName = testType.getName();
		if (type.getName().equals(testTypeName)) return true;
		
		Class[] interfaces = testType.getInterfaces();
		List<Class> interfaceList = Arrays.asList(interfaces);
		
		for(int i = 0; i < interfaceList.size(); i++) {
			Class interf = interfaceList.get(i);
			if (type.getName().equals(interf.getName())) return true;
			
			Class[] superInterfaces = interf.getInterfaces();
			List<Class> superInterfaceList = Arrays.asList(superInterfaces);
			
			interfaceList.addAll(superInterfaceList);
		}
		
		Class superClass = testType.getSuperclass();
		while(superClass != null) {
			if (type.getName().equals(superClass.getName())) return true;
			
			superClass = superClass.getSuperclass();
		}
		
		return false;
	}
	/**
	 * This method computes a list of two lists. The elements of the new list are
	 * the elements that are not part of both input lists. For example:
	 * We have these two lists: A = {1,2,3,4} and B= {1,2,5,6,7}.
	 * The result list will be C = {3,4,5,6,7} because the elements 1 and 2
	 * are part of both lists.
	 * @param firstList the first list 
	 * @param secondList the second list
	 * @return the interseciton list between the two lists.
	 */
	private List<Class> intersectedList(List<Class> firstList, List<Class> secondList) {
		// Create a result list.
		List<Class> result = new ArrayList<Class>();
		
		// If the first list is null, we must check the second list.
		if (firstList == null) {
			// If the second list is also null, we will return the empty result list.
			if (secondList == null) return result;
			
			// If the secons list is not null, we will return the second list.
			return secondList;
		} else { // The first list is not null.
			// If the second list is null we will return the first list.
			if(secondList == null) return firstList;
			
			// Both lists contains elements.
			
			/* 
			 * We add the elements of the first list into the result list
			 * that are not part of the second list.
			 */
			for(Class elem : firstList) {
				if (!secondList.contains(elem)) result.add(elem);
			}
			
			/*
			 * We add the elements of the second list that are not part of
			 * the first list.
			 */
			for(Class elem : secondList) {
				if (!firstList.contains(elem)) result.add(elem);
			}
			
			return result;
		}
	}
	
	public List<Class> getSuperTypes(Class type) {
		List<Class> typeList = new ArrayList<Class>();
		
		typeList.addAll(Arrays.asList(type.getInterfaces()));
		typeList.add(type.getSuperclass());
		
		return typeList;
	}
}
