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
import java.util.Iterator;
import ru.novosoft.uml.foundation.core.*;
import tudresden.ocl.codegen.decl.Table;
import tudresden.ocl.codegen.decl.ObjectView;

  /**
   * Maps each class to a single table. this should be the default
   * mapping strategy for classes.<BR>
   */
public class SimpleClassToTableMapping implements ClassStrategy{

  private static SimpleClassToTableMapping myInstance;

  private SimpleClassToTableMapping(){
  }

  public static SimpleClassToTableMapping getInstance(){
    if ( myInstance == null ) {
      myInstance = new SimpleClassToTableMapping();
    }
    return myInstance;
  }

  /**
   * Assumes that class never was mapped before. If an entry for
   * classifier is found in the classToTables mapping no mapping
   * will be done.
   *
   * @param classifier the class to be mapped
   * @param classToTables all classes mapped so far. see ClassStrategy for details
   *
   * @see ClassStrategy
   */
  public synchronized void map(MClassifier classifier, Map classToTables, Map classViews){
    //System.out.println(""+classifier+", "+classToTables);
    if (!classToTables.containsKey(classifier)){
      Table t = new Table(classifier.getName().toUpperCase());
      ObjectView ov = new ObjectView("OV_"+classifier.getName().toUpperCase(),
      classifier.getName());
      Iterator it = classifier.getFeatures().iterator();
      while ( it.hasNext() ) {
        MFeature feature = (MFeature) it.next();
        if ( feature instanceof MAttribute ) {
          //System.out.println(classifier.getName()+": "+feature.getName());
          try{
           t.addColumn(feature.getName(), ((MAttribute) feature).getType().getName(),
             feature.getName().toUpperCase());
           ov.addColumn(feature.getName(),
             feature.getName().toUpperCase(), t);
          }catch(IllegalArgumentException e){}
        }
      }
      Vector tables = new Vector();
      tables.add(t);
      classToTables.put(classifier, tables);
      classViews.put(classifier, ov);
    }
  }

  public Object clone(){
    return getInstance();
  }

  public String toString(){
    return "one class to one table";
  }
}
