package tudresden.ocl20.pivot.language.ocl.staticsemantics.postporcessor;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import tudresden.ocl20.pivot.essentialocl.types.OclLibrary;
import tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolveHelper;
import tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclResource;
import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.TypedElement;

public class OclReferenceResolveHelper implements IOclReferenceResolveHelper {

	@Override
	public List<Namespace> resolveNamespace(String identifier,
			boolean resolveFuzzy, EObject container, IModel model,
			OclLibrary oclLibrary) {
		List<Namespace> ret = OclStaticSemanticsProvider.getStaticSemantics(model,
				oclLibrary, (OclResource) container.eResource()).resolveNamespace(
				identifier, resolveFuzzy, container);
		return ret;
	}

	@Override
	public List<Type> resolveType(String identifier, boolean resolveFuzzy,
			EObject container, IModel model, OclLibrary oclLibrary) {

		List<Type> ret = OclStaticSemanticsProvider.getStaticSemantics(model,
				oclLibrary, (OclResource) container.eResource()).resolveType(
				identifier, resolveFuzzy, container);
		return ret;
	}

	@Override
	public List<TypedElement> resolveTypedElement(String identifier,
			boolean resolveFuzzy, EObject container, IModel model,
			OclLibrary oclLibrary) {
		List<TypedElement> ret = OclStaticSemanticsProvider.getStaticSemantics(
				model, oclLibrary, (OclResource) container.eResource())
				.resolveTypedElement(identifier, resolveFuzzy, container);
		return ret;
	}

}
