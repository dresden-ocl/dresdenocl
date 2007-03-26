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



/**

 * Represents an OCL tuple.

 * @author  Stefan Ocke

 */

public class OclTuple extends OclAny {

    

    private Map parts;

    

    OclTuple(){

        parts = new HashMap();

    }

        

    public OclTuple(String [] names, OclRoot [] values){

        parts = new HashMap();

        for(int i = 0; i<names.length; i++){

            parts.put(names[i], values[i]);

        }

    }

    

    public OclTuple(String undefinedReason){

        super(0, undefinedReason);

    }

    

    /**

     * @param o

     * @return tudresden.ocl20.lib.OclBoolean

     * @roseuid 3EDB7CF702EE

     */

    public OclBoolean isEqualTo(Object o) {

        if ( !(o instanceof OclTuple) ) {

            System.out.println(

            "OclTuple isEqualTo() is called with a non-OclTuple parameter"

            );

            return OclBoolean.FALSE;

        }

        OclTuple other=(OclTuple)o;

        if(isUndefined()){

            return new OclBoolean(0,getUndefinedReason());

        }

        if(other.isUndefined()){

            return new OclBoolean(0,other.getUndefinedReason());

        }



        return OclBoolean.getOclRepresentationFor(this.parts.equals(other.parts));

           

    }



    

    /** Gets a feature of this modelobject. The OCL type of the result is determined by its Java Class.*/

    public OclRoot getFeature(String partname){

        OclRoot or = getValue(partname);

        if(or == null){

            throw new OclException("Unknown tuple part: "+partname+" in "+this);

        }

        return or;

    }

    

    OclRoot getValue(String partname){

        return (OclRoot) parts.get(partname);

    }

    

    void setValue(String partname, OclRoot value){

        parts.put(partname, value);

    }

    

    int getSize(){

        return parts.size();

    }

    

    public String toString(){

        StringBuffer result=new StringBuffer();

        result.append("<OclTuple> ");

        Iterator it = parts.entrySet().iterator();

        while(it.hasNext()){

            Map.Entry entry = (Map.Entry) it.next();

            result.append(entry.getKey());

            result.append(" = ");

            result.append(entry.getValue()); 

            if(it.hasNext()){

              result.append(", ");  

            }

        }

        return result.toString();

    }

}



