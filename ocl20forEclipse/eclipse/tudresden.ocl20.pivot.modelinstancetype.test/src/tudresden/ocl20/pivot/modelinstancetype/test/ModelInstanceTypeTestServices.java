/*
Copyright (C) 2009 by Claas Wilke (info@claaswilke.de)

This file is part of the Generic Model Instance Type Test Suite of Dresden 
OCL2 for Eclipse.

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
package tudresden.ocl20.pivot.modelinstancetype.test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.Bundle;

import tudresden.ocl20.pivot.facade.Ocl2ForEclipseFacade;
import tudresden.ocl20.pivot.metamodels.uml2.UML2MetamodelPlugin;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.metamodel.IMetamodel;
import tudresden.ocl20.pivot.modelbus.model.IModel;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceProvider;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceType;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.TypeConstants;
import tudresden.ocl20.pivot.modelinstancetype.test.msg.ModelInstanceTypeTestSuiteMessages;
import tudresden.ocl20.pivot.modelinstancetype.test.testmodel.TestModelTypesNames;
import tudresden.ocl20.pivot.pivotmodel.PivotModelFactory;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * This class provides some services required for {@link IMetamodel} testing.
 * </p>
 * 
 * <p>
 * This class implements the <code>Singleton</code> pattern.
 * </p>
 * 
 * @author Claas Wilke
 */
public final class ModelInstanceTypeTestServices {

	/** The ID of the {@link IMetamodel} which shall be tested. */
	private static final String META_MODEL_ID = UML2MetamodelPlugin.ID;

	/**
	 * The ID of the {@link Bundle} providing the {@link IModel} which shall be
	 * used for testing.
	 */
	private static final String MODEL_BUNDLE_ID =
			ModelInstanceTypeTestPlugin.PLUGIN_ID;

	/**
	 * The path of the {@link IModel} which shall be used for testing.
	 */
	private static final String MODEL_PATH = "resources/testmodel.uml";

	/** The only instance of {@link ModelInstanceTypeTestServices}. */
	private static ModelInstanceTypeTestServices myInstance;

	/**
	 * Contains the already loaded {@link IModelInstance}s identified by their
	 * {@link File} represented as a {@link String}.
	 */
	private Map<String, IModelInstance> myCachedModelInstances =
			new HashMap<String, IModelInstance>();

	/**
	 * The {@link IModel}s used during testing;
	 */
	private IModel myModel;

	/**
	 * The ID of the {@link Bundle} providing the {@link IModelInstance} which
	 * shall be tested.
	 */
	private String myModelInstanceBundleId;

	/** The path of the {@link IModelInstance} which shall be tested. */
	private String myModelInstancePath;

	/** The ID of the {@link IModelInstanceType} which shall be tested. */
	private String myModelInstanceTypeId;

	/**
	 * Indicates, how many boolean {@link Property}s the
	 * <code>PrimitiveTypeProviderClass</code> has.
	 */
	private int myBooleanPropertyCounter = 1;

	/**
	 * Indicates, how many integer {@link Property}s the
	 * <code>PrimitiveTypeProviderClass</code> has.
	 */
	private int myIntegerPropertyCounter = 1;

	/**
	 * Indicates, how many integer {@link Property}s the
	 * <code>PrimitiveTypeProviderClass</code> has.
	 */
	private int myRealPropertyCounter = 1;

	/**
	 * Indicates, how many integer {@link Property}s the
	 * <code>PrimitiveTypeProviderClass</code> has.
	 */
	private int myStringPropertyCounter = 1;

	/**
	 * Indicates, how many bag {@link Property}s the
	 * <code>CollectionTypeProviderClass</code> has.
	 */
	private int myBagPropertyCounter = 1;

	/**
	 * Indicates, how many OrderedSet {@link Property}s the
	 * <code>CollectionTypeProviderClass</code> has.
	 */
	private int myOrderedSetPropertyCounter = 1;

	/**
	 * Indicates, how many Sequence {@link Property}s the
	 * <code>CollectionTypeProviderClass</code> has.
	 */
	private int mySequencePropertyCounter = 1;

	/**
	 * Indicates, how many Set {@link Property}s the
	 * <code>CollectionTypeProviderClass</code> has.
	 */
	private int mySetPropertyCounter = 1;

	/**
	 * <p>
	 * The private constructor.
	 * </p>
	 */
	private ModelInstanceTypeTestServices() {

	}

	public static ModelInstanceTypeTestServices getInstance() {

		/* Probably create the instance. */
		if (myInstance == null) {
			myInstance = new ModelInstanceTypeTestServices();
		}
		// no else.

		return myInstance;
	}

	/**
	 * <p>
	 * Indicates, how many bag {@link Property}s the
	 * <code>CollectionTypeProviderClass</code> has.
	 * </p>
	 * 
	 * @return How many bag {@link Property}s the
	 *         <code>CollectionTypeProviderClass</code> has.
	 */
	public int getBagPropertyCounter() {

		return myBagPropertyCounter;
	}

	/**
	 * <p>
	 * Indicates, how many boolean {@link Property}s the
	 * <code>PrimitiveTypeProviderClass</code> has.
	 * </p>
	 * 
	 * @return Indicates, how many boolean {@link Property}s the
	 *         <code>PrimitiveTypeProviderClass</code> has.
	 */
	public int getBooleanPropertyCounter() {

		return this.myBooleanPropertyCounter;
	}

	/**
	 * <p>
	 * Returns an empty {@link IModelInstance} of the current
	 * {@link IModelInstanceType} under test.
	 * </p>
	 * 
	 * @return An empty {@link IModelInstance}.
	 */
	public IModelInstance getEmptyModelInstance() {

		IModelInstance result;

		/* Load the IModel first. */
		if (this.myModel == null) {
			this.loadModel();
		}
		// no else.

		/* Check if the model has been loaded. */
		if (this.myModel != null) {

			try {
				result =
						Ocl2ForEclipseFacade.getEmptyModelInstance(this.myModel,
								this.myModelInstanceTypeId);
			}
			// end try.

			catch (IllegalArgumentException e) {
				throw new RuntimeException(e.getMessage(), e);
			}

			catch (ModelAccessException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
			// end catch.
		}

		else {
			String msg;

			msg =
					ModelInstanceTypeTestSuiteMessages.ModelInstanceTypeTestSuite_Services_ModelWasNull;

			throw new RuntimeException(msg);
		}

		return result;
	}

	/**
	 * <p>
	 * Indicates, how many integer {@link Property}s the
	 * <code>PrimitiveTypeProviderClass</code> has.
	 * </p>
	 * 
	 * @return Indicates, how many integer {@link Property}s the
	 *         <code>PrimitiveTypeProviderClass</code> has.
	 */
	public int getIntegerPropertyCounter() {

		return this.myIntegerPropertyCounter;
	}

	/**
	 * <p>
	 * Returns all {@link IModelInstanceObject}s of a given {@link Type} that are
	 * part of the current {@link IModelInstance} under test.
	 * </p>
	 * 
	 * @param type
	 *          The {@link Type} whose {@link IModelInstanceObject}s shall be
	 *          returned.
	 * @return All {@link IModelInstanceObject}s of the given {@link Type}, if
	 *         any.
	 */
	public Set<IModelInstanceObject> getModelInstanceObjectsOfType(Type type) {

		return this.getModelInstance().getAllInstances(type);
	}

	/**
	 * <p>
	 * Returns the {@link IModelInstance} which can be loaded by the current
	 * settings of {@link ModelInstanceTypeTestServices#myModelInstanceTypeId},
	 * {@link ModelInstanceTypeTestServices#myModelInstanceBundleId}, and
	 * {@link ModelInstanceTypeTestServices#myModelInstancePath}.
	 * </p>
	 * 
	 * @return The current {@link IModelInstance} or <code>null</code>.
	 */
	public IModelInstance getModelInstance() {

		IModelInstance result;

		String bundleDirectory;
		File modelInstanceFile;

		/* Load the IModel first. */
		if (this.myModel == null) {
			this.loadModel();
		}
		// no else.

		/* Get the bundle location for the model instance files. */
		bundleDirectory =
				Platform.getBundle(this.myModelInstanceBundleId).getLocation();

		/* Remove the 'reference:file:' from the beginning. */
		bundleDirectory = bundleDirectory.substring(15);

		modelInstanceFile = new File(bundleDirectory + this.myModelInstancePath);

		/* Check if the model instance has already been loaded. */
		if (this.myCachedModelInstances.containsKey(modelInstanceFile.toString())) {
			result = this.myCachedModelInstances.get(modelInstanceFile.toString());
		}

		/* Else check if the given file does not exist. */
		else if (!modelInstanceFile.exists()) {
			String msg;

			msg =
					ModelInstanceTypeTestSuiteMessages.ModelInstanceTypeTestSuite_Services_ModelInstanceFileNotFound;
			msg =
					NLS.bind(msg, this.myModelInstancePath, this.myModelInstanceBundleId);

			throw new RuntimeException(msg);
		}

		/* Else try to load the model instance. */
		else {

			try {
				/* Check if the model has been loaded. */
				if (this.myModel != null) {

					result =
							Ocl2ForEclipseFacade.getModelInstance(modelInstanceFile,
									this.myModel, this.myModelInstanceTypeId);

					/* Cache the result. */
					this.myCachedModelInstances.put(modelInstanceFile.toString(), result);
				}

				else {
					String msg;

					msg =
							ModelInstanceTypeTestSuiteMessages.ModelInstanceTypeTestSuite_Services_ModelWasNull;

					throw new RuntimeException(msg);
				}
			}

			catch (ModelAccessException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		// end else.

		return result;
	}

	/**
	 * <p>
	 * Returns the {@link IModelInstanceProvider} of the current
	 * {@link IModelInstanceType} under test.
	 * </p>
	 * 
	 * @return The {@link IModelInstanceProvider} of the current
	 *         {@link IModelInstanceType} under test.
	 */
	public IModelInstanceProvider getModelInstanceProvider() {

		return ModelBusPlugin.getModelInstanceTypeRegistry().getModelInstanceType(
				this.myModelInstanceTypeId).getModelInstanceProvider();
	}

	/**
	 * <p>
	 * Searches for a {@link Type} in the current {@link IModel} under test.
	 * </p>
	 * 
	 * @param qualifiedName
	 *          The qualified name of the {@link Type} which shall be returned.
	 * @return The found {@link Type}.
	 */
	public Type getModelType(String qualifiedName) {

		Type result;

		/* Probably load the model. */
		if (this.myModel == null) {
			this.loadModel();
		}
		// no else.

		/* Get the type to test. */
		try {
			result =
					this.myModel
							.findType(convertQualifiedNameToQualifiedNameList(qualifiedName));
		}

		catch (ModelAccessException e) {
			throw new RuntimeException(e.getMessage());
		}

		return result;
	}

	/**
	 * <p>
	 * Returns the {@link IModel} used for testing.
	 * </p>
	 * 
	 * @return The {@link IModel} used for testing.
	 */
	public IModel getModelUnderTest() {

		return this.myModel;
	}

	/**
	 * <p>
	 * Indicates, how many OrderedSet {@link Property}s the
	 * <code>CollectionTypeProviderClass</code> has.
	 * </p>
	 * 
	 * @return How many OrderedSet {@link Property}s the
	 *         <code>CollectionTypeProviderClass</code> has.
	 */
	public int getOrderedSetPropertyCounter() {

		return myOrderedSetPropertyCounter;
	}

	/**
	 * <p>
	 * Indicates, how many real {@link Property}s the
	 * <code>PrimitiveTypeProviderClass</code> has.
	 * </p>
	 * 
	 * @return Indicates, how many real {@link Property}s the
	 *         <code>PrimitiveTypeProviderClass</code> has.
	 */
	public int getRealPropertyCounter() {

		return this.myRealPropertyCounter;
	}

	/**
	 * <p>
	 * Indicates, how many Sequence {@link Property}s the
	 * <code>CollectionTypeProviderClass</code> has.
	 * </p>
	 * 
	 * @return How many Sequence {@link Property}s the
	 *         <code>CollectionTypeProviderClass</code> has.
	 */
	public int getSequencePropertyCounter() {

		return mySequencePropertyCounter;
	}

	/**
	 * <p>
	 * Indicates, how many Set {@link Property}s the
	 * <code>CollectionTypeProviderClass</code> has.
	 * </p>
	 * 
	 * @return How many Set {@link Property}s the
	 *         <code>CollectionTypeProviderClass</code> has.
	 */
	public int getSetPropertyCounter() {

		return mySetPropertyCounter;
	}

	/**
	 * <p>
	 * Indicates, how many string {@link Property}s the
	 * <code>PrimitiveTypeProviderClass</code> has.
	 * </p>
	 * 
	 * @return Indicates, how many string {@link Property}s the
	 *         <code>PrimitiveTypeProviderClass</code> has.
	 */
	public int getStringPropertyCounter() {

		return this.myStringPropertyCounter;
	}

	/**
	 * <p>
	 * Sets how many Bag {@link Property}s the
	 * <code>CollectionTypeProviderClass</code> has.
	 * </p>
	 * 
	 * @param bagPropertyCounter
	 *          How many bag {@link Property}s the
	 *          <code>CollectionTypeProviderClass</code> has.
	 */
	public void setBagPropertyCounter(int bagPropertyCounter) {

		this.myBagPropertyCounter = bagPropertyCounter;
	}

	/**
	 * <p>
	 * Sets how many boolean {@link Property}s the
	 * <code>PrimitiveTypeProviderClass</code> has.
	 * </p>
	 * 
	 * @param booleanPropertyCounter
	 *          The count how many boolean {@link Property}s the
	 *          <code>PrimitiveTypeProviderClass</code> has.
	 */
	public void setBooleanPropertyCounter(int booleanPropertyCounter) {

		this.myBooleanPropertyCounter = booleanPropertyCounter;
	}

	/**
	 * <p>
	 * Sets how many OrderedSet {@link Property}s the
	 * <code>CollectionTypeProviderClass</code> has.
	 * </p>
	 * 
	 * @param orderedSetPropertyCounter
	 *          How many OrderedSet {@link Property}s the
	 *          <code>CollectionTypeProviderClass</code> has.
	 */
	public void setOrderedSetPropertyCounter(int orderedSetPropertyCounter) {

		this.myOrderedSetPropertyCounter = orderedSetPropertyCounter;
	}

	/**
	 * <p>
	 * Sets how many Integer {@link Property}s the
	 * <code>PrimitiveTypeProviderClass</code> has.
	 * </p>
	 * 
	 * @param integerPropertyCounter
	 *          The count how many Integer {@link Property}s the
	 *          <code>PrimitiveTypeProviderClass</code> has.
	 */
	public void setIntegerPropertyCounter(int integerPropertyCounter) {

		this.myIntegerPropertyCounter = integerPropertyCounter;
	}

	/**
	 * <p>
	 * Sets how many Real {@link Property}s the
	 * <code>PrimitiveTypeProviderClass</code> has.
	 * </p>
	 * 
	 * @param realPropertyCounter
	 *          The count how many Real {@link Property}s the
	 *          <code>PrimitiveTypeProviderClass</code> has.
	 */
	public void setRealPropertyCounter(int realPropertyCounter) {

		this.myRealPropertyCounter = realPropertyCounter;
	}

	/**
	 * <p>
	 * Sets how many Sequence {@link Property}s the
	 * <code>CollectionTypeProviderClass</code> has.
	 * </p>
	 * 
	 * @param sequencePropertyCounter
	 *          How many Sequence {@link Property}s the
	 *          <code>CollectionTypeProviderClass</code> has.
	 */
	public void setSequencePropertyCounter(int sequencePropertyCounter) {

		this.mySequencePropertyCounter = sequencePropertyCounter;
	}

	/**
	 * <p>
	 * Sets how many Set {@link Property}s the
	 * <code>CollectionTypeProviderClass</code> has.
	 * </p>
	 * 
	 * @param setPropertyCounter
	 *          How many Set {@link Property}s the
	 *          <code>CollectionTypeProviderClass</code> has.
	 */
	public void setSetPropertyCounter(int setPropertyCounter) {

		this.mySetPropertyCounter = setPropertyCounter;
	}

	/**
	 * <p>
	 * Sets how many String {@link Property}s the
	 * <code>PrimitiveTypeProviderClass</code> has.
	 * </p>
	 * 
	 * @param stringPropertyCounter
	 *          The count how many String {@link Property}s the
	 *          <code>PrimitiveTypeProviderClass</code> has.
	 */
	public void setStringPropertyCounter(int stringPropertyCounter) {

		this.myStringPropertyCounter = stringPropertyCounter;
	}

	/**
	 * <p>
	 * Sets the ID of the {@link Bundle} providing the {@link IModelInstance}
	 * which shall be tested.
	 * </p>
	 * 
	 * @param testModelBundleID
	 *          The ID of the {@link Bundle} providing the {@link IModelInstance}
	 *          which shall be tested.
	 */
	public void setTestModelInstanceBundleID(String testModelBundleID) {

		this.myModelInstanceBundleId = testModelBundleID;
	}

	/**
	 * <p>
	 * Sets the path of the {@link IModelInstance} which shall be tested.
	 * </p>
	 * 
	 * @param testModelPath
	 *          The path of the {@link IModelInstance} which shall be tested.
	 */
	public void setTestModelInstancePath(String testModelPath) {

		this.myModelInstancePath = testModelPath;
	}

	/**
	 * <p>
	 * Sets the ID of the {@link IMetamodel} which shall be tested.
	 * </p>
	 * 
	 * @param metaModelID
	 *          The ID of the {@link IMetamodel} which shall be tested.
	 */
	public void setModelInstanceTypeID(String modelInstanceTypeId) {

		this.myModelInstanceTypeId = modelInstanceTypeId;
	}

	/**
	 * <p>
	 * A helper method that probably adds some additional {@link Property}s to
	 * {@link Type}s in the {@link IModel}. E.g., to provide more than one boolean
	 * {@link Property} to test different adaptations.
	 * </p>
	 */
	private void addPropertiesToTestModel() {

		Type primitiveTypeProviderClass;
		Type collectionTypeProviderClass;

		primitiveTypeProviderClass =
				this
						.getModelType(TestModelTypesNames.TYPE_NAME_PRIMITIVE_TYPE_PROVIDER_CLASS);

		collectionTypeProviderClass =
				this
						.getModelType(TestModelTypesNames.TYPE_NAME_COLLECTION_TYPE_PROVIDER_CLASS);

		if (primitiveTypeProviderClass != null) {

			/* Probably add additional boolean properties to the model. */
			for (int index = 2; index <= getBooleanPropertyCounter(); index++) {
				Property aProperty;

				aProperty = PivotModelFactory.INSTANCE.createProperty();
				aProperty.setName("booleanProperty" + index);
				aProperty.setType(TypeConstants.BOOLEAN);

				primitiveTypeProviderClass.addProperty(aProperty);
			}

			/* Probably add additional integer properties to the model. */
			for (int index = 2; index <= getIntegerPropertyCounter(); index++) {
				Property aProperty;

				aProperty = PivotModelFactory.INSTANCE.createProperty();
				aProperty.setName("integerProperty" + index);
				aProperty.setType(TypeConstants.INTEGER);

				primitiveTypeProviderClass.addProperty(aProperty);
			}

			/* Probably add additional real properties to the model. */
			for (int index = 2; index <= getRealPropertyCounter(); index++) {
				Property aProperty;

				aProperty = PivotModelFactory.INSTANCE.createProperty();
				aProperty.setName("realProperty" + index);
				aProperty.setType(TypeConstants.REAL);

				primitiveTypeProviderClass.addProperty(aProperty);
			}

			/* Probably add additional string properties to the model. */
			for (int index = 2; index <= getStringPropertyCounter(); index++) {
				Property aProperty;

				aProperty = PivotModelFactory.INSTANCE.createProperty();
				aProperty.setName("stringProperty" + index);
				aProperty.setType(TypeConstants.STRING);

				primitiveTypeProviderClass.addProperty(aProperty);
			}
		}
		// no else.

		if (collectionTypeProviderClass != null) {

			/* Probably add additional bag properties to the model. */
			for (int index = 2; index <= getBagPropertyCounter(); index++) {
				Property aProperty;

				aProperty = PivotModelFactory.INSTANCE.createProperty();
				aProperty.setName("bagProperty" + index);
				aProperty.setType(TypeConstants.BAG);

				collectionTypeProviderClass.addProperty(aProperty);
			}

			/* Probably add additional ordered set properties to the model. */
			for (int index = 2; index <= getOrderedSetPropertyCounter(); index++) {
				Property aProperty;

				aProperty = PivotModelFactory.INSTANCE.createProperty();
				aProperty.setName("orderedSetProperty" + index);
				aProperty.setType(TypeConstants.ORDERED_SET);

				collectionTypeProviderClass.addProperty(aProperty);
			}

			/* Probably add additional sequence properties to the model. */
			for (int index = 2; index <= getSequencePropertyCounter(); index++) {
				Property aProperty;

				aProperty = PivotModelFactory.INSTANCE.createProperty();
				aProperty.setName("sequenceProperty" + index);
				aProperty.setType(TypeConstants.SEQUENCE);

				collectionTypeProviderClass.addProperty(aProperty);
			}

			/* Probably add additional set properties to the model. */
			for (int index = 2; index <= getBagPropertyCounter(); index++) {
				Property aProperty;

				aProperty = PivotModelFactory.INSTANCE.createProperty();
				aProperty.setName("setProperty" + index);
				aProperty.setType(TypeConstants.SET);

				collectionTypeProviderClass.addProperty(aProperty);
			}
		}
		// no else.
	}

	/**
	 * <p>
	 * Converts a given qualified name into a {@link List} describing its
	 * qualified name in the {@link IModel}.
	 * 
	 * @param qualifiedName
	 *          The qualifiedName name that shall be converted.
	 * @return The converted qualified name.
	 */
	private List<String> convertQualifiedNameToQualifiedNameList(
			String qualifiedName) {

		List<String> result;

		result = new ArrayList<String>();

		for (String aPackagesName : qualifiedName.split("::")) {
			result.add(aPackagesName);
		}

		return result;
	}

	/**
	 * <p>
	 * A helper method that loads the {@link IModel} required for testing.
	 * </p>
	 */
	private void loadModel() {

		/* Check if the model has already been loaded. */
		if (this.myModel == null) {

			String bundleDirectory;
			File modelFile;

			/* Get the bundle location for the model files. */
			bundleDirectory = Platform.getBundle(MODEL_BUNDLE_ID).getLocation();

			/* Remove the 'reference:file:' from the beginning. */
			bundleDirectory = bundleDirectory.substring(15);

			modelFile = new File(bundleDirectory + MODEL_PATH);

			/* Check if the given file does not exist. */
			if (!modelFile.exists()) {
				String msg;

				msg =
						ModelInstanceTypeTestSuiteMessages.ModelInstanceTypeTestSuite_Services_ModelFileNotFound;
				msg = NLS.bind(msg, MODEL_PATH, bundleDirectory);

				throw new RuntimeException(msg);
			}

			/* Else try to load the model. */
			else {

				try {
					this.myModel =
							Ocl2ForEclipseFacade.getModel(modelFile, META_MODEL_ID);

					this.addPropertiesToTestModel();
				}

				catch (ModelAccessException e) {
					throw new RuntimeException(e.getMessage());
				}
			}
			// end else.

		}
		// no else. Model has already been loaded.
	}
}