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
import javax.jmi.reflect.RefPackage;
import javax.jmi.reflect.JmiException;
import javax.jmi.reflect.RefEnum;

import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * The JMI implementation of an OCL enumeration type description. 
 * Since there is no adequate class for enumeration type in JMI Reflection, the enumeration type is represented
 * by its  name and the package (javax.jmi.reflect.RefPackage) it lives in.
 * @author  Stefan Ocke.
 */
public class JmiEnumType extends OclEnumType {
    
    private RefPackage refPackage;
    private List pathnameList;
    private String shortname;
    
    /** Creates a new instance of JmiEnumType. */
    JmiEnumType(String pathname, RefPackage refPackage) {
        super(pathname);
        this.refPackage=refPackage;
        this.pathnameList = new ArrayList();
        StringTokenizer st = new StringTokenizer(pathname,"::");
        while(st.hasMoreTokens()){
            pathnameList.add(st.nextToken());
        }
        
        shortname = (String) pathnameList.get(pathnameList.size()-1);
    }
    
    public OclEnumLiteral getLiteralFor(String literalName) {
        try{
            return new JmiEnumLiteral(refPackage.refGetEnum(shortname, literalName));
        } catch (JmiException e){
            throw new OclException("Literal "+literalName+" does not exist in enumeration "+getName());
        }
    }
    
    boolean isOfType(OclRoot or){
        if(or.isUndefined()){
            return false;  //follows strictly the rules in [OCL 1.6, p. A-26]
        }
        if(!(or instanceof JmiEnumLiteral)){
            return false;
        } 
        else{
            RefEnum ro = ((JmiEnumLiteral)or).getRefEnum();
            List literalTypeName = (List)ro.refTypeName();
            
            return literalTypeName.equals(pathnameList);
        }     
    }
    
    boolean isOfKind(OclRoot or){
        if(or.isUndefined()){
            return true;  //follows strictly the rules in [OCL 1.6, p. A-26]
        }
        //issue: is Enumeration subtyping possible?
        return isOfType(or);
    }
    
    public boolean equals(Object o) {
        if(!(o instanceof JmiEnumType)){
            return false;
        }
        else{
            JmiEnumType other = (JmiEnumType) o;          
            return other.refPackage.equals(refPackage) && other.shortname.equals(shortname);
        }        
    }
    
}
