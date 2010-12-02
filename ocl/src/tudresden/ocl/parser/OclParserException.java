/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * OCL Compiler                                                      *
 * Copyright (C) 1999, 2000 Frank Finger (frank@finger.org).         *
 * All rights reserved.                                              *
 *                                                                   *
 * This work was done as a diploma project at the Chair for Software *
 * Technology, Dresden University Of Technology, Germany             *
 * (http://www-st.inf.tu-dresden.de).  It is understood that any     *
 * modification not identified as such is not covered by the         *
 * preceding statement.                                              *
 *                                                                   *
 * This work is free software; you can redistribute it and/or        *
 * modify it under the terms of the GNU Library General Public       *
 * License as published by the Free Software Foundation; either      *
 * version 2 of the License, or (at your option) any later version.  *
 *                                                                   *
 * This work is distributed in the hope that it will be useful,      *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of    *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU *
 * Library General Public License for more details.                  *
 *                                                                   *
 * You should have received a copy of the GNU Library General Public *
 * License along with this library; if not, write to the             *
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,      *
 * Boston, MA  02111-1307, USA.                                      *
 *                                                                   *
 * To submit a bug report, send a comment, or get the latest news on *
 * this project and other projects, please visit the web site:       *
 * http://www-st.inf.tu-dresden.de/ (Chair home page) or             *
 * http://www-st.inf.tu-dresden.de/ocl/ (project home page)          *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

// HISTORY
//
// 03/12/2001  [sz9 ]  Added extraction of error position.

package tudresden.ocl.parser;

import java.util.StringTokenizer;

public class OclParserException extends RuntimeException {

  private String m_sDetailMessage;
  private int m_nErrorLine;
  private int m_nErrorCol;
  
  public OclParserException(String s) {
    super();
    
    extractErrorPosition (s);
  }
  
  public String getMessage() {
    return m_sDetailMessage;
  }
  
  public int getErrorLine() {
    return m_nErrorLine;
  }
  
  public int getErrorCol() {
    return m_nErrorCol;
  }
  
  /**
   * Extract error position from detail message, if possible. Assumes SableCC
   * detail message
   * format: "[" <line> "," <col> "]" <error message>
   *
   * <p>Error line and column are stored in {@link #m_nErrorLine} and
   * {@link #m_nErrorCol} so that they can be retrieved using
   * {@link #getErrorLine} and {@link #getErrorCol}. The detail message without
   * the position information is stored in {@link #m_sDetailMessage}</p>
   *
   * @author Steffen Zschaler (sz9)
   * @since  03/12/2001
   */
  private void extractErrorPosition (String sDetailMessage) {
    if (sDetailMessage.charAt (0) == '[') {
      // Positional data seems to be available
      StringTokenizer st = new StringTokenizer (sDetailMessage.substring(1),
                                                   ",]");
      
      try {
        m_nErrorLine = Integer.parseInt (st.nextToken());
        m_nErrorCol  = Integer.parseInt (st.nextToken());
        
        m_sDetailMessage = st.nextToken ("").substring (2); // skip "] "
      }
      catch (NumberFormatException nfe) {
        nfe.printStackTrace();
        // No positional data available
        m_sDetailMessage = sDetailMessage;
        m_nErrorLine = -1;
        m_nErrorCol = -1;
      }
    }
    else {
      // No positional data available
      m_sDetailMessage = sDetailMessage;
      m_nErrorLine = -1;
      m_nErrorCol = -1;
    }
  }
}
