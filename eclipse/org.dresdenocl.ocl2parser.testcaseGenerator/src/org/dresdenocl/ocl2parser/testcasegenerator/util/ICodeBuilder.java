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
package org.dresdenocl.ocl2parser.testcasegenerator.util;

import java.util.HashMap;
import java.util.List;

/**
 * This interfaces describes the code builder. A code builder
 * has an internal state that is assembled by several properties.
 * Because this is only an interface, no properties can here be
 * declared, but the interface has several set-methods for the
 * properties, for example <code>setTestcaseName()</code>.
 * To use the code builder a sequence of method calls must be kept.
 * This order is depending on what code should be created: for a
 * testcase or for a testsuite.
 * First we begin with the testcase.
 * The order of the methods is as follow:
 * <ul>
 * 	<li><code>newTestcase()</code></li>
 * 	<li><code>setXxx()</code></li>
 * 	<li><code>generateTestcase()</code></li>
 * </ul>
 * The <code>setXxx()</code>-methods can be called in any order.
 * For the testcase the following <code>set</code>-methods are relevant:
 * <ul>
 * 	<li><code>setTestcaseName()</code></li>
 * 	<li><code>setMetamodel()</code></li>
 * 	<li><code>setModelFilename()</code></li>
 * 	<li><code>setPackagename()</code></li>
 * </ul>
 * 
 * Once you called the method <code>newTestcase()</code>, you can add a new
 * test method by calling <code>newTestMethod</code>. After that you can
 * set the information for that new method with the following methods:
 * <ul>
 * 	<li><code>addCodeSnippet()</code></li>
 * 	<li><code>setOclExpression()</code></li>
 * 	<li><code>addVarDeclSnippet()</code></li>
 * 	<li><code>setErrorElement()</code></li>
 * 	<li><code>setTestName()</code></li>
 * </ul>
 * After setting the information for the new test method, you can create 
 * a new test method with the appropriate method.
 * 
 * 
 * Now we describe the procedure for the test suite. The order of
 * method calls is as follows:
 * <ul>
 * 	<li><code>newTestsuite()</code></li>
 * 	<li><code>setXxx()</code></li>
 * 	<li><code>generateTestsuite()</code></li>
 * </ul>
 * The <code>setXxx()</code>-methods are the following ones:
 * <ul>
 * 	<li><code>setTestsuiteName()</code></li>
 * 	<li><code>addTestClassName()</code></li>
 * 	<li><code>setPackagename()</code></li>
 * </ul>
 * With the <code>addTestClassName()</code>-method
 * new class names for the testcases can be added to the test suite.
 * 
 * 
 * @author Nils
 *
 */
public interface ICodeBuilder {
	/**
	 * Begin a new test case. Initializes the internal
	 * code builder properties that are affected by the
	 * testcase generation process to default values.
	 */
	public void newTestCase();
	
	/**
	 * Set the name of the test case.
	 * @param name name of the test case to be set
	 */
	public void setTestcaseName(String name);
	
	/**
	 * Get the metamodel name that was set.
	 * @return the metamodel name
	 */
	public String getMetamodel();
	
	/**
	 * Set the metamodel name.
	 * @param metamodel the metamodel name to be set.
	 */
	public void setMetamodel(String metamodel);
	
	/**
	 * Get the name of the model file.
	 * @return the name of the model file
	 */
	public String getModelFile();
	
	/**
	 * Set the name of the model file.
	 * @param modelFile the name of the model file to be set.
	 */
	public void setModelFile(String modelFile);
	
	
	//------------------------------------------------------------------------------
	
	/**
	 * Create a new test method.
	 */
	public void newTestMethod();
	
	/**
	 * Add a code snippet to the newly created test method.
	 * @param codeSnippet a code snippet to be added to the test method
	 */
	public void addCodeSnippet(String codeSnippet);
	
	/**
	 * Set the ocl expression of the test method that should be parsed.
	 * @param oclExp the ocl expression for the test method
	 */
	public void setOclExpression(String oclExp);
	
	/**
	 * The variable declaration of the test method contains several
	 * declarations. To add one declaration use this method. The snippet
	 * must end with a semicolon.
	 * @param varDecl a code snippet for the variable declaration with ending semicolon
	 */
	public void addVarDeclSnippet(String varDecl);
	
	/**
	 * With this method the test method can be marked as error element.
	 * If it isn't set, the test method is <b>not</b> an error method. 
	 * @param elementValue true if the test method should be an error method
	 */
	public void setErrorElement(boolean elementValue);
	
	/**
	 * Gets the name of the method.
	 * @return name of the method
	 */
	public String getTestName();
	
	/**
	 * Sets the name of the method.
	 * @param testName name of the method
	 */
	public void setTestName(String testName);
	
	/**
	 * Generate code for the testcase by means of the information
	 * that were previously set.
	 * @throws throws an exception if file access fails or other failures occurs 
	 */
	public void generateTestcase() throws Exception;
	
	//***********************************************************************************************	
	
	/**
	 * Initializes the code builder for a new test suite.
	 */
	public void newTestSuite();
	
	/**
	 * Add a new class name to the test suite. The class name
	 * is used in the generated <code>@RunWith</code> annotation.
	 * The class name is the name of the testcase class.
	 * @param className test case class to be added
	 */
	public void addTestClassName(String className);
	
	/**
	 * Get the class names.
	 * @return class names
	 */
	public List<String> getTestClassNames();
	
	/**
	 * Get the set test suite name.
	 * @return the test suite name
	 */
	public String getTestsuiteName();
	
	/**
	 * Set the test suite name.
	 * @param testsuiteName test suite to be set
	 */
	public void setTestsuiteName(String testsuiteName);
	
	/**
	 * Generate code for the testsuite by means of the
	 * previously set information.
	 * @throws an exception is thrown if a file access error occurs or another error
	 */
	public void generateTestsuite() throws Exception;

	//*************************************************************************
	/*
	 * These methods are used by the testcase and testsuite generation process.
	 */
	
	/**
	 * Get the package name.
	 */
	public String getPackagename();
	
	/**
	 * Set the package name.
	 * @param packagename the package name to be set
	 */
	public void setPackagename(String packagename);
}
