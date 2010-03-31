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
package tudresden.ocl20.benchmark.common;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.osgi.framework.Bundle;

import tudresden.ocl20.benchmark.common.StatementDefinition;
import tudresden.ocl20.pivot.modelinstance.IModelInstance;

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
	public Map<String, IModelInstance> loadedInstances;

	/**
	 * Instantiates a new test environment beta.
	 * 
	 * @param name
	 */
	public TestEnvironment() {

		this.loadedInstances = new HashMap<String, IModelInstance>();

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
	 * Stores a constriant definition under it's name qualified name. Assert that
	 * a name is not used twice!
	 * 
	 * @param packageInfo 
	 * @param specification 
	 */
	public void storeStatementDefinition(String packageInfo, String specification, String name) {

		StatementDefinition statDef =
				new StatementDefinition(packageInfo, name,
						specification);

		// assert we have a unique name for each statement
		assert (!this.loadedStatements.contains(statDef));

		this.loadedStatements.add(statDef);

	}
}
