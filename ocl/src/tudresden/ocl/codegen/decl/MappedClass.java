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
import java.util.Hashtable;
import java.util.Enumeration;

/**
 *  This class represents a mapped class from the object model.
 *  The class should be mapped using an implementation
 *  of ORMappingScheme.
 *  The four basic characteristics of a mapped class are:
 *  - a class can be mapped to one or more tables;
 *  - the mapped class knows about the kind of joins necessary to navigate to association ends;
 *  - an object identifier, necessary to uniquely identify instances of the class,
 *    can be mapped to one or more table columns;
 *  - an attribute can be mapped to one or more columns.
 *  To query all that important information about a mapped class, MappedClass
 *  provides a number of service methodes.
 *
 *  @author Sten Loecher
 */
public class MappedClass {

	/** the name of the class */
	String className;
	/** a Vector containing a number of tables the mapped class is mapped to */
	private Vector tables;
	/** a Vector containing all method names of the class for which isQuery holds */
	private Hashtable queries;
	/** a Hashtable containing references to all MappedClass objects of association ends */
	private Hashtable associationEnds;
	/** a Hashtable containing all guides to association ends */
	private Hashtable guidesToAssociationEnds;
        /** a Hashtable containing references to all MappedClass objects of superclasses */
        private Hashtable superclasses;

	/**
	 *  Creates a new MappedClass object.
	 *  @param name the class name from the object model
	 */
	public MappedClass(String name) {
		className = name;
		tables = new Vector();
		queries = new Hashtable();
		associationEnds = new Hashtable();
		guidesToAssociationEnds = new Hashtable();
                superclasses = new Hashtable();
	}

	/**
	 *  @param table a table the class is mapped to
	 */
	public void addTable(Table table) {
		if (table != null) {
			tables.add(table);
		}
	}

	/**
	 *  @param name the methode for which isQuery holds
	 */
	public void addQuery(String name) {
	 	if (name != null) {
	 		queries.put(name, "");
	 	}
	}

	/**
	 *  @param roleName the role name of the association end or null if it is the class name starting with a lowercase letter
	 *  @param ae the MappedClass object related to the rolename
	 */
	public void addAssociationEnd(String roleName, MappedClass ae) {
	 	if (ae != null) {
	 		if (roleName == null) {
	 			try {
	 				String s = ae.getClassName().toLowerCase();
	 				String role = s.charAt(0) + ae.getClassName().substring(1);
	 				associationEnds.put(role, ae);
	 			} catch (Exception e) {
	 				System.err.println(e.toString());
	 			}
	 		} else {
	 			associationEnds.put(roleName, ae);
	 		}
	 	}
	}

	/**
	 * @param name the name of the association end the join guide leads to
         * @param guide the join guide
	 */
	public void addJoinGuide(String name, Guide guide) {
		if (guide != null) {
			guidesToAssociationEnds.put(name, guide);
		}
	}

        /**
         * @param name the name of the superclass
         * @param the MappedClass object related to the superclass
         */
         public void addSuperclass(String name, MappedClass mc) {
                if ((name != null) && (mc != null)) {
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
         * @return the join guide leading to the specified association end
	 */
	public Guide getJoinGuide(String name) {
		if (name != null) {
                        Guide guide = null;
                        Enumeration e;
                        MappedClass mc;

                        if (!associationEnds.containsKey(name)) {
                          e = superclasses.elements();

                          while (e.hasMoreElements()) {
                            mc = (MappedClass)e.nextElement();
                            if (mc.isAssociationEnd(name)) {
                              guide = mc.getJoinGuide(name);
                            }
                          }
                        } else {
  			  guide = (Guide)guidesToAssociationEnds.get(name);
                        }

			if ((guide == null) && (name.equals(className))) {
				e = tables.elements();
				guide = new Guide(false);

				while (e.hasMoreElements()) {
					Table t = (Table)e.nextElement();
					String[] keys = t.getPrimaryKeyColumns();
					String key = "";

					for (int i=0; i<keys.length; i++) {
						if (!key.equals("")) key += ",";
						key += keys[i];
					}

					guide.add(key, t.getTableName(), "");
				}
			}

			return guide;
		} else {
			return null;
		}
	}

	/**
	 * @param name the name of an attribute of the mapped class
         * @return a Guide to navigate to the specified attribute;
         *         The guide could represent nested subqueries, if a
         *         navigation to other classes or to superclasses is
         *         necessary to get access to the attribute.<br>
         *         Important: Currently there is no support for multiple
         *         inheritance.
	 */
	public Guide getAttributeGuide(String name) {
                Guide g = null;
                Enumeration e;
                Table t;
                String col, where;
                String[] temp;
                MappedClass mc;
                boolean notInherited = false;

                if (name != null) {
                  e = tables.elements();

		  while (e.hasMoreElements()) {
		    t = (Table)e.nextElement();
                    try {
		      t.getAttributeColumn(name);
                      notInherited = true;
                      break;
		    } catch (ORMSException ex) {
                    }
		  }

                  if (notInherited) {
                    // attribute is class member
                    g = new Guide(false);
                    e = tables.elements();

                    while (e.hasMoreElements()) {
				t = (Table)e.nextElement();
				col = t.getAttributeColumn(name);
				temp = t.getPrimaryKeyColumns();
				where = "";

				for (int i=0; i<temp.length; i++) {
					if (i > 0) where += ",";
					where += temp[i];
				}

				if (col != null) {
					g.add(col, t.getTableName(), where);
				}
		    }
                  } else {
                    // attribute is probably inherited
                    e = superclasses.elements();

                    while(e.hasMoreElements()) {
                      mc =  (MappedClass)e.nextElement();
                      g = mc.getAttributeGuide(name);
                      if (g != null) break;
                    }
                  }
                }

		if (g.numberOfSteps() > 0) {
		  return g;
		} else {
		  return null;
		}
	}

	/**
	 * @param name the name of the association end
         * @return the MappedClass object representing the association end
	 */
	public MappedClass getAssociationEnd(String name) {
                if (!associationEnds.containsKey(name)) {
                  Enumeration e = superclasses.elements();
                  MappedClass mc;

                  while (e.hasMoreElements()) {
                    mc = (MappedClass)e.nextElement();
                    if (mc.isAssociationEnd(name)) {
                      return mc.getAssociationEnd(name);
                    }
                  }

                  return null;
                } else {
                  return (MappedClass)associationEnds.get(name);
                }
	}

	/**
	 * @param name a classifier from the class model
         * @return true if the classifier is an attribute name, false otherwise
	 */
	public boolean isAttribute(String name) {
		if (name != null) {
                        Enumeration e;

                        e = tables.elements();

			while (e.hasMoreElements()) {
				Table t = (Table)e.nextElement();
                                try {
				  t.getAttributeColumn(name);
                                  return true;
				} catch (ORMSException ex) {
                                }
			}

                        e = superclasses.elements();

                        while (e.hasMoreElements()) {
                                if (((MappedClass)e.nextElement()).isAttribute(name)) {
                                  return true;
                                }
                        }

			return false;
		} else {
			return false;
		}
	}

	/**
         * @param name a classifier from the class model
	 * @return name true if the classifier is the name of an association end, false otherwise
	 */
	public boolean isAssociationEnd(String name) {
		if (name != null) {
                        if (!associationEnds.containsKey(name)) {
                          Enumeration e = superclasses.elements();

                          while (e.hasMoreElements()) {
                            if (((MappedClass)e.nextElement()).isAssociationEnd(name)) {
                              return true;
                            }
                          }

                          return false;
                        } else {
                          return true;
                        }
        	} else {
			return false;
		}
	}

	/**
	 * @param name a classifier from the class model
	 * @return name true if the classifier is the name of a query methode, false otherwise
	 */
	public boolean isQuery(String name) {
		if (name != null) {
			return queries.containsKey(name);
		} else {
			return false;
		}
	}

	/**
	 * @return an enumeration of all tables the class is mapped to
	 */
	public Enumeration getTables() {
		return tables.elements();
	}

	/**
         * @param name a table name
	 * @return true if the mapped class contains a table with the specified name, false otherwise
	 */
	public boolean hasTable(String name) {
		if (name != null) {
			Enumeration e = tables.elements();

			while(e.hasMoreElements()) {
				if (((Table)e.nextElement()).getTableName().equals(name)) return true;
			}

			return false;
		} else {
			return false;
		}
	}
}