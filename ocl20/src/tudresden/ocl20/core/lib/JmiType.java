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

package tudresden.ocl20.lib;

/**
 * This class describes for JMI model access the type of a parameter of an operation.
 * For the JMI primitive types constants are defined. Types like CollectionType and StructureType are described by subclasses.
 * Enumeration types and model object types are described by constants as well, because they have one-to-one representations
 * in the library: JmiModelObject and JmiEnumLiteral. (That means, their JMI type is inherent in their OclRoot representation.) 
 * @author  Stefan Ocke
 */
public class JmiType implements NonOclType {
    
    public static final JmiType BOOLEAN = new JmiType("Boolean");
    public static final JmiType DOUBLE = new JmiType("Double");
    public static final JmiType FLOAT = new JmiType("Float");
    public static final JmiType INTEGER = new JmiType("Integer");
    public static final JmiType LONG = new JmiType("Long");
    public static final JmiType STRING = new JmiType("String");
    
    public static final JmiType ENUMTYPE = new JmiType("RefEnum");  //instances of RefEnum
    public static final JmiType MODELTYPE = new JmiType("RefObject"); //instances of RefObject
    
    private String name;
    /** Creates a new instance of JmiType */
    public JmiType(String name) {
        this.name = name;
    }
    public String toString(){
        return name;
    }
    
    public JmiCollectionType getCollectionType(){
        return new JmiCollectionType(this, JmiCollectionType.COLLECTION);
    }
    
     public JmiCollectionType getListType(){
        return new JmiCollectionType(this, JmiCollectionType.LIST);
    }
}
