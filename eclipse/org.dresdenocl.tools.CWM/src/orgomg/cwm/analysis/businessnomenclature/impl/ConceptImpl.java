/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.businessnomenclature.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import orgomg.cwm.analysis.businessnomenclature.BusinessnomenclaturePackage;
import orgomg.cwm.analysis.businessnomenclature.Concept;
import orgomg.cwm.analysis.businessnomenclature.Term;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Concept</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.businessnomenclature.impl.ConceptImpl#getTerm <em>Term</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.businessnomenclature.impl.ConceptImpl#getRelatedConcept <em>Related Concept</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.businessnomenclature.impl.ConceptImpl#getConcept <em>Concept</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConceptImpl extends VocabularyElementImpl implements Concept {
	/**
	 * The cached value of the '{@link #getTerm() <em>Term</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTerm()
	 * @generated
	 * @ordered
	 */
	protected EList<Term> term;

	/**
	 * The cached value of the '{@link #getRelatedConcept() <em>Related Concept</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelatedConcept()
	 * @generated
	 * @ordered
	 */
	protected EList<Concept> relatedConcept;

	/**
	 * The cached value of the '{@link #getConcept() <em>Concept</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConcept()
	 * @generated
	 * @ordered
	 */
	protected EList<Concept> concept;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConceptImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BusinessnomenclaturePackage.Literals.CONCEPT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Term> getTerm() {
		if (term == null) {
			term = new EObjectWithInverseResolvingEList.ManyInverse<Term>(Term.class, this, BusinessnomenclaturePackage.CONCEPT__TERM, BusinessnomenclaturePackage.TERM__CONCEPT);
		}
		return term;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Concept> getRelatedConcept() {
		if (relatedConcept == null) {
			relatedConcept = new EObjectWithInverseResolvingEList.ManyInverse<Concept>(Concept.class, this, BusinessnomenclaturePackage.CONCEPT__RELATED_CONCEPT, BusinessnomenclaturePackage.CONCEPT__CONCEPT);
		}
		return relatedConcept;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Concept> getConcept() {
		if (concept == null) {
			concept = new EObjectWithInverseResolvingEList.ManyInverse<Concept>(Concept.class, this, BusinessnomenclaturePackage.CONCEPT__CONCEPT, BusinessnomenclaturePackage.CONCEPT__RELATED_CONCEPT);
		}
		return concept;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BusinessnomenclaturePackage.CONCEPT__TERM:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTerm()).basicAdd(otherEnd, msgs);
			case BusinessnomenclaturePackage.CONCEPT__RELATED_CONCEPT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getRelatedConcept()).basicAdd(otherEnd, msgs);
			case BusinessnomenclaturePackage.CONCEPT__CONCEPT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getConcept()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BusinessnomenclaturePackage.CONCEPT__TERM:
				return ((InternalEList<?>)getTerm()).basicRemove(otherEnd, msgs);
			case BusinessnomenclaturePackage.CONCEPT__RELATED_CONCEPT:
				return ((InternalEList<?>)getRelatedConcept()).basicRemove(otherEnd, msgs);
			case BusinessnomenclaturePackage.CONCEPT__CONCEPT:
				return ((InternalEList<?>)getConcept()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BusinessnomenclaturePackage.CONCEPT__TERM:
				return getTerm();
			case BusinessnomenclaturePackage.CONCEPT__RELATED_CONCEPT:
				return getRelatedConcept();
			case BusinessnomenclaturePackage.CONCEPT__CONCEPT:
				return getConcept();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case BusinessnomenclaturePackage.CONCEPT__TERM:
				getTerm().clear();
				getTerm().addAll((Collection<? extends Term>)newValue);
				return;
			case BusinessnomenclaturePackage.CONCEPT__RELATED_CONCEPT:
				getRelatedConcept().clear();
				getRelatedConcept().addAll((Collection<? extends Concept>)newValue);
				return;
			case BusinessnomenclaturePackage.CONCEPT__CONCEPT:
				getConcept().clear();
				getConcept().addAll((Collection<? extends Concept>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case BusinessnomenclaturePackage.CONCEPT__TERM:
				getTerm().clear();
				return;
			case BusinessnomenclaturePackage.CONCEPT__RELATED_CONCEPT:
				getRelatedConcept().clear();
				return;
			case BusinessnomenclaturePackage.CONCEPT__CONCEPT:
				getConcept().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case BusinessnomenclaturePackage.CONCEPT__TERM:
				return term != null && !term.isEmpty();
			case BusinessnomenclaturePackage.CONCEPT__RELATED_CONCEPT:
				return relatedConcept != null && !relatedConcept.isEmpty();
			case BusinessnomenclaturePackage.CONCEPT__CONCEPT:
				return concept != null && !concept.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ConceptImpl
