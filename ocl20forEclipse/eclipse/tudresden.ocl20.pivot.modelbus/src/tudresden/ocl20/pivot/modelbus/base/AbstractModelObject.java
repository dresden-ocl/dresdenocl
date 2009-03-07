/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Ronny Brandt (Ronny_Brandt@web.de).                    *
 * All rights reserved.                                                      *
 *                                                                           *
 * This work was done as a project at the Chair for Software Technology,     *
 * Dresden University Of Technology, Germany (http://st.inf.tu-dresden.de).  *
 * It is understood that any modification not identified as such is not      *
 * covered by the preceding statement.                                       *
 *                                                                           *
 * This work is free software; you can redistribute it and/or modify it      *
 * under the terms of the GNU Library General Public License as published    *
 * by the Free Software Foundation; either version 2 of the License, or      *
 * (at your option) any later version.                                       *
 *                                                                           *
 * This work is distributed in the hope that it will be useful, but WITHOUT  *
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or     *
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Library General Public     *
 * License for more details.                                                 *
 *                                                                           *
 * You should have received a copy of the GNU Library General Public License *
 * along with this library; if not, you can view it online at                *
 * http://www.fsf.org/licensing/licenses/gpl.html.                           *
 *                                                                           *
 * To submit a bug report, send a comment, or get the latest news on this    *
 * project, please visit the website: http://dresden-ocl.sourceforge.net.    *
 * For more information on OCL and related projects visit the OCL Portal:    *
 * http://st.inf.tu-dresden.de/ocl                                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package tudresden.ocl20.pivot.modelbus.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * An abstract implementation of {@link IModelObject}.
 * </p>
 * 
 * @author Ronny Brandt
 */
public abstract class AbstractModelObject implements IModelObject {

	/** The name of this {@link IModelObject}. */
	protected String myName;

	/** The {@link OclRoot} object of this {@link IModelObject}. */
	protected OclRoot myOclObject;

	/** The results are mapped to constraints. */
	protected Map<Constraint, OclRoot> myResults = new HashMap<Constraint, OclRoot>();

	/**
	 * The {@link Type} of the {@link IModel} of which this IModelObject is an
	 * instance.
	 */
	protected Type myType;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelObject#addResult(tudresden.ocl20
	 * .pivot.pivotmodel.Constraint,
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	public void addResult(Constraint aConstraint, OclRoot aResult) {
		this.myResults.put(aConstraint, aResult);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelObject#clearResults()
	 */
	public boolean clearResults() {

		boolean result;

		/* Eventually clear the results map. */
		if (this.myResults != null) {

			this.myResults.clear();

			result = true;
		}

		else {
			result = false;
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelObject#getName()
	 */
	public String getName() {
		return this.myType.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelObject#getQualifiedName()
	 */
	public List<String> getQualifiedName() {

		List<String> result;
		String qualifiedNamePackages[];

		qualifiedNamePackages = this.myType.getQualifiedName().split("::");

		result = new ArrayList<String>();

		for (int index = 0; index < qualifiedNamePackages.length; index++) {
			result.add(qualifiedNamePackages[index]);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelObject#getQualifiedNameString()
	 */
	public String getQualifiedNameString() {
		return this.myType.getQualifiedName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelObject#getResults()
	 */
	public Map<Constraint, OclRoot> getResults() {
		return this.myResults;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelObject#getType()
	 */
	public Type getType() {
		return this.myType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelObject#isInstanceOf(tudresden.ocl20
	 * .pivot.pivotmodel.Type)
	 */
	public boolean isInstanceOf(Type aType) {

		boolean result;

		if (this.myType != null) {
			result = this.myType.conformsTo(aType);
		}

		else {
			result = false;
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelObject#removeResult(tudresden.ocl20
	 * .pivot.pivotmodel.Constraint)
	 */
	public boolean removeResult(Constraint cs) {
		if (myResults != null) {
			myResults.remove(cs);
			return true;
		}
		return false;
	}
}
