/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis;

public class OclOR_OPERATORTokenResolver implements tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTokenResolver {
	
	private tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.OclDefaultTokenResolver defaultTokenResolver = new tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.OclDefaultTokenResolver();
	
	public java.lang.String deResolve(java.lang.Object value, org.eclipse.emf.ecore.EStructuralFeature feature, org.eclipse.emf.ecore.EObject container) {
		java.lang.String result = defaultTokenResolver.deResolve(value, feature, container);
		return result;
	}
	
	public void resolve(java.lang.String lexem, org.eclipse.emf.ecore.EStructuralFeature feature, tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTokenResolveResult result) {
		defaultTokenResolver.resolve(lexem, feature, result);
	}
	
	public void setOptions(java.util.Map<?,?> options) {
		defaultTokenResolver.setOptions(options);
	}
	
}
