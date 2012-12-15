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

import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IEnumerationLiteralElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.ITokenAS;
import org.dresdenocl.ocl2parser.testcasegenerator.util.BuildingASMException;
import org.dresdenocl.ocl2parser.testcasegenerator.util.IEnvironment;
import org.dresdenocl.ocl2parser.testcasegenerator.visitors.IVisitor;
import org.dresdenocl.ocl2parser.testcasegenerator.visitors.IVisitorCodeGen;

public class EnumerationLiteralElement extends PivotmodelReferenceElement implements IEnumerationLiteralElement{
	public EnumerationLiteralElement() {
		super(Messages.getString("EnumerationLiteralName"));
	}
	
	public EnumerationLiteralElement(ITokenAS token) {
		super(token, Messages.getString("EnumerationLiteralName"));
	}
	//private TokenAS enumerationLiteralToken = null;
	//private EnumerationLiteral literal = null;
	
	/*public EnumerationLiteralElement() {
		super("Enumeration");
	}
	
	public EnumerationLiteralElement(TokenAS name) {
		super(name, "Enumeration");
	}
	
	public String getClassName() {
		return "org.dresdenocl.pivotmodel."+ className;
	}*/
	
	/*public void computeTypeConformance(Environment env) throws BuildingASMException {
		IModel model = env.getModel();
		
		List<String> pathName = UtilClass.transformPathName2List(value.getValue());
		
		Type type = null;
		try {
			type = model.findType(pathName.subList(0, pathName.size()));
		} catch(ModelAccessException ex) {
			throw new BuildingASMException("The enumeration literal was not found.", value, ex);
		}
		
		if (!(type instanceof Enumeration)) throw new BuildingASMException("The enumeration was not found.", value);
		
		Enumeration enumeration = (Enumeration) type;
		
		EnumerationLiteral enumLiteral = enumeration.lookupLiteral(pathName.get(pathName.size()));
		
		if (enumLiteral == null) throw new BuildingASMException("The enumeration literal was not found.", value);
	}*/

	public void accept(IVisitor visitor, IEnvironment env) throws BuildingASMException {
		visitor.visitEnumerationLiteralElement(this, env);
		
	}

	public void accept(IVisitorCodeGen visitor, StringBuffer buffer)
			throws BuildingASMException {
		visitor.visitEnumerationLiteralElement(this, buffer);
		
	}

	/*public EnumerationLiteral getLiteral() {
		return literal;
	}

	public void setLiteral(EnumerationLiteral literal) {
		this.literal = literal;
	}

	public TokenAS getEnumerationLliteralToken() {
		return enumerationLiteralToken;
	}

	public void setEnumerationLliteralToken(TokenAS enumerationLliteralToken) {
		this.enumerationLiteralToken = enumerationLliteralToken;
	}*/

}
