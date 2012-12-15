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

import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.INamespaceElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.ITokenAS;
import org.dresdenocl.ocl2parser.testcasegenerator.util.BuildingASMException;
import org.dresdenocl.ocl2parser.testcasegenerator.util.IEnvironment;
import org.dresdenocl.ocl2parser.testcasegenerator.visitors.IVisitor;
import org.dresdenocl.ocl2parser.testcasegenerator.visitors.IVisitorCodeGen;

public class NamespaceElement extends PivotmodelReferenceElement implements INamespaceElement {
	public NamespaceElement() {
		super(Messages.getString("NamespaceName"));
	}
	
	public NamespaceElement(ITokenAS token) {
		super(token, Messages.getString("NamespaceName"));
	}
	//private TokenAS namespaceToken = null;
	//private Namespace namespace = null;
	//private ModelExpression constraint = null;
	
	/*public NamespaceElement() {
		super("Namespace");
	}
	
	public NamespaceElement(TokenAS name) {
		super(name, "Namespace");
	}*/
	
	/*public String getClassName() {
		return "org.dresdenocl.pivotmodel." + className;
	}*/
	
	/*public void computeTypeConformance(Environment env) throws BuildingASMException {
		IModel model = env.getModel();
		List<String> pathName = UtilClass.transformPathName2List(value.getValue());
		Namespace nm = null;
		try {
			nm = model.findNamespace(pathName);
		} catch (ModelAccessException e) {
			throw new BuildingASMException("The namespace was not found.", value, e);
		}
		
		if (nm == null) throw new BuildingASMException("The namespace was not found.",  value);
		
		
	}*/

	public void accept(IVisitor visitor, IEnvironment env) throws BuildingASMException {
		visitor.visitNamespaceElement(this, env);	
	}

	public void accept(IVisitorCodeGen visitor, StringBuffer buffer)
			throws BuildingASMException {
		visitor.visitNamespaceElement(this, buffer);
		
	}

	/*public Namespace getNamespace() {
		return namespace;
	}

	public void setNamespace(Namespace namespace) {
		this.namespace = namespace;
	}

	public ModelExpression getConstraint() {
		return constraint;
	}

	public void setConstraint(ModelExpression constraint) {
		this.constraint = constraint;
	}

	public TokenAS getNamespaceToken() {
		return namespaceToken;
	}

	public void setNamespaceToken(TokenAS namespaceToken) {
		this.namespaceToken = namespaceToken;
	}*/
}
