package tudresden.ocl20.pivot.language.ocl.resource.ocl;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import tudresden.ocl20.pivot.language.ocl.OclExpressionCS;
import tudresden.ocl20.pivot.language.ocl.ParameterCS;
import tudresden.ocl20.pivot.language.ocl.TypeCS;
import tudresden.ocl20.pivot.pivotmodel.NamedElement;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

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
