/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.language.ocl.impl;

import org.dresdenocl.language.ocl.*;
import org.dresdenocl.language.ocl.AdditiveOperationCallExpCS;
import org.dresdenocl.language.ocl.AttributeContextDeclarationCS;
import org.dresdenocl.language.ocl.BodyDeclarationCS;
import org.dresdenocl.language.ocl.BooleanLiteralExpCS;
import org.dresdenocl.language.ocl.BracketExpCS;
import org.dresdenocl.language.ocl.ClassifierContextDeclarationCS;
import org.dresdenocl.language.ocl.CollectionLiteralExpCS;
import org.dresdenocl.language.ocl.CollectionLiteralPartsOclExpCS;
import org.dresdenocl.language.ocl.CollectionRangeCS;
import org.dresdenocl.language.ocl.CollectionTypeIdentifierCS;
import org.dresdenocl.language.ocl.CollectionTypeLiteralExpCS;
import org.dresdenocl.language.ocl.DefinitionExpCS;
import org.dresdenocl.language.ocl.DefinitionExpOperationCS;
import org.dresdenocl.language.ocl.DefinitionExpPropertyCS;
import org.dresdenocl.language.ocl.DeriveValueCS;
import org.dresdenocl.language.ocl.EqualityOperationCallExpCS;
import org.dresdenocl.language.ocl.IfExpCS;
import org.dresdenocl.language.ocl.ImplicitOperationCallCS;
import org.dresdenocl.language.ocl.ImplicitPropertyCallCS;
import org.dresdenocl.language.ocl.InitValueCS;
import org.dresdenocl.language.ocl.IntegerLiteralExpCS;
import org.dresdenocl.language.ocl.InvalidLiteralExpCS;
import org.dresdenocl.language.ocl.InvariantExpCS;
import org.dresdenocl.language.ocl.IterateExpCS;
import org.dresdenocl.language.ocl.IteratorExpCS;
import org.dresdenocl.language.ocl.IteratorExpVariableCS;
import org.dresdenocl.language.ocl.LetExpCS;
import org.dresdenocl.language.ocl.LogicalAndOperationCallExpCS;
import org.dresdenocl.language.ocl.LogicalImpliesOperationCallExpCS;
import org.dresdenocl.language.ocl.LogicalNotOperationCallExpCS;
import org.dresdenocl.language.ocl.LogicalOrOperationCallExpCS;
import org.dresdenocl.language.ocl.LogicalXorOperationCallExpCS;
import org.dresdenocl.language.ocl.ModelElementCS;
import org.dresdenocl.language.ocl.MultOperationCallExpCS;
import org.dresdenocl.language.ocl.NamedElementCS;
import org.dresdenocl.language.ocl.NavigationCallExp;
import org.dresdenocl.language.ocl.NullLiteralExpCS;
import org.dresdenocl.language.ocl.OclFactory;
import org.dresdenocl.language.ocl.OclPackage;
import org.dresdenocl.language.ocl.OperationCallBaseExpCS;
import org.dresdenocl.language.ocl.OperationCallOnSelfExpCS;
import org.dresdenocl.language.ocl.OperationCallWithImlicitSourceExpCS;
import org.dresdenocl.language.ocl.OperationCallWithSourceExpCS;
import org.dresdenocl.language.ocl.OperationContextDeclarationCS;
import org.dresdenocl.language.ocl.OperationDefinitionInContextCS;
import org.dresdenocl.language.ocl.OperationDefinitionInDefCS;
import org.dresdenocl.language.ocl.PackageDeclarationNestedNamespaceCS;
import org.dresdenocl.language.ocl.PackageDeclarationWithNamespaceCS;
import org.dresdenocl.language.ocl.PackageDeclarationWithoutNamespaceCS;
import org.dresdenocl.language.ocl.ParameterCS;
import org.dresdenocl.language.ocl.PathNamePathCS;
import org.dresdenocl.language.ocl.PathNameSimpleCS;
import org.dresdenocl.language.ocl.PostConditionDeclarationCS;
import org.dresdenocl.language.ocl.PreConditionDeclarationCS;
import org.dresdenocl.language.ocl.PropertyCallBaseExpCS;
import org.dresdenocl.language.ocl.PropertyCallExplicitPathExpCS;
import org.dresdenocl.language.ocl.PropertyCallOnSelfExpCS;
import org.dresdenocl.language.ocl.RealLiteralExpCS;
import org.dresdenocl.language.ocl.RelationalOperationCallExpCS;
import org.dresdenocl.language.ocl.SimpleNameCS;
import org.dresdenocl.language.ocl.StaticOperationCallExpCS;
import org.dresdenocl.language.ocl.StringLiteralExpCS;
import org.dresdenocl.language.ocl.TupleLiteralExpCS;
import org.dresdenocl.language.ocl.TupleTypeCS;
import org.dresdenocl.language.ocl.TupleTypeLiteralExpCS;
import org.dresdenocl.language.ocl.TypeModelElementCS;
import org.dresdenocl.language.ocl.UnaryOperationCallExpCS;
import org.dresdenocl.language.ocl.VariableDeclarationWithInitCS;
import org.dresdenocl.language.ocl.VariableDeclarationWithInitListCS;
import org.dresdenocl.language.ocl.VariableDeclarationWithoutInitCS;
import org.dresdenocl.language.ocl.VariableDeclarationWithoutInitListCS;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class OclFactoryImpl extends EFactoryImpl implements OclFactory {
	/**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public static OclFactory init() {
    try
    {
      OclFactory theOclFactory = (OclFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.tu-dresden.de/ocl20/pivot/language/ocl"); 
      if (theOclFactory != null)
      {
        return theOclFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new OclFactoryImpl();
  }

	/**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public OclFactoryImpl() {
    super();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EObject create(EClass eClass) {
    switch (eClass.getClassifierID())
    {
      case OclPackage.BRACKET_EXP_CS: return createBracketExpCS();
      case OclPackage.MODEL_ELEMENT_CS: return createModelElementCS();
      case OclPackage.PATH_NAME_SIMPLE_CS: return createPathNameSimpleCS();
      case OclPackage.PATH_NAME_PATH_CS: return createPathNamePathCS();
      case OclPackage.NAMED_ELEMENT_CS: return createNamedElementCS();
      case OclPackage.SIMPLE_NAME_CS: return createSimpleNameCS();
      case OclPackage.TYPE_MODEL_ELEMENT_CS: return createTypeModelElementCS();
      case OclPackage.TUPLE_TYPE_CS: return createTupleTypeCS();
      case OclPackage.COLLECTION_TYPE_LITERAL_EXP_CS: return createCollectionTypeLiteralExpCS();
      case OclPackage.TUPLE_TYPE_LITERAL_EXP_CS: return createTupleTypeLiteralExpCS();
      case OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS: return createVariableDeclarationWithInitCS();
      case OclPackage.VARIABLE_DECLARATION_WITHOUT_INIT_CS: return createVariableDeclarationWithoutInitCS();
      case OclPackage.VARIABLE_DECLARATION_WITH_INIT_LIST_CS: return createVariableDeclarationWithInitListCS();
      case OclPackage.VARIABLE_DECLARATION_WITHOUT_INIT_LIST_CS: return createVariableDeclarationWithoutInitListCS();
      case OclPackage.COLLECTION_LITERAL_EXP_CS: return createCollectionLiteralExpCS();
      case OclPackage.COLLECTION_TYPE_IDENTIFIER_CS: return createCollectionTypeIdentifierCS();
      case OclPackage.COLLECTION_LITERAL_PARTS_OCL_EXP_CS: return createCollectionLiteralPartsOclExpCS();
      case OclPackage.COLLECTION_RANGE_CS: return createCollectionRangeCS();
      case OclPackage.ITERATOR_EXP_VARIABLE_CS: return createIteratorExpVariableCS();
      case OclPackage.ITERATOR_EXP_CS: return createIteratorExpCS();
      case OclPackage.ITERATE_EXP_CS: return createIterateExpCS();
      case OclPackage.NAVIGATION_CALL_EXP: return createNavigationCallExp();
      case OclPackage.OPERATION_CALL_BASE_EXP_CS: return createOperationCallBaseExpCS();
      case OclPackage.PROPERTY_CALL_BASE_EXP_CS: return createPropertyCallBaseExpCS();
      case OclPackage.IMPLICIT_PROPERTY_CALL_CS: return createImplicitPropertyCallCS();
      case OclPackage.IMPLICIT_OPERATION_CALL_CS: return createImplicitOperationCallCS();
      case OclPackage.PROPERTY_CALL_ON_SELF_EXP_CS: return createPropertyCallOnSelfExpCS();
      case OclPackage.PROPERTY_CALL_EXPLICIT_PATH_EXP_CS: return createPropertyCallExplicitPathExpCS();
      case OclPackage.OPERATION_CALL_ON_SELF_EXP_CS: return createOperationCallOnSelfExpCS();
      case OclPackage.STATIC_OPERATION_CALL_EXP_CS: return createStaticOperationCallExpCS();
      case OclPackage.UNARY_OPERATION_CALL_EXP_CS: return createUnaryOperationCallExpCS();
      case OclPackage.LOGICAL_NOT_OPERATION_CALL_EXP_CS: return createLogicalNotOperationCallExpCS();
      case OclPackage.OPERATION_CALL_WITH_SOURCE_EXP_CS: return createOperationCallWithSourceExpCS();
      case OclPackage.ADDITIVE_OPERATION_CALL_EXP_CS: return createAdditiveOperationCallExpCS();
      case OclPackage.MULT_OPERATION_CALL_EXP_CS: return createMultOperationCallExpCS();
      case OclPackage.RELATIONAL_OPERATION_CALL_EXP_CS: return createRelationalOperationCallExpCS();
      case OclPackage.EQUALITY_OPERATION_CALL_EXP_CS: return createEqualityOperationCallExpCS();
      case OclPackage.LOGICAL_AND_OPERATION_CALL_EXP_CS: return createLogicalAndOperationCallExpCS();
      case OclPackage.LOGICAL_OR_OPERATION_CALL_EXP_CS: return createLogicalOrOperationCallExpCS();
      case OclPackage.LOGICAL_XOR_OPERATION_CALL_EXP_CS: return createLogicalXorOperationCallExpCS();
      case OclPackage.LOGICAL_IMPLIES_OPERATION_CALL_EXP_CS: return createLogicalImpliesOperationCallExpCS();
      case OclPackage.OPERATION_CALL_WITH_IMLICIT_SOURCE_EXP_CS: return createOperationCallWithImlicitSourceExpCS();
      case OclPackage.TUPLE_LITERAL_EXP_CS: return createTupleLiteralExpCS();
      case OclPackage.INTEGER_LITERAL_EXP_CS: return createIntegerLiteralExpCS();
      case OclPackage.REAL_LITERAL_EXP_CS: return createRealLiteralExpCS();
      case OclPackage.BOOLEAN_LITERAL_EXP_CS: return createBooleanLiteralExpCS();
      case OclPackage.STRING_LITERAL_EXP_CS: return createStringLiteralExpCS();
      case OclPackage.INVALID_LITERAL_EXP_CS: return createInvalidLiteralExpCS();
      case OclPackage.NULL_LITERAL_EXP_CS: return createNullLiteralExpCS();
      case OclPackage.LET_EXP_CS: return createLetExpCS();
      case OclPackage.IF_EXP_CS: return createIfExpCS();
      case OclPackage.PACKAGE_DECLARATION_WITH_NAMESPACE_CS: return createPackageDeclarationWithNamespaceCS();
      case OclPackage.PACKAGE_DECLARATION_NESTED_NAMESPACE_CS: return createPackageDeclarationNestedNamespaceCS();
      case OclPackage.PACKAGE_DECLARATION_WITHOUT_NAMESPACE_CS: return createPackageDeclarationWithoutNamespaceCS();
      case OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS: return createAttributeContextDeclarationCS();
      case OclPackage.CLASSIFIER_CONTEXT_DECLARATION_CS: return createClassifierContextDeclarationCS();
      case OclPackage.OPERATION_CONTEXT_DECLARATION_CS: return createOperationContextDeclarationCS();
      case OclPackage.INIT_VALUE_CS: return createInitValueCS();
      case OclPackage.DERIVE_VALUE_CS: return createDeriveValueCS();
      case OclPackage.INVARIANT_EXP_CS: return createInvariantExpCS();
      case OclPackage.DEFINITION_EXP_CS: return createDefinitionExpCS();
      case OclPackage.DEFINITION_EXP_PROPERTY_CS: return createDefinitionExpPropertyCS();
      case OclPackage.DEFINITION_EXP_OPERATION_CS: return createDefinitionExpOperationCS();
      case OclPackage.PRE_CONDITION_DECLARATION_CS: return createPreConditionDeclarationCS();
      case OclPackage.POST_CONDITION_DECLARATION_CS: return createPostConditionDeclarationCS();
      case OclPackage.BODY_DECLARATION_CS: return createBodyDeclarationCS();
      case OclPackage.OPERATION_DEFINITION_IN_CONTEXT_CS: return createOperationDefinitionInContextCS();
      case OclPackage.OPERATION_DEFINITION_IN_DEF_CS: return createOperationDefinitionInDefCS();
      case OclPackage.PARAMETER_CS: return createParameterCS();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public BracketExpCS createBracketExpCS() {
    BracketExpCSImpl bracketExpCS = new BracketExpCSImpl();
    return bracketExpCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public ModelElementCS createModelElementCS() {
    ModelElementCSImpl modelElementCS = new ModelElementCSImpl();
    return modelElementCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public PathNameSimpleCS createPathNameSimpleCS() {
    PathNameSimpleCSImpl pathNameSimpleCS = new PathNameSimpleCSImpl();
    return pathNameSimpleCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public PathNamePathCS createPathNamePathCS() {
    PathNamePathCSImpl pathNamePathCS = new PathNamePathCSImpl();
    return pathNamePathCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public NamedElementCS createNamedElementCS() {
    NamedElementCSImpl namedElementCS = new NamedElementCSImpl();
    return namedElementCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public SimpleNameCS createSimpleNameCS() {
    SimpleNameCSImpl simpleNameCS = new SimpleNameCSImpl();
    return simpleNameCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public TypeModelElementCS createTypeModelElementCS() {
    TypeModelElementCSImpl typeModelElementCS = new TypeModelElementCSImpl();
    return typeModelElementCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public TupleTypeCS createTupleTypeCS() {
    TupleTypeCSImpl tupleTypeCS = new TupleTypeCSImpl();
    return tupleTypeCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public CollectionTypeLiteralExpCS createCollectionTypeLiteralExpCS() {
    CollectionTypeLiteralExpCSImpl collectionTypeLiteralExpCS = new CollectionTypeLiteralExpCSImpl();
    return collectionTypeLiteralExpCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public TupleTypeLiteralExpCS createTupleTypeLiteralExpCS() {
    TupleTypeLiteralExpCSImpl tupleTypeLiteralExpCS = new TupleTypeLiteralExpCSImpl();
    return tupleTypeLiteralExpCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public VariableDeclarationWithInitCS createVariableDeclarationWithInitCS() {
    VariableDeclarationWithInitCSImpl variableDeclarationWithInitCS = new VariableDeclarationWithInitCSImpl();
    return variableDeclarationWithInitCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public VariableDeclarationWithoutInitCS createVariableDeclarationWithoutInitCS() {
    VariableDeclarationWithoutInitCSImpl variableDeclarationWithoutInitCS = new VariableDeclarationWithoutInitCSImpl();
    return variableDeclarationWithoutInitCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public VariableDeclarationWithInitListCS createVariableDeclarationWithInitListCS() {
    VariableDeclarationWithInitListCSImpl variableDeclarationWithInitListCS = new VariableDeclarationWithInitListCSImpl();
    return variableDeclarationWithInitListCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public VariableDeclarationWithoutInitListCS createVariableDeclarationWithoutInitListCS() {
    VariableDeclarationWithoutInitListCSImpl variableDeclarationWithoutInitListCS = new VariableDeclarationWithoutInitListCSImpl();
    return variableDeclarationWithoutInitListCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public CollectionLiteralExpCS createCollectionLiteralExpCS() {
    CollectionLiteralExpCSImpl collectionLiteralExpCS = new CollectionLiteralExpCSImpl();
    return collectionLiteralExpCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public CollectionTypeIdentifierCS createCollectionTypeIdentifierCS() {
    CollectionTypeIdentifierCSImpl collectionTypeIdentifierCS = new CollectionTypeIdentifierCSImpl();
    return collectionTypeIdentifierCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public CollectionLiteralPartsOclExpCS createCollectionLiteralPartsOclExpCS() {
    CollectionLiteralPartsOclExpCSImpl collectionLiteralPartsOclExpCS = new CollectionLiteralPartsOclExpCSImpl();
    return collectionLiteralPartsOclExpCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public CollectionRangeCS createCollectionRangeCS() {
    CollectionRangeCSImpl collectionRangeCS = new CollectionRangeCSImpl();
    return collectionRangeCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public IteratorExpVariableCS createIteratorExpVariableCS() {
    IteratorExpVariableCSImpl iteratorExpVariableCS = new IteratorExpVariableCSImpl();
    return iteratorExpVariableCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public IteratorExpCS createIteratorExpCS() {
    IteratorExpCSImpl iteratorExpCS = new IteratorExpCSImpl();
    return iteratorExpCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public IterateExpCS createIterateExpCS() {
    IterateExpCSImpl iterateExpCS = new IterateExpCSImpl();
    return iterateExpCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public NavigationCallExp createNavigationCallExp() {
    NavigationCallExpImpl navigationCallExp = new NavigationCallExpImpl();
    return navigationCallExp;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public OperationCallBaseExpCS createOperationCallBaseExpCS() {
    OperationCallBaseExpCSImpl operationCallBaseExpCS = new OperationCallBaseExpCSImpl();
    return operationCallBaseExpCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public PropertyCallBaseExpCS createPropertyCallBaseExpCS() {
    PropertyCallBaseExpCSImpl propertyCallBaseExpCS = new PropertyCallBaseExpCSImpl();
    return propertyCallBaseExpCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public ImplicitPropertyCallCS createImplicitPropertyCallCS() {
    ImplicitPropertyCallCSImpl implicitPropertyCallCS = new ImplicitPropertyCallCSImpl();
    return implicitPropertyCallCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public PropertyCallExplicitPathExpCS createPropertyCallExplicitPathExpCS() {
    PropertyCallExplicitPathExpCSImpl propertyCallExplicitPathExpCS = new PropertyCallExplicitPathExpCSImpl();
    return propertyCallExplicitPathExpCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public OperationCallOnSelfExpCS createOperationCallOnSelfExpCS() {
    OperationCallOnSelfExpCSImpl operationCallOnSelfExpCS = new OperationCallOnSelfExpCSImpl();
    return operationCallOnSelfExpCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public StaticOperationCallExpCS createStaticOperationCallExpCS() {
    StaticOperationCallExpCSImpl staticOperationCallExpCS = new StaticOperationCallExpCSImpl();
    return staticOperationCallExpCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public UnaryOperationCallExpCS createUnaryOperationCallExpCS() {
    UnaryOperationCallExpCSImpl unaryOperationCallExpCS = new UnaryOperationCallExpCSImpl();
    return unaryOperationCallExpCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public LogicalNotOperationCallExpCS createLogicalNotOperationCallExpCS() {
    LogicalNotOperationCallExpCSImpl logicalNotOperationCallExpCS = new LogicalNotOperationCallExpCSImpl();
    return logicalNotOperationCallExpCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public OperationCallWithSourceExpCS createOperationCallWithSourceExpCS() {
    OperationCallWithSourceExpCSImpl operationCallWithSourceExpCS = new OperationCallWithSourceExpCSImpl();
    return operationCallWithSourceExpCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public AdditiveOperationCallExpCS createAdditiveOperationCallExpCS() {
    AdditiveOperationCallExpCSImpl additiveOperationCallExpCS = new AdditiveOperationCallExpCSImpl();
    return additiveOperationCallExpCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public MultOperationCallExpCS createMultOperationCallExpCS() {
    MultOperationCallExpCSImpl multOperationCallExpCS = new MultOperationCallExpCSImpl();
    return multOperationCallExpCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public RelationalOperationCallExpCS createRelationalOperationCallExpCS() {
    RelationalOperationCallExpCSImpl relationalOperationCallExpCS = new RelationalOperationCallExpCSImpl();
    return relationalOperationCallExpCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EqualityOperationCallExpCS createEqualityOperationCallExpCS() {
    EqualityOperationCallExpCSImpl equalityOperationCallExpCS = new EqualityOperationCallExpCSImpl();
    return equalityOperationCallExpCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public LogicalAndOperationCallExpCS createLogicalAndOperationCallExpCS() {
    LogicalAndOperationCallExpCSImpl logicalAndOperationCallExpCS = new LogicalAndOperationCallExpCSImpl();
    return logicalAndOperationCallExpCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public LogicalOrOperationCallExpCS createLogicalOrOperationCallExpCS() {
    LogicalOrOperationCallExpCSImpl logicalOrOperationCallExpCS = new LogicalOrOperationCallExpCSImpl();
    return logicalOrOperationCallExpCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public LogicalXorOperationCallExpCS createLogicalXorOperationCallExpCS() {
    LogicalXorOperationCallExpCSImpl logicalXorOperationCallExpCS = new LogicalXorOperationCallExpCSImpl();
    return logicalXorOperationCallExpCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public LogicalImpliesOperationCallExpCS createLogicalImpliesOperationCallExpCS() {
    LogicalImpliesOperationCallExpCSImpl logicalImpliesOperationCallExpCS = new LogicalImpliesOperationCallExpCSImpl();
    return logicalImpliesOperationCallExpCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public OperationCallWithImlicitSourceExpCS createOperationCallWithImlicitSourceExpCS() {
    OperationCallWithImlicitSourceExpCSImpl operationCallWithImlicitSourceExpCS = new OperationCallWithImlicitSourceExpCSImpl();
    return operationCallWithImlicitSourceExpCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public ImplicitOperationCallCS createImplicitOperationCallCS() {
    ImplicitOperationCallCSImpl implicitOperationCallCS = new ImplicitOperationCallCSImpl();
    return implicitOperationCallCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public PropertyCallOnSelfExpCS createPropertyCallOnSelfExpCS() {
    PropertyCallOnSelfExpCSImpl propertyCallOnSelfExpCS = new PropertyCallOnSelfExpCSImpl();
    return propertyCallOnSelfExpCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public TupleLiteralExpCS createTupleLiteralExpCS() {
    TupleLiteralExpCSImpl tupleLiteralExpCS = new TupleLiteralExpCSImpl();
    return tupleLiteralExpCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public IntegerLiteralExpCS createIntegerLiteralExpCS() {
    IntegerLiteralExpCSImpl integerLiteralExpCS = new IntegerLiteralExpCSImpl();
    return integerLiteralExpCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public RealLiteralExpCS createRealLiteralExpCS() {
    RealLiteralExpCSImpl realLiteralExpCS = new RealLiteralExpCSImpl();
    return realLiteralExpCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public BooleanLiteralExpCS createBooleanLiteralExpCS() {
    BooleanLiteralExpCSImpl booleanLiteralExpCS = new BooleanLiteralExpCSImpl();
    return booleanLiteralExpCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public StringLiteralExpCS createStringLiteralExpCS() {
    StringLiteralExpCSImpl stringLiteralExpCS = new StringLiteralExpCSImpl();
    return stringLiteralExpCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public InvalidLiteralExpCS createInvalidLiteralExpCS() {
    InvalidLiteralExpCSImpl invalidLiteralExpCS = new InvalidLiteralExpCSImpl();
    return invalidLiteralExpCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public NullLiteralExpCS createNullLiteralExpCS() {
    NullLiteralExpCSImpl nullLiteralExpCS = new NullLiteralExpCSImpl();
    return nullLiteralExpCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public LetExpCS createLetExpCS() {
    LetExpCSImpl letExpCS = new LetExpCSImpl();
    return letExpCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public IfExpCS createIfExpCS() {
    IfExpCSImpl ifExpCS = new IfExpCSImpl();
    return ifExpCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public PackageDeclarationWithNamespaceCS createPackageDeclarationWithNamespaceCS() {
    PackageDeclarationWithNamespaceCSImpl packageDeclarationWithNamespaceCS = new PackageDeclarationWithNamespaceCSImpl();
    return packageDeclarationWithNamespaceCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public PackageDeclarationNestedNamespaceCS createPackageDeclarationNestedNamespaceCS() {
    PackageDeclarationNestedNamespaceCSImpl packageDeclarationNestedNamespaceCS = new PackageDeclarationNestedNamespaceCSImpl();
    return packageDeclarationNestedNamespaceCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public PackageDeclarationWithoutNamespaceCS createPackageDeclarationWithoutNamespaceCS() {
    PackageDeclarationWithoutNamespaceCSImpl packageDeclarationWithoutNamespaceCS = new PackageDeclarationWithoutNamespaceCSImpl();
    return packageDeclarationWithoutNamespaceCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public AttributeContextDeclarationCS createAttributeContextDeclarationCS() {
    AttributeContextDeclarationCSImpl attributeContextDeclarationCS = new AttributeContextDeclarationCSImpl();
    return attributeContextDeclarationCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public ClassifierContextDeclarationCS createClassifierContextDeclarationCS() {
    ClassifierContextDeclarationCSImpl classifierContextDeclarationCS = new ClassifierContextDeclarationCSImpl();
    return classifierContextDeclarationCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public OperationContextDeclarationCS createOperationContextDeclarationCS() {
    OperationContextDeclarationCSImpl operationContextDeclarationCS = new OperationContextDeclarationCSImpl();
    return operationContextDeclarationCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public InitValueCS createInitValueCS() {
    InitValueCSImpl initValueCS = new InitValueCSImpl();
    return initValueCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public DeriveValueCS createDeriveValueCS() {
    DeriveValueCSImpl deriveValueCS = new DeriveValueCSImpl();
    return deriveValueCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public InvariantExpCS createInvariantExpCS() {
    InvariantExpCSImpl invariantExpCS = new InvariantExpCSImpl();
    return invariantExpCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public DefinitionExpCS createDefinitionExpCS() {
    DefinitionExpCSImpl definitionExpCS = new DefinitionExpCSImpl();
    return definitionExpCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public DefinitionExpPropertyCS createDefinitionExpPropertyCS() {
    DefinitionExpPropertyCSImpl definitionExpPropertyCS = new DefinitionExpPropertyCSImpl();
    return definitionExpPropertyCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public DefinitionExpOperationCS createDefinitionExpOperationCS() {
    DefinitionExpOperationCSImpl definitionExpOperationCS = new DefinitionExpOperationCSImpl();
    return definitionExpOperationCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public PreConditionDeclarationCS createPreConditionDeclarationCS() {
    PreConditionDeclarationCSImpl preConditionDeclarationCS = new PreConditionDeclarationCSImpl();
    return preConditionDeclarationCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public PostConditionDeclarationCS createPostConditionDeclarationCS() {
    PostConditionDeclarationCSImpl postConditionDeclarationCS = new PostConditionDeclarationCSImpl();
    return postConditionDeclarationCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public BodyDeclarationCS createBodyDeclarationCS() {
    BodyDeclarationCSImpl bodyDeclarationCS = new BodyDeclarationCSImpl();
    return bodyDeclarationCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public OperationDefinitionInContextCS createOperationDefinitionInContextCS() {
    OperationDefinitionInContextCSImpl operationDefinitionInContextCS = new OperationDefinitionInContextCSImpl();
    return operationDefinitionInContextCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public OperationDefinitionInDefCS createOperationDefinitionInDefCS() {
    OperationDefinitionInDefCSImpl operationDefinitionInDefCS = new OperationDefinitionInDefCSImpl();
    return operationDefinitionInDefCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public ParameterCS createParameterCS() {
    ParameterCSImpl parameterCS = new ParameterCSImpl();
    return parameterCS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public OclPackage getOclPackage() {
    return (OclPackage)getEPackage();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
	@Deprecated
	public static OclPackage getPackage() {
    return OclPackage.eINSTANCE;
  }

} //OclFactoryImpl
