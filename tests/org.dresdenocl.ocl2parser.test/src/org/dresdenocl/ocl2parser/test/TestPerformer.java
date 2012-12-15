/*
    Copyright (C) 2007  Nils (s0006383@inf.tu-dresden.de)

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.dresdenocl.ocl2parser.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;
import org.eclipse.emf.common.util.URI;
import org.osgi.framework.Bundle;

import org.dresdenocl.facade.Ocl2ForEclipseFacade;
import org.dresdenocl.language.ocl.resource.ocl.Ocl22Parser;
import org.dresdenocl.model.IModel;
import org.dresdenocl.model.IModelProvider;
import org.dresdenocl.model.ModelAccessException;
import org.dresdenocl.model.metamodel.IMetamodel;
import org.dresdenocl.ocl2parser.test.exception.MetaModelNotFoundException;
import org.dresdenocl.parser.ParseException;
import org.dresdenocl.pivotmodel.Constraint;

/**
 * <p>
 * The {@link TestPerformer} is used to load {@link IMetamodel}s and
 * {@link IModel} used during testing.
 * </p>
 * 
 * @author Nils Thieme
 */
public class TestPerformer {

	/** The path to the customized, (locally in this project) saved Model Files. */
	protected final static String LOCAL_MODEL_FILE_DIRECTORY = "./src/testData/";

	/**
	 * The path of the basic directory that contains the OCL constraints used
	 * during testing.
	 */
	protected static String OCL_FILE_DIRECTORY = "resources/oclTestFiles/";

	/**
	 * Contains the instance of the {@link TestPerformer} in relation to the ID
	 * of their {@link IMetamodel}.
	 */
	protected static Map<String, TestPerformer> myInstances = null;

	/** The current {@link IMetamodel}. */
	protected IMetamodel myMetaModel = null;

	/** The current {@link IModel}. */
	protected IModel myModel = null;

	/**
	 * The ID of the {@link Bundle} used to provide the {@link IModel} for
	 * testing.
	 */
	protected String myModelBundle;

	/** The path of the {@link IModel} in its {@link Bundle}. */
	protected String myModelPath;

	/** The path to external {@link IModel} files. */
	protected String myRemoteModelFileDirectoryPath = null;

	/**
	 * <p>
	 * Creates a new {@link TestPerformer}.
	 * </p>
	 * 
	 * @param modelBundle
	 *            The ID of the {@link Bundle} of the {@link IModel} used for
	 *            testing.
	 * @param modelPath
	 *            The path of the {@link IModel} inside the {@link Bundle}.
	 */
	private TestPerformer(String modelBundle, String modelPath) {

		super();

		this.myModelBundle = modelBundle;
		this.myModelPath = modelPath;
	}

	/**
	 * <p>
	 * Returns the instance of the {@link TestPerformer} for a given
	 * {@link IMetamodel}.
	 * </p>
	 * 
	 * @param metamodelID
	 *            The ID of the {@link IMetamodel} whose {@link TestPerformer}
	 *            instance shall be returned.
	 * @param modelBundle
	 *            The id of the {@link Bundle} that provides the {@link IModel}
	 *            that shall be used.
	 * @param modelPath
	 *            The path of the directory of the {@link IModel} in its
	 *            {@link Bundle} .
	 * @return The {@link TestPerformer} instance for the given
	 *         {@link IMetamodel} .
	 * @throws MetaModelNotFoundException
	 *             Thrown if no {@link IMetamodel} for the given name can be
	 *             found.
	 */
	public static TestPerformer getInstance(String metamodelID,
			String modelBundle, String modelPath)
			throws MetaModelNotFoundException {

		TestPerformer result;
		result = null;

		/* Probably instantiate the map of instances. */
		if (myInstances == null) {
			myInstances = new HashMap<String, TestPerformer>();
		}

		/* Probably get the already existing instance. */
		else {
			result = myInstances.get(metamodelID + modelBundle + modelPath);
		}

		/* If not existing yet, create the instance. */
		if (result == null) {

			/* initialize TestPerformer. */
			result = new TestPerformer(modelBundle, modelPath);
			result.initializeMetamodel(metamodelID);

			/* Add TestPerformer to Cache. */
			myInstances.put(metamodelID + modelPath + modelPath, result);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Returns the {@link IModel} instance used during testing.
	 * </p>
	 * 
	 * @return The {@link IModel} instance used during testing.
	 */
	public IModel getCurrentModel() {

		return this.myModel;
	}

	/**
	 * <p>
	 * Parses a given OCL file against the loaded {@link IModel} file. If an
	 * error occurred an {@link Exception} will be thrown.
	 * </p>
	 * 
	 * @param filename
	 *            The name of OCL {@link File} to be parsed.
	 * @throws Ocl2ParsingException
	 *             Thrown, if a exception occurred during parsing.
	 * @throws FileNotFoundException
	 *             Thrown, if the required {@link File} cannot be found.
	 * @return A {@link List} containing the parsed {@link Constraint}s as
	 *         specified in the parsed file.
	 */
	public List<Constraint> parseFile(String filename) throws ParseException,
			FileNotFoundException {

		return this.parseFile(filename, false);
	}

	/**
	 * <p>
	 * Parses a given OCL file against the loaded {@link IModel} file. If an
	 * error occurred an {@link Exception} will be thrown.
	 * </p>
	 * 
	 * @param filename
	 *            The name of OCL {@link File} to be parsed.
	 * @param addToModel
	 *            Indicates whether or not to add the constraints to the
	 *            {@link IModel} after parsing.
	 * @throws Ocl2ParsingException
	 *             Thrown, if a exception occurred during parsing.
	 * @throws FileNotFoundException
	 *             Thrown, if the required {@link File} cannot be found.
	 * @return A {@link List} containing the parsed {@link Constraint}s as
	 *         specified in the parsed file.
	 */
	public List<Constraint> parseFile(String filename, boolean addToModel)
			throws ParseException, FileNotFoundException {

		File oclFile;
		try {
			oclFile = AbstractDresdenOclTest.getFile(OCL_FILE_DIRECTORY
					+ filename, Activator.PLUGIN_ID);
		} catch (IOException e) {
			throw new FileNotFoundException(e.getMessage());
		}
		URI uri = URI.createFileURI(oclFile.getAbsolutePath());

		/* Not replaced by facade method to test the different exception types. */
		return Ocl22Parser.INSTANCE.doParse(this.myModel, uri, addToModel);
	}

	/**
	 * <p>
	 * Wrapper for {@link TestPerformer#setModel(String, boolean)} which sets
	 * the second argument to false; thus using the remote location (
	 * {@link TestPerformer#MODEL_BUNDLE}/{@link TestPerformer#MODEL_DIRECTORY})
	 * for loading the {@link IModel}.
	 * </p>
	 * 
	 * @param modelName
	 *            The name of file containing the {@link IModel}.
	 * @throws ModelAccessException
	 *             Thrown from {@link IModelProvider#getModel(String)}
	 * @throws FileNotFoundException
	 *             Thrown if the {@link IModel} has not been found.
	 */
	public void setModel(String modelName) throws ModelAccessException,
			FileNotFoundException {

		this.setModel(modelName, false);
	}

	/**
	 * <p>
	 * This method loads an {@link IModel} from the specified file. The path to
	 * the file is either {@link #MODEL_BUNDLE}/{@link #MODEL_DIRECTORY}/ (
	 * <code>local == false</code>) or {@link #LOCAL_MODEL_FILE_DIRECTORY} (
	 * <code>local == true</code>).
	 * 
	 * @param modelName
	 *            The name of file containing the {@link IModel}.
	 * @param local
	 *            Flag for the path to a {@link IModel}.
	 * @throws ModelAccessException
	 *             Thrown from {@link IModelProvider#getModel(String)}
	 * @throws FileNotFoundException
	 *             Thrown if the {@link IModel} has not been found.
	 */
	public void setModel(String modelName, boolean local)
			throws ModelAccessException, FileNotFoundException {

		File modelFile;
		try {
			modelFile = AbstractDresdenOclTest.getFile(this.myModelPath
					+ modelName, Activator.PLUGIN_ID);
		} catch (IOException e) {
			throw new FileNotFoundException(e.getMessage());
		}

		this.setModel(modelFile);
	}

	/**
	 * <p>
	 * This method initializes the {@link IMetamodel} used during testing.
	 * </p>
	 * 
	 * @throws MetaModelNotFoundException
	 *             Thrown if the {@link IMetamodel} has not been found.
	 */
	private void initializeMetamodel(String metamodelName)
			throws MetaModelNotFoundException {

		this.myMetaModel = Ocl2ForEclipseFacade.getMetaModel(metamodelName);
	}

	/**
	 * <p>
	 * Loads the {@link IModel} from a specified location.
	 * </p>
	 * 
	 * @param modelFile
	 *            The file containing the {@link IModel}.
	 * @throws ModelAccessException
	 *             Thrown from {@link IModelProvider#getModel(File)}
	 * @throws FileNotFoundException
	 *             Thrown, if the {@link IModel} file has not been found.
	 */
	private void setModel(File modelFile) throws ModelAccessException,
			FileNotFoundException {

		this.myModel = Ocl2ForEclipseFacade.getModel(modelFile,
				this.myMetaModel);
	}
}