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
 * an interface for object-relational mapping strategies as used by ORMappingImpl
 * a KeyStrategy defines the way the Primary Key of the Tables of a Classifier
 * is chosen
 *
 * @see tudresden.ocl.sql.ORMappingImpl
 * @author Andrea Kling
 * */
public interface KeyStrategy{

  /**
   * Defines the primary key for all tables this classifier was
   * mapped to.
   * Structure of classToTables: MClassifier -> List of Table
   * the first Table listed gets the primary key other class tables
   * refer to
   *
   * @param classifier the class, whose primary key shall be set
   * @param classToTables a List of Table for each class mapped.
   * @param classViews contains an ObjectView for every MClassifier
   *
   * @see tudresden.ocl.codegen.decl.ObjectView
   * @see tudresden.ocl.codegen.decl.Table
   */
  public void map(MClassifier classifier, Map classToTables, Map classViews);
}
