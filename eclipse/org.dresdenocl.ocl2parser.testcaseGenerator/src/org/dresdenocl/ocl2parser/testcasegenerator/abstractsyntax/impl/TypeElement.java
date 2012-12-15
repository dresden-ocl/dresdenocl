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

import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.ITokenAS;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.ITypeElement;
import org.dresdenocl.ocl2parser.testcasegenerator.util.BuildingASMException;
import org.dresdenocl.ocl2parser.testcasegenerator.util.IEnvironment;
import org.dresdenocl.ocl2parser.testcasegenerator.visitors.IVisitor;
import org.dresdenocl.ocl2parser.testcasegenerator.visitors.IVisitorCodeGen;

public class TypeElement extends PivotmodelReferenceElement implements ITypeElement {
	public TypeElement() {
		super(Messages.getString("TypeName"));
	}
	
	public TypeElement(ITokenAS token) {
		super(token, Messages.getString("TypeName"));
	}
	/*private Type type;
	
	public TypeElement() {
		super("Type");
	}
	
	public TypeElement(TokenAS name) {
		super(name, "Type");
	}*/
	
	/*public void computeTypeConformance(Environment env) throws BuildingASMException {
		IModel model = env.getModel();
		
		Type foundType = null;
		try {
			foundType = model.findType(UtilClass.transformPathName2List(value.getValue()));
		} catch(ModelAccessException ex) {
			throw new BuildingASMException("The type was not found.", value, ex);
		}
		
		if (foundType == null) throw new BuildingASMException("The type was not found.", value);
	}*/

	public void accept(IVisitor visitor, IEnvironment env) throws BuildingASMException {
		visitor.visitTypeElement(this, env);
	}

	public void accept(IVisitorCodeGen visitor, StringBuffer buffer)
			throws BuildingASMException {
		visitor.visitTypeElement(this, buffer);
		
	}

	/*public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}*/

}
