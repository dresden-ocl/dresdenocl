/*
 * SQLCodeGenerator.java
 * 
 * Copyright (c) 2005 Florian Heidenreich
 * Contact: <mail@fheidenreich.de>
 *
 * This file is part of the Dresden OCL2.0 Toolkit
 * created at Technische Universitaet Dresden (TUD), Germany.
 * Visit http://dresden-ocl.sourceforge.net/ for details.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston,
 * MA  02111-1307  USA
 *
 */

package tudresden.ocl20.codegen.sql.codegen;

import java.util.*;

import tudresden.ocl20.codegen.sql.orm.ORMappingScheme;
import tudresden.ocl20.codegen.sql.orm.Table;
import tudresden.ocl20.core.jmi.ocl.commonmodel.*;
import tudresden.ocl20.core.jmi.ocl.expressions.*;
import tudresden.ocl20.core.jmi.ocl.types.*;
import tudresden.ocl20.core.util.ReflectiveVisitor;

/**
 * The SQLCodeGenerator creates SQL integrity views for OclExpressions.
 * To access the object relational mapping this generator makes use of
 * the object view concept implemented by ObjectViewSchema. The mapping
 * of OCL invariants to SQL code is done by the class CodeAgent.
 * 
 * @author Florian Heidenreich
 * @see tudresden.ocl20.codegen.sql.codegen.CodeAgent
 * @see tudresden.ocl20.codegen.sql.codegen.ObjectViewSchema
 */
public class SQLCodeGenerator extends ReflectiveVisitor {
	
	private static final String SEQNO = "SEQNO";

	private final String ALIAS = "TA";
	
	private int aliasCount = 0;
	
	private LinkedList<String> aliasList;

	/** CodeAgent to create parameterized SQL code templates */
	private CodeAgent codeAgent;
	
	/** type of the current contextual classifier */
	private String constrainedType;
	
	/** name of the current constraint */
    private String constraintName;
	
	/** Set of tables involved with the current constraint */
	private Set<String> involvedTables;
	
	/** counter to create unique join aliases */
	private int joinAliasCount = 0;
	/**
	 * true if classic column joins should be generated, false if derived tables are needed.
	 * At the moment only derived tables are well tested.
     */
    private boolean joinMode = false;
	/** holds the current code necessary for table navigation */
	private String joinRepresentation;
	
	private String joinTargetObject;
	
	/** true if the integrity query specifies the primary key for the constrained table, false otherwise */
    private boolean keyMode = false;
	
	/** Mapping between OclExpression instances and their corresponding navigation guides */
	private Map<OclExpression, List<Guide>> navigation;
	
	private String tableRepresentation;
	/** object relational mapping scheme */
	private ORMappingScheme theMap;
	
	/**
	 * Creates a new SQLCodeGenerator. 
	 * 
	 * @param rules an URL of the file containing the mapping rules
	 * @see tudresden.ocl20.codegen.sql.codegen.CodeAgent
	 */
	public SQLCodeGenerator(String rules) {
		super("transform");
		reset();
		codeAgent = new CodeAgent(rules);
		aliasList = new LinkedList<String>();
		aliasList.add(getUniqueAlias());
	}
	
	/**
	 * Assigns navigation guides to instances of AttributeCallExp and AssociationEndCallExp 
	 * and generates all guides to the end of the expression.
	 * 
	 * @param expression for which guides should be determined and assigned 
	 */
	private void assignGuides(OclExpression exp) {
		Guide guide = null;
		List<Guide> guides;
		MappedClass mc;
		String featureName = "";
		String startType = "";
		String startFeature = "";		
		
		if (exp == null || navigation.containsKey(exp)) {
			return;
		}
		
		// this instanceof switching is a little bit ugly. A special visitor can be used later		
		if (exp instanceof AttributeCallExp || exp instanceof AssociationEndCallExp || exp instanceof AssociationClassCallExp) {
			
			OclExpression next = null;
						
			if (exp instanceof AttributeCallExp) {
				AttributeCallExp ac = (AttributeCallExp)exp;
				next = ac.getSource();
				featureName = ac.getReferredAttribute().getNameA();
				startType = next.getType().getNameA();				
			} else if (exp instanceof AssociationEndCallExp) {
				AssociationEndCallExp ae = (AssociationEndCallExp)exp;
				next = ae.getSource();
				featureName = ae.getReferredAssociationEnd().getNameA();
				startType = next.getType().getNameA();				
			} else if (exp instanceof AssociationClassCallExp) {
				AssociationClassCallExp acc = (AssociationClassCallExp)exp;
				next = acc.getSource();
				featureName = acc.getReferredAssociationClass().getNameA();
				startType = next.getType().getNameA();
			}
			
			startFeature = featureName;
			
			// get MappedClass for start type
			mc = theMap.getMappedClass(startType);
			
			// get guides to the feature starting from startType
			guides = new ArrayList<Guide>();

            try {
            	if (mc.isAttribute(featureName)) {
					guide = mc.getAttributeGuide(featureName);
					guides.add(guide);
            	} else if (mc.isAssociationEnd(featureName)) {
					guide = mc.getJoinGuide(featureName);
					guides.add(guide);
            	}
            } catch(NullPointerException e) {
				guide = mc.getJoinGuide(mc.getClassName());
				guide.setAlias(startType);
            }
		
			int i = 0;
			
			while (next != null && (next instanceof AttributeCallExp || next instanceof AssociationEndCallExp || next instanceof AssociationClassCallExp)) {
				
				OclExpression tmpNext = null;
				
				if (next instanceof AttributeCallExp) {
					AttributeCallExp ac = (AttributeCallExp)next;
					tmpNext = ac.getSource();
					featureName = ac.getReferredAttribute().getNameA();
					startType = tmpNext.getType().getNameA();				
				} else if (next instanceof AssociationEndCallExp) {
					AssociationEndCallExp ae = (AssociationEndCallExp)next;
					tmpNext = ae.getSource();
					featureName = ae.getReferredAssociationEnd().getNameA();
					startType = tmpNext.getType().getNameA();				
				} else if (exp instanceof AssociationClassCallExp) {
					AssociationClassCallExp acc = (AssociationClassCallExp)exp;
					tmpNext = acc.getSource();
					featureName = acc.getReferredAssociationClass().getNameA();
					startType = tmpNext.getType().getNameA();
				}
				
				mc = theMap.getMappedClass(startType);
				
				if (mc.isAttribute(featureName)) {
					// next feature is an attribute
					guide = mc.getAttributeGuide(featureName);
					if (i == 0) guide.setAlias(startFeature.toUpperCase());
					guides.add(guide);    
				} else if (mc.isAssociationEnd(featureName)) {
					// next feature navigates to an association end
					guide = mc.getJoinGuide(featureName);
					if (i == 0) guide.setAlias(startFeature.toUpperCase());
					guides.add(guide);
					mc = mc.getAssociationEnd(featureName);                                        
				}
				
				// assign guides list to last source (necessary for type features without successor)
				if (tmpNext == null) {
					if (guides.size() == 0) {
						guides.add(guide);
					}
					navigation.put(next, guides);
				}      
				
				next = tmpNext;
				i++;
			}
			
			// assign alias to last element
			if (next == null || next instanceof VariableExp) {
				guide.setAlias(aliasList.getLast());
			}
			
			// if no source exists, assign the guides list to the original expression
			if (guides.size() == 0) {
				guides.add(guide);
			}
			navigation.put(exp, guides);
			
			updateInvolvedTables(guides);
		}
	}
	
	/**
	 * This helper method creates a Guide for a given static Classifier
	 * @param type the Classifier for which the Guide is to be created
	 * @return the Guide for the given static Classifier
	 */
	private ArrayList<Guide> createGuidesForClassifier(Classifier type) {
		ArrayList<Guide> guides = new ArrayList<Guide>();
		Guide g = new Guide(false);
		MappedClass mc = theMap.getMappedClass(type.getNameA());
		
		String objects = "", tables = "";
		for (Table t : mc.getTables()) {
			tables += t.getTableName() + ", ";
			objects += t.getPrimaryKeyRepresentation() + ", ";
		}
		if (tables.length() > 0) {
			tables = tables.substring(0, tables.length() - 2);
		}
		if (objects.length() > 0) {
			objects = objects.substring(0, objects.length() - 2);
		}
		
		g.add(objects, tables, "");	
		guides.add(g);
		
		return guides;
	}
		
	/**
	 * Generates SQL Code for an ExpressionInOcl.
	 * 
	 * @param exp the ExpressionInOcl 
	 */
    public String getCode(ExpressionInOcl exp) {
        StringBuffer result = new StringBuffer("");

		try {
			result.append((String) visit(exp));
		} catch (NoSuchMethodException e) {
			// some  method is missing in the visitor implementation
			e.printStackTrace();
		} catch (java.lang.reflect.InvocationTargetException e) {
			e.getTargetException().printStackTrace();
		}

        return result.toString();
    }
	
    /**
	 * Generates SQL Code for an OclExpression.
	 * 
	 * @param exp the OclExpression
	 */
    public String getCode(OclExpression exp) {
        StringBuffer result = new StringBuffer("");
		
        if (exp != null) {
			try {
				result.append((String) visit(exp));
			} catch (NoSuchMethodException e) {
				// some  method is missing in the visitor implementation
				e.printStackTrace();
			} catch (java.lang.reflect.InvocationTargetException e) {
				e.getTargetException().printStackTrace();
			}
        }

        return result.toString();
    }
	
	/**
     *  Returns a string that contains all join definitions to put into a where clause.
     *  puplic for use in TestCases.
     *  
     *  @return a String that contains all join definitions to put into a where clause
     */
	public String getJoinRepresentation() {
		return joinRepresentation;
	}

	private Table getOclSupertypeTable(Classifier type) throws IllegalStateException {
    	MappedClass mc = theMap.getMappedClass(type.getNameA());            
        
        if (mc.supertypes().size() == 0) return null; 
        if (mc.supertypes().size() > 1) {
        	throw new IllegalStateException("Illegal number of supertypes for type: " + type.getNameA() + " !");
        }
        mc = theMap.getMappedClass((String)mc.supertypes().iterator().next());
        if (mc.getTables().size() != 1) {
        	throw new IllegalStateException("Illegal number of class tables for supertype of: " + type.getNameA() + " !");            
        }
                                
        return mc.getTables().get(0);
    }

	private Table getOclTypeTable(Classifier type) throws IllegalStateException {
    	MappedClass mc = theMap.getMappedClass(type.getNameA());
        
    	if (mc == null || mc.getTables().size() != 1) {
    		throw new IllegalStateException("Illegal number of class tables for type: " + type.getNameA() + " !");
    	}
                                
        return mc.getTables().get(0);
    }

	/**
     *  Returns a String that contains all tables for a classic join to put into a from clause
     *  puplic for use in TestCases.
     *  
     *  @return a String that contains all tables for a classic join to put into a from clause
     */
	public String getTableRepresentation() {
		return tableRepresentation;
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
	 * Parameterizes the CodeAgent for a minus operation
	 * 
	 * @param codeSrcExp the code of the source expression (argument 1)
	 * @param args the code of all other arguments
	 * @param rule will be changed to the appropriate rule for use with the CodeAgent
	 * @param spec will be changed to the appropriate spec for use with the CodeAgent
	 * @pre args.size()==1
	 */
	private void handleAddMinus(String codeSrcExp, List<String> args, StringBuffer rule, StringBuffer spec) {
		assert(args.size() == 1) : "Additive expression - needs exactly one argument";
		
		codeAgent.setArgument("mult_exp_1", codeSrcExp);
		codeAgent.setArgument("mult_exp_2", args.get(0));
		rule.append("additive_expression_tail");
		spec.append("minus");		
	}

	/**
	 * Parameterizes the CodeAgent for a plus operation
	 * 
	 * @param codeSrcExp the code of the source expression (argument 1)
	 * @param args the code of all other arguments
	 * @param rule will be changed to the appropriate rule for use with the CodeAgent
	 * @param spec will be changed to the appropriate spec for use with the CodeAgent
	 * @pre args.size()==1
	 */
	private void handleAddPlus(String codeSrcExp, List<String> args, StringBuffer rule, StringBuffer spec) {
		assert(args.size() == 1) : "Additive expression + needs exactly one argument";
		
		codeAgent.setArgument("mult_exp_1", codeSrcExp);
		codeAgent.setArgument("mult_exp_2", args.get(0));
		rule.append("additive_expression_tail");
		spec.append("plus");		
	}

	private void handleAllInstances(Guide g, StringBuffer rule, StringBuffer spec) {
		codeAgent.reset();
		codeAgent.setArgument("object", g.getSelect());
		codeAgent.setArgument("tables", g.getFrom());
		
		rule.append("feature_call");
		spec.append("allInstances");
	}

	private void handleCollCount(String codeSrcExp, Guide guide, List<String> args, StringBuffer rule, StringBuffer spec) {
		assert(args.size() == 1) : "count() needs exactly one argument";
		assert(guide != null) : "count(): guide may not be null";
		
		guide.reset();
		guide.next();
		
		codeAgent.reset();
		codeAgent.setArgument("table", guide.getFrom());
        codeAgent.setArgument("element", guide.getSelect());
        codeAgent.setArgument("collection", codeSrcExp);
        codeAgent.setArgument("object", args.get(0));
		rule.append("feature_call");
		spec.append("count");	
	}

	private void handleCollExcludes(String codeSrcExp, List<String> args, StringBuffer rule, StringBuffer spec) {
		assert(args.size() == 1) : "excludes() needs exactly one argument";
		
		codeAgent.reset();
		codeAgent.setArgument("object", args.get(0));
		codeAgent.setArgument("collection", codeSrcExp);
		
		rule.append("feature_call");
		spec.append("excludes");
	}
	
	private void handleCollExcludesAll(String codeSrcExp, List<String> args, StringBuffer rule, StringBuffer spec) {
		assert(args.size() == 1) : "excludesAll() needs exactly one argument";
		
		codeAgent.reset();
		codeAgent.setArgument("collection", codeSrcExp);
		codeAgent.setArgument("collection2", args.get(0));
		
		rule.append("feature_call");
		spec.append("excludesAll");		
	}
	
	private void handleCollExcluding(String codeSrcExp, List<String> args, StringBuffer rule, StringBuffer spec) {
		assert(args.size() == 1) : "excluding() needs exactly one argument";
		assert(spec.length() > 0) : "excluding() spec is empty (should be set or bag)";
		
		codeAgent.reset();
		codeAgent.setArgument("object", args.get(0));
		codeAgent.setArgument("collection", codeSrcExp);
		
		rule.append("feature_call");
		spec.append("_excluding");		
	}
	
	private void handleCollExists(String codeSrcExp, Guide guide, List<String> args, StringBuffer rule, StringBuffer spec) {
		assert(args.size() == 1) : "exists() needs exactly one argument";
		assert(guide != null) : "exists(): guide may not be null";
		
		guide.reset();
		guide.next();
			
		codeAgent.reset();
		codeAgent.setArgument("table", guide.getFrom());
        codeAgent.setArgument("object", guide.getSelect());
        codeAgent.setArgument("collection", codeSrcExp);
        codeAgent.setArgument("expression", args.get(0));
		rule.append("feature_call");
		spec.append("exists");
	}
	
	private void handleCollIncludes(String codeSrcExp, List<String> args, StringBuffer rule, StringBuffer spec) {
		assert(args.size() == 1) : "includes() needs exactly one argument";
		
		codeAgent.reset();
		codeAgent.setArgument("object", args.get(0));
		codeAgent.setArgument("collection", codeSrcExp);
		
		rule.append("feature_call");
		spec.append("includes");
	}

	private void handleCollIncludesAll(String codeSrcExp, List<String> args, StringBuffer rule, StringBuffer spec) {
		assert(args.size() == 1) : "includesAll() needs exactly one argument";
		
		codeAgent.reset();
		codeAgent.setArgument("collection", codeSrcExp);
		codeAgent.setArgument("collection2", args.get(0));
		
		rule.append("feature_call");
		spec.append("includesAll");
	}

	private void handleCollIncluding(String codeSrcExp, List<String> args, StringBuffer rule, StringBuffer spec) {
		assert(args.size() == 1): "including() needs exactly one argument";
		assert(spec.length() > 0): "including() spec is empty (should be set or bag)";
		
		codeAgent.reset();
		codeAgent.setArgument("object", args.get(0));
		codeAgent.setArgument("collection", codeSrcExp);
		
		rule.append("feature_call");
		spec.append("_including");		
	}

	private void handleCollIntersection(String codeSrcExp, List<String> args, StringBuffer rule, StringBuffer spec) {
		assert(args.size() == 1): "intersection() needs exactly one argument";
		assert(spec.length() > 0): "intersection() spec is empty (should be set or bag)";
		
		codeAgent.reset();
		codeAgent.setArgument("collection", args.get(0));
		codeAgent.setArgument("collection2", codeSrcExp);
		
		rule.append("feature_call");
		spec.append("_intersection");
		
	}
	
	private void handleCollIsEmpty(String codeSrcExp, StringBuffer rule, StringBuffer spec) {
		codeAgent.reset();
        codeAgent.setArgument("collection", codeSrcExp);
		rule.append("feature_call");
		spec.append("isEmpty");
	}
	
	private void handleCollNotEmpty(String codeSrcExp, StringBuffer rule, StringBuffer spec) {
		codeAgent.reset();
		codeAgent.setArgument("collection", codeSrcExp);
		rule.append("feature_call");
		spec.append("notEmpty");
	}
	
	private void handleCollSize(String codeSrcExp, Guide guide, StringBuffer rule, StringBuffer spec) {
		assert(guide != null) : "size(): guide may not be null";
		guide.reset();
		guide.next();
			
		codeAgent.reset();
		codeAgent.setArgument("table", guide.getFrom());
        codeAgent.setArgument("element", guide.getSelect());
        codeAgent.setArgument("collection", codeSrcExp);
		rule.append("feature_call");
		spec.append("size");
	}

	private void handleCollSum(String codeSrcExp, Guide guide, StringBuffer rule, StringBuffer spec) {
		assert(guide != null) : "sum(): guide may not be null";
		guide.reset();
		guide.next();
		
		codeAgent.reset();
		codeAgent.setArgument("table", guide.getFrom());
        codeAgent.setArgument("element", guide.getSelect());
        codeAgent.setArgument("collection", codeSrcExp);
		rule.append("feature_call");
		spec.append("sum");
	}
		
	private void handleCollSymmetricDifference(String codeSrcExp, List<String> args, StringBuffer rule, StringBuffer spec) {
		assert(args.size() == 1) : "symmetricDifference() needs exactly one argument";
		
		codeAgent.reset();
		codeAgent.setArgument("collection", args.get(0));
		codeAgent.setArgument("collection2", codeSrcExp);
		
		rule.append("feature_call");
		spec.append("symmetricDifference");
		
	}
        
    private void handleCollUnion(String codeSrcExp, List<String> args, StringBuffer rule, StringBuffer spec) {
		assert(args.size() == 1) : "union() needs exactly one argument";
		assert(spec.length() > 0) : "union() spec is empty (should be set or bag)";
		
		codeAgent.reset();
		codeAgent.setArgument("collection", args.get(0));
		codeAgent.setArgument("collection2", codeSrcExp);
		
		rule.append("feature_call");
		spec.append("_union");
		
	}
	
	private void handleIterCollect(String codeSrcExp, List<Guide> guides, List<String> args, StringBuffer rule, StringBuffer spec) {
		codeAgent.setArgument("expression", "<collect>");
		rule.append("feature_call");
		spec.append("collect");
	}
	
	private void handleIterForAll(String codeSrcExp, Guide guide, List<String> args, StringBuffer rule, StringBuffer spec) {
		assert(args.size() == 1) : "forAll() needs exactly one argument";
		assert(guide != null) : "forAll(): guide may not be null";
		
		guide.reset();
		guide.next();
		
		codeAgent.reset();
		codeAgent.setArgument("table", guide.getFrom() + " " + aliasList.getLast());
        codeAgent.setArgument("object", guide.getSelect());
        codeAgent.setArgument("collection", codeSrcExp);
        codeAgent.setArgument("expression", args.get(0));
		rule.append("feature_call");
		spec.append("forAll");
	}
	
	private void handleIterReject(String codeSrcExp, Guide guide, List<String> args, StringBuffer rule, StringBuffer spec) {
		assert(args.size() == 1) : "reject() needs exactly one argument";
		assert(guide != null) : "reject(): guide may not be null";
		
		guide.reset();
		guide.next();
			
		codeAgent.reset();
		codeAgent.setArgument("table", guide.getFrom() + " " + aliasList.getLast());
        codeAgent.setArgument("object", guide.getSelect());
        codeAgent.setArgument("collection", codeSrcExp);
        codeAgent.setArgument("expression", args.get(0));
		rule.append("feature_call");
		spec.append("reject");
	}
	
	private void handleIterSelect(String codeSrcExp, Guide guide, List<String> args, StringBuffer rule, StringBuffer spec) {
		assert(args.size() == 1) : "select() needs exactly one argument";
		assert(guide != null) : "select(): guide may not be null";
		
		guide.reset();
		guide.next();
			
		codeAgent.reset();
		codeAgent.setArgument("table", guide.getFrom() + " " + aliasList.getLast());
        codeAgent.setArgument("object", guide.getSelect());
        codeAgent.setArgument("collection", codeSrcExp);
        codeAgent.setArgument("expression", args.get(0));
		rule.append("feature_call");
		spec.append("select");
	}
	
	/**
	 * Parameterizes the CodeAgent for a logical and operation
	 * 
	 * @param codeSrcExp the code of the source expression (argument 1)
	 * @param args the code of all other arguments
	 * @param rule will be changed to the appropriate rule for use with the CodeAgent
	 * @param spec will be changed to the appropriate spec for use with the CodeAgent
	 * @pre args.size()==1
	 */
	private void handleLogAnd(String codeSrcExp, List<String> args, StringBuffer rule, StringBuffer spec) {
		assert(args.size() == 1) : "Logical expression and needs exactly one argument";
		
		codeAgent.setArgument("rel_exp_1", codeSrcExp);
		codeAgent.setArgument("rel_exp_2", args.get(0));	
		rule.append("logical_expression_tail");
		spec.append("and");		
	}
	
	/**
	 * Parameterizes the CodeAgent for a logical or operation
	 * 
	 * @param codeSrcExp the code of the source expression (argument 1)
	 * @param args the code of all other arguments
	 * @param rule will be changed to the appropriate rule for use with the CodeAgent
	 * @param spec will be changed to the appropriate spec for use with the CodeAgent
	 * @pre args.size()==1
	 */
	private void handleLogOr(String codeSrcExp, List<String> args, StringBuffer rule, StringBuffer spec) {
		assert(args.size() == 1) : "Logical expression or needs exactly one argument";
		
		codeAgent.setArgument("rel_exp_1", codeSrcExp);
		codeAgent.setArgument("rel_exp_2", args.get(0));	
		rule.append("logical_expression_tail");
		spec.append("or");		
	}
	
	/**
	 * Parameterizes the CodeAgent for a logical xor operation
	 * 
	 * @param codeSrcExp the code of the source expression (argument 1)
	 * @param args the code of all other arguments
	 * @param rule will be changed to the appropriate rule for use with the CodeAgent
	 * @param spec will be changed to the appropriate spec for use with the CodeAgent
	 * @pre args.size()==1
	 */
	private void handleLogXor(String codeSrcExp, List<String> args, StringBuffer rule, StringBuffer spec) {
		assert(args.size() == 1) : "Logical expression xor needs exactly one argument";
		
		codeAgent.setArgument("rel_exp_1", codeSrcExp);
		codeAgent.setArgument("rel_exp_2", args.get(0));	
		rule.append("logical_expression_tail");
		spec.append("xor");		
	}
	
	/**
	 * Parameterizes the CodeAgent for a divide operation
	 * 
	 * @param codeSrcExp the code of the source expression (argument 1)
	 * @param args the code of all other arguments
	 * @param rule will be changed to the appropriate rule for use with the CodeAgent
	 * @param spec will be changed to the appropriate spec for use with the CodeAgent
	 * @pre args.size()==1
	 */
	private void handleMultDiv(String codeSrcExp, List<String> args, StringBuffer rule, StringBuffer spec) {
		assert(args.size() == 1) : "Multiplicative expression / needs exactly one argument";
		
		codeAgent.setArgument("un_exp_1", codeSrcExp);
		codeAgent.setArgument("un_exp_2", args.get(0));
		rule.append("multiplicative_expression_tail");
		spec.append("div");		
	}
	
	/**
	 * Parameterizes the CodeAgent for a multiply operation
	 * 
	 * @param codeSrcExp the code of the source expression (argument 1)
	 * @param args the code of all other arguments
	 * @param rule will be changed to the appropriate rule for use with the CodeAgent
	 * @param spec will be changed to the appropriate spec for use with the CodeAgent
	 * @pre args.size()==1
	 */
	private void handleMultMult(String codeSrcExp, List<String> args, StringBuffer rule, StringBuffer spec) {
		assert(args.size() == 1) : "Multiplicative expression * needs exactly one argument";
		
		codeAgent.setArgument("un_exp_1", codeSrcExp);
		codeAgent.setArgument("un_exp_2", args.get(0));
		rule.append("multiplicative_expression_tail");
		spec.append("mult");
	}
	
	private void handleOclIsKindOf(Table typeTable, StringBuffer rule, StringBuffer spec) {
		codeAgent.reset();
		codeAgent.setArgument("table", typeTable.getTableName());
        codeAgent.setArgument("object", typeTable.getPrimaryKeyRepresentation());
        codeAgent.setArgument("context_object", aliasList.getLast() + "." + typeTable.getPrimaryKeyRepresentation());
		rule.append("feature_call");
		spec.append("oclIsKindOf");		
	}

	private void handleOclIsTypeOf(Table typeTable, Table superTypeTable, StringBuffer rule, StringBuffer spec) {
		codeAgent.reset();
		codeAgent.setArgument("table", typeTable.getTableName());
        codeAgent.setArgument("object", typeTable.getPrimaryKeyRepresentation());
        codeAgent.setArgument("context_object", aliasList.getLast() + "." + typeTable.getPrimaryKeyRepresentation());
						
		if (superTypeTable == null) {
            codeAgent.setArgument("table2", "foo"); 
        } else {
            codeAgent.enableConnector();
            codeAgent.setArgument("table2", superTypeTable.getTableName()); 
        }
		
		rule.append("feature_call");
		spec.append("oclIsTypeOf");		
	}
	
	/**
	 * Parameterizes the CodeAgent for a relational equals operation
	 * 
	 * @param srcExp the OclExpression representing the first argument
	 * @param codeSrcExp the code of the source expression (argument 1)
	 * @param args the code of all other arguments
	 * @param rule will be changed to the appropriate rule for use with the CodeAgent
	 * @param spec will be changed to the appropriate spec for use with the CodeAgent
	 * @pre args.size()==1
	 */
	private void handleRelEquals(OclExpression srcExp, String codeSrcExp, List<String> args, StringBuffer rule, StringBuffer spec) {
		assert(args.size() == 1) : "Relational expression = needs exactly one argument";
		
		Classifier attrType = null;
		
		if (srcExp instanceof OperationCallExp) {
			attrType = srcExp.getType();
		} else if (srcExp instanceof AttributeCallExp) {
			attrType = ((AttributeCallExp)srcExp).getReferredAttribute().getTypeA();
		} else if (srcExp instanceof PrimitiveLiteralExp) {
			attrType = ((PrimitiveLiteralExp)srcExp).getType();
		} else {
			throw new IllegalStateException("Unhandled attribute type for relational expression =!");
		}	
		
		if (attrType instanceof Primitive) {
			if (attrType.getNameA().equals("Boolean")) {
				spec.append("equal_Bool"); 
			} else if (attrType.getNameA().equals("Integer") || 
				attrType.getNameA().equals("Real") ||
				attrType.getNameA().equals("String") ||
				attrType.getNameA().equals("Enumeration"))
			{
				spec.append("equal_IntRealStringEnum"); 
			}
		} else if (attrType instanceof CollectionType) {
			if(attrType instanceof SetType) {
				spec.append("equal_Set");
            } else if(attrType instanceof SequenceType) {
				spec.append("equal_Sequence");
            } else if(attrType instanceof BagType) {
				spec.append("equal_Bag");
            } else if(attrType instanceof OrderedSetType) {
                // spec = "equal_OrderedSet"
                throw new RuntimeException("OrderedSetType not yet supported by code generation.");
            }
		} else {
			spec.append("equal_Any");
		}
		
		assert(spec.length() != 0): "Code agent spec for = is empty!";
		
		codeAgent.setArgument("add_exp_1", codeSrcExp);
		codeAgent.setArgument("add_exp_2", args.get(0));
		rule.append("relational_expression_tail");
	}

	/**
	 * Parameterizes the CodeAgent for a relational greater operation
	 * 
	 * @param codeSrcExp the code of the source expression (argument 1)
	 * @param args the code of all other arguments
	 * @param rule will be changed to the appropriate rule for use with the CodeAgent
	 * @param spec will be changed to the appropriate spec for use with the CodeAgent
	 * @pre args.size()==1
	 */
	private void handleRelGreater(String codeSrcExp, List<String> args, StringBuffer rule, StringBuffer spec) {
		assert(args.size() == 1) : "Relational expression > needs exactly one argument";
		
		codeAgent.setArgument("add_exp_1", codeSrcExp);
		codeAgent.setArgument("add_exp_2", args.get(0));
		rule.append("relational_expression_tail");
		spec.append("gt");
	}

	/**
	 * Parameterizes the CodeAgent for a relational greater-equal operation
	 * 
	 * @param codeSrcExp the code of the source expression (argument 1)
	 * @param args the code of all other arguments
	 * @param rule will be changed to the appropriate rule for use with the CodeAgent
	 * @param spec will be changed to the appropriate spec for use with the CodeAgent
	 * @pre args.size()==1
	 */
	private void handleRelGreaterEqual(String codeSrcExp, List<String> args, StringBuffer rule, StringBuffer spec) {
		assert(args.size() == 1) : "Relational expression >= needs exactly one argument";
		
		codeAgent.setArgument("add_exp_1", codeSrcExp);
		codeAgent.setArgument("add_exp_2", args.get(0));
		rule.append("relational_expression_tail");
		spec.append("gteq");		
	}

	/**
	 * Parameterizes the CodeAgent for a relational implies operation
	 * 
	 * @param codeSrcExp the code of the source expression (argument 1)
	 * @param args the code of all other arguments
	 * @param rule will be changed to the appropriate rule for use with the CodeAgent
	 * @param spec will be changed to the appropriate spec for use with the CodeAgent
	 * @pre args.size()==1
	 */
	private void handleRelImplies(String codeSrcExp, List<String> args, StringBuffer rule, StringBuffer spec) {
		assert(args.size() == 1) : "Logical expression implies needs exactly one argument";
		
		codeAgent.setArgument("rel_exp_1", codeSrcExp);
		codeAgent.setArgument("rel_exp_2", args.get(0));	
		rule.append("logical_expression_tail");
		spec.append("implies");
	}

	/**
	 * Parameterizes the CodeAgent for a relational lesser operation
	 * 
	 * @param codeSrcExp the code of the source expression (argument 1)
	 * @param args the code of all other arguments
	 * @param rule will be changed to the appropriate rule for use with the CodeAgent
	 * @param spec will be changed to the appropriate spec for use with the CodeAgent
	 * @pre args.size()==1
	 */
	private void handleRelLesser(String codeSrcExp, List<String> args, StringBuffer rule, StringBuffer spec) {
		assert(args.size() == 1) : "Relational expression < needs exactly one argument";
		
		codeAgent.setArgument("add_exp_1", codeSrcExp);
		codeAgent.setArgument("add_exp_2", args.get(0));
		rule.append("relational_expression_tail");
		spec.append("lt");
	}

	/**
	 * Parameterizes the CodeAgent for a relational lesser-equal operation
	 * 
	 * @param codeSrcExp the code of the source expression (argument 1)
	 * @param args the code of all other arguments
	 * @param rule will be changed to the appropriate rule for use with the CodeAgent
	 * @param spec will be changed to the appropriate spec for use with the CodeAgent
	 * @pre args.size()==1
	 */
	private void handleRelLesserEqual(String codeSrcExp, List<String> args, StringBuffer rule, StringBuffer spec) {
		assert(args.size() == 1) : "Relational expression <= needs exactly one argument";
		
		codeAgent.setArgument("add_exp_1", codeSrcExp);
		codeAgent.setArgument("add_exp_2", args.get(0));
		rule.append("relational_expression_tail");
		spec.append("lteq");
	}

	/**
	 * Parameterizes the CodeAgent for a relational not-equal operation
	 * 
	 * @param srcExp the OclExpression representing the first argument
	 * @param codeSrcExp the code of the source expression (argument 1)
	 * @param args the code of all other arguments
	 * @param rule will be changed to the appropriate rule for use with the CodeAgent
	 * @param spec will be changed to the appropriate spec for use with the CodeAgent
	 * @pre args.size()==1
	 */
	private void handleRelNEquals(OclExpression srcExp, String codeSrcExp, List<String> args, StringBuffer rule, StringBuffer spec) {
		assert(args.size() == 1) : "Relational expression <> needs exactly one argument";
		
		if (srcExp instanceof AttributeCallExp) {
			Classifier attrType = ((AttributeCallExp)srcExp).getReferredAttribute().getTypeA();
			if (attrType instanceof Primitive) {
				spec.append("nequal");
			} else {
				spec.append("nequal_Any");
			}
		}
		
		assert(spec.length() != 0): "Code agent spec for <> is empty!";
		
		codeAgent.setArgument("add_exp_1", codeSrcExp);
		codeAgent.setArgument("add_exp_2", args.get(0));
		rule.append("relational_expression_tail");
		
	}
	
	private void handleSequenceExcluding(String codeSrcExp, List<String> args, Guide guide, StringBuffer rule, StringBuffer spec) {
		assert(args.size() == 1) : "excluding() needs exactly one argument";
		guide.reset();
		guide.next();
		codeAgent.reset();
		codeAgent.setArgument("seqNo", SEQNO);
		codeAgent.setArgument("seqNo2", SEQNO);
		codeAgent.setArgument("sequence", codeSrcExp);
		codeAgent.setArgument("object", args.get(0));
		codeAgent.setArgument("element", guide.getSelect());
		rule.append("feature_call");
		spec.append("sequence_excluding");		
	}

	private void handleSequenceIncluding(String codeSrcExp, List<String> args, StringBuffer rule, StringBuffer spec) {
		assert(args.size() == 1) : "including() needs exactly one argument";
		codeAgent.reset();
		codeAgent.setArgument("seqNo", SEQNO);
		codeAgent.setArgument("seqNo2", SEQNO);
		codeAgent.setArgument("sequence", codeSrcExp);
		codeAgent.setArgument("object", args.get(0));
		rule.append("feature_call");
		spec.append("sequence_including");		
	}

	private void handleSequenceUnion(String codeSrcExp, List<String> args, Guide guide, StringBuffer rule, StringBuffer spec) {
		assert(args.size() == 1): "union() needs exactly one argument";
		guide.reset();
		guide.next();
		codeAgent.reset();
		codeAgent.setArgument("seqNo", SEQNO);
		codeAgent.setArgument("seqNo2", SEQNO);
		codeAgent.setArgument("sequence", codeSrcExp);
		codeAgent.setArgument("sequence2", args.get(0));
		codeAgent.setArgument("element", guide.getSelect());
		rule.append("feature_call");
		spec.append("sequence_union");
	}
	
	/**
	 * Parameterizes the CodeAgent for a unary minus operation
	 * 
	 * @param codeSrcExp the code of the source expression (argument 1)
	 * @param rule will be changed to the appropriate rule for use with the CodeAgent
	 * @param spec will be changed to the appropriate spec for use with the CodeAgent
	 */
	private void handleUnaryMinus(String codeSrcExp, StringBuffer rule, StringBuffer spec) {
		codeAgent.setArgument("un_exp", codeSrcExp);
		rule.append("unary_expression");
		spec.append("minus");		
	}

	/**
	 * Parameterizes the CodeAgent for a unary not operation
	 * 
	 * @param codeSrcExp the code of the source expression (argument 1)
	 * @param args the code of all other arguments
	 * @param rule will be changed to the appropriate rule for use with the CodeAgent
	 * @param spec will be changed to the appropriate spec for use with the CodeAgent
	 * @pre args.size()==1
	 */
	private void handleUnaryNot(String codeSrcExp, StringBuffer rule, StringBuffer spec) {
		codeAgent.setArgument("un_exp", codeSrcExp);
		rule.append("unary_expression");
		spec.append("not");		
	}
	
	/**
	 * Prepares a classic join navigation (currently not well tested)
	 * 
	 * @pre guides.size() > 1
	 */
	public void prepareClassicJoin(List<Guide> guides) {
		StringBuffer tables = new StringBuffer();
		StringBuffer joins = new StringBuffer();
		String select = "", from = "", where = "", tableAlias, firstTable = "", firstColumn = "", thisCode = "";
		Guide guide;
                
        for (int i = 0; i < guides.size(); i++) {
			guide = guides.get(i); 
			guide.reset();
						
			for (int k = 0; k < guide.numberOfSteps(); k++) {
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
                                
				if ((i == 0) && (k == 0)) {
					joinTargetObject = tableAlias + "." + guide.getWhere();
				}
				
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
			codeAgent.setArgument("table", firstTable );
			codeAgent.setArgument("column", firstColumn);
			codeAgent.setArgument("tables", tableRepresentation);
			codeAgent.setArgument("joins", joinRepresentation);
		    thisCode = codeAgent.getCodeFor("feature_call", "navigation_classic");  
		    joinRepresentation = "";
		    tableRepresentation = thisCode;                                  
		} catch (Exception e) {
		    throw new RuntimeException("navigation_classic: " + e.toString());
		}                                                                                   
	}
	
	/**
	 * Prepares a navigation statement using derived tables
	 * 
	 * @pre guides.size() > 1
	 */
    public void prepareDerivedTable(List<Guide> guides) {
		String select = "", from = "", where = "", navExpr = "", koc, thisCode = "";
	    String ph = "$$_dt_$$";
		Guide guide;                           
	    tableRepresentation = "";
                                                   
	    for (int i = 0; i < guides.size(); i++) {
			guide = guides.get(i); 
			guide.reset();
						
			for (int k = 0; k < guide.numberOfSteps(); k++) {
				guide.next();
				
				// skip unnecessary joins
				if ((from.equals(guide.getFrom())) &&
				    (where.equals(guide.getSelect())) &&
				    (where.equals(guide.getWhere())))
					continue;
                                
                // add derived table to table representation
				codeAgent.reset();
				codeAgent.setArgument("object", guide.getSelect());
				codeAgent.setArgument("table1", guide.getFrom());
				codeAgent.setArgument("ref_object", guide.getWhere());
                
                // last step of whole derived table must be treated in a special way
                if ((i == (guides.size() - 1))  && (k == (guide.numberOfSteps() - 1))) { 
					codeAgent.setArgument("context_alias", guide.getAlias());
					codeAgent.setArgument("context_object", guide.getWhere());
                    koc = "navigation_context";
                } else {                                    
					codeAgent.setArgument("table2", ph);
                    koc = "navigation";
                }				
                                
				try {
    			    thisCode = codeAgent.getCodeFor("feature_call", koc);                                    
    			} catch (Exception e) {
    			    throw new RuntimeException("navigation: " + e.toString());
    			}                                                                
                                
                if (navExpr.length() == 0) {
                    navExpr = thisCode;
                } else {                                    
                    if (thisCode.endsWith("\n")) {
                    	thisCode = thisCode.substring(0, thisCode.length()-1);
                    }
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
	 * Prepares a navigation depending on the value of the attribute joinMode
	 * 
	 * @pre guides.size() > 1
	 */
    public void prepareNavigation(List<Guide> guides) {                    
        if (joinMode == true) {
            prepareClassicJoin(guides);
        } else {
            prepareDerivedTable(guides);
        }           
    }

	/**
	 * A replacement method for Strings.
	 * 
	 * @param source the string containing substrings that equal to oldStr
	 * @param oldStr the substring to be replaced
	 * @param newStr the replacement
	 * @return A String with replaced occurences of oldStr with newStr
	 */
	String replaceInString(String source, String oldStr, String newStr) {
		StringBuffer sourceBuffer = new StringBuffer(source);
		int i = source.indexOf(oldStr, 0);

		while (i != -1) {
			sourceBuffer.replace(i, i+oldStr.length(), newStr);
			i += newStr.length();
			i = sourceBuffer.toString().indexOf(oldStr, i);
		}

		return sourceBuffer.toString();
	}

	/**
	 *  Resets the SQLCodeGenerator object to initial values.
     *  Must be used, if multiple invariants will be translated 
     *  by one code generator object.
	 */
	public void reset() {
		constrainedType = null;
    	constraintName = null;
		involvedTables = new HashSet<String>();
		joinAliasCount = 0;
		navigation = new HashMap<OclExpression, List<Guide>>();
	}

	/**
	 * Sets the object relational mapping scheme used by the code generator.
	 * 
	 * @param orm
	 */
	public void setORMappingScheme(ORMappingScheme orm) {
		theMap = orm;
	}
	
	/**
	 * Generates a code fragment for the given OclExpression
	 * 
	 * @param exp expression to generate code for
	 * @return a SQL code fragment for this expression 
	 */
	public String transform(AssociationClassCallExp exp) {
		/* StringBuffer result = new StringBuffer();
		
		assignGuides(exp);
		
		List<Guide> guides = navigation.get(exp);
		Guide guide;
		if (guides != null) {
			guide = (Guide)guides.get(0);
            guide.reset();
            guide.next();
			
			if (guide.isNavigation()) {	
	            // navigation only
	            prepareNavigation(guides);
	            result.append(tableRepresentation);
			}
		}
		
		return result.toString();*/
		
		return "! Association Classes not supported !";
	}

	/**
	 * Generates a code fragment for the given OclExpression
	 * 
	 * @param exp expression to generate code for
	 * @return a SQL code fragment for this expression 
	 */
	public String transform(AssociationEndCallExp exp) {
		StringBuffer result = new StringBuffer();
		
		assignGuides(exp);
		
		List<Guide> guides = navigation.get(exp);
		Guide guide;
		if (guides != null) {
			guide = (Guide)guides.get(0);
            guide.reset();
            guide.next();
			
			if (guide.isNavigation()) {	
	            // navigation only
	            prepareNavigation(guides);
	            result.append(tableRepresentation);
			}
		}		
				
		return result.toString();
	}
	
	/**
	 * Generates a code fragment for the given OclExpression
	 * 
	 * @param exp expression to generate code for
	 * @return a SQL code fragment for this expression 
	 */
    public String transform(AttributeCallExp exp){
        StringBuffer result = new StringBuffer();
		
		assignGuides(exp);
			
		List<Guide> guides = navigation.get(exp);
		Guide guide;
		if (guides != null) {
			guide = guides.get(0);
            guide.reset();
            guide.next();
		
			if (guides.size() == 1) {
				// attribute access without navigation
				codeAgent.reset();
				codeAgent.setArgument("context_alias", guide.getAlias());
				codeAgent.setArgument("column", guide.getSelect());
		
				try {
					result.append(codeAgent.getCodeFor("feature_call", "attribute_context"));
				} catch (Exception e) {
					throw new RuntimeException("attribute_context at feature primary expression: " + e.toString());
				}
				
				// special case for Boolean attributes: (expand 'attribute' to 'attribute = 1')
				if (exp.getReferredAttribute().getTypeA().getNameA().equals("Boolean")) {
					codeAgent.reset();
	                codeAgent.setArgument("attribute", result.toString());
	                try {
	                	result = new StringBuffer();
						result.append(codeAgent.getCodeFor("feature_call", "attribute_boolean"));
					} catch (Exception e) {
						throw new RuntimeException("attribute_boolean: " + e.toString());
					}
				}
			} else {
				// attribute access with navigation
				prepareNavigation(guides);
                result.append(tableRepresentation);
			}
		}
			
        return result.toString();
        
    }
	
	/**
	 * Generates a code fragment for the given OclExpression
	 * 
	 * @param exp expression to generate code for
	 * @return a SQL code fragment for this expression 
	 */
	public String transform(BooleanLiteralExp exp) {
		StringBuffer result = new StringBuffer("");
		
		codeAgent.reset();
		
		try {			
			if (exp.isBooleanSymbol()) {
				result.append(codeAgent.getCodeFor("literal", "boolean_true"));
			} else {
				result.append(codeAgent.getCodeFor("literal", "boolean_false"));
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		
		return result.toString();
	}

	/**
	 * Generates a code fragment for the given OclExpression
	 * 
	 * @param exp expression to generate code for
	 * @return a SQL code fragment for this expression 
	 */
	public String transform(CollectionLiteralExp exp) {
		StringBuffer result = new StringBuffer("");
		String spec = "";
		
		if (exp.getKind() == CollectionKindEnum.BAG) {
			spec = "bag";
		} else if (exp.getKind() == CollectionKindEnum.SET) {
			spec = "set";
		} else if (exp.getKind() == CollectionKindEnum.SEQUENCE) {
			spec = "sequence";
		} else {
			assert(false): "Unknown CollectionKind";
		}
		
		// evaluate the parts
		List parts = exp.getParts();
        for(int i = 0; i < parts.size(); i++) {
        	codeAgent.reset();
        	codeAgent.setArgument("value", getCode((OclExpression) ((CollectionItem) parts.get(i)).getItem()));
        	
        	if (exp.getKind() == CollectionKindEnum.SEQUENCE) {
				codeAgent.setArgument("seqnr", i + 1 + "");
			}
        	
        	try {
				if (result.length() != 0) {
					codeAgent.enableConnector();
				}
				result.append(codeAgent.getCodeFor("literal_collection", spec));
			} catch (Exception e) {
				System.err.println(e.toString());
			}
        }
		
		return result.toString();
	}

	/**
	 * Generates a code fragment for the given OclExpression
	 * 
	 * @param exp expression to generate code for
	 * @return a SQL code fragment for this expression 
	 */
	public String transform(EnumLiteralExp exp) {
		StringBuffer result = new StringBuffer("");
		EnumerationLiteral el = exp.getReferredEnumLiteral();
		
		codeAgent.reset();
		codeAgent.setArgument("value", el.getNameA());
		
		try {
			result.append(codeAgent.getCodeFor("literal", "enum"));
		} catch (Exception e) {
			System.err.println(e.toString());
		}		
		
		return result.toString();
	}

	/**
	 * Generates a code fragment for the given OclExpression
	 * 
	 * @param exp expression to generate code for
	 * @return a SQL code fragment for this expression 
	 */
	public String transform(ExpressionInOcl exp) {
		StringBuffer result = new StringBuffer(""); 
		
		constrainedType = exp.getContextualClassifier().getNameA();
		constraintName = exp.getConstraintA().getNameA();
		
		Table table = (Table)theMap.getMappedClass(constrainedType).getTables().get(0);
			
		OclExpression bodyExp = exp.getBodyExpression();
		String expression = getCode(bodyExp);
		
		codeAgent.reset();
    	if (keyMode) {
    		codeAgent.setArgument("key", table.getPrimaryKeyRepresentation());
    	}		
		codeAgent.setArgument("constraint_name", constraintName);
		codeAgent.setArgument("context_table", table.getTableName());
		codeAgent.setArgument("context_alias", aliasList.getLast());
		codeAgent.setArgument("expression", expression);
		
    	try {
    		result= new StringBuffer(codeAgent.getCodeFor("constraint_body", "any"));
    	} catch (Exception e) {
			e.printStackTrace();
    	}
		
		return result.toString();
	}

	/**
	 * Generates a code fragment for the given OclExpression
	 * 
	 * @param exp expression to generate code for
	 * @return a SQL code fragment for this expression 
	 */
	public String transform(IfExp exp) {
		StringBuffer result = new StringBuffer("");
		
		String srcCond = getCode(exp.getCondition());
		String srcThen = getCode(exp.getThenExpression());
		String srcElse = getCode(exp.getElseExpression());
		
		// initialize code agent
		codeAgent.reset();
		codeAgent.setArgument("if_branch", srcCond);
		codeAgent.setArgument("then_branch", srcThen);
		codeAgent.setArgument("else_branch", srcElse);

		// get code
		try {
			result.append(codeAgent.getCodeFor("if_expression", "any"));
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return result.toString();
	}

	/**
	 * Generates a code fragment for the given OclExpression
	 * 
	 * @param exp expression to generate code for
	 * @return a SQL code fragment for this expression 
	 */
	public String transform(IntegerLiteralExp exp) {
		StringBuffer result = new StringBuffer("");
		
		result.append(exp.getIntegerSymbol());
				
		return result.toString();
	}

	/**
	 * Generates a code fragment for the given OclExpression
	 * 
	 * @param exp expression to generate code for
	 * @return a SQL code fragment for this expression 
	 */
	public String transform(IteratorExp exp) {
		StringBuffer result = new StringBuffer("");
		StringBuffer rule = new StringBuffer();
		StringBuffer spec = new StringBuffer();
		
		OclExpression srcExp = exp.getSource();
		String name = exp.getNameA();
		
		// get code for source expression
		String codeSrcExp = getCode(srcExp);

		// create new alias for iterator
		aliasList.add(getUniqueAlias());

		// evaluate the arguments
		List<String> args = new ArrayList<String>();
		args.add(getCode(exp.getBody()));
        
		if (name.equals("collect")) {
			handleIterCollect(codeSrcExp, navigation.get(srcExp), args, rule, spec); // dummy
		} else if (name.equals("forAll")) {
			handleIterForAll(codeSrcExp, navigation.get(srcExp).get(0), args, rule, spec);
		} else if (name.equals("reject")) {
			assert(srcExp.getType() instanceof CollectionType): "source of reject iterator must be of type CollectionType";
			
			// create Guide object to the elements of this IteratorExp for later use in the parent of this IteratorExp
			ArrayList<Guide> guides = createGuidesForClassifier(((CollectionType)srcExp.getType()).getElementType());
			navigation.put(exp, guides);
			
			handleIterReject(codeSrcExp, guides.get(0), args, rule, spec);
		} else if (name.equals("select")) {
			assert(srcExp.getType() instanceof CollectionType): "source of select iterator must be of type CollectionType";
			
			// create Guide object to the elements of this IteratorExp for later use in the parent of this IteratorExp
			ArrayList<Guide> guides = createGuidesForClassifier(((CollectionType)srcExp.getType()).getElementType());
			navigation.put(exp, guides);
			
			handleIterSelect(codeSrcExp, guides.get(0), args, rule, spec);
		}
		
		
		try {
			result.append(codeAgent.getCodeFor(rule.toString(), spec.toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		aliasList.removeLast();
		
		return result.toString();
	}

	/**
	 * Generates a code fragment for the given OclExpression
	 * 
	 * @param exp expression to generate code for
	 * @return a SQL code fragment for this expression 
	 */
	public String transform(OclOperationWithTypeArgExp exp) {
		StringBuffer result = new StringBuffer("");
		StringBuffer rule = new StringBuffer();
		StringBuffer spec = new StringBuffer();
		String name = exp.getNameA();
		
		// other feature calls - CLASS AND ATTRIBUTE
		if (name.equals("oclIsKindOf")) {
			Table typeTable = getOclTypeTable(exp.getTypeArgument());
			handleOclIsKindOf(typeTable, rule, spec);
		} else if (name.equals("oclIsTypeOf")) {
			Table typeTable = getOclTypeTable(exp.getTypeArgument());
			Table superTypeTable = getOclSupertypeTable(exp.getTypeArgument());
			handleOclIsTypeOf(typeTable, superTypeTable, rule, spec);
		}
		
		try {
			result.append(codeAgent.getCodeFor(rule.toString(), spec.toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result.toString();
	}

	/**
	 * Generates a code fragment for the given OclExpression
	 * 
	 * @param exp expression to generate code for
	 * @return a SQL code fragment for this expression 
	 */
	public String transform(OperationCallExp exp) {
		StringBuffer result = new StringBuffer("");
		StringBuffer rule = new StringBuffer();
		StringBuffer spec = new StringBuffer();
		boolean ignore = false;

		OclExpression srcExp = exp.getSource();
		List arguments = exp.getArguments();
        Operation op = exp.getReferredOperation();
        String name = op.getNameA();
		
		// get code for source expression (will be argument 1)
		String codeSrcExp = getCode(srcExp);
		
		// evaluate the arguments
		List<String> args = new ArrayList<String>();
        for(int i = 0; i < arguments.size(); i++) {
			args.add(getCode((OclExpression) arguments.get(i)));
        }
		
		// initialize the code agent
		codeAgent.reset();
		
		// relational expressions
		if (name.equals("=")) {
			handleRelEquals(srcExp, codeSrcExp, args, rule, spec);						
		} else if (name.equals("<>")) {
			handleRelNEquals(srcExp, codeSrcExp, args, rule, spec);						
		} else if (name.equals(">")) {
			handleRelGreater(codeSrcExp, args, rule, spec);
		} else if (name.equals(">=")) {
			handleRelGreaterEqual(codeSrcExp, args, rule, spec);
		} else if (name.equals("<")) {
			handleRelLesser(codeSrcExp, args, rule, spec);
		} else if (name.equals("<=")) {
			handleRelLesserEqual(codeSrcExp, args, rule, spec);
		}
		
		// additive expressions
		else if (name.equals("+")) {
			handleAddPlus(codeSrcExp, args, rule, spec);
		} else if (name.equals("-")) {
			if (args.isEmpty()) { // unary minus special case
				handleUnaryMinus(codeSrcExp, rule, spec);
			} else {
				handleAddMinus(codeSrcExp, args, rule, spec);
			}
		}
		
		// multiplicative expressions
		else if (name.equals("*")) {
			handleMultMult(codeSrcExp, args, rule, spec);
		} else if (name.equals("/")) {
			handleMultDiv(codeSrcExp, args, rule, spec);
		}
			
		// logical expression
		else if (name.equals("and")) {
			handleLogAnd(codeSrcExp, args, rule, spec);
		} else if (name.equals("or")) {
			handleLogOr(codeSrcExp, args, rule, spec);
		} else if (name.equals("xor")) {
			handleLogXor(codeSrcExp, args, rule, spec);
		} else if (name.equals("implies")) {
			handleRelImplies(codeSrcExp, args, rule, spec);
		}
		
		// unary expressions (unary minus special case above)
		else if (name.equals("not")) {
			handleUnaryNot(codeSrcExp, rule, spec);
		}
		
		// collection related operations - BASIC VALUE
		else if (name.equals("count")) {
			handleCollCount(codeSrcExp, navigation.get(srcExp).get(0), args, rule, spec);
		} else if (name.equals("size") && !(srcExp.getType() instanceof Primitive)) {
			handleCollSize(codeSrcExp, navigation.get(srcExp).get(0), rule, spec);
		} else if (name.equals("sum")) {
			List<Guide> guides;
			if (srcExp instanceof OperationCallExp && ((OperationCallExp)srcExp).getReferredOperation().getNameA().equals("asSet")) {
				guides = navigation.get(((OperationCallExp)srcExp).getSource());
			} else {
				guides = navigation.get(srcExp);
			}
			handleCollSum(codeSrcExp, guides.get(0), rule, spec);
		}
		
		// collection related operations - COMPLEX PREDICATE
		else if (name.equals("includes")) {
			handleCollIncludes(codeSrcExp, args, rule, spec);
		} else if (name.equals("excludes")) {
			handleCollExcludes(codeSrcExp, args, rule, spec);
		} else if (name.equals("includesAll")) {
			handleCollIncludesAll(codeSrcExp, args, rule, spec);
		} else if (name.equals("excludesAll")) {
			handleCollExcludesAll(codeSrcExp, args, rule, spec);
		} else if (name.equals("isEmpty")) {
			handleCollIsEmpty(codeSrcExp, rule, spec);
		} else if (name.equals("notEmpty")) {
			handleCollNotEmpty(codeSrcExp, rule, spec);
		} else if (name.equals("exists")) {
			handleCollExists(codeSrcExp, navigation.get(srcExp).get(0), args, rule, spec);
		} else if (name.equals("intersection")) {
			CollectionType collType = (CollectionType)srcExp.getType();
			
			if (collType instanceof BagType) {
				spec.append("bag");
			} else if (collType instanceof SetType) {
				spec.append("set");
			}
			handleCollIntersection(codeSrcExp, args, rule, spec);
		} else if (name.equals("including")) {
			CollectionType collType = (CollectionType)srcExp.getType();
			
			if (collType instanceof SequenceType) {
				handleSequenceIncluding(codeSrcExp, args, rule, spec);
			} else {
				if (collType instanceof BagType) {
					spec.append("bag");
				} else if (collType instanceof SetType) {
					spec.append("set");
				}
				handleCollIncluding(codeSrcExp, args, rule, spec);
			}
		} else if (name.equals("excluding")) {
			CollectionType collType = (CollectionType)srcExp.getType();
			
			if (collType instanceof SequenceType) {
				ArrayList<Guide> guides = createGuidesForClassifier(((CollectionType)srcExp.getType()).getElementType());
				handleSequenceExcluding(codeSrcExp, args, guides.get(0), rule, spec);
			} else {
				if (collType instanceof BagType) {
					spec.append("bag");
				} else if (collType instanceof SetType) {
					spec.append("set");
				}
				handleCollExcluding(codeSrcExp, args, rule, spec);
			}
		} else if (name.equals("union")) {
			CollectionType collType = (CollectionType)srcExp.getType();
			
			if (collType instanceof SequenceType) {
				ArrayList<Guide> guides = createGuidesForClassifier(((CollectionType)srcExp.getType()).getElementType());
				handleSequenceUnion(codeSrcExp, args, guides.get(0), rule, spec);
			} else {
				if (collType instanceof BagType) {
					spec.append("bag");
				} else if (collType instanceof SetType) {
					spec.append("set");
				}
				handleCollUnion(codeSrcExp, args, rule, spec);
			}
		}
		
		// collection related operations - MODEL TYPE QUERY
		else if (name.equals("allInstances")) {
			// create Guide object to the return value of allInstances for later use in the parent of this expression
			ArrayList<Guide> guides = createGuidesForClassifier(exp.getSrcType());
			navigation.put(exp, guides);
			
			handleAllInstances(guides.get(0), rule, spec);
		}
		
		else if (name.equals("symmetricDifference")) {
			handleCollSymmetricDifference(codeSrcExp, args, rule, spec);
		}
		
		// BASIC TYPE - String operations
		else if (name.equals("size")) {
			codeAgent.setArgument("operand", codeSrcExp);
			rule.append("feature_call");
			spec.append("basic_size");
		} else if (name.equals("concat")) {
			codeAgent.setArgument("operand1", codeSrcExp);
			codeAgent.setArgument("operand2", args.get(0));
			rule.append("feature_call");
			spec.append("basic_concat");
		} else if (name.equals("toUpper")) {
			codeAgent.setArgument("operand", codeSrcExp);
			rule.append("feature_call");
			spec.append("basic_toUpper");
		} else if (name.equals("toLower")) {
			codeAgent.setArgument("operand", codeSrcExp);
			rule.append("feature_call");
			spec.append("basic_toLower");
		} else if (name.equals("substring")) {
			codeAgent.setArgument("operand", codeSrcExp);
			codeAgent.setArgument("start", args.get(0));
			codeAgent.setArgument("end", args.get(1));
			rule.append("feature_call");
			spec.append("basic_substring");
		}
		
		// BASIC TYPE - Real and Integer operations
		else if (name.equals("abs")) {
			codeAgent.setArgument("operand", codeSrcExp);
			rule.append("feature_call");
			spec.append("basic_abs");
		} else if (name.equals("floor")) {
			codeAgent.setArgument("operand", codeSrcExp);
			rule.append("feature_call");
			spec.append("basic_floor");
		} else if (name.equals("max")) {
			codeAgent.setArgument("operand1", codeSrcExp);
			codeAgent.setArgument("operand2", args.get(0));
			rule.append("feature_call");
			spec.append("basic_max");
		} else if (name.equals("min")) {
			codeAgent.setArgument("operand1", codeSrcExp);
			codeAgent.setArgument("operand2", args.get(0));
			rule.append("feature_call");
			spec.append("basic_min");
		} else if (name.equals("round")) {
			codeAgent.setArgument("operand", codeSrcExp);
			rule.append("feature_call");
			spec.append("basic_round");
		} else if (name.equals("div")) {
			codeAgent.setArgument("operand1", codeSrcExp);
			codeAgent.setArgument("operand2", args.get(0));
			rule.append("feature_call");
			spec.append("basic_div");
		} else if (name.equals("mod")) {
			codeAgent.setArgument("operand1", codeSrcExp);
			codeAgent.setArgument("operand2", args.get(0));
			rule.append("feature_call");
			spec.append("basic_mod");
		}

		else {
			ignore = true;
			result.append(codeSrcExp);
		}
		
		// get the code
		if (!ignore) {
			try {
				result.append(codeAgent.getCodeFor(rule.toString(), spec.toString()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
				
		return result.toString();
	}

	/**
	 * Generates a code fragment for the given OclExpression
	 * 
	 * @param exp expression to generate code for
	 * @return a SQL code fragment for this expression 
	 */
	public String transform(RealLiteralExp exp) {
		StringBuffer result = new StringBuffer("");
		
		result.append(exp.getRealSymbol());
				
		return result.toString();
	}
	
	/**
	 * Generates a code fragment for the given OclExpression
	 * 
	 * @param exp expression to generate code for
	 * @return a SQL code fragment for this expression 
	 */
	public String transform(StringLiteralExp exp) {
		StringBuffer result = new StringBuffer("");
		
		codeAgent.reset();
		codeAgent.setArgument("value", exp.getStringSymbol());
		
		try {
			result.append(codeAgent.getCodeFor("literal", "string"));
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		
		return result.toString();
	}
    
    /**
	 * Generates a code fragment for the given OclExpression
	 * 
	 * @param exp expression to generate code for
	 * @return a SQL code fragment for this expression 
	 */
	public String transform(VariableExp exp) {
		StringBuffer result = new StringBuffer("");
		
		return result.toString();
	}
    
    /**
     *  Adds all tables to the involved tables list which are contained within the guides in the specified list.
     *  
     *  @param guides a List object containing Guide objects for the translation of navigation
     */
    private void updateInvolvedTables(List<Guide> guides) {
        String tn;
        
        for (Guide guide : guides) {
            guide.reset();
            while (guide.hasMoreSteps()) {
                guide.next();
                tn = guide.getFrom().trim();
                if (tn.length() > 0) involvedTables.add(tn);
            }
        }
    }
}