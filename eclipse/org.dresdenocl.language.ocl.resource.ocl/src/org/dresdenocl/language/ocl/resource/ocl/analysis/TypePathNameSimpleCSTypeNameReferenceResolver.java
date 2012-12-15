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
import tudresden.ocl20.pivot.pivotmodel.Type;

public class TypePathNameSimpleCSTypeNameReferenceResolver
		implements
		tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolver<tudresden.ocl20.pivot.language.ocl.TypePathNameSimpleCS, tudresden.ocl20.pivot.pivotmodel.Type> {

	private tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.OclDefaultResolverDelegate<tudresden.ocl20.pivot.language.ocl.TypePathNameSimpleCS, tudresden.ocl20.pivot.pivotmodel.Type> delegate = new tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.OclDefaultResolverDelegate<tudresden.ocl20.pivot.language.ocl.TypePathNameSimpleCS, tudresden.ocl20.pivot.pivotmodel.Type>();

	public void resolve(
			java.lang.String identifier,
			tudresden.ocl20.pivot.language.ocl.TypePathNameSimpleCS container,
			org.eclipse.emf.ecore.EReference reference,
			int position,
			boolean resolveFuzzy,
			final tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolveResult<tudresden.ocl20.pivot.pivotmodel.Type> result) {

		IOclReferenceResolveHelper rrHelper = OclReferenceResolveHelperProvider
				.getOclReferenceResolveHelper();
		if (rrHelper != null) {
			List<Type> types = rrHelper.resolveType(identifier, resolveFuzzy,
					container);
			for (Type type : types) {
				if (!resolveFuzzy)
					result.addMapping(identifier, type);
				else
					result.addMapping(type.getName(), type);
			}
		}
	}

	public java.lang.String deResolve(
			tudresden.ocl20.pivot.pivotmodel.Type element,
			tudresden.ocl20.pivot.language.ocl.TypePathNameSimpleCS container,
			org.eclipse.emf.ecore.EReference reference) {
		return delegate.deResolve(element, container, reference);
	}

	public void setOptions(java.util.Map<?, ?> options) {
		// save options in a field or leave method empty if this resolver does not
		// depend on any option
	}

}
