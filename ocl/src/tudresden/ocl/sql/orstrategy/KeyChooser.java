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
import java.util.List;
import java.util.Set;
import ru.novosoft.uml.foundation.core.*;
import tudresden.ocl.codegen.decl.Table;
import tudresden.ocl.codegen.decl.ObjectView;

/**
 * allows the user to choose one or more attributes as primary key
 * these primary key column are added to every Table the classifier
 * is directly mapped to
 *
 * @see tudresden.ocl.codegen.decl.Table
 * @author Andrea Kling
 * */
public class KeyChooser implements KeyStrategy{

  private List pk;
  /**
   * the primary Key may contain several attributes. none of these
   * attributes should have another than a simple datatype as
   * for example defined in TypeManager
   *
   * @param pk a List of MAttribute of all attributes that are to be
   * used as primary key columns
   *
   * @see tudresden.ocl.sql.TypeManager
   * */
  public KeyChooser(List pk){
    this.pk = pk;
  }

  /**
   * Defines the primary key for all tables this classifier was
   * mapped to.
   * Structure of classToTables: MClassifier -> List of Table
   * the first Table listed gets the primary key other class tables
   * refer to
   * the primary key chosen will be used to identify rows in the
   * Classifiers ObjectView
   *
   * @param classifier the class, whose primary key shall be set
   * @param classToTables a List of Table for each class mapped.
   * @param classViews contains an ObjectView for every MClassifier
   *
   * @see tudresden.ocl.codegen.decl.ObjectView
   * @see tudresden.ocl.codegen.decl.Table
   */
  public void map(MClassifier classifier, Map classToTables, Map classViews){
    if(classToTables.get(classifier) == null) return;
    List l = (List) classToTables.get(classifier);
    ObjectView ov = (ObjectView) classViews.get(classifier);
    Table main = null;
    if(l.size() > 0){
      main = (Table) l.get(0);
      Set attributes = main.attributes();
      for(int i=0; i<pk.size();i++){
        MAttribute attribute = (MAttribute) pk.get(i);
        if(!attributes.contains(attribute.getName())){
          main.addColumn(attribute.getName(), attribute.getType().getName(),
            attribute.getName().toUpperCase(), true);
          ov.setPrimaryKey(attribute.getName().toUpperCase(), main);
        }else{
          main.setPrimaryKey(main.getAttributeColumn(attribute.getName()));
          ov.setPrimaryKey(main.getAttributeColumn(attribute.getName()),
            main);
        }

      }
    }
    for(int i=1; i<l.size(); i++){
      Table t = (Table) l.get(i);
      Set attributes = t.attributes();
      String[] keys = new String[pk.size()];
      String[] foreign = new String[pk.size()];
      for(int a=0; a<pk.size(); a++){
        MAttribute attribute = (MAttribute) pk.get(a);
        if(!attributes.contains(attribute.getName())){
          t.addColumn(attribute.getName(), attribute.getType().getName(),
            attribute.getName().toUpperCase(), true);
          keys[a]=attribute.getName().toUpperCase();
          foreign[a] = main.getAttributeColumn(attribute.getName());
        }else{
          t.setPrimaryKey(t.getAttributeColumn(attribute.getName()));
          keys[a]=t.getAttributeColumn(attribute.getName());
          foreign[a] = main.getAttributeColumn(attribute.getName());
        }
      }
      t.setForeignKey(keys, main, foreign);
      for(int a=0; a<keys.length; a++)
        ov.addConnection(keys[a], t, foreign[a], main);
    }
  }

  /**
   * a short descrption of the strategy
   * */
  public String toString(){
    return "using attributes as primary key";
  }
}
