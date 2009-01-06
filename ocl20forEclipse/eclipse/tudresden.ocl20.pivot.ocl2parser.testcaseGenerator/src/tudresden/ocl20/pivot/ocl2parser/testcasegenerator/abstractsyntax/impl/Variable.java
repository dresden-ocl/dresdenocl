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
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.ITokenAS;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IVariable;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.util.BuildingASMException;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.util.IEnvironment;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.visitors.IVisitor;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.visitors.IVisitorCodeGen;

public class Variable extends ModelExpression implements IVariable {
	private IModelExpression reference;
	private boolean isChecked;
	private boolean isUsed;

	public Variable() {
		isChecked = false;
		isUsed = false;
	}
	
	public Variable(ITokenAS token) {
		super(token);
		isChecked = false;
		isUsed = false;
	}
	
	/*public Variable(ITokenAS name, IModelExpression ref) {
		super(name, "Variable");
		this.reference = ref;
	}*/
	
	public String getTypename() {
		return reference.getTypename();
	}
	
	public String getFullQualifiedTypename() {
		return reference.getFullQualifiedTypename();
	}
	
	public String getPackageName() {
		return reference.getPackageName();
	}

	public IModelExpression getReference() {
		return reference;
	}

	public void setReference(IModelExpression ref) {
		this.reference = ref;
	}
	
	/**
	 * Two variables are equal if their names are equal.
	 */
	public boolean equals(Object var) {
		if (!(var instanceof IVariable)) return false;
		
		IVariable compVar = (IVariable) var;
		if (getToken().getValue().equals(compVar.getToken().getValue())) return true;
		
		return false;
	}
	
	public boolean isUsed() {
		return isUsed;
	}
	
	public void setUsed(boolean value) {
		this.isUsed = value;
	}
	
	public String toString() {
		String stringRep = "Variable: " + getToken().getValue() + " at line " + getToken().getLine() + " and column " + getToken().getColumn();
		return stringRep;
	}
	
	/*public String getClassName() {
		return modelExpression.getClassName();
	}*/
	
	/*public void computeTypeConformance(Environment env) throws BuildingASMException {
		modelExpression.computeTypeConformance(env);
	}*/
	
	/*public void setType(Class type) throws BuildingASMException {
		modelExpression.setType(type);
	}*/

	public void accept(IVisitor visitor, IEnvironment env) throws BuildingASMException {
		visitor.visitVariable(this, env);
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean value) {
		isChecked = value;
	}

	public void accept(IVisitorCodeGen visitor, StringBuffer buffer)
			throws BuildingASMException {
		visitor.visitVariable(this, buffer);
		
	}
	
	
}
