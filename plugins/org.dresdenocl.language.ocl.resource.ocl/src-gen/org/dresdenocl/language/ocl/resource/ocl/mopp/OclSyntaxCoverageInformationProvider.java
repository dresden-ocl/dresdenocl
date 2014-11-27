/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.mopp;

import org.eclipse.emf.ecore.EClass;

public class OclSyntaxCoverageInformationProvider {
	
	public EClass[] getClassesWithSyntax() {
		return new EClass[] {
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getSimpleNameCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationNestedNamespaceCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithoutNamespaceCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInitValueCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDeriveValueCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInvariantExpCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpPropertyCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getDefinitionExpOperationCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPreConditionDeclarationCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPostConditionDeclarationCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBodyDeclarationCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationDefinitionInContextCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationDefinitionInDefCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getParameterCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalImpliesOperationCallExpCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalXorOperationCallExpCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalOrOperationCallExpCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalAndOperationCallExpCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getEqualityOperationCallExpCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getRelationalOperationCallExpCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getAdditiveOperationCallExpCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getMultOperationCallExpCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getUnaryOperationCallExpCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLogicalNotOperationCallExpCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getNavigationCallExp(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitOperationCallCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getImplicitPropertyCallCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIterateExpCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIteratorExpVariableCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getTupleTypeCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionTypeIdentifierCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getTypeModelElementCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithoutInitCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithoutInitListCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getVariableDeclarationWithInitListCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getOperationCallOnSelfExpCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getModelElementCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPathNameSimpleCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPathNamePathCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getNamedElementCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getTupleLiteralExpCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIfExpCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralExpCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionRangeCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionLiteralPartsOclExpCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getCollectionTypeLiteralExpCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getTupleTypeLiteralExpCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPropertyCallOnSelfExpCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getLetExpCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getRealLiteralExpCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getIntegerLiteralExpCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBooleanLiteralExpCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getStringLiteralExpCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getInvalidLiteralExpCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getNullLiteralExpCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getBracketExpCS(),
		};
	}
	
	public EClass[] getStartSymbols() {
		return new EClass[] {
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithoutNamespaceCS(),
		};
	}
	
}
