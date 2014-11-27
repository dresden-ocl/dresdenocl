/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import org.dresdenocl.language.ocl.AdditiveOperationCallExpCS;
import org.dresdenocl.language.ocl.AttributeContextDeclarationCS;
import org.dresdenocl.language.ocl.BodyDeclarationCS;
import org.dresdenocl.language.ocl.BooleanLiteralExpCS;
import org.dresdenocl.language.ocl.BracketExpCS;
import org.dresdenocl.language.ocl.CallExpCS;
import org.dresdenocl.language.ocl.ClassifierContextDeclarationCS;
import org.dresdenocl.language.ocl.CollectionLiteralExpCS;
import org.dresdenocl.language.ocl.CollectionLiteralPartsCS;
import org.dresdenocl.language.ocl.CollectionLiteralPartsOclExpCS;
import org.dresdenocl.language.ocl.CollectionRangeCS;
import org.dresdenocl.language.ocl.CollectionTypeIdentifierCS;
import org.dresdenocl.language.ocl.CollectionTypeLiteralExpCS;
import org.dresdenocl.language.ocl.ContextDeclarationCS;
import org.dresdenocl.language.ocl.DefinitionExpCS;
import org.dresdenocl.language.ocl.DefinitionExpOperationCS;
import org.dresdenocl.language.ocl.DefinitionExpPartCS;
import org.dresdenocl.language.ocl.DefinitionExpPropertyCS;
import org.dresdenocl.language.ocl.DeriveValueCS;
import org.dresdenocl.language.ocl.EqualityOperationCallExpCS;
import org.dresdenocl.language.ocl.FeatureCallExpCS;
import org.dresdenocl.language.ocl.IfExpCS;
import org.dresdenocl.language.ocl.ImplicitFeatureCallCS;
import org.dresdenocl.language.ocl.ImplicitOperationCallCS;
import org.dresdenocl.language.ocl.ImplicitPropertyCallCS;
import org.dresdenocl.language.ocl.InitOrDeriveValueCS;
import org.dresdenocl.language.ocl.InitValueCS;
import org.dresdenocl.language.ocl.IntegerLiteralExpCS;
import org.dresdenocl.language.ocl.InvalidLiteralExpCS;
import org.dresdenocl.language.ocl.InvariantExpCS;
import org.dresdenocl.language.ocl.InvariantOrDefinitionCS;
import org.dresdenocl.language.ocl.IterateExpCS;
import org.dresdenocl.language.ocl.IteratorExpCS;
import org.dresdenocl.language.ocl.IteratorExpVariableCS;
import org.dresdenocl.language.ocl.LetExpCS;
import org.dresdenocl.language.ocl.LiteralExpCS;
import org.dresdenocl.language.ocl.LogicalAndOperationCallExpCS;
import org.dresdenocl.language.ocl.LogicalImpliesOperationCallExpCS;
import org.dresdenocl.language.ocl.LogicalNotOperationCallExpCS;
import org.dresdenocl.language.ocl.LogicalOrOperationCallExpCS;
import org.dresdenocl.language.ocl.LogicalXorOperationCallExpCS;
import org.dresdenocl.language.ocl.LoopExpCS;
import org.dresdenocl.language.ocl.ModelElementCS;
import org.dresdenocl.language.ocl.MultOperationCallExpCS;
import org.dresdenocl.language.ocl.NamedElementCS;
import org.dresdenocl.language.ocl.NavigationCallExp;
import org.dresdenocl.language.ocl.NullLiteralExpCS;
import org.dresdenocl.language.ocl.OclExpressionCS;
import org.dresdenocl.language.ocl.OperationCallBaseExpCS;
import org.dresdenocl.language.ocl.OperationCallBinaryExpCS;
import org.dresdenocl.language.ocl.OperationCallExpCS;
import org.dresdenocl.language.ocl.OperationCallOnSelfExpCS;
import org.dresdenocl.language.ocl.OperationCallWithImlicitSourceExpCS;
import org.dresdenocl.language.ocl.OperationCallWithSourceExpCS;
import org.dresdenocl.language.ocl.OperationContextDeclarationCS;
import org.dresdenocl.language.ocl.OperationDefinitionCS;
import org.dresdenocl.language.ocl.OperationDefinitionInContextCS;
import org.dresdenocl.language.ocl.OperationDefinitionInDefCS;
import org.dresdenocl.language.ocl.PackageDeclarationCS;
import org.dresdenocl.language.ocl.PackageDeclarationNestedNamespaceCS;
import org.dresdenocl.language.ocl.PackageDeclarationWithNamespaceCS;
import org.dresdenocl.language.ocl.PackageDeclarationWithoutNamespaceCS;
import org.dresdenocl.language.ocl.ParameterCS;
import org.dresdenocl.language.ocl.PathNameCS;
import org.dresdenocl.language.ocl.PathNamePathCS;
import org.dresdenocl.language.ocl.PathNameSimpleCS;
import org.dresdenocl.language.ocl.PostConditionDeclarationCS;
import org.dresdenocl.language.ocl.PreConditionDeclarationCS;
import org.dresdenocl.language.ocl.PrePostOrBodyDeclarationCS;
import org.dresdenocl.language.ocl.PrimitiveLiteralExpCS;
import org.dresdenocl.language.ocl.PropertyCallBaseExpCS;
import org.dresdenocl.language.ocl.PropertyCallExpCS;
import org.dresdenocl.language.ocl.PropertyCallExplicitPathExpCS;
import org.dresdenocl.language.ocl.PropertyCallOnSelfExpCS;
import org.dresdenocl.language.ocl.RealLiteralExpCS;
import org.dresdenocl.language.ocl.RelationalOperationCallExpCS;
import org.dresdenocl.language.ocl.SimpleNameCS;
import org.dresdenocl.language.ocl.StaticOperationCallExpCS;
import org.dresdenocl.language.ocl.StringLiteralExpCS;
import org.dresdenocl.language.ocl.TupleLiteralExpCS;
import org.dresdenocl.language.ocl.TupleTypeCS;
import org.dresdenocl.language.ocl.TupleTypeLiteralExpCS;
import org.dresdenocl.language.ocl.TypeCS;
import org.dresdenocl.language.ocl.TypeModelElementCS;
import org.dresdenocl.language.ocl.UnaryOperationCallExpCS;
import org.dresdenocl.language.ocl.UnreservedSimpleNameCS;
import org.dresdenocl.language.ocl.VariableDeclarationCS;
import org.dresdenocl.language.ocl.VariableDeclarationWithInitCS;
import org.dresdenocl.language.ocl.VariableDeclarationWithInitListCS;
import org.dresdenocl.language.ocl.VariableDeclarationWithoutInitCS;
import org.dresdenocl.language.ocl.VariableDeclarationWithoutInitListCS;
import org.dresdenocl.pivotmodel.AssociationProperty;
import org.dresdenocl.pivotmodel.ComplexGenericType;
import org.dresdenocl.pivotmodel.ConstrainableElement;
import org.dresdenocl.pivotmodel.Constraint;
import org.dresdenocl.pivotmodel.Enumeration;
import org.dresdenocl.pivotmodel.EnumerationLiteral;
import org.dresdenocl.pivotmodel.Expression;
import org.dresdenocl.pivotmodel.Feature;
import org.dresdenocl.pivotmodel.GenericElement;
import org.dresdenocl.pivotmodel.GenericType;
import org.dresdenocl.pivotmodel.NamedElement;
import org.dresdenocl.pivotmodel.Namespace;
import org.dresdenocl.pivotmodel.Operation;
import org.dresdenocl.pivotmodel.Parameter;
import org.dresdenocl.pivotmodel.ParameterGenericType;
import org.dresdenocl.pivotmodel.PrimitiveType;
import org.dresdenocl.pivotmodel.Property;
import org.dresdenocl.pivotmodel.Type;
import org.dresdenocl.pivotmodel.TypeArgument;
import org.dresdenocl.pivotmodel.TypeParameter;
import org.dresdenocl.pivotmodel.TypedElement;
import org.eclipse.emf.ecore.EObject;

/**
 * This class provides basic infrastructure to interpret models. To implement
 * concrete interpreters, subclass this abstract interpreter and override the
 * interprete_* methods. The interpretation can be customized by binding the two
 * type parameters (ResultType, ContextType). The former is returned by all
 * interprete_* methods, while the latter is passed from method to method while
 * traversing the model. The concrete traversal strategy can also be exchanged.
 * One can use a static traversal strategy by pushing all objects to interpret on
 * the interpretation stack (using addObjectToInterprete()) before calling
 * interprete(). Alternatively, the traversal strategy can be dynamic by pushing
 * objects on the interpretation stack during interpretation.
 */
public class AbstractOclInterpreter<ResultType, ContextType> {
	
	private Stack<EObject> interpretationStack = new Stack<EObject>();
	private List<org.dresdenocl.language.ocl.resource.ocl.IOclInterpreterListener> listeners = new ArrayList<org.dresdenocl.language.ocl.resource.ocl.IOclInterpreterListener>();
	private EObject nextObjectToInterprete;
	private ContextType currentContext;
	
	public ResultType interprete(ContextType context) {
		ResultType result = null;
		EObject next = null;
		currentContext = context;
		while (!interpretationStack.empty()) {
			try {
				next = interpretationStack.pop();
			} catch (EmptyStackException ese) {
				// this can happen when the interpreter was terminated between the call to empty()
				// and pop()
				break;
			}
			nextObjectToInterprete = next;
			notifyListeners(next);
			result = interprete(next, context);
			if (!continueInterpretation(context, result)) {
				break;
			}
		}
		currentContext = null;
		return result;
	}
	
	/**
	 * Override this method to stop the overall interpretation depending on the result
	 * of the interpretation of a single model elements.
	 */
	public boolean continueInterpretation(ContextType context, ResultType result) {
		return true;
	}
	
	public ResultType interprete(EObject object, ContextType context) {
		ResultType result = null;
		if (object instanceof org.dresdenocl.language.ocl.BracketExpCS) {
			result = interprete_org_dresdenocl_language_ocl_BracketExpCS((org.dresdenocl.language.ocl.BracketExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.ModelElementCS) {
			result = interprete_org_dresdenocl_language_ocl_ModelElementCS((org.dresdenocl.language.ocl.ModelElementCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.OclExpressionCS) {
			result = interprete_org_dresdenocl_language_ocl_OclExpressionCS((org.dresdenocl.language.ocl.OclExpressionCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.PathNameSimpleCS) {
			result = interprete_org_dresdenocl_language_ocl_PathNameSimpleCS((org.dresdenocl.language.ocl.PathNameSimpleCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.PathNamePathCS) {
			result = interprete_org_dresdenocl_language_ocl_PathNamePathCS((org.dresdenocl.language.ocl.PathNamePathCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.PathNameCS) {
			result = interprete_org_dresdenocl_language_ocl_PathNameCS((org.dresdenocl.language.ocl.PathNameCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.NamedElementCS) {
			result = interprete_org_dresdenocl_language_ocl_NamedElementCS((org.dresdenocl.language.ocl.NamedElementCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.UnreservedSimpleNameCS) {
			result = interprete_org_dresdenocl_language_ocl_UnreservedSimpleNameCS((org.dresdenocl.language.ocl.UnreservedSimpleNameCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.SimpleNameCS) {
			result = interprete_org_dresdenocl_language_ocl_SimpleNameCS((org.dresdenocl.language.ocl.SimpleNameCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.TypeModelElementCS) {
			result = interprete_org_dresdenocl_language_ocl_TypeModelElementCS((org.dresdenocl.language.ocl.TypeModelElementCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.TupleTypeCS) {
			result = interprete_org_dresdenocl_language_ocl_TupleTypeCS((org.dresdenocl.language.ocl.TupleTypeCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.TypeCS) {
			result = interprete_org_dresdenocl_language_ocl_TypeCS((org.dresdenocl.language.ocl.TypeCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.CollectionTypeLiteralExpCS) {
			result = interprete_org_dresdenocl_language_ocl_CollectionTypeLiteralExpCS((org.dresdenocl.language.ocl.CollectionTypeLiteralExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.TupleTypeLiteralExpCS) {
			result = interprete_org_dresdenocl_language_ocl_TupleTypeLiteralExpCS((org.dresdenocl.language.ocl.TupleTypeLiteralExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.VariableDeclarationWithInitCS) {
			result = interprete_org_dresdenocl_language_ocl_VariableDeclarationWithInitCS((org.dresdenocl.language.ocl.VariableDeclarationWithInitCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.VariableDeclarationWithoutInitCS) {
			result = interprete_org_dresdenocl_language_ocl_VariableDeclarationWithoutInitCS((org.dresdenocl.language.ocl.VariableDeclarationWithoutInitCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.VariableDeclarationCS) {
			result = interprete_org_dresdenocl_language_ocl_VariableDeclarationCS((org.dresdenocl.language.ocl.VariableDeclarationCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.VariableDeclarationWithInitListCS) {
			result = interprete_org_dresdenocl_language_ocl_VariableDeclarationWithInitListCS((org.dresdenocl.language.ocl.VariableDeclarationWithInitListCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.VariableDeclarationWithoutInitListCS) {
			result = interprete_org_dresdenocl_language_ocl_VariableDeclarationWithoutInitListCS((org.dresdenocl.language.ocl.VariableDeclarationWithoutInitListCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.CollectionLiteralExpCS) {
			result = interprete_org_dresdenocl_language_ocl_CollectionLiteralExpCS((org.dresdenocl.language.ocl.CollectionLiteralExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.LiteralExpCS) {
			result = interprete_org_dresdenocl_language_ocl_LiteralExpCS((org.dresdenocl.language.ocl.LiteralExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.CollectionTypeIdentifierCS) {
			result = interprete_org_dresdenocl_language_ocl_CollectionTypeIdentifierCS((org.dresdenocl.language.ocl.CollectionTypeIdentifierCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.CollectionLiteralPartsOclExpCS) {
			result = interprete_org_dresdenocl_language_ocl_CollectionLiteralPartsOclExpCS((org.dresdenocl.language.ocl.CollectionLiteralPartsOclExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.CollectionRangeCS) {
			result = interprete_org_dresdenocl_language_ocl_CollectionRangeCS((org.dresdenocl.language.ocl.CollectionRangeCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.CollectionLiteralPartsCS) {
			result = interprete_org_dresdenocl_language_ocl_CollectionLiteralPartsCS((org.dresdenocl.language.ocl.CollectionLiteralPartsCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.LoopExpCS) {
			result = interprete_org_dresdenocl_language_ocl_LoopExpCS((org.dresdenocl.language.ocl.LoopExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.CallExpCS) {
			result = interprete_org_dresdenocl_language_ocl_CallExpCS((org.dresdenocl.language.ocl.CallExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.IteratorExpVariableCS) {
			result = interprete_org_dresdenocl_language_ocl_IteratorExpVariableCS((org.dresdenocl.language.ocl.IteratorExpVariableCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.IteratorExpCS) {
			result = interprete_org_dresdenocl_language_ocl_IteratorExpCS((org.dresdenocl.language.ocl.IteratorExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.IterateExpCS) {
			result = interprete_org_dresdenocl_language_ocl_IterateExpCS((org.dresdenocl.language.ocl.IterateExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.NavigationCallExp) {
			result = interprete_org_dresdenocl_language_ocl_NavigationCallExp((org.dresdenocl.language.ocl.NavigationCallExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.FeatureCallExpCS) {
			result = interprete_org_dresdenocl_language_ocl_FeatureCallExpCS((org.dresdenocl.language.ocl.FeatureCallExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.ImplicitOperationCallCS) {
			result = interprete_org_dresdenocl_language_ocl_ImplicitOperationCallCS((org.dresdenocl.language.ocl.ImplicitOperationCallCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.OperationCallBaseExpCS) {
			result = interprete_org_dresdenocl_language_ocl_OperationCallBaseExpCS((org.dresdenocl.language.ocl.OperationCallBaseExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.ImplicitPropertyCallCS) {
			result = interprete_org_dresdenocl_language_ocl_ImplicitPropertyCallCS((org.dresdenocl.language.ocl.ImplicitPropertyCallCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.PropertyCallBaseExpCS) {
			result = interprete_org_dresdenocl_language_ocl_PropertyCallBaseExpCS((org.dresdenocl.language.ocl.PropertyCallBaseExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.ImplicitFeatureCallCS) {
			result = interprete_org_dresdenocl_language_ocl_ImplicitFeatureCallCS((org.dresdenocl.language.ocl.ImplicitFeatureCallCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.PropertyCallOnSelfExpCS) {
			result = interprete_org_dresdenocl_language_ocl_PropertyCallOnSelfExpCS((org.dresdenocl.language.ocl.PropertyCallOnSelfExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.PropertyCallExplicitPathExpCS) {
			result = interprete_org_dresdenocl_language_ocl_PropertyCallExplicitPathExpCS((org.dresdenocl.language.ocl.PropertyCallExplicitPathExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.PropertyCallExpCS) {
			result = interprete_org_dresdenocl_language_ocl_PropertyCallExpCS((org.dresdenocl.language.ocl.PropertyCallExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.OperationCallOnSelfExpCS) {
			result = interprete_org_dresdenocl_language_ocl_OperationCallOnSelfExpCS((org.dresdenocl.language.ocl.OperationCallOnSelfExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.StaticOperationCallExpCS) {
			result = interprete_org_dresdenocl_language_ocl_StaticOperationCallExpCS((org.dresdenocl.language.ocl.StaticOperationCallExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.UnaryOperationCallExpCS) {
			result = interprete_org_dresdenocl_language_ocl_UnaryOperationCallExpCS((org.dresdenocl.language.ocl.UnaryOperationCallExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.LogicalNotOperationCallExpCS) {
			result = interprete_org_dresdenocl_language_ocl_LogicalNotOperationCallExpCS((org.dresdenocl.language.ocl.LogicalNotOperationCallExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.AdditiveOperationCallExpCS) {
			result = interprete_org_dresdenocl_language_ocl_AdditiveOperationCallExpCS((org.dresdenocl.language.ocl.AdditiveOperationCallExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.MultOperationCallExpCS) {
			result = interprete_org_dresdenocl_language_ocl_MultOperationCallExpCS((org.dresdenocl.language.ocl.MultOperationCallExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.RelationalOperationCallExpCS) {
			result = interprete_org_dresdenocl_language_ocl_RelationalOperationCallExpCS((org.dresdenocl.language.ocl.RelationalOperationCallExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.EqualityOperationCallExpCS) {
			result = interprete_org_dresdenocl_language_ocl_EqualityOperationCallExpCS((org.dresdenocl.language.ocl.EqualityOperationCallExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.LogicalAndOperationCallExpCS) {
			result = interprete_org_dresdenocl_language_ocl_LogicalAndOperationCallExpCS((org.dresdenocl.language.ocl.LogicalAndOperationCallExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.LogicalOrOperationCallExpCS) {
			result = interprete_org_dresdenocl_language_ocl_LogicalOrOperationCallExpCS((org.dresdenocl.language.ocl.LogicalOrOperationCallExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.LogicalXorOperationCallExpCS) {
			result = interprete_org_dresdenocl_language_ocl_LogicalXorOperationCallExpCS((org.dresdenocl.language.ocl.LogicalXorOperationCallExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.LogicalImpliesOperationCallExpCS) {
			result = interprete_org_dresdenocl_language_ocl_LogicalImpliesOperationCallExpCS((org.dresdenocl.language.ocl.LogicalImpliesOperationCallExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.OperationCallBinaryExpCS) {
			result = interprete_org_dresdenocl_language_ocl_OperationCallBinaryExpCS((org.dresdenocl.language.ocl.OperationCallBinaryExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.OperationCallWithSourceExpCS) {
			result = interprete_org_dresdenocl_language_ocl_OperationCallWithSourceExpCS((org.dresdenocl.language.ocl.OperationCallWithSourceExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.OperationCallWithImlicitSourceExpCS) {
			result = interprete_org_dresdenocl_language_ocl_OperationCallWithImlicitSourceExpCS((org.dresdenocl.language.ocl.OperationCallWithImlicitSourceExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.OperationCallExpCS) {
			result = interprete_org_dresdenocl_language_ocl_OperationCallExpCS((org.dresdenocl.language.ocl.OperationCallExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.TupleLiteralExpCS) {
			result = interprete_org_dresdenocl_language_ocl_TupleLiteralExpCS((org.dresdenocl.language.ocl.TupleLiteralExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.IntegerLiteralExpCS) {
			result = interprete_org_dresdenocl_language_ocl_IntegerLiteralExpCS((org.dresdenocl.language.ocl.IntegerLiteralExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.RealLiteralExpCS) {
			result = interprete_org_dresdenocl_language_ocl_RealLiteralExpCS((org.dresdenocl.language.ocl.RealLiteralExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.BooleanLiteralExpCS) {
			result = interprete_org_dresdenocl_language_ocl_BooleanLiteralExpCS((org.dresdenocl.language.ocl.BooleanLiteralExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.StringLiteralExpCS) {
			result = interprete_org_dresdenocl_language_ocl_StringLiteralExpCS((org.dresdenocl.language.ocl.StringLiteralExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.InvalidLiteralExpCS) {
			result = interprete_org_dresdenocl_language_ocl_InvalidLiteralExpCS((org.dresdenocl.language.ocl.InvalidLiteralExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.NullLiteralExpCS) {
			result = interprete_org_dresdenocl_language_ocl_NullLiteralExpCS((org.dresdenocl.language.ocl.NullLiteralExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.PrimitiveLiteralExpCS) {
			result = interprete_org_dresdenocl_language_ocl_PrimitiveLiteralExpCS((org.dresdenocl.language.ocl.PrimitiveLiteralExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.LetExpCS) {
			result = interprete_org_dresdenocl_language_ocl_LetExpCS((org.dresdenocl.language.ocl.LetExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.IfExpCS) {
			result = interprete_org_dresdenocl_language_ocl_IfExpCS((org.dresdenocl.language.ocl.IfExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.PackageDeclarationWithNamespaceCS) {
			result = interprete_org_dresdenocl_language_ocl_PackageDeclarationWithNamespaceCS((org.dresdenocl.language.ocl.PackageDeclarationWithNamespaceCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.PackageDeclarationCS) {
			result = interprete_org_dresdenocl_language_ocl_PackageDeclarationCS((org.dresdenocl.language.ocl.PackageDeclarationCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.PackageDeclarationNestedNamespaceCS) {
			result = interprete_org_dresdenocl_language_ocl_PackageDeclarationNestedNamespaceCS((org.dresdenocl.language.ocl.PackageDeclarationNestedNamespaceCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.PackageDeclarationWithoutNamespaceCS) {
			result = interprete_org_dresdenocl_language_ocl_PackageDeclarationWithoutNamespaceCS((org.dresdenocl.language.ocl.PackageDeclarationWithoutNamespaceCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.AttributeContextDeclarationCS) {
			result = interprete_org_dresdenocl_language_ocl_AttributeContextDeclarationCS((org.dresdenocl.language.ocl.AttributeContextDeclarationCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.ClassifierContextDeclarationCS) {
			result = interprete_org_dresdenocl_language_ocl_ClassifierContextDeclarationCS((org.dresdenocl.language.ocl.ClassifierContextDeclarationCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.OperationContextDeclarationCS) {
			result = interprete_org_dresdenocl_language_ocl_OperationContextDeclarationCS((org.dresdenocl.language.ocl.OperationContextDeclarationCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.ContextDeclarationCS) {
			result = interprete_org_dresdenocl_language_ocl_ContextDeclarationCS((org.dresdenocl.language.ocl.ContextDeclarationCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.InitValueCS) {
			result = interprete_org_dresdenocl_language_ocl_InitValueCS((org.dresdenocl.language.ocl.InitValueCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.DeriveValueCS) {
			result = interprete_org_dresdenocl_language_ocl_DeriveValueCS((org.dresdenocl.language.ocl.DeriveValueCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.InitOrDeriveValueCS) {
			result = interprete_org_dresdenocl_language_ocl_InitOrDeriveValueCS((org.dresdenocl.language.ocl.InitOrDeriveValueCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.InvariantExpCS) {
			result = interprete_org_dresdenocl_language_ocl_InvariantExpCS((org.dresdenocl.language.ocl.InvariantExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.DefinitionExpCS) {
			result = interprete_org_dresdenocl_language_ocl_DefinitionExpCS((org.dresdenocl.language.ocl.DefinitionExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.InvariantOrDefinitionCS) {
			result = interprete_org_dresdenocl_language_ocl_InvariantOrDefinitionCS((org.dresdenocl.language.ocl.InvariantOrDefinitionCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.DefinitionExpPropertyCS) {
			result = interprete_org_dresdenocl_language_ocl_DefinitionExpPropertyCS((org.dresdenocl.language.ocl.DefinitionExpPropertyCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.DefinitionExpOperationCS) {
			result = interprete_org_dresdenocl_language_ocl_DefinitionExpOperationCS((org.dresdenocl.language.ocl.DefinitionExpOperationCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.DefinitionExpPartCS) {
			result = interprete_org_dresdenocl_language_ocl_DefinitionExpPartCS((org.dresdenocl.language.ocl.DefinitionExpPartCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.PreConditionDeclarationCS) {
			result = interprete_org_dresdenocl_language_ocl_PreConditionDeclarationCS((org.dresdenocl.language.ocl.PreConditionDeclarationCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.PostConditionDeclarationCS) {
			result = interprete_org_dresdenocl_language_ocl_PostConditionDeclarationCS((org.dresdenocl.language.ocl.PostConditionDeclarationCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.BodyDeclarationCS) {
			result = interprete_org_dresdenocl_language_ocl_BodyDeclarationCS((org.dresdenocl.language.ocl.BodyDeclarationCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.PrePostOrBodyDeclarationCS) {
			result = interprete_org_dresdenocl_language_ocl_PrePostOrBodyDeclarationCS((org.dresdenocl.language.ocl.PrePostOrBodyDeclarationCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.OperationDefinitionInContextCS) {
			result = interprete_org_dresdenocl_language_ocl_OperationDefinitionInContextCS((org.dresdenocl.language.ocl.OperationDefinitionInContextCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.OperationDefinitionInDefCS) {
			result = interprete_org_dresdenocl_language_ocl_OperationDefinitionInDefCS((org.dresdenocl.language.ocl.OperationDefinitionInDefCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.OperationDefinitionCS) {
			result = interprete_org_dresdenocl_language_ocl_OperationDefinitionCS((org.dresdenocl.language.ocl.OperationDefinitionCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.language.ocl.ParameterCS) {
			result = interprete_org_dresdenocl_language_ocl_ParameterCS((org.dresdenocl.language.ocl.ParameterCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.pivotmodel.Property) {
			result = interprete_org_dresdenocl_pivotmodel_Property((org.dresdenocl.pivotmodel.Property) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.pivotmodel.Operation) {
			result = interprete_org_dresdenocl_pivotmodel_Operation((org.dresdenocl.pivotmodel.Operation) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.pivotmodel.Feature) {
			result = interprete_org_dresdenocl_pivotmodel_Feature((org.dresdenocl.pivotmodel.Feature) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.pivotmodel.TypedElement) {
			result = interprete_org_dresdenocl_pivotmodel_TypedElement((org.dresdenocl.pivotmodel.TypedElement) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.pivotmodel.Namespace) {
			result = interprete_org_dresdenocl_pivotmodel_Namespace((org.dresdenocl.pivotmodel.Namespace) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.pivotmodel.PrimitiveType) {
			result = interprete_org_dresdenocl_pivotmodel_PrimitiveType((org.dresdenocl.pivotmodel.PrimitiveType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.pivotmodel.Enumeration) {
			result = interprete_org_dresdenocl_pivotmodel_Enumeration((org.dresdenocl.pivotmodel.Enumeration) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.pivotmodel.Type) {
			result = interprete_org_dresdenocl_pivotmodel_Type((org.dresdenocl.pivotmodel.Type) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.pivotmodel.GenericElement) {
			result = interprete_org_dresdenocl_pivotmodel_GenericElement((org.dresdenocl.pivotmodel.GenericElement) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.pivotmodel.EnumerationLiteral) {
			result = interprete_org_dresdenocl_pivotmodel_EnumerationLiteral((org.dresdenocl.pivotmodel.EnumerationLiteral) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.pivotmodel.Parameter) {
			result = interprete_org_dresdenocl_pivotmodel_Parameter((org.dresdenocl.pivotmodel.Parameter) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.pivotmodel.ParameterGenericType) {
			result = interprete_org_dresdenocl_pivotmodel_ParameterGenericType((org.dresdenocl.pivotmodel.ParameterGenericType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.pivotmodel.ComplexGenericType) {
			result = interprete_org_dresdenocl_pivotmodel_ComplexGenericType((org.dresdenocl.pivotmodel.ComplexGenericType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.pivotmodel.GenericType) {
			result = interprete_org_dresdenocl_pivotmodel_GenericType((org.dresdenocl.pivotmodel.GenericType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.pivotmodel.TypeArgument) {
			result = interprete_org_dresdenocl_pivotmodel_TypeArgument((org.dresdenocl.pivotmodel.TypeArgument) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.pivotmodel.Constraint) {
			result = interprete_org_dresdenocl_pivotmodel_Constraint((org.dresdenocl.pivotmodel.Constraint) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.pivotmodel.AssociationProperty) {
			result = interprete_org_dresdenocl_pivotmodel_AssociationProperty((org.dresdenocl.pivotmodel.AssociationProperty) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.pivotmodel.NamedElement) {
			result = interprete_org_dresdenocl_pivotmodel_NamedElement((org.dresdenocl.pivotmodel.NamedElement) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.pivotmodel.ConstrainableElement) {
			result = interprete_org_dresdenocl_pivotmodel_ConstrainableElement((org.dresdenocl.pivotmodel.ConstrainableElement) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.pivotmodel.TypeParameter) {
			result = interprete_org_dresdenocl_pivotmodel_TypeParameter((org.dresdenocl.pivotmodel.TypeParameter) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.dresdenocl.pivotmodel.Expression) {
			result = interprete_org_dresdenocl_pivotmodel_Expression((org.dresdenocl.pivotmodel.Expression) object, context);
		}
		if (result != null) {
			return result;
		}
		return result;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_OclExpressionCS(OclExpressionCS oclExpressionCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_BracketExpCS(BracketExpCS bracketExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_ModelElementCS(ModelElementCS modelElementCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_PathNameCS(PathNameCS pathNameCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_PathNameSimpleCS(PathNameSimpleCS pathNameSimpleCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_PathNamePathCS(PathNamePathCS pathNamePathCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_UnreservedSimpleNameCS(UnreservedSimpleNameCS unreservedSimpleNameCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_NamedElementCS(NamedElementCS namedElementCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_SimpleNameCS(SimpleNameCS simpleNameCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_TypeCS(TypeCS typeCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_TypeModelElementCS(TypeModelElementCS typeModelElementCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_TupleTypeCS(TupleTypeCS tupleTypeCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_CollectionTypeLiteralExpCS(CollectionTypeLiteralExpCS collectionTypeLiteralExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_TupleTypeLiteralExpCS(TupleTypeLiteralExpCS tupleTypeLiteralExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_VariableDeclarationCS(VariableDeclarationCS variableDeclarationCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_VariableDeclarationWithInitCS(VariableDeclarationWithInitCS variableDeclarationWithInitCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_VariableDeclarationWithoutInitCS(VariableDeclarationWithoutInitCS variableDeclarationWithoutInitCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_VariableDeclarationWithInitListCS(VariableDeclarationWithInitListCS variableDeclarationWithInitListCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_VariableDeclarationWithoutInitListCS(VariableDeclarationWithoutInitListCS variableDeclarationWithoutInitListCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_LiteralExpCS(LiteralExpCS literalExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_CollectionLiteralExpCS(CollectionLiteralExpCS collectionLiteralExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_CollectionTypeIdentifierCS(CollectionTypeIdentifierCS collectionTypeIdentifierCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_CollectionLiteralPartsCS(CollectionLiteralPartsCS collectionLiteralPartsCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_CollectionLiteralPartsOclExpCS(CollectionLiteralPartsOclExpCS collectionLiteralPartsOclExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_CollectionRangeCS(CollectionRangeCS collectionRangeCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_CallExpCS(CallExpCS callExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_LoopExpCS(LoopExpCS loopExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_IteratorExpVariableCS(IteratorExpVariableCS iteratorExpVariableCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_IteratorExpCS(IteratorExpCS iteratorExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_IterateExpCS(IterateExpCS iterateExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_FeatureCallExpCS(FeatureCallExpCS featureCallExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_NavigationCallExp(NavigationCallExp navigationCallExp, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_OperationCallBaseExpCS(OperationCallBaseExpCS operationCallBaseExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_PropertyCallBaseExpCS(PropertyCallBaseExpCS propertyCallBaseExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_ImplicitFeatureCallCS(ImplicitFeatureCallCS implicitFeatureCallCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_ImplicitPropertyCallCS(ImplicitPropertyCallCS implicitPropertyCallCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_ImplicitOperationCallCS(ImplicitOperationCallCS implicitOperationCallCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_PropertyCallExpCS(PropertyCallExpCS propertyCallExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_PropertyCallOnSelfExpCS(PropertyCallOnSelfExpCS propertyCallOnSelfExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_PropertyCallExplicitPathExpCS(PropertyCallExplicitPathExpCS propertyCallExplicitPathExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_OperationCallExpCS(OperationCallExpCS operationCallExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_OperationCallOnSelfExpCS(OperationCallOnSelfExpCS operationCallOnSelfExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_StaticOperationCallExpCS(StaticOperationCallExpCS staticOperationCallExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_UnaryOperationCallExpCS(UnaryOperationCallExpCS unaryOperationCallExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_LogicalNotOperationCallExpCS(LogicalNotOperationCallExpCS logicalNotOperationCallExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_OperationCallWithSourceExpCS(OperationCallWithSourceExpCS operationCallWithSourceExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_OperationCallBinaryExpCS(OperationCallBinaryExpCS operationCallBinaryExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_AdditiveOperationCallExpCS(AdditiveOperationCallExpCS additiveOperationCallExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_MultOperationCallExpCS(MultOperationCallExpCS multOperationCallExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_RelationalOperationCallExpCS(RelationalOperationCallExpCS relationalOperationCallExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_EqualityOperationCallExpCS(EqualityOperationCallExpCS equalityOperationCallExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_LogicalAndOperationCallExpCS(LogicalAndOperationCallExpCS logicalAndOperationCallExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_LogicalOrOperationCallExpCS(LogicalOrOperationCallExpCS logicalOrOperationCallExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_LogicalXorOperationCallExpCS(LogicalXorOperationCallExpCS logicalXorOperationCallExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_LogicalImpliesOperationCallExpCS(LogicalImpliesOperationCallExpCS logicalImpliesOperationCallExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_OperationCallWithImlicitSourceExpCS(OperationCallWithImlicitSourceExpCS operationCallWithImlicitSourceExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_TupleLiteralExpCS(TupleLiteralExpCS tupleLiteralExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_PrimitiveLiteralExpCS(PrimitiveLiteralExpCS primitiveLiteralExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_IntegerLiteralExpCS(IntegerLiteralExpCS integerLiteralExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_RealLiteralExpCS(RealLiteralExpCS realLiteralExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_BooleanLiteralExpCS(BooleanLiteralExpCS booleanLiteralExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_StringLiteralExpCS(StringLiteralExpCS stringLiteralExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_InvalidLiteralExpCS(InvalidLiteralExpCS invalidLiteralExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_NullLiteralExpCS(NullLiteralExpCS nullLiteralExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_LetExpCS(LetExpCS letExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_IfExpCS(IfExpCS ifExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_PackageDeclarationCS(PackageDeclarationCS packageDeclarationCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_PackageDeclarationWithNamespaceCS(PackageDeclarationWithNamespaceCS packageDeclarationWithNamespaceCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_PackageDeclarationNestedNamespaceCS(PackageDeclarationNestedNamespaceCS packageDeclarationNestedNamespaceCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_PackageDeclarationWithoutNamespaceCS(PackageDeclarationWithoutNamespaceCS packageDeclarationWithoutNamespaceCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_ContextDeclarationCS(ContextDeclarationCS contextDeclarationCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_AttributeContextDeclarationCS(AttributeContextDeclarationCS attributeContextDeclarationCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_ClassifierContextDeclarationCS(ClassifierContextDeclarationCS classifierContextDeclarationCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_OperationContextDeclarationCS(OperationContextDeclarationCS operationContextDeclarationCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_InitOrDeriveValueCS(InitOrDeriveValueCS initOrDeriveValueCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_InitValueCS(InitValueCS initValueCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_DeriveValueCS(DeriveValueCS deriveValueCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_InvariantOrDefinitionCS(InvariantOrDefinitionCS invariantOrDefinitionCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_InvariantExpCS(InvariantExpCS invariantExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_DefinitionExpCS(DefinitionExpCS definitionExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_DefinitionExpPartCS(DefinitionExpPartCS definitionExpPartCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_DefinitionExpPropertyCS(DefinitionExpPropertyCS definitionExpPropertyCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_DefinitionExpOperationCS(DefinitionExpOperationCS definitionExpOperationCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_PrePostOrBodyDeclarationCS(PrePostOrBodyDeclarationCS prePostOrBodyDeclarationCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_PreConditionDeclarationCS(PreConditionDeclarationCS preConditionDeclarationCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_PostConditionDeclarationCS(PostConditionDeclarationCS postConditionDeclarationCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_BodyDeclarationCS(BodyDeclarationCS bodyDeclarationCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_OperationDefinitionCS(OperationDefinitionCS operationDefinitionCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_OperationDefinitionInContextCS(OperationDefinitionInContextCS operationDefinitionInContextCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_OperationDefinitionInDefCS(OperationDefinitionInDefCS operationDefinitionInDefCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_language_ocl_ParameterCS(ParameterCS parameterCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_pivotmodel_NamedElement(NamedElement namedElement, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_pivotmodel_TypedElement(TypedElement typedElement, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_pivotmodel_Feature(Feature feature, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_pivotmodel_GenericElement(GenericElement genericElement, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_pivotmodel_ConstrainableElement(ConstrainableElement constrainableElement, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_pivotmodel_Namespace(Namespace namespace, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_pivotmodel_Type(Type type, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_pivotmodel_PrimitiveType(PrimitiveType primitiveType, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_pivotmodel_Enumeration(Enumeration enumeration, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_pivotmodel_EnumerationLiteral(EnumerationLiteral enumerationLiteral, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_pivotmodel_Property(Property property, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_pivotmodel_Operation(Operation operation, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_pivotmodel_Parameter(Parameter parameter, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_pivotmodel_GenericType(GenericType genericType, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_pivotmodel_ParameterGenericType(ParameterGenericType parameterGenericType, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_pivotmodel_ComplexGenericType(ComplexGenericType complexGenericType, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_pivotmodel_TypeParameter(TypeParameter typeParameter, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_pivotmodel_TypeArgument(TypeArgument typeArgument, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_pivotmodel_Constraint(Constraint constraint, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_pivotmodel_Expression(Expression expression, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_dresdenocl_pivotmodel_AssociationProperty(AssociationProperty associationProperty, ContextType context) {
		return null;
	}
	
	private void notifyListeners(EObject element) {
		for (org.dresdenocl.language.ocl.resource.ocl.IOclInterpreterListener listener : listeners) {
			listener.handleInterpreteObject(element);
		}
	}
	
	/**
	 * Adds the given object to the interpretation stack. Attention: Objects that are
	 * added first, are interpret last.
	 */
	public void addObjectToInterprete(EObject object) {
		interpretationStack.push(object);
	}
	
	/**
	 * Adds the given collection of objects to the interpretation stack. Attention:
	 * Collections that are added first, are interpret last.
	 */
	public void addObjectsToInterprete(Collection<? extends EObject> objects) {
		for (EObject object : objects) {
			addObjectToInterprete(object);
		}
	}
	
	/**
	 * Adds the given collection of objects in reverse order to the interpretation
	 * stack.
	 */
	public void addObjectsToInterpreteInReverseOrder(Collection<? extends EObject> objects) {
		List<EObject> reverse = new ArrayList<EObject>(objects.size());
		reverse.addAll(objects);
		Collections.reverse(reverse);
		addObjectsToInterprete(reverse);
	}
	
	/**
	 * Adds the given object and all its children to the interpretation stack such
	 * that they are interpret in top down order.
	 */
	public void addObjectTreeToInterpreteTopDown(EObject root) {
		List<EObject> objects = new ArrayList<EObject>();
		objects.add(root);
		Iterator<EObject> it = root.eAllContents();
		while (it.hasNext()) {
			EObject eObject = (EObject) it.next();
			objects.add(eObject);
		}
		addObjectsToInterpreteInReverseOrder(objects);
	}
	
	public void addListener(org.dresdenocl.language.ocl.resource.ocl.IOclInterpreterListener newListener) {
		listeners.add(newListener);
	}
	
	public boolean removeListener(org.dresdenocl.language.ocl.resource.ocl.IOclInterpreterListener listener) {
		return listeners.remove(listener);
	}
	
	public EObject getNextObjectToInterprete() {
		return nextObjectToInterprete;
	}
	
	public Stack<EObject> getInterpretationStack() {
		return interpretationStack;
	}
	
	public void terminate() {
		interpretationStack.clear();
	}
	
	public ContextType getCurrentContext() {
		return currentContext;
	}
	
}
