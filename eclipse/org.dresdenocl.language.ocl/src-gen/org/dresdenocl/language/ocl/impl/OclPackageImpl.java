/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.language.ocl.impl;

import org.dresdenocl.datatypes.DatatypesPackage;
import org.dresdenocl.language.ocl.AdditiveOperationCallExpCS;
import org.dresdenocl.language.ocl.AttributeContextDeclarationCS;
import org.dresdenocl.language.ocl.BodyDeclarationCS;
import org.dresdenocl.language.ocl.BooleanLiteralExpCS;
import org.dresdenocl.language.ocl.BracketExpCS;
import org.dresdenocl.language.ocl.CallExpCS;
import org.dresdenocl.language.ocl.ClassifierContextDeclarationCS;
import org.dresdenocl.language.ocl.CollectionLiteralExpCS;
import org.dresdenocl.language.ocl.CollectionLiteralPartsCS;
import org.dresdenocl.language.ocl.CollectionLiteralPartsOclExpCS;
import org.dresdenocl.language.ocl.CollectionRangeCS;
import org.dresdenocl.language.ocl.CollectionTypeIdentifierCS;
import org.dresdenocl.language.ocl.CollectionTypeLiteralExpCS;
import org.dresdenocl.language.ocl.ContextDeclarationCS;
import org.dresdenocl.language.ocl.DefinitionExpCS;
import org.dresdenocl.language.ocl.DefinitionExpOperationCS;
import org.dresdenocl.language.ocl.DefinitionExpPartCS;
import org.dresdenocl.language.ocl.DefinitionExpPropertyCS;
import org.dresdenocl.language.ocl.DeriveValueCS;
import org.dresdenocl.language.ocl.EqualityOperationCallExpCS;
import org.dresdenocl.language.ocl.FeatureCallExpCS;
import org.dresdenocl.language.ocl.IfExpCS;
import org.dresdenocl.language.ocl.ImplicitFeatureCallCS;
import org.dresdenocl.language.ocl.ImplicitOperationCallCS;
import org.dresdenocl.language.ocl.ImplicitPropertyCallCS;
import org.dresdenocl.language.ocl.InitOrDeriveValueCS;
import org.dresdenocl.language.ocl.InitValueCS;
import org.dresdenocl.language.ocl.IntegerLiteralExpCS;
import org.dresdenocl.language.ocl.InvalidLiteralExpCS;
import org.dresdenocl.language.ocl.InvariantExpCS;
import org.dresdenocl.language.ocl.InvariantOrDefinitionCS;
import org.dresdenocl.language.ocl.IterateExpCS;
import org.dresdenocl.language.ocl.IteratorExpCS;
import org.dresdenocl.language.ocl.IteratorExpVariableCS;
import org.dresdenocl.language.ocl.LetExpCS;
import org.dresdenocl.language.ocl.LiteralExpCS;
import org.dresdenocl.language.ocl.LogicalAndOperationCallExpCS;
import org.dresdenocl.language.ocl.LogicalImpliesOperationCallExpCS;
import org.dresdenocl.language.ocl.LogicalNotOperationCallExpCS;
import org.dresdenocl.language.ocl.LogicalOrOperationCallExpCS;
import org.dresdenocl.language.ocl.LogicalXorOperationCallExpCS;
import org.dresdenocl.language.ocl.LoopExpCS;
import org.dresdenocl.language.ocl.ModelElementCS;
import org.dresdenocl.language.ocl.MultOperationCallExpCS;
import org.dresdenocl.language.ocl.NamedElementCS;
import org.dresdenocl.language.ocl.NavigationCallExp;
import org.dresdenocl.language.ocl.NullLiteralExpCS;
import org.dresdenocl.language.ocl.OclExpressionCS;
import org.dresdenocl.language.ocl.OclFactory;
import org.dresdenocl.language.ocl.OclPackage;
import org.dresdenocl.language.ocl.OperationCallBaseExpCS;
import org.dresdenocl.language.ocl.OperationCallBinaryExpCS;
import org.dresdenocl.language.ocl.OperationCallExpCS;
import org.dresdenocl.language.ocl.OperationCallOnSelfExpCS;
import org.dresdenocl.language.ocl.OperationCallWithImlicitSourceExpCS;
import org.dresdenocl.language.ocl.OperationCallWithSourceExpCS;
import org.dresdenocl.language.ocl.OperationContextDeclarationCS;
import org.dresdenocl.language.ocl.OperationDefinitionCS;
import org.dresdenocl.language.ocl.OperationDefinitionInContextCS;
import org.dresdenocl.language.ocl.OperationDefinitionInDefCS;
import org.dresdenocl.language.ocl.PackageDeclarationCS;
import org.dresdenocl.language.ocl.PackageDeclarationNestedNamespaceCS;
import org.dresdenocl.language.ocl.PackageDeclarationWithNamespaceCS;
import org.dresdenocl.language.ocl.PackageDeclarationWithoutNamespaceCS;
import org.dresdenocl.language.ocl.ParameterCS;
import org.dresdenocl.language.ocl.PathNameCS;
import org.dresdenocl.language.ocl.PathNamePathCS;
import org.dresdenocl.language.ocl.PathNameSimpleCS;
import org.dresdenocl.language.ocl.PostConditionDeclarationCS;
import org.dresdenocl.language.ocl.PreConditionDeclarationCS;
import org.dresdenocl.language.ocl.PrePostOrBodyDeclarationCS;
import org.dresdenocl.language.ocl.PrimitiveLiteralExpCS;
import org.dresdenocl.language.ocl.PropertyCallBaseExpCS;
import org.dresdenocl.language.ocl.PropertyCallExpCS;
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
import org.dresdenocl.language.ocl.TypeCS;
import org.dresdenocl.language.ocl.TypeModelElementCS;
import org.dresdenocl.language.ocl.UnaryOperationCallExpCS;
import org.dresdenocl.language.ocl.UnreservedSimpleNameCS;
import org.dresdenocl.language.ocl.VariableDeclarationCS;
import org.dresdenocl.language.ocl.VariableDeclarationWithInitCS;
import org.dresdenocl.language.ocl.VariableDeclarationWithInitListCS;
import org.dresdenocl.language.ocl.VariableDeclarationWithoutInitCS;
import org.dresdenocl.language.ocl.VariableDeclarationWithoutInitListCS;
import org.dresdenocl.pivotmodel.PivotModelPackage;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.emftext.commons.layout.LayoutPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class OclPackageImpl extends EPackageImpl implements OclPackage {
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass oclExpressionCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass bracketExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass modelElementCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass pathNameCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass pathNameSimpleCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass pathNamePathCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass unreservedSimpleNameCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass namedElementCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass simpleNameCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass typeCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass typeModelElementCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass tupleTypeCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass collectionTypeLiteralExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass tupleTypeLiteralExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass variableDeclarationCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass variableDeclarationWithInitCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass variableDeclarationWithoutInitCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass variableDeclarationWithInitListCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass variableDeclarationWithoutInitListCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass literalExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass collectionLiteralExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass collectionTypeIdentifierCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass collectionLiteralPartsCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass collectionLiteralPartsOclExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass collectionRangeCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass callExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass loopExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass iteratorExpVariableCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass iteratorExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass iterateExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass featureCallExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass navigationCallExpEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass operationCallBaseExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass propertyCallBaseExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass implicitFeatureCallCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass propertyCallExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass propertyCallOnSelfExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass implicitPropertyCallCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass propertyCallExplicitPathExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass operationCallExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass operationCallOnSelfExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass staticOperationCallExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass unaryOperationCallExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass logicalNotOperationCallExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass operationCallWithSourceExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass operationCallBinaryExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass additiveOperationCallExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass multOperationCallExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass relationalOperationCallExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass equalityOperationCallExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass logicalAndOperationCallExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass logicalOrOperationCallExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass logicalXorOperationCallExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass logicalImpliesOperationCallExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass operationCallWithImlicitSourceExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass implicitOperationCallCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass tupleLiteralExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass primitiveLiteralExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass integerLiteralExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass realLiteralExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass booleanLiteralExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass stringLiteralExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass invalidLiteralExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass nullLiteralExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass letExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass ifExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass packageDeclarationCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass packageDeclarationWithNamespaceCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass packageDeclarationNestedNamespaceCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass packageDeclarationWithoutNamespaceCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass contextDeclarationCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass attributeContextDeclarationCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass classifierContextDeclarationCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass operationContextDeclarationCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass initOrDeriveValueCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass initValueCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass deriveValueCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass invariantOrDefinitionCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass invariantExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass definitionExpCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass definitionExpPartCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass definitionExpPropertyCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass definitionExpOperationCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass prePostOrBodyDeclarationCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass preConditionDeclarationCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass postConditionDeclarationCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass bodyDeclarationCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass operationDefinitionCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass operationDefinitionInContextCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass operationDefinitionInDefCSEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass parameterCSEClass = null;

	/**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see org.dresdenocl.language.ocl.OclPackage#eNS_URI
   * @see #init()
   * @generated
   */
	private OclPackageImpl() {
    super(eNS_URI, OclFactory.eINSTANCE);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private static boolean isInited = false;

	/**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   * 
   * <p>This method is used to initialize {@link OclPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
	public static OclPackage init() {
    if (isInited) return (OclPackage)EPackage.Registry.INSTANCE.getEPackage(OclPackage.eNS_URI);

    // Obtain or create and register package
    OclPackageImpl theOclPackage = (OclPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof OclPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new OclPackageImpl());

    isInited = true;

    // Initialize simple dependencies
    LayoutPackage.eINSTANCE.eClass();
    PivotModelPackage.eINSTANCE.eClass();
    DatatypesPackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theOclPackage.createPackageContents();

    // Initialize created meta-data
    theOclPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theOclPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(OclPackage.eNS_URI, theOclPackage);
    return theOclPackage;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getOclExpressionCS() {
    return oclExpressionCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getBracketExpCS() {
    return bracketExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getBracketExpCS_OclExpression() {
    return (EReference)bracketExpCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getModelElementCS() {
    return modelElementCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getModelElementCS_PathName() {
    return (EReference)modelElementCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getPathNameCS() {
    return pathNameCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getPathNameSimpleCS() {
    return pathNameSimpleCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getPathNameSimpleCS_NamedElement() {
    return (EReference)pathNameSimpleCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getPathNamePathCS() {
    return pathNamePathCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getPathNamePathCS_PathName() {
    return (EReference)pathNamePathCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getUnreservedSimpleNameCS() {
    return unreservedSimpleNameCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getNamedElementCS() {
    return namedElementCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getNamedElementCS_NamedElement() {
    return (EReference)namedElementCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getSimpleNameCS() {
    return simpleNameCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getSimpleNameCS_SimpleName() {
    return (EAttribute)simpleNameCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getTypeCS() {
    return typeCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getTypeModelElementCS() {
    return typeModelElementCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getTypeModelElementCS_ModelElement() {
    return (EReference)typeModelElementCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getTupleTypeCS() {
    return tupleTypeCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getTupleTypeCS_VariableDeclarationList() {
    return (EReference)tupleTypeCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getCollectionTypeLiteralExpCS() {
    return collectionTypeLiteralExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getCollectionTypeLiteralExpCS_CollectionType() {
    return (EReference)collectionTypeLiteralExpCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getTupleTypeLiteralExpCS() {
    return tupleTypeLiteralExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getTupleTypeLiteralExpCS_TupleType() {
    return (EReference)tupleTypeLiteralExpCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getVariableDeclarationCS() {
    return variableDeclarationCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getVariableDeclarationCS_VariableName() {
    return (EReference)variableDeclarationCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getVariableDeclarationWithInitCS() {
    return variableDeclarationWithInitCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getVariableDeclarationWithInitCS_TypeName() {
    return (EReference)variableDeclarationWithInitCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getVariableDeclarationWithInitCS_Initialization() {
    return (EReference)variableDeclarationWithInitCSEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getVariableDeclarationWithInitCS_Equal() {
    return (EAttribute)variableDeclarationWithInitCSEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getVariableDeclarationWithoutInitCS() {
    return variableDeclarationWithoutInitCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getVariableDeclarationWithoutInitCS_TypeName() {
    return (EReference)variableDeclarationWithoutInitCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getVariableDeclarationWithInitListCS() {
    return variableDeclarationWithInitListCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getVariableDeclarationWithInitListCS_VariableDeclarations() {
    return (EReference)variableDeclarationWithInitListCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getVariableDeclarationWithoutInitListCS() {
    return variableDeclarationWithoutInitListCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getVariableDeclarationWithoutInitListCS_VariableDeclarations() {
    return (EReference)variableDeclarationWithoutInitListCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getLiteralExpCS() {
    return literalExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getCollectionLiteralExpCS() {
    return collectionLiteralExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getCollectionLiteralExpCS_CollectionType() {
    return (EReference)collectionLiteralExpCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getCollectionLiteralExpCS_CollectionLiteralParts() {
    return (EReference)collectionLiteralExpCSEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getCollectionTypeIdentifierCS() {
    return collectionTypeIdentifierCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getCollectionTypeIdentifierCS_TypeName() {
    return (EReference)collectionTypeIdentifierCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getCollectionTypeIdentifierCS_GenericType() {
    return (EReference)collectionTypeIdentifierCSEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getCollectionLiteralPartsCS() {
    return collectionLiteralPartsCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getCollectionLiteralPartsOclExpCS() {
    return collectionLiteralPartsOclExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getCollectionLiteralPartsOclExpCS_OclExpression() {
    return (EReference)collectionLiteralPartsOclExpCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getCollectionRangeCS() {
    return collectionRangeCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getCollectionRangeCS_From() {
    return (EReference)collectionRangeCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getCollectionRangeCS_To() {
    return (EReference)collectionRangeCSEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getCallExpCS() {
    return callExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getLoopExpCS() {
    return loopExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getIteratorExpVariableCS() {
    return iteratorExpVariableCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getIteratorExpVariableCS_VariableName() {
    return (EReference)iteratorExpVariableCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getIteratorExpVariableCS_TypeName() {
    return (EReference)iteratorExpVariableCSEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getIteratorExpCS() {
    return iteratorExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getIteratorExpCS_IteratorName() {
    return (EAttribute)iteratorExpCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getIteratorExpCS_IteratorVariables() {
    return (EReference)iteratorExpCSEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getIteratorExpCS_BodyExpression() {
    return (EReference)iteratorExpCSEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getIterateExpCS() {
    return iterateExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getIterateExpCS_IteratorVariable() {
    return (EReference)iterateExpCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getIterateExpCS_ResultVariable() {
    return (EReference)iterateExpCSEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getIterateExpCS_BodyExpression() {
    return (EReference)iterateExpCSEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getFeatureCallExpCS() {
    return featureCallExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getNavigationCallExp() {
    return navigationCallExpEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getNavigationCallExp_Source() {
    return (EReference)navigationCallExpEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getNavigationCallExp_NavigationOperator() {
    return (EAttribute)navigationCallExpEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getNavigationCallExp_FeatureCalls() {
    return (EReference)navigationCallExpEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getOperationCallBaseExpCS() {
    return operationCallBaseExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getOperationCallBaseExpCS_OperationName() {
    return (EReference)operationCallBaseExpCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getOperationCallBaseExpCS_Arguments() {
    return (EReference)operationCallBaseExpCSEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getOperationCallBaseExpCS_IsMarkedPre() {
    return (EAttribute)operationCallBaseExpCSEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getPropertyCallBaseExpCS() {
    return propertyCallBaseExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getPropertyCallBaseExpCS_Property() {
    return (EReference)propertyCallBaseExpCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getPropertyCallBaseExpCS_IsMarkedPre() {
    return (EAttribute)propertyCallBaseExpCSEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getImplicitFeatureCallCS() {
    return implicitFeatureCallCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getPropertyCallExpCS() {
    return propertyCallExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getPropertyCallOnSelfExpCS() {
    return propertyCallOnSelfExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getImplicitPropertyCallCS() {
    return implicitPropertyCallCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getPropertyCallExplicitPathExpCS() {
    return propertyCallExplicitPathExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getPropertyCallExplicitPathExpCS_Source() {
    return (EReference)propertyCallExplicitPathExpCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getPropertyCallExplicitPathExpCS_PropertyPath() {
    return (EReference)propertyCallExplicitPathExpCSEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getPropertyCallExplicitPathExpCS_PropertyName() {
    return (EReference)propertyCallExplicitPathExpCSEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getPropertyCallExplicitPathExpCS_IsMarkedPre() {
    return (EAttribute)propertyCallExplicitPathExpCSEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getOperationCallExpCS() {
    return operationCallExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getOperationCallOnSelfExpCS() {
    return operationCallOnSelfExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getStaticOperationCallExpCS() {
    return staticOperationCallExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getStaticOperationCallExpCS_TypeName() {
    return (EReference)staticOperationCallExpCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getStaticOperationCallExpCS_OperationName() {
    return (EReference)staticOperationCallExpCSEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getStaticOperationCallExpCS_Arguments() {
    return (EReference)staticOperationCallExpCSEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getUnaryOperationCallExpCS() {
    return unaryOperationCallExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getUnaryOperationCallExpCS_OperationName() {
    return (EAttribute)unaryOperationCallExpCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getUnaryOperationCallExpCS_Target() {
    return (EReference)unaryOperationCallExpCSEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getLogicalNotOperationCallExpCS() {
    return logicalNotOperationCallExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getLogicalNotOperationCallExpCS_OperationName() {
    return (EAttribute)logicalNotOperationCallExpCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getLogicalNotOperationCallExpCS_Target() {
    return (EReference)logicalNotOperationCallExpCSEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getOperationCallWithSourceExpCS() {
    return operationCallWithSourceExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getOperationCallWithSourceExpCS_Source() {
    return (EReference)operationCallWithSourceExpCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getOperationCallWithSourceExpCS_IsMarkedPre() {
    return (EAttribute)operationCallWithSourceExpCSEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getOperationCallBinaryExpCS() {
    return operationCallBinaryExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getOperationCallBinaryExpCS_OperationName() {
    return (EAttribute)operationCallBinaryExpCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getOperationCallBinaryExpCS_Target() {
    return (EReference)operationCallBinaryExpCSEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getAdditiveOperationCallExpCS() {
    return additiveOperationCallExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getMultOperationCallExpCS() {
    return multOperationCallExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getRelationalOperationCallExpCS() {
    return relationalOperationCallExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getEqualityOperationCallExpCS() {
    return equalityOperationCallExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getLogicalAndOperationCallExpCS() {
    return logicalAndOperationCallExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getLogicalOrOperationCallExpCS() {
    return logicalOrOperationCallExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getLogicalXorOperationCallExpCS() {
    return logicalXorOperationCallExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getLogicalImpliesOperationCallExpCS() {
    return logicalImpliesOperationCallExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getOperationCallWithImlicitSourceExpCS() {
    return operationCallWithImlicitSourceExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getImplicitOperationCallCS() {
    return implicitOperationCallCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getTupleLiteralExpCS() {
    return tupleLiteralExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getTupleLiteralExpCS_VariableDeclarations() {
    return (EReference)tupleLiteralExpCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getPrimitiveLiteralExpCS() {
    return primitiveLiteralExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getIntegerLiteralExpCS() {
    return integerLiteralExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getIntegerLiteralExpCS_IntegerLiteral() {
    return (EAttribute)integerLiteralExpCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getRealLiteralExpCS() {
    return realLiteralExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getRealLiteralExpCS_IntValue() {
    return (EAttribute)realLiteralExpCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getRealLiteralExpCS_RealValue() {
    return (EAttribute)realLiteralExpCSEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getRealLiteralExpCS_NavigationOperator() {
    return (EAttribute)realLiteralExpCSEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getBooleanLiteralExpCS() {
    return booleanLiteralExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getBooleanLiteralExpCS_BooleanLiteral() {
    return (EAttribute)booleanLiteralExpCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getStringLiteralExpCS() {
    return stringLiteralExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getStringLiteralExpCS_StringLiteral() {
    return (EAttribute)stringLiteralExpCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getInvalidLiteralExpCS() {
    return invalidLiteralExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getNullLiteralExpCS() {
    return nullLiteralExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getLetExpCS() {
    return letExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getLetExpCS_VariableDeclarations() {
    return (EReference)letExpCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getLetExpCS_OclExpression() {
    return (EReference)letExpCSEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getIfExpCS() {
    return ifExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getIfExpCS_Condition() {
    return (EReference)ifExpCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getIfExpCS_ThenBranch() {
    return (EReference)ifExpCSEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getIfExpCS_ElseBranch() {
    return (EReference)ifExpCSEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getPackageDeclarationCS() {
    return packageDeclarationCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getPackageDeclarationCS_ContextDeclarations() {
    return (EReference)packageDeclarationCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getPackageDeclarationCS_LayoutInformation() {
    return (EReference)packageDeclarationCSEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getPackageDeclarationWithNamespaceCS() {
    return packageDeclarationWithNamespaceCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getPackageDeclarationWithNamespaceCS_NestedNamespace() {
    return (EReference)packageDeclarationWithNamespaceCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getPackageDeclarationNestedNamespaceCS() {
    return packageDeclarationNestedNamespaceCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getPackageDeclarationNestedNamespaceCS_Namespace() {
    return (EReference)packageDeclarationNestedNamespaceCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getPackageDeclarationNestedNamespaceCS_NestedNamespace() {
    return (EReference)packageDeclarationNestedNamespaceCSEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getPackageDeclarationNestedNamespaceCS_LayoutInformation() {
    return (EReference)packageDeclarationNestedNamespaceCSEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getPackageDeclarationWithoutNamespaceCS() {
    return packageDeclarationWithoutNamespaceCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getContextDeclarationCS() {
    return contextDeclarationCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getAttributeContextDeclarationCS() {
    return attributeContextDeclarationCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getAttributeContextDeclarationCS_TypeName() {
    return (EReference)attributeContextDeclarationCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getAttributeContextDeclarationCS_Type() {
    return (EReference)attributeContextDeclarationCSEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getAttributeContextDeclarationCS_InitOrDeriveValue() {
    return (EReference)attributeContextDeclarationCSEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getClassifierContextDeclarationCS() {
    return classifierContextDeclarationCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getClassifierContextDeclarationCS_TypeName() {
    return (EReference)classifierContextDeclarationCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getClassifierContextDeclarationCS_InvariantsAndDefinitions() {
    return (EReference)classifierContextDeclarationCSEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getOperationContextDeclarationCS() {
    return operationContextDeclarationCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getOperationContextDeclarationCS_Operation() {
    return (EReference)operationContextDeclarationCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getOperationContextDeclarationCS_PrePostOrBodyDeclarations() {
    return (EReference)operationContextDeclarationCSEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getInitOrDeriveValueCS() {
    return initOrDeriveValueCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getInitOrDeriveValueCS_OclExpression() {
    return (EReference)initOrDeriveValueCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getInitValueCS() {
    return initValueCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getDeriveValueCS() {
    return deriveValueCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getInvariantOrDefinitionCS() {
    return invariantOrDefinitionCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getInvariantExpCS() {
    return invariantExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getInvariantExpCS_Name() {
    return (EReference)invariantExpCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getInvariantExpCS_OclExpression() {
    return (EReference)invariantExpCSEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getDefinitionExpCS() {
    return definitionExpCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getDefinitionExpCS_Static() {
    return (EAttribute)definitionExpCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getDefinitionExpCS_DefinitionExpPart() {
    return (EReference)definitionExpCSEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getDefinitionExpPartCS() {
    return definitionExpPartCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getDefinitionExpPropertyCS() {
    return definitionExpPropertyCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getDefinitionExpPropertyCS_VariableDeclaration() {
    return (EReference)definitionExpPropertyCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getDefinitionExpOperationCS() {
    return definitionExpOperationCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getDefinitionExpOperationCS_Operation() {
    return (EReference)definitionExpOperationCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getDefinitionExpOperationCS_Equal() {
    return (EAttribute)definitionExpOperationCSEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getDefinitionExpOperationCS_OclExpression() {
    return (EReference)definitionExpOperationCSEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getPrePostOrBodyDeclarationCS() {
    return prePostOrBodyDeclarationCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getPrePostOrBodyDeclarationCS_Name() {
    return (EReference)prePostOrBodyDeclarationCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getPrePostOrBodyDeclarationCS_OclExpression() {
    return (EReference)prePostOrBodyDeclarationCSEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getPreConditionDeclarationCS() {
    return preConditionDeclarationCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getPostConditionDeclarationCS() {
    return postConditionDeclarationCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getBodyDeclarationCS() {
    return bodyDeclarationCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getOperationDefinitionCS() {
    return operationDefinitionCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getOperationDefinitionCS_Operation() {
    return (EReference)operationDefinitionCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getOperationDefinitionCS_Parameters() {
    return (EReference)operationDefinitionCSEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getOperationDefinitionCS_ReturnType() {
    return (EReference)operationDefinitionCSEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getOperationDefinitionInContextCS() {
    return operationDefinitionInContextCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getOperationDefinitionInContextCS_TypeName() {
    return (EReference)operationDefinitionInContextCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getOperationDefinitionInDefCS() {
    return operationDefinitionInDefCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getParameterCS() {
    return parameterCSEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getParameterCS_Parameter() {
    return (EReference)parameterCSEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getParameterCS_ParameterType() {
    return (EReference)parameterCSEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public OclFactory getOclFactory() {
    return (OclFactory)getEFactoryInstance();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private boolean isCreated = false;

	/**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void createPackageContents() {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    oclExpressionCSEClass = createEClass(OCL_EXPRESSION_CS);

    bracketExpCSEClass = createEClass(BRACKET_EXP_CS);
    createEReference(bracketExpCSEClass, BRACKET_EXP_CS__OCL_EXPRESSION);

    modelElementCSEClass = createEClass(MODEL_ELEMENT_CS);
    createEReference(modelElementCSEClass, MODEL_ELEMENT_CS__PATH_NAME);

    pathNameCSEClass = createEClass(PATH_NAME_CS);

    pathNameSimpleCSEClass = createEClass(PATH_NAME_SIMPLE_CS);
    createEReference(pathNameSimpleCSEClass, PATH_NAME_SIMPLE_CS__NAMED_ELEMENT);

    pathNamePathCSEClass = createEClass(PATH_NAME_PATH_CS);
    createEReference(pathNamePathCSEClass, PATH_NAME_PATH_CS__PATH_NAME);

    unreservedSimpleNameCSEClass = createEClass(UNRESERVED_SIMPLE_NAME_CS);

    namedElementCSEClass = createEClass(NAMED_ELEMENT_CS);
    createEReference(namedElementCSEClass, NAMED_ELEMENT_CS__NAMED_ELEMENT);

    simpleNameCSEClass = createEClass(SIMPLE_NAME_CS);
    createEAttribute(simpleNameCSEClass, SIMPLE_NAME_CS__SIMPLE_NAME);

    typeCSEClass = createEClass(TYPE_CS);

    typeModelElementCSEClass = createEClass(TYPE_MODEL_ELEMENT_CS);
    createEReference(typeModelElementCSEClass, TYPE_MODEL_ELEMENT_CS__MODEL_ELEMENT);

    tupleTypeCSEClass = createEClass(TUPLE_TYPE_CS);
    createEReference(tupleTypeCSEClass, TUPLE_TYPE_CS__VARIABLE_DECLARATION_LIST);

    collectionTypeLiteralExpCSEClass = createEClass(COLLECTION_TYPE_LITERAL_EXP_CS);
    createEReference(collectionTypeLiteralExpCSEClass, COLLECTION_TYPE_LITERAL_EXP_CS__COLLECTION_TYPE);

    tupleTypeLiteralExpCSEClass = createEClass(TUPLE_TYPE_LITERAL_EXP_CS);
    createEReference(tupleTypeLiteralExpCSEClass, TUPLE_TYPE_LITERAL_EXP_CS__TUPLE_TYPE);

    variableDeclarationCSEClass = createEClass(VARIABLE_DECLARATION_CS);
    createEReference(variableDeclarationCSEClass, VARIABLE_DECLARATION_CS__VARIABLE_NAME);

    variableDeclarationWithInitCSEClass = createEClass(VARIABLE_DECLARATION_WITH_INIT_CS);
    createEReference(variableDeclarationWithInitCSEClass, VARIABLE_DECLARATION_WITH_INIT_CS__TYPE_NAME);
    createEReference(variableDeclarationWithInitCSEClass, VARIABLE_DECLARATION_WITH_INIT_CS__INITIALIZATION);
    createEAttribute(variableDeclarationWithInitCSEClass, VARIABLE_DECLARATION_WITH_INIT_CS__EQUAL);

    variableDeclarationWithoutInitCSEClass = createEClass(VARIABLE_DECLARATION_WITHOUT_INIT_CS);
    createEReference(variableDeclarationWithoutInitCSEClass, VARIABLE_DECLARATION_WITHOUT_INIT_CS__TYPE_NAME);

    variableDeclarationWithInitListCSEClass = createEClass(VARIABLE_DECLARATION_WITH_INIT_LIST_CS);
    createEReference(variableDeclarationWithInitListCSEClass, VARIABLE_DECLARATION_WITH_INIT_LIST_CS__VARIABLE_DECLARATIONS);

    variableDeclarationWithoutInitListCSEClass = createEClass(VARIABLE_DECLARATION_WITHOUT_INIT_LIST_CS);
    createEReference(variableDeclarationWithoutInitListCSEClass, VARIABLE_DECLARATION_WITHOUT_INIT_LIST_CS__VARIABLE_DECLARATIONS);

    literalExpCSEClass = createEClass(LITERAL_EXP_CS);

    collectionLiteralExpCSEClass = createEClass(COLLECTION_LITERAL_EXP_CS);
    createEReference(collectionLiteralExpCSEClass, COLLECTION_LITERAL_EXP_CS__COLLECTION_TYPE);
    createEReference(collectionLiteralExpCSEClass, COLLECTION_LITERAL_EXP_CS__COLLECTION_LITERAL_PARTS);

    collectionTypeIdentifierCSEClass = createEClass(COLLECTION_TYPE_IDENTIFIER_CS);
    createEReference(collectionTypeIdentifierCSEClass, COLLECTION_TYPE_IDENTIFIER_CS__TYPE_NAME);
    createEReference(collectionTypeIdentifierCSEClass, COLLECTION_TYPE_IDENTIFIER_CS__GENERIC_TYPE);

    collectionLiteralPartsCSEClass = createEClass(COLLECTION_LITERAL_PARTS_CS);

    collectionLiteralPartsOclExpCSEClass = createEClass(COLLECTION_LITERAL_PARTS_OCL_EXP_CS);
    createEReference(collectionLiteralPartsOclExpCSEClass, COLLECTION_LITERAL_PARTS_OCL_EXP_CS__OCL_EXPRESSION);

    collectionRangeCSEClass = createEClass(COLLECTION_RANGE_CS);
    createEReference(collectionRangeCSEClass, COLLECTION_RANGE_CS__FROM);
    createEReference(collectionRangeCSEClass, COLLECTION_RANGE_CS__TO);

    callExpCSEClass = createEClass(CALL_EXP_CS);

    loopExpCSEClass = createEClass(LOOP_EXP_CS);

    iteratorExpVariableCSEClass = createEClass(ITERATOR_EXP_VARIABLE_CS);
    createEReference(iteratorExpVariableCSEClass, ITERATOR_EXP_VARIABLE_CS__VARIABLE_NAME);
    createEReference(iteratorExpVariableCSEClass, ITERATOR_EXP_VARIABLE_CS__TYPE_NAME);

    iteratorExpCSEClass = createEClass(ITERATOR_EXP_CS);
    createEAttribute(iteratorExpCSEClass, ITERATOR_EXP_CS__ITERATOR_NAME);
    createEReference(iteratorExpCSEClass, ITERATOR_EXP_CS__ITERATOR_VARIABLES);
    createEReference(iteratorExpCSEClass, ITERATOR_EXP_CS__BODY_EXPRESSION);

    iterateExpCSEClass = createEClass(ITERATE_EXP_CS);
    createEReference(iterateExpCSEClass, ITERATE_EXP_CS__ITERATOR_VARIABLE);
    createEReference(iterateExpCSEClass, ITERATE_EXP_CS__RESULT_VARIABLE);
    createEReference(iterateExpCSEClass, ITERATE_EXP_CS__BODY_EXPRESSION);

    featureCallExpCSEClass = createEClass(FEATURE_CALL_EXP_CS);

    navigationCallExpEClass = createEClass(NAVIGATION_CALL_EXP);
    createEReference(navigationCallExpEClass, NAVIGATION_CALL_EXP__SOURCE);
    createEAttribute(navigationCallExpEClass, NAVIGATION_CALL_EXP__NAVIGATION_OPERATOR);
    createEReference(navigationCallExpEClass, NAVIGATION_CALL_EXP__FEATURE_CALLS);

    operationCallBaseExpCSEClass = createEClass(OPERATION_CALL_BASE_EXP_CS);
    createEReference(operationCallBaseExpCSEClass, OPERATION_CALL_BASE_EXP_CS__OPERATION_NAME);
    createEReference(operationCallBaseExpCSEClass, OPERATION_CALL_BASE_EXP_CS__ARGUMENTS);
    createEAttribute(operationCallBaseExpCSEClass, OPERATION_CALL_BASE_EXP_CS__IS_MARKED_PRE);

    propertyCallBaseExpCSEClass = createEClass(PROPERTY_CALL_BASE_EXP_CS);
    createEReference(propertyCallBaseExpCSEClass, PROPERTY_CALL_BASE_EXP_CS__PROPERTY);
    createEAttribute(propertyCallBaseExpCSEClass, PROPERTY_CALL_BASE_EXP_CS__IS_MARKED_PRE);

    implicitFeatureCallCSEClass = createEClass(IMPLICIT_FEATURE_CALL_CS);

    implicitPropertyCallCSEClass = createEClass(IMPLICIT_PROPERTY_CALL_CS);

    implicitOperationCallCSEClass = createEClass(IMPLICIT_OPERATION_CALL_CS);

    propertyCallExpCSEClass = createEClass(PROPERTY_CALL_EXP_CS);

    propertyCallOnSelfExpCSEClass = createEClass(PROPERTY_CALL_ON_SELF_EXP_CS);

    propertyCallExplicitPathExpCSEClass = createEClass(PROPERTY_CALL_EXPLICIT_PATH_EXP_CS);
    createEReference(propertyCallExplicitPathExpCSEClass, PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__SOURCE);
    createEReference(propertyCallExplicitPathExpCSEClass, PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__PROPERTY_PATH);
    createEReference(propertyCallExplicitPathExpCSEClass, PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__PROPERTY_NAME);
    createEAttribute(propertyCallExplicitPathExpCSEClass, PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__IS_MARKED_PRE);

    operationCallExpCSEClass = createEClass(OPERATION_CALL_EXP_CS);

    operationCallOnSelfExpCSEClass = createEClass(OPERATION_CALL_ON_SELF_EXP_CS);

    staticOperationCallExpCSEClass = createEClass(STATIC_OPERATION_CALL_EXP_CS);
    createEReference(staticOperationCallExpCSEClass, STATIC_OPERATION_CALL_EXP_CS__TYPE_NAME);
    createEReference(staticOperationCallExpCSEClass, STATIC_OPERATION_CALL_EXP_CS__OPERATION_NAME);
    createEReference(staticOperationCallExpCSEClass, STATIC_OPERATION_CALL_EXP_CS__ARGUMENTS);

    unaryOperationCallExpCSEClass = createEClass(UNARY_OPERATION_CALL_EXP_CS);
    createEAttribute(unaryOperationCallExpCSEClass, UNARY_OPERATION_CALL_EXP_CS__OPERATION_NAME);
    createEReference(unaryOperationCallExpCSEClass, UNARY_OPERATION_CALL_EXP_CS__TARGET);

    logicalNotOperationCallExpCSEClass = createEClass(LOGICAL_NOT_OPERATION_CALL_EXP_CS);
    createEAttribute(logicalNotOperationCallExpCSEClass, LOGICAL_NOT_OPERATION_CALL_EXP_CS__OPERATION_NAME);
    createEReference(logicalNotOperationCallExpCSEClass, LOGICAL_NOT_OPERATION_CALL_EXP_CS__TARGET);

    operationCallWithSourceExpCSEClass = createEClass(OPERATION_CALL_WITH_SOURCE_EXP_CS);
    createEReference(operationCallWithSourceExpCSEClass, OPERATION_CALL_WITH_SOURCE_EXP_CS__SOURCE);
    createEAttribute(operationCallWithSourceExpCSEClass, OPERATION_CALL_WITH_SOURCE_EXP_CS__IS_MARKED_PRE);

    operationCallBinaryExpCSEClass = createEClass(OPERATION_CALL_BINARY_EXP_CS);
    createEAttribute(operationCallBinaryExpCSEClass, OPERATION_CALL_BINARY_EXP_CS__OPERATION_NAME);
    createEReference(operationCallBinaryExpCSEClass, OPERATION_CALL_BINARY_EXP_CS__TARGET);

    additiveOperationCallExpCSEClass = createEClass(ADDITIVE_OPERATION_CALL_EXP_CS);

    multOperationCallExpCSEClass = createEClass(MULT_OPERATION_CALL_EXP_CS);

    relationalOperationCallExpCSEClass = createEClass(RELATIONAL_OPERATION_CALL_EXP_CS);

    equalityOperationCallExpCSEClass = createEClass(EQUALITY_OPERATION_CALL_EXP_CS);

    logicalAndOperationCallExpCSEClass = createEClass(LOGICAL_AND_OPERATION_CALL_EXP_CS);

    logicalOrOperationCallExpCSEClass = createEClass(LOGICAL_OR_OPERATION_CALL_EXP_CS);

    logicalXorOperationCallExpCSEClass = createEClass(LOGICAL_XOR_OPERATION_CALL_EXP_CS);

    logicalImpliesOperationCallExpCSEClass = createEClass(LOGICAL_IMPLIES_OPERATION_CALL_EXP_CS);

    operationCallWithImlicitSourceExpCSEClass = createEClass(OPERATION_CALL_WITH_IMLICIT_SOURCE_EXP_CS);

    tupleLiteralExpCSEClass = createEClass(TUPLE_LITERAL_EXP_CS);
    createEReference(tupleLiteralExpCSEClass, TUPLE_LITERAL_EXP_CS__VARIABLE_DECLARATIONS);

    primitiveLiteralExpCSEClass = createEClass(PRIMITIVE_LITERAL_EXP_CS);

    integerLiteralExpCSEClass = createEClass(INTEGER_LITERAL_EXP_CS);
    createEAttribute(integerLiteralExpCSEClass, INTEGER_LITERAL_EXP_CS__INTEGER_LITERAL);

    realLiteralExpCSEClass = createEClass(REAL_LITERAL_EXP_CS);
    createEAttribute(realLiteralExpCSEClass, REAL_LITERAL_EXP_CS__INT_VALUE);
    createEAttribute(realLiteralExpCSEClass, REAL_LITERAL_EXP_CS__REAL_VALUE);
    createEAttribute(realLiteralExpCSEClass, REAL_LITERAL_EXP_CS__NAVIGATION_OPERATOR);

    booleanLiteralExpCSEClass = createEClass(BOOLEAN_LITERAL_EXP_CS);
    createEAttribute(booleanLiteralExpCSEClass, BOOLEAN_LITERAL_EXP_CS__BOOLEAN_LITERAL);

    stringLiteralExpCSEClass = createEClass(STRING_LITERAL_EXP_CS);
    createEAttribute(stringLiteralExpCSEClass, STRING_LITERAL_EXP_CS__STRING_LITERAL);

    invalidLiteralExpCSEClass = createEClass(INVALID_LITERAL_EXP_CS);

    nullLiteralExpCSEClass = createEClass(NULL_LITERAL_EXP_CS);

    letExpCSEClass = createEClass(LET_EXP_CS);
    createEReference(letExpCSEClass, LET_EXP_CS__VARIABLE_DECLARATIONS);
    createEReference(letExpCSEClass, LET_EXP_CS__OCL_EXPRESSION);

    ifExpCSEClass = createEClass(IF_EXP_CS);
    createEReference(ifExpCSEClass, IF_EXP_CS__CONDITION);
    createEReference(ifExpCSEClass, IF_EXP_CS__THEN_BRANCH);
    createEReference(ifExpCSEClass, IF_EXP_CS__ELSE_BRANCH);

    packageDeclarationCSEClass = createEClass(PACKAGE_DECLARATION_CS);
    createEReference(packageDeclarationCSEClass, PACKAGE_DECLARATION_CS__CONTEXT_DECLARATIONS);
    createEReference(packageDeclarationCSEClass, PACKAGE_DECLARATION_CS__LAYOUT_INFORMATION);

    packageDeclarationWithNamespaceCSEClass = createEClass(PACKAGE_DECLARATION_WITH_NAMESPACE_CS);
    createEReference(packageDeclarationWithNamespaceCSEClass, PACKAGE_DECLARATION_WITH_NAMESPACE_CS__NESTED_NAMESPACE);

    packageDeclarationNestedNamespaceCSEClass = createEClass(PACKAGE_DECLARATION_NESTED_NAMESPACE_CS);
    createEReference(packageDeclarationNestedNamespaceCSEClass, PACKAGE_DECLARATION_NESTED_NAMESPACE_CS__NAMESPACE);
    createEReference(packageDeclarationNestedNamespaceCSEClass, PACKAGE_DECLARATION_NESTED_NAMESPACE_CS__NESTED_NAMESPACE);
    createEReference(packageDeclarationNestedNamespaceCSEClass, PACKAGE_DECLARATION_NESTED_NAMESPACE_CS__LAYOUT_INFORMATION);

    packageDeclarationWithoutNamespaceCSEClass = createEClass(PACKAGE_DECLARATION_WITHOUT_NAMESPACE_CS);

    contextDeclarationCSEClass = createEClass(CONTEXT_DECLARATION_CS);

    attributeContextDeclarationCSEClass = createEClass(ATTRIBUTE_CONTEXT_DECLARATION_CS);
    createEReference(attributeContextDeclarationCSEClass, ATTRIBUTE_CONTEXT_DECLARATION_CS__TYPE_NAME);
    createEReference(attributeContextDeclarationCSEClass, ATTRIBUTE_CONTEXT_DECLARATION_CS__TYPE);
    createEReference(attributeContextDeclarationCSEClass, ATTRIBUTE_CONTEXT_DECLARATION_CS__INIT_OR_DERIVE_VALUE);

    classifierContextDeclarationCSEClass = createEClass(CLASSIFIER_CONTEXT_DECLARATION_CS);
    createEReference(classifierContextDeclarationCSEClass, CLASSIFIER_CONTEXT_DECLARATION_CS__TYPE_NAME);
    createEReference(classifierContextDeclarationCSEClass, CLASSIFIER_CONTEXT_DECLARATION_CS__INVARIANTS_AND_DEFINITIONS);

    operationContextDeclarationCSEClass = createEClass(OPERATION_CONTEXT_DECLARATION_CS);
    createEReference(operationContextDeclarationCSEClass, OPERATION_CONTEXT_DECLARATION_CS__OPERATION);
    createEReference(operationContextDeclarationCSEClass, OPERATION_CONTEXT_DECLARATION_CS__PRE_POST_OR_BODY_DECLARATIONS);

    initOrDeriveValueCSEClass = createEClass(INIT_OR_DERIVE_VALUE_CS);
    createEReference(initOrDeriveValueCSEClass, INIT_OR_DERIVE_VALUE_CS__OCL_EXPRESSION);

    initValueCSEClass = createEClass(INIT_VALUE_CS);

    deriveValueCSEClass = createEClass(DERIVE_VALUE_CS);

    invariantOrDefinitionCSEClass = createEClass(INVARIANT_OR_DEFINITION_CS);

    invariantExpCSEClass = createEClass(INVARIANT_EXP_CS);
    createEReference(invariantExpCSEClass, INVARIANT_EXP_CS__NAME);
    createEReference(invariantExpCSEClass, INVARIANT_EXP_CS__OCL_EXPRESSION);

    definitionExpCSEClass = createEClass(DEFINITION_EXP_CS);
    createEAttribute(definitionExpCSEClass, DEFINITION_EXP_CS__STATIC);
    createEReference(definitionExpCSEClass, DEFINITION_EXP_CS__DEFINITION_EXP_PART);

    definitionExpPartCSEClass = createEClass(DEFINITION_EXP_PART_CS);

    definitionExpPropertyCSEClass = createEClass(DEFINITION_EXP_PROPERTY_CS);
    createEReference(definitionExpPropertyCSEClass, DEFINITION_EXP_PROPERTY_CS__VARIABLE_DECLARATION);

    definitionExpOperationCSEClass = createEClass(DEFINITION_EXP_OPERATION_CS);
    createEReference(definitionExpOperationCSEClass, DEFINITION_EXP_OPERATION_CS__OPERATION);
    createEAttribute(definitionExpOperationCSEClass, DEFINITION_EXP_OPERATION_CS__EQUAL);
    createEReference(definitionExpOperationCSEClass, DEFINITION_EXP_OPERATION_CS__OCL_EXPRESSION);

    prePostOrBodyDeclarationCSEClass = createEClass(PRE_POST_OR_BODY_DECLARATION_CS);
    createEReference(prePostOrBodyDeclarationCSEClass, PRE_POST_OR_BODY_DECLARATION_CS__NAME);
    createEReference(prePostOrBodyDeclarationCSEClass, PRE_POST_OR_BODY_DECLARATION_CS__OCL_EXPRESSION);

    preConditionDeclarationCSEClass = createEClass(PRE_CONDITION_DECLARATION_CS);

    postConditionDeclarationCSEClass = createEClass(POST_CONDITION_DECLARATION_CS);

    bodyDeclarationCSEClass = createEClass(BODY_DECLARATION_CS);

    operationDefinitionCSEClass = createEClass(OPERATION_DEFINITION_CS);
    createEReference(operationDefinitionCSEClass, OPERATION_DEFINITION_CS__OPERATION);
    createEReference(operationDefinitionCSEClass, OPERATION_DEFINITION_CS__PARAMETERS);
    createEReference(operationDefinitionCSEClass, OPERATION_DEFINITION_CS__RETURN_TYPE);

    operationDefinitionInContextCSEClass = createEClass(OPERATION_DEFINITION_IN_CONTEXT_CS);
    createEReference(operationDefinitionInContextCSEClass, OPERATION_DEFINITION_IN_CONTEXT_CS__TYPE_NAME);

    operationDefinitionInDefCSEClass = createEClass(OPERATION_DEFINITION_IN_DEF_CS);

    parameterCSEClass = createEClass(PARAMETER_CS);
    createEReference(parameterCSEClass, PARAMETER_CS__PARAMETER);
    createEReference(parameterCSEClass, PARAMETER_CS__PARAMETER_TYPE);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private boolean isInitialized = false;

	/**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void initializePackageContents() {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Obtain other dependent packages
    PivotModelPackage thePivotModelPackage = (PivotModelPackage)EPackage.Registry.INSTANCE.getEPackage(PivotModelPackage.eNS_URI);
    LayoutPackage theLayoutPackage = (LayoutPackage)EPackage.Registry.INSTANCE.getEPackage(LayoutPackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    bracketExpCSEClass.getESuperTypes().add(this.getOclExpressionCS());
    modelElementCSEClass.getESuperTypes().add(this.getOclExpressionCS());
    pathNameSimpleCSEClass.getESuperTypes().add(this.getPathNameCS());
    pathNamePathCSEClass.getESuperTypes().add(this.getPathNameCS());
    namedElementCSEClass.getESuperTypes().add(this.getUnreservedSimpleNameCS());
    typeModelElementCSEClass.getESuperTypes().add(this.getTypeCS());
    tupleTypeCSEClass.getESuperTypes().add(this.getTypeCS());
    collectionTypeLiteralExpCSEClass.getESuperTypes().add(this.getLiteralExpCS());
    tupleTypeLiteralExpCSEClass.getESuperTypes().add(this.getLiteralExpCS());
    variableDeclarationWithInitCSEClass.getESuperTypes().add(this.getVariableDeclarationCS());
    variableDeclarationWithoutInitCSEClass.getESuperTypes().add(this.getVariableDeclarationCS());
    literalExpCSEClass.getESuperTypes().add(this.getOclExpressionCS());
    collectionLiteralExpCSEClass.getESuperTypes().add(this.getLiteralExpCS());
    collectionTypeIdentifierCSEClass.getESuperTypes().add(this.getTypeCS());
    collectionLiteralPartsOclExpCSEClass.getESuperTypes().add(this.getCollectionLiteralPartsCS());
    collectionRangeCSEClass.getESuperTypes().add(this.getCollectionLiteralPartsCS());
    callExpCSEClass.getESuperTypes().add(this.getOclExpressionCS());
    loopExpCSEClass.getESuperTypes().add(this.getCallExpCS());
    iteratorExpCSEClass.getESuperTypes().add(this.getLoopExpCS());
    iteratorExpCSEClass.getESuperTypes().add(this.getImplicitFeatureCallCS());
    iterateExpCSEClass.getESuperTypes().add(this.getLoopExpCS());
    iterateExpCSEClass.getESuperTypes().add(this.getImplicitFeatureCallCS());
    featureCallExpCSEClass.getESuperTypes().add(this.getCallExpCS());
    navigationCallExpEClass.getESuperTypes().add(this.getFeatureCallExpCS());
    implicitPropertyCallCSEClass.getESuperTypes().add(this.getImplicitFeatureCallCS());
    implicitPropertyCallCSEClass.getESuperTypes().add(this.getPropertyCallBaseExpCS());
    implicitOperationCallCSEClass.getESuperTypes().add(this.getImplicitFeatureCallCS());
    implicitOperationCallCSEClass.getESuperTypes().add(this.getOperationCallBaseExpCS());
    propertyCallExpCSEClass.getESuperTypes().add(this.getFeatureCallExpCS());
    propertyCallOnSelfExpCSEClass.getESuperTypes().add(this.getPropertyCallExpCS());
    propertyCallOnSelfExpCSEClass.getESuperTypes().add(this.getPropertyCallBaseExpCS());
    propertyCallExplicitPathExpCSEClass.getESuperTypes().add(this.getPropertyCallExpCS());
    operationCallExpCSEClass.getESuperTypes().add(this.getFeatureCallExpCS());
    operationCallOnSelfExpCSEClass.getESuperTypes().add(this.getOperationCallExpCS());
    operationCallOnSelfExpCSEClass.getESuperTypes().add(this.getOperationCallBaseExpCS());
    staticOperationCallExpCSEClass.getESuperTypes().add(this.getOperationCallExpCS());
    unaryOperationCallExpCSEClass.getESuperTypes().add(this.getOperationCallExpCS());
    logicalNotOperationCallExpCSEClass.getESuperTypes().add(this.getOperationCallExpCS());
    operationCallWithSourceExpCSEClass.getESuperTypes().add(this.getOperationCallExpCS());
    operationCallBinaryExpCSEClass.getESuperTypes().add(this.getOperationCallWithSourceExpCS());
    additiveOperationCallExpCSEClass.getESuperTypes().add(this.getOperationCallBinaryExpCS());
    multOperationCallExpCSEClass.getESuperTypes().add(this.getOperationCallBinaryExpCS());
    relationalOperationCallExpCSEClass.getESuperTypes().add(this.getOperationCallBinaryExpCS());
    equalityOperationCallExpCSEClass.getESuperTypes().add(this.getOperationCallBinaryExpCS());
    logicalAndOperationCallExpCSEClass.getESuperTypes().add(this.getOperationCallBinaryExpCS());
    logicalOrOperationCallExpCSEClass.getESuperTypes().add(this.getOperationCallBinaryExpCS());
    logicalXorOperationCallExpCSEClass.getESuperTypes().add(this.getOperationCallBinaryExpCS());
    logicalImpliesOperationCallExpCSEClass.getESuperTypes().add(this.getOperationCallBinaryExpCS());
    operationCallWithImlicitSourceExpCSEClass.getESuperTypes().add(this.getOperationCallOnSelfExpCS());
    tupleLiteralExpCSEClass.getESuperTypes().add(this.getLiteralExpCS());
    primitiveLiteralExpCSEClass.getESuperTypes().add(this.getLiteralExpCS());
    integerLiteralExpCSEClass.getESuperTypes().add(this.getPrimitiveLiteralExpCS());
    realLiteralExpCSEClass.getESuperTypes().add(this.getPrimitiveLiteralExpCS());
    booleanLiteralExpCSEClass.getESuperTypes().add(this.getPrimitiveLiteralExpCS());
    stringLiteralExpCSEClass.getESuperTypes().add(this.getPrimitiveLiteralExpCS());
    invalidLiteralExpCSEClass.getESuperTypes().add(this.getPrimitiveLiteralExpCS());
    nullLiteralExpCSEClass.getESuperTypes().add(this.getPrimitiveLiteralExpCS());
    letExpCSEClass.getESuperTypes().add(this.getOclExpressionCS());
    ifExpCSEClass.getESuperTypes().add(this.getOclExpressionCS());
    packageDeclarationWithNamespaceCSEClass.getESuperTypes().add(this.getPackageDeclarationCS());
    packageDeclarationWithoutNamespaceCSEClass.getESuperTypes().add(this.getPackageDeclarationCS());
    attributeContextDeclarationCSEClass.getESuperTypes().add(this.getContextDeclarationCS());
    classifierContextDeclarationCSEClass.getESuperTypes().add(this.getContextDeclarationCS());
    operationContextDeclarationCSEClass.getESuperTypes().add(this.getContextDeclarationCS());
    initValueCSEClass.getESuperTypes().add(this.getInitOrDeriveValueCS());
    deriveValueCSEClass.getESuperTypes().add(this.getInitOrDeriveValueCS());
    invariantExpCSEClass.getESuperTypes().add(this.getInvariantOrDefinitionCS());
    definitionExpCSEClass.getESuperTypes().add(this.getInvariantOrDefinitionCS());
    definitionExpPropertyCSEClass.getESuperTypes().add(this.getDefinitionExpPartCS());
    definitionExpOperationCSEClass.getESuperTypes().add(this.getDefinitionExpPartCS());
    preConditionDeclarationCSEClass.getESuperTypes().add(this.getPrePostOrBodyDeclarationCS());
    postConditionDeclarationCSEClass.getESuperTypes().add(this.getPrePostOrBodyDeclarationCS());
    bodyDeclarationCSEClass.getESuperTypes().add(this.getPrePostOrBodyDeclarationCS());
    operationDefinitionInContextCSEClass.getESuperTypes().add(this.getOperationDefinitionCS());
    operationDefinitionInDefCSEClass.getESuperTypes().add(this.getOperationDefinitionCS());

    // Initialize classes and features; add operations and parameters
    initEClass(oclExpressionCSEClass, OclExpressionCS.class, "OclExpressionCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(bracketExpCSEClass, BracketExpCS.class, "BracketExpCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getBracketExpCS_OclExpression(), this.getOclExpressionCS(), null, "oclExpression", null, 1, 1, BracketExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(modelElementCSEClass, ModelElementCS.class, "ModelElementCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getModelElementCS_PathName(), this.getPathNameCS(), null, "pathName", null, 1, 1, ModelElementCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    addEOperation(modelElementCSEClass, thePivotModelPackage.getNamedElement(), "getNamedElement", 1, 1, IS_UNIQUE, IS_ORDERED);

    initEClass(pathNameCSEClass, PathNameCS.class, "PathNameCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    addEOperation(pathNameCSEClass, thePivotModelPackage.getNamedElement(), "getNamedElement", 1, 1, IS_UNIQUE, IS_ORDERED);

    initEClass(pathNameSimpleCSEClass, PathNameSimpleCS.class, "PathNameSimpleCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPathNameSimpleCS_NamedElement(), thePivotModelPackage.getNamedElement(), null, "namedElement", null, 1, 1, PathNameSimpleCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(pathNamePathCSEClass, PathNamePathCS.class, "PathNamePathCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPathNamePathCS_PathName(), this.getUnreservedSimpleNameCS(), null, "pathName", null, 2, -1, PathNamePathCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(unreservedSimpleNameCSEClass, UnreservedSimpleNameCS.class, "UnreservedSimpleNameCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    addEOperation(unreservedSimpleNameCSEClass, thePivotModelPackage.getNamedElement(), "getNamedElement", 1, 1, IS_UNIQUE, IS_ORDERED);

    addEOperation(unreservedSimpleNameCSEClass, ecorePackage.getEBoolean(), "isProxy", 1, 1, IS_UNIQUE, IS_ORDERED);

    initEClass(namedElementCSEClass, NamedElementCS.class, "NamedElementCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getNamedElementCS_NamedElement(), thePivotModelPackage.getNamedElement(), null, "namedElement", null, 1, 1, NamedElementCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(simpleNameCSEClass, SimpleNameCS.class, "SimpleNameCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getSimpleNameCS_SimpleName(), ecorePackage.getEString(), "simpleName", null, 1, 1, SimpleNameCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(typeCSEClass, TypeCS.class, "TypeCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(typeModelElementCSEClass, TypeModelElementCS.class, "TypeModelElementCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getTypeModelElementCS_ModelElement(), this.getModelElementCS(), null, "modelElement", null, 1, 1, TypeModelElementCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(tupleTypeCSEClass, TupleTypeCS.class, "TupleTypeCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getTupleTypeCS_VariableDeclarationList(), this.getVariableDeclarationWithoutInitListCS(), null, "variableDeclarationList", null, 0, 1, TupleTypeCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(collectionTypeLiteralExpCSEClass, CollectionTypeLiteralExpCS.class, "CollectionTypeLiteralExpCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getCollectionTypeLiteralExpCS_CollectionType(), this.getCollectionTypeIdentifierCS(), null, "collectionType", null, 1, 1, CollectionTypeLiteralExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(tupleTypeLiteralExpCSEClass, TupleTypeLiteralExpCS.class, "TupleTypeLiteralExpCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getTupleTypeLiteralExpCS_TupleType(), this.getTupleTypeCS(), null, "tupleType", null, 1, 1, TupleTypeLiteralExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(variableDeclarationCSEClass, VariableDeclarationCS.class, "VariableDeclarationCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getVariableDeclarationCS_VariableName(), this.getSimpleNameCS(), null, "variableName", null, 1, 1, VariableDeclarationCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(variableDeclarationWithInitCSEClass, VariableDeclarationWithInitCS.class, "VariableDeclarationWithInitCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getVariableDeclarationWithInitCS_TypeName(), this.getTypeCS(), null, "typeName", null, 0, 1, VariableDeclarationWithInitCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getVariableDeclarationWithInitCS_Initialization(), this.getOclExpressionCS(), null, "initialization", null, 1, 1, VariableDeclarationWithInitCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getVariableDeclarationWithInitCS_Equal(), ecorePackage.getEString(), "equal", null, 1, 1, VariableDeclarationWithInitCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(variableDeclarationWithoutInitCSEClass, VariableDeclarationWithoutInitCS.class, "VariableDeclarationWithoutInitCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getVariableDeclarationWithoutInitCS_TypeName(), this.getTypeCS(), null, "typeName", null, 1, 1, VariableDeclarationWithoutInitCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(variableDeclarationWithInitListCSEClass, VariableDeclarationWithInitListCS.class, "VariableDeclarationWithInitListCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getVariableDeclarationWithInitListCS_VariableDeclarations(), this.getVariableDeclarationWithInitCS(), null, "variableDeclarations", null, 1, -1, VariableDeclarationWithInitListCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(variableDeclarationWithoutInitListCSEClass, VariableDeclarationWithoutInitListCS.class, "VariableDeclarationWithoutInitListCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getVariableDeclarationWithoutInitListCS_VariableDeclarations(), this.getVariableDeclarationWithoutInitCS(), null, "variableDeclarations", null, 1, -1, VariableDeclarationWithoutInitListCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(literalExpCSEClass, LiteralExpCS.class, "LiteralExpCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(collectionLiteralExpCSEClass, CollectionLiteralExpCS.class, "CollectionLiteralExpCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getCollectionLiteralExpCS_CollectionType(), this.getCollectionTypeIdentifierCS(), null, "collectionType", null, 1, 1, CollectionLiteralExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getCollectionLiteralExpCS_CollectionLiteralParts(), this.getCollectionLiteralPartsCS(), null, "collectionLiteralParts", null, 0, -1, CollectionLiteralExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(collectionTypeIdentifierCSEClass, CollectionTypeIdentifierCS.class, "CollectionTypeIdentifierCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getCollectionTypeIdentifierCS_TypeName(), thePivotModelPackage.getType(), null, "typeName", null, 1, 1, CollectionTypeIdentifierCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getCollectionTypeIdentifierCS_GenericType(), this.getTypeCS(), null, "genericType", null, 0, 1, CollectionTypeIdentifierCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(collectionLiteralPartsCSEClass, CollectionLiteralPartsCS.class, "CollectionLiteralPartsCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(collectionLiteralPartsOclExpCSEClass, CollectionLiteralPartsOclExpCS.class, "CollectionLiteralPartsOclExpCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getCollectionLiteralPartsOclExpCS_OclExpression(), this.getOclExpressionCS(), null, "oclExpression", null, 1, 1, CollectionLiteralPartsOclExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(collectionRangeCSEClass, CollectionRangeCS.class, "CollectionRangeCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getCollectionRangeCS_From(), this.getOclExpressionCS(), null, "from", null, 1, 1, CollectionRangeCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getCollectionRangeCS_To(), this.getOclExpressionCS(), null, "to", null, 1, 1, CollectionRangeCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(callExpCSEClass, CallExpCS.class, "CallExpCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(loopExpCSEClass, LoopExpCS.class, "LoopExpCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(iteratorExpVariableCSEClass, IteratorExpVariableCS.class, "IteratorExpVariableCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getIteratorExpVariableCS_VariableName(), this.getSimpleNameCS(), null, "variableName", null, 1, 1, IteratorExpVariableCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getIteratorExpVariableCS_TypeName(), this.getTypeCS(), null, "typeName", null, 0, 1, IteratorExpVariableCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(iteratorExpCSEClass, IteratorExpCS.class, "IteratorExpCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getIteratorExpCS_IteratorName(), ecorePackage.getEString(), "iteratorName", null, 1, 1, IteratorExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getIteratorExpCS_IteratorVariables(), this.getIteratorExpVariableCS(), null, "iteratorVariables", null, 0, 2, IteratorExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getIteratorExpCS_BodyExpression(), this.getOclExpressionCS(), null, "bodyExpression", null, 1, 1, IteratorExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(iterateExpCSEClass, IterateExpCS.class, "IterateExpCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getIterateExpCS_IteratorVariable(), this.getIteratorExpVariableCS(), null, "iteratorVariable", null, 0, 1, IterateExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getIterateExpCS_ResultVariable(), this.getVariableDeclarationWithInitCS(), null, "resultVariable", null, 1, 1, IterateExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getIterateExpCS_BodyExpression(), this.getOclExpressionCS(), null, "bodyExpression", null, 1, 1, IterateExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(featureCallExpCSEClass, FeatureCallExpCS.class, "FeatureCallExpCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(navigationCallExpEClass, NavigationCallExp.class, "NavigationCallExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getNavigationCallExp_Source(), this.getOclExpressionCS(), null, "source", null, 1, 1, NavigationCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getNavigationCallExp_NavigationOperator(), ecorePackage.getEString(), "navigationOperator", null, 1, -1, NavigationCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getNavigationCallExp_FeatureCalls(), this.getImplicitFeatureCallCS(), null, "featureCalls", null, 1, -1, NavigationCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(operationCallBaseExpCSEClass, OperationCallBaseExpCS.class, "OperationCallBaseExpCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getOperationCallBaseExpCS_OperationName(), thePivotModelPackage.getOperation(), null, "operationName", null, 1, 1, OperationCallBaseExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getOperationCallBaseExpCS_Arguments(), this.getOclExpressionCS(), null, "arguments", null, 0, -1, OperationCallBaseExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getOperationCallBaseExpCS_IsMarkedPre(), ecorePackage.getEBoolean(), "isMarkedPre", null, 0, 1, OperationCallBaseExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(propertyCallBaseExpCSEClass, PropertyCallBaseExpCS.class, "PropertyCallBaseExpCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPropertyCallBaseExpCS_Property(), thePivotModelPackage.getProperty(), null, "property", null, 1, 1, PropertyCallBaseExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPropertyCallBaseExpCS_IsMarkedPre(), ecorePackage.getEBoolean(), "isMarkedPre", null, 0, 1, PropertyCallBaseExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(implicitFeatureCallCSEClass, ImplicitFeatureCallCS.class, "ImplicitFeatureCallCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(implicitPropertyCallCSEClass, ImplicitPropertyCallCS.class, "ImplicitPropertyCallCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(implicitOperationCallCSEClass, ImplicitOperationCallCS.class, "ImplicitOperationCallCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(propertyCallExpCSEClass, PropertyCallExpCS.class, "PropertyCallExpCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(propertyCallOnSelfExpCSEClass, PropertyCallOnSelfExpCS.class, "PropertyCallOnSelfExpCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(propertyCallExplicitPathExpCSEClass, PropertyCallExplicitPathExpCS.class, "PropertyCallExplicitPathExpCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPropertyCallExplicitPathExpCS_Source(), this.getOclExpressionCS(), null, "source", null, 1, 1, PropertyCallExplicitPathExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPropertyCallExplicitPathExpCS_PropertyPath(), this.getPathNameCS(), null, "propertyPath", null, 1, 1, PropertyCallExplicitPathExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPropertyCallExplicitPathExpCS_PropertyName(), this.getSimpleNameCS(), null, "propertyName", null, 1, 1, PropertyCallExplicitPathExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPropertyCallExplicitPathExpCS_IsMarkedPre(), ecorePackage.getEBoolean(), "isMarkedPre", null, 0, 1, PropertyCallExplicitPathExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(operationCallExpCSEClass, OperationCallExpCS.class, "OperationCallExpCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(operationCallOnSelfExpCSEClass, OperationCallOnSelfExpCS.class, "OperationCallOnSelfExpCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(staticOperationCallExpCSEClass, StaticOperationCallExpCS.class, "StaticOperationCallExpCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getStaticOperationCallExpCS_TypeName(), this.getModelElementCS(), null, "typeName", null, 1, 1, StaticOperationCallExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getStaticOperationCallExpCS_OperationName(), thePivotModelPackage.getOperation(), null, "operationName", null, 1, 1, StaticOperationCallExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getStaticOperationCallExpCS_Arguments(), this.getOclExpressionCS(), null, "arguments", null, 0, -1, StaticOperationCallExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(unaryOperationCallExpCSEClass, UnaryOperationCallExpCS.class, "UnaryOperationCallExpCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getUnaryOperationCallExpCS_OperationName(), ecorePackage.getEString(), "operationName", null, 1, 1, UnaryOperationCallExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getUnaryOperationCallExpCS_Target(), this.getOclExpressionCS(), null, "target", null, 1, 1, UnaryOperationCallExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(logicalNotOperationCallExpCSEClass, LogicalNotOperationCallExpCS.class, "LogicalNotOperationCallExpCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getLogicalNotOperationCallExpCS_OperationName(), ecorePackage.getEString(), "operationName", null, 1, 1, LogicalNotOperationCallExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getLogicalNotOperationCallExpCS_Target(), this.getOclExpressionCS(), null, "target", null, 1, 1, LogicalNotOperationCallExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(operationCallWithSourceExpCSEClass, OperationCallWithSourceExpCS.class, "OperationCallWithSourceExpCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getOperationCallWithSourceExpCS_Source(), this.getOclExpressionCS(), null, "source", null, 1, 1, OperationCallWithSourceExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getOperationCallWithSourceExpCS_IsMarkedPre(), ecorePackage.getEBoolean(), "isMarkedPre", "false", 0, 1, OperationCallWithSourceExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(operationCallBinaryExpCSEClass, OperationCallBinaryExpCS.class, "OperationCallBinaryExpCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getOperationCallBinaryExpCS_OperationName(), ecorePackage.getEString(), "operationName", null, 1, 1, OperationCallBinaryExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getOperationCallBinaryExpCS_Target(), this.getOclExpressionCS(), null, "target", null, 1, 1, OperationCallBinaryExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(additiveOperationCallExpCSEClass, AdditiveOperationCallExpCS.class, "AdditiveOperationCallExpCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(multOperationCallExpCSEClass, MultOperationCallExpCS.class, "MultOperationCallExpCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(relationalOperationCallExpCSEClass, RelationalOperationCallExpCS.class, "RelationalOperationCallExpCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(equalityOperationCallExpCSEClass, EqualityOperationCallExpCS.class, "EqualityOperationCallExpCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(logicalAndOperationCallExpCSEClass, LogicalAndOperationCallExpCS.class, "LogicalAndOperationCallExpCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(logicalOrOperationCallExpCSEClass, LogicalOrOperationCallExpCS.class, "LogicalOrOperationCallExpCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(logicalXorOperationCallExpCSEClass, LogicalXorOperationCallExpCS.class, "LogicalXorOperationCallExpCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(logicalImpliesOperationCallExpCSEClass, LogicalImpliesOperationCallExpCS.class, "LogicalImpliesOperationCallExpCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(operationCallWithImlicitSourceExpCSEClass, OperationCallWithImlicitSourceExpCS.class, "OperationCallWithImlicitSourceExpCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(tupleLiteralExpCSEClass, TupleLiteralExpCS.class, "TupleLiteralExpCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getTupleLiteralExpCS_VariableDeclarations(), this.getVariableDeclarationWithInitListCS(), null, "variableDeclarations", null, 1, 1, TupleLiteralExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(primitiveLiteralExpCSEClass, PrimitiveLiteralExpCS.class, "PrimitiveLiteralExpCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(integerLiteralExpCSEClass, IntegerLiteralExpCS.class, "IntegerLiteralExpCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getIntegerLiteralExpCS_IntegerLiteral(), ecorePackage.getEInt(), "integerLiteral", null, 1, 1, IntegerLiteralExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(realLiteralExpCSEClass, RealLiteralExpCS.class, "RealLiteralExpCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getRealLiteralExpCS_IntValue(), ecorePackage.getEInt(), "intValue", null, 1, 1, RealLiteralExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getRealLiteralExpCS_RealValue(), ecorePackage.getEString(), "realValue", null, 1, 1, RealLiteralExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getRealLiteralExpCS_NavigationOperator(), ecorePackage.getEString(), "navigationOperator", null, 1, 1, RealLiteralExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(booleanLiteralExpCSEClass, BooleanLiteralExpCS.class, "BooleanLiteralExpCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getBooleanLiteralExpCS_BooleanLiteral(), ecorePackage.getEBoolean(), "booleanLiteral", null, 1, 1, BooleanLiteralExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(stringLiteralExpCSEClass, StringLiteralExpCS.class, "StringLiteralExpCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getStringLiteralExpCS_StringLiteral(), ecorePackage.getEString(), "stringLiteral", null, 1, 1, StringLiteralExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(invalidLiteralExpCSEClass, InvalidLiteralExpCS.class, "InvalidLiteralExpCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(nullLiteralExpCSEClass, NullLiteralExpCS.class, "NullLiteralExpCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(letExpCSEClass, LetExpCS.class, "LetExpCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getLetExpCS_VariableDeclarations(), this.getVariableDeclarationWithInitCS(), null, "variableDeclarations", null, 1, -1, LetExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getLetExpCS_OclExpression(), this.getOclExpressionCS(), null, "oclExpression", null, 1, 1, LetExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(ifExpCSEClass, IfExpCS.class, "IfExpCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getIfExpCS_Condition(), this.getOclExpressionCS(), null, "condition", null, 1, 1, IfExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getIfExpCS_ThenBranch(), this.getOclExpressionCS(), null, "thenBranch", null, 1, 1, IfExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getIfExpCS_ElseBranch(), this.getOclExpressionCS(), null, "elseBranch", null, 1, 1, IfExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(packageDeclarationCSEClass, PackageDeclarationCS.class, "PackageDeclarationCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPackageDeclarationCS_ContextDeclarations(), this.getContextDeclarationCS(), null, "contextDeclarations", null, 0, -1, PackageDeclarationCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPackageDeclarationCS_LayoutInformation(), theLayoutPackage.getLayoutInformation(), null, "layoutInformation", null, 0, -1, PackageDeclarationCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(packageDeclarationWithNamespaceCSEClass, PackageDeclarationWithNamespaceCS.class, "PackageDeclarationWithNamespaceCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPackageDeclarationWithNamespaceCS_NestedNamespace(), this.getPackageDeclarationNestedNamespaceCS(), null, "nestedNamespace", null, 1, 1, PackageDeclarationWithNamespaceCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(packageDeclarationNestedNamespaceCSEClass, PackageDeclarationNestedNamespaceCS.class, "PackageDeclarationNestedNamespaceCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPackageDeclarationNestedNamespaceCS_Namespace(), thePivotModelPackage.getNamespace(), null, "namespace", null, 1, 1, PackageDeclarationNestedNamespaceCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPackageDeclarationNestedNamespaceCS_NestedNamespace(), this.getPackageDeclarationNestedNamespaceCS(), null, "nestedNamespace", null, 0, 1, PackageDeclarationNestedNamespaceCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPackageDeclarationNestedNamespaceCS_LayoutInformation(), theLayoutPackage.getLayoutInformation(), null, "layoutInformation", null, 0, -1, PackageDeclarationNestedNamespaceCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(packageDeclarationWithoutNamespaceCSEClass, PackageDeclarationWithoutNamespaceCS.class, "PackageDeclarationWithoutNamespaceCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(contextDeclarationCSEClass, ContextDeclarationCS.class, "ContextDeclarationCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(attributeContextDeclarationCSEClass, AttributeContextDeclarationCS.class, "AttributeContextDeclarationCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAttributeContextDeclarationCS_TypeName(), this.getModelElementCS(), null, "typeName", null, 1, 1, AttributeContextDeclarationCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAttributeContextDeclarationCS_Type(), this.getTypeCS(), null, "type", null, 0, 1, AttributeContextDeclarationCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAttributeContextDeclarationCS_InitOrDeriveValue(), this.getInitOrDeriveValueCS(), null, "initOrDeriveValue", null, 1, 2, AttributeContextDeclarationCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(classifierContextDeclarationCSEClass, ClassifierContextDeclarationCS.class, "ClassifierContextDeclarationCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getClassifierContextDeclarationCS_TypeName(), this.getModelElementCS(), null, "typeName", null, 1, 1, ClassifierContextDeclarationCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getClassifierContextDeclarationCS_InvariantsAndDefinitions(), this.getInvariantOrDefinitionCS(), null, "invariantsAndDefinitions", null, 1, -1, ClassifierContextDeclarationCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(operationContextDeclarationCSEClass, OperationContextDeclarationCS.class, "OperationContextDeclarationCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getOperationContextDeclarationCS_Operation(), this.getOperationDefinitionInContextCS(), null, "operation", null, 1, 1, OperationContextDeclarationCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getOperationContextDeclarationCS_PrePostOrBodyDeclarations(), this.getPrePostOrBodyDeclarationCS(), null, "prePostOrBodyDeclarations", null, 1, -1, OperationContextDeclarationCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(initOrDeriveValueCSEClass, InitOrDeriveValueCS.class, "InitOrDeriveValueCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getInitOrDeriveValueCS_OclExpression(), this.getOclExpressionCS(), null, "oclExpression", null, 1, 1, InitOrDeriveValueCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(initValueCSEClass, InitValueCS.class, "InitValueCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(deriveValueCSEClass, DeriveValueCS.class, "DeriveValueCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(invariantOrDefinitionCSEClass, InvariantOrDefinitionCS.class, "InvariantOrDefinitionCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(invariantExpCSEClass, InvariantExpCS.class, "InvariantExpCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getInvariantExpCS_Name(), this.getSimpleNameCS(), null, "name", null, 0, 1, InvariantExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getInvariantExpCS_OclExpression(), this.getOclExpressionCS(), null, "oclExpression", null, 1, 1, InvariantExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(definitionExpCSEClass, DefinitionExpCS.class, "DefinitionExpCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getDefinitionExpCS_Static(), ecorePackage.getEBoolean(), "static", null, 0, 1, DefinitionExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDefinitionExpCS_DefinitionExpPart(), this.getDefinitionExpPartCS(), null, "definitionExpPart", null, 1, 1, DefinitionExpCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(definitionExpPartCSEClass, DefinitionExpPartCS.class, "DefinitionExpPartCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(definitionExpPropertyCSEClass, DefinitionExpPropertyCS.class, "DefinitionExpPropertyCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getDefinitionExpPropertyCS_VariableDeclaration(), this.getVariableDeclarationWithInitCS(), null, "variableDeclaration", null, 1, 1, DefinitionExpPropertyCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(definitionExpOperationCSEClass, DefinitionExpOperationCS.class, "DefinitionExpOperationCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getDefinitionExpOperationCS_Operation(), this.getOperationDefinitionInDefCS(), null, "operation", null, 1, 1, DefinitionExpOperationCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getDefinitionExpOperationCS_Equal(), ecorePackage.getEString(), "equal", null, 1, 1, DefinitionExpOperationCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDefinitionExpOperationCS_OclExpression(), this.getOclExpressionCS(), null, "oclExpression", null, 1, 1, DefinitionExpOperationCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(prePostOrBodyDeclarationCSEClass, PrePostOrBodyDeclarationCS.class, "PrePostOrBodyDeclarationCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPrePostOrBodyDeclarationCS_Name(), this.getSimpleNameCS(), null, "name", null, 0, 1, PrePostOrBodyDeclarationCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPrePostOrBodyDeclarationCS_OclExpression(), this.getOclExpressionCS(), null, "oclExpression", null, 1, 1, PrePostOrBodyDeclarationCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(preConditionDeclarationCSEClass, PreConditionDeclarationCS.class, "PreConditionDeclarationCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(postConditionDeclarationCSEClass, PostConditionDeclarationCS.class, "PostConditionDeclarationCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(bodyDeclarationCSEClass, BodyDeclarationCS.class, "BodyDeclarationCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(operationDefinitionCSEClass, OperationDefinitionCS.class, "OperationDefinitionCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getOperationDefinitionCS_Operation(), thePivotModelPackage.getOperation(), null, "operation", null, 1, 1, OperationDefinitionCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getOperationDefinitionCS_Parameters(), this.getParameterCS(), null, "parameters", null, 0, -1, OperationDefinitionCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getOperationDefinitionCS_ReturnType(), this.getTypeCS(), null, "returnType", null, 0, 1, OperationDefinitionCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(operationDefinitionInContextCSEClass, OperationDefinitionInContextCS.class, "OperationDefinitionInContextCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getOperationDefinitionInContextCS_TypeName(), this.getModelElementCS(), null, "typeName", null, 1, 1, OperationDefinitionInContextCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(operationDefinitionInDefCSEClass, OperationDefinitionInDefCS.class, "OperationDefinitionInDefCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(parameterCSEClass, ParameterCS.class, "ParameterCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getParameterCS_Parameter(), thePivotModelPackage.getParameter(), null, "parameter", null, 1, 1, ParameterCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getParameterCS_ParameterType(), this.getTypeCS(), null, "parameterType", null, 1, 1, ParameterCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Create resource
    createResource(eNS_URI);
  }

} //OclPackageImpl
