/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.businessnomenclature.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import orgomg.cwm.analysis.businessnomenclature.BusinessnomenclaturePackage;
import orgomg.cwm.analysis.businessnomenclature.Concept;
import orgomg.cwm.analysis.businessnomenclature.Term;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Term</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.businessnomenclature.impl.TermImpl#getConcept <em>Concept</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.businessnomenclature.impl.TermImpl#getRelatedTerm <em>Related Term</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.businessnomenclature.impl.TermImpl#getTerm <em>Term</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.businessnomenclature.impl.TermImpl#getPreferredTerm <em>Preferred Term</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.businessnomenclature.impl.TermImpl#getSynonym <em>Synonym</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.businessnomenclature.impl.TermImpl#getWiderTerm <em>Wider Term</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.businessnomenclature.impl.TermImpl#getNarrowerTerm <em>Narrower Term</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TermImpl extends VocabularyElementImpl implements Term {
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
	 * The cached value of the '{@link #getRelatedTerm() <em>Related Term</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelatedTerm()
	 * @generated
	 * @ordered
	 */
	protected EList<Term> relatedTerm;

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
	 * The cached value of the '{@link #getPreferredTerm() <em>Preferred Term</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreferredTerm()
	 * @generated
	 * @ordered
	 */
	protected Term preferredTerm;

	/**
	 * The cached value of the '{@link #getSynonym() <em>Synonym</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSynonym()
	 * @generated
	 * @ordered
	 */
	protected EList<Term> synonym;

	/**
	 * The cached value of the '{@link #getWiderTerm() <em>Wider Term</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWiderTerm()
	 * @generated
	 * @ordered
	 */
	protected EList<Term> widerTerm;

	/**
	 * The cached value of the '{@link #getNarrowerTerm() <em>Narrower Term</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNarrowerTerm()
	 * @generated
	 * @ordered
	 */
	protected EList<Term> narrowerTerm;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TermImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BusinessnomenclaturePackage.Literals.TERM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Concept> getConcept() {
		if (concept == null) {
			concept = new EObjectWithInverseResolvingEList.ManyInverse<Concept>(Concept.class, this, BusinessnomenclaturePackage.TERM__CONCEPT, BusinessnomenclaturePackage.CONCEPT__TERM);
		}
		return concept;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Term> getRelatedTerm() {
		if (relatedTerm == null) {
			relatedTerm = new EObjectWithInverseResolvingEList.ManyInverse<Term>(Term.class, this, BusinessnomenclaturePackage.TERM__RELATED_TERM, BusinessnomenclaturePackage.TERM__TERM);
		}
		return relatedTerm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Term> getTerm() {
		if (term == null) {
			term = new EObjectWithInverseResolvingEList.ManyInverse<Term>(Term.class, this, BusinessnomenclaturePackage.TERM__TERM, BusinessnomenclaturePackage.TERM__RELATED_TERM);
		}
		return term;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Term getPreferredTerm() {
		if (preferredTerm != null && preferredTerm.eIsProxy()) {
			InternalEObject oldPreferredTerm = (InternalEObject)preferredTerm;
			preferredTerm = (Term)eResolveProxy(oldPreferredTerm);
			if (preferredTerm != oldPreferredTerm) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BusinessnomenclaturePackage.TERM__PREFERRED_TERM, oldPreferredTerm, preferredTerm));
			}
		}
		return preferredTerm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Term basicGetPreferredTerm() {
		return preferredTerm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPreferredTerm(Term newPreferredTerm, NotificationChain msgs) {
		Term oldPreferredTerm = preferredTerm;
		preferredTerm = newPreferredTerm;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BusinessnomenclaturePackage.TERM__PREFERRED_TERM, oldPreferredTerm, newPreferredTerm);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPreferredTerm(Term newPreferredTerm) {
		if (newPreferredTerm != preferredTerm) {
			NotificationChain msgs = null;
			if (preferredTerm != null)
				msgs = ((InternalEObject)preferredTerm).eInverseRemove(this, BusinessnomenclaturePackage.TERM__SYNONYM, Term.class, msgs);
			if (newPreferredTerm != null)
				msgs = ((InternalEObject)newPreferredTerm).eInverseAdd(this, BusinessnomenclaturePackage.TERM__SYNONYM, Term.class, msgs);
			msgs = basicSetPreferredTerm(newPreferredTerm, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BusinessnomenclaturePackage.TERM__PREFERRED_TERM, newPreferredTerm, newPreferredTerm));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Term> getSynonym() {
		if (synonym == null) {
			synonym = new EObjectWithInverseResolvingEList<Term>(Term.class, this, BusinessnomenclaturePackage.TERM__SYNONYM, BusinessnomenclaturePackage.TERM__PREFERRED_TERM);
		}
		return synonym;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Term> getWiderTerm() {
		if (widerTerm == null) {
			widerTerm = new EObjectWithInverseResolvingEList.ManyInverse<Term>(Term.class, this, BusinessnomenclaturePackage.TERM__WIDER_TERM, BusinessnomenclaturePackage.TERM__NARROWER_TERM);
		}
		return widerTerm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Term> getNarrowerTerm() {
		if (narrowerTerm == null) {
			narrowerTerm = new EObjectWithInverseResolvingEList.ManyInverse<Term>(Term.class, this, BusinessnomenclaturePackage.TERM__NARROWER_TERM, BusinessnomenclaturePackage.TERM__WIDER_TERM);
		}
		return narrowerTerm;
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
			case BusinessnomenclaturePackage.TERM__CONCEPT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getConcept()).basicAdd(otherEnd, msgs);
			case BusinessnomenclaturePackage.TERM__RELATED_TERM:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getRelatedTerm()).basicAdd(otherEnd, msgs);
			case BusinessnomenclaturePackage.TERM__TERM:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTerm()).basicAdd(otherEnd, msgs);
			case BusinessnomenclaturePackage.TERM__PREFERRED_TERM:
				if (preferredTerm != null)
					msgs = ((InternalEObject)preferredTerm).eInverseRemove(this, BusinessnomenclaturePackage.TERM__SYNONYM, Term.class, msgs);
				return basicSetPreferredTerm((Term)otherEnd, msgs);
			case BusinessnomenclaturePackage.TERM__SYNONYM:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSynonym()).basicAdd(otherEnd, msgs);
			case BusinessnomenclaturePackage.TERM__WIDER_TERM:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getWiderTerm()).basicAdd(otherEnd, msgs);
			case BusinessnomenclaturePackage.TERM__NARROWER_TERM:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getNarrowerTerm()).basicAdd(otherEnd, msgs);
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
			case BusinessnomenclaturePackage.TERM__CONCEPT:
				return ((InternalEList<?>)getConcept()).basicRemove(otherEnd, msgs);
			case BusinessnomenclaturePackage.TERM__RELATED_TERM:
				return ((InternalEList<?>)getRelatedTerm()).basicRemove(otherEnd, msgs);
			case BusinessnomenclaturePackage.TERM__TERM:
				return ((InternalEList<?>)getTerm()).basicRemove(otherEnd, msgs);
			case BusinessnomenclaturePackage.TERM__PREFERRED_TERM:
				return basicSetPreferredTerm(null, msgs);
			case BusinessnomenclaturePackage.TERM__SYNONYM:
				return ((InternalEList<?>)getSynonym()).basicRemove(otherEnd, msgs);
			case BusinessnomenclaturePackage.TERM__WIDER_TERM:
				return ((InternalEList<?>)getWiderTerm()).basicRemove(otherEnd, msgs);
			case BusinessnomenclaturePackage.TERM__NARROWER_TERM:
				return ((InternalEList<?>)getNarrowerTerm()).basicRemove(otherEnd, msgs);
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
			case BusinessnomenclaturePackage.TERM__CONCEPT:
				return getConcept();
			case BusinessnomenclaturePackage.TERM__RELATED_TERM:
				return getRelatedTerm();
			case BusinessnomenclaturePackage.TERM__TERM:
				return getTerm();
			case BusinessnomenclaturePackage.TERM__PREFERRED_TERM:
				if (resolve) return getPreferredTerm();
				return basicGetPreferredTerm();
			case BusinessnomenclaturePackage.TERM__SYNONYM:
				return getSynonym();
			case BusinessnomenclaturePackage.TERM__WIDER_TERM:
				return getWiderTerm();
			case BusinessnomenclaturePackage.TERM__NARROWER_TERM:
				return getNarrowerTerm();
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
			case BusinessnomenclaturePackage.TERM__CONCEPT:
				getConcept().clear();
				getConcept().addAll((Collection<? extends Concept>)newValue);
				return;
			case BusinessnomenclaturePackage.TERM__RELATED_TERM:
				getRelatedTerm().clear();
				getRelatedTerm().addAll((Collection<? extends Term>)newValue);
				return;
			case BusinessnomenclaturePackage.TERM__TERM:
				getTerm().clear();
				getTerm().addAll((Collection<? extends Term>)newValue);
				return;
			case BusinessnomenclaturePackage.TERM__PREFERRED_TERM:
				setPreferredTerm((Term)newValue);
				return;
			case BusinessnomenclaturePackage.TERM__SYNONYM:
				getSynonym().clear();
				getSynonym().addAll((Collection<? extends Term>)newValue);
				return;
			case BusinessnomenclaturePackage.TERM__WIDER_TERM:
				getWiderTerm().clear();
				getWiderTerm().addAll((Collection<? extends Term>)newValue);
				return;
			case BusinessnomenclaturePackage.TERM__NARROWER_TERM:
				getNarrowerTerm().clear();
				getNarrowerTerm().addAll((Collection<? extends Term>)newValue);
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
			case BusinessnomenclaturePackage.TERM__CONCEPT:
				getConcept().clear();
				return;
			case BusinessnomenclaturePackage.TERM__RELATED_TERM:
				getRelatedTerm().clear();
				return;
			case BusinessnomenclaturePackage.TERM__TERM:
				getTerm().clear();
				return;
			case BusinessnomenclaturePackage.TERM__PREFERRED_TERM:
				setPreferredTerm((Term)null);
				return;
			case BusinessnomenclaturePackage.TERM__SYNONYM:
				getSynonym().clear();
				return;
			case BusinessnomenclaturePackage.TERM__WIDER_TERM:
				getWiderTerm().clear();
				return;
			case BusinessnomenclaturePackage.TERM__NARROWER_TERM:
				getNarrowerTerm().clear();
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
			case BusinessnomenclaturePackage.TERM__CONCEPT:
				return concept != null && !concept.isEmpty();
			case BusinessnomenclaturePackage.TERM__RELATED_TERM:
				return relatedTerm != null && !relatedTerm.isEmpty();
			case BusinessnomenclaturePackage.TERM__TERM:
				return term != null && !term.isEmpty();
			case BusinessnomenclaturePackage.TERM__PREFERRED_TERM:
				return preferredTerm != null;
			case BusinessnomenclaturePackage.TERM__SYNONYM:
				return synonym != null && !synonym.isEmpty();
			case BusinessnomenclaturePackage.TERM__WIDER_TERM:
				return widerTerm != null && !widerTerm.isEmpty();
			case BusinessnomenclaturePackage.TERM__NARROWER_TERM:
				return narrowerTerm != null && !narrowerTerm.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TermImpl
