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

package tudresden.ocl20.core.lib;

import java.util.*;

/**
 * Describes an OCL tuple type.
 * @author  Stefan Ocke
 */
public class OclTupleType extends OclType {
    
    private Map parts;
    
    private OclTupleType(String names[], OclType types[]){
        super(createName(names,types));
        parts = new HashMap();
        for(int i = 0; i<names.length; i++){
            parts.put(names[i], types[i]);
        }
    }
    
    public static OclTupleType make(String names[], OclType types[]){
        return new OclTupleType(names, types);
    }
    
    private static String createName(String names[], OclType types[]){
        StringBuffer name = new StringBuffer();
        name.append("TupleType(");
        for(int i=0; i<names.length; i++){
            name.append(names[i]);
            name.append(":");
            name.append(types[i].getName());
            if(i<names.length-1){
                name.append(",");
            }
        }
        name.append(")");
        return name.toString();
    }
    
    
    OclType getType(String partname){
        return (OclType) parts.get(partname);
    }
    
    Set getPartNames(){
        return parts.keySet();
    }
    
    boolean isOfType(OclRoot or){
        if(or.isUndefined()){
            return false;  //follows strictly the rules in [OCL 1.6, p. A-26]
        }
        if(!(or instanceof OclTuple)){
            return false;
        }
        else{
            OclTuple ot = (OclTuple) or;
            
            if(ot.getSize() != parts.size()){
                return false;
            }
            
            Iterator it = parts.entrySet().iterator();
            OclType partType;
            String partName;
            OclRoot partValue;
            while(it.hasNext()){
                Map.Entry entry = (Map.Entry) it.next();
                partName = (String) entry.getKey();
                partType = (OclType) entry.getValue();
                
                partValue = ot.getValue(partName);
                if(partValue == null){
                    return false; //no tuple part with this name exists...
                }
                if(!partType.isOfType(partValue)){
                    return false;
                }
            }
            
            return true;
        }
    }
    
    boolean isOfKind(OclRoot or){
        if(or.isUndefined()){
            return true;  //follows strictly the rules in [OCL 1.6, p. A-26]
        }
        if(!(or instanceof OclTuple)){
            return false;
        }
        else{
            OclTuple ot = (OclTuple) or;
            
            Iterator it = parts.entrySet().iterator();
            OclType partType;
            String partName;
            OclRoot partValue;
            while(it.hasNext()){
                Map.Entry entry = (Map.Entry) it.next();
                partName = (String) entry.getKey();
                partType = (OclType) entry.getValue();
                
                partValue = ot.getValue(partName);
                if(partValue == null){
                    return false; //no tuple part with this name exists...
                }
                if(!partType.isOfKind(partValue)){
                    return false;
                }
            }
            
            return true;
        }
    }
}
