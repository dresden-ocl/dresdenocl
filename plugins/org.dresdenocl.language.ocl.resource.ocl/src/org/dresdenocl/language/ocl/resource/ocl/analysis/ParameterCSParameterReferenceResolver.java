/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.analysis;

import java.util.List;

import org.dresdenocl.language.ocl.resource.ocl.IOclReferenceResolveHelper;
import org.dresdenocl.language.ocl.resource.ocl.OclReferenceResolveHelperProvider;
import org.dresdenocl.pivotmodel.Parameter;

public class ParameterCSParameterReferenceResolver
		implements
		org.dresdenocl.language.ocl.resource.ocl.IOclReferenceResolver<org.dresdenocl.language.ocl.ParameterCS, org.dresdenocl.pivotmodel.Parameter> {

	private org.dresdenocl.language.ocl.resource.ocl.analysis.OclDefaultResolverDelegate<org.dresdenocl.language.ocl.ParameterCS, org.dresdenocl.pivotmodel.Parameter> delegate = new org.dresdenocl.language.ocl.resource.ocl.analysis.OclDefaultResolverDelegate<org.dresdenocl.language.ocl.ParameterCS, org.dresdenocl.pivotmodel.Parameter>();

	public void resolve(
			java.lang.String identifier,
			org.dresdenocl.language.ocl.ParameterCS container,
			org.eclipse.emf.ecore.EReference reference,
			int position,
			boolean resolveFuzzy,
			final org.dresdenocl.language.ocl.resource.ocl.IOclReferenceResolveResult<org.dresdenocl.pivotmodel.Parameter> result) {
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
			org.dresdenocl.pivotmodel.Parameter element,
			org.dresdenocl.language.ocl.ParameterCS container,
			org.eclipse.emf.ecore.EReference reference) {
		return delegate.deResolve(element, container, reference);
	}

	public void setOptions(java.util.Map<?, ?> options) {
		// save options in a field or leave method empty if this resolver does not
		// depend on any option
	}

}
