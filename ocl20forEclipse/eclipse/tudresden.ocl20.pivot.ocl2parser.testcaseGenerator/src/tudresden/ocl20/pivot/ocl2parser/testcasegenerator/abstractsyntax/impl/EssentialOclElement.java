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

import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IEssentialOclElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.ITokenAS;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.util.BuildingASMException;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.util.IEnvironment;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.visitors.IVisitor;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.visitors.IVisitorCodeGen;

public class EssentialOclElement extends ComplexElement implements IEssentialOclElement{
	public EssentialOclElement(ITokenAS token) {
		super(token);
		setTypename(token.getValue());
		setPackageName(Messages.getString("EssentialOclExpressionsPackageName"));
	}
	
	public EssentialOclElement() {
		super();
		setPackageName(Messages.getString("EssentialOclExpressionsPackageName"));
	}
	/*public EssentialOclElement() {
		super();
	}*/
	
	/*public EssentialOclElement(TokenAS name) {
		super(name, name.getValue());
		
	}

	public EssentialOclElement(TokenAS name, List<ModelExpression> parameters) {
		super(name, name.getValue());
		modelElements = parameters;
	}*/
	
	/*public String getClassName() {
		return "tudresden.ocl20.pivot.essentialocl.expressions." + className;
	}*/
	
	/*public void computeTypeConformance(Environment env) throws BuildingASMException {
		className = value.getValue();
		for(ModelExpression exp : modelElements) {
			exp.computeTypeConformance(env);
		}
		
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Class imodelFactory = null;
		try {
			imodelFactory = Class.forName("tudresden.ocl20.pivot.modelbus.IModelFactory", false, loader);
		} catch(ClassNotFoundException ex) {
			throw new BuildingASMException("The IModelFactory was not found while looking up for essential" +
					" ocl expression creation method. It is an internal error.", value, ex);
		}
		
		
		String methodName = "create" + className;
		List<Method> methods = new ArrayList<Method>();
		methods.addAll(Arrays.asList(imodelFactory.getDeclaredMethods()));
		
		List<Method> foundMethods = new ArrayList<Method>();
		
		for(Method m : methods) {
			if (m.getName().equals(methodName)) foundMethods.add(m);
		}
		
		for(Method m : foundMethods) {
			Class[] methodParameters = m.getParameterTypes();
			if (methodParameters.length != modelElements.size()) continue;
			
			boolean found = true;
			for(int i = 0; i < methodParameters.length; i++) {
				Class parameterType = modelElements.get(i).getType();
				if (parameterType == null) {
					modelElements.get(i).setType(methodParameters[i]);
				} else {
					if (conformsType(methodParameters[i], parameterType, null) == false) {
						found = false;
						break;
					}
				}
				
			}
			
			if (found == true) return;
		}
		
		throw new BuildingASMException("The parameter types don't correspond to the type of the essential ocl element.", value);	
		
	}*/

	public void accept(IVisitor visitor, IEnvironment env) throws BuildingASMException {
		visitor.visitEssentialOclElement(this, env);
	}

	public void accept(IVisitorCodeGen visitor, StringBuffer buffer)
			throws BuildingASMException {
		visitor.visitEssentialOclElement(this, buffer);
		
	}

	/*public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}*/
	
	/*public void setTypename(String name) {
		super.setTypename(getPackageName() + name);
	}*/
}
