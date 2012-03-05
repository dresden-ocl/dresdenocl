/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.language.ocl.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

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
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see tudresden.ocl20.pivot.language.ocl.OclPackage
 * @generated
 */
public class OclAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static OclPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OclAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = OclPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OclSwitch<Adapter> modelSwitch =
		new OclSwitch<Adapter>() {
			@Override
			public Adapter caseOclExpressionCS(OclExpressionCS object) {
				return createOclExpressionCSAdapter();
			}
			@Override
			public Adapter caseBracketExpCS(BracketExpCS object) {
				return createBracketExpCSAdapter();
			}
			@Override
			public Adapter caseNamedLiteralExpCS(NamedLiteralExpCS object) {
				return createNamedLiteralExpCSAdapter();
			}
			@Override
			public Adapter casePathNameCS(PathNameCS object) {
				return createPathNameCSAdapter();
			}
			@Override
			public Adapter caseSimpleNameCS(SimpleNameCS object) {
				return createSimpleNameCSAdapter();
			}
			@Override
			public Adapter caseTypeCS(TypeCS object) {
				return createTypeCSAdapter();
			}
			@Override
			public Adapter caseTypePathNameCS(TypePathNameCS object) {
				return createTypePathNameCSAdapter();
			}
			@Override
			public Adapter caseTypePathNameSimpleCS(TypePathNameSimpleCS object) {
				return createTypePathNameSimpleCSAdapter();
			}
			@Override
			public Adapter caseTypePathNameNestedCS(TypePathNameNestedCS object) {
				return createTypePathNameNestedCSAdapter();
			}
			@Override
			public Adapter caseTupleTypeCS(TupleTypeCS object) {
				return createTupleTypeCSAdapter();
			}
			@Override
			public Adapter caseCollectionTypeLiteralExpCS(CollectionTypeLiteralExpCS object) {
				return createCollectionTypeLiteralExpCSAdapter();
			}
			@Override
			public Adapter caseTupleTypeLiteralExpCS(TupleTypeLiteralExpCS object) {
				return createTupleTypeLiteralExpCSAdapter();
			}
			@Override
			public Adapter caseVariableDeclarationCS(VariableDeclarationCS object) {
				return createVariableDeclarationCSAdapter();
			}
			@Override
			public Adapter caseVariableDeclarationWithInitCS(VariableDeclarationWithInitCS object) {
				return createVariableDeclarationWithInitCSAdapter();
			}
			@Override
			public Adapter caseVariableDeclarationWithoutInitCS(VariableDeclarationWithoutInitCS object) {
				return createVariableDeclarationWithoutInitCSAdapter();
			}
			@Override
			public Adapter caseVariableDeclarationWithInitListCS(VariableDeclarationWithInitListCS object) {
				return createVariableDeclarationWithInitListCSAdapter();
			}
			@Override
			public Adapter caseVariableDeclarationWithoutInitListCS(VariableDeclarationWithoutInitListCS object) {
				return createVariableDeclarationWithoutInitListCSAdapter();
			}
			@Override
			public Adapter caseLiteralExpCS(LiteralExpCS object) {
				return createLiteralExpCSAdapter();
			}
			@Override
			public Adapter caseEnumLiteralOrStaticPropertyExpCS(EnumLiteralOrStaticPropertyExpCS object) {
				return createEnumLiteralOrStaticPropertyExpCSAdapter();
			}
			@Override
			public Adapter caseCollectionLiteralExpCS(CollectionLiteralExpCS object) {
				return createCollectionLiteralExpCSAdapter();
			}
			@Override
			public Adapter caseCollectionTypeIdentifierCS(CollectionTypeIdentifierCS object) {
				return createCollectionTypeIdentifierCSAdapter();
			}
			@Override
			public Adapter caseCollectionLiteralPartsCS(CollectionLiteralPartsCS object) {
				return createCollectionLiteralPartsCSAdapter();
			}
			@Override
			public Adapter caseCollectionLiteralPartsOclExpCS(CollectionLiteralPartsOclExpCS object) {
				return createCollectionLiteralPartsOclExpCSAdapter();
			}
			@Override
			public Adapter caseCollectionRangeCS(CollectionRangeCS object) {
				return createCollectionRangeCSAdapter();
			}
			@Override
			public Adapter caseCallExpCS(CallExpCS object) {
				return createCallExpCSAdapter();
			}
			@Override
			public Adapter caseLoopExpCS(LoopExpCS object) {
				return createLoopExpCSAdapter();
			}
			@Override
			public Adapter caseIteratorExpVariableCS(IteratorExpVariableCS object) {
				return createIteratorExpVariableCSAdapter();
			}
			@Override
			public Adapter caseIteratorExpCS(IteratorExpCS object) {
				return createIteratorExpCSAdapter();
			}
			@Override
			public Adapter caseIterateExpCS(IterateExpCS object) {
				return createIterateExpCSAdapter();
			}
			@Override
			public Adapter caseFeatureCallExpCS(FeatureCallExpCS object) {
				return createFeatureCallExpCSAdapter();
			}
			@Override
			public Adapter caseNavigationCallExp(NavigationCallExp object) {
				return createNavigationCallExpAdapter();
			}
			@Override
			public Adapter caseOperationCallBaseExpCS(OperationCallBaseExpCS object) {
				return createOperationCallBaseExpCSAdapter();
			}
			@Override
			public Adapter casePropertyCallBaseExpCS(PropertyCallBaseExpCS object) {
				return createPropertyCallBaseExpCSAdapter();
			}
			@Override
			public Adapter caseImplicitFeatureCallCS(ImplicitFeatureCallCS object) {
				return createImplicitFeatureCallCSAdapter();
			}
			@Override
			public Adapter caseImplicitPropertyCallCS(ImplicitPropertyCallCS object) {
				return createImplicitPropertyCallCSAdapter();
			}
			@Override
			public Adapter caseImplicitOperationCallCS(ImplicitOperationCallCS object) {
				return createImplicitOperationCallCSAdapter();
			}
			@Override
			public Adapter casePropertyCallExpCS(PropertyCallExpCS object) {
				return createPropertyCallExpCSAdapter();
			}
			@Override
			public Adapter casePropertyCallOnSelfExpCS(PropertyCallOnSelfExpCS object) {
				return createPropertyCallOnSelfExpCSAdapter();
			}
			@Override
			public Adapter casePropertyCallExplicitPathExpCS(PropertyCallExplicitPathExpCS object) {
				return createPropertyCallExplicitPathExpCSAdapter();
			}
			@Override
			public Adapter caseOperationCallExpCS(OperationCallExpCS object) {
				return createOperationCallExpCSAdapter();
			}
			@Override
			public Adapter caseOperationCallOnSelfExpCS(OperationCallOnSelfExpCS object) {
				return createOperationCallOnSelfExpCSAdapter();
			}
			@Override
			public Adapter caseStaticOperationCallExpCS(StaticOperationCallExpCS object) {
				return createStaticOperationCallExpCSAdapter();
			}
			@Override
			public Adapter caseUnaryOperationCallExpCS(UnaryOperationCallExpCS object) {
				return createUnaryOperationCallExpCSAdapter();
			}
			@Override
			public Adapter caseLogicalNotOperationCallExpCS(LogicalNotOperationCallExpCS object) {
				return createLogicalNotOperationCallExpCSAdapter();
			}
			@Override
			public Adapter caseOperationCallWithSourceExpCS(OperationCallWithSourceExpCS object) {
				return createOperationCallWithSourceExpCSAdapter();
			}
			@Override
			public Adapter caseOperationCallBinaryExpCS(OperationCallBinaryExpCS object) {
				return createOperationCallBinaryExpCSAdapter();
			}
			@Override
			public Adapter caseAdditiveOperationCallExpCS(AdditiveOperationCallExpCS object) {
				return createAdditiveOperationCallExpCSAdapter();
			}
			@Override
			public Adapter caseMultOperationCallExpCS(MultOperationCallExpCS object) {
				return createMultOperationCallExpCSAdapter();
			}
			@Override
			public Adapter caseRelationalOperationCallExpCS(RelationalOperationCallExpCS object) {
				return createRelationalOperationCallExpCSAdapter();
			}
			@Override
			public Adapter caseEqualityOperationCallExpCS(EqualityOperationCallExpCS object) {
				return createEqualityOperationCallExpCSAdapter();
			}
			@Override
			public Adapter caseLogicalAndOperationCallExpCS(LogicalAndOperationCallExpCS object) {
				return createLogicalAndOperationCallExpCSAdapter();
			}
			@Override
			public Adapter caseLogicalOrOperationCallExpCS(LogicalOrOperationCallExpCS object) {
				return createLogicalOrOperationCallExpCSAdapter();
			}
			@Override
			public Adapter caseLogicalXorOperationCallExpCS(LogicalXorOperationCallExpCS object) {
				return createLogicalXorOperationCallExpCSAdapter();
			}
			@Override
			public Adapter caseLogicalImpliesOperationCallExpCS(LogicalImpliesOperationCallExpCS object) {
				return createLogicalImpliesOperationCallExpCSAdapter();
			}
			@Override
			public Adapter caseOperationCallWithImlicitSourceExpCS(OperationCallWithImlicitSourceExpCS object) {
				return createOperationCallWithImlicitSourceExpCSAdapter();
			}
			@Override
			public Adapter caseTupleLiteralExpCS(TupleLiteralExpCS object) {
				return createTupleLiteralExpCSAdapter();
			}
			@Override
			public Adapter casePrimitiveLiteralExpCS(PrimitiveLiteralExpCS object) {
				return createPrimitiveLiteralExpCSAdapter();
			}
			@Override
			public Adapter caseIntegerLiteralExpCS(IntegerLiteralExpCS object) {
				return createIntegerLiteralExpCSAdapter();
			}
			@Override
			public Adapter caseRealLiteralExpCS(RealLiteralExpCS object) {
				return createRealLiteralExpCSAdapter();
			}
			@Override
			public Adapter caseBooleanLiteralExpCS(BooleanLiteralExpCS object) {
				return createBooleanLiteralExpCSAdapter();
			}
			@Override
			public Adapter caseStringLiteralExpCS(StringLiteralExpCS object) {
				return createStringLiteralExpCSAdapter();
			}
			@Override
			public Adapter caseInvalidLiteralExpCS(InvalidLiteralExpCS object) {
				return createInvalidLiteralExpCSAdapter();
			}
			@Override
			public Adapter caseNullLiteralExpCS(NullLiteralExpCS object) {
				return createNullLiteralExpCSAdapter();
			}
			@Override
			public Adapter caseLetExpCS(LetExpCS object) {
				return createLetExpCSAdapter();
			}
			@Override
			public Adapter caseIfExpCS(IfExpCS object) {
				return createIfExpCSAdapter();
			}
			@Override
			public Adapter casePackageDeclarationCS(PackageDeclarationCS object) {
				return createPackageDeclarationCSAdapter();
			}
			@Override
			public Adapter casePackageDeclarationWithNamespaceCS(PackageDeclarationWithNamespaceCS object) {
				return createPackageDeclarationWithNamespaceCSAdapter();
			}
			@Override
			public Adapter casePackageDeclarationNestedNamespaceCS(PackageDeclarationNestedNamespaceCS object) {
				return createPackageDeclarationNestedNamespaceCSAdapter();
			}
			@Override
			public Adapter casePackageDeclarationWithoutNamespaceCS(PackageDeclarationWithoutNamespaceCS object) {
				return createPackageDeclarationWithoutNamespaceCSAdapter();
			}
			@Override
			public Adapter caseContextDeclarationCS(ContextDeclarationCS object) {
				return createContextDeclarationCSAdapter();
			}
			@Override
			public Adapter caseAttributeContextDeclarationCS(AttributeContextDeclarationCS object) {
				return createAttributeContextDeclarationCSAdapter();
			}
			@Override
			public Adapter caseClassifierContextDeclarationCS(ClassifierContextDeclarationCS object) {
				return createClassifierContextDeclarationCSAdapter();
			}
			@Override
			public Adapter caseOperationContextDeclarationCS(OperationContextDeclarationCS object) {
				return createOperationContextDeclarationCSAdapter();
			}
			@Override
			public Adapter caseInitOrDeriveValueCS(InitOrDeriveValueCS object) {
				return createInitOrDeriveValueCSAdapter();
			}
			@Override
			public Adapter caseInitValueCS(InitValueCS object) {
				return createInitValueCSAdapter();
			}
			@Override
			public Adapter caseDeriveValueCS(DeriveValueCS object) {
				return createDeriveValueCSAdapter();
			}
			@Override
			public Adapter caseInvariantOrDefinitionCS(InvariantOrDefinitionCS object) {
				return createInvariantOrDefinitionCSAdapter();
			}
			@Override
			public Adapter caseInvariantExpCS(InvariantExpCS object) {
				return createInvariantExpCSAdapter();
			}
			@Override
			public Adapter caseDefinitionExpCS(DefinitionExpCS object) {
				return createDefinitionExpCSAdapter();
			}
			@Override
			public Adapter caseDefinitionExpPartCS(DefinitionExpPartCS object) {
				return createDefinitionExpPartCSAdapter();
			}
			@Override
			public Adapter caseDefinitionExpPropertyCS(DefinitionExpPropertyCS object) {
				return createDefinitionExpPropertyCSAdapter();
			}
			@Override
			public Adapter caseDefinitionExpOperationCS(DefinitionExpOperationCS object) {
				return createDefinitionExpOperationCSAdapter();
			}
			@Override
			public Adapter casePrePostOrBodyDeclarationCS(PrePostOrBodyDeclarationCS object) {
				return createPrePostOrBodyDeclarationCSAdapter();
			}
			@Override
			public Adapter casePreConditionDeclarationCS(PreConditionDeclarationCS object) {
				return createPreConditionDeclarationCSAdapter();
			}
			@Override
			public Adapter casePostConditionDeclarationCS(PostConditionDeclarationCS object) {
				return createPostConditionDeclarationCSAdapter();
			}
			@Override
			public Adapter caseBodyDeclarationCS(BodyDeclarationCS object) {
				return createBodyDeclarationCSAdapter();
			}
			@Override
			public Adapter caseOperationDefinitionCS(OperationDefinitionCS object) {
				return createOperationDefinitionCSAdapter();
			}
			@Override
			public Adapter caseOperationDefinitionInContextCS(OperationDefinitionInContextCS object) {
				return createOperationDefinitionInContextCSAdapter();
			}
			@Override
			public Adapter caseOperationDefinitionInDefCS(OperationDefinitionInDefCS object) {
				return createOperationDefinitionInDefCSAdapter();
			}
			@Override
			public Adapter caseParameterCS(ParameterCS object) {
				return createParameterCSAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.OclExpressionCS <em>Expression CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.OclExpressionCS
	 * @generated
	 */
	public Adapter createOclExpressionCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.BracketExpCS <em>Bracket Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.BracketExpCS
	 * @generated
	 */
	public Adapter createBracketExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.NamedLiteralExpCS <em>Named Literal Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.NamedLiteralExpCS
	 * @generated
	 */
	public Adapter createNamedLiteralExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.PathNameCS <em>Path Name CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.PathNameCS
	 * @generated
	 */
	public Adapter createPathNameCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.SimpleNameCS <em>Simple Name CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.SimpleNameCS
	 * @generated
	 */
	public Adapter createSimpleNameCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.TypeCS <em>Type CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.TypeCS
	 * @generated
	 */
	public Adapter createTypeCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.TypePathNameCS <em>Type Path Name CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.TypePathNameCS
	 * @generated
	 */
	public Adapter createTypePathNameCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.TypePathNameSimpleCS <em>Type Path Name Simple CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.TypePathNameSimpleCS
	 * @generated
	 */
	public Adapter createTypePathNameSimpleCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.TypePathNameNestedCS <em>Type Path Name Nested CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.TypePathNameNestedCS
	 * @generated
	 */
	public Adapter createTypePathNameNestedCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.TupleTypeCS <em>Tuple Type CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.TupleTypeCS
	 * @generated
	 */
	public Adapter createTupleTypeCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.CollectionTypeLiteralExpCS <em>Collection Type Literal Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.CollectionTypeLiteralExpCS
	 * @generated
	 */
	public Adapter createCollectionTypeLiteralExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.TupleTypeLiteralExpCS <em>Tuple Type Literal Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.TupleTypeLiteralExpCS
	 * @generated
	 */
	public Adapter createTupleTypeLiteralExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.VariableDeclarationCS <em>Variable Declaration CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.VariableDeclarationCS
	 * @generated
	 */
	public Adapter createVariableDeclarationCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitCS <em>Variable Declaration With Init CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitCS
	 * @generated
	 */
	public Adapter createVariableDeclarationWithInitCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithoutInitCS <em>Variable Declaration Without Init CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithoutInitCS
	 * @generated
	 */
	public Adapter createVariableDeclarationWithoutInitCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitListCS <em>Variable Declaration With Init List CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitListCS
	 * @generated
	 */
	public Adapter createVariableDeclarationWithInitListCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithoutInitListCS <em>Variable Declaration Without Init List CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithoutInitListCS
	 * @generated
	 */
	public Adapter createVariableDeclarationWithoutInitListCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.LiteralExpCS <em>Literal Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.LiteralExpCS
	 * @generated
	 */
	public Adapter createLiteralExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.EnumLiteralOrStaticPropertyExpCS <em>Enum Literal Or Static Property Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.EnumLiteralOrStaticPropertyExpCS
	 * @generated
	 */
	public Adapter createEnumLiteralOrStaticPropertyExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.CollectionLiteralExpCS <em>Collection Literal Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.CollectionLiteralExpCS
	 * @generated
	 */
	public Adapter createCollectionLiteralExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.CollectionTypeIdentifierCS <em>Collection Type Identifier CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.CollectionTypeIdentifierCS
	 * @generated
	 */
	public Adapter createCollectionTypeIdentifierCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.CollectionLiteralPartsCS <em>Collection Literal Parts CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.CollectionLiteralPartsCS
	 * @generated
	 */
	public Adapter createCollectionLiteralPartsCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.CollectionLiteralPartsOclExpCS <em>Collection Literal Parts Ocl Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.CollectionLiteralPartsOclExpCS
	 * @generated
	 */
	public Adapter createCollectionLiteralPartsOclExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.CollectionRangeCS <em>Collection Range CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.CollectionRangeCS
	 * @generated
	 */
	public Adapter createCollectionRangeCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.CallExpCS <em>Call Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.CallExpCS
	 * @generated
	 */
	public Adapter createCallExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.LoopExpCS <em>Loop Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.LoopExpCS
	 * @generated
	 */
	public Adapter createLoopExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.IteratorExpVariableCS <em>Iterator Exp Variable CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.IteratorExpVariableCS
	 * @generated
	 */
	public Adapter createIteratorExpVariableCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.IteratorExpCS <em>Iterator Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.IteratorExpCS
	 * @generated
	 */
	public Adapter createIteratorExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.IterateExpCS <em>Iterate Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.IterateExpCS
	 * @generated
	 */
	public Adapter createIterateExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.FeatureCallExpCS <em>Feature Call Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.FeatureCallExpCS
	 * @generated
	 */
	public Adapter createFeatureCallExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.NavigationCallExp <em>Navigation Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.NavigationCallExp
	 * @generated
	 */
	public Adapter createNavigationCallExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.OperationCallBaseExpCS <em>Operation Call Base Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.OperationCallBaseExpCS
	 * @generated
	 */
	public Adapter createOperationCallBaseExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.PropertyCallBaseExpCS <em>Property Call Base Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.PropertyCallBaseExpCS
	 * @generated
	 */
	public Adapter createPropertyCallBaseExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.ImplicitFeatureCallCS <em>Implicit Feature Call CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.ImplicitFeatureCallCS
	 * @generated
	 */
	public Adapter createImplicitFeatureCallCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.PropertyCallExpCS <em>Property Call Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.PropertyCallExpCS
	 * @generated
	 */
	public Adapter createPropertyCallExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.PropertyCallOnSelfExpCS <em>Property Call On Self Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.PropertyCallOnSelfExpCS
	 * @generated
	 */
	public Adapter createPropertyCallOnSelfExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.ImplicitPropertyCallCS <em>Implicit Property Call CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.ImplicitPropertyCallCS
	 * @generated
	 */
	public Adapter createImplicitPropertyCallCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.PropertyCallExplicitPathExpCS <em>Property Call Explicit Path Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.PropertyCallExplicitPathExpCS
	 * @generated
	 */
	public Adapter createPropertyCallExplicitPathExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.OperationCallExpCS <em>Operation Call Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.OperationCallExpCS
	 * @generated
	 */
	public Adapter createOperationCallExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.OperationCallOnSelfExpCS <em>Operation Call On Self Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.OperationCallOnSelfExpCS
	 * @generated
	 */
	public Adapter createOperationCallOnSelfExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.StaticOperationCallExpCS <em>Static Operation Call Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.StaticOperationCallExpCS
	 * @generated
	 */
	public Adapter createStaticOperationCallExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.UnaryOperationCallExpCS <em>Unary Operation Call Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.UnaryOperationCallExpCS
	 * @generated
	 */
	public Adapter createUnaryOperationCallExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.LogicalNotOperationCallExpCS <em>Logical Not Operation Call Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.LogicalNotOperationCallExpCS
	 * @generated
	 */
	public Adapter createLogicalNotOperationCallExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.OperationCallWithSourceExpCS <em>Operation Call With Source Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.OperationCallWithSourceExpCS
	 * @generated
	 */
	public Adapter createOperationCallWithSourceExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.OperationCallBinaryExpCS <em>Operation Call Binary Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.OperationCallBinaryExpCS
	 * @generated
	 */
	public Adapter createOperationCallBinaryExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.AdditiveOperationCallExpCS <em>Additive Operation Call Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.AdditiveOperationCallExpCS
	 * @generated
	 */
	public Adapter createAdditiveOperationCallExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.MultOperationCallExpCS <em>Mult Operation Call Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.MultOperationCallExpCS
	 * @generated
	 */
	public Adapter createMultOperationCallExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.RelationalOperationCallExpCS <em>Relational Operation Call Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.RelationalOperationCallExpCS
	 * @generated
	 */
	public Adapter createRelationalOperationCallExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.EqualityOperationCallExpCS <em>Equality Operation Call Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.EqualityOperationCallExpCS
	 * @generated
	 */
	public Adapter createEqualityOperationCallExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.LogicalAndOperationCallExpCS <em>Logical And Operation Call Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.LogicalAndOperationCallExpCS
	 * @generated
	 */
	public Adapter createLogicalAndOperationCallExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.LogicalOrOperationCallExpCS <em>Logical Or Operation Call Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.LogicalOrOperationCallExpCS
	 * @generated
	 */
	public Adapter createLogicalOrOperationCallExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.LogicalXorOperationCallExpCS <em>Logical Xor Operation Call Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.LogicalXorOperationCallExpCS
	 * @generated
	 */
	public Adapter createLogicalXorOperationCallExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.LogicalImpliesOperationCallExpCS <em>Logical Implies Operation Call Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.LogicalImpliesOperationCallExpCS
	 * @generated
	 */
	public Adapter createLogicalImpliesOperationCallExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.OperationCallWithImlicitSourceExpCS <em>Operation Call With Imlicit Source Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.OperationCallWithImlicitSourceExpCS
	 * @generated
	 */
	public Adapter createOperationCallWithImlicitSourceExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.ImplicitOperationCallCS <em>Implicit Operation Call CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.ImplicitOperationCallCS
	 * @generated
	 */
	public Adapter createImplicitOperationCallCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.TupleLiteralExpCS <em>Tuple Literal Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.TupleLiteralExpCS
	 * @generated
	 */
	public Adapter createTupleLiteralExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.PrimitiveLiteralExpCS <em>Primitive Literal Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.PrimitiveLiteralExpCS
	 * @generated
	 */
	public Adapter createPrimitiveLiteralExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.IntegerLiteralExpCS <em>Integer Literal Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.IntegerLiteralExpCS
	 * @generated
	 */
	public Adapter createIntegerLiteralExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.RealLiteralExpCS <em>Real Literal Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.RealLiteralExpCS
	 * @generated
	 */
	public Adapter createRealLiteralExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.BooleanLiteralExpCS <em>Boolean Literal Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.BooleanLiteralExpCS
	 * @generated
	 */
	public Adapter createBooleanLiteralExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.StringLiteralExpCS <em>String Literal Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.StringLiteralExpCS
	 * @generated
	 */
	public Adapter createStringLiteralExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.InvalidLiteralExpCS <em>Invalid Literal Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.InvalidLiteralExpCS
	 * @generated
	 */
	public Adapter createInvalidLiteralExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.NullLiteralExpCS <em>Null Literal Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.NullLiteralExpCS
	 * @generated
	 */
	public Adapter createNullLiteralExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.LetExpCS <em>Let Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.LetExpCS
	 * @generated
	 */
	public Adapter createLetExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.IfExpCS <em>If Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.IfExpCS
	 * @generated
	 */
	public Adapter createIfExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.PackageDeclarationCS <em>Package Declaration CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.PackageDeclarationCS
	 * @generated
	 */
	public Adapter createPackageDeclarationCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.PackageDeclarationWithNamespaceCS <em>Package Declaration With Namespace CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.PackageDeclarationWithNamespaceCS
	 * @generated
	 */
	public Adapter createPackageDeclarationWithNamespaceCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.PackageDeclarationNestedNamespaceCS <em>Package Declaration Nested Namespace CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.PackageDeclarationNestedNamespaceCS
	 * @generated
	 */
	public Adapter createPackageDeclarationNestedNamespaceCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.PackageDeclarationWithoutNamespaceCS <em>Package Declaration Without Namespace CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.PackageDeclarationWithoutNamespaceCS
	 * @generated
	 */
	public Adapter createPackageDeclarationWithoutNamespaceCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.ContextDeclarationCS <em>Context Declaration CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.ContextDeclarationCS
	 * @generated
	 */
	public Adapter createContextDeclarationCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.AttributeContextDeclarationCS <em>Attribute Context Declaration CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.AttributeContextDeclarationCS
	 * @generated
	 */
	public Adapter createAttributeContextDeclarationCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.ClassifierContextDeclarationCS <em>Classifier Context Declaration CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.ClassifierContextDeclarationCS
	 * @generated
	 */
	public Adapter createClassifierContextDeclarationCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.OperationContextDeclarationCS <em>Operation Context Declaration CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.OperationContextDeclarationCS
	 * @generated
	 */
	public Adapter createOperationContextDeclarationCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.InitOrDeriveValueCS <em>Init Or Derive Value CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.InitOrDeriveValueCS
	 * @generated
	 */
	public Adapter createInitOrDeriveValueCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.InitValueCS <em>Init Value CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.InitValueCS
	 * @generated
	 */
	public Adapter createInitValueCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.DeriveValueCS <em>Derive Value CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.DeriveValueCS
	 * @generated
	 */
	public Adapter createDeriveValueCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.InvariantOrDefinitionCS <em>Invariant Or Definition CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.InvariantOrDefinitionCS
	 * @generated
	 */
	public Adapter createInvariantOrDefinitionCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.InvariantExpCS <em>Invariant Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.InvariantExpCS
	 * @generated
	 */
	public Adapter createInvariantExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.DefinitionExpCS <em>Definition Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.DefinitionExpCS
	 * @generated
	 */
	public Adapter createDefinitionExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.DefinitionExpPartCS <em>Definition Exp Part CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.DefinitionExpPartCS
	 * @generated
	 */
	public Adapter createDefinitionExpPartCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.DefinitionExpPropertyCS <em>Definition Exp Property CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.DefinitionExpPropertyCS
	 * @generated
	 */
	public Adapter createDefinitionExpPropertyCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.DefinitionExpOperationCS <em>Definition Exp Operation CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.DefinitionExpOperationCS
	 * @generated
	 */
	public Adapter createDefinitionExpOperationCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.PrePostOrBodyDeclarationCS <em>Pre Post Or Body Declaration CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.PrePostOrBodyDeclarationCS
	 * @generated
	 */
	public Adapter createPrePostOrBodyDeclarationCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.PreConditionDeclarationCS <em>Pre Condition Declaration CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.PreConditionDeclarationCS
	 * @generated
	 */
	public Adapter createPreConditionDeclarationCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.PostConditionDeclarationCS <em>Post Condition Declaration CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.PostConditionDeclarationCS
	 * @generated
	 */
	public Adapter createPostConditionDeclarationCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.BodyDeclarationCS <em>Body Declaration CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.BodyDeclarationCS
	 * @generated
	 */
	public Adapter createBodyDeclarationCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.OperationDefinitionCS <em>Operation Definition CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.OperationDefinitionCS
	 * @generated
	 */
	public Adapter createOperationDefinitionCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.OperationDefinitionInContextCS <em>Operation Definition In Context CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.OperationDefinitionInContextCS
	 * @generated
	 */
	public Adapter createOperationDefinitionInContextCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.OperationDefinitionInDefCS <em>Operation Definition In Def CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.OperationDefinitionInDefCS
	 * @generated
	 */
	public Adapter createOperationDefinitionInDefCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.language.ocl.ParameterCS <em>Parameter CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.language.ocl.ParameterCS
	 * @generated
	 */
	public Adapter createParameterCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //OclAdapterFactory
