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

package tudresden.ocl20.jmi.mof14.impl.model;

import tudresden.ocl20.jmi.mof14.model.*;
import org.netbeans.mdr.handlers.InstanceHandler;
import org.netbeans.mdr.storagemodel.StorableObject;
import java.util.*;
/** MOF1.4-specific implementations for Operations defined in
 * CommonModel::AssociationEnd
 * @author Stefan Ocke
 */
public abstract class AssociationEndImpl extends ModelElementImpl implements AssociationEnd{
    
    List qualifiers; //always empty
    /** Creates a new instance of AssociationEndImpl */
    protected AssociationEndImpl(StorableObject storable) {
        super(storable);
    }
    
    /** yields true, if upper multiplicity is greater 1 */    
    public boolean hasMultiGreaterOne() {
       return isMultipleA();
    }  
    
    /** always true for associations */    
     public boolean isUniqueA() {
        return getMultiplicity().isUnique();
    }
    
     /** yields true, if the association end is ordered */     
    public boolean isOrderedA() {
        return getMultiplicity().isOrdered();
    }
    
    /** yields true, if upper multiplicity is greater 1 */    
    public boolean isMultipleA() {
        int upper = getMultiplicity().getUpper();
        return (upper>1 || upper==-1);
    }
    
    public tudresden.ocl20.jmi.ocl.commonmodel.Classifier getTypeA() {
        return this.getType().toOclType();
    }
    
    /** always true for MOF */    
    public boolean isBinaryA() {
        return true;
    }

    /** get the qualifiers for this association end */    
    public java.util.List getQualifiersA() {
        if(qualifiers == null){
            qualifiers = new ArrayList();
        }
        return qualifiers;
    }
    
}

