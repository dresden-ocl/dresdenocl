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

import java.util.*;

/**
 * This class holds information about navigation in relational schemes as kind of
 * steps of select-from-where clauses. One guide is dedicated to describe either
 * navigations over tables or simple attribute access. If a guide is dedicated to
 * describe a navigation or attribute access can be queried by the according methode.
 * If a navigation or attribute access requires more than one table access, each
 * access must be described with a separate Guide object. In that case, MappedClass
 * objects then return an array of Guide objects. Guides must lead from the target
 * table to the table from where the navigation or attribute starts. The start table
 * is called the context table. Since it is possible to start from a table that uses
 * an alias, the alias in use should be stored within the Guide object too.
 *
 * @author Sten Loecher
 * @see tudresden.codegen.decl.MappedClass
 */
public class  Guide {

	private static String EX_NO_ALIAS = "No context alias specified !";

	/** a List containing all the steps */
	private List steps;
	/** a flag that holds information about the interpretation of the class data */
	private boolean isNavigation;
	/** a pointer that points the current step */
	private int pointer;
	/** the alias for the context table, should be null if it is no final guide */
	private String contextAlias;


	/**
	 * Creates a new Guide.
	 * @param navigation true if the guide describes a navigation, false otherwise
	 * @param contextAlias the context alias of the start table
	 */
	public Guide(boolean navigation, String contextAlias) {
		steps = new ArrayList();
		isNavigation = navigation;
		this.contextAlias = contextAlias;
		pointer = -1;
	}

	/**
	 * Creates a new Guide.
	 * @param navigation true if the guide describes a navigation, false otherwise
	 */
	public Guide(boolean navigation) {
		this(navigation, null);
	}

	/**
	 * Add a select-from-where clause. The steps must be added in the order
	 * they are going to be queried.
	 * @param select the select part
	 * @param from the from part
	 * @param where the where part
	 * @exception NullPointerException if one of the parameters is null
	 */
	public void add(String select, String from, String where)
	throws NullPointerException {
		if ((select == null) || (from == null) && (where == null)) {
			throw new NullPointerException();
		} else {
			steps.add(new Step(select, from, where));
		}
	}

	/**
	 * Resets the navigation pointer. To query the first step, next() must be called first.
	 */
	public void reset() {
		pointer = -1;
	}

	/**
	 * Set the navigation pointer to next select-from-where clause.
	 * @exception IllegalStateException if there is no next step
	 */
	public void next()
	throws IllegalStateException {
		if (pointer < (steps.size()-1)) {
			pointer++;
		} else {
			throw new IllegalStateException();
		}
	}

	/**
	 * @return true if there is a next step available, false otherwise
	 */
	public boolean hasMoreSteps() {
		if (pointer < (steps.size()-1)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return the select part of the current step
	 * @exception IllegalStateException if the client failed to call next() after reset() or object creation
	 */
	public String getSelect()
	throws IllegalStateException {
		if (pointer < 0) {
			throw new IllegalStateException();
		} else {
			return (String)((Step)steps.get(pointer)).select;
		}
	}

	/**
	 * @return the from part of the current step
	 * @exception IllegalStateException if the client failed to call next() after reset() or object creation
	 */
	public String getFrom()
	throws IllegalStateException {
		if (pointer < 0) {
			throw new IllegalStateException();
		} else {
			return (String)((Step)steps.get(pointer)).from;
		}
	}

	/**
	 * @return the where part of the current step
	 * @exception IllegalStateException if the client failed to call next() after reset() or object creation
	 */
	public String getWhere()
	throws IllegalStateException {
		if (pointer < 0) {
			throw new IllegalStateException();
		} else {
			return (String)((Step)steps.get(pointer)).where;
		}
	}

	/**
	 * @return true if the guide describes a navigation, false otherwise
	 */
	public boolean isNavigation() {
		return isNavigation;
	}

	/**
	 * @return the number of steps
	 */
	 public int numberOfSteps() {
	 	return steps.size();
	 }

	 /**
	  * @param alias the context table alias
	  */
	 public void setAlias(String contextAlias) {
	 	this.contextAlias = contextAlias;
	 }

	 /**
	  * @return the alias of the context table
	  * @exception NullPointerException if no context alias is specified
	  */
	 public String getAlias()
	 throws NullPointerException {
	 	if (contextAlias == null) {
	 		throw new NullPointerException(EX_NO_ALIAS);
	 	} else {
	 		return contextAlias;
	 	}
	 }

         /**
          * @return a description of this guide
          */
         public String toString() {
                String result = "Guide:\n";
                Step s;

                for (Iterator i=steps.iterator(); i.hasNext(); ) {
                	s = (Step)i.next();
                	result += "select " + (s).select +
                	          " from " + (s).from +
                	          " where " + (s).where +
                	          "\n";
                }

                if (isNavigation) {
                	result += "navigation description";
                } else {
                	result += "attribute access description";
                }

                return result;
         }

        private class Step {
        	Step(String select, String from, String where) {
        		this.select = select;
        		this.from = from;
        		this.where = where;
        	}

        	String select;
        	String from;
        	String where;
	}
}
