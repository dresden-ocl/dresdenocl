/*
Copyright (C) 2000  Sten Loecher

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
package tudresden.ocl.codegen.decl;

import tudresden.ocl.parser.node.*;
import tudresden.ocl.check.types.*;

import java.lang.*;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * A class that generates SQL code.
 * @author Sten Loecher
 */
public class ILSQLCodeGenerator extends DeclarativeCodeGenerator {
	String constrainedType;
        String constraintName;
	String oclTokens = "sum;count;size;select;forAll;includes;includesAll;excludesAll;isEmpty;notEmpty;exists;isUnique;sortedby;allInstances";
	String oclTokBasic1 = "size;toUpper;toLower;abs;floor;round";
	String oclTokBasic2 = "max;min;div;mod;concat";
	String oclTokBasic3 = "substring";
	String dcolon = "::";
	String ALIAS = "TA";
        ORMappingScheme map;
        /**
         * @key-type 
         * @element-type List
         */
	Map navigation;
	Hashtable declarators;
	boolean formatCode = true;
	int aliasCount;
	int joinAliasCount = 0;
	String tableRepresentation;
	String joinRepresentation;
        String joinTargetObject;

	final static String STANDARDKEY = "elem";
        final static String COMPLEX_PREDICATE = "inlcudes;excludes;includesAll;excludesAll;isEmpty;notEmpty;exists;forAll;isUnique";

	// constructor
	public ILSQLCodeGenerator(String rules) {
		super(rules);

		map = new ORTestScheme();
	}
	
	// helper methodes
	public void formatSQLCode() {
		int count, ind;
		String indent = "";
		Vector v = new Vector();

		// replace right paragraphs
		for (int i=0; i<(code.length()-1); i++) {
			if (code.charAt(i) == '\n') {

				if (code.charAt(i+1) == ')') {
					code.deleteCharAt(i);
				}
			}
		}

		// indent code
		count = 0;
		for (int i=0; i<code.length(); i++, count++) {
			if (code.charAt(i) == '\n') {
				count = 0;
				indent = "";
				if (v.size()>0) {
					ind = ((Integer)v.elementAt(v.size()-1)).intValue();
				} else {
					ind = 0;
				}
				for (int k=0; k<ind; k++) {
					indent += " ";
				}
				code.insert(i+1, indent);
			} else if (code.charAt(i) == '(') {
				v.add(new Integer(count));
			} else if (code.charAt(i) == ')') {
				if (v.size() > 0) {
					v.remove(v.size()-1);
				}
			}
		}
	}

	private String getPathName(APathName node) {
		Object[] tail = node.getPathNameTail().toArray();
		return tail[tail.length - 1].toString();
	}
	
	/**
	 * @param pathName a path name
	 * @return true if pathName contains packages, false otherwise
	 */
	private boolean containsPackage(String pathName) {
		int i1, i2;
		String classifier;
		
		i1 = pathName.indexOf(dcolon);
		i2 = pathName.lastIndexOf(dcolon);
		
		if ((i1 != -1) && (i2 != -1)) {
			if (i1 == i2) {
				classifier = pathName.substring(0, i1);
				if (map.getMappedClass(classifier) != null) {
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
	
	/**
	 * @param guides a List of Guide objects
	 * @param alias an context alias to set in all Guides in the List
	 */
	private void setContextAlias(List guides, String alias) {
		//System.err.println(alias);
		for (Iterator i=guides.iterator(); i.hasNext(); ) {
			((Guide)i.next()).setAlias(alias);
		}
	}
	
	/**
	 * @return a unique table alias for use in the SQL code
	 */
	private String getUniqueAlias() {
		if (aliasCount == 0) {
			aliasCount++;
			return "SELF";
		} else {
			String result = ALIAS + aliasCount;
			aliasCount++;
			return result;
		}
	}
				
	public void setORMappingScheme(ORMappingScheme map) {
		this.map = map;
	}
	
	/**
	 *
	 */
	public void reset() {
		fragments = new Vector();
		constrainedType = null;
        	constraintName = null;
		navigation = new HashMap();
		declarators = new Hashtable();
		aliasCount = 0;
		joinAliasCount = 0;
	}
		
	/**
	 * @pre guides.size() > 1
	 */
	public void prepareJoin(List guides) {
		StringBuffer tables = new StringBuffer();
		StringBuffer joins = new StringBuffer();
		String select = "", from = "", where = "", tableAlias;
		Guide guide;
                
                for (int i=0; i<guides.size(); i++) {
			guide = (Guide)guides.get(i);
			guide.reset();
						
			for (int k=0; k<guide.numberOfSteps(); k++) {
				guide.next();
				
				// skip unnecessary joins
				if ((from.equals(guide.getFrom())) &&
				    (where.equals(guide.getSelect())) &&
				    (where.equals(guide.getWhere())))
					continue;
					
				// determine next table alias
				tableAlias = "TA" + joinAliasCount;
				joinAliasCount++;
				
				// close last join
				if ((i>0) || (k>0)) {
					joins.append(tableAlias + "." + guide.getSelect());
					joins.append(")");
				}
				
				// add table to table to from clause list				
				if (tables.length() == 0) {
					tables.append(guide.getFrom() + " " + tableAlias);
				} else {
					tables.append("," + guide.getFrom() + " " + tableAlias);					
				}
			
				// add logical link to next join
				if (joins.length() > 0) {
					joins.append(" and ");
				} 
				
				// open next join
				joins.append("(");
				joins.append(tableAlias + "." + guide.getWhere());
				joins.append(" = ");	
                                
                                if ((i == 0) && (k == 0)) joinTargetObject = tableAlias + "." + guide.getWhere();
				
				select = guide.getSelect();
				from = guide.getFrom();
				where = guide.getWhere();
			}
			
			if (i == (guides.size()-1)) {
				joins.append(guide.getAlias() + "." + guide.getWhere());
				joins.append(")");
			}					
		}		
		
		tableRepresentation = tables.toString();
		joinRepresentation = joins.toString();
	}
	
	public String getTableRepresentation() {
		return tableRepresentation;
	}
	
	public String getJoinRepresentation() {
		return joinRepresentation;
	}
		
	// handling of grammar rules
	public void inAConstraint(AConstraint node) {
		reset();
		
        	super.inAConstraint(node);

		AContextDeclaration node2 = (AContextDeclaration)node.getContextDeclaration();
		AClassifierContextBody node3 = (AClassifierContextBody)node2.getContextBody();
		AClassifierContext node4 = (AClassifierContext)node3.getClassifierContext();

		constrainedType = node4.getTypeName().toString().trim();		
        }

        public void inAConstraintBody(AConstraintBody node) {
        	String expTask = getUniqueTask();
                constraintName =  node.getName().toString().trim();                                
                
        	ca.reset();
        	ca.setArgument("constraint_name", constraintName);
        	ca.setArgument("context_table", (String)getIn(node));
        	ca.setArgument("context_alias", getUniqueAlias());
        	ca.setArgument("expression", expTask);

        	try {
        		code = new StringBuffer(ca.getCodeFor("constraint_body", "any"));
        	} catch (Exception e) {
        	}

        	setIn(node.getExpression(), expTask);
	}

	public void outAConstraintBody(AConstraintBody node) {

	}

	public void inAContextDeclaration(AContextDeclaration node) {
		// todo
		MappedClass mc = map.getMappedClass(constrainedType);
		String task = (String)getIn(node);

		if (mc != null) {
		        Iterator e = mc.getTables().iterator();

			while (e.hasNext()) {
				// code generate a number of code fragments equal to the number of tables !!!
				String tableName = ((Table)e.next()).getTableName();
				replaceTask(task, tableName);
			}

		 	if (formatCode) formatSQLCode();
		 	System.err.println("--> " + constrainedType + ":" + constraintName + "\n" + code.toString());
			fragments.add(new DeclarativeCodeFragment(constraintName, constrainedType, code.toString()));
		} else {
			// Error !!!
		}
	}

        public void inALogicalExpressionTail(ALogicalExpressionTail node) {
        	String task = (String)getIn(node);
        	if (task == null) return;
        	String thisCode = "";

        	// generate task for relational expression
        	String taskRe = getUniqueTask();
	       	setIn(node.getRelationalExpression(), taskRe);

	       	// prepare code agent
        	ca.reset();
        	ca.setArgument("rel_exp_1", task);
        	ca.setArgument("rel_exp_2", taskRe);

	      	// get SQL code related to the operation type
        	try {
        		if (node.getLogicalOperator() instanceof AAndLogicalOperator) {
        			thisCode = ca.getCodeFor("logical_expression_tail", "and");
        		} else if (node.getLogicalOperator() instanceof AOrLogicalOperator) {
        			thisCode = ca.getCodeFor("logical_expression_tail", "or");
        		} else if (node.getLogicalOperator() instanceof AXorLogicalOperator) {
        			thisCode = ca.getCodeFor("logical_expression_tail", "xor");
        		} else if (node.getLogicalOperator() instanceof AImpliesLogicalOperator) {
        			thisCode = ca.getCodeFor("logical_expression_tail", "implies");
        		}
        	} catch (Exception e) {
        		System.err.println(e.toString());
        	}

        	// replace task with generated target code
        	replaceTask(task, thisCode);
	}

	public void inARelationalExpressionTail(ARelationalExpressionTail node) {
        	String task = (String)getIn(node);
        	if (task == null) return;
        	String thisCode = "";
        	String spec = "";
        	Type type;

        	// generate task for additive expression
        	String taskAe = getUniqueTask();
	       	setIn(node.getAdditiveExpression(), taskAe);

	       	// prepare code agent
        	ca.reset();
        	ca.setArgument("add_exp_1", task);
        	ca.setArgument("add_exp_2", taskAe);

        	// get SQL code related to the operation type
        	try {
        		if (node.getRelationalOperator() instanceof AEqualRelationalOperator) {
        			type = theTree.getNodeType(node.getAdditiveExpression());

        			if (type instanceof Basic) {
        				if ((type == Basic.INTEGER) ||
        				    (type == Basic.REAL) ||
        				    (type == Basic.STRING) ||
        				    (type == Basic.ENUMERATION)) {
        					thisCode = ca.getCodeFor("relational_expression_tail", "equal_IntRealStringEnum");
        				} else if (type == Basic.BOOLEAN) {
        					thisCode = ca.getCodeFor("relational_expression_tail", "equal_Bool");
        				}
        			} else if (type instanceof Collection) {
        				switch (((Collection)type).getCollectionKind()) {
        					case Collection.SET	: spec = "Set";
        								  break;
        					case Collection.BAG	: spec = "Bag";
        								  break;
        					case Collection.SEQUENCE: spec = "Sequence";
        								  break;
        				}
        				thisCode = ca.getCodeFor("relational_expression_tail", "equal_" + spec);
        			} else {
        				// OclAny
        				thisCode = ca.getCodeFor("relational_expression_tail", "equal_OclAny");
        			}
        		} else if (node.getRelationalOperator() instanceof ANEqualRelationalOperator) {
                                type = theTree.getNodeType(node.getAdditiveExpression());

                                if (type instanceof Basic) {
                                        // Bascic types and Enumeration
                                        thisCode = ca.getCodeFor("relational_expression_tail", "nequal_Basic");
                                } else {
                                        // OclAny
                                        thisCode = ca.getCodeFor("relational_expression_tail", "nequal_OclAny");
                                }
        		} else if (node.getRelationalOperator() instanceof AGtRelationalOperator) {
        			thisCode = ca.getCodeFor("relational_expression_tail", "gt");
        		} else if (node.getRelationalOperator() instanceof ALtRelationalOperator) {
        			thisCode = ca.getCodeFor("relational_expression_tail", "lt");
        		} else if (node.getRelationalOperator() instanceof AGteqRelationalOperator) {
        			thisCode = ca.getCodeFor("relational_expression_tail", "gteq");
        		} else if (node.getRelationalOperator() instanceof ALteqRelationalOperator) {
        			thisCode = ca.getCodeFor("relational_expression_tail", "lteq");
        		}
	       	} catch (Exception e) {
        		System.err.println(e.toString());
        	}

        	// replace task with generated target code
        	replaceTask(task, thisCode);
	}

	public void inAAdditiveExpressionTail(AAdditiveExpressionTail node) {
        	String task = (String)getIn(node);
        	if (task == null) return;
	       	String thisCode = "";

        	// generate task for relational expression
        	String taskMe = getUniqueTask();
	       	setIn(node.getMultiplicativeExpression(), taskMe);

	       	// prepare code agent
        	ca.reset();
        	ca.setArgument("mult_exp_1", task);
        	ca.setArgument("mult_exp_2", taskMe);

        	// get SQL code related to the operation type
        	try {
        		if (node.getAddOperator() instanceof APlusAddOperator) {
        			thisCode = ca.getCodeFor("additive_expression_tail", "plus");
        		} else if (node.getAddOperator() instanceof AMinusAddOperator) {
        			thisCode = ca.getCodeFor("additive_expression_tail", "minus");
        		}
	       	} catch (Exception e) {
        		System.err.println(e.toString());
        	}

        	// replace task with generated target code
        	replaceTask(task, thisCode);
	}

	public void inAMultiplicativeExpressionTail(AMultiplicativeExpressionTail node) {
        	String task = (String)getIn(node);
        	if (task == null) return;
	       	String thisCode = "";

        	// generate task for relational expression
        	String taskUe = getUniqueTask();
	       	setIn(node.getUnaryExpression(), taskUe);

	       	// prepare code agent
        	ca.reset();
        	ca.setArgument("un_exp_1", task);
        	ca.setArgument("un_exp_2", taskUe);

        	// get SQL code related to the operation type
        	try {
        		if (node.getMultiplyOperator() instanceof AMultMultiplyOperator) {
        			thisCode = ca.getCodeFor("multiplicative_expression_tail", "mult");
        		} else if (node.getMultiplyOperator() instanceof ADivMultiplyOperator) {
        			thisCode = ca.getCodeFor("multiplicative_expression_tail", "div");
        		}
	       	} catch (Exception e) {
        		System.err.println(e.toString());
        	}

        	// replace task with generated target code
        	replaceTask(task, thisCode);
	}

	public void inAUnaryUnaryExpression(AUnaryUnaryExpression node) {
        	String task = (String)getIn(node);
        	if (task == null) return;
	       	String thisCode = "";

        	// generate task for relational expression
        	String taskPe = getUniqueTask();
	       	setIn(node.getPostfixExpression(), taskPe);

	       	// prepare code agent
        	ca.reset();
        	ca.setArgument("un_exp", taskPe);

        	// get SQL code related to the operation type
        	try {
        		if (node.getUnaryOperator() instanceof AMinusUnaryOperator) {
        			thisCode = ca.getCodeFor("unary_expression", "minus");
        		} else if (node.getUnaryOperator() instanceof ANotUnaryOperator) {
        			thisCode = ca.getCodeFor("unary_expression", "not");
        		}
	       	} catch (Exception e) {
        		System.err.println(e.toString());
        	}

        	// replace task with generated target code
        	replaceTask(task, thisCode);
	}

	public void inAPostfixExpression(APostfixExpression node) {
		transferTask(node, node.getPostfixExpressionTail());
        	transferTask(node, node.getPrimaryExpression());

        	// handling of navigations to attributes and association ends:
        	// - determines all guides to the target attribute or association end respectivly
        	// - associates these guides with the appropriate grammar nodes with the help of
        	//   the navigation Map
        	PPrimaryExpression pex = node.getPrimaryExpression();
        	String start, next, startFeature, startType, featureName;
        	Object[] tail;
        	MappedClass mc = null;
        	Guide g = null, guide;
        	List guides;
        	Node type;
                int dColInd;
                String inheritedFeature = "";
                boolean assEndSucc;

                if (pex instanceof AFeaturePrimaryExpression) {
                        startFeature = ((AFeaturePrimaryExpression)pex).getPathName().toString().trim();
                        
                        // check for package usage 
                        if (containsPackage(startFeature)) {
                        	throw new IllegalStateException("No support for packages yet !");
                        }

			// determine type of the starting point and feature name
			if ((dColInd = startFeature.lastIndexOf(dcolon)) != -1) {
				// feature is inherited
                                startType = startFeature.substring(0, dColInd);
                                featureName = startFeature.substring(dColInd+2);
                        } else {
                        	// feature is not inherited
                        	startType = theTree.getTypeFor(startFeature, pex).toString().trim();
                        	featureName = null;
                        }
                        
                        // get MappedClass for start type
                        mc = map.getMappedClass(startType);
                        
                        // get guides to the feature starting from startType
                        guides = new ArrayList();
                        try {
                        	if (mc.isAttribute(featureName)) {
                        		guides.add(mc.getAttributeGuide(featureName));
                        	} else if (mc.isAssociationEnd(featureName)) {
                        		guides.add(mc.getJoinGuide(featureName));
                        	}                        	
                        } catch(NullPointerException e) {
                        	// feature name is given in one of the postfix expression tails                        	
                        }
                        
                        // iterate the postfix expression tail and assign navigation guides
        		tail = node.getPostfixExpressionTail().toArray();
                        assEndSucc = false;

        		for (int i=0; i<tail.length; i++) {
                                next = ((AFeatureCall)((APostfixExpressionTail)tail[i]).getFeatureCall()).getPathName().toString().trim();

                                if (mc.isAttribute(next)) {
        				// next feature is an attribute
        				guide = mc.getAttributeGuide(next);
        				if ((i==0) && (dColInd == -1)) guide.setAlias(startFeature.toUpperCase());
        				guides.add(0, guide);
                                        assEndSucc = false;
            			} else if (mc.isAssociationEnd(next)) {
        				// next feature navigates to an association end
        				guide = mc.getJoinGuide(next);
        				if ((i==0) && (dColInd == -1)) guide.setAlias(startFeature.toUpperCase());
        				guides.add(0, guide);
	       				mc = mc.getAssociationEnd(next);
                                        assEndSucc = true;
        			} else if (oclTokens.indexOf(next) != -1) {
        				// next feature is an ocl feature --> assign guides list to last tail if it was an association end
                                        if (assEndSucc == true) {
                                            navigation.put(tail[i-1], guides);
                                        }                                                          
        			};
        			
        			// assign guides list to last postfix expression tail
        			if (i == (tail.length-1)) {
        				navigation.put(tail[i], guides);
        			}        			
        		}
	  	}

	}

        public void inAFeaturePrimaryExpression(AFeaturePrimaryExpression node) {
                String task = (String)getIn(node);
    		if (task == null) return;
                String thisCode = "";
                List guides = (List)navigation.get(node.parent());

                if ((guides != null) && (guides.size() > 1)) {
                    
                }

                // replace task with generated target code
    		try {
    			if (!thisCode.equals("")) {
        			replaceTask(task, thisCode);
        		}
        	} catch (Exception e) {
			System.err.println("replacement failure at FeaturePrimaryExpression: " + e.toString());
        	}
        }

	public void inAFeatureCall(AFeatureCall node) {
    		String task = (String)getIn(node);
    		if (task == null) return;
    		String pathName = node.getPathName().toString().trim();
    		Node tailBegin = ((APostfixExpressionTail)node.parent()).getPostfixExpressionTailBegin();
    		String thisCode = "";
    		String navCode;
    		String spec = "";
    		MappedClass mc = null;
    		Guide guide;
    		String pkName = "";
    		boolean codeForPathName = false;
    		AStandardDeclarator asd = null;
   		Node fcp = null;
   		String tableAlias = "";
   		String className = "";
   		String taskAp = "";
   		Table table = new Table("dummy");
    	
    		List guides = (List)navigation.get(node.parent());


    		if (oclTokens.indexOf(pathName) == -1) {
    			// quit if no guides list is available or complain about empty list
    			if (guides == null) return;
    			if (guides.size() == 0) throw new RuntimeException("empty guides list at " + pathName);
    			    			    			    			
    			// generate target code from guide
    			guide = (Guide)guides.get(0);
                        guide.reset();
    			guide.next();
    			    			   				
    			if (guide.isNavigation()) {
                                // navigation only
                                prepareJoin(guides);
                                ca.reset();
                                ca.setArgument("object", joinTargetObject);
                                ca.setArgument("tables", tableRepresentation);
                                ca.setArgument("joins", joinRepresentation);
                                try {
        			    thisCode = ca.getCodeFor("feature_call", "navigation");
        			} catch (Exception e) {
        			    throw new RuntimeException("navigation: " + e.toString());
        			}
	    		} else {
	    			if (guides.size() == 1) {
	    				// attribute access without navigation
    					ca.reset();
    					ca.setArgument("context_alias", guide.getAlias());
    					ca.setArgument("column", guide.getSelect());
    					try {
        					thisCode = ca.getCodeFor("feature_call", "attribute_context");
        				} catch (Exception e) {
        					throw new RuntimeException("attribute_context: " + e.toString());
        				}
	    			} else {
	    				// attribute access with navigation
	    				prepareJoin(guides);
    					ca.reset();
    					ca.setArgument("column", guide.getSelect());
    					ca.setArgument("tables", tableRepresentation);
    					ca.setArgument("joins", joinRepresentation);
    						    			
	    				try {
        					thisCode = ca.getCodeFor("feature_call", "attribute_navigation");
        				} catch (Exception e) {
        					throw new RuntimeException("attribute_navigation: " + e.toString());
        				}
	    			}	    			    						
    			}
   		} else {
   			// maybe a special feature call
   			// get necessary mapping type information of iterators
   			try {
   				fcp = node.getFeatureCallParameters();
   				asd = (AStandardDeclarator)((AFeatureCallParameters)node.getFeatureCallParameters()).getDeclarator();

   				tableAlias = asd.getName().toString().trim();
   				className = theTree.getTypeFor(tableAlias, asd).toString().trim();
   				mc = map.getMappedClass(className);
   				table = (Table)mc.getTables().get(0);
   			} catch (Exception e) {
   				// just to avoid null pointer exceptions, if there is no StandardDeclarator
   			}
   			
   			// prepare navigation data
   			if ((guides != null) && (guides.size() > 0)) {
   				guide = (Guide)guides.get(0);
   				guide.reset();
    				guide.next();
    				pkName = guide.getSelect();
    				prepareJoin(guides);
    			} 
   			
   			// generate code
   			// features that need to be mapped using the MODEL TYPE QUERY
   			if (pathName.equals("allInstances")) {
   				
   				ca.reset();
        			ca.setArgument("column", pkName);
        			ca.setArgument("tables", tableRepresentation);
    				ca.setArgument("joins", joinRepresentation);

        			try {
        				thisCode = ca.getCodeFor("feature_call", "allInstances");
        			} catch (Exception e) {
        				System.err.println(e.toString());
        			}
   			}
   			
   			// operations over collections
   			if (tailBegin instanceof AArrowPostfixExpressionTailBegin) {
                        	ca.reset();

	                        if (fcp != null) {
        	                	taskAp = getUniqueTask();
                	        	setIn(fcp, taskAp);
                        	}	
   			
   				// features that need to be mapped using the COMPLEX PREDICATE pattern from UML'99
                          	if ((pathName.equals("includes")) || (pathName.equals("excludes"))) {
                          	} else if ((pathName.equals("includesAll")) || (pathName.equals("excludesAll"))) {
  			  	} else if ((pathName.equals("isEmpty")) || (pathName.equals("notEmpty"))) {
	         		} else if ((pathName.equals("forAll")) || (pathName.equals("exists")) || (pathName.equals("isUnique"))) {
  					ca.reset();
                                        ca.setArgument("collection", task);
        				ca.setArgument("object", pkName);
        				ca.setArgument("table", table.getTableName() + " " + tableAlias.toUpperCase());
        				ca.setArgument("expression", taskAp);
        				codeForPathName = true;
  				}
			  
			  	if (codeForPathName) {
                          		try {
	        		  		thisCode = ca.getCodeFor("feature_call", pathName);
        			  	} catch (Exception e) {
        			      		System.err.println(e.toString());
        			    	}
                          	}
                        }
   		}

   		// replace task with generated target code
    		try {
    			if (!thisCode.equals("")) {
        			replaceTask(task, thisCode);
        		}
        	} catch (Exception e) {
			System.err.println("replacement failure at feature call: " + e.toString());
        	}
   	}

	public void inALiteralPrimaryExpression(ALiteralPrimaryExpression node) {
		String task = (String)getIn(node);
		if (task == null) return;
		Node n = node.getLiteral();
		String thisCode = "";

		try {
			// get target code
			if (n instanceof AIntegerLiteral) {
				thisCode = n.toString().trim();
			} else if (n instanceof AStringLiteral) {
				ca.reset();
				ca.setArgument("value", n.toString().trim());
				thisCode = ca.getCodeFor("literal", "string");
        		} else if (n instanceof ABooleanLiteral) {
        			ca.reset();
        			if (n.toString().trim().equals("true")) {
					thisCode = ca.getCodeFor("literal", "boolean_true");
				} else {
					thisCode = ca.getCodeFor("literal", "boolean_false");
				}
			} else if (n instanceof ARealLiteral) {
				thisCode = n.toString().trim();
			} else if (n instanceof AEnumLiteral) {
				ca.reset();
				ca.setArgument("value", ((AEnumLiteral)n).getName().toString().trim());
				thisCode = ca.getCodeFor("literal", "enum");
			}

			// replace task with generated target code
    			if (!thisCode.equals("")) {
        			replaceTask(task, thisCode);
        		}
        	} catch (Exception e) {
			System.err.println(e.toString());
        	}
	}

	public void inALiteralCollection(ALiteralCollection node) {
		String task = (String)getIn(node);
    		if (task == null) return;
    		Node collKind = node.getCollectionKind();
		Node elor = node.getExpressionListOrRange();
		Node elort;
		Vector exp = new Vector();
		Object[] elt;
		int min, max;
		String thisCode = "";
		String spec = "";

		// get all expressions necessary to create the collection
		if (elor != null) {
			exp.add(((AExpressionListOrRange)elor).getExpression().toString().trim());
			elort = ((AExpressionListOrRange)elor).getExpressionListOrRangeTail();

			if (elort != null) {
				if (elort instanceof AListExpressionListOrRangeTail) {
					elt = ((AListExpressionListOrRangeTail)elort).getExpressionListTail().toArray();

					for (int i=0; i<elt.length; i++) {
						exp.add(((AExpressionListTail)elt[i]).getExpression().toString().trim());
					}
				} else if (elort instanceof ARangeExpressionListOrRangeTail) {
					min = (new Integer((String)exp.elementAt(0))).intValue();
					max = (new Integer(((ARangeExpressionListOrRangeTail)elort).getExpression().toString())).intValue();

					for (int i=++min; i<=max; i++) {
						exp.add(new String(i+""));
					}
				}
			}

		}

		// determine collection kind
		if (collKind instanceof ASetCollectionKind) spec = "set" ;
		if (collKind instanceof ABagCollectionKind) spec = "bag" ;
		if (collKind instanceof ASequenceCollectionKind) spec = "sequence" ;

		// create target code
		for (int i=0; i<exp.size(); i++) {
			ca.reset();
			ca.setArgument("value", exp.elementAt(i).toString());
			if (collKind instanceof ASequenceCollectionKind) {
				ca.setArgument("seqnr", i + "");
			}

			try {
        			if (!thisCode.equals("")) ca.enableConnector();
        			thisCode += ca.getCodeFor("literal_collection", spec);
        		} catch (Exception e) {
        			System.err.println("select: " + e.toString());
			}
		}

		// replace task with generated target code
    		try {
    			if (!thisCode.equals("")) {
        			replaceTask(task, thisCode);
        		}
        	} catch (Exception e) {
			System.err.println("replacement failure at literal_collection: " + e.toString());
        	}
	}

	public void inAIfExpression(AIfExpression node) {
		String task = (String)getIn(node);
    		if (task == null) return;
    		String taskIf = getUniqueTask();
    		String taskThen = getUniqueTask();
    		String taskElse = getUniqueTask();
    		String thisCode;

    		// delegate expression tasks
    		setIn(node.getIfBranch(), taskIf);
    		setIn(node.getThenBranch(), taskThen);
    		setIn(node.getElseBranch(), taskElse);

    		// prepare code agent
    		ca.reset();
    		ca.setArgument("if_branch", taskIf);
    		ca.setArgument("then_branch", taskThen);
    		ca.setArgument("else_branch", taskElse);

		// get code and replace task
		try {
        		thisCode = ca.getCodeFor("if_expression", "any");
        		replaceTask(task, thisCode);
        	} catch (Exception e) {
        		System.err.println(e.toString());
        	}
	}

	public void inAParenthesesPrimaryExpression(AParenthesesPrimaryExpression node) {
		String task = (String)getIn(node);
    		if (task == null) return;
    		String taskEx = getUniqueTask();
    		String thisCode;

    		// delegate expression task
    		setIn(node.getExpression(), taskEx);

    		// prepare code agent
    		ca.reset();
    		ca.setArgument("expression", taskEx);

    		// get code and replace task
		try {
        		thisCode = ca.getCodeFor("parentheses_primary_expression", "any");
        		replaceTask(task, thisCode);
        	} catch (Exception e) {
        		System.err.println(e.toString());
        	}
	}

}