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

import java.lang.*;
import java.util.*;

/**
 * This class represents metadata about tables of relational databases.
 * A table consists of:<br>
 * - an arbitrary number of columns;<br>
 * - a primary key consisting of zero ore more columns;<br>
 * - foreign key references consisting of zero or more columns;<br>
 * The columns of the table can be associated to an attribute name that can
 * be different from the real column name. The columns of the table can
 * have a type. Since the metadata is not dependend of a specific database
 * system, no special types are prescribed. It is suggested to follow the
 * java basic data types like int, long, boolean, real and so on and map
 * them to the specific database system types if necessary. The foreign
 * keys are ordinary columns that are associated to additional information.
 * These information are:<br>
 * - the name of a foreign table;(deprecated)<br>
 * modified for reference to foreign table object <BR>
 * - the name of a column in the foreign table.<br>
 * - possibly other columns that are part of the same (multiple) foreign key
 * @author Sten Loecher
 *
 * @version 2.1 (Revised by Andrea Kling 2002, enhanced foreign key references)
 */

//02/2002 [eja] enhanced FK-references
//              added optional-flag for columns
public class Table {

	private static String EX_DUPL_COL = "Column still exists: ";
	private static String EX_COL_NULL = "Columns are not allowed to be null.";
	private static String EX_COL_NE   = "Column does not exist: ";
	private static String EX_FCOL_NE  = "Foreign key column does not exist: ";
	private static String EX_FK_SNOTS = "Foreign key specifikation is not sufficient.";
	private static String EX_TMAC     = "Attribute is mapped to more than one column:";

	private List columns;
	private String name;

	/**
         * Creates a new empty table.
         * @param name the name of the table
         */
	public Table(String name) {
		columns = new ArrayList();
		this.name = name;
	}

	/**
         * Adds a new column to the table.
         * @param colName the name of the column
         * @exception NullPointerException if colName is null
         * @exception IllegalArgumentException if colName still exists
         */
	public void addColumn(String colName)
	throws NullPointerException, IllegalArgumentException {
		addColumn(null, null, colName, false);
	}

	/**
         * Adds a new column to the table.
         * @param attName the name of the attribute associated to the column
         * @param type the type name of the column
         * @param colName the name of the column
         * @exception NullPointerException if colName is null
         * @exception IllegalArgumentException if colName still exists
         */
	public void addColumn(String attName, String type, String colName)
	throws NullPointerException, IllegalArgumentException {
		addColumn(attName, type, colName, false);
	}

        /**
         * Adds a new column to the table.
         * @param attName the name of the attribute associated to the column
         * @param type the type name of the column
         * @param colName the name of the column
         * @param pk a flag that should be true if the column is a primary key column, false otherwise
         * @exception NullPointerException if colName is null
         * @exception IllegalArgumentException if colName still exists
         */
	public void addColumn(String attName, String colName, boolean pk)
	throws NullPointerException, IllegalArgumentException {
		addColumn(attName, null, colName, pk);
	}

	/**
         * Adds a new column to the table.
         * @param attName the name of the attribute associated to the column
         * @param type the type name of the column
         * @param colName the name of the column
         * @param pk a flag that should be true if the column is a primary key column, false otherwise
         * @exception NullPointerException if colName is null
         * @exception IllegalArgumentException if colName still exists
         */
	public void addColumn(String attName, String type, String colName, boolean pk)
	throws NullPointerException, IllegalArgumentException {
		if (colName == null) throw new NullPointerException(EX_COL_NULL);
		if (getColumn(colName) != null) throw new IllegalArgumentException(EX_DUPL_COL + colName);

		columns.add(new Column(attName, type, colName, pk));
	}

	/**
	 * Sets the specified column to primary key column state.
	 * @param colName the column name
	 * @exception NullPointerException if colName is null
         * @exception IllegalArgumentException if colName does not exists
	 */
	public void setPrimaryKey(String colName)
	throws NullPointerException, IllegalArgumentException {
		if (colName == null) throw new NullPointerException(EX_COL_NULL);

		Column pkCol = getColumn(colName);

		if (pkCol == null) {
			throw new IllegalArgumentException(EX_COL_NE + colName);
		} else {
			pkCol.pk = true;
		}
  }

	/**
	 * Sets the specified column to foreign key state.
	 * @param colName the column name
	 * @param fkTable the name of the foreign table
   * @param fkColumn the name of the foreign column
	 * @exception NullPointerException if one of colName, fkTable or fkColumn is null
         * @exception IllegalArgumentException if colName does not exists
   *
   * @deprecated use setForeignKey(String colName, Table fkTable, String fkColumn) instead
	 */
	public void setForeignKey(String colName, String fkTable, String fkColumn)
	throws NullPointerException, IllegalArgumentException {
		if ((colName == null) || (fkTable == null) || (fkColumn == null))
			throw new NullPointerException(EX_FK_SNOTS);

		Column fkCol = getColumn(colName);

		if (fkCol == null) {
			throw new IllegalArgumentException(EX_COL_NE + colName);
		} else {
			fkCol.fk = true;
			fkCol.fkTable = fkTable;
			fkCol.fkColumn = fkColumn;
		}
	}
  /**
	 * Sets the specified column to foreign key state.
	 * @param colName the column name
   * @param fkTable the foreign table
   * @param fkColumn the name of the foreign column
	 * @exception NullPointerException if one of colName, fkTable or fkColumn is null
         * @exception IllegalArgumentException if colName does not exists
	 */
  public void setForeignKey(String colName, Table fkTable, String fkColumn)
	throws NullPointerException, IllegalArgumentException {
		if ((colName == null) || (fkTable == null) || (fkColumn == null))
			throw new NullPointerException(EX_FK_SNOTS);
    if(!fkTable.isColumn(fkColumn))
      throw new IllegalArgumentException(EX_COL_NE + fkColumn);
		Column fkCol = getColumn(colName);

		if (fkCol == null) {
			throw new IllegalArgumentException(EX_COL_NE + colName);
		} else {
      if(! getColumnType(colName).equals(fkTable.getColumnType(fkColumn)))
        throw new IllegalArgumentException("Column types do not match: "+
        getTableName()+": "+colName+", "+fkTable.getTableName()+": "+
        fkColumn);
			fkCol.fk = true;
      fkCol.realFKTable = fkTable;
      fkCol.fkTable = fkTable.getTableName();
			fkCol.fkColumn = fkColumn;
      fkTable.addReferringTable(fkColumn, this, colName);
      String fkType = fkTable.getColumnType(fkColumn).trim().toLowerCase();
      if(fkType.equals("id") || fkType.equals("oid"))
        fkCol.type = "long";
		}
	}

  /**
   * Marks the specified columns as multi column foreign key reference.
   * @param colNames the column names that are part of the foreign key
   * @param fkTable the foreign table
   * @param fkColumns the names of the corresponding foreign columns
	 * @exception NullPointerException if one of colName, fkTable or fkColumn is null
   * @exception IllegalArgumentException if colName does not exists or columns do not match foreign columns
	 */

  public void setForeignKey(String[] colNames, Table fkTable, String[] fkColumns)
	throws NullPointerException, IllegalArgumentException {
    if ((colNames == null) || (fkTable == null) || (fkColumns == null))
			throw new NullPointerException(EX_FK_SNOTS);
    if(colNames.length != fkColumns.length)
      throw new IllegalArgumentException("Keys do not match");

    for(int i=0; i< fkColumns.length; i++){

      if(!fkTable.isColumn(fkColumns[i]))
        throw new IllegalArgumentException(EX_COL_NE + fkColumns[i]);
      Column fkCol = getColumn(colNames[i]);

      if (fkCol == null) {
        throw new IllegalArgumentException(EX_COL_NE + colNames[i]);
      } else {
        fkCol.multiColumnFK = colNames;
        setForeignKey(colNames[i], fkTable, fkColumns[i]);
      }
    }
	}


    /**
     * used to set the reverse table reference for foreign key references
     */
  protected void addReferringTable(String referredColumn, Table referringTable, String referringColumn){
    Column fkCol = getColumn(referredColumn);
    fkCol.referringTables.add(referringTable);
    fkCol.referringColumns.add(referringColumn);
	}

	/**
	 * @return a String array that contains all column names of this table
	 */
	public String[] getColumns() {
		List l = new ArrayList();

		for (Iterator i=columns.iterator(); i.hasNext();) {
			l.add(new String(((Column)i.next()).colName));
		}

		return (String[])l.toArray(new String[l.size()]);
	}

	/**
	 * @param attName the name of an attribute
	 * @return a String that contains the name of the associated column
	 * @exception NullPointerException if attName is null
         * @exception IllegalStateException if the specified attribute is mapped to more than one column
         */
	public String getAttributeColumn(String attName)
	throws NullPointerException, IllegalStateException {
		String ac[] = getAttributeColumns(attName);

		if (ac.length > 1) throw new IllegalStateException(EX_TMAC + attName);
		if (ac.length == 1) {
			return ac[0];
		} else {
			return null;
		}
	}

	/**
	 * @param attName the name of an attribute
	 * @return a String array that contains the names of the associated columns
	 * @exception NullPointerException if attName is null
         */
	public String[] getAttributeColumns(String attName)
	throws NullPointerException {
		if (attName == null) throw new NullPointerException();

		List l = new ArrayList();
		Column c;

		for (Iterator i=columns.iterator(); i.hasNext();) {
			c = (Column)i.next();
			if (c.attName.equals(attName)) {
				l.add(new String(c.colName));
			}
		}

		return (String[])l.toArray(new String[l.size()]);
	}

	/**
	 * @param colName the name of a column
	 * @return a String that contains the name of the associated attribute
	 * @exception NullPointerException if colName is null
         * @exception IllegalArgumentException if colName does not exists
	 */
	public String getColumnAttribute(String colName)
	throws NullPointerException, IllegalArgumentException {
		if (colName == null) throw new NullPointerException();

		Column c = getColumn(colName);

		if (c != null) {
			if (c.attName == null) {
				return null;
			} else {
				return new String(c.attName);
			}
		}

		throw new IllegalArgumentException(EX_COL_NE + colName);
	}

	/**
	 * @return a String array that contains all primary key column names
	 * @deprecated Use methode getPrimaryKeyColumns instead !
	 */
	public String[] getKeyColumns() {
		return getPrimaryKeyColumns();
	}

	/**
	 * @return a String array that contains all primary key column names
	 */
	public String[] getPrimaryKeyColumns() {
		List l = new ArrayList();
		Column c;

		for (Iterator i=columns.iterator(); i.hasNext();) {
			c = (Column)i.next();
			if (c.pk == true) {
				l.add(new String(c.colName));
			}
		}

		return (String[])l.toArray(new String[l.size()]);
	}

	/**
   * @return a String array that contains all columns referring to another Table
	 */
	public String[] getForeignKeyColumns() {
		List l = new ArrayList();
		Column c;

		for (Iterator i=columns.iterator(); i.hasNext();) {
			c = (Column)i.next();
			if (c.fk == true) {
				l.add(new String(c.colName));
			}
		}

		return (String[])l.toArray(new String[l.size()]);
	}

	/**
	 * @param colName the name of a column
	 * @return the name of the column type
	 * @exception NullPointerException if colName is null
         * @exception IllegalArgumentException if colName does not exists
	 */
	public String getColumnType(String colName)
	throws NullPointerException, IllegalArgumentException {
		if (colName == null) throw new NullPointerException(EX_COL_NULL);

		Column c = getColumn(colName);

		if (c == null) {
			throw new IllegalArgumentException(EX_COL_NE + colName);
		} else {
			return new String(c.type);
		}
	}

  public void setColumnType(String colName, String colType)
	throws NullPointerException, IllegalArgumentException {
		if (colName == null) throw new NullPointerException(EX_COL_NULL);

		Column c = getColumn(colName);

		if (c == null) {
			throw new IllegalArgumentException(EX_COL_NE + colName);
		} else {
      c.type = colType;
		}
	}

	/**
	 * @param colName the name of a column
	 * @return the foreign table name of the specified column
	 * @exception NullPointerException if colName is null
         * @exception IllegalArgumentException if colName does not exists
	 */
	public String getForeignTable(String colName)
	throws NullPointerException, IllegalArgumentException {
		if (colName == null) throw new NullPointerException(EX_COL_NULL);

		Column c = getColumn(colName);

		if (c == null) {
			throw new IllegalArgumentException(EX_FCOL_NE + colName);
		} else {
			return new String(c.fkTable);
		}
	}

	/**
	 * @param colName the name of a column
   * @return the foreign table of the specified column
	 * @exception NullPointerException if colName is null
         * @exception IllegalArgumentException if colName does not exists
	 */

  public Table getReferredTable(String colName)
	throws NullPointerException, IllegalArgumentException {
		if (colName == null) throw new NullPointerException(EX_COL_NULL);

		Column c = getColumn(colName);

		if (c == null) {
			throw new IllegalArgumentException(EX_FCOL_NE + colName);
		} else {
      return c.realFKTable;
		}
	}

	/**
	 * @param colName the name of a column
	 * @return the foreign column name of the specified column
	 * @exception NullPointerException if colName is null
         * @exception IllegalArgumentException if colName does not exists
	 */
	public String getForeignColumn(String colName)
	throws NullPointerException, IllegalArgumentException {
		if (colName == null) throw new NullPointerException(EX_COL_NULL);

		Column c = getColumn(colName);

		if (c == null) {
			throw new IllegalArgumentException(EX_FCOL_NE + colName);
		} else {
			return new String(c.fkColumn);
		}
	}

	/**
	 * @param colName the name of a column
   * @return all column names that are part of the same foreign key
   *         as the specified column (including specified column)
	 * @exception NullPointerException if colName is null
         * @exception IllegalArgumentException if colName does not exists
	 */
  public String[] getAllForeignKeyColumns(String colName)
	throws NullPointerException, IllegalArgumentException {
		if (colName == null) throw new NullPointerException(EX_COL_NULL);

		Column c = getColumn(colName);

		if (c == null) {
			throw new IllegalArgumentException(EX_FCOL_NE + colName);
		} else {
      if(c.multiColumnFK != null){
        return c.multiColumnFK;
      }else{
        String[] ret = new String[1];
        ret[0] = c.colName;
        return ret;
      }
		}
	}

 /**
  * @return a String representation of the Foreign key the given column
  *         is part of, (fk1, fk2, ...)
  * */
   public String getForeignKeyRepresentation(String colName)
     throws NullPointerException, IllegalArgumentException {
   if (colName == null) throw new NullPointerException(EX_COL_NULL);

		Column c = getColumn(colName);

		if (c == null) {
			throw new IllegalArgumentException(EX_FCOL_NE + colName);
		} else {
      if(! c.fk){
        throw new IllegalArgumentException("Column is not part of a foreign key: "+colName);
      }else{
        if(c.multiColumnFK == null){
          return "( "+colName+" )";
        }else{
          String str = "( "+c.multiColumnFK[0];
          for(int i=1; i<c.multiColumnFK.length; i++){
            str += ", "+c.multiColumnFK[i];
          }
          return str+" )";
        }
      }
		}
	}

	/**
	 * @return a String representation of all primary key columns,
	 *         which is the column name in case of single column primary key
	 *         or "(pk1, pk2, ..., pkn)" in case of a multi column key
	 */
	public String getPrimaryKeyRepresentation() {
		StringBuffer result = new StringBuffer();
		String pks[] = getPrimaryKeyColumns();

		if (pks.length > 1) result.append("(");
		for (int i=0; i<pks.length; i++) {
			if (i>0) result.append(",");
			result.append(pks[i]);
		}
		if (pks.length > 1) result.append(")");

		return result.toString();
	}

	/**
         * @return the name of the table
         */
	public String getTableName() {
		return new String(name);
	}

	/**
         * @param colName the name of a column
         * @return true if the specified column is a primary key column, false otherwise
         * @exception NullPointerException if colName is null
	 * @deprecated Use methode isPrimaryKeyColumn instead !
	 */
	public boolean isKeyColumn(String colName)
	throws NullPointerException {
		return isPrimaryKeyColumn(colName);
	}

	/**
         * @param colName the name of a column
         * @return true if the specified column is a primary key column, false otherwise
         * @exception NullPointerException if colName is null
         */
	public boolean isPrimaryKeyColumn(String colName)
	throws NullPointerException {
		if (colName == null) throw new NullPointerException(EX_COL_NULL);

		Column c = getColumn(colName);

		if (c == null) {
			return false;
		} else {
			return c.pk;
		}
	}

	/**
         * @param colName the name of a column
         * @return true if the specified column is a foreign key column, false otherwise
         * @exception NullPointerException if colName is null
         */
	public boolean isForeignKeyColumn(String colName)
	throws NullPointerException {
		if (colName == null) throw new NullPointerException(EX_COL_NULL);

		Column c = getColumn(colName);

		if (c == null) {
			return false;
		} else {
			return c.fk;
		}
	}

	/**
         * @param colName the name of a column
         * @return true if the specified column is a column of this table, false otherwise
         * @exception NullPointerException if colName is null
         */
	public boolean isColumn(String colName)
	throws NullPointerException {
		if (colName == null) throw new NullPointerException(EX_COL_NULL);

		Column c = getColumn(colName);

		if (c == null) {
			return false;
		} else {
			return true;
		}
	}

        /**
         * @return a set of attribute names that are associated to the table columns
         */
        public Set attributes() {
            Set result = new HashSet();
            String att;

            for (Iterator i=columns.iterator(); i.hasNext(); ) {
                att = ((Column)i.next()).attName;
                if (att != null )
                  if(!att.trim().equals("")) result.add(att.trim());
            }

            return Collections.unmodifiableSet(result);
        }
  /**
   * sets the Table's name
   */

  public void setName(String name){
    this.name = name;
  }

    /**
     * marks a column as optional column (may contain null values)
     * @exception IlleglArgumentException if column does not exist
     * */
  public void setOptional(String colName) throws IllegalArgumentException {
    Column c = getColumn(colName);
		if (c == null) {
      throw new IllegalArgumentException(EX_COL_NE + colName);
		} else {
      c.optional = true;
    }
  }

    /**
     * @return true, if the column may contain null-values
     * @exception IlleglArgumentException if column does not exist
     * */
  public boolean isOptional(String colName) throws IllegalArgumentException {
    Column c = getColumn(colName);
		if (c == null) {
      throw new IllegalArgumentException(EX_COL_NE + colName);
		} else {
      return c.optional;
    }
  }

  /**
   * @param colName the column to remove
   * @exception IlleglArgumentException if column is Part of the primary key or of a foreign key
   **/
  public void removeColumn(String colName) throws IllegalArgumentException{
    Column c = getColumn(colName);
		if (c == null) {
      return;
		} else {
      if(c.pk)
        throw new IllegalArgumentException(colName +" is part of primary key");
      if(c.fk)
        throw new IllegalArgumentException(colName +" is part of foreign key");
      columns.remove(c);
    }
  }

  /**
   * @exception IlleglArgumentException if primary key is referred to
   **/
  public void removePrimaryKey() throws IllegalArgumentException{
    String[] cols = getPrimaryKeyColumns();
    for(int i = 0; i<cols.length; i++){
      Column c = getColumn(cols[i]);
      if(c.referringTables.size() > 0)
        throw new IllegalArgumentException(cols[i] +" is referred to");
      c.pk = false;
    }
  }



	/**
         * @return a string representation of a table object
         */
        public String toString() {
        	StringBuffer retVal = new StringBuffer();
        	String pk[] = getPrimaryKeyColumns(),
        	       fk[] = getForeignKeyColumns(),
        	       ac[] = getColumns();

        	retVal.append("table (\n");
        	retVal.append("name: " + name + "\n");

        	retVal.append("primary key columns:\n");
        	for (int i=0; i<pk.length; i++) {
        		retVal.append(pk[i]);
        		if (getColumnType(pk[i]) != null) retVal.append(" of type " + getColumnType(pk[i]));
        		if (getColumnAttribute(pk[i]) != null) retVal.append(" associated to " + getColumnAttribute(pk[i]));
        		retVal.append("\n");
        	}

        	retVal.append("foreign key columns:\n");
        	for (int i=0; i<fk.length; i++) {
        		retVal.append(fk[i]);
        		if (getColumnType(fk[i]) != null) retVal.append(" of type " + getColumnType(fk[i]));
        		if (getColumnAttribute(fk[i]) != null) retVal.append(" associated to " + getColumnAttribute(fk[i]));
        		retVal.append("\n");
        	}

        	retVal.append("non key columns:\n");
        	for (int i=0; i<ac.length; i++) {
        		if ((!isPrimaryKeyColumn(ac[i])) &&(!isForeignKeyColumn(ac[i]))) {
        			retVal.append(ac[i]);
        			if (getColumnType(ac[i]) != null) retVal.append(" of type " + getColumnType(ac[i]));
        			if (getColumnAttribute(ac[i]) != null) retVal.append(" associated to " + getColumnAttribute(ac[i]));
        			retVal.append("\n");
        		}
        	}

        	retVal.append(")");

        	return retVal.toString();
        }

        /**
         * @param colName the name of a column
         * @return a column object to the colName or null if such an object does not exist
         */
        private Column getColumn(String colName) {
        	Column c;

        	for (Iterator i=columns.iterator(); i.hasNext();) {
			c = (Column)i.next();
			if (c.colName.equals(colName)) return c;
		}

		return null;
        }

        private class Column {
		Column(String attName, String type, String colName, boolean pk) {
			this.colName = colName;
			this.attName = attName;
			this.type = type;
			this.pk = pk;
      referringTables = new Vector();
      referringColumns = new Vector();
      optional = false;
		}

		String colName;
		String attName;
		String type;
		String fkTable;
		String fkColumn;
    Table  realFKTable;
    List referringTables; //List of Table
    List referringColumns; //List of String, corresponding referring Table
                            // to make (Table, Column) pairs referring (thisTable, thisColumn)
    String[] multiColumnFK; //contains all columns that are part of the same foreign key
		boolean pk;
		boolean fk;
    boolean optional; //true, if column may be null
	}
}
