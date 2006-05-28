/*
 * DeclarativeCodeGenerator.java
 * 
 * Copyright (c) 2006 Florian Heidenreich
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

package tudresden.ocl20.codegen.decl.codegen;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import tudresden.ocl20.codegen.decl.mapping.Guide;
import tudresden.ocl20.codegen.decl.mapping.MappedClass;
import tudresden.ocl20.codegen.decl.mapping.MappedModel;
import tudresden.ocl20.codegen.decl.template.Template;
import tudresden.ocl20.codegen.decl.template.TemplateEngine;
import tudresden.ocl20.codegen.decl.util.MetaModelHelper;
import tudresden.ocl20.core.jmi.ocl.commonmodel.Classifier;
import tudresden.ocl20.core.jmi.ocl.commonmodel.Operation;
import tudresden.ocl20.core.jmi.ocl.commonmodel.Primitive;
import tudresden.ocl20.core.jmi.ocl.expressions.AssociationClassCallExp;
import tudresden.ocl20.core.jmi.ocl.expressions.AssociationEndCallExp;
import tudresden.ocl20.core.jmi.ocl.expressions.AttributeCallExp;
import tudresden.ocl20.core.jmi.ocl.expressions.BooleanLiteralExp;
import tudresden.ocl20.core.jmi.ocl.expressions.CollectionItem;
import tudresden.ocl20.core.jmi.ocl.expressions.CollectionKindEnum;
import tudresden.ocl20.core.jmi.ocl.expressions.CollectionLiteralExp;
import tudresden.ocl20.core.jmi.ocl.expressions.EnumLiteralExp;
import tudresden.ocl20.core.jmi.ocl.expressions.ExpressionInOcl;
import tudresden.ocl20.core.jmi.ocl.expressions.IfExp;
import tudresden.ocl20.core.jmi.ocl.expressions.IntegerLiteralExp;
import tudresden.ocl20.core.jmi.ocl.expressions.IteratorExp;
import tudresden.ocl20.core.jmi.ocl.expressions.OclExpression;
import tudresden.ocl20.core.jmi.ocl.expressions.OclOperationWithTypeArgExp;
import tudresden.ocl20.core.jmi.ocl.expressions.OperationCallExp;
import tudresden.ocl20.core.jmi.ocl.expressions.PrimitiveLiteralExp;
import tudresden.ocl20.core.jmi.ocl.expressions.RealLiteralExp;
import tudresden.ocl20.core.jmi.ocl.expressions.StringLiteralExp;
import tudresden.ocl20.core.jmi.ocl.expressions.VariableExp;
import tudresden.ocl20.core.jmi.ocl.types.BagType;
import tudresden.ocl20.core.jmi.ocl.types.CollectionType;
import tudresden.ocl20.core.jmi.ocl.types.OrderedSetType;
import tudresden.ocl20.core.jmi.ocl.types.SequenceType;
import tudresden.ocl20.core.jmi.ocl.types.SetType;
import tudresden.ocl20.core.util.ReflectiveVisitor;

/**
 * <p>
 * The DeclarativeCodeGenerator generates declarative target code for a given
 * ExpressionInOcl.
 * </p>
 * <p>
 * The OclExpressions from a UML/OCL or MOF/OCL model are transformed to the target
 * language using two different mappings:<br />
 * <ul>
 * <li>Mapping between models:<br />
 * The first mapping maps the Classifiers, its attributes and association ends to 
 * a target model. The target model must implement the MappedModel interface and
 * describes the model elements by Guide objects.</li>
 * <li>Language mapping:<br />
 * The second mapping maps OCL language artefacts to fragments of the target language
 * using templates which are read by the DeclarativeTemplateEngine. The templates are
 * parametrisized by the DeclarativeCodeGenerator using target model information queried
 * via the MappedModel instance.
 * </ul>
 * 
 * @author Florian Heidenreich
 * @see tudresden.ocl20.codegen.decl.mapping.MappedModel
 * @see tudresden.ocl20.codegen.decl.mapping.MappedClass
 * @see tudresden.ocl20.codegen.decl.mapping.Guide
 * @see tudresden.ocl20.codegen.decl.template.DeclarativeTemplateEngine
 * @see tudresden.ocl20.codegen.decl.template.DeclarativeTemplate
 */
public class DeclarativeCodeGenerator extends ReflectiveVisitor {

	/**
	 * MappedModel which is used for the mapping between the base model and the target model.
	 */
	private MappedModel mappedModel;
	
	/**
	 * This attribute holds all templates of the target language used by the code generator
	 */
	private TemplateEngine templateEngine;
	
	/**
	 * This map is filled by assignGuides and maps an OclExpression to a lis of Guide objects
	 */
	private Map<OclExpression,List<Guide>> navigationMap;
	
	/**
	 * Holds generated alias which may be used in the declarative target language
	 */
	private LinkedList<String> aliasList;
	private LinkedList<String> contextList;
	
	/**
	 * Creates a new DeclarativeCodeGenerator.
	 * 
	 */
	public DeclarativeCodeGenerator(MappedModel mappedModel, TemplateEngine templateEngine) {
		super("transform");
		
		this.mappedModel = mappedModel;
		this.templateEngine = templateEngine;
		
		this.aliasList = new LinkedList<String>();
		this.contextList = new LinkedList<String>();
		this.navigationMap = new HashMap<OclExpression, List<Guide>>();
		
		aliasList.add(mappedModel.getUniqueAlias());
	}
	
	/**
	 * Assigns a class guide to the given OclExpression which are used during the
	 * code generation process.
	 * 
	 * @param exp the OclExpression to assign the Guide for.
	 */
	protected List<Guide> assignClassGuide(OclExpression exp, Classifier type) {
		List<Guide> guides = new ArrayList<Guide>();
		guides.add(mappedModel.getClass(type.getNameA()).getClassGuide());
		navigationMap.put(exp, guides);
		return guides;
	}
	
	/**
	 * Assigns guides to the given OclExpression which are used during the
	 * code generation process.
	 * 
	 * @param exp the OclExpression to assign the Guides for.
	 */
	protected List<Guide> assignGuides(OclExpression exp) {
		Guide guide;
		List<Guide> guides = new ArrayList<Guide>();
		OclExpression next = exp;
		OclExpression tmpNext;
		String featureName;	// used for attribute/association end names
		String startType;	// used for attribute/association end types
		

		if (navigationMap.containsKey(exp)) {
			return navigationMap.get(exp);
		}

		while (next != null) {
			if (next instanceof AttributeCallExp) {
				AttributeCallExp ac = (AttributeCallExp)next;
				tmpNext = ac.getSource();
				featureName = ac.getReferredAttribute().getNameA();
				startType = tmpNext.getType().getNameA();
				guide = mappedModel.getClass(startType).getAttributeGuide(featureName);
			} else if (next instanceof AssociationEndCallExp) {
				AssociationEndCallExp ae = (AssociationEndCallExp)next;
				tmpNext = ae.getSource();
				featureName = ae.getReferredAssociationEnd().getNameA();
				startType = tmpNext.getType().getNameA();
				guide = mappedModel.getClass(startType).getAssociationEndGuide(featureName);
			} else if (next instanceof AssociationClassCallExp) {
				AssociationClassCallExp acc = (AssociationClassCallExp)next;
				tmpNext = acc.getSource();
				featureName = acc.getReferredAssociationClass().getNameA();
				startType = next.getType().getNameA();
				guide = mappedModel.getClass(featureName).getClassGuide();
			}
			else {
				break;
			}
			
			// assign alias to last element
			if (tmpNext == null || tmpNext instanceof VariableExp) {
				guide.setAlias(aliasList.getLast());
			}
			
			guides.add(guide);
			next = tmpNext;
		}
		
		navigationMap.put(exp, guides);
		
		return guides;
	}
	
	/**
	 * Generates target code for a given ExpressionInOcl
	 * by visiting the visit methods of the ReflectiveVisitor.
	 * 
	 * @param exp ExpressionInOcl to generate code for
	 * @return target code
	 */
	public String getCode(ExpressionInOcl exp) {
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
	 * Generates target code for a given OclExpression
	 * by visiting the visit methods of the ReflectiveVisitor.
	 * 
	 * @param exp OclExpression to generate code for
	 * @return target code
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
	 * Generates a code fragment for an arithmetic operation.
	 * 
	 * @param name the name of the arithmentic operation (can be div, minus, mult and plus)
	 * @param codeArg1 the code of the source expression (argument 1)
	 * @param codeArg2 the code of the argument expression (argument 2)
	 * @return declarative code fragment for the arithmetic operation
	 */
	protected String handleArithmeticOperation(String name, String codeArg1, String codeArg2) {
		Template template = templateEngine.getTemplate("arithmetic_expression_" + name);
		
		template.setAttribute("operand1", codeArg1);
		template.setAttribute("operand2", codeArg2);
		
		return template.toString();
	}
	
	/**
	 * Generates a code fragment for an allInstances operation.
	 * 
	 * @param guide the guide to the classifier representation
	 * @return declarative code fragment for the allInstances operation
	 */
	protected String handleAllInstances(Guide guide) {
		guide.reset();
		
		Template template = templateEngine.getTemplate("feature_call_allinstances");
		
		template.setAttribute("object", guide.getSelect());
		template.setAttribute("source", guide.getFrom());
		
		return template.toString();
	}
	
	/**
	 * Generates a code fragment for a count operation.
	 * 
	 * @param collection the declarative code fragment representing the source collection
	 * @param guide the guide to the source expression
	 * @param object argument passed to the feature call which is used as reference object
	 * @return declarative code fragment for the count operation
	 */
	protected String handleCollCount(String collection, Guide guide, String object) {
		assert(guide != null) : "count(): guide may not be null";
		guide.reset();
		
		Template template = templateEngine.getTemplate("feature_call_count");
		
		template.setAttribute("source", guide.getFrom());
		template.setAttribute("element", guide.getSelect());
		template.setAttribute("collection", collection);
		template.setAttribute("object", object);
		
		return template.toString();	
	}
	
	/**
	 * Generates a code fragment for an excludes operation.
	 * 
	 * @param collection the declarative code fragment representing the source collection
	 * @param object argument passed to the feature call which is used as reference object
	 * @return declarative code fragment for the excludes operation
	 */
	protected String handleCollExcludes(String collection, String object) {
		Template template = templateEngine.getTemplate("feature_call_excludes");
		
		template.setAttribute("collection", collection);
		template.setAttribute("object", object);
		
		return template.toString();
	}
	
	/**
	 * Generates a code fragment for an excludesAll operation.
	 * 
	 * @param collection the declarative code fragment representing the source collection
	 * @param collection2 argument passed to the feature call which is used as reference collection
	 * @return declarative code fragment for the excludesAll operation
	 */
	protected String handleCollExcludesAll(String collection, String collection2) {
		Template template = templateEngine.getTemplate("feature_call_excludesall");
		
		template.setAttribute("collection", collection);
		template.setAttribute("collection2", collection2);
		
		return template.toString();
	}
	
	/**
	 * Generates a code fragment for an excluding operation.
	 * 
	 * @param collection the declarative code fragment representing the source collection
	 * @param object argument passed to the feature call which is used as reference object
	 * @param collType collection type to determine the template to use (BagType, SequenceType, SetType)
	 * @return declarative code fragment for the excluding operation
	 */
	protected String handleCollExcluding(String collection, String object, CollectionType collType) {
		assert(collType != null): "including() collType may not be null";
		
		Template template = null; 
		
		if (collType instanceof BagType) {
			template = templateEngine.getTemplate("feature_call_excluding_bag");
		} else if (collType instanceof SequenceType) {
			template = templateEngine.getTemplate("feature_call_excluding_sequence");
		} else if (collType instanceof SetType) {
			template = templateEngine.getTemplate("feature_call_excluding_set");
		} else {
			throw new IllegalStateException("Unhandled collection type for excluding operation!"); 
		}
		
		template.setAttribute("object", object);
		template.setAttribute("element", mappedModel.getClass(collType.getElementType().getNameA()).getClassGuide().getSelect());
		template.setAttribute("collection", collection);
		
		return template.toString();		
	}
	
	/**
	 * Generates a code fragment for an exists operation.
	 * 
	 * @param collection the declarative code fragment representing the source collection
	 * @param guide the guide to the source expression
	 * @param expression argument passed to the feature call
	 * @return declarative code fragment for the exists operation
	 */
	protected String handleCollExists(String collection, Guide guide, String expression) {
		assert(guide != null) : "exists(): guide may not be null";
		guide.reset();

		Template template = templateEngine.getTemplate("feature_call_exists");
		
		template.setAttribute("source", guide.getFrom());
        template.setAttribute("object", guide.getSelect());
        template.setAttribute("collection", collection);
        template.setAttribute("expression", expression);
        
        return template.toString();
	}
	
	/**
	 * Generates a code fragment for an includes operation.
	 * 
	 * @param collection the declarative code fragment representing the source collection
	 * @param object argument passed to the feature call which is used as reference object
	 * @return declarative code fragment for the includes operation
	 */
	protected String handleCollIncludes(String collection, String object) {
		Template template = templateEngine.getTemplate("feature_call_includes");
		
		template.setAttribute("collection", collection);
		template.setAttribute("object", object);
		
		return template.toString();
	}
	
	/**
	 * Generates a code fragment for an includesAll operation.
	 * 
	 * @param collection the declarative code fragment representing the source collection
	 * @param collection2 argument passed to the feature call which is used as reference collection
	 * @return declarative code fragment for the includesAll operation
	 */
	protected String handleCollIncludesAll(String collection, String collection2) {
		Template template = templateEngine.getTemplate("feature_call_includesall");
		
		template.setAttribute("collection", collection);
		template.setAttribute("collection2", collection2);
		
		return template.toString();
	}
	
	/**
	 * Generates a code fragment for an including operation.
	 * 
	 * @param collection the declarative code fragment representing the source collection
	 * @param object argument passed to the feature call which is used as reference object
	 * @param collType collection type to determine the template to use (BagType, SequenceType, SetType) 
	 * @return declarative code fragment for the including operation
	 */
	protected String handleCollIncluding(String collection, String object, CollectionType collType) {
		assert(collType != null): "including() collType may not be null";
		
		Template template = null; 
		
		if (collType instanceof BagType) {
			template = templateEngine.getTemplate("feature_call_including_bag");
		} else if (collType instanceof SequenceType) {
			template = templateEngine.getTemplate("feature_call_including_sequence");
		} else if (collType instanceof SetType) {
			template = templateEngine.getTemplate("feature_call_including_set");
		} else {
			throw new IllegalStateException("Unhandled collection type for including operation!"); 
		}
		
		template.setAttribute("object", object);
		template.setAttribute("collection", collection);
		
		return template.toString();		
	}
	
	/**
	 * Generates a code fragment for a intersection operation.
	 * 
	 * @param collection the declarative code fragment representing the source collection
	 * @param collection2 argument passed to the feature call which is used as reference collection
	 * @param collType collection type to determine the template to use (BagType, SetType)
	 * @return declarative code fragment for the intersection operation
	 */
	protected String handleCollIntersection(String collection, String collection2, CollectionType collType) {
		assert(collType != null): "intersection() collType may not be null";
		
		Template template = null;
		
		if (collType instanceof BagType) {
			template = templateEngine.getTemplate("feature_call_intersection_bag");
		} else if (collType instanceof SetType) {
			template = templateEngine.getTemplate("feature_call_intersection_set");
		} else {
			throw new IllegalStateException("Unhandled collection type for intesection operation!");
		}
		
		template.setAttribute("collection", collection);
		template.setAttribute("collection2", collection2);
		
		return template.toString();		
	}
	
	/**
	 * Generates a code fragment for a isEmpty operation
	 * 
	 * @param collection the declarative code fragment representing the source collection
	 * @return declarative code fragment for the isEmpty operation
	 */
	protected String handleCollIsEmpty(String collection) {
		Template template = templateEngine.getTemplate("feature_call_isempty");
		
		template.setAttribute("collection", collection);
		
		return template.toString();
	}
	
	/**
	 * Generates a code fragment for a notEmpty operation.
	 * 
	 * @param collection the declarative code fragment representing the source collection
	 * @return declarative code fragment for the notEmpty operation
	 */
	protected String handleCollNotEmpty(String collection) {
		Template template = templateEngine.getTemplate("feature_call_notempty");
		
		template.setAttribute("collection", collection);
		
		return template.toString();
	}
	
	/**
	 * Generates a code fragment for a size operation.
	 * 
	 * @param collection the declarative code fragment representing the source collection
	 * @param guide the guide to the source expression
	 * @return declarative code fragment for the size operation
	 */
	protected String handleCollSize(String collection, Guide guide) {
		assert(guide != null) : "size(): guide may not be null";
		guide.reset();

		Template template = templateEngine.getTemplate("feature_call_size");
		
		template.setAttribute("source", guide.getFrom());
        template.setAttribute("element", guide.getSelect());
        template.setAttribute("collection", collection);
        
        return template.toString();
	}
	
	/**
	 * Generates a code fragment for a sum operation.
	 * 
	 * @param collection the declarative code fragment representing the source collection
	 * @param guide the guide to the source expression
	 * @return declarative code fragment for the sum operation
	 */
	protected String handleCollSum(String collection, Guide guide) {
		assert(guide != null) : "sum(): guide may not be null";
		guide.reset();
		
		Template template = templateEngine.getTemplate("feature_call_sum");
		
		template.setAttribute("source", guide.getFrom());
        template.setAttribute("element", guide.getSelect());
        template.setAttribute("collection", collection);
        
        return template.toString();
	}
	
	/**
	 * Generates a code fragment for a symmetricDifference operation.
	 * 
	 * @param collection the declarative code fragment representing the source collection
	 * @param collection2 the declarative code fragment representing the collection passed to the feature call which is used to build the symmetric difference
	 * @return declarative code fragment for the symmetricDifference operation
	 */
	protected String handleCollSymmetricDifference(String collection, String collection2) {
		Template template = templateEngine.getTemplate("feature_call_symmetricdifference");
		
		template.setAttribute("collection", collection2);
		template.setAttribute("collection2", collection);
		
		return template.toString();		
	}
	
	/**
	 * Generates a declarative code fragment for an union operation.
	 * 
	 * @param collection the declarative code fragment representing the source collection
	 * @param collection2 the declarative code fragment representing the collection passed to the feature call which is used to build the union of the two collections
	 * @param collType collection type to determine the template to use (BagType, SequenceType, SetType)
	 * @return declarative code fragment for the union operation
	 */
	protected String handleCollUnion(String collection, String collection2, CollectionType collType) {
		assert(collType != null): "including() collType may not be null";
		
		Template template = null; 
		
		if (collType instanceof BagType) {
			template = templateEngine.getTemplate("feature_call_union_bag");
		} else if (collType instanceof SequenceType) {
			template = templateEngine.getTemplate("feature_call_union_sequence");
		} else if (collType instanceof SetType) {
			template = templateEngine.getTemplate("feature_call_union_set");
		} else {
			throw new IllegalStateException("Unhandled collection type for union operation!"); 
		}
		
		template.setAttribute("element", mappedModel.getClass(collType.getElementType().getNameA()).getClassGuide().getSelect());
		template.setAttribute("collection", collection);
		template.setAttribute("collection2", collection2);
		
		return template.toString();		
	}
	
	/**
	 * Generates a declarative code fragment for a abs operation on Integer/Real.
	 * 
	 * @param operand declarative code fragment for the operand to the abs operation
	 * @return declarative code fragment for the abs operation
	 */
	protected String handleIntAbs(String operand) {
		Template template = templateEngine.getTemplate("feature_call_int_abs");
		
		template.setAttribute("operand", operand);
		
		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for a div operation on Integer/Real.
	 * 
	 * @param operand1 declarative code fragment for the first operand of the div operation
	 * @param operand2 declarative code fragment for the second operand of the div operation
	 * @return declarative code fragment for the div operation 
	 */
	protected String handleIntDiv(String operand1, String operand2) {
		Template template = templateEngine.getTemplate("feature_call_int_div");
		
		template.setAttribute("operand1", operand1);
		template.setAttribute("operand2", operand2);
		
		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for a floor operation on Integer/Real.
	 * 
	 * @param operand declarative code fragment for the operand of the floor operation
	 * @return declarative code fragment for the floor operation
	 */
	protected String handleIntFloor(String operand) {
		Template template = templateEngine.getTemplate("feature_call_int_floor");
		
		template.setAttribute("operand", operand);
		
		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for a max operation on Integer/Real.
	 * 
	 * @param operand1 declarative code fragment for the first operand of the max operation
	 * @param operand2 declarative code fragment for the second operand of the max operation
	 * @return declarative code fragment for the max operation
	 */
	protected String handleIntMax(String operand1, String operand2) {
		Template template = templateEngine.getTemplate("feature_call_int_max");
		
		template.setAttribute("operand1", operand1);
		template.setAttribute("operand2", operand2);
		
		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for a min operation on Integer/Real.
	 * 
	 * @param operand1 declarative code fragment for the first operand of the min operation
	 * @param operand2 declarative code fragment for the second operand of the min operation
	 * @return declarative code fragment for the min operation 
	 */
	protected String handleIntMin(String operand1, String operand2) {
		Template template = templateEngine.getTemplate("feature_call_int_min");
		
		template.setAttribute("operand1", operand1);
		template.setAttribute("operand2", operand2);
		
		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for a mod operation on Integer/Real.
	 * 
	 * @param operand1 declarative code fragment for the first operand of the mod operation
	 * @param operand2 declarative code fragment for the second operand of the div operation
	 * @return declarative code fragment for the mod operation 
	 */
	protected String handleIntMod(String operand1, String operand2) {
		Template template = templateEngine.getTemplate("feature_call_int_mod");
		
		template.setAttribute("operand1", operand1);
		template.setAttribute("operand2", operand2);
		
		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for a round operation on Integer/Real.
	 * 
	 * @param operand declarative code fragment for the operand of the round operation
	 * @return declarative code fragment for the round operation
	 */
	protected String handleIntRound(String operand) {
		Template template = templateEngine.getTemplate("feature_call_int_round");
		
		template.setAttribute("operand", operand);
		
		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for a collect iterator.
	 * 
	 * @param codeSrcExp the code of the source expression
	 * @return declarative code fragment for the collect iterator
	 */
	protected String handleIterCollect(String codeSrcExp) {
		Template template = templateEngine.getTemplate("feature_call_collect");
		
		template.setAttribute("expression", codeSrcExp);
		
		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for a forAll iterator.
	 * 
	 * @param collection the declarative code fragment representing the source collection
	 * @param guide the Guide to the source expression
	 * @param expression argument passed to the iterator
	 * @return declarative code fragment for the forAll iterator
	 */
	protected String handleIterForAll(String collection, Guide guide, String expression) {
		guide.reset();
		
		Template template = templateEngine.getTemplate("feature_call_forall");
		
		template.setAttribute("source", guide.getFrom());
		template.setAttribute("alias", aliasList.getLast());
		template.setAttribute("object", guide.getSelect());
		template.setAttribute("collection", collection);
		template.setAttribute("expression", expression);

		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for a reject iterator.
	 * 
	 * @param collection the declarative code fragment representing the source collection
	 * @param guide the Guide to the source expression
	 * @param expression argument passed to the iterator
	 * @return declarative code fragment for the reject iterator
	 */
	protected String handleIterReject(String collection, Guide guide, String expression) {
		guide.reset();
		
		Template template = templateEngine.getTemplate("feature_call_reject");
		
		template.setAttribute("source", guide.getFrom());
		template.setAttribute("alias", aliasList.getLast());
		template.setAttribute("object", guide.getSelect());
		template.setAttribute("collection", collection);
		template.setAttribute("expression", expression);

		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for a select iterator
	 * 
	 * @param collection the declarative code fragment representing the source collection
	 * @param guide the Guide to the source expression
	 * @param expression argument passed to the iterator
	 * @return declarative code fragment for the select iterator
	 */
	protected String handleIterSelect(String collection, Guide guide, String expression) {
		guide.reset();
			
		Template template = templateEngine.getTemplate("feature_call_select");
		
		template.setAttribute("source", guide.getFrom());
		template.setAttribute("alias", aliasList.getLast());
		template.setAttribute("object", guide.getSelect());
		template.setAttribute("collection", collection);
		template.setAttribute("expression", expression);

		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for a logical expression.
	 * 
	 * @param name name of the logical expression (and, or, xor, implies) 
	 * @param expression1 declarative code fragment of the first expression
	 * @param expression2 declarative code fragment of the second expression
	 * @return declarative code fragment for the logical expression
	 */
	protected String handleLogicalExpression(String name, String expression1, String expression2) {
		Template template = templateEngine.getTemplate("logical_expression_" + name);
		
		template.setAttribute("expression1", expression1);
		template.setAttribute("expression2", expression2);
		
		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for a oclIsKindOf feature call.
	 * 
	 * @param guide the guide to the classifier type
	 * @return declarative code fragment for the oclIsKindOf feature call
	 */
	protected String handleOclIsKindOf(Guide guide) {
		guide.reset();
		
		Template template = templateEngine.getTemplate("feature_call_ocliskindof");
		
		template.setAttribute("source", guide.getFrom());
		template.setAttribute("object", guide.getSelect());
		template.setAttribute("alias", aliasList.getLast());
		
		return template.toString();		
	}
	
	/**
	 * Generates a declarative code fragment for a oclIsTypeOf feature call.
	 * 
	 * @param typeGuide the guide to the classifier type
	 * @param supertypeGuide the guide to the classifiers supertype
	 * @return declarative code fragment for the oclIsTypeOf feature call
	 */
	protected String handleOclIsTypeOf(Guide typeGuide, Guide supertypeGuide) {
		typeGuide.reset();
		if (supertypeGuide != null) supertypeGuide.reset();
		
		Template template = templateEngine.getTemplate("feature_call_oclistypeof");
		
		template.setAttribute("source", typeGuide.getFrom());
		template.setAttribute("object", typeGuide.getSelect());
		template.setAttribute("alias", aliasList.getLast());
		template.setAttribute("source2", (supertypeGuide != null) ? supertypeGuide.getFrom() : null);
		
		return template.toString();		
	}
	
	/**
	 * Generates a declarative code fragment for a relational expression.
	 * 
	 * @param name name of the relational expression (equal, nequal, greater, lesser, greaterequal, lesserequal)
	 * @param srcExp the OclExpression representing the first argument used to determine the operand types
	 * @param operand1 declarative code fragment of the first operand
	 * @param operand2 declarative code fragment of the second operand
	 * @return declarative code fragment for the equals operation
	 */
	protected String handleRelExpression(String name, OclExpression srcExp, String operand1, String operand2) {
		StringBuffer templateName = new StringBuffer("relational_expression_");
		Classifier attrType = null;
		
		templateName.append(name);
		templateName.append("_");
		
		if (srcExp instanceof OperationCallExp) {
			attrType = srcExp.getType();
		} else if (srcExp instanceof AttributeCallExp) {
			attrType = ((AttributeCallExp)srcExp).getReferredAttribute().getTypeA();
		} else if (srcExp instanceof PrimitiveLiteralExp) {
			attrType = ((PrimitiveLiteralExp)srcExp).getType();
		} else {
			throw new IllegalStateException("Unhandled attribute type for relational expression: " + srcExp.getClass().getName() + "!");
		}	
		
		if (attrType instanceof Primitive) {
			if (attrType.getNameA().equals("Boolean")) {
				templateName.append("boolean"); 
			} else if (attrType.getNameA().equals("Enumeration")) {
				templateName.append("enumeration");
			} else if (attrType.getNameA().equals("Integer")) {
				templateName.append("integer");
			} else if (attrType.getNameA().equals("Real")) {
				templateName.append("real");
			} else if (attrType.getNameA().equals("String")) {
				templateName.append("string");
			} else {
				throw new IllegalStateException("Unhandled primitive type for relational expression!");
			}
		} else if (attrType instanceof CollectionType) {
			if(attrType instanceof SetType) {
				templateName.append("set");
            } else if(attrType instanceof SequenceType) {
            	templateName.append("sequence");
            } else if(attrType instanceof BagType) {
            	templateName.append("bag");
            } else if(attrType instanceof OrderedSetType) {
            	templateName.append("orderedset");
            } else {
				throw new IllegalStateException("Unhandled collection type for relational expression!");
			}
		} else {
			templateName.append("any");
		}
		
		Template template = templateEngine.getTemplate(templateName.toString());
		template.setAttribute("operand1", operand1);
		template.setAttribute("operand2", operand2);
		
		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for a concat operation on strings.
	 * 
	 * @param operand1 declarative code fragment of the first argument (usually a simple string)
	 * @param operand2 declarative code fragment of the second argument (usually a simple string)
	 * @return declarative code fragment for the concat operation
	 */
	protected String handleStringConcat(String operand1, String operand2) {
		Template template = templateEngine.getTemplate("feature_call_string_concat");
		
		template.setAttribute("operand1", operand1);
		template.setAttribute("operand2", operand2);
		
		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for a size operation on strings.
	 * 
	 * @param operand declarative code fragment of the operand (the string to get the size from) 
	 * @return declarative code fragment for the size operation
	 */
	protected String handleStringSize(String operand) {
		Template template = templateEngine.getTemplate("feature_call_string_size");
		
		template.setAttribute("operand", operand);
		
		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for a substring operation.
	 * 
	 * @param operand declarative code fragment of the operand (the string to build the substring from) 
	 * @param start the start of the substring
	 * @param end the end of the substring
	 * @return declarative code fragment for the substring operation
	 */
	protected String handleStringSubstring(String operand, String start, String end) {
		Template template = templateEngine.getTemplate("feature_call_string_substring");
		
		template.setAttribute("operand", operand);
		template.setAttribute("start", start);
		template.setAttribute("end", end);
		
		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for a toLower operation on strings.
	 * 
	 * @param operand declarative code fragment of the operand
	 * @return declarative code fragment for the toLower operation
	 */
	protected String handleStringToLower(String operand) {
		Template template = templateEngine.getTemplate("feature_call_string_tolower");
		
		template.setAttribute("operand", operand);
		
		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for a toUpper operation on strings.
	 * 
	 * @param operand declarative code fragment of the operand
	 * @return declarative code fragment for the toUpper operation
	 */
	protected String handleStringToUpper(String operand) {
		Template template = templateEngine.getTemplate("feature_call_string_toupper");
		
		template.setAttribute("operand", operand);
		
		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for a unary minus operation.
	 * 
	 * @param operand declarative code fragment of the operand
	 * @return declarative code fragment for the unary minus operation
	 */
	protected String handleUnaryMinus(String operand) {
		Template template = templateEngine.getTemplate("unary_expression_minus");
		
		template.setAttribute("operand", operand);
		
		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for a unary not operation.
	 * 
	 * @param operand declarative code fragment of the operand
	 * @return declarative code fragment for the unary not operation
	 */
	protected String handleUnaryNot(String operand) {
		Template template = templateEngine.getTemplate("unary_expression_not");
		
		template.setAttribute("operand", operand);
		
		return template.toString();		
	}
	
	/**
	 * Creates a declarative code fragment for a navigation described by the Guide objects
	 * in the guides parameter.
	 * 
	 * @param guides the guides which are describing the navigation
	 * @return declarative code fragment for a navigation in the target model
	 */
	protected String createNavigation(List<Guide> guides) {
		String navigationExpression = "";
		String targetCodeArtifact = "";
	    String placeHolder = "##_dt_##";
	    
	    Guide guide;
	    Template template;
	    
	    for (int i = 0; i < guides.size(); i++) {
	    	guide = guides.get(i); 
	    	guide.reset();
						
	    	for (int k = 0; k < guide.numberOfSteps(); k++, guide.next()) {

	    		// last step must be treated in a special way
	    		if ((i == (guides.size() - 1))  && (k == (guide.numberOfSteps() - 1))) {
	    			template = templateEngine.getTemplate("feature_call_navigation_context");
	    			template.setAttribute("context_alias", guide.getAlias());
	    			template.setAttribute("context_object", contextList.getLast());
	    		} else {
	    			template = templateEngine.getTemplate("feature_call_navigation");
	    			template.setAttribute("context2", placeHolder);
	    		}
                                
	    		template.setAttribute("object", guide.getSelect());
	    		template.setAttribute("context1", guide.getFrom());
	    		template.setAttribute("ref_object", guide.getWhere());
                
	    		targetCodeArtifact = template.toString();
               
	    		if (navigationExpression.length() == 0) {
	    			navigationExpression = targetCodeArtifact;
	    		} else {
	    			navigationExpression = navigationExpression.replaceAll(placeHolder, targetCodeArtifact);
                }
   			}
	    }
	    
	    return navigationExpression;
    }
	
	/**
	 * Generates a declarative code fragment for the given AssociationClassCallExp
	 * 
	 * @param exp AssociationClassCallExp to generate code for
	 * @return a declarative code fragment for this expression 
	 */
	public String transform(AssociationClassCallExp exp) {
		List<Guide> guides = assignGuides(exp);
		
		if (guides.get(0).isNavigation()) {	
            return createNavigation(guides);
		}
		
		return null;
	}
	
	/**
	 * Generates a declarative code fragment for the given AssociationEndCallExp
	 * 
	 * @param exp AssociationEndCallExp to generate code for
	 * @return a declarative code fragment for this expression 
	 */
	public String transform(AssociationEndCallExp exp) {
		List<Guide> guides = assignGuides(exp);
		
		if (guides.get(0).isNavigation()) {	
            return createNavigation(guides);
		}		

		return null;
	}
	
	/**
	 * Generates a declarative code fragment for the given AttributeCallExp
	 * 
	 * @param exp AttributeCallExp to generate code for
	 * @return a declarative code fragment for this expression 
	 */
    public String transform(AttributeCallExp exp){
		List<Guide> guides = assignGuides(exp);;
		Guide guide = guides.get(0);
        guide.reset();
	
        // attribute access without navigation
		if (guides.size() == 1) {
			Template template = templateEngine.getTemplate("feature_call_attribute_context");
			template.setAttribute("context_alias", guide.getAlias());
			template.setAttribute("attribute", guide.getSelect());
			
			// special case for Boolean attributes: e.g. expand 'attribute' to 'attribute = 1' in SQL
			if (exp.getReferredAttribute().getTypeA().getNameA().equals("Boolean")) {
				String attr = template.toString();
				template = templateEngine.getTemplate("feature_call_attribute_boolean");
				template.setAttribute("attribute", attr);
			}
			
			return template.toString();
		}
		// attribute access with navigation
		else {	
			return createNavigation(guides);
		}     
    }
    
    /**
	 * Generates a declarative code fragment for the given BooleanLiteralExp
	 * 
	 * @param exp BooleanLiteralExp to generate code for
	 * @return a declarative code fragment for this expression 
	 */
	public String transform(BooleanLiteralExp exp) {
		Template template;
		
		if (exp.isBooleanSymbol()) {
			template = templateEngine.getTemplate("literal_boolean_true");
		} else {
			template = templateEngine.getTemplate("literal_boolean_false");
		}
		
		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for the given CollectionLiteralExp
	 * 
	 * @param exp CollectionLiteralExp to generate code for
	 * @return a declarative code fragment for this expression 
	 */
	public String transform(CollectionLiteralExp exp) {
		List parts = exp.getParts();
		String templateName = "";
		Template template;
		
		// determine template to use depending on collection kind
		if (exp.getKind() == CollectionKindEnum.BAG) {
			templateName = "literal_collection_bag";
		} else if (exp.getKind() == CollectionKindEnum.SET) {
			templateName = "literal_collection_set";
		} else if (exp.getKind() == CollectionKindEnum.SEQUENCE) {
			templateName = "literal_collection_sequence";
		} else {
			assert(false): "Unknown CollectionKind";
		}
		
		// get template for items in the collection
		template = templateEngine.getTemplate(templateName);
		
		// parameterize the template engine
        for(int i = 0; i < parts.size(); i++) {
        	template.setAttribute("items", getCode((OclExpression) ((CollectionItem) parts.get(i)).getItem()));
        }
        
		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for the given EnumLiteralExp
	 * 
	 * @param exp EnumLiteralExp to generate code for
	 * @return a declarative code fragment for this expression 
	 */
	public String transform(EnumLiteralExp exp) {
		Template template = templateEngine.getTemplate("literal_enum");
	
		// parameterize the template engine
		template.setAttribute("value", exp.getReferredEnumLiteral().getNameA());
		
		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for the given OclExpression
	 * 
	 * @param exp expression to generate code for
	 * @return a declarative code fragment for this expression 
	 */
	public String transform(ExpressionInOcl exp) {

		MappedClass constrainedType = mappedModel.getClass(exp.getContextualClassifier().getNameA());
		Guide classGuide = mappedModel.getClass(constrainedType.getName()).getClassGuide();
		classGuide.reset();
		contextList.add(classGuide.getWhere());
		String constraintName;
		
		if (exp.getConstraintA() != null) {
			constraintName = exp.getConstraintA().getNameA();
		} else {
			constraintName = "UNAMED CONSTRAINT";
		}
		
		String expression = getCode(exp.getBodyExpression());
		
		Template template = templateEngine.getTemplate("constraint_body");
		template.setAttribute("constraint_name", constraintName);
		template.setAttribute("context", classGuide.getFrom());
		template.setAttribute("context_alias", aliasList.getLast());
		template.setAttribute("expression", expression);
		
		
		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for the given IfExp
	 * 
	 * @param exp IfExp to generate code for
	 * @return a declarative code fragment for this expression 
	 */
	public String transform(IfExp exp) {
		Template template = templateEngine.getTemplate("if_expression");
		
		String srcCond = getCode(exp.getCondition());
		String srcThen = getCode(exp.getThenExpression());
		String srcElse = getCode(exp.getElseExpression());
		
		// parameterize the template engine
		template.setAttribute("if_branch", srcCond);
		template.setAttribute("then_branch", srcThen);
		template.setAttribute("else_branch", srcElse);
						
		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for the given IntegerLiteralExp
	 * 
	 * @param exp IntegerLiteralExp to generate code for
	 * @return a declarative code fragment for this expression 
	 */
	public String transform(IntegerLiteralExp exp) {
		Template template = templateEngine.getTemplate("literal_integer");
		
		// parameterize the template engine
		template.setAttribute("value", new Integer(exp.getIntegerSymbol()).toString());
				
		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for the given IteratorExp
	 * 
	 * @param exp IteratorExp to generate code for
	 * @return a declarative code fragment for this expression 
	 */
	public String transform(IteratorExp exp) {
		StringBuffer result = new StringBuffer();
		OclExpression srcExp = exp.getSource();
		String name = exp.getNameA();
		
		// get code for source expression
		String codeSrcExp = getCode(srcExp);

		// add new alias for iterator to the end of the alias list
		aliasList.addLast(mappedModel.getUniqueAlias());

		// evaluate the arguments
		List<String> args = new ArrayList<String>();
		args.add(getCode(exp.getBody()));
        
		if (name.equals("collect")) {
			// collect is special and weird, hackfix
			Guide guideSrc = navigationMap.get(srcExp).get(0); guideSrc.reset();
			Guide guideArg = navigationMap.get(exp.getBody()).get(0); guideArg.reset();
			navigationMap.put(exp, navigationMap.get(exp.getBody()));
			
			List<Guide> nav = new LinkedList<Guide>();
			nav.add(guideArg);
			nav.add(guideSrc);
			result.append(handleIterCollect(createNavigation(nav)));
		} else if (name.equals("forAll")) {
			result.append(handleIterForAll(codeSrcExp, navigationMap.get(srcExp).get(0), args.get(0)));
		} else if (name.equals("reject")) {
			List<Guide> guides = assignClassGuide(exp, ((CollectionType)srcExp.getType()).getElementType());
			result.append(handleIterReject(codeSrcExp, guides.get(0), args.get(0)));
		} else if (name.equals("select")) {
			List<Guide> guides = assignClassGuide(exp, ((CollectionType)srcExp.getType()).getElementType());			
			result.append(handleIterSelect(codeSrcExp, guides.get(0), args.get(0)));
		} else if (name.equals("iterate")) {
			throw new UnsupportedOperationException("The iterate operation is not supported.");
		}
		
		// remove last alias from alias list
		aliasList.removeLast();
		
		return result.toString();
	}
	
	/**
	 * Generates a declarative code fragment for the given OclOperationWithTypeArgExp
	 * 
	 * @param exp OclOperationWithTypeArgExp to generate code for
	 * @return a declarative code fragment for this expression 
	 */
	public String transform(OclOperationWithTypeArgExp exp) {
		StringBuffer result = new StringBuffer();
		String name = exp.getNameA();
		
		if (name.equals("oclIsTypeOf")) {
			result.append(handleOclIsKindOf(mappedModel.getClass(exp.getTypeArgument().getNameA()).getClassGuide()));
		} else if (name.equals("oclIsKindOf")) {
			List<Classifier> parents = MetaModelHelper.getSupertypes(exp.getTypeArgument());
			
			if (parents.size() != 1) {
	    		throw new IllegalStateException("Illegal number of supertypes for type: " + exp.getTypeArgument().getNameA() + " !");
	    	}
			
			result.append(handleOclIsTypeOf(mappedModel.getClass(exp.getTypeArgument().getNameA()).getClassGuide(),
					mappedModel.getClass(parents.get(0).getNameA()).getClassGuide()));
		}
			
		return result.toString();
	}
	
	/**
	 * Generates a declarative code fragment for the given OperationCallExp
	 * 
	 * @param exp OperationCallExp to generate code for
	 * @return a declarative code fragment for this expression 
	 */
	public String transform(OperationCallExp exp) {
		StringBuffer result = new StringBuffer("");
		OclExpression srcExp = exp.getSource();
		List arguments = exp.getArguments();
        Operation op = exp.getReferredOperation();
        String name = op.getNameA();
        String firstArg = null;
		
		// get code for source expression (will be argument 1)
		String codeSrcExp = getCode(srcExp);
		
		// evaluate the arguments
		List<String> args = new ArrayList<String>();
        for(int i = 0; i < arguments.size(); i++) {
			args.add(getCode((OclExpression) arguments.get(i)));
        }
        if (args.size() > 0) {
        	firstArg = args.get(0);
        }
		
		// relational expressions
		if (name.equals("=")) {
			result.append(handleRelExpression("equals", srcExp, codeSrcExp, firstArg));						
		} else if (name.equals("<>")) {
			result.append(handleRelExpression("nequals", srcExp, codeSrcExp, firstArg));						
		} else if (name.equals(">")) {
			result.append(handleRelExpression("greater", srcExp, codeSrcExp, firstArg));
		} else if (name.equals(">=")) {
			result.append(handleRelExpression("greaterequal", srcExp, codeSrcExp, firstArg));
		} else if (name.equals("<")) {
			result.append(handleRelExpression("lesser", srcExp, codeSrcExp, firstArg));
		} else if (name.equals("<=")) {
			result.append(handleRelExpression("lesserequal", srcExp, codeSrcExp, firstArg));
		}
		
		// arithmetic expressions
		else if (name.equals("+")) {
			result.append(handleArithmeticOperation("plus", codeSrcExp, firstArg));
		} else if (name.equals("*")) {
			result.append(handleArithmeticOperation("mult", codeSrcExp, firstArg));
		} else if (name.equals("/")) {
			result.append(handleArithmeticOperation("div", codeSrcExp, firstArg));
		} else if (name.equals("-")) {
			if (args.isEmpty()) { // unary minus special case
				result.append(handleUnaryMinus(codeSrcExp));
			} else {
				result.append(handleArithmeticOperation("minus", codeSrcExp, firstArg));
			}
		}

		// unary expressions (unary minus special case above)
		else if (name.equals("not")) {
			result.append(handleUnaryNot(codeSrcExp));
		}

		// logical expression
		else if (name.equals("and")) {
			result.append(handleLogicalExpression("and", codeSrcExp, firstArg));
		} else if (name.equals("or")) {
			result.append(handleLogicalExpression("or", codeSrcExp, firstArg));
		} else if (name.equals("xor")) {
			result.append(handleLogicalExpression("xor", codeSrcExp, firstArg));
		} else if (name.equals("implies")) {
			result.append(handleLogicalExpression("implies", codeSrcExp, firstArg));
		}
		
		
		// collection related operations - BASIC VALUE
		else if (name.equals("count")) {
			result.append(handleCollCount(codeSrcExp, navigationMap.get(srcExp).get(0), firstArg));
		} else if (name.equals("size") && !(srcExp.getType() instanceof Primitive)) {
			result.append(handleCollSize(codeSrcExp, navigationMap.get(srcExp).get(0)));
		} else if (name.equals("sum")) {
			List<Guide> guides;
			if (srcExp instanceof OperationCallExp && ((OperationCallExp)srcExp).getReferredOperation().getNameA().equals("asSet")) {
				guides = navigationMap.get(((OperationCallExp)srcExp).getSource());
			} else {
				guides = navigationMap.get(srcExp);
			}
			result.append(handleCollSum(codeSrcExp, guides.get(0)));
		}
		
		// collection related operations - COMPLEX PREDICATE
		else if (name.equals("includes")) {
			result.append(handleCollIncludes(codeSrcExp, firstArg));
		} else if (name.equals("excludes")) {
			result.append(handleCollExcludes(codeSrcExp, firstArg));
		} else if (name.equals("includesAll")) {
			result.append(handleCollIncludesAll(codeSrcExp, firstArg));
		} else if (name.equals("excludesAll")) {
			result.append(handleCollExcludesAll(codeSrcExp, firstArg));
		} else if (name.equals("isEmpty")) {
			result.append(handleCollIsEmpty(codeSrcExp));
		} else if (name.equals("notEmpty")) {
			result.append(handleCollNotEmpty(codeSrcExp));
		} else if (name.equals("exists")) {
			result.append(handleCollExists(codeSrcExp, navigationMap.get(srcExp).get(0), firstArg));
		} else if (name.equals("intersection")) {
			result.append(handleCollIntersection(codeSrcExp, firstArg, (CollectionType)srcExp.getType()));
		} else if (name.equals("including")) {
			result.append(handleCollIncluding(codeSrcExp, firstArg, (CollectionType)srcExp.getType()));
		} else if (name.equals("excluding")) {
			result.append(handleCollExcluding(codeSrcExp, firstArg, (CollectionType)srcExp.getType()));
		} else if (name.equals("union")) {
			result.append(handleCollUnion(codeSrcExp, firstArg, (CollectionType)srcExp.getType()));
		} else if (name.equals("symmetricDifference")) {
			result.append(handleCollSymmetricDifference(codeSrcExp, firstArg));
		}
		
		// collection related operations - MODEL TYPE QUERY
		else if (name.equals("allInstances")) {
			List<Guide> guides = assignClassGuide(exp, exp.getSrcType()); // create Guide object to the return value of allInstances for later use in the parent of this expression
			result.append(handleAllInstances(guides.get(0)));
		}
		
		// BASIC TYPE - String operations
		else if (name.equals("size")) {
			result.append(handleStringSize(codeSrcExp));
		} else if (name.equals("concat")) {
			result.append(handleStringConcat(codeSrcExp, firstArg));
		} else if (name.equals("toUpper")) {
			result.append(handleStringToUpper(codeSrcExp));
		} else if (name.equals("toLower")) {
			result.append(handleStringToLower(codeSrcExp));
		} else if (name.equals("substring")) {
			result.append(handleStringSubstring(codeSrcExp, firstArg, args.get(1)));
		}
		
		// BASIC TYPE - Real and Integer operations
		else if (name.equals("abs")) {
			result.append(handleIntAbs(codeSrcExp));
		} else if (name.equals("floor")) {
			result.append(handleIntFloor(codeSrcExp));
		} else if (name.equals("max")) {
			result.append(handleIntMax(codeSrcExp, firstArg));
		} else if (name.equals("min")) {
			result.append(handleIntMin(codeSrcExp, firstArg));
		} else if (name.equals("round")) {
			result.append(handleIntRound(codeSrcExp));
		} else if (name.equals("div")) {
			result.append(handleIntDiv(codeSrcExp, firstArg));
		} else if (name.equals("mod")) {
			result.append(handleIntMod(codeSrcExp, firstArg));
		}

		else {
			throw new UnsupportedOperationException("The operation " + name + "is not supported.");
		}
						
		return result.toString();
	}
	
	/**
	 * Generates a declarative code fragment for the given RealLiteralExp
	 * 
	 * @param exp RealLiteralExp to generate code for
	 * @return a declarative code fragment for this expression 
	 */
	public String transform(RealLiteralExp exp) {
		Template template = templateEngine.getTemplate("literal_real");
		
		// parameterize the template engine
		template.setAttribute("value", new Double(exp.getRealSymbol()).toString());
				
		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for the given StringLiteralExp
	 * 
	 * @param exp StringLiteralExp to generate code for
	 * @return a declarative code fragment for this expression 
	 */
	public String transform(StringLiteralExp exp) {
		Template template = templateEngine.getTemplate("literal_string");
		
		// parameterize the template engine
		template.setAttribute("value", exp.getStringSymbol());
				
		return template.toString();
	}
	
	/**
	 * Generates a declarative code fragment for the given VariableExp
	 * 
	 * @param exp VariableExp to generate code for
	 * @return a declarative code fragment for this expression 
	 */
	public String transform(VariableExp exp) {
		Template template = templateEngine.getTemplate("literal_variable");
		
		// parameterize the template engine
		template.setAttribute("value", exp.getNameA());
				
		return template.toString();
	}
	
	/**
	 *  Resets the DeclarativeCodeGenerator to initial values.
     *  Must be used, if multiple invariants will be translated 
     *  by one code generator object.
	 */
	public void reset() {
		String firstAlias = aliasList.getFirst();
		aliasList.clear();
		aliasList.add(firstAlias);
		contextList.remove();
		navigationMap.clear();
	}
}