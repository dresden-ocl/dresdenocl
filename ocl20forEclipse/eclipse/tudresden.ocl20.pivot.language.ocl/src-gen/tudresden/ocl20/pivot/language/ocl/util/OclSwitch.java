/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.language.ocl.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

import tudresden.ocl20.pivot.language.ocl.AdditiveOperationCallExpCS;
import tudresden.ocl20.pivot.language.ocl.AttributeContextDeclarationCS;
import tudresden.ocl20.pivot.language.ocl.BodyDeclarationCS;
import tudresden.ocl20.pivot.language.ocl.BooleanLiteralExpCS;
import tudresden.ocl20.pivot.language.ocl.BracketExpCS;
import tudresden.ocl20.pivot.language.ocl.CallExpCS;
import tudresden.ocl20.pivot.language.ocl.ClassifierContextDeclarationCS;
import tudresden.ocl20.pivot.language.ocl.CollectionLiteralExpCS;
import tudresden.ocl20.pivot.language.ocl.CollectionLiteralPartsCS;
import tudresden.ocl20.pivot.language.ocl.CollectionLiteralPartsOclExpCS;
import tudresden.ocl20.pivot.language.ocl.CollectionRangeCS;
import tudresden.ocl20.pivot.language.ocl.CollectionTypeIdentifierCS;
import tudresden.ocl20.pivot.language.ocl.CollectionTypeLiteralExpCS;
import tudresden.ocl20.pivot.language.ocl.ContextDeclarationCS;
import tudresden.ocl20.pivot.language.ocl.DefinitionExpCS;
import tudresden.ocl20.pivot.language.ocl.DefinitionExpOperationCS;
import tudresden.ocl20.pivot.language.ocl.DefinitionExpPartCS;
import tudresden.ocl20.pivot.language.ocl.DefinitionExpPropertyCS;
import tudresden.ocl20.pivot.language.ocl.DeriveValueCS;
import tudresden.ocl20.pivot.language.ocl.EnumLiteralOrStaticPropertyExpCS;
import tudresden.ocl20.pivot.language.ocl.EqualityOperationCallExpCS;
import tudresden.ocl20.pivot.language.ocl.FeatureCallExpCS;
import tudresden.ocl20.pivot.language.ocl.IfExpCS;
import tudresden.ocl20.pivot.language.ocl.ImplicitFeatureCallCS;
import tudresden.ocl20.pivot.language.ocl.ImplicitOperationCallCS;
import tudresden.ocl20.pivot.language.ocl.ImplicitPropertyCallCS;
import tudresden.ocl20.pivot.language.ocl.InitOrDeriveValueCS;
import tudresden.ocl20.pivot.language.ocl.InitValueCS;
import tudresden.ocl20.pivot.language.ocl.IntegerLiteralExpCS;
import tudresden.ocl20.pivot.language.ocl.InvalidLiteralExpCS;
import tudresden.ocl20.pivot.language.ocl.InvariantExpCS;
import tudresden.ocl20.pivot.language.ocl.InvariantOrDefinitionCS;
import tudresden.ocl20.pivot.language.ocl.IterateExpCS;
import tudresden.ocl20.pivot.language.ocl.IteratorExpCS;
import tudresden.ocl20.pivot.language.ocl.IteratorExpVariableCS;
import tudresden.ocl20.pivot.language.ocl.LetExpCS;
import tudresden.ocl20.pivot.language.ocl.LiteralExpCS;
import tudresden.ocl20.pivot.language.ocl.LogicalAndOperationCallExpCS;
import tudresden.ocl20.pivot.language.ocl.LogicalImpliesOperationCallExpCS;
import tudresden.ocl20.pivot.language.ocl.LogicalNotOperationCallExpCS;
import tudresden.ocl20.pivot.language.ocl.LogicalOrOperationCallExpCS;
import tudresden.ocl20.pivot.language.ocl.LogicalXorOperationCallExpCS;
import tudresden.ocl20.pivot.language.ocl.LoopExpCS;
import tudresden.ocl20.pivot.language.ocl.MultOperationCallExpCS;
import tudresden.ocl20.pivot.language.ocl.NamedLiteralExpCS;
import tudresden.ocl20.pivot.language.ocl.NavigationCallExp;
import tudresden.ocl20.pivot.language.ocl.NullLiteralExpCS;
import tudresden.ocl20.pivot.language.ocl.OclExpressionCS;
import tudresden.ocl20.pivot.language.ocl.OclPackage;
import tudresden.ocl20.pivot.language.ocl.OperationCallBaseExpCS;
import tudresden.ocl20.pivot.language.ocl.OperationCallBinaryExpCS;
import tudresden.ocl20.pivot.language.ocl.OperationCallExpCS;
import tudresden.ocl20.pivot.language.ocl.OperationCallOnSelfExpCS;
import tudresden.ocl20.pivot.language.ocl.OperationCallWithImlicitSourceExpCS;
import tudresden.ocl20.pivot.language.ocl.OperationCallWithSourceExpCS;
import tudresden.ocl20.pivot.language.ocl.OperationContextDeclarationCS;
import tudresden.ocl20.pivot.language.ocl.OperationDefinitionCS;
import tudresden.ocl20.pivot.language.ocl.OperationDefinitionInContextCS;
import tudresden.ocl20.pivot.language.ocl.OperationDefinitionInDefCS;
import tudresden.ocl20.pivot.language.ocl.PackageDeclarationCS;
import tudresden.ocl20.pivot.language.ocl.PackageDeclarationNestedNamespaceCS;
import tudresden.ocl20.pivot.language.ocl.PackageDeclarationWithNamespaceCS;
import tudresden.ocl20.pivot.language.ocl.PackageDeclarationWithoutNamespaceCS;
import tudresden.ocl20.pivot.language.ocl.ParameterCS;
import tudresden.ocl20.pivot.language.ocl.PathNameCS;
import tudresden.ocl20.pivot.language.ocl.PostConditionDeclarationCS;
import tudresden.ocl20.pivot.language.ocl.PreConditionDeclarationCS;
import tudresden.ocl20.pivot.language.ocl.PrePostOrBodyDeclarationCS;
import tudresden.ocl20.pivot.language.ocl.PrimitiveLiteralExpCS;
import tudresden.ocl20.pivot.language.ocl.PropertyCallBaseExpCS;
import tudresden.ocl20.pivot.language.ocl.PropertyCallExpCS;
import tudresden.ocl20.pivot.language.ocl.PropertyCallExplicitPathExpCS;
import tudresden.ocl20.pivot.language.ocl.PropertyCallOnSelfExpCS;
import tudresden.ocl20.pivot.language.ocl.RealLiteralExpCS;
import tudresden.ocl20.pivot.language.ocl.RelationalOperationCallExpCS;
import tudresden.ocl20.pivot.language.ocl.SimpleNameCS;
import tudresden.ocl20.pivot.language.ocl.StaticOperationCallExpCS;
import tudresden.ocl20.pivot.language.ocl.StringLiteralExpCS;
import tudresden.ocl20.pivot.language.ocl.TupleLiteralExpCS;
import tudresden.ocl20.pivot.language.ocl.TupleTypeCS;
import tudresden.ocl20.pivot.language.ocl.TupleTypeLiteralExpCS;
import tudresden.ocl20.pivot.language.ocl.TypeCS;
import tudresden.ocl20.pivot.language.ocl.TypePathNameCS;
import tudresden.ocl20.pivot.language.ocl.TypePathNameNestedCS;
import tudresden.ocl20.pivot.language.ocl.TypePathNameSimpleCS;
import tudresden.ocl20.pivot.language.ocl.UnaryOperationCallExpCS;
import tudresden.ocl20.pivot.language.ocl.VariableDeclarationCS;
import tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitCS;
import tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitListCS;
import tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithoutInitCS;
import tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithoutInitListCS;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see tudresden.ocl20.pivot.language.ocl.OclPackage
 * @generated
 */
public class OclSwitch<T> extends Switch<T> {
	/**
   * The cached model package
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected static OclPackage modelPackage;

	/**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public OclSwitch() {
    if (modelPackage == null)
    {
      modelPackage = OclPackage.eINSTANCE;
    }
  }

	/**
   * Checks whether this is a switch for the given package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @parameter ePackage the package in question.
   * @return whether this is a switch for the given package.
   * @generated
   */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
    return ePackage == modelPackage;
  }

	/**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
    switch (classifierID)
    {
      case OclPackage.OCL_EXPRESSION_CS:
      {
        OclExpressionCS oclExpressionCS = (OclExpressionCS)theEObject;
        T result = caseOclExpressionCS(oclExpressionCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.BRACKET_EXP_CS:
      {
        BracketExpCS bracketExpCS = (BracketExpCS)theEObject;
        T result = caseBracketExpCS(bracketExpCS);
        if (result == null) result = caseOclExpressionCS(bracketExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.NAMED_LITERAL_EXP_CS:
      {
        NamedLiteralExpCS namedLiteralExpCS = (NamedLiteralExpCS)theEObject;
        T result = caseNamedLiteralExpCS(namedLiteralExpCS);
        if (result == null) result = caseOclExpressionCS(namedLiteralExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.PATH_NAME_CS:
      {
        PathNameCS pathNameCS = (PathNameCS)theEObject;
        T result = casePathNameCS(pathNameCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.SIMPLE_NAME_CS:
      {
        SimpleNameCS simpleNameCS = (SimpleNameCS)theEObject;
        T result = caseSimpleNameCS(simpleNameCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.TYPE_CS:
      {
        TypeCS typeCS = (TypeCS)theEObject;
        T result = caseTypeCS(typeCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.TYPE_PATH_NAME_CS:
      {
        TypePathNameCS typePathNameCS = (TypePathNameCS)theEObject;
        T result = caseTypePathNameCS(typePathNameCS);
        if (result == null) result = caseTypeCS(typePathNameCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.TYPE_PATH_NAME_SIMPLE_CS:
      {
        TypePathNameSimpleCS typePathNameSimpleCS = (TypePathNameSimpleCS)theEObject;
        T result = caseTypePathNameSimpleCS(typePathNameSimpleCS);
        if (result == null) result = caseTypePathNameCS(typePathNameSimpleCS);
        if (result == null) result = caseTypeCS(typePathNameSimpleCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.TYPE_PATH_NAME_NESTED_CS:
      {
        TypePathNameNestedCS typePathNameNestedCS = (TypePathNameNestedCS)theEObject;
        T result = caseTypePathNameNestedCS(typePathNameNestedCS);
        if (result == null) result = caseTypePathNameCS(typePathNameNestedCS);
        if (result == null) result = caseTypeCS(typePathNameNestedCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.TUPLE_TYPE_CS:
      {
        TupleTypeCS tupleTypeCS = (TupleTypeCS)theEObject;
        T result = caseTupleTypeCS(tupleTypeCS);
        if (result == null) result = caseTypeCS(tupleTypeCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.COLLECTION_TYPE_LITERAL_EXP_CS:
      {
        CollectionTypeLiteralExpCS collectionTypeLiteralExpCS = (CollectionTypeLiteralExpCS)theEObject;
        T result = caseCollectionTypeLiteralExpCS(collectionTypeLiteralExpCS);
        if (result == null) result = caseLiteralExpCS(collectionTypeLiteralExpCS);
        if (result == null) result = caseOclExpressionCS(collectionTypeLiteralExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.TUPLE_TYPE_LITERAL_EXP_CS:
      {
        TupleTypeLiteralExpCS tupleTypeLiteralExpCS = (TupleTypeLiteralExpCS)theEObject;
        T result = caseTupleTypeLiteralExpCS(tupleTypeLiteralExpCS);
        if (result == null) result = caseLiteralExpCS(tupleTypeLiteralExpCS);
        if (result == null) result = caseOclExpressionCS(tupleTypeLiteralExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.VARIABLE_DECLARATION_CS:
      {
        VariableDeclarationCS variableDeclarationCS = (VariableDeclarationCS)theEObject;
        T result = caseVariableDeclarationCS(variableDeclarationCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS:
      {
        VariableDeclarationWithInitCS variableDeclarationWithInitCS = (VariableDeclarationWithInitCS)theEObject;
        T result = caseVariableDeclarationWithInitCS(variableDeclarationWithInitCS);
        if (result == null) result = caseVariableDeclarationCS(variableDeclarationWithInitCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.VARIABLE_DECLARATION_WITHOUT_INIT_CS:
      {
        VariableDeclarationWithoutInitCS variableDeclarationWithoutInitCS = (VariableDeclarationWithoutInitCS)theEObject;
        T result = caseVariableDeclarationWithoutInitCS(variableDeclarationWithoutInitCS);
        if (result == null) result = caseVariableDeclarationCS(variableDeclarationWithoutInitCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.VARIABLE_DECLARATION_WITH_INIT_LIST_CS:
      {
        VariableDeclarationWithInitListCS variableDeclarationWithInitListCS = (VariableDeclarationWithInitListCS)theEObject;
        T result = caseVariableDeclarationWithInitListCS(variableDeclarationWithInitListCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.VARIABLE_DECLARATION_WITHOUT_INIT_LIST_CS:
      {
        VariableDeclarationWithoutInitListCS variableDeclarationWithoutInitListCS = (VariableDeclarationWithoutInitListCS)theEObject;
        T result = caseVariableDeclarationWithoutInitListCS(variableDeclarationWithoutInitListCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.LITERAL_EXP_CS:
      {
        LiteralExpCS literalExpCS = (LiteralExpCS)theEObject;
        T result = caseLiteralExpCS(literalExpCS);
        if (result == null) result = caseOclExpressionCS(literalExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.ENUM_LITERAL_OR_STATIC_PROPERTY_EXP_CS:
      {
        EnumLiteralOrStaticPropertyExpCS enumLiteralOrStaticPropertyExpCS = (EnumLiteralOrStaticPropertyExpCS)theEObject;
        T result = caseEnumLiteralOrStaticPropertyExpCS(enumLiteralOrStaticPropertyExpCS);
        if (result == null) result = caseLiteralExpCS(enumLiteralOrStaticPropertyExpCS);
        if (result == null) result = caseOclExpressionCS(enumLiteralOrStaticPropertyExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.COLLECTION_LITERAL_EXP_CS:
      {
        CollectionLiteralExpCS collectionLiteralExpCS = (CollectionLiteralExpCS)theEObject;
        T result = caseCollectionLiteralExpCS(collectionLiteralExpCS);
        if (result == null) result = caseLiteralExpCS(collectionLiteralExpCS);
        if (result == null) result = caseOclExpressionCS(collectionLiteralExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.COLLECTION_TYPE_IDENTIFIER_CS:
      {
        CollectionTypeIdentifierCS collectionTypeIdentifierCS = (CollectionTypeIdentifierCS)theEObject;
        T result = caseCollectionTypeIdentifierCS(collectionTypeIdentifierCS);
        if (result == null) result = caseTypeCS(collectionTypeIdentifierCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.COLLECTION_LITERAL_PARTS_CS:
      {
        CollectionLiteralPartsCS collectionLiteralPartsCS = (CollectionLiteralPartsCS)theEObject;
        T result = caseCollectionLiteralPartsCS(collectionLiteralPartsCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.COLLECTION_LITERAL_PARTS_OCL_EXP_CS:
      {
        CollectionLiteralPartsOclExpCS collectionLiteralPartsOclExpCS = (CollectionLiteralPartsOclExpCS)theEObject;
        T result = caseCollectionLiteralPartsOclExpCS(collectionLiteralPartsOclExpCS);
        if (result == null) result = caseCollectionLiteralPartsCS(collectionLiteralPartsOclExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.COLLECTION_RANGE_CS:
      {
        CollectionRangeCS collectionRangeCS = (CollectionRangeCS)theEObject;
        T result = caseCollectionRangeCS(collectionRangeCS);
        if (result == null) result = caseCollectionLiteralPartsCS(collectionRangeCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.CALL_EXP_CS:
      {
        CallExpCS callExpCS = (CallExpCS)theEObject;
        T result = caseCallExpCS(callExpCS);
        if (result == null) result = caseOclExpressionCS(callExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.LOOP_EXP_CS:
      {
        LoopExpCS loopExpCS = (LoopExpCS)theEObject;
        T result = caseLoopExpCS(loopExpCS);
        if (result == null) result = caseCallExpCS(loopExpCS);
        if (result == null) result = caseOclExpressionCS(loopExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.ITERATOR_EXP_VARIABLE_CS:
      {
        IteratorExpVariableCS iteratorExpVariableCS = (IteratorExpVariableCS)theEObject;
        T result = caseIteratorExpVariableCS(iteratorExpVariableCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.ITERATOR_EXP_CS:
      {
        IteratorExpCS iteratorExpCS = (IteratorExpCS)theEObject;
        T result = caseIteratorExpCS(iteratorExpCS);
        if (result == null) result = caseLoopExpCS(iteratorExpCS);
        if (result == null) result = caseImplicitFeatureCallCS(iteratorExpCS);
        if (result == null) result = caseCallExpCS(iteratorExpCS);
        if (result == null) result = caseOclExpressionCS(iteratorExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.ITERATE_EXP_CS:
      {
        IterateExpCS iterateExpCS = (IterateExpCS)theEObject;
        T result = caseIterateExpCS(iterateExpCS);
        if (result == null) result = caseLoopExpCS(iterateExpCS);
        if (result == null) result = caseImplicitFeatureCallCS(iterateExpCS);
        if (result == null) result = caseCallExpCS(iterateExpCS);
        if (result == null) result = caseOclExpressionCS(iterateExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.FEATURE_CALL_EXP_CS:
      {
        FeatureCallExpCS featureCallExpCS = (FeatureCallExpCS)theEObject;
        T result = caseFeatureCallExpCS(featureCallExpCS);
        if (result == null) result = caseCallExpCS(featureCallExpCS);
        if (result == null) result = caseOclExpressionCS(featureCallExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.NAVIGATION_CALL_EXP:
      {
        NavigationCallExp navigationCallExp = (NavigationCallExp)theEObject;
        T result = caseNavigationCallExp(navigationCallExp);
        if (result == null) result = caseFeatureCallExpCS(navigationCallExp);
        if (result == null) result = caseCallExpCS(navigationCallExp);
        if (result == null) result = caseOclExpressionCS(navigationCallExp);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.OPERATION_CALL_BASE_EXP_CS:
      {
        OperationCallBaseExpCS operationCallBaseExpCS = (OperationCallBaseExpCS)theEObject;
        T result = caseOperationCallBaseExpCS(operationCallBaseExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.PROPERTY_CALL_BASE_EXP_CS:
      {
        PropertyCallBaseExpCS propertyCallBaseExpCS = (PropertyCallBaseExpCS)theEObject;
        T result = casePropertyCallBaseExpCS(propertyCallBaseExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.IMPLICIT_FEATURE_CALL_CS:
      {
        ImplicitFeatureCallCS implicitFeatureCallCS = (ImplicitFeatureCallCS)theEObject;
        T result = caseImplicitFeatureCallCS(implicitFeatureCallCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.IMPLICIT_PROPERTY_CALL_CS:
      {
        ImplicitPropertyCallCS implicitPropertyCallCS = (ImplicitPropertyCallCS)theEObject;
        T result = caseImplicitPropertyCallCS(implicitPropertyCallCS);
        if (result == null) result = caseImplicitFeatureCallCS(implicitPropertyCallCS);
        if (result == null) result = casePropertyCallBaseExpCS(implicitPropertyCallCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.IMPLICIT_OPERATION_CALL_CS:
      {
        ImplicitOperationCallCS implicitOperationCallCS = (ImplicitOperationCallCS)theEObject;
        T result = caseImplicitOperationCallCS(implicitOperationCallCS);
        if (result == null) result = caseImplicitFeatureCallCS(implicitOperationCallCS);
        if (result == null) result = caseOperationCallBaseExpCS(implicitOperationCallCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.PROPERTY_CALL_EXP_CS:
      {
        PropertyCallExpCS propertyCallExpCS = (PropertyCallExpCS)theEObject;
        T result = casePropertyCallExpCS(propertyCallExpCS);
        if (result == null) result = caseFeatureCallExpCS(propertyCallExpCS);
        if (result == null) result = caseCallExpCS(propertyCallExpCS);
        if (result == null) result = caseOclExpressionCS(propertyCallExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.PROPERTY_CALL_ON_SELF_EXP_CS:
      {
        PropertyCallOnSelfExpCS propertyCallOnSelfExpCS = (PropertyCallOnSelfExpCS)theEObject;
        T result = casePropertyCallOnSelfExpCS(propertyCallOnSelfExpCS);
        if (result == null) result = casePropertyCallExpCS(propertyCallOnSelfExpCS);
        if (result == null) result = casePropertyCallBaseExpCS(propertyCallOnSelfExpCS);
        if (result == null) result = caseFeatureCallExpCS(propertyCallOnSelfExpCS);
        if (result == null) result = caseCallExpCS(propertyCallOnSelfExpCS);
        if (result == null) result = caseOclExpressionCS(propertyCallOnSelfExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.PROPERTY_CALL_EXPLICIT_PATH_EXP_CS:
      {
        PropertyCallExplicitPathExpCS propertyCallExplicitPathExpCS = (PropertyCallExplicitPathExpCS)theEObject;
        T result = casePropertyCallExplicitPathExpCS(propertyCallExplicitPathExpCS);
        if (result == null) result = casePropertyCallExpCS(propertyCallExplicitPathExpCS);
        if (result == null) result = caseFeatureCallExpCS(propertyCallExplicitPathExpCS);
        if (result == null) result = caseCallExpCS(propertyCallExplicitPathExpCS);
        if (result == null) result = caseOclExpressionCS(propertyCallExplicitPathExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.OPERATION_CALL_EXP_CS:
      {
        OperationCallExpCS operationCallExpCS = (OperationCallExpCS)theEObject;
        T result = caseOperationCallExpCS(operationCallExpCS);
        if (result == null) result = caseFeatureCallExpCS(operationCallExpCS);
        if (result == null) result = caseCallExpCS(operationCallExpCS);
        if (result == null) result = caseOclExpressionCS(operationCallExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.OPERATION_CALL_ON_SELF_EXP_CS:
      {
        OperationCallOnSelfExpCS operationCallOnSelfExpCS = (OperationCallOnSelfExpCS)theEObject;
        T result = caseOperationCallOnSelfExpCS(operationCallOnSelfExpCS);
        if (result == null) result = caseOperationCallExpCS(operationCallOnSelfExpCS);
        if (result == null) result = caseOperationCallBaseExpCS(operationCallOnSelfExpCS);
        if (result == null) result = caseFeatureCallExpCS(operationCallOnSelfExpCS);
        if (result == null) result = caseCallExpCS(operationCallOnSelfExpCS);
        if (result == null) result = caseOclExpressionCS(operationCallOnSelfExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.STATIC_OPERATION_CALL_EXP_CS:
      {
        StaticOperationCallExpCS staticOperationCallExpCS = (StaticOperationCallExpCS)theEObject;
        T result = caseStaticOperationCallExpCS(staticOperationCallExpCS);
        if (result == null) result = caseOperationCallExpCS(staticOperationCallExpCS);
        if (result == null) result = caseFeatureCallExpCS(staticOperationCallExpCS);
        if (result == null) result = caseCallExpCS(staticOperationCallExpCS);
        if (result == null) result = caseOclExpressionCS(staticOperationCallExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.UNARY_OPERATION_CALL_EXP_CS:
      {
        UnaryOperationCallExpCS unaryOperationCallExpCS = (UnaryOperationCallExpCS)theEObject;
        T result = caseUnaryOperationCallExpCS(unaryOperationCallExpCS);
        if (result == null) result = caseOperationCallExpCS(unaryOperationCallExpCS);
        if (result == null) result = caseFeatureCallExpCS(unaryOperationCallExpCS);
        if (result == null) result = caseCallExpCS(unaryOperationCallExpCS);
        if (result == null) result = caseOclExpressionCS(unaryOperationCallExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.LOGICAL_NOT_OPERATION_CALL_EXP_CS:
      {
        LogicalNotOperationCallExpCS logicalNotOperationCallExpCS = (LogicalNotOperationCallExpCS)theEObject;
        T result = caseLogicalNotOperationCallExpCS(logicalNotOperationCallExpCS);
        if (result == null) result = caseOperationCallExpCS(logicalNotOperationCallExpCS);
        if (result == null) result = caseFeatureCallExpCS(logicalNotOperationCallExpCS);
        if (result == null) result = caseCallExpCS(logicalNotOperationCallExpCS);
        if (result == null) result = caseOclExpressionCS(logicalNotOperationCallExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.OPERATION_CALL_WITH_SOURCE_EXP_CS:
      {
        OperationCallWithSourceExpCS operationCallWithSourceExpCS = (OperationCallWithSourceExpCS)theEObject;
        T result = caseOperationCallWithSourceExpCS(operationCallWithSourceExpCS);
        if (result == null) result = caseOperationCallExpCS(operationCallWithSourceExpCS);
        if (result == null) result = caseFeatureCallExpCS(operationCallWithSourceExpCS);
        if (result == null) result = caseCallExpCS(operationCallWithSourceExpCS);
        if (result == null) result = caseOclExpressionCS(operationCallWithSourceExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.OPERATION_CALL_BINARY_EXP_CS:
      {
        OperationCallBinaryExpCS operationCallBinaryExpCS = (OperationCallBinaryExpCS)theEObject;
        T result = caseOperationCallBinaryExpCS(operationCallBinaryExpCS);
        if (result == null) result = caseOperationCallWithSourceExpCS(operationCallBinaryExpCS);
        if (result == null) result = caseOperationCallExpCS(operationCallBinaryExpCS);
        if (result == null) result = caseFeatureCallExpCS(operationCallBinaryExpCS);
        if (result == null) result = caseCallExpCS(operationCallBinaryExpCS);
        if (result == null) result = caseOclExpressionCS(operationCallBinaryExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.ADDITIVE_OPERATION_CALL_EXP_CS:
      {
        AdditiveOperationCallExpCS additiveOperationCallExpCS = (AdditiveOperationCallExpCS)theEObject;
        T result = caseAdditiveOperationCallExpCS(additiveOperationCallExpCS);
        if (result == null) result = caseOperationCallBinaryExpCS(additiveOperationCallExpCS);
        if (result == null) result = caseOperationCallWithSourceExpCS(additiveOperationCallExpCS);
        if (result == null) result = caseOperationCallExpCS(additiveOperationCallExpCS);
        if (result == null) result = caseFeatureCallExpCS(additiveOperationCallExpCS);
        if (result == null) result = caseCallExpCS(additiveOperationCallExpCS);
        if (result == null) result = caseOclExpressionCS(additiveOperationCallExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.MULT_OPERATION_CALL_EXP_CS:
      {
        MultOperationCallExpCS multOperationCallExpCS = (MultOperationCallExpCS)theEObject;
        T result = caseMultOperationCallExpCS(multOperationCallExpCS);
        if (result == null) result = caseOperationCallBinaryExpCS(multOperationCallExpCS);
        if (result == null) result = caseOperationCallWithSourceExpCS(multOperationCallExpCS);
        if (result == null) result = caseOperationCallExpCS(multOperationCallExpCS);
        if (result == null) result = caseFeatureCallExpCS(multOperationCallExpCS);
        if (result == null) result = caseCallExpCS(multOperationCallExpCS);
        if (result == null) result = caseOclExpressionCS(multOperationCallExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.RELATIONAL_OPERATION_CALL_EXP_CS:
      {
        RelationalOperationCallExpCS relationalOperationCallExpCS = (RelationalOperationCallExpCS)theEObject;
        T result = caseRelationalOperationCallExpCS(relationalOperationCallExpCS);
        if (result == null) result = caseOperationCallBinaryExpCS(relationalOperationCallExpCS);
        if (result == null) result = caseOperationCallWithSourceExpCS(relationalOperationCallExpCS);
        if (result == null) result = caseOperationCallExpCS(relationalOperationCallExpCS);
        if (result == null) result = caseFeatureCallExpCS(relationalOperationCallExpCS);
        if (result == null) result = caseCallExpCS(relationalOperationCallExpCS);
        if (result == null) result = caseOclExpressionCS(relationalOperationCallExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.EQUALITY_OPERATION_CALL_EXP_CS:
      {
        EqualityOperationCallExpCS equalityOperationCallExpCS = (EqualityOperationCallExpCS)theEObject;
        T result = caseEqualityOperationCallExpCS(equalityOperationCallExpCS);
        if (result == null) result = caseOperationCallBinaryExpCS(equalityOperationCallExpCS);
        if (result == null) result = caseOperationCallWithSourceExpCS(equalityOperationCallExpCS);
        if (result == null) result = caseOperationCallExpCS(equalityOperationCallExpCS);
        if (result == null) result = caseFeatureCallExpCS(equalityOperationCallExpCS);
        if (result == null) result = caseCallExpCS(equalityOperationCallExpCS);
        if (result == null) result = caseOclExpressionCS(equalityOperationCallExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.LOGICAL_AND_OPERATION_CALL_EXP_CS:
      {
        LogicalAndOperationCallExpCS logicalAndOperationCallExpCS = (LogicalAndOperationCallExpCS)theEObject;
        T result = caseLogicalAndOperationCallExpCS(logicalAndOperationCallExpCS);
        if (result == null) result = caseOperationCallBinaryExpCS(logicalAndOperationCallExpCS);
        if (result == null) result = caseOperationCallWithSourceExpCS(logicalAndOperationCallExpCS);
        if (result == null) result = caseOperationCallExpCS(logicalAndOperationCallExpCS);
        if (result == null) result = caseFeatureCallExpCS(logicalAndOperationCallExpCS);
        if (result == null) result = caseCallExpCS(logicalAndOperationCallExpCS);
        if (result == null) result = caseOclExpressionCS(logicalAndOperationCallExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.LOGICAL_OR_OPERATION_CALL_EXP_CS:
      {
        LogicalOrOperationCallExpCS logicalOrOperationCallExpCS = (LogicalOrOperationCallExpCS)theEObject;
        T result = caseLogicalOrOperationCallExpCS(logicalOrOperationCallExpCS);
        if (result == null) result = caseOperationCallBinaryExpCS(logicalOrOperationCallExpCS);
        if (result == null) result = caseOperationCallWithSourceExpCS(logicalOrOperationCallExpCS);
        if (result == null) result = caseOperationCallExpCS(logicalOrOperationCallExpCS);
        if (result == null) result = caseFeatureCallExpCS(logicalOrOperationCallExpCS);
        if (result == null) result = caseCallExpCS(logicalOrOperationCallExpCS);
        if (result == null) result = caseOclExpressionCS(logicalOrOperationCallExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.LOGICAL_XOR_OPERATION_CALL_EXP_CS:
      {
        LogicalXorOperationCallExpCS logicalXorOperationCallExpCS = (LogicalXorOperationCallExpCS)theEObject;
        T result = caseLogicalXorOperationCallExpCS(logicalXorOperationCallExpCS);
        if (result == null) result = caseOperationCallBinaryExpCS(logicalXorOperationCallExpCS);
        if (result == null) result = caseOperationCallWithSourceExpCS(logicalXorOperationCallExpCS);
        if (result == null) result = caseOperationCallExpCS(logicalXorOperationCallExpCS);
        if (result == null) result = caseFeatureCallExpCS(logicalXorOperationCallExpCS);
        if (result == null) result = caseCallExpCS(logicalXorOperationCallExpCS);
        if (result == null) result = caseOclExpressionCS(logicalXorOperationCallExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.LOGICAL_IMPLIES_OPERATION_CALL_EXP_CS:
      {
        LogicalImpliesOperationCallExpCS logicalImpliesOperationCallExpCS = (LogicalImpliesOperationCallExpCS)theEObject;
        T result = caseLogicalImpliesOperationCallExpCS(logicalImpliesOperationCallExpCS);
        if (result == null) result = caseOperationCallBinaryExpCS(logicalImpliesOperationCallExpCS);
        if (result == null) result = caseOperationCallWithSourceExpCS(logicalImpliesOperationCallExpCS);
        if (result == null) result = caseOperationCallExpCS(logicalImpliesOperationCallExpCS);
        if (result == null) result = caseFeatureCallExpCS(logicalImpliesOperationCallExpCS);
        if (result == null) result = caseCallExpCS(logicalImpliesOperationCallExpCS);
        if (result == null) result = caseOclExpressionCS(logicalImpliesOperationCallExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.OPERATION_CALL_WITH_IMLICIT_SOURCE_EXP_CS:
      {
        OperationCallWithImlicitSourceExpCS operationCallWithImlicitSourceExpCS = (OperationCallWithImlicitSourceExpCS)theEObject;
        T result = caseOperationCallWithImlicitSourceExpCS(operationCallWithImlicitSourceExpCS);
        if (result == null) result = caseOperationCallOnSelfExpCS(operationCallWithImlicitSourceExpCS);
        if (result == null) result = caseOperationCallExpCS(operationCallWithImlicitSourceExpCS);
        if (result == null) result = caseOperationCallBaseExpCS(operationCallWithImlicitSourceExpCS);
        if (result == null) result = caseFeatureCallExpCS(operationCallWithImlicitSourceExpCS);
        if (result == null) result = caseCallExpCS(operationCallWithImlicitSourceExpCS);
        if (result == null) result = caseOclExpressionCS(operationCallWithImlicitSourceExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.TUPLE_LITERAL_EXP_CS:
      {
        TupleLiteralExpCS tupleLiteralExpCS = (TupleLiteralExpCS)theEObject;
        T result = caseTupleLiteralExpCS(tupleLiteralExpCS);
        if (result == null) result = caseLiteralExpCS(tupleLiteralExpCS);
        if (result == null) result = caseOclExpressionCS(tupleLiteralExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.PRIMITIVE_LITERAL_EXP_CS:
      {
        PrimitiveLiteralExpCS primitiveLiteralExpCS = (PrimitiveLiteralExpCS)theEObject;
        T result = casePrimitiveLiteralExpCS(primitiveLiteralExpCS);
        if (result == null) result = caseLiteralExpCS(primitiveLiteralExpCS);
        if (result == null) result = caseOclExpressionCS(primitiveLiteralExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.INTEGER_LITERAL_EXP_CS:
      {
        IntegerLiteralExpCS integerLiteralExpCS = (IntegerLiteralExpCS)theEObject;
        T result = caseIntegerLiteralExpCS(integerLiteralExpCS);
        if (result == null) result = casePrimitiveLiteralExpCS(integerLiteralExpCS);
        if (result == null) result = caseLiteralExpCS(integerLiteralExpCS);
        if (result == null) result = caseOclExpressionCS(integerLiteralExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.REAL_LITERAL_EXP_CS:
      {
        RealLiteralExpCS realLiteralExpCS = (RealLiteralExpCS)theEObject;
        T result = caseRealLiteralExpCS(realLiteralExpCS);
        if (result == null) result = casePrimitiveLiteralExpCS(realLiteralExpCS);
        if (result == null) result = caseLiteralExpCS(realLiteralExpCS);
        if (result == null) result = caseOclExpressionCS(realLiteralExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.BOOLEAN_LITERAL_EXP_CS:
      {
        BooleanLiteralExpCS booleanLiteralExpCS = (BooleanLiteralExpCS)theEObject;
        T result = caseBooleanLiteralExpCS(booleanLiteralExpCS);
        if (result == null) result = casePrimitiveLiteralExpCS(booleanLiteralExpCS);
        if (result == null) result = caseLiteralExpCS(booleanLiteralExpCS);
        if (result == null) result = caseOclExpressionCS(booleanLiteralExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.STRING_LITERAL_EXP_CS:
      {
        StringLiteralExpCS stringLiteralExpCS = (StringLiteralExpCS)theEObject;
        T result = caseStringLiteralExpCS(stringLiteralExpCS);
        if (result == null) result = casePrimitiveLiteralExpCS(stringLiteralExpCS);
        if (result == null) result = caseLiteralExpCS(stringLiteralExpCS);
        if (result == null) result = caseOclExpressionCS(stringLiteralExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.INVALID_LITERAL_EXP_CS:
      {
        InvalidLiteralExpCS invalidLiteralExpCS = (InvalidLiteralExpCS)theEObject;
        T result = caseInvalidLiteralExpCS(invalidLiteralExpCS);
        if (result == null) result = casePrimitiveLiteralExpCS(invalidLiteralExpCS);
        if (result == null) result = caseLiteralExpCS(invalidLiteralExpCS);
        if (result == null) result = caseOclExpressionCS(invalidLiteralExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.NULL_LITERAL_EXP_CS:
      {
        NullLiteralExpCS nullLiteralExpCS = (NullLiteralExpCS)theEObject;
        T result = caseNullLiteralExpCS(nullLiteralExpCS);
        if (result == null) result = casePrimitiveLiteralExpCS(nullLiteralExpCS);
        if (result == null) result = caseLiteralExpCS(nullLiteralExpCS);
        if (result == null) result = caseOclExpressionCS(nullLiteralExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.LET_EXP_CS:
      {
        LetExpCS letExpCS = (LetExpCS)theEObject;
        T result = caseLetExpCS(letExpCS);
        if (result == null) result = caseOclExpressionCS(letExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.IF_EXP_CS:
      {
        IfExpCS ifExpCS = (IfExpCS)theEObject;
        T result = caseIfExpCS(ifExpCS);
        if (result == null) result = caseOclExpressionCS(ifExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.PACKAGE_DECLARATION_CS:
      {
        PackageDeclarationCS packageDeclarationCS = (PackageDeclarationCS)theEObject;
        T result = casePackageDeclarationCS(packageDeclarationCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.PACKAGE_DECLARATION_WITH_NAMESPACE_CS:
      {
        PackageDeclarationWithNamespaceCS packageDeclarationWithNamespaceCS = (PackageDeclarationWithNamespaceCS)theEObject;
        T result = casePackageDeclarationWithNamespaceCS(packageDeclarationWithNamespaceCS);
        if (result == null) result = casePackageDeclarationCS(packageDeclarationWithNamespaceCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.PACKAGE_DECLARATION_NESTED_NAMESPACE_CS:
      {
        PackageDeclarationNestedNamespaceCS packageDeclarationNestedNamespaceCS = (PackageDeclarationNestedNamespaceCS)theEObject;
        T result = casePackageDeclarationNestedNamespaceCS(packageDeclarationNestedNamespaceCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.PACKAGE_DECLARATION_WITHOUT_NAMESPACE_CS:
      {
        PackageDeclarationWithoutNamespaceCS packageDeclarationWithoutNamespaceCS = (PackageDeclarationWithoutNamespaceCS)theEObject;
        T result = casePackageDeclarationWithoutNamespaceCS(packageDeclarationWithoutNamespaceCS);
        if (result == null) result = casePackageDeclarationCS(packageDeclarationWithoutNamespaceCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.CONTEXT_DECLARATION_CS:
      {
        ContextDeclarationCS contextDeclarationCS = (ContextDeclarationCS)theEObject;
        T result = caseContextDeclarationCS(contextDeclarationCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS:
      {
        AttributeContextDeclarationCS attributeContextDeclarationCS = (AttributeContextDeclarationCS)theEObject;
        T result = caseAttributeContextDeclarationCS(attributeContextDeclarationCS);
        if (result == null) result = caseContextDeclarationCS(attributeContextDeclarationCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.CLASSIFIER_CONTEXT_DECLARATION_CS:
      {
        ClassifierContextDeclarationCS classifierContextDeclarationCS = (ClassifierContextDeclarationCS)theEObject;
        T result = caseClassifierContextDeclarationCS(classifierContextDeclarationCS);
        if (result == null) result = caseContextDeclarationCS(classifierContextDeclarationCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.OPERATION_CONTEXT_DECLARATION_CS:
      {
        OperationContextDeclarationCS operationContextDeclarationCS = (OperationContextDeclarationCS)theEObject;
        T result = caseOperationContextDeclarationCS(operationContextDeclarationCS);
        if (result == null) result = caseContextDeclarationCS(operationContextDeclarationCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.INIT_OR_DERIVE_VALUE_CS:
      {
        InitOrDeriveValueCS initOrDeriveValueCS = (InitOrDeriveValueCS)theEObject;
        T result = caseInitOrDeriveValueCS(initOrDeriveValueCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.INIT_VALUE_CS:
      {
        InitValueCS initValueCS = (InitValueCS)theEObject;
        T result = caseInitValueCS(initValueCS);
        if (result == null) result = caseInitOrDeriveValueCS(initValueCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.DERIVE_VALUE_CS:
      {
        DeriveValueCS deriveValueCS = (DeriveValueCS)theEObject;
        T result = caseDeriveValueCS(deriveValueCS);
        if (result == null) result = caseInitOrDeriveValueCS(deriveValueCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.INVARIANT_OR_DEFINITION_CS:
      {
        InvariantOrDefinitionCS invariantOrDefinitionCS = (InvariantOrDefinitionCS)theEObject;
        T result = caseInvariantOrDefinitionCS(invariantOrDefinitionCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.INVARIANT_EXP_CS:
      {
        InvariantExpCS invariantExpCS = (InvariantExpCS)theEObject;
        T result = caseInvariantExpCS(invariantExpCS);
        if (result == null) result = caseInvariantOrDefinitionCS(invariantExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.DEFINITION_EXP_CS:
      {
        DefinitionExpCS definitionExpCS = (DefinitionExpCS)theEObject;
        T result = caseDefinitionExpCS(definitionExpCS);
        if (result == null) result = caseInvariantOrDefinitionCS(definitionExpCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.DEFINITION_EXP_PART_CS:
      {
        DefinitionExpPartCS definitionExpPartCS = (DefinitionExpPartCS)theEObject;
        T result = caseDefinitionExpPartCS(definitionExpPartCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.DEFINITION_EXP_PROPERTY_CS:
      {
        DefinitionExpPropertyCS definitionExpPropertyCS = (DefinitionExpPropertyCS)theEObject;
        T result = caseDefinitionExpPropertyCS(definitionExpPropertyCS);
        if (result == null) result = caseDefinitionExpPartCS(definitionExpPropertyCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.DEFINITION_EXP_OPERATION_CS:
      {
        DefinitionExpOperationCS definitionExpOperationCS = (DefinitionExpOperationCS)theEObject;
        T result = caseDefinitionExpOperationCS(definitionExpOperationCS);
        if (result == null) result = caseDefinitionExpPartCS(definitionExpOperationCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.PRE_POST_OR_BODY_DECLARATION_CS:
      {
        PrePostOrBodyDeclarationCS prePostOrBodyDeclarationCS = (PrePostOrBodyDeclarationCS)theEObject;
        T result = casePrePostOrBodyDeclarationCS(prePostOrBodyDeclarationCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.PRE_CONDITION_DECLARATION_CS:
      {
        PreConditionDeclarationCS preConditionDeclarationCS = (PreConditionDeclarationCS)theEObject;
        T result = casePreConditionDeclarationCS(preConditionDeclarationCS);
        if (result == null) result = casePrePostOrBodyDeclarationCS(preConditionDeclarationCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.POST_CONDITION_DECLARATION_CS:
      {
        PostConditionDeclarationCS postConditionDeclarationCS = (PostConditionDeclarationCS)theEObject;
        T result = casePostConditionDeclarationCS(postConditionDeclarationCS);
        if (result == null) result = casePrePostOrBodyDeclarationCS(postConditionDeclarationCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.BODY_DECLARATION_CS:
      {
        BodyDeclarationCS bodyDeclarationCS = (BodyDeclarationCS)theEObject;
        T result = caseBodyDeclarationCS(bodyDeclarationCS);
        if (result == null) result = casePrePostOrBodyDeclarationCS(bodyDeclarationCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.OPERATION_DEFINITION_CS:
      {
        OperationDefinitionCS operationDefinitionCS = (OperationDefinitionCS)theEObject;
        T result = caseOperationDefinitionCS(operationDefinitionCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.OPERATION_DEFINITION_IN_CONTEXT_CS:
      {
        OperationDefinitionInContextCS operationDefinitionInContextCS = (OperationDefinitionInContextCS)theEObject;
        T result = caseOperationDefinitionInContextCS(operationDefinitionInContextCS);
        if (result == null) result = caseOperationDefinitionCS(operationDefinitionInContextCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.OPERATION_DEFINITION_IN_DEF_CS:
      {
        OperationDefinitionInDefCS operationDefinitionInDefCS = (OperationDefinitionInDefCS)theEObject;
        T result = caseOperationDefinitionInDefCS(operationDefinitionInDefCS);
        if (result == null) result = caseOperationDefinitionCS(operationDefinitionInDefCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OclPackage.PARAMETER_CS:
      {
        ParameterCS parameterCS = (ParameterCS)theEObject;
        T result = caseParameterCS(parameterCS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Expression CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Expression CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseOclExpressionCS(OclExpressionCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Bracket Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Bracket Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseBracketExpCS(BracketExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Named Literal Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Named Literal Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseNamedLiteralExpCS(NamedLiteralExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Path Name CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Path Name CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePathNameCS(PathNameCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Simple Name CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Simple Name CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseSimpleNameCS(SimpleNameCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Type CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Type CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseTypeCS(TypeCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Type Path Name CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Type Path Name CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseTypePathNameCS(TypePathNameCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Type Path Name Simple CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Type Path Name Simple CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseTypePathNameSimpleCS(TypePathNameSimpleCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Type Path Name Nested CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Type Path Name Nested CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseTypePathNameNestedCS(TypePathNameNestedCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Tuple Type CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Tuple Type CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseTupleTypeCS(TupleTypeCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Collection Type Literal Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Collection Type Literal Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseCollectionTypeLiteralExpCS(CollectionTypeLiteralExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Tuple Type Literal Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Tuple Type Literal Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseTupleTypeLiteralExpCS(TupleTypeLiteralExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Variable Declaration CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Variable Declaration CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseVariableDeclarationCS(VariableDeclarationCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Variable Declaration With Init CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Variable Declaration With Init CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseVariableDeclarationWithInitCS(VariableDeclarationWithInitCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Variable Declaration Without Init CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Variable Declaration Without Init CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseVariableDeclarationWithoutInitCS(VariableDeclarationWithoutInitCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Variable Declaration With Init List CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Variable Declaration With Init List CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseVariableDeclarationWithInitListCS(VariableDeclarationWithInitListCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Variable Declaration Without Init List CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Variable Declaration Without Init List CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseVariableDeclarationWithoutInitListCS(VariableDeclarationWithoutInitListCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Literal Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Literal Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseLiteralExpCS(LiteralExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Enum Literal Or Static Property Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Enum Literal Or Static Property Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseEnumLiteralOrStaticPropertyExpCS(EnumLiteralOrStaticPropertyExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Collection Literal Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Collection Literal Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseCollectionLiteralExpCS(CollectionLiteralExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Collection Type Identifier CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Collection Type Identifier CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseCollectionTypeIdentifierCS(CollectionTypeIdentifierCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Collection Literal Parts CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Collection Literal Parts CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseCollectionLiteralPartsCS(CollectionLiteralPartsCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Collection Literal Parts Ocl Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Collection Literal Parts Ocl Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseCollectionLiteralPartsOclExpCS(CollectionLiteralPartsOclExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Collection Range CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Collection Range CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseCollectionRangeCS(CollectionRangeCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Call Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Call Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseCallExpCS(CallExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Loop Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Loop Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseLoopExpCS(LoopExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Iterator Exp Variable CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Iterator Exp Variable CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseIteratorExpVariableCS(IteratorExpVariableCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Iterator Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Iterator Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseIteratorExpCS(IteratorExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Iterate Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Iterate Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseIterateExpCS(IterateExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Feature Call Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Feature Call Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseFeatureCallExpCS(FeatureCallExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Navigation Call Exp</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Navigation Call Exp</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseNavigationCallExp(NavigationCallExp object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Operation Call Base Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Operation Call Base Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseOperationCallBaseExpCS(OperationCallBaseExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Property Call Base Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Property Call Base Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePropertyCallBaseExpCS(PropertyCallBaseExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Implicit Feature Call CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Implicit Feature Call CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseImplicitFeatureCallCS(ImplicitFeatureCallCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Property Call Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Property Call Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePropertyCallExpCS(PropertyCallExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Property Call On Self Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Property Call On Self Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePropertyCallOnSelfExpCS(PropertyCallOnSelfExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Implicit Property Call CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Implicit Property Call CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseImplicitPropertyCallCS(ImplicitPropertyCallCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Property Call Explicit Path Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Property Call Explicit Path Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePropertyCallExplicitPathExpCS(PropertyCallExplicitPathExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Operation Call Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Operation Call Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseOperationCallExpCS(OperationCallExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Operation Call On Self Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Operation Call On Self Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseOperationCallOnSelfExpCS(OperationCallOnSelfExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Static Operation Call Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Static Operation Call Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseStaticOperationCallExpCS(StaticOperationCallExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Unary Operation Call Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Unary Operation Call Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseUnaryOperationCallExpCS(UnaryOperationCallExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Logical Not Operation Call Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Logical Not Operation Call Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseLogicalNotOperationCallExpCS(LogicalNotOperationCallExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Operation Call With Source Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Operation Call With Source Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseOperationCallWithSourceExpCS(OperationCallWithSourceExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Operation Call Binary Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Operation Call Binary Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseOperationCallBinaryExpCS(OperationCallBinaryExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Additive Operation Call Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Additive Operation Call Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAdditiveOperationCallExpCS(AdditiveOperationCallExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Mult Operation Call Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Mult Operation Call Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseMultOperationCallExpCS(MultOperationCallExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Relational Operation Call Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Relational Operation Call Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseRelationalOperationCallExpCS(RelationalOperationCallExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Equality Operation Call Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Equality Operation Call Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseEqualityOperationCallExpCS(EqualityOperationCallExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Logical And Operation Call Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Logical And Operation Call Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseLogicalAndOperationCallExpCS(LogicalAndOperationCallExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Logical Or Operation Call Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Logical Or Operation Call Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseLogicalOrOperationCallExpCS(LogicalOrOperationCallExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Logical Xor Operation Call Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Logical Xor Operation Call Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseLogicalXorOperationCallExpCS(LogicalXorOperationCallExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Logical Implies Operation Call Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Logical Implies Operation Call Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseLogicalImpliesOperationCallExpCS(LogicalImpliesOperationCallExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Operation Call With Imlicit Source Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Operation Call With Imlicit Source Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseOperationCallWithImlicitSourceExpCS(OperationCallWithImlicitSourceExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Implicit Operation Call CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Implicit Operation Call CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseImplicitOperationCallCS(ImplicitOperationCallCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Tuple Literal Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Tuple Literal Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseTupleLiteralExpCS(TupleLiteralExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Primitive Literal Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Primitive Literal Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePrimitiveLiteralExpCS(PrimitiveLiteralExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Integer Literal Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Integer Literal Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseIntegerLiteralExpCS(IntegerLiteralExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Real Literal Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Real Literal Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseRealLiteralExpCS(RealLiteralExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Boolean Literal Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Boolean Literal Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseBooleanLiteralExpCS(BooleanLiteralExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>String Literal Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>String Literal Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseStringLiteralExpCS(StringLiteralExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Invalid Literal Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Invalid Literal Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseInvalidLiteralExpCS(InvalidLiteralExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Null Literal Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Null Literal Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseNullLiteralExpCS(NullLiteralExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Let Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Let Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseLetExpCS(LetExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>If Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>If Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseIfExpCS(IfExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Package Declaration CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Package Declaration CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePackageDeclarationCS(PackageDeclarationCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Package Declaration With Namespace CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Package Declaration With Namespace CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePackageDeclarationWithNamespaceCS(PackageDeclarationWithNamespaceCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Package Declaration Nested Namespace CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Package Declaration Nested Namespace CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePackageDeclarationNestedNamespaceCS(PackageDeclarationNestedNamespaceCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Package Declaration Without Namespace CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Package Declaration Without Namespace CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePackageDeclarationWithoutNamespaceCS(PackageDeclarationWithoutNamespaceCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Context Declaration CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Context Declaration CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseContextDeclarationCS(ContextDeclarationCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Attribute Context Declaration CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Attribute Context Declaration CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAttributeContextDeclarationCS(AttributeContextDeclarationCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Classifier Context Declaration CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Classifier Context Declaration CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseClassifierContextDeclarationCS(ClassifierContextDeclarationCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Operation Context Declaration CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Operation Context Declaration CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseOperationContextDeclarationCS(OperationContextDeclarationCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Init Or Derive Value CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Init Or Derive Value CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseInitOrDeriveValueCS(InitOrDeriveValueCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Init Value CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Init Value CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseInitValueCS(InitValueCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Derive Value CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Derive Value CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseDeriveValueCS(DeriveValueCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Invariant Or Definition CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Invariant Or Definition CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseInvariantOrDefinitionCS(InvariantOrDefinitionCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Invariant Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Invariant Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseInvariantExpCS(InvariantExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Definition Exp CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Definition Exp CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseDefinitionExpCS(DefinitionExpCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Definition Exp Part CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Definition Exp Part CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseDefinitionExpPartCS(DefinitionExpPartCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Definition Exp Property CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Definition Exp Property CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseDefinitionExpPropertyCS(DefinitionExpPropertyCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Definition Exp Operation CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Definition Exp Operation CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseDefinitionExpOperationCS(DefinitionExpOperationCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Pre Post Or Body Declaration CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Pre Post Or Body Declaration CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePrePostOrBodyDeclarationCS(PrePostOrBodyDeclarationCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Pre Condition Declaration CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Pre Condition Declaration CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePreConditionDeclarationCS(PreConditionDeclarationCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Post Condition Declaration CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Post Condition Declaration CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePostConditionDeclarationCS(PostConditionDeclarationCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Body Declaration CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Body Declaration CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseBodyDeclarationCS(BodyDeclarationCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Operation Definition CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Operation Definition CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseOperationDefinitionCS(OperationDefinitionCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Operation Definition In Context CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Operation Definition In Context CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseOperationDefinitionInContextCS(OperationDefinitionInContextCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Operation Definition In Def CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Operation Definition In Def CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseOperationDefinitionInDefCS(OperationDefinitionInDefCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Parameter CS</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Parameter CS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseParameterCS(ParameterCS object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
	@Override
	public T defaultCase(EObject object) {
    return null;
  }

} //OclSwitch
