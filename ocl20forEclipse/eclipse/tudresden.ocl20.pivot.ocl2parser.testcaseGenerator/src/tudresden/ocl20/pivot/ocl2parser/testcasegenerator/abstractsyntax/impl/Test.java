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

import java.io.File;

import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IModelNode;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IPackageDeclaration;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.ITest;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.ITokenAS;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.util.BuildingASMException;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.util.Environment;

abstract public class Test implements ITest {
	protected ITokenAS token;
	protected IPackageDeclaration packageDecl;
	protected File file;
	//protected String testname;

	public Test() {
		
	}
	
	public Test(ITokenAS token) {
		this.token = token;
	}
	
	/*public Test(ITokenAS name) {
		super();
		this.name = name;
	}
	
	public Test(ITokenAS name, IPackageDeclaration decl) {
		this.name = name;
		this.packageDecl = decl;
	}*/
	
	public ITokenAS getToken() {
		return token;
	}
	
	public void setToken(ITokenAS token) {
		this.token = token;
	}
	
	public IPackageDeclaration getPackageDeclaration() {
		return packageDecl;
	}
	
	public void setPackageDeclaration(IPackageDeclaration decl) {
		this.packageDecl = decl;
	}

	
	
	//abstract public void computeVariables(Environment env) throws BuildingASMException;
	
	/**
	 * This method checks whether the name of test (test case or test suite) is already
	 * used. If so, the method throw a {@link BuildingASMException} exception.
	 * If the check goes right, a new environment will be created. In this environment
	 * a new file name container will be added, namely with the file given from the old
	 * environment and the name of the test. This new environment will be returned.
	 * 
	 * @param env the environment
	 * @return a new environment (it's a successor of the old environment)
	 * @throws BuildingASMException is thrown if the test name is already be used
	 */
	//protected Environment checkTestName(Environment env) throws BuildingASMException {
		// Look up for the test suite name in the environment.
		//FilenameContainer elemName = env.lookupTestElement(name.getValue());
		
		// If the test suite name was already used, it is an error.
		/*if (elemName != null) throw new BuildingASMException("The suite name is used twice." +
				" Name of the test: " + elemName.getElementName() + " and the file: " + elemName.getFile().getAbsolutePath(), name);
		*/
		/*
		 * We must create a new nested environment. Why? Suppose we have a test case file with variables.
		 * These variables are added to the environment. So if we use the same environment for
		 * another test case file, the variables in the environment are still valid. So if a variable
		 * name is used again, we get an exception where no error is. At least one variable name
		 * is in any test case file, namely the variable 'model'. 
		 */
		//env = env.nestedEnvironment();
		
		/* The test suite name was not used before, so we must save the name
		 * in the environment. To do so, we create a file name container.
		 * The element name is the name of this test suite. The file object
		 * get we from the environment through the 'getFile' method. This file
		 * object that we get from this method was set before the variable computation
		 * was started.
		 */
		/*FilenameContainer con = new FilenameContainer();
		con.setElementName(name.getValue());
		
		File gettedFile = env.getFile();
		if (gettedFile == null) throw new BuildingASMException("An internal error occurred. The file of the environment was not set.", name);
		
		con.setFile(gettedFile);*(
		
		// We have create a file name container, so we add it to the environment.
		/*env.addTestElement(con);
		
		return env;*/
	//}
	
	//abstract public void checkCyclesInTest(Environment env) throws BuildingASMException;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	
}
