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
 * provides a number of service methodes.
 *
 * @author Sten Loecher
 * @see tudresden.ocl.codegen.decl.ORMappingScheme
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
	private List tables;
	/** a Set containing all method names of the class for which isQuery holds */
	private Set queries;
	/** a Map of association end names to  AssociationEnd objects */
	private Set associationEnds;
        /** a Map of all direct superclass names to the MappedClass objects of the superclasses */
        private Map superclasses;

	/**
	 *  Creates a new MappedClass object.
	 *  @param name the class name from the object model
	 *  @exception NullPointerException if the name is null
	 */
	public MappedClass(String name)
	throws NullPointerException {
		if (name == null) {
			throw new NullPointerException(EX_NAME_NULL);
		} else {
			className = name;
			tables = new ArrayList();
			queries = new HashSet();
			associationEnds = new HashSet();
			superclasses = new HashMap();
		}
	}

	/**
	 *  @param table a table the class is mapped to
	 *  @exception NullPointerException if table is null
	 */
	public void addTable(Table table)
	throws NullPointerException {
		if (table == null) {
			throw new NullPointerException(EX_TABLE_NULL);
		} else {
			tables.add(table);
		}
	}

	/**
	 *  @param name the methode for which isQuery holds
	 *  @exception NullPointerException if the name is null
	 */
	public void addQuery(String name)
	throws NullPointerException {
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
	 			theRoleName = ae.getClassName().toLowerCase().charAt(0) + ae.getClassName().substring(1);
	 		} else {
	 			theRoleName = roleName;
	 		}

	 		try {
	 			theAe = getAssEnd(theRoleName);
	 			theAe.theClass = ae;
	 		} catch(NoSuchElementException e) {
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
	 		} catch(NoSuchElementException e) {
	 			theAe = new AssociationEnd(name);
	 			theAe.guides.add(guide);
	 			associationEnds.add(theAe);
	 		}
		}
	}

        /**
         * @param name the name of the superclass
         * @param the MappedClass object related to the superclass
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
	 *  @return the class name of the mapped class
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param name the name of the association end the join guide is supposed to lead to
         * @return a List with all join guides that lead to the specified association end
         * @exception IllegalArgumentException if name is not the name of an association end
	 */
	public List getJoinGuides(String name)
	throws IllegalArgumentException {
		if (name == null) throw new IllegalArgumentException(EX_PAR_NULL);

		// note: be aware that association ends of superclasses must be
		//       investigated too, because subclasses inherit the association
		//       ends of it's superclasses
		AssociationEnd ae;
		List scg;
		int count = 0;

		try {
			ae = getAssEnd(name);
			return Collections.unmodifiableList(ae.guides);
		} catch(NoSuchElementException e) {
			// search in all superclasses
			scg = new ArrayList();
			for (Iterator i=superclasses.values().iterator(); i.hasNext(); ) {
				try {
					scg.addAll(((MappedClass)i.next()).getJoinGuides(name));
				} catch(IllegalArgumentException e1) {
					count++;
				}
			}
			if (superclasses.size() == count) {
				throw new IllegalArgumentException(EX_NO_AE + name + " in " + className);
			} else {
				return Collections.unmodifiableList(scg);
			}
		}
	}

	/**
	 * @param name the name of the association end the join guide is supposed to lead to
         * @return the join guide leading to the specified association end
         * @exception IllegalArgumentException if name is not the name of an association end
         * @exception IllegalStateException if the number of Guide objects to be returned is greater than 1
         * @exception NullPointerException if the association end exists but no Guide is defined
	 */
	public Guide getJoinGuide(String name)
	throws IllegalArgumentException, IllegalStateException, NullPointerException {
		List guides = getJoinGuides(name);

		if (guides.size() > 1) throw new IllegalStateException();
		if (guides.size() == 0) throw new NullPointerException();

		return (Guide)guides.get(0);
	}

	/**
	 * @param name the name of an attribute of the mapped class
         * @return a List of Guides to navigate to the specified attribute. If a guide
         *         is in mode isNavigation() == true, a navigation or join is necessary
         *         prior to query the attribute's column.
         * @exception IllegalArgumentException if name is not a valid attribute name
	 */
	public List getAttributeGuides(String name)
	throws IllegalArgumentException {
		if ((name == null) || (!isAttribute(name))) throw new IllegalArgumentException();

		List result = new ArrayList();
		Table table;
		Guide guide;
		String cols[];

		// attribute in this class
		for (Iterator i=tables.iterator(); i.hasNext(); ) {
			table = (Table)i.next();
			cols = table.getAttributeColumns(name);
			if (cols.length > 0) {
				guide = new Guide(false);
				for (int k=0; k<cols.length; k++) {
					guide.add(cols[k], table.getTableName(), table.getPrimaryKeyRepresentation());
				}
				result.add(guide);
			}
		}
		if (result.size() > 0) return result;

		// inherited attribute
		for (Iterator i=superclasses.values().iterator(); i.hasNext(); ) {
			result.addAll(((MappedClass)i.next()).getAttributeGuides(name));
		}

		return Collections.unmodifiableList(result);
	}

	/**
	 * @param name the name of an attribute of the mapped class
         * @return a Guide to navigate to the specified attribute
         * @exception IllegalStateException if more than one Guide exists for the attribute
         * @exception NullPointerException if no attribute guide exists
	 */
	public Guide getAttributeGuide(String name)
	throws IllegalStateException {
              	List guides = getAttributeGuides(name);

		if (guides.size() > 1) throw new IllegalStateException();
		if (guides.size() == 0) throw new NullPointerException();

		return (Guide)guides.get(0);
	}

	/**
	 * @param name the name of the association end
         * @return the MappedClass object representing the association end
         * @exception IllegalArgumentException if name is not a valid association end
         * @exception NullPointerException if no MappedClass object is defined for the association end
         * @exception IllegalStateException if more than one MappedClass exists
	 */
	public MappedClass getAssociationEnd(String name)
	throws IllegalArgumentException, NullPointerException, IllegalStateException {
		if (name == null) throw new IllegalArgumentException(EX_PAR_NULL);

		AssociationEnd ae;
		MappedClass mc = new MappedClass("dummy");
		int count = 0;

		try {
			ae = getAssEnd(name);
		} catch(Exception e) {
			// search in all superclasses
			for (Iterator i=superclasses.values().iterator(); i.hasNext(); ) {
				try {
					mc = ((MappedClass)i.next()).getAssociationEnd(name);
					count++;
				} catch(IllegalArgumentException e1) {
				} catch(NullPointerException e2) {
				}
			}

			if (count == 0) throw new NullPointerException(EX_MC_ND);
			if (count > 1) 	throw new IllegalStateException();
			return mc;
		}

		if (ae.theClass == null) {
			throw new NullPointerException(EX_MC_ND);
		} else {
			return ae.theClass;
		}
	}

	/**
	 * @param name a classifier from the class model
         * @return true if the classifier is an attribute name, false otherwise
         * @exception NullPointerException if name is null
	 */
	public boolean isAttribute(String name)
	throws NullPointerException {
		if (name == null) throw new NullPointerException(EX_PAR_NULL);

		boolean result = false;

		for (Iterator i=tables.iterator(); i.hasNext(); ) {
			if (((Table)i.next()).getAttributeColumns(name).length > 0) {
				result = true;
				break;
			}
		}

		// search in superclasses
		if (!result) {
			for (Iterator i=superclasses.values().iterator(); i.hasNext(); ) {
				if (((MappedClass)i.next()).isAttribute(name)) {
					result = true;
					break;
				}
			}
		}

		return result;
	}

	/**
         * @param name a classifier from the class model
	 * @return name true if the classifier is the name of an association end, false otherwise
	 * @exception NullPointerException if the name is null
	 */
	public boolean isAssociationEnd(String name)
	throws NullPointerException {
		if (name == null) {
			throw new NullPointerException(EX_PAR_NULL);
		} else {
			try {
				getAssEnd(name);
				return true;
			} catch(NoSuchElementException e) {
				// search in superclasses
				boolean isEnd = false;
				for (Iterator i=superclasses.values().iterator(); i.hasNext(); ) {
					if (((MappedClass)i.next()).isAssociationEnd(name)) {
						isEnd = true;
					}
				}
				return isEnd;
			}
                }
	}

	/**
	 * @param name a classifier from the class model
	 * @return name true if the classifier is the name of a query methode, false otherwise
	 * @exception NullPointerException if the name is null
	 */
	public boolean isQuery(String name)
	throws NullPointerException {
		if (name == null) {
			throw new NullPointerException(EX_PAR_NULL);
		} else {
			if (!queries.contains(name)) {
				MappedClass mc;
				for (Iterator i=superclasses.values().iterator(); i.hasNext(); ) {
					mc = (MappedClass)i.next();
					if (mc.isQuery(name)) return true;
				}
				return false;
			} else {
				return true;
			}
		}
	}

	/**
	 * @return a List with all tables the class is mapped to
	 */
	public List getTables() {
		return Collections.unmodifiableList(tables);
	}

	/**
         * @param name a table name
	 * @return true if the mapped class contains a table with the specified name, false otherwise
	 * @exception NullPointerException if the name is null
	 */
	public boolean hasTable(String name)
	throws NullPointerException {
		if (name == null) {
			throw new NullPointerException(EX_PAR_NULL);
		} else {
			for (Iterator i=tables.iterator(); i.hasNext(); ) {
				if (((Table)i.next()).getTableName().equals(name)) {
					return true;
				}
			}

			return false;
		}
	}

	// -------------------------------- private stuff ------------------------------------
	/**
	 * Note: This methode does not consider superclasses !
	 * @param the name of the association end
	 * @return the AssociationEnd with the given name
	 * @exception if no association end with the given name exists
	 */
	private AssociationEnd getAssEnd(String name)
	throws NoSuchElementException {
		for (Iterator i=associationEnds.iterator(); i.hasNext();) {
			AssociationEnd ae = (AssociationEnd)i.next();
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
			guides = new ArrayList();
			theClass = mc;
		}

		String name;
		List guides;
		MappedClass theClass;
	}

}