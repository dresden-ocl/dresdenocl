/*
Copyright (C) 2002  Sten Loecher

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

*/
package tudresden.ocl.codegen.decl.treegen;

import java.util.*;
import tudresden.ocl.codegen.decl.*;
import tudresden.ocl.codegen.decl.treegen.node.*;
import tudresden.ocl.codegen.decl.treegen.analysis.*;

/**
	This class creates a SQL abstract syntax tree. The usual 
	order of methode calls is:
	<ol>
		<li>create new TreeBuilder object</li>
		<li>reset()</li>
		<li>setArgument(...) as many times as necessary</li>
		<li>subtreeFor(...)</li>
		<li>getSQLTree()</li>		
	</ol>
	Steps two to four will be iterated as many times as necessary to finish the tree.
	
	@author Sten Loecher
 */
public class TreeBuilder extends DepthFirstAdapter {
	
	/** a map to hold arguments which will be used to create the tree*/
	private Map arguments;
	/** the SQL tree */
	private Node theSQLTree;
	/** helper node */
	private Node origin;
	/** helper variable */
	private int task;
	
	public TreeBuilder() {
		arguments = new HashMap();
		theSQLTree = null;
	}
	
	/**
		This methode resets the TreeBuilder to zero arguments.
	 */
	public void reset() {
		arguments.clear();
	}
	
	/**
		@param name the name of the argument
		@param arg the value of the argument
	 */
	public void setArgument(String name, Object arg) {
		arguments.put(name, arg);
	}
	
	/**
		@return the generated SQL tree.
	 */
	public Node getSQLTree() {
		return theSQLTree;
	}
	
	// ---------------------------------- helper methodes ----------------------------------
	private Object getArgument(String name) {
		Object result = arguments.get(name);
		if (result == null) throw new RuntimeException("Missing argument: " + name);
		return result;
	}
	
	private void putTask(Integer task, Node node) {
		node.setTaskNumber(task.intValue());		
	}
	
	private void replaceTask(Integer task, Node node) {
		origin = node;
		this.task = task.intValue();
		theSQLTree.apply(this);
		origin = null;
		this.task = -1;
	}
	
	public void defaultIn(Node node) {		
		if (node.getTaskNumber() == task) {
			Node theClone = (Node)origin.clone();			
			insertSubtree(node, theClone);			
		}
    }
    
    private AQuerySpecQueryExpression getSimpleQuery(String column, String colName, String table, String alias, AWhereClause wc) {
		TIdentifier idAlias1 = null, idAlias2 = null, idColAlias = null;
		if (alias != null) {
			idAlias1 = new TIdentifier(alias);
			idAlias2 = new TIdentifier(alias);
		}
		
		if (colName != null) {
			idColAlias = new TIdentifier(colName);
		}
		
		List subList = new ArrayList();
		subList.add(new  AColumnSelectSubListItem(new AColumn(idAlias1, new TIdentifier(column), idColAlias)));
		
		List tabRef = new ArrayList();
		tabRef.add(new ATableNameTableReference(new TIdentifier(table), idAlias2));		
		
		return new AQuerySpecQueryExpression(
			new ASelectClause(null, new ASubListSelectList(subList)),
			new AFromClause(tabRef),
			wc
		);		
	}

	private AQuerySpecQueryExpression getSimpleQuery(String column, String table, String alias, AWhereClause wc) {
		TIdentifier idAlias1 = null, idAlias2 = null;
		if (alias != null) {
			idAlias1 = new TIdentifier(alias);
			idAlias2 = new TIdentifier(alias);
		}
		
		List subList = new ArrayList();
		subList.add(new  AColumnSelectSubListItem(new AColumn(idAlias1, new TIdentifier(column), null)));
		
		List tabRef = new ArrayList();
		tabRef.add(new ATableNameTableReference(new TIdentifier(table), idAlias2));		
		
		return new AQuerySpecQueryExpression(
			new ASelectClause(null, new ASubListSelectList(subList)),
			new AFromClause(tabRef),
			wc
		);		
	}
	
	private AQuerySpecQueryExpression getSimpleQuery(String column, AFromClause fc, AWhereClause wc) {
		List subList = new ArrayList();
		subList.add(new  AColumnSelectSubListItem(new AColumn(null, new TIdentifier(column), null)));
		
		return new AQuerySpecQueryExpression(
			new ASelectClause(null, new ASubListSelectList(subList)),
			fc,
			wc
		);
	}
	
	private AQuerySpecQueryExpression getSimpleQuery(ASelectClause sc, AFromClause fc, AWhereClause wc) {
		return new AQuerySpecQueryExpression(
			sc,
			fc,
			wc
		);
	}

	/**
		The purpose of this methode is to bridge gaps between certain empty and concrete nodes.
	 */
	private void insertSubtree(Node  insert, Node subtree) {
		if ((insert instanceof AEmptyTableReference) && (subtree instanceof PQueryExpression)) {
			insert.replaceBy(new ADerivedTableTableReference((PQueryExpression)subtree, null));
		} else if ((insert instanceof AEmptyQueryExpression) && (subtree instanceof AQuerySpecQueryExpression)) {
			insert.replaceBy(subtree);
		} else if ((insert instanceof AEmptyBooleanExpression) && (subtree instanceof PRelationalExpression)) {
			insert.replaceBy(new ARExpBooleanExpression((PRelationalExpression)subtree));
		} else if ((insert instanceof AEmptyBooleanExpression) && (subtree instanceof PUnaryExpression)) {
			insert.replaceBy(new ARExpBooleanExpression(new ANumExpRelationalExpression(new AValueNumericExpression((PUnaryExpression)subtree))));
		} else if ((insert instanceof AEmptyNumericExpression) && (subtree instanceof PUnaryExpression)) {
			insert.replaceBy(new AValueNumericExpression((PUnaryExpression)subtree));
		} else if ((insert instanceof AEmptyNumericExpression) && (subtree instanceof PQueryExpression)) {
			insert.replaceBy(new AValueNumericExpression(new AQueryUnaryExpression((PQueryExpression)subtree)));
		} else if ((insert instanceof AEmptyBooleanExpression) && (subtree instanceof PBooleanExpression)) {
			insert.replaceBy((PBooleanExpression)subtree);
		} else if ((insert instanceof AEmptyQueryExpression) && (subtree instanceof PQueryExpression)) {
			insert.replaceBy((PQueryExpression)subtree);
		} else if ((insert instanceof AEmptyUnaryExpression) && (subtree instanceof PQueryExpression)) {
			insert.replaceBy(new AQueryUnaryExpression((PQueryExpression)subtree));
		} else if ((insert instanceof AEmptyUnaryExpression) && (subtree instanceof PUnaryExpression)) {
			insert.replaceBy((PUnaryExpression)subtree);
		} else if ((insert instanceof AEmptyUnaryExpression) && (subtree instanceof PNumericExpression)) {
			insert.replaceBy(new ABooleanUnaryExpression(new ARExpBooleanExpression(new ANumExpRelationalExpression((PNumericExpression)subtree))));
		} else if ((insert instanceof AEmptyUnaryExpression) && (subtree instanceof PRelationalExpression)) {
			insert.replaceBy(new ABooleanUnaryExpression(new ARExpBooleanExpression((PRelationalExpression)subtree)));
		} else if ((insert instanceof AEmptyUnaryExpression) && (subtree instanceof PBooleanExpression)) {
			insert.replaceBy(new ABooleanUnaryExpression((PBooleanExpression)subtree));
		} else if ((insert instanceof AEmptyNumericExpression) && (subtree instanceof PBooleanExpression)) {	
			insert.replaceBy(new AValueNumericExpression(new ABooleanUnaryExpression((PBooleanExpression)subtree)));
		} else if ((insert instanceof AEmptyNumericExpression) && (subtree instanceof PNumericExpression)) {
			insert.replaceBy((PNumericExpression)subtree);
		} else if ((insert instanceof AEmptyBooleanExpression) && (subtree instanceof PNumericExpression)) {
			insert.replaceBy(new ARExpBooleanExpression(new ANumExpRelationalExpression((PNumericExpression)subtree)));
		} else if ((insert instanceof AEmptyBooleanExpression) && (subtree instanceof PQueryExpression)) {
			insert.replaceBy(new ARExpBooleanExpression(new ANumExpRelationalExpression(new AValueNumericExpression((PUnaryExpression)subtree))));
		} else if ((insert instanceof AEmptyNumericExpression) && (subtree instanceof PRelationalExpression)) {
			insert.replaceBy(new ABooleanUnaryExpression(new ARExpBooleanExpression((PRelationalExpression)subtree)));
		} else if ((insert instanceof AEmptyNumericExpression) && (subtree instanceof PNumericExpression)) {
			insert.replaceBy((PNumericExpression)subtree);
		} else  {
			throw new RuntimeException("Unhandled case for insertSubtree (" + insert.getClass().getName() + ", " + subtree.getClass().getName() + ")!");
		} 
	}
	
	public void throwUnhandledRuleException(String rule, String spec) {
		throw new RuntimeException("No handling for spec " + spec + " in rule " + rule + " in methode subtreeFor !");
	}
	
	/**
	 *	@param rule the name of the OCL grammar rule
	 *	@param spec a clarification parameter
	 *	@param task the task to replace with the subtree
	 */
	public void subtreeFor(String rule, String spec, Integer task) {
		PBooleanExpression pbe = null, pbe1 = null, pbe2 = null, pbe3 = null, pbe4 = null;
		PQueryExpression pqe, pqe1, pqe2 = null;
		PNumericExpression pne = null, pne1 = null, pne2 = null, pne3 = null, pne4 = null;
		PRelationalExpression pre = null;
		PTableReference ptr;
		PUnaryExpression pue, pue1, pue2, pue3;
		PSelectList psl;
		ASelectClause sc;
		AWhereClause wc, wc1;
		AFromClause fc;
		Node n = null, n1 = null, n2 = null;
		List list, list1, list2;
		String opName, str, coll1, coll2;
		int i1, i2;
		
		//System.err.println("subtree for rule " + rule + " on spec " + spec);
			
		if (rule.equals("inAConstraintBody")) {
			// create tree
			wc = new AWhereClause(new ANotBooleanExpression(pbe = new AEmptyBooleanExpression()));
			theSQLTree = new Start(getSimpleQuery((String)getArgument("object"), (String)getArgument("context_table"), (String)getArgument("context_alias"), wc), new EOF());	
			
			// place tasks
			putTask((Integer)getArgument("expression"), pbe);
			
		} else if (rule.equals("inAParenthesesPrimaryExpression")) {
			// create tree
			pue = new AParUnaryExpression(pue1 = new AEmptyUnaryExpression());
						
			// place tasks
			putTask((Integer)getArgument("expression"), pue1);
			
			// insert subtree into tree
			replaceTask(task, pue);
			
		} else if (rule.equals("inALogicalExpressionTail")) {
			// create subtree
			if (spec.equals("and")) {
				list = new ArrayList();
				list.add(pbe1 = new AEmptyBooleanExpression());
				list.add(pbe2 = new AEmptyBooleanExpression());
				pbe = new AAndBooleanExpression(list);
			} else if (spec.equals("or")) {
				list = new ArrayList();
				list.add(pbe1 = new AEmptyBooleanExpression());
				list.add(pbe2 = new AEmptyBooleanExpression());
				pbe = new AOrBooleanExpression(list);
			} else if (spec.equals("xor")) {
				list1 = new ArrayList();
				list1.add(pbe1 = new AEmptyBooleanExpression());
				list1.add(pbe2 = new AEmptyBooleanExpression());
				pbe3 = new AOrBooleanExpression(list1);
				
				putTask((Integer)getArgument("rel_exp_1"), pbe1);
				putTask((Integer)getArgument("rel_exp_2"), pbe2);
				
				list2 = new ArrayList();
				list2.add(pbe1 = new AEmptyBooleanExpression());
				list2.add(pbe2 = new AEmptyBooleanExpression());
				pbe4 = new ANotBooleanExpression(new AAndBooleanExpression(list2));
				
				list = new ArrayList();
				list.add(pbe3);			
				list.add(pbe4);
				pbe = new AAndBooleanExpression(list);				
			} else if (spec.equals("implies")) {
				list = new ArrayList();
				list.add(new ANotBooleanExpression(pbe1 = new AEmptyBooleanExpression()));
				list.add(pbe2 = new AEmptyBooleanExpression());
				pbe = new AOrBooleanExpression(list);
			} else {
				throwUnhandledRuleException(rule, spec);
			}
						
			// attach new tasks to nodes
			putTask((Integer)getArgument("rel_exp_1"), pbe1);
			putTask((Integer)getArgument("rel_exp_2"), pbe2);
			
			// insert subtree into tree
			replaceTask(task, pbe);

		} else if (rule.equals("inARelationalExpressionTail")) {
			// create subtree
			if (spec.equals("gt")) {
				pre = new AGtRelationalExpression(pne = new AEmptyNumericExpression(), pne1 = new AEmptyNumericExpression());
			} else if (spec.equals("lt")) {
				pre = new ALtRelationalExpression(pne = new AEmptyNumericExpression(), pne1 = new AEmptyNumericExpression());
			} else if (spec.equals("gteq")) {
				pre = new AGteqRelationalExpression(pne = new AEmptyNumericExpression(), pne1 = new AEmptyNumericExpression());
			} else if (spec.equals("lteq")) {
				pre = new ALteqRelationalExpression(pne = new AEmptyNumericExpression(), pne1 = new AEmptyNumericExpression());
			} else if (spec.equals("eq_IntRealString")) {
				pre = new AEqRelationalExpression(pne = new AEmptyNumericExpression(), pne1 = new AEmptyNumericExpression());
			} else if (spec.equals("eq_Boolean")) {
				list = new ArrayList();
				list.add(pbe1 = new AEmptyBooleanExpression());
				list.add(pbe2 = new AEmptyBooleanExpression());
				pbe3 = new AAndBooleanExpression(list);
				
				putTask((Integer)getArgument("add_exp_1"), pbe1);
				putTask((Integer)getArgument("add_exp_2"), pbe2);
				
				list = new ArrayList();
				list.add(new ANotBooleanExpression(pbe1 = new AEmptyBooleanExpression()));
				list.add(new ANotBooleanExpression(pbe2 = new AEmptyBooleanExpression()));
				pbe4 = new AAndBooleanExpression(list);
				
				putTask((Integer)getArgument("add_exp_1"), pbe1);
				putTask((Integer)getArgument("add_exp_2"), pbe2);
				
				list = new ArrayList();
				list.add(pbe3);			
				list.add(pbe4);
				pbe = new AOrBooleanExpression(list);				
			} else if (spec.equals("eq_Any")) {
				pre = new AEqRelationalExpression(pne = new AEmptyNumericExpression(), pne1 = new AEmptyNumericExpression());
			} else if (spec.equals("neq")) {
				pre = new ANeqRelationalExpression(pne = new AEmptyNumericExpression(), pne1 = new AEmptyNumericExpression());
			} else {
				throwUnhandledRuleException(rule, spec);
			}
			
			
			if (!(spec.equals("eq_Boolean"))) {
				// attach new tasks to nodes
				putTask((Integer)getArgument("add_exp_1"), pne);
				putTask((Integer)getArgument("add_exp_2"), pne1);
				
				// insert subtree into tree
				replaceTask(task, pre);
			} else {
				// insert subtree into tree
				replaceTask(task, pbe);
			}		
					
		} else if (rule.equals("inAAdditiveExpressionTail")) {
			// create subtree
			if (spec.equals("plus")) {
				pne = new APlusNumericExpression(
					pne1 = new AEmptyNumericExpression(),
					pne2 = new AEmptyNumericExpression()	
				);
			} else if (spec.equals("minus")) {
				pne = new AMinusNumericExpression(
					pne1 = new AEmptyNumericExpression(),
					pne2 = new AEmptyNumericExpression()	
				);
			} else {
				throwUnhandledRuleException(rule, spec);
			}
			
			// attach new tasks to nodes
			putTask((Integer)getArgument("mult_exp_1"), pne1);
			putTask((Integer)getArgument("mult_exp_2"), pne2);
						
			// insert subtree into tree
			replaceTask(task, pne);
			
		} else if (rule.equals("inAMultiplicativeExpressionTail")) {
			// create subtree
			if (spec.equals("mult")) {
				pne = new AMultNumericExpression(
					pne1 = new AEmptyNumericExpression(),
					pne2 = new AEmptyNumericExpression()	
				);
			} else if (spec.equals("div")) {
				pne = new ADivNumericExpression(
					pne1 = new AEmptyNumericExpression(),
					pne2 = new AEmptyNumericExpression()	
				);
			} else {
				throwUnhandledRuleException(rule, spec);
			}
			
			// attach new tasks to nodes
			putTask((Integer)getArgument("un_exp_1"), pne1);
			putTask((Integer)getArgument("un_exp_2"), pne2);
						
			// insert subtree into tree
			replaceTask(task, pne);
			
		} else if (rule.equals("inAUnaryUnaryExpression")) {
			// create subtree
			if (spec.equals("minus")) {
				n = new AMinusUnaryExpression((PUnaryExpression)(n1 = new AEmptyUnaryExpression()));
			} else if (spec.equals("not")) {
				n = new ANotBooleanExpression((PBooleanExpression)(n1 = new AEmptyBooleanExpression())); 
			} else {
				throwUnhandledRuleException(rule, spec);
			}
			
			// attach new tasks to nodes
			putTask((Integer)getArgument("un_exp"), n1);
						
			// insert subtree into tree
			replaceTask(task, n);
			
		} else if (rule.equals("inAFeaturePrimaryExpression")) {
			
		} else if (rule.equals("inAFeatureCall")) {
			if (spec.equals("navigation")) {
				// create subtree
				pue = new AColumnUnaryExpression(new AColumn(null, new TIdentifier((String)getArgument("link_object")), null));
				pqe = new AEmptyQueryExpression();
				pbe = new AInBooleanExpression(pue, pqe);
				wc = new AWhereClause(pbe);
				pqe1 = getSimpleQuery((String)getArgument("object"), (String)getArgument("table"), null, wc);
				
				// attach new tasks to nodes
				putTask((Integer)getArgument("collection"), pqe);	
				
				// insert subtree into tree
				replaceTask(task, pqe1);				
				
			} else if (spec.equals("navigation_context")) {
				// create subtree
				wc = new AWhereClause(new ARExpBooleanExpression(new AEqRelationalExpression(
					new AValueNumericExpression(new AColumnUnaryExpression(new AColumn(null, new TIdentifier((String)getArgument("link_object")), null))), 
					new AValueNumericExpression(new AColumnUnaryExpression(new AColumn(new TIdentifier((String)getArgument("context")), new TIdentifier((String)getArgument("link_object")), null)))
				)));
				pqe1 = getSimpleQuery((String)getArgument("object"), (String)getArgument("table"), null, wc);
				
				// insert subtree into tree
				replaceTask(task, pqe1);
					
			} else if (spec.equals("attribute_context")) {
				// create subtree
				pue = new AColumnUnaryExpression(new AColumn(new TIdentifier((String)getArgument("context")), new TIdentifier((String)getArgument("object")), null));
				
				// insert subtree into tree
				replaceTask(task, pue);	
			
			} else if (spec.equals("operation")) {
				opName = (String)getArgument("operation");
				
				if ((opName.indexOf("nullPString") != -1) ||
				    (opName.indexOf("nullPDate") != -1)   ||
				    (opName.indexOf("nullPInt") != -1)    ||
				    (opName.indexOf("nullPFloat") != -1)) {
					// create subtree
					pbe = new ANullBooleanExpression(pue = new AEmptyUnaryExpression());
					
					// attach new tasks to nodes
					putTask((Integer)getArgument("parameter0"), pue);
					
					// insert subtree into tree
					replaceTask(task, pbe);						
					
				} else if (opName.indexOf("toInt") != -1) {
					// create subtree
					list = new ArrayList();
					list.add(pue1 = new AEmptyUnaryExpression());
					pue = new AFunctionUnaryExpression(new TIdentifier("TO_NUMBER"), list);
					
					// attach new tasks to nodes
					putTask((Integer)getArgument("parameter0"), pue1);
					
					// insert subtree into tree
					replaceTask(task, pue);
					
				} else if (opName.indexOf("intToStr") != -1) {
					// create subtree
					list = new ArrayList();
					list.add(pue1 = new AEmptyUnaryExpression());
					pue = new AFunctionUnaryExpression(new TIdentifier("TO_CHAR"), list);
					
					// attach new tasks to nodes
					putTask((Integer)getArgument("parameter0"), pue1);
					
					// insert subtree into tree
					replaceTask(task, pue);
					
				} else if (opName.indexOf("strToInt") != -1) {
					// create subtree
					list = new ArrayList();
					list.add(pue1 = new AEmptyUnaryExpression());
					pue = new AFunctionUnaryExpression(new TIdentifier("TO_NUMBER"), list);
					
					// attach new tasks to nodes
					putTask((Integer)getArgument("parameter0"), pue1);
					
					// insert subtree into tree
					replaceTask(task, pue);

				} else if (opName.indexOf("dateToStrFmt") != -1) {
					// create subtree
					list = new ArrayList();
					list.add(pue1 = new AEmptyUnaryExpression());
					list.add(pue2 = new AEmptyUnaryExpression());
					pue = new AFunctionUnaryExpression(new TIdentifier("TO_CHAR"), list);
					
					// attach new tasks to nodes
					putTask((Integer)getArgument("parameter0"), pue1);
					putTask((Integer)getArgument("parameter1"), pue2);
					
					// insert subtree into tree
					replaceTask(task, pue);
										
				} else if (opName.indexOf("dateToStr") != -1) {
					// create subtree
					list = new ArrayList();
					list.add(pue1 = new AEmptyUnaryExpression());
					pue = new AFunctionUnaryExpression(new TIdentifier("TO_CHAR"), list);
					
					// attach new tasks to nodes
					putTask((Integer)getArgument("parameter0"), pue1);
					
					// insert subtree into tree
					replaceTask(task, pue);

				} else if (opName.indexOf("strToDateFmt") != -1) {
					// create subtree
					list = new ArrayList();
					list.add(pue1 = new AEmptyUnaryExpression());
					list.add(pue2 = new AEmptyUnaryExpression());
					pue = new AFunctionUnaryExpression(new TIdentifier("TO_DATE"), list);
					
					// attach new tasks to nodes
					putTask((Integer)getArgument("parameter0"), pue1);
					putTask((Integer)getArgument("parameter1"), pue2);
					
					// insert subtree into tree
					replaceTask(task, pue);
										
				} else if (opName.indexOf("strToDate") != -1) {
					// create subtree
					list = new ArrayList();
					list.add(pue1 = new AEmptyUnaryExpression());
					pue = new AFunctionUnaryExpression(new TIdentifier("TO_DATE"), list);
					
					// attach new tasks to nodes
					putTask((Integer)getArgument("parameter0"), pue1);
					
					// insert subtree into tree
					replaceTask(task, pue);
					
				} else if (opName.indexOf("sysdate") != -1) {
					// create subtree
					list = new ArrayList();
					pue = new AFunctionUnaryExpression(new TIdentifier("SYSDATE"), list);
										
					// insert subtree into tree
					replaceTask(task, pue);
					
				} else if (opName.indexOf("isGreaterEqual") != -1) {
					// create subtree
					pre = new AGteqRelationalExpression(
						pne1 = new AEmptyNumericExpression(),
						pne2 = new AEmptyNumericExpression()
					);
					
					// attach new tasks to nodes
					putTask((Integer)getArgument("object"), pne1);
					putTask((Integer)getArgument("parameter0"), pne2);
					
					// insert subtree into tree
					replaceTask(task, pre);
										
				} else if (opName.indexOf("isGreater") != -1) {
					// create subtree
					pre = new AGtRelationalExpression(
						pne1 = new AEmptyNumericExpression(),
						pne2 = new AEmptyNumericExpression()
					);
					
					// attach new tasks to nodes
					putTask((Integer)getArgument("object"), pne1);
					putTask((Integer)getArgument("parameter0"), pne2);
					
					// insert subtree into tree
					replaceTask(task, pre);
					
				} else if (opName.indexOf("isEqual") != -1) {
					// create subtree
					pre = new AEqRelationalExpression(
						pne1 = new AEmptyNumericExpression(),
						pne2 = new AEmptyNumericExpression()
					);
					
					// attach new tasks to nodes
					putTask((Integer)getArgument("object"), pne1);
					putTask((Integer)getArgument("parameter0"), pne2);
					
					// insert subtree into tree
					replaceTask(task, pre);
					
				} else if (opName.indexOf("isLessEqual") != -1) {
					// create subtree
					pre = new ALteqRelationalExpression(
						pne1 = new AEmptyNumericExpression(),
						pne2 = new AEmptyNumericExpression()
					);
					
					// attach new tasks to nodes
					putTask((Integer)getArgument("object"), pne1);
					putTask((Integer)getArgument("parameter0"), pne2);
					
					// insert subtree into tree
					replaceTask(task, pre);
					
				} else if (opName.indexOf("isLess") != -1) {
					// create subtree
					pre = new ALtRelationalExpression(
						pne1 = new AEmptyNumericExpression(),
						pne2 = new AEmptyNumericExpression()
					);
					
					// attach new tasks to nodes
					putTask((Integer)getArgument("object"), pne1);
					putTask((Integer)getArgument("parameter0"), pne2);
					
					// insert subtree into tree
					replaceTask(task, pre);
					
				} else if (opName.indexOf("addMonth") != -1) {
					// create subtree
					list = new ArrayList();
					list.add(pue1 = new AEmptyUnaryExpression());
					list.add(pue2 = new AEmptyUnaryExpression());
					pue = new AFunctionUnaryExpression(new TIdentifier("ADD_MONTHS"), list);
					
					// attach new tasks to nodes
					putTask((Integer)getArgument("object"), pue1);
					putTask((Integer)getArgument("parameter0"), pue2);
					
					// insert subtree into tree
					replaceTask(task, pue);
					
				} else if (opName.indexOf("monthBetween") != -1) {
					// create subtree
					list = new ArrayList();
					list.add(pue1 = new AEmptyUnaryExpression());
					list.add(pue2 = new AEmptyUnaryExpression());
					pue = new AFunctionUnaryExpression(new TIdentifier("MONTHS_BETWEEN"), list);
					
					// attach new tasks to nodes
					putTask((Integer)getArgument("object"), pue1);
					putTask((Integer)getArgument("parameter0"), pue2);
					
					// insert subtree into tree
					replaceTask(task, pue);
					
				} else if (opName.indexOf("lastDay") != -1) {
					// create subtree
					list = new ArrayList();
					list.add(pue1 = new AEmptyUnaryExpression());
					pue = new AFunctionUnaryExpression(new TIdentifier("LAST_DAY"), list);
					
					// attach new tasks to nodes
					putTask((Integer)getArgument("object"), pue1);
										
					// insert subtree into tree
					replaceTask(task, pue);
					
				} else if (opName.indexOf("nextDay") != -1) {
					// create subtree
					list = new ArrayList();
					list.add(pue1 = new AEmptyUnaryExpression());
					list.add(pue2 = new AEmptyUnaryExpression());
					pue = new AFunctionUnaryExpression(new TIdentifier("NEXT_DAY"), list);
					
					// attach new tasks to nodes
					putTask((Integer)getArgument("object"), pue1);
					putTask((Integer)getArgument("parameter0"), pue2);
					
					// insert subtree into tree
					replaceTask(task, pue);
					
				} else if (opName.indexOf("roundFmt") != -1) {
					// create subtree
					list = new ArrayList();
					list.add(pue1 = new AEmptyUnaryExpression());
					list.add(pue2 = new AEmptyUnaryExpression());
					pue = new AFunctionUnaryExpression(new TIdentifier("ROUND"), list);
					
					// attach new tasks to nodes
					putTask((Integer)getArgument("object"), pue1);
					putTask((Integer)getArgument("parameter0"), pue2);
					
					// insert subtree into tree
					replaceTask(task, pue);
					
				} else if (opName.indexOf("truncFmt") != -1) {
					// create subtree
					list = new ArrayList();
					list.add(pue1 = new AEmptyUnaryExpression());
					list.add(pue2 = new AEmptyUnaryExpression());
					pue = new AFunctionUnaryExpression(new TIdentifier("TRUNC"), list);
					
					// attach new tasks to nodes
					putTask((Integer)getArgument("object"), pue1);
					putTask((Integer)getArgument("parameter0"), pue2);
					
					// insert subtree into tree
					replaceTask(task, pue);
					
				} else {
					throwUnhandledRuleException(rule, spec);
				}
			
			} else if (spec.equals("String_size")) {
				// create subtree
				list = new ArrayList();
				list.add(pue1 = new AEmptyUnaryExpression());
				pue = new AFunctionUnaryExpression(new TIdentifier("LENGTH"), list);
				
				// attach new tasks to nodes
				putTask((Integer)getArgument("object"), pue1);
				
				// insert subtree into tree
				replaceTask(task, pue);				
								
			} else if (spec.equals("String_substring")) {
				// create subtree
				list = new ArrayList();
				list.add(pue1 = new AEmptyUnaryExpression());
				list.add(pue2 = new AEmptyUnaryExpression());
				list.add(pue3 = new AEmptyUnaryExpression());
				pue = new AFunctionUnaryExpression(new TIdentifier("SUBSTR"), list);
				
				// attach new tasks to nodes
				putTask((Integer)getArgument("object"), pue1);
				putTask((Integer)getArgument("parameter0"), pue2);
				putTask((Integer)getArgument("parameter1"), pue3);
				
				// insert subtree into tree
				replaceTask(task, pue);					
			
			} else if (spec.equals("String_concat")) {
				list = new ArrayList();
				list.add(pue1 = new AEmptyUnaryExpression());
				list.add(pue2 = new AEmptyUnaryExpression());
				pue = new AFunctionUnaryExpression(new TIdentifier("CONCAT"), list);
				
				// attach new tasks to nodes
				putTask((Integer)getArgument("object"), pue1);
				putTask((Integer)getArgument("parameter0"), pue2);
				
				// insert subtree into tree
				replaceTask(task, pue);						
				
			} else if (spec.equals("String_toUpper")) {
				list = new ArrayList();
				list.add(pue1 = new AEmptyUnaryExpression());
				pue = new AFunctionUnaryExpression(new TIdentifier("UPPER"), list);
				
				// attach new tasks to nodes
				putTask((Integer)getArgument("object"), pue1);
								
				// insert subtree into tree
				replaceTask(task, pue);	
				
			} else if (spec.equals("String_toLower")) {
				list = new ArrayList();
				list.add(pue1 = new AEmptyUnaryExpression());
				pue = new AFunctionUnaryExpression(new TIdentifier("LOWER"), list);
				
				// attach new tasks to nodes
				putTask((Integer)getArgument("object"), pue1);
								
				// insert subtree into tree
				replaceTask(task, pue);
				
			} else if ((spec.equals("Integer_abs")) || (spec.equals("Real_abs"))) {
				list = new ArrayList();
				list.add(pue1 = new AEmptyUnaryExpression());
				pue = new AFunctionUnaryExpression(new TIdentifier("ABS"), list);
				
				// attach new tasks to nodes
				putTask((Integer)getArgument("object"), pue1);
								
				// insert subtree into tree
				replaceTask(task, pue);
				
			} else if (spec.equals("Integer_div")) {
				list = new ArrayList();
				list.add(
					new ABooleanUnaryExpression(
					new ARExpBooleanExpression(
					new ANumExpRelationalExpression(
					new ADivNumericExpression(
						pne1 = new AEmptyNumericExpression(),
						pne2 = new AEmptyNumericExpression()		
				)))));
				pne = new AValueNumericExpression(new AFunctionUnaryExpression(new TIdentifier("FLOOR"), list));
				
				putTask((Integer)getArgument("object"), pne1);
				putTask((Integer)getArgument("parameter0"), pne2);
				
				// insert subtree into tree
				replaceTask(task, pne);
				
			} else if (spec.equals("Integer_mod")) {
				list = new ArrayList();
				list.add(
					new ABooleanUnaryExpression(
					new ARExpBooleanExpression(
					new ANumExpRelationalExpression(
					new ADivNumericExpression(
						pne1 = new AEmptyNumericExpression(),
						pne2 = new AEmptyNumericExpression()		
				)))));
				pne = new AValueNumericExpression(new AFunctionUnaryExpression(new TIdentifier("FLOOR"), list));
				
				putTask((Integer)getArgument("object"), pne1);
				putTask((Integer)getArgument("parameter0"), pne2);
				
				pne = new AMultNumericExpression(pne2 = new AEmptyNumericExpression(), pne);
				
				putTask((Integer)getArgument("parameter0"), pne2);
				
				pne = new AMinusNumericExpression(pne1 = new AEmptyNumericExpression(), pne);
				putTask((Integer)getArgument("object"), pne1);
				
				pue = new AParUnaryExpression(new ABooleanUnaryExpression(new ARExpBooleanExpression(new ANumExpRelationalExpression(pne))));
				
				// insert subtree into tree
				replaceTask(task, pue);			
				
			} else if ((spec.equals("Integer_max")) || (spec.equals("Real_max"))) {
				list = new ArrayList();
				list.add(pue1 = new AEmptyUnaryExpression());
				list.add(pue2 = new AEmptyUnaryExpression());
				pue = new AFunctionUnaryExpression(new TIdentifier("GREATEST"), list);
				
				// attach new tasks to nodes
				putTask((Integer)getArgument("object"), pue1);
				putTask((Integer)getArgument("parameter0"), pue2);
				
				// insert subtree into tree
				replaceTask(task, pue);	
				
			} else if ((spec.equals("Integer_min")) || (spec.equals("Real_min"))) {
				list = new ArrayList();
				list.add(pue1 = new AEmptyUnaryExpression());
				list.add(pue2 = new AEmptyUnaryExpression());
				pue = new AFunctionUnaryExpression(new TIdentifier("LEAST"), list);
				
				// attach new tasks to nodes
				putTask((Integer)getArgument("object"), pue1);
				putTask((Integer)getArgument("parameter0"), pue2);
				
				// insert subtree into tree
				replaceTask(task, pue);	
				
			} else if (spec.equals("Real_floor")) {
				list = new ArrayList();
				list.add(pue1 = new AEmptyUnaryExpression());
				pue = new AFunctionUnaryExpression(new TIdentifier("FLOOR"), list);
				
				// attach new tasks to nodes
				putTask((Integer)getArgument("object"), pue1);
								
				// insert subtree into tree
				replaceTask(task, pue);
				
			} else if (spec.equals("Real_round")) {
				list = new ArrayList();
				list.add(pue1 = new AEmptyUnaryExpression());
				pue = new AFunctionUnaryExpression(new TIdentifier("ROUND"), list);
				
				// attach new tasks to nodes
				putTask((Integer)getArgument("object"), pue1);
								
				// insert subtree into tree
				replaceTask(task, pue);
												
			} else if (spec.equals("allInstances")) {
				// create subtree
				pqe = getSimpleQuery((String)getArgument("object"), (String)getArgument("base_table"), null, null);
				
				// insert subtree into tree
				replaceTask(task, pqe);	
				
			} else if ((spec.equals("forAll")) || (spec.equals("exists")))  {
				// create subtree
				if (spec.equals("forAll")) 
					wc1 = new AWhereClause(new ANotBooleanExpression(pbe2 = new AEmptyBooleanExpression()));
				else
					wc1 = new AWhereClause(pbe2 = new AEmptyBooleanExpression());
				pqe = getSimpleQuery((String)getArgument("object"), (String)getArgument("base_table"), (String)getArgument("base_table_alias"), wc1);
				
				pue = new AColumnUnaryExpression(new AColumn(null, new TIdentifier((String)getArgument("object")), null));
				wc = new AWhereClause(new AInBooleanExpression(pue, pqe));
				list = new ArrayList();
				list.add(ptr = new AEmptyTableReference());
				fc = new AFromClause(list);
				pqe1 = getSimpleQuery((String)getArgument("object"), fc, wc);
				
				if (spec.equals("forAll")) 
					pbe = new ANotBooleanExpression(new AExistsBooleanExpression(pqe1));
				else
					pbe = new AExistsBooleanExpression(pqe1);	
				
				// attach new tasks to nodes
				putTask((Integer)getArgument("collection"), ptr);
				putTask((Integer)getArgument("expression"), pbe2);
				
				// insert subtree into tree
				replaceTask(task, pbe);
			
			} else if ((spec.equals("isEmpty")) || (spec.equals("notEmpty"))) {
				pqe = new AEmptyQueryExpression();
				
				if (spec.equals("isEmpty"))
					pbe = new ANotBooleanExpression(new AExistsBooleanExpression(pqe));
				else 
					pbe = new AExistsBooleanExpression(pqe);
				
				// attach new tasks to nodes
				putTask((Integer)getArgument("collection"), pqe);
								
				// insert subtree into tree
				replaceTask(task, pbe);		
								
			} else if ((spec.equals("includes")) || (spec.equals("excludes"))) {
				// create subtree
				pbe = new AInBooleanExpression(pue = new AEmptyUnaryExpression(), pqe = new AEmptyQueryExpression());
				if (spec.equals("excludes")) pbe = new ANotBooleanExpression(pbe);
				
				// attach new tasks to nodes
				putTask((Integer)getArgument("object"), pue);
				putTask((Integer)getArgument("collection"), pqe);
				
				// insert subtree into tree
				replaceTask(task, pbe);
				
			} else if ((spec.equals("includesAll")) || (spec.equals("excludesAll"))) {
				// create subtree
				pqe1 = new AEmptyQueryExpression();
				pqe2 = new AEmptyQueryExpression();
				
				if (spec.equals("includesAll")) {
					pqe = new AExceptQueryExpression(null, pqe2, pqe1);
				} else {
					list = new ArrayList();
					list.add(pqe2);
					list.add(pqe1);
					pqe = new AIntersectQueryExpression(null, list);					
				}
				
				pbe = new ANotBooleanExpression(new AExistsBooleanExpression(pqe));
				
				// attach new tasks to nodes
				putTask((Integer)getArgument("collection"), pqe1);
				putTask((Integer)getArgument("collection2"), pqe2);
				
				// insert subtree into tree
				replaceTask(task, pbe);
							
			} else if ((spec.equals("select")) || (spec.equals("reject"))) {
				// create subtree
				if (spec.equals("reject")) {
					pbe3 = new ANotBooleanExpression(pbe2 = new AEmptyBooleanExpression());
				} else {
					pbe3 = pbe2 = new AEmptyBooleanExpression();
				}
					
				wc1 = new AWhereClause(pbe3);
				pqe = getSimpleQuery((String)getArgument("object"), (String)getArgument("base_table"), (String)getArgument("base_table_alias"), wc1);
				
				pue = new AColumnUnaryExpression(new AColumn(null, new TIdentifier((String)getArgument("object")), null));
				wc = new AWhereClause(new AInBooleanExpression(pue, pqe));
				list = new ArrayList();
				list.add(ptr = new AEmptyTableReference());
				fc = new AFromClause(list);
				pqe1 = getSimpleQuery((String)getArgument("object"), fc, wc);
										
				// attach new tasks to nodes
				putTask((Integer)getArgument("collection"), ptr);
				putTask((Integer)getArgument("expression"), pbe2);
				
				// insert subtree into tree
				replaceTask(task, pqe1);				
			
			} else if (spec.equals("union")) {
				// create subtree
				list = new ArrayList();
				list.add(pqe1 = new AEmptyQueryExpression());
				list.add(pqe2 = new AEmptyQueryExpression());
				
				coll1 = (String)getArgument("collType");
				coll2 = (String)getArgument("collType2");
				
				if (coll1.equals("SET")) {
					if (coll2.equals("SET")) {
						n = null;
					} else {
						n = new TAll();
					}
				} else if (coll1.equals("BAG")) {
					n = new TAll();
				} else {
					throw new RuntimeException("No support for sequences yet !");
				}
				
				pqe = new AUnionQueryExpression((TAll)n, list);
				
				// attach new tasks to nodes
				putTask((Integer)getArgument("collection"), pqe1);
				putTask((Integer)getArgument("collection2"), pqe2);
				
				// insert subtree into tree
				replaceTask(task, pqe);
			
			} else if (spec.equals("intersection")) {
				// create subtree
				list = new ArrayList();
				list.add(pqe1 = new AEmptyQueryExpression());
				list.add(pqe2 = new AEmptyQueryExpression());
				pqe = new AIntersectQueryExpression(null, list);
				
				// attach new tasks to nodes
				putTask((Integer)getArgument("collection"), pqe1);
				putTask((Integer)getArgument("collection2"), pqe2);
				
				// insert subtree into tree
				replaceTask(task, pqe);
			
			} else if (spec.equals("including")) {
				// create subtree
				list = new ArrayList();
				list.add(pqe1 = new AEmptyQueryExpression());
				list.add(pqe2 = new AEmptyQueryExpression());
				
				coll1 = (String)getArgument("collType");
				
				if (coll1.equals("SET")) {
					n = null;
				} else if (coll1.equals("BAG")) {
					n = new TAll();
				} else {
					throw new RuntimeException("No support for sequences yet !");
				}
							
				pqe = new AUnionQueryExpression((TAll)n, list);
								
				// attach new tasks to nodes
				putTask((Integer)getArgument("collection"), pqe1);
				putTask((Integer)getArgument("object"), pqe2);
				
				// insert subtree into tree
				replaceTask(task, pqe);
				
			} else if (spec.equals("excluding")) {	
				// create subtree
				pqe1 = new AEmptyQueryExpression();
				pqe2 = new AEmptyQueryExpression();
										
				pqe = new AExceptQueryExpression(null, pqe1, pqe2);
								
				// attach new tasks to nodes
				putTask((Integer)getArgument("collection"), pqe1);
				putTask((Integer)getArgument("object"), pqe2);
				
				// insert subtree into tree
				replaceTask(task, pqe);
			
			} else if (spec.equals("symmetricDifference")) {
				// create subtree
				list = new ArrayList();
				
				pqe1 = new AEmptyQueryExpression();
				pqe2 = new AEmptyQueryExpression();
				list.add(new AExceptQueryExpression(null, pqe1, pqe2));
				putTask((Integer)getArgument("collection"), pqe1);
				putTask((Integer)getArgument("collection2"), pqe2);
				
				pqe1 = new AEmptyQueryExpression();
				pqe2 = new AEmptyQueryExpression();
				list.add(new AExceptQueryExpression(null, pqe2, pqe1));
				putTask((Integer)getArgument("collection"), pqe1);
				putTask((Integer)getArgument("collection2"), pqe2);
				
				pqe = new AUnionQueryExpression(null, list);
							
				// insert subtree into tree
				replaceTask(task, pqe);
			
			} else if (spec.equals("size")) {
				// create subtree
				sc = new ASelectClause(null, new AFunctionSelectList(new TIdentifier("count"), new AAsteriskSelectList()));
				
				list = new ArrayList();
				list.add(ptr = new AEmptyTableReference());
				fc = new AFromClause(list);
							
				pqe = getSimpleQuery(sc, fc, null);
				
				// attach new tasks to nodes
				putTask((Integer)getArgument("collection"), ptr);
				
				// insert subtree into tree
				replaceTask(task, pqe);
				
			} else if (spec.equals("sum")) {
				// create subtree
				list = new ArrayList();
				list.add(new AColumnSelectSubListItem(new AColumn(null, new TIdentifier((String)getArgument("object")), null)));
				sc = new ASelectClause(null, new AFunctionSelectList(new TIdentifier("sum"), new ASubListSelectList(list)));
				
				list = new ArrayList();
				list.add(ptr = new AEmptyTableReference());
				fc = new AFromClause(list);
							
				pqe = getSimpleQuery(sc, fc, null);
				
				// attach new tasks to nodes
				putTask((Integer)getArgument("collection"), ptr);
				
				// insert subtree into tree
				replaceTask(task, pqe);
			
			} else if (spec.equals("count")) {
				// create subtree
				sc = new ASelectClause(null, new AFunctionSelectList(new TIdentifier("count"), new AAsteriskSelectList()));
				
				list = new ArrayList();
				list.add(ptr = new AEmptyTableReference());
				fc = new AFromClause(list);
				
				wc = new AWhereClause(new ARExpBooleanExpression(new AEqRelationalExpression(
					pne1 = new AValueNumericExpression(new AColumnUnaryExpression(new AColumn(null, new TIdentifier((String)getArgument("element")), null))),
					pne2 = new AEmptyNumericExpression()
				)));
				
				pqe = getSimpleQuery(sc, fc, wc);
				
				// attach new tasks to nodes
				putTask((Integer)getArgument("collection"), ptr);				
				putTask((Integer)getArgument("object"), pne2);
				
				// insert subtree into tree
				replaceTask(task, pqe);
				
			} else {
				throwUnhandledRuleException(rule, spec);
			}
	
		} else if (rule.equals("inALiteralPrimaryExpression")) {
			if ((spec.equals("AIntegerLiteral")) || (spec.equals("ARealLiteral"))) {
				// create subtree
				pue = new ANumericUnaryExpression(new TNumericValue((String)getArgument("value")));
								
				// insert subtree into tree
				replaceTask(task, pue);
			} else if ((spec.equals("AStringLiteral")) || (spec.equals("AEnumLiteral"))) {
				// create subtree
				pue = new AStringUnaryExpression(new TIdentifier((String)getArgument("value")));
								
				// insert subtree into tree
				replaceTask(task, pue);
			} else if (spec.equals("ABooleanLiteral")) {
				// create subtree
				if (((String)getArgument("value")).equals("true")) {
					pbe = new ATrueBooleanExpression();
				} else {
					pbe = new AFalseBooleanExpression();
				}
								
				// insert subtree into tree
				replaceTask(task, pbe);
			} else {
				throwUnhandledRuleException(rule, spec);
			}
			
		} else if (rule.equals("inALiteralCollection")) {
			if ((spec.equals("bag")) || (spec.equals("sequence"))) {
				throw new RuntimeException("No support for Bag and Sequence in ALiteralCollection !");
			}
			// create subtree
			int numElem = ((Integer)getArgument("no_elements")).intValue();
			list = new ArrayList();
			
			for (int i=0; i<numElem; i++) {
				list.add(getSimpleQuery((String)getArgument("value"+i), SQLCodeGenerator.STANDARDKEY, "DUAL", null, null));												
			}
			
			if (list.size() > 1) {
				pqe = new AUnionQueryExpression(null, list);
			} else {
				pqe = (PQueryExpression)list.get(0);
			}
								
			// insert subtree into tree
			replaceTask(task, pqe);										
			
		} else if (rule.equals("inAParenthesesPrimaryExpression")) {
			
		} else {
			throwUnhandledRuleException(rule, spec);
		}						
	}
}