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

package tudresden.ocl20.pivot.ocl2parser.test.parsertests;

import java.io.File;
import java.io.FileReader;

import tudresden.ocl20.pivot.modelbus.IMetamodel;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelProvider;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.ocl2parser.parser.OCL2Parser;

/**
 * This class is a singleton. It loads the metamodel repository with the uml metamodel.
 * Also it loads the model file <i>LoyalRoyalOCL2Parser_4.xmi</i>. With the <i>parse</i>-method
 * an ocl file can be parsed against the loaded ocl model. With the the method
 * <i>getLoyaltyProgramModel</i> the model instance can be received.
 * @author Nils
 *
 */
public class TestPerformer {
	protected IMetamodel metaModel = null;
	protected IModelProvider modelProvider = null;
	protected IModel model = null;
	protected String fileDirectory = null;
	static protected TestPerformer self = null;
	
	/**
	 * This constructor loads the uml metamodel.
	 * @throws Exception is thrown if any error occurred while loading the model or the metamodel
	 */
	private TestPerformer() throws Exception {
		super();
		
		//initializeMetamodel();
		
		
	}
	
	/**
	 * With this method the singleton instance can be received. This instance is initialized
	 * with the NetBeans UML Metamodel.
	 * @return the singleton instance
	 * @throws Exception is thrown if any error occurred
	 */
	static public TestPerformer getDefault() throws Exception {
		if (self == null) self = new TestPerformer();
		
		self.initializeMetamodel("tudresden.ocl20.pivot.metamodels.uml");
		
		return self;
	}
	
	/**
	 * With this method the singleton instance can be received. With the argument a
	 * metamodel can be specified.
	 * @param metamodelName the name of the metamodel
	 * @return the singleton instance with the given metamodel instance initialized
	 * @throws Exception is thrown if any error occurred
	 */
	static public TestPerformer getDefault(String metamodelName) throws Exception {
		if (self == null) self = new TestPerformer();
		
		self.initializeMetamodel(metamodelName);
		
		return self;
	}
	
	/**
	 * With this method a model (in uml) can be set. This method must be
	 * invoked before the <i>parseFile</i> method.
	 * @param modelName the filename of the uml model that is located in the <i>./src/testdata</i> directory
	 * @throws Exception is thrown if the model cannot be initialized or the model file is not found
	 */
	public void setModel(String modelName) throws Exception {
		/*if (model != null) {
			if (model.getDisplayName().equals(umlModelName)) return;
		}*/
		
		//initializeMetamodel(metaModel.getId());
		modelProvider = metaModel.getModelProvider();
		File currentDir = new File(".");
		//System.out.println("Current directory: " + currentDir.getAbsolutePath());
		fileDirectory = "./src/testData/";
		File modelFile = new File(fileDirectory + modelName);
		if (!modelFile.exists()) throw new Exception("The model file doesn't exists. The file name was: " + fileDirectory + modelName);
		
		model = modelProvider.getModel(modelFile);
	}
	
	/**
	 * Parse the file <i>filename</i> against the loaded model file. If
	 * an error occurred an Exception will be thrown.
	 * @param filename the ocl file to be parsed
	 * @throws Exception is thrown if any error occurs
	 */
	public void parseFile(String filename) throws Exception {
		
		File oclFile = new File(fileDirectory + filename);
		if (!oclFile.exists()) throw new Exception("The ocl test file doesn't exists. File name: " + filename);
		
		FileReader oclFileReader = new FileReader(oclFile);
		
		OCL2Parser parser = new OCL2Parser(model, oclFileReader);
		parser.parse();
		
	}
	
	/**
	 * Get the model instance of the loaded model.
	 * @return the model instance of the loaded model
	 */
	public IModel getCurrentModel() {
		return model;
	}
	
	/**
	 * This method initializes the uml metamodel.
	 * @throws Exception is thrown if the metamodel is not found
	 */
	private void initializeMetamodel(String metamodelName) throws Exception {
		try {
			//umlMetaModel = ModelBusPlugin.getMetamodelRegistry().getMetamodel("tudresden.ocl20.pivot.metamodels.uml");
			metaModel = ModelBusPlugin.getMetamodelRegistry().getMetamodel(metamodelName);
			if (metaModel == null) {
				throw new Exception("Unable to load uml metmodel.   ");
			}
		} catch(Exception ex) {
			throw new Exception("Unable to load uml metmodel.   " + ex.getMessage());
		}
	}
}
