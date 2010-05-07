/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis;

import java.util.List;

import tudresden.ocl20.pivot.essentialocl.EssentialOclPlugin;
import tudresden.ocl20.pivot.essentialocl.types.OclLibrary;
import tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolveHelper;
import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.pivotmodel.Operation;

public class ImplicitOperationCallCSOperationNameReferenceResolver implements tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolver<tudresden.ocl20.pivot.language.ocl.ImplicitOperationCallCS, tudresden.ocl20.pivot.pivotmodel.Operation> {
	
	private tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.OclDefaultResolverDelegate<tudresden.ocl20.pivot.language.ocl.ImplicitOperationCallCS, tudresden.ocl20.pivot.pivotmodel.Operation> delegate = new tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.OclDefaultResolverDelegate<tudresden.ocl20.pivot.language.ocl.ImplicitOperationCallCS, tudresden.ocl20.pivot.pivotmodel.Operation>();
	
	public void resolve(java.lang.String identifier, tudresden.ocl20.pivot.language.ocl.ImplicitOperationCallCS container, org.eclipse.emf.ecore.EReference reference, int position, boolean resolveFuzzy, final tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolveResult<tudresden.ocl20.pivot.pivotmodel.Operation> result) {
		IModel model = ModelBusPlugin.getModelRegistry().getActiveModel();
		if (model == null)
			return;
		OclLibrary oclLibrary = EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary();

		IOclReferenceResolveHelper rrHelper = OclReferenceResolveHelperProvider
				.getOclReferenceResolveHelper();
		if (rrHelper != null) {
			List<Operation> operations = rrHelper.resolveOperation(
					identifier, resolveFuzzy, container, reference, container.getArguments(), model, oclLibrary);
			for (Operation operation : operations) {
				if (!resolveFuzzy)
					result.addMapping(identifier, operation);
				else
					result.addMapping(operation.getName(), operation);
			}
		}
	}
	
	public java.lang.String deResolve(tudresden.ocl20.pivot.pivotmodel.Operation element, tudresden.ocl20.pivot.language.ocl.ImplicitOperationCallCS container, org.eclipse.emf.ecore.EReference reference) {
		return delegate.deResolve(element, container, reference);
	}
	
	public void setOptions(java.util.Map<?,?> options) {
		// save options in a field or leave method empty if this resolver does not depend on any option
	}
	
}
