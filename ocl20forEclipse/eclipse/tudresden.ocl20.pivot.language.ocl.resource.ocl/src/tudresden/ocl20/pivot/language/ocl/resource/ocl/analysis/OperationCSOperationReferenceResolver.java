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
import tudresden.ocl20.pivot.pivotmodel.Operation;

public class OperationCSOperationReferenceResolver
		implements
		tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolver<tudresden.ocl20.pivot.language.ocl.OperationCS, tudresden.ocl20.pivot.pivotmodel.Operation> {

	private tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.OclDefaultResolverDelegate<tudresden.ocl20.pivot.language.ocl.OperationCS, tudresden.ocl20.pivot.pivotmodel.Operation> delegate = new tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.OclDefaultResolverDelegate<tudresden.ocl20.pivot.language.ocl.OperationCS, tudresden.ocl20.pivot.pivotmodel.Operation>();

	public void resolve(
			java.lang.String identifier,
			tudresden.ocl20.pivot.language.ocl.OperationCS container,
			org.eclipse.emf.ecore.EReference reference,
			int position,
			boolean resolveFuzzy,
			final tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolveResult<tudresden.ocl20.pivot.pivotmodel.Operation> result) {
		IOclReferenceResolveHelper rrHelper = OclReferenceResolveHelperProvider
				.getOclReferenceResolveHelper();
		if (rrHelper != null) {
			List<Operation> operations = rrHelper.resolveOperationDefinition(
					identifier, resolveFuzzy, container, reference, container
							.getParameters(), container.getTypeName(), container.getReturnType());
			for (Operation operation : operations) {
				if (!resolveFuzzy)
					result.addMapping(identifier, operation);
				else
					result.addMapping(operation.getName(), operation);
			}
		}
	}

	public java.lang.String deResolve(
			tudresden.ocl20.pivot.pivotmodel.Operation element,
			tudresden.ocl20.pivot.language.ocl.OperationCS container,
			org.eclipse.emf.ecore.EReference reference) {
		return delegate.deResolve(element, container, reference);
	}

	public void setOptions(java.util.Map<?, ?> options) {
		// save options in a field or leave method empty if this resolver does not
		// depend on any option
	}

}
