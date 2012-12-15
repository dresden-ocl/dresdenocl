/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.olap.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import orgomg.cwm.analysis.olap.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class OlapFactoryImpl extends EFactoryImpl implements OlapFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static OlapFactory init() {
		try {
			OlapFactory theOlapFactory = (OlapFactory)EPackage.Registry.INSTANCE.getEFactory("http:///orgomg/cwm/analysis/olap.ecore"); 
			if (theOlapFactory != null) {
				return theOlapFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new OlapFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OlapFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case OlapPackage.CONTENT_MAP: return createContentMap();
			case OlapPackage.CUBE: return createCube();
			case OlapPackage.CUBE_DEPLOYMENT: return createCubeDeployment();
			case OlapPackage.CUBE_DIMENSION_ASSOCIATION: return createCubeDimensionAssociation();
			case OlapPackage.CUBE_REGION: return createCubeRegion();
			case OlapPackage.DEPLOYMENT_GROUP: return createDeploymentGroup();
			case OlapPackage.DIMENSION: return createDimension();
			case OlapPackage.DIMENSION_DEPLOYMENT: return createDimensionDeployment();
			case OlapPackage.HIERARCHY_LEVEL_ASSOCIATION: return createHierarchyLevelAssociation();
			case OlapPackage.LEVEL_BASED_HIERARCHY: return createLevelBasedHierarchy();
			case OlapPackage.MEMBER_SELECTION_GROUP: return createMemberSelectionGroup();
			case OlapPackage.MEMBER_SELECTION: return createMemberSelection();
			case OlapPackage.SCHEMA: return createSchema();
			case OlapPackage.VALUE_BASED_HIERARCHY: return createValueBasedHierarchy();
			case OlapPackage.LEVEL: return createLevel();
			case OlapPackage.CODED_LEVEL: return createCodedLevel();
			case OlapPackage.MEASURE: return createMeasure();
			case OlapPackage.STRUCTURE_MAP: return createStructureMap();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContentMap createContentMap() {
		ContentMapImpl contentMap = new ContentMapImpl();
		return contentMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Cube createCube() {
		CubeImpl cube = new CubeImpl();
		return cube;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CubeDeployment createCubeDeployment() {
		CubeDeploymentImpl cubeDeployment = new CubeDeploymentImpl();
		return cubeDeployment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CubeDimensionAssociation createCubeDimensionAssociation() {
		CubeDimensionAssociationImpl cubeDimensionAssociation = new CubeDimensionAssociationImpl();
		return cubeDimensionAssociation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CubeRegion createCubeRegion() {
		CubeRegionImpl cubeRegion = new CubeRegionImpl();
		return cubeRegion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeploymentGroup createDeploymentGroup() {
		DeploymentGroupImpl deploymentGroup = new DeploymentGroupImpl();
		return deploymentGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Dimension createDimension() {
		DimensionImpl dimension = new DimensionImpl();
		return dimension;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DimensionDeployment createDimensionDeployment() {
		DimensionDeploymentImpl dimensionDeployment = new DimensionDeploymentImpl();
		return dimensionDeployment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HierarchyLevelAssociation createHierarchyLevelAssociation() {
		HierarchyLevelAssociationImpl hierarchyLevelAssociation = new HierarchyLevelAssociationImpl();
		return hierarchyLevelAssociation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LevelBasedHierarchy createLevelBasedHierarchy() {
		LevelBasedHierarchyImpl levelBasedHierarchy = new LevelBasedHierarchyImpl();
		return levelBasedHierarchy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MemberSelectionGroup createMemberSelectionGroup() {
		MemberSelectionGroupImpl memberSelectionGroup = new MemberSelectionGroupImpl();
		return memberSelectionGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MemberSelection createMemberSelection() {
		MemberSelectionImpl memberSelection = new MemberSelectionImpl();
		return memberSelection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Schema createSchema() {
		SchemaImpl schema = new SchemaImpl();
		return schema;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueBasedHierarchy createValueBasedHierarchy() {
		ValueBasedHierarchyImpl valueBasedHierarchy = new ValueBasedHierarchyImpl();
		return valueBasedHierarchy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Level createLevel() {
		LevelImpl level = new LevelImpl();
		return level;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CodedLevel createCodedLevel() {
		CodedLevelImpl codedLevel = new CodedLevelImpl();
		return codedLevel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Measure createMeasure() {
		MeasureImpl measure = new MeasureImpl();
		return measure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructureMap createStructureMap() {
		StructureMapImpl structureMap = new StructureMapImpl();
		return structureMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OlapPackage getOlapPackage() {
		return (OlapPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static OlapPackage getPackage() {
		return OlapPackage.eINSTANCE;
	}

} //OlapFactoryImpl
