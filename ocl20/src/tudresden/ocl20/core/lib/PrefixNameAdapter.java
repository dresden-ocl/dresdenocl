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

package tudresden.ocl20.lib;
import java.util.*;

/** A implementation of NameAdapter, which handles prefixes on
 *  association roles.
 *  A special subclass is provided for Argo/UML.
 *
 *  @see NameAdapter
 *  @see ArgoNameAdapter
 *  @author Frank Finger
 */
public class PrefixNameAdapter implements NameAdapter {
  
  private String prefix;
  private int prefix_length;
  
  /**
     Constructs a PrefixNameAdapter with the given prefix.
  */
  public PrefixNameAdapter(String prefix)
  {
    this.prefix=prefix;
    this.prefix_length=prefix.length();
  }

  /** @return an array containing the parameter and >prefix<+the parameter with
   *          first letter capitalized, e.g. "person" and "myPerson" for
   *          "person"
   */
  public String[] getNames(String name) {
    String[] ret=new String[2];
    ret[0]=name;
    ret[1]=prefix+name.substring(0, 1).toUpperCase()+name.substring(1);
    return ret;
  }

  /** @return if the given Strings begins with >prefix<, return an array
   *          containing the String, the String without >prefix< and this
   *          String with first letter in lower case; else, return an array
   *          containing only the given String
   */
  public String[] getPossibleAssociationNames(String n) {
    String[] ret;
    if (n.length()>prefix_length && n.startsWith(prefix)) {
      ret=new String[3];
      ret[0]=n;
      String womy=n.substring(prefix_length);
      ret[1]=womy.substring(0,1).toLowerCase() + womy.substring(1);
      ret[2]=womy;
    } else {
      ret=new String[1];
      ret[0]=n;
    }
    return ret;
  }

  public String toString()
  {
    return getClass().getName()+'('+prefix+')';
  }

}

