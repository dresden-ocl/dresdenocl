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
package tudresden.ocl20.pivot.ocl2parser.testcasegenerator.util;

import java.util.HashMap;
import java.util.List;

/**
 * This interface declares four methods that should be used to generate
 * code from the abstract model of the testcase generator. This interface
 * is kept generally. The implementation can generate code for an Eclipse-Plug-In
 * but can also generate code for a jar-file or something else.
 * To use an implementation we must first initialize the builder with a
 * project name. After that we can create the location. For a plugin this
 * is a new project.
 * Then we can generate code for testcases and testsuites.
 * @author Nils
 *
 */
public interface ICodeBuilder {
	/**
	 * Initialize the builder with a project name.
	 * @param projectname
	 */
	public void initialize(String projectname);
	
	/**
	 * Create the location at which the code will be stored.
	 */
	public void createCodeLocation();
	
	/**
	 * Generate code for the testcase and store it into the
	 * location. The map contains all information that are necessary
	 * to generate the code.
	 * @param map
	 */
	public void generateTestcase() throws Exception;
	
	/**
	 * Generate code for the testsuite and store it into the
	 * location. The map contains all information that are necessary
	 * to generate the code.
	 * @param map
	 */
	public void generateTestsuite() throws Exception;
	
	
	public void addCodeSnippet(String codeSnippet);
	
	public void setTestcaseName(String name);
	
	public void setOclExpression(String oclExp);
	
	//public void setVariableDecl(String varDecl);
	public void addVarDeclSnippet(String varDecl);
	
	public void setErrorElement(boolean elementValue);
	
	public void newTestCase();
	
	public void newTestMethod();
	
	public void newTestSuite();
	
	public void addTestClassName(String className);
	
	
	
	public String getTestName();

	public void setTestName(String testName);

	public String getMetamodel();

	public void setMetamodel(String metamodel);

	public String getModelFile();

	public void setModelFile(String modelFile);

	public String getTestsuiteName();

	public void setTestsuiteName(String testsuiteName);

	public List<String> getTestClassNames();

	public String getPackagename();

	public void setPackagename(String packagename);
}
