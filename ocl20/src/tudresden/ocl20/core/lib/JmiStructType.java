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

package tudresden.ocl20.lib;

import javax.jmi.reflect.*;
import java.util.*;
/**
 * This class describes a JMI StructureType.  Since there is no adequate class for StructureType in JMI Reflection, the structure type is represented
 * by its  name , the package (javax.jmi.reflect.RefPackage) it lives in and by the structure field names and types.
 * @author  Administrator
 */
public class JmiStructType extends JmiType {
    
    RefPackage refPackage;
    JmiOclFactory fact;
    String name;
    
    String [] fieldnames;
    JmiType [] fieldtypes;
    
    /** Creates a new instance of JmiStructType */
    JmiStructType(JmiOclFactory fact, RefPackage rp, String name, String [] fieldnames, JmiType [] fieldtypes) {
        super("Struct");
        this.fact = fact;
        this.refPackage=rp;
        this.name = name;
        this.fieldnames = fieldnames;
        this.fieldtypes = fieldtypes;       
    }
    
    RefStruct createStruct(OclTuple tuple){
        RefStruct result;
        
        List values = new ArrayList();
        
        for(int i=0; i<fieldnames.length; i++){
            OclRoot or = tuple.getValue(fieldnames[i]);
            if(or == null){
                throw new OclException("Field "+fieldnames[i]+" is missing in OclTuple "+tuple);
            }
            values.add(fact.reconvert(fieldtypes[i], or));
        }
        
        result = refPackage.refCreateStruct(name, values);
        return result;
    }
    
}
