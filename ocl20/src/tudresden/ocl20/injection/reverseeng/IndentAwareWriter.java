/*
Copyright (C) 2000  Steffen Zschaler

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
 * IndentAwareWriter.java
 *
 * Created on 11. September 2000, 14:11
 */
package tudresden.ocl20.injection.reverseeng;

import java.io.*;

/** 
  * A writer that is aware of the current indent level. The indent level is defined as the number of 
  * consecutive spaces/tabs since the last \n.
  *
  * @author  sz9 (Steffen Zschaler)
  * @version 0.1
  */
public class IndentAwareWriter extends FilterWriter {

  /**
    * Current indent level.
    */
  private int m_nCurrentIndent = 0;
  
  /**
    * Indent found before last \n.
    */
  private int m_nLastIndent = 0;
  
  /**
    * true if indent should be accumulated (i.e., after \n until the first character different from
    * ' ' and \n)
    */
  private boolean m_fCollectIndent = true;
  
  /** 
    * Creates new IndentAwareWriter 
    */
  public IndentAwareWriter (Writer wSource) {
    super (wSource);
  }
  
  public void write (int c) 
    throws IOException {
    if (c == '\n') {
      resetIndent();
    }
    else if (m_fCollectIndent) {
      if ((c == ' ') ||
          (c == '\t')) {
        m_nCurrentIndent ++;
      }
      else {
        m_fCollectIndent = false;
      }
    }
    
    out.write (c);
  }
  
  public void write (String s, int off, int len) 
    throws IOException {
    for (int i = off; i - off < len && i < s.length(); i++) {
      write (s.charAt (i));
    }
  }
  
  public void write (char[] cbuf, int off, int len) 
    throws IOException {
    for (int i = off; i - off < len && i < cbuf.length; i++) {
      write (cbuf[i]);
    }
  }
  
  private void resetIndent() {
    m_nLastIndent = m_nCurrentIndent;
    m_nCurrentIndent = 0;
    m_fCollectIndent = true;
  }
  
  public int getCurrentIndent() {
    return m_nCurrentIndent;
  }
  
  public int getLastIndent() {
    return m_nLastIndent;
  }
}