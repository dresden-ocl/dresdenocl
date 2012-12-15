/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis;

public class OclIS_MARKED_PRETokenResolver implements
		tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTokenResolver {

	private tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.OclDefaultTokenResolver defaultTokenResolver = new tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.OclDefaultTokenResolver();

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
			tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTokenResolveResult result) {
		if (lexem.equals("@pre"))
			result.setResolvedToken(true);
		else
			result.setResolvedToken(false);
	}

	public void setOptions(java.util.Map<?, ?> options) {
		defaultTokenResolver.setOptions(options);
	}

}
