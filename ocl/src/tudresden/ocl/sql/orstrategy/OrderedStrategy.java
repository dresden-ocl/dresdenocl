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
import ru.novosoft.uml.foundation.core.MAssociationEnd;


/**
 * an interface for object-relational mapping strategies as used by
 * ORMappingImpl.
 * OrderedStrategies are used to map ordering of AssociationEnds to
 * relational databases
 * @author Andrea Kling
 * */
public interface OrderedStrategy{

  /**
   * Inserts colums into Tables to retain order of ordered
   * associations if neccesary.
   *
   * @param end an ordered or sorted association end
   * @param classToTables contains a List of Table for every MClassifier
   * @param classViews contains an ObjectView for every MClassifier
   *
   * @see tudresden.ocl.codegen.decl.ObjectView
   * @see tudresden.ocl.codegen.decl.Table
   * */
  public void map(MAssociationEnd end, Map classToTables, Map associationTables, Map classViews);
}
