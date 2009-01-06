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


import java.util.ArrayList;
import java.util.List;

import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.ITest;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.ITestSuite;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.ITokenAS;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.util.BuildingASMException;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.util.IEnvironment;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.visitors.IVisitor;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.visitors.IVisitorCodeGen;

public class TestSuite extends Test implements ITestSuite {
	private List<ITest> testElements;
	
	/*
	 * The parser set this list of tokens. It can't be
	 * determined at this stage of evaluation whether a
	 * token refer to a test suite or to a testcase. So we can't
	 * create really ITest instance. While making the cycle check
	 * of the test suites we can determine this informations and so
	 * we can set the list 'testElements'.
	 */
	private List<ITokenAS> testElementsToken;
	
	public TestSuite() {
		testElements = new ArrayList<ITest>();
		testElementsToken = new ArrayList<ITokenAS>();
	}
	
	public TestSuite(ITokenAS token) {
		super(token);
		testElements = new ArrayList<ITest>();
		testElementsToken = new ArrayList<ITokenAS>();
	}
	
	//private List<ITokenAS> suiteElementsStrings;

	/*public TestSuite() {
		super();
		suiteElements = new ArrayList<Test>();
	}*/
	
	/*public TestSuite(ITokenAS name) {
		super(name);
	}
	
	public TestSuite(ITokenAS name, List<ITest> testElements) {
		super(name);
		this.testElements = testElements;
	}*/

	public List<ITest> getTestElements() {
		return testElements;
	}

	public void setTestElements(List<ITest> testElements) {
		this.testElements = testElements;
	}

	/*public List<TokenAS> getSuiteElementsStrings() {
		return suiteElementsStrings;
	}

	public void setSuiteElementsStrings(List<TokenAS> suiteElementsStrings) {
		this.suiteElementsStrings = suiteElementsStrings;
	}*/
	
	/**
	 * Computes the variables that are in the tests (testsuites and testcases)
	 * that are hold in this test suite. We need an environment <i>env</i>. If
	 * something goes wrong we get a {@link BuildingASMException}.
	 * @param env the environment that we need for the computation
	 * @throws BuildingASMException is thrown if an error occurs
	 */
	/*public void computeVariables(Environment env) throws BuildingASMException {
		for(Test test : suiteElements) {
			test.computeVariables(env);
		}
	}*/
	
	/*public void computeTypeConformance(Environment env) throws BuildingASMException {
		for(Test elem : suiteElements) {
			elem.computeTypeConformance(env);
		}
	}*/

//	@Override
//	public void checkCyclesInTest(Environment env) throws BuildingASMException {
//		/*
//		 * We check whether the file and the name of this test suite
//		 * is already known to the environment. If so, we have detected a cycle
//		 * and we get a list of filename containers that represents this cycle.
//		 */
//		List<FilenameContainer> conList = null;
//		try {
//			conList = env.lookupTestElement(file, token.getValue());
//		} /*
//		   * If this exception is thrown, it is very strange because the name of the
//		   *  testsuite and the file was already be set.
//		   */
//		catch (InvalidArgumentException e) {
//			e.printStackTrace();
//			System.err.println("This error is very strange.");
//		}
//		
//		/*
//		 * The container list is not empty. That means, that we have found a cycle.
//		 * We use the information given from the list to make a beautiful error
//		 * message, so that the user can easily detect the cycle.
//		 */
//		if (conList != null) {
//			String errorString = "A cycle in a test suite was detected. Cycle:\n";
//			for(int i = conList.size() - 1; i >= 0; i--) {
//				errorString = errorString + "Test suite name : " + conList.get(i).getElementName() + "\n File:  " + conList.get(i).getFile().getAbsolutePath() + "\n ->\n";
//			}
//			
//			throw new BuildingASMException(errorString, token);
//		}
//		
//		/*
//		 * Here we know that we have no cycle, so we create a new environment with the
//		 * file reference and the name in this suite. These informations are not part of
//		 * the environment so far. We must set a filename container object in the
//		 * environment.
//		 */
//		Environment innerEnv = env.nestedEnvironment();
//		innerEnv.setTestElement(new FilenameContainer(file, token.getValue()));
//		
//		/*
//		 * We must examine whether the files that are given from the user are exists.
//		 * So we iterate over all file names and test whether the file exists or not.
//		 * In addition we create a list of this file objects.
//		 */
//		List<File> files = new ArrayList<File>();
//		for(TokenAS token : suiteElementsStrings) {
//			File file = new File(token.getValue());
//			if (!(file.exists())) {
//				throw new BuildingASMException("The file for the test doesn't exists.", token);
//			}
//			
//			files.add(file);
//		}
//		
//		/*
//		 * We iterate the file list created so far to parse these files and make test object out of it.
//		 */
//		for(File file : files) {
//			// We must create a file reader object.
//			FileReader fileReader = null;
//			try {
//				fileReader = new FileReader(file);
//			}catch(FileNotFoundException ex) { // This exception can't be thrown because we have test this already.
//				throw new BuildingASMException("The file was not found: " + file.getName());
//			}
//			
//			// Create a pushback reader with the file reader.
//			PushbackReader reader = new PushbackReader(fileReader, 10000);
//			
//			// Create a lexer and a parser object.
//			Lexer lexer = new Lexer(reader);
//			Parser parser = new Parser(lexer);
//			
//			
//			/*
//			 * Here we parse the file. If any error occurred while lexing or parsing we thrown an exception.
//			 */
//			Start start = null;
//			try {
//				start = parser.parse();
//			}catch(ParserException ex) {
//				throw new BuildingASMException("An error occurred while parsing the file " + file.getName() + ". " + ex.getMessage(), token, ex);
//			} catch(LexerException ex) {
//				throw new BuildingASMException("An lexer error occured while processing the file " + file.getName() + ". " + ex.getMessage(), token, ex);
//			} catch(Exception ex) {
//				throw new BuildingASMException("An internal error occurred while processing the file " + file.getName() + ".", token, ex);
//			}
//			
//			
//			// The parsing process was successful So we can create an attribute evaluator object.
//			AttributeEvaluator eval = new AttributeEvaluator(new NodeFactory());
//			
//			
//			/*
//			 * Here we compute the test object, but no variable computation or type check will be processed.
//			 */
//			Test test = null;
//			try {
//				test = (Test) start.apply(eval, new Heritage());
//			}catch(AttrEvalException ex) {
//				throw new BuildingASMException("An concrete to abstract syntax tree error occurred while processing the file " + file.getName(), token, ex);
//			}catch(Exception ex) {
//				throw new BuildingASMException("An internal error occurred while processing the file " + file.getName(), token, ex);
//			}
//			
//
//			// We set the original file to the test.
//			test.setFile(file);
//
//			// If all get right, we can add the test element to the suite elements.
//			suiteElements.add(test);
//		}
//		
//		/*
//		 * So we have all files parsed. We can now check for cycles.
//		 */
//		for(Test test : suiteElements) {
//			/*
//			 * We set the environment created here, filled with the
//			 * file and name information. In the next invocation of this
//			 * method the environment hold this information.
//			 */
//			test.checkCyclesInTest(innerEnv);
//		}
//		
//	}

	public void accept(IVisitor visitor, IEnvironment env) throws BuildingASMException {
		visitor.visitTestSuite(this, env);
	}

//	@Override
//	public void checkCyclesInTest(Environment env) throws BuildingASMException {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void computeVariables(Environment env) throws BuildingASMException {
//		// TODO Auto-generated method stub
//		
//	}

	public List<ITokenAS> getTestElementsToken() {
		return testElementsToken;
	}

	public void setTestElementsToken(List<ITokenAS> elements) {
		testElementsToken = elements;		
	}

	public void accept(IVisitorCodeGen visitor, StringBuffer buffer)
			throws BuildingASMException {
		visitor.visitTestSuite(this, buffer);
		
	}
}
