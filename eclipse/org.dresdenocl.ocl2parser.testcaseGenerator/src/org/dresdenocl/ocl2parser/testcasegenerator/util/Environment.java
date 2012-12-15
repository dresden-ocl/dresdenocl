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

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.dresdenocl.modelbus.IMetamodel;
import org.dresdenocl.modelbus.IMetamodelRegistry;
import org.dresdenocl.modelbus.IModel;
import org.dresdenocl.modelbus.IModelRegistry;
import org.dresdenocl.modelbus.ModelBusPlugin;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IGenModelFactory;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IVariable;
import org.dresdenocl.ocl2parser.testcasegenerator.testcasegenerator.InvalidArgumentException;


public class Environment implements IEnvironment {
	private Environment predecessor;
	private IModel model;
	private IMetamodel metamodel;
	private IMetamodelRegistry metamodelReg;
	private IModelRegistry modelReg;
	private List<IVariable> variables;
	private FilenameContainer testElement;
	private File file;
	private IGenModelFactory factory;
	
	private HashMap map;
	
	public Environment(IGenModelFactory fact) {
		metamodelReg = ModelBusPlugin.getDefault().getMetamodelRegistry();
		modelReg = ModelBusPlugin.getDefault().getModelRegistry();
		variables = new ArrayList<IVariable>();
		factory = fact;
		map = new HashMap();
		//testElements = new ArrayList<FilenameContainer>();
		
	}

	public IModel getModel() {
		return model;
	}

	public void setModel(IModel model) {
		this.model = model;
	}

	public IMetamodel getMetamodel() {
		return metamodel;
	}

	public void setMetamodel(IMetamodel metamodel) {
		this.metamodel = metamodel;
	}

	public IMetamodelRegistry getMetamodelReg() {
		return metamodelReg;
	}

	public void setMetamodelReg(IMetamodelRegistry metamodelReg) {
		this.metamodelReg = metamodelReg;
	}

	public IModelRegistry getModelReg() {
		return modelReg;
	}

	public void setModelReg(IModelRegistry modelReg) {
		this.modelReg = modelReg;
	}

	public List<IVariable> getVariables() {
		return variables;
	}

	public void setVariables(List<IVariable> variables) {
		this.variables = variables;
	}
	
	/**
	 * This method looks up for a variable with the name
	 * of <i>var</i>. The input object and the output
	 * object are different although both object have the same
	 * name. If no variable was found, the method will return <i>null</i>.
	 * Note: the lookup is local to the actual environment, that means that we don't
	 * lookup in the predecessor environment.
	 * @param var the variable object which name is used for the search
	 * @return a variable object (with a model expression) or null, if no variable was found
	 */
	public IVariable lookupVariable(IVariable var) {
		int index = variables.indexOf(var);
		if (index == -1) return null;
		
		return variables.get(index);
	}
	
	/**
	 * Each environment holds a filename container that consists of a file reference and
	 * a test name. The test name can either be a name of a testcase or a name of a
	 * test suite. This method looks up for a filename container that consists of
	 * the two elements <i>file</i> and <i>testName</i>. If no filename container was
	 * found in the actual environment we go the parent environment and so. If found
	 * no filename container in the path to the root environment the method returns null.
	 * Otherwise we found a filename container with the information of the parameters. So
	 * we return a list of filename containers. These filename containers are those that we 
	 * have collected from each environment we have searched.
	 * If one of the parameters is null, a {@link InvalidArgumentException} is thrown.
	 * @param file the file reference to be searched
	 * @param testName the test name to be searched
	 * @throws InvalidArgumentException is thrown if one of the parameter is null
	 * @return null, if we don't find a corresponding filename container, otherwise a
	 * complete list with all filename containers 
	 */
	public List<FilenameContainer> lookupTestElement(File file, String testName) throws InvalidArgumentException {
		// First we check for the validity of the parameters.
		if (file == null) throw new InvalidArgumentException("file", "null");
		if (testName == null) throw new InvalidArgumentException("testName", "null");
		
		// We create a filename container list that we use to invoke the internal method.
		List<FilenameContainer> containerList = new ArrayList<FilenameContainer>();
		
		// We create from the parameters a filename container.
		FilenameContainer newCon = new FilenameContainer(file, testName);
		
		// We invoke the internal representation of this method.
		return lookupTestElementInternal(containerList, newCon);
	}
	
	/**
	 * This method is an internal representation of the original method
	 * <i>lookupTestElement</i> except that the original method is user
	 * friendly. Here we have a parameter more, namely <i>conList</i>, and
	 * the parameters <i>file</i> and <i>testName</i> are gone for the parameter
	 * <i>con</i>. This list keeps track of all filename containers that we have
	 * visited so far.
	 * If we find the filename container <i>con</i> in one of the filename
	 * container in any of the environments hierarchy, we give the whole list
	 * of filename containers back. With this information we can create better
	 * user feeback, to inform the user precisely where the cycle is.
	 * @param conList the list of filename container that holds all of the visited container
	 * @param con the container that we search for in the environment hierarchy
	 * @return null, if we don't find <i>con</i> in the hierachy, otherwise the complete list of containers
	 * @throws InvalidArgumentException are thrown if the parameters are null
	 */
	private List<FilenameContainer> lookupTestElementInternal(List<FilenameContainer> conList, FilenameContainer con) throws InvalidArgumentException {
		// We make sure that we have concrete parameters.
		if (con == null) throw new InvalidArgumentException("con", "null");
		if (conList == null) throw new InvalidArgumentException("conList", "null");
		
		/* 
		 * First we add the test element of this environment to the
		 * filename container list.
		 */
		conList.add(testElement);
		
		/* 
		 * Before we can check for the test element, we must check
		 * whether the test elements exists. That can be the case
		 * when this is the first environment. Then no test element was
		 * already be set.
		 * The test element of this environment is equal to
		 * the con-parameter. So we have detected a cycle. That means
		 * that we return the filename container list to give
		 * the user feeback.
		 */
		if (testElement != null) {
			if (this.testElement.equals(con)) {
				return conList;
			}
		}
		
		
		// We are in the parent environment.
		if (this.predecessor == null) {
			/*
			 * The check whether we have a cycle is already be done.
			 * So we know that we have at this point no cycle, so we
			 * can return null. 
			 */
			return null;
		}
		
		/* 
		 * We are not the parent environment. So we look up in
		 * the predecessor environment.
		 */
		return this.predecessor.lookupTestElementInternal(conList, con);
	}
	
	/**
	 * Add a test element name to the environment. If the name already exists
	 * nothing happens.
	 * @param elem the name to be added
	 */
	/*public void addTestElement(FilenameContainer elem) {
		if (!(testElements.contains(elem))) {
			testElements.add(elem);
		}
	}*/

	/**
	 * Returns the file object saved in the environment. If the object
	 * is not found in the current environment, we look up in the parent
	 * environment and so on. If no parent environment exists any more and
	 * no file object was found, we return 'null'.
	 * @return
	 */
	public File getFile() {
		if (file != null) return file;
		
		if (predecessor != null) return predecessor.getFile();
		
		return null;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	public IEnvironment nestedEnvironment() {
		Environment env = new Environment(factory);
		env.setPredecessor(this);
		return env;
	}

	public Environment getPredecessor() {
		return predecessor;
	}

	public void setPredecessor(Environment predecessor) {
		this.predecessor = predecessor;
	}

	public FilenameContainer getTestElement() {
		return testElement;
	}

	public void setTestElement(FilenameContainer testElement) {
		this.testElement = testElement;
	}

	public IGenModelFactory getFactory() {
		return factory;
	}
	
	public HashMap getTemplateHashMap() {
		/*
		 * We only need the first hash map of the
		 * environment chain.
		 */
		if (predecessor == null) return map;
		return getTemplateHashMap();
	}
	
	
}
