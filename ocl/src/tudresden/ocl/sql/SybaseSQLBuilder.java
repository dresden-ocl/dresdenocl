/*
Copyright (C) 2002 Andrea Kling

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
package tudresden.ocl.sql;

import java.util.Hashtable;
import java.util.HashSet;
import java.util.Vector;
import java.io.*;
//import tudresden.ocl.sql.SQLBuilder;

/**
 * implementation of the SQLBuilder for Sybase DBMS
 *
 * @author Andrea Kling
 * */
public class SybaseSQLBuilder implements SQLBuilder{

  private Hashtable datatypes;
  private StringBuffer theCode;
	private int nextAliasPos;
	private int aliasCount;
  private String pks = null;
  private HashSet reserved;
  //private Vector uniques;

  public SybaseSQLBuilder(){
    //uniques = new Vector();
    theCode = new StringBuffer();
    datatypes = new Hashtable();
    datatypes.put(TypeManager.INTEGER, "int");
    datatypes.put(TypeManager.SMALLINT, "smallint");
    datatypes.put(TypeManager.BIT_1, "bit");
    datatypes.put(TypeManager.LONG, "numeric(19)");
    datatypes.put(TypeManager.ID, "numeric(19) IDENTITY");
    datatypes.put(TypeManager.CHAR, "char(1)");
    datatypes.put(TypeManager.VARCHAR, "varchar(255)");
    datatypes.put(TypeManager.FLOAT, "float");
    datatypes.put(TypeManager.DOUBLE, "float(16)");
    datatypes.put(TypeManager.DATE, "datetime");
    datatypes.put(TypeManager.TIMESTAMP, "datetime");
    reserved = new HashSet();
    try{
        BufferedReader r = new BufferedReader(new InputStreamReader(
          new FileInputStream("tudresden/ocl/sql/sybaseReserved.txt")));
        if(r != null)
            try{
              String line="";
              while((line = r.readLine())!=null)
                reserved.add(line.trim().toLowerCase());
            }finally{
                r.close();
            }
    }
    catch (Exception e){
        System.err.println("OracleSQLBuilder: Exception while reading reserved words.");
        e.printStackTrace();
    }
  }

  public void addAlias(String name){
		if (aliasCount > 0) {
      theCode.insert(nextAliasPos, ", " + name);
			nextAliasPos += name.length() + 2;
		} else {
      theCode.insert(nextAliasPos, name);
			nextAliasPos += name.length();
		}
		aliasCount++;
	}


  public void addAnd(){
    theCode.append("\nand ");
	}


  public void addColumn(String name){
    if (aliasCount > 0)
      theCode.append(", " + check(name));
    else
      theCode.append(check(name));
		aliasCount++;
	}

  private String indent(int length){
    StringBuffer in = new StringBuffer(" ");
    for(int i=0; i<length; i++)
      in.append(" ");
    return in.toString();
  }

  private String check(String word){
    String syColName = new String(word);
    if(reserved.contains(word.trim().toLowerCase())){
      syColName = "MY_"+syColName;
      System.err.println("SybaseSQLBuilder reserved word: "+word+
      " changed for: "+syColName);
    }
    return syColName;
  }

  public void addColumn(String colName, String type, boolean pk){
    String col = check(colName);
    theCode.append(col);
    theCode.append(indent(30-col.length()));
    String dtype = (String) datatypes.get(TypeManager.getInstance().getSQLType(type));
    if(!pk && dtype == datatypes.get(TypeManager.ID))
      dtype = (String) datatypes.get(TypeManager.LONG);
    if(dtype == null)
      dtype = type;
    if(! datatypes.contains(dtype))
      System.err.println("SybaseSQLBuilder unknown type: " + dtype);
    theCode.append(" "+dtype);
    if(pk && TypeManager.getInstance().getSQLType(type) != TypeManager.ID){
     //uniques.add(colName);
     theCode.append(" NOT NULL");
    }
    //  theCode.append(" UNIQUE NOT NULL");
    if(pk)
      if(pks == null){
        pks = new String(col);
      }else{
        pks += ", "+col;
      }

  }

  public void addColumn(String colName, boolean optional, String colType){
    addColumn(colName, colType, false);
    if(optional)
      theCode.append(" NULL");
    else
      theCode.append(" NOT NULL");
  }

  public void addColumnSeparator(){
    theCode.append(",\n");
  }

  public void addEquation(String op1, String op2){
    theCode.append(op1 + "=" + op2);
	}

  public void addFKConstraint(String constrName, String tableName,
    String colName, String fkTable, String fkColName){
    theCode.append("alter table " + check(tableName) + " add constraint " + check(constrName) + "\n");
    theCode.append("foreign key ");
    if(colName.startsWith("(")){
      theCode.append(colName + " references " + check(fkTable));
    }else{
      theCode.append("(" + check(colName) + ") references " + check(fkTable));
    }
    if(fkColName.startsWith("(")){
      theCode.append(fkColName + "\n");
    }else{
      theCode.append("(" + check(fkColName) + ")\n");
    }
  }

  public void addFKConstraint(String conName, String tableName, String[] colName, String fkTable,String[] fkColName) {
    if(colName.length != fkColName.length){
      System.err.println("SybaseSQLBuilder "+conName+" foreign key columns do not match.");
      return;
    }
    String colNames = new String("("+check(colName[0]));
    String fkColNames = new String("("+check(fkColName[0]));
    for(int i=1; i<colName.length; i++){
      colNames += ", "+check(colName[i]);
      fkColNames += ", "+check(fkColName[i]);
    }
    addFKConstraint(conName, tableName, colNames+")", fkTable, fkColNames+")");
  }

  public void addTable(String name){
    if (aliasCount > 0)
      theCode.append(", " + check(name));
    else
      theCode.append(check(name));
		aliasCount++;
	}


  public void beginTable(String tableName){
    theCode.append("create table " + check(tableName) + "\n(");
  }


  public void createAssertionReplacement(String triggerName, String tableName,
    String viewName, String errMsg){
    theCode.append("create trigger " + check(triggerName) + "\n");
    theCode.append("on " + check(tableName) + "\n");
    theCode.append("for insert, update, delete as\n");
    theCode.append("begin\n");
    theCode.append("  if (select count(*) from " + check(viewName) +") > 0\n");
    theCode.append("    RAISERROR 20000 " + errMsg + "\n");
    theCode.append("end\n");
  }


  public void createECATriggerTemplate(String triggerName, String tableName,
    String viewName){
    theCode.append("create trigger " + check(triggerName) + "\n");
    theCode.append("on " + check(tableName) + "\n");
    theCode.append("for insert, update, delete as\n");
    theCode.append("begin\n");
    theCode.append("  if (select count(*) from " + check(viewName) +") > 0\n");
    theCode.append("  begin\n");
    theCode.append("    -- todo: add action code here\n");
    theCode.append("  end\n");
    theCode.append("end\n");
  }

  public void createSelect(){
    theCode.append("SELECT ");
		aliasCount = 0;
	}

  public void createFrom(){
    theCode.append(" \nFROM ");
		aliasCount = 0;
	}

  public void createWhere(){
    theCode.append("\nWHERE ");
	}

  public void createUnion(){
    theCode.append("\nUNION\n");
	}

  public void createView(String name, boolean alias){
    theCode.append("CREATE VIEW " + check(name));
		if (alias) {
      theCode.append(" ()");
			nextAliasPos = theCode.length()-1;
			aliasCount = 0;
		}
    theCode.append(" AS\n");
	}

  public void endTable(){
    if(pks != null){
      theCode.append(",\nPRIMARY KEY ("+pks+")");
      pks = null;
    }
    /*if(uniques.size()>0){
      if(uniques.size() ==1)
        theCode.append(",\nUNIQUE ("+uniques.get(0)+")");
      uniques.removeAllElements();
    } */
    theCode.append("\n)\n");
  }

  public void endView(){
    theCode.append("\n");
  }

  public void endStatement(){
    theCode.append("go\n");
  }

  public String getCode(){
    return theCode.toString();
  }

  public void reset(){
    theCode = new StringBuffer();
  }

  public String getDescription(){
    return "Sybase SQL Server";
  }

}
