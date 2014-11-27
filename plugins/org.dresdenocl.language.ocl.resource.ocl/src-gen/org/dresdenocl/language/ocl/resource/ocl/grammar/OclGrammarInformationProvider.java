/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.grammar;

import java.lang.reflect.Field;
import java.util.LinkedHashSet;
import java.util.Set;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;

public class OclGrammarInformationProvider {
	
	public final static EStructuralFeature ANONYMOUS_FEATURE = EcoreFactory.eINSTANCE.createEAttribute();
	static {
		ANONYMOUS_FEATURE.setName("_");
	}
	
	public final static OclGrammarInformationProvider INSTANCE = new OclGrammarInformationProvider();
	
	private Set<String> keywords;
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_0_0_0_0 = INSTANCE.getOCL_0_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_0_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getSimpleNameCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.SIMPLE_NAME_CS__SIMPLE_NAME), "ITERATOR_NAME", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_0_0_0 = INSTANCE.getOCL_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_0_0_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_0_0_1_0 = INSTANCE.getOCL_0_0_1_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_0_0_1_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getSimpleNameCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.SIMPLE_NAME_CS__SIMPLE_NAME), "SIMPLE_NAME", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_0_0_1 = INSTANCE.getOCL_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_0_0_1_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_0_0 = INSTANCE.getOCL_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_0_0_0, OCL_0_0_1);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_0 = INSTANCE.getOCL_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getSimpleNameCS(), OCL_0_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_1_0_0_0 = INSTANCE.getOCL_1_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_1_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("package", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_1_0_0_1 = INSTANCE.getOCL_1_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_1_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PACKAGE_DECLARATION_WITH_NAMESPACE_CS__NESTED_NAMESPACE), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationNestedNamespaceCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak OCL_1_0_0_2 = INSTANCE.getOCL_1_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak getOCL_1_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_1_0_0_3_0_0_0 = INSTANCE.getOCL_1_0_0_3_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_1_0_0_3_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PACKAGE_DECLARATION_WITH_NAMESPACE_CS__CONTEXT_DECLARATIONS), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getContextDeclarationCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_1_0_0_3_0_0 = INSTANCE.getOCL_1_0_0_3_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_1_0_0_3_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_1_0_0_3_0_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_1_0_0_3_0 = INSTANCE.getOCL_1_0_0_3_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_1_0_0_3_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_1_0_0_3_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound OCL_1_0_0_3 = INSTANCE.getOCL_1_0_0_3();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound getOCL_1_0_0_3() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound(OCL_1_0_0_3_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.STAR);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak OCL_1_0_0_4 = INSTANCE.getOCL_1_0_0_4();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak getOCL_1_0_0_4() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_1_0_0_5 = INSTANCE.getOCL_1_0_0_5();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_1_0_0_5() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("endpackage", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_1_0_0 = INSTANCE.getOCL_1_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_1_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_1_0_0_0, OCL_1_0_0_1, OCL_1_0_0_2, OCL_1_0_0_3, OCL_1_0_0_4, OCL_1_0_0_5);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_1_0 = INSTANCE.getOCL_1_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_1_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_1_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_1 = INSTANCE.getOCL_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(), OCL_1_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_2_0_0_0 = INSTANCE.getOCL_2_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_2_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationNestedNamespaceCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PACKAGE_DECLARATION_NESTED_NAMESPACE_CS__NAMESPACE), "SIMPLE_NAME", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_2_0_0_1_0_0_0 = INSTANCE.getOCL_2_0_0_1_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_2_0_0_1_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("::", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_2_0_0_1_0_0_1 = INSTANCE.getOCL_2_0_0_1_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_2_0_0_1_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationNestedNamespaceCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PACKAGE_DECLARATION_NESTED_NAMESPACE_CS__NESTED_NAMESPACE), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationNestedNamespaceCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_2_0_0_1_0_0 = INSTANCE.getOCL_2_0_0_1_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_2_0_0_1_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_2_0_0_1_0_0_0, OCL_2_0_0_1_0_0_1);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_2_0_0_1_0 = INSTANCE.getOCL_2_0_0_1_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_2_0_0_1_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_2_0_0_1_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound OCL_2_0_0_1 = INSTANCE.getOCL_2_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound getOCL_2_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound(OCL_2_0_0_1_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.QUESTIONMARK);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_2_0_0 = INSTANCE.getOCL_2_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_2_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_2_0_0_0, OCL_2_0_0_1);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_2_0 = INSTANCE.getOCL_2_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_2_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_2_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_2 = INSTANCE.getOCL_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationNestedNamespaceCS(), OCL_2_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_3_0_0_0 = INSTANCE.getOCL_3_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_3_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithoutNamespaceCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PACKAGE_DECLARATION_WITHOUT_NAMESPACE_CS__CONTEXT_DECLARATIONS), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.STAR, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getContextDeclarationCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_3_0_0 = INSTANCE.getOCL_3_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_3_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_3_0_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_3_0 = INSTANCE.getOCL_3_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_3_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_3_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_3 = INSTANCE.getOCL_3();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_3() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithoutNamespaceCS(), OCL_3_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_4_0_0_0 = INSTANCE.getOCL_4_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_4_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("context", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_4_0_0_1 = INSTANCE.getOCL_4_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_4_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CONTEXT_DECLARATION_CS__OPERATION), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationDefinitionInContextCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak OCL_4_0_0_2 = INSTANCE.getOCL_4_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak getOCL_4_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_4_0_0_3 = INSTANCE.getOCL_4_0_0_3();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_4_0_0_3() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CONTEXT_DECLARATION_CS__PRE_POST_OR_BODY_DECLARATIONS), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.PLUS, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPrePostOrBodyDeclarationCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_4_0_0 = INSTANCE.getOCL_4_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_4_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_4_0_0_0, OCL_4_0_0_1, OCL_4_0_0_2, OCL_4_0_0_3);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_4_0 = INSTANCE.getOCL_4_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_4_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_4_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_4 = INSTANCE.getOCL_4();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_4() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(), OCL_4_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_5_0_0_0 = INSTANCE.getOCL_5_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_5_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("context", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_5_0_0_1 = INSTANCE.getOCL_5_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_5_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__TYPE_NAME), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getModelElementCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_5_0_0_2_0_0_0 = INSTANCE.getOCL_5_0_0_2_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_5_0_0_2_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword(":", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_5_0_0_2_0_0_1 = INSTANCE.getOCL_5_0_0_2_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_5_0_0_2_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__TYPE), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getTypeCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_5_0_0_2_0_0 = INSTANCE.getOCL_5_0_0_2_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_5_0_0_2_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_5_0_0_2_0_0_0, OCL_5_0_0_2_0_0_1);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_5_0_0_2_0 = INSTANCE.getOCL_5_0_0_2_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_5_0_0_2_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_5_0_0_2_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound OCL_5_0_0_2 = INSTANCE.getOCL_5_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound getOCL_5_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound(OCL_5_0_0_2_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.QUESTIONMARK);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak OCL_5_0_0_3 = INSTANCE.getOCL_5_0_0_3();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak getOCL_5_0_0_3() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_5_0_0_4 = INSTANCE.getOCL_5_0_0_4();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_5_0_0_4() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__INIT_OR_DERIVE_VALUE), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInitOrDeriveValueCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_5_0_0_5 = INSTANCE.getOCL_5_0_0_5();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_5_0_0_5() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__INIT_OR_DERIVE_VALUE), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.QUESTIONMARK, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInitOrDeriveValueCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_5_0_0 = INSTANCE.getOCL_5_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_5_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_5_0_0_0, OCL_5_0_0_1, OCL_5_0_0_2, OCL_5_0_0_3, OCL_5_0_0_4, OCL_5_0_0_5);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_5_0 = INSTANCE.getOCL_5_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_5_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_5_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_5 = INSTANCE.getOCL_5();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_5() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(), OCL_5_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_6_0_0_0 = INSTANCE.getOCL_6_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_6_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("context", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_6_0_0_1 = INSTANCE.getOCL_6_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_6_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.CLASSIFIER_CONTEXT_DECLARATION_CS__TYPE_NAME), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getModelElementCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak OCL_6_0_0_2 = INSTANCE.getOCL_6_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak getOCL_6_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_6_0_0_3 = INSTANCE.getOCL_6_0_0_3();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_6_0_0_3() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.CLASSIFIER_CONTEXT_DECLARATION_CS__INVARIANTS_AND_DEFINITIONS), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.PLUS, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInvariantOrDefinitionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_6_0_0 = INSTANCE.getOCL_6_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_6_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_6_0_0_0, OCL_6_0_0_1, OCL_6_0_0_2, OCL_6_0_0_3);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_6_0 = INSTANCE.getOCL_6_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_6_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_6_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_6 = INSTANCE.getOCL_6();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_6() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(), OCL_6_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_7_0_0_0 = INSTANCE.getOCL_7_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_7_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("init", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_7_0_0_1 = INSTANCE.getOCL_7_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_7_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_7_0_0_2 = INSTANCE.getOCL_7_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_7_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword(":", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_7_0_0_3 = INSTANCE.getOCL_7_0_0_3();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_7_0_0_3() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInitValueCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.INIT_VALUE_CS__OCL_EXPRESSION), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_7_0_0 = INSTANCE.getOCL_7_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_7_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_7_0_0_0, OCL_7_0_0_1, OCL_7_0_0_2, OCL_7_0_0_3);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_7_0 = INSTANCE.getOCL_7_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_7_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_7_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_7 = INSTANCE.getOCL_7();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_7() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInitValueCS(), OCL_7_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_8_0_0_0 = INSTANCE.getOCL_8_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_8_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("derive", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_8_0_0_1 = INSTANCE.getOCL_8_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_8_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_8_0_0_2 = INSTANCE.getOCL_8_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_8_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword(":", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_8_0_0_3 = INSTANCE.getOCL_8_0_0_3();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_8_0_0_3() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDeriveValueCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.DERIVE_VALUE_CS__OCL_EXPRESSION), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_8_0_0 = INSTANCE.getOCL_8_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_8_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_8_0_0_0, OCL_8_0_0_1, OCL_8_0_0_2, OCL_8_0_0_3);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_8_0 = INSTANCE.getOCL_8_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_8_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_8_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_8 = INSTANCE.getOCL_8();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_8() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDeriveValueCS(), OCL_8_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_9_0_0_0 = INSTANCE.getOCL_9_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_9_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("inv", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_9_0_0_1 = INSTANCE.getOCL_9_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_9_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInvariantExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.INVARIANT_EXP_CS__NAME), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.QUESTIONMARK, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getSimpleNameCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_9_0_0_2 = INSTANCE.getOCL_9_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_9_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_9_0_0_3 = INSTANCE.getOCL_9_0_0_3();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_9_0_0_3() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword(":", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_9_0_0_4 = INSTANCE.getOCL_9_0_0_4();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_9_0_0_4() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInvariantExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.INVARIANT_EXP_CS__OCL_EXPRESSION), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_9_0_0 = INSTANCE.getOCL_9_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_9_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_9_0_0_0, OCL_9_0_0_1, OCL_9_0_0_2, OCL_9_0_0_3, OCL_9_0_0_4);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_9_0 = INSTANCE.getOCL_9_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_9_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_9_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_9 = INSTANCE.getOCL_9();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_9() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInvariantExpCS(), OCL_9_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_10_0_0_0_0_0_0 = INSTANCE.getOCL_10_0_0_0_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_10_0_0_0_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.DEFINITION_EXP_CS__STATIC), "STATIC", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_10_0_0_0_0_0 = INSTANCE.getOCL_10_0_0_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_10_0_0_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_10_0_0_0_0_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_10_0_0_0_0 = INSTANCE.getOCL_10_0_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_10_0_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_10_0_0_0_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound OCL_10_0_0_0 = INSTANCE.getOCL_10_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound getOCL_10_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound(OCL_10_0_0_0_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.QUESTIONMARK);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_10_0_0_1 = INSTANCE.getOCL_10_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_10_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("def", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_10_0_0_2 = INSTANCE.getOCL_10_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_10_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_10_0_0_3 = INSTANCE.getOCL_10_0_0_3();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_10_0_0_3() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword(":", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_10_0_0_4 = INSTANCE.getOCL_10_0_0_4();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_10_0_0_4() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.DEFINITION_EXP_CS__DEFINITION_EXP_PART), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpPartCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_10_0_0 = INSTANCE.getOCL_10_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_10_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_10_0_0_0, OCL_10_0_0_1, OCL_10_0_0_2, OCL_10_0_0_3, OCL_10_0_0_4);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_10_0 = INSTANCE.getOCL_10_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_10_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_10_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_10 = INSTANCE.getOCL_10();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_10() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpCS(), OCL_10_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_11_0_0_0 = INSTANCE.getOCL_11_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_11_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpPropertyCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.DEFINITION_EXP_PROPERTY_CS__VARIABLE_DECLARATION), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_11_0_0 = INSTANCE.getOCL_11_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_11_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_11_0_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_11_0 = INSTANCE.getOCL_11_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_11_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_11_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_11 = INSTANCE.getOCL_11();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_11() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpPropertyCS(), OCL_11_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_12_0_0_0 = INSTANCE.getOCL_12_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_12_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpOperationCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.DEFINITION_EXP_OPERATION_CS__OPERATION), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationDefinitionInDefCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_12_0_0_1 = INSTANCE.getOCL_12_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_12_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpOperationCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.DEFINITION_EXP_OPERATION_CS__EQUAL), "EQUALITY_OPERATOR", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_12_0_0_2 = INSTANCE.getOCL_12_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_12_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpOperationCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.DEFINITION_EXP_OPERATION_CS__OCL_EXPRESSION), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_12_0_0 = INSTANCE.getOCL_12_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_12_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_12_0_0_0, OCL_12_0_0_1, OCL_12_0_0_2);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_12_0 = INSTANCE.getOCL_12_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_12_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_12_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_12 = INSTANCE.getOCL_12();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_12() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpOperationCS(), OCL_12_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_13_0_0_0 = INSTANCE.getOCL_13_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_13_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("pre", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_13_0_0_1_0_0_0 = INSTANCE.getOCL_13_0_0_1_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_13_0_0_1_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPreConditionDeclarationCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PRE_CONDITION_DECLARATION_CS__NAME), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getSimpleNameCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_13_0_0_1_0_0 = INSTANCE.getOCL_13_0_0_1_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_13_0_0_1_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_13_0_0_1_0_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_13_0_0_1_0 = INSTANCE.getOCL_13_0_0_1_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_13_0_0_1_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_13_0_0_1_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound OCL_13_0_0_1 = INSTANCE.getOCL_13_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound getOCL_13_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound(OCL_13_0_0_1_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.QUESTIONMARK);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_13_0_0_2 = INSTANCE.getOCL_13_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_13_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_13_0_0_3 = INSTANCE.getOCL_13_0_0_3();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_13_0_0_3() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword(":", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_13_0_0_4 = INSTANCE.getOCL_13_0_0_4();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_13_0_0_4() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPreConditionDeclarationCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PRE_CONDITION_DECLARATION_CS__OCL_EXPRESSION), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_13_0_0 = INSTANCE.getOCL_13_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_13_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_13_0_0_0, OCL_13_0_0_1, OCL_13_0_0_2, OCL_13_0_0_3, OCL_13_0_0_4);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_13_0 = INSTANCE.getOCL_13_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_13_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_13_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_13 = INSTANCE.getOCL_13();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_13() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPreConditionDeclarationCS(), OCL_13_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_14_0_0_0 = INSTANCE.getOCL_14_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_14_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("post", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_14_0_0_1_0_0_0 = INSTANCE.getOCL_14_0_0_1_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_14_0_0_1_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPostConditionDeclarationCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.POST_CONDITION_DECLARATION_CS__NAME), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getSimpleNameCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_14_0_0_1_0_0 = INSTANCE.getOCL_14_0_0_1_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_14_0_0_1_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_14_0_0_1_0_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_14_0_0_1_0 = INSTANCE.getOCL_14_0_0_1_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_14_0_0_1_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_14_0_0_1_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound OCL_14_0_0_1 = INSTANCE.getOCL_14_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound getOCL_14_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound(OCL_14_0_0_1_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.QUESTIONMARK);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_14_0_0_2 = INSTANCE.getOCL_14_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_14_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_14_0_0_3 = INSTANCE.getOCL_14_0_0_3();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_14_0_0_3() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword(":", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_14_0_0_4 = INSTANCE.getOCL_14_0_0_4();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_14_0_0_4() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPostConditionDeclarationCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.POST_CONDITION_DECLARATION_CS__OCL_EXPRESSION), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_14_0_0 = INSTANCE.getOCL_14_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_14_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_14_0_0_0, OCL_14_0_0_1, OCL_14_0_0_2, OCL_14_0_0_3, OCL_14_0_0_4);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_14_0 = INSTANCE.getOCL_14_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_14_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_14_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_14 = INSTANCE.getOCL_14();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_14() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPostConditionDeclarationCS(), OCL_14_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_15_0_0_0 = INSTANCE.getOCL_15_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_15_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("body", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_15_0_0_1_0_0_0 = INSTANCE.getOCL_15_0_0_1_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_15_0_0_1_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBodyDeclarationCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.BODY_DECLARATION_CS__NAME), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getSimpleNameCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_15_0_0_1_0_0 = INSTANCE.getOCL_15_0_0_1_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_15_0_0_1_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_15_0_0_1_0_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_15_0_0_1_0 = INSTANCE.getOCL_15_0_0_1_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_15_0_0_1_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_15_0_0_1_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound OCL_15_0_0_1 = INSTANCE.getOCL_15_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound getOCL_15_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound(OCL_15_0_0_1_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.QUESTIONMARK);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_15_0_0_2 = INSTANCE.getOCL_15_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_15_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_15_0_0_3 = INSTANCE.getOCL_15_0_0_3();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_15_0_0_3() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword(":", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_15_0_0_4 = INSTANCE.getOCL_15_0_0_4();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_15_0_0_4() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBodyDeclarationCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.BODY_DECLARATION_CS__OCL_EXPRESSION), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_15_0_0 = INSTANCE.getOCL_15_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_15_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_15_0_0_0, OCL_15_0_0_1, OCL_15_0_0_2, OCL_15_0_0_3, OCL_15_0_0_4);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_15_0 = INSTANCE.getOCL_15_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_15_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_15_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_15 = INSTANCE.getOCL_15();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_15() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBodyDeclarationCS(), OCL_15_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_16_0_0_0 = INSTANCE.getOCL_16_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_16_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationDefinitionInContextCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_CONTEXT_CS__TYPE_NAME), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getModelElementCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_16_0_0_1 = INSTANCE.getOCL_16_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_16_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_16_0_0_2 = INSTANCE.getOCL_16_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_16_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("::", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_16_0_0_3 = INSTANCE.getOCL_16_0_0_3();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_16_0_0_3() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_16_0_0_4 = INSTANCE.getOCL_16_0_0_4();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_16_0_0_4() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationDefinitionInContextCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_CONTEXT_CS__OPERATION), "SIMPLE_NAME", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_16_0_0_5 = INSTANCE.getOCL_16_0_0_5();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_16_0_0_5() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_16_0_0_6 = INSTANCE.getOCL_16_0_0_6();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_16_0_0_6() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("(", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_16_0_0_7 = INSTANCE.getOCL_16_0_0_7();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_16_0_0_7() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_16_0_0_8_0_0_0 = INSTANCE.getOCL_16_0_0_8_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_16_0_0_8_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationDefinitionInContextCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_CONTEXT_CS__PARAMETERS), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getParameterCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_16_0_0_8_0_0_1_0_0_0 = INSTANCE.getOCL_16_0_0_8_0_0_1_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_16_0_0_8_0_0_1_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_16_0_0_8_0_0_1_0_0_1 = INSTANCE.getOCL_16_0_0_8_0_0_1_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_16_0_0_8_0_0_1_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword(",", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_16_0_0_8_0_0_1_0_0_2 = INSTANCE.getOCL_16_0_0_8_0_0_1_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_16_0_0_8_0_0_1_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationDefinitionInContextCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_CONTEXT_CS__PARAMETERS), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getParameterCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_16_0_0_8_0_0_1_0_0 = INSTANCE.getOCL_16_0_0_8_0_0_1_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_16_0_0_8_0_0_1_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_16_0_0_8_0_0_1_0_0_0, OCL_16_0_0_8_0_0_1_0_0_1, OCL_16_0_0_8_0_0_1_0_0_2);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_16_0_0_8_0_0_1_0 = INSTANCE.getOCL_16_0_0_8_0_0_1_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_16_0_0_8_0_0_1_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_16_0_0_8_0_0_1_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound OCL_16_0_0_8_0_0_1 = INSTANCE.getOCL_16_0_0_8_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound getOCL_16_0_0_8_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound(OCL_16_0_0_8_0_0_1_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.STAR);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_16_0_0_8_0_0 = INSTANCE.getOCL_16_0_0_8_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_16_0_0_8_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_16_0_0_8_0_0_0, OCL_16_0_0_8_0_0_1);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_16_0_0_8_0 = INSTANCE.getOCL_16_0_0_8_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_16_0_0_8_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_16_0_0_8_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound OCL_16_0_0_8 = INSTANCE.getOCL_16_0_0_8();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound getOCL_16_0_0_8() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound(OCL_16_0_0_8_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.QUESTIONMARK);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_16_0_0_9 = INSTANCE.getOCL_16_0_0_9();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_16_0_0_9() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_16_0_0_10 = INSTANCE.getOCL_16_0_0_10();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_16_0_0_10() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword(")", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_16_0_0_11_0_0_0 = INSTANCE.getOCL_16_0_0_11_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_16_0_0_11_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword(":", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_16_0_0_11_0_0_1 = INSTANCE.getOCL_16_0_0_11_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_16_0_0_11_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationDefinitionInContextCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_CONTEXT_CS__RETURN_TYPE), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getTypeCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_16_0_0_11_0_0 = INSTANCE.getOCL_16_0_0_11_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_16_0_0_11_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_16_0_0_11_0_0_0, OCL_16_0_0_11_0_0_1);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_16_0_0_11_0 = INSTANCE.getOCL_16_0_0_11_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_16_0_0_11_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_16_0_0_11_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound OCL_16_0_0_11 = INSTANCE.getOCL_16_0_0_11();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound getOCL_16_0_0_11() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound(OCL_16_0_0_11_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.QUESTIONMARK);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_16_0_0 = INSTANCE.getOCL_16_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_16_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_16_0_0_0, OCL_16_0_0_1, OCL_16_0_0_2, OCL_16_0_0_3, OCL_16_0_0_4, OCL_16_0_0_5, OCL_16_0_0_6, OCL_16_0_0_7, OCL_16_0_0_8, OCL_16_0_0_9, OCL_16_0_0_10, OCL_16_0_0_11);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_16_0 = INSTANCE.getOCL_16_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_16_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_16_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_16 = INSTANCE.getOCL_16();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_16() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationDefinitionInContextCS(), OCL_16_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_17_0_0_0 = INSTANCE.getOCL_17_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_17_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationDefinitionInDefCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_DEF_CS__OPERATION), "SIMPLE_NAME", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_17_0_0_1 = INSTANCE.getOCL_17_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_17_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("(", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_17_0_0_2_0_0_0 = INSTANCE.getOCL_17_0_0_2_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_17_0_0_2_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationDefinitionInDefCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_DEF_CS__PARAMETERS), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getParameterCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_17_0_0_2_0_0_1_0_0_0 = INSTANCE.getOCL_17_0_0_2_0_0_1_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_17_0_0_2_0_0_1_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword(",", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_17_0_0_2_0_0_1_0_0_1 = INSTANCE.getOCL_17_0_0_2_0_0_1_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_17_0_0_2_0_0_1_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationDefinitionInDefCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_DEF_CS__PARAMETERS), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getParameterCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_17_0_0_2_0_0_1_0_0 = INSTANCE.getOCL_17_0_0_2_0_0_1_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_17_0_0_2_0_0_1_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_17_0_0_2_0_0_1_0_0_0, OCL_17_0_0_2_0_0_1_0_0_1);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_17_0_0_2_0_0_1_0 = INSTANCE.getOCL_17_0_0_2_0_0_1_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_17_0_0_2_0_0_1_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_17_0_0_2_0_0_1_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound OCL_17_0_0_2_0_0_1 = INSTANCE.getOCL_17_0_0_2_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound getOCL_17_0_0_2_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound(OCL_17_0_0_2_0_0_1_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.STAR);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_17_0_0_2_0_0 = INSTANCE.getOCL_17_0_0_2_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_17_0_0_2_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_17_0_0_2_0_0_0, OCL_17_0_0_2_0_0_1);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_17_0_0_2_0 = INSTANCE.getOCL_17_0_0_2_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_17_0_0_2_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_17_0_0_2_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound OCL_17_0_0_2 = INSTANCE.getOCL_17_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound getOCL_17_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound(OCL_17_0_0_2_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.QUESTIONMARK);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_17_0_0_3 = INSTANCE.getOCL_17_0_0_3();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_17_0_0_3() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword(")", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_17_0_0_4_0_0_0 = INSTANCE.getOCL_17_0_0_4_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_17_0_0_4_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword(":", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_17_0_0_4_0_0_1 = INSTANCE.getOCL_17_0_0_4_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_17_0_0_4_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationDefinitionInDefCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_DEF_CS__RETURN_TYPE), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getTypeCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_17_0_0_4_0_0 = INSTANCE.getOCL_17_0_0_4_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_17_0_0_4_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_17_0_0_4_0_0_0, OCL_17_0_0_4_0_0_1);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_17_0_0_4_0 = INSTANCE.getOCL_17_0_0_4_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_17_0_0_4_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_17_0_0_4_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound OCL_17_0_0_4 = INSTANCE.getOCL_17_0_0_4();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound getOCL_17_0_0_4() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound(OCL_17_0_0_4_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.QUESTIONMARK);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_17_0_0 = INSTANCE.getOCL_17_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_17_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_17_0_0_0, OCL_17_0_0_1, OCL_17_0_0_2, OCL_17_0_0_3, OCL_17_0_0_4);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_17_0 = INSTANCE.getOCL_17_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_17_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_17_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_17 = INSTANCE.getOCL_17();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_17() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationDefinitionInDefCS(), OCL_17_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_18_0_0_0 = INSTANCE.getOCL_18_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_18_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getParameterCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PARAMETER_CS__PARAMETER), "SIMPLE_NAME", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_18_0_0_1 = INSTANCE.getOCL_18_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_18_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword(":", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_18_0_0_2 = INSTANCE.getOCL_18_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_18_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getParameterCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PARAMETER_CS__PARAMETER_TYPE), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getTypeCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_18_0_0 = INSTANCE.getOCL_18_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_18_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_18_0_0_0, OCL_18_0_0_1, OCL_18_0_0_2);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_18_0 = INSTANCE.getOCL_18_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_18_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_18_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_18 = INSTANCE.getOCL_18();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_18() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getParameterCS(), OCL_18_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_19_0_0_0 = INSTANCE.getOCL_19_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_19_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalImpliesOperationCallExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_IMPLIES_OPERATION_CALL_EXP_CS__SOURCE), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_19_0_0_1 = INSTANCE.getOCL_19_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_19_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalImpliesOperationCallExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_IMPLIES_OPERATION_CALL_EXP_CS__OPERATION_NAME), "IMPLIES_OPERATOR", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_19_0_0_2 = INSTANCE.getOCL_19_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_19_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalImpliesOperationCallExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_IMPLIES_OPERATION_CALL_EXP_CS__TARGET), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_19_0_0 = INSTANCE.getOCL_19_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_19_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_19_0_0_0, OCL_19_0_0_1, OCL_19_0_0_2);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_19_0 = INSTANCE.getOCL_19_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_19_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_19_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_19 = INSTANCE.getOCL_19();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_19() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalImpliesOperationCallExpCS(), OCL_19_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_20_0_0_0 = INSTANCE.getOCL_20_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_20_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalXorOperationCallExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_XOR_OPERATION_CALL_EXP_CS__SOURCE), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_20_0_0_1 = INSTANCE.getOCL_20_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_20_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalXorOperationCallExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_XOR_OPERATION_CALL_EXP_CS__OPERATION_NAME), "XOR_OPERATOR", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_20_0_0_2 = INSTANCE.getOCL_20_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_20_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalXorOperationCallExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_XOR_OPERATION_CALL_EXP_CS__TARGET), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_20_0_0 = INSTANCE.getOCL_20_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_20_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_20_0_0_0, OCL_20_0_0_1, OCL_20_0_0_2);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_20_0 = INSTANCE.getOCL_20_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_20_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_20_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_20 = INSTANCE.getOCL_20();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_20() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalXorOperationCallExpCS(), OCL_20_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_21_0_0_0 = INSTANCE.getOCL_21_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_21_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalOrOperationCallExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_OR_OPERATION_CALL_EXP_CS__SOURCE), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_21_0_0_1 = INSTANCE.getOCL_21_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_21_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalOrOperationCallExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_OR_OPERATION_CALL_EXP_CS__OPERATION_NAME), "OR_OPERATOR", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_21_0_0_2 = INSTANCE.getOCL_21_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_21_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalOrOperationCallExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_OR_OPERATION_CALL_EXP_CS__TARGET), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_21_0_0 = INSTANCE.getOCL_21_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_21_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_21_0_0_0, OCL_21_0_0_1, OCL_21_0_0_2);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_21_0 = INSTANCE.getOCL_21_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_21_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_21_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_21 = INSTANCE.getOCL_21();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_21() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalOrOperationCallExpCS(), OCL_21_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_22_0_0_0 = INSTANCE.getOCL_22_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_22_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalAndOperationCallExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_AND_OPERATION_CALL_EXP_CS__SOURCE), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_22_0_0_1 = INSTANCE.getOCL_22_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_22_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalAndOperationCallExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_AND_OPERATION_CALL_EXP_CS__OPERATION_NAME), "AND_OPERATOR", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_22_0_0_2 = INSTANCE.getOCL_22_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_22_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalAndOperationCallExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_AND_OPERATION_CALL_EXP_CS__TARGET), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_22_0_0 = INSTANCE.getOCL_22_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_22_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_22_0_0_0, OCL_22_0_0_1, OCL_22_0_0_2);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_22_0 = INSTANCE.getOCL_22_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_22_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_22_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_22 = INSTANCE.getOCL_22();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_22() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalAndOperationCallExpCS(), OCL_22_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_23_0_0_0 = INSTANCE.getOCL_23_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_23_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.EQUALITY_OPERATION_CALL_EXP_CS__SOURCE), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_23_0_0_1_0_0_0 = INSTANCE.getOCL_23_0_0_1_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_23_0_0_1_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.EQUALITY_OPERATION_CALL_EXP_CS__OPERATION_NAME), "EQUALITY_OPERATOR", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_23_0_0_1_0_0 = INSTANCE.getOCL_23_0_0_1_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_23_0_0_1_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_23_0_0_1_0_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_23_0_0_1_0_1_0 = INSTANCE.getOCL_23_0_0_1_0_1_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_23_0_0_1_0_1_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.EQUALITY_OPERATION_CALL_EXP_CS__OPERATION_NAME), "NEQUALITY_OPERATOR", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_23_0_0_1_0_1 = INSTANCE.getOCL_23_0_0_1_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_23_0_0_1_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_23_0_0_1_0_1_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_23_0_0_1_0 = INSTANCE.getOCL_23_0_0_1_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_23_0_0_1_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_23_0_0_1_0_0, OCL_23_0_0_1_0_1);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound OCL_23_0_0_1 = INSTANCE.getOCL_23_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound getOCL_23_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound(OCL_23_0_0_1_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_23_0_0_2 = INSTANCE.getOCL_23_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_23_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.EQUALITY_OPERATION_CALL_EXP_CS__TARGET), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_23_0_0 = INSTANCE.getOCL_23_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_23_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_23_0_0_0, OCL_23_0_0_1, OCL_23_0_0_2);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_23_0 = INSTANCE.getOCL_23_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_23_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_23_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_23 = INSTANCE.getOCL_23();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_23() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(), OCL_23_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_24_0_0_0 = INSTANCE.getOCL_24_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_24_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getRelationalOperationCallExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.RELATIONAL_OPERATION_CALL_EXP_CS__SOURCE), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_24_0_0_1 = INSTANCE.getOCL_24_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_24_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getRelationalOperationCallExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.RELATIONAL_OPERATION_CALL_EXP_CS__OPERATION_NAME), "RELATIONAL_OPERATOR", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_24_0_0_2 = INSTANCE.getOCL_24_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_24_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getRelationalOperationCallExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.RELATIONAL_OPERATION_CALL_EXP_CS__TARGET), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_24_0_0 = INSTANCE.getOCL_24_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_24_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_24_0_0_0, OCL_24_0_0_1, OCL_24_0_0_2);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_24_0 = INSTANCE.getOCL_24_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_24_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_24_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_24 = INSTANCE.getOCL_24();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_24() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getRelationalOperationCallExpCS(), OCL_24_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_25_0_0_0 = INSTANCE.getOCL_25_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_25_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAdditiveOperationCallExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ADDITIVE_OPERATION_CALL_EXP_CS__SOURCE), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_25_0_0_1 = INSTANCE.getOCL_25_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_25_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAdditiveOperationCallExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ADDITIVE_OPERATION_CALL_EXP_CS__OPERATION_NAME), "ADDITIVE_OPERATOR", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_25_0_0_2 = INSTANCE.getOCL_25_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_25_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAdditiveOperationCallExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ADDITIVE_OPERATION_CALL_EXP_CS__TARGET), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_25_0_0 = INSTANCE.getOCL_25_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_25_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_25_0_0_0, OCL_25_0_0_1, OCL_25_0_0_2);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_25_0 = INSTANCE.getOCL_25_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_25_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_25_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_25 = INSTANCE.getOCL_25();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_25() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAdditiveOperationCallExpCS(), OCL_25_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_26_0_0_0 = INSTANCE.getOCL_26_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_26_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getMultOperationCallExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.MULT_OPERATION_CALL_EXP_CS__SOURCE), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_26_0_0_1 = INSTANCE.getOCL_26_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_26_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getMultOperationCallExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.MULT_OPERATION_CALL_EXP_CS__OPERATION_NAME), "MULT_OPERATOR", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_26_0_0_2 = INSTANCE.getOCL_26_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_26_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getMultOperationCallExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.MULT_OPERATION_CALL_EXP_CS__TARGET), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_26_0_0 = INSTANCE.getOCL_26_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_26_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_26_0_0_0, OCL_26_0_0_1, OCL_26_0_0_2);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_26_0 = INSTANCE.getOCL_26_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_26_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_26_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_26 = INSTANCE.getOCL_26();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_26() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getMultOperationCallExpCS(), OCL_26_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_27_0_0_0 = INSTANCE.getOCL_27_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_27_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getUnaryOperationCallExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.UNARY_OPERATION_CALL_EXP_CS__OPERATION_NAME), "ADDITIVE_OPERATOR", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_27_0_0_1 = INSTANCE.getOCL_27_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_27_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_27_0_0_2 = INSTANCE.getOCL_27_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_27_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getUnaryOperationCallExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.UNARY_OPERATION_CALL_EXP_CS__TARGET), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_27_0_0 = INSTANCE.getOCL_27_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_27_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_27_0_0_0, OCL_27_0_0_1, OCL_27_0_0_2);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_27_0 = INSTANCE.getOCL_27_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_27_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_27_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_27 = INSTANCE.getOCL_27();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_27() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getUnaryOperationCallExpCS(), OCL_27_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_28_0_0_0 = INSTANCE.getOCL_28_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_28_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalNotOperationCallExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_NOT_OPERATION_CALL_EXP_CS__OPERATION_NAME), "NOT_OPERATOR", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_28_0_0_1 = INSTANCE.getOCL_28_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_28_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_28_0_0_2 = INSTANCE.getOCL_28_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_28_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalNotOperationCallExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_NOT_OPERATION_CALL_EXP_CS__TARGET), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_28_0_0 = INSTANCE.getOCL_28_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_28_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_28_0_0_0, OCL_28_0_0_1, OCL_28_0_0_2);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_28_0 = INSTANCE.getOCL_28_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_28_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_28_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_28 = INSTANCE.getOCL_28();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_28() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalNotOperationCallExpCS(), OCL_28_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_29_0_0_0 = INSTANCE.getOCL_29_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_29_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getNavigationCallExp().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.NAVIGATION_CALL_EXP__SOURCE), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_29_0_0_1 = INSTANCE.getOCL_29_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_29_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_29_0_0_2 = INSTANCE.getOCL_29_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_29_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getNavigationCallExp().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.NAVIGATION_CALL_EXP__NAVIGATION_OPERATOR), "NAVIGATION_OPERATOR", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_29_0_0_3 = INSTANCE.getOCL_29_0_0_3();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_29_0_0_3() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_29_0_0_4 = INSTANCE.getOCL_29_0_0_4();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_29_0_0_4() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getNavigationCallExp().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.NAVIGATION_CALL_EXP__FEATURE_CALLS), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitFeatureCallCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_29_0_0_5_0_0_0 = INSTANCE.getOCL_29_0_0_5_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_29_0_0_5_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_29_0_0_5_0_0_1 = INSTANCE.getOCL_29_0_0_5_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_29_0_0_5_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getNavigationCallExp().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.NAVIGATION_CALL_EXP__NAVIGATION_OPERATOR), "NAVIGATION_OPERATOR", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_29_0_0_5_0_0_2 = INSTANCE.getOCL_29_0_0_5_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_29_0_0_5_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_29_0_0_5_0_0_3 = INSTANCE.getOCL_29_0_0_5_0_0_3();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_29_0_0_5_0_0_3() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getNavigationCallExp().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.NAVIGATION_CALL_EXP__FEATURE_CALLS), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitFeatureCallCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_29_0_0_5_0_0 = INSTANCE.getOCL_29_0_0_5_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_29_0_0_5_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_29_0_0_5_0_0_0, OCL_29_0_0_5_0_0_1, OCL_29_0_0_5_0_0_2, OCL_29_0_0_5_0_0_3);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_29_0_0_5_0 = INSTANCE.getOCL_29_0_0_5_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_29_0_0_5_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_29_0_0_5_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound OCL_29_0_0_5 = INSTANCE.getOCL_29_0_0_5();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound getOCL_29_0_0_5() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound(OCL_29_0_0_5_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.STAR);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_29_0_0 = INSTANCE.getOCL_29_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_29_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_29_0_0_0, OCL_29_0_0_1, OCL_29_0_0_2, OCL_29_0_0_3, OCL_29_0_0_4, OCL_29_0_0_5);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_29_0 = INSTANCE.getOCL_29_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_29_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_29_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_29 = INSTANCE.getOCL_29();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_29() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getNavigationCallExp(), OCL_29_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_30_0_0_0_0_0_0 = INSTANCE.getOCL_30_0_0_0_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_30_0_0_0_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME), "EQUALITY_OPERATOR", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_30_0_0_0_0_0 = INSTANCE.getOCL_30_0_0_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_30_0_0_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_30_0_0_0_0_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_30_0_0_0_0_1_0 = INSTANCE.getOCL_30_0_0_0_0_1_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_30_0_0_0_0_1_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME), "NEQUALITY_OPERATOR", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_30_0_0_0_0_1 = INSTANCE.getOCL_30_0_0_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_30_0_0_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_30_0_0_0_0_1_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_30_0_0_0_0_2_0 = INSTANCE.getOCL_30_0_0_0_0_2_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_30_0_0_0_0_2_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME), "NOT_OPERATOR", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_30_0_0_0_0_2 = INSTANCE.getOCL_30_0_0_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_30_0_0_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_30_0_0_0_0_2_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_30_0_0_0_0_3_0 = INSTANCE.getOCL_30_0_0_0_0_3_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_30_0_0_0_0_3_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME), "AND_OPERATOR", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_30_0_0_0_0_3 = INSTANCE.getOCL_30_0_0_0_0_3();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_30_0_0_0_0_3() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_30_0_0_0_0_3_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_30_0_0_0_0_4_0 = INSTANCE.getOCL_30_0_0_0_0_4_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_30_0_0_0_0_4_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME), "OR_OPERATOR", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_30_0_0_0_0_4 = INSTANCE.getOCL_30_0_0_0_0_4();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_30_0_0_0_0_4() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_30_0_0_0_0_4_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_30_0_0_0_0_5_0 = INSTANCE.getOCL_30_0_0_0_0_5_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_30_0_0_0_0_5_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME), "XOR_OPERATOR", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_30_0_0_0_0_5 = INSTANCE.getOCL_30_0_0_0_0_5();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_30_0_0_0_0_5() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_30_0_0_0_0_5_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_30_0_0_0_0_6_0 = INSTANCE.getOCL_30_0_0_0_0_6_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_30_0_0_0_0_6_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME), "IMPLIES_OPERATOR", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_30_0_0_0_0_6 = INSTANCE.getOCL_30_0_0_0_0_6();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_30_0_0_0_0_6() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_30_0_0_0_0_6_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_30_0_0_0_0_7_0 = INSTANCE.getOCL_30_0_0_0_0_7_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_30_0_0_0_0_7_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME), "SIMPLE_NAME", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_30_0_0_0_0_7 = INSTANCE.getOCL_30_0_0_0_0_7();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_30_0_0_0_0_7() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_30_0_0_0_0_7_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_30_0_0_0_0 = INSTANCE.getOCL_30_0_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_30_0_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_30_0_0_0_0_0, OCL_30_0_0_0_0_1, OCL_30_0_0_0_0_2, OCL_30_0_0_0_0_3, OCL_30_0_0_0_0_4, OCL_30_0_0_0_0_5, OCL_30_0_0_0_0_6, OCL_30_0_0_0_0_7);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound OCL_30_0_0_0 = INSTANCE.getOCL_30_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound getOCL_30_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound(OCL_30_0_0_0_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_30_0_0_1 = INSTANCE.getOCL_30_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_30_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_30_0_0_2 = INSTANCE.getOCL_30_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_30_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("(", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_30_0_0_3_0_0_0 = INSTANCE.getOCL_30_0_0_3_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_30_0_0_3_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_30_0_0_3_0_0_1 = INSTANCE.getOCL_30_0_0_3_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_30_0_0_3_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__ARGUMENTS), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_30_0_0_3_0_0_2_0_0_0 = INSTANCE.getOCL_30_0_0_3_0_0_2_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_30_0_0_3_0_0_2_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword(",", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_30_0_0_3_0_0_2_0_0_1 = INSTANCE.getOCL_30_0_0_3_0_0_2_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_30_0_0_3_0_0_2_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__ARGUMENTS), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_30_0_0_3_0_0_2_0_0 = INSTANCE.getOCL_30_0_0_3_0_0_2_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_30_0_0_3_0_0_2_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_30_0_0_3_0_0_2_0_0_0, OCL_30_0_0_3_0_0_2_0_0_1);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_30_0_0_3_0_0_2_0 = INSTANCE.getOCL_30_0_0_3_0_0_2_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_30_0_0_3_0_0_2_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_30_0_0_3_0_0_2_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound OCL_30_0_0_3_0_0_2 = INSTANCE.getOCL_30_0_0_3_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound getOCL_30_0_0_3_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound(OCL_30_0_0_3_0_0_2_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.STAR);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_30_0_0_3_0_0 = INSTANCE.getOCL_30_0_0_3_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_30_0_0_3_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_30_0_0_3_0_0_0, OCL_30_0_0_3_0_0_1, OCL_30_0_0_3_0_0_2);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_30_0_0_3_0 = INSTANCE.getOCL_30_0_0_3_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_30_0_0_3_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_30_0_0_3_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound OCL_30_0_0_3 = INSTANCE.getOCL_30_0_0_3();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound getOCL_30_0_0_3() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound(OCL_30_0_0_3_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.QUESTIONMARK);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_30_0_0_4 = INSTANCE.getOCL_30_0_0_4();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_30_0_0_4() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_30_0_0_5 = INSTANCE.getOCL_30_0_0_5();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_30_0_0_5() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword(")", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_30_0_0 = INSTANCE.getOCL_30_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_30_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_30_0_0_0, OCL_30_0_0_1, OCL_30_0_0_2, OCL_30_0_0_3, OCL_30_0_0_4, OCL_30_0_0_5);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_30_0 = INSTANCE.getOCL_30_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_30_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_30_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_30 = INSTANCE.getOCL_30();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_30() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(), OCL_30_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_31_0_0_0 = INSTANCE.getOCL_31_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_31_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitPropertyCallCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_PROPERTY_CALL_CS__PROPERTY), "SIMPLE_NAME", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_31_0_0_1_0_0_0 = INSTANCE.getOCL_31_0_0_1_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_31_0_0_1_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_31_0_0_1_0_0_1 = INSTANCE.getOCL_31_0_0_1_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_31_0_0_1_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitPropertyCallCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_PROPERTY_CALL_CS__IS_MARKED_PRE), "IS_MARKED_PRE", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_31_0_0_1_0_0 = INSTANCE.getOCL_31_0_0_1_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_31_0_0_1_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_31_0_0_1_0_0_0, OCL_31_0_0_1_0_0_1);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_31_0_0_1_0 = INSTANCE.getOCL_31_0_0_1_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_31_0_0_1_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_31_0_0_1_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound OCL_31_0_0_1 = INSTANCE.getOCL_31_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound getOCL_31_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound(OCL_31_0_0_1_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.QUESTIONMARK);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_31_0_0 = INSTANCE.getOCL_31_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_31_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_31_0_0_0, OCL_31_0_0_1);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_31_0 = INSTANCE.getOCL_31_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_31_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_31_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_31 = INSTANCE.getOCL_31();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_31() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitPropertyCallCS(), OCL_31_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_32_0_0_0 = INSTANCE.getOCL_32_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_32_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ITERATOR_EXP_CS__ITERATOR_NAME), "ITERATOR_NAME", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_32_0_0_1 = INSTANCE.getOCL_32_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_32_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_32_0_0_2 = INSTANCE.getOCL_32_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_32_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("(", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_32_0_0_3_0_0_0 = INSTANCE.getOCL_32_0_0_3_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_32_0_0_3_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_32_0_0_3_0_0_1 = INSTANCE.getOCL_32_0_0_3_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_32_0_0_3_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ITERATOR_EXP_CS__ITERATOR_VARIABLES), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpVariableCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_32_0_0_3_0_0_2_0_0_0 = INSTANCE.getOCL_32_0_0_3_0_0_2_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_32_0_0_3_0_0_2_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_32_0_0_3_0_0_2_0_0_1 = INSTANCE.getOCL_32_0_0_3_0_0_2_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_32_0_0_3_0_0_2_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword(",", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_32_0_0_3_0_0_2_0_0_2 = INSTANCE.getOCL_32_0_0_3_0_0_2_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_32_0_0_3_0_0_2_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ITERATOR_EXP_CS__ITERATOR_VARIABLES), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpVariableCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_32_0_0_3_0_0_2_0_0 = INSTANCE.getOCL_32_0_0_3_0_0_2_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_32_0_0_3_0_0_2_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_32_0_0_3_0_0_2_0_0_0, OCL_32_0_0_3_0_0_2_0_0_1, OCL_32_0_0_3_0_0_2_0_0_2);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_32_0_0_3_0_0_2_0 = INSTANCE.getOCL_32_0_0_3_0_0_2_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_32_0_0_3_0_0_2_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_32_0_0_3_0_0_2_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound OCL_32_0_0_3_0_0_2 = INSTANCE.getOCL_32_0_0_3_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound getOCL_32_0_0_3_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound(OCL_32_0_0_3_0_0_2_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.QUESTIONMARK);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_32_0_0_3_0_0_3 = INSTANCE.getOCL_32_0_0_3_0_0_3();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_32_0_0_3_0_0_3() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("|", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_32_0_0_3_0_0 = INSTANCE.getOCL_32_0_0_3_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_32_0_0_3_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_32_0_0_3_0_0_0, OCL_32_0_0_3_0_0_1, OCL_32_0_0_3_0_0_2, OCL_32_0_0_3_0_0_3);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_32_0_0_3_0 = INSTANCE.getOCL_32_0_0_3_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_32_0_0_3_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_32_0_0_3_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound OCL_32_0_0_3 = INSTANCE.getOCL_32_0_0_3();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound getOCL_32_0_0_3() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound(OCL_32_0_0_3_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.QUESTIONMARK);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak OCL_32_0_0_4 = INSTANCE.getOCL_32_0_0_4();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak getOCL_32_0_0_4() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 1);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_32_0_0_5 = INSTANCE.getOCL_32_0_0_5();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_32_0_0_5() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ITERATOR_EXP_CS__BODY_EXPRESSION), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak OCL_32_0_0_6 = INSTANCE.getOCL_32_0_0_6();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak getOCL_32_0_0_6() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_32_0_0_7 = INSTANCE.getOCL_32_0_0_7();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_32_0_0_7() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword(")", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_32_0_0 = INSTANCE.getOCL_32_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_32_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_32_0_0_0, OCL_32_0_0_1, OCL_32_0_0_2, OCL_32_0_0_3, OCL_32_0_0_4, OCL_32_0_0_5, OCL_32_0_0_6, OCL_32_0_0_7);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_32_0 = INSTANCE.getOCL_32_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_32_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_32_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_32 = INSTANCE.getOCL_32();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_32() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(), OCL_32_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_33_0_0_0 = INSTANCE.getOCL_33_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_33_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("iterate", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_33_0_0_1 = INSTANCE.getOCL_33_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_33_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_33_0_0_2 = INSTANCE.getOCL_33_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_33_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("(", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_33_0_0_3_0_0_0 = INSTANCE.getOCL_33_0_0_3_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_33_0_0_3_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIterateExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ITERATE_EXP_CS__ITERATOR_VARIABLE), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpVariableCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_33_0_0_3_0_0_1 = INSTANCE.getOCL_33_0_0_3_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_33_0_0_3_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_33_0_0_3_0_0_2 = INSTANCE.getOCL_33_0_0_3_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_33_0_0_3_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword(";", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_33_0_0_3_0_0 = INSTANCE.getOCL_33_0_0_3_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_33_0_0_3_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_33_0_0_3_0_0_0, OCL_33_0_0_3_0_0_1, OCL_33_0_0_3_0_0_2);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_33_0_0_3_0 = INSTANCE.getOCL_33_0_0_3_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_33_0_0_3_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_33_0_0_3_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound OCL_33_0_0_3 = INSTANCE.getOCL_33_0_0_3();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound getOCL_33_0_0_3() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound(OCL_33_0_0_3_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.QUESTIONMARK);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_33_0_0_4 = INSTANCE.getOCL_33_0_0_4();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_33_0_0_4() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIterateExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ITERATE_EXP_CS__RESULT_VARIABLE), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_33_0_0_5 = INSTANCE.getOCL_33_0_0_5();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_33_0_0_5() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("|", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_33_0_0_6 = INSTANCE.getOCL_33_0_0_6();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_33_0_0_6() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIterateExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ITERATE_EXP_CS__BODY_EXPRESSION), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_33_0_0_7 = INSTANCE.getOCL_33_0_0_7();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_33_0_0_7() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_33_0_0_8 = INSTANCE.getOCL_33_0_0_8();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_33_0_0_8() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword(")", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_33_0_0 = INSTANCE.getOCL_33_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_33_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_33_0_0_0, OCL_33_0_0_1, OCL_33_0_0_2, OCL_33_0_0_3, OCL_33_0_0_4, OCL_33_0_0_5, OCL_33_0_0_6, OCL_33_0_0_7, OCL_33_0_0_8);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_33_0 = INSTANCE.getOCL_33_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_33_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_33_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_33 = INSTANCE.getOCL_33();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_33() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIterateExpCS(), OCL_33_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_34_0_0_0 = INSTANCE.getOCL_34_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_34_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpVariableCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ITERATOR_EXP_VARIABLE_CS__VARIABLE_NAME), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getSimpleNameCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_34_0_0_1_0_0_0 = INSTANCE.getOCL_34_0_0_1_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_34_0_0_1_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword(":", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_34_0_0_1_0_0_1 = INSTANCE.getOCL_34_0_0_1_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_34_0_0_1_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpVariableCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ITERATOR_EXP_VARIABLE_CS__TYPE_NAME), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getTypeCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_34_0_0_1_0_0 = INSTANCE.getOCL_34_0_0_1_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_34_0_0_1_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_34_0_0_1_0_0_0, OCL_34_0_0_1_0_0_1);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_34_0_0_1_0 = INSTANCE.getOCL_34_0_0_1_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_34_0_0_1_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_34_0_0_1_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound OCL_34_0_0_1 = INSTANCE.getOCL_34_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound getOCL_34_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound(OCL_34_0_0_1_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.QUESTIONMARK);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_34_0_0 = INSTANCE.getOCL_34_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_34_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_34_0_0_0, OCL_34_0_0_1);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_34_0 = INSTANCE.getOCL_34_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_34_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_34_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_34 = INSTANCE.getOCL_34();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_34() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpVariableCS(), OCL_34_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_35_0_0_0 = INSTANCE.getOCL_35_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_35_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("Tuple", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_35_0_0_1 = INSTANCE.getOCL_35_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_35_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("(", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_35_0_0_2 = INSTANCE.getOCL_35_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_35_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_35_0_0_3 = INSTANCE.getOCL_35_0_0_3();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_35_0_0_3() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getTupleTypeCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.TUPLE_TYPE_CS__VARIABLE_DECLARATION_LIST), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.QUESTIONMARK, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithoutInitListCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_35_0_0_4 = INSTANCE.getOCL_35_0_0_4();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_35_0_0_4() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_35_0_0_5 = INSTANCE.getOCL_35_0_0_5();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_35_0_0_5() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword(")", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_35_0_0 = INSTANCE.getOCL_35_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_35_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_35_0_0_0, OCL_35_0_0_1, OCL_35_0_0_2, OCL_35_0_0_3, OCL_35_0_0_4, OCL_35_0_0_5);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_35_0 = INSTANCE.getOCL_35_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_35_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_35_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_35 = INSTANCE.getOCL_35();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_35() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getTupleTypeCS(), OCL_35_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_36_0_0_0 = INSTANCE.getOCL_36_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_36_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionTypeIdentifierCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.COLLECTION_TYPE_IDENTIFIER_CS__TYPE_NAME), "COLLECTION_TYPES", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_36_0_0_1_0_0_0 = INSTANCE.getOCL_36_0_0_1_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_36_0_0_1_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_36_0_0_1_0_0_1 = INSTANCE.getOCL_36_0_0_1_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_36_0_0_1_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("(", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_36_0_0_1_0_0_2 = INSTANCE.getOCL_36_0_0_1_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_36_0_0_1_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_36_0_0_1_0_0_3 = INSTANCE.getOCL_36_0_0_1_0_0_3();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_36_0_0_1_0_0_3() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionTypeIdentifierCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.COLLECTION_TYPE_IDENTIFIER_CS__GENERIC_TYPE), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getTypeCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_36_0_0_1_0_0_4 = INSTANCE.getOCL_36_0_0_1_0_0_4();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_36_0_0_1_0_0_4() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_36_0_0_1_0_0_5 = INSTANCE.getOCL_36_0_0_1_0_0_5();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_36_0_0_1_0_0_5() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword(")", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_36_0_0_1_0_0 = INSTANCE.getOCL_36_0_0_1_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_36_0_0_1_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_36_0_0_1_0_0_0, OCL_36_0_0_1_0_0_1, OCL_36_0_0_1_0_0_2, OCL_36_0_0_1_0_0_3, OCL_36_0_0_1_0_0_4, OCL_36_0_0_1_0_0_5);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_36_0_0_1_0 = INSTANCE.getOCL_36_0_0_1_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_36_0_0_1_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_36_0_0_1_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound OCL_36_0_0_1 = INSTANCE.getOCL_36_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound getOCL_36_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound(OCL_36_0_0_1_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.QUESTIONMARK);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_36_0_0 = INSTANCE.getOCL_36_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_36_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_36_0_0_0, OCL_36_0_0_1);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_36_0 = INSTANCE.getOCL_36_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_36_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_36_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_36 = INSTANCE.getOCL_36();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_36() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionTypeIdentifierCS(), OCL_36_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_37_0_0_0 = INSTANCE.getOCL_37_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_37_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getTypeModelElementCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.TYPE_MODEL_ELEMENT_CS__MODEL_ELEMENT), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getModelElementCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_37_0_0 = INSTANCE.getOCL_37_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_37_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_37_0_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_37_0 = INSTANCE.getOCL_37_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_37_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_37_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_37 = INSTANCE.getOCL_37();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_37() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getTypeModelElementCS(), OCL_37_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_38_0_0_0 = INSTANCE.getOCL_38_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_38_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithoutInitCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.VARIABLE_DECLARATION_WITHOUT_INIT_CS__VARIABLE_NAME), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getSimpleNameCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_38_0_0_1 = INSTANCE.getOCL_38_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_38_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword(":", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_38_0_0_2 = INSTANCE.getOCL_38_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_38_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithoutInitCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.VARIABLE_DECLARATION_WITHOUT_INIT_CS__TYPE_NAME), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getTypeCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_38_0_0 = INSTANCE.getOCL_38_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_38_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_38_0_0_0, OCL_38_0_0_1, OCL_38_0_0_2);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_38_0 = INSTANCE.getOCL_38_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_38_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_38_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_38 = INSTANCE.getOCL_38();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_38() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithoutInitCS(), OCL_38_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_39_0_0_0 = INSTANCE.getOCL_39_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_39_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithoutInitListCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.VARIABLE_DECLARATION_WITHOUT_INIT_LIST_CS__VARIABLE_DECLARATIONS), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithoutInitCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_39_0_0_1_0_0_0 = INSTANCE.getOCL_39_0_0_1_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_39_0_0_1_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_39_0_0_1_0_0_1 = INSTANCE.getOCL_39_0_0_1_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_39_0_0_1_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword(",", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_39_0_0_1_0_0_2 = INSTANCE.getOCL_39_0_0_1_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_39_0_0_1_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithoutInitListCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.VARIABLE_DECLARATION_WITHOUT_INIT_LIST_CS__VARIABLE_DECLARATIONS), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithoutInitCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_39_0_0_1_0_0 = INSTANCE.getOCL_39_0_0_1_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_39_0_0_1_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_39_0_0_1_0_0_0, OCL_39_0_0_1_0_0_1, OCL_39_0_0_1_0_0_2);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_39_0_0_1_0 = INSTANCE.getOCL_39_0_0_1_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_39_0_0_1_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_39_0_0_1_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound OCL_39_0_0_1 = INSTANCE.getOCL_39_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound getOCL_39_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound(OCL_39_0_0_1_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.STAR);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_39_0_0 = INSTANCE.getOCL_39_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_39_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_39_0_0_0, OCL_39_0_0_1);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_39_0 = INSTANCE.getOCL_39_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_39_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_39_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_39 = INSTANCE.getOCL_39();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_39() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithoutInitListCS(), OCL_39_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_40_0_0_0 = INSTANCE.getOCL_40_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_40_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__VARIABLE_NAME), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getSimpleNameCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_40_0_0_1_0_0_0 = INSTANCE.getOCL_40_0_0_1_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_40_0_0_1_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword(":", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_40_0_0_1_0_0_1 = INSTANCE.getOCL_40_0_0_1_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_40_0_0_1_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__TYPE_NAME), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getTypeCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_40_0_0_1_0_0 = INSTANCE.getOCL_40_0_0_1_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_40_0_0_1_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_40_0_0_1_0_0_0, OCL_40_0_0_1_0_0_1);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_40_0_0_1_0 = INSTANCE.getOCL_40_0_0_1_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_40_0_0_1_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_40_0_0_1_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound OCL_40_0_0_1 = INSTANCE.getOCL_40_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound getOCL_40_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound(OCL_40_0_0_1_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.QUESTIONMARK);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_40_0_0_2 = INSTANCE.getOCL_40_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_40_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__EQUAL), "EQUALITY_OPERATOR", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_40_0_0_3 = INSTANCE.getOCL_40_0_0_3();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_40_0_0_3() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__INITIALIZATION), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_40_0_0 = INSTANCE.getOCL_40_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_40_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_40_0_0_0, OCL_40_0_0_1, OCL_40_0_0_2, OCL_40_0_0_3);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_40_0 = INSTANCE.getOCL_40_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_40_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_40_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_40 = INSTANCE.getOCL_40();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_40() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS(), OCL_40_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_41_0_0_0 = INSTANCE.getOCL_41_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_41_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitListCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.VARIABLE_DECLARATION_WITH_INIT_LIST_CS__VARIABLE_DECLARATIONS), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_41_0_0_1_0_0_0 = INSTANCE.getOCL_41_0_0_1_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_41_0_0_1_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_41_0_0_1_0_0_1 = INSTANCE.getOCL_41_0_0_1_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_41_0_0_1_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword(",", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_41_0_0_1_0_0_2 = INSTANCE.getOCL_41_0_0_1_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_41_0_0_1_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitListCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.VARIABLE_DECLARATION_WITH_INIT_LIST_CS__VARIABLE_DECLARATIONS), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_41_0_0_1_0_0 = INSTANCE.getOCL_41_0_0_1_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_41_0_0_1_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_41_0_0_1_0_0_0, OCL_41_0_0_1_0_0_1, OCL_41_0_0_1_0_0_2);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_41_0_0_1_0 = INSTANCE.getOCL_41_0_0_1_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_41_0_0_1_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_41_0_0_1_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound OCL_41_0_0_1 = INSTANCE.getOCL_41_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound getOCL_41_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound(OCL_41_0_0_1_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.STAR);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_41_0_0 = INSTANCE.getOCL_41_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_41_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_41_0_0_0, OCL_41_0_0_1);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_41_0 = INSTANCE.getOCL_41_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_41_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_41_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_41 = INSTANCE.getOCL_41();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_41() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitListCS(), OCL_41_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_42_0_0_0_0_0_0 = INSTANCE.getOCL_42_0_0_0_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_42_0_0_0_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME), "EQUALITY_OPERATOR", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_42_0_0_0_0_0 = INSTANCE.getOCL_42_0_0_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_42_0_0_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_42_0_0_0_0_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_42_0_0_0_0_1_0 = INSTANCE.getOCL_42_0_0_0_0_1_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_42_0_0_0_0_1_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME), "NEQUALITY_OPERATOR", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_42_0_0_0_0_1 = INSTANCE.getOCL_42_0_0_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_42_0_0_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_42_0_0_0_0_1_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_42_0_0_0_0_2_0 = INSTANCE.getOCL_42_0_0_0_0_2_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_42_0_0_0_0_2_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME), "NOT_OPERATOR", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_42_0_0_0_0_2 = INSTANCE.getOCL_42_0_0_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_42_0_0_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_42_0_0_0_0_2_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_42_0_0_0_0_3_0 = INSTANCE.getOCL_42_0_0_0_0_3_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_42_0_0_0_0_3_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME), "AND_OPERATOR", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_42_0_0_0_0_3 = INSTANCE.getOCL_42_0_0_0_0_3();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_42_0_0_0_0_3() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_42_0_0_0_0_3_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_42_0_0_0_0_4_0 = INSTANCE.getOCL_42_0_0_0_0_4_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_42_0_0_0_0_4_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME), "OR_OPERATOR", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_42_0_0_0_0_4 = INSTANCE.getOCL_42_0_0_0_0_4();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_42_0_0_0_0_4() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_42_0_0_0_0_4_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_42_0_0_0_0_5_0 = INSTANCE.getOCL_42_0_0_0_0_5_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_42_0_0_0_0_5_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME), "XOR_OPERATOR", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_42_0_0_0_0_5 = INSTANCE.getOCL_42_0_0_0_0_5();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_42_0_0_0_0_5() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_42_0_0_0_0_5_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_42_0_0_0_0_6_0 = INSTANCE.getOCL_42_0_0_0_0_6_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_42_0_0_0_0_6_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME), "IMPLIES_OPERATOR", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_42_0_0_0_0_6 = INSTANCE.getOCL_42_0_0_0_0_6();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_42_0_0_0_0_6() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_42_0_0_0_0_6_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_42_0_0_0_0_7_0 = INSTANCE.getOCL_42_0_0_0_0_7_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_42_0_0_0_0_7_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME), "SIMPLE_NAME", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_42_0_0_0_0_7 = INSTANCE.getOCL_42_0_0_0_0_7();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_42_0_0_0_0_7() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_42_0_0_0_0_7_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_42_0_0_0_0 = INSTANCE.getOCL_42_0_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_42_0_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_42_0_0_0_0_0, OCL_42_0_0_0_0_1, OCL_42_0_0_0_0_2, OCL_42_0_0_0_0_3, OCL_42_0_0_0_0_4, OCL_42_0_0_0_0_5, OCL_42_0_0_0_0_6, OCL_42_0_0_0_0_7);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound OCL_42_0_0_0 = INSTANCE.getOCL_42_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound getOCL_42_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound(OCL_42_0_0_0_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_42_0_0_1 = INSTANCE.getOCL_42_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_42_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_42_0_0_2_0_0_0 = INSTANCE.getOCL_42_0_0_2_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_42_0_0_2_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__IS_MARKED_PRE), "IS_MARKED_PRE", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_42_0_0_2_0_0_1 = INSTANCE.getOCL_42_0_0_2_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_42_0_0_2_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_42_0_0_2_0_0 = INSTANCE.getOCL_42_0_0_2_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_42_0_0_2_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_42_0_0_2_0_0_0, OCL_42_0_0_2_0_0_1);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_42_0_0_2_0 = INSTANCE.getOCL_42_0_0_2_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_42_0_0_2_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_42_0_0_2_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound OCL_42_0_0_2 = INSTANCE.getOCL_42_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound getOCL_42_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound(OCL_42_0_0_2_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.QUESTIONMARK);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_42_0_0_3 = INSTANCE.getOCL_42_0_0_3();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_42_0_0_3() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("(", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_42_0_0_4_0_0_0 = INSTANCE.getOCL_42_0_0_4_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_42_0_0_4_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_42_0_0_4_0_0_1 = INSTANCE.getOCL_42_0_0_4_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_42_0_0_4_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__ARGUMENTS), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_42_0_0_4_0_0_2_0_0_0 = INSTANCE.getOCL_42_0_0_4_0_0_2_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_42_0_0_4_0_0_2_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword(",", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_42_0_0_4_0_0_2_0_0_1 = INSTANCE.getOCL_42_0_0_4_0_0_2_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_42_0_0_4_0_0_2_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__ARGUMENTS), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_42_0_0_4_0_0_2_0_0 = INSTANCE.getOCL_42_0_0_4_0_0_2_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_42_0_0_4_0_0_2_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_42_0_0_4_0_0_2_0_0_0, OCL_42_0_0_4_0_0_2_0_0_1);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_42_0_0_4_0_0_2_0 = INSTANCE.getOCL_42_0_0_4_0_0_2_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_42_0_0_4_0_0_2_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_42_0_0_4_0_0_2_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound OCL_42_0_0_4_0_0_2 = INSTANCE.getOCL_42_0_0_4_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound getOCL_42_0_0_4_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound(OCL_42_0_0_4_0_0_2_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.STAR);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_42_0_0_4_0_0 = INSTANCE.getOCL_42_0_0_4_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_42_0_0_4_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_42_0_0_4_0_0_0, OCL_42_0_0_4_0_0_1, OCL_42_0_0_4_0_0_2);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_42_0_0_4_0 = INSTANCE.getOCL_42_0_0_4_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_42_0_0_4_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_42_0_0_4_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound OCL_42_0_0_4 = INSTANCE.getOCL_42_0_0_4();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound getOCL_42_0_0_4() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound(OCL_42_0_0_4_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.QUESTIONMARK);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_42_0_0_5 = INSTANCE.getOCL_42_0_0_5();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_42_0_0_5() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_42_0_0_6 = INSTANCE.getOCL_42_0_0_6();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_42_0_0_6() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword(")", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_42_0_0 = INSTANCE.getOCL_42_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_42_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_42_0_0_0, OCL_42_0_0_1, OCL_42_0_0_2, OCL_42_0_0_3, OCL_42_0_0_4, OCL_42_0_0_5, OCL_42_0_0_6);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_42_0 = INSTANCE.getOCL_42_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_42_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_42_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_42 = INSTANCE.getOCL_42();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_42() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(), OCL_42_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_43_0_0_0 = INSTANCE.getOCL_43_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_43_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.STATIC_OPERATION_CALL_EXP_CS__TYPE_NAME), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getModelElementCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_43_0_0_1 = INSTANCE.getOCL_43_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_43_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_43_0_0_2 = INSTANCE.getOCL_43_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_43_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("::", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_43_0_0_3 = INSTANCE.getOCL_43_0_0_3();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_43_0_0_3() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_43_0_0_4 = INSTANCE.getOCL_43_0_0_4();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_43_0_0_4() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.STATIC_OPERATION_CALL_EXP_CS__OPERATION_NAME), "SIMPLE_NAME", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_43_0_0_5 = INSTANCE.getOCL_43_0_0_5();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_43_0_0_5() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_43_0_0_6 = INSTANCE.getOCL_43_0_0_6();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_43_0_0_6() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("(", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_43_0_0_7_0_0_0 = INSTANCE.getOCL_43_0_0_7_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_43_0_0_7_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_43_0_0_7_0_0_1 = INSTANCE.getOCL_43_0_0_7_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_43_0_0_7_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.STATIC_OPERATION_CALL_EXP_CS__ARGUMENTS), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_43_0_0_7_0_0_2_0_0_0 = INSTANCE.getOCL_43_0_0_7_0_0_2_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_43_0_0_7_0_0_2_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword(",", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_43_0_0_7_0_0_2_0_0_1 = INSTANCE.getOCL_43_0_0_7_0_0_2_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_43_0_0_7_0_0_2_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.STATIC_OPERATION_CALL_EXP_CS__ARGUMENTS), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_43_0_0_7_0_0_2_0_0 = INSTANCE.getOCL_43_0_0_7_0_0_2_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_43_0_0_7_0_0_2_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_43_0_0_7_0_0_2_0_0_0, OCL_43_0_0_7_0_0_2_0_0_1);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_43_0_0_7_0_0_2_0 = INSTANCE.getOCL_43_0_0_7_0_0_2_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_43_0_0_7_0_0_2_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_43_0_0_7_0_0_2_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound OCL_43_0_0_7_0_0_2 = INSTANCE.getOCL_43_0_0_7_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound getOCL_43_0_0_7_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound(OCL_43_0_0_7_0_0_2_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.STAR);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_43_0_0_7_0_0 = INSTANCE.getOCL_43_0_0_7_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_43_0_0_7_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_43_0_0_7_0_0_0, OCL_43_0_0_7_0_0_1, OCL_43_0_0_7_0_0_2);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_43_0_0_7_0 = INSTANCE.getOCL_43_0_0_7_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_43_0_0_7_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_43_0_0_7_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound OCL_43_0_0_7 = INSTANCE.getOCL_43_0_0_7();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound getOCL_43_0_0_7() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound(OCL_43_0_0_7_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.QUESTIONMARK);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_43_0_0_8 = INSTANCE.getOCL_43_0_0_8();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_43_0_0_8() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_43_0_0_9 = INSTANCE.getOCL_43_0_0_9();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_43_0_0_9() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword(")", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_43_0_0 = INSTANCE.getOCL_43_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_43_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_43_0_0_0, OCL_43_0_0_1, OCL_43_0_0_2, OCL_43_0_0_3, OCL_43_0_0_4, OCL_43_0_0_5, OCL_43_0_0_6, OCL_43_0_0_7, OCL_43_0_0_8, OCL_43_0_0_9);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_43_0 = INSTANCE.getOCL_43_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_43_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_43_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_43 = INSTANCE.getOCL_43();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_43() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(), OCL_43_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_44_0_0_0 = INSTANCE.getOCL_44_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_44_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getModelElementCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.MODEL_ELEMENT_CS__PATH_NAME), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPathNameCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_44_0_0 = INSTANCE.getOCL_44_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_44_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_44_0_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_44_0 = INSTANCE.getOCL_44_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_44_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_44_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_44 = INSTANCE.getOCL_44();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_44() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getModelElementCS(), OCL_44_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_45_0_0_0 = INSTANCE.getOCL_45_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_45_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPathNameSimpleCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PATH_NAME_SIMPLE_CS__NAMED_ELEMENT), "SIMPLE_NAME", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_45_0_0 = INSTANCE.getOCL_45_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_45_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_45_0_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_45_0 = INSTANCE.getOCL_45_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_45_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_45_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_45 = INSTANCE.getOCL_45();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_45() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPathNameSimpleCS(), OCL_45_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_46_0_0_0_0_0_0 = INSTANCE.getOCL_46_0_0_0_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_46_0_0_0_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPathNamePathCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PATH_NAME_PATH_CS__PATH_NAME), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getUnreservedSimpleNameCS(), }, 1);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_46_0_0_0_0_0_1 = INSTANCE.getOCL_46_0_0_0_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_46_0_0_0_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_46_0_0_0_0_0_2 = INSTANCE.getOCL_46_0_0_0_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_46_0_0_0_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("::", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_46_0_0_0_0_0_3 = INSTANCE.getOCL_46_0_0_0_0_0_3();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_46_0_0_0_0_0_3() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_46_0_0_0_0_0 = INSTANCE.getOCL_46_0_0_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_46_0_0_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_46_0_0_0_0_0_0, OCL_46_0_0_0_0_0_1, OCL_46_0_0_0_0_0_2, OCL_46_0_0_0_0_0_3);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_46_0_0_0_0 = INSTANCE.getOCL_46_0_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_46_0_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_46_0_0_0_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound OCL_46_0_0_0 = INSTANCE.getOCL_46_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound getOCL_46_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound(OCL_46_0_0_0_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.PLUS);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_46_0_0_1 = INSTANCE.getOCL_46_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_46_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPathNamePathCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PATH_NAME_PATH_CS__PATH_NAME), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getUnreservedSimpleNameCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_46_0_0 = INSTANCE.getOCL_46_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_46_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_46_0_0_0, OCL_46_0_0_1);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_46_0 = INSTANCE.getOCL_46_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_46_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_46_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_46 = INSTANCE.getOCL_46();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_46() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPathNamePathCS(), OCL_46_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_47_0_0_0 = INSTANCE.getOCL_47_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_47_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getNamedElementCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.NAMED_ELEMENT_CS__NAMED_ELEMENT), "SIMPLE_NAME", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_47_0_0 = INSTANCE.getOCL_47_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_47_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_47_0_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_47_0 = INSTANCE.getOCL_47_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_47_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_47_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_47 = INSTANCE.getOCL_47();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_47() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getNamedElementCS(), OCL_47_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_48_0_0_0 = INSTANCE.getOCL_48_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_48_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("Tuple", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_48_0_0_1 = INSTANCE.getOCL_48_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_48_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("{", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_48_0_0_2 = INSTANCE.getOCL_48_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_48_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getTupleLiteralExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.TUPLE_LITERAL_EXP_CS__VARIABLE_DECLARATIONS), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitListCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_48_0_0_3 = INSTANCE.getOCL_48_0_0_3();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_48_0_0_3() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("}", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_48_0_0 = INSTANCE.getOCL_48_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_48_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_48_0_0_0, OCL_48_0_0_1, OCL_48_0_0_2, OCL_48_0_0_3);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_48_0 = INSTANCE.getOCL_48_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_48_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_48_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_48 = INSTANCE.getOCL_48();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_48() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getTupleLiteralExpCS(), OCL_48_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_49_0_0_0 = INSTANCE.getOCL_49_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_49_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("if", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_49_0_0_1 = INSTANCE.getOCL_49_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_49_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IF_EXP_CS__CONDITION), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak OCL_49_0_0_2 = INSTANCE.getOCL_49_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak getOCL_49_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_49_0_0_3 = INSTANCE.getOCL_49_0_0_3();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_49_0_0_3() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("then", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak OCL_49_0_0_4 = INSTANCE.getOCL_49_0_0_4();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak getOCL_49_0_0_4() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 1);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_49_0_0_5 = INSTANCE.getOCL_49_0_0_5();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_49_0_0_5() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IF_EXP_CS__THEN_BRANCH), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak OCL_49_0_0_6 = INSTANCE.getOCL_49_0_0_6();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak getOCL_49_0_0_6() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_49_0_0_7 = INSTANCE.getOCL_49_0_0_7();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_49_0_0_7() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("else", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak OCL_49_0_0_8 = INSTANCE.getOCL_49_0_0_8();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak getOCL_49_0_0_8() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 1);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_49_0_0_9 = INSTANCE.getOCL_49_0_0_9();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_49_0_0_9() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IF_EXP_CS__ELSE_BRANCH), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak OCL_49_0_0_10 = INSTANCE.getOCL_49_0_0_10();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak getOCL_49_0_0_10() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_49_0_0_11 = INSTANCE.getOCL_49_0_0_11();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_49_0_0_11() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("endif", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_49_0_0 = INSTANCE.getOCL_49_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_49_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_49_0_0_0, OCL_49_0_0_1, OCL_49_0_0_2, OCL_49_0_0_3, OCL_49_0_0_4, OCL_49_0_0_5, OCL_49_0_0_6, OCL_49_0_0_7, OCL_49_0_0_8, OCL_49_0_0_9, OCL_49_0_0_10, OCL_49_0_0_11);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_49_0 = INSTANCE.getOCL_49_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_49_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_49_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_49 = INSTANCE.getOCL_49();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_49() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(), OCL_49_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_50_0_0_0 = INSTANCE.getOCL_50_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_50_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.COLLECTION_LITERAL_EXP_CS__COLLECTION_TYPE), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionTypeIdentifierCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_50_0_0_1 = INSTANCE.getOCL_50_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_50_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("{", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_50_0_0_2_0_0_0 = INSTANCE.getOCL_50_0_0_2_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_50_0_0_2_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.COLLECTION_LITERAL_EXP_CS__COLLECTION_LITERAL_PARTS), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralPartsCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_50_0_0_2_0_0_1_0_0_0 = INSTANCE.getOCL_50_0_0_2_0_0_1_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_50_0_0_2_0_0_1_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_50_0_0_2_0_0_1_0_0_1 = INSTANCE.getOCL_50_0_0_2_0_0_1_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_50_0_0_2_0_0_1_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword(",", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_50_0_0_2_0_0_1_0_0_2 = INSTANCE.getOCL_50_0_0_2_0_0_1_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_50_0_0_2_0_0_1_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.COLLECTION_LITERAL_EXP_CS__COLLECTION_LITERAL_PARTS), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralPartsCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_50_0_0_2_0_0_1_0_0 = INSTANCE.getOCL_50_0_0_2_0_0_1_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_50_0_0_2_0_0_1_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_50_0_0_2_0_0_1_0_0_0, OCL_50_0_0_2_0_0_1_0_0_1, OCL_50_0_0_2_0_0_1_0_0_2);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_50_0_0_2_0_0_1_0 = INSTANCE.getOCL_50_0_0_2_0_0_1_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_50_0_0_2_0_0_1_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_50_0_0_2_0_0_1_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound OCL_50_0_0_2_0_0_1 = INSTANCE.getOCL_50_0_0_2_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound getOCL_50_0_0_2_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound(OCL_50_0_0_2_0_0_1_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.STAR);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_50_0_0_2_0_0 = INSTANCE.getOCL_50_0_0_2_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_50_0_0_2_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_50_0_0_2_0_0_0, OCL_50_0_0_2_0_0_1);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_50_0_0_2_0 = INSTANCE.getOCL_50_0_0_2_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_50_0_0_2_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_50_0_0_2_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound OCL_50_0_0_2 = INSTANCE.getOCL_50_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound getOCL_50_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound(OCL_50_0_0_2_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.QUESTIONMARK);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_50_0_0_3 = INSTANCE.getOCL_50_0_0_3();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_50_0_0_3() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("}", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_50_0_0 = INSTANCE.getOCL_50_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_50_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_50_0_0_0, OCL_50_0_0_1, OCL_50_0_0_2, OCL_50_0_0_3);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_50_0 = INSTANCE.getOCL_50_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_50_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_50_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_50 = INSTANCE.getOCL_50();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_50() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(), OCL_50_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_51_0_0_0 = INSTANCE.getOCL_51_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_51_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionRangeCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.COLLECTION_RANGE_CS__FROM), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_51_0_0_1 = INSTANCE.getOCL_51_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_51_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_51_0_0_2 = INSTANCE.getOCL_51_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_51_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("..", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_51_0_0_3 = INSTANCE.getOCL_51_0_0_3();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_51_0_0_3() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_51_0_0_4 = INSTANCE.getOCL_51_0_0_4();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_51_0_0_4() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionRangeCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.COLLECTION_RANGE_CS__TO), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_51_0_0 = INSTANCE.getOCL_51_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_51_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_51_0_0_0, OCL_51_0_0_1, OCL_51_0_0_2, OCL_51_0_0_3, OCL_51_0_0_4);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_51_0 = INSTANCE.getOCL_51_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_51_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_51_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_51 = INSTANCE.getOCL_51();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_51() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionRangeCS(), OCL_51_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_52_0_0_0 = INSTANCE.getOCL_52_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_52_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralPartsOclExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.COLLECTION_LITERAL_PARTS_OCL_EXP_CS__OCL_EXPRESSION), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_52_0_0 = INSTANCE.getOCL_52_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_52_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_52_0_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_52_0 = INSTANCE.getOCL_52_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_52_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_52_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_52 = INSTANCE.getOCL_52();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_52() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralPartsOclExpCS(), OCL_52_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_53_0_0_0 = INSTANCE.getOCL_53_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_53_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionTypeLiteralExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.COLLECTION_TYPE_LITERAL_EXP_CS__COLLECTION_TYPE), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionTypeIdentifierCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_53_0_0 = INSTANCE.getOCL_53_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_53_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_53_0_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_53_0 = INSTANCE.getOCL_53_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_53_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_53_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_53 = INSTANCE.getOCL_53();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_53() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionTypeLiteralExpCS(), OCL_53_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_54_0_0_0 = INSTANCE.getOCL_54_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_54_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getTupleTypeLiteralExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.TUPLE_TYPE_LITERAL_EXP_CS__TUPLE_TYPE), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getTupleTypeCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_54_0_0 = INSTANCE.getOCL_54_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_54_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_54_0_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_54_0 = INSTANCE.getOCL_54_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_54_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_54_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_54 = INSTANCE.getOCL_54();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_54() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getTupleTypeLiteralExpCS(), OCL_54_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_55_0_0_0 = INSTANCE.getOCL_55_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_55_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPropertyCallOnSelfExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PROPERTY_CALL_ON_SELF_EXP_CS__PROPERTY), "SIMPLE_NAME", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_55_0_0_1 = INSTANCE.getOCL_55_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_55_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_55_0_0_2 = INSTANCE.getOCL_55_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_55_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPropertyCallOnSelfExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PROPERTY_CALL_ON_SELF_EXP_CS__IS_MARKED_PRE), "IS_MARKED_PRE", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_55_0_0 = INSTANCE.getOCL_55_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_55_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_55_0_0_0, OCL_55_0_0_1, OCL_55_0_0_2);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_55_0 = INSTANCE.getOCL_55_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_55_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_55_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_55 = INSTANCE.getOCL_55();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_55() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPropertyCallOnSelfExpCS(), OCL_55_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_56_0_0_0 = INSTANCE.getOCL_56_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_56_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("let", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_56_0_0_1 = INSTANCE.getOCL_56_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_56_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLetExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LET_EXP_CS__VARIABLE_DECLARATIONS), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_56_0_0_2_0_0_0 = INSTANCE.getOCL_56_0_0_2_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_56_0_0_2_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_56_0_0_2_0_0_1 = INSTANCE.getOCL_56_0_0_2_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_56_0_0_2_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword(",", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_56_0_0_2_0_0_2 = INSTANCE.getOCL_56_0_0_2_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_56_0_0_2_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLetExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LET_EXP_CS__VARIABLE_DECLARATIONS), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_56_0_0_2_0_0 = INSTANCE.getOCL_56_0_0_2_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_56_0_0_2_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_56_0_0_2_0_0_0, OCL_56_0_0_2_0_0_1, OCL_56_0_0_2_0_0_2);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_56_0_0_2_0 = INSTANCE.getOCL_56_0_0_2_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_56_0_0_2_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_56_0_0_2_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound OCL_56_0_0_2 = INSTANCE.getOCL_56_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound getOCL_56_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound(OCL_56_0_0_2_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.STAR);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_56_0_0_3 = INSTANCE.getOCL_56_0_0_3();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_56_0_0_3() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("in", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak OCL_56_0_0_4 = INSTANCE.getOCL_56_0_0_4();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak getOCL_56_0_0_4() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 1);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_56_0_0_5 = INSTANCE.getOCL_56_0_0_5();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_56_0_0_5() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLetExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LET_EXP_CS__OCL_EXPRESSION), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak OCL_56_0_0_6 = INSTANCE.getOCL_56_0_0_6();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak getOCL_56_0_0_6() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclLineBreak(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_56_0_0 = INSTANCE.getOCL_56_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_56_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_56_0_0_0, OCL_56_0_0_1, OCL_56_0_0_2, OCL_56_0_0_3, OCL_56_0_0_4, OCL_56_0_0_5, OCL_56_0_0_6);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_56_0 = INSTANCE.getOCL_56_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_56_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_56_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_56 = INSTANCE.getOCL_56();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_56() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLetExpCS(), OCL_56_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_57_0_0_0 = INSTANCE.getOCL_57_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_57_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getRealLiteralExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.REAL_LITERAL_EXP_CS__INT_VALUE), "INTEGER_LITERAL", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_57_0_0_1 = INSTANCE.getOCL_57_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_57_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_57_0_0_2 = INSTANCE.getOCL_57_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_57_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getRealLiteralExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.REAL_LITERAL_EXP_CS__NAVIGATION_OPERATOR), "NAVIGATION_OPERATOR", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_57_0_0_3 = INSTANCE.getOCL_57_0_0_3();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_57_0_0_3() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_57_0_0_4_0_0_0 = INSTANCE.getOCL_57_0_0_4_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_57_0_0_4_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getRealLiteralExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.REAL_LITERAL_EXP_CS__REAL_VALUE), "INTEGER_0", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_57_0_0_4_0_0 = INSTANCE.getOCL_57_0_0_4_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_57_0_0_4_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_57_0_0_4_0_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_57_0_0_4_0_1_0 = INSTANCE.getOCL_57_0_0_4_0_1_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_57_0_0_4_0_1_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getRealLiteralExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.REAL_LITERAL_EXP_CS__REAL_VALUE), "INTEGER_LITERAL", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_57_0_0_4_0_1 = INSTANCE.getOCL_57_0_0_4_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_57_0_0_4_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_57_0_0_4_0_1_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_57_0_0_4_0 = INSTANCE.getOCL_57_0_0_4_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_57_0_0_4_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_57_0_0_4_0_0, OCL_57_0_0_4_0_1);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound OCL_57_0_0_4 = INSTANCE.getOCL_57_0_0_4();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound getOCL_57_0_0_4() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclCompound(OCL_57_0_0_4_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_57_0_0 = INSTANCE.getOCL_57_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_57_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_57_0_0_0, OCL_57_0_0_1, OCL_57_0_0_2, OCL_57_0_0_3, OCL_57_0_0_4);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_57_0 = INSTANCE.getOCL_57_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_57_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_57_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_57 = INSTANCE.getOCL_57();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_57() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getRealLiteralExpCS(), OCL_57_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_58_0_0_0 = INSTANCE.getOCL_58_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_58_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIntegerLiteralExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.INTEGER_LITERAL_EXP_CS__INTEGER_LITERAL), "INTEGER_LITERAL", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_58_0_0 = INSTANCE.getOCL_58_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_58_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_58_0_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_58_0 = INSTANCE.getOCL_58_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_58_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_58_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_58 = INSTANCE.getOCL_58();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_58() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIntegerLiteralExpCS(), OCL_58_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_59_0_0_0 = INSTANCE.getOCL_59_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_59_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBooleanLiteralExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.BOOLEAN_LITERAL_EXP_CS__BOOLEAN_LITERAL), "BOOLEAN_LITERAL", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_59_0_0 = INSTANCE.getOCL_59_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_59_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_59_0_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_59_0 = INSTANCE.getOCL_59_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_59_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_59_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_59 = INSTANCE.getOCL_59();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_59() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBooleanLiteralExpCS(), OCL_59_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder OCL_60_0_0_0 = INSTANCE.getOCL_60_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder getOCL_60_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclPlaceholder(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStringLiteralExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.STRING_LITERAL_EXP_CS__STRING_LITERAL), "QUOTED_39_39", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_60_0_0 = INSTANCE.getOCL_60_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_60_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_60_0_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_60_0 = INSTANCE.getOCL_60_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_60_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_60_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_60 = INSTANCE.getOCL_60();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_60() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStringLiteralExpCS(), OCL_60_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_61_0_0_0 = INSTANCE.getOCL_61_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_61_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("invalid", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_61_0_0 = INSTANCE.getOCL_61_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_61_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_61_0_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_61_0 = INSTANCE.getOCL_61_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_61_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_61_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_61 = INSTANCE.getOCL_61();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_61() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInvalidLiteralExpCS(), OCL_61_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_62_0_0_0 = INSTANCE.getOCL_62_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_62_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("null", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_62_0_0 = INSTANCE.getOCL_62_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_62_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_62_0_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_62_0 = INSTANCE.getOCL_62_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_62_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_62_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_62 = INSTANCE.getOCL_62();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_62() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getNullLiteralExpCS(), OCL_62_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_63_0_0_0 = INSTANCE.getOCL_63_0_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_63_0_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword("(", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_63_0_0_1 = INSTANCE.getOCL_63_0_0_1();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_63_0_0_1() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment OCL_63_0_0_2 = INSTANCE.getOCL_63_0_0_2();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment getOCL_63_0_0_2() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclContainment(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBracketExpCS().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.BRACKET_EXP_CS__OCL_EXPRESSION), org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, new EClass[] {org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOclExpressionCS(), }, 0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace OCL_63_0_0_3 = INSTANCE.getOCL_63_0_0_3();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace getOCL_63_0_0_3() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclWhiteSpace(0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword OCL_63_0_0_4 = INSTANCE.getOCL_63_0_0_4();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword getOCL_63_0_0_4() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword(")", org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence OCL_63_0_0 = INSTANCE.getOCL_63_0_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence getOCL_63_0_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclSequence(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_63_0_0_0, OCL_63_0_0_1, OCL_63_0_0_2, OCL_63_0_0_3, OCL_63_0_0_4);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice OCL_63_0 = INSTANCE.getOCL_63_0();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice getOCL_63_0() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclChoice(org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE, OCL_63_0_0);
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule OCL_63 = INSTANCE.getOCL_63();
	private org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule getOCL_63() {
		return new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule(org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBracketExpCS(), OCL_63_0, org.dresdenocl.language.ocl.resource.ocl.grammar.OclCardinality.ONE);
	}
	
	
	public static String getSyntaxElementID(org.dresdenocl.language.ocl.resource.ocl.grammar.OclSyntaxElement syntaxElement) {
		if (syntaxElement == null) {
			// null indicates EOF
			return "<EOF>";
		}
		for (Field field : org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.class.getFields()) {
			Object fieldValue;
			try {
				fieldValue = field.get(null);
				if (fieldValue == syntaxElement) {
					String id = field.getName();
					return id;
				}
			} catch (Exception e) { }
		}
		return null;
	}
	
	public static org.dresdenocl.language.ocl.resource.ocl.grammar.OclSyntaxElement getSyntaxElementByID(String syntaxElementID) {
		try {
			return (org.dresdenocl.language.ocl.resource.ocl.grammar.OclSyntaxElement) org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.class.getField(syntaxElementID).get(null);
		} catch (Exception e) {
			return null;
		}
	}
	
	public final static org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule[] RULES = new org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule[] {
		OCL_0,
		OCL_1,
		OCL_2,
		OCL_3,
		OCL_4,
		OCL_5,
		OCL_6,
		OCL_7,
		OCL_8,
		OCL_9,
		OCL_10,
		OCL_11,
		OCL_12,
		OCL_13,
		OCL_14,
		OCL_15,
		OCL_16,
		OCL_17,
		OCL_18,
		OCL_19,
		OCL_20,
		OCL_21,
		OCL_22,
		OCL_23,
		OCL_24,
		OCL_25,
		OCL_26,
		OCL_27,
		OCL_28,
		OCL_29,
		OCL_30,
		OCL_31,
		OCL_32,
		OCL_33,
		OCL_34,
		OCL_35,
		OCL_36,
		OCL_37,
		OCL_38,
		OCL_39,
		OCL_40,
		OCL_41,
		OCL_42,
		OCL_43,
		OCL_44,
		OCL_45,
		OCL_46,
		OCL_47,
		OCL_48,
		OCL_49,
		OCL_50,
		OCL_51,
		OCL_52,
		OCL_53,
		OCL_54,
		OCL_55,
		OCL_56,
		OCL_57,
		OCL_58,
		OCL_59,
		OCL_60,
		OCL_61,
		OCL_62,
		OCL_63,
	};
	
	/**
	 * Returns all keywords of the grammar. This includes all literals for boolean and
	 * enumeration terminals.
	 */
	public Set<String> getKeywords() {
		if (this.keywords == null) {
			this.keywords = new LinkedHashSet<String>();
			for (org.dresdenocl.language.ocl.resource.ocl.grammar.OclRule rule : RULES) {
				findKeywords(rule, this.keywords);
			}
		}
		return keywords;
	}
	
	/**
	 * Finds all keywords in the given element and its children and adds them to the
	 * set. This includes all literals for boolean and enumeration terminals.
	 */
	private void findKeywords(org.dresdenocl.language.ocl.resource.ocl.grammar.OclSyntaxElement element, Set<String> keywords) {
		if (element instanceof org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword) {
			keywords.add(((org.dresdenocl.language.ocl.resource.ocl.grammar.OclKeyword) element).getValue());
		} else if (element instanceof org.dresdenocl.language.ocl.resource.ocl.grammar.OclBooleanTerminal) {
			keywords.add(((org.dresdenocl.language.ocl.resource.ocl.grammar.OclBooleanTerminal) element).getTrueLiteral());
			keywords.add(((org.dresdenocl.language.ocl.resource.ocl.grammar.OclBooleanTerminal) element).getFalseLiteral());
		} else if (element instanceof org.dresdenocl.language.ocl.resource.ocl.grammar.OclEnumerationTerminal) {
			org.dresdenocl.language.ocl.resource.ocl.grammar.OclEnumerationTerminal terminal = (org.dresdenocl.language.ocl.resource.ocl.grammar.OclEnumerationTerminal) element;
			for (String key : terminal.getLiteralMapping().keySet()) {
				keywords.add(key);
			}
		}
		for (org.dresdenocl.language.ocl.resource.ocl.grammar.OclSyntaxElement child : element.getChildren()) {
			findKeywords(child, this.keywords);
		}
	}
	
}
