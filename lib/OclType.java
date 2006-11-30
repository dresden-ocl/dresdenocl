/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * OCL Compiler                                                      *
 * Copyright (C) 1999, 2000 Frank Finger (frank@finger.org).         *
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
// FILE: d:/java/classes/de/tudresden/ocl/OclType.java

package tudresden.ocl20.core.lib; /* !!! if the package name is changed,
                            * please change the class attribute
                            * "oclPackage" further down
                            */

import java.util.*;

//////
///////** Old:: This class represents the predefined OCL class OclType and gives access to
////// *  the meta level of OCL. Is is implemented as an adapter class around a
////// *  java.lang.Class object.
////// *
////// *  @see java.lang.Class
////// *  @author Frank Finger
 

/** This class provides type information for an OclRoot. 
 * 
 *  @author Stefan Ocke
 */

public class OclType{

    private OclCollectionType [] collectionTypes;
    private String name;
  
    private static OclType oclAny; 
    private static OclType oclVoid; 
    
    static{
        oclAny = new OclType("OclAny");
        addPredefined(oclAny);
        oclVoid = new OclType("OclVoid");
        addPredefined(oclVoid);
    }
    
    private static Map predefinedTypes;
  
    protected OclType(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
    public static OclType getPredefined(String name){
        return (OclType) predefinedTypes.get(name);
    }
    
    protected static void addPredefined(String name, OclType type){
        if(predefinedTypes==null){predefinedTypes = new HashMap();}
        predefinedTypes.put(name, type);
    }
    protected static void addPredefined(OclType type){
        addPredefined(type.getName(), type);
    }
    
    public OclCollectionType getCollectionType(int kind){
        if(collectionTypes==null){
            collectionTypes = new OclCollectionType [OclCollectionType.COUNT];
        }
        if(kind >= OclCollectionType.COUNT){
            throw new OclException("Unknown Collection type: "+kind);
        }
        if(collectionTypes[kind] == null){
            collectionTypes[kind] = new OclCollectionType(kind, this);
        }
        return collectionTypes[kind];
    }
    
    public static OclType getOclAny(){
        return oclAny;
    }
    
    public static OclType getOclVoid(){
        return oclVoid;
    }
    
    public OclCollectionType getOclSetType(){
        return getCollectionType(OclCollectionType.SET);
    }
    public OclCollectionType getOclCollectionType(){
        return getCollectionType(OclCollectionType.COLLECTION);
    }
    public OclCollectionType getOclBagType(){
        return getCollectionType(OclCollectionType.BAG);
    }
    public OclCollectionType getOclSequenceType(){
        return getCollectionType(OclCollectionType.SEQUENCE);
    }
    public OclCollectionType getOclOrderedSetType(){
        return getCollectionType(OclCollectionType.ORDEREDSET);
    }
    
    boolean isOfType(OclRoot or){
        if(this==oclAny){
            return false; //oclAny has no direct instances
        } 
        if(this==oclVoid && or.isUndefined()){
            return true;
        }
        return false;
    }
    
    boolean isOfKind(OclRoot or){
        if(this==oclAny && !(or instanceof OclCollection)){
            return true;
        } else if(this==oclVoid && or.isUndefined()){
            return false; 
        }
        return false;
    }
    
    public OclSet allInstances(){
        return new OclSet(0, "allInstances not allowed on primitive types");
    }
    
    
    


} /* end class OclType */
