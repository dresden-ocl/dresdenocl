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
package tudresden.ocl.sql.gui;

import javax.swing.JComponent;
import java.util.List;
import ru.novosoft.uml.MBase;
import tudresden.ocl.sql.orstrategy.SimpleTypeMapping;
import tudresden.ocl.sql.ORMappingImpl;

/**
 * A Creator for SimpleTapeMapping-DatatypeStrategies
 *
 * @see tudresden.ocl.sql.orstrategy.SimpleTypeMapping
 * @author Andrea Kling
 * */
public class SimpleTypeMappingCreator implements DatatypeStrategyCreator{
  private String type;

  public SimpleTypeMappingCreator(String type){
    this.type = type;
  }

  public void setAdditionalDatatypes(List classNames){}

  public String getStrategyType(){
    return ORMappingImpl.TYPE;
  }
  /**
   * @return a short description of the mapping method
   * */
  public String getStrategyDescription(){
    return type;
  }
  /**
   * @param element the element that shall be mapped with the Strategy
   * @return a component making all necessary input for the creation
   * of the Strategy available (null if no data is needed)
   * */
  public JComponent getStrategyView(MBase element){
    return null;
  }
  /**
   * @return a tudresden.ocl.sql.orstrategy.SimpleTypeMapping for type
   *
   * @see tudresden.ocl.sql.orstrategy.SimpleTypeMapping
   * */
  public Object getStrategy(){
    return new SimpleTypeMapping(type);
  }
}
