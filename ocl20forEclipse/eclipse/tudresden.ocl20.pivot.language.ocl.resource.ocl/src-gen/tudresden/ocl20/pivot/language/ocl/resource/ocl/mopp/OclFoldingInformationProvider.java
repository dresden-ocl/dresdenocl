/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp;

public class OclFoldingInformationProvider {
	
	public org.eclipse.emf.ecore.EClass[] getFoldableClasses() {
		return new org.eclipse.emf.ecore.EClass[] {
			tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getOperationContextDeclarationCS(),
			tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS(),
			tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getClassifierContextDeclarationCS(),
			tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getInitValueCS(),
			tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getDeriveValueCS(),
			tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getInvariantExpCS(),
			tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getDefinitionExpCS(),
			tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getPreConditionDeclarationCS(),
			tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getPostConditionDeclarationCS(),
			tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getBodyDeclarationCS(),
		};
	}
	
}
