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

import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IConstraintKindElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.ITokenAS;
import org.dresdenocl.ocl2parser.testcasegenerator.util.BuildingASMException;
import org.dresdenocl.ocl2parser.testcasegenerator.util.Environment;
import org.dresdenocl.ocl2parser.testcasegenerator.util.IEnvironment;
import org.dresdenocl.ocl2parser.testcasegenerator.visitors.IVisitor;
import org.dresdenocl.ocl2parser.testcasegenerator.visitors.IVisitorCodeGen;
import org.dresdenocl.pivotmodel.ConstraintKind;

public class ConstraintKindElement extends KindElement implements IConstraintKindElement {
	private TokenAS constraintKindToken = null;
	private ConstraintKind kind;
	
	public ConstraintKindElement() {
		super(Messages.getString("ConstraintKindName"));
		setPackageName(Messages.getString("EssentialOclExpressionsPackageName"));
	}
	
	public ConstraintKindElement(ITokenAS token) {
		super(token, Messages.getString("ConstraintKindName"));
		setPackageName(Messages.getString("EssentialOclExpressionsPackageName"));
	}
	/*public ConstraintKindElement() {
		super("ConstraintKind");
	}*/
	
	/*public ConstraintKindElement(TokenAS name) {
		super(name, "ConstraintKind");
	}*/
	
	public void semanticCheck(Environment env) throws BuildingASMException {
		
	}
	
	/*public String getClassName() {
		return "org.dresdenocl.pivotmodel." + className;
	}*/
	
	/*public void computeTypeConformance(Environment env) throws BuildingASMException {
		ConstraintKind kind = ConstraintKind.get(value.getValue());
		
		if (kind == null) throw new BuildingASMException("The constraint kind doesn't exists.", value);
	}*/

	public void accept(IVisitor visitor, IEnvironment env) throws BuildingASMException {
		visitor.visitConstraintKindElement(this, env);
		
	}

	public void accept(IVisitorCodeGen visitor, StringBuffer buffer)
			throws BuildingASMException {
		visitor.visitConstraintKindElement(this, buffer);
		
	}

	/*public ConstraintKind getKind() {
		return kind;
	}

	public void setKind(ConstraintKind kind) {
		this.kind = kind;
	}

	public TokenAS getConstraintKindToken() {
		return constraintKindToken;
	}

	public void setConstraintKindToken(TokenAS constraintKindToken) {
		this.constraintKindToken = constraintKindToken;
	}*/

}
