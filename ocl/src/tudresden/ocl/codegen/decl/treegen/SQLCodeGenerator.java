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

import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Iterator;

import java.lang.reflect.Method;
import tudresden.ocl.OclTree;
import tudresden.ocl.parser.node.*;
import tudresden.ocl.parser.analysis.*;
import tudresden.ocl.codegen.*;
import tudresden.ocl.codegen.decl.*;
import tudresden.ocl.check.types.*;
import tudresden.ocl.codegen.decl.treegen.normalize.*;

/**
	This SQL code generator is a prototype to demonstrate the generation
	of SQL code out of OCL constraints. The generator first creates an
	abstract SQL tree, then applies normalization steps on it and finally
	uses a printer class to get plain SQL code.<br>
	This prototype translates only a subset of the OCL language to SQL. 
	Especially the following items are not yet supported:<br>
	<ul>
		<li>multi column primary keys of class tables</li>
		<li>multiple class tables for one class (use object views instead)</li>
		<li>operations that produce or are applied on sequences</li>
		<li>limited support for operations on bags</li>
		<li>if-then-else-expressions</li>
		<li>iterate operation</li>
		<li>operations regarding the MODEL TYPE pattern of the reworked UML'99 catalogue</li>
		<li>enumerations</li>
	</ul>
	
	@author Sten Loecher
*/
public class SQLCodeGenerator extends ReversedDepthFirstAdapter implements CodeGenerator {
	
	private static String TOK_ALIAS  = "TAB";
	private static String TOK_DCOLON = "::";
	private static String oclCollectionOperations = "isUnique;forAll;exists;isEmpty; notEmpty;includesAll;excludesAll;includes;excludes;size;count;sum;select;reject;union;intersection;including;excluding;symmetricDifference;allInstances";
	private static String oclAllTokens = oclCollectionOperations;
	protected static String STANDARDKEY   = "elem";
    protected static String STANDARDTABLE = "DUAL";
	
	/** a List of SQL tree objects */
	private List sqlTrees;
	/** the OCL abstract syntax tree */
	private OclTree theTree;
	/** counter for tasks which will be assigned to SQL tree nodes*/
	private int taskCounter;
	/** counter for tables alias*/
	private int aliasCounter;
	/** variable to hold the generated SQL tree after the generation process */
	private tudresden.ocl.codegen.decl.treegen.node.Node sqlTree;
	/** the name of the constrained type */
	private String constrainedType;
	/** the name of the constraint */
	private String constraintName;
	/** the names of primary key columns of the context table */
	private String[] contextTablePks;
	/** the object relational mapping component */
	private ORMappingScheme theMap = null;
	/** the actual SQL tree builder which creates the SQL tree*/
	private TreeBuilder theTreeBuilder;
	/** the printer to get SQL code from the SQL tree */
	private Printer thePrinter;
	/** flag to switch normalization on/off */
	private boolean normalize = true;
	/** 
		maps nodes of type PPostfixExpressionTail to Guide objects 
		@see tudresden.ocl.codegen.decl.Guide
	*/
	private Map guides;	
	private Set navigations, oclOperation;
	private Map operations, basicTypeSucc, collect, collTypes;
		
	public SQLCodeGenerator() {
		theTreeBuilder = new TreeBuilder();
		thePrinter = new Oracle8iPrinter();
	}				
	
	/**
		Implementation of the CodeGenerator interface.
		@param tree a OCL abstract syntax tree
		@see tudresden.ocl.codegen.CodeGenerator
	 */
	public CodeFragment[] getCode(OclTree tree) {		
		tudresden.ocl.codegen.decl.treegen.node.Node sqlTree;
		List cf;
		SQLCodeFragment sqlcf;
		String code;
		TableNameFinder tnf;
		
		// generate SQL trees
		getSQLTree(tree);
		
		// normalize
		for (Iterator i=sqlTrees.iterator(); i.hasNext() && normalize; ) {
			sqlTree = (tudresden.ocl.codegen.decl.treegen.node.Node)i.next();
			// get correlation variables outside of from clauses
			sqlTree.apply(new SelectElimination());
			// eliminate redundant navigation steps
			sqlTree.apply(new RedNavElimination());
			// eliminate redundant context navigation
			sqlTree.apply(new ContNavElimination());
			// optimize AInBooleanExpression
			sqlTree.apply(new WhereSelectElimination());
		}
		
		// get SQL code and put it into code fragments
		cf = new ArrayList();
		tnf = new TableNameFinder();
		for (Iterator i=sqlTrees.iterator(); i.hasNext(); ) {
			sqlTree = (tudresden.ocl.codegen.decl.treegen.node.Node)i.next();
			sqlTree.apply(thePrinter);
			code = thePrinter.getCode();
			sqlTree.apply(tnf);			
			sqlcf = new SQLCodeFragment(constraintName, constrainedType, code, tnf.getInvolvedTables(), contextTablePks);
			cf.add(sqlcf);
		}
		
		// deliver result
		return (CodeFragment[])cf.toArray(new CodeFragment[cf.size()]);
	}
	
	/**
		This methode has to be called before getCode.
		@param map a map containing object relational mapping information
		@see tudresden.ocl.codegen.decl.ORMappingScheme
	 */
	public void setORMappingScheme(ORMappingScheme map) 
	throws NullPointerException {
		if (map == null) throw new NullPointerException("Expected ORMappingScheme but found null !");
		theMap = map;
	}
	
	/**
		The default printer is Oracle8iPrinter.
		@param p a printer
		@exception NullPointerException if p is null
	 */
	public void setPrinter(Printer p) 
	throws NullPointerException {
		if (p == null) throw new NullPointerException("Expected Printer but found null !");
		thePrinter = p;
	}
	
	/** 
		The standard mode for normalization is true.
		@param b true if the code generator should normalize, false otherwise
	 */
	public void setNormalization(boolean b) {
		normalize = b;
	}
	
	/**
		This methode can be called instead of getCode. It is provided public for debugging 
		purposes only. In case there are multiple SQL trees, it returns only the last
		generated one. All generated trees are gathered in sqlTrees.
		@return the last generated SQL tree from the OCL tree 
		@param tree a OCL abstract syntax tree
	 */
	public tudresden.ocl.codegen.decl.treegen.node.Node getSQLTree(OclTree tree) {
		if (theMap == null) throw new RuntimeException("Missing object relational map !");
		theTree = tree;
		theTree.apply(this);
		return theTreeBuilder.getSQLTree();
	}
	
	/**
		This methode is provided for debugging purposes only. One can try to get 
		the last generated SQL tree even if there were exceptions thrown during the generation
		process. Be aware that the result can therefore be also null. 		
		@return the SQL tree generated from the OCL tree  
	 */
	public tudresden.ocl.codegen.decl.treegen.node.Node getUnfinishedTree() {
		return theTreeBuilder.getSQLTree();
	}
	
	
	
	
	// ------------------------ methodes for the basic strategy ------------------------
	/**
		Reset the code generator. Must be called before each new run. 
	 */
	private void reset() {
		taskCounter = 0;
		aliasCounter = 0;
		guides = new HashMap();
		navigations = new HashSet();
		oclOperation = new HashSet(); 
		operations = new HashMap();
		basicTypeSucc = new HashMap(); 
		collect = new HashMap(); 
		collTypes = new HashMap(); 
		sqlTrees = new ArrayList();
	}
	
	/**
		@return a unique task for the generation process 
	 */
	private Integer getUniqueTask() {
		taskCounter++;
		return new Integer(taskCounter);
	};
	
	/**
	 	Delegates a task to a list of subnodes.
	 	@param from the owner of the task
	 	@param to the list of recievers
	 */
	void transferTask(Node from, LinkedList to) {
		if ((from == null) || (to == null)) return;

		Integer task = (Integer)getIn(from);
		Object temp[] = to.toArray();

		if (task != null)
            for(int i=0; i < temp.length; i++) {
            	setIn((Node)temp[i], new Integer(task.intValue()));
        	}
	}
	
	/**
	 	Delegates a task to a subnode.
	 	@param from the owner of the task
	 	@param to the reciever
	 */
	void transferTask(Node from, Node to) {
		if ((from ==null) || (to == null)) return;

		Integer task = (Integer)getIn(from);
		if (task != null) setIn(to, new Integer(task.intValue()));
	}
	
	/**
	 	Automated task delegation for all nodes.
	 */
	public void defaultIn(Node node) {
		Method[] method = node.getClass().getMethods();
		Object target;

		try {
			for (int i=0; i<method.length; i++) {
				if (method[i].getName().substring(0,3).equals("get")) {
					target = method[i].invoke(node, null);
					if (target instanceof Node) {
						transferTask(node, (Node)target);
					} else if (target instanceof LinkedList) {
						transferTask(node, (LinkedList)target);
					}
				}
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}
		
	/**
		@param name the name of a class
		@return the class table of the specified class
	 */
	private Table getClassTable(String name) {
		List classTables = theMap.getMappedClass(name).getTables();
		if (classTables.size() > 1) throw new RuntimeException("1:n class to table mapping with n > 1 is not supported !");
		return (Table)classTables.get(0);
	}
	
	/**
		@return a unique table alias
	 */
	private String getUniqueAlias() {
		if (aliasCounter == 0) {
			aliasCounter++;
			return "self";
		} else {
			String result = TOK_ALIAS + aliasCounter;
			aliasCounter++;
			return result;
		}
	}
	
	/**
	 * @param pathName a path name
	 * @return true if pathName contains packages, false otherwise
	 */
	private boolean containsPackage(String pathName) {
		int i1, i2;
		String classifier;
		
		i1 = pathName.indexOf(TOK_DCOLON);
		i2 = pathName.lastIndexOf(TOK_DCOLON);
		
		if ((i1 != -1) && (i2 != -1)) {
			if (i1 == i2) {
				classifier = pathName.substring(0, i1);
				if (theMap.getMappedClass(classifier) != null) {
					return false;
				} else {
					return true;
				}
			} else {
				return true;
			}
		} else {
			return false;		
		}
	}
		
	// ------------------------- handling of ocl grammar rules -------------------------
	public void inAConstraint(AConstraint node) {
		// initialize first nodes with tasks
		setIn(node, getUniqueTask());
		transferTask(node, node.getConstraintBody());
		transferTask(node, node.getContextDeclaration());
		
		// get constrained type
		AClassifierContext acc = (AClassifierContext)((AClassifierContextBody)((AContextDeclaration)node.getContextDeclaration()).getContextBody()).getClassifierContext();
		constrainedType = acc.getTypeName().toString().trim();
	}
	
	public void inAConstraintBody(AConstraintBody node) {
		// reset SQLCodeGenerator
		reset();
		
		// get context table and related information
		Table contextTable = getClassTable(constrainedType);
		String contextTableName = contextTable.getTableName();
		contextTablePks = contextTable.getPrimaryKeyColumns();
		String contextTableAlias = getUniqueAlias();
		constraintName = node.getName().toString().trim();   
		
		// check number of primary key columns
		if (contextTablePks.length != 1) {
			throw new RuntimeException("Expected primary key consisting of exactly one column (inAConstraintBody)!");
		}
		
		// create task for translation of expression and delegate it
		Integer expTask = getUniqueTask();
		setIn(node.getExpression(), expTask);
		
		// create SQL subtree for this node
		theTreeBuilder.reset();
		theTreeBuilder.setArgument("object", contextTablePks[0]);
		theTreeBuilder.setArgument("context_table", contextTableName);
		theTreeBuilder.setArgument("context_alias", contextTableAlias);
		theTreeBuilder.setArgument("expression", expTask);
		theTreeBuilder.subtreeFor("inAConstraintBody", null, null);						
	}
	
	public void outAConstraintBody(AConstraintBody node) {
		sqlTrees.add(theTreeBuilder.getSQLTree());
	}
	
	public void inALogicalExpressionTail(ALogicalExpressionTail node) {
		// get task
		Integer task = (Integer)getIn(node);
        if (task == null) return;
        
        // generate task for relational expression
    	Integer taskRe = getUniqueTask();
	   	setIn(node.getRelationalExpression(), taskRe);
	   	
	   	// prepare tree builder
		theTreeBuilder.reset();
		theTreeBuilder.setArgument("rel_exp_1", task);
		theTreeBuilder.setArgument("rel_exp_2", taskRe);
		
		if (node.getLogicalOperator() instanceof AAndLogicalOperator) {
    		theTreeBuilder.subtreeFor("inALogicalExpressionTail", "and", task);
    	} else if (node.getLogicalOperator() instanceof AOrLogicalOperator) {
    		theTreeBuilder.subtreeFor("inALogicalExpressionTail", "or", task);
    	} else if (node.getLogicalOperator() instanceof AXorLogicalOperator) {
    		theTreeBuilder.subtreeFor("inALogicalExpressionTail", "xor", task);
    	} else if (node.getLogicalOperator() instanceof AImpliesLogicalOperator) {
    		theTreeBuilder.subtreeFor("inALogicalExpressionTail", "implies", task);
    	}		
	}
	
	public void inARelationalExpressionTail(ARelationalExpressionTail node) {
		// get task
		Integer task = (Integer)getIn(node);
        if (task == null) return;
        
		// generate task for additive expression
		Integer taskAe = getUniqueTask();
		setIn(node.getAdditiveExpression(), taskAe);
		
		// prepare tree builder
		theTreeBuilder.reset();
		theTreeBuilder.setArgument("add_exp_1", task);
		theTreeBuilder.setArgument("add_exp_2", taskAe);
		
		// create subtree related to operation type
		Type type = theTree.getNodeType(node.getAdditiveExpression());
		
		if (node.getRelationalOperator() instanceof AEqualRelationalOperator) {
			if (type instanceof Basic) {
				if ((type == Basic.INTEGER) || (type == Basic.REAL) || (type == Basic.STRING)) {
					theTreeBuilder.subtreeFor("inARelationalExpressionTail", "eq_IntRealString", task);
				} else if (type == Basic.BOOLEAN) {
					theTreeBuilder.subtreeFor("inARelationalExpressionTail", "eq_Boolean", task);
				} else if (type == Basic.ENUMERATION) {
					throw new RuntimeException("No support for AEqualRelationalOperator on enumerations !");
				}
			} else if (type instanceof Collection) {
				throw new RuntimeException("No support for AEqualRelationalOperator on collections !");
			} else {
				// OclAny
				theTreeBuilder.subtreeFor("inARelationalExpressionTail", "eq_Any", task);
			}
		} else if (node.getRelationalOperator() instanceof ANEqualRelationalOperator) {
			theTreeBuilder.subtreeFor("inARelationalExpressionTail", "neq", task);
		} else if (node.getRelationalOperator() instanceof AGtRelationalOperator) {
        	theTreeBuilder.subtreeFor("inARelationalExpressionTail", "gt", task);
        } else if (node.getRelationalOperator() instanceof ALtRelationalOperator) {
			theTreeBuilder.subtreeFor("inARelationalExpressionTail", "lt", task);
		} else if (node.getRelationalOperator() instanceof AGteqRelationalOperator) {
			theTreeBuilder.subtreeFor("inARelationalExpressionTail", "gteq", task);
		} else if (node.getRelationalOperator() instanceof ALteqRelationalOperator) {
			theTreeBuilder.subtreeFor("inARelationalExpressionTail", "lteq", task);
		}			
	}
	
    public void inAAdditiveExpressionTail(AAdditiveExpressionTail node) {
    	// get task
		Integer task = (Integer)getIn(node);
        if (task == null) return;
        
		// generate task for relational expression
		Integer taskMe = getUniqueTask();
		setIn(node.getMultiplicativeExpression(), taskMe);
		
    	
        // prepare tree builder
		theTreeBuilder.reset();
		theTreeBuilder.setArgument("mult_exp_1", task);
		theTreeBuilder.setArgument("mult_exp_2", taskMe);

        // get SQL code related to the operation type
        if (node.getAddOperator() instanceof APlusAddOperator) {
        	theTreeBuilder.subtreeFor("inAAdditiveExpressionTail", "plus", task);        
        } else if (node.getAddOperator() instanceof AMinusAddOperator) {
            theTreeBuilder.subtreeFor("inAAdditiveExpressionTail", "minus", task);    
        }
    }
    
    public void inAMultiplicativeExpressionTail(AMultiplicativeExpressionTail node) {
    	// get task
		Integer task = (Integer)getIn(node);
        if (task == null) return;
        
		// generate task for relational expression
		Integer taskUe = getUniqueTask();
		setIn(node.getUnaryExpression(), taskUe);
		
		// prepare tree builder
		theTreeBuilder.reset();
		theTreeBuilder.setArgument("un_exp_1", task);
		theTreeBuilder.setArgument("un_exp_2", taskUe);
		
		if (node.getMultiplyOperator() instanceof AMultMultiplyOperator) {
        	theTreeBuilder.subtreeFor("inAMultiplicativeExpressionTail", "mult", task);      
        } else if (node.getMultiplyOperator() instanceof ADivMultiplyOperator) {
        	theTreeBuilder.subtreeFor("inAMultiplicativeExpressionTail", "div", task);      
        }				
    }
    
    public void inAUnaryUnaryExpression(AUnaryUnaryExpression node) {
    	// get task
		Integer task = (Integer)getIn(node);
        if (task == null) return;
        
		// generate task for relational expression
		Integer taskPe = getUniqueTask();
		setIn(node.getPostfixExpression(), taskPe);
		
		// prepare tree builder
		theTreeBuilder.reset();
		theTreeBuilder.setArgument("un_exp", taskPe);
		
		if (node.getUnaryOperator() instanceof AMinusUnaryOperator) {
        	theTreeBuilder.subtreeFor("inAUnaryUnaryExpression", "minus", task); 
        } else if (node.getUnaryOperator() instanceof ANotUnaryOperator) {
        	theTreeBuilder.subtreeFor("inAUnaryUnaryExpression", "not", task); 
        }		
    }
	
	/**
		At this node respectivly in this methode, the guides will be assigned 
		to the PostfixExpressionTails to provide necessary object relational
		mapping information during the ongoing tree traversal.
	 */
	public void inAPostfixExpression(APostfixExpression node) {
		// first delegate tasks to subtrees
		transferTask(node, node.getPostfixExpressionTail());
    	transferTask(node, node.getPrimaryExpression());
    	
    	// then assign guides to primary expression and all postfix expression tails
    	String startType, startFeature, featureName, next, str, typeName;
    	MappedClass mc = null;
    	Object[] tail;
    	Guide guide;
    	int dColInd;
    	APostfixExpressionTail pet;
    	boolean typeFeatSucc = false;
    	PPrimaryExpression pex = node.getPrimaryExpression();
    	Type type = null;
    	
    	if ((pex instanceof ALitColPrimaryExpression) || 
    	    (pex instanceof ALiteralPrimaryExpression)||
    	    (pex instanceof AParenthesesPrimaryExpression)) {
    		// iterate the postfix expression tail 
        	tail = node.getPostfixExpressionTail().toArray();
        	type = theTree.getNodeType(pex);
        	
        	for (int i=0; i<tail.length; i++) {
        		pet = (APostfixExpressionTail)tail[i];
        		next = ((AFeatureCall)(pet).getFeatureCall()).getPathName().toString().trim();
        		
        		if (type instanceof Basic) {
        			basicTypeSucc.put(pet, type);     
        		} else if (oclAllTokens.indexOf(next) != -1) {
					oclOperation.add(pet);					
				} 
				
				if (type instanceof Collection) collTypes.put(pet, type);
				type = theTree.getNodeType(pet);
        	}
    		
    	} else if (pex instanceof AFeaturePrimaryExpression) { 
			// get start feature and check for usage of package names
			startFeature = ((AFeaturePrimaryExpression)pex).getPathName().toString().trim();
            if (containsPackage(startFeature)) throw new IllegalStateException("No support for packages yet !");
            
            // determine type of the starting point and feature name
			if ((dColInd = startFeature.lastIndexOf(TOK_DCOLON)) != -1) {
				// feature is inherited
				startType = startFeature.substring(0, dColInd);
				featureName = startFeature.substring(dColInd+2);
			} else {
				// feature is not inherited
				if (theTree.getTypeFor(startFeature, pex) == null) {
					startType = startFeature;
				} else {    
					startType = theTree.getTypeFor(startFeature, pex).toString().trim();
				}
				featureName = null;
            }
            
            // get MappedClass for start type
			mc = theMap.getMappedClass(startType);
			
			// get guides to the feature starting from startType
			try {
				if (mc.isAttribute(featureName)) {
					guide = mc.getAttributeGuide(featureName);
				} else if (mc.isAssociationEnd(featureName)) {
					guide = mc.getJoinGuide(featureName);
				} else {
					throw new RuntimeException("No guide available !");
				}                        	
			} catch(NullPointerException e) {
				// feature name is given in one of the postfix expression tails
				guide = mc.getJoinGuide(mc.getClassName());
				guide.setAlias(startType);
			}
			guides.put(pex, guide);
			type = theTree.getNodeType(pex);
						
			// iterate the postfix expression tail and assign navigation guides
        	tail = node.getPostfixExpressionTail().toArray();
        	
        	for (int i=0; i<tail.length; i++) {
        		pet = (APostfixExpressionTail)tail[i];
        		next = ((AFeatureCall)(pet).getFeatureCall()).getPathName().toString().trim();
        		
        		if (next.equals("collect")) {
        			str = (((AFeatureCallParameters)((AFeatureCall)pet.getFeatureCall()).getFeatureCallParameters()).getActualParameterList()).toString().trim();
        			if (str.indexOf(".") == -1) {
        				next = str;
        			} else {
        				next = str.substring(str.indexOf(".") + 1).trim();        				
        			}
        			collect.put(pet, next);
        		}
        		
        		if (type instanceof Basic) {
        			basicTypeSucc.put(pet, type);      			
        		} else if (mc.isAttribute(next)) {
                	// next feature is an attribute
        			guide = mc.getAttributeGuide(next);
        			if ((i==0) && (dColInd == -1)) guide.setAlias(startFeature);
                    guides.put(pet, guide);    
                    navigations.add(pet);
                    typeFeatSucc = true;                 
                    // special handling for complex attributes
                    typeName = theTree.getNodeType((Node)tail[i]).toString();
                    if (theMap.getMappedClass(typeName) != null) {
                    	mc = theMap.getMappedClass(typeName);                        
                    } 
				} else if (mc.isAssociationEnd(next)) {
					// next feature navigates to an association end
	    			guide = mc.getJoinGuide(next);
	    			if ((i==0) && (dColInd == -1)) guide.setAlias(startFeature);
	    			guides.put(pet, guide);
	    			navigations.add(pet);
	       			mc = mc.getAssociationEnd(next);                                        
                    typeFeatSucc = true;                                           
				} else if (oclAllTokens.indexOf(next) != -1) {
					guides.put(pet, guide);
					oclOperation.add(pet);
					typeFeatSucc = false;
				} else if (mc.isQuery(next)) {
					guides.put(pet, guide);
					typeFeatSucc = false;
					operations.put(pet, mc.getClassName());
				}					
				
				if (type instanceof Collection) collTypes.put(pet, type);
				type = theTree.getNodeType(pet);
        	}		
			
    	}
    }
	
	public void inAFeatureCall(AFeatureCall node) {
		int step, k;
		String alias, tableAlias = null, className, aKey, aTable;
		Node tailBegin, fcp = null;
		Integer taskAp = null, parameter;
		AStandardDeclarator asd;
		PFeatureCallParameters fcps;
		PExpression pe;
		AActualParameterList aapl;
		LinkedList ll;
		MappedClass mc;
		Table table;
		Type collType, collType2 = null;
				
		// get task
		Integer task = (Integer)getIn(node);
		if (task == null) return;
				
		// get path name for this feature call
        String pathName = node.getPathName().toString().trim();
        //System.err.println("-->" + pathName);
                
        // get guide, if available
        Guide guide = (Guide)guides.get(node.parent());  
        if (guide != null) {
        	guide.reset(); 
        	guide.next();     
    	}
    	
    	// treatment of collect
    	if (collect.containsKey(node.parent())) {
    		pathName = (String)collect.remove(node.parent());
    		//System.err.println("collect on " + pathName);
    	}
        
        // treatment of navigations
        if (navigations.contains(node.parent())) {
        	navigations.remove(node.parent());
        	//System.err.println("navigation");
        	
        	// complain about missing guide
			if (guide == null) throw new RuntimeException("missing guide for " + pathName);
			
			// generate target code from guide
			guide.reset();
			try {
	        	alias = guide.getAlias();
	        } catch(NullPointerException ex) {
	        	alias = null;
	        }
	        step = 0;
						
			if (guide.isNavigation()) {	
	            // navigation only
	            while (guide.hasMoreSteps()) {
	            	guide.next();
	            	step++;
	                theTreeBuilder.reset();
		            theTreeBuilder.setArgument("object",     guide.getSelect());
		            theTreeBuilder.setArgument("table",      guide.getFrom());
		            theTreeBuilder.setArgument("link_object",guide.getWhere());
		            if ((alias != null) && (step == guide.numberOfSteps())) {
		            	// context step
		            	theTreeBuilder.setArgument("context", alias);	
		            	theTreeBuilder.subtreeFor("inAFeatureCall", "navigation_context", task);
		            } else {
		            	// ordinary step
		            	theTreeBuilder.setArgument("collection", task);
		            	theTreeBuilder.subtreeFor("inAFeatureCall", "navigation", task);
		            }		            
		      	}
			} else {
				// attribute access
				if (alias != null) {
		        	// direct attribute access
		        	theTreeBuilder.reset();
		        	guide.reset();
		        	guide.next();
		        	theTreeBuilder.setArgument("object", guide.getSelect());
		        	theTreeBuilder.setArgument("context", alias);
		        	theTreeBuilder.subtreeFor("inAFeatureCall", "attribute_context", task);
		        } else {
		        	// attribute access with navigation
			        while (guide.hasMoreSteps()) {
		            	guide.next();
		            	step++;
		                theTreeBuilder.reset();
			            theTreeBuilder.setArgument("object",     guide.getSelect());
			            theTreeBuilder.setArgument("table",      guide.getFrom());
			            theTreeBuilder.setArgument("link_object",guide.getWhere());
			            theTreeBuilder.setArgument("collection", task);
			            // call tree builder for navigation, since the procedure is the same
			            theTreeBuilder.subtreeFor("inAFeatureCall", "navigation", task);			            		            
			      	}
		        }				                                                                  		
			}
        }
        
        // treatment of operations over basic types
        if (basicTypeSucc.containsKey(node.parent())) {
        	//System.err.println("basic type op");
        	Type type = (Type)basicTypeSucc.remove(node.parent());
        	fcp = node.getFeatureCallParameters();
        	theTreeBuilder.reset();
        	k=0;
        	        	
        	if (fcp != null) {
        		// get parameters for according operations  
    			aapl = (AActualParameterList)((AFeatureCallParameters)fcp).getActualParameterList();
    			
    			// parameter list expression
    			taskAp = getUniqueTask();
    			setIn(aapl.getExpression(), taskAp);
    			theTreeBuilder.setArgument("parameter0", taskAp);
    			
    			// parameter list tail
    			ll = aapl.getActualParameterListTail();
    			k = 1;
    			
    			for (Iterator i=ll.iterator(); i.hasNext(); k++) {
    				pe = ((AActualParameterListTail)i.next()).getExpression();
    				taskAp = getUniqueTask();
    				setIn(pe, taskAp);
    				theTreeBuilder.setArgument("parameter" + k, taskAp);
    			}
	       	}
        	
        	theTreeBuilder.setArgument("object", task);
        	theTreeBuilder.setArgument("no_parameters", new Integer(k));
        	theTreeBuilder.subtreeFor("inAFeatureCall", type.toString() + "_" + pathName, task);
        }
        
        // treatment of model type operations
        if (operations.containsKey(node.parent())) {
        	//System.err.println("model type op");
        	className = (String)operations.remove(node.parent());
        	
        	// prepare tree builder
        	theTreeBuilder.reset();
        	theTreeBuilder.setArgument("operation", className + "_" + pathName);
        	theTreeBuilder.setArgument("object", task);
        	
        	// treatment of parameters
			fcps = node.getFeatureCallParameters();
			if (fcps instanceof AConcreteFeatureCallParameters)	{
				throw new RuntimeException("No support for AConcreteFeatureCallParameters for " + pathName + "!");				
			} else if (fcps instanceof AEmptyFeatureCallParameters) {
				throw new RuntimeException("No support for AEmptyFeatureCallParameters for " + pathName + "!");
			} else if (fcps instanceof AFeatureCallParameters) {
				// get names of parameters and prepare code agent with them
				aapl = (AActualParameterList)((AFeatureCallParameters)fcps).getActualParameterList();
				try {
					// first parameter
					// the parameter arguments are named parameter0, ..., parameterN with (N+1) = total number of parameters
					parameter = getUniqueTask();
					setIn(aapl.getExpression(), parameter);
					theTreeBuilder.setArgument("parameter0", parameter);
								
					// all parameters from the second
					ll = aapl.getActualParameterListTail();
					for (int i=0; i<ll.size(); i++) {
						parameter = getUniqueTask();
						setIn((AActualParameterListTail)ll.get(i), parameter);
						theTreeBuilder.setArgument("parameter" + (i+1), parameter);
					}
				} catch(NullPointerException ex) {
					// in case there are no parameters
				}
			}
			
			theTreeBuilder.subtreeFor("inAFeatureCall", "operation", task);
        }
        
        // treatment of collection operations
        if (oclOperation.contains(node.parent())) {
        	oclOperation.remove(node.parent());
	       	tailBegin = ((APostfixExpressionTail)node.parent()).getPostfixExpressionTailBegin();
	       	//System.err.println("ocl op");
        	        	
        	try {
   				fcp = node.getFeatureCallParameters();
   				asd = (AStandardDeclarator)((AFeatureCallParameters)node.getFeatureCallParameters()).getDeclarator();

   				tableAlias = asd.getName().toString().trim();
   				className = theTree.getTypeFor(tableAlias, asd).toString().trim();
   				mc = theMap.getMappedClass(className);
   				table = (Table)mc.getTables().get(0);
   			} catch (Exception e) {
   				// just to avoid null pointer exceptions, if there is no StandardDeclarator
   			}
        	
        	if ((tailBegin instanceof AArrowPostfixExpressionTailBegin) && (fcp != null)) {
                taskAp = getUniqueTask();
	        	setIn(fcp, taskAp);
        	}       
        	
        	// determine type of base collection and prepare tree builder
        	theTreeBuilder.reset();
        	collType = (Type)collTypes.remove(node.parent());
	        if ((collType != null) && (collType instanceof Collection)) {
				if (((Collection)collType).getCollectionKind() == Collection.SET)
					theTreeBuilder.setArgument("collType", "SET");
				if (((Collection)collType).getCollectionKind() == Collection.BAG) 
					theTreeBuilder.setArgument("collType", "BAG");
	            if (((Collection)collType).getCollectionKind() == Collection.SEQUENCE) { 
	            	theTreeBuilder.setArgument("collType", "SEQUENCE");			     
	            	throw new RuntimeException("Sequences are not yet supported !");
	            }
	        } 
	        
	        // determine type of second collection, if there is one
	        if ((fcp != null) && ((AFeatureCallParameters)fcp).getActualParameterList() != null) {
	        	collType2 = theTree.getNodeType(((AActualParameterList)((AFeatureCallParameters)fcp).getActualParameterList()).getExpression());
	        	if ((collType2 != null) &&  (collType2 instanceof Collection)) {
			 		if (((Collection)collType2).getCollectionKind() == Collection.SET)
						theTreeBuilder.setArgument("collType2", "SET");
					if (((Collection)collType2).getCollectionKind() == Collection.BAG) 
						theTreeBuilder.setArgument("collType2", "BAG");
		            if (((Collection)collType2).getCollectionKind() == Collection.SEQUENCE) { 
		            	theTreeBuilder.setArgument("collType2", "SEQUENCE");
		            	throw new RuntimeException("Sequences are not yet supported !");
		            }
			    }
	        } 
	        
	        // get aKey and aTable
	        if (guide == null) {
	        	aKey = STANDARDKEY;
	        	aTable = STANDARDTABLE;
	        } else {
	        	aKey = guide.getSelect();
	        	aTable = guide.getFrom();
	        }
	        
        	// features that need to be mapped using the MODEL TYPE QUERY    
        	if (pathName.equals("allInstances")) {                       
	       		theTreeBuilder.setArgument("object", aKey);
        		theTreeBuilder.setArgument("base_table", aTable);
			}
        	
        	// features that need to be mapped using the COMPLEX PREDICATE pattern from UML'99
        	if ((pathName.equals("forAll")) || (pathName.equals("exists"))) {
				theTreeBuilder.setArgument("object", aKey);
				theTreeBuilder.setArgument("collection", task);
				theTreeBuilder.setArgument("base_table", aTable);
				theTreeBuilder.setArgument("base_table_alias", tableAlias);
				theTreeBuilder.setArgument("expression", taskAp);
			} else if ((pathName.equals("isEmpty")) || (pathName.equals("notEmpty"))) {
				theTreeBuilder.setArgument("collection", task);
        	} else if ((pathName.equals("includes"))  || (pathName.equals("excludes"))) {
        		theTreeBuilder.setArgument("object", taskAp);
        		theTreeBuilder.setArgument("collection", task);
        	} else if ((pathName.equals("includesAll"))  || (pathName.equals("excludesAll"))) {
        		theTreeBuilder.setArgument("collection", task);
        		theTreeBuilder.setArgument("collection2", taskAp);
        	} else if (pathName.equals("isUnique")) {
        		throw new RuntimeException("Operator isUnique not yet supported !");
        	}
        	
        	// features that need to be mapped using the QUERY pattern from UML'99
        	if ((pathName.equals("select")) || (pathName.equals("reject"))) {
        		theTreeBuilder.setArgument("object", aKey);
				theTreeBuilder.setArgument("collection", task);
				theTreeBuilder.setArgument("base_table", aTable);
				theTreeBuilder.setArgument("base_table_alias", tableAlias);
				theTreeBuilder.setArgument("expression", taskAp);
        	} else if (pathName.equals("union")) {
        		theTreeBuilder.setArgument("collection", task);
        		theTreeBuilder.setArgument("collection2", taskAp);
        	} else if (pathName.equals("intersection")) {
        		theTreeBuilder.setArgument("collection", task);
        		theTreeBuilder.setArgument("collection2", taskAp);
        	} else if ((pathName.equals("including"))  || (pathName.equals("excluding"))) {
        		theTreeBuilder.setArgument("object", taskAp);
        		theTreeBuilder.setArgument("collection", task);
        	} else if (pathName.equals("symmetricDifference")) {
        		theTreeBuilder.setArgument("collection", task);
        		theTreeBuilder.setArgument("collection2", taskAp);
        	}
       	
        	// features that need to be mapped using the BASIC VALUE pattern from UML'99
        	if (pathName.equals("size")) {
        		theTreeBuilder.setArgument("collection", task);
        	} else if (pathName.equals("count")) {
        		theTreeBuilder.setArgument("collection", task);
        		theTreeBuilder.setArgument("element", aKey);
        		theTreeBuilder.setArgument("object", taskAp);
        	} else if (pathName.equals("sum")) {
        		theTreeBuilder.setArgument("object", aKey);
        		theTreeBuilder.setArgument("collection", task);        		        		
        	}
        	
        	// build tree
        	theTreeBuilder.subtreeFor("inAFeatureCall", pathName, task);
        }
	}
	
	public void inALiteralPrimaryExpression(ALiteralPrimaryExpression node) {
		// get task
		Integer task = (Integer)getIn(node);
        if (task == null) return;
        
        // translate literal
        Node n = node.getLiteral();
        theTreeBuilder.reset();
        theTreeBuilder.setArgument("value", n.toString().trim());
        
        if (n instanceof AIntegerLiteral) {
        	theTreeBuilder.subtreeFor("inALiteralPrimaryExpression", "AIntegerLiteral", task);
        } else if (n instanceof AStringLiteral) {			
        	theTreeBuilder.subtreeFor("inALiteralPrimaryExpression", "AStringLiteral", task);
		} else if (n instanceof ABooleanLiteral) {			
        	theTreeBuilder.subtreeFor("inALiteralPrimaryExpression", "ABooleanLiteral", task);
		} else if (n instanceof ARealLiteral) {
        	theTreeBuilder.subtreeFor("inALiteralPrimaryExpression", "ARealLiteral", task);
		} else if (n instanceof AEnumLiteral) {
			theTreeBuilder.subtreeFor("inALiteralPrimaryExpression", "AEnumLiteral", task);
		}
	}
	
	public void inALiteralCollection(ALiteralCollection node) {
		// get task
		Integer task = (Integer)getIn(node);
        if (task == null) return;
        
        // get all expressions necessary to create the collection
        Node elor = node.getExpressionListOrRange(), elort;
        List exp = new ArrayList();
        Object[] elt;
		int min, max;
        
        if (elor != null) {
			exp.add(((AExpressionListOrRange)elor).getExpression());
			elort = ((AExpressionListOrRange)elor).getExpressionListOrRangeTail();
	
			if (elort != null) {
				if (elort instanceof AListExpressionListOrRangeTail) {
					elt = ((AListExpressionListOrRangeTail)elort).getExpressionListTail().toArray();
	
					for (int i=0; i<elt.length; i++) {
						exp.add(((AExpressionListTail)elt[i]).getExpression());
					}
				} else if (elort instanceof ARangeExpressionListOrRangeTail) {
					throw new RuntimeException("No support for ARangeExpressionListOrRangeTail !");
				}
			}
		}
		
		// determine collection kind
		Node collKind = node.getCollectionKind();
		String specColl = null;
		
		if (collKind instanceof ASetCollectionKind) {
			specColl = "set";
		} else if (collKind instanceof ABagCollectionKind) {
			specColl = "bag";
		} else if (collKind instanceof ASequenceCollectionKind) {
			specColl = "sequence" ;
			throw new RuntimeException("Sequences are not yet supported !");
		}
		
		// create subtree
		PExpression pe;
		int k = 0;
		
		theTreeBuilder.reset();
		theTreeBuilder.setArgument("no_elements", new Integer(exp.size()));
		
		for (Iterator i=exp.iterator(); i.hasNext(); k++) {
			pe = (PExpression)i.next();
			theTreeBuilder.setArgument("value" + k, pe.toString().trim());			
		}			
		theTreeBuilder.subtreeFor("inALiteralCollection", specColl, task);
	}
	
	public void inAParenthesesPrimaryExpression(AParenthesesPrimaryExpression node) {
		// get task
		Integer task = (Integer)getIn(node);
        if (task == null) return;
        
        // translate parentheses
		Integer taskEx = getUniqueTask();
		setIn(node.getExpression(), taskEx);
		
		theTreeBuilder.reset();
		theTreeBuilder.setArgument("expression", taskEx);		
        theTreeBuilder.subtreeFor("inAParenthesesPrimaryExpression", null, task);		
	}
}