/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.language.ocl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see tudresden.ocl20.pivot.language.ocl.OclFactory
 * @model kind="package"
 * @generated
 */
public interface OclPackage extends EPackage {
	/**
   * The package name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNAME = "ocl";

	/**
   * The package namespace URI.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_URI = "http://www.tu-dresden.de/ocl20/pivot/language/ocl";

	/**
   * The package namespace name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_PREFIX = "ocl";

	/**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	OclPackage eINSTANCE = tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl.init();

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.OclExpressionCSImpl <em>Expression CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclExpressionCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getOclExpressionCS()
   * @generated
   */
	int OCL_EXPRESSION_CS = 0;

	/**
   * The number of structural features of the '<em>Expression CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OCL_EXPRESSION_CS_FEATURE_COUNT = 0;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.BracketExpCSImpl <em>Bracket Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.BracketExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getBracketExpCS()
   * @generated
   */
	int BRACKET_EXP_CS = 1;

	/**
   * The feature id for the '<em><b>Ocl Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BRACKET_EXP_CS__OCL_EXPRESSION = OCL_EXPRESSION_CS_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Bracket Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BRACKET_EXP_CS_FEATURE_COUNT = OCL_EXPRESSION_CS_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.NamedLiteralExpCSImpl <em>Named Literal Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.NamedLiteralExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getNamedLiteralExpCS()
   * @generated
   */
	int NAMED_LITERAL_EXP_CS = 2;

	/**
   * The feature id for the '<em><b>Named Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_LITERAL_EXP_CS__NAMED_ELEMENT = OCL_EXPRESSION_CS_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Named Literal Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_LITERAL_EXP_CS_FEATURE_COUNT = OCL_EXPRESSION_CS_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.PathNameCSImpl <em>Path Name CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.PathNameCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getPathNameCS()
   * @generated
   */
	int PATH_NAME_CS = 3;

	/**
   * The feature id for the '<em><b>Simple Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PATH_NAME_CS__SIMPLE_NAME = 0;

	/**
   * The feature id for the '<em><b>Path Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PATH_NAME_CS__PATH_NAME = 1;

	/**
   * The number of structural features of the '<em>Path Name CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PATH_NAME_CS_FEATURE_COUNT = 2;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.SimpleNameCSImpl <em>Simple Name CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.SimpleNameCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getSimpleNameCS()
   * @generated
   */
	int SIMPLE_NAME_CS = 4;

	/**
   * The feature id for the '<em><b>Simple Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIMPLE_NAME_CS__SIMPLE_NAME = 0;

	/**
   * The number of structural features of the '<em>Simple Name CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIMPLE_NAME_CS_FEATURE_COUNT = 1;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.TypeCSImpl <em>Type CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.TypeCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getTypeCS()
   * @generated
   */
	int TYPE_CS = 5;

	/**
   * The number of structural features of the '<em>Type CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE_CS_FEATURE_COUNT = 0;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.TypePathNameCSImpl <em>Type Path Name CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.TypePathNameCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getTypePathNameCS()
   * @generated
   */
	int TYPE_PATH_NAME_CS = 6;

	/**
   * The number of structural features of the '<em>Type Path Name CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE_PATH_NAME_CS_FEATURE_COUNT = TYPE_CS_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.TypePathNameSimpleCSImpl <em>Type Path Name Simple CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.TypePathNameSimpleCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getTypePathNameSimpleCS()
   * @generated
   */
	int TYPE_PATH_NAME_SIMPLE_CS = 7;

	/**
   * The feature id for the '<em><b>Type Name</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE_PATH_NAME_SIMPLE_CS__TYPE_NAME = TYPE_PATH_NAME_CS_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Type Path Name Simple CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE_PATH_NAME_SIMPLE_CS_FEATURE_COUNT = TYPE_PATH_NAME_CS_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.TypePathNameNestedCSImpl <em>Type Path Name Nested CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.TypePathNameNestedCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getTypePathNameNestedCS()
   * @generated
   */
	int TYPE_PATH_NAME_NESTED_CS = 8;

	/**
   * The feature id for the '<em><b>Namespace</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE_PATH_NAME_NESTED_CS__NAMESPACE = TYPE_PATH_NAME_CS_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Type Path Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE_PATH_NAME_NESTED_CS__TYPE_PATH_NAME = TYPE_PATH_NAME_CS_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Type Path Name Nested CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE_PATH_NAME_NESTED_CS_FEATURE_COUNT = TYPE_PATH_NAME_CS_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.TupleTypeCSImpl <em>Tuple Type CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.TupleTypeCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getTupleTypeCS()
   * @generated
   */
	int TUPLE_TYPE_CS = 9;

	/**
   * The feature id for the '<em><b>Variable Declaration List</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TUPLE_TYPE_CS__VARIABLE_DECLARATION_LIST = TYPE_CS_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Tuple Type CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TUPLE_TYPE_CS_FEATURE_COUNT = TYPE_CS_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.VariableDeclarationCSImpl <em>Variable Declaration CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.VariableDeclarationCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getVariableDeclarationCS()
   * @generated
   */
	int VARIABLE_DECLARATION_CS = 12;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.VariableDeclarationWithInitCSImpl <em>Variable Declaration With Init CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.VariableDeclarationWithInitCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getVariableDeclarationWithInitCS()
   * @generated
   */
	int VARIABLE_DECLARATION_WITH_INIT_CS = 13;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.VariableDeclarationWithoutInitCSImpl <em>Variable Declaration Without Init CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.VariableDeclarationWithoutInitCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getVariableDeclarationWithoutInitCS()
   * @generated
   */
	int VARIABLE_DECLARATION_WITHOUT_INIT_CS = 14;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.VariableDeclarationWithInitListCSImpl <em>Variable Declaration With Init List CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.VariableDeclarationWithInitListCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getVariableDeclarationWithInitListCS()
   * @generated
   */
	int VARIABLE_DECLARATION_WITH_INIT_LIST_CS = 15;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.VariableDeclarationWithoutInitListCSImpl <em>Variable Declaration Without Init List CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.VariableDeclarationWithoutInitListCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getVariableDeclarationWithoutInitListCS()
   * @generated
   */
	int VARIABLE_DECLARATION_WITHOUT_INIT_LIST_CS = 16;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.LiteralExpCSImpl <em>Literal Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.LiteralExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getLiteralExpCS()
   * @generated
   */
	int LITERAL_EXP_CS = 17;

	/**
   * The number of structural features of the '<em>Literal Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_EXP_CS_FEATURE_COUNT = OCL_EXPRESSION_CS_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.CollectionTypeLiteralExpCSImpl <em>Collection Type Literal Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.CollectionTypeLiteralExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getCollectionTypeLiteralExpCS()
   * @generated
   */
	int COLLECTION_TYPE_LITERAL_EXP_CS = 10;

	/**
   * The feature id for the '<em><b>Collection Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_TYPE_LITERAL_EXP_CS__COLLECTION_TYPE = LITERAL_EXP_CS_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Collection Type Literal Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_TYPE_LITERAL_EXP_CS_FEATURE_COUNT = LITERAL_EXP_CS_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.TupleTypeLiteralExpCSImpl <em>Tuple Type Literal Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.TupleTypeLiteralExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getTupleTypeLiteralExpCS()
   * @generated
   */
	int TUPLE_TYPE_LITERAL_EXP_CS = 11;

	/**
   * The feature id for the '<em><b>Tuple Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TUPLE_TYPE_LITERAL_EXP_CS__TUPLE_TYPE = LITERAL_EXP_CS_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Tuple Type Literal Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TUPLE_TYPE_LITERAL_EXP_CS_FEATURE_COUNT = LITERAL_EXP_CS_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Variable Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VARIABLE_DECLARATION_CS__VARIABLE_NAME = 0;

	/**
   * The number of structural features of the '<em>Variable Declaration CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VARIABLE_DECLARATION_CS_FEATURE_COUNT = 1;

	/**
   * The feature id for the '<em><b>Variable Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VARIABLE_DECLARATION_WITH_INIT_CS__VARIABLE_NAME = VARIABLE_DECLARATION_CS__VARIABLE_NAME;

	/**
   * The feature id for the '<em><b>Type Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VARIABLE_DECLARATION_WITH_INIT_CS__TYPE_NAME = VARIABLE_DECLARATION_CS_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Initialization</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VARIABLE_DECLARATION_WITH_INIT_CS__INITIALIZATION = VARIABLE_DECLARATION_CS_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Equal</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VARIABLE_DECLARATION_WITH_INIT_CS__EQUAL = VARIABLE_DECLARATION_CS_FEATURE_COUNT + 2;

	/**
   * The number of structural features of the '<em>Variable Declaration With Init CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VARIABLE_DECLARATION_WITH_INIT_CS_FEATURE_COUNT = VARIABLE_DECLARATION_CS_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Variable Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VARIABLE_DECLARATION_WITHOUT_INIT_CS__VARIABLE_NAME = VARIABLE_DECLARATION_CS__VARIABLE_NAME;

	/**
   * The feature id for the '<em><b>Type Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VARIABLE_DECLARATION_WITHOUT_INIT_CS__TYPE_NAME = VARIABLE_DECLARATION_CS_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Variable Declaration Without Init CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VARIABLE_DECLARATION_WITHOUT_INIT_CS_FEATURE_COUNT = VARIABLE_DECLARATION_CS_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Variable Declarations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VARIABLE_DECLARATION_WITH_INIT_LIST_CS__VARIABLE_DECLARATIONS = 0;

	/**
   * The number of structural features of the '<em>Variable Declaration With Init List CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VARIABLE_DECLARATION_WITH_INIT_LIST_CS_FEATURE_COUNT = 1;

	/**
   * The feature id for the '<em><b>Variable Declarations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VARIABLE_DECLARATION_WITHOUT_INIT_LIST_CS__VARIABLE_DECLARATIONS = 0;

	/**
   * The number of structural features of the '<em>Variable Declaration Without Init List CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VARIABLE_DECLARATION_WITHOUT_INIT_LIST_CS_FEATURE_COUNT = 1;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.EnumLiteralOrStaticPropertyExpCSImpl <em>Enum Literal Or Static Property Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.EnumLiteralOrStaticPropertyExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getEnumLiteralOrStaticPropertyExpCS()
   * @generated
   */
	int ENUM_LITERAL_OR_STATIC_PROPERTY_EXP_CS = 18;

	/**
   * The feature id for the '<em><b>Type Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUM_LITERAL_OR_STATIC_PROPERTY_EXP_CS__TYPE_NAME = LITERAL_EXP_CS_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Enum Literal Or Static Property</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUM_LITERAL_OR_STATIC_PROPERTY_EXP_CS__ENUM_LITERAL_OR_STATIC_PROPERTY = LITERAL_EXP_CS_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Enum Literal Or Static Property Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUM_LITERAL_OR_STATIC_PROPERTY_EXP_CS_FEATURE_COUNT = LITERAL_EXP_CS_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.CollectionLiteralExpCSImpl <em>Collection Literal Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.CollectionLiteralExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getCollectionLiteralExpCS()
   * @generated
   */
	int COLLECTION_LITERAL_EXP_CS = 19;

	/**
   * The feature id for the '<em><b>Collection Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_LITERAL_EXP_CS__COLLECTION_TYPE = LITERAL_EXP_CS_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Collection Literal Parts</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_LITERAL_EXP_CS__COLLECTION_LITERAL_PARTS = LITERAL_EXP_CS_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Collection Literal Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_LITERAL_EXP_CS_FEATURE_COUNT = LITERAL_EXP_CS_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.CollectionTypeIdentifierCSImpl <em>Collection Type Identifier CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.CollectionTypeIdentifierCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getCollectionTypeIdentifierCS()
   * @generated
   */
	int COLLECTION_TYPE_IDENTIFIER_CS = 20;

	/**
   * The feature id for the '<em><b>Type Name</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_TYPE_IDENTIFIER_CS__TYPE_NAME = TYPE_CS_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Generic Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_TYPE_IDENTIFIER_CS__GENERIC_TYPE = TYPE_CS_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Collection Type Identifier CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_TYPE_IDENTIFIER_CS_FEATURE_COUNT = TYPE_CS_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.CollectionLiteralPartsCSImpl <em>Collection Literal Parts CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.CollectionLiteralPartsCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getCollectionLiteralPartsCS()
   * @generated
   */
	int COLLECTION_LITERAL_PARTS_CS = 21;

	/**
   * The number of structural features of the '<em>Collection Literal Parts CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_LITERAL_PARTS_CS_FEATURE_COUNT = 0;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.CollectionLiteralPartsOclExpCSImpl <em>Collection Literal Parts Ocl Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.CollectionLiteralPartsOclExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getCollectionLiteralPartsOclExpCS()
   * @generated
   */
	int COLLECTION_LITERAL_PARTS_OCL_EXP_CS = 22;

	/**
   * The feature id for the '<em><b>Ocl Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_LITERAL_PARTS_OCL_EXP_CS__OCL_EXPRESSION = COLLECTION_LITERAL_PARTS_CS_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Collection Literal Parts Ocl Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_LITERAL_PARTS_OCL_EXP_CS_FEATURE_COUNT = COLLECTION_LITERAL_PARTS_CS_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.CollectionRangeCSImpl <em>Collection Range CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.CollectionRangeCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getCollectionRangeCS()
   * @generated
   */
	int COLLECTION_RANGE_CS = 23;

	/**
   * The feature id for the '<em><b>From</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_RANGE_CS__FROM = COLLECTION_LITERAL_PARTS_CS_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>To</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_RANGE_CS__TO = COLLECTION_LITERAL_PARTS_CS_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Collection Range CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_RANGE_CS_FEATURE_COUNT = COLLECTION_LITERAL_PARTS_CS_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.CallExpCSImpl <em>Call Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.CallExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getCallExpCS()
   * @generated
   */
	int CALL_EXP_CS = 24;

	/**
   * The number of structural features of the '<em>Call Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CALL_EXP_CS_FEATURE_COUNT = OCL_EXPRESSION_CS_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.LoopExpCSImpl <em>Loop Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.LoopExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getLoopExpCS()
   * @generated
   */
	int LOOP_EXP_CS = 25;

	/**
   * The number of structural features of the '<em>Loop Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOOP_EXP_CS_FEATURE_COUNT = CALL_EXP_CS_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.IteratorExpVariableCSImpl <em>Iterator Exp Variable CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.IteratorExpVariableCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getIteratorExpVariableCS()
   * @generated
   */
	int ITERATOR_EXP_VARIABLE_CS = 26;

	/**
   * The feature id for the '<em><b>Variable Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITERATOR_EXP_VARIABLE_CS__VARIABLE_NAME = 0;

	/**
   * The feature id for the '<em><b>Type Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITERATOR_EXP_VARIABLE_CS__TYPE_NAME = 1;

	/**
   * The number of structural features of the '<em>Iterator Exp Variable CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITERATOR_EXP_VARIABLE_CS_FEATURE_COUNT = 2;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.IteratorExpCSImpl <em>Iterator Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.IteratorExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getIteratorExpCS()
   * @generated
   */
	int ITERATOR_EXP_CS = 27;

	/**
   * The feature id for the '<em><b>Iterator Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITERATOR_EXP_CS__ITERATOR_NAME = LOOP_EXP_CS_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Iterator Variables</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITERATOR_EXP_CS__ITERATOR_VARIABLES = LOOP_EXP_CS_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Body Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITERATOR_EXP_CS__BODY_EXPRESSION = LOOP_EXP_CS_FEATURE_COUNT + 2;

	/**
   * The number of structural features of the '<em>Iterator Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITERATOR_EXP_CS_FEATURE_COUNT = LOOP_EXP_CS_FEATURE_COUNT + 3;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.IterateExpCSImpl <em>Iterate Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.IterateExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getIterateExpCS()
   * @generated
   */
	int ITERATE_EXP_CS = 28;

	/**
   * The feature id for the '<em><b>Iterator Variable</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITERATE_EXP_CS__ITERATOR_VARIABLE = LOOP_EXP_CS_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Result Variable</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITERATE_EXP_CS__RESULT_VARIABLE = LOOP_EXP_CS_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Body Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITERATE_EXP_CS__BODY_EXPRESSION = LOOP_EXP_CS_FEATURE_COUNT + 2;

	/**
   * The number of structural features of the '<em>Iterate Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITERATE_EXP_CS_FEATURE_COUNT = LOOP_EXP_CS_FEATURE_COUNT + 3;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.FeatureCallExpCSImpl <em>Feature Call Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.FeatureCallExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getFeatureCallExpCS()
   * @generated
   */
	int FEATURE_CALL_EXP_CS = 29;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.NavigationCallExpImpl <em>Navigation Call Exp</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.NavigationCallExpImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getNavigationCallExp()
   * @generated
   */
	int NAVIGATION_CALL_EXP = 30;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.ImplicitFeatureCallCSImpl <em>Implicit Feature Call CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.ImplicitFeatureCallCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getImplicitFeatureCallCS()
   * @generated
   */
	int IMPLICIT_FEATURE_CALL_CS = 33;

	/**
   * The number of structural features of the '<em>Feature Call Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FEATURE_CALL_EXP_CS_FEATURE_COUNT = CALL_EXP_CS_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Source</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAVIGATION_CALL_EXP__SOURCE = FEATURE_CALL_EXP_CS_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Navigation Operator</b></em>' attribute list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAVIGATION_CALL_EXP__NAVIGATION_OPERATOR = FEATURE_CALL_EXP_CS_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Feature Calls</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAVIGATION_CALL_EXP__FEATURE_CALLS = FEATURE_CALL_EXP_CS_FEATURE_COUNT + 2;

	/**
   * The number of structural features of the '<em>Navigation Call Exp</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAVIGATION_CALL_EXP_FEATURE_COUNT = FEATURE_CALL_EXP_CS_FEATURE_COUNT + 3;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.OperationCallBaseExpCSImpl <em>Operation Call Base Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.OperationCallBaseExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getOperationCallBaseExpCS()
   * @generated
   */
	int OPERATION_CALL_BASE_EXP_CS = 31;

	/**
   * The feature id for the '<em><b>Operation Name</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_CALL_BASE_EXP_CS__OPERATION_NAME = 0;

	/**
   * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_CALL_BASE_EXP_CS__ARGUMENTS = 1;

	/**
   * The feature id for the '<em><b>Is Marked Pre</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_CALL_BASE_EXP_CS__IS_MARKED_PRE = 2;

	/**
   * The number of structural features of the '<em>Operation Call Base Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_CALL_BASE_EXP_CS_FEATURE_COUNT = 3;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.PropertyCallBaseExpCSImpl <em>Property Call Base Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.PropertyCallBaseExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getPropertyCallBaseExpCS()
   * @generated
   */
	int PROPERTY_CALL_BASE_EXP_CS = 32;

	/**
   * The feature id for the '<em><b>Property</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_CALL_BASE_EXP_CS__PROPERTY = 0;

	/**
   * The feature id for the '<em><b>Is Marked Pre</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_CALL_BASE_EXP_CS__IS_MARKED_PRE = 1;

	/**
   * The number of structural features of the '<em>Property Call Base Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_CALL_BASE_EXP_CS_FEATURE_COUNT = 2;

	/**
   * The number of structural features of the '<em>Implicit Feature Call CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int IMPLICIT_FEATURE_CALL_CS_FEATURE_COUNT = 0;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.PropertyCallExpCSImpl <em>Property Call Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.PropertyCallExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getPropertyCallExpCS()
   * @generated
   */
	int PROPERTY_CALL_EXP_CS = 36;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.ImplicitPropertyCallCSImpl <em>Implicit Property Call CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.ImplicitPropertyCallCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getImplicitPropertyCallCS()
   * @generated
   */
	int IMPLICIT_PROPERTY_CALL_CS = 34;

	/**
   * The feature id for the '<em><b>Property</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int IMPLICIT_PROPERTY_CALL_CS__PROPERTY = IMPLICIT_FEATURE_CALL_CS_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Is Marked Pre</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int IMPLICIT_PROPERTY_CALL_CS__IS_MARKED_PRE = IMPLICIT_FEATURE_CALL_CS_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Implicit Property Call CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int IMPLICIT_PROPERTY_CALL_CS_FEATURE_COUNT = IMPLICIT_FEATURE_CALL_CS_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.PropertyCallExplicitPathExpCSImpl <em>Property Call Explicit Path Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.PropertyCallExplicitPathExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getPropertyCallExplicitPathExpCS()
   * @generated
   */
	int PROPERTY_CALL_EXPLICIT_PATH_EXP_CS = 38;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.OperationCallExpCSImpl <em>Operation Call Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.OperationCallExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getOperationCallExpCS()
   * @generated
   */
	int OPERATION_CALL_EXP_CS = 39;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.UnaryOperationCallExpCSImpl <em>Unary Operation Call Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.UnaryOperationCallExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getUnaryOperationCallExpCS()
   * @generated
   */
	int UNARY_OPERATION_CALL_EXP_CS = 42;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.LogicalNotOperationCallExpCSImpl <em>Logical Not Operation Call Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.LogicalNotOperationCallExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getLogicalNotOperationCallExpCS()
   * @generated
   */
	int LOGICAL_NOT_OPERATION_CALL_EXP_CS = 43;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.OperationCallWithSourceExpCSImpl <em>Operation Call With Source Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.OperationCallWithSourceExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getOperationCallWithSourceExpCS()
   * @generated
   */
	int OPERATION_CALL_WITH_SOURCE_EXP_CS = 44;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.OperationCallBinaryExpCSImpl <em>Operation Call Binary Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.OperationCallBinaryExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getOperationCallBinaryExpCS()
   * @generated
   */
	int OPERATION_CALL_BINARY_EXP_CS = 45;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.AdditiveOperationCallExpCSImpl <em>Additive Operation Call Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.AdditiveOperationCallExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getAdditiveOperationCallExpCS()
   * @generated
   */
	int ADDITIVE_OPERATION_CALL_EXP_CS = 46;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.MultOperationCallExpCSImpl <em>Mult Operation Call Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.MultOperationCallExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getMultOperationCallExpCS()
   * @generated
   */
	int MULT_OPERATION_CALL_EXP_CS = 47;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.RelationalOperationCallExpCSImpl <em>Relational Operation Call Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.RelationalOperationCallExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getRelationalOperationCallExpCS()
   * @generated
   */
	int RELATIONAL_OPERATION_CALL_EXP_CS = 48;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.EqualityOperationCallExpCSImpl <em>Equality Operation Call Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.EqualityOperationCallExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getEqualityOperationCallExpCS()
   * @generated
   */
	int EQUALITY_OPERATION_CALL_EXP_CS = 49;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.LogicalAndOperationCallExpCSImpl <em>Logical And Operation Call Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.LogicalAndOperationCallExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getLogicalAndOperationCallExpCS()
   * @generated
   */
	int LOGICAL_AND_OPERATION_CALL_EXP_CS = 50;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.LogicalOrOperationCallExpCSImpl <em>Logical Or Operation Call Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.LogicalOrOperationCallExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getLogicalOrOperationCallExpCS()
   * @generated
   */
	int LOGICAL_OR_OPERATION_CALL_EXP_CS = 51;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.LogicalXorOperationCallExpCSImpl <em>Logical Xor Operation Call Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.LogicalXorOperationCallExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getLogicalXorOperationCallExpCS()
   * @generated
   */
	int LOGICAL_XOR_OPERATION_CALL_EXP_CS = 52;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.LogicalImpliesOperationCallExpCSImpl <em>Logical Implies Operation Call Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.LogicalImpliesOperationCallExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getLogicalImpliesOperationCallExpCS()
   * @generated
   */
	int LOGICAL_IMPLIES_OPERATION_CALL_EXP_CS = 53;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.OperationCallWithImlicitSourceExpCSImpl <em>Operation Call With Imlicit Source Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.OperationCallWithImlicitSourceExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getOperationCallWithImlicitSourceExpCS()
   * @generated
   */
	int OPERATION_CALL_WITH_IMLICIT_SOURCE_EXP_CS = 54;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.ImplicitOperationCallCSImpl <em>Implicit Operation Call CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.ImplicitOperationCallCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getImplicitOperationCallCS()
   * @generated
   */
	int IMPLICIT_OPERATION_CALL_CS = 35;

	/**
   * The feature id for the '<em><b>Operation Name</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME = IMPLICIT_FEATURE_CALL_CS_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int IMPLICIT_OPERATION_CALL_CS__ARGUMENTS = IMPLICIT_FEATURE_CALL_CS_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Is Marked Pre</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int IMPLICIT_OPERATION_CALL_CS__IS_MARKED_PRE = IMPLICIT_FEATURE_CALL_CS_FEATURE_COUNT + 2;

	/**
   * The number of structural features of the '<em>Implicit Operation Call CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int IMPLICIT_OPERATION_CALL_CS_FEATURE_COUNT = IMPLICIT_FEATURE_CALL_CS_FEATURE_COUNT + 3;

	/**
   * The number of structural features of the '<em>Property Call Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_CALL_EXP_CS_FEATURE_COUNT = FEATURE_CALL_EXP_CS_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.PropertyCallOnSelfExpCSImpl <em>Property Call On Self Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.PropertyCallOnSelfExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getPropertyCallOnSelfExpCS()
   * @generated
   */
	int PROPERTY_CALL_ON_SELF_EXP_CS = 37;

	/**
   * The feature id for the '<em><b>Property</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_CALL_ON_SELF_EXP_CS__PROPERTY = PROPERTY_CALL_EXP_CS_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Is Marked Pre</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_CALL_ON_SELF_EXP_CS__IS_MARKED_PRE = PROPERTY_CALL_EXP_CS_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Property Call On Self Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_CALL_ON_SELF_EXP_CS_FEATURE_COUNT = PROPERTY_CALL_EXP_CS_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Source</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__SOURCE = PROPERTY_CALL_EXP_CS_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Property Path</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__PROPERTY_PATH = PROPERTY_CALL_EXP_CS_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Property Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__PROPERTY_NAME = PROPERTY_CALL_EXP_CS_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Is Marked Pre</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__IS_MARKED_PRE = PROPERTY_CALL_EXP_CS_FEATURE_COUNT + 3;

	/**
   * The number of structural features of the '<em>Property Call Explicit Path Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_CALL_EXPLICIT_PATH_EXP_CS_FEATURE_COUNT = PROPERTY_CALL_EXP_CS_FEATURE_COUNT + 4;

	/**
   * The number of structural features of the '<em>Operation Call Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_CALL_EXP_CS_FEATURE_COUNT = FEATURE_CALL_EXP_CS_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.OperationCallOnSelfExpCSImpl <em>Operation Call On Self Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.OperationCallOnSelfExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getOperationCallOnSelfExpCS()
   * @generated
   */
	int OPERATION_CALL_ON_SELF_EXP_CS = 40;

	/**
   * The feature id for the '<em><b>Operation Name</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME = OPERATION_CALL_EXP_CS_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_CALL_ON_SELF_EXP_CS__ARGUMENTS = OPERATION_CALL_EXP_CS_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Is Marked Pre</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_CALL_ON_SELF_EXP_CS__IS_MARKED_PRE = OPERATION_CALL_EXP_CS_FEATURE_COUNT + 2;

	/**
   * The number of structural features of the '<em>Operation Call On Self Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_CALL_ON_SELF_EXP_CS_FEATURE_COUNT = OPERATION_CALL_EXP_CS_FEATURE_COUNT + 3;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.StaticOperationCallExpCSImpl <em>Static Operation Call Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.StaticOperationCallExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getStaticOperationCallExpCS()
   * @generated
   */
	int STATIC_OPERATION_CALL_EXP_CS = 41;

	/**
   * The feature id for the '<em><b>Type Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATIC_OPERATION_CALL_EXP_CS__TYPE_NAME = OPERATION_CALL_EXP_CS_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Operation Name</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATIC_OPERATION_CALL_EXP_CS__OPERATION_NAME = OPERATION_CALL_EXP_CS_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATIC_OPERATION_CALL_EXP_CS__ARGUMENTS = OPERATION_CALL_EXP_CS_FEATURE_COUNT + 2;

	/**
   * The number of structural features of the '<em>Static Operation Call Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATIC_OPERATION_CALL_EXP_CS_FEATURE_COUNT = OPERATION_CALL_EXP_CS_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Operation Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNARY_OPERATION_CALL_EXP_CS__OPERATION_NAME = OPERATION_CALL_EXP_CS_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Target</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNARY_OPERATION_CALL_EXP_CS__TARGET = OPERATION_CALL_EXP_CS_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Unary Operation Call Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNARY_OPERATION_CALL_EXP_CS_FEATURE_COUNT = OPERATION_CALL_EXP_CS_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Operation Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_NOT_OPERATION_CALL_EXP_CS__OPERATION_NAME = OPERATION_CALL_EXP_CS_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Target</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_NOT_OPERATION_CALL_EXP_CS__TARGET = OPERATION_CALL_EXP_CS_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Logical Not Operation Call Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_NOT_OPERATION_CALL_EXP_CS_FEATURE_COUNT = OPERATION_CALL_EXP_CS_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Source</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_CALL_WITH_SOURCE_EXP_CS__SOURCE = OPERATION_CALL_EXP_CS_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Is Marked Pre</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_CALL_WITH_SOURCE_EXP_CS__IS_MARKED_PRE = OPERATION_CALL_EXP_CS_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Operation Call With Source Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_CALL_WITH_SOURCE_EXP_CS_FEATURE_COUNT = OPERATION_CALL_EXP_CS_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Source</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_CALL_BINARY_EXP_CS__SOURCE = OPERATION_CALL_WITH_SOURCE_EXP_CS__SOURCE;

	/**
   * The feature id for the '<em><b>Is Marked Pre</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_CALL_BINARY_EXP_CS__IS_MARKED_PRE = OPERATION_CALL_WITH_SOURCE_EXP_CS__IS_MARKED_PRE;

	/**
   * The feature id for the '<em><b>Operation Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_CALL_BINARY_EXP_CS__OPERATION_NAME = OPERATION_CALL_WITH_SOURCE_EXP_CS_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Target</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_CALL_BINARY_EXP_CS__TARGET = OPERATION_CALL_WITH_SOURCE_EXP_CS_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Operation Call Binary Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_CALL_BINARY_EXP_CS_FEATURE_COUNT = OPERATION_CALL_WITH_SOURCE_EXP_CS_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Source</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ADDITIVE_OPERATION_CALL_EXP_CS__SOURCE = OPERATION_CALL_BINARY_EXP_CS__SOURCE;

	/**
   * The feature id for the '<em><b>Is Marked Pre</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ADDITIVE_OPERATION_CALL_EXP_CS__IS_MARKED_PRE = OPERATION_CALL_BINARY_EXP_CS__IS_MARKED_PRE;

	/**
   * The feature id for the '<em><b>Operation Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ADDITIVE_OPERATION_CALL_EXP_CS__OPERATION_NAME = OPERATION_CALL_BINARY_EXP_CS__OPERATION_NAME;

	/**
   * The feature id for the '<em><b>Target</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ADDITIVE_OPERATION_CALL_EXP_CS__TARGET = OPERATION_CALL_BINARY_EXP_CS__TARGET;

	/**
   * The number of structural features of the '<em>Additive Operation Call Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ADDITIVE_OPERATION_CALL_EXP_CS_FEATURE_COUNT = OPERATION_CALL_BINARY_EXP_CS_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Source</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MULT_OPERATION_CALL_EXP_CS__SOURCE = OPERATION_CALL_BINARY_EXP_CS__SOURCE;

	/**
   * The feature id for the '<em><b>Is Marked Pre</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MULT_OPERATION_CALL_EXP_CS__IS_MARKED_PRE = OPERATION_CALL_BINARY_EXP_CS__IS_MARKED_PRE;

	/**
   * The feature id for the '<em><b>Operation Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MULT_OPERATION_CALL_EXP_CS__OPERATION_NAME = OPERATION_CALL_BINARY_EXP_CS__OPERATION_NAME;

	/**
   * The feature id for the '<em><b>Target</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MULT_OPERATION_CALL_EXP_CS__TARGET = OPERATION_CALL_BINARY_EXP_CS__TARGET;

	/**
   * The number of structural features of the '<em>Mult Operation Call Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MULT_OPERATION_CALL_EXP_CS_FEATURE_COUNT = OPERATION_CALL_BINARY_EXP_CS_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Source</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RELATIONAL_OPERATION_CALL_EXP_CS__SOURCE = OPERATION_CALL_BINARY_EXP_CS__SOURCE;

	/**
   * The feature id for the '<em><b>Is Marked Pre</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RELATIONAL_OPERATION_CALL_EXP_CS__IS_MARKED_PRE = OPERATION_CALL_BINARY_EXP_CS__IS_MARKED_PRE;

	/**
   * The feature id for the '<em><b>Operation Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RELATIONAL_OPERATION_CALL_EXP_CS__OPERATION_NAME = OPERATION_CALL_BINARY_EXP_CS__OPERATION_NAME;

	/**
   * The feature id for the '<em><b>Target</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RELATIONAL_OPERATION_CALL_EXP_CS__TARGET = OPERATION_CALL_BINARY_EXP_CS__TARGET;

	/**
   * The number of structural features of the '<em>Relational Operation Call Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RELATIONAL_OPERATION_CALL_EXP_CS_FEATURE_COUNT = OPERATION_CALL_BINARY_EXP_CS_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Source</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EQUALITY_OPERATION_CALL_EXP_CS__SOURCE = OPERATION_CALL_BINARY_EXP_CS__SOURCE;

	/**
   * The feature id for the '<em><b>Is Marked Pre</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EQUALITY_OPERATION_CALL_EXP_CS__IS_MARKED_PRE = OPERATION_CALL_BINARY_EXP_CS__IS_MARKED_PRE;

	/**
   * The feature id for the '<em><b>Operation Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EQUALITY_OPERATION_CALL_EXP_CS__OPERATION_NAME = OPERATION_CALL_BINARY_EXP_CS__OPERATION_NAME;

	/**
   * The feature id for the '<em><b>Target</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EQUALITY_OPERATION_CALL_EXP_CS__TARGET = OPERATION_CALL_BINARY_EXP_CS__TARGET;

	/**
   * The number of structural features of the '<em>Equality Operation Call Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EQUALITY_OPERATION_CALL_EXP_CS_FEATURE_COUNT = OPERATION_CALL_BINARY_EXP_CS_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Source</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_AND_OPERATION_CALL_EXP_CS__SOURCE = OPERATION_CALL_BINARY_EXP_CS__SOURCE;

	/**
   * The feature id for the '<em><b>Is Marked Pre</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_AND_OPERATION_CALL_EXP_CS__IS_MARKED_PRE = OPERATION_CALL_BINARY_EXP_CS__IS_MARKED_PRE;

	/**
   * The feature id for the '<em><b>Operation Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_AND_OPERATION_CALL_EXP_CS__OPERATION_NAME = OPERATION_CALL_BINARY_EXP_CS__OPERATION_NAME;

	/**
   * The feature id for the '<em><b>Target</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_AND_OPERATION_CALL_EXP_CS__TARGET = OPERATION_CALL_BINARY_EXP_CS__TARGET;

	/**
   * The number of structural features of the '<em>Logical And Operation Call Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_AND_OPERATION_CALL_EXP_CS_FEATURE_COUNT = OPERATION_CALL_BINARY_EXP_CS_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Source</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_OR_OPERATION_CALL_EXP_CS__SOURCE = OPERATION_CALL_BINARY_EXP_CS__SOURCE;

	/**
   * The feature id for the '<em><b>Is Marked Pre</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_OR_OPERATION_CALL_EXP_CS__IS_MARKED_PRE = OPERATION_CALL_BINARY_EXP_CS__IS_MARKED_PRE;

	/**
   * The feature id for the '<em><b>Operation Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_OR_OPERATION_CALL_EXP_CS__OPERATION_NAME = OPERATION_CALL_BINARY_EXP_CS__OPERATION_NAME;

	/**
   * The feature id for the '<em><b>Target</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_OR_OPERATION_CALL_EXP_CS__TARGET = OPERATION_CALL_BINARY_EXP_CS__TARGET;

	/**
   * The number of structural features of the '<em>Logical Or Operation Call Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_OR_OPERATION_CALL_EXP_CS_FEATURE_COUNT = OPERATION_CALL_BINARY_EXP_CS_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Source</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_XOR_OPERATION_CALL_EXP_CS__SOURCE = OPERATION_CALL_BINARY_EXP_CS__SOURCE;

	/**
   * The feature id for the '<em><b>Is Marked Pre</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_XOR_OPERATION_CALL_EXP_CS__IS_MARKED_PRE = OPERATION_CALL_BINARY_EXP_CS__IS_MARKED_PRE;

	/**
   * The feature id for the '<em><b>Operation Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_XOR_OPERATION_CALL_EXP_CS__OPERATION_NAME = OPERATION_CALL_BINARY_EXP_CS__OPERATION_NAME;

	/**
   * The feature id for the '<em><b>Target</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_XOR_OPERATION_CALL_EXP_CS__TARGET = OPERATION_CALL_BINARY_EXP_CS__TARGET;

	/**
   * The number of structural features of the '<em>Logical Xor Operation Call Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_XOR_OPERATION_CALL_EXP_CS_FEATURE_COUNT = OPERATION_CALL_BINARY_EXP_CS_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Source</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_IMPLIES_OPERATION_CALL_EXP_CS__SOURCE = OPERATION_CALL_BINARY_EXP_CS__SOURCE;

	/**
   * The feature id for the '<em><b>Is Marked Pre</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_IMPLIES_OPERATION_CALL_EXP_CS__IS_MARKED_PRE = OPERATION_CALL_BINARY_EXP_CS__IS_MARKED_PRE;

	/**
   * The feature id for the '<em><b>Operation Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_IMPLIES_OPERATION_CALL_EXP_CS__OPERATION_NAME = OPERATION_CALL_BINARY_EXP_CS__OPERATION_NAME;

	/**
   * The feature id for the '<em><b>Target</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_IMPLIES_OPERATION_CALL_EXP_CS__TARGET = OPERATION_CALL_BINARY_EXP_CS__TARGET;

	/**
   * The number of structural features of the '<em>Logical Implies Operation Call Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_IMPLIES_OPERATION_CALL_EXP_CS_FEATURE_COUNT = OPERATION_CALL_BINARY_EXP_CS_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Operation Name</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_CALL_WITH_IMLICIT_SOURCE_EXP_CS__OPERATION_NAME = OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME;

	/**
   * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_CALL_WITH_IMLICIT_SOURCE_EXP_CS__ARGUMENTS = OPERATION_CALL_ON_SELF_EXP_CS__ARGUMENTS;

	/**
   * The feature id for the '<em><b>Is Marked Pre</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_CALL_WITH_IMLICIT_SOURCE_EXP_CS__IS_MARKED_PRE = OPERATION_CALL_ON_SELF_EXP_CS__IS_MARKED_PRE;

	/**
   * The number of structural features of the '<em>Operation Call With Imlicit Source Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_CALL_WITH_IMLICIT_SOURCE_EXP_CS_FEATURE_COUNT = OPERATION_CALL_ON_SELF_EXP_CS_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.TupleLiteralExpCSImpl <em>Tuple Literal Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.TupleLiteralExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getTupleLiteralExpCS()
   * @generated
   */
	int TUPLE_LITERAL_EXP_CS = 55;

	/**
   * The feature id for the '<em><b>Variable Declarations</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TUPLE_LITERAL_EXP_CS__VARIABLE_DECLARATIONS = LITERAL_EXP_CS_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Tuple Literal Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TUPLE_LITERAL_EXP_CS_FEATURE_COUNT = LITERAL_EXP_CS_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.PrimitiveLiteralExpCSImpl <em>Primitive Literal Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.PrimitiveLiteralExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getPrimitiveLiteralExpCS()
   * @generated
   */
	int PRIMITIVE_LITERAL_EXP_CS = 56;

	/**
   * The number of structural features of the '<em>Primitive Literal Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PRIMITIVE_LITERAL_EXP_CS_FEATURE_COUNT = LITERAL_EXP_CS_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.IntegerLiteralExpCSImpl <em>Integer Literal Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.IntegerLiteralExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getIntegerLiteralExpCS()
   * @generated
   */
	int INTEGER_LITERAL_EXP_CS = 57;

	/**
   * The feature id for the '<em><b>Integer Literal</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTEGER_LITERAL_EXP_CS__INTEGER_LITERAL = PRIMITIVE_LITERAL_EXP_CS_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Integer Literal Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTEGER_LITERAL_EXP_CS_FEATURE_COUNT = PRIMITIVE_LITERAL_EXP_CS_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.RealLiteralExpCSImpl <em>Real Literal Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.RealLiteralExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getRealLiteralExpCS()
   * @generated
   */
	int REAL_LITERAL_EXP_CS = 58;

	/**
   * The feature id for the '<em><b>Int Value</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REAL_LITERAL_EXP_CS__INT_VALUE = PRIMITIVE_LITERAL_EXP_CS_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Real Value</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REAL_LITERAL_EXP_CS__REAL_VALUE = PRIMITIVE_LITERAL_EXP_CS_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Navigation Operator</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REAL_LITERAL_EXP_CS__NAVIGATION_OPERATOR = PRIMITIVE_LITERAL_EXP_CS_FEATURE_COUNT + 2;

	/**
   * The number of structural features of the '<em>Real Literal Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REAL_LITERAL_EXP_CS_FEATURE_COUNT = PRIMITIVE_LITERAL_EXP_CS_FEATURE_COUNT + 3;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.BooleanLiteralExpCSImpl <em>Boolean Literal Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.BooleanLiteralExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getBooleanLiteralExpCS()
   * @generated
   */
	int BOOLEAN_LITERAL_EXP_CS = 59;

	/**
   * The feature id for the '<em><b>Boolean Literal</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_LITERAL_EXP_CS__BOOLEAN_LITERAL = PRIMITIVE_LITERAL_EXP_CS_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Boolean Literal Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_LITERAL_EXP_CS_FEATURE_COUNT = PRIMITIVE_LITERAL_EXP_CS_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.StringLiteralExpCSImpl <em>String Literal Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.StringLiteralExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getStringLiteralExpCS()
   * @generated
   */
	int STRING_LITERAL_EXP_CS = 60;

	/**
   * The feature id for the '<em><b>String Literal</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_LITERAL_EXP_CS__STRING_LITERAL = PRIMITIVE_LITERAL_EXP_CS_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>String Literal Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_LITERAL_EXP_CS_FEATURE_COUNT = PRIMITIVE_LITERAL_EXP_CS_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.InvalidLiteralExpCSImpl <em>Invalid Literal Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.InvalidLiteralExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getInvalidLiteralExpCS()
   * @generated
   */
	int INVALID_LITERAL_EXP_CS = 61;

	/**
   * The number of structural features of the '<em>Invalid Literal Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVALID_LITERAL_EXP_CS_FEATURE_COUNT = PRIMITIVE_LITERAL_EXP_CS_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.NullLiteralExpCSImpl <em>Null Literal Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.NullLiteralExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getNullLiteralExpCS()
   * @generated
   */
	int NULL_LITERAL_EXP_CS = 62;

	/**
   * The number of structural features of the '<em>Null Literal Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NULL_LITERAL_EXP_CS_FEATURE_COUNT = PRIMITIVE_LITERAL_EXP_CS_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.LetExpCSImpl <em>Let Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.LetExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getLetExpCS()
   * @generated
   */
	int LET_EXP_CS = 63;

	/**
   * The feature id for the '<em><b>Variable Declarations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LET_EXP_CS__VARIABLE_DECLARATIONS = OCL_EXPRESSION_CS_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Ocl Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LET_EXP_CS__OCL_EXPRESSION = OCL_EXPRESSION_CS_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Let Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LET_EXP_CS_FEATURE_COUNT = OCL_EXPRESSION_CS_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.IfExpCSImpl <em>If Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.IfExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getIfExpCS()
   * @generated
   */
	int IF_EXP_CS = 64;

	/**
   * The feature id for the '<em><b>Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int IF_EXP_CS__CONDITION = OCL_EXPRESSION_CS_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Then Branch</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int IF_EXP_CS__THEN_BRANCH = OCL_EXPRESSION_CS_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Else Branch</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int IF_EXP_CS__ELSE_BRANCH = OCL_EXPRESSION_CS_FEATURE_COUNT + 2;

	/**
   * The number of structural features of the '<em>If Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int IF_EXP_CS_FEATURE_COUNT = OCL_EXPRESSION_CS_FEATURE_COUNT + 3;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.PackageDeclarationCSImpl <em>Package Declaration CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.PackageDeclarationCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getPackageDeclarationCS()
   * @generated
   */
	int PACKAGE_DECLARATION_CS = 65;

	/**
   * The feature id for the '<em><b>Context Declarations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PACKAGE_DECLARATION_CS__CONTEXT_DECLARATIONS = 0;

	/**
   * The feature id for the '<em><b>Layout Information</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PACKAGE_DECLARATION_CS__LAYOUT_INFORMATION = 1;

	/**
   * The number of structural features of the '<em>Package Declaration CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PACKAGE_DECLARATION_CS_FEATURE_COUNT = 2;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.PackageDeclarationWithNamespaceCSImpl <em>Package Declaration With Namespace CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.PackageDeclarationWithNamespaceCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getPackageDeclarationWithNamespaceCS()
   * @generated
   */
	int PACKAGE_DECLARATION_WITH_NAMESPACE_CS = 66;

	/**
   * The feature id for the '<em><b>Context Declarations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PACKAGE_DECLARATION_WITH_NAMESPACE_CS__CONTEXT_DECLARATIONS = PACKAGE_DECLARATION_CS__CONTEXT_DECLARATIONS;

	/**
   * The feature id for the '<em><b>Layout Information</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PACKAGE_DECLARATION_WITH_NAMESPACE_CS__LAYOUT_INFORMATION = PACKAGE_DECLARATION_CS__LAYOUT_INFORMATION;

	/**
   * The feature id for the '<em><b>Nested Namespace</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PACKAGE_DECLARATION_WITH_NAMESPACE_CS__NESTED_NAMESPACE = PACKAGE_DECLARATION_CS_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Package Declaration With Namespace CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PACKAGE_DECLARATION_WITH_NAMESPACE_CS_FEATURE_COUNT = PACKAGE_DECLARATION_CS_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.PackageDeclarationNestedNamespaceCSImpl <em>Package Declaration Nested Namespace CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.PackageDeclarationNestedNamespaceCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getPackageDeclarationNestedNamespaceCS()
   * @generated
   */
	int PACKAGE_DECLARATION_NESTED_NAMESPACE_CS = 67;

	/**
   * The feature id for the '<em><b>Namespace</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PACKAGE_DECLARATION_NESTED_NAMESPACE_CS__NAMESPACE = 0;

	/**
   * The feature id for the '<em><b>Nested Namespace</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PACKAGE_DECLARATION_NESTED_NAMESPACE_CS__NESTED_NAMESPACE = 1;

	/**
   * The feature id for the '<em><b>Layout Information</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PACKAGE_DECLARATION_NESTED_NAMESPACE_CS__LAYOUT_INFORMATION = 2;

	/**
   * The number of structural features of the '<em>Package Declaration Nested Namespace CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PACKAGE_DECLARATION_NESTED_NAMESPACE_CS_FEATURE_COUNT = 3;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.PackageDeclarationWithoutNamespaceCSImpl <em>Package Declaration Without Namespace CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.PackageDeclarationWithoutNamespaceCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getPackageDeclarationWithoutNamespaceCS()
   * @generated
   */
	int PACKAGE_DECLARATION_WITHOUT_NAMESPACE_CS = 68;

	/**
   * The feature id for the '<em><b>Context Declarations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PACKAGE_DECLARATION_WITHOUT_NAMESPACE_CS__CONTEXT_DECLARATIONS = PACKAGE_DECLARATION_CS__CONTEXT_DECLARATIONS;

	/**
   * The feature id for the '<em><b>Layout Information</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PACKAGE_DECLARATION_WITHOUT_NAMESPACE_CS__LAYOUT_INFORMATION = PACKAGE_DECLARATION_CS__LAYOUT_INFORMATION;

	/**
   * The number of structural features of the '<em>Package Declaration Without Namespace CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PACKAGE_DECLARATION_WITHOUT_NAMESPACE_CS_FEATURE_COUNT = PACKAGE_DECLARATION_CS_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.ContextDeclarationCSImpl <em>Context Declaration CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.ContextDeclarationCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getContextDeclarationCS()
   * @generated
   */
	int CONTEXT_DECLARATION_CS = 69;

	/**
   * The number of structural features of the '<em>Context Declaration CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTEXT_DECLARATION_CS_FEATURE_COUNT = 0;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.AttributeContextDeclarationCSImpl <em>Attribute Context Declaration CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.AttributeContextDeclarationCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getAttributeContextDeclarationCS()
   * @generated
   */
	int ATTRIBUTE_CONTEXT_DECLARATION_CS = 70;

	/**
   * The feature id for the '<em><b>Type Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ATTRIBUTE_CONTEXT_DECLARATION_CS__TYPE_NAME = CONTEXT_DECLARATION_CS_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Property</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ATTRIBUTE_CONTEXT_DECLARATION_CS__PROPERTY = CONTEXT_DECLARATION_CS_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ATTRIBUTE_CONTEXT_DECLARATION_CS__TYPE = CONTEXT_DECLARATION_CS_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Init Or Derive Value</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ATTRIBUTE_CONTEXT_DECLARATION_CS__INIT_OR_DERIVE_VALUE = CONTEXT_DECLARATION_CS_FEATURE_COUNT + 3;

	/**
   * The number of structural features of the '<em>Attribute Context Declaration CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ATTRIBUTE_CONTEXT_DECLARATION_CS_FEATURE_COUNT = CONTEXT_DECLARATION_CS_FEATURE_COUNT + 4;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.ClassifierContextDeclarationCSImpl <em>Classifier Context Declaration CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.ClassifierContextDeclarationCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getClassifierContextDeclarationCS()
   * @generated
   */
	int CLASSIFIER_CONTEXT_DECLARATION_CS = 71;

	/**
   * The feature id for the '<em><b>Type Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASSIFIER_CONTEXT_DECLARATION_CS__TYPE_NAME = CONTEXT_DECLARATION_CS_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Invariants And Definitions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASSIFIER_CONTEXT_DECLARATION_CS__INVARIANTS_AND_DEFINITIONS = CONTEXT_DECLARATION_CS_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Classifier Context Declaration CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASSIFIER_CONTEXT_DECLARATION_CS_FEATURE_COUNT = CONTEXT_DECLARATION_CS_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.OperationContextDeclarationCSImpl <em>Operation Context Declaration CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.OperationContextDeclarationCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getOperationContextDeclarationCS()
   * @generated
   */
	int OPERATION_CONTEXT_DECLARATION_CS = 72;

	/**
   * The feature id for the '<em><b>Operation</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_CONTEXT_DECLARATION_CS__OPERATION = CONTEXT_DECLARATION_CS_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Pre Post Or Body Declarations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_CONTEXT_DECLARATION_CS__PRE_POST_OR_BODY_DECLARATIONS = CONTEXT_DECLARATION_CS_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Operation Context Declaration CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_CONTEXT_DECLARATION_CS_FEATURE_COUNT = CONTEXT_DECLARATION_CS_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.InitOrDeriveValueCSImpl <em>Init Or Derive Value CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.InitOrDeriveValueCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getInitOrDeriveValueCS()
   * @generated
   */
	int INIT_OR_DERIVE_VALUE_CS = 73;

	/**
   * The feature id for the '<em><b>Ocl Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INIT_OR_DERIVE_VALUE_CS__OCL_EXPRESSION = 0;

	/**
   * The number of structural features of the '<em>Init Or Derive Value CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INIT_OR_DERIVE_VALUE_CS_FEATURE_COUNT = 1;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.InitValueCSImpl <em>Init Value CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.InitValueCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getInitValueCS()
   * @generated
   */
	int INIT_VALUE_CS = 74;

	/**
   * The feature id for the '<em><b>Ocl Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INIT_VALUE_CS__OCL_EXPRESSION = INIT_OR_DERIVE_VALUE_CS__OCL_EXPRESSION;

	/**
   * The number of structural features of the '<em>Init Value CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INIT_VALUE_CS_FEATURE_COUNT = INIT_OR_DERIVE_VALUE_CS_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.DeriveValueCSImpl <em>Derive Value CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.DeriveValueCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getDeriveValueCS()
   * @generated
   */
	int DERIVE_VALUE_CS = 75;

	/**
   * The feature id for the '<em><b>Ocl Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DERIVE_VALUE_CS__OCL_EXPRESSION = INIT_OR_DERIVE_VALUE_CS__OCL_EXPRESSION;

	/**
   * The number of structural features of the '<em>Derive Value CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DERIVE_VALUE_CS_FEATURE_COUNT = INIT_OR_DERIVE_VALUE_CS_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.InvariantOrDefinitionCSImpl <em>Invariant Or Definition CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.InvariantOrDefinitionCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getInvariantOrDefinitionCS()
   * @generated
   */
	int INVARIANT_OR_DEFINITION_CS = 76;

	/**
   * The number of structural features of the '<em>Invariant Or Definition CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVARIANT_OR_DEFINITION_CS_FEATURE_COUNT = 0;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.InvariantExpCSImpl <em>Invariant Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.InvariantExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getInvariantExpCS()
   * @generated
   */
	int INVARIANT_EXP_CS = 77;

	/**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVARIANT_EXP_CS__NAME = INVARIANT_OR_DEFINITION_CS_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Ocl Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVARIANT_EXP_CS__OCL_EXPRESSION = INVARIANT_OR_DEFINITION_CS_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Invariant Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVARIANT_EXP_CS_FEATURE_COUNT = INVARIANT_OR_DEFINITION_CS_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.DefinitionExpCSImpl <em>Definition Exp CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.DefinitionExpCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getDefinitionExpCS()
   * @generated
   */
	int DEFINITION_EXP_CS = 78;

	/**
   * The feature id for the '<em><b>Static</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEFINITION_EXP_CS__STATIC = INVARIANT_OR_DEFINITION_CS_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Definition Exp Part</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEFINITION_EXP_CS__DEFINITION_EXP_PART = INVARIANT_OR_DEFINITION_CS_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Definition Exp CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEFINITION_EXP_CS_FEATURE_COUNT = INVARIANT_OR_DEFINITION_CS_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.DefinitionExpPartCSImpl <em>Definition Exp Part CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.DefinitionExpPartCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getDefinitionExpPartCS()
   * @generated
   */
	int DEFINITION_EXP_PART_CS = 79;

	/**
   * The number of structural features of the '<em>Definition Exp Part CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEFINITION_EXP_PART_CS_FEATURE_COUNT = 0;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.DefinitionExpPropertyCSImpl <em>Definition Exp Property CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.DefinitionExpPropertyCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getDefinitionExpPropertyCS()
   * @generated
   */
	int DEFINITION_EXP_PROPERTY_CS = 80;

	/**
   * The feature id for the '<em><b>Variable Declaration</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEFINITION_EXP_PROPERTY_CS__VARIABLE_DECLARATION = DEFINITION_EXP_PART_CS_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Definition Exp Property CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEFINITION_EXP_PROPERTY_CS_FEATURE_COUNT = DEFINITION_EXP_PART_CS_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.DefinitionExpOperationCSImpl <em>Definition Exp Operation CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.DefinitionExpOperationCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getDefinitionExpOperationCS()
   * @generated
   */
	int DEFINITION_EXP_OPERATION_CS = 81;

	/**
   * The feature id for the '<em><b>Operation</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEFINITION_EXP_OPERATION_CS__OPERATION = DEFINITION_EXP_PART_CS_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Equal</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEFINITION_EXP_OPERATION_CS__EQUAL = DEFINITION_EXP_PART_CS_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Ocl Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEFINITION_EXP_OPERATION_CS__OCL_EXPRESSION = DEFINITION_EXP_PART_CS_FEATURE_COUNT + 2;

	/**
   * The number of structural features of the '<em>Definition Exp Operation CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEFINITION_EXP_OPERATION_CS_FEATURE_COUNT = DEFINITION_EXP_PART_CS_FEATURE_COUNT + 3;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.PrePostOrBodyDeclarationCSImpl <em>Pre Post Or Body Declaration CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.PrePostOrBodyDeclarationCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getPrePostOrBodyDeclarationCS()
   * @generated
   */
	int PRE_POST_OR_BODY_DECLARATION_CS = 82;

	/**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PRE_POST_OR_BODY_DECLARATION_CS__NAME = 0;

	/**
   * The feature id for the '<em><b>Ocl Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PRE_POST_OR_BODY_DECLARATION_CS__OCL_EXPRESSION = 1;

	/**
   * The number of structural features of the '<em>Pre Post Or Body Declaration CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PRE_POST_OR_BODY_DECLARATION_CS_FEATURE_COUNT = 2;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.PreConditionDeclarationCSImpl <em>Pre Condition Declaration CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.PreConditionDeclarationCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getPreConditionDeclarationCS()
   * @generated
   */
	int PRE_CONDITION_DECLARATION_CS = 83;

	/**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PRE_CONDITION_DECLARATION_CS__NAME = PRE_POST_OR_BODY_DECLARATION_CS__NAME;

	/**
   * The feature id for the '<em><b>Ocl Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PRE_CONDITION_DECLARATION_CS__OCL_EXPRESSION = PRE_POST_OR_BODY_DECLARATION_CS__OCL_EXPRESSION;

	/**
   * The number of structural features of the '<em>Pre Condition Declaration CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PRE_CONDITION_DECLARATION_CS_FEATURE_COUNT = PRE_POST_OR_BODY_DECLARATION_CS_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.PostConditionDeclarationCSImpl <em>Post Condition Declaration CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.PostConditionDeclarationCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getPostConditionDeclarationCS()
   * @generated
   */
	int POST_CONDITION_DECLARATION_CS = 84;

	/**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int POST_CONDITION_DECLARATION_CS__NAME = PRE_POST_OR_BODY_DECLARATION_CS__NAME;

	/**
   * The feature id for the '<em><b>Ocl Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int POST_CONDITION_DECLARATION_CS__OCL_EXPRESSION = PRE_POST_OR_BODY_DECLARATION_CS__OCL_EXPRESSION;

	/**
   * The number of structural features of the '<em>Post Condition Declaration CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int POST_CONDITION_DECLARATION_CS_FEATURE_COUNT = PRE_POST_OR_BODY_DECLARATION_CS_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.BodyDeclarationCSImpl <em>Body Declaration CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.BodyDeclarationCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getBodyDeclarationCS()
   * @generated
   */
	int BODY_DECLARATION_CS = 85;

	/**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BODY_DECLARATION_CS__NAME = PRE_POST_OR_BODY_DECLARATION_CS__NAME;

	/**
   * The feature id for the '<em><b>Ocl Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BODY_DECLARATION_CS__OCL_EXPRESSION = PRE_POST_OR_BODY_DECLARATION_CS__OCL_EXPRESSION;

	/**
   * The number of structural features of the '<em>Body Declaration CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BODY_DECLARATION_CS_FEATURE_COUNT = PRE_POST_OR_BODY_DECLARATION_CS_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.OperationDefinitionCSImpl <em>Operation Definition CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.OperationDefinitionCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getOperationDefinitionCS()
   * @generated
   */
	int OPERATION_DEFINITION_CS = 86;

	/**
   * The feature id for the '<em><b>Operation</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_DEFINITION_CS__OPERATION = 0;

	/**
   * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_DEFINITION_CS__PARAMETERS = 1;

	/**
   * The feature id for the '<em><b>Return Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_DEFINITION_CS__RETURN_TYPE = 2;

	/**
   * The number of structural features of the '<em>Operation Definition CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_DEFINITION_CS_FEATURE_COUNT = 3;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.OperationDefinitionInContextCSImpl <em>Operation Definition In Context CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.OperationDefinitionInContextCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getOperationDefinitionInContextCS()
   * @generated
   */
	int OPERATION_DEFINITION_IN_CONTEXT_CS = 87;

	/**
   * The feature id for the '<em><b>Operation</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_DEFINITION_IN_CONTEXT_CS__OPERATION = OPERATION_DEFINITION_CS__OPERATION;

	/**
   * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_DEFINITION_IN_CONTEXT_CS__PARAMETERS = OPERATION_DEFINITION_CS__PARAMETERS;

	/**
   * The feature id for the '<em><b>Return Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_DEFINITION_IN_CONTEXT_CS__RETURN_TYPE = OPERATION_DEFINITION_CS__RETURN_TYPE;

	/**
   * The feature id for the '<em><b>Type Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_DEFINITION_IN_CONTEXT_CS__TYPE_NAME = OPERATION_DEFINITION_CS_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Operation Definition In Context CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_DEFINITION_IN_CONTEXT_CS_FEATURE_COUNT = OPERATION_DEFINITION_CS_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.OperationDefinitionInDefCSImpl <em>Operation Definition In Def CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.OperationDefinitionInDefCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getOperationDefinitionInDefCS()
   * @generated
   */
	int OPERATION_DEFINITION_IN_DEF_CS = 88;

	/**
   * The feature id for the '<em><b>Operation</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_DEFINITION_IN_DEF_CS__OPERATION = OPERATION_DEFINITION_CS__OPERATION;

	/**
   * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_DEFINITION_IN_DEF_CS__PARAMETERS = OPERATION_DEFINITION_CS__PARAMETERS;

	/**
   * The feature id for the '<em><b>Return Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_DEFINITION_IN_DEF_CS__RETURN_TYPE = OPERATION_DEFINITION_CS__RETURN_TYPE;

	/**
   * The number of structural features of the '<em>Operation Definition In Def CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_DEFINITION_IN_DEF_CS_FEATURE_COUNT = OPERATION_DEFINITION_CS_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link tudresden.ocl20.pivot.language.ocl.impl.ParameterCSImpl <em>Parameter CS</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.language.ocl.impl.ParameterCSImpl
   * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getParameterCS()
   * @generated
   */
	int PARAMETER_CS = 89;

	/**
   * The feature id for the '<em><b>Parameter</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER_CS__PARAMETER = 0;

	/**
   * The feature id for the '<em><b>Parameter Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER_CS__PARAMETER_TYPE = 1;

	/**
   * The number of structural features of the '<em>Parameter CS</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER_CS_FEATURE_COUNT = 2;


	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.OclExpressionCS <em>Expression CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Expression CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.OclExpressionCS
   * @generated
   */
	EClass getOclExpressionCS();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.BracketExpCS <em>Bracket Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Bracket Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.BracketExpCS
   * @generated
   */
	EClass getBracketExpCS();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.BracketExpCS#getOclExpression <em>Ocl Expression</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Ocl Expression</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.BracketExpCS#getOclExpression()
   * @see #getBracketExpCS()
   * @generated
   */
	EReference getBracketExpCS_OclExpression();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.NamedLiteralExpCS <em>Named Literal Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Named Literal Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.NamedLiteralExpCS
   * @generated
   */
	EClass getNamedLiteralExpCS();

	/**
   * Returns the meta object for the reference '{@link tudresden.ocl20.pivot.language.ocl.NamedLiteralExpCS#getNamedElement <em>Named Element</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Named Element</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.NamedLiteralExpCS#getNamedElement()
   * @see #getNamedLiteralExpCS()
   * @generated
   */
	EReference getNamedLiteralExpCS_NamedElement();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.PathNameCS <em>Path Name CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Path Name CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.PathNameCS
   * @generated
   */
	EClass getPathNameCS();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.PathNameCS#getSimpleName <em>Simple Name</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Simple Name</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.PathNameCS#getSimpleName()
   * @see #getPathNameCS()
   * @generated
   */
	EReference getPathNameCS_SimpleName();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.PathNameCS#getPathName <em>Path Name</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Path Name</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.PathNameCS#getPathName()
   * @see #getPathNameCS()
   * @generated
   */
	EReference getPathNameCS_PathName();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.SimpleNameCS <em>Simple Name CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Simple Name CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.SimpleNameCS
   * @generated
   */
	EClass getSimpleNameCS();

	/**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.language.ocl.SimpleNameCS#getSimpleName <em>Simple Name</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Simple Name</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.SimpleNameCS#getSimpleName()
   * @see #getSimpleNameCS()
   * @generated
   */
	EAttribute getSimpleNameCS_SimpleName();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.TypeCS <em>Type CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Type CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.TypeCS
   * @generated
   */
	EClass getTypeCS();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.TypePathNameCS <em>Type Path Name CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Type Path Name CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.TypePathNameCS
   * @generated
   */
	EClass getTypePathNameCS();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.TypePathNameSimpleCS <em>Type Path Name Simple CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Type Path Name Simple CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.TypePathNameSimpleCS
   * @generated
   */
	EClass getTypePathNameSimpleCS();

	/**
   * Returns the meta object for the reference '{@link tudresden.ocl20.pivot.language.ocl.TypePathNameSimpleCS#getTypeName <em>Type Name</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Type Name</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.TypePathNameSimpleCS#getTypeName()
   * @see #getTypePathNameSimpleCS()
   * @generated
   */
	EReference getTypePathNameSimpleCS_TypeName();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.TypePathNameNestedCS <em>Type Path Name Nested CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Type Path Name Nested CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.TypePathNameNestedCS
   * @generated
   */
	EClass getTypePathNameNestedCS();

	/**
   * Returns the meta object for the reference '{@link tudresden.ocl20.pivot.language.ocl.TypePathNameNestedCS#getNamespace <em>Namespace</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Namespace</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.TypePathNameNestedCS#getNamespace()
   * @see #getTypePathNameNestedCS()
   * @generated
   */
	EReference getTypePathNameNestedCS_Namespace();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.TypePathNameNestedCS#getTypePathName <em>Type Path Name</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type Path Name</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.TypePathNameNestedCS#getTypePathName()
   * @see #getTypePathNameNestedCS()
   * @generated
   */
	EReference getTypePathNameNestedCS_TypePathName();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.TupleTypeCS <em>Tuple Type CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Tuple Type CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.TupleTypeCS
   * @generated
   */
	EClass getTupleTypeCS();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.TupleTypeCS#getVariableDeclarationList <em>Variable Declaration List</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Variable Declaration List</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.TupleTypeCS#getVariableDeclarationList()
   * @see #getTupleTypeCS()
   * @generated
   */
	EReference getTupleTypeCS_VariableDeclarationList();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.CollectionTypeLiteralExpCS <em>Collection Type Literal Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Collection Type Literal Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.CollectionTypeLiteralExpCS
   * @generated
   */
	EClass getCollectionTypeLiteralExpCS();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.CollectionTypeLiteralExpCS#getCollectionType <em>Collection Type</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Collection Type</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.CollectionTypeLiteralExpCS#getCollectionType()
   * @see #getCollectionTypeLiteralExpCS()
   * @generated
   */
	EReference getCollectionTypeLiteralExpCS_CollectionType();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.TupleTypeLiteralExpCS <em>Tuple Type Literal Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Tuple Type Literal Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.TupleTypeLiteralExpCS
   * @generated
   */
	EClass getTupleTypeLiteralExpCS();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.TupleTypeLiteralExpCS#getTupleType <em>Tuple Type</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Tuple Type</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.TupleTypeLiteralExpCS#getTupleType()
   * @see #getTupleTypeLiteralExpCS()
   * @generated
   */
	EReference getTupleTypeLiteralExpCS_TupleType();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.VariableDeclarationCS <em>Variable Declaration CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Variable Declaration CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.VariableDeclarationCS
   * @generated
   */
	EClass getVariableDeclarationCS();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.VariableDeclarationCS#getVariableName <em>Variable Name</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Variable Name</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.VariableDeclarationCS#getVariableName()
   * @see #getVariableDeclarationCS()
   * @generated
   */
	EReference getVariableDeclarationCS_VariableName();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitCS <em>Variable Declaration With Init CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Variable Declaration With Init CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitCS
   * @generated
   */
	EClass getVariableDeclarationWithInitCS();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitCS#getTypeName <em>Type Name</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type Name</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitCS#getTypeName()
   * @see #getVariableDeclarationWithInitCS()
   * @generated
   */
	EReference getVariableDeclarationWithInitCS_TypeName();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitCS#getInitialization <em>Initialization</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Initialization</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitCS#getInitialization()
   * @see #getVariableDeclarationWithInitCS()
   * @generated
   */
	EReference getVariableDeclarationWithInitCS_Initialization();

	/**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitCS#getEqual <em>Equal</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Equal</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitCS#getEqual()
   * @see #getVariableDeclarationWithInitCS()
   * @generated
   */
	EAttribute getVariableDeclarationWithInitCS_Equal();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithoutInitCS <em>Variable Declaration Without Init CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Variable Declaration Without Init CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithoutInitCS
   * @generated
   */
	EClass getVariableDeclarationWithoutInitCS();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithoutInitCS#getTypeName <em>Type Name</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type Name</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithoutInitCS#getTypeName()
   * @see #getVariableDeclarationWithoutInitCS()
   * @generated
   */
	EReference getVariableDeclarationWithoutInitCS_TypeName();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitListCS <em>Variable Declaration With Init List CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Variable Declaration With Init List CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitListCS
   * @generated
   */
	EClass getVariableDeclarationWithInitListCS();

	/**
   * Returns the meta object for the containment reference list '{@link tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitListCS#getVariableDeclarations <em>Variable Declarations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Variable Declarations</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitListCS#getVariableDeclarations()
   * @see #getVariableDeclarationWithInitListCS()
   * @generated
   */
	EReference getVariableDeclarationWithInitListCS_VariableDeclarations();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithoutInitListCS <em>Variable Declaration Without Init List CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Variable Declaration Without Init List CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithoutInitListCS
   * @generated
   */
	EClass getVariableDeclarationWithoutInitListCS();

	/**
   * Returns the meta object for the containment reference list '{@link tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithoutInitListCS#getVariableDeclarations <em>Variable Declarations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Variable Declarations</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithoutInitListCS#getVariableDeclarations()
   * @see #getVariableDeclarationWithoutInitListCS()
   * @generated
   */
	EReference getVariableDeclarationWithoutInitListCS_VariableDeclarations();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.LiteralExpCS <em>Literal Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Literal Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.LiteralExpCS
   * @generated
   */
	EClass getLiteralExpCS();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.EnumLiteralOrStaticPropertyExpCS <em>Enum Literal Or Static Property Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Enum Literal Or Static Property Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.EnumLiteralOrStaticPropertyExpCS
   * @generated
   */
	EClass getEnumLiteralOrStaticPropertyExpCS();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.EnumLiteralOrStaticPropertyExpCS#getTypeName <em>Type Name</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type Name</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.EnumLiteralOrStaticPropertyExpCS#getTypeName()
   * @see #getEnumLiteralOrStaticPropertyExpCS()
   * @generated
   */
	EReference getEnumLiteralOrStaticPropertyExpCS_TypeName();

	/**
   * Returns the meta object for the reference '{@link tudresden.ocl20.pivot.language.ocl.EnumLiteralOrStaticPropertyExpCS#getEnumLiteralOrStaticProperty <em>Enum Literal Or Static Property</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Enum Literal Or Static Property</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.EnumLiteralOrStaticPropertyExpCS#getEnumLiteralOrStaticProperty()
   * @see #getEnumLiteralOrStaticPropertyExpCS()
   * @generated
   */
	EReference getEnumLiteralOrStaticPropertyExpCS_EnumLiteralOrStaticProperty();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.CollectionLiteralExpCS <em>Collection Literal Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Collection Literal Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.CollectionLiteralExpCS
   * @generated
   */
	EClass getCollectionLiteralExpCS();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.CollectionLiteralExpCS#getCollectionType <em>Collection Type</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Collection Type</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.CollectionLiteralExpCS#getCollectionType()
   * @see #getCollectionLiteralExpCS()
   * @generated
   */
	EReference getCollectionLiteralExpCS_CollectionType();

	/**
   * Returns the meta object for the containment reference list '{@link tudresden.ocl20.pivot.language.ocl.CollectionLiteralExpCS#getCollectionLiteralParts <em>Collection Literal Parts</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Collection Literal Parts</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.CollectionLiteralExpCS#getCollectionLiteralParts()
   * @see #getCollectionLiteralExpCS()
   * @generated
   */
	EReference getCollectionLiteralExpCS_CollectionLiteralParts();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.CollectionTypeIdentifierCS <em>Collection Type Identifier CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Collection Type Identifier CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.CollectionTypeIdentifierCS
   * @generated
   */
	EClass getCollectionTypeIdentifierCS();

	/**
   * Returns the meta object for the reference '{@link tudresden.ocl20.pivot.language.ocl.CollectionTypeIdentifierCS#getTypeName <em>Type Name</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Type Name</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.CollectionTypeIdentifierCS#getTypeName()
   * @see #getCollectionTypeIdentifierCS()
   * @generated
   */
	EReference getCollectionTypeIdentifierCS_TypeName();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.CollectionTypeIdentifierCS#getGenericType <em>Generic Type</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Generic Type</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.CollectionTypeIdentifierCS#getGenericType()
   * @see #getCollectionTypeIdentifierCS()
   * @generated
   */
	EReference getCollectionTypeIdentifierCS_GenericType();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.CollectionLiteralPartsCS <em>Collection Literal Parts CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Collection Literal Parts CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.CollectionLiteralPartsCS
   * @generated
   */
	EClass getCollectionLiteralPartsCS();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.CollectionLiteralPartsOclExpCS <em>Collection Literal Parts Ocl Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Collection Literal Parts Ocl Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.CollectionLiteralPartsOclExpCS
   * @generated
   */
	EClass getCollectionLiteralPartsOclExpCS();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.CollectionLiteralPartsOclExpCS#getOclExpression <em>Ocl Expression</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Ocl Expression</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.CollectionLiteralPartsOclExpCS#getOclExpression()
   * @see #getCollectionLiteralPartsOclExpCS()
   * @generated
   */
	EReference getCollectionLiteralPartsOclExpCS_OclExpression();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.CollectionRangeCS <em>Collection Range CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Collection Range CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.CollectionRangeCS
   * @generated
   */
	EClass getCollectionRangeCS();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.CollectionRangeCS#getFrom <em>From</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>From</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.CollectionRangeCS#getFrom()
   * @see #getCollectionRangeCS()
   * @generated
   */
	EReference getCollectionRangeCS_From();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.CollectionRangeCS#getTo <em>To</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>To</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.CollectionRangeCS#getTo()
   * @see #getCollectionRangeCS()
   * @generated
   */
	EReference getCollectionRangeCS_To();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.CallExpCS <em>Call Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Call Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.CallExpCS
   * @generated
   */
	EClass getCallExpCS();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.LoopExpCS <em>Loop Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Loop Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.LoopExpCS
   * @generated
   */
	EClass getLoopExpCS();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.IteratorExpVariableCS <em>Iterator Exp Variable CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Iterator Exp Variable CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.IteratorExpVariableCS
   * @generated
   */
	EClass getIteratorExpVariableCS();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.IteratorExpVariableCS#getVariableName <em>Variable Name</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Variable Name</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.IteratorExpVariableCS#getVariableName()
   * @see #getIteratorExpVariableCS()
   * @generated
   */
	EReference getIteratorExpVariableCS_VariableName();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.IteratorExpVariableCS#getTypeName <em>Type Name</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type Name</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.IteratorExpVariableCS#getTypeName()
   * @see #getIteratorExpVariableCS()
   * @generated
   */
	EReference getIteratorExpVariableCS_TypeName();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.IteratorExpCS <em>Iterator Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Iterator Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.IteratorExpCS
   * @generated
   */
	EClass getIteratorExpCS();

	/**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.language.ocl.IteratorExpCS#getIteratorName <em>Iterator Name</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Iterator Name</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.IteratorExpCS#getIteratorName()
   * @see #getIteratorExpCS()
   * @generated
   */
	EAttribute getIteratorExpCS_IteratorName();

	/**
   * Returns the meta object for the containment reference list '{@link tudresden.ocl20.pivot.language.ocl.IteratorExpCS#getIteratorVariables <em>Iterator Variables</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Iterator Variables</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.IteratorExpCS#getIteratorVariables()
   * @see #getIteratorExpCS()
   * @generated
   */
	EReference getIteratorExpCS_IteratorVariables();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.IteratorExpCS#getBodyExpression <em>Body Expression</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Body Expression</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.IteratorExpCS#getBodyExpression()
   * @see #getIteratorExpCS()
   * @generated
   */
	EReference getIteratorExpCS_BodyExpression();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.IterateExpCS <em>Iterate Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Iterate Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.IterateExpCS
   * @generated
   */
	EClass getIterateExpCS();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.IterateExpCS#getIteratorVariable <em>Iterator Variable</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Iterator Variable</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.IterateExpCS#getIteratorVariable()
   * @see #getIterateExpCS()
   * @generated
   */
	EReference getIterateExpCS_IteratorVariable();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.IterateExpCS#getResultVariable <em>Result Variable</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Result Variable</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.IterateExpCS#getResultVariable()
   * @see #getIterateExpCS()
   * @generated
   */
	EReference getIterateExpCS_ResultVariable();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.IterateExpCS#getBodyExpression <em>Body Expression</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Body Expression</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.IterateExpCS#getBodyExpression()
   * @see #getIterateExpCS()
   * @generated
   */
	EReference getIterateExpCS_BodyExpression();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.FeatureCallExpCS <em>Feature Call Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Feature Call Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.FeatureCallExpCS
   * @generated
   */
	EClass getFeatureCallExpCS();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.NavigationCallExp <em>Navigation Call Exp</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Navigation Call Exp</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.NavigationCallExp
   * @generated
   */
	EClass getNavigationCallExp();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.NavigationCallExp#getSource <em>Source</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Source</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.NavigationCallExp#getSource()
   * @see #getNavigationCallExp()
   * @generated
   */
	EReference getNavigationCallExp_Source();

	/**
   * Returns the meta object for the attribute list '{@link tudresden.ocl20.pivot.language.ocl.NavigationCallExp#getNavigationOperator <em>Navigation Operator</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Navigation Operator</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.NavigationCallExp#getNavigationOperator()
   * @see #getNavigationCallExp()
   * @generated
   */
	EAttribute getNavigationCallExp_NavigationOperator();

	/**
   * Returns the meta object for the containment reference list '{@link tudresden.ocl20.pivot.language.ocl.NavigationCallExp#getFeatureCalls <em>Feature Calls</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Feature Calls</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.NavigationCallExp#getFeatureCalls()
   * @see #getNavigationCallExp()
   * @generated
   */
	EReference getNavigationCallExp_FeatureCalls();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.OperationCallBaseExpCS <em>Operation Call Base Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operation Call Base Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.OperationCallBaseExpCS
   * @generated
   */
	EClass getOperationCallBaseExpCS();

	/**
   * Returns the meta object for the reference '{@link tudresden.ocl20.pivot.language.ocl.OperationCallBaseExpCS#getOperationName <em>Operation Name</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Operation Name</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.OperationCallBaseExpCS#getOperationName()
   * @see #getOperationCallBaseExpCS()
   * @generated
   */
	EReference getOperationCallBaseExpCS_OperationName();

	/**
   * Returns the meta object for the containment reference list '{@link tudresden.ocl20.pivot.language.ocl.OperationCallBaseExpCS#getArguments <em>Arguments</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Arguments</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.OperationCallBaseExpCS#getArguments()
   * @see #getOperationCallBaseExpCS()
   * @generated
   */
	EReference getOperationCallBaseExpCS_Arguments();

	/**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.language.ocl.OperationCallBaseExpCS#isIsMarkedPre <em>Is Marked Pre</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Is Marked Pre</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.OperationCallBaseExpCS#isIsMarkedPre()
   * @see #getOperationCallBaseExpCS()
   * @generated
   */
	EAttribute getOperationCallBaseExpCS_IsMarkedPre();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.PropertyCallBaseExpCS <em>Property Call Base Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Property Call Base Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.PropertyCallBaseExpCS
   * @generated
   */
	EClass getPropertyCallBaseExpCS();

	/**
   * Returns the meta object for the reference '{@link tudresden.ocl20.pivot.language.ocl.PropertyCallBaseExpCS#getProperty <em>Property</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Property</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.PropertyCallBaseExpCS#getProperty()
   * @see #getPropertyCallBaseExpCS()
   * @generated
   */
	EReference getPropertyCallBaseExpCS_Property();

	/**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.language.ocl.PropertyCallBaseExpCS#isIsMarkedPre <em>Is Marked Pre</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Is Marked Pre</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.PropertyCallBaseExpCS#isIsMarkedPre()
   * @see #getPropertyCallBaseExpCS()
   * @generated
   */
	EAttribute getPropertyCallBaseExpCS_IsMarkedPre();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.ImplicitFeatureCallCS <em>Implicit Feature Call CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Implicit Feature Call CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.ImplicitFeatureCallCS
   * @generated
   */
	EClass getImplicitFeatureCallCS();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.PropertyCallExpCS <em>Property Call Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Property Call Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.PropertyCallExpCS
   * @generated
   */
	EClass getPropertyCallExpCS();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.PropertyCallOnSelfExpCS <em>Property Call On Self Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Property Call On Self Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.PropertyCallOnSelfExpCS
   * @generated
   */
	EClass getPropertyCallOnSelfExpCS();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.ImplicitPropertyCallCS <em>Implicit Property Call CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Implicit Property Call CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.ImplicitPropertyCallCS
   * @generated
   */
	EClass getImplicitPropertyCallCS();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.PropertyCallExplicitPathExpCS <em>Property Call Explicit Path Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Property Call Explicit Path Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.PropertyCallExplicitPathExpCS
   * @generated
   */
	EClass getPropertyCallExplicitPathExpCS();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.PropertyCallExplicitPathExpCS#getSource <em>Source</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Source</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.PropertyCallExplicitPathExpCS#getSource()
   * @see #getPropertyCallExplicitPathExpCS()
   * @generated
   */
	EReference getPropertyCallExplicitPathExpCS_Source();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.PropertyCallExplicitPathExpCS#getPropertyPath <em>Property Path</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Property Path</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.PropertyCallExplicitPathExpCS#getPropertyPath()
   * @see #getPropertyCallExplicitPathExpCS()
   * @generated
   */
	EReference getPropertyCallExplicitPathExpCS_PropertyPath();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.PropertyCallExplicitPathExpCS#getPropertyName <em>Property Name</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Property Name</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.PropertyCallExplicitPathExpCS#getPropertyName()
   * @see #getPropertyCallExplicitPathExpCS()
   * @generated
   */
	EReference getPropertyCallExplicitPathExpCS_PropertyName();

	/**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.language.ocl.PropertyCallExplicitPathExpCS#isIsMarkedPre <em>Is Marked Pre</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Is Marked Pre</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.PropertyCallExplicitPathExpCS#isIsMarkedPre()
   * @see #getPropertyCallExplicitPathExpCS()
   * @generated
   */
	EAttribute getPropertyCallExplicitPathExpCS_IsMarkedPre();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.OperationCallExpCS <em>Operation Call Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operation Call Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.OperationCallExpCS
   * @generated
   */
	EClass getOperationCallExpCS();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.OperationCallOnSelfExpCS <em>Operation Call On Self Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operation Call On Self Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.OperationCallOnSelfExpCS
   * @generated
   */
	EClass getOperationCallOnSelfExpCS();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.StaticOperationCallExpCS <em>Static Operation Call Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Static Operation Call Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.StaticOperationCallExpCS
   * @generated
   */
	EClass getStaticOperationCallExpCS();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.StaticOperationCallExpCS#getTypeName <em>Type Name</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type Name</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.StaticOperationCallExpCS#getTypeName()
   * @see #getStaticOperationCallExpCS()
   * @generated
   */
	EReference getStaticOperationCallExpCS_TypeName();

	/**
   * Returns the meta object for the reference '{@link tudresden.ocl20.pivot.language.ocl.StaticOperationCallExpCS#getOperationName <em>Operation Name</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Operation Name</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.StaticOperationCallExpCS#getOperationName()
   * @see #getStaticOperationCallExpCS()
   * @generated
   */
	EReference getStaticOperationCallExpCS_OperationName();

	/**
   * Returns the meta object for the containment reference list '{@link tudresden.ocl20.pivot.language.ocl.StaticOperationCallExpCS#getArguments <em>Arguments</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Arguments</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.StaticOperationCallExpCS#getArguments()
   * @see #getStaticOperationCallExpCS()
   * @generated
   */
	EReference getStaticOperationCallExpCS_Arguments();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.UnaryOperationCallExpCS <em>Unary Operation Call Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Unary Operation Call Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.UnaryOperationCallExpCS
   * @generated
   */
	EClass getUnaryOperationCallExpCS();

	/**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.language.ocl.UnaryOperationCallExpCS#getOperationName <em>Operation Name</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Operation Name</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.UnaryOperationCallExpCS#getOperationName()
   * @see #getUnaryOperationCallExpCS()
   * @generated
   */
	EAttribute getUnaryOperationCallExpCS_OperationName();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.UnaryOperationCallExpCS#getTarget <em>Target</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Target</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.UnaryOperationCallExpCS#getTarget()
   * @see #getUnaryOperationCallExpCS()
   * @generated
   */
	EReference getUnaryOperationCallExpCS_Target();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.LogicalNotOperationCallExpCS <em>Logical Not Operation Call Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Logical Not Operation Call Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.LogicalNotOperationCallExpCS
   * @generated
   */
	EClass getLogicalNotOperationCallExpCS();

	/**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.language.ocl.LogicalNotOperationCallExpCS#getOperationName <em>Operation Name</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Operation Name</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.LogicalNotOperationCallExpCS#getOperationName()
   * @see #getLogicalNotOperationCallExpCS()
   * @generated
   */
	EAttribute getLogicalNotOperationCallExpCS_OperationName();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.LogicalNotOperationCallExpCS#getTarget <em>Target</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Target</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.LogicalNotOperationCallExpCS#getTarget()
   * @see #getLogicalNotOperationCallExpCS()
   * @generated
   */
	EReference getLogicalNotOperationCallExpCS_Target();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.OperationCallWithSourceExpCS <em>Operation Call With Source Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operation Call With Source Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.OperationCallWithSourceExpCS
   * @generated
   */
	EClass getOperationCallWithSourceExpCS();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.OperationCallWithSourceExpCS#getSource <em>Source</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Source</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.OperationCallWithSourceExpCS#getSource()
   * @see #getOperationCallWithSourceExpCS()
   * @generated
   */
	EReference getOperationCallWithSourceExpCS_Source();

	/**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.language.ocl.OperationCallWithSourceExpCS#isIsMarkedPre <em>Is Marked Pre</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Is Marked Pre</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.OperationCallWithSourceExpCS#isIsMarkedPre()
   * @see #getOperationCallWithSourceExpCS()
   * @generated
   */
	EAttribute getOperationCallWithSourceExpCS_IsMarkedPre();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.OperationCallBinaryExpCS <em>Operation Call Binary Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operation Call Binary Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.OperationCallBinaryExpCS
   * @generated
   */
	EClass getOperationCallBinaryExpCS();

	/**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.language.ocl.OperationCallBinaryExpCS#getOperationName <em>Operation Name</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Operation Name</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.OperationCallBinaryExpCS#getOperationName()
   * @see #getOperationCallBinaryExpCS()
   * @generated
   */
	EAttribute getOperationCallBinaryExpCS_OperationName();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.OperationCallBinaryExpCS#getTarget <em>Target</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Target</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.OperationCallBinaryExpCS#getTarget()
   * @see #getOperationCallBinaryExpCS()
   * @generated
   */
	EReference getOperationCallBinaryExpCS_Target();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.AdditiveOperationCallExpCS <em>Additive Operation Call Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Additive Operation Call Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.AdditiveOperationCallExpCS
   * @generated
   */
	EClass getAdditiveOperationCallExpCS();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.MultOperationCallExpCS <em>Mult Operation Call Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Mult Operation Call Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.MultOperationCallExpCS
   * @generated
   */
	EClass getMultOperationCallExpCS();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.RelationalOperationCallExpCS <em>Relational Operation Call Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Relational Operation Call Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.RelationalOperationCallExpCS
   * @generated
   */
	EClass getRelationalOperationCallExpCS();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.EqualityOperationCallExpCS <em>Equality Operation Call Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Equality Operation Call Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.EqualityOperationCallExpCS
   * @generated
   */
	EClass getEqualityOperationCallExpCS();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.LogicalAndOperationCallExpCS <em>Logical And Operation Call Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Logical And Operation Call Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.LogicalAndOperationCallExpCS
   * @generated
   */
	EClass getLogicalAndOperationCallExpCS();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.LogicalOrOperationCallExpCS <em>Logical Or Operation Call Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Logical Or Operation Call Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.LogicalOrOperationCallExpCS
   * @generated
   */
	EClass getLogicalOrOperationCallExpCS();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.LogicalXorOperationCallExpCS <em>Logical Xor Operation Call Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Logical Xor Operation Call Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.LogicalXorOperationCallExpCS
   * @generated
   */
	EClass getLogicalXorOperationCallExpCS();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.LogicalImpliesOperationCallExpCS <em>Logical Implies Operation Call Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Logical Implies Operation Call Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.LogicalImpliesOperationCallExpCS
   * @generated
   */
	EClass getLogicalImpliesOperationCallExpCS();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.OperationCallWithImlicitSourceExpCS <em>Operation Call With Imlicit Source Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operation Call With Imlicit Source Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.OperationCallWithImlicitSourceExpCS
   * @generated
   */
	EClass getOperationCallWithImlicitSourceExpCS();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.ImplicitOperationCallCS <em>Implicit Operation Call CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Implicit Operation Call CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.ImplicitOperationCallCS
   * @generated
   */
	EClass getImplicitOperationCallCS();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.TupleLiteralExpCS <em>Tuple Literal Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Tuple Literal Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.TupleLiteralExpCS
   * @generated
   */
	EClass getTupleLiteralExpCS();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.TupleLiteralExpCS#getVariableDeclarations <em>Variable Declarations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Variable Declarations</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.TupleLiteralExpCS#getVariableDeclarations()
   * @see #getTupleLiteralExpCS()
   * @generated
   */
	EReference getTupleLiteralExpCS_VariableDeclarations();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.PrimitiveLiteralExpCS <em>Primitive Literal Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Primitive Literal Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.PrimitiveLiteralExpCS
   * @generated
   */
	EClass getPrimitiveLiteralExpCS();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.IntegerLiteralExpCS <em>Integer Literal Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Integer Literal Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.IntegerLiteralExpCS
   * @generated
   */
	EClass getIntegerLiteralExpCS();

	/**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.language.ocl.IntegerLiteralExpCS#getIntegerLiteral <em>Integer Literal</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Integer Literal</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.IntegerLiteralExpCS#getIntegerLiteral()
   * @see #getIntegerLiteralExpCS()
   * @generated
   */
	EAttribute getIntegerLiteralExpCS_IntegerLiteral();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.RealLiteralExpCS <em>Real Literal Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Real Literal Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.RealLiteralExpCS
   * @generated
   */
	EClass getRealLiteralExpCS();

	/**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.language.ocl.RealLiteralExpCS#getIntValue <em>Int Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Int Value</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.RealLiteralExpCS#getIntValue()
   * @see #getRealLiteralExpCS()
   * @generated
   */
	EAttribute getRealLiteralExpCS_IntValue();

	/**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.language.ocl.RealLiteralExpCS#getRealValue <em>Real Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Real Value</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.RealLiteralExpCS#getRealValue()
   * @see #getRealLiteralExpCS()
   * @generated
   */
	EAttribute getRealLiteralExpCS_RealValue();

	/**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.language.ocl.RealLiteralExpCS#getNavigationOperator <em>Navigation Operator</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Navigation Operator</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.RealLiteralExpCS#getNavigationOperator()
   * @see #getRealLiteralExpCS()
   * @generated
   */
	EAttribute getRealLiteralExpCS_NavigationOperator();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.BooleanLiteralExpCS <em>Boolean Literal Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Boolean Literal Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.BooleanLiteralExpCS
   * @generated
   */
	EClass getBooleanLiteralExpCS();

	/**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.language.ocl.BooleanLiteralExpCS#isBooleanLiteral <em>Boolean Literal</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Boolean Literal</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.BooleanLiteralExpCS#isBooleanLiteral()
   * @see #getBooleanLiteralExpCS()
   * @generated
   */
	EAttribute getBooleanLiteralExpCS_BooleanLiteral();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.StringLiteralExpCS <em>String Literal Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>String Literal Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.StringLiteralExpCS
   * @generated
   */
	EClass getStringLiteralExpCS();

	/**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.language.ocl.StringLiteralExpCS#getStringLiteral <em>String Literal</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>String Literal</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.StringLiteralExpCS#getStringLiteral()
   * @see #getStringLiteralExpCS()
   * @generated
   */
	EAttribute getStringLiteralExpCS_StringLiteral();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.InvalidLiteralExpCS <em>Invalid Literal Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Invalid Literal Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.InvalidLiteralExpCS
   * @generated
   */
	EClass getInvalidLiteralExpCS();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.NullLiteralExpCS <em>Null Literal Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Null Literal Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.NullLiteralExpCS
   * @generated
   */
	EClass getNullLiteralExpCS();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.LetExpCS <em>Let Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Let Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.LetExpCS
   * @generated
   */
	EClass getLetExpCS();

	/**
   * Returns the meta object for the containment reference list '{@link tudresden.ocl20.pivot.language.ocl.LetExpCS#getVariableDeclarations <em>Variable Declarations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Variable Declarations</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.LetExpCS#getVariableDeclarations()
   * @see #getLetExpCS()
   * @generated
   */
	EReference getLetExpCS_VariableDeclarations();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.LetExpCS#getOclExpression <em>Ocl Expression</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Ocl Expression</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.LetExpCS#getOclExpression()
   * @see #getLetExpCS()
   * @generated
   */
	EReference getLetExpCS_OclExpression();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.IfExpCS <em>If Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>If Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.IfExpCS
   * @generated
   */
	EClass getIfExpCS();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.IfExpCS#getCondition <em>Condition</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Condition</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.IfExpCS#getCondition()
   * @see #getIfExpCS()
   * @generated
   */
	EReference getIfExpCS_Condition();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.IfExpCS#getThenBranch <em>Then Branch</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Then Branch</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.IfExpCS#getThenBranch()
   * @see #getIfExpCS()
   * @generated
   */
	EReference getIfExpCS_ThenBranch();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.IfExpCS#getElseBranch <em>Else Branch</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Else Branch</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.IfExpCS#getElseBranch()
   * @see #getIfExpCS()
   * @generated
   */
	EReference getIfExpCS_ElseBranch();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.PackageDeclarationCS <em>Package Declaration CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Package Declaration CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.PackageDeclarationCS
   * @generated
   */
	EClass getPackageDeclarationCS();

	/**
   * Returns the meta object for the containment reference list '{@link tudresden.ocl20.pivot.language.ocl.PackageDeclarationCS#getContextDeclarations <em>Context Declarations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Context Declarations</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.PackageDeclarationCS#getContextDeclarations()
   * @see #getPackageDeclarationCS()
   * @generated
   */
	EReference getPackageDeclarationCS_ContextDeclarations();

	/**
   * Returns the meta object for the reference list '{@link tudresden.ocl20.pivot.language.ocl.PackageDeclarationCS#getLayoutInformation <em>Layout Information</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Layout Information</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.PackageDeclarationCS#getLayoutInformation()
   * @see #getPackageDeclarationCS()
   * @generated
   */
	EReference getPackageDeclarationCS_LayoutInformation();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.PackageDeclarationWithNamespaceCS <em>Package Declaration With Namespace CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Package Declaration With Namespace CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.PackageDeclarationWithNamespaceCS
   * @generated
   */
	EClass getPackageDeclarationWithNamespaceCS();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.PackageDeclarationWithNamespaceCS#getNestedNamespace <em>Nested Namespace</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Nested Namespace</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.PackageDeclarationWithNamespaceCS#getNestedNamespace()
   * @see #getPackageDeclarationWithNamespaceCS()
   * @generated
   */
	EReference getPackageDeclarationWithNamespaceCS_NestedNamespace();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.PackageDeclarationNestedNamespaceCS <em>Package Declaration Nested Namespace CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Package Declaration Nested Namespace CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.PackageDeclarationNestedNamespaceCS
   * @generated
   */
	EClass getPackageDeclarationNestedNamespaceCS();

	/**
   * Returns the meta object for the reference '{@link tudresden.ocl20.pivot.language.ocl.PackageDeclarationNestedNamespaceCS#getNamespace <em>Namespace</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Namespace</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.PackageDeclarationNestedNamespaceCS#getNamespace()
   * @see #getPackageDeclarationNestedNamespaceCS()
   * @generated
   */
	EReference getPackageDeclarationNestedNamespaceCS_Namespace();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.PackageDeclarationNestedNamespaceCS#getNestedNamespace <em>Nested Namespace</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Nested Namespace</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.PackageDeclarationNestedNamespaceCS#getNestedNamespace()
   * @see #getPackageDeclarationNestedNamespaceCS()
   * @generated
   */
	EReference getPackageDeclarationNestedNamespaceCS_NestedNamespace();

	/**
   * Returns the meta object for the reference list '{@link tudresden.ocl20.pivot.language.ocl.PackageDeclarationNestedNamespaceCS#getLayoutInformation <em>Layout Information</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Layout Information</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.PackageDeclarationNestedNamespaceCS#getLayoutInformation()
   * @see #getPackageDeclarationNestedNamespaceCS()
   * @generated
   */
	EReference getPackageDeclarationNestedNamespaceCS_LayoutInformation();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.PackageDeclarationWithoutNamespaceCS <em>Package Declaration Without Namespace CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Package Declaration Without Namespace CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.PackageDeclarationWithoutNamespaceCS
   * @generated
   */
	EClass getPackageDeclarationWithoutNamespaceCS();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.ContextDeclarationCS <em>Context Declaration CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Context Declaration CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.ContextDeclarationCS
   * @generated
   */
	EClass getContextDeclarationCS();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.AttributeContextDeclarationCS <em>Attribute Context Declaration CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Attribute Context Declaration CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.AttributeContextDeclarationCS
   * @generated
   */
	EClass getAttributeContextDeclarationCS();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.AttributeContextDeclarationCS#getTypeName <em>Type Name</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type Name</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.AttributeContextDeclarationCS#getTypeName()
   * @see #getAttributeContextDeclarationCS()
   * @generated
   */
	EReference getAttributeContextDeclarationCS_TypeName();

	/**
   * Returns the meta object for the reference '{@link tudresden.ocl20.pivot.language.ocl.AttributeContextDeclarationCS#getProperty <em>Property</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Property</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.AttributeContextDeclarationCS#getProperty()
   * @see #getAttributeContextDeclarationCS()
   * @generated
   */
	EReference getAttributeContextDeclarationCS_Property();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.AttributeContextDeclarationCS#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.AttributeContextDeclarationCS#getType()
   * @see #getAttributeContextDeclarationCS()
   * @generated
   */
	EReference getAttributeContextDeclarationCS_Type();

	/**
   * Returns the meta object for the containment reference list '{@link tudresden.ocl20.pivot.language.ocl.AttributeContextDeclarationCS#getInitOrDeriveValue <em>Init Or Derive Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Init Or Derive Value</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.AttributeContextDeclarationCS#getInitOrDeriveValue()
   * @see #getAttributeContextDeclarationCS()
   * @generated
   */
	EReference getAttributeContextDeclarationCS_InitOrDeriveValue();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.ClassifierContextDeclarationCS <em>Classifier Context Declaration CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Classifier Context Declaration CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.ClassifierContextDeclarationCS
   * @generated
   */
	EClass getClassifierContextDeclarationCS();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.ClassifierContextDeclarationCS#getTypeName <em>Type Name</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type Name</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.ClassifierContextDeclarationCS#getTypeName()
   * @see #getClassifierContextDeclarationCS()
   * @generated
   */
	EReference getClassifierContextDeclarationCS_TypeName();

	/**
   * Returns the meta object for the containment reference list '{@link tudresden.ocl20.pivot.language.ocl.ClassifierContextDeclarationCS#getInvariantsAndDefinitions <em>Invariants And Definitions</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Invariants And Definitions</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.ClassifierContextDeclarationCS#getInvariantsAndDefinitions()
   * @see #getClassifierContextDeclarationCS()
   * @generated
   */
	EReference getClassifierContextDeclarationCS_InvariantsAndDefinitions();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.OperationContextDeclarationCS <em>Operation Context Declaration CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operation Context Declaration CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.OperationContextDeclarationCS
   * @generated
   */
	EClass getOperationContextDeclarationCS();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.OperationContextDeclarationCS#getOperation <em>Operation</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Operation</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.OperationContextDeclarationCS#getOperation()
   * @see #getOperationContextDeclarationCS()
   * @generated
   */
	EReference getOperationContextDeclarationCS_Operation();

	/**
   * Returns the meta object for the containment reference list '{@link tudresden.ocl20.pivot.language.ocl.OperationContextDeclarationCS#getPrePostOrBodyDeclarations <em>Pre Post Or Body Declarations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Pre Post Or Body Declarations</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.OperationContextDeclarationCS#getPrePostOrBodyDeclarations()
   * @see #getOperationContextDeclarationCS()
   * @generated
   */
	EReference getOperationContextDeclarationCS_PrePostOrBodyDeclarations();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.InitOrDeriveValueCS <em>Init Or Derive Value CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Init Or Derive Value CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.InitOrDeriveValueCS
   * @generated
   */
	EClass getInitOrDeriveValueCS();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.InitOrDeriveValueCS#getOclExpression <em>Ocl Expression</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Ocl Expression</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.InitOrDeriveValueCS#getOclExpression()
   * @see #getInitOrDeriveValueCS()
   * @generated
   */
	EReference getInitOrDeriveValueCS_OclExpression();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.InitValueCS <em>Init Value CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Init Value CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.InitValueCS
   * @generated
   */
	EClass getInitValueCS();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.DeriveValueCS <em>Derive Value CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Derive Value CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.DeriveValueCS
   * @generated
   */
	EClass getDeriveValueCS();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.InvariantOrDefinitionCS <em>Invariant Or Definition CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Invariant Or Definition CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.InvariantOrDefinitionCS
   * @generated
   */
	EClass getInvariantOrDefinitionCS();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.InvariantExpCS <em>Invariant Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Invariant Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.InvariantExpCS
   * @generated
   */
	EClass getInvariantExpCS();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.InvariantExpCS#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.InvariantExpCS#getName()
   * @see #getInvariantExpCS()
   * @generated
   */
	EReference getInvariantExpCS_Name();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.InvariantExpCS#getOclExpression <em>Ocl Expression</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Ocl Expression</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.InvariantExpCS#getOclExpression()
   * @see #getInvariantExpCS()
   * @generated
   */
	EReference getInvariantExpCS_OclExpression();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.DefinitionExpCS <em>Definition Exp CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Definition Exp CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.DefinitionExpCS
   * @generated
   */
	EClass getDefinitionExpCS();

	/**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.language.ocl.DefinitionExpCS#isStatic <em>Static</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Static</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.DefinitionExpCS#isStatic()
   * @see #getDefinitionExpCS()
   * @generated
   */
	EAttribute getDefinitionExpCS_Static();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.DefinitionExpCS#getDefinitionExpPart <em>Definition Exp Part</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Definition Exp Part</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.DefinitionExpCS#getDefinitionExpPart()
   * @see #getDefinitionExpCS()
   * @generated
   */
	EReference getDefinitionExpCS_DefinitionExpPart();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.DefinitionExpPartCS <em>Definition Exp Part CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Definition Exp Part CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.DefinitionExpPartCS
   * @generated
   */
	EClass getDefinitionExpPartCS();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.DefinitionExpPropertyCS <em>Definition Exp Property CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Definition Exp Property CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.DefinitionExpPropertyCS
   * @generated
   */
	EClass getDefinitionExpPropertyCS();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.DefinitionExpPropertyCS#getVariableDeclaration <em>Variable Declaration</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Variable Declaration</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.DefinitionExpPropertyCS#getVariableDeclaration()
   * @see #getDefinitionExpPropertyCS()
   * @generated
   */
	EReference getDefinitionExpPropertyCS_VariableDeclaration();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.DefinitionExpOperationCS <em>Definition Exp Operation CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Definition Exp Operation CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.DefinitionExpOperationCS
   * @generated
   */
	EClass getDefinitionExpOperationCS();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.DefinitionExpOperationCS#getOperation <em>Operation</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Operation</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.DefinitionExpOperationCS#getOperation()
   * @see #getDefinitionExpOperationCS()
   * @generated
   */
	EReference getDefinitionExpOperationCS_Operation();

	/**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.language.ocl.DefinitionExpOperationCS#getEqual <em>Equal</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Equal</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.DefinitionExpOperationCS#getEqual()
   * @see #getDefinitionExpOperationCS()
   * @generated
   */
	EAttribute getDefinitionExpOperationCS_Equal();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.DefinitionExpOperationCS#getOclExpression <em>Ocl Expression</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Ocl Expression</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.DefinitionExpOperationCS#getOclExpression()
   * @see #getDefinitionExpOperationCS()
   * @generated
   */
	EReference getDefinitionExpOperationCS_OclExpression();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.PrePostOrBodyDeclarationCS <em>Pre Post Or Body Declaration CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pre Post Or Body Declaration CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.PrePostOrBodyDeclarationCS
   * @generated
   */
	EClass getPrePostOrBodyDeclarationCS();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.PrePostOrBodyDeclarationCS#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.PrePostOrBodyDeclarationCS#getName()
   * @see #getPrePostOrBodyDeclarationCS()
   * @generated
   */
	EReference getPrePostOrBodyDeclarationCS_Name();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.PrePostOrBodyDeclarationCS#getOclExpression <em>Ocl Expression</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Ocl Expression</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.PrePostOrBodyDeclarationCS#getOclExpression()
   * @see #getPrePostOrBodyDeclarationCS()
   * @generated
   */
	EReference getPrePostOrBodyDeclarationCS_OclExpression();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.PreConditionDeclarationCS <em>Pre Condition Declaration CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pre Condition Declaration CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.PreConditionDeclarationCS
   * @generated
   */
	EClass getPreConditionDeclarationCS();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.PostConditionDeclarationCS <em>Post Condition Declaration CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Post Condition Declaration CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.PostConditionDeclarationCS
   * @generated
   */
	EClass getPostConditionDeclarationCS();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.BodyDeclarationCS <em>Body Declaration CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Body Declaration CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.BodyDeclarationCS
   * @generated
   */
	EClass getBodyDeclarationCS();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.OperationDefinitionCS <em>Operation Definition CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operation Definition CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.OperationDefinitionCS
   * @generated
   */
	EClass getOperationDefinitionCS();

	/**
   * Returns the meta object for the reference '{@link tudresden.ocl20.pivot.language.ocl.OperationDefinitionCS#getOperation <em>Operation</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Operation</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.OperationDefinitionCS#getOperation()
   * @see #getOperationDefinitionCS()
   * @generated
   */
	EReference getOperationDefinitionCS_Operation();

	/**
   * Returns the meta object for the containment reference list '{@link tudresden.ocl20.pivot.language.ocl.OperationDefinitionCS#getParameters <em>Parameters</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Parameters</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.OperationDefinitionCS#getParameters()
   * @see #getOperationDefinitionCS()
   * @generated
   */
	EReference getOperationDefinitionCS_Parameters();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.OperationDefinitionCS#getReturnType <em>Return Type</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Return Type</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.OperationDefinitionCS#getReturnType()
   * @see #getOperationDefinitionCS()
   * @generated
   */
	EReference getOperationDefinitionCS_ReturnType();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.OperationDefinitionInContextCS <em>Operation Definition In Context CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operation Definition In Context CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.OperationDefinitionInContextCS
   * @generated
   */
	EClass getOperationDefinitionInContextCS();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.OperationDefinitionInContextCS#getTypeName <em>Type Name</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type Name</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.OperationDefinitionInContextCS#getTypeName()
   * @see #getOperationDefinitionInContextCS()
   * @generated
   */
	EReference getOperationDefinitionInContextCS_TypeName();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.OperationDefinitionInDefCS <em>Operation Definition In Def CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operation Definition In Def CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.OperationDefinitionInDefCS
   * @generated
   */
	EClass getOperationDefinitionInDefCS();

	/**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.language.ocl.ParameterCS <em>Parameter CS</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Parameter CS</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.ParameterCS
   * @generated
   */
	EClass getParameterCS();

	/**
   * Returns the meta object for the reference '{@link tudresden.ocl20.pivot.language.ocl.ParameterCS#getParameter <em>Parameter</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Parameter</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.ParameterCS#getParameter()
   * @see #getParameterCS()
   * @generated
   */
	EReference getParameterCS_Parameter();

	/**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.language.ocl.ParameterCS#getParameterType <em>Parameter Type</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Parameter Type</em>'.
   * @see tudresden.ocl20.pivot.language.ocl.ParameterCS#getParameterType()
   * @see #getParameterCS()
   * @generated
   */
	EReference getParameterCS_ParameterType();

	/**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
	OclFactory getOclFactory();

	/**
   * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
   * @generated
   */
	interface Literals {
		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.OclExpressionCSImpl <em>Expression CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclExpressionCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getOclExpressionCS()
     * @generated
     */
		EClass OCL_EXPRESSION_CS = eINSTANCE.getOclExpressionCS();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.BracketExpCSImpl <em>Bracket Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.BracketExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getBracketExpCS()
     * @generated
     */
		EClass BRACKET_EXP_CS = eINSTANCE.getBracketExpCS();

		/**
     * The meta object literal for the '<em><b>Ocl Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference BRACKET_EXP_CS__OCL_EXPRESSION = eINSTANCE.getBracketExpCS_OclExpression();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.NamedLiteralExpCSImpl <em>Named Literal Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.NamedLiteralExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getNamedLiteralExpCS()
     * @generated
     */
		EClass NAMED_LITERAL_EXP_CS = eINSTANCE.getNamedLiteralExpCS();

		/**
     * The meta object literal for the '<em><b>Named Element</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference NAMED_LITERAL_EXP_CS__NAMED_ELEMENT = eINSTANCE.getNamedLiteralExpCS_NamedElement();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.PathNameCSImpl <em>Path Name CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.PathNameCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getPathNameCS()
     * @generated
     */
		EClass PATH_NAME_CS = eINSTANCE.getPathNameCS();

		/**
     * The meta object literal for the '<em><b>Simple Name</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PATH_NAME_CS__SIMPLE_NAME = eINSTANCE.getPathNameCS_SimpleName();

		/**
     * The meta object literal for the '<em><b>Path Name</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PATH_NAME_CS__PATH_NAME = eINSTANCE.getPathNameCS_PathName();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.SimpleNameCSImpl <em>Simple Name CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.SimpleNameCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getSimpleNameCS()
     * @generated
     */
		EClass SIMPLE_NAME_CS = eINSTANCE.getSimpleNameCS();

		/**
     * The meta object literal for the '<em><b>Simple Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute SIMPLE_NAME_CS__SIMPLE_NAME = eINSTANCE.getSimpleNameCS_SimpleName();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.TypeCSImpl <em>Type CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.TypeCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getTypeCS()
     * @generated
     */
		EClass TYPE_CS = eINSTANCE.getTypeCS();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.TypePathNameCSImpl <em>Type Path Name CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.TypePathNameCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getTypePathNameCS()
     * @generated
     */
		EClass TYPE_PATH_NAME_CS = eINSTANCE.getTypePathNameCS();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.TypePathNameSimpleCSImpl <em>Type Path Name Simple CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.TypePathNameSimpleCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getTypePathNameSimpleCS()
     * @generated
     */
		EClass TYPE_PATH_NAME_SIMPLE_CS = eINSTANCE.getTypePathNameSimpleCS();

		/**
     * The meta object literal for the '<em><b>Type Name</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference TYPE_PATH_NAME_SIMPLE_CS__TYPE_NAME = eINSTANCE.getTypePathNameSimpleCS_TypeName();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.TypePathNameNestedCSImpl <em>Type Path Name Nested CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.TypePathNameNestedCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getTypePathNameNestedCS()
     * @generated
     */
		EClass TYPE_PATH_NAME_NESTED_CS = eINSTANCE.getTypePathNameNestedCS();

		/**
     * The meta object literal for the '<em><b>Namespace</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference TYPE_PATH_NAME_NESTED_CS__NAMESPACE = eINSTANCE.getTypePathNameNestedCS_Namespace();

		/**
     * The meta object literal for the '<em><b>Type Path Name</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference TYPE_PATH_NAME_NESTED_CS__TYPE_PATH_NAME = eINSTANCE.getTypePathNameNestedCS_TypePathName();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.TupleTypeCSImpl <em>Tuple Type CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.TupleTypeCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getTupleTypeCS()
     * @generated
     */
		EClass TUPLE_TYPE_CS = eINSTANCE.getTupleTypeCS();

		/**
     * The meta object literal for the '<em><b>Variable Declaration List</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference TUPLE_TYPE_CS__VARIABLE_DECLARATION_LIST = eINSTANCE.getTupleTypeCS_VariableDeclarationList();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.CollectionTypeLiteralExpCSImpl <em>Collection Type Literal Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.CollectionTypeLiteralExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getCollectionTypeLiteralExpCS()
     * @generated
     */
		EClass COLLECTION_TYPE_LITERAL_EXP_CS = eINSTANCE.getCollectionTypeLiteralExpCS();

		/**
     * The meta object literal for the '<em><b>Collection Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COLLECTION_TYPE_LITERAL_EXP_CS__COLLECTION_TYPE = eINSTANCE.getCollectionTypeLiteralExpCS_CollectionType();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.TupleTypeLiteralExpCSImpl <em>Tuple Type Literal Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.TupleTypeLiteralExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getTupleTypeLiteralExpCS()
     * @generated
     */
		EClass TUPLE_TYPE_LITERAL_EXP_CS = eINSTANCE.getTupleTypeLiteralExpCS();

		/**
     * The meta object literal for the '<em><b>Tuple Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference TUPLE_TYPE_LITERAL_EXP_CS__TUPLE_TYPE = eINSTANCE.getTupleTypeLiteralExpCS_TupleType();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.VariableDeclarationCSImpl <em>Variable Declaration CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.VariableDeclarationCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getVariableDeclarationCS()
     * @generated
     */
		EClass VARIABLE_DECLARATION_CS = eINSTANCE.getVariableDeclarationCS();

		/**
     * The meta object literal for the '<em><b>Variable Name</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference VARIABLE_DECLARATION_CS__VARIABLE_NAME = eINSTANCE.getVariableDeclarationCS_VariableName();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.VariableDeclarationWithInitCSImpl <em>Variable Declaration With Init CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.VariableDeclarationWithInitCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getVariableDeclarationWithInitCS()
     * @generated
     */
		EClass VARIABLE_DECLARATION_WITH_INIT_CS = eINSTANCE.getVariableDeclarationWithInitCS();

		/**
     * The meta object literal for the '<em><b>Type Name</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference VARIABLE_DECLARATION_WITH_INIT_CS__TYPE_NAME = eINSTANCE.getVariableDeclarationWithInitCS_TypeName();

		/**
     * The meta object literal for the '<em><b>Initialization</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference VARIABLE_DECLARATION_WITH_INIT_CS__INITIALIZATION = eINSTANCE.getVariableDeclarationWithInitCS_Initialization();

		/**
     * The meta object literal for the '<em><b>Equal</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute VARIABLE_DECLARATION_WITH_INIT_CS__EQUAL = eINSTANCE.getVariableDeclarationWithInitCS_Equal();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.VariableDeclarationWithoutInitCSImpl <em>Variable Declaration Without Init CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.VariableDeclarationWithoutInitCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getVariableDeclarationWithoutInitCS()
     * @generated
     */
		EClass VARIABLE_DECLARATION_WITHOUT_INIT_CS = eINSTANCE.getVariableDeclarationWithoutInitCS();

		/**
     * The meta object literal for the '<em><b>Type Name</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference VARIABLE_DECLARATION_WITHOUT_INIT_CS__TYPE_NAME = eINSTANCE.getVariableDeclarationWithoutInitCS_TypeName();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.VariableDeclarationWithInitListCSImpl <em>Variable Declaration With Init List CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.VariableDeclarationWithInitListCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getVariableDeclarationWithInitListCS()
     * @generated
     */
		EClass VARIABLE_DECLARATION_WITH_INIT_LIST_CS = eINSTANCE.getVariableDeclarationWithInitListCS();

		/**
     * The meta object literal for the '<em><b>Variable Declarations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference VARIABLE_DECLARATION_WITH_INIT_LIST_CS__VARIABLE_DECLARATIONS = eINSTANCE.getVariableDeclarationWithInitListCS_VariableDeclarations();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.VariableDeclarationWithoutInitListCSImpl <em>Variable Declaration Without Init List CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.VariableDeclarationWithoutInitListCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getVariableDeclarationWithoutInitListCS()
     * @generated
     */
		EClass VARIABLE_DECLARATION_WITHOUT_INIT_LIST_CS = eINSTANCE.getVariableDeclarationWithoutInitListCS();

		/**
     * The meta object literal for the '<em><b>Variable Declarations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference VARIABLE_DECLARATION_WITHOUT_INIT_LIST_CS__VARIABLE_DECLARATIONS = eINSTANCE.getVariableDeclarationWithoutInitListCS_VariableDeclarations();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.LiteralExpCSImpl <em>Literal Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.LiteralExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getLiteralExpCS()
     * @generated
     */
		EClass LITERAL_EXP_CS = eINSTANCE.getLiteralExpCS();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.EnumLiteralOrStaticPropertyExpCSImpl <em>Enum Literal Or Static Property Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.EnumLiteralOrStaticPropertyExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getEnumLiteralOrStaticPropertyExpCS()
     * @generated
     */
		EClass ENUM_LITERAL_OR_STATIC_PROPERTY_EXP_CS = eINSTANCE.getEnumLiteralOrStaticPropertyExpCS();

		/**
     * The meta object literal for the '<em><b>Type Name</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ENUM_LITERAL_OR_STATIC_PROPERTY_EXP_CS__TYPE_NAME = eINSTANCE.getEnumLiteralOrStaticPropertyExpCS_TypeName();

		/**
     * The meta object literal for the '<em><b>Enum Literal Or Static Property</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ENUM_LITERAL_OR_STATIC_PROPERTY_EXP_CS__ENUM_LITERAL_OR_STATIC_PROPERTY = eINSTANCE.getEnumLiteralOrStaticPropertyExpCS_EnumLiteralOrStaticProperty();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.CollectionLiteralExpCSImpl <em>Collection Literal Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.CollectionLiteralExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getCollectionLiteralExpCS()
     * @generated
     */
		EClass COLLECTION_LITERAL_EXP_CS = eINSTANCE.getCollectionLiteralExpCS();

		/**
     * The meta object literal for the '<em><b>Collection Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COLLECTION_LITERAL_EXP_CS__COLLECTION_TYPE = eINSTANCE.getCollectionLiteralExpCS_CollectionType();

		/**
     * The meta object literal for the '<em><b>Collection Literal Parts</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COLLECTION_LITERAL_EXP_CS__COLLECTION_LITERAL_PARTS = eINSTANCE.getCollectionLiteralExpCS_CollectionLiteralParts();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.CollectionTypeIdentifierCSImpl <em>Collection Type Identifier CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.CollectionTypeIdentifierCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getCollectionTypeIdentifierCS()
     * @generated
     */
		EClass COLLECTION_TYPE_IDENTIFIER_CS = eINSTANCE.getCollectionTypeIdentifierCS();

		/**
     * The meta object literal for the '<em><b>Type Name</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COLLECTION_TYPE_IDENTIFIER_CS__TYPE_NAME = eINSTANCE.getCollectionTypeIdentifierCS_TypeName();

		/**
     * The meta object literal for the '<em><b>Generic Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COLLECTION_TYPE_IDENTIFIER_CS__GENERIC_TYPE = eINSTANCE.getCollectionTypeIdentifierCS_GenericType();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.CollectionLiteralPartsCSImpl <em>Collection Literal Parts CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.CollectionLiteralPartsCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getCollectionLiteralPartsCS()
     * @generated
     */
		EClass COLLECTION_LITERAL_PARTS_CS = eINSTANCE.getCollectionLiteralPartsCS();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.CollectionLiteralPartsOclExpCSImpl <em>Collection Literal Parts Ocl Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.CollectionLiteralPartsOclExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getCollectionLiteralPartsOclExpCS()
     * @generated
     */
		EClass COLLECTION_LITERAL_PARTS_OCL_EXP_CS = eINSTANCE.getCollectionLiteralPartsOclExpCS();

		/**
     * The meta object literal for the '<em><b>Ocl Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COLLECTION_LITERAL_PARTS_OCL_EXP_CS__OCL_EXPRESSION = eINSTANCE.getCollectionLiteralPartsOclExpCS_OclExpression();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.CollectionRangeCSImpl <em>Collection Range CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.CollectionRangeCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getCollectionRangeCS()
     * @generated
     */
		EClass COLLECTION_RANGE_CS = eINSTANCE.getCollectionRangeCS();

		/**
     * The meta object literal for the '<em><b>From</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COLLECTION_RANGE_CS__FROM = eINSTANCE.getCollectionRangeCS_From();

		/**
     * The meta object literal for the '<em><b>To</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COLLECTION_RANGE_CS__TO = eINSTANCE.getCollectionRangeCS_To();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.CallExpCSImpl <em>Call Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.CallExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getCallExpCS()
     * @generated
     */
		EClass CALL_EXP_CS = eINSTANCE.getCallExpCS();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.LoopExpCSImpl <em>Loop Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.LoopExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getLoopExpCS()
     * @generated
     */
		EClass LOOP_EXP_CS = eINSTANCE.getLoopExpCS();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.IteratorExpVariableCSImpl <em>Iterator Exp Variable CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.IteratorExpVariableCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getIteratorExpVariableCS()
     * @generated
     */
		EClass ITERATOR_EXP_VARIABLE_CS = eINSTANCE.getIteratorExpVariableCS();

		/**
     * The meta object literal for the '<em><b>Variable Name</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ITERATOR_EXP_VARIABLE_CS__VARIABLE_NAME = eINSTANCE.getIteratorExpVariableCS_VariableName();

		/**
     * The meta object literal for the '<em><b>Type Name</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ITERATOR_EXP_VARIABLE_CS__TYPE_NAME = eINSTANCE.getIteratorExpVariableCS_TypeName();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.IteratorExpCSImpl <em>Iterator Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.IteratorExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getIteratorExpCS()
     * @generated
     */
		EClass ITERATOR_EXP_CS = eINSTANCE.getIteratorExpCS();

		/**
     * The meta object literal for the '<em><b>Iterator Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute ITERATOR_EXP_CS__ITERATOR_NAME = eINSTANCE.getIteratorExpCS_IteratorName();

		/**
     * The meta object literal for the '<em><b>Iterator Variables</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ITERATOR_EXP_CS__ITERATOR_VARIABLES = eINSTANCE.getIteratorExpCS_IteratorVariables();

		/**
     * The meta object literal for the '<em><b>Body Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ITERATOR_EXP_CS__BODY_EXPRESSION = eINSTANCE.getIteratorExpCS_BodyExpression();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.IterateExpCSImpl <em>Iterate Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.IterateExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getIterateExpCS()
     * @generated
     */
		EClass ITERATE_EXP_CS = eINSTANCE.getIterateExpCS();

		/**
     * The meta object literal for the '<em><b>Iterator Variable</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ITERATE_EXP_CS__ITERATOR_VARIABLE = eINSTANCE.getIterateExpCS_IteratorVariable();

		/**
     * The meta object literal for the '<em><b>Result Variable</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ITERATE_EXP_CS__RESULT_VARIABLE = eINSTANCE.getIterateExpCS_ResultVariable();

		/**
     * The meta object literal for the '<em><b>Body Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ITERATE_EXP_CS__BODY_EXPRESSION = eINSTANCE.getIterateExpCS_BodyExpression();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.FeatureCallExpCSImpl <em>Feature Call Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.FeatureCallExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getFeatureCallExpCS()
     * @generated
     */
		EClass FEATURE_CALL_EXP_CS = eINSTANCE.getFeatureCallExpCS();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.NavigationCallExpImpl <em>Navigation Call Exp</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.NavigationCallExpImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getNavigationCallExp()
     * @generated
     */
		EClass NAVIGATION_CALL_EXP = eINSTANCE.getNavigationCallExp();

		/**
     * The meta object literal for the '<em><b>Source</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference NAVIGATION_CALL_EXP__SOURCE = eINSTANCE.getNavigationCallExp_Source();

		/**
     * The meta object literal for the '<em><b>Navigation Operator</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute NAVIGATION_CALL_EXP__NAVIGATION_OPERATOR = eINSTANCE.getNavigationCallExp_NavigationOperator();

		/**
     * The meta object literal for the '<em><b>Feature Calls</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference NAVIGATION_CALL_EXP__FEATURE_CALLS = eINSTANCE.getNavigationCallExp_FeatureCalls();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.OperationCallBaseExpCSImpl <em>Operation Call Base Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.OperationCallBaseExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getOperationCallBaseExpCS()
     * @generated
     */
		EClass OPERATION_CALL_BASE_EXP_CS = eINSTANCE.getOperationCallBaseExpCS();

		/**
     * The meta object literal for the '<em><b>Operation Name</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATION_CALL_BASE_EXP_CS__OPERATION_NAME = eINSTANCE.getOperationCallBaseExpCS_OperationName();

		/**
     * The meta object literal for the '<em><b>Arguments</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATION_CALL_BASE_EXP_CS__ARGUMENTS = eINSTANCE.getOperationCallBaseExpCS_Arguments();

		/**
     * The meta object literal for the '<em><b>Is Marked Pre</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute OPERATION_CALL_BASE_EXP_CS__IS_MARKED_PRE = eINSTANCE.getOperationCallBaseExpCS_IsMarkedPre();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.PropertyCallBaseExpCSImpl <em>Property Call Base Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.PropertyCallBaseExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getPropertyCallBaseExpCS()
     * @generated
     */
		EClass PROPERTY_CALL_BASE_EXP_CS = eINSTANCE.getPropertyCallBaseExpCS();

		/**
     * The meta object literal for the '<em><b>Property</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PROPERTY_CALL_BASE_EXP_CS__PROPERTY = eINSTANCE.getPropertyCallBaseExpCS_Property();

		/**
     * The meta object literal for the '<em><b>Is Marked Pre</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute PROPERTY_CALL_BASE_EXP_CS__IS_MARKED_PRE = eINSTANCE.getPropertyCallBaseExpCS_IsMarkedPre();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.ImplicitFeatureCallCSImpl <em>Implicit Feature Call CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.ImplicitFeatureCallCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getImplicitFeatureCallCS()
     * @generated
     */
		EClass IMPLICIT_FEATURE_CALL_CS = eINSTANCE.getImplicitFeatureCallCS();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.PropertyCallExpCSImpl <em>Property Call Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.PropertyCallExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getPropertyCallExpCS()
     * @generated
     */
		EClass PROPERTY_CALL_EXP_CS = eINSTANCE.getPropertyCallExpCS();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.PropertyCallOnSelfExpCSImpl <em>Property Call On Self Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.PropertyCallOnSelfExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getPropertyCallOnSelfExpCS()
     * @generated
     */
		EClass PROPERTY_CALL_ON_SELF_EXP_CS = eINSTANCE.getPropertyCallOnSelfExpCS();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.ImplicitPropertyCallCSImpl <em>Implicit Property Call CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.ImplicitPropertyCallCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getImplicitPropertyCallCS()
     * @generated
     */
		EClass IMPLICIT_PROPERTY_CALL_CS = eINSTANCE.getImplicitPropertyCallCS();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.PropertyCallExplicitPathExpCSImpl <em>Property Call Explicit Path Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.PropertyCallExplicitPathExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getPropertyCallExplicitPathExpCS()
     * @generated
     */
		EClass PROPERTY_CALL_EXPLICIT_PATH_EXP_CS = eINSTANCE.getPropertyCallExplicitPathExpCS();

		/**
     * The meta object literal for the '<em><b>Source</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__SOURCE = eINSTANCE.getPropertyCallExplicitPathExpCS_Source();

		/**
     * The meta object literal for the '<em><b>Property Path</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__PROPERTY_PATH = eINSTANCE.getPropertyCallExplicitPathExpCS_PropertyPath();

		/**
     * The meta object literal for the '<em><b>Property Name</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__PROPERTY_NAME = eINSTANCE.getPropertyCallExplicitPathExpCS_PropertyName();

		/**
     * The meta object literal for the '<em><b>Is Marked Pre</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__IS_MARKED_PRE = eINSTANCE.getPropertyCallExplicitPathExpCS_IsMarkedPre();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.OperationCallExpCSImpl <em>Operation Call Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.OperationCallExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getOperationCallExpCS()
     * @generated
     */
		EClass OPERATION_CALL_EXP_CS = eINSTANCE.getOperationCallExpCS();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.OperationCallOnSelfExpCSImpl <em>Operation Call On Self Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.OperationCallOnSelfExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getOperationCallOnSelfExpCS()
     * @generated
     */
		EClass OPERATION_CALL_ON_SELF_EXP_CS = eINSTANCE.getOperationCallOnSelfExpCS();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.StaticOperationCallExpCSImpl <em>Static Operation Call Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.StaticOperationCallExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getStaticOperationCallExpCS()
     * @generated
     */
		EClass STATIC_OPERATION_CALL_EXP_CS = eINSTANCE.getStaticOperationCallExpCS();

		/**
     * The meta object literal for the '<em><b>Type Name</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference STATIC_OPERATION_CALL_EXP_CS__TYPE_NAME = eINSTANCE.getStaticOperationCallExpCS_TypeName();

		/**
     * The meta object literal for the '<em><b>Operation Name</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference STATIC_OPERATION_CALL_EXP_CS__OPERATION_NAME = eINSTANCE.getStaticOperationCallExpCS_OperationName();

		/**
     * The meta object literal for the '<em><b>Arguments</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference STATIC_OPERATION_CALL_EXP_CS__ARGUMENTS = eINSTANCE.getStaticOperationCallExpCS_Arguments();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.UnaryOperationCallExpCSImpl <em>Unary Operation Call Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.UnaryOperationCallExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getUnaryOperationCallExpCS()
     * @generated
     */
		EClass UNARY_OPERATION_CALL_EXP_CS = eINSTANCE.getUnaryOperationCallExpCS();

		/**
     * The meta object literal for the '<em><b>Operation Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute UNARY_OPERATION_CALL_EXP_CS__OPERATION_NAME = eINSTANCE.getUnaryOperationCallExpCS_OperationName();

		/**
     * The meta object literal for the '<em><b>Target</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference UNARY_OPERATION_CALL_EXP_CS__TARGET = eINSTANCE.getUnaryOperationCallExpCS_Target();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.LogicalNotOperationCallExpCSImpl <em>Logical Not Operation Call Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.LogicalNotOperationCallExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getLogicalNotOperationCallExpCS()
     * @generated
     */
		EClass LOGICAL_NOT_OPERATION_CALL_EXP_CS = eINSTANCE.getLogicalNotOperationCallExpCS();

		/**
     * The meta object literal for the '<em><b>Operation Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute LOGICAL_NOT_OPERATION_CALL_EXP_CS__OPERATION_NAME = eINSTANCE.getLogicalNotOperationCallExpCS_OperationName();

		/**
     * The meta object literal for the '<em><b>Target</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference LOGICAL_NOT_OPERATION_CALL_EXP_CS__TARGET = eINSTANCE.getLogicalNotOperationCallExpCS_Target();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.OperationCallWithSourceExpCSImpl <em>Operation Call With Source Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.OperationCallWithSourceExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getOperationCallWithSourceExpCS()
     * @generated
     */
		EClass OPERATION_CALL_WITH_SOURCE_EXP_CS = eINSTANCE.getOperationCallWithSourceExpCS();

		/**
     * The meta object literal for the '<em><b>Source</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATION_CALL_WITH_SOURCE_EXP_CS__SOURCE = eINSTANCE.getOperationCallWithSourceExpCS_Source();

		/**
     * The meta object literal for the '<em><b>Is Marked Pre</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute OPERATION_CALL_WITH_SOURCE_EXP_CS__IS_MARKED_PRE = eINSTANCE.getOperationCallWithSourceExpCS_IsMarkedPre();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.OperationCallBinaryExpCSImpl <em>Operation Call Binary Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.OperationCallBinaryExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getOperationCallBinaryExpCS()
     * @generated
     */
		EClass OPERATION_CALL_BINARY_EXP_CS = eINSTANCE.getOperationCallBinaryExpCS();

		/**
     * The meta object literal for the '<em><b>Operation Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute OPERATION_CALL_BINARY_EXP_CS__OPERATION_NAME = eINSTANCE.getOperationCallBinaryExpCS_OperationName();

		/**
     * The meta object literal for the '<em><b>Target</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATION_CALL_BINARY_EXP_CS__TARGET = eINSTANCE.getOperationCallBinaryExpCS_Target();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.AdditiveOperationCallExpCSImpl <em>Additive Operation Call Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.AdditiveOperationCallExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getAdditiveOperationCallExpCS()
     * @generated
     */
		EClass ADDITIVE_OPERATION_CALL_EXP_CS = eINSTANCE.getAdditiveOperationCallExpCS();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.MultOperationCallExpCSImpl <em>Mult Operation Call Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.MultOperationCallExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getMultOperationCallExpCS()
     * @generated
     */
		EClass MULT_OPERATION_CALL_EXP_CS = eINSTANCE.getMultOperationCallExpCS();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.RelationalOperationCallExpCSImpl <em>Relational Operation Call Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.RelationalOperationCallExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getRelationalOperationCallExpCS()
     * @generated
     */
		EClass RELATIONAL_OPERATION_CALL_EXP_CS = eINSTANCE.getRelationalOperationCallExpCS();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.EqualityOperationCallExpCSImpl <em>Equality Operation Call Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.EqualityOperationCallExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getEqualityOperationCallExpCS()
     * @generated
     */
		EClass EQUALITY_OPERATION_CALL_EXP_CS = eINSTANCE.getEqualityOperationCallExpCS();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.LogicalAndOperationCallExpCSImpl <em>Logical And Operation Call Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.LogicalAndOperationCallExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getLogicalAndOperationCallExpCS()
     * @generated
     */
		EClass LOGICAL_AND_OPERATION_CALL_EXP_CS = eINSTANCE.getLogicalAndOperationCallExpCS();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.LogicalOrOperationCallExpCSImpl <em>Logical Or Operation Call Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.LogicalOrOperationCallExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getLogicalOrOperationCallExpCS()
     * @generated
     */
		EClass LOGICAL_OR_OPERATION_CALL_EXP_CS = eINSTANCE.getLogicalOrOperationCallExpCS();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.LogicalXorOperationCallExpCSImpl <em>Logical Xor Operation Call Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.LogicalXorOperationCallExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getLogicalXorOperationCallExpCS()
     * @generated
     */
		EClass LOGICAL_XOR_OPERATION_CALL_EXP_CS = eINSTANCE.getLogicalXorOperationCallExpCS();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.LogicalImpliesOperationCallExpCSImpl <em>Logical Implies Operation Call Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.LogicalImpliesOperationCallExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getLogicalImpliesOperationCallExpCS()
     * @generated
     */
		EClass LOGICAL_IMPLIES_OPERATION_CALL_EXP_CS = eINSTANCE.getLogicalImpliesOperationCallExpCS();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.OperationCallWithImlicitSourceExpCSImpl <em>Operation Call With Imlicit Source Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.OperationCallWithImlicitSourceExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getOperationCallWithImlicitSourceExpCS()
     * @generated
     */
		EClass OPERATION_CALL_WITH_IMLICIT_SOURCE_EXP_CS = eINSTANCE.getOperationCallWithImlicitSourceExpCS();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.ImplicitOperationCallCSImpl <em>Implicit Operation Call CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.ImplicitOperationCallCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getImplicitOperationCallCS()
     * @generated
     */
		EClass IMPLICIT_OPERATION_CALL_CS = eINSTANCE.getImplicitOperationCallCS();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.TupleLiteralExpCSImpl <em>Tuple Literal Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.TupleLiteralExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getTupleLiteralExpCS()
     * @generated
     */
		EClass TUPLE_LITERAL_EXP_CS = eINSTANCE.getTupleLiteralExpCS();

		/**
     * The meta object literal for the '<em><b>Variable Declarations</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference TUPLE_LITERAL_EXP_CS__VARIABLE_DECLARATIONS = eINSTANCE.getTupleLiteralExpCS_VariableDeclarations();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.PrimitiveLiteralExpCSImpl <em>Primitive Literal Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.PrimitiveLiteralExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getPrimitiveLiteralExpCS()
     * @generated
     */
		EClass PRIMITIVE_LITERAL_EXP_CS = eINSTANCE.getPrimitiveLiteralExpCS();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.IntegerLiteralExpCSImpl <em>Integer Literal Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.IntegerLiteralExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getIntegerLiteralExpCS()
     * @generated
     */
		EClass INTEGER_LITERAL_EXP_CS = eINSTANCE.getIntegerLiteralExpCS();

		/**
     * The meta object literal for the '<em><b>Integer Literal</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute INTEGER_LITERAL_EXP_CS__INTEGER_LITERAL = eINSTANCE.getIntegerLiteralExpCS_IntegerLiteral();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.RealLiteralExpCSImpl <em>Real Literal Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.RealLiteralExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getRealLiteralExpCS()
     * @generated
     */
		EClass REAL_LITERAL_EXP_CS = eINSTANCE.getRealLiteralExpCS();

		/**
     * The meta object literal for the '<em><b>Int Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute REAL_LITERAL_EXP_CS__INT_VALUE = eINSTANCE.getRealLiteralExpCS_IntValue();

		/**
     * The meta object literal for the '<em><b>Real Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute REAL_LITERAL_EXP_CS__REAL_VALUE = eINSTANCE.getRealLiteralExpCS_RealValue();

		/**
     * The meta object literal for the '<em><b>Navigation Operator</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute REAL_LITERAL_EXP_CS__NAVIGATION_OPERATOR = eINSTANCE.getRealLiteralExpCS_NavigationOperator();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.BooleanLiteralExpCSImpl <em>Boolean Literal Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.BooleanLiteralExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getBooleanLiteralExpCS()
     * @generated
     */
		EClass BOOLEAN_LITERAL_EXP_CS = eINSTANCE.getBooleanLiteralExpCS();

		/**
     * The meta object literal for the '<em><b>Boolean Literal</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute BOOLEAN_LITERAL_EXP_CS__BOOLEAN_LITERAL = eINSTANCE.getBooleanLiteralExpCS_BooleanLiteral();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.StringLiteralExpCSImpl <em>String Literal Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.StringLiteralExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getStringLiteralExpCS()
     * @generated
     */
		EClass STRING_LITERAL_EXP_CS = eINSTANCE.getStringLiteralExpCS();

		/**
     * The meta object literal for the '<em><b>String Literal</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute STRING_LITERAL_EXP_CS__STRING_LITERAL = eINSTANCE.getStringLiteralExpCS_StringLiteral();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.InvalidLiteralExpCSImpl <em>Invalid Literal Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.InvalidLiteralExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getInvalidLiteralExpCS()
     * @generated
     */
		EClass INVALID_LITERAL_EXP_CS = eINSTANCE.getInvalidLiteralExpCS();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.NullLiteralExpCSImpl <em>Null Literal Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.NullLiteralExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getNullLiteralExpCS()
     * @generated
     */
		EClass NULL_LITERAL_EXP_CS = eINSTANCE.getNullLiteralExpCS();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.LetExpCSImpl <em>Let Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.LetExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getLetExpCS()
     * @generated
     */
		EClass LET_EXP_CS = eINSTANCE.getLetExpCS();

		/**
     * The meta object literal for the '<em><b>Variable Declarations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference LET_EXP_CS__VARIABLE_DECLARATIONS = eINSTANCE.getLetExpCS_VariableDeclarations();

		/**
     * The meta object literal for the '<em><b>Ocl Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference LET_EXP_CS__OCL_EXPRESSION = eINSTANCE.getLetExpCS_OclExpression();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.IfExpCSImpl <em>If Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.IfExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getIfExpCS()
     * @generated
     */
		EClass IF_EXP_CS = eINSTANCE.getIfExpCS();

		/**
     * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference IF_EXP_CS__CONDITION = eINSTANCE.getIfExpCS_Condition();

		/**
     * The meta object literal for the '<em><b>Then Branch</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference IF_EXP_CS__THEN_BRANCH = eINSTANCE.getIfExpCS_ThenBranch();

		/**
     * The meta object literal for the '<em><b>Else Branch</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference IF_EXP_CS__ELSE_BRANCH = eINSTANCE.getIfExpCS_ElseBranch();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.PackageDeclarationCSImpl <em>Package Declaration CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.PackageDeclarationCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getPackageDeclarationCS()
     * @generated
     */
		EClass PACKAGE_DECLARATION_CS = eINSTANCE.getPackageDeclarationCS();

		/**
     * The meta object literal for the '<em><b>Context Declarations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PACKAGE_DECLARATION_CS__CONTEXT_DECLARATIONS = eINSTANCE.getPackageDeclarationCS_ContextDeclarations();

		/**
     * The meta object literal for the '<em><b>Layout Information</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PACKAGE_DECLARATION_CS__LAYOUT_INFORMATION = eINSTANCE.getPackageDeclarationCS_LayoutInformation();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.PackageDeclarationWithNamespaceCSImpl <em>Package Declaration With Namespace CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.PackageDeclarationWithNamespaceCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getPackageDeclarationWithNamespaceCS()
     * @generated
     */
		EClass PACKAGE_DECLARATION_WITH_NAMESPACE_CS = eINSTANCE.getPackageDeclarationWithNamespaceCS();

		/**
     * The meta object literal for the '<em><b>Nested Namespace</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PACKAGE_DECLARATION_WITH_NAMESPACE_CS__NESTED_NAMESPACE = eINSTANCE.getPackageDeclarationWithNamespaceCS_NestedNamespace();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.PackageDeclarationNestedNamespaceCSImpl <em>Package Declaration Nested Namespace CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.PackageDeclarationNestedNamespaceCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getPackageDeclarationNestedNamespaceCS()
     * @generated
     */
		EClass PACKAGE_DECLARATION_NESTED_NAMESPACE_CS = eINSTANCE.getPackageDeclarationNestedNamespaceCS();

		/**
     * The meta object literal for the '<em><b>Namespace</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PACKAGE_DECLARATION_NESTED_NAMESPACE_CS__NAMESPACE = eINSTANCE.getPackageDeclarationNestedNamespaceCS_Namespace();

		/**
     * The meta object literal for the '<em><b>Nested Namespace</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PACKAGE_DECLARATION_NESTED_NAMESPACE_CS__NESTED_NAMESPACE = eINSTANCE.getPackageDeclarationNestedNamespaceCS_NestedNamespace();

		/**
     * The meta object literal for the '<em><b>Layout Information</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PACKAGE_DECLARATION_NESTED_NAMESPACE_CS__LAYOUT_INFORMATION = eINSTANCE.getPackageDeclarationNestedNamespaceCS_LayoutInformation();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.PackageDeclarationWithoutNamespaceCSImpl <em>Package Declaration Without Namespace CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.PackageDeclarationWithoutNamespaceCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getPackageDeclarationWithoutNamespaceCS()
     * @generated
     */
		EClass PACKAGE_DECLARATION_WITHOUT_NAMESPACE_CS = eINSTANCE.getPackageDeclarationWithoutNamespaceCS();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.ContextDeclarationCSImpl <em>Context Declaration CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.ContextDeclarationCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getContextDeclarationCS()
     * @generated
     */
		EClass CONTEXT_DECLARATION_CS = eINSTANCE.getContextDeclarationCS();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.AttributeContextDeclarationCSImpl <em>Attribute Context Declaration CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.AttributeContextDeclarationCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getAttributeContextDeclarationCS()
     * @generated
     */
		EClass ATTRIBUTE_CONTEXT_DECLARATION_CS = eINSTANCE.getAttributeContextDeclarationCS();

		/**
     * The meta object literal for the '<em><b>Type Name</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ATTRIBUTE_CONTEXT_DECLARATION_CS__TYPE_NAME = eINSTANCE.getAttributeContextDeclarationCS_TypeName();

		/**
     * The meta object literal for the '<em><b>Property</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ATTRIBUTE_CONTEXT_DECLARATION_CS__PROPERTY = eINSTANCE.getAttributeContextDeclarationCS_Property();

		/**
     * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ATTRIBUTE_CONTEXT_DECLARATION_CS__TYPE = eINSTANCE.getAttributeContextDeclarationCS_Type();

		/**
     * The meta object literal for the '<em><b>Init Or Derive Value</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ATTRIBUTE_CONTEXT_DECLARATION_CS__INIT_OR_DERIVE_VALUE = eINSTANCE.getAttributeContextDeclarationCS_InitOrDeriveValue();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.ClassifierContextDeclarationCSImpl <em>Classifier Context Declaration CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.ClassifierContextDeclarationCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getClassifierContextDeclarationCS()
     * @generated
     */
		EClass CLASSIFIER_CONTEXT_DECLARATION_CS = eINSTANCE.getClassifierContextDeclarationCS();

		/**
     * The meta object literal for the '<em><b>Type Name</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CLASSIFIER_CONTEXT_DECLARATION_CS__TYPE_NAME = eINSTANCE.getClassifierContextDeclarationCS_TypeName();

		/**
     * The meta object literal for the '<em><b>Invariants And Definitions</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CLASSIFIER_CONTEXT_DECLARATION_CS__INVARIANTS_AND_DEFINITIONS = eINSTANCE.getClassifierContextDeclarationCS_InvariantsAndDefinitions();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.OperationContextDeclarationCSImpl <em>Operation Context Declaration CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.OperationContextDeclarationCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getOperationContextDeclarationCS()
     * @generated
     */
		EClass OPERATION_CONTEXT_DECLARATION_CS = eINSTANCE.getOperationContextDeclarationCS();

		/**
     * The meta object literal for the '<em><b>Operation</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATION_CONTEXT_DECLARATION_CS__OPERATION = eINSTANCE.getOperationContextDeclarationCS_Operation();

		/**
     * The meta object literal for the '<em><b>Pre Post Or Body Declarations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATION_CONTEXT_DECLARATION_CS__PRE_POST_OR_BODY_DECLARATIONS = eINSTANCE.getOperationContextDeclarationCS_PrePostOrBodyDeclarations();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.InitOrDeriveValueCSImpl <em>Init Or Derive Value CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.InitOrDeriveValueCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getInitOrDeriveValueCS()
     * @generated
     */
		EClass INIT_OR_DERIVE_VALUE_CS = eINSTANCE.getInitOrDeriveValueCS();

		/**
     * The meta object literal for the '<em><b>Ocl Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INIT_OR_DERIVE_VALUE_CS__OCL_EXPRESSION = eINSTANCE.getInitOrDeriveValueCS_OclExpression();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.InitValueCSImpl <em>Init Value CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.InitValueCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getInitValueCS()
     * @generated
     */
		EClass INIT_VALUE_CS = eINSTANCE.getInitValueCS();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.DeriveValueCSImpl <em>Derive Value CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.DeriveValueCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getDeriveValueCS()
     * @generated
     */
		EClass DERIVE_VALUE_CS = eINSTANCE.getDeriveValueCS();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.InvariantOrDefinitionCSImpl <em>Invariant Or Definition CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.InvariantOrDefinitionCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getInvariantOrDefinitionCS()
     * @generated
     */
		EClass INVARIANT_OR_DEFINITION_CS = eINSTANCE.getInvariantOrDefinitionCS();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.InvariantExpCSImpl <em>Invariant Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.InvariantExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getInvariantExpCS()
     * @generated
     */
		EClass INVARIANT_EXP_CS = eINSTANCE.getInvariantExpCS();

		/**
     * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INVARIANT_EXP_CS__NAME = eINSTANCE.getInvariantExpCS_Name();

		/**
     * The meta object literal for the '<em><b>Ocl Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INVARIANT_EXP_CS__OCL_EXPRESSION = eINSTANCE.getInvariantExpCS_OclExpression();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.DefinitionExpCSImpl <em>Definition Exp CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.DefinitionExpCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getDefinitionExpCS()
     * @generated
     */
		EClass DEFINITION_EXP_CS = eINSTANCE.getDefinitionExpCS();

		/**
     * The meta object literal for the '<em><b>Static</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute DEFINITION_EXP_CS__STATIC = eINSTANCE.getDefinitionExpCS_Static();

		/**
     * The meta object literal for the '<em><b>Definition Exp Part</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference DEFINITION_EXP_CS__DEFINITION_EXP_PART = eINSTANCE.getDefinitionExpCS_DefinitionExpPart();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.DefinitionExpPartCSImpl <em>Definition Exp Part CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.DefinitionExpPartCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getDefinitionExpPartCS()
     * @generated
     */
		EClass DEFINITION_EXP_PART_CS = eINSTANCE.getDefinitionExpPartCS();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.DefinitionExpPropertyCSImpl <em>Definition Exp Property CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.DefinitionExpPropertyCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getDefinitionExpPropertyCS()
     * @generated
     */
		EClass DEFINITION_EXP_PROPERTY_CS = eINSTANCE.getDefinitionExpPropertyCS();

		/**
     * The meta object literal for the '<em><b>Variable Declaration</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference DEFINITION_EXP_PROPERTY_CS__VARIABLE_DECLARATION = eINSTANCE.getDefinitionExpPropertyCS_VariableDeclaration();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.DefinitionExpOperationCSImpl <em>Definition Exp Operation CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.DefinitionExpOperationCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getDefinitionExpOperationCS()
     * @generated
     */
		EClass DEFINITION_EXP_OPERATION_CS = eINSTANCE.getDefinitionExpOperationCS();

		/**
     * The meta object literal for the '<em><b>Operation</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference DEFINITION_EXP_OPERATION_CS__OPERATION = eINSTANCE.getDefinitionExpOperationCS_Operation();

		/**
     * The meta object literal for the '<em><b>Equal</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute DEFINITION_EXP_OPERATION_CS__EQUAL = eINSTANCE.getDefinitionExpOperationCS_Equal();

		/**
     * The meta object literal for the '<em><b>Ocl Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference DEFINITION_EXP_OPERATION_CS__OCL_EXPRESSION = eINSTANCE.getDefinitionExpOperationCS_OclExpression();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.PrePostOrBodyDeclarationCSImpl <em>Pre Post Or Body Declaration CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.PrePostOrBodyDeclarationCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getPrePostOrBodyDeclarationCS()
     * @generated
     */
		EClass PRE_POST_OR_BODY_DECLARATION_CS = eINSTANCE.getPrePostOrBodyDeclarationCS();

		/**
     * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PRE_POST_OR_BODY_DECLARATION_CS__NAME = eINSTANCE.getPrePostOrBodyDeclarationCS_Name();

		/**
     * The meta object literal for the '<em><b>Ocl Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PRE_POST_OR_BODY_DECLARATION_CS__OCL_EXPRESSION = eINSTANCE.getPrePostOrBodyDeclarationCS_OclExpression();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.PreConditionDeclarationCSImpl <em>Pre Condition Declaration CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.PreConditionDeclarationCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getPreConditionDeclarationCS()
     * @generated
     */
		EClass PRE_CONDITION_DECLARATION_CS = eINSTANCE.getPreConditionDeclarationCS();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.PostConditionDeclarationCSImpl <em>Post Condition Declaration CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.PostConditionDeclarationCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getPostConditionDeclarationCS()
     * @generated
     */
		EClass POST_CONDITION_DECLARATION_CS = eINSTANCE.getPostConditionDeclarationCS();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.BodyDeclarationCSImpl <em>Body Declaration CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.BodyDeclarationCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getBodyDeclarationCS()
     * @generated
     */
		EClass BODY_DECLARATION_CS = eINSTANCE.getBodyDeclarationCS();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.OperationDefinitionCSImpl <em>Operation Definition CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.OperationDefinitionCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getOperationDefinitionCS()
     * @generated
     */
		EClass OPERATION_DEFINITION_CS = eINSTANCE.getOperationDefinitionCS();

		/**
     * The meta object literal for the '<em><b>Operation</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATION_DEFINITION_CS__OPERATION = eINSTANCE.getOperationDefinitionCS_Operation();

		/**
     * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATION_DEFINITION_CS__PARAMETERS = eINSTANCE.getOperationDefinitionCS_Parameters();

		/**
     * The meta object literal for the '<em><b>Return Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATION_DEFINITION_CS__RETURN_TYPE = eINSTANCE.getOperationDefinitionCS_ReturnType();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.OperationDefinitionInContextCSImpl <em>Operation Definition In Context CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.OperationDefinitionInContextCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getOperationDefinitionInContextCS()
     * @generated
     */
		EClass OPERATION_DEFINITION_IN_CONTEXT_CS = eINSTANCE.getOperationDefinitionInContextCS();

		/**
     * The meta object literal for the '<em><b>Type Name</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATION_DEFINITION_IN_CONTEXT_CS__TYPE_NAME = eINSTANCE.getOperationDefinitionInContextCS_TypeName();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.OperationDefinitionInDefCSImpl <em>Operation Definition In Def CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.OperationDefinitionInDefCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getOperationDefinitionInDefCS()
     * @generated
     */
		EClass OPERATION_DEFINITION_IN_DEF_CS = eINSTANCE.getOperationDefinitionInDefCS();

		/**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.language.ocl.impl.ParameterCSImpl <em>Parameter CS</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.language.ocl.impl.ParameterCSImpl
     * @see tudresden.ocl20.pivot.language.ocl.impl.OclPackageImpl#getParameterCS()
     * @generated
     */
		EClass PARAMETER_CS = eINSTANCE.getParameterCS();

		/**
     * The meta object literal for the '<em><b>Parameter</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PARAMETER_CS__PARAMETER = eINSTANCE.getParameterCS_Parameter();

		/**
     * The meta object literal for the '<em><b>Parameter Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PARAMETER_CS__PARAMETER_TYPE = eINSTANCE.getParameterCS_ParameterType();

	}

} //OclPackage
