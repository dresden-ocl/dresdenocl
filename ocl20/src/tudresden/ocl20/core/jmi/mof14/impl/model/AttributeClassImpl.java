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

package tudresden.ocl20.core.jmi.mof14.impl.model;

import org.netbeans.mdr.handlers.*;
import org.netbeans.mdr.storagemodel.*;

import tudresden.ocl20.core.jmi.mof14.model.*;

/**
 *
 * @author  Administrator
 */
public abstract class AttributeClassImpl extends ClassProxyHandler implements AttributeClass{
    
    /** Creates a new instance of AttributeClassImpl */
    protected AttributeClassImpl(StorableClass storable) {
        super(storable);
    }
    
    public tudresden.ocl20.core.jmi.ocl.commonmodel.Attribute make(String name, tudresden.ocl20.core.jmi.ocl.commonmodel.Classifier type) {
        ModelPackage modelPackage = (ModelPackage) this.refOutermostPackage();
        
        Attribute a = this.createAttribute();

        a.setNameA(name);
        a.setType((MofClass)type);
        
        MultiplicityType m = modelPackage.createMultiplicityType(1,1,false,false);
        
        a.setMultiplicity(m);
        a.setScope(ScopeKindEnum.INSTANCE_LEVEL);

        a.setVisibility(VisibilityKindEnum.PUBLIC_VIS);  //we want to  access it! dont make  it private!     
        return a;
    }
    
}
