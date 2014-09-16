/*
 * Copyright (C) 2008-2010 by Claas Wilke (claas.wilke@tu-dresden.de)
 *
 * This file is part of the OCL2Java Code Generator of Dresden OCL.
 *
 * Dresden OCL is free software: you can redistribute it and/or modify 
 * it under the terms of the GNU Lesser General Public License as published by the 
 * Free Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * Dresden OCL is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along 
 * with Dresden OCL. If not, see <http://www.gnu.org/licenses/>.
 */
package org.dresdenocl.tools.codegen.ocl2java.code.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

import org.dresdenocl.essentialocl.expressions.Variable;
import org.dresdenocl.pivotmodel.Type;
import org.dresdenocl.tools.codegen.code.ITransformedCode;
import org.dresdenocl.tools.codegen.ocl2java.code.IOcl2JavaEnvironment;
import org.dresdenocl.tools.template.ITemplate;

/**
 * <p>
 * This class provides methods to get name for variables and fields during code
 * transformation.
 * </p>
 * 
 * @author Claas Wilke
 */
public class Ocl2JavaEnvironment implements IOcl2JavaEnvironment {

	/** Names for generated aspects of Body constraints having no specific name. */
	public static final String BODY_ASPECT_NAME = "BodyAspect";

	/** Names for generated variable for result value. */
	protected static final String BODY_RESULT_VAR_NAME = "bodyResult";

	/**
	 * Names for generated aspects of Definition constraints having no specific
	 * name.
	 */
	public static final String DEF_ASPECT_NAME = "DefAspect";

	/**
	 * Names for generated aspects of Derive constraints having no specific
	 * name.
	 */
	public static final String DERIVE_ASPECT_NAME = "DeriveAspect";

	/**
	 * Names for generated aspects of Initial constraints having no specific
	 * name.
	 */
	public static final String INIT_ASPECT_NAME = "InitAspect";

	/**
	 * Names for generated aspects of Invariant constraints having no specific
	 * name.
	 */
	public static final String INV_ASPECT_NAME = "InvAspect";

	/**
	 * Names for generated aspects of Postcondition constraints having no
	 * specific name.
	 */
	public static final String POST_ASPECT_NAME = "PostAspect";

	/**
	 * Names for generated aspects of Precondition constraints having no
	 * specific name.
	 */
	public static final String PRE_ASPECT_NAME = "PreAspect";

	/** Names for generated variable for atPre value. */
	protected static final String AT_PRE_VAR_NAME = "atPreValue";

	/** Names for generated variable for collection value. */
	protected static final String COLLECTION_VAR_NAME = "collection";

	/** Names for generated variable for compoarator value. */
	protected static final String COMPARATOR_VAR_NAME = "comparator";

	/** Names for generated variable for index value. */
	protected static final String INDEX_VAR_NAME = "index";

	/** Names for generated variable for IfExpression result value. */
	protected static final String IF_EXP_RESULT_VAR_NAME = "ifExpResult";

	/** Names for generated variable for iterator variable. */
	protected static final String ITERATOR_EXP_ITERATOR_VAR_NAME = "anElement";

	/** Names for generated variable for result value. */
	protected static final String RESULT_VAR_NAME = "result";

	/** Names for generated variable for to visit collections. */
	protected static final String TO_VISIT_VAR_NAME = "toVisit";

	/** Names for generated variable for tuple value. */
	protected static final String TUPLE_VAR_NAME = "tuple";

	/**
	 * The canonical names of all classes which use the special OCL operation
	 * allInstances().
	 */
	protected Set<String> allInstancesClasses = new TreeSet<String>();

	/**
	 * The {@link ITransformedCode} of Expressions whose atPre value shall be
	 * stored as key, and their transformed type and name of the variable in
	 * which they shall be stored as {@link String} array.
	 */
	protected Map<ITransformedCode, Object[]> atPreValues = new IdentityHashMap<ITransformedCode, Object[]>();

	/** Counter for names for generated variables for atPre values. */
	protected int atPreVarNameCounter = 0;

	/** Counter for names for generated body constraint aspects. */
	protected int bodyAspectNameCounter = 0;

	/** Counter for names for generated variables for body result values. */
	protected int bodyResultVarCounter = 0;

	/**
	 * The call paths of all properties which are called for Constraint
	 * evaluation.
	 */
	protected Set<String> calledProperties = new TreeSet<String>();

	/**
	 * Contains {@link ITemplate}s of transformed Java classes which are used to
	 * extend constrained classes.
	 */
	protected Map<String, ITemplate> classTemplates = new TreeMap<String, ITemplate>();

	/** Counter for names for generated variables for collection values. */
	protected int collectionVarCounter = 0;

	/** Counter for names for generated variables for comparator values. */
	protected int comparatorVarCounter = 0;

	/** Counter for names for generated defintion constraint aspects. */
	protected int defAspectNameCounter = 0;

	/**
	 * Contains the names of methods allready defined by the names of their
	 * classes.
	 */
	protected Map<String, Set<String>> definedMethodsByClass = new HashMap<String, Set<String>>();

	/** Counter for names for generated derive constraint aspects. */
	protected int deriveAspectNameCounter = 0;

	/** Counter for names for generated variables for index values. */
	protected int indexVarCounter = 0;

	/** Counter for names for generated init constraint aspects. */
	protected int initAspectNameCounter = 0;

	/** Counter for names for generated invariant constraint aspects. */
	protected int invAspectNameCounter = 0;

	/** Counter for names for generated variables for IfExpression rsult values. */
	protected int ifExpResultVarCounter = 0;

	/** Counter for names for generated variables for iterator variables. */
	protected int iteratorExpIteratorVarCounter = 0;

	/** Whether or not the last transformed constraint uses the self variable. */
	protected boolean isUsingSelfVariable = false;

	/**
	 * Contains the name mappings for {@link Variable}s that require renaming
	 * during code generation.
	 */
	protected Map<String, Stack<String>> mappedVariableNames = new HashMap<String, Stack<String>>();

	/**
	 * The canonical names of all classes which use the special OCL operation
	 * oclIsNew().
	 */
	protected Set<String> oclIsNewClasses = new TreeSet<String>();

	/** Counter for names for generated postcondition constraint aspects. */
	protected int postAspectNameCounter = 0;

	/** Counter for names for generated precondition constraint aspects. */
	protected int preAspectNameCounter = 0;

	/** Counter for names for generated variables for result values. */
	protected int resultVarCounter = 0;

	/** {@link Stack} used for expected return {@link Type}s. */
	protected Stack<Type> returnTypeStack = new Stack<Type>();

	/** Counter for names for generated variables for to visit collections. */
	protected int toVisitVarCounter = 0;

	/** Counter for names for generated variables for tuple values. */
	protected int tupleVarCounter = 0;

	/** Contains all names allready used for generated aspects. */
	protected Set<String> usedAspectNames = new HashSet<String>();

	public Ocl2JavaEnvironment() {
		/*
		 * Adds some variable mappings for names that must be always rename
		 * (reserved keywords in Java).
		 */
		Stack<String> stack = new Stack<String>();
		stack.push("clazz");
		mappedVariableNames.put("class", stack);

		stack = new Stack<String>();
		stack.push("ixtends");
		mappedVariableNames.put("extends", stack);

		stack = new Stack<String>();
		stack.push("publik");
		mappedVariableNames.put("public", stack);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.dresdenocl.ocl2java.code.IOcl22JavaEnvironment#
	 * addAllInstancesClass(java.lang.String)
	 */
	public void addAllInstancesClass(String canonicalName) {

		this.allInstancesClasses.add(canonicalName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.ocl2java.code.IOcl22JavaEnvironment#addAtPreValue
	 * (org.dresdenocl.ocl2java.code.ITransformedCode,
	 * org.dresdenocl.pivotmodel.Type)
	 */
	public String addAtPreValue(ITransformedCode sourceCode, Type type) {

		String result;
		Object[] value;

		if (this.atPreValues.containsKey(sourceCode)) {

			result = this.atPreValues.get(sourceCode)[1].toString();
		}

		else {
			result = this.getNewAtPreVarName();
			value = new Object[2];

			value[0] = type;
			value[1] = result;

			this.atPreValues.put(sourceCode, value);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.ocl2java.code.IOcl22JavaEnvironment#addCalledProperty
	 * (java.lang.String)
	 */
	public void addCalledProperty(String callPath) {

		this.calledProperties.add(callPath);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.tools.codegen.ocl2java.code.IOcl2JavaEnvironment
	 * #addDefinedMethod(java.lang.String, java.lang.String)
	 */
	public void addDefinedMethod(String methodName, String className) {

		Set<String> methodsOfClass = this.definedMethodsByClass.get(className);

		if (methodsOfClass == null)
			methodsOfClass = new HashSet<String>();
		// no else.

		methodsOfClass.add(methodName);

		this.definedMethodsByClass.put(className, methodsOfClass);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.ocl2java.code.IOcl22JavaEnvironment#addIsNewClass
	 * (java.lang.String)
	 */
	public void addIsNewClass(String canonicalName) {

		this.oclIsNewClasses.add(canonicalName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.tools.codegen.ocl2java.code.IOcl2JavaEnvironment
	 * #addUsedAspectName(java.lang.String)
	 */
	public void addUsedAspectName(String name) {
		this.usedAspectNames.add(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.tools.codegen.ocl2java.code.IOcl2JavaEnvironment
	 * #addVariableMapping(java.lang.String, java.lang.String)
	 */
	public void addVariableMapping(String oldName, String newName) {
		
		Stack<String> stack;
		
		if (this.mappedVariableNames.containsKey(oldName))
			stack = this.mappedVariableNames.get(oldName);
		else
			stack = new Stack<String>();
		
		stack.add(newName);
		
		this.mappedVariableNames.put(oldName, stack);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.tools.codegen.ocl2java.code.IOcl2JavaEnvironment
	 * #existsMappingForVariable(java.lang.String)
	 */
	public boolean existsMappingForVariable(String name) {
		return this.mappedVariableNames.containsKey(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.dresdenocl.ocl2java.code.IOcl22JavaEnvironment#
	 * getAllInstancesClasses()
	 */
	public Set<String> getAllInstancesClasses() {

		return this.allInstancesClasses;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.ocl2java.code.IOcl22JavaEnvironment#getAtPreValues
	 * ()
	 */
	public Map<ITransformedCode, Object[]> getAtPreValues() {

		return this.atPreValues;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.ocl2java.code.IOcl22JavaEnvironment#getIsNewClasses
	 * ()
	 */
	public Set<String> getIsNewClasses() {

		return this.oclIsNewClasses;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.ocl2java.code.IOcl22JavaEnvironment#getCalledProperties
	 * ()
	 */
	public Set<String> getCalledProperties() {

		return this.calledProperties;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.tools.codegen.ocl2java.code.IOcl2JavaEnvironment
	 * #getExpectedReturnType()
	 */
	public Type getExpectedReturnType() {
		if (this.returnTypeStack.isEmpty())
			return null;
		else
			return this.returnTypeStack.peek();
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.tools.codegen.ocl2java.code.IOcl2JavaEnvironment#getNewBodyResultVarName()
	 */
	public String getNewBodyResultVarName() {
	
		String result;
	
		this.bodyResultVarCounter++;
	
		result = BODY_RESULT_VAR_NAME;
		result += this.bodyResultVarCounter;
	
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.dresdenocl.ocl2java.code.ICodeGenEnvironment#
	 * getNewIteratorExpCollectionVarName()
	 */
	public String getNewCollectionVarName() {

		String result;

		this.collectionVarCounter++;

		result = COLLECTION_VAR_NAME;
		result += this.collectionVarCounter;

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.ocl2java.code.ICodeGenEnvironment#getNewComparatorName
	 * ()
	 */
	public String getNewComparatorName() {

		String result;

		this.comparatorVarCounter++;

		result = COMPARATOR_VAR_NAME;
		result += this.comparatorVarCounter;

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.ocl2java.code.ICodeGenEnvironment#getNewIndexVarName
	 * ()
	 */
	public String getNewIndexVarName() {

		String result;

		this.indexVarCounter++;

		result = INDEX_VAR_NAME;
		result += this.indexVarCounter;

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.ocl22code.ICodeGenEnvironment#getNewIfExpResultName
	 * ()
	 */
	public String getNewIfExpResultName() {

		String result;

		this.ifExpResultVarCounter++;

		result = IF_EXP_RESULT_VAR_NAME;
		result += this.ifExpResultVarCounter;

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.dresdenocl.ocl22code.ICodeGenEnvironment#
	 * getNewIteratorExpIteratorVarName()
	 */
	public String getNewIteratorVarName() {

		String result;

		this.iteratorExpIteratorVarCounter++;

		result = ITERATOR_EXP_ITERATOR_VAR_NAME;
		result += this.iteratorExpIteratorVarCounter;

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.ocl2java.code.ICodeGenEnvironment#getNewResultVarName
	 * ()
	 */
	public String getNewResultVarName() {

		String result;

		this.resultVarCounter++;

		result = RESULT_VAR_NAME;
		result += this.resultVarCounter;

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.tools.codegen.ocl2java.code.IOcl2JavaEnvironment#getNewToVisitVarName()
	 */
	public String getNewToVisitVarName() {

		String result;

		this.toVisitVarCounter++;

		result = TO_VISIT_VAR_NAME;
		result += this.toVisitVarCounter;

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.dresdenocl.ocl2java.code.ICodeTransformationEnvironment#
	 * getNewTupleVarName()
	 */
	public String getNewTupleVarName() {

		String result;

		this.tupleVarCounter++;

		result = TUPLE_VAR_NAME;
		result += this.tupleVarCounter;

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.dresdenocl.ocl2java.code.ICodeTransformationEnvironment#
	 * getNewBodyAspectName()
	 */
	public String getNewBodyAspectName() {

		String result;

		this.bodyAspectNameCounter++;

		result = BODY_ASPECT_NAME;
		result += this.bodyAspectNameCounter;

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.dresdenocl.ocl2java.code.ICodeTransformationEnvironment#
	 * getNewDefAspectName()
	 */
	public String getNewDefAspectName() {

		String result;

		this.defAspectNameCounter++;

		result = DEF_ASPECT_NAME;
		result += this.defAspectNameCounter;

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.dresdenocl.ocl2java.code.ICodeTransformationEnvironment#
	 * getNewDeriveAspectName()
	 */
	public String getNewDeriveAspectName() {

		String result;

		this.deriveAspectNameCounter++;

		result = DERIVE_ASPECT_NAME;
		result += this.deriveAspectNameCounter;

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.ocl2java.code.ICodeGenEnvironment#getNewInitAspectName
	 * ()
	 */
	public String getNewInitAspectName() {

		String result;

		this.initAspectNameCounter++;

		result = INIT_ASPECT_NAME;
		result += this.initAspectNameCounter;

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.ocl2java.code.IOcl22JavaEnvironment#getNewInvAspectName
	 * ()
	 */
	public String getNewInvAspectName() {

		String result;

		this.invAspectNameCounter++;

		result = INV_ASPECT_NAME;
		result += this.invAspectNameCounter;

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.dresdenocl.ocl2java.code.IOcl22JavaEnvironment#
	 * getNewPostAspectName()
	 */
	public String getNewPostAspectName() {

		String result;

		this.postAspectNameCounter++;

		result = POST_ASPECT_NAME;
		result += this.postAspectNameCounter;

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.ocl2java.code.IOcl22JavaEnvironment#getNewPreAspectName
	 * ()
	 */
	public String getNewPreAspectName() {

		String result;

		this.preAspectNameCounter++;

		result = PRE_ASPECT_NAME;
		result += this.preAspectNameCounter;

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.tools.codegen.ocl2java.code.IOcl2JavaEnvironment
	 * #getVariableMapping(java.lang.String)
	 */
	public String getVariableMapping(String name) {
		
		String result = null;
		
		Stack<String> stack = this.mappedVariableNames.get(name);
		
		if (stack != null && stack.size() > 0)
			result = stack.peek();
		
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.dresdenocl.ocl2java.code.IOcl22JavaEnvironment#
	 * hasAllInstancesClasses()
	 */
	public boolean hasAllInstancesClasses() {

		return !this.allInstancesClasses.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.ocl2java.code.IOcl22JavaEnvironment#hasAtPreValues
	 * ()
	 */
	public boolean hasAtPreValues() {

		return this.atPreValues.size() > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.ocl2java.code.IOcl22JavaEnvironment#hasCalledProperties
	 * ()
	 */
	public boolean hasCalledProperties() {

		return this.calledProperties.size() > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.ocl2java.code.IOcl22JavaEnvironment#hasIsNewClasses
	 * ()
	 */
	public boolean hasIsNewClasses() {

		return !this.oclIsNewClasses.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.tools.codegen.ocl2java.code.IOcl2JavaEnvironment
	 * #isMethodAlreadyDefined(java.lang.String, java.lang.String)
	 */
	public boolean isMethodAlreadyDefined(String methodName, String className) {

		boolean result;

		if (this.definedMethodsByClass.containsKey(className)) {
			result = this.definedMethodsByClass.get(className).contains(
					methodName);
		}

		else
			result = false;

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.tools.codegen.ocl2java.code.IOcl2JavaEnvironment
	 * #isUsedAspectName(java.lang.String)
	 */
	public boolean isUsedAspectName(String name) {
		return name != null && name.length() > 0
				&& this.usedAspectNames.contains(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.tools.codegen.ocl2java.code.IOcl2JavaEnvironment
	 * #isUsingSelfVariable()
	 */
	public boolean isUsingSelfVariable() {
		return this.isUsingSelfVariable;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.tools.codegen.ocl2java.code.IOcl2JavaEnvironment
	 * #popExpectedReturnType()
	 */
	public Type popExpectedReturnType() {
		return this.returnTypeStack.pop();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.tools.codegen.ocl2java.code.IOcl2JavaEnvironment
	 * #pushExpectedReturnType(org.dresdenocl.pivotmodel.Type)
	 */
	public void pushExpectedReturnType(Type type) {
		this.returnTypeStack.push(type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.tools.codegen.ocl2java.code.IOcl2JavaEnvironment
	 * #removeVariableMapping(java.lang.String)
	 */
	public boolean removeVariableMapping(String name) {
		
		boolean result;
		
		Stack<String> stack = this.mappedVariableNames.get(name);
		
		if (stack != null) {
			if (stack.size() == 0) {
				this.mappedVariableNames.remove(name);
				result = false;
			}
			
			else {
				stack.pop();
				
				if (stack.size() > 0)
					this.mappedVariableNames.put(name, stack);
				else
					this.mappedVariableNames.remove(name);
				
				result = true;
			}
		}
		
		else
			result = false;
		
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.ocl2java.code.ICodeGenEnvironment#resetAllCounters
	 * ()
	 */
	public void resetEnvironmentForNextConstraint() {

		this.atPreVarNameCounter = 0;
		this.bodyResultVarCounter = 0;
		this.comparatorVarCounter = 0;
		this.collectionVarCounter = 0;
		this.ifExpResultVarCounter = 0;
		this.indexVarCounter = 0;
		this.iteratorExpIteratorVarCounter = 0;
		this.resultVarCounter = 0;
		this.toVisitVarCounter = 0;
		this.tupleVarCounter = 0;

		this.allInstancesClasses.clear();
		this.oclIsNewClasses.clear();
		this.atPreValues.clear();
		this.calledProperties.clear();

		this.isUsingSelfVariable = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.tools.codegen.ocl2java.code.IOcl2JavaEnvironment
	 * #setIsUsigSelfVariable(boolean)
	 */
	public void setIsUsigSelfVariable(boolean enabled) {
		this.isUsingSelfVariable = enabled;
	}

	/**
	 * <p>
	 * A name for a Variable which stores a atPre value.
	 * </p>
	 * 
	 * @return A name for a Variable which stores a atPre value.
	 */
	protected String getNewAtPreVarName() {

		String result;

		this.atPreVarNameCounter++;

		result = AT_PRE_VAR_NAME;
		result += this.atPreVarNameCounter;

		return result;
	}
}