/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * OCL 2 Compiler                                                    *
 * Copyright (C) 2002, 2003 Stefan Ocke (stefan.ocke@gmx.de).        *
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

package tudresden.ocl20.util;
import java.util.*;

/**
 * Provides a method for making names unique within a collection of names. It does so by appending numbers.
 * @author  Stefan Ocke
 */
public class Naming {
    
    /** Creates a new instance of Naming */
    public Naming() {
    }
    
    /** if name exists in names, it is made unique by appending some suffix*/
    public static String makeUniqueName(String name, Collection names){
        if(!names.contains(name)){
            return name;
        }
        int index = name.lastIndexOf('_');
        if(index<0){
            return makeUniqueName(name+"_1",names);
        }
        try{
            
            Integer number = Integer.valueOf(name.substring(index+1));
            number = new Integer(number.intValue()+1);
            return makeUniqueName(name.substring(0,index)+"_"+number.toString(),names);
            
        } catch (NumberFormatException nfe){
            return makeUniqueName(name+"_1",names);
        }
        
    }
}
