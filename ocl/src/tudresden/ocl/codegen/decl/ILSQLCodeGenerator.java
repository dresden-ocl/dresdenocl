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
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A class that generates SQL code out of a ocl contraint or the 
 * abstract syntax tree of the ocl contraint respectivly. This is the second 
 * version of a SQL code generator, formerly implemented as SQLCodeGenerator.
 * @author Sten Loecher
 */
public class ILSQLCodeGenerator extends DeclarativeCodeGenerator {
    
    // the name of the variables tell what are they supposed to contain
	String constrainedType;
    String constraintName;	
    /**
     * @key-type Node
     * @element-type List
     */
	Map navigation;
	Set operation;
    ORMappingScheme map;
    Set modelTypeFeatures;
    Set involvedTables;
    Hashtable declarators;
    int aliasCount;
    int joinAliasCount = 0;
    String tableRepresentation;
	String joinRepresentation;
    String joinTargetObject;
    boolean dMsg = false;

    // some information needed during the generation process
    String oclNotSupportedFeatures = "oclInState;oclIsNew;iterate";
    String oclCollectionOperations = "size;includes;excludes;count;includesAll;excludesAll;isEmpty;notEmpty;sum;exists;forAll;isUnique;sortedBy;union;intersection;including;excluding;symmetricDifference;select;reject;collect;count;asSequence;asBag;asSet;subSequence;at;first;last";
    String oclTypeOperations = "name;attributes;associationEnds;operations;supertypes;allSupertypes;allInstances";
    String oclAnyOperations = "oclIsKindOf;oclIsTypeOf;oclAsType";
	String oclTokBasic1 = "size;toUpper;toLower;abs;floor;round;";  // operations with one parameter
	String oclTokBasic2 = "max;min;div;mod;concat;";                // operations with two parameters
	String oclTokBasic3 = "substring;";                             // operations with more than two parameters
    String oclTokens = oclCollectionOperations + oclTokBasic1 + oclTokBasic2 + oclTokBasic3 + oclAnyOperations + oclTypeOperations;
        
    final static String dcolon = "::";
	final static String ALIAS = "TA";
    final static String SEQNO = "seqNo";
	final static String STANDARDKEY = "elem";
    final static String STANDARDTABLE = "DUAL";
    final static String COMPLEX_PREDICATE = "inlcudes;excludes;includesAll;excludesAll;isEmpty;notEmpty;exists;forAll;isUnique";

    /**
     *  true if the generated code should be formated, false otherwise
     */
	boolean formatCode = true;
    /**
     *  true if classic column joins should be generated, false if derived tables are needed
     */
    boolean joinMode = false; 
    /**
     *  true if operations should be translated by keeping the name, false if a special translation is specified
     */
    boolean genOpMode = false;
    /**
     *	true if the integrity query specifies the primary key for the constrained table, false otherwise
     */
    boolean keyMode = false;
     
    // constructor
	public ILSQLCodeGenerator(String rules) {
		super(rules);

		map = new ORTestScheme();
	}
	
	// helper methodes
    /**
     * Does some formating of the generated SQL code.
     */
	public void formatSQLCode() {
        int count, ind;
		String indent = "";
		Vector v = new Vector();
        boolean flag;

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
                
        // remove multiple linebreaks with just spaces or nothing between them
        flag = false;
        ind = 0;
        for (int i=0; i<code.length(); i++) {
            if (code.charAt(i) == '\n') {
                if (flag == true) code.setCharAt(ind,' '); 
                flag = true;
                ind = i;                        
            } else if (code.charAt(i) != ' ') {
                flag = false;
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
        
    /**
     *  @param map an object relational mapping schema 
     */
	public void setORMappingScheme(ORMappingScheme map) {
		this.map = map;
	}
	
	/**
	 *  Resets the ILSQLCodeGenerator object to initial values.
     *  Must be used, if multiple invariants will be translated 
     *  by one code generator object.
	 */
	public void reset() {
		fragments = new Vector();
		constrainedType = null;
    	constraintName = null;
		navigation = new HashMap();
		operation = new HashSet();
        modelTypeFeatures = new HashSet();
        involvedTables = new HashSet();
		declarators = new Hashtable();
		aliasCount = 0;
		joinAliasCount = 0;
	}
        
    /**
     *  Adds all tables to the involved tables list which are contained within the guides in the specified list.
     *  @param guides a List object containing Guide objects for the translation of navigation
     */
    private void updateInvolvedTables(List guides) {
        Guide guide;
        String tn;
        
        for (Iterator i=guides.iterator(); i.hasNext(); ) {
            guide = (Guide)i.next();
            guide.reset();
            while (guide.hasMoreSteps()) {
                guide.next();
                tn = guide.getFrom().trim();
                if (tn.length() > 0) involvedTables.add(tn);
            }
        }
    }
    
    /**
     *  Sets the join mode for the generation process. See variable joinMode for more information.
     */
    public void setJoinMode(boolean b) {
        joinMode = b;
    }
    
    /**
     *	Sets the key mode for the generation process. See variable keyMode for more information.
     */
    public void setKeyMode(boolean b) {
    	keyMode = b;
    }
        
    /**
	 * @pre guides.size() > 1
	 */
    public void prepareNavigation(List guides) {                    
        if (joinMode == true) {
            prepareClassicJoin(guides);
        } else {
            prepareDerivedTable(guides);
        }           
    }
		
	/**
	 * @pre guides.size() > 1
	 */
	public void prepareClassicJoin(List guides) {
		StringBuffer tables = new StringBuffer();
		StringBuffer joins = new StringBuffer();
		String select = "", from = "", where = "", tableAlias, firstTable = "", firstColumn = "", thisCode = "";
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
				
				// remember first table and select column
				if ((i == 0) && (k == 0)) {
					firstTable = tableAlias;
					firstColumn = guide.getSelect();
				}
				
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
                                involvedTables.add(new String(guide.getFrom()));
			
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
		
		try {
			ca.setArgument("table", firstTable );
			ca.setArgument("column", firstColumn);
			ca.setArgument("tables", tableRepresentation);
			ca.setArgument("joins", joinRepresentation);
		    thisCode = ca.getCodeFor("feature_call", "navigation_classic");  
		    joinRepresentation = "";
		    tableRepresentation = thisCode;                                  
		} catch (Exception e) {
		    throw new RuntimeException("navigation_classic: " + e.toString());
		}                                                                                   
	}
        
    /**
	 * @pre guides.size() > 1
	 */
    public void prepareDerivedTable(List guides) {
		String select = "", from = "", where = "", navExpr = "", koc, thisCode = "";
	    String ph = "$$_dt_$$";
		Guide guide;                           
	    tableRepresentation = "";
                                                   
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
                                
                    // add derived table to table representation
                    ca.reset();
                    ca.setArgument("object", guide.getSelect());
                    ca.setArgument("table1", guide.getFrom());
                    ca.setArgument("ref_object", guide.getWhere());
                    
                    // last step of whole derived table must be treated in a special way
                    if ((i == (guides.size() - 1))  && (k == (guide.numberOfSteps() - 1))) { 
                        ca.setArgument("context_alias", guide.getAlias());
                        ca.setArgument("context_object", guide.getWhere());
                        koc = "navigation_context";
                    } else {                                    
                        ca.setArgument("table2", ph);
                        koc = "navigation";
                    }				
                                
				try {
    			    thisCode = ca.getCodeFor("feature_call", koc);                                    
    			} catch (Exception e) {
    			    throw new RuntimeException("navigation: " + e.toString());
    			}                                                                
                                
                if (navExpr.length() == 0) {
                    navExpr = thisCode;
                } else {                                    
                    if (thisCode.endsWith("\n")) thisCode = thisCode.substring(0, thisCode.length()-1);
                    navExpr = replaceInString(navExpr, ph, thisCode);
                }                                                                
                
                // keep current step in mind to skip unnecessary joins later on
				select = guide.getSelect();
				from = guide.getFrom();
				where = guide.getWhere();
			}				
		}		
                
        tableRepresentation = navExpr;
		joinRepresentation = "";          
    }
	
    /**
     *  @return a String that contains all tables for a classic join to put into a from clause
     */
	public String getTableRepresentation() {
		return tableRepresentation;
	}
	
    /**
     *  @return a String that contains all join definitions to put into a where clause
     */
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

		constrainedType = node4.getPathTypeName().toString().trim();		
        }

    public void inAConstraintBody(AConstraintBody node) {
    	String expTask = getUniqueTask();
        constraintName = node.getName().toString().trim();     
       	Table table = (Table)map.getMappedClass(constrainedType).getTables().get(0);
                            
    	ca.reset();
    	if (keyMode) {
    		ca.setArgument("key", table.getPrimaryKeyRepresentation());
    	}
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
		String[] keys = ((Table)map.getMappedClass(constrainedType).getTables().get(0)).getPrimaryKeyColumns();
		String task = (String)getIn(node);

		if (mc != null) {
	        Iterator e = mc.getTables().iterator();
	        
			while (e.hasNext()) {
				// code generate a number of code fragments equal to the number of tables !!!
				String tableName = ((Table)e.next()).getTableName();
				replaceTask(task, tableName);
                involvedTables.add(tableName);
			}
			
		 	if (formatCode) formatSQLCode();
		 	//System.err.println("--> " + constrainedType + ":" + constraintName + "\n" + code.toString());       
            //System.err.println("--> " + constrainedType + ":" + constraintName);
            //System.err.println("--> involved Tables:" + involvedTables.toString());
			fragments.add(new SQLCodeFragment(constraintName, constrainedType, code.toString(), (String[])involvedTables.toArray(new String[involvedTables.size()]), keys));
		} else {
			//Error !!!
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
        				thisCode = ca.getCodeFor("relational_expression_tail", "equal_Any");
        			}
        		} else if (node.getRelationalOperator() instanceof ANEqualRelationalOperator) {
                    type = theTree.getNodeType(node.getAdditiveExpression());

                    if (type instanceof Basic) {
                            // Bascic types and Enumeration
                            thisCode = ca.getCodeFor("relational_expression_tail", "nequal_Basic");
                    } else {
                            // OclAny
                            thisCode = ca.getCodeFor("relational_expression_tail", "nequal_Any");
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
    	Guide g = null, guide = null;
    	List guides;
    	Node type;
        int dColInd;
        String inheritedFeature = "", typeName;
        boolean typeFeatSucc = false;
                                
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
                        if (theTree.getTypeFor(startFeature, pex) == null) {
                            startType = startFeature;
                        } else {    
                            startType = theTree.getTypeFor(startFeature, pex).toString().trim();
                        }
                    	featureName = null;
                }
                        
                // get MappedClass for start type
                mc = map.getMappedClass(startType);
                                                                
                // get guides to the feature starting from startType
                guides = new ArrayList();
                try {
                	if (mc.isAttribute(featureName)) {
                        guide = mc.getAttributeGuide(featureName);
                		guides.add(guide);
                	} else if (mc.isAssociationEnd(featureName)) {
                        guide = mc.getJoinGuide(featureName);
                		guides.add(guide);
                	}                        	
                } catch(NullPointerException e) {
                	// feature name is given in one of the postfix expression tails
                    guide = mc.getJoinGuide(mc.getClassName());
                    guide.setAlias(startType);
                }
                        
                // iterate the postfix expression tail and assign navigation guides
        		tail = node.getPostfixExpressionTail().toArray();
                        
        		for (int i=0; i<tail.length; i++) {
                    next = ((AFeatureCall)((APostfixExpressionTail)tail[i]).getFeatureCall()).getPathName().toString().trim();
                        
                    // special treatment for collect
                    if (next.equals("collect")) {
                    	
                    }
                                
                    if (mc.isAttribute(next)) {
        				// next feature is an attribute
        				guide = mc.getAttributeGuide(next);
        				if ((i==0) && (dColInd == -1)) guide.setAlias(startFeature.toUpperCase());
                      	guides.add(0, guide);    
                        typeFeatSucc = true;
                        modelTypeFeatures.add(tail[i]);
                        // special handling of complex attributes
                        typeName = theTree.getNodeType((Node)tail[i]).toString();
                        if (map.getMappedClass(typeName) != null) {
                        	mc = map.getMappedClass(typeName);
                        	if (guides.size() == 0) guides.add(guide);
        					navigation.put(tail[i], guides);
                        } 
            		} else if (mc.isAssociationEnd(next)) {
	    				// next feature navigates to an association end
	    				guide = mc.getJoinGuide(next);
	    				if ((i==0) && (dColInd == -1)) guide.setAlias(startFeature.toUpperCase());
	    				guides.add(0, guide);
	       				mc = mc.getAssociationEnd(next);                                        
                        typeFeatSucc = true;
                        modelTypeFeatures.add(tail[i]);
        			} else if (oclTokens.indexOf(next) != -1) {
        				// next feature is an ocl feature --> assign guides list to last tail if it was an association end
                        if (typeFeatSucc) navigation.put(tail[i-1], guides);
                        // each ocl feature gets last determined guide information
                        if (guides.size() == 0) guides.add(guide);
                        navigation.put(tail[i], guides);                
        			} else if (mc.isQuery(next)) {
        				operation.add(next);
        				modelTypeFeatures.add(tail[i]);
        			}
        			
        			// assign guides list to last postfix expression tail (necessary for type features without successor)
        			if (i == (tail.length-1)) {
        				if (guides.size() == 0) guides.add(guide);
        				navigation.put(tail[i], guides);
        			}      
                                
                    // assign guides list to primary expression, if just one tail exists that is an ocl token
                    if ((tail.length == 1) && (typeFeatSucc == false)) { 
                    	if (guides.size() == 0) guides.add(guide);
                        navigation.put(pex, guides);
                    }                                
        		}
                        
                // if no tail exists, assign the guides list to the primary expression
                if (tail.length == 0) { 
                    if (guides.size() == 0) guides.add(guide);
                    navigation.put(pex, guides);
                }
                
                updateInvolvedTables(guides);
	  	}

	}

    public void inAFeaturePrimaryExpression(AFeaturePrimaryExpression node) {
        String task = (String)getIn(node);
		if (task == null) return;
        String thisCode = "";
        List guides = (List)navigation.get(node);
        Guide guide;

        if (guides != null) {
            guide = (Guide)guides.get(0);
            guide.reset();
            guide.next();
            
            ca.reset();
            ca.setArgument("context_alias", node.getPathName().toString().trim().toUpperCase());
            ca.setArgument("column", guide.getSelect());
            
            try {
    	        thisCode = ca.getCodeFor("feature_call", "attribute_context");
    	    } catch (Exception e) {
    	        throw new RuntimeException("attribute_context at feature primary expression: " + e.toString());
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
		Guide guide;
		String pkName = "", pkTable = "", pkClassType = "", tmpStr;
		boolean codeForPathName = false;
		AStandardDeclarator asd = null;
   		Node fcp = null;
   		String tableAlias = "";
   		String className = "";
   		String taskAp = "";
   		Table table = new Table("dummy");
        Set metaInfo = null;
        String opSpec = "", parameter = "";
        PFeatureCallParameters fcps;
        AActualParameterList aapl;
        LinkedList ll;
        String opClass = "";
    	
		List guides = (List)navigation.get(node.parent());
        if (dMsg) System.err.println("current pathName: " + pathName + " task:" + task);
        //if (guides == null) {
        //    System.err.println("No guides available !");
        //} else {
        //    if (guides.size() == 0) System.err.println("Empty guide list !");
        //}

		// translate access to query operation 
		if ((modelTypeFeatures.contains(node.parent())) && (operation.contains(pathName))) {
			modelTypeFeatures.remove(node.parent());
			
			// reset code agent
			ca.reset();
						
			// determine operation type
			if (genOpMode) {
				opSpec="operation_general";	
				ca.setArgument("operation", pathName);
			} else {
				opSpec="operation_" + pathName;
			}
			
			// treatment of parameters
			fcps = node.getFeatureCallParameters();
			if (fcps instanceof AConcreteFeatureCallParameters)	{
				System.err.println("AConcreteFeatureCallParameters" + " for " + pathName);
				// not supported yet
			} else if (fcps instanceof AEmptyFeatureCallParameters) {
				System.err.println("AEmptyFeatureCallParameters" + " for " + pathName);
				// nothing to do anyway
			} else if (fcps instanceof AFeatureCallParameters) {
				// get names of parameters and prepare code agent with them
				aapl = (AActualParameterList)((AFeatureCallParameters)fcps).getActualParameterList();
				try {
					// first parameter
					parameter = getUniqueTask();
					setIn(aapl.getExpression(), parameter);
					ca.setArgument("parameter", parameter);
								
					// all parameters from the second
					ll = aapl.getActualParameterListTail();
					for (int i=0; i<ll.size(); i++) {
						parameter = getUniqueTask();
						setIn((AActualParameterListTail)ll.get(i), parameter);
						ca.setArgument("parameter" + (i+1), parameter);
					}
				} catch(NullPointerException ex) {
					// in case there are no parameters
				}
			}		
						
			ca.setArgument("object", task);
						
			// get code	
			try {
				thisCode = ca.getCodeFor("feature_call", opSpec);
			} catch (Exception e) {
				throw new RuntimeException(opSpec + ": " + e.toString());
			}			
		} 
		
		// attributes and navigations
		if (modelTypeFeatures.contains(node.parent())) {
	    	modelTypeFeatures.remove(node.parent());
    		
		    // quit if no guides list is available or complain about empty list
			if (guides == null) return;
			if (guides.size() == 0) throw new RuntimeException("empty guides list at " + pathName);
			    			    			    			
			// generate target code from guide
			guide = (Guide)guides.get(0);
	        guide.reset();
			guide.next();
			    			   				
			if (guide.isNavigation()) {	
	            // navigation only
	            prepareNavigation(guides);
	            thisCode = tableRepresentation;
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
					prepareNavigation(guides);
	                thisCode = tableRepresentation;
				}
                        
	            // special treatment for boolean values
	            if (theTree.getNodeType(node.parent()).conformsTo(Basic.BOOLEAN)) {
	                ca.reset();
	                ca.setArgument("attribute", thisCode);
	                try {
						thisCode = ca.getCodeFor("feature_call", "attribute_boolean");
					} catch (Exception e) {
						throw new RuntimeException("attribute_boolean: " + e.toString());
					}
				}                                                                   		
			}
   		} else if (!operation.contains(pathName)) {
   			if (dMsg) System.err.println("... treated in general case");
   			
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
                pkTable = guide.getFrom();
                pkClassType = guide.getAlias();
                /*
                try {
                    prepareJoin(guides);                      
                } catch (Exception e) {
                    // some guide lists are not supposed to serve joins 
                    // in that case, the last guide has no context alias specified                                
                }
                */
            } else {
	            pkName = STANDARDKEY;
	            pkTable = STANDARDTABLE;
            }                        
   			
   			// generate code
            ca.reset();
                        
   			// features that need to be mapped using the MODEL TYPE QUERY                        
           	if (pathName.equals("allInstances")) {                       
   				ca.setArgument("object", pkName);
    			ca.setArgument("tables", pkTable);
                codeForPathName = true;
            } else if (pathName.equals("name")) {
                ca.setArgument("name", pkClassType);
                codeForPathName = true;
            } else if ("attributes;associationEnds;operations;supertypes;allSupertypes".indexOf(pathName) != -1) {
                // get the meta information 
                mc = map.getMappedClass(pkClassType);
                                                          
                if (pathName.equals("associationEnds")) {
                    metaInfo = mc.associationEnds();
                } else if (pathName.equals("operations")) {
                    metaInfo = mc.operations();
                } else if (pathName.equals("supertypes")) {
                    metaInfo = mc.supertypes();
                } else if (pathName.equals("allSupertypes")) {
                    metaInfo = mc.allSupertypes();
                } else if (pathName.equals("attributes")) {
                    metaInfo = mc.attributes();
                }
                                
                // treatment of empty meta information sets
                if (metaInfo.size() == 0) {
                    metaInfo = new HashSet();
                    metaInfo.add("");
                }
                                
                // build target code
                for (Iterator i=metaInfo.iterator(); i.hasNext(); ) {
                    tmpStr = (String)i.next();
                    ca.setArgument("name", tmpStr); 
                                                      
                    try {
                        thisCode += ca.getCodeFor("feature_call", pathName);
                    } catch (Exception e) {
		                System.err.println(e.toString());
		            }
                    
                    ca.reset();
                    ca.enableConnector();
                }
            } 
                        
            // features on OclAny --> need to be mapped using the CLASS AND ATTRIBUTE pattern
            if (pathName.equals("oclIsKindOf")) {                                
                    ca.setArgument("object", pkName); 
                    ca.setArgument("table", getOclTypeTable(node).getTableName()); 
                    ca.setArgument("context_object", task);
                    codeForPathName = true;
            } else if (pathName.equals("oclIsTypeOf")) {
                    ca.setArgument("object", pkName); 
                    ca.setArgument("table", getOclTypeTable(node).getTableName()); 
                    ca.setArgument("context_object", task);
                    
                    if (getOclSupertypeTable(node) == null) {
                        ca.setArgument("table2", "foo"); 
                    } else {
                        ca.enableConnector();
                        ca.setArgument("table2", getOclSupertypeTable(node).getTableName()); 
                    }
                    
                    codeForPathName = true;
            } else if (pathName.equals("oclAsType")) {
                    throw new IllegalStateException("Operation oclAsType not supported yet !");
            }
                        
            // features that need to be mapped using the BASIC TYPE pattern from UML'99
            if (dMsg) System.err.println("this :" + thisCode + "< " + pathName + " ::: " + (tailBegin instanceof ADotPostfixExpressionTailBegin));
   			if ((tailBegin instanceof ADotPostfixExpressionTailBegin) &&
   			    (!pathName.equals("allInstances")) && 
   			    (thisCode.equals("")) &&
   			    ((oclTokBasic1 + oclTokBasic2 + oclTokBasic3).indexOf(pathName) != -1)) {
   				ca.reset();
   				if (oclTokBasic1.indexOf(pathName) != -1) {
   					ca.setArgument("operand", task);
  				} else if (oclTokBasic2.indexOf(pathName) != -1) {
  					taskAp = getUniqueTask();
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
            	ca.reset();

                if (fcp != null) {
            	taskAp = getUniqueTask();
	        	setIn(fcp, taskAp);
        	}	
   			
			// features that need to be mapped using the COMPLEX PREDICATE pattern from UML'99
          	if ((pathName.equals("includes")) || (pathName.equals("excludes"))) {
                ca.reset();
                ca.setArgument("collection", task);
			    ca.setArgument("object", taskAp);
			    codeForPathName = true;
          	} else if ((pathName.equals("includesAll")) || (pathName.equals("excludesAll"))) {
                ca.reset();
                ca.setArgument("collection", task);
			    ca.setArgument("collection2", taskAp);
			    codeForPathName = true;
		  	} else if ((pathName.equals("isEmpty")) || (pathName.equals("notEmpty"))) {
                ca.reset();
                ca.setArgument("collection", task);
			    codeForPathName = true;
     		} else if ((pathName.equals("forAll")) || (pathName.equals("exists")) || (pathName.equals("isUnique"))) {
  				    ca.reset();
	                ca.setArgument("collection", task);
				    ca.setArgument("object", pkName);
				    ca.setArgument("table", table.getTableName() + " " + tableAlias.toUpperCase());
				    ca.setArgument("expression", taskAp);
				    codeForPathName = true;
			}
                                
            // features that need to be mapped using the QUERY pattern from UML'99
	        Type type = theTree.getNodeType(node.parent());
	        if (type != null) {
			    if (type instanceof Collection) {
					if (((Collection)type).getCollectionKind() == Collection.SET) spec = "set";
					if (((Collection)type).getCollectionKind() == Collection.BAG) spec = "bag";
		            if (((Collection)type).getCollectionKind() == Collection.SEQUENCE) { 
		                spec = "sequence";
		                pathName = spec + "_" + pathName;
		            }
			    }
	        }
                                                               
	        if ((pathName.equals("select")) || (pathName.equals("reject"))) {
                ca.reset();
                ca.setArgument("collection", task);
			    ca.setArgument("object", pkName);
			    ca.setArgument("table", table.getTableName() + " " + tableAlias.toUpperCase());
			    ca.setArgument("expression", taskAp);   
	            codeForPathName = true;
	        } else if (pathName.equals("intersection")) { 
                ca.reset();
                ca.setArgument("collection", task);
                ca.setArgument("collection2", taskAp);
                pathName = spec + "_intersection";
                codeForPathName = true;
			} else if ((pathName.equals("including")) || (pathName.equals("excluding"))) {
                ca.reset();
                ca.setArgument("collection", task);
			    ca.setArgument("object", taskAp);
	            pathName = spec + "_" + pathName;
	            codeForPathName = true;
		    } else if (pathName.equals("union")) { 
                ca.reset();
                ca.setArgument("collection", task);
                ca.setArgument("collection2", taskAp);
                pathName = spec + "_" + pathName;
                codeForPathName = true;
	        } else if (pathName.equals("symmetricDifference")) {  
	            ca.reset();
	            ca.setArgument("collection", task);
	            ca.setArgument("collection2", taskAp);
	            pathName = pathName;
	            codeForPathName = true;
		    } else if (pathName.equals("collect")) {  
                //ca.reset();
                //ca.setArgument("collection", task);
			    //ca.setArgument("object", pkName);
			    //ca.setArgument("table", table.getTableName() + " " + tableAlias.toUpperCase());
			    //System.err.println(guides.toString());
			    ca.setArgument("expression", "<collect>");   
	            codeForPathName = true;
	        }
                                
            // features that need to be mapped using the SEQUENCE pattern
            if (pathName.equals("sequence_union")) {
                ca.reset();
                ca.setArgument("sequence", task);
                ca.setArgument("sequence2", taskAp);
                ca.setArgument("seqNo", SEQNO);
                ca.setArgument("seqNo2", SEQNO);
                ca.setArgument("element", pkName);
                codeForPathName = true;
            } else if (pathName.equals("sequence_including")) {
                ca.reset();
                ca.setArgument("sequence", task);
                ca.setArgument("object", taskAp);
                ca.setArgument("seqNo", SEQNO);
                ca.setArgument("seqNo2", SEQNO);
                codeForPathName = true;
            } else if (pathName.equals("sequence_excluding")) {
                ca.reset();
                ca.setArgument("sequence", task);
                ca.setArgument("object", taskAp);
                ca.setArgument("element", pkName);
                ca.setArgument("seqNo", SEQNO);
                ca.setArgument("seqNo2", SEQNO);
                codeForPathName = true;
            } // --> todo
                                
            // features that need to be mapped using the BASIC VALUE pattern from UML'99
            if (pathName.equals("size")) {
                ca.reset();
			    ca.setArgument("table", pkTable);
                ca.setArgument("element", pkName);
                ca.setArgument("collection", task);
                codeForPathName = true;
	        } else if (pathName.equals("count")) {
			    ca.reset();
                ca.setArgument("table", pkTable);
                ca.setArgument("element", pkName);
                ca.setArgument("object", taskAp);
                ca.setArgument("collection", task);
                codeForPathName = true;
	        } else if (pathName.equals("sum")) {                                    
			    ca.reset();
                ca.setArgument("table", pkTable);
                ca.setArgument("element", pkName);
                ca.setArgument("collection", task);
                codeForPathName = true;
	        }			  
        }
		}
                
	    // query code agent for target code
	    if (codeForPathName) {
	        try {
	            thisCode = ca.getCodeFor("feature_call", pathName);
	        } catch (Exception e) {
			    System.err.println(e.toString());
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
				ca.setArgument("seqnr", i + 1 + "");
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
        
    // some helper functions
        
    /**
     *  @return the table/view that belongs to the type for expression:
     *          FeatureCall->FeatureCallParameters->ActualParameterList->Expression
     */
    private Table getOclTypeTable(AFeatureCall node) 
    throws IllegalStateException {
        String typeName;
        AExpression aexp;
        MappedClass mc;
        
        aexp = (AExpression)((AActualParameterList)((AFeatureCallParameters)node.getFeatureCallParameters()).getActualParameterList()).getExpression();
        typeName = ((OclType)theTree.getNodeType(aexp)).getType().toString();
        mc = map.getMappedClass(typeName);
        if (mc.getTables().size() != 1) throw new IllegalStateException("Illegal number of class tables for type: " + typeName + " !");
                                
        return ((Table)mc.getTables().get(0));
    }
        
    /**
     *  Note: If the type for expression does not have a supertype, a null value will be returned.
     *  @return the table/view that belongs to the supertype for expression:
     *          FeatureCall->FeatureCallParameters->ActualParameterList->Expression
     */
    private Table getOclSupertypeTable(AFeatureCall node) 
    throws IllegalStateException {
        String typeName;
        AExpression aexp;
        MappedClass mc = null;            
        
        aexp = (AExpression)((AActualParameterList)((AFeatureCallParameters)node.getFeatureCallParameters()).getActualParameterList()).getExpression();
        typeName = ((OclType)theTree.getNodeType(aexp)).getType().toString();
        mc = map.getMappedClass(typeName);            
        
        if (mc.supertypes().size() == 0) return null; 
        if (mc.supertypes().size() > 1) throw new IllegalStateException("Illegal number of supertypes for type: " + typeName + " !");
        mc = map.getMappedClass((String)mc.supertypes().iterator().next());
        if (mc.getTables().size() != 1) throw new IllegalStateException("Illegal number of class tables for supertype of: " + typeName + " !");            
                                
        return ((Table)mc.getTables().get(0));
    }
}
