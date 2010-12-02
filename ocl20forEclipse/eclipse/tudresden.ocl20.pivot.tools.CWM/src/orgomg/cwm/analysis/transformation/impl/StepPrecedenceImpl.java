/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.transformation.impl;

import org.eclipse.emf.ecore.EClass;

import orgomg.cwm.analysis.transformation.StepPrecedence;
import orgomg.cwm.analysis.transformation.TransformationPackage;

import orgomg.cwm.objectmodel.core.impl.DependencyImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Step Precedence</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class StepPrecedenceImpl extends DependencyImpl implements StepPrecedence {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StepPrecedenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TransformationPackage.Literals.STEP_PRECEDENCE;
	}

} //StepPrecedenceImpl
