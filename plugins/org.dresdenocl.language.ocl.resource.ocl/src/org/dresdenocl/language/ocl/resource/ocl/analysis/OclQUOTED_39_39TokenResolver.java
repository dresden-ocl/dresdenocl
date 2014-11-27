/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.analysis;

public class OclQUOTED_39_39TokenResolver implements org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver {
	
	private org.dresdenocl.language.ocl.resource.ocl.analysis.OclDefaultTokenResolver defaultTokenResolver = new org.dresdenocl.language.ocl.resource.ocl.analysis.OclDefaultTokenResolver();
	
	public java.lang.String deResolve(java.lang.Object value, org.eclipse.emf.ecore.EStructuralFeature feature, org.eclipse.emf.ecore.EObject container) {
		java.lang.String result = defaultTokenResolver.deResolve(value, feature, container);
		result += "'";
		result = "'" + result;
		return result;
	}
	
	public void resolve(java.lang.String lexem, org.eclipse.emf.ecore.EStructuralFeature feature, org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result) {
		lexem = lexem.substring(1);
		lexem = lexem.substring(0, lexem.length() - 1);
		defaultTokenResolver.resolve(lexem, feature, result);
	}
	
	public void setOptions(java.util.Map<?,?> options) {
		defaultTokenResolver.setOptions(options);
	}
	
}
