/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.businessnomenclature.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import orgomg.cwm.analysis.businessnomenclature.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BusinessnomenclatureFactoryImpl extends EFactoryImpl implements BusinessnomenclatureFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static BusinessnomenclatureFactory init() {
		try {
			BusinessnomenclatureFactory theBusinessnomenclatureFactory = (BusinessnomenclatureFactory)EPackage.Registry.INSTANCE.getEFactory("http:///orgomg/cwm/analysis/businessnomenclature.ecore"); 
			if (theBusinessnomenclatureFactory != null) {
				return theBusinessnomenclatureFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new BusinessnomenclatureFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BusinessnomenclatureFactoryImpl() {
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
			case BusinessnomenclaturePackage.VOCABULARY_ELEMENT: return createVocabularyElement();
			case BusinessnomenclaturePackage.NOMENCLATURE: return createNomenclature();
			case BusinessnomenclaturePackage.TAXONOMY: return createTaxonomy();
			case BusinessnomenclaturePackage.GLOSSARY: return createGlossary();
			case BusinessnomenclaturePackage.BUSINESS_DOMAIN: return createBusinessDomain();
			case BusinessnomenclaturePackage.CONCEPT: return createConcept();
			case BusinessnomenclaturePackage.TERM: return createTerm();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VocabularyElement createVocabularyElement() {
		VocabularyElementImpl vocabularyElement = new VocabularyElementImpl();
		return vocabularyElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Nomenclature createNomenclature() {
		NomenclatureImpl nomenclature = new NomenclatureImpl();
		return nomenclature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Taxonomy createTaxonomy() {
		TaxonomyImpl taxonomy = new TaxonomyImpl();
		return taxonomy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Glossary createGlossary() {
		GlossaryImpl glossary = new GlossaryImpl();
		return glossary;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BusinessDomain createBusinessDomain() {
		BusinessDomainImpl businessDomain = new BusinessDomainImpl();
		return businessDomain;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Concept createConcept() {
		ConceptImpl concept = new ConceptImpl();
		return concept;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Term createTerm() {
		TermImpl term = new TermImpl();
		return term;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BusinessnomenclaturePackage getBusinessnomenclaturePackage() {
		return (BusinessnomenclaturePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static BusinessnomenclaturePackage getPackage() {
		return BusinessnomenclaturePackage.eINSTANCE;
	}

} //BusinessnomenclatureFactoryImpl
