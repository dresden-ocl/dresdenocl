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
 * An interface for object-relational Mapping strategies as used by
 * ORMappingImpl
 * an inheritance strategy is used to map inheritance trees to
 * tables
 *
 * @see tudresden.ocl.sql.ORMappingImpl
 * @author Andrea Kling
 * */
public interface InheritanceStrategy{

  /**
   * Maps inheritance structures to tables
   * Structure of classToTables: MClassifier -> List of Table
   * the first Table listed contains the basic primary key
   *
   * @param root the root class or interface of the inheritance tree
   * @param classToTables a List of Table for each class mapped.
   * @param classViews contains an ObjectView for every MClassifier
   *
   * @see tudresden.ocl.codegen.decl.ObjectView
   */
  public void map(MClassifier root, Map classToTables, Map classViews);
}
