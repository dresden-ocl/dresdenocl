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

import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IModelExpression;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IModelNode;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.ITokenAS;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.util.BuildingASMException;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.util.Environment;

public abstract class ModelExpression implements IModelExpression {
	//protected TokenAS value;
	private String typename;
	private ITokenAS token;
	private String packageName = null;
	//protected String className;

	public ModelExpression() {
		super();
		typename = new String();
		packageName = new String();
	}
	
	public ModelExpression(ITokenAS token, String typename) {
		this.typename = typename;
		this.token = token;
	}
	
	public ModelExpression(ITokenAS token) {
		this.token = token;
	}
	
	/*public ModelExpression(TokenAS value) {
		super();
		this.value = value;
	}
	
	public ModelExpression(TokenAS value, String className) {
		this.value = value;
		this.className = className;
	}*/
	
	public ModelExpression(String typename) {
		this.typename = typename;
	}

	/*public TokenAS getValue() {
		return value;
	}

	public void setValue(TokenAS value) {
		this.value = value;
	}*/

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}
	
	/**
	 * We define a standard behavior for the variable resolution. That means,
	 * we do nothing. Why makes this sense? Some sub classes  like <i>PropertyElement</i>
	 * don't contain any parameter and so they can't held a reference on a variable. So
	 * in this cases we must do nothing. But in other sub classes like <i>ListElement</i>
	 * we must override this behavior.
	 * @param env the environment that is used for the resolution
	 * @throws BuildingASMException is thrown if any error occurs
	 */
	public void computeVariables(Environment env) throws BuildingASMException {
		return;
	}

	public ITokenAS getToken() {
		return token;
	}

	public void setToken(ITokenAS token) {
		this.token = token;
	}
	
	/**
	 * Get the type of the model expression.
	 * If the look up is failed, a {@link BuildingASMException} will be thrown.
	 * @return the type of the model expression
	 * @throws BuildingASMException thrown if the type was not found
	 */
	/*public Class getType() throws BuildingASMException {
		if (getClassName() == null) return null;
		
		String name = getClassName();
		
		if (name.equals("real")) return double.class;
		if (name.equals("int")) return int.class;
		if (name.equals("boolean")) return boolean.class;
		
		
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Class type = null;
		try {
			type = Class.forName(name, false, loader);
		} catch(ClassNotFoundException ex) {
			//throw new BuildingASMException("The type was not found.", value, ex);
			
		}
		
		return type;
		
	}*/
	
	/*public void setType(Class type) throws BuildingASMException {
		className = type.getName();
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}*/
	
	public String getPackageName() {
		return packageName;
	}
	
	public void setPackageName(String name) {
		packageName = name;
	}
	
	public String getFullQualifiedTypename() {
		if ((packageName == null) || (packageName.equals(""))) return typename;
		return packageName + "." + typename;
	}
	
	
}
