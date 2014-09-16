/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.olap.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import orgomg.cwm.analysis.olap.*;

import orgomg.cwm.analysis.transformation.Transformation;
import orgomg.cwm.analysis.transformation.TransformationMap;

import orgomg.cwm.objectmodel.core.Attribute;
import orgomg.cwm.objectmodel.core.Classifier;
import orgomg.cwm.objectmodel.core.Element;
import orgomg.cwm.objectmodel.core.Feature;
import orgomg.cwm.objectmodel.core.ModelElement;
import orgomg.cwm.objectmodel.core.Namespace;
import orgomg.cwm.objectmodel.core.StructuralFeature;

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
 * @see orgomg.cwm.analysis.olap.OlapPackage
 * @generated
 */
public class OlapSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static OlapPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OlapSwitch() {
		if (modelPackage == null) {
			modelPackage = OlapPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case OlapPackage.CONTENT_MAP: {
				ContentMap contentMap = (ContentMap)theEObject;
				T result = caseContentMap(contentMap);
				if (result == null) result = caseTransformationMap(contentMap);
				if (result == null) result = caseTransformation(contentMap);
				if (result == null) result = caseNamespace(contentMap);
				if (result == null) result = caseModelElement(contentMap);
				if (result == null) result = caseElement(contentMap);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OlapPackage.CUBE: {
				Cube cube = (Cube)theEObject;
				T result = caseCube(cube);
				if (result == null) result = caseClass(cube);
				if (result == null) result = caseClassifier(cube);
				if (result == null) result = caseNamespace(cube);
				if (result == null) result = caseModelElement(cube);
				if (result == null) result = caseElement(cube);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OlapPackage.CUBE_DEPLOYMENT: {
				CubeDeployment cubeDeployment = (CubeDeployment)theEObject;
				T result = caseCubeDeployment(cubeDeployment);
				if (result == null) result = caseClass(cubeDeployment);
				if (result == null) result = caseClassifier(cubeDeployment);
				if (result == null) result = caseNamespace(cubeDeployment);
				if (result == null) result = caseModelElement(cubeDeployment);
				if (result == null) result = caseElement(cubeDeployment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OlapPackage.CUBE_DIMENSION_ASSOCIATION: {
				CubeDimensionAssociation cubeDimensionAssociation = (CubeDimensionAssociation)theEObject;
				T result = caseCubeDimensionAssociation(cubeDimensionAssociation);
				if (result == null) result = caseClass(cubeDimensionAssociation);
				if (result == null) result = caseClassifier(cubeDimensionAssociation);
				if (result == null) result = caseNamespace(cubeDimensionAssociation);
				if (result == null) result = caseModelElement(cubeDimensionAssociation);
				if (result == null) result = caseElement(cubeDimensionAssociation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OlapPackage.CUBE_REGION: {
				CubeRegion cubeRegion = (CubeRegion)theEObject;
				T result = caseCubeRegion(cubeRegion);
				if (result == null) result = caseClass(cubeRegion);
				if (result == null) result = caseClassifier(cubeRegion);
				if (result == null) result = caseNamespace(cubeRegion);
				if (result == null) result = caseModelElement(cubeRegion);
				if (result == null) result = caseElement(cubeRegion);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OlapPackage.DEPLOYMENT_GROUP: {
				DeploymentGroup deploymentGroup = (DeploymentGroup)theEObject;
				T result = caseDeploymentGroup(deploymentGroup);
				if (result == null) result = casePackage(deploymentGroup);
				if (result == null) result = caseNamespace(deploymentGroup);
				if (result == null) result = caseModelElement(deploymentGroup);
				if (result == null) result = caseElement(deploymentGroup);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OlapPackage.DIMENSION: {
				Dimension dimension = (Dimension)theEObject;
				T result = caseDimension(dimension);
				if (result == null) result = caseClass(dimension);
				if (result == null) result = caseClassifier(dimension);
				if (result == null) result = caseNamespace(dimension);
				if (result == null) result = caseModelElement(dimension);
				if (result == null) result = caseElement(dimension);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OlapPackage.DIMENSION_DEPLOYMENT: {
				DimensionDeployment dimensionDeployment = (DimensionDeployment)theEObject;
				T result = caseDimensionDeployment(dimensionDeployment);
				if (result == null) result = caseClass(dimensionDeployment);
				if (result == null) result = caseClassifier(dimensionDeployment);
				if (result == null) result = caseNamespace(dimensionDeployment);
				if (result == null) result = caseModelElement(dimensionDeployment);
				if (result == null) result = caseElement(dimensionDeployment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OlapPackage.HIERARCHY: {
				Hierarchy hierarchy = (Hierarchy)theEObject;
				T result = caseHierarchy(hierarchy);
				if (result == null) result = caseClass(hierarchy);
				if (result == null) result = caseClassifier(hierarchy);
				if (result == null) result = caseNamespace(hierarchy);
				if (result == null) result = caseModelElement(hierarchy);
				if (result == null) result = caseElement(hierarchy);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OlapPackage.HIERARCHY_LEVEL_ASSOCIATION: {
				HierarchyLevelAssociation hierarchyLevelAssociation = (HierarchyLevelAssociation)theEObject;
				T result = caseHierarchyLevelAssociation(hierarchyLevelAssociation);
				if (result == null) result = caseClass(hierarchyLevelAssociation);
				if (result == null) result = caseClassifier(hierarchyLevelAssociation);
				if (result == null) result = caseNamespace(hierarchyLevelAssociation);
				if (result == null) result = caseModelElement(hierarchyLevelAssociation);
				if (result == null) result = caseElement(hierarchyLevelAssociation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OlapPackage.LEVEL_BASED_HIERARCHY: {
				LevelBasedHierarchy levelBasedHierarchy = (LevelBasedHierarchy)theEObject;
				T result = caseLevelBasedHierarchy(levelBasedHierarchy);
				if (result == null) result = caseHierarchy(levelBasedHierarchy);
				if (result == null) result = caseClass(levelBasedHierarchy);
				if (result == null) result = caseClassifier(levelBasedHierarchy);
				if (result == null) result = caseNamespace(levelBasedHierarchy);
				if (result == null) result = caseModelElement(levelBasedHierarchy);
				if (result == null) result = caseElement(levelBasedHierarchy);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OlapPackage.MEMBER_SELECTION_GROUP: {
				MemberSelectionGroup memberSelectionGroup = (MemberSelectionGroup)theEObject;
				T result = caseMemberSelectionGroup(memberSelectionGroup);
				if (result == null) result = caseClass(memberSelectionGroup);
				if (result == null) result = caseClassifier(memberSelectionGroup);
				if (result == null) result = caseNamespace(memberSelectionGroup);
				if (result == null) result = caseModelElement(memberSelectionGroup);
				if (result == null) result = caseElement(memberSelectionGroup);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OlapPackage.MEMBER_SELECTION: {
				MemberSelection memberSelection = (MemberSelection)theEObject;
				T result = caseMemberSelection(memberSelection);
				if (result == null) result = caseClass(memberSelection);
				if (result == null) result = caseClassifier(memberSelection);
				if (result == null) result = caseNamespace(memberSelection);
				if (result == null) result = caseModelElement(memberSelection);
				if (result == null) result = caseElement(memberSelection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OlapPackage.SCHEMA: {
				Schema schema = (Schema)theEObject;
				T result = caseSchema(schema);
				if (result == null) result = casePackage(schema);
				if (result == null) result = caseNamespace(schema);
				if (result == null) result = caseModelElement(schema);
				if (result == null) result = caseElement(schema);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OlapPackage.VALUE_BASED_HIERARCHY: {
				ValueBasedHierarchy valueBasedHierarchy = (ValueBasedHierarchy)theEObject;
				T result = caseValueBasedHierarchy(valueBasedHierarchy);
				if (result == null) result = caseHierarchy(valueBasedHierarchy);
				if (result == null) result = caseClass(valueBasedHierarchy);
				if (result == null) result = caseClassifier(valueBasedHierarchy);
				if (result == null) result = caseNamespace(valueBasedHierarchy);
				if (result == null) result = caseModelElement(valueBasedHierarchy);
				if (result == null) result = caseElement(valueBasedHierarchy);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OlapPackage.LEVEL: {
				Level level = (Level)theEObject;
				T result = caseLevel(level);
				if (result == null) result = caseMemberSelection(level);
				if (result == null) result = caseClass(level);
				if (result == null) result = caseClassifier(level);
				if (result == null) result = caseNamespace(level);
				if (result == null) result = caseModelElement(level);
				if (result == null) result = caseElement(level);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OlapPackage.CODED_LEVEL: {
				CodedLevel codedLevel = (CodedLevel)theEObject;
				T result = caseCodedLevel(codedLevel);
				if (result == null) result = caseLevel(codedLevel);
				if (result == null) result = caseMemberSelection(codedLevel);
				if (result == null) result = caseClass(codedLevel);
				if (result == null) result = caseClassifier(codedLevel);
				if (result == null) result = caseNamespace(codedLevel);
				if (result == null) result = caseModelElement(codedLevel);
				if (result == null) result = caseElement(codedLevel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OlapPackage.MEASURE: {
				Measure measure = (Measure)theEObject;
				T result = caseMeasure(measure);
				if (result == null) result = caseAttribute(measure);
				if (result == null) result = caseStructuralFeature(measure);
				if (result == null) result = caseFeature(measure);
				if (result == null) result = caseModelElement(measure);
				if (result == null) result = caseElement(measure);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OlapPackage.STRUCTURE_MAP: {
				StructureMap structureMap = (StructureMap)theEObject;
				T result = caseStructureMap(structureMap);
				if (result == null) result = caseTransformationMap(structureMap);
				if (result == null) result = caseTransformation(structureMap);
				if (result == null) result = caseNamespace(structureMap);
				if (result == null) result = caseModelElement(structureMap);
				if (result == null) result = caseElement(structureMap);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Content Map</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Content Map</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContentMap(ContentMap object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Cube</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Cube</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCube(Cube object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Cube Deployment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Cube Deployment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCubeDeployment(CubeDeployment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Cube Dimension Association</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Cube Dimension Association</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCubeDimensionAssociation(CubeDimensionAssociation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Cube Region</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Cube Region</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCubeRegion(CubeRegion object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Deployment Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Deployment Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDeploymentGroup(DeploymentGroup object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Dimension</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Dimension</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDimension(Dimension object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Dimension Deployment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Dimension Deployment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDimensionDeployment(DimensionDeployment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Hierarchy</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Hierarchy</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHierarchy(Hierarchy object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Hierarchy Level Association</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Hierarchy Level Association</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHierarchyLevelAssociation(HierarchyLevelAssociation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Level Based Hierarchy</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Level Based Hierarchy</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLevelBasedHierarchy(LevelBasedHierarchy object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Member Selection Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Member Selection Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMemberSelectionGroup(MemberSelectionGroup object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Member Selection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Member Selection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMemberSelection(MemberSelection object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Schema</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Schema</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSchema(Schema object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Value Based Hierarchy</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Value Based Hierarchy</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseValueBasedHierarchy(ValueBasedHierarchy object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Level</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Level</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLevel(Level object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Coded Level</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Coded Level</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCodedLevel(CodedLevel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Measure</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Measure</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMeasure(Measure object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Structure Map</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Structure Map</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStructureMap(StructureMap object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseElement(Element object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModelElement(ModelElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Namespace</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Namespace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamespace(Namespace object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Transformation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Transformation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTransformation(Transformation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Map</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Map</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTransformationMap(TransformationMap object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Classifier</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Classifier</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseClassifier(Classifier object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseClass(orgomg.cwm.objectmodel.core.Class object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Package</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Package</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePackage(orgomg.cwm.objectmodel.core.Package object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Feature</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Feature</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFeature(Feature object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Structural Feature</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Structural Feature</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStructuralFeature(StructuralFeature object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Attribute</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAttribute(Attribute object) {
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
	public T defaultCase(EObject object) {
		return null;
	}

} //OlapSwitch
