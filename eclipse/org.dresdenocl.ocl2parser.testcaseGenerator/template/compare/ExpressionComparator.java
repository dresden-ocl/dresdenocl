package ${packagename}.compare;

import java.util.List;

import org.dresdenocl.essentialocl.expressions.BooleanLiteralExp;
import org.dresdenocl.essentialocl.expressions.CallExp;
import org.dresdenocl.essentialocl.expressions.CollectionItem;
import org.dresdenocl.essentialocl.expressions.CollectionLiteralExp;
import org.dresdenocl.essentialocl.expressions.CollectionLiteralPart;
import org.dresdenocl.essentialocl.expressions.CollectionRange;
import org.dresdenocl.essentialocl.expressions.EnumLiteralExp;
import org.dresdenocl.essentialocl.expressions.ExpressionInOcl;
import org.dresdenocl.essentialocl.expressions.FeatureCallExp;
import org.dresdenocl.essentialocl.expressions.IfExp;
import org.dresdenocl.essentialocl.expressions.IntegerLiteralExp;
import org.dresdenocl.essentialocl.expressions.InvalidLiteralExp;
import org.dresdenocl.essentialocl.expressions.IterateExp;
import org.dresdenocl.essentialocl.expressions.IteratorExp;
import org.dresdenocl.essentialocl.expressions.LetExp;
import org.dresdenocl.essentialocl.expressions.LiteralExp;
import org.dresdenocl.essentialocl.expressions.LoopExp;
import org.dresdenocl.essentialocl.expressions.NumericLiteralExp;
import org.dresdenocl.essentialocl.expressions.OclExpression;
import org.dresdenocl.essentialocl.expressions.OperationCallExp;
import org.dresdenocl.essentialocl.expressions.PrimitiveLiteralExp;
import org.dresdenocl.essentialocl.expressions.PropertyCallExp;
import org.dresdenocl.essentialocl.expressions.RealLiteralExp;
import org.dresdenocl.essentialocl.expressions.StringLiteralExp;
import org.dresdenocl.essentialocl.expressions.TupleLiteralExp;
import org.dresdenocl.essentialocl.expressions.TupleLiteralPart;
import org.dresdenocl.essentialocl.expressions.TypeLiteralExp;
import org.dresdenocl.essentialocl.expressions.UndefinedLiteralExp;
import org.dresdenocl.essentialocl.expressions.UnlimitedNaturalExp;
import org.dresdenocl.essentialocl.expressions.Variable;
import org.dresdenocl.essentialocl.expressions.VariableExp;
import ${packagename}.compare.stringTree.ErrorFlag;
import ${packagename}.compare.stringTree.StringInnerNode;
import ${packagename}.compare.stringTree.StringLeaf;
import ${packagename}.compare.stringTree.StringNode;
import org.dresdenocl.pivotmodel.ConstrainableElement;
import org.dresdenocl.pivotmodel.Constraint;
import org.dresdenocl.pivotmodel.EnumerationLiteral;
import org.dresdenocl.pivotmodel.Expression;
import org.dresdenocl.pivotmodel.Feature;
import org.dresdenocl.pivotmodel.Namespace;
import org.dresdenocl.pivotmodel.Operation;
import org.dresdenocl.pivotmodel.Parameter;
import org.dresdenocl.pivotmodel.Property;
import org.dresdenocl.pivotmodel.Type;

/**
 * This class is used to compare two expressions of the essential ocl and parts
 * of the pivotmodel. In this class we have two kinds of methods. First we have
 * the 'compare'-method with different parameters types. The idea is that we put
 * two expressions in one of these compare-methods and the polymorphism search the
 * convenient method. So the expressions are traversed recursively. The polymorphism
 * unfortunately doesn't always work. So we must write compare-methods that  expect
 * abstract types, for example {@link OclExpression} or {@link LoopExp}. These methods make a
 * type check and downcast the objects to the right type. For example: if we put a
 * {@link IntegerLiteralExp} in the compare method with the {@link OclExpression}-type parameter
 * then the object is cast to {@link LiteralExp}. Then the compare-method with the
 * {@link LiteralExp}-type parameters is called and the object is cast to {@link PrimitiveLiteralExp}
 * and so on.
 * <p>
 * The other type of method has the name compareXXX, where XXX stands for some
 * type identifier such as {@link LoopExp}, {@link FeatureCallExp} etc. This methods are needed for reducing
 * duplicated code. For example: Suppose we want to compare two {@link OperationCallExp} instances. The type
 * {@link OperationCallExp} has several super types such as {@link FeatureCallExp} and {@link CallExp}. These super types
 * has elements, that we must consider while comparing the two instances. But these elements
 * must be considered for the {@link PropertyCallExp} too, so we must write duplicated code. To
 * bypass this we have introduced the second type of compare-methods.
 * <p>
 * All compare methods have an additional parameter of type {@link StringNode}. This
 * parameter is used to construct a tree that represents the common structure of both expressions
 * that are compared to. This tree is used to give the user a hint where an error appeared if
 * any was detected.
 * <p>
 * All compare methods follow an identical schema.
 * <p>
 * If the compare method expect two concrete types such as {@link BooleanLiteralExp} then the schema is to following:
 * - Set the text of the actual node by getting the original text of the node and append the type of the parameter type
 *   (for example: if we have the {@link BooleanLiteralExp} the we set node.setText(<code>node.getText()+" BooleanLiteralExp"</code>))
 *   The original text of the node was set a level before. This text is typically a hint for part that is represented
 *   by this subtree (for exmaple: a {@link PropertyCallExp} has a source, so the node's text will be set to 'source').
 * <p>
 * <ul>
 * <li> Examine whether the two expressions are both null. If so, we assume that these expressions are equal and we will return.</li>
 * <li> Call the method compareByInstances with the expressions and the node. This method will examine whether at least one
 *   of the expressions is null. If so it must be an error because one expression represents potentially a sub tree while
 *   the other expression is null. That can't be equal. If exactly one the expressions is null, this will noticed in the
 *   node with the setting of the error tag of the node and the {@link CompareException} will be thrown.</li>
 * <li>If the expression has any subexpressions as for example {@link CollectionRange} then create a new {@link StringNode},
 *   set the text of the node (in this example 'first:' and 'last:'), add the node to the actual node of the method and
 *   call the compare-method with the subexpressions and the just created child node.</li>
 * <li> Make the comparisons of this node. For example: the {@link BooleanLiteralExp} has a symbol. The expressions
 *   must be compared to have the same symbols. If that is not the case, an error message will be built, the node's
 *   error tag will be set and a {@link CompareException} will be thrown.</li>
 * </ul>
 * <p>
 * The compare methods that represents switches (the methods that have abstract type parameters such as {@link OclExpression}
 * etc.) are built as follows:
 * <ul>
 * <li>Examine whether the expressions both are null. If so we return because we assume that to null expressions
 *   are equal.</li>
 * <li> We call the method compareByInstance to examine whether both instances really exist. If exactly one
 *   of the instances is null, we assume that these expressions are unequal (see ahead).</li>
 * <li> Examine whether both expressions are of the same subtype (for example: suppose we consider the compare method
 *   with the {@link CallExp} type parameter. In this method we must examine whether both expressions are of type
 *   {@link FeatureCallExp} and {@link LoopExp}.). If so make a downcast to the more concrete type and call
 *   the appropiate compare-method. Note: in this case we leave the node unchanged.</li>
 * <li> If the expressions aren't of the same type: make an error message, set the error tag of the node and
 *   throw a {@link CompareException}.</li>
 * </ul>
 * Some other helper methods exist in this class. See their javadoc explanation for more details.
 * <p>
 * Some hints at the comparison:
 * <ul>
 * <li> The ocl string of the ocl expression (type 'Expression' or 'ExpressionInOcl') is not
 *   compared.</li>
 * </ul>
 *  
 * @author Nils
 *
 */
public class ExpressionComparator {
	public void compare(BooleanLiteralExp exp1, BooleanLiteralExp exp2, StringNode node) throws CompareException {
		node.setText(node.getText() + " BooleanLiteralExp");
		if ((exp1 == null) && (exp2 == null)) return;
		compareByInstances(exp1, exp2, node);
		
		if (exp1.isBooleanSymbol() == exp2.isBooleanSymbol()) return;
		
		String message = "BooleanLiteralExpressions are unequal.";
		message = message + "\n The value of the first expression is: " + exp1.isBooleanSymbol();
		message = message + "\n The value of the second expression is: " + exp2.isBooleanSymbol();
		node.setError(true);
		node.setErrorType(ErrorFlag.DIFFERENTIATE_ERROR);
		throw new CompareException(message, node);
	}
	
	public void compare(CallExp exp1, CallExp exp2, StringNode node) throws CompareException {
		
		if ((exp1 == null) && (exp2 == null)) return;
		
		compareByInstances(exp1, exp2, node);
		
		if ((exp1 instanceof FeatureCallExp) && (exp2 instanceof FeatureCallExp)) {
			compare((FeatureCallExp)exp1, (FeatureCallExp)exp2, node);
			return;
		}
			
		if ((exp1 instanceof LoopExp) && (exp2 instanceof LoopExp)) {
			compare((LoopExp) exp1, (LoopExp) exp2, node);
			return;
		}
		
		String message = constructUnequalExpString(exp1, exp2);
		node.setError(true);
		node.setErrorType(ErrorFlag.DIFFERENTIATE_ERROR);
		throw new CompareException(message, node);
	}
	
	public void compare(CollectionItem exp1, CollectionItem exp2, StringNode node) throws CompareException {
		node.setText(node.getText() + " CollectionItem");
		if ((exp1 == null) && (exp2 == null)) return;
		
		compareByInstances(exp1, exp2, node);
		
		String message = "CollectionItems are unequal.";
		StringNode childNode = new StringInnerNode();
		childNode.setText("item:");
		node.addChild(childNode);
		
		compare(exp1.getItem(), exp2.getItem(), childNode);
		
		StringNode childNode2 = new StringLeaf();
		childNode2.setText("name:");
		node.addChild(childNode2);
		
		compare(exp1.getName(), exp2.getName(), childNode2);
	}
	
	public void compare(CollectionLiteralExp exp1, CollectionLiteralExp exp2, StringNode node) throws CompareException {
		node.setText(node.getText() + " CollectionLiteralExp");
		if ((exp1 == null) && (exp2 == null)) return;
		String message = "CollectionLiteralExps are unequal.";
		
		List<CollectionLiteralPart> partListExp1 = exp1.getPart();
		List<CollectionLiteralPart> partListExp2 = exp2.getPart();
		
		StringNode childNode = new StringInnerNode();
		childNode.setText("part:");
		node.addChild(childNode);
		compareCollectionLiteralPartList(partListExp1, partListExp2, childNode);
		
		StringNode childNodeKind = new StringLeaf();
		childNodeKind.setText("kind");
		node.addChild(childNodeKind);
		
		if (!(exp1.getKind().equals(exp2.getKind()))) {
			message = message + "\n The first collection literal expression has the kind: " + exp1.getKind();
			message = message + "\n The second collection literal expression has the kind: " + exp2.getKind();
			childNodeKind.setError(true);
			childNodeKind.setErrorType(ErrorFlag.DIFFERENTIATE_ERROR);
			throw new CompareException(message, childNodeKind);
		}
	}
	
	public void compareCollectionLiteralPartList(List<CollectionLiteralPart> list1, List<CollectionLiteralPart> list2, StringNode node) throws CompareException {
		node.setText(node.getText() + " CollectionLiteralList");
		if ((list1 == null) && (list2 == null)) return;
		
		compareByInstances(list1, list2, node);
		
		String message = "The collection literal part lists are unequal.";
		if (list1.size() != list2.size()) {
			message = message + "\n The lists have different lengths.";
			message = message + "\n The first list has the following number of elements: " + list1.size();
			message = message + "\n The second list has the following number of elements: " + list2.size();
			node.setError(true);
			node.setErrorType(ErrorFlag.DIFFERENTIATE_ERROR);
			throw new CompareException(message, node);
		}
		
		for(int i = 0; i < list1.size(); i++) {
			StringNode childNode = new StringInnerNode();
			childNode.setText("part:");
			node.addChild(childNode);
			
			compare(list1.get(i), list2.get(i), childNode);
		}
	}
	
	public void compare(CollectionLiteralPart exp1, CollectionLiteralPart exp2, StringNode node) throws CompareException {
		if ((exp1 == null) && (exp2 == null)) return;
		compareByInstances(exp1, exp2, node);
		
		if ((exp1 instanceof CollectionRange) && (exp2 instanceof CollectionRange)) {
			compare((CollectionRange) exp1, (CollectionRange) exp2, node);
			return;
		}
		
		if ((exp1 instanceof CollectionItem) && (exp2 instanceof CollectionItem)) {
			compare((CollectionItem) exp1, (CollectionItem) exp2, node);
			return;
		}
		
		String message = constructUnequalExpString(exp1, exp2);
		node.setError(true);
		node.setErrorType(ErrorFlag.DIFFERENTIATE_ERROR);
		throw new CompareException(message, node);
	}
	
	public void compare(CollectionRange exp1, CollectionRange exp2, StringNode node) throws CompareException {
		node.setText(node.getText() + " CollectionRange");
		if ((exp1 == null) && (exp2 == null)) return;
		
		StringNode childFirst = new StringInnerNode();
		childFirst.setText("first");
		node.addChild(childFirst);
		
		compare(exp1.getFirst(), exp2.getFirst(), childFirst);
		
		StringNode childLast = new StringInnerNode();
		childLast.setText("last");
		node.addChild(childLast);
		compare(exp1.getLast(), exp2.getLast(), childLast);
	}
	
	public void compare(EnumLiteralExp exp1, EnumLiteralExp exp2, StringNode node) throws CompareException {
		node.setText(node.getText() + " EnumLiteralExp");
		if ((exp1 == null) && (exp2 == null)) return;
		
		StringNode childNode = new StringLeaf();
		childNode.setText("literal:");
		node.addChild(childNode);
		compare(exp1.getReferredEnumLiteral(), exp2.getReferredEnumLiteral(), childNode);
	}
	
	public void compare(ExpressionInOcl exp1, ExpressionInOcl exp2, StringNode node) throws CompareException {
		node.setText(node.getText() + " ExpressionInOcl");
		if ((exp1 == null) && (exp2 == null)) return;
		compareByInstances(exp1, exp2, node);
		
		StringNode childBody = new StringInnerNode();
		childBody.setText("body:");
		node.addChild(childBody);
		compare(exp1.getBodyExpression(), exp2.getBodyExpression(), childBody);
		
		StringNode childContext = new StringInnerNode();
		childContext.setText("context:");
		node.addChild(childContext);
		compare(exp1.getContext(), exp2.getContext(), childContext);
		
		StringNode childResult = new StringInnerNode();
		childResult.setText("result:");
		node.addChild(childResult);
		compare(exp1.getResult(), exp2.getResult(), childResult);
		
		String message = "The ExpressionInOcl expressions are unequal.";
		
		List<Variable> parameterList1 = exp1.getParameter();
		List<Variable> parameterList2 = exp2.getParameter();
		
		StringNode childParameterList = new StringInnerNode();
		childParameterList.setText("parameter:");
		node.addChild(childParameterList);
		compareVariableList(parameterList1, parameterList2, childParameterList);
	}
	
	public void compareVariableList(List<Variable> list1, List<Variable> list2, StringNode node) throws CompareException {
		node.setText(node.getText() + " VariableList");
		if ((list1 == null) && (list2 == null)) return;
		
		compareByInstances(list1, list2, node);
		
		String message = "The variable lists are not equal.";
		if (list1.size() != list2.size()) {
			message = message + "\n The lists have different sizes.";
			message = message + "\n The first variable list has the following number of elements: " + list1.size();
			message = message + "\n The second variable list has the following number of elements: " + list2.size();
			node.setError(true);
			node.setErrorType(ErrorFlag.DIFFERENTIATE_ERROR);
			throw new CompareException(message, node);
		}
		
		for(int i = 0; i < list1.size(); i++) {
			StringNode child = new StringInnerNode();
			child.setText("variable:");
			node.addChild(child);
			
			compare(list1.get(i), list2.get(i), child);
		}
	}
	
	public void compare(FeatureCallExp exp1, FeatureCallExp exp2, StringNode node) throws CompareException {
		if ((exp1 == null) && (exp2 == null)) return;
		
		compareByInstances(exp1, exp2, node);
				
		if ((exp1 instanceof PropertyCallExp) && (exp2 instanceof PropertyCallExp)) {
			compare((PropertyCallExp) exp1, (PropertyCallExp) exp2, node);
			return;
		}
		
		if ((exp1 instanceof OperationCallExp) && (exp2 instanceof OperationCallExp)) {
			compare((OperationCallExp) exp1, (OperationCallExp) exp2, node);
			return;
		}
		
		String message = constructUnequalExpString(exp1, exp2);
		node.setError(true);
		node.setErrorType(ErrorFlag.DIFFERENTIATE_ERROR);
		throw new CompareException(message, node);
		
	}
	
	public void compare(IfExp exp1, IfExp exp2, StringNode node) throws CompareException {
		node.setText(node.getText() + " IfExp");
		if ((exp1 == null) && (exp2 == null)) return;
		
		compareByInstances(exp1, exp2, node);
		
		StringNode childCondition = new StringInnerNode();
		childCondition.setText("condition:");
		node.addChild(childCondition);
		
		compare(exp1.getCondition(), exp2.getCondition(), childCondition);
		
		StringNode childThen = new StringInnerNode();
		childThen.setText("then:");
		node.addChild(childThen);
		
		compare(exp1.getThenExpression(), exp2.getThenExpression(), childThen);
		
		StringNode childElse = new StringInnerNode();
		childElse.setText("else:");
		node.addChild(childElse);
		compare(exp1.getElseExpression(), exp2.getElseExpression(), childElse);
	}
	
	public void compare(IntegerLiteralExp exp1, IntegerLiteralExp exp2, StringNode node) throws CompareException {
		node.setText(node.getText() + " IntegerLiteralExp");
		if ((exp1 == null) && (exp2 == null)) return;
		
		compareByInstances(exp1, exp2, node);
		
		String message = "The IntegerLiteralExp expressions are unequal.";
		if (exp1.getIntegerSymbol() != exp2.getIntegerSymbol()) {
			message = message + "\n The value of the first expression is: " + exp1.getIntegerSymbol();
			message = message + "\n The value of the second expression is: " + exp2.getIntegerSymbol();
			node.setError(true);
			node.setErrorType(ErrorFlag.DIFFERENTIATE_ERROR);
			throw new CompareException(message, node);
		}
	}
	
	public void compare(InvalidLiteralExp exp1, InvalidLiteralExp exp2, StringNode node) throws CompareException {
		node.setText(node.getText() + " InvalidLiteralExp");
		compareByInstances(exp1, exp2, node);
		return;
	}
	
	public void compare(IterateExp exp1, IterateExp exp2, StringNode node) throws CompareException {
		node.setText(node.getText() + " IterateExp");
		if ((exp1 == null) && (exp2 == null)) return;
		
		compareByInstances(exp1, exp2, node);
		
		StringNode childCall = new StringInnerNode();
		childCall.setText("call part:");
		node.addChild(childCall);
		compareCallExp(exp1, exp2, childCall);
		
		StringNode childLoop = new StringInnerNode();
		childLoop.setText("loop part:");
		node.addChild(childLoop);
		compareLoopExp("IterateExp",exp1, exp2, childLoop);
		
		StringNode childResult = new StringInnerNode();
		childResult.setText("result:");
		node.addChild(childResult);
		compare(exp1.getResult(), exp2.getResult(), childResult);
	}
	
	public void compare(IteratorExp exp1, IteratorExp exp2, StringNode node) throws CompareException {
		node.setText(node.getText() + " IteratorExp");
		if ((exp1 == null) && (exp2 == null)) return;
		
		compareByInstances(exp1, exp2, node);
		
		StringNode childCall = new StringInnerNode();
		childCall.setText("call part:");
		node.addChild(childCall);
		
		compareCallExp(exp1, exp2, childCall);
		
		StringNode childLoop = new StringInnerNode();
		childLoop.setText("loop part:");
		node.addChild(childLoop);
		compareLoopExp("IteratorExp", exp1, exp2, childLoop);
	}
	
	public void compare(LetExp exp1, LetExp exp2, StringNode node) throws CompareException {
		node.setText(node.getText() + " LetExp");
		if ((exp1 == null) && (exp2 == null)) return;
		
		compareByInstances(exp1, exp2, node);
		
		StringNode childIn = new StringInnerNode();
		childIn.setText("in:");
		node.addChild(childIn);
		compare(exp1.getIn(), exp2.getIn(), childIn);
		
		StringNode childVariable = new StringInnerNode();
		childVariable.setText("variable:");
		node.addChild(childVariable);
		compare(exp1.getVariable(), exp2.getVariable(), childVariable);
	}
	
	public void compare(LiteralExp exp1, LiteralExp exp2, StringNode node) throws CompareException {
		if ((exp1 == null) && (exp2 == null)) return;
		
		compareByInstances(exp1, exp2, node);
				
		if ((exp1 instanceof CollectionLiteralExp) && (exp2 instanceof CollectionLiteralExp)) {
			compare((CollectionLiteralExp) exp1, (CollectionLiteralExp) exp2, node);
			return;
		}
		
		if ((exp1 instanceof EnumLiteralExp) && (exp2 instanceof EnumLiteralExp)) {
			compare((EnumLiteralExp) exp1, (EnumLiteralExp) exp2, node);
			return;
		}
		
		if ((exp1 instanceof InvalidLiteralExp) && (exp2 instanceof InvalidLiteralExp)) {
			compare((InvalidLiteralExp) exp1, (InvalidLiteralExp) exp2, node);
			return;
		}
		
		if ((exp1 instanceof PrimitiveLiteralExp) && (exp2 instanceof PrimitiveLiteralExp)) {
			compare((PrimitiveLiteralExp) exp1, (PrimitiveLiteralExp) exp2, node);
			return;
		}
		
		if ((exp1 instanceof TupleLiteralExp) && (exp2 instanceof TupleLiteralExp)) {
			compare((TupleLiteralExp) exp1, (TupleLiteralExp) exp2, node);
			return;
		}
		
		if ((exp1 instanceof TypeLiteralExp) && (exp2 instanceof TypeLiteralExp)) {
			compare((TypeLiteralExp) exp1, (TypeLiteralExp) exp2, node);
			return;
		}
		
		if ((exp1 instanceof UndefinedLiteralExp) && (exp2 instanceof UndefinedLiteralExp)) {
			compare((UndefinedLiteralExp) exp1, (UndefinedLiteralExp) exp2, node);
			return;
		}
			
		String message = constructUnequalExpString(exp1, exp2);
		node.setError(true);
		node.setErrorType(ErrorFlag.DIFFERENTIATE_ERROR);
		throw new CompareException(message, node);
		
	}
	
	public void compare(LoopExp exp1, LoopExp exp2, StringNode node) throws CompareException {
		if ((exp1 == null) && (exp2 == null)) return;
		
		compareByInstances(exp1, exp2, node);
		
		
		if ((exp1 instanceof IterateExp) && (exp2 instanceof IterateExp)) {
			compare((IterateExp) exp1, (IterateExp) exp2, node);
			return;
		}
		
		if ((exp1 instanceof IteratorExp) && (exp2 instanceof IteratorExp)) {
			compare((IteratorExp) exp1, (IteratorExp) exp2, node);
			return;
		}
		
		String message = constructUnequalExpString(exp1, exp2);
		node.setError(true);
		node.setErrorType(ErrorFlag.DIFFERENTIATE_ERROR);
		throw new CompareException(message, node);
		
	}
	
	public void compare(NumericLiteralExp exp1, NumericLiteralExp exp2, StringNode node) throws CompareException {
		if ((exp1 == null) && (exp2 == null)) return;
		
		compareByInstances(exp1, exp2, node);
		
		if ((exp1 instanceof RealLiteralExp) && (exp2 instanceof RealLiteralExp)) {
			compare((RealLiteralExp) exp1, (RealLiteralExp) exp2, node);
			return;
		}
		
		if ((exp1 instanceof IntegerLiteralExp) && (exp2 instanceof IntegerLiteralExp)) {
			compare((IntegerLiteralExp) exp1, (IntegerLiteralExp) exp2, node);
			return;
		}
		
		if ((exp1 instanceof UnlimitedNaturalExp) && (exp2 instanceof UnlimitedNaturalExp)) {
			compare((UnlimitedNaturalExp) exp1, (UnlimitedNaturalExp) exp2, node);
			return;
		}
			
		String message = constructUnequalExpString(exp1, exp2);
		node.setError(true);
		node.setErrorType(ErrorFlag.DIFFERENTIATE_ERROR);
		throw new CompareException(message, node);
	
	}
	
	public void compare(OclExpression exp1, OclExpression exp2, StringNode node) throws CompareException {
		if ((exp1 == null) && (exp2 == null)) return;
		
		compareByInstances(exp1, exp2, node);
		
		if ((exp1 instanceof CallExp) && (exp2 instanceof CallExp)) {
			compare((CallExp) exp1, (CallExp) exp2, node);
			return;
		}
		
		if ((exp1 instanceof IfExp) && (exp2 instanceof IfExp)) {
			compare((IfExp) exp1, (IfExp) exp2, node);
			return;
		}
		
		if ((exp1 instanceof LetExp) && (exp2 instanceof LetExp)) {
			compare((LetExp) exp1, (LetExp) exp2, node);
			return;
		}
		
		if ((exp1 instanceof LiteralExp) && (exp2 instanceof LiteralExp)) {
			compare((LiteralExp) exp1, (LiteralExp) exp2, node);
			return;
		}
		
		if ((exp1 instanceof VariableExp) && (exp2 instanceof VariableExp)) {
			compare((VariableExp) exp1, (VariableExp) exp2, node);
			return;
		}
		
		String message = constructUnequalExpString(exp1, exp2);
		node.setError(true);
		node.setErrorType(ErrorFlag.DIFFERENTIATE_ERROR);
		throw new CompareException(message, node);
		
	}
	
	public void compare(OperationCallExp exp1, OperationCallExp exp2, StringNode node) throws CompareException {
		node.setText(node.getText() + " OperationCallExp");
		if ((exp1 == null) && (exp2 == null)) return;
		
		compareByInstances(exp1, exp2, node);
		
		StringNode childCall = new StringInnerNode();
		childCall.setText("call part:");
		node.addChild(childCall);
		compareCallExp(exp1, exp2, childCall);
		
		StringNode childReferredOperation = new StringInnerNode();
		childReferredOperation.setText("referred operation part:");
		node.addChild(childReferredOperation);
		compare(exp1.getReferredOperation(), exp2.getReferredOperation(), childReferredOperation);
		
		List<OclExpression> arguments1 = exp1.getArgument();
		List<OclExpression> arguments2 = exp2.getArgument();
		
		StringNode childArguments = new StringInnerNode();
		childArguments.setText("arguments");
		node.addChild(childArguments);
		
		compareOclExpressionList(arguments1, arguments2, childArguments);
	}
	
	public void compareOclExpressionList(List<OclExpression> list1, List<OclExpression> list2, StringNode node) throws CompareException {
		node.setText(node.getText() + " OclExpressionList");
		if ((list1 == null) && (list2 == null)) return;
		
		compareByInstances(list1, list2, node);
		
		String message = "The ocl expression lists are not equal.";
		
		if (list1.size() != list2.size()) {
			message = message + "\n The first list has the following number of elements: " + list1.size();
			message = message + "\n The second list has the following number of elements: " + list2.size();
			node.setError(true);
			node.setErrorType(ErrorFlag.DIFFERENTIATE_ERROR);
			throw new CompareException(message, node);
		}
		
		for (int i = 0; i < list1.size(); i++) {
			StringNode child = new StringInnerNode();
			child.setText("ocl expression:");
			node.addChild(child);
			compare(list1.get(i), list2.get(i), child);
		}
	}
	
	public void compare(PrimitiveLiteralExp exp1, PrimitiveLiteralExp exp2, StringNode node) throws CompareException {
		if ((exp1 == null) && (exp2 == null)) return;
		
		compareByInstances(exp1, exp2, node);
		
		if ((exp1 instanceof BooleanLiteralExp) && (exp2 instanceof BooleanLiteralExp)) {
			compare((BooleanLiteralExp) exp1, (BooleanLiteralExp) exp2, node);
			return;
		}
		
		if ((exp1 instanceof NumericLiteralExp) && (exp2 instanceof NumericLiteralExp)) {
			compare((NumericLiteralExp) exp1, (NumericLiteralExp) exp2, node);
			return;
		}
		
		if ((exp1 instanceof StringLiteralExp) && (exp2 instanceof StringLiteralExp)) {
			compare((StringLiteralExp) exp1, (StringLiteralExp) exp2, node);
			return;
		}
		
		String message = constructUnequalExpString(exp1, exp2);
		node.setError(true);
		node.setErrorType(ErrorFlag.DIFFERENTIATE_ERROR);
		throw new CompareException(message, node);
	}
	
	public void compare(PropertyCallExp exp1, PropertyCallExp exp2, StringNode node) throws CompareException {
		node.setText(node.getText() + " PropertyCallExp");
		if ((exp1 == null) && (exp2 == null)) return;
		
		compareByInstances(exp1, exp2, node);
		
		StringNode childCall = new StringInnerNode();
		childCall.setText("call part:");
		node.addChild(childCall);
		compareCallExp(exp1, exp2, childCall);
		
		
		StringNode childProperty = new StringInnerNode();
		childProperty.setText("referred property:");
		node.addChild(childProperty);
		
		compare(exp1.getReferredProperty(), exp2.getReferredProperty(), childProperty);
		
		List<OclExpression> qualifiers1 = exp1.getQualifier();
		List<OclExpression> qualifiers2 = exp2.getQualifier();
		
		StringNode childQualifiers = new StringInnerNode();
		childQualifiers.setText("qualifier:");
		node.addChild(childQualifiers);
		
		compareOclExpressionList(qualifiers1, qualifiers2, childQualifiers);
		
		String message = "The PropertyCallExp expressions are unequal.";
	}
	
	public void compare(RealLiteralExp exp1, RealLiteralExp exp2, StringNode node) throws CompareException {
		node.setText(node.getText() + " RealLiteralExp");
		if ((exp1 == null) && (exp2 == null)) return;
		
		compareByInstances(exp1, exp2, node);
		
		String message = "The RealLiteralExp expressions are not equal.";
		if (exp1.getRealSymbol() != exp2.getRealSymbol()) {
			message = message + "\n The value of the first expression is: " + exp1.getRealSymbol();
			message = message + "\n The value of the second expression is: " + exp2.getRealSymbol();
			node.setError(true);
			node.setErrorType(ErrorFlag.DIFFERENTIATE_ERROR);
			throw new CompareException(message, node);
		}
	}
	
	public void compare(StringLiteralExp exp1, StringLiteralExp exp2, StringNode node) throws CompareException {
		node.setText(node.getText() + " StringLiteralExp");
		if ((exp1 == null) && (exp2 == null)) return;
		
		compareByInstances(exp1, exp2, node);
		
		
		String message = "The StringLiteralExp expressions are not equal.";
		
		String stringSymbol1 = exp1.getStringSymbol();
		String stringSymbol2 = exp2.getStringSymbol();
		
		StringNode childSymbol = new StringLeaf();
		childSymbol.setText("symbol:");
		node.addChild(childSymbol);
		
		compare(stringSymbol1, stringSymbol2, childSymbol);
	}
	
	public void compare(TupleLiteralExp exp1, TupleLiteralExp exp2, StringNode node) throws CompareException {
		node.setText(node.getText() + " TupleLiteralExp");
		if ((exp1 == null) && (exp2 == null)) return;
		
		compareByInstances(exp1, exp2, node);
		
		List<TupleLiteralPart> part1 = exp1.getPart();
		List<TupleLiteralPart> part2 = exp2.getPart();
		
		StringNode childPart = new StringInnerNode();
		childPart.setText("part:");
		node.addChild(childPart);
		
		compareTupleLiteralPartList(part1, part2, childPart);
	}
	
	public void compareTupleLiteralPartList(List<TupleLiteralPart> list1, List<TupleLiteralPart> list2, StringNode node) throws CompareException {
		node.setText(node.getText() + " TupleLiteralPartList");
		if ((list1 == null) && (list2 == null)) return;
		
		compareByInstances(list1, list2, node);
		
		String message = "The lists of tuple literal parts are not equal.";
		if (list1.size() != list2.size()) {
			message = message + "\n The sizes of the lists are different.";
			message = message + "\n The size of the first list is: " + list1.size();
			message = message + "\n The size of the second list is: " + list2.size();
			node.setError(true);
			node.setErrorType(ErrorFlag.DIFFERENTIATE_ERROR);
			throw new CompareException(message, node);
		}
		
		for (int i = 0; i < list1.size(); i++) {
			StringNode child = new StringInnerNode();
			child.setText("literal part:");
			node.addChild(child);
			
			compare(list1.get(i), list2.get(i), child);
		}
	}
	
	public void compare(TupleLiteralPart exp1, TupleLiteralPart exp2, StringNode node) throws CompareException {
		node.setText(node.getText() + " TupleLiteralPart");
		if ((exp1 == null) && (exp2 == null)) return;
		
		compareByInstances(exp1, exp2, node);
		
		StringNode childValue = new StringInnerNode();
		childValue.setText("value:");
		node.addChild(childValue);
		
		compare(exp1.getValue(), exp2.getValue(), childValue);
		
		StringNode childProperty = new StringInnerNode();
		childProperty.setText("property:");
		node.addChild(childProperty);
		compare(exp1.getProperty(), exp2.getProperty(), childProperty);
	}
	
	public void compare(TypeLiteralExp exp1, TypeLiteralExp exp2, StringNode node) throws CompareException {
		node.setText(node.getText() + " TypeLiteralExp");
		if ((exp1 == null) && (exp2 == null)) return;
		
		compareByInstances(exp1, exp2, node);
		
		StringNode childReferredType = new StringInnerNode();
		childReferredType.setText("referred type:");
		node.addChild(childReferredType);
		compare(exp1.getReferredType(), exp2.getReferredType(), childReferredType);
	}
	
	public void compare(UndefinedLiteralExp exp1, UndefinedLiteralExp exp2, StringNode node) throws CompareException {
		node.setText(node.getText() + " UndefinedLiteralExp");
		compareByInstances(exp1, exp2, node);
		return;
	}
	
	public void compare(UnlimitedNaturalExp exp1, UnlimitedNaturalExp exp2, StringNode node) throws CompareException {
		node.setText(node.getText() + " UnlimitedNaturalExp");
		if ((exp1 == null) && (exp2 == null)) return;
		
		compareByInstances(exp1, exp2, node);
		
		String message = "The UnlimitedNaturalExp expressions are not equal.";
		if (exp1.getSymbol() != exp2.getSymbol()) {
			message = message + "\n The value of the first expression is: " + exp1.getSymbol();
			message = message + "\n The value of the second expression is: " + exp2.getSymbol();
			node.setError(true);
			node.setErrorType(ErrorFlag.DIFFERENTIATE_ERROR);
			throw new CompareException(message, node);
		}
	}
	
	public void compare(Variable exp1, Variable exp2, StringNode node) throws CompareException {
		node.setText(node.getText() + " Variable");
		if ((exp1 == null) && (exp2 == null)) return;
		
		compareByInstances(exp1, exp2, node);
		
		StringNode childInit = new StringInnerNode();
		childInit.setText("init expression:");
		node.addChild(childInit);
		
		compare(exp1.getInitExpression(), exp2.getInitExpression(), childInit);
		
		StringNode childParameter = new StringInnerNode();
		childParameter.setText("represented parameter:");
		node.addChild(childParameter);
		
		compare(exp1.getRepresentedParameter(), exp2.getRepresentedParameter(), childParameter);
		
		StringNode childType = new StringInnerNode();
		childType.setText("type:");
		node.addChild(childType);
		compare(exp1.getType(), exp2.getType(), childType);
		
		String variableName1 = exp1.getName();
		String variableName2 = exp2.getName();
		
		StringNode childName = new StringLeaf();
		childName.setText("name:");
		node.addChild(childName);
		
		compare(variableName1, variableName2, childName);
	}
	
	public void compare(VariableExp exp1, VariableExp exp2, StringNode node) throws CompareException {
		node.setText(node.getText() + " VariableExp");
		if ((exp1 == null) && (exp2 == null)) return;
		
		compareByInstances(exp1, exp2, node);
		
		StringNode childReferredVariable = new StringInnerNode();
		childReferredVariable.setText("referred variable:");
		node.addChild(childReferredVariable);
		compare(exp1.getReferredVariable(), exp2.getReferredVariable(), childReferredVariable);
	}
	
	public void compare(Property prop1, Property prop2, StringNode node) throws CompareException {
		node.setText(node.getText() + " Property");
		if ((prop1 == null) && (prop2 == null)) return;
		
		compareByInstances(prop1, prop2, node);
		
		StringNode childType =  new StringInnerNode();
		childType.setText("type:");
		node.addChild(childType);
		compare(prop1.getType(), prop2.getType(), childType);
				
		String propName1 = prop1.getName();
		String propName2 = prop2.getName();
		
		StringNode childName = new StringLeaf();
		childName.setText("name:");
		node.addChild(childName);
		
		compare(propName1, propName2, childName);
	}
	
	public void compare(Operation oper1, Operation oper2, StringNode node) throws CompareException {
		node.setText(node.getText() + " Operation");
		if ((oper1 == null) && (oper2 == null)) return;
		
		compareByInstances(oper1, oper2, node);
		
		Type type1 = oper1.getType();
		Type type2 = oper2.getType();
		
		StringNode childType = new StringInnerNode();
		childType.setText("type:");
		node.addChild(childType);
		compare(oper1.getType(), oper2.getType(), childType);
		
		
		String operName1 = oper1.getName();
		String operName2 = oper2.getName();
		
		StringNode childName = new StringLeaf();
		childName.setText("name:");
		node.addChild(childName);
		
		compare(operName1, operName2, childName);
		
			
		List<Parameter> sigParameter1 = oper1.getSignatureParameter();
		List<Parameter> sigParameter2 = oper2.getSignatureParameter();
		
		StringNode childParameter = new StringInnerNode();
		childParameter.setText("signature parameter:");
		node.addChild(childParameter);
		
		compareParameterList(sigParameter1, sigParameter2, childParameter);
		
			
		Parameter returnParameter1 = oper1.getReturnParameter();
		Parameter returnParameter2 = oper2.getReturnParameter();
		
		StringNode childReturnParameter = new StringInnerNode();
		childReturnParameter.setText("return parameter:");
		node.addChild(childReturnParameter);
		
		compare(returnParameter1, returnParameter2, childReturnParameter);
	}
	
	public void compareParameterList(List<Parameter> list1, List<Parameter> list2, StringNode node) throws CompareException {
		node.setText(node.getText() + " ParameterList");
		if ((list1 == null) && (list2 == null)) return;
		
		compareByInstances(list1, list2, node);
		
		String message = "The parameter lists are not equal.";
		
		if (list1.size() != list2.size()) {
			message = message + "\n The parameter lists have different sizes.";
			message = message + "\n The first list has the following number of elements: " + list1.size();
			message = message + "\n The second list has the following number of elements: " + list2.size();
			node.setError(true);
			node.setErrorType(ErrorFlag.DIFFERENTIATE_ERROR);
			throw new CompareException(message, node);
		}
		
		for (int i = 0; i < list1.size(); i++) {
			StringNode childParameter = new StringInnerNode();
			childParameter.setText("parameter:");
			node.addChild(childParameter);
			
			compare(list1.get(i), list2.get(i), childParameter);
		}
	}
	
	public void compare(Parameter param1, Parameter param2, StringNode node) throws CompareException {
		node.setText(node.getText() + " Parameter");
		if ((param1 == null) && (param2 == null)) return;
		
		compareByInstances(param1, param2, node);
		
		StringNode childType = new StringInnerNode();
		childType.setText("type");
		node.addChild(childType);
		compare(param1.getType(), param2.getType(), childType);
		
		
		String paramName1 = param1.getName();
		String paramName2 = param2.getName();
		
		StringNode childName = new StringLeaf();
		childName.setText("name:");
		node.addChild(childName);
		
		compare(paramName1, paramName2, childName);
		
		StringNode childKind = new StringLeaf();
		childKind.setText("kind:");
		node.addChild(childKind);
		
		String message = "The parameters are unequal.";
		if (param1.getKind() != param2.getKind()) {
			message = message + "\n The first parameter is of kind: " + param1.getKind().getName();
			message = message + "\n The second parameter is of kind: " + param2.getKind().getName();
			childKind.setError(true);
			childKind.setErrorType(ErrorFlag.DIFFERENTIATE_ERROR);
			throw new CompareException(message, childKind);
		}
	}
	
	public void compare(Constraint constraint1, Constraint constraint2, StringNode node) throws CompareException {
		node.setText(node.getText() + " Constraint");
		if ((constraint1 == null) && (constraint2 == null)) return;
		
		compareByInstances(constraint1, constraint2, node);
		
		String message = "The constraints are not equal.";
		
		StringNode childKind = new StringLeaf();
		childKind.setText("kind: ConstraintKind");
		node.addChild(childKind);
		
		if (constraint1.getKind() != constraint2.getKind()) {
			message = message + "\n The first constraint is of kind: " + constraint1.getKind().toString();
			message = message + "\n The second constraint is of kind: " + constraint2.getKind().toString();
			childKind.setError(true);
			childKind.setErrorType(ErrorFlag.DIFFERENTIATE_ERROR);
			throw  new CompareException(message, childKind);
		}
		
		StringNode childSpec = new StringInnerNode();
		childSpec.setText("specification:");
		node.addChild(childSpec);
		
		compare((ExpressionInOcl)constraint1.getSpecification(), (ExpressionInOcl)constraint2.getSpecification(), childSpec);
		
		
		List<ConstrainableElement> constElement1 = constraint1.getConstrainedElement();
		List<ConstrainableElement> constElement2 = constraint2.getConstrainedElement();
		
		StringNode childConstrainableList = new StringInnerNode();
		childConstrainableList.setText("list constrainable elements:");
		node.addChild(childConstrainableList);
		
		compareConstrainableElementList(constElement1, constElement2, childConstrainableList);
		
		
		StringNode childDefinedFeature = new StringInnerNode();
		childDefinedFeature.setText("defined feature:");
		node.addChild(childDefinedFeature);
		
				
		compare(constraint1.getDefinedFeature(), constraint2.getDefinedFeature(), childDefinedFeature);
		
		StringNode childName = new StringLeaf();
		childName.setText("name:");
		node.addChild(childName);
		
		compare(constraint1.getName(), constraint2.getName(), childName);
	}
	
	public void compareConstrainableElementList(List<ConstrainableElement> list1, List<ConstrainableElement> list2, StringNode node) throws CompareException {
		node.setText(node.getText() + " ConstrainableElementList");
		if ((list1 == null) && (list2 == null)) return;
		
		compareByInstances(list1, list2, node);
		
		String message = "The lists of constrainable elements are not equal.";
		if (list1.size() != list2.size()) {
			message = message + "\n The lists have different sizes.";
			message = message + "\n The first list has the following number of elements: " + list1.size();
			message = message + "\n The second list has the following number of elements: " + list2.size();
			node.setError(true);
			node.setErrorType(ErrorFlag.DIFFERENTIATE_ERROR);
			throw new CompareException(message, node);
		}
		
		for (int i = 0; i < list1.size(); i++) {
			StringNode child = new StringInnerNode();
			child.setText("element:");
			node.addChild(child);
			
			compare(list1.get(i), list2.get(i), child);
		}
	}
	
	public void compare(Feature feat1, Feature feat2, StringNode node) throws CompareException {
		if ((feat1 == null) && (feat2 == null)) return;
		
		compareByInstances(feat1, feat2, node);
		
		if ((feat1 instanceof Operation) && (feat2 instanceof Operation)) {
			compare((Operation) feat1, (Operation) feat2, node);
			return;
		}
		
		if ((feat1 instanceof Property) && (feat2 instanceof Property)) {
			compare((Property) feat1, (Property) feat2, node);
			return;
		}
		
		if ((feat1 instanceof Type) && (feat2 instanceof Type)) {
			compare((Type) feat1, (Type) feat2, node);
			return;
		}
	
		String message = constructUnequalExpString(feat1, feat2);
		node.setError(true);
		node.setErrorType(ErrorFlag.DIFFERENTIATE_ERROR);
		throw new CompareException(message, node);
		
	}
	
	public void compare(ConstrainableElement elem1, ConstrainableElement elem2, StringNode node) throws CompareException {
		if ((elem1 == null) && (elem2 == null)) return;
		
		compareByInstances(elem1, elem2, node);
		
		if ((elem1 instanceof Type) && (elem2 instanceof Type)) {
			compare((Type) elem1, (Type) elem2, node);
			return;
		}
		
		if ((elem1 instanceof Operation) && (elem2 instanceof Operation)) {
			compare((Operation) elem1, (Operation) elem2, node);
			return;
		}
		
		if ((elem1 instanceof Property) && (elem2 instanceof Property)) {
			compare((Property) elem1, (Property) elem2, node);
			return;
		}
	
		String message = constructUnequalExpString(elem1, elem2);
		node.setError(true);
		node.setErrorType(ErrorFlag.DIFFERENTIATE_ERROR);
		throw new CompareException(message, node);
		
	}
	
	public void compare(Expression exp1, Expression exp2, StringNode node) throws CompareException {
		if ((exp1 == null) && (exp2 == null)) return;
		
		compareByInstances(exp1, exp2, node);
		
		if ((exp1 instanceof ExpressionInOcl) && (exp2 instanceof ExpressionInOcl)) {
			compare((ExpressionInOcl) exp1, (ExpressionInOcl) exp2, node);
			return;
		}
	
		String message = constructUnequalExpString(exp1, exp2);
		node.setError(true);
		node.setErrorType(ErrorFlag.DIFFERENTIATE_ERROR);
		throw new CompareException(message, node);
		
	}
	
	public void compare(EnumerationLiteral literal1, EnumerationLiteral literal2, StringNode node) throws CompareException {
		node.setText(node.getText() + " EnumerationLiteral");
		if ((literal1 == null) && (literal2 == null)) return;
		
		compareByInstances(literal1, literal2, node);
				
		String literalName1 = literal1.getName();
		String literalName2 = literal2.getName();
		
		StringNode childName = new StringLeaf();
		childName.setText("name:");
		node.addChild(childName);
		
		compare(literalName1, literalName2, childName);
	}
	
	public void compare(Type type1, Type type2, StringNode node) throws CompareException {
		node.setText(node.getText() + " Type");
		if ((type1 == null) && (type2 == null)) return;
		
		compareByInstances(type1, type2, node);
		
		String message = "The types are not equal.";
		if (!(type1.conformsTo(type2) && type2.conformsTo(type1))) {
			message = message + "\n The name of the first type is: " + type1.getName();
			message = message + "\n The name of the second type is: " + type2.getName();
			node.setError(true);
			node.setErrorType(ErrorFlag.DIFFERENTIATE_ERROR);
			throw new CompareException(message, node);
		}
	}
	
	public void compare(Namespace nm1, Namespace nm2, StringNode node) throws CompareException {
		//node.setText(node.getText() + " Namespace");
		if ((nm1 == null) && (nm2 == null)) return;
		
		compareByInstances(nm1, nm2, node);
		
		boolean result = compareNamespace(nm1, nm2, node);
		if (result == false) {
			String message = "The namespaces are not equal.";
			message = message + "\n The first namespace has the name: " + nm1.getName();
			message = message + "\n The second namespace has the name: " + nm2.getName();
			node.setError(true);
			node.setErrorType(ErrorFlag.DIFFERENTIATE_ERROR);
			throw new CompareException(message, node);
		}
	}
	
	private boolean compareNamespace(Namespace nm1, Namespace nm2, StringNode node) throws CompareException {
		node.setText(node.getText() + " Namespace");
		if ((nm1 == null) && (nm2 == null)) return true;
		
		compareByInstances(nm1, nm2, node);
		
		if (!(nm1.getName().equals(nm2.getName()))) return false;
		
		String message = "The namespaces are not equal.";
		
		List<Constraint> constraintList1 = nm1.getOwnedRule();
		List<Constraint> constraintList2 = nm2.getOwnedRule();
		
		StringNode childList = new StringInnerNode();
		childList.setText("constraint list:");
		node.addChild(childList);
		
		compareConstraintList(constraintList1, constraintList2, childList);
		
		if (((nm1.getNestedNamespace() == null) || (nm1.getNestedNamespace().size() == 0)) && 
				((nm2.getNestedNamespace() == null) || (nm2.getNestedNamespace().size() == 0))) return true;
		
		/*
		 * At this point we compare the namespaces with each other. If
		 * a comparison is successful we count that, otherwise not.
		 * At the end of this comparison we compare the success counter
		 * with the number of nested namespaces. If these numbers are equal
		 * we return true, otherwise false.
		 * To make this construction clear, we make an example: Suppose we have
		 * to namespaces: A->B->C, A->B->D and A->B->D, A->B->C (this notation
		 * mean the following: A->B->C stand for single path in the namespace.
		 * The construction A->B->C, A->B->D mean, that the C namespace come first and
		 * the namespace D last. In the second namespace construct it is vice versa. Note
		 * that in the implementation that is expressed with lists. So in the first case
		 * the C-namespace come first and then the D-namespace.). To compare these two
		 * namespaces we go through each combination, that mean (C,D), (C,C), (D,C) and
		 * (D,D) (where the first namespace is from the first model and the second namespace
		 * from the other). In the cases 2 and 4 we have match. So we count these matches, if
		 * the comparison is successful (it haven't to be, because the constraints can be
		 * different). So we assure that, when the namespaces are correct, we return true.
		 */
		
		int numberSuccesses = 0;
		for(Namespace cNamespace1 : nm1.getNestedNamespace()) {
			for(Namespace cNamespace2 : nm2.getNestedNamespace()) {
				/*
				 * We must track the path through the expression tree.
				 * So we create an inner string node and add this
				 * new node to the parent node. But the namespace names
				 * must not be equal because of the comparison of the
				 * namespaces with each other. So we add a node to the
				 * parent that is nonsense. To overcome this, we delete this
				 * last node if the compare method returns null.
				 * If an error occurred while going through the expression tree
				 * the whole tree is preserved.
				 */
				StringNode child = new StringInnerNode();
				child.setText("namespace:");
				node.addChild(child);
				boolean result = compareNamespace(cNamespace1, cNamespace2, child);
				if (result == true) {
					numberSuccesses++;
				} else {
					node.deleteLastNode();
				}
			}
		}
		
		if (numberSuccesses == nm1.getNestedNamespace().size()) return true;
		
		return false;
	}
	
	public void compareConstraintList(List<Constraint> list1, List<Constraint> list2, StringNode node) throws CompareException {
		node.setText(node.getText() + " ConstraintList");
		if ((list1 == null) && (list2 == null)) return;
		
		compareByInstances(list1, list2, node);
		
		String message = "The constraint lists are different.";
		if (list1.size() != list2.size()) {
			message = message + "\n The constraint lists have different sizes.";
			message = message + "\n The first list has the following number of elements: " + list1.size();
			message = message + "\n The second list has the following number of elements: " + list2.size();
			node.setError(true);
			node.setErrorType(ErrorFlag.DIFFERENTIATE_ERROR);
			throw new CompareException(message, node);
		}
		
		for (int i = 0; i < list1.size(); i++) {
			StringNode child = new StringInnerNode();
			child.setText("constraint:");
			node.addChild(child);
			compare(list1.get(i), list2.get(i), child);
		}
	}
	
	public void compare(String name1, String name2, StringNode node) throws CompareException {
		node.setText(node.getText() + " String");
		if ((name1 == null) && (name2 == null)) return;
		
		compareByInstances(name1, name2, node);
		
		if (!(name1.equals(name2))) {
			String message = "The strings are different.";
			message = message + "\n The first string is: " + name1;
			message = message + "\n The second string is: " + name2;
			node.setError(true);
			node.setErrorType(ErrorFlag.DIFFERENTIATE_ERROR);
			throw new CompareException(message, node);
		}
		
	}
	
	/*public void compareFeature(String elementName, Feature feat1, Feature feat2) throws CompareException {
		if ((feat1 == null) && (feat2 == null)) return;
		String message = "The " + elementName + " elements are unequal.";
		if (feat1.isStatic() != feat2.isStatic()) {
			message = message + "\n The first " + elementName + " has the static value: " + feat1.isStatic();
			message = message + "\n The second " + elementName + " has the static value: " + feat2.isStatic();
			throw new CompareException(message);
		}
	}*/
	
	/*public void compareType(String elementName, Type type1, Type type2) throws CompareException {
		if ((type1 == null) && (type2 == null)) return;
		String message = "The types of " + elementName + " are not equal.";
		if (!(type1.conformsTo(type2) && type2.conformsTo(type1))) {
			message = message + "\n The name of the first type is: " + type1.getName();
			message = message + "\n The name of the second type is: " + type2.getName();
			throw new CompareException(message);
		}
	}*/
	
	/*public void compareMultiplicityElement(String elementName, MultiplicityElement elem1, MultiplicityElement elem2) throws CompareException {
		if ((elem1 == null) && (elem2 == null)) return;
		String message = "The " + elementName + " elements are not equal.";
		if (elem1.isOrdered() != elem2.isOrdered()) {
			message = message + "\n The first element has the ordered attribute: " + elem1.isOrdered();
			message = message + "\n The second element has the ordered attribute: " + elem2.isOrdered();
			throw new CompareException(message);
		}
		
		if (elem1.isUnique() != elem2.isUnique()) {
			message = message + "\n The first element has the unique attribute: " + elem1.isOrdered();
			message = message + "\n The second element has the unique attribute: " + elem2.isOrdered();
			throw new CompareException(message);
		}
		
		if (elem1.isMultiple() != elem2.isMultiple()) {
			message = message + "\n The first element has the multiple attribute: " + elem1.isOrdered();
			message = message + "\n The second element has the multiple attribute: " + elem2.isOrdered();
			throw new CompareException(message);
		}
	}*/
	
	public void compareLoopExp(String expressionName, LoopExp exp1, LoopExp exp2, StringNode node) throws CompareException {
		node.setText(node.getText() + " LoopExp");
		if ((exp1 == null) && (exp2 == null)) return;
		
		compareByInstances(exp1, exp2, node);
		
		StringNode childBody = new StringInnerNode();
		childBody.setText("body:");
		node.addChild(childBody);
		
		compare(exp1.getBody(), exp2.getBody(), childBody);
		
		String message = "The "+ expressionName + " expressions are unequal.";
		
		List<Variable> iterVariables1 = exp1.getIterator();
		List<Variable> iterVariables2 = exp2.getIterator();
		
		StringNode childVariables = new StringInnerNode();
		childVariables.setText("iterate variables:");
		node.addChild(childVariables);
		
		compareVariableList(iterVariables1, iterVariables2, childVariables);
	}
		
	public void compareCallExp(CallExp exp1, CallExp exp2, StringNode node) throws CompareException {
		node.setText(node.getText() + " CallExp");
		if ((exp1 == null) && (exp2 == null)) return;
		
		compareByInstances(exp1, exp2, node);
		
		StringNode childSource = new StringInnerNode();
		childSource.setText("source:");
		node.addChild(childSource);
		
		compare(exp1.getSource(), exp2.getSource(), childSource);
	}
	
	/**
	 * This method is used to construct an error message. The method
	 * get two objects. The method extract the class names of these objects
	 * and build a message.
	 * @param exp1 the first object to build a message from
	 * @param exp2 the second object to build a message from
	 * @return the error message
	 */
	private String constructUnequalExpString(Object exp1, Object exp2) {
		String firstClassName = null;
		String secondClassName = null;
		
		if (exp1 != null) firstClassName = exp1.getClass().getName();
		if (exp2 != null) secondClassName = exp2.getClass().getName();
		
		String message = "The expressions are unequal.";
		message = message + "\n The first expression is of type: " + firstClassName;
		message = message + "\n The second expression is of type: " + secondClassName;
		return message;
	}
	
	/**
	 * This method takes two objects and examines whether both objects are instances.
	 * If exactly of the object is null, false will be returned, otherwise true. This
	 * means implicitly that if the instances null, it will be returned true.
	 * @param exp1 first object
	 * @param exp2 second object
	 * @return true if both objects are instances, otherwise false
	 */
	/*private boolean areInstances(Object exp1, Object exp2) {
		if ( ( (exp1 != null) && (exp2 == null) ) || ( (exp1 == null) && (exp2 != null) ) ) return false;
		
		return true;
	}*/
	
	/**
	 * This method takes two object instances and compares them with the null-value.
	 * If exactly one of the instances is null, the method will throw a {@link CompareException},
	 * otherwise nothing will happen. This implies that if the both instances are null
	 * nothing will happen.
	 * @param obj1 the first instance
	 * @param obj2 the second instance
	 * @throws CompareException is thrown if exactly one the instances is null
	 */
	private void compareByInstances(Object obj1, Object obj2, StringNode node) throws CompareException {
		String message = "Exactly one of the instances is null. So the expressions can't be equal.";
		
		if ( ( (obj1 != null) && (obj2 == null) ) || ( (obj1 == null) && (obj2 != null) ) ) {
			message = message + "\n The name of the first instance is: " + obj1;
			message = message + "\n The name of the second instance is: " + obj2;
			node.setError(true);
			node.setErrorType(ErrorFlag.DIFFERENTIATE_ERROR);
			throw new CompareException(message, node);
		}
	}
}
