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
   * A StrategyCreator is the link between real Strategy objects
   * and the user interface for the choice of strategies. Every
   * choosable strategy needs a StrategyCreator registered in the
   * StrategyManager.
   * @see StrategyManager
   * @author Andrea Kling
   * */
public interface StrategyCreator{
  /**
   * @return the Type of Strategy according to Types defined in ORMappingImpl
   * @see tudresden.ocl.sql.ORMappingImpl
   * */
  public String getStrategyType();
  /**
   * @return a short description of the mapping method
   * */
  public String getStrategyDescription();
  /**
   * @param element the element that shall be mapped with the Strategy
   * @return a component making all necessary input for the creation
   * of the Strategy available (null if no data is needed)
   * */
  public JComponent getStrategyView(MBase element);
  /**
   * @return the Strategy, null if not enough data was provided by the view
   * */
  public Object getStrategy();
}
