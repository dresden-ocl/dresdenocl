/*
 * Guide.java
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

package tudresden.ocl20.pivot.tools.codegen.declarativ.mapping;

import java.util.ArrayList;
import java.util.List;

/**
 * This class holds information about navigation in target models as kind of
 * steps of select-from-where clauses. One guide is dedicated to describe either
 * navigations over association ends or simple attribute access.
 * 
 * @author Florian Heidenreich
 * @see tudresden.ocl20.pivot.tools.codegen.declarativ.mapping.IMappedModel
 */
public class Guide {

	private class Step {

		String from;
		String select;
		String where;

		Step(String select, String from, String where) {

			this.select = select;
			this.from = from;
			this.where = where;
		}
	}

	/**
	 * Alias for the context alias of the MappedClass in the target model, should
	 * be null if not used
	 */
	private String contextAlias;

	/**
	 * Flag that holds information about the interpretation of the class data
	 */
	private boolean isNavigation;

	/**
	 * Pointer that points to the current step
	 */
	private int pointer;

	/**
	 * List containing all the steps of this Guide
	 */
	private List<Step> steps;

	/**
	 * Creates a new Guide.
	 * 
	 * @param navigation
	 *          true if the guide describes a navigation, false otherwise
	 */
	public Guide(boolean navigation) {

		this(navigation, null);
	}

	/**
	 * Creates a new Guide.
	 * 
	 * @param navigation
	 *          true if the guide describes a navigation, false otherwise
	 * @param contextAlias
	 *          the context alias of the MappedClass to start from
	 */
	public Guide(boolean navigation, String contextAlias) {

		steps = new ArrayList<Step>();
		isNavigation = navigation;
		this.contextAlias = contextAlias;
		pointer = -1;
	}

	/**
	 * Add a select-from-where clause. The steps must be added in the order they
	 * are going to be queried.
	 * 
	 * @param select
	 *          the select part
	 * @param from
	 *          the from part
	 * @param where
	 *          the where part
	 * @exception NullPointerException
	 *              if one of the parameters is null
	 */
	public void add(String select, String from, String where)
			throws NullPointerException {

		if ((select == null) || (from == null) && (where == null)) {
			throw new NullPointerException();
		}
		else {
			steps.add(new Step(select, from, where));
		}
	}

	/**
	 * Returns the alias of the context representation in the target model
	 * 
	 * @return the alias of the context representation in the target model
	 * @exception NullPointerException
	 *              if no context alias is specified
	 */
	public String getAlias() throws NullPointerException {

		if (contextAlias == null) {
			throw new NullPointerException("No context alias specified!");
		}
		else {
			return contextAlias;
		}
	}

	/**
	 * Returns the from part of the current step
	 * 
	 * @return the from part of the current step
	 * @exception IllegalStateException
	 *              if the client failed to call next() after reset() or object
	 *              creation
	 */
	public String getFrom() throws IllegalStateException {

		if (pointer < 0) {
			throw new IllegalStateException();
		}
		else {
			return steps.get(pointer).from;
		}
	}

	/**
	 * Returns the select part of the current step
	 * 
	 * @return the select part of the current step
	 * @exception IllegalStateException
	 *              if the client failed to call next() after reset() or object
	 *              creation
	 */
	public String getSelect() throws IllegalStateException {

		if (pointer < 0) {
			throw new IllegalStateException();
		}
		else {
			return steps.get(pointer).select;
		}
	}

	/**
	 * Returns the where part of the current step
	 * 
	 * @return the where part of the current step
	 * @exception IllegalStateException
	 *              if the client failed to call next() after reset() or object
	 *              creation
	 */
	public String getWhere() throws IllegalStateException {

		if (pointer < 0) {
			throw new IllegalStateException();
		}
		else {
			return steps.get(pointer).where;
		}
	}

	/**
	 * Determines whether there is a next step available or not.
	 * 
	 * @return true if there is a next step available, false otherwise
	 */
	public boolean hasNext() {

		if (pointer < (steps.size() - 1)) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Determines whether the guide describes a navigation
	 * 
	 * @return true if the guide describes a navigation, false otherwise
	 */
	public boolean isNavigation() {

		return isNavigation;
	}

	/**
	 * Set the navigation pointer to next select-from-where clause.
	 * 
	 * @exception IllegalStateException
	 *              if there is no next step
	 */
	public void next() throws IllegalStateException {

		if (pointer < (steps.size()/* - 1 */)) {
			pointer++;
		}
		else {
			throw new IllegalStateException();
		}
	}

	/**
	 * Gives the number of steps assigned to the guide
	 * 
	 * @return the number of steps
	 */
	public int numberOfSteps() {

		return steps.size();
	}

	/**
	 * Resets the navigation pointer. To query the first step, next() must be
	 * called first.
	 */
	public void reset() {

		pointer = 0;
	}

	/**
	 * Sets the context alias
	 * 
	 * @param contextAlias
	 *          the context alias
	 */
	public void setAlias(String contextAlias) {

		this.contextAlias = contextAlias;
	}

	/**
	 * Returns a description of this guide
	 * 
	 * @return a description of this guide
	 */
	public String toString() {

		StringBuffer result = new StringBuffer();
		String lineSeperator = System.getProperty("line.separator");

		result.append("Guide:");
		result.append(lineSeperator);

		for (Step s : steps) {
			result.append("select ");
			result.append(s.select);
			result.append(" from ");
			result.append(s.from);
			result.append(" where ");
			result.append(s.where);
			result.append(lineSeperator);
		}

		if (isNavigation) {
			result.append("navigation description");
		}
		else {
			result.append("attribute access description");
		}

		return result.toString();
	}
}
