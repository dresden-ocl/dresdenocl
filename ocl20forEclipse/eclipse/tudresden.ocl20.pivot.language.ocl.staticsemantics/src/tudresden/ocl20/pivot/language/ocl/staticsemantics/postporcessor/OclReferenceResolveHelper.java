package tudresden.ocl20.pivot.language.ocl.staticsemantics.postporcessor;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import tudresden.ocl20.pivot.language.ocl.OclExpressionCS;
import tudresden.ocl20.pivot.language.ocl.ParameterCS;
import tudresden.ocl20.pivot.language.ocl.TypeCS;
import tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolveHelper;
import tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclResource;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.TypedElement;

public class OclReferenceResolveHelper implements IOclReferenceResolveHelper {

	@Override
	public List<Namespace> resolveNamespace(String identifier,
			boolean resolveFuzzy, EObject container) {
		List<Namespace> ret = OclStaticSemanticsProvider.getStaticSemantics(
				(OclResource) container.eResource()).resolveNamespace(identifier,
				resolveFuzzy, container);
		return ret;
	}

	@Override
	public List<Type> resolveType(String identifier, boolean resolveFuzzy,
			EObject container) {
		List<Type> ret = OclStaticSemanticsProvider.getStaticSemantics(
				(OclResource) container.eResource()).resolveType(identifier,
				resolveFuzzy, container);
		return ret;
	}

	@Override
	public List<TypedElement> resolveTypedElement(String identifier,
			boolean resolveFuzzy, EObject container) {
		List<TypedElement> ret = OclStaticSemanticsProvider.getStaticSemantics(
				(OclResource) container.eResource()).resolveTypedElement(identifier,
				resolveFuzzy, container);
		return ret;
	}

	@Override
	public List<Property> resolveProperty(String identifier,
			boolean resolveFuzzy, EObject container) {
		List<Property> ret = OclStaticSemanticsProvider.getStaticSemantics(
				(OclResource) container.eResource()).resolveProperty(identifier,
				resolveFuzzy, container);
		return ret;
	}

	@Override
	public List<Property> resolvePropertyDefinition(String identifier,
			boolean resolveFuzzy, EObject container) {
		List<Property> ret = OclStaticSemanticsProvider.getStaticSemantics(
				(OclResource) container.eResource()).resolvePropertyDefinition(identifier,
				resolveFuzzy, container);
		return ret;
	}

	@Override
	public List<Operation> resolveOperation(String identifier,
			boolean resolveFuzzy, EObject container, EReference reference,
			List<OclExpressionCS> parameters) {
		List<Operation> ret = OclStaticSemanticsProvider.getStaticSemantics(
				(OclResource) container.eResource()).resolveOperation(identifier,
				resolveFuzzy, container, reference, parameters);
		return ret;
	}

	@Override
	public List<Operation> resolveOperationDefinition(String identifier,
			boolean resolveFuzzy, EObject container, EReference reference,
			List<ParameterCS> parameters, TypeCS returnType) {
		List<Operation> ret = OclStaticSemanticsProvider.getStaticSemantics(
				(OclResource) container.eResource()).resolveOperationDefinition(
				identifier, resolveFuzzy, container, reference, parameters,
				returnType);
		return ret;
	}

	@Override
	public List<Parameter> resolveParameterDefinition(String identifier,
			boolean resolveFuzzy, EObject container, EReference reference,
			TypeCS parameterType) {
		List<Parameter> ret = OclStaticSemanticsProvider.getStaticSemantics(
				(OclResource) container.eResource()).resolveParameterDefinition(
				identifier, resolveFuzzy, container, reference, parameterType);
		return ret;
	}

}
