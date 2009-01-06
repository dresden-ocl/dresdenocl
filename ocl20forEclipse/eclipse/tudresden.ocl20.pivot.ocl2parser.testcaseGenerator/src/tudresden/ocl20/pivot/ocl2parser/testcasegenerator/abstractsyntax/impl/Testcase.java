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

import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IMetamodelReference;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IModelReference;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.ITestcase;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.ITestcaseElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.ITokenAS;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.util.BuildingASMException;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.util.Environment;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.util.IEnvironment;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.visitors.IVisitor;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.visitors.IVisitorCodeGen;

public class Testcase extends Test implements ITestcase {
	private IModelReference modelRef;
	private IMetamodelReference metamodelRef;
	private List<ITestcaseElement> elements;
		
	public Testcase() {
		super();
	}
	
	public Testcase(ITokenAS token) {
		super(token);
	}
	
//	public Testcase(ITokenAS token, IModelReference modelRef,
//			IMetamodelReference metamodelRef, List<ITestcaseElement> elements) {
//		super(token);
//		this.modelRef = modelRef;
//		this.metamodelRef = metamodelRef;
//		this.elements = elements;
//	}
	
	public IModelReference getModelReference() {
		return modelRef;
	}
	
	public void setModelReference(IModelReference modelRef) {
		this.modelRef = modelRef;
	}
	
	public IMetamodelReference getMetamodelReference() {
		return metamodelRef;
	}
	
	public void setMetamodelReference(IMetamodelReference metamodelRef) {
		this.metamodelRef = metamodelRef;
	}
	
	public List<ITestcaseElement> getTestcaseElements() {
		return elements;
	}
	
	public void setTestcaseElements(List<ITestcaseElement> elements) {
		this.elements = elements;
	}

//	@Override
//	public void computeVariables(Environment env) throws BuildingASMException {
//		// Check the name of this test case.
//		//Environment fileEnv = checkTestName(env);
//		
//		
//		// We compute the variable of elements in the testcase.
//		for(TestcaseElement elem : elements) {
//			Environment testcaseEnv = env.nestedEnvironment();
//			
//			elem.computeVariables(testcaseEnv);
//			/* We need to create a new nested environment.
//			 * We have more then a test case in one file. In each
//			 * test case variables are used but they separate. So one
//			 * variable name in one of the test case can be used in another
//			 * test case in the same file. The environment collect all variables
//			 * and these variable are still available for the next test case.
//			 * So we must create a new nested environment with no variables.
//			 */
//			//fileEnv = fileEnv.nestedEnvironment();
//		}
//		
//		/* Note: we don't need to set the file in the environment again,
//		 * because a test case doesn't contain any file references. 
//		 */
//		
//	}
	
	/*public void computeTypeConformance(Environment env) throws BuildingASMException {
		metamodelRef.computeTypeConformance(env);
		modelRef.computeTypeConformance(env);
		
		for(TestcaseElement elem : elements) {
			elem.computeTypeConformance(env);
		}
	}*/

	/**
	 * We must check whether a cycle is in a test case. This not
	 * very meaningful because a testcase doesn't contain any references
	 * to another test case or a test suite. So we simply skip this check
	 * by simple returning.
	 */
	//@Override
	/*public void checkCyclesInTest(Environment env) throws BuildingASMException {
		return;
	}*/

	public void accept(IVisitor visitor, IEnvironment env) throws BuildingASMException {
		visitor.visitTestcase(this, env);
	}

	public void accept(IVisitorCodeGen visitor, StringBuffer buffer)
			throws BuildingASMException {
		visitor.visitTestcase(this, buffer);
		
	}
	
	
	
	
	
}
