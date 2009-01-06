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

import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IModelNode;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IResult;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.ITestcaseElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.ITokenAS;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.util.BuildingASMException;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.util.Environment;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.util.IEnvironment;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.visitors.IVisitor;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.visitors.IVisitorCodeGen;

public class TestcaseElement implements ITestcaseElement {
	private ITokenAS oclExpression;
	private IResult result;
	private ITokenAS token;
	
	public TestcaseElement() {
		super();
	}
	
	/*public TestcaseElement(TokenAS name, TokenAS oclExpression, Result result) {
		this.token = name;
		this.oclExpression = oclExpression;
		this.result = result;
	}*/
	
	public TestcaseElement(ITokenAS token) {
		this.token = token;
	}
	
	public ITokenAS getOclExpression() {
		return oclExpression;
	}
	public void setOclExpression(ITokenAS oclExpression) {
		this.oclExpression = oclExpression;
	}
	public IResult getResult() {
		return result;
	}
	public void setResult(IResult result) {
		this.result = result;
	}

	public ITokenAS getToken() {
		return token;
	}

	public void setToken(ITokenAS token) {
		this.token = token;
	}
	
	/*public void computeVariables(Environment env) throws BuildingASMException {
		result.computeVariables(env);
	}*/
	
	/*public void computeTypeConformance(Environment env) throws BuildingASMException {
		result.computeTypeConformance(env);
	}*/

	public void accept(IVisitor visitor, IEnvironment env) throws BuildingASMException {
		visitor.visitTestcaseElement(this, env);
	}

	public void accept(IVisitorCodeGen visitor, StringBuffer buffer)
			throws BuildingASMException {
		visitor.visitTestcaseElement(this, buffer);
		
	}
}
