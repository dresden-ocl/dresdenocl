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
import ru.novosoft.uml.foundation.core.MClassifier;

/**
 * An Interface for object-relational mapping Strategies as used
 * by ORMappingImpl
 * this Interface is used mapping classifiers to Tables
 *
 * @see tudresden.ocl.sql.ORMappingImpl
 * */
public interface ClassStrategy{

  /**
   * maps the given class to relational tables
   * Structure of classToTables: MClassifier -> List of Table
   * the first table in a class's list is the most important one,
   * the one containing the primary key, other class tables will
   * refer to
   *
   * @param classifier the class(or interface) to map
   * @param classToTables a Map containing all MClassifiers mapped so far to a List of Tables
   * @param classViews a Map containing an (unfinished) ObjectView for
   * all MClassifiers mapped so far
   */
  public void map(MClassifier classifier, Map classToTables, Map classViews);
}


