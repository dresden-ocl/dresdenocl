package tudresden.ocl20.pivot.language.ocl.staticsemantics.postporcessor;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import tudresden.ocl20.pivot.essentialocl.types.OclLibrary;
import tudresden.ocl20.pivot.language.ocl.OclExpressionCS;
import tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolveHelper;
import tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclResource;
import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Property;
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

	@Override
	public List<Property> resolveProperty(String identifier,
			boolean resolveFuzzy, EObject container, IModel model,
			OclLibrary oclLibrary) {
		List<Property> ret = OclStaticSemanticsProvider.getStaticSemantics(model,
				oclLibrary, (OclResource) container.eResource()).resolveProperty(
				identifier, resolveFuzzy, container);
		return ret;
	}

	@Override
	public List<Operation> resolveOperation(String identifier,
			boolean resolveFuzzy, EObject container, EReference reference,
			List<OclExpressionCS> parameters, IModel model, OclLibrary oclLibrary) {
		List<Operation> ret = OclStaticSemanticsProvider.getStaticSemantics(model,
				oclLibrary, (OclResource) container.eResource()).resolveOperation(
				identifier, resolveFuzzy, container, reference, parameters);
		return ret;
	}

}
