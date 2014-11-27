/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.analysis;

public class OclSTATICTokenResolver implements
		org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver {

	private org.dresdenocl.language.ocl.resource.ocl.analysis.OclDefaultTokenResolver defaultTokenResolver = new org.dresdenocl.language.ocl.resource.ocl.analysis.OclDefaultTokenResolver();

	public java.lang.String deResolve(java.lang.Object value,
			org.eclipse.emf.ecore.EStructuralFeature feature,
			org.eclipse.emf.ecore.EObject container) {
		java.lang.String result = "";
		if (value.equals(true))
			result = "static";
		return result;
	}

	public void resolve(
			java.lang.String lexem,
			org.eclipse.emf.ecore.EStructuralFeature feature,
			org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result) {
		if (lexem.equals("static"))
			result.setResolvedToken(true);
		else
			result.setResolvedToken(false);
	}

	public void setOptions(java.util.Map<?, ?> options) {
		defaultTokenResolver.setOptions(options);
	}

}
