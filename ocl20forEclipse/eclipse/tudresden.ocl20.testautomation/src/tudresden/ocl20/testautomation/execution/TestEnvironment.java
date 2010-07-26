/*
Copyright (C) 2009 by Franz Eichhorn (franz.eichhorn@gmx.de)

This file is part of the OCL Interpreter Test Suite of Dresden OCL2 for
Eclipse.

Dresden OCL2 for Eclipse is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL2 for Eclipse is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>.
 */
package tudresden.ocl20.testautomation.execution;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.osgi.framework.Bundle;

import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.modelinstance.IModelInstance;
import tudresden.ocl20.testautomation.tools.StatementDefinition;

/**
 * the test environment stores data which is accessed by different objects but
 * doesn't belong in one of these object's space
 */
public class TestEnvironment {

	// absolute directory of the bundle.
	public String fileDirectory;

	// list of statements
	public List<StatementDefinition> loadedStatements;

	// all loaded model instances
	// since the testperformer is used by differend test cases the instances
	// can be reused
	public Map<URI, IModelInstance> loadedInstances;

	/**
	 * In order to reuse already loaded models they're stored here. Since we don't
	 * know in which order the constraints are executed, active models must be
	 * changed on the fly.
	 */
	public Map<URI, IModel> loadedModels;

	/**
	 * Instantiates a new test environment beta.
	 * 
	 * @param name
	 */
	public TestEnvironment() {

		this.loadedInstances = new HashMap<URI, IModelInstance>();
		
		this.loadedModels = new HashMap<URI, IModel>();

		this.loadedStatements = new LinkedList<StatementDefinition>();

	}

	/**
	 * Inits the file directory.
	 * 
	 * @param bundle
	 */

	public void initFileDirectory(Bundle bundle) {

		// Get the bundle location for the model files.
		this.fileDirectory = bundle.getLocation();

		// Remove the 'reference:file:' from the beginning.
		this.fileDirectory = this.fileDirectory.substring(15);
	}

	/**
	 * 
	 * @param modelFile
	 * @return returns the model connected to the model file if exists, or null.
	 */
	public IModel getModelIfLoaded(URI modelPath) {

		return this.loadedModels.get(modelPath);
	}

	public void addModel(URI modelPath, IModel model) {

		assert (this.getModelIfLoaded(modelPath) == null);

		this.loadedModels.put(modelPath, model);
	}
	
	/**
	 * 
	 * @param modelFile
	 * @return returns the model instance or null if it doesnt exist
	 */
	public IModelInstance getModelInstanceIfLoaded(URI filePath) {

		return this.loadedInstances.get(filePath);
	}

	public void addModelInstance(URI filePath, IModelInstance model) {

		assert (this.getModelInstanceIfLoaded(filePath) == null);

		this.loadedInstances.put(filePath, model);
	}

	/**
	 * Stores a constriant definition under it's name qualified name. Assert that
	 * a name is not used twice!
	 * 
	 * @param packageInfo
	 * @param specification
	 */
	public void storeStatementDefinition(String packageInfo,
			String specification, String name) {

		StatementDefinition statDef =
				new StatementDefinition(packageInfo, name, specification);

		// assert we have a unique name for each statement
		assert (!this.loadedStatements.contains(statDef));

		this.loadedStatements.add(statDef);

	}
}
