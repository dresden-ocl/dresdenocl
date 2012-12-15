package org.dresdenocl.language.ocl.resource.ocl;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import org.dresdenocl.language.ocl.OclExpressionCS;
import org.dresdenocl.language.ocl.ParameterCS;
import org.dresdenocl.language.ocl.TypeCS;
import org.dresdenocl.pivotmodel.NamedElement;
import org.dresdenocl.pivotmodel.Namespace;
import org.dresdenocl.pivotmodel.Operation;
import org.dresdenocl.pivotmodel.Parameter;
import org.dresdenocl.pivotmodel.Property;
import org.dresdenocl.pivotmodel.Type;

public interface IOclReferenceResolveHelper {

	List<Namespace> resolveNamespace(String identifier, boolean resolveFuzzy,
			EObject container);

	List<Type> resolveType(String identifier, boolean resolveFuzzy,
			EObject container);

	List<NamedElement> resolveNamedElement(String identifier,
			boolean resolveFuzzy, EObject container);

	List<Property> resolveProperty(String identifier, boolean resolveFuzzy,
			EObject container);

	List<Property> resolvePropertyDefinition(String identifier,
			boolean resolveFuzzy, EObject container);

	List<Operation> resolveOperation(String identifier, boolean resolveFuzzy,
			EObject container, EReference reference,
			List<OclExpressionCS> parameters, boolean isStatic);

	List<Operation> resolveOperationDefinition(String identifier,
			boolean resolveFuzzy, EObject container, EReference reference,
			List<ParameterCS> parameters, TypeCS returnType);

	List<Parameter> resolveParameterDefinition(String identifier,
			boolean resolveFuzzy, EObject container, EReference reference,
			TypeCS parameterType);
}
