package org.dresdenocl.language.ocl.staticsemantics.postporcessor;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import org.dresdenocl.language.ocl.OclExpressionCS;
import org.dresdenocl.language.ocl.ParameterCS;
import org.dresdenocl.language.ocl.TypeCS;
import org.dresdenocl.language.ocl.resource.ocl.IOclReferenceResolveHelper;
import org.dresdenocl.language.ocl.resource.ocl.mopp.IOclResource;
import org.dresdenocl.pivotmodel.NamedElement;
import org.dresdenocl.pivotmodel.Namespace;
import org.dresdenocl.pivotmodel.Operation;
import org.dresdenocl.pivotmodel.Parameter;
import org.dresdenocl.pivotmodel.Property;
import org.dresdenocl.pivotmodel.Type;

public class OclReferenceResolveHelper implements IOclReferenceResolveHelper {

	public List<Namespace> resolveNamespace(String identifier,
			boolean resolveFuzzy, EObject container) {
		List<Namespace> ret = OclStaticSemanticsProvider.getStaticSemantics(
				(IOclResource) container.eResource()).resolveNamespace(identifier,
				resolveFuzzy, container);
		return ret;
	}

	public List<Type> resolveType(String identifier, boolean resolveFuzzy,
			EObject container) {
		List<Type> ret = OclStaticSemanticsProvider.getStaticSemantics(
				(IOclResource) container.eResource()).resolveType(identifier,
				resolveFuzzy, container);
		return ret;
	}

	public List<NamedElement> resolveNamedElement(String identifier,
			boolean resolveFuzzy, EObject container) {
		List<NamedElement> ret = OclStaticSemanticsProvider.getStaticSemantics(
				(IOclResource) container.eResource()).resolveNamedElement(identifier,
				resolveFuzzy, container);
		return ret;
	}

	public List<Property> resolveProperty(String identifier,
			boolean resolveFuzzy, EObject container) {
		List<Property> ret = OclStaticSemanticsProvider.getStaticSemantics(
				(IOclResource) container.eResource()).resolveProperty(identifier,
				resolveFuzzy, container);
		return ret;
	}

	public List<Property> resolvePropertyDefinition(String identifier,
			boolean resolveFuzzy, EObject container) {
		List<Property> ret = OclStaticSemanticsProvider.getStaticSemantics(
				(IOclResource) container.eResource()).resolvePropertyDefinition(identifier,
				resolveFuzzy, container);
		return ret;
	}

	public List<Operation> resolveOperation(String identifier,
			boolean resolveFuzzy, EObject container, EReference reference,
			List<OclExpressionCS> parameters, boolean isStatic) {
		List<Operation> ret = OclStaticSemanticsProvider.getStaticSemantics(
				(IOclResource) container.eResource()).resolveOperation(identifier,
				resolveFuzzy, container, reference, parameters, isStatic);
		return ret;
	}

	public List<Operation> resolveOperationDefinition(String identifier,
			boolean resolveFuzzy, EObject container, EReference reference,
			List<ParameterCS> parameters, TypeCS returnType) {
		List<Operation> ret = OclStaticSemanticsProvider.getStaticSemantics(
				(IOclResource) container.eResource()).resolveOperationDefinition(
				identifier, resolveFuzzy, container, reference, parameters,
				returnType);
		return ret;
	}

	public List<Parameter> resolveParameterDefinition(String identifier,
			boolean resolveFuzzy, EObject container, EReference reference,
			TypeCS parameterType) {
		List<Parameter> ret = OclStaticSemanticsProvider.getStaticSemantics(
				(IOclResource) container.eResource()).resolveParameterDefinition(
				identifier, resolveFuzzy, container, reference, parameterType);
		return ret;
	}

}
