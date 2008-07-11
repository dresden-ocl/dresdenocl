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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.pivotmodel.Constraint;

/**
 * Abstract implementation of {@link IModelObject}
 * 
 * @author Ronny Brandt
 * @version 1.0 31.08.2007
 */
public abstract class AbstractModelObject implements IModelObject {

	// The name
	protected String name;

	// The qualified name
	protected List<String> qualifiedName;

	// The qualified name as string
	protected String qualifiedNameString;

	// The results are mapped to constraints
	protected Map<Constraint, OclRoot> results = new HashMap<Constraint, OclRoot>();

	// The OCL object
	protected OclRoot oclObject;

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelObject#getName()
	 */
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelObject#getQualifiedName()
	 */
	public List<String> getQualifiedName() {
		return qualifiedName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelObject#getQualifiedNameString()
	 */
	public String getQualifiedNameString() {
		if (qualifiedNameString == null)
			if (qualifiedName != null) {
				for (String part : qualifiedName) {
					if (qualifiedNameString == null)
						qualifiedNameString = part;
					else
						qualifiedNameString = qualifiedNameString + "::" + part;
				}
			}
		return qualifiedNameString;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelObject#addResult(tudresden.ocl20.pivot.pivotmodel.Constraint,
	 *      tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	public void addResult(Constraint cs, OclRoot result) {
		results.put(cs, result);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelObject#getResults()
	 */
	public Map<Constraint, OclRoot> getResults() {
		return results;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelObject#clearResults()
	 */
	public boolean clearResults() {
		if (results != null) {
			results.clear();
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelObject#removeResult(tudresden.ocl20.pivot.pivotmodel.Constraint)
	 */
	public boolean removeResult(Constraint cs) {
		if (results != null) {
			results.remove(cs);
			return true;
		}
		return false;
	}
}
