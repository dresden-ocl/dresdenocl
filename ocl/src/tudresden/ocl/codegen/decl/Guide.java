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

To submit a bug report, send a comment, or get the latest news on
this project, please see the contactReadme.txt file in this package.
*/
package tudresden.ocl.codegen.decl;

import java.util.Vector;

/**
 *  This class holds information about navigation
 *  in relational schemes as kind of steps of
 *  select-from-where clauses.
 *
 *  @author Sten Loecher
 */
public class  Guide {

	/** a Vector of Strings that holds all select statements */
	private Vector select;
	/** a Vector of Strings that holds all from statements */
	private Vector from;
	/** a Vector of Strings that holds all where statements */
	private Vector where;
	/** a flag that holds information about the interpretation of the class data */
	private boolean nested;
	/** a pointer that points the current statement */
	private int pointer;
	/** the alias for the context table, should be null if it is no final guide */
	private String contextAlias;


	/**
	 * Creates a new Guide.
	 * @param nest should be true if all steps should be interpreted as nested subqueries, false otherwise
	 */
	public Guide(boolean nest) {
		select = new Vector();
		from = new Vector();
		where = new Vector();
		nested = nest;
		pointer = 0;
		contextAlias = null;
	}

	/**
	 * Add a select-from-where clause. The steps should be added in the order
	 * they have to be queried.
	 * @param select the select part
	 * @param from the from part
	 * @param where the where part
	 */
	public void add(String select, String from, String where) {
		if ((select != null) && (from != null) && (where != null)) {
			this.select.add(new String(select));
			this.from.add(new String(from));
			this.where.add(new String(where));
		}
	}

	/**
	 * Reset the navigation pointer. To query the first step, next() must be called,
	 * otherwise null pointers will be returned.
	 */
	public void reset() {
		pointer = -1;
	}

	/**
	 * Set the navigation pointer to next select-from-where clause.
	 */
	public void next() {
		if (pointer < (select.size()-1)) {
			pointer++;
		}
	}

	/**
	 * @return true if there is a next step available, false otherwise
	 */
	public boolean hasMoreSteps() {
		if (pointer < (select.size()-1)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return the select part of the current step
	 */
	public String getSelect() {
		if (pointer <0) return null;
		return (String)select.elementAt(pointer);
	}

	/**
	 * @return the from part of the current step
	 */
	public String getFrom() {
		if (pointer <0) return null;
		return (String)from.elementAt(pointer);
	}

	/**
	 * @return the where part of the current step
	 */
	public String getWhere() {
		if (pointer <0) return null;
		return (String)where.elementAt(pointer);
	}

	/**
	 * @return true if all steps should be interpreted as nested subqueries, false otherwise
	 */
	public boolean isNested() {
		return nested;
	}

	/**
	 * @return the number of steps
	 */
	 public int numberOfSteps() {
	 	return select.size();
	 }

	 /**
	  * @param alias the context table alias
	  */
	 public void setAlias(String alias) {
	 	contextAlias = alias;
	 }

	 /**
	  * @return the alias of the context table, or null if no alias is set
	  */
	 public String getAlias() {
	 	return contextAlias;
	 }

         /**
          * @return a description of this guide
          */
         public String toString() {
                String ret = "Guide:\n";
                if (nested) {
                  ret += "should be interpreted as nested \n";
                } else {
                  ret += "should be interpreted as unioned \n";
                }
                if (contextAlias != null) {
                  ret += "context alias is: " + contextAlias + "\n";
                }

                for (int i=0; i<select.size(); i++) {
                  ret += select.elementAt(i) + ", " + from.elementAt(i) + ", " + where.elementAt(i) + "\n";
                }

                return ret;
         }
}
