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

import tudresden.ocl20.core.jmi.mof14.model.*;
import org.netbeans.mdr.handlers.InstanceHandler;
import org.netbeans.mdr.storagemodel.StorableObject;
import java.util.*;
/** MOF1.4-specific implementations for Operations defined in
 * CommonModel::Parameter
 * @author Administrator
 */
public abstract class ParameterImpl extends ModelElementImpl implements Parameter{
    
    /** Creates a new instance of ParameterImpl */
    protected ParameterImpl(StorableObject storable) {
        super(storable);
    }
    
    //Ocl Submission version 1.6, chapter 3.3.8, p 3-23
    //typemapping added. multiplicity of this Parameter is considered
    //(multiplicity greater 1 results in an OCL-CollectionType)
    /** convert this parameter to an attribute. this attribute is used by the type
     * evaluator to build up the
     * tuple type, for the case that the operation has out parameters.
     */    
    public tudresden.ocl20.core.jmi.ocl.commonmodel.Attribute asAttribute() {
        ModelPackage modelPackage = (ModelPackage) this.refOutermostPackage(); 
        
        String name;
        if(getDirection().equals(DirectionKindEnum.RETURN_DIR)){
            name = "result";
        } else {
            name = getNameA();
        }

        tudresden.ocl20.core.jmi.ocl.commonmodel.Classifier type = getTypeA(); //consider multiplicity and ordering! MOF->OclTypemapping!
        
        Attribute a = (Attribute) modelPackage.getAttribute().make(name,type);
        
        return a;
    }
    
    public boolean isUniqueA() {
        return getMultiplicity().isUnique();
    }
    
    public boolean isOrderedA() {
        return getMultiplicity().isOrdered();
    }
    
    public boolean isMultipleA() {
        int upper = getMultiplicity().getUpper();
        return (upper>1 || upper==-1);
    }
    
    /** the OCL-mapped type of the parameter. multiplicity is already taken into
     * account, because parameter multiplicity is not seen as a Common-Model concept
     * but as MOF-specific.
     */    
    public tudresden.ocl20.core.jmi.ocl.commonmodel.Classifier getTypeA() {
        ModelPackage modelPackage = (ModelPackage) this.refOutermostPackage(); 
        tudresden.ocl20.core.jmi.mof14.impl.mof14ocl.types.OclLibraryHelper oclLib = tudresden.ocl20.core.jmi.mof14.impl.mof14ocl.types.OclLibraryHelper.getInstance(modelPackage);
        return oclLib.mapDataTypeToOcl(getType(), getMultiplicity());
    }
    
    private static Map kindMap = new HashMap();
    static {
        kindMap.put(DirectionKindEnum.INOUT_DIR, tudresden.ocl20.core.jmi.ocl.commonmodel.DirectionKindEnum.INOUT);
        kindMap.put(DirectionKindEnum.IN_DIR, tudresden.ocl20.core.jmi.ocl.commonmodel.DirectionKindEnum.IN);
        kindMap.put(DirectionKindEnum.OUT_DIR, tudresden.ocl20.core.jmi.ocl.commonmodel.DirectionKindEnum.OUT);
        kindMap.put(DirectionKindEnum.RETURN_DIR, tudresden.ocl20.core.jmi.ocl.commonmodel.DirectionKindEnum.RETURN);
    }
    /** is the parameter kind in,inout or out? */    
    public tudresden.ocl20.core.jmi.ocl.commonmodel.DirectionKind getKindA() {
        return (tudresden.ocl20.core.jmi.ocl.commonmodel.DirectionKind) kindMap.get(this.getDirection());
    }
    
}

