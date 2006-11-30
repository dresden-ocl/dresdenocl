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

 * Represents an undefined value whose type is unknown and still to be determined

 * by an explicit cast (via OCL.toOclCollection, Ocl.toInteger and so on).

 * In former versions of this Library this library OclAnyImpl(0,undefinedreason)

 * was used instead.

 * @author  Stefan Ocke

 */

public class OclUndefined implements OclRoot{

    

    

    private String undefinedreason = "";

    

   

    OclUndefined(String undefinedreason) {

        if(undefinedreason==null)

            throw new RuntimeException();

        this.undefinedreason=undefinedreason;

    }

    

    public final boolean isUndefined() {

        return true;

    }

    

    public final String getUndefinedReason() {

        if(undefinedreason!=null)

            return undefinedreason;

        else

            throw new RuntimeException();

    }

    

    public OclBoolean isEqualTo(Object o) {

        return OclBoolean.FALSE;

    }

    

    public OclBoolean isNotEqualTo(Object o) {

         return OclBoolean.TRUE;

    }

    

    public OclBoolean oclIsKindOf(OclType t) {

        return new OclBoolean(0, getUndefinedReason());   //specification says: true !

        

    }

    

    public OclBoolean oclIsTypeOf(OclType t) {

        return new OclBoolean(0, getUndefinedReason());   //specification says: false !

    }

    /**
     * @return the java class of this object  
     * @author: Ronny Brandt     
     */
    public Class toUmlClass() {
    	return null;
    }
    

    

    

}

