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

import java.lang.Exception;
import java.lang.String;

import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

/**
 * A class that generates SQL code.
 * !!! Currently, this Generator does not work anymore. Use class ILSQLCodeGenerator instead !!!
 * @author Sten Loecher
 */
public class SQLCodeGenerator extends DeclarativeCodeGenerator {
	String constrainedType;
        String constraintName;
	String oclTokens = "sum;count;size;select;forAll;includes;includesAll;excludesAll;isEmpty;notEmpty;exists;isUnique;sortedby;allInstances";
	String oclTokBasic1 = "size;toUpper;toLower;abs;floor;round";
	String oclTokBasic2 = "max;min;div;mod;concat";
	String oclTokBasic3 = "substring";
        ORMappingScheme map;
	Hashtable navigation;
	Hashtable declarators;

	final static String STANDARDKEY = "elem";
        final static String COMPLEX_PREDICATE = "inlcudes;excludes;includesAll;excludesAll;isEmpty;notEmpty;exists;forAll;isUnique";

	// constructor
	public SQLCodeGenerator(String rules) {
		super(rules);

		map = new ORTestScheme();
		navigation = new Hashtable();
		declarators = new Hashtable();
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

	// handling of grammar rules
	public void inAConstraint(AConstraint node) {
        	super.inAConstraint(node);

		AContextDeclaration node2 = (AContextDeclaration)node.getContextDeclaration();
		AClassifierContextBody node3 = (AClassifierContextBody)node2.getContextBody();
		AClassifierContext node4 = (AClassifierContext)node3.getClassifierContext();

		constrainedType = node4.getPathTypeName().toString().trim();
        }

        public void inAConstraintBody(AConstraintBody node) {
        	String expTask = getUniqueTask();
                constraintName =  node.getName().toString().trim();

        	ca.reset();
        	ca.setArgument("constraint_name", constraintName);
        	ca.setArgument("context_table", (String)getIn(node));
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

		 	formatSQLCode();
			fragments.add(new DeclarativeCodeFragment(constraintName, constrainedType, code.toString(), null));
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

        	// handling of navigations to attributes and association ends
        	PPrimaryExpression pex = node.getPrimaryExpression();
        	String start, next, startFeature;
        	Object[] tail;
        	MappedClass mc = null;
        	Guide g = null;
        	Node type;
                int dcolon;
                String inheritedFeature = "";

                if (pex instanceof AFeaturePrimaryExpression) {
                        startFeature = ((AFeaturePrimaryExpression)pex).getPathName().toString().trim();

                        // treatment of inheritance --> important: use of package navigation would probably cause errors
                        if ((dcolon = startFeature.lastIndexOf("::")) != -1) {
                                inheritedFeature = startFeature.substring(dcolon+2).trim();
                                startFeature = constrainedType;
                        }

        		// get name of start type
        		if (theTree.getTypeFor(startFeature, pex) != null) {
        			start = theTree.getTypeFor(startFeature, pex).toString().trim();
        		} else {
                                // for inheritance
        			start = startFeature;
        		}

        		mc = map.getMappedClass(start);
                        if (dcolon != -1) {
                                if (mc.isAttribute(inheritedFeature)) {
        				// inherited feature is an attribute
                                        g = mc.getAttributeGuide(inheritedFeature);
                                        g.setAlias("SELF");
                                } else if (mc.isAssociationEnd(inheritedFeature)) {
        				// inherited feature navigates to an association end
        				g = mc.getJoinGuide(inheritedFeature);
                                        g.setAlias("SELF");
                                        mc = mc.getAssociationEnd(inheritedFeature);
        			}
                        } else {
                          g = mc.getJoinGuide(start);
                        }
                        navigation.put(pex, g);

                        // iterate the postfix_expression_tail an assign navigation guides
        		tail = node.getPostfixExpressionTail().toArray();

        		for (int i=0; i<tail.length; i++) {
                                next = ((AFeatureCall)((APostfixExpressionTail)tail[i]).getFeatureCall()).getPathName().toString().trim();

                                if (mc.isAttribute(next)) {
        				// next feature is an attribute
        				g = mc.getAttributeGuide(next);
        				if ((i==0) && (dcolon == -1)) g.setAlias(startFeature.toUpperCase());
        				navigation.put(tail[i], g);
            			} else if (mc.isAssociationEnd(next)) {
        				// next feature navigates to an association end
        				g = mc.getJoinGuide(next);
        				if ((i==0) && (dcolon == -1)) g.setAlias(startFeature.toUpperCase());
        				navigation.put(tail[i], g);
	       				mc = mc.getAssociationEnd(next);
        			} else if (oclTokens.indexOf(next) != -1) {
        				// next feature is an ocl feature that needs information about the preceeding navigation step
					if (g != null) {
						navigation.put(tail[i], g);
					}
        			} else break;
        		}
	  	}

	}

        public void inAFeaturePrimaryExpression(AFeaturePrimaryExpression node) {
                String task = (String)getIn(node);
    		if (task == null) return;
                MappedClass mc;
                String navCode;
                String thisCode = "";
                Guide g = (Guide)navigation.get(node);

                if (g != null) {
                    // for handling of inheritance only
                    // some navigation code for this node to build
    			mc = map.getMappedClass(constrainedType);
    			g.reset();

    			if (g.isNavigation()) {
    				// navigation with nested subqueries
    				navCode = task;
    				while (g.hasMoreSteps()) {
    					// get next navigation step
    					g.next();

    					// prepare the code agent
    					ca.reset();
        				ca.setArgument("column1", g.getSelect());
        				ca.setArgument("table", g.getFrom());
        				ca.setArgument("column2", g.getWhere());

        				// get code for next navigation step
        				try {
        					if ((!g.hasMoreSteps()) && (g.getAlias() != null)) {
        						// nested context
        						ca.setArgument("context_alias", g.getAlias());
        						ca.setArgument("context_key", g.getWhere());
        						navCode = replaceInString(navCode, task, ca.getCodeFor("feature_call", "nested_context"));
	       					} else {
        						// nested
        						ca.setArgument("nested_select", task);
        						navCode = replaceInString(navCode, task, ca.getCodeFor("feature_call", "nested"));
        					}
        				} catch (Exception e) {
        					System.err.println("CodeAgent failure at nested...: " + e.toString());
        				}
    				}
    				thisCode = navCode;
    			} else {
    				// navigation with unioned queries
    				g.next();

    				if (g.getAlias() != null) {
    					// attribute access without additional navigation
    					ca.reset();
                                        ca.setArgument("column1", g.getSelect());
    					ca.setArgument("table1", g.getFrom());
    					ca.setArgument("column2", g.getWhere());
    					ca.setArgument("context_alias", g.getAlias());
    					try {
        					thisCode = ca.getCodeFor("feature_call", "attribute_inherited");
        				} catch (Exception e) {
        					System.err.println("attribute_inherited: " + e.toString());
        				}
    				} else {
    					// attribute access with navigation
    					ca.reset();
    					ca.setArgument("column1", g.getSelect());
    					ca.setArgument("table1", g.getFrom());
    					ca.setArgument("column2", g.getWhere());
    					ca.setArgument("table2", task);


    					try {
        					thisCode = ca.getCodeFor("feature_call", "attribute_navigation");
        				} catch (Exception e) {
        					System.err.println("attribute_navigation: " + e.toString());
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
    		Guide g = (Guide)navigation.get(node.parent());


    		if ((g != null) && (oclTokens.indexOf(pathName) == -1)) {
    			// some navigation code for this node to build
    			mc = map.getMappedClass(constrainedType);
    			g.reset();

    			if (g.isNavigation()) {
                                // navigation with nested subqueries
    				navCode = task;
    				while (g.hasMoreSteps()) {
    					// get next navigation step
    					g.next();

    					// prepare the code agent
    					ca.reset();
        				ca.setArgument("column1", g.getSelect());
        				ca.setArgument("table", g.getFrom());
        				ca.setArgument("column2", g.getWhere());

        				// get code for next navigation step
        				try {
        					if ((!g.hasMoreSteps()) && (g.getAlias() != null)) {
        						// nested context
        						ca.setArgument("context_alias", g.getAlias());
        						ca.setArgument("context_key", g.getWhere());
        						navCode = replaceInString(navCode, task, ca.getCodeFor("feature_call", "nested_context"));
	       					} else {
        						// nested
        						ca.setArgument("nested_select", task);
        						navCode = replaceInString(navCode, task, ca.getCodeFor("feature_call", "nested"));
        					}
        				} catch (Exception e) {
        					System.err.println("CodeAgent failure at nested...: " + e.toString());
        				}
    				}
    				thisCode = navCode;
    			} else {
    				// navigation with unioned queries
    				g.next();

                                if (g.getAlias() != null) {
    					// attribute access without navigation
    					ca.reset();
    					ca.setArgument("context_alias", g.getAlias());
    					ca.setArgument("column", g.getSelect());
    					try {
        					thisCode = ca.getCodeFor("feature_call", "attribute_context");
        				} catch (Exception e) {
        					System.err.println("attribute_context: " + e.toString());
        				}
    				} else {
    					// attribute access with navigation
    					ca.reset();
    					ca.setArgument("column1", g.getSelect());
    					ca.setArgument("table1", g.getFrom());
    					ca.setArgument("column2", g.getWhere());
    					ca.setArgument("table2", task);


    					try {
        					thisCode = ca.getCodeFor("feature_call", "attribute_navigation");
        				} catch (Exception e) {
        					System.err.println("attribute_navigation: " + e.toString());
        				}
    				}
    			}
   		} else {
   			// maybe a special feature call
   			// get necessary mapping type information for code generation
   			AStandardDeclarator asd = null;
   			Node fcp = null;
   			String tableAlias = "";
   			String className = "";

   			try {
   				fcp = node.getFeatureCallParameters();
   				asd = (AStandardDeclarator)((AFeatureCallParameters)node.getFeatureCallParameters()).getDeclarator();

   				tableAlias = asd.getName().toString().trim();
   				className = theTree.getTypeFor(tableAlias, asd).toString().trim();
   				mc = map.getMappedClass(className);
   			} catch (Exception e) {
   				// just to avoid null pointer exceptions, if there is no StandardDeclarator
   			}

   			// generate guide if none is given
   			if (g == null) {
   				g = new Guide(false);
   				g.add(STANDARDKEY, "", "");
   			}

   			// generate code
   			// features that need to be mapped using the CLASS AND ATTRIBUTE pattern from UML'99
   			if (pathName.equals("allInstances")) {
   				ca.reset();
        			ca.setArgument("column", g.getSelect());
        			ca.setArgument("table", g.getFrom());

        			try {
        				thisCode = ca.getCodeFor("feature_call", "allInstances");
        			} catch (Exception e) {
        				System.err.println(e.toString());
        			}
   			}

   			// features that need to be mapped using the BASIC TYPE pattern from UML'99
   			if ((tailBegin instanceof ADotPostfixExpressionTailBegin) && (!pathName.equals("allInstances"))) {
   				ca.reset();
   				if (oclTokBasic1.indexOf(pathName) != -1) {
   					ca.setArgument("operand", task);
  				} else if (oclTokBasic2.indexOf(pathName) != -1) {
  					String taskAp = getUniqueTask();
  					if (fcp != null) setIn(fcp, taskAp);
  					ca.setArgument("operand1", task);
  					ca.setArgument("operand2", taskAp);
  				} else if (oclTokBasic3.indexOf(pathName) != -1) {
  					String op2, op3;

  					ca.setArgument("operand", task);

  					try {
  						AActualParameterList apl = (AActualParameterList)((AFeatureCallParameters)node.getFeatureCallParameters()).getActualParameterList();
  						op2 = apl.getExpression().toString().trim();
  						op3 = ((AActualParameterListTail)((apl.getActualParameterListTail().toArray())[0])).getExpression().toString().trim();

  						ca.setArgument("start", op2);
  						ca.setArgument("end", op3);
  					} catch (Exception e) {
  						System.err.println("Missing arguments for substring feature!");
					}
  				}

   				try {
   					spec = "basic_" + pathName;
        				thisCode += ca.getCodeFor("feature_call", spec);
        			} catch (Exception e) {
        				System.err.println("sum: " + e.toString());
        			}
   			}

   			// operations over collections
                        if (tailBegin instanceof AArrowPostfixExpressionTailBegin) {
                          String taskAp = "";
                          boolean codeForPathName = false;
                          thisCode = "";
                          ca.reset();

                          if (fcp != null) {
                            taskAp = getUniqueTask();
                            setIn(fcp, taskAp);
                          }

                          // features that need to be mapped using the COMPLEX PREDICATE pattern from UML'99
                          if ((pathName.equals("includes")) || (pathName.equals("excludes"))) {
   				ca.setArgument("element", taskAp);
        			ca.setArgument("column", g.getSelect());
        			ca.setArgument("table", task);
                                codeForPathName = true;
  			  } else if ((pathName.equals("includesAll")) || (pathName.equals("excludesAll"))) {
   				ca.setArgument("column", g.getSelect());
        			ca.setArgument("table1", taskAp);
        			ca.setArgument("table2", task);
                                codeForPathName = true;
         		  } else if ((pathName.equals("isEmpty")) || (pathName.equals("notEmpty"))) {
   				ca.setArgument("table", task);
                                codeForPathName = true;
   			  } else if ((pathName.equals("forAll")) || (pathName.equals("exists")) || (pathName.equals("isUnique"))) {
  				Iterator en = mc.getTables().iterator();
                                while (en.hasNext()) {
   					ca.reset();
        				ca.setArgument("table1", task);
        				ca.setArgument("table2", ((Table)en.next()).getTableName() + " " + tableAlias.toUpperCase());
        				ca.setArgument("column", g.getSelect());
        				ca.setArgument("expression", taskAp);

        				try {
        					if (!thisCode.equals("")) ca.enableConnector();
        					thisCode += ca.getCodeFor("feature_call", pathName);
        				} catch (Exception e) {
        					System.err.println("forAll: " + e.toString());
        				}
  				}
			  }

                          // features that need to be mapped using the BASIC VALUE pattern from UML'99
                          if (pathName.equals("sum")) {
   				ca.setArgument("column", g.getSelect());
        			ca.setArgument("table", task);
                                codeForPathName = true;
   			  } else if (pathName.equals("size")) {
   				ca.setArgument("column", g.getSelect());
        			ca.setArgument("table", task);
                                codeForPathName = true;
        	          } else if (pathName.equals("count")) {
   				ca.setArgument("column", g.getSelect());
        			ca.setArgument("table", task);
        			ca.setArgument("expression", taskAp);
                                codeForPathName = true;
   			  }

                          // features that need to be mapped using the QUERY pattern from UML'99
  			  Type type = theTree.getNodeType(node.parent());
  			  if (type != null) {
  				if (type instanceof Collection) {
  					if (((Collection)type).getCollectionKind() == Collection.SET) spec = "set";
  					if (((Collection)type).getCollectionKind() == Collection.BAG) spec = "bag";
  					if (((Collection)type).getCollectionKind() == Collection.SEQUENCE) spec = "sequence";
  				}
  			  }

  			  if ((pathName.equals("select")) || (pathName.equals("reject"))) {
  				Iterator en = mc.getTables().iterator();
   				while (en.hasNext()) {
   					ca.reset();
        				ca.setArgument("key", g.getSelect());
        				ca.setArgument("table1", task);
        				ca.setArgument("table2", ((Table)en.next()).getTableName() + " " + tableAlias.toUpperCase());
        				ca.setArgument("expression", taskAp);

        				try {
        					if (!thisCode.equals("")) ca.enableConnector();
        					thisCode += ca.getCodeFor("feature_call", pathName);
        				} catch (Exception e) {
        					System.err.println("select: " + e.toString());
        				}
        			}
 			  } else if (pathName.equals("intersection")) {
  					ca.setArgument("coll1", task);
  					ca.setArgument("coll2", taskAp);
                                        pathName = spec + "_intersection";
                                        codeForPathName = true;
 			  } else if (pathName.equals("including")) {
  					ca.setArgument("coll", task);
  					ca.setArgument("element", taskAp);
                                        pathName = spec + "_including";
                                        codeForPathName = true;
 			  } else if (pathName.equals("excluding")) {
  					ca.setArgument("coll", task);
  					ca.setArgument("column", g.getSelect());
  					ca.setArgument("element", taskAp);
                                        codeForPathName = true;
 			  } else if (pathName.equals("union")) {
  					ca.setArgument("coll1", task);
  					ca.setArgument("coll2", taskAp);
                                        pathName = spec + "_union";
                                        codeForPathName = true;
   			  } else if (pathName.equals("symmetricDifference")) {
  					ca.setArgument("coll1", task);
  					ca.setArgument("coll2", taskAp);
                                        codeForPathName = true;
 			  } else if (pathName.equals("collect")) {
  					ca.setArgument("column", g.getSelect());
  					ca.setArgument("table", task);
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