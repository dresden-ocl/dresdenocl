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

import java.util.Hashtable;
import java.util.Vector;
import java.util.Enumeration;

/**
 *  Objects of this class represent meta information about
 *  database tables. They have a name and an arbitrary
 *  number of named columns that can optionaly be primary key
 *  columns. Additionaly, each column of the table is related
 *  to an attribute name of a class model. That is, each
 *  attribute name of a class model can be mapped to zero or more
 *  columns with names different from the name in the class model.
 *
 *  @author Sten Loecher
 */
public class Table {

        static final String EX_MANYCOLUMNS = "Attribut is mapped to more than one column: ";
        static final String EX_NOCOLUMNS   = "Attribut is mapped to zero columns in this table: ";
        static final String EX_CRLF        = "\n";

	/** the table's name */
	private String tableName;
	/** a Hashtable containing the names of all columns and primary key information */
	private Hashtable columns;
	/** a Hashtable containing the mapping from column names to attribut names */
	private Hashtable columnsToAttributes;

        /**
         * Creates a new empty table.
         * @param name the name of the table
         */
	public Table(String name) {
		columns = new Hashtable();
		columnsToAttributes = new Hashtable();
		tableName = name;
	}

        /**
         * Adds a new column to the table.
         * @param attName the name of the attribute from the class model that is mapped to the column
         * @param colName the name of the column
         * @param key a flag that should be true if the column is a primary key column, false otherwise
         */
	public void addColumn(String attName, String colName, boolean key) {
		if ((attName != null) && (colName != null)) {
			columns.put(new String(colName), new Boolean(key));
			columnsToAttributes.put(new String(colName), new String(attName));
		}
	}

        /**
         * @param name the name of a column
         * @return true if the specified column is a primary key column, false otherwise
         */
        public boolean isKeyColumn(String name) {
		Boolean temp = (Boolean)columns.get(name);

		if (temp != null) {
			return temp.booleanValue();
		} else {
			return false;
		}
	}

        /**
         * @return true if the table contains a column with the specified name, false otherwise
         */
	public boolean isColumn(String name) {
		Object temp = columns.get(name);

		if (temp != null) {
			return true;
		} else {
			return false;
		}
	}

        /**
         * @return a string array that contains the names of all primary key columns
         */
	public String[] getKeyColumns() {
		Enumeration e = columns.keys();
		Vector v = new Vector();
		String s;

		while(e.hasMoreElements()) {
			s = (String)e.nextElement();
			if (((Boolean)columns.get(s)).booleanValue()) {
				v.add(s);
			}
		}

		return (String[])v.toArray(new String[v.size()]);
	}

        /**
         * @return a string array that contains the names of all columns, including the primary key columns
         */
	public String[] getColumns() {
		Enumeration e = columns.keys();
		Vector v = new Vector();

		while(e.hasMoreElements()) {
			v.add((String)e.nextElement());
		}

		return (String[])v.toArray(new String[v.size()]);
	}

        /**
         * @param name the attribute name from the class model
         * @return the name of the column the attribute is mapped to;
         *         In case an attribute is mapped to more than one column,
         *         getAttributeColumns should be used, otherwise an
         *         ORMSException will be thrown. An ORMSException will also
         *         be thrown, if the attribute is not mapped to any column in
         *         the table.
         */
	public String getAttributeColumn(String name) {
                Enumeration e = columnsToAttributes.keys();
                String colName = null;
                String tempCol;
                String tempAtt;

                while (e.hasMoreElements()) {
                  tempCol = (String)e.nextElement();
                  tempAtt = (String)columnsToAttributes.get(tempCol);
                  if (name.equals(tempAtt)) {
                    if (colName == null) {
                      colName = tempCol;
                    } else {
                      throw new ORMSException(EX_MANYCOLUMNS + name + EX_CRLF);
                    }
                  }
                }

                if (colName != null)  {
                  return colName;
                } else {
                  throw new ORMSException(EX_NOCOLUMNS + name + EX_CRLF);
                }
	}

        /**
         * @param name the attribute name from the class model
         * @return a string array containing all columns an attribute is mapped
         *         to in the table; If an attribute is mapped to zero columns,
         *         an ORMSException will be thrown.
         */
         public String[] getAttributeColumns(String name) {
                Enumeration e = columnsToAttributes.keys();
                Vector v = new Vector();
                String tempCol;
                String tempAtt;

                while (e.hasMoreElements()) {
                  tempCol = (String)e.nextElement();
                  tempAtt = (String)columnsToAttributes.get(tempCol);
                  if (name.equals(tempAtt)) {
                    v.add(tempCol);
                  }
                }

                if (v.size() > 0)  {
                  return (String[])v.toArray(new String[v.size()]);
                } else {
                  throw new ORMSException(EX_NOCOLUMNS + name + EX_CRLF);
                }
         }

        /**
         * @return the name of the table
         */
	public String getTableName() {
		return tableName;
	}

        /**
         * @return a string representation of a table object
         */
        public String toString() {
          String ret =  "table: \n";
          String temp;
          Enumeration e = columns.keys();

          while (e.hasMoreElements()) {
            temp = (String)e.nextElement();
            ret += temp + "   (" + (String)columnsToAttributes.get(temp) + ")  ";
            if (((Boolean)columns.get(temp)).booleanValue()) ret += "primary key column";
            ret += "\n";
          }

          ret+= "end of table.";
          return ret;
        }
}