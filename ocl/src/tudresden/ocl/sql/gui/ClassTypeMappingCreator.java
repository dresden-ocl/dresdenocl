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
import ru.novosoft.uml.foundation.core.MClassifier;
import tudresden.ocl.sql.orstrategy.ClassTypeMapping;
import tudresden.ocl.sql.ORMappingImpl;


/**
 * StrategyCreator for ClassTypeMapping
 *
 * @see tudresden.ocl.sql.orstrategy.ClassTypeMapping
 * @author Andrea Kling
 * */
public class ClassTypeMappingCreator implements DatatypeStrategyCreator{
  private MClassifier type;

 /**
  * @param type the MClassifier that is used as attribute type for
  * ClassTypeMappings created by this creator
  * */
  public ClassTypeMappingCreator(MClassifier type){
    this.type = type;
  }

  /**
   * empty implementation of the DatatypeStrategy interface
   * */
  public void setAdditionalDatatypes(List classNames){}

  /**
   * type of the Strategy created according to types defined in
   * ORMappingImpl
   * @see tudresden.ocl.sql.ORMappingImpl
   * */
  public String getStrategyType(){
    return ORMappingImpl.TYPE;
  }
  /**
   * @return a short description of the mapping method
   * */
  public String getStrategyDescription(){
    return type.getName();
  }
  /**
   * @param element the element that shall be mapped with the Strategy
   * @return null as no data is needed
   * */
  public JComponent getStrategyView(MBase element){
    return null;
  }
  /**
   * @return a ClassTypeMappping for the type provided by the constructor
   * @see tudresden.ocl.sql.orstrategy.ClassTypeMapping
   * */
  public Object getStrategy(){
    return new ClassTypeMapping(type);
  }
}
