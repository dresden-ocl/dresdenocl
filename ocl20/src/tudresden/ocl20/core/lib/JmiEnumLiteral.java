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
import javax.jmi.reflect.RefEnum;

/**
 * The JMI implementation of an OCL enumeration literal. Basically a wrapper around the reflective class javax.jmi.reflect.RefEnum.
 * @author  Stefan OCke.
 */
public class JmiEnumLiteral extends OclEnumLiteral {
    
    private RefEnum refEnum;
    
    /** Creates a new instance of JmiEnumLiteral */
    JmiEnumLiteral(RefEnum refEnum) {
        this.refEnum = refEnum;
    }
    
    public OclBoolean isEqualTo(Object o) {
                
        if ( !(o instanceof JmiEnumLiteral) ) {
            System.out.println("JmiEnumLiteral isEqualTo() is called with a non-JmiEnumLiteral parameter");
            return OclBoolean.FALSE;
        }
        JmiEnumLiteral other=(JmiEnumLiteral)o;
        if (isUndefined())
            return new OclBoolean(0,getUndefinedReason());
        else if(other.isUndefined())
            return new OclBoolean(0,other.getUndefinedReason());
        
        if (this.refEnum.equals(other.refEnum)) {
            return OclBoolean.TRUE;
        } else {
            return OclBoolean.FALSE;
        }
    }
    
    RefEnum getRefEnum(){
        return refEnum;
    }
    
}
