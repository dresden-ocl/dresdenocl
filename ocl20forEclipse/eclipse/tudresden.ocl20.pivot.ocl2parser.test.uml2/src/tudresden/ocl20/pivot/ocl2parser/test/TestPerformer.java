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

package tudresden.ocl20.pivot.ocl2parser.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.Platform;

import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.metamodel.IMetamodel;
import tudresden.ocl20.pivot.modelbus.model.IModel;
import tudresden.ocl20.pivot.modelbus.model.IModelProvider;
import tudresden.ocl20.pivot.ocl2parser.parser.OCL2Parser;
import tudresden.ocl20.pivot.ocl2parser.parser.exceptions.BuildingASTException;
import tudresden.ocl20.pivot.ocl2parser.parser.exceptions.LexException;
import tudresden.ocl20.pivot.ocl2parser.parser.exceptions.ParsingException;
import tudresden.ocl20.pivot.ocl2parser.parser.exceptions.SemanticException;

/**
 * TODO obsolete JavaDoc This class is a singleton. It loads the metamodel
 * repository with the uml metamodel. Also it loads the model file
 * <i>LoyalRoyalOCL2Parser_4.xmi</i>. With the <i>parse</i>-method an ocl file
 * can be parsed against the loaded ocl model. With the the method
 * <i>getLoyaltyProgramModel</i> the model instance can be received.
 * 
 * @author Nils
 * 
 */
public class TestPerformer {

	protected String model_bundle;
	protected String model_bundle_path;
	protected IMetamodel metaModel = null;
	protected IModelProvider modelProvider = null;
	protected IModel model = null;
	/**
	 * The path to the OCL files.
	 */
	protected String oclFileDirectory = "./src/testData/";
	/**
	 * The path to the Modelfiels in the
	 * tudresden.ocl20.pivot.examples.royalandloyal project.
	 */
	protected String remoteModelFileDirectory = null;
	/**
	 * The path to the customized, locally (in this project) saved Model Files.
	 */
	protected String localModelFileDirectory = "./src/testData/";
	/**
	 * TestPerformer Cache
	 */
	protected static Map<String, TestPerformer> theInstances = null;

	/**
	 * This constructor loads the uml metamodel.
	 * 
	 * @throws Exception
	 *           is thrown if any error occurred while loading the model or the
	 *           metamodel
	 */
	private TestPerformer(String model_bundle, String model_bundle_path) {

		super();
		this.model_bundle = model_bundle;
		this.model_bundle_path = model_bundle_path;
	}

	/**
	 * With this method the singleton instance can be received. With the argument
	 * a metamodel can be specified.
	 * 
	 * @param metamodelName
	 *          the name of the metamodel
	 * @return the singleton instance with the given metamodel instance
	 *         initialized
	 * @throws MetaModelNotFoundException
	 *           if metaModelName is invalid
	 */
	public static TestPerformer getInstance(String metamodelName,
			String model_bundle, String model_bundle_path)
			throws MetaModelNotFoundException {

		TestPerformer currentTestPerformer = null;
		if (theInstances == null) {
			theInstances = new HashMap<String, TestPerformer>();
		}
		else {
			currentTestPerformer =
					theInstances.get(metamodelName + model_bundle + model_bundle_path);
		}
		if (currentTestPerformer == null) {
			// initialize TestPerformer
			currentTestPerformer = new TestPerformer(model_bundle, model_bundle_path);
			// TODO insert proper Exception Handling
			currentTestPerformer.initializeMetamodel(metamodelName);
			// add TestPerformer to Cache
			theInstances.put(metamodelName + model_bundle_path + model_bundle_path,
					currentTestPerformer);
		}
		return currentTestPerformer;
	}

	/**
	 * Wrapper for setModell(String modelName, booelan local) which sets the
	 * second argument to false; thus using the remote location (
	 * {@link TestPerformer#MODEL_BUNDLE}/{@link TestPerformer#MODEL_BUNDLE_PATH})
	 * for loading the model.
	 * 
	 * @param modelName
	 *          The name of file containing the model.
	 * @param pathToModel
	 *          The path to the models file.
	 * @throws ModelAccessException
	 *           from tudresden.ocl20.pivot.modelbus.IModelProvider.getModel(File)
	 * @throws FileNotFoundException
	 *           the model file is not found
	 */
	public void setModel(String modelName) throws ModelAccessException,
			FileNotFoundException {

		setModel(modelName, false);
	}

	/**
	 * This method loads a model from the specified file. The path to the file is
	 * either {@link #MODEL_BUNDLE}/{@link #MODEL_BUNDLE_PATH}/ (loacl=false) or
	 * {@link #localModelFileDirectory} (local=true).
	 * 
	 * @param modelName
	 *          The name of file containing the model.
	 * @param local
	 *          Flag for the path to a model.
	 * @throws ModelAccessException
	 *           from tudresden.ocl20.pivot.modelbus.IModelProvider.getModel(File)
	 * @throws FileNotFoundException
	 *           the model file is not found
	 */
	public void setModel(String modelName, boolean local)
			throws ModelAccessException, FileNotFoundException {

		String pathToModel;
		if (local)
			pathToModel = localModelFileDirectory;
		else {
			/* Get the bundle location for the model files. */
			pathToModel = Platform.getBundle(model_bundle).getLocation();

			/*
			 * Remove the 'reference:file:' from the beginning and add path in remote
			 * location.
			 */
			pathToModel = pathToModel.substring(15) + model_bundle_path;
		}

		setModel(modelName, pathToModel);
	}

	/**
	 * Loads the Model from a specified location.
	 * 
	 * @param modelName
	 *          The name of file containing the model.
	 * @param pathToModel
	 *          The path to the models file.
	 * @throws ModelAccessException
	 *           from tudresden.ocl20.pivot.modelbus.IModelProvider.getModel(File)
	 * @throws FileNotFoundException
	 *           the model file is not found
	 */
	private void setModel(String modelName, String pathToModel)
			throws ModelAccessException, FileNotFoundException {

		modelProvider = metaModel.getModelProvider();

		File modelFile = new File(pathToModel + modelName);
		if (!modelFile.exists()) {
			throw new FileNotFoundException(
					"The model file doesn't exists. The file name was: "
							+ remoteModelFileDirectory + modelName);
		}
		model = modelProvider.getModel(modelFile);
	}

	/**
	 * Parse the file <i>filename</i> against the loaded model file. If an error
	 * occurred an Exception will be thrown.
	 * 
	 * @param filename
	 *          the ocl file to be parsed
	 * @throws SemanticException
	 * @throws BuildingASTException
	 * @throws IOException
	 * @throws LexException
	 * @throws ParsingException
	 */
	public void parseFile(String filename) throws FileNotFoundException,
			ParsingException, LexException, IOException, BuildingASTException,
			SemanticException {

		File oclFile = new File(oclFileDirectory + filename);
		if (!oclFile.exists())
			throw new FileNotFoundException(
					"The ocl test file doesn't exists. File name: " + oclFileDirectory
							+ filename);

		FileReader oclFileReader = new FileReader(oclFile);

		OCL2Parser parser = new OCL2Parser(model, oclFileReader);
		parser.parse();

	}

	/**
	 * Get the model instance of the loaded model.
	 * 
	 * @return the model instance of the loaded model
	 */
	public IModel getCurrentModel() {

		return model;
	}

	/**
	 * This method initializes the uml metamodel.
	 * 
	 * @throws MetaModelNotFoundException
	 *           is thrown if the metamodel is not found
	 */
	private void initializeMetamodel(String metamodelName)
			throws MetaModelNotFoundException {

		// TODO insert proper Exception Handling
		// umlMetaModel =
		// ModelBusPlugin.getMetamodelRegistry().getMetamodel("tudresden.ocl20.pivot.metamodels.uml");
		metaModel =
				ModelBusPlugin.getMetamodelRegistry().getMetamodel(metamodelName);
		if (metaModel == null) {
			throw new MetaModelNotFoundException("Unable to load uml metamodel \""
					+ metamodelName + "\"");
		}
	}
}
