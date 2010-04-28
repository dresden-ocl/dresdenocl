/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis;

public class OclQUOTED_36_36TokenResolver implements tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTokenResolver {
	
	private tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.OclDefaultTokenResolver defaultTokenResolver = new tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.OclDefaultTokenResolver();
	
	public java.lang.String deResolve(java.lang.Object value, org.eclipse.emf.ecore.EStructuralFeature feature, org.eclipse.emf.ecore.EObject container) {
		java.lang.String result = defaultTokenResolver.deResolve(value, feature, container);
		result += "$";
		result = "$" + result;
		return result;
	}
	
	public void resolve(java.lang.String lexem, org.eclipse.emf.ecore.EStructuralFeature feature, tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTokenResolveResult result) {
		lexem = lexem.substring(1);
		lexem = lexem.substring(0, lexem.length() - 1);
		defaultTokenResolver.resolve(lexem, feature, result);
	}
	
	public void setOptions(java.util.Map<?,?> options) {
		defaultTokenResolver.setOptions(options);
	}
	
}
