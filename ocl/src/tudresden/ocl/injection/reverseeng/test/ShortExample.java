/*
Copyright (C) 2000  Ralf Wiebicke

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

/*
 * ShortExample.java
 *
 * Created on 11. September 2000, 13:41
 */

package tudresden.ocl.injection.reverseeng.test;

import java.util.*;

/**
  * Test file for checking save operations in RevengGUI.
  * @author  sz9
  * @version
  */
public class ShortExample extends Object {

  /**
    * This will probably make life a little harder for the tool
    */
  int a;
  
  // This definitely will!
  int b;
  
  /**
    * This makes our life much easier...
    */
  private LinkedList m_llCollectionTester;
  
  private ArrayList m_alCollectionTesterNoComment;
  
  /**
     This comment has no stars in front!
    */
  private Map m_mMapTester;
  
  private HashMap m_hmMapTesterNoComment;
  
  class Inner {
    /**
      * Testing indenting...
      */
    public Set m_sBag;
  }
  
  /** Creates new ShortExample */
  public ShortExample() {
  }
  
}