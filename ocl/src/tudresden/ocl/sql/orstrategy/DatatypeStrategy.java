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
import ru.novosoft.uml.foundation.core.MAttribute;

/**
 * An Interface for object-relational mapping Strategies as used
 * by ORMappingImpl
 * this Interface is used mapping datatypes that are unknown to
 * the system, or not defined in the TypeManager
 *
 * @see tudresden.ocl.sql.TypeManager
 * @see tudresden.ocl.sql.ORMappingImpl
 * */
public interface DatatypeStrategy{
 /**
  * allows to have special mappings for certain unknown datatypes
  * recommended for complex or multiple datatypes
  *
  * @param attribute an Attribute having an unusual datatype
  * @param classToTables a Map containing for every MClassifier a List of Table
  * @param classViews contains an ObjectView for every MClassifier
  *
  * @see tudresden.ocl.codegen.decl.ObjectView
  * @see ru.novosoft.uml.foundation.core.MClassifier
  * */
  public void map(MAttribute attribute, Map classToTables, Map classViews);
}
