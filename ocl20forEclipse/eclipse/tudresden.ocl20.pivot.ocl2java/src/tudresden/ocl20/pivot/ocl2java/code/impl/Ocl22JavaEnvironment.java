/*
Copyright (C) 2008-2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the OCL 2 Java Code Generator of Dresden OCL2 for Eclipse.

Dresden OCL2 for Eclipse is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL2 for Eclipse is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>.
 */
package tudresden.ocl20.pivot.ocl2java.code.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import tudresden.ocl20.pivot.ocl2java.code.IOcl22CodeEnvironment;
import tudresden.ocl20.pivot.ocl2java.code.ITransformedCode;
import tudresden.ocl20.pivot.ocl2java.template.ITemplate;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 *<p>
 * This class provides methods to get name for variables and fields during code
 * transformation.
 * </p>
 * 
 * @author Claas Wilke
 */
public class Ocl22JavaEnvironment implements IOcl22CodeEnvironment {

	protected static final String AT_PRE_VAR_NAME = "atPreValue";
	protected int atPreVarNameCounter = 0;

	protected static final String COLLECTION_VAR_NAME = "collection";
	protected int collectionVarCounter = 0;
	protected static final String COMPARATOR_VAR_NAME = "comparator";
	protected int comparatorVarCounter = 0;
	protected static final String INDEX_VAR_NAME = "index";
	protected int indexVarCounter = 0;
	protected static final String IF_EXP_RESULT_VAR_NAME = "ifExpResult";
	protected int ifExpResultVarCounter = 0;
	protected static final String ITERATOR_EXP_ITERATOR_VAR_NAME = "anElement";
	protected int iteratorExpIteratorVarCounter = 0;
	protected static final String RESULT_VAR_NAME = "result";
	protected int resultVarCounter = 0;
	protected static final String TUPLE_VAR_NAME = "tuple";
	protected int tupleVarCounter = 0;
	protected static final String BODY_ASPECT_NAME = "BodyAspect";
	protected int bodyAspectNameCounter = 0;

	protected static final String DEF_ASPECT_NAME = "DefAspect";
	protected int defAspectNameCounter = 0;

	protected static final String DERIVE_ASPECT_NAME = "DeriveAspect";
	protected int deriveAspectNameCounter = 0;

	protected static final String INIT_ASPECT_NAME = "InitAspect";
	protected int initAspectNameCounter = 0;

	protected static final String INV_ASPECT_NAME = "InvAspect";
	protected int invAspectNameCounter = 0;

	protected static final String POST_ASPECT_NAME = "PostAspect";
	protected int postAspectNameCounter = 0;

	protected static final String PRE_ASPECT_NAME = "PreAspect";
	protected int preAspectNameCounter = 0;

	/**
	 * Contains {@link ITemplate}s of transformed Java classes which are used to
	 * extend constrained classes.
	 */
	protected Map<String, ITemplate> classTemplates = new HashMap<String, ITemplate>();

	/**
	 * The canonical names of all classes which use the special OCL operation
	 * allInstances().
	 */
	protected Set<String> allInstancesClasses = new HashSet<String>();

	/**
	 * The canonical names of all classes which use the special OCL operation
	 * oclIsNew().
	 */
	protected Set<String> oclIsNewClasses = new HashSet<String>();

	/**
	 * The {@link ITransformedCode} of Expressions whose atPre value shall be
	 * stored as key, and their transformed type and name of the variable in
	 * which they shall be stored as {@link String} array.
	 */
	protected Map<ITransformedCode, Object[]> atPreValues = new IdentityHashMap<ITransformedCode, Object[]>();

	/**
	 * The call paths of all properties which are called for Constraint
	 * evaluation.
	 */
	protected Set<String> calledProperties = new HashSet<String>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.ocl2java.code.ICodeGenEnvironment#resetAllCounters
	 * ()
	 */
	public void resetEnvironmentForNextConstraint() {

		this.atPreVarNameCounter = 0;
		this.collectionVarCounter = 0;
		this.ifExpResultVarCounter = 0;
		this.indexVarCounter = 0;
		this.iteratorExpIteratorVarCounter = 0;
		this.resultVarCounter = 0;
		this.tupleVarCounter = 0;

		this.allInstancesClasses.clear();
		this.oclIsNewClasses.clear();
		this.atPreValues.clear();
		this.calledProperties.clear();
	}

	/**
	 * @return A name for a Variable which stores a atPre value.
	 */
	protected String getNewAtPreVarName() {

		String result;

		this.atPreVarNameCounter++;

		result = AT_PRE_VAR_NAME;
		result += this.atPreVarNameCounter;

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seetudresden.ocl20.pivot.ocl2java.code.ICodeGenEnvironment#
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
	 * tudresden.ocl20.pivot.ocl2java.code.ICodeGenEnvironment#getNewComparatorName
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
	 * tudresden.ocl20.pivot.ocl2java.code.ICodeGenEnvironment#getNewIndexVarName
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
	 * tudresden.ocl20.pivot.ocl22code.ICodeGenEnvironment#getNewIfExpResultName
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
	 * @seetudresden.ocl20.pivot.ocl22code.ICodeGenEnvironment#
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
	 * tudresden.ocl20.pivot.ocl2java.code.ICodeGenEnvironment#getNewResultVarName
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
	 * 
	 * @seetudresden.ocl20.pivot.ocl2java.code.ICodeTransformationEnvironment#
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
	 * @seetudresden.ocl20.pivot.ocl2java.code.ICodeTransformationEnvironment#
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
	 * @seetudresden.ocl20.pivot.ocl2java.code.ICodeTransformationEnvironment#
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
	 * @seetudresden.ocl20.pivot.ocl2java.code.ICodeTransformationEnvironment#
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
	 * tudresden.ocl20.pivot.ocl2java.code.ICodeGenEnvironment#getNewInitAspectName
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
	 * tudresden.ocl20.pivot.ocl2java.code.IOcl22JavaEnvironment#getNewInvAspectName
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
	 * @seetudresden.ocl20.pivot.ocl2java.code.IOcl22JavaEnvironment#
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
	 * tudresden.ocl20.pivot.ocl2java.code.IOcl22JavaEnvironment#getNewPreAspectName
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
	 * @seetudresden.ocl20.pivot.ocl2java.code.IOcl22JavaEnvironment#
	 * addSuperClassTemplate(java.lang.String,
	 * tudresden.ocl20.pivot.ocl2java.template.ITemplate)
	 */
	public void addSuperClassTemplate(String canonicalName,
			ITemplate aClassTemplate) {
		this.classTemplates.put(canonicalName, aClassTemplate);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.ocl2java.code.IOcl22JavaEnvironment#existsClassTemplate
	 * (java.lang.String)
	 */
	public boolean existsClassTemplate(String canonicalName) {
		return this.classTemplates.containsKey(canonicalName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seetudresden.ocl20.pivot.ocl2java.code.IOcl22JavaEnvironment#
	 * getSuperClassTemplate(java.lang.String)
	 */
	public ITemplate getSuperClassTemplate(String canonicalName) {
		return this.classTemplates.get(canonicalName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seetudresden.ocl20.pivot.ocl2java.code.IOcl22JavaEnvironment#
	 * getSuperClassTemplates()
	 */
	public Map<String, ITemplate> getSuperClassTemplates() {
		return this.classTemplates;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seetudresden.ocl20.pivot.ocl2java.code.IOcl22JavaEnvironment#
	 * addAllInstancesClass(java.lang.String)
	 */
	public void addAllInstancesClass(String canonicalName) {
		this.allInstancesClasses.add(canonicalName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seetudresden.ocl20.pivot.ocl2java.code.IOcl22JavaEnvironment#
	 * hasAllInstancesClasses()
	 */
	public boolean hasAllInstancesClasses() {
		return !this.allInstancesClasses.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seetudresden.ocl20.pivot.ocl2java.code.IOcl22JavaEnvironment#
	 * getAllInstancesClasses()
	 */
	public Set<String> getAllInstancesClasses() {
		return this.allInstancesClasses;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.ocl2java.code.IOcl22JavaEnvironment#addIsNewClass
	 * (java.lang.String)
	 */
	public void addIsNewClass(String canonicalName) {
		this.oclIsNewClasses.add(canonicalName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.ocl2java.code.IOcl22JavaEnvironment#hasIsNewClasses
	 * ()
	 */
	public boolean hasIsNewClasses() {
		return !this.oclIsNewClasses.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.ocl2java.code.IOcl22JavaEnvironment#getIsNewClasses
	 * ()
	 */
	public Set<String> getIsNewClasses() {
		return this.oclIsNewClasses;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.ocl2java.code.IOcl22JavaEnvironment#addAtPreValue
	 * (tudresden.ocl20.pivot.ocl2java.code.ITransformedCode,
	 * tudresden.ocl20.pivot.pivotmodel.Type)
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
	 * tudresden.ocl20.pivot.ocl2java.code.IOcl22JavaEnvironment#hasAtPreValues
	 * ()
	 */
	public boolean hasAtPreValues() {
		return this.atPreValues.size() > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.ocl2java.code.IOcl22JavaEnvironment#getAtPreValues
	 * ()
	 */
	public Map<ITransformedCode, Object[]> getAtPreValues() {
		return this.atPreValues;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.ocl2java.code.IOcl22JavaEnvironment#addCalledProperty
	 * (java.lang.String)
	 */
	public void addCalledProperty(String callPath) {
		this.calledProperties.add(callPath);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.ocl2java.code.IOcl22JavaEnvironment#hasCalledProperties
	 * ()
	 */
	public boolean hasCalledProperties() {
		return this.calledProperties.size() > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.ocl2java.code.IOcl22JavaEnvironment#getCalledProperties
	 * ()
	 */
	public Set<String> getCalledProperties() {
		return this.calledProperties;
	}
}
