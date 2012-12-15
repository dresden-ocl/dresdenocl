/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis;

import java.util.List;

import tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolveHelper;
import tudresden.ocl20.pivot.language.ocl.resource.ocl.OclReferenceResolveHelperProvider;
import tudresden.ocl20.pivot.pivotmodel.Parameter;

public class ParameterCSParameterReferenceResolver
		implements
		tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolver<tudresden.ocl20.pivot.language.ocl.ParameterCS, tudresden.ocl20.pivot.pivotmodel.Parameter> {

	private tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.OclDefaultResolverDelegate<tudresden.ocl20.pivot.language.ocl.ParameterCS, tudresden.ocl20.pivot.pivotmodel.Parameter> delegate = new tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.OclDefaultResolverDelegate<tudresden.ocl20.pivot.language.ocl.ParameterCS, tudresden.ocl20.pivot.pivotmodel.Parameter>();

	public void resolve(
			java.lang.String identifier,
			tudresden.ocl20.pivot.language.ocl.ParameterCS container,
			org.eclipse.emf.ecore.EReference reference,
			int position,
			boolean resolveFuzzy,
			final tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolveResult<tudresden.ocl20.pivot.pivotmodel.Parameter> result) {
		IOclReferenceResolveHelper rrHelper = OclReferenceResolveHelperProvider
				.getOclReferenceResolveHelper();
		if (rrHelper != null) {
			List<Parameter> parameters = rrHelper.resolveParameterDefinition(
					identifier, resolveFuzzy, container, reference, container
							.getParameterType());
			for (Parameter parameter : parameters) {
				if (!resolveFuzzy)
					result.addMapping(identifier, parameter);
				else
					result.addMapping(parameter.getName(), parameter);
			}
		}
	}

	public java.lang.String deResolve(
			tudresden.ocl20.pivot.pivotmodel.Parameter element,
			tudresden.ocl20.pivot.language.ocl.ParameterCS container,
			org.eclipse.emf.ecore.EReference reference) {
		return delegate.deResolve(element, container, reference);
	}

	public void setOptions(java.util.Map<?, ?> options) {
		// save options in a field or leave method empty if this resolver does not
		// depend on any option
	}

}
