/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.modelinstancetype.test.testmodel.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import tudresden.ocl20.pivot.modelinstancetype.test.testmodel.CopyableClass;
import tudresden.ocl20.pivot.modelinstancetype.test.testmodel.TestmodelPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Copyable Class</b></em>'. <!-- end-user-doc -->
 * <p>
 * </p>
 * 
 * @generated NOT
 */
public class CopyableClassImpl extends EObjectImpl implements CopyableClass,
		Cloneable {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected CopyableClassImpl() {

		super();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 * 
	 * @generated NOT
	 */
	public Object clone() {

		return new ContainerClassImpl();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {

		return TestmodelPackage.Literals.COPYABLE_CLASS;
	}

} // CopyableClassImpl
