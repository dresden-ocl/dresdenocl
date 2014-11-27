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
import org.dresdenocl.pivotmodel.Operation;

public class OperationDefinitionCSOperationReferenceResolver
		implements
		org.dresdenocl.language.ocl.resource.ocl.IOclReferenceResolver<org.dresdenocl.language.ocl.OperationDefinitionCS, org.dresdenocl.pivotmodel.Operation> {

	private org.dresdenocl.language.ocl.resource.ocl.analysis.OclDefaultResolverDelegate<org.dresdenocl.language.ocl.OperationDefinitionCS, org.dresdenocl.pivotmodel.Operation> delegate = new org.dresdenocl.language.ocl.resource.ocl.analysis.OclDefaultResolverDelegate<org.dresdenocl.language.ocl.OperationDefinitionCS, org.dresdenocl.pivotmodel.Operation>();

	public void resolve(
			java.lang.String identifier,
			org.dresdenocl.language.ocl.OperationDefinitionCS container,
			org.eclipse.emf.ecore.EReference reference,
			int position,
			boolean resolveFuzzy,
			final org.dresdenocl.language.ocl.resource.ocl.IOclReferenceResolveResult<org.dresdenocl.pivotmodel.Operation> result) {
		IOclReferenceResolveHelper rrHelper = OclReferenceResolveHelperProvider
				.getOclReferenceResolveHelper();

		if (rrHelper != null) {
			List<Operation> operations = rrHelper.resolveOperationDefinition(
					identifier, resolveFuzzy, container, reference, container
							.getParameters(), container.getReturnType());
			for (Operation operation : operations) {
				if (!resolveFuzzy)
					result.addMapping(identifier, operation);
				else
					result.addMapping(operation.getName(), operation);
			}
		}
	}

	public java.lang.String deResolve(
			org.dresdenocl.pivotmodel.Operation element,
			org.dresdenocl.language.ocl.OperationDefinitionCS container,
			org.eclipse.emf.ecore.EReference reference) {
		return delegate.deResolve(element, container, reference);
	}

	public void setOptions(java.util.Map<?, ?> options) {
		// save options in a field or leave method empty if this resolver does not
		// depend on any option
	}

}
