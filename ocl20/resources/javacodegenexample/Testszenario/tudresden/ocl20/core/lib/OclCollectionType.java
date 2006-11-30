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
 * Describes a (possibly nested) OCL collection type.
 * @author Stefan Ocke
 */
public class OclCollectionType extends OclType{
    
    public static final int COLLECTION = 0;
    public static final int SET = 1;
    public static final int ORDEREDSET = 2;
    public static final int SEQUENCE = 3;
    public static final int BAG = 4;
    public static final int COUNT = 5;
    
    private int kind;
    private OclType elementType;
    
    private static final String [] names= {"Collection","Set","OrderedSet","Sequence","Bag"};
    private static final Class [] classes = {OclCollection.class, OclSet.class, null, OclSequence.class, OclBag.class };
    
    /** Creates a new instance of OclCollectionType */
    OclCollectionType(int kind, OclType elementType) {
        super(names[kind]+"("+elementType.getName()+")");
        this.kind=kind;
        this.elementType=elementType;
    }
   
    
    public OclType getElementType(){
        return elementType;
    }
    
    public int getKind(){
        return kind;
    }
    
     /*According to the semantics chapter [Ocl1.6, p A-26] this is 
      either welldefined for Collections. The semantics is the following:
      All elements of the Collection must be kind of the stated element type, and
      at least one element must be "oclIsTypeOf" stated element type.
      */
    
    boolean isOfType(OclRoot or){
        if(or.getClass().equals(classes[kind])){
            //return elementType
            OclCollection col = (OclCollection) or;
            final OclIterator it = col.getIterator();
            OclBooleanEvaluatable eval = new OclBooleanEvaluatable(){
                public OclBoolean evaluate(){
                    return it.getValue().oclIsKindOf(elementType);
                }
            };
            if(!col.forAll(it, eval).isTrue()){
                return false;
            }
            final OclIterator it2 = col.getIterator();
            OclBooleanEvaluatable eval2 = new OclBooleanEvaluatable(){
                public OclBoolean evaluate(){
                    return it.getValue().oclIsTypeOf(elementType);
                }
            };
            return col.exists(it2, eval2).isTrue();         
        }
        else{
            return false;
        }
    }
    
    boolean isOfKind(OclRoot or){
         if(classes[kind].isInstance(or)){
            //return elementType
            OclCollection col = (OclCollection) or;
            final OclIterator it = col.getIterator();
            OclBooleanEvaluatable eval = new OclBooleanEvaluatable(){
                public OclBoolean evaluate(){
                    return it.getValue().oclIsKindOf(elementType);
                }
            };
            return col.forAll(it, eval).isTrue();             
        }
        else{
            return false;
        }
    }
    
    public OclSet allInstances(){
        return new OclSet(0, "allInstances not allowed on collection types");
    }
}
