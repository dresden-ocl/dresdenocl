/*
Copyright (C) 2008-2010 by Claas Wilke (claaswilke@gmx.net)

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
package tudresden.ocl20.pivot.tools.codegen.ocl2java.internal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.tools.codegen.code.ITransformedCode;
import tudresden.ocl20.pivot.tools.codegen.code.impl.TransformedCodeImpl;
import tudresden.ocl20.pivot.tools.codegen.ocl2java.IOcl2JavaSettings;
import tudresden.ocl20.pivot.tools.template.ITemplateGroup;

/**
 * <p>
 * This class implements the interface {@link IOcl22CodeSettings}.
 * </p>
 * 
 * @author Claas Wilke
 */
public class Ocl2JavaSettings implements IOcl2JavaSettings {

	/**
	 * Specifies whether or not the transformed Code shall be saved in files.
	 */
	protected boolean saveCode;

	/** The location where the transformed Code shall be saved. */
	protected String sourceDirectory;

	/** The sub folder into which the aspect files shall be generated. */
	protected String constraintFolder;

	/**
	 * Specifies whether or not getter methods shall be generated for new defined
	 * attributes.
	 */
	protected boolean createGettersForDefinedAttributes;

	/**
	 * Specifies whether or not inheritance is disabled by default for all
	 * {@link Constraint}s.
	 */
	protected boolean defaultIsInheritanceDisabled;

	/** Contains all {@link Constraint}s which a special inheritance settings. */
	protected Set<Constraint> disabledInheritance;

	/**
	 * Contains the default invariant check mode for all invariants without a
	 * special setting.
	 */
	protected int defaultInvariantCheckMode;

	/**
	 * Contains the invariant check mode of all invariants with a special setting.
	 */
	protected Map<Constraint, Integer> invariantCheckMode;

	/**
	 * Contains the default violation macro for all {@link Constraint}s without a
	 * special setting.
	 */
	protected ITransformedCode defaultViolationMacro;

	/**
	 * Contains the violation macro for all {@link Constraint}s with a special
	 * setting.
	 */
	protected Map<Constraint, ITransformedCode> violationMacros;

	/**
	 * <p>
	 * Creates a new {@link Ocl2JavaSettings}.
	 * </p>
	 */
	public Ocl2JavaSettings() {

		this.sourceDirectory = "";

		this.constraintFolder = "constraints";

		this.saveCode = false;

		this.createGettersForDefinedAttributes = false;

		this.defaultIsInheritanceDisabled = true;

		this.disabledInheritance = new HashSet<Constraint>();

		this.defaultInvariantCheckMode =
				IOcl2JavaSettings.INVARIANT_CHECK_AFTER_CONSTRUCT_AND_ATTRIBUTE_CHANGE;

		this.invariantCheckMode = new HashMap<Constraint, Integer>();

		this.defaultViolationMacro = new TransformedCodeImpl();
		this.defaultViolationMacro
				.addCode("// TODO Auto-generated code executed when constraint is violated.");
		this.defaultViolationMacro
				.addCode("throw new RuntimeException(\"Error: Constraint was violated.\");");

		this.violationMacros = new HashMap<Constraint, ITransformedCode>();
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.ocl2java.IOcl2CodeSettings#getSourceDirectory()
	 */
	public String getSourceDirectory() {

		return this.sourceDirectory;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.ocl2java.IOcl2CodeSettings#setSourceDirectory(java
	 * .lang.String)
	 */
	public void setSourceDirectory(String path) {

		this.sourceDirectory = path;

		if (!this.sourceDirectory.endsWith("/")) {
			this.sourceDirectory += "/";
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.ocl2java.IOcl2CodeSettings#getConstraintDirectory()
	 */
	public String getConstraintDirectory() {

		return this.constraintFolder;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.ocl2java.IOcl2CodeSettings#setConstraintDirectory
	 * (java.lang.String)
	 */
	public void setConstraintDirectory(String folderName) {

		this.constraintFolder = folderName;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.ocl2java.IOcl2CodeSettings#isSaveCode()
	 */
	public boolean isSaveCode() {

		return this.saveCode;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.ocl2java.IOcl2CodeSettings#setSaveCode(boolean)
	 */
	public void setSaveCode(boolean saveCode) {

		this.saveCode = saveCode;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.ocl2java.IOcl2CodeSettings#getViolationMacro(tudresden
	 * .ocl20.pivot.pivotmodel.Constraint)
	 */
	public ITransformedCode getViolationMacro(Constraint constraint) {

		ITransformedCode result;

		if (constraint != null && this.violationMacros.containsKey(constraint)) {
			result = this.violationMacros.get(constraint);
		}

		else {
			result = this.defaultViolationMacro;
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.ocl2java.IOcl2CodeSettings#setDefaultViolationMacro
	 * (tudresden.ocl20.pivot.ocl2java.code.ITransformedCode)
	 */
	public void setDefaultViolationMacro(ITransformedCode macro) {

		this.defaultViolationMacro = macro;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.ocl2java.IOcl2CodeSettings#setViolationMacro(tudresden
	 * .ocl20.pivot.ocl2java.code.ITransformedCode,
	 * tudresden.ocl20.pivot.pivotmodel.Constraint)
	 */
	public void setViolationMacro(ITransformedCode macro, Constraint constraint) {

		this.violationMacros.put(constraint, macro);
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.ocl2java.IOcl2CodeSettings#
	 * isGettersForDefinedAttributesEnabled()
	 */
	public boolean isGettersForDefinedAttributesEnabled() {

		return this.createGettersForDefinedAttributes;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.ocl2java.IOcl2CodeSettings#
	 * setGettersForDefinedAttributesEnabled(boolean)
	 */
	public void setGettersForDefinedAttributesEnabled(boolean enable) {

		this.createGettersForDefinedAttributes = enable;
	}

	public void setDefaultInheritanceDisabled(boolean disable) {

		this.defaultIsInheritanceDisabled = disable;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.ocl2java.IOcl2CodeSettings#disableInheritance(tudresden
	 * .ocl20.pivot.pivotmodel.Constraint)
	 */
	public void setInheritanceDisabled(Constraint aConstraint, boolean disable) {

		if (disable) {
			this.disabledInheritance.add(aConstraint);
		}

		else if (this.disabledInheritance.contains(aConstraint)) {
			this.disabledInheritance.remove(aConstraint);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.ocl2java.IOcl2CodeSettings#isInheritanceDisabled
	 * (tudresden.ocl20.pivot.pivotmodel.Constraint)
	 */
	public boolean isInheritanceDisabled(Constraint aConstraint) {

		boolean result;

		if (aConstraint != null && this.disabledInheritance.contains(aConstraint)) {
			result = true;
		}

		else {
			result = this.defaultIsInheritanceDisabled;
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.ocl2java.IOcl2CodeSettings#setDefaultInvariantCheckMode
	 * (int)
	 */
	public void setDefaultInvariantCheckMode(int mode) {

		this.defaultInvariantCheckMode = mode;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.ocl2java.IOcl2CodeSettings#setInvariantCheckMode
	 * (tudresden.ocl20.pivot.pivotmodel.Constraint, int)
	 */
	public void setInvariantCheckMode(Constraint aConstraint, int mode) {

		this.invariantCheckMode.put(aConstraint, mode);
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.ocl2java.IOcl2CodeSettings#getInvariantCheckMode
	 * (tudresden.ocl20.pivot.pivotmodel.Constraint)
	 */
	public int getInvariantCheckMode(Constraint aConstraint) {

		int result;

		if (this.invariantCheckMode.containsKey(aConstraint)) {
			result = this.invariantCheckMode.get(aConstraint);
		}

		else {
			result = this.defaultInvariantCheckMode;
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.tools.codegen.IOcl2CodeSettings#getTemplateGroup()
	 */
	public ITemplateGroup getTemplateGroup() {

		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.tools.codegen.IOcl2CodeSettings#setTemplateGroup
	 * (tudresden.ocl20.pivot.tools.template.ITemplateGroup)
	 */
	public void setTemplateGroup(ITemplateGroup templateGroup) {

		// TODO Auto-generated method stub
	}
}