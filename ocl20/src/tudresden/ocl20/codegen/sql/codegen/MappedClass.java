/*
 * MappedClass.java
 * 
 * Copyright (c) 2000 Sten Loecher
 * Slightly modified 2005 by Florian Heidenreich 
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

import tudresden.ocl20.codegen.sql.orm.Table;

/**
 * This class represents a mapped class from the object model.
 * The class should be mapped using an implementation
 * of ORMappingScheme.
 * The four basic characteristics of a mapped class are:<br>
 * <ol>
 * <li>- a class can be mapped to one or more tables;</li>
 * <li>- the mapped class knows about the kind of joins necessary to navigate to association ends;</li>
 * <li>- an object identifier, necessary to uniquely identify instances of the class, can be mapped to one or more table columns;</i>
 * <li>- an attribute can be mapped to one or more columns.</li>
 * </ol>
 * To query all that important information about a mapped class, MappedClass
 * provides a number of service methods.
 *
 * @author Sten Loecher, Florian Heidenreich
 * @see tudresden.ocl20.sql.orm.ORMappingScheme
 * @invariant associationEnds->isUnique(ae:associationEnd|ae.name)
 */
public class MappedClass {

	private static String EX_NAME_NULL = "Class name must not be null !";
	private static String EX_TABLE_NULL = "Null reference for table not allowed !";
	private static String EX_OP_NULL = "Operation name must not be null !";
	private static String EX_MC_NULL = "MappedClass must not be null !";
	private static String EX_PAR_NULL = "All parameters must not be null !";
	private static String EX_NO_GUIDE = "No Guides defined in: ";
	private static String EX_NO_AE = "No such association end: ";
	private static String EX_MC_ND = "No MappedClass object.";

	/** the name of the class */
	String className;

	/** a List of tables this class is mapped to */
	private List<Table> tables;

	/** a Set containing all method names of the class for which isQuery holds */
	private Set<String> queries;

	/** a Set of association ends */
	private Set<AssociationEnd> associationEnds;

	/** a Map of all direct superclass names to the MappedClass objects of the superclasses */
	private Map<String,MappedClass> superclasses;

	/**
	 *  Creates a new MappedClass object.
	 *  
	 *  @param name the class name from the object model
	 *  @exception NullPointerException if the name is null
	 */
	public MappedClass(String name) throws NullPointerException {
		if (name == null) {
			throw new NullPointerException(EX_NAME_NULL);
		} else {
			className = name;
			tables = new ArrayList<Table>();
			queries = new HashSet<String>();
			associationEnds = new HashSet<AssociationEnd>();
			superclasses = new HashMap<String,MappedClass>();
		}
	}

	/**
	 *  Adds a table this class is mapped to
	 *  
	 *  @param table a table the class is mapped to
	 *  @exception NullPointerException if table is null
	 */
	public void addTable(Table table) throws NullPointerException {
		if (table == null) {
			throw new NullPointerException(EX_TABLE_NULL);
		} else {
			tables.add(table);
		}
	}

	/**
	 *  Adds a query for which isQuery returns true
	 *  
	 *  @param name the method for which isQuery returns true
	 *  @exception NullPointerException if the name is null
	 */
	public void addQuery(String name) throws NullPointerException {
		if (name == null) {
			throw new NullPointerException(EX_OP_NULL);
		} else {
			queries.add(name);
		}
	}

	/**
	 * Adds an asscociation end to the MappedClass object. If no role Name for the
	 * association end is provided, the name of the given class is going to be used
	 * with the first letter changed to lower case. If the association end already
	 * exists, it is going to be replaced by the parameter values.
	 * 
	 * @param roleName the role name of the association end or null
	 * @param ae the MappedClass object related to the rolename
	 * @exception NullPointerException if ae is null
	 */
	public void addAssociationEnd(String roleName, MappedClass ae)
			throws NullPointerException {
		if (ae == null) {
			throw new NullPointerException(EX_MC_NULL);
		} else {
			String theRoleName;
			AssociationEnd theAe;

			if (roleName == null) {
				theRoleName = ae.getClassName().toLowerCase().charAt(0)
						+ ae.getClassName().substring(1);
			} else {
				theRoleName = roleName;
			}

			try {
				theAe = getAssEnd(theRoleName);
				theAe.theClass = ae;
			} catch (NoSuchElementException e) {
				associationEnds.add(new AssociationEnd(theRoleName, ae));
			}
		}
	}

	/**
	 * Adds a join guide to the MappedClass object. If the according name of
	 * the association end is not yet existing, a new association end is
	 * going to be added to the MappedClass object.
	 * @param name the name of the association end the join guide leads to
	 * @param guide the join guide
	 * @exception NullPointerException if one of the parameters is null
	 */
	public void addJoinGuide(String name, Guide guide)
			throws NullPointerException {
		if ((name == null) || (guide == null)) {
			throw new NullPointerException(EX_PAR_NULL);
		} else {
			AssociationEnd theAe;

			try {
				theAe = getAssEnd(name);
				theAe.guides.add(guide);
			} catch (NoSuchElementException e) {
				theAe = new AssociationEnd(name);
				theAe.guides.add(guide);
				associationEnds.add(theAe);
			}
		}
	}

	/**
	 * Adds a superclass to the MappedClass object.
	 * 
	 * @param name the name of the superclass
	 * @param mc MappedClass object related to the superclass
	 * @exception NullPointerException if one of the parameters is null
	 */
	public void addSuperclass(String name, MappedClass mc)
			throws NullPointerException {
		if ((name == null) || (mc == null)) {
			throw new NullPointerException(EX_PAR_NULL);
		} else {
			superclasses.put(name, mc);
		}
	}

	/**
	 *  Returns the class name of the mapped class
	 *  
	 *  @return the class name of the mapped class
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * Returns a List with all join guides that lead to the given association end.
	 * If the parameter name equals the class name, join guides to this class will be returned.
	 * 
	 * @param name the name of the association end the join guide is supposed to lead to
	 * @return a List with all join guides that lead to the specified association end;
	 *         if the parameter name equals the class name, join guides to this class will be returned
	 * @exception IllegalArgumentException if name is not the name of an association end
	 */
	public List<Guide> getJoinGuides(String name) throws IllegalArgumentException {
		if (name == null)
			throw new IllegalArgumentException(EX_PAR_NULL);

		AssociationEnd ae;
		List<Guide> scg;
		int count = 0;
		Guide guide;

		// if the given name equals the class name, a join guides to this class will be returned
		if (name.equals(className)) {
			scg = new ArrayList<Guide>();

			for (Table table : tables) {
				guide = new Guide(true);
				guide.add(table.getPrimaryKeyRepresentation(), table
						.getTableName(), "");
				scg.add(guide);
			}

			return Collections.unmodifiableList(scg);
		}

		// note: be aware that association ends of superclasses must be
		//       investigated too, because subclasses inherit the association
		//       ends of it's superclasses
		try {
			ae = getAssEnd(name);
			return Collections.unmodifiableList(ae.guides);
		} catch (NoSuchElementException e) {
			// search in all superclasses
			scg = new ArrayList<Guide>();
			for (MappedClass mc : superclasses.values()) {
				try {
					scg.addAll(mc.getJoinGuides(name));
				} catch (IllegalArgumentException e1) {
					count++;
				}
			}
			if (superclasses.size() == count) {
				throw new IllegalArgumentException(EX_NO_AE + name + " in "
						+ className);
			} else {
				return Collections.unmodifiableList(scg);
			}
		}
	}

	/**
	 * Returns the join guide leading to the specified association end.
	 * 
	 * @param name the name of the association end the join guide is supposed to lead to
	 * @return the join guide leading to the specified association end
	 * @exception IllegalArgumentException if name is not the name of an association end
	 * @exception IllegalStateException if the number of Guide objects to be returned is greater than 1
	 * @exception NullPointerException if the association end exists but no Guide is defined
	 */
	public Guide getJoinGuide(String name) throws IllegalArgumentException,
			IllegalStateException, NullPointerException {
		List<Guide> guides = getJoinGuides(name);

		if (guides.size() > 1)
			throw new IllegalStateException();
		if (guides.size() == 0)
			throw new NullPointerException();

		return guides.get(0);
	}

	/**
	 * Returns a List of Guides to navigate to the specified attribute. If a guide
	 * is in mode isNavigation() == true, a navigation or join is necessary
	 * prior to query the attribute's column.
	 * 
	 * @param name the name of an attribute of the mapped class
	 * @return a List of Guides to navigate to the specified attribute. If a guide
	 *         is in mode isNavigation() == true, a navigation or join is necessary
	 *         prior to query the attribute's column.
	 * @exception IllegalArgumentException if name is not a valid attribute name
	 */
	public List<Guide> getAttributeGuides(String name) throws IllegalArgumentException {
		if ((name == null) || (!isAttribute(name)))
			throw new IllegalArgumentException();

		List<Guide> result = new ArrayList<Guide>();
		Guide guide;
		String cols[];

		// attribute in this class
		for (Table table : tables) {
			cols = table.getAttributeColumns(name);
			if (cols.length > 0) {
				guide = new Guide(false);
				for (int k = 0; k < cols.length; k++) {
					guide.add(cols[k], table.getTableName(), table
							.getPrimaryKeyRepresentation());
				}
				result.add(guide);
			}
		}
		if (result.size() > 0)
			return result;

		// inherited attribute
		for (MappedClass mc : superclasses.values()) {
			result.addAll(mc.getAttributeGuides(name));
		}

		return Collections.unmodifiableList(result);
	}

	/**
	 * Returns a Guide to navigate to the specified attribute.
	 * 
	 * @param name the name of an attribute of the mapped class
	 * @return a Guide to navigate to the specified attribute
	 * @exception IllegalStateException if more than one Guide exists for the attribute
	 * @exception NullPointerException if no attribute guide exists
	 */
	public Guide getAttributeGuide(String name) throws IllegalStateException {
		List<Guide> guides = getAttributeGuides(name);

		if (guides.size() > 1)
			throw new IllegalStateException();
		if (guides.size() == 0)
			throw new NullPointerException();

		return guides.get(0);
	}

	/**
	 * Returns the MappedClass object representing the association end
	 * 
	 * @param name the name of the association end
	 * @return the MappedClass object representing the association end
	 * @exception IllegalArgumentException if name is not a valid association end
	 * @exception NullPointerException if no MappedClass object is defined for the association end
	 * @exception IllegalStateException if more than one MappedClass exists
	 */
	public MappedClass getAssociationEnd(String name)
			throws IllegalArgumentException, NullPointerException,
			IllegalStateException {
		if (name == null)
			throw new IllegalArgumentException(EX_PAR_NULL);

		AssociationEnd ae;
		MappedClass mappedClassAssEnd = new MappedClass("dummy");
		int count = 0;

		try {
			ae = getAssEnd(name);
		} catch (Exception e) {
			// search in all superclasses
			for (MappedClass mc : superclasses.values()) {
				try {
					mappedClassAssEnd = mc.getAssociationEnd(name);
					count++;
				} catch (IllegalArgumentException e1) {
				} catch (NullPointerException e2) {
				}
			}

			if (count == 0)
				throw new NullPointerException(EX_MC_ND);
			if (count > 1)
				throw new IllegalStateException();
			return mappedClassAssEnd;
		}

		if (ae.theClass == null) {
			throw new NullPointerException(EX_MC_ND);
		} else {
			return ae.theClass;
		}
	}

	/**
	 * Determines whether the given classifier is an attribute of this class.
	 * 
	 * @param name a classifier from the class model
	 * @return true if the classifier is an attribute name, false otherwise
	 * @exception NullPointerException if name is null
	 */
	public boolean isAttribute(String name) throws NullPointerException {
		if (name == null)
			throw new NullPointerException(EX_PAR_NULL);

		boolean result = false;

		for (Table t : tables) {
			if (t.getAttributeColumns(name).length > 0) {
				result = true;
				break;
			}
		}

		// search in superclasses
		if (!result) {
			for (MappedClass mc : superclasses.values()) {
				if (mc.isAttribute(name)) {
					result = true;
					break;
				}
			}
		}

		return result;
	}

	/**
	 * Determines whether the given classifier is the name of an association end of this class.
	 * 
	 * @param name a classifier from the class model
	 * @return name true if the classifier is the name of an association end, false otherwise
	 * @exception NullPointerException if the name is null
	 */
	public boolean isAssociationEnd(String name) throws NullPointerException {
		if (name == null) {
			throw new NullPointerException(EX_PAR_NULL);
		} else {
			try {
				getAssEnd(name);
				return true;
			} catch (NoSuchElementException e) {
				// search in superclasses
				boolean isEnd = false;
				for (MappedClass mc : superclasses.values()) {
					if (mc.isAssociationEnd(name)) {
						isEnd = true;
					}
				}
				return isEnd;
			}
		}
	}

	/**
	 * Determines whether the given name is the name of a query method.
	 * 
	 * @param name a classifier from the class model
	 * @return name true if the classifier is the name of a query method, false otherwise
	 * @exception NullPointerException if the name is null
	 */
	public boolean isQuery(String name) throws NullPointerException {
		if (name == null) {
			throw new NullPointerException(EX_PAR_NULL);
		} else {
			if (!queries.contains(name)) {
				for (MappedClass mc : superclasses.values()) {
					if (mc.isQuery(name))
						return true;
				}
				return false;
			} else {
				return true;
			}
		}
	}

	/**
	 * Returns a List with all tables the class is mapped to
	 * 
	 * @return a List with all tables the class is mapped to
	 */
	public List<Table> getTables() {
		return Collections.unmodifiableList(tables);
	}

	/**
	 * Checks, whether the mapped class contains a table with the name specified.
	 * 
	 * @param name a table name
	 * @return true if the mapped class contains a table with the specified name, false otherwise
	 * @exception NullPointerException if the name is null
	 */
	public boolean hasTable(String name) throws NullPointerException {
		if (name == null) {
			throw new NullPointerException(EX_PAR_NULL);
		} else {
			for (Table t : tables) {
				if (t.getTableName().equals(name)) {
					return true;
				}
			}

			return false;
		}
	}

	/**
	 *  Returns all the attribute names of the mapped class 
	 *  
	 *  @return the set of names of the attributes of the mapped class
	 */
	public Set<String> attributes() {
		Set<String> result = new HashSet<String>();

		// search this class
		for (Table t : tables) {
			result.addAll(t.attributes());
		}

		// search in superclasses
		for (MappedClass mc : superclasses.values()) {
			result.addAll(mc.attributes());
		}

		return Collections.unmodifiableSet(result);
	}

	/**
	 *  Returns all the names of the navigatable associationEnds of the mapped class
	 *  
	 *  @return the set of names of navigatable associationEnds of the mapped class
	 */
	public Set<String> associationEnds() {
		Set<String> result = new HashSet<String>();
		
		for (AssociationEnd assEnd : associationEnds) {
			result.add(assEnd.name);
		}

		return Collections.unmodifiableSet(result);
	}

	/**
	 *  Returns all operation names of the mapped class
	 *  
	 *  @return the set of names of operations of the mapped class
	 */
	public Set operations() {
		throw new IllegalStateException(
				"Method MappedClass.operations() not supported yet !");
	}

	/**
	 *  Returns all names of the direct supertypes of the mapped class
	 *  
	 *  @return the set of names of direct supertypes of the mapped class
	 */
	public Set supertypes() {
		Set<String> result = new HashSet<String>();

		for (MappedClass mc : superclasses.values()) {
			result.add(mc.getClassName());
		}

		return Collections.unmodifiableSet(result);
	}

	/**
	 *  Returns all names of all supertypes of the mapped class
	 *  
	 *  @return the set of names of all supertypes of the mapped class
	 */
	public Set<String> allSupertypes() {
		Set<String> result = new HashSet<String>();

		for (MappedClass mc : superclasses.values()) {
			result.add(mc.getClassName());
			result.addAll(mc.allSupertypes());
		}

		return Collections.unmodifiableSet(result);
	}

	// -------------------------------- private stuff ------------------------------------
	/**
	 * Note: This methode does not consider superclasses !
	 * @param the name of the association end
	 * @return the AssociationEnd with the given name
	 * @exception if no association end with the given name exists
	 */
	private AssociationEnd getAssEnd(String name) throws NoSuchElementException {
		for (AssociationEnd ae : associationEnds) {
			if (ae.name.equals(name)) {
				return ae;
			}
		}

		throw new NoSuchElementException();
	}

	private class AssociationEnd {
		public AssociationEnd(String name) {
			this(name, null);
		}

		public AssociationEnd(String name, MappedClass mc) {
			this.name = name;
			guides = new ArrayList<Guide>();
			theClass = mc;
		}

		String name;

		List<Guide> guides;

		MappedClass theClass;
	}

}