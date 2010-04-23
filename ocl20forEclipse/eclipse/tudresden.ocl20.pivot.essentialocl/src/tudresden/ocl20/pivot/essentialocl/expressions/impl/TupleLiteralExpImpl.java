/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Matthias Braeuer (braeuer.matthias@web.de).            *
 * All rights reserved.                                                      *
 *                                                                           *
 * This work was done as a project at the Chair for Software Technology,     *
 * Dresden University Of Technology, Germany (http://st.inf.tu-dresden.de).  *
 * It is understood that any modification not identified as such is not      *
 * covered by the preceding statement.                                       *
 *                                                                           *
 * This work is free software; you can redistribute it and/or modify it      *
 * under the terms of the GNU Library General Public License as published    *
 * by the Free Software Foundation; either version 2 of the License, or      *
 * (at your option) any later version.                                       *
 *                                                                           *
 * This work is distributed in the hope that it will be useful, but WITHOUT  *
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or     *
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Library General Public     *
 * License for more details.                                                 *
 *                                                                           *
 * You should have received a copy of the GNU Library General Public License *
 * along with this library; if not, you can view it online at                *
 * http://www.fsf.org/licensing/licenses/gpl.html.                           *
 *                                                                           *
 * To submit a bug report, send a comment, or get the latest news on this    *
 * project, please visit the website: http://dresden-ocl.sourceforge.net.    *
 * For more information on OCL and related projects visit the OCL Portal:    *
 * http://st.inf.tu-dresden.de/ocl                                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * $Id$
 */
package tudresden.ocl20.pivot.essentialocl.expressions.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import tudresden.ocl20.pivot.essentialocl.expressions.TupleLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.TupleLiteralPart;
import tudresden.ocl20.pivot.essentialocl.expressions.WellformednessException;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Tuple Literal Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.TupleLiteralExpImpl#getPart <em>Part</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TupleLiteralExpImpl extends LiteralExpImpl implements
		TupleLiteralExp {

	/**
	 * The cached value of the '{@link #getPart() <em>Part</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPart()
	 * @generated
	 * @ordered
	 */
	protected EList<TupleLiteralPart> part;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected TupleLiteralExpImpl() {

		super();
	}

	/**
	 * Overridden to determine the type of the <code>TupleLiteralExp</code>
	 * according to the OCL specification (Section 8.3):
	 * 
	 * [1] The type of a TupleLiteralExp is a TupleType with the specified parts.
	 * 
	 * <pre>
	 *   context TupleLiteralExp
	 *   inv: type.oclIsKindOf (TupleType)
	 *      and part-&gt;forAll (tlep |
	 *         type.oclAsType (TupleType).allProperties()-&gt;exists (tp | tlep.attribute = tp))
	 *      and part-&gt;size() = type.oclAsType (TupleType).allProperties()-&gt;size()
	 * </pre>
	 * 
	 * [2] All tuple literal expression parts of one tuple literal expression have
	 * unique names.
	 * 
	 * <pre>
	 *   context TupleLiteralExp
	 *   inv: part-&gt;isUnique (attribute.name)
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.OclExpressionImpl#evaluateType()
	 */
	@Override
	protected Type evaluateType() {

		List<Property> tupleProperties;
		Type type;

		// collect the properties of all tuple literal parts
		tupleProperties = new ArrayList<Property>(getPart().size());

		for (TupleLiteralPart tupleLiteral : getPart()) {
			Property partProperty = tupleLiteral.getProperty();

			// check that the part references a property
			if (partProperty == null) {
				throw new WellformednessException(this,
						"All tuple literal parts of a tuple literal expression " //$NON-NLS-1$
								+ "must reference a valid property. "); //$NON-NLS-1$
			}

			tupleProperties.add(partProperty);
		}

		// check wellformedness
		Set<String> partNames = new HashSet<String>();

		for (Property tupleProperty : tupleProperties) {
			String partName = tupleProperty.getName();

			// check uniqueness of part name
			if (partNames.contains(partName)) {
				throw new WellformednessException(this,
						"All tuple literal parts of one tuple literal expression " //$NON-NLS-1$
								+ "must have unique names"); //$NON-NLS-1$
			}

			partNames.add(partName);
		}

		// get a tuple type with the corresponding properties
		type = getValidOclLibrary().makeTupleType(tupleProperties);

		return type;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {

		return ExpressionsPackageImpl.Literals.TUPLE_LITERAL_EXP;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public List<TupleLiteralPart> getPart() {

		if (part == null) {
			part =
					new EObjectContainmentEList<TupleLiteralPart>(TupleLiteralPart.class,
							this, ExpressionsPackageImpl.TUPLE_LITERAL_EXP__PART);
		}
		return part;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {

		switch (featureID) {
		case ExpressionsPackageImpl.TUPLE_LITERAL_EXP__PART:
			return ((InternalEList<?>) getPart()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {

		switch (featureID) {
		case ExpressionsPackageImpl.TUPLE_LITERAL_EXP__PART:
			return getPart();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {

		switch (featureID) {
		case ExpressionsPackageImpl.TUPLE_LITERAL_EXP__PART:
			getPart().clear();
			getPart().addAll((Collection<? extends TupleLiteralPart>) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {

		switch (featureID) {
		case ExpressionsPackageImpl.TUPLE_LITERAL_EXP__PART:
			getPart().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {

		switch (featureID) {
		case ExpressionsPackageImpl.TUPLE_LITERAL_EXP__PART:
			return part != null && !part.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // TupleLiteralExpImpl
