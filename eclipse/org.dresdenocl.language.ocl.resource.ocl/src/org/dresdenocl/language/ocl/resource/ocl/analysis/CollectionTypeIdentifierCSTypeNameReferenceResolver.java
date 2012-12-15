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
import org.dresdenocl.pivotmodel.Type;

public class CollectionTypeIdentifierCSTypeNameReferenceResolver
		implements
		org.dresdenocl.language.ocl.resource.ocl.IOclReferenceResolver<org.dresdenocl.language.ocl.CollectionTypeIdentifierCS, org.dresdenocl.pivotmodel.Type> {

	private org.dresdenocl.language.ocl.resource.ocl.analysis.OclDefaultResolverDelegate<org.dresdenocl.language.ocl.CollectionTypeIdentifierCS, org.dresdenocl.pivotmodel.Type> delegate = new org.dresdenocl.language.ocl.resource.ocl.analysis.OclDefaultResolverDelegate<org.dresdenocl.language.ocl.CollectionTypeIdentifierCS, org.dresdenocl.pivotmodel.Type>();

	public void resolve(
			java.lang.String identifier,
			org.dresdenocl.language.ocl.CollectionTypeIdentifierCS container,
			org.eclipse.emf.ecore.EReference reference,
			int position,
			boolean resolveFuzzy,
			final org.dresdenocl.language.ocl.resource.ocl.IOclReferenceResolveResult<org.dresdenocl.pivotmodel.Type> result) {

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
			org.dresdenocl.pivotmodel.Type element,
			org.dresdenocl.language.ocl.CollectionTypeIdentifierCS container,
			org.eclipse.emf.ecore.EReference reference) {
		return delegate.deResolve(element, container, reference);
	}

	public void setOptions(java.util.Map<?, ?> options) {
		// save options in a field or leave method empty if this resolver does not
		// depend on any option
	}

}
