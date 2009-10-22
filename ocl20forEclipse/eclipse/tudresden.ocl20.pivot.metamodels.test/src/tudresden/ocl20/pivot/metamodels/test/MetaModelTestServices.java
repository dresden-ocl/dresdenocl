/*
 * Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net) This file is part of
 * the Meta Model Architecture of Dresden OCL2 for Eclipse. Dresden OCL2 for
 * Eclipse is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version. Dresden OCL2 for Eclipse is distributed in the hope that it
 * will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser
 * General Public License for more details. You should have received a copy of
 * the GNU Lesser General Public License along with Dresden OCL2 for Eclipse. If
 * not, see <http://www.gnu.org/licenses/>.
 */
package tudresden.ocl20.pivot.metamodels.test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.Bundle;

import tudresden.ocl20.pivot.metamodels.test.msg.MetaModelTestSuiteMessages;
import tudresden.ocl20.pivot.modelbus.IModelBusConstants;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.metamodel.IMetamodel;
import tudresden.ocl20.pivot.modelbus.model.IModel;
import tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind;
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
public final class MetaModelTestServices {

	/** The name of the {@link Namespace} <code>package1</code>. */
	public static final String NAMESPACE_NAME_PACKAGE1 = "package1";

	/** The name of the {@link Namespace} <code>package2</code>. */
	public static final String NAMESPACE_NAME_PACKAGE2 = "package2";

	/** The name of the {@link Namespace} <code>package1</code>. */
	public static final String NAMESPACE_QUALIFIED_NAME_PACKAGE1 =
			IModelBusConstants.ROOT_PACKAGE_NAME + "::" + NAMESPACE_NAME_PACKAGE1;

	/** The name of the {@link Namespace} <code>package2</code>. */
	public static final String NAMESPACE_QUALIFIED_NAME_PACKAGE2 =
			NAMESPACE_QUALIFIED_NAME_PACKAGE1 + "::" + NAMESPACE_NAME_PACKAGE2;

	/** The name of the {@link Type} <code>TestTypeClass1</code>. */
	public static final String TYPE_NAME_TESTTYPECLASS1 = "TestTypeClass1";

	/** The name of the {@link Type} <code>TestTypeClass2</code>. */
	public static final String TYPE_NAME_TESTTYPECLASS2 = "TestTypeClass2";

	/** The name of the {@link Type} <code>TestPropertyClass</code>. */
	public static final String TYPE_NAME_TESTPROPERTYCLASS = "TestPropertyClass";

	/** The name of the {@link Type} <code>TestOperationAndParameterClass</code>. */
	public static final String TYPE_NAME_TESTOPERATIONANDPARAMETERCLASS =
			"TestOperationAndParameterClass";

	/** The name of the {@link Type} <code>TestPrimitiveTypeClass</code>. */
	public static final String TYPE_NAME_TESTPRIMITIVETYPECLASS =
			"TestPrimitiveTypeClass";

	/** The name of the {@link Type} <code>TestTypeInterface1</code>. */
	public static final String TYPE_NAME_TESTTYPEINTERFACE1 =
			"TestTypeInterface1";

	/** The name of the {@link Type} <code>TestTypeInterface2</code>. */
	public static final String TYPE_NAME_TESTTYPEINTERFACE2 =
			"TestTypeInterface2";

	/** The qualified name of the {@link Type} <code>TestTypeClass1</code>. */
	public static final String TYPE_QUALIFIED_NAME_TESTTYPECLASS1 =
			NAMESPACE_QUALIFIED_NAME_PACKAGE2 + "::" + TYPE_NAME_TESTTYPECLASS1;

	/** The qualified name of the {@link Type} <code>TestTypeClass2</code>. */
	public static final String TYPE_QUALIFIED_NAME_TESTTYPECLASS2 =
			NAMESPACE_QUALIFIED_NAME_PACKAGE2 + "::" + TYPE_NAME_TESTTYPECLASS2;

	/** The qualified name of the {@link Type} <code>TestPropertyClass</code>. */
	public static final String TYPE_QUALIFIED_NAME_TESTPROPERTYCLASS =
			NAMESPACE_QUALIFIED_NAME_PACKAGE2 + "::" + TYPE_NAME_TESTPROPERTYCLASS;

	/**
	 * The qualified name of the {@link Type}
	 * <code>TestOperationAndParameterClass</code>.
	 */
	public static final String TYPE_QUALIFIED_NAME_TESTOPERATIONANDPARAMETERCLASS =
			NAMESPACE_QUALIFIED_NAME_PACKAGE2 + "::"
					+ TYPE_NAME_TESTOPERATIONANDPARAMETERCLASS;

	/**
	 * The qualified name of the {@link Type} <code>TestPrimitiveTypeClass</code>.
	 */
	public static final String TYPE_QUALIFIED_NAME_TESTPRIMITIVETYPECLASS =
			NAMESPACE_QUALIFIED_NAME_PACKAGE2 + "::"
					+ TYPE_NAME_TESTPRIMITIVETYPECLASS;

	/** The qualified name of the {@link Type} <code>TestTypeInterface1</code>. */
	public static final String TYPE_QUALIFIED_NAME_TESTTYPEINTERFACE1 =
			NAMESPACE_QUALIFIED_NAME_PACKAGE2 + "::" + TYPE_NAME_TESTTYPEINTERFACE1;

	/** The qualified name of the {@link Type} <code>TestTypeInterface2</code>. */
	public static final String TYPE_QUALIFIED_NAME_TESTTYPEINTERFACE2 =
			NAMESPACE_QUALIFIED_NAME_PACKAGE2 + "::" + TYPE_NAME_TESTTYPEINTERFACE2;

	/** The name of the {@link Type} <code>TestEnumeration</code>. */
	public static final String ENUMERATION_NAME_ENUMERATION1 = "TestEnumeration";

	/** The qualified name of the {@link Type} <code>TestEnumeration</code>. */
	public static final String ENUMERATION_QUALIFIED_NAME_ENUMERATION1 =
			NAMESPACE_QUALIFIED_NAME_PACKAGE2 + "::" + ENUMERATION_NAME_ENUMERATION1;

	/**
	 * The name of the {@link EnumerationLiteral} <code>TestLiteral1</code>.
	 */
	public static final String ENUMERATIONLITERAL_NAME_ENUMERATIONLITERAL1 =
			"TestLiteral1";

	/**
	 * The name of the {@link EnumerationLiteral} <code>TestLiteral2</code>.
	 */
	public static final String ENUMERATIONLITERAL_NAME_ENUMERATIONLITERAL2 =
			"TestLiteral2";

	/**
	 * The qualified name of the {@link EnumerationLiteral}
	 * <code>TestLiteral1</code>.
	 */
	public static final String ENUMERATIONLITERAL_QUALIFIED_NAME_ENUMERATIONLITERAL1 =
			ENUMERATION_QUALIFIED_NAME_ENUMERATION1 + "::"
					+ ENUMERATIONLITERAL_NAME_ENUMERATIONLITERAL1;

	/**
	 * The qualified name of the {@link EnumerationLiteral}
	 * <code>TestLiteral2</code>.
	 */
	public static final String ENUMERATIONLITERAL_QUALIFIED_NAME_ENUMERATIONLITERAL2 =
			ENUMERATION_QUALIFIED_NAME_ENUMERATION1 + "::"
					+ ENUMERATIONLITERAL_NAME_ENUMERATIONLITERAL2;

	/** The name of the {@link Operation} <code>operation1</code>. */
	public static final String OPERATION_NAME_OPERATION1 = "operation1";

	/** The name of the {@link Operation} <code>operation2</code>. */
	public static final String OPERATION_NAME_OPERATION2 = "operation2";

	/** The name of the {@link Operation} <code>operationWithoutParameters</code>. */
	public static final String OPERATION_NAME_OPERATIONWITHOUTPARAMETERS =
			"operationWithoutParameters";

	/** The name of the {@link Operation} <code>voidOperationWithParameter</code>. */
	public static final String OPERATION_NAME_VOIDOPERATIONWITHPARAMETER =
			"voidOperationWithParameter";

	/**
	 * The name of the {@link Operation}
	 * <code>inputOutputParameterOperation</code>.
	 */
	public static final String OPERATION_NAME_INPUTOUTPUTPARAMETEROPERATION =
			"inputOutputParameterOperation";

	/** The name of the {@link Operation} <code>outputParameterOperation</code>. */
	public static final String OPERATION_NAME_OUTPUTPARAMETEROPERATION =
			"outputParameterOperation";

	/** The name of the {@link Operation} <code>staticOperation</code>. */
	public static final String OPERATION_NAME_STATICOPERATION = "staticOperation";

	/** The name of the {@link Operation} <code>multipleOperation</code>. */
	public static final String OPERATION_NAME_MULTIPLEOPERATION =
			"multipleOperation";

	/** The name of the {@link Operation} <code>orderedMultipleOperation</code>. */
	public static final String OPERATION_NAME_ORDEREDMULTIPLEOPERATION =
			"orderedMultipleOperation";

	/**
	 * The name of the {@link Operation} <code>unorderedMultipleOperation</code> .
	 */
	public static final String OPERATION_NAME_UNORDEREDMULTIPLEOPERATION =
			"unorderedMultipleOperation";

	/** The name of the {@link Operation} <code>uniqueMultipleOperation</code>. */
	public static final String OPERATION_NAME_UNIQUEMULTIPLEOPERATION =
			"uniqueMultipleOperation";

	/**
	 * The name of the {@link Operation} <code>nonuniqueMultipleOperation</code> .
	 */
	public static final String OPERATION_NAME_NONUNIQUEMULTIPLEOPERATION =
			"nonuniqueMultipleOperation";

	/** The name of the {@link Operation} <code>operationWithoutParameters</code>. */
	public static final String OPERATION_QUALIFIED_NAME_OPERATIONWITHOUTPARAMETERS =
			TYPE_QUALIFIED_NAME_TESTOPERATIONANDPARAMETERCLASS + "::"
					+ OPERATION_NAME_OPERATIONWITHOUTPARAMETERS + "()";

	/** The name of the {@link Operation} <code>voidOperationWithParameter</code>. */
	public static final String OPERATION_QUALIFIED_NAME_VOIDOPERATIONWITHPARAMETER =
			TYPE_QUALIFIED_NAME_TESTOPERATIONANDPARAMETERCLASS + "::"
					+ OPERATION_NAME_VOIDOPERATIONWITHPARAMETER + "("
					+ TYPE_QUALIFIED_NAME_TESTTYPECLASS1 + ")";

	/** The name of the {@link Property} <code>associationEnd1</code>. */
	public static final String PROPERTY_NAME_ASSOCIATONEND1 = "associationEnd1";

	/** The name of the {@link Property} <code>associationEnd</code>. */
	public static final String PROPERTY_NAME_NONMULTIPLEASSOCIATONEND =
			"nonmultipleAssociationEnd";

	/** The name of the {@link Property} <code>property1</code>. */
	public static final String PROPERTY_NAME_PROPERTY1 = "property1";

	/** The name of the {@link Property} <code>property2</code>. */
	public static final String PROPERTY_NAME_PROPERTY2 = "property2";

	/** The name of the {@link Property} <code>nonmultipleProperty</code>. */
	public static final String PROPERTY_NAME_NONMULTIPLEPROPERTY =
			"nonmultipleProperty";

	/** The name of the {@link Property} <code>staticAssociationEnd</code>. */
	public static final String PROPERTY_NAME_STATICASSOCIATONEND =
			"staticAssociationEnd";

	/** The name of the {@link Property} <code>staticProperty</code>. */
	public static final String PROPERTY_NAME_STATICPROPERTY = "staticProperty";

	/**
	 * The name of the {@link Property} <code>orderedMultipleAssociationEnd</code>
	 * .
	 */
	public static final String PROPERTY_NAME_ORDEREDMULTIPLEASSOCIATONEND =
			"orderedMultipleAssociationEnd";

	/** The name of the {@link Property} <code>orderedMultipleProperty</code>. */
	public static final String PROPERTY_NAME_ORDEREDMULTIPLEPROPERTY =
			"orderedMultipleProperty";

	/**
	 * The name of the {@link Property}
	 * <code>unorderedMultipleAssociationEnd</code>.
	 */
	public static final String PROPERTY_NAME_UNORDEREDMULTIPLEASSOCIATONEND =
			"unorderedMultipleAssociationEnd";

	/** The name of the {@link Property} <code>unorderedMultipleProperty</code>. */
	public static final String PROPERTY_NAME_UNORDEREDMULTIPLEPROPERTY =
			"unorderedMultipleProperty";

	/**
	 * The name of the {@link Property} <code>uniqueMultipleAssociationEnd</code>.
	 */
	public static final String PROPERTY_NAME_UNIQUEMULTIPLEASSOCIATONEND =
			"uniqueMultipleAssociationEnd";

	/** The name of the {@link Property} <code>uniqueMultipleProperty</code>. */
	public static final String PROPERTY_NAME_UNIQUEMULTIPLEPROPERTY =
			"uniqueMultipleProperty";

	/**
	 * The name of the {@link Property}
	 * <code>nonuniqueMultipleAssociationEnd</code>.
	 */
	public static final String PROPERTY_NAME_NONUNIQUEMULTIPLEASSOCIATONEND =
			"nonuniqueMultipleAssociationEnd";

	/** The name of the {@link Property} <code>nonuniqueMultipleProperty</code>. */
	public static final String PROPERTY_NAME_NONUNIQUEMULTIPLEPROPERTY =
			"nonuniqueMultipleProperty";

	/** The name of the {@link Property} <code>nonmultipleProperty</code>. */
	public static final String PROPERTY_QUALIFIED_NAME_NONMULTIPLEPROPERTY =
			TYPE_QUALIFIED_NAME_TESTPROPERTYCLASS + "::"
					+ PROPERTY_NAME_NONMULTIPLEPROPERTY;

	/** The name of the {@link Property} <code>nonmultipleAssociationEnd</code>. */
	public static final String PROPERTY_QUALIFIED_NAME_NONMULTIPLEASSOCIATIONEND =
			TYPE_QUALIFIED_NAME_TESTPROPERTYCLASS + "::"
					+ PROPERTY_NAME_NONMULTIPLEASSOCIATONEND;

	/** The name of the {@link Parameter} <code>in1</code>. */
	public static final String PARAMETER_NAME_INPUTPARAMETER1 = "in1";

	/** The name of the {@link Parameter} <code>in1</code>. */
	public static final String PARAMETER_QUALIFIED_NAME_INPUTPARAMETER1 =
			OPERATION_QUALIFIED_NAME_VOIDOPERATIONWITHPARAMETER + "::"
					+ PARAMETER_NAME_INPUTPARAMETER1;

	/**
	 * The prefix of {@link Property}s that represent a {@link PrimitiveType} of
	 * the {@link PrimitiveTypeKind#BOOLEAN} which shall be tested.
	 */
	public static final String PRIMITIVETYPE_BOOLEAN_PROPERTY_PREFIX = "aBoolean";

	/**
	 * The prefix of {@link Property}s that represent a {@link PrimitiveType} of
	 * the {@link PrimitiveTypeKind#INTEGER} which shall be tested.
	 */
	public static final String PRIMITIVETYPE_INTEGER_PROPERTY_PREFIX =
			"anInteger";

	/**
	 * The prefix of {@link Property}s that represent a {@link PrimitiveType} of
	 * the {@link PrimitiveTypeKind#REAL} which shall be tested.
	 */
	public static final String PRIMITIVETYPE_REAL_PROPERTY_PREFIX = "aReal";

	/**
	 * The prefix of {@link Property}s that represent a {@link PrimitiveType} of
	 * the {@link PrimitiveTypeKind#STRING} which shall be tested.
	 */
	public static final String PRIMITIVETYPE_STRING_PROPERTY_PREFIX = "aString";

	/** The only instance of {@link MetaModelTestServices}. */
	private static MetaModelTestServices myInstance;

	/**
	 * Contains the already loaded {@link IModel}s identified by their
	 * {@link File} represented as a {@link String}.
	 */
	private Map<String, IModel> myCachedModels;

	/** The ID of the {@link IMetamodel} which shall be tested. */
	private String myMetaModelId;

	/**
	 * The ID of the {@link Bundle} providing the {@link IModel} which shall be
	 * tested.
	 */
	private String myTestModelBundleId;

	/** The path of the {@link IModel} which shall be tested. */
	private String myTestModelPath;

	/**
	 * <p>
	 * The private constructor.
	 * </p>
	 */
	private MetaModelTestServices() {

		this.myCachedModels = new HashMap<String, IModel>();
	}

	public static MetaModelTestServices getInstance() {

		/* Eventually create the instance. */
		if (myInstance == null) {
			myInstance = new MetaModelTestServices();
		}
		// no else.

		return myInstance;
	}

	/**
	 * <p>
	 * Converts a given qualified name into a {@link List} of {@link String}s
	 * (representing the packages and the {@link Type}'s name.
	 * </p>
	 * 
	 * @param qualifiedName
	 *          The name which shall be converted.
	 * @return The converted path.
	 */
	private static List<String> convertQualifiedNameToList(String qualifiedName) {

		List<String> result;

		result = new ArrayList<String>();

		for (String aPackage : qualifiedName.split("::")) {
			result.add(aPackage);
		}

		return result;
	}

	/**
	 * <p>
	 * Returns the ID of the {@link IMetamodel} that is currently tested.
	 * </p>
	 * 
	 * @return The ID of the {@link IMetamodel} that is currently tested.
	 */
	public String getMetaModelUnderTestID() {

		return this.myMetaModelId;
	}

	/**
	 * <p>
	 * Returns the {@link IModel} which can be loaded by the current settings of
	 * <code>MetaModelID</code>, <code>ModelBundleID</code>, and
	 * <code>TestModelPath</code>.
	 * </p>
	 * 
	 * @return The current {@link IModel} or <code>null</code>.
	 */
	public IModel getModelUnderTest() {

		IModel result;

		String bundleDirectory;
		File modelFile;

		/* Get the bundle location for the model files. */
		bundleDirectory =
				Platform.getBundle(this.myTestModelBundleId).getLocation();

		/* Remove the 'reference:file:' from the beginning. */
		bundleDirectory = bundleDirectory.substring(15);

		modelFile = new File(bundleDirectory + myTestModelPath);

		/* Check if the model has already been loaded. */
		if (this.myCachedModels.containsKey(modelFile.toString())) {
			result = this.myCachedModels.get(modelFile.toString());
		}

		/* Else check if the given file does not exist. */
		else if (!modelFile.exists()) {
			String msg;

			msg = MetaModelTestSuiteMessages.MetaModelTestSuite_MetaModelNotFound;
			msg = NLS.bind(msg, myMetaModelId);

			throw new RuntimeException(msg);
		}

		/* Else try to load the model. */
		else {

			try {
				IMetamodel metaModel;

				/* Get the metaModel. */
				metaModel =
						ModelBusPlugin.getMetamodelRegistry().getMetamodel(myMetaModelId);

				if (metaModel != null) {
					result = metaModel.getModelProvider().getModel(modelFile);

					/* Cache the result. */
					this.myCachedModels.put(modelFile.toString(), result);
				}

				else {
					String msg;

					msg = MetaModelTestSuiteMessages.MetaModelTestSuite_MetaModelNotFound;
					msg = NLS.bind(msg, myMetaModelId);

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
	 * Searches for a {@link Namespace} in the current {@link IModel} under test.
	 * </p>
	 * 
	 * @param qualifiedName
	 *          The qualified name of the {@link Namespace} which shall be
	 *          returned.
	 * @return The found {@link Namespace}.
	 */
	public Namespace getNamespaceUnderTest(String qualifiedName) {

		Namespace result;
		IModel model;

		/* Get the model. */
		model = this.getModelUnderTest();

		/* Get the type to test. */
		try {
			result = model.findNamespace(convertQualifiedNameToList(qualifiedName));
		}

		catch (ModelAccessException e) {
			throw new RuntimeException(e.getMessage());
		}

		return result;
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
	public Type getTypeUnderTest(String qualifiedName) {

		Type result;
		IModel model;

		/* Get the model. */
		model = this.getModelUnderTest();

		/* Get the type to test. */
		try {
			result = model.findType(convertQualifiedNameToList(qualifiedName));
		}

		catch (ModelAccessException e) {
			throw new RuntimeException(e.getMessage());
		}

		return result;
	}

	/**
	 * <p>
	 * Sets the ID of the {@link IMetamodel} which shall be tested.
	 * </p>
	 * 
	 * @param metaModelID
	 *          The ID of the {@link IMetamodel} which shall be tested.
	 */
	public void setMetaModelID(String metaModelID) {

		this.myMetaModelId = metaModelID;
	}

	/**
	 * <p>
	 * Sets the ID of the {@link Bundle} providing the {@link IModel} which shall
	 * be tested.
	 * </p>
	 * 
	 * @param testModelBundleID
	 *          The ID of the {@link Bundle} providing the {@link IModel} which
	 *          shall be tested.
	 */
	public void setTestModelBundleID(String testModelBundleID) {

		this.myTestModelBundleId = testModelBundleID;
	}

	/**
	 * <p>
	 * Sets the path of the {@link IModel} which shall be tested.
	 * </p>
	 * 
	 * @param testModelPath
	 *          The path of the {@link IModel} which shall be tested.
	 */
	public void setTestModelPath(String testModelPath) {

		this.myTestModelPath = testModelPath;
	}
}