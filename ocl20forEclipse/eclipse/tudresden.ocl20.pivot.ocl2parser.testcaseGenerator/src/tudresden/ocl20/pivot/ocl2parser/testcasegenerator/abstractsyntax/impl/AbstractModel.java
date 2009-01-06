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

import java.util.List;

import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IAbstractModel;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.ITokenAS;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IVariable;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.util.BuildingASMException;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.util.IEnvironment;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.visitors.IVisitor;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.visitors.IVisitorCodeGen;

public class AbstractModel extends Result implements IAbstractModel {
	private List<IVariable> variables;

	
	public AbstractModel(ITokenAS token) {
		super(token);
	}
	
	public AbstractModel() {
		super();
	}

	/*public AbstractModel(List<IVariable> variables) {
		super();
		this.variables = variables;
	}*/

	public List<IVariable> getVariables() {
		return variables;
	}

	public void setVariables(List<IVariable> variables) {
		this.variables = variables;
	}
	
	/*@Override
	public void computeTypeConformance(Environment env)
			throws BuildingASMException {
		// TODO Auto-generated method stub
		
	}*/

	public void accept(IVisitor visitor, IEnvironment env)
			throws BuildingASMException {
		visitor.visitAbstractModel(this, env);
		
	}

	public void accept(IVisitorCodeGen visitor, StringBuffer buffer)
			throws BuildingASMException {
		visitor.visitAbstractModel(this, buffer);
		
	}
	
	/*public void computeTypeConformance(Environment env) throws BuildingASMException {
		variables.get(0).computeTypeConformance(env);
	}*/

	
	
	
}
