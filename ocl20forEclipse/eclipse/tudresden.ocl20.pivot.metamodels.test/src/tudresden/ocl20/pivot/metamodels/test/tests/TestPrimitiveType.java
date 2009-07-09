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
package tudresden.ocl20.pivot.metamodels.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.osgi.util.NLS;
import org.junit.BeforeClass;
import org.junit.Test;

import tudresden.ocl20.pivot.metamodels.test.MetaModelTestPlugin;
import tudresden.ocl20.pivot.metamodels.test.MetaModelTestServices;
import tudresden.ocl20.pivot.metamodels.test.msg.MetaModelTestSuiteMessages;
import tudresden.ocl20.pivot.modelbus.IMetamodel;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * This class provides test cases to test the {@link PrimitiveType}
 * implementation/adaptation of a {@link IMetamodel}.
 * </p>
 * 
 * @author Claas Wilke
 * 
 */
public class TestPrimitiveType {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			MetaModelTestPlugin.getLogger(TestPrimitiveType.class);

	/**
	 * The {@link Type} in the {@link IModel} under test which contains all
	 * {@link PrimitiveType}s that shall be tested as properties.
	 */
	private static Type containerClass;

	/**
	 * A {@link Map} containing all {@link PrimitiveType}s that shall be tested
	 * stored by their name.
	 */
	private static Map<String, PrimitiveType> allPrimitiveTypes;

	/**
	 * A {@link List} containing all {@link PrimitiveType}s of the
	 * {@link PrimitiveTypeKind#BOOLEAN} that shall be tested stored by their
	 * name.
	 */
	private static Map<String, PrimitiveType> booleanPrimitiveTypes;

	/**
	 * A {@link List} containing all {@link PrimitiveType}s of the
	 * {@link PrimitiveTypeKind#INTEGER} that shall be tested stored by their
	 * name.
	 */
	private static Map<String, PrimitiveType> integerPrimitiveTypes;

	/**
	 * A {@link List} containing all {@link PrimitiveType}s of the
	 * {@link PrimitiveTypeKind#REAL} that shall be tested stored by their name.
	 */
	private static Map<String, PrimitiveType> realPrimitiveTypes;

	/**
	 * A {@link List} containing all {@link PrimitiveType}s of the
	 * {@link PrimitiveTypeKind#STRING} that shall be tested stored by their name.
	 */
	private static Map<String, PrimitiveType> stringPrimitiveTypes;

	/**
	 * <p>
	 * Loads some elements from the current {@link IModel} under test required for
	 * the tests contained in this Class.
	 * </p>
	 */
	@BeforeClass
	public static void setUp() {

		/* Get the container class. */
		containerClass =
				MetaModelTestServices.getInstance().getTypeUnderTest(
						MetaModelTestServices.TYPE_QUALIFIED_NAME_TESTPRIMITIVETYPECLASS);

		allPrimitiveTypes = new HashMap<String, PrimitiveType>();
		booleanPrimitiveTypes = new HashMap<String, PrimitiveType>();
		integerPrimitiveTypes = new HashMap<String, PrimitiveType>();
		realPrimitiveTypes = new HashMap<String, PrimitiveType>();
		stringPrimitiveTypes = new HashMap<String, PrimitiveType>();

		if (containerClass == null) {

			/* Eventually send a warning to the logger. */
			if (LOGGER.isInfoEnabled()) {
				String msg;

				msg =
						NLS
								.bind(
										MetaModelTestSuiteMessages.MetaModelTestSuite_ClassNotFoundInModel,
										MetaModelTestServices.TYPE_QUALIFIED_NAME_TESTPRIMITIVETYPECLASS,
										PrimitiveType.class.getName());
				msg +=
						" "
								+ NLS
										.bind(
												MetaModelTestSuiteMessages.MetaModelTestSuite_CurrentlyTestedMetaModel,
												MetaModelTestServices.getInstance()
														.getMetaModelUnderTestID());

				LOGGER.warn(msg);
			}
			// no else.
		}

		/* Get the primitive types from the container class. */
		else {

			List<Property> allProperties;

			allProperties = containerClass.getOwnedProperty();

			/* Iterate through the properties and arrange them by their name. */
			for (Property aProperty : allProperties) {

				String aPropertiesName;
				String primtiveTypeName;

				PrimitiveType aPrimitiveType;

				aPropertiesName = aProperty.getName();
				aPrimitiveType = (PrimitiveType) aProperty.getType();

				primtiveTypeName = null;

				/* Check if the property represents a boolean. */
				if (aPropertiesName
						.startsWith(MetaModelTestServices.PRIMITIVETYPE_BOOLEAN_PROPERTY_PREFIX)) {

					/* Get the types name. */
					primtiveTypeName =
							aPropertiesName
									.substring(MetaModelTestServices.PRIMITIVETYPE_BOOLEAN_PROPERTY_PREFIX
											.length());

					/* Store the type in the map. */
					booleanPrimitiveTypes.put(primtiveTypeName, aPrimitiveType);
				}

				/* Else check if the property represents an integer. */
				else if (aPropertiesName
						.startsWith(MetaModelTestServices.PRIMITIVETYPE_INTEGER_PROPERTY_PREFIX)) {

					/* Get the types name. */
					primtiveTypeName =
							aPropertiesName
									.substring(MetaModelTestServices.PRIMITIVETYPE_INTEGER_PROPERTY_PREFIX
											.length());

					/* Store the type in the map. */
					integerPrimitiveTypes.put(primtiveTypeName, aPrimitiveType);
				}

				/* Else check if the property represents a real. */
				else if (aPropertiesName
						.startsWith(MetaModelTestServices.PRIMITIVETYPE_REAL_PROPERTY_PREFIX)) {

					/* Get the types name. */
					primtiveTypeName =
							aPropertiesName
									.substring(MetaModelTestServices.PRIMITIVETYPE_REAL_PROPERTY_PREFIX
											.length());

					/* Store the type in the map. */
					realPrimitiveTypes.put(primtiveTypeName, aPrimitiveType);
				}

				/* Else check if the property represents a string. */
				else if (aPropertiesName
						.startsWith(MetaModelTestServices.PRIMITIVETYPE_STRING_PROPERTY_PREFIX)) {

					/* Get the types name. */
					primtiveTypeName =
							aPropertiesName
									.substring(MetaModelTestServices.PRIMITIVETYPE_STRING_PROPERTY_PREFIX
											.length());

					/* Store the type in the map. */
					stringPrimitiveTypes.put(primtiveTypeName, aPrimitiveType);
				}
				// no else.

				/* Store the type in the map of all primitive types. */
				if (primtiveTypeName != null) {
					allPrimitiveTypes.put(primtiveTypeName, aPrimitiveType);
				}

				else {

					/* Eventually send a warning to the logger. */
					if (LOGGER.isInfoEnabled()) {
						String msg;

						msg =
								NLS
										.bind(
												MetaModelTestSuiteMessages.MetaModelTestSuite_UnknownPrimitiveTypeKind,
												aPropertiesName);
						msg +=
								" "
										+ NLS
												.bind(
														MetaModelTestSuiteMessages.MetaModelTestSuite_CurrentlyTestedMetaModel,
														MetaModelTestServices.getInstance()
																.getMetaModelUnderTestID());

						LOGGER.warn(msg);
					}
					// no else.
				}
			}
			// end for.
		}
		// end else.

		/* Check if any primitive type has been found. */
		if (allPrimitiveTypes.size() == 0) {

			/* Eventually send a warning to the logger. */
			if (LOGGER.isInfoEnabled()) {
				String msg;

				msg =
						MetaModelTestSuiteMessages.MetaModelTestSuite_NoPrimitiveTypeFound;
				msg +=
						" "
								+ NLS
										.bind(
												MetaModelTestSuiteMessages.MetaModelTestSuite_CurrentlyTestedMetaModel,
												MetaModelTestServices.getInstance()
														.getMetaModelUnderTestID());

				LOGGER.warn(msg);
			}
			// no else.
		}

		else {

			/* Check if any boolean type has been found. */
			if (booleanPrimitiveTypes.size() == 0) {

				/* Eventually send a warning to the logger. */
				if (LOGGER.isInfoEnabled()) {
					String msg;

					msg =
							NLS
									.bind(
											MetaModelTestSuiteMessages.MetaModelTestSuite_NoPrimitiveTypeOfKindFound,
											PrimitiveTypeKind.BOOLEAN.getName());
					msg +=
							" "
									+ NLS
											.bind(
													MetaModelTestSuiteMessages.MetaModelTestSuite_CurrentlyTestedMetaModel,
													MetaModelTestServices.getInstance()
															.getMetaModelUnderTestID());

					LOGGER.warn(msg);
				}
				// no else.
			}
			// no else.

			/* Check if any integer type has been found. */
			if (integerPrimitiveTypes.size() == 0) {

				/* Eventually send a warning to the logger. */
				if (LOGGER.isInfoEnabled()) {
					String msg;

					msg =
							NLS
									.bind(
											MetaModelTestSuiteMessages.MetaModelTestSuite_NoPrimitiveTypeOfKindFound,
											PrimitiveTypeKind.INTEGER.getName());
					msg +=
							" "
									+ NLS
											.bind(
													MetaModelTestSuiteMessages.MetaModelTestSuite_CurrentlyTestedMetaModel,
													MetaModelTestServices.getInstance()
															.getMetaModelUnderTestID());

					LOGGER.warn(msg);
				}
				// no else.
			}
			// no else.

			/* Check if any real type has been found. */
			if (realPrimitiveTypes.size() == 0) {

				/* Eventually send a warning to the logger. */
				if (LOGGER.isInfoEnabled()) {
					String msg;

					msg =
							NLS
									.bind(
											MetaModelTestSuiteMessages.MetaModelTestSuite_NoPrimitiveTypeOfKindFound,
											PrimitiveTypeKind.REAL.getName());
					msg +=
							" "
									+ NLS
											.bind(
													MetaModelTestSuiteMessages.MetaModelTestSuite_CurrentlyTestedMetaModel,
													MetaModelTestServices.getInstance()
															.getMetaModelUnderTestID());

					LOGGER.warn(msg);
				}
				// no else.
			}
			// no else.

			/* Check if any string type has been found. */
			if (stringPrimitiveTypes.size() == 0) {

				/* Eventually send a warning to the logger. */
				if (LOGGER.isInfoEnabled()) {
					String msg;

					msg =
							NLS
									.bind(
											MetaModelTestSuiteMessages.MetaModelTestSuite_NoPrimitiveTypeOfKindFound,
											PrimitiveTypeKind.STRING.getName());
					msg +=
							" "
									+ NLS
											.bind(
													MetaModelTestSuiteMessages.MetaModelTestSuite_CurrentlyTestedMetaModel,
													MetaModelTestServices.getInstance()
															.getMetaModelUnderTestID());

					LOGGER.warn(msg);
				}
				// no else.
			}
			// no else.
		}
		// end else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link PrimitiveType#getKind()}.
	 * </p>
	 */
	@Test
	public void testGetKind1() {

		PrimitiveType type;

		String msg;

		for (String aTypeName : booleanPrimitiveTypes.keySet()) {

			msg = "The adaptation of PrimitiveType.getKind() seems to be wrong";
			msg += " for the Type " + aTypeName + ".";

			type = booleanPrimitiveTypes.get(aTypeName);

			/* The primitive type must have the expected kind in the model. */
			assertEquals(msg, PrimitiveTypeKind.BOOLEAN, type.getKind());
		}
	}

	/**
	 * <p>
	 * A test case testing the operation {@link PrimitiveType#getKind()}.
	 * </p>
	 */
	@Test
	public void testGetKind2() {

		PrimitiveType type;

		String msg;

		for (String aTypeName : integerPrimitiveTypes.keySet()) {

			msg = "The adaptation of PrimitiveType.getKind() seems to be wrong";
			msg += " for the Type " + aTypeName + ".";

			type = integerPrimitiveTypes.get(aTypeName);

			/* The primitive type must have the expected kind in the model. */
			assertEquals(msg, PrimitiveTypeKind.INTEGER, type.getKind());
		}
	}

	/**
	 * <p>
	 * A test case testing the operation {@link PrimitiveType#getKind()}.
	 * </p>
	 */
	@Test
	public void testGetKind3() {

		PrimitiveType type;

		String msg;

		for (String aTypeName : realPrimitiveTypes.keySet()) {

			msg = "The adaptation of PrimitiveType.getKind() seems to be wrong";
			msg += " for the Type " + aTypeName + ".";

			type = realPrimitiveTypes.get(aTypeName);

			/* The primitive type must have the expected kind in the model. */
			assertEquals(msg, PrimitiveTypeKind.REAL, type.getKind());
		}
	}

	/**
	 * <p>
	 * A test case testing the operation {@link PrimitiveType#getKind()}.
	 * </p>
	 */
	@Test
	public void testGetKind4() {

		PrimitiveType type;

		String msg;

		for (String aTypeName : stringPrimitiveTypes.keySet()) {

			msg = "The adaptation of PrimitiveType.getKind() seems to be wrong";
			msg += " for the Type " + aTypeName + ".";

			type = stringPrimitiveTypes.get(aTypeName);

			/* The primitive type must have the expected kind in the model. */
			assertEquals(msg, PrimitiveTypeKind.STRING, type.getKind());
		}
	}

	/**
	 * <p>
	 * A test case testing the operation {@link PrimitiveType#getName()}.
	 * </p>
	 */
	@Test
	public void testGetName1() {

		PrimitiveType type;

		String msg;

		msg = "The adaptation of PrimitiveType.getName() seems to be wrong.";

		for (String aTypesName : allPrimitiveTypes.keySet()) {

			type = (PrimitiveType) allPrimitiveTypes.get(aTypesName);

			/* The primitive type must have the same name as its kind has. */
			assertEquals(msg, type.getKind().getName(), type.getName());
		}
	}

	/**
	 * <p>
	 * A test case testing the operation {@link PrimitiveType#getName()}.
	 * </p>
	 */
	@Test
	public void testGetNamespace1() {

		PrimitiveType type;

		String msg;

		msg = "The adaptation of PrimitiveType.getNamespace() seems to be wrong.";
		msg = " Primitive Type shall not belong to a Namespace.";

		for (String aTypesName : allPrimitiveTypes.keySet()) {

			type = (PrimitiveType) allPrimitiveTypes.get(aTypesName);

			/* The primitive type must not be contained in any name space. */
			assertNull(msg, type.getNamespace());
		}
	}

	/**
	 * <p>
	 * A test case testing the operation {@link PrimitiveType#getOwnedOperation()}
	 * .
	 * </p>
	 */
	@Test
	public void testGetOwnedOperation1() {

		PrimitiveType type;

		String msg;

		msg =
				"The adaptation of PrimitiveType.getOwnedOperation() seems to be wrong.";
		msg += "PrimitiveTypes are not allowed to own Operations.";

		for (String aTypesName : allPrimitiveTypes.keySet()) {

			type = (PrimitiveType) allPrimitiveTypes.get(aTypesName);

			/* Primitive types can not have operations. */
			assertEquals(msg, 0, type.getOwnedOperation().size());
		}
	}

	/**
	 * <p>
	 * A test case testing the operation {@link PrimitiveType#getOwnedProperty()}.
	 * </p>
	 */
	@Test
	public void testGetOwnedProperty1() {

		PrimitiveType type;

		String msg;

		msg =
				"The adaptation of PrimitiveType.getOwnedProperty() seems to be wrong.";
		msg += "PrimitiveTypes are not allowed to own Properties.";

		for (String aTypesName : allPrimitiveTypes.keySet()) {

			type = (PrimitiveType) allPrimitiveTypes.get(aTypesName);

			/* Primitive types can not have properties. */
			assertEquals(msg, 0, type.getOwnedProperty().size());
		}
	}

	/**
	 * <p>
	 * A test case testing the operation
	 * {@link PrimitiveType#getOwnedTypeParameter()}.
	 * </p>
	 */
	@Test
	public void testGetOwnedTypeParameter1() {

		PrimitiveType type;

		String msg;

		msg =
				"The adaptation of PrimitiveType.getOwnedTypeParameter() seems to be wrong.";
		msg += "PrimitiveTypes are not allowed to own TypeParameters.";

		for (String aTypesName : allPrimitiveTypes.keySet()) {

			type = (PrimitiveType) allPrimitiveTypes.get(aTypesName);

			/* Primitive types should not have type parameters. */
			assertEquals(msg, 0, type.getOwnedTypeParameter().size());
		}
	}

	/**
	 * <p>
	 * A test case testing the operation {@link PrimitiveType#getOwner()}.
	 * </p>
	 */
	@Test
	public void testGetOwner1() {

		PrimitiveType type;

		String msg;

		msg = "The adaptation of PrimitiveType.getOwner() seems to be wrong.";
		msg = " Primitive Type shall be owned by any Element.";

		for (String aTypesName : allPrimitiveTypes.keySet()) {

			type = (PrimitiveType) allPrimitiveTypes.get(aTypesName);

			/* The primitive type must not be owned by any element. */
			assertNull(msg, type.getOwner());
		}
	}

	/**
	 * <p>
	 * A test case testing the operation {@link PrimitiveType#getQualifiedName()}.
	 * </p>
	 */
	@Test
	public void testGetQualifiedName1() {

		PrimitiveType type;

		String msg;

		msg =
				"The adaptation of PrimitiveType.getQualifiedName() seems to be wrong.";

		for (String aTypesName : allPrimitiveTypes.keySet()) {

			type = (PrimitiveType) allPrimitiveTypes.get(aTypesName);

			/*
			 * The qualified name of a primitive type must conform to the name of its
			 * kind.
			 */
			assertEquals(msg, type.getKind().getName(), type.getQualifiedName());
		}
	}

	/**
	 * <p>
	 * A test case testing the operation
	 * {@link PrimitiveType#getGenericSuperType()}.
	 * </p>
	 */
	@Test
	public void testGetGenericSuperType1() {

		PrimitiveType type;

		String msg;

		msg =
				"The adaptation of PrimitiveType.getGenericSuperType() seems to be wrong.";
		msg += "PrimitiveTypes are not allowed to have generic super Types.";

		for (String aTypesName : allPrimitiveTypes.keySet()) {

			type = (PrimitiveType) allPrimitiveTypes.get(aTypesName);

			/* The primitive must not have generic super types. */
			assertEquals(msg, 0, type.getGenericSuperType().size());
		}
	}

	/**
	 * <p>
	 * A test case testing the operation {@link PrimitiveType#getSuperType()}.
	 * </p>
	 */
	@Test
	public void testGetSuperType1() {

		PrimitiveType type;

		String msg;

		msg = "The adaptation of PrimitiveType.getSuperType() seems to be wrong.";
		msg += "PrimitiveTypes are not allowed to have super Types.";

		for (String aTypesName : allPrimitiveTypes.keySet()) {

			type = (PrimitiveType) allPrimitiveTypes.get(aTypesName);

			/* The primitive must not have super types. */
			assertEquals(msg, 0, type.getSuperType().size());
		}
	}
}