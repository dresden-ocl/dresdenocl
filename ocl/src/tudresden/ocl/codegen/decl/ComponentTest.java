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

public class ComponentTest {

  public ComponentTest() {
    System.err.println("Test Component running ...\n");
  }

  public static void main(String[] args) {
    executeTableTest();
  }

  private static void executeTableTest() {
    Table t = new Table("Testtable");

    t.addColumn("Attribute1", "Column1", true);
    t.addColumn("Attribute1", "Column2", true);
    t.addColumn("Attribute3", "Column3", false);
    t.addColumn("Attribute4", "Column4", false);
    t.addColumn("Attribute5", "Column5", false);
    t.addColumn("Attribute6", "Column6", false);

    System.err.println(t.toString());

    // case 1:
    //System.err.println(t.getAttributeColumn("Attribute3"));
    //System.err.println(t.getAttributeColumn("Attribute1"));

    // case 2:
    String[] stra = t.getAttributeColumns("Attribute1");
    System.err.println("Columns for Attribute1: ");
    for (int i=0; i<stra.length; i++) {
      System.err.println(stra[i]);
    }

    // case 3:
    System.err.println(t.getTableName());
  }

  public void executeMappedClassTest() {
  }
}