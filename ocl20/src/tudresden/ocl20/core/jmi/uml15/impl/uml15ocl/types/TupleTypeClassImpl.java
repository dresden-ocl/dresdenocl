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

package tudresden.ocl20.jmi.uml15.impl.uml15ocl.types;

import org.netbeans.mdr.handlers.*;
import org.netbeans.mdr.storagemodel.*;

import tudresden.ocl20.jmi.uml15.uml15ocl.types.*;
import tudresden.ocl20.jmi.uml15.core.*;
import tudresden.ocl20.jmi.uml15.datatypes.*;

import java.util.*;
/**
 *
 * @author  Administrator
 */
public abstract class TupleTypeClassImpl extends ClassProxyHandler implements TupleTypeClass {
    
    private Map lookup = null;
    
    /** Creates a new instance of TupleTypeClassImpl */
    protected TupleTypeClassImpl(StorableClass storable) {
        super(storable);
    }
    
    //Ocl Submission version 1.6, chapter 3.3.9, p3-25
    //If there  is  already  a TupleType with the given attribute names and types,
    //that one is returned instead of creating a new one.
    
    public tudresden.ocl20.jmi.ocl.types.TupleType make(java.util.List atts) {
              
        //Ocl Submission version 1.6, chapter 3.2.2, p3-7
        StringBuffer name = new StringBuffer("Tuple(");
        for(int i=0; i<atts.size(); i++){
            if(i>0){name.append(',');}
            Attribute a = (Attribute) atts.get(i);
            name.append(a.getName());
            name.append(':');
            name.append(a.getType().getName());
        }
        name.append(')');
        
        TupleType tt;
        
        tt=lookup(name.toString()); 
        
        if(tt == null){
            System.out.println("Create new TupleType: "+name);
            tt = createTupleType();
            tt.setName(name.toString());
            tt.getFeature().addAll(atts);
            OclLibraryHelper.getInstance(this).addClassifier(tt);
        }
                       
        return tt;
    }
    
    private TupleType lookup(String name){
        if (lookup==null){
            lookup=new HashMap();
        }
        Iterator it = refAllOfClass().iterator();
        while(it.hasNext()){
            TupleType tt = (TupleType) it.next();
            lookup.put(tt.getName(),tt);
        }
        return (TupleType) lookup.get(name);
    }
}
