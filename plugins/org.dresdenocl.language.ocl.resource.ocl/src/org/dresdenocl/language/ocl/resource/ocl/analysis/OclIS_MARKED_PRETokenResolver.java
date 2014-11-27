/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.analysis;

public class OclIS_MARKED_PRETokenResolver implements
		org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver {

	private org.dresdenocl.language.ocl.resource.ocl.analysis.OclDefaultTokenResolver defaultTokenResolver = new org.dresdenocl.language.ocl.resource.ocl.analysis.OclDefaultTokenResolver();

	public java.lang.String deResolve(java.lang.Object value,
			org.eclipse.emf.ecore.EStructuralFeature feature,
			org.eclipse.emf.ecore.EObject container) {
		if (value.equals(true))
			return "@pre";
		else
			return "";
	}

	public void resolve(
			java.lang.String lexem,
			org.eclipse.emf.ecore.EStructuralFeature feature,
			org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result) {
		if (lexem.equals("@pre"))
			result.setResolvedToken(true);
		else
			result.setResolvedToken(false);
	}

	public void setOptions(java.util.Map<?, ?> options) {
		defaultTokenResolver.setOptions(options);
	}

}
