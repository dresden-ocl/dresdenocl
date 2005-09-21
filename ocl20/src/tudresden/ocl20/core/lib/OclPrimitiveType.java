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

/**
 * Describes an OCL primitive type.
 * @author  Stefan Ocke
 */
public class OclPrimitiveType extends OclType{
    
    
    private static OclPrimitiveType typeInteger = new OclPrimitiveType("OclInteger", OclInteger.class);
    private static OclPrimitiveType typeReal = new OclPrimitiveType("OclReal", OclReal.class);
    private static OclPrimitiveType typeBoolean = new OclPrimitiveType("OclBoolean", OclBoolean.class);
    private static OclPrimitiveType typeString = new OclPrimitiveType("OclString", OclString.class);
    
    static{
        addPredefined(typeInteger);
        addPredefined("Integer",typeInteger);
        addPredefined(typeReal);
        addPredefined("Real",typeReal);
        addPredefined(typeBoolean);
        addPredefined("Boolean",typeBoolean);
        addPredefined(typeString);
        addPredefined("String",typeString);
    
    }
    
    private Class clazz;
    
    /** Creates a new instance of OclPrimitiveType */
    private OclPrimitiveType(String name, Class clazz) {
        super(name);
        this.clazz=clazz;
    }
    
    public static OclPrimitiveType getOclInteger(){return typeInteger;}
    public static OclPrimitiveType getOclReal(){return typeReal;}
    public static OclPrimitiveType getOclBoolean(){return typeBoolean;}
    public static OclPrimitiveType getOclString(){return typeString;}
    
    boolean isOfType(OclRoot or){
       if(or.isUndefined()){
            return false;  //follows strictly the rules in [OCL 1.6, p. A-26]
       }
       return or.getClass().equals(clazz);
    }
    
    boolean isOfKind(OclRoot or){
        if(or.isUndefined()){
            return true;  //follows strictly the rules in [OCL 1.6, p. A-26]
        }
        return clazz.isInstance(or); 
    }
    
}
