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

import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IAsSetElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.ITokenAS;
import org.dresdenocl.ocl2parser.testcasegenerator.util.BuildingASMException;
import org.dresdenocl.ocl2parser.testcasegenerator.util.IEnvironment;
import org.dresdenocl.ocl2parser.testcasegenerator.visitors.IVisitor;
import org.dresdenocl.ocl2parser.testcasegenerator.visitors.IVisitorCodeGen;

public class AsSetElement extends EssentialOclElement implements IAsSetElement {
	public AsSetElement(ITokenAS token) {
		super(token);
	}
	
	public AsSetElement() {
		super();
	}
	
	public void accept(IVisitor visitor, IEnvironment env) throws BuildingASMException {
		visitor.visitAsSetElement(this, env);
	}
	
	public void accept(IVisitorCodeGen visitor, StringBuffer buffer) throws BuildingASMException {
		visitor.visitAsSetElement(this, buffer);
	}
	
	public String getTypename() {
		return Messages.getString("OperationCallExp");
	}
	
	public void setTypename(String name) {
		
	}
	
	public String getFullQualifiedTypename() {
		return Messages.getString("EssentialOclExpressionsPackageName") + "." + Messages.getString("OperationCallExp");
	}
	
	public String getPackageName() {
		return Messages.getString("EssentialOclExpressionsPackageName");
	}
	public void setPackageName(String packageName) {
		
	}
}
