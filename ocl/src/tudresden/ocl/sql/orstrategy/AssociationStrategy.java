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
import java.util.Set;
import ru.novosoft.uml.foundation.core.MAssociation;

public interface AssociationStrategy{

  /**
   * Maps associations to foreign key relationships and
   * association tables if neccesary
   * Structure of classToTables: MClassifier -> List of Table <BR>
   * structure of navigationGuides: String className -> (String opposite association role -> Guide)<BR>
   * structure of associationTables: MAssociation -> Table<BR>
   * structure of classViews: MClassifier -> ObjectView <BR>
   * any new foreign keys will be added to the first Table listed and
   * refer to the primary key to another classes first listed Table
   *
   * @param association the association to be inserted
   * @param classToTables a List of Table for each class mapped.
   * @param associationTables a Map storing associationTables for Associations that need them
   * @param navigationGuides a Map storing for each class name a Map of association roles
   * and guides to their Ends
   * @param classViews a Map storing an ObjectView for every MClassifier
   */
  public void map(MAssociation association, Map classToTables,
    Map associationTables, Map navigationGuides, Map classViews);
}
