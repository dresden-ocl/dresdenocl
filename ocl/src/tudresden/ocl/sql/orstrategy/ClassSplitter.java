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
package tudresden.ocl.sql.orstrategy;

import java.util.Map;
import java.util.Vector;
import java.util.List;
import ru.novosoft.uml.foundation.core.*;
import tudresden.ocl.codegen.decl.Table;
import tudresden.ocl.codegen.decl.ObjectView;

 /**
  * An object-relational mapping strategy to Map a modeled class to a number of Tables each table
  * containing some of the class's attributes
  * */
public class ClassSplitter implements ClassStrategy{

  private List parts, names;

  /**
   * @param names a List of String, containing the table names for the different parts
   * @param parts a List of List of MAttribute, representing the different
   * Tables the class shall be mapped to
   * */
  public ClassSplitter(List names, List parts){
    this.parts = parts;
    this.names = names;
  }
  /**
   * maps the given class to more than one relational table
   * Structure of classToTables: MClassifier -> List of Table
   * the first table in a class's list is the most important one,
   * the one containing the primary key, other class tables will
   * refer to <BR>
   * Although the class will be split into a number of Tables the
   * ObjectView will always contain all class attributes
   *
   * @param classifier the class to map
   * @param classToTables a Map containing all MClassifieres mapped so far to a List of Tables
   * @param classViews a Map containing ObjectViews for all MCClassifiers
   */
  public void map(MClassifier classifier, Map classToTables, Map classViews){
    Vector tables = new Vector();
    ObjectView ov = new ObjectView("OV_"+classifier.getName().toUpperCase(),
      classifier.getName());
    for(int i=0; i< parts.size(); i++){
      List l = (List) parts.get(i);
      if(l.size() == 0) continue;
      Table t = new Table((String) names.get(i));
      for (int x=0; x<l.size(); x++) {
        MAttribute m = (MAttribute) l.get(x);
        t.addColumn(m.getName(), m.getType().getName(),
          m.getName().toUpperCase(), false);
        ov.addColumn(m.getName(), m.getName().toUpperCase(), t);
      }
      tables.add(t);
    }
    classToTables.put(classifier, tables);
    classViews.put(classifier, ov);
  }

  /**
   * describes the strategy
   * @ return a short String describing the mapping strategy
   * */
  public String toString(){
    return "splitting class into several tables";
  }
}
