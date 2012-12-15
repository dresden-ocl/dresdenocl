/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.util;

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
	
	private java.util.Stack<org.eclipse.emf.ecore.EObject> interpretationStack = new java.util.Stack<org.eclipse.emf.ecore.EObject>();
	private java.util.List<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclInterpreterListener> listeners = new java.util.ArrayList<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclInterpreterListener>();
	private org.eclipse.emf.ecore.EObject nextObjectToInterprete;
	private Object currentContext;
	
	public ResultType interprete(ContextType context) {
		ResultType result = null;
		org.eclipse.emf.ecore.EObject next = null;
		currentContext = context;
		while (!interpretationStack.empty()) {
			try {
				next = interpretationStack.pop();
			} catch (java.util.EmptyStackException ese) {
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
	
	public ResultType interprete(org.eclipse.emf.ecore.EObject object, ContextType context) {
		ResultType result = null;
		if (object instanceof tudresden.ocl20.pivot.language.ocl.BracketExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_BracketExpCS((tudresden.ocl20.pivot.language.ocl.BracketExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.NamedLiteralExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_NamedLiteralExpCS((tudresden.ocl20.pivot.language.ocl.NamedLiteralExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.OclExpressionCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_OclExpressionCS((tudresden.ocl20.pivot.language.ocl.OclExpressionCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.PathNameCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_PathNameCS((tudresden.ocl20.pivot.language.ocl.PathNameCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.SimpleNameCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_SimpleNameCS((tudresden.ocl20.pivot.language.ocl.SimpleNameCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.TypePathNameSimpleCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_TypePathNameSimpleCS((tudresden.ocl20.pivot.language.ocl.TypePathNameSimpleCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.TypePathNameNestedCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_TypePathNameNestedCS((tudresden.ocl20.pivot.language.ocl.TypePathNameNestedCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.TypePathNameCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_TypePathNameCS((tudresden.ocl20.pivot.language.ocl.TypePathNameCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.TupleTypeCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_TupleTypeCS((tudresden.ocl20.pivot.language.ocl.TupleTypeCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.TypeCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_TypeCS((tudresden.ocl20.pivot.language.ocl.TypeCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.CollectionTypeLiteralExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_CollectionTypeLiteralExpCS((tudresden.ocl20.pivot.language.ocl.CollectionTypeLiteralExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.TupleTypeLiteralExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_TupleTypeLiteralExpCS((tudresden.ocl20.pivot.language.ocl.TupleTypeLiteralExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_VariableDeclarationWithInitCS((tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithoutInitCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_VariableDeclarationWithoutInitCS((tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithoutInitCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.VariableDeclarationCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_VariableDeclarationCS((tudresden.ocl20.pivot.language.ocl.VariableDeclarationCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitListCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_VariableDeclarationWithInitListCS((tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitListCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithoutInitListCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_VariableDeclarationWithoutInitListCS((tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithoutInitListCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.EnumLiteralOrStaticPropertyExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_EnumLiteralOrStaticPropertyExpCS((tudresden.ocl20.pivot.language.ocl.EnumLiteralOrStaticPropertyExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.CollectionLiteralExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_CollectionLiteralExpCS((tudresden.ocl20.pivot.language.ocl.CollectionLiteralExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.LiteralExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_LiteralExpCS((tudresden.ocl20.pivot.language.ocl.LiteralExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.CollectionTypeIdentifierCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_CollectionTypeIdentifierCS((tudresden.ocl20.pivot.language.ocl.CollectionTypeIdentifierCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.CollectionLiteralPartsOclExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_CollectionLiteralPartsOclExpCS((tudresden.ocl20.pivot.language.ocl.CollectionLiteralPartsOclExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.CollectionRangeCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_CollectionRangeCS((tudresden.ocl20.pivot.language.ocl.CollectionRangeCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.CollectionLiteralPartsCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_CollectionLiteralPartsCS((tudresden.ocl20.pivot.language.ocl.CollectionLiteralPartsCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.IteratorExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_IteratorExpCS((tudresden.ocl20.pivot.language.ocl.IteratorExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.LoopExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_LoopExpCS((tudresden.ocl20.pivot.language.ocl.LoopExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.CallExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_CallExpCS((tudresden.ocl20.pivot.language.ocl.CallExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.IteratorExpVariableCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_IteratorExpVariableCS((tudresden.ocl20.pivot.language.ocl.IteratorExpVariableCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.IterateExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_IterateExpCS((tudresden.ocl20.pivot.language.ocl.IterateExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.NavigationCallExp) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_NavigationCallExp((tudresden.ocl20.pivot.language.ocl.NavigationCallExp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.FeatureCallExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_FeatureCallExpCS((tudresden.ocl20.pivot.language.ocl.FeatureCallExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.ImplicitOperationCallCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_ImplicitOperationCallCS((tudresden.ocl20.pivot.language.ocl.ImplicitOperationCallCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.OperationCallBaseExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_OperationCallBaseExpCS((tudresden.ocl20.pivot.language.ocl.OperationCallBaseExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.ImplicitPropertyCallCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_ImplicitPropertyCallCS((tudresden.ocl20.pivot.language.ocl.ImplicitPropertyCallCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.PropertyCallBaseExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_PropertyCallBaseExpCS((tudresden.ocl20.pivot.language.ocl.PropertyCallBaseExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.ImplicitFeatureCallCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_ImplicitFeatureCallCS((tudresden.ocl20.pivot.language.ocl.ImplicitFeatureCallCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.PropertyCallOnSelfExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_PropertyCallOnSelfExpCS((tudresden.ocl20.pivot.language.ocl.PropertyCallOnSelfExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.PropertyCallExplicitPathExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_PropertyCallExplicitPathExpCS((tudresden.ocl20.pivot.language.ocl.PropertyCallExplicitPathExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.PropertyCallExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_PropertyCallExpCS((tudresden.ocl20.pivot.language.ocl.PropertyCallExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.OperationCallOnSelfExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_OperationCallOnSelfExpCS((tudresden.ocl20.pivot.language.ocl.OperationCallOnSelfExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.StaticOperationCallExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_StaticOperationCallExpCS((tudresden.ocl20.pivot.language.ocl.StaticOperationCallExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.UnaryOperationCallExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_UnaryOperationCallExpCS((tudresden.ocl20.pivot.language.ocl.UnaryOperationCallExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.LogicalNotOperationCallExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_LogicalNotOperationCallExpCS((tudresden.ocl20.pivot.language.ocl.LogicalNotOperationCallExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.AdditiveOperationCallExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_AdditiveOperationCallExpCS((tudresden.ocl20.pivot.language.ocl.AdditiveOperationCallExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.MultOperationCallExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_MultOperationCallExpCS((tudresden.ocl20.pivot.language.ocl.MultOperationCallExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.RelationalOperationCallExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_RelationalOperationCallExpCS((tudresden.ocl20.pivot.language.ocl.RelationalOperationCallExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.EqualityOperationCallExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_EqualityOperationCallExpCS((tudresden.ocl20.pivot.language.ocl.EqualityOperationCallExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.LogicalAndOperationCallExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_LogicalAndOperationCallExpCS((tudresden.ocl20.pivot.language.ocl.LogicalAndOperationCallExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.LogicalOrOperationCallExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_LogicalOrOperationCallExpCS((tudresden.ocl20.pivot.language.ocl.LogicalOrOperationCallExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.LogicalXorOperationCallExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_LogicalXorOperationCallExpCS((tudresden.ocl20.pivot.language.ocl.LogicalXorOperationCallExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.LogicalImpliesOperationCallExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_LogicalImpliesOperationCallExpCS((tudresden.ocl20.pivot.language.ocl.LogicalImpliesOperationCallExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.OperationCallBinaryExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_OperationCallBinaryExpCS((tudresden.ocl20.pivot.language.ocl.OperationCallBinaryExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.OperationCallWithSourceExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_OperationCallWithSourceExpCS((tudresden.ocl20.pivot.language.ocl.OperationCallWithSourceExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.OperationCallWithImlicitSourceExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_OperationCallWithImlicitSourceExpCS((tudresden.ocl20.pivot.language.ocl.OperationCallWithImlicitSourceExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.OperationCallExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_OperationCallExpCS((tudresden.ocl20.pivot.language.ocl.OperationCallExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.TupleLiteralExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_TupleLiteralExpCS((tudresden.ocl20.pivot.language.ocl.TupleLiteralExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.IntegerLiteralExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_IntegerLiteralExpCS((tudresden.ocl20.pivot.language.ocl.IntegerLiteralExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.RealLiteralExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_RealLiteralExpCS((tudresden.ocl20.pivot.language.ocl.RealLiteralExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.BooleanLiteralExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_BooleanLiteralExpCS((tudresden.ocl20.pivot.language.ocl.BooleanLiteralExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.StringLiteralExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_StringLiteralExpCS((tudresden.ocl20.pivot.language.ocl.StringLiteralExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.InvalidLiteralExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_InvalidLiteralExpCS((tudresden.ocl20.pivot.language.ocl.InvalidLiteralExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.NullLiteralExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_NullLiteralExpCS((tudresden.ocl20.pivot.language.ocl.NullLiteralExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.PrimitiveLiteralExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_PrimitiveLiteralExpCS((tudresden.ocl20.pivot.language.ocl.PrimitiveLiteralExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.LetExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_LetExpCS((tudresden.ocl20.pivot.language.ocl.LetExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.IfExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_IfExpCS((tudresden.ocl20.pivot.language.ocl.IfExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.PackageDeclarationWithNamespaceCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_PackageDeclarationWithNamespaceCS((tudresden.ocl20.pivot.language.ocl.PackageDeclarationWithNamespaceCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.PackageDeclarationCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_PackageDeclarationCS((tudresden.ocl20.pivot.language.ocl.PackageDeclarationCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.PackageDeclarationNestedNamespaceCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_PackageDeclarationNestedNamespaceCS((tudresden.ocl20.pivot.language.ocl.PackageDeclarationNestedNamespaceCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.PackageDeclarationWithoutNamespaceCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_PackageDeclarationWithoutNamespaceCS((tudresden.ocl20.pivot.language.ocl.PackageDeclarationWithoutNamespaceCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.AttributeContextDeclarationCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_AttributeContextDeclarationCS((tudresden.ocl20.pivot.language.ocl.AttributeContextDeclarationCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.ClassifierContextDeclarationCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_ClassifierContextDeclarationCS((tudresden.ocl20.pivot.language.ocl.ClassifierContextDeclarationCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.OperationContextDeclarationCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_OperationContextDeclarationCS((tudresden.ocl20.pivot.language.ocl.OperationContextDeclarationCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.ContextDeclarationCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_ContextDeclarationCS((tudresden.ocl20.pivot.language.ocl.ContextDeclarationCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.InitValueCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_InitValueCS((tudresden.ocl20.pivot.language.ocl.InitValueCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.DeriveValueCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_DeriveValueCS((tudresden.ocl20.pivot.language.ocl.DeriveValueCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.InitOrDeriveValueCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_InitOrDeriveValueCS((tudresden.ocl20.pivot.language.ocl.InitOrDeriveValueCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.InvariantExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_InvariantExpCS((tudresden.ocl20.pivot.language.ocl.InvariantExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.DefinitionExpCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_DefinitionExpCS((tudresden.ocl20.pivot.language.ocl.DefinitionExpCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.InvariantOrDefinitionCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_InvariantOrDefinitionCS((tudresden.ocl20.pivot.language.ocl.InvariantOrDefinitionCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.DefinitionExpPropertyCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_DefinitionExpPropertyCS((tudresden.ocl20.pivot.language.ocl.DefinitionExpPropertyCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.DefinitionExpOperationCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_DefinitionExpOperationCS((tudresden.ocl20.pivot.language.ocl.DefinitionExpOperationCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.DefinitionExpPartCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_DefinitionExpPartCS((tudresden.ocl20.pivot.language.ocl.DefinitionExpPartCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.PreConditionDeclarationCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_PreConditionDeclarationCS((tudresden.ocl20.pivot.language.ocl.PreConditionDeclarationCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.PostConditionDeclarationCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_PostConditionDeclarationCS((tudresden.ocl20.pivot.language.ocl.PostConditionDeclarationCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.BodyDeclarationCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_BodyDeclarationCS((tudresden.ocl20.pivot.language.ocl.BodyDeclarationCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.PrePostOrBodyDeclarationCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_PrePostOrBodyDeclarationCS((tudresden.ocl20.pivot.language.ocl.PrePostOrBodyDeclarationCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.OperationDefinitionInContextCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_OperationDefinitionInContextCS((tudresden.ocl20.pivot.language.ocl.OperationDefinitionInContextCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.OperationDefinitionInDefCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_OperationDefinitionInDefCS((tudresden.ocl20.pivot.language.ocl.OperationDefinitionInDefCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.OperationDefinitionCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_OperationDefinitionCS((tudresden.ocl20.pivot.language.ocl.OperationDefinitionCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.language.ocl.ParameterCS) {
			result = interprete_tudresden_ocl20_pivot_language_ocl_ParameterCS((tudresden.ocl20.pivot.language.ocl.ParameterCS) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.pivotmodel.Feature) {
			result = interprete_tudresden_ocl20_pivot_pivotmodel_Feature((tudresden.ocl20.pivot.pivotmodel.Feature) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.pivotmodel.TypedElement) {
			result = interprete_tudresden_ocl20_pivot_pivotmodel_TypedElement((tudresden.ocl20.pivot.pivotmodel.TypedElement) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.pivotmodel.Namespace) {
			result = interprete_tudresden_ocl20_pivot_pivotmodel_Namespace((tudresden.ocl20.pivot.pivotmodel.Namespace) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.pivotmodel.PrimitiveType) {
			result = interprete_tudresden_ocl20_pivot_pivotmodel_PrimitiveType((tudresden.ocl20.pivot.pivotmodel.PrimitiveType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.pivotmodel.Enumeration) {
			result = interprete_tudresden_ocl20_pivot_pivotmodel_Enumeration((tudresden.ocl20.pivot.pivotmodel.Enumeration) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.pivotmodel.Type) {
			result = interprete_tudresden_ocl20_pivot_pivotmodel_Type((tudresden.ocl20.pivot.pivotmodel.Type) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.pivotmodel.GenericElement) {
			result = interprete_tudresden_ocl20_pivot_pivotmodel_GenericElement((tudresden.ocl20.pivot.pivotmodel.GenericElement) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral) {
			result = interprete_tudresden_ocl20_pivot_pivotmodel_EnumerationLiteral((tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.pivotmodel.Property) {
			result = interprete_tudresden_ocl20_pivot_pivotmodel_Property((tudresden.ocl20.pivot.pivotmodel.Property) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.pivotmodel.Operation) {
			result = interprete_tudresden_ocl20_pivot_pivotmodel_Operation((tudresden.ocl20.pivot.pivotmodel.Operation) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.pivotmodel.Parameter) {
			result = interprete_tudresden_ocl20_pivot_pivotmodel_Parameter((tudresden.ocl20.pivot.pivotmodel.Parameter) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.pivotmodel.ParameterGenericType) {
			result = interprete_tudresden_ocl20_pivot_pivotmodel_ParameterGenericType((tudresden.ocl20.pivot.pivotmodel.ParameterGenericType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.pivotmodel.ComplexGenericType) {
			result = interprete_tudresden_ocl20_pivot_pivotmodel_ComplexGenericType((tudresden.ocl20.pivot.pivotmodel.ComplexGenericType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.pivotmodel.GenericType) {
			result = interprete_tudresden_ocl20_pivot_pivotmodel_GenericType((tudresden.ocl20.pivot.pivotmodel.GenericType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.pivotmodel.TypeParameter) {
			result = interprete_tudresden_ocl20_pivot_pivotmodel_TypeParameter((tudresden.ocl20.pivot.pivotmodel.TypeParameter) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.pivotmodel.Constraint) {
			result = interprete_tudresden_ocl20_pivot_pivotmodel_Constraint((tudresden.ocl20.pivot.pivotmodel.Constraint) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.pivotmodel.AssociationProperty) {
			result = interprete_tudresden_ocl20_pivot_pivotmodel_AssociationProperty((tudresden.ocl20.pivot.pivotmodel.AssociationProperty) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.pivotmodel.NamedElement) {
			result = interprete_tudresden_ocl20_pivot_pivotmodel_NamedElement((tudresden.ocl20.pivot.pivotmodel.NamedElement) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.pivotmodel.ConstrainableElement) {
			result = interprete_tudresden_ocl20_pivot_pivotmodel_ConstrainableElement((tudresden.ocl20.pivot.pivotmodel.ConstrainableElement) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.pivotmodel.TypeArgument) {
			result = interprete_tudresden_ocl20_pivot_pivotmodel_TypeArgument((tudresden.ocl20.pivot.pivotmodel.TypeArgument) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof tudresden.ocl20.pivot.pivotmodel.Expression) {
			result = interprete_tudresden_ocl20_pivot_pivotmodel_Expression((tudresden.ocl20.pivot.pivotmodel.Expression) object, context);
		}
		if (result != null) {
			return result;
		}
		return result;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_OclExpressionCS(tudresden.ocl20.pivot.language.ocl.OclExpressionCS oclExpressionCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_BracketExpCS(tudresden.ocl20.pivot.language.ocl.BracketExpCS bracketExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_NamedLiteralExpCS(tudresden.ocl20.pivot.language.ocl.NamedLiteralExpCS namedLiteralExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_PathNameCS(tudresden.ocl20.pivot.language.ocl.PathNameCS pathNameCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_SimpleNameCS(tudresden.ocl20.pivot.language.ocl.SimpleNameCS simpleNameCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_TypeCS(tudresden.ocl20.pivot.language.ocl.TypeCS typeCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_TypePathNameCS(tudresden.ocl20.pivot.language.ocl.TypePathNameCS typePathNameCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_TypePathNameSimpleCS(tudresden.ocl20.pivot.language.ocl.TypePathNameSimpleCS typePathNameSimpleCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_TypePathNameNestedCS(tudresden.ocl20.pivot.language.ocl.TypePathNameNestedCS typePathNameNestedCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_TupleTypeCS(tudresden.ocl20.pivot.language.ocl.TupleTypeCS tupleTypeCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_CollectionTypeLiteralExpCS(tudresden.ocl20.pivot.language.ocl.CollectionTypeLiteralExpCS collectionTypeLiteralExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_TupleTypeLiteralExpCS(tudresden.ocl20.pivot.language.ocl.TupleTypeLiteralExpCS tupleTypeLiteralExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_VariableDeclarationCS(tudresden.ocl20.pivot.language.ocl.VariableDeclarationCS variableDeclarationCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_VariableDeclarationWithInitCS(tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitCS variableDeclarationWithInitCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_VariableDeclarationWithoutInitCS(tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithoutInitCS variableDeclarationWithoutInitCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_VariableDeclarationWithInitListCS(tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitListCS variableDeclarationWithInitListCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_VariableDeclarationWithoutInitListCS(tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithoutInitListCS variableDeclarationWithoutInitListCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_LiteralExpCS(tudresden.ocl20.pivot.language.ocl.LiteralExpCS literalExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_EnumLiteralOrStaticPropertyExpCS(tudresden.ocl20.pivot.language.ocl.EnumLiteralOrStaticPropertyExpCS enumLiteralOrStaticPropertyExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_CollectionLiteralExpCS(tudresden.ocl20.pivot.language.ocl.CollectionLiteralExpCS collectionLiteralExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_CollectionTypeIdentifierCS(tudresden.ocl20.pivot.language.ocl.CollectionTypeIdentifierCS collectionTypeIdentifierCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_CollectionLiteralPartsCS(tudresden.ocl20.pivot.language.ocl.CollectionLiteralPartsCS collectionLiteralPartsCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_CollectionLiteralPartsOclExpCS(tudresden.ocl20.pivot.language.ocl.CollectionLiteralPartsOclExpCS collectionLiteralPartsOclExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_CollectionRangeCS(tudresden.ocl20.pivot.language.ocl.CollectionRangeCS collectionRangeCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_CallExpCS(tudresden.ocl20.pivot.language.ocl.CallExpCS callExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_LoopExpCS(tudresden.ocl20.pivot.language.ocl.LoopExpCS loopExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_IteratorExpVariableCS(tudresden.ocl20.pivot.language.ocl.IteratorExpVariableCS iteratorExpVariableCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_IteratorExpCS(tudresden.ocl20.pivot.language.ocl.IteratorExpCS iteratorExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_IterateExpCS(tudresden.ocl20.pivot.language.ocl.IterateExpCS iterateExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_FeatureCallExpCS(tudresden.ocl20.pivot.language.ocl.FeatureCallExpCS featureCallExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_NavigationCallExp(tudresden.ocl20.pivot.language.ocl.NavigationCallExp navigationCallExp, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_OperationCallBaseExpCS(tudresden.ocl20.pivot.language.ocl.OperationCallBaseExpCS operationCallBaseExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_PropertyCallBaseExpCS(tudresden.ocl20.pivot.language.ocl.PropertyCallBaseExpCS propertyCallBaseExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_ImplicitFeatureCallCS(tudresden.ocl20.pivot.language.ocl.ImplicitFeatureCallCS implicitFeatureCallCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_ImplicitPropertyCallCS(tudresden.ocl20.pivot.language.ocl.ImplicitPropertyCallCS implicitPropertyCallCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_ImplicitOperationCallCS(tudresden.ocl20.pivot.language.ocl.ImplicitOperationCallCS implicitOperationCallCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_PropertyCallExpCS(tudresden.ocl20.pivot.language.ocl.PropertyCallExpCS propertyCallExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_PropertyCallOnSelfExpCS(tudresden.ocl20.pivot.language.ocl.PropertyCallOnSelfExpCS propertyCallOnSelfExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_PropertyCallExplicitPathExpCS(tudresden.ocl20.pivot.language.ocl.PropertyCallExplicitPathExpCS propertyCallExplicitPathExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_OperationCallExpCS(tudresden.ocl20.pivot.language.ocl.OperationCallExpCS operationCallExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_OperationCallOnSelfExpCS(tudresden.ocl20.pivot.language.ocl.OperationCallOnSelfExpCS operationCallOnSelfExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_StaticOperationCallExpCS(tudresden.ocl20.pivot.language.ocl.StaticOperationCallExpCS staticOperationCallExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_UnaryOperationCallExpCS(tudresden.ocl20.pivot.language.ocl.UnaryOperationCallExpCS unaryOperationCallExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_LogicalNotOperationCallExpCS(tudresden.ocl20.pivot.language.ocl.LogicalNotOperationCallExpCS logicalNotOperationCallExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_OperationCallWithSourceExpCS(tudresden.ocl20.pivot.language.ocl.OperationCallWithSourceExpCS operationCallWithSourceExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_OperationCallBinaryExpCS(tudresden.ocl20.pivot.language.ocl.OperationCallBinaryExpCS operationCallBinaryExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_AdditiveOperationCallExpCS(tudresden.ocl20.pivot.language.ocl.AdditiveOperationCallExpCS additiveOperationCallExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_MultOperationCallExpCS(tudresden.ocl20.pivot.language.ocl.MultOperationCallExpCS multOperationCallExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_RelationalOperationCallExpCS(tudresden.ocl20.pivot.language.ocl.RelationalOperationCallExpCS relationalOperationCallExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_EqualityOperationCallExpCS(tudresden.ocl20.pivot.language.ocl.EqualityOperationCallExpCS equalityOperationCallExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_LogicalAndOperationCallExpCS(tudresden.ocl20.pivot.language.ocl.LogicalAndOperationCallExpCS logicalAndOperationCallExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_LogicalOrOperationCallExpCS(tudresden.ocl20.pivot.language.ocl.LogicalOrOperationCallExpCS logicalOrOperationCallExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_LogicalXorOperationCallExpCS(tudresden.ocl20.pivot.language.ocl.LogicalXorOperationCallExpCS logicalXorOperationCallExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_LogicalImpliesOperationCallExpCS(tudresden.ocl20.pivot.language.ocl.LogicalImpliesOperationCallExpCS logicalImpliesOperationCallExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_OperationCallWithImlicitSourceExpCS(tudresden.ocl20.pivot.language.ocl.OperationCallWithImlicitSourceExpCS operationCallWithImlicitSourceExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_TupleLiteralExpCS(tudresden.ocl20.pivot.language.ocl.TupleLiteralExpCS tupleLiteralExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_PrimitiveLiteralExpCS(tudresden.ocl20.pivot.language.ocl.PrimitiveLiteralExpCS primitiveLiteralExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_IntegerLiteralExpCS(tudresden.ocl20.pivot.language.ocl.IntegerLiteralExpCS integerLiteralExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_RealLiteralExpCS(tudresden.ocl20.pivot.language.ocl.RealLiteralExpCS realLiteralExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_BooleanLiteralExpCS(tudresden.ocl20.pivot.language.ocl.BooleanLiteralExpCS booleanLiteralExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_StringLiteralExpCS(tudresden.ocl20.pivot.language.ocl.StringLiteralExpCS stringLiteralExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_InvalidLiteralExpCS(tudresden.ocl20.pivot.language.ocl.InvalidLiteralExpCS invalidLiteralExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_NullLiteralExpCS(tudresden.ocl20.pivot.language.ocl.NullLiteralExpCS nullLiteralExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_LetExpCS(tudresden.ocl20.pivot.language.ocl.LetExpCS letExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_IfExpCS(tudresden.ocl20.pivot.language.ocl.IfExpCS ifExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_PackageDeclarationCS(tudresden.ocl20.pivot.language.ocl.PackageDeclarationCS packageDeclarationCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_PackageDeclarationWithNamespaceCS(tudresden.ocl20.pivot.language.ocl.PackageDeclarationWithNamespaceCS packageDeclarationWithNamespaceCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_PackageDeclarationNestedNamespaceCS(tudresden.ocl20.pivot.language.ocl.PackageDeclarationNestedNamespaceCS packageDeclarationNestedNamespaceCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_PackageDeclarationWithoutNamespaceCS(tudresden.ocl20.pivot.language.ocl.PackageDeclarationWithoutNamespaceCS packageDeclarationWithoutNamespaceCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_ContextDeclarationCS(tudresden.ocl20.pivot.language.ocl.ContextDeclarationCS contextDeclarationCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_AttributeContextDeclarationCS(tudresden.ocl20.pivot.language.ocl.AttributeContextDeclarationCS attributeContextDeclarationCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_ClassifierContextDeclarationCS(tudresden.ocl20.pivot.language.ocl.ClassifierContextDeclarationCS classifierContextDeclarationCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_OperationContextDeclarationCS(tudresden.ocl20.pivot.language.ocl.OperationContextDeclarationCS operationContextDeclarationCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_InitOrDeriveValueCS(tudresden.ocl20.pivot.language.ocl.InitOrDeriveValueCS initOrDeriveValueCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_InitValueCS(tudresden.ocl20.pivot.language.ocl.InitValueCS initValueCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_DeriveValueCS(tudresden.ocl20.pivot.language.ocl.DeriveValueCS deriveValueCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_InvariantOrDefinitionCS(tudresden.ocl20.pivot.language.ocl.InvariantOrDefinitionCS invariantOrDefinitionCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_InvariantExpCS(tudresden.ocl20.pivot.language.ocl.InvariantExpCS invariantExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_DefinitionExpCS(tudresden.ocl20.pivot.language.ocl.DefinitionExpCS definitionExpCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_DefinitionExpPartCS(tudresden.ocl20.pivot.language.ocl.DefinitionExpPartCS definitionExpPartCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_DefinitionExpPropertyCS(tudresden.ocl20.pivot.language.ocl.DefinitionExpPropertyCS definitionExpPropertyCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_DefinitionExpOperationCS(tudresden.ocl20.pivot.language.ocl.DefinitionExpOperationCS definitionExpOperationCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_PrePostOrBodyDeclarationCS(tudresden.ocl20.pivot.language.ocl.PrePostOrBodyDeclarationCS prePostOrBodyDeclarationCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_PreConditionDeclarationCS(tudresden.ocl20.pivot.language.ocl.PreConditionDeclarationCS preConditionDeclarationCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_PostConditionDeclarationCS(tudresden.ocl20.pivot.language.ocl.PostConditionDeclarationCS postConditionDeclarationCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_BodyDeclarationCS(tudresden.ocl20.pivot.language.ocl.BodyDeclarationCS bodyDeclarationCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_OperationDefinitionCS(tudresden.ocl20.pivot.language.ocl.OperationDefinitionCS operationDefinitionCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_OperationDefinitionInContextCS(tudresden.ocl20.pivot.language.ocl.OperationDefinitionInContextCS operationDefinitionInContextCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_OperationDefinitionInDefCS(tudresden.ocl20.pivot.language.ocl.OperationDefinitionInDefCS operationDefinitionInDefCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_language_ocl_ParameterCS(tudresden.ocl20.pivot.language.ocl.ParameterCS parameterCS, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_pivotmodel_NamedElement(tudresden.ocl20.pivot.pivotmodel.NamedElement namedElement, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_pivotmodel_TypedElement(tudresden.ocl20.pivot.pivotmodel.TypedElement typedElement, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_pivotmodel_Feature(tudresden.ocl20.pivot.pivotmodel.Feature feature, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_pivotmodel_GenericElement(tudresden.ocl20.pivot.pivotmodel.GenericElement genericElement, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_pivotmodel_ConstrainableElement(tudresden.ocl20.pivot.pivotmodel.ConstrainableElement constrainableElement, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_pivotmodel_Namespace(tudresden.ocl20.pivot.pivotmodel.Namespace namespace, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_pivotmodel_Type(tudresden.ocl20.pivot.pivotmodel.Type type, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_pivotmodel_PrimitiveType(tudresden.ocl20.pivot.pivotmodel.PrimitiveType primitiveType, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_pivotmodel_Enumeration(tudresden.ocl20.pivot.pivotmodel.Enumeration enumeration, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_pivotmodel_EnumerationLiteral(tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral enumerationLiteral, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_pivotmodel_Property(tudresden.ocl20.pivot.pivotmodel.Property property, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_pivotmodel_Operation(tudresden.ocl20.pivot.pivotmodel.Operation operation, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_pivotmodel_Parameter(tudresden.ocl20.pivot.pivotmodel.Parameter parameter, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_pivotmodel_GenericType(tudresden.ocl20.pivot.pivotmodel.GenericType genericType, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_pivotmodel_ParameterGenericType(tudresden.ocl20.pivot.pivotmodel.ParameterGenericType parameterGenericType, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_pivotmodel_ComplexGenericType(tudresden.ocl20.pivot.pivotmodel.ComplexGenericType complexGenericType, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_pivotmodel_TypeParameter(tudresden.ocl20.pivot.pivotmodel.TypeParameter typeParameter, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_pivotmodel_TypeArgument(tudresden.ocl20.pivot.pivotmodel.TypeArgument typeArgument, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_pivotmodel_Constraint(tudresden.ocl20.pivot.pivotmodel.Constraint constraint, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_pivotmodel_Expression(tudresden.ocl20.pivot.pivotmodel.Expression expression, ContextType context) {
		return null;
	}
	
	public ResultType interprete_tudresden_ocl20_pivot_pivotmodel_AssociationProperty(tudresden.ocl20.pivot.pivotmodel.AssociationProperty associationProperty, ContextType context) {
		return null;
	}
	
	private void notifyListeners(org.eclipse.emf.ecore.EObject element) {
		for (tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclInterpreterListener listener : listeners) {
			listener.handleInterpreteObject(element);
		}
	}
	
	/**
	 * Adds the given object to the interpretation stack. Attention: Objects that are
	 * added first, are interpret last.
	 */
	public void addObjectToInterprete(org.eclipse.emf.ecore.EObject object) {
		interpretationStack.push(object);
	}
	
	/**
	 * Adds the given collection of objects to the interpretation stack. Attention:
	 * Collections that are added first, are interpret last.
	 */
	public void addObjectsToInterprete(java.util.Collection<? extends org.eclipse.emf.ecore.EObject> objects) {
		for (org.eclipse.emf.ecore.EObject object : objects) {
			addObjectToInterprete(object);
		}
	}
	
	/**
	 * Adds the given collection of objects in reverse order to the interpretation
	 * stack.
	 */
	public void addObjectsToInterpreteInReverseOrder(java.util.Collection<? extends org.eclipse.emf.ecore.EObject> objects) {
		java.util.List<org.eclipse.emf.ecore.EObject> reverse = new java.util.ArrayList<org.eclipse.emf.ecore.EObject>(objects.size());
		reverse.addAll(objects);
		java.util.Collections.reverse(reverse);
		addObjectsToInterprete(reverse);
	}
	
	/**
	 * Adds the given object and all its children to the interpretation stack such
	 * that they are interpret in top down order.
	 */
	public void addObjectTreeToInterpreteTopDown(org.eclipse.emf.ecore.EObject root) {
		java.util.List<org.eclipse.emf.ecore.EObject> objects = new java.util.ArrayList<org.eclipse.emf.ecore.EObject>();
		objects.add(root);
		java.util.Iterator<org.eclipse.emf.ecore.EObject> it = root.eAllContents();
		while (it.hasNext()) {
			org.eclipse.emf.ecore.EObject eObject = (org.eclipse.emf.ecore.EObject) it.next();
			objects.add(eObject);
		}
		addObjectsToInterpreteInReverseOrder(objects);
	}
	
	public void addListener(tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclInterpreterListener newListener) {
		listeners.add(newListener);
	}
	
	public boolean removeListener(tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclInterpreterListener listener) {
		return listeners.remove(listener);
	}
	
	public org.eclipse.emf.ecore.EObject getNextObjectToInterprete() {
		return nextObjectToInterprete;
	}
	
	public java.util.Stack<org.eclipse.emf.ecore.EObject> getInterpretationStack() {
		return interpretationStack;
	}
	
	public void terminate() {
		interpretationStack.clear();
	}
	
	public Object getCurrentContext() {
		return currentContext;
	}
	
}
