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

package tudresden.ocl20.jmi.uml15.impl.core;

import tudresden.ocl20.jmi.uml15.uml15.*;
import tudresden.ocl20.jmi.uml15.core.*;
import tudresden.ocl20.jmi.uml15.datatypes.*;
import org.netbeans.mdr.handlers.*;
import org.netbeans.mdr.storagemodel.*;

import java.util.*;
/**
 *
 * @author  Administrator
 */
public abstract class ParameterImpl extends ModelElementImpl implements Parameter{
    
    /** Creates a new instance of ParameterImpl */
    protected ParameterImpl(StorableObject storable) {
        super(storable);
    }
    
    //Ocl Submission version 1.6, chapter 3.3.8, p 3-23
    public tudresden.ocl20.jmi.ocl.commonmodel.Attribute asAttribute() {
        Uml15Package umlPackage = (Uml15Package) this.refOutermostPackage(); 
        
        
        String name;
        if(getKind().equals(ParameterDirectionKindEnum.PDK_RETURN)){
            name = "result";
        } else {
            name = getName();
        }
        tudresden.ocl20.jmi.ocl.commonmodel.Classifier type = this.getTypeA(); 
        
        Attribute a = (Attribute) umlPackage.getCore().getAttribute().make(name,type);
        
        return a;
    }
    
    //UML-Parameters can't be  multivalued
    public boolean isMultipleA() {
        return false;
    }
    
    public boolean isOrderedA() {
        return false;  //or true... nevermind
    }
    
    public boolean isUniqueA() {
        return false;  //or true... nevermind
    }
    
    public tudresden.ocl20.jmi.ocl.commonmodel.Classifier getTypeA() {
        return this.getType().toOclType();
    }
    
    private static Map kindMap = new HashMap();
    static {
        kindMap.put(ParameterDirectionKindEnum.PDK_INOUT, tudresden.ocl20.jmi.ocl.commonmodel.DirectionKindEnum.INOUT);
        kindMap.put(ParameterDirectionKindEnum.PDK_IN, tudresden.ocl20.jmi.ocl.commonmodel.DirectionKindEnum.IN);
        kindMap.put(ParameterDirectionKindEnum.PDK_OUT, tudresden.ocl20.jmi.ocl.commonmodel.DirectionKindEnum.OUT);
        kindMap.put(ParameterDirectionKindEnum.PDK_RETURN, tudresden.ocl20.jmi.ocl.commonmodel.DirectionKindEnum.RETURN);
    }
    public tudresden.ocl20.jmi.ocl.commonmodel.DirectionKind getKindA() {
        return (tudresden.ocl20.jmi.ocl.commonmodel.DirectionKind) kindMap.get(this.getKind());
    }
    
}
