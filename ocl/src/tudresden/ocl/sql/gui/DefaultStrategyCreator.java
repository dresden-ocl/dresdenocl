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
import ru.novosoft.uml.MBase;

/**
 * A simple StrategyCreator, that can be used for strategies not
 * needing an explicit view.
 *
 * @see tudresden.ocl.sql.orstrategy.ClassStrategy
 * @see tudresden.ocl.sql.orstrategy.KeyStrategy
 * @see tudresden.ocl.sql.orstrategy.DatatypeStrategy
 * @see tudresden.ocl.sql.orstrategy.InheritanceStrategy
 * @see tudresden.ocl.sql.orstrategy.OrderedStrategy
 * @see tudresden.ocl.sql.orstrategy.AssociationStrategy
 * @author Andrea Kling
 * */
public class DefaultStrategyCreator implements StrategyCreator{
  private String desc,type;
  private Object strategy;

  public DefaultStrategyCreator(){}

  /**
   * @return the Type of Strategy according to Types defined in ORMappingImpl
   * @see tudresden.ocl.sql.ORMappingImpl
   * */
  public String getStrategyType(){
    return type;
  }

  /**
   * @return a short description of the mapping method
   * */
  public String getStrategyDescription(){
    return desc;
  }

  /**
   * @param element the element that shall be mapped with the Strategy, may be null
   * @return null as no data is needed
   * */
  public JComponent getStrategyView(MBase element){
    return null;
  }

  /**
   * @return the Strategy set
   * */
  public Object getStrategy(){
    return strategy;
  }

  /**
   * @param type the Type of Strategy according to Types defined in ORMappingImpl
   * @see tudresden.ocl.sql.ORMappingImpl
   * */
  public void setStrategyType(String type){
    this.type = type;
  }

  /**
   * @param desc a short description of the mapping method
   * */
  public void setStrategyDescription(String desc){
    this.desc = desc;
  }

  /**
   * @param strategy the strategy to be returned
   * */
  public void setStrategy(Object strategy){
    this.strategy = strategy;
  }
}

