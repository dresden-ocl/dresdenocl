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

import java.util.List;

/**
 * an extension to the StrategyCreator interface to make additional
 * by the model defined datatypes known to StrategyCreators for
 * DatatypeStrategies
 *
 * @see tudresden.ocl.sql.orstrategy.DatatypeStrategy
 * @author Andrea Kling
 * */
public interface DatatypeStrategyCreator extends StrategyCreator{

  /**
   * use this method to make additional datatypes as for instance classes defined
   * in the model known to the Creator
   *
   * @param classNames a List of String with additional valid datatypes
   * */
  public void setAdditionalDatatypes(List classNames);
}
