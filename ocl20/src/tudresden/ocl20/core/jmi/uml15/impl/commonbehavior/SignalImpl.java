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

package tudresden.ocl20.jmi.uml15.impl.commonbehavior;

/**
 *
 * @author  Administrator
 */
import tudresden.ocl20.jmi.uml15.core.*;
import tudresden.ocl20.jmi.uml15.commonbehavior.*;

import tudresden.ocl20.jmi.uml15.impl.core.ModelElementImpl;

import org.netbeans.mdr.handlers.InstanceHandler;
import org.netbeans.mdr.storagemodel.StorableObject;

import java.util.*;


public abstract class SignalImpl extends tudresden.ocl20.jmi.uml15.impl.core.ClassifierImpl implements Signal{
    
    /** Creates a new instance of SignalImpl */
    public SignalImpl(StorableObject storable) {
        super(storable);
    }
    
    public boolean hasMatchingSignature(java.util.List paramTypes) {
        //quite fuzzed in the specification...
        //There the  paramTypes  are compared with the Sequence of Attributes
        //of the Signal... but allAttributes() yields a Set, so it is quite
        //unclear which Attributes shoud be the first in the List (the Attributes
        //of the Signal itself or the Attributes  of the superclasses of the  Signal?)
        //On the other hand the WFR for OclMessageExp just does not consider the Attributes
        //of superclasses.
        //The UML (<=1.5) itself underspecifies Signal and Reception. No WFR like
        //"All Attributes of the Signal (and its  superclasses) have to appear as Parameter
        //of the Reception in the following order..." are stated
               
        int pos=0;
        
        
        /*Iterator parentsIt = getGeneralization().iterator();
        while(parentsIt.hasNext()){
            Classifier parent = (Classifier) ((Generalization) parentsIt.next()).getParent();
            allAttributes.addAll(parent.allAttributes());
        }*/
        
        Iterator featuresIt =  getFeature().iterator();
        
        while(featuresIt.hasNext()){
            Feature feature  = (Feature) featuresIt.next();
            if(feature instanceof Attribute){
                if(paramTypes == null || pos>=paramTypes.size()){
                    return false;
                }
                if(!((Classifier)paramTypes.get(pos)).conformsTo(((Attribute)feature).getType())){
                    return false;
                }
                pos++;
            }
        }
        
        if(paramTypes != null && pos<paramTypes.size()){
            return false;
        }
        return true;
    }
    
}
