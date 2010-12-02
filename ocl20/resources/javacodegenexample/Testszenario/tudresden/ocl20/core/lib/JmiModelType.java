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
import javax.jmi.reflect.*;

/**
 * The JMI implementation of an OCL model type description. Basically a wrapper around the reflective class javax.jmi.reflect.RefClass.
 * @author  Stefan Ocke.
 */
public class JmiModelType extends OclModelType{
    
    private RefClass refClass;
    /** Creates a new instance of JmiModelType */
    JmiModelType(String name, RefClass rc, OclFactory factory) {
        super(name, factory);
        refClass = rc;
    }
    
    /**A dummy model type that can be used, if the type mapping in JmiOclFactory shall not check the exact model type.*/  
    public static final JmiModelType ANY = new JmiModelType("ANY", null, null); 
    
    
    public RefClass getRefClass(){
        return refClass;
    }
    
    Object getFeatureImpl(String name) {
        try{
            return refClass.refGetValue(name);
        } catch (Exception e){
            throw new OclException("getFeature("+name+") for "+this.getName()+" failed : "+e.getMessage());
        }
    }
    
    Object getFeatureImpl(String name, Object[] parameters) {
        try{
            return refClass.refInvokeOperation(name, Arrays.asList(parameters));
        } catch (Exception e){
            throw new OclException("getFeature("+name+",...) for "+this.getName()+" failed : "+e.getMessage());
        }
    }
    
    public boolean equals(Object o) {
        if(!(o instanceof JmiModelType)){
            return false;
        }
        else{
            return ((JmiModelType)o).refClass.equals(refClass);
        }
        
    }
    
    boolean isOfType(OclRoot or){
        if(or.isUndefined()){
            return false;  //follows strictly the rules in [OCL 1.6, p. A-26]
        }
        if(!(or instanceof JmiModelObject)){
            return false;
        } 
        else{
            if(this==ANY){
                return true;
            }
            RefObject ro = ((JmiModelObject)or).getRefObject();
            return ro.refIsInstanceOf(refClass.refMetaObject(),false);
        }     
    }
    
    boolean isOfKind(OclRoot or){
        if(or.isUndefined()){
            return true;  //follows strictly the rules in [OCL 1.6, p. A-26]
        }
        if(!(or instanceof JmiModelObject)){
            return false;
        } 
        else{
            if(this==ANY){
                return true;
            }
            RefObject ro = ((JmiModelObject)or).getRefObject();          
            return ro.refIsInstanceOf(refClass.refMetaObject(),true);
        }   
    }
    
    public OclSet allInstances() {
        Collection c = refClass.refAllOfType();
        return (OclSet)factory.getOclRepresentationFor(this.getOclSetType(), c);
    }
    
}
