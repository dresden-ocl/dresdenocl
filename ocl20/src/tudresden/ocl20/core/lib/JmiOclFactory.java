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

import javax.jmi.reflect.*;
import java.util.*;
/**
 * This class is the implementation of the {@link OclFactory OclFactory} that provides model access vie JMI reflection.
 * @author Stefan Ocke
 */
public class JmiOclFactory extends AbstractOclFactory {
    
    private static Map instances = new HashMap();
    
    private JmiModelTypeManager typemanager;
    private RefPackage model;
    
    /** Creates a new instance of JmiOclFactory */
    private JmiOclFactory(RefPackage model) {
        this.model = model;
        typemanager = new JmiModelTypeManager(this);
    }
    
    public static JmiOclFactory getInstance(RefPackage model){
        JmiOclFactory result = (JmiOclFactory) instances.get(model);
        if(result == null){
            result = new JmiOclFactory(model);
            instances.put(model,result);
        }
        return result;
    }
    
    public JmiModelTypeManager getJmiModelTypeManager(){
        return typemanager;
    }
    
    public RefPackage getModel(){
        return model;
    }
    
    public OclModelType getOclModelTypeFor(String name){
        OclModelType result = typemanager.getModelType(name);
        if(result==null){
            throw new OclException("Type "+name+ " not found.");
        }
        return result;
    }
    
    /** Maps a JMI-Type to the explixcitly stated Ocl-Type*/
    //still TBD: Enumerations, TupleTypes
    public OclRoot getOclRepresentationFor(OclType type, Object o) {
        if(type == null){
            return  getOclRepresentationFor(o);
        }
        OclRoot result = null;
        if (o==null){
            result = new OclUndefined("OclRepresentation for null");
        }
        else if(o instanceof Object[]){
            //unwrap an out/inout parameter result value
            Object wrappedObject = ((Object []) o) [0];
            result = getOclRepresentationFor(type, wrappedObject);
        }
        else if(!(type instanceof OclCollectionType)){
            
            if (o instanceof Boolean)
                result= getOclRepresentationFor( ((Boolean)o).booleanValue() );
            else if (o instanceof Integer)
                result= getOclRepresentationFor( ((Integer)o).intValue() );
            else if (o instanceof Long)
                result= getOclRepresentationFor( ((Long)o).longValue() );
            else if (o instanceof Float)
                result= getOclRepresentationFor( ((Float)o).floatValue() );
            else if (o instanceof Double)
                result= getOclRepresentationFor( ((Double)o).doubleValue() );
            else if (o instanceof String)
                result= new OclString( (String)o );
            else if(o instanceof RefObject){
                RefObject ro = (RefObject) o;
                result = new JmiModelObject(ro, this);
            }
            else if(o instanceof RefEnum){
                result = new JmiEnumLiteral((RefEnum)o);
            }
            else if(o instanceof RefStruct && type instanceof OclTupleType){
                OclTupleType tupleType = (OclTupleType) type;
                RefStruct rs = (RefStruct) o;
                OclTuple tuple = new OclTuple();
                
                Iterator it = tupleType.getPartNames().iterator();
                while(it.hasNext()){
                    String partname = (String) it.next();
                    
                    OclRoot value = getOclRepresentationFor(tupleType.getType(partname),rs.refGetValue(partname));
                    tuple.setValue(partname, value);
                }
                
                result = tuple;
            }
            
            if(result == null || !type.isOfKind(result)){
                throw new OclException("JMI Type Mapping: Can not map "+ o.getClass().getName()+ " to "+type.getName());
            }
            
        } else {
            OclCollectionType oct = (OclCollectionType) type;
            if(oct.getKind()==OclCollectionType.SEQUENCE){
                if(o instanceof List){
                    ArrayList list=new ArrayList();
                    for(Iterator i=((List) o).iterator(); i.hasNext(); )
                        list.add(getOclRepresentationFor(oct.getElementType(), i.next()));
                    return new OclSequence(list);
                }
                else {
                    throw new OclException("JMI Type Mapping: Expected a List, got "+ o.getClass().getName() + " instead");
                }
            }
            //            else if(oct.getKind()==OclCollectionType.ORDEREDSET){
            //                if(o instanceof List){
            //                }
            //                else
            //                {
            //                    throw new OclException("JMI Type Mapping: Expected a List, got "+ o.getClass().getName() + " instead");
            //                }
            //            }
            else if(oct.getKind()==OclCollectionType.BAG){
                if(o instanceof Collection){
                    ArrayList list=new ArrayList();
                    for(Iterator i=((Collection) o).iterator(); i.hasNext(); )
                        list.add(getOclRepresentationFor( i.next()));
                    return new OclSequence(list);
                }
                else {
                    throw new OclException("JMI Type Mapping: Expected a Collection, got "+ o.getClass().getName() + " instead");
                }
            }
            else if(oct.getKind()==OclCollectionType.SET){
                if(o instanceof Collection){
                    HashSet set=new HashSet();
                    for(Iterator i=((Collection)o).iterator(); i.hasNext(); )
                        set.add(getOclRepresentationFor(oct.getElementType(), i.next()));
                    result =  new OclSet(set);
                }
                else {
                    throw new OclException("JMI Type Mapping: Expected a Collection, got "+ o.getClass().getName() + " instead");
                }
            }
            else {
                throw new OclException("Unknown Collection kind or tried to instantiate abstract type OclCollection: "+oct.getKind());
            }
        }
        
        return result;
        
    }
    
    public OclRoot getOclRepresentationFor(Object o) {
        throw new OclException("getOclRepresentation without explicit type is not allowed in JmiOclFactory.");
    }
    
    public Object reconvert(OclRoot oclObject){
        throw new OclException("JMI Type Mapping: reconvert: targetType must be explicitly stated!");
    }
    
    /** reconverts the parameter to a JMI-Type. For out / inout parameters an array is constructed as
     * forced by the JMI mapping for parameters
     */
    public Object reconvert(OclParameter param) {
        int dir = param.getDirection();
        if(dir==OclParameter.DIR_IN){
            return reconvert(param.getNonOclType(), param.getValue(), false);
        }
        else if(dir==OclParameter.DIR_OUT || dir==OclParameter.DIR_INOUT){
            return reconvert(param.getNonOclType(), param.getValue(), true);
        }
        return null;
    }
    
    /** reconverts an OCL Root to a JMI-Type. The parameter targetType is needed to solve disambiguities
     *      in case of Integer vs. Long and Float vs. Double.
     */
    public Object reconvert(NonOclType targetType, OclRoot oclObject) {
        return reconvert(targetType, oclObject, false);
    }
    
    private Object reconvert(NonOclType targetType, OclRoot oclObject, boolean arrayWrap) {
        
        if(targetType == null){
            throw new OclException("JMI Type Mapping: reconvert: targetType must  not be null!");
        }
        if(!(targetType instanceof JmiType)){
            throw new OclException("JMI Type Mapping: reconvert: targetType must be a JmiType!");
        }
        JmiType jmiType = (JmiType) targetType;
        Object result=null;
        
        if(targetType instanceof JmiCollectionType && oclObject instanceof OclCollection){
            JmiCollectionType jct = (JmiCollectionType) targetType;
            if(arrayWrap && oclObject == null){
                //construct an empty array that can hold one Collection / List
                
                result = (jct.isList())? new List[1] : new Collection[1];
            } else {
                OclCollection oc=(OclCollection)oclObject;
                List l=new ArrayList();
                OclIterator iter=oc.getIterator();
                while (iter.hasNext()) {
                    iter.next();
                    OclRoot obj=iter.getValue();
                    l.add( reconvert(((JmiCollectionType)targetType).getElementType(), obj) );
                }
                
                if(arrayWrap){
                    result = (jct.isList())? new List[]{l} : new Collection[]{l};
                } else {
                    result = l;
                }
            }
        }
        else if(targetType == JmiType.STRING && oclObject instanceof OclString){
            if(arrayWrap && oclObject == null){
                //construct an empty array that can hold one String
                
                result = new String[1];
            } else {
                String s = ((OclString)oclObject).getString();
                if(arrayWrap){
                    result = new String[]{s};
                } else {
                    result = s;
                }
            }
        }
        else if(targetType == JmiType.BOOLEAN && oclObject instanceof OclBoolean){
            if(arrayWrap && oclObject == null){
                //construct an empty array that can hold one Boolean
                result = new Boolean[1];
            } else {
                Boolean b = Boolean.valueOf(((OclBoolean)oclObject).isTrue());
                if(arrayWrap){
                    result = new Boolean[]{b};
                } else {
                    result = b;
                }
            }
        }
              
        else if(targetType == JmiType.INTEGER && oclObject instanceof OclInteger) {
            if(arrayWrap && oclObject == null){
                //construct an empty array that can hold one Integer
                result = new Integer[1];
            } else {
                Integer i = new Integer(((OclInteger) oclObject).getInt());
                if(arrayWrap){
                    result = new Integer[]{i};
                } else {
                    result = i;
                }
            }
        }
        else if(targetType == JmiType.LONG) {
            if(arrayWrap && oclObject == null){
                //construct an empty array that can hold one Long
                result = new Long[1];
            } else if(oclObject instanceof OclInteger){
                Long l = new Long(((OclInteger) oclObject).getLong());
                if(arrayWrap){
                    result = new Long[]{l};
                } else {
                    result = l;
                }
            }
        }
        else if(targetType == JmiType.DOUBLE) {
            if(arrayWrap && oclObject == null){
                //construct an empty array that can hold one Double
                result = new Double[1];
            } else {
                Double d = null;
                if(oclObject instanceof OclInteger){
                    d = new Double(((OclInteger) oclObject).getDouble());
                } else if(oclObject instanceof OclReal){
                    d = new Double(((OclReal) oclObject).getDouble());
                }
                if(d!=null){
                    if(arrayWrap){
                        result = new Double[]{d};
                    } else {
                        result = d;
                    }
                }
            }
        }
        else if(targetType == JmiType.FLOAT) {
            if(arrayWrap && oclObject == null){
                //construct an empty array that can hold one Double
                result = new Float[1];
            } else {
                Float f = null;
                if(oclObject instanceof OclInteger){
                    f = new Float(((OclInteger) oclObject).getDouble());
                } else if(oclObject instanceof OclReal){
                    f = new Float(((OclReal) oclObject).getDouble());
                }
                if(f!=null){
                    if(arrayWrap){
                        result = new Float[]{f} ;
                    } else {
                        result = f;
                    }
                }
            }
        }
        
        else if (targetType == JmiType.MODELTYPE && oclObject instanceof JmiModelObject) {
            if(arrayWrap && oclObject == null){
                //problem: it is not possible to determine the right type for the array
                // RefObject[] is to general an can not be narrowed as appropriate
                throw new OclException("JMI Type Mapping: reconvert: out parameters are not supported for model types.");
            } else {
                JmiModelObject impl=(JmiModelObject)oclObject;
                RefObject ro = impl.getRefObject();
                if (arrayWrap){
                    throw new OclException("JMI Type Mapping: reconvert: inout parameters are not supported for model types.");
//                    result = java.lang.reflect.Array.newInstance(ro.getClass(),1);
//                    java.lang.reflect.Array.set(result, 0, ro);
                }
                else{
                    result = ro;
                }
            }
        }
        else if (targetType == JmiType.ENUMTYPE && oclObject instanceof JmiEnumLiteral ) {
            if(arrayWrap && oclObject == null){
                //problem: it is not possible to determine the right type for the array
                // RefEnum[] is to general an can not be narrowed as appropriate
                throw new OclException("JMI Type Mapping: reconvert: out parameters are not supported for enumeration types.");
            } else {
                JmiEnumLiteral impl=(JmiEnumLiteral)oclObject;
                RefEnum re = impl.getRefEnum();
                if (arrayWrap){
                    throw new OclException("JMI Type Mapping: reconvert: inout parameters are not supported for enumeration types.");        
//                    result = java.lang.reflect.Array.newInstance(re.getClass(),1);
//                    java.lang.reflect.Array.set(result, 0, re);
                }
                else{
                    result = re;
                }
            }
        }
        else if (targetType instanceof JmiStructType && oclObject instanceof OclTuple ){
            if(arrayWrap && oclObject == null){
                //problem: it is not possible to determine the right type for the array
                // RefStruct[] is to general an can not be narrowed as appropriate
                throw new OclException("JMI Type Mapping: reconvert: out parameters are not supported for structure types.");
            } else {
                
                JmiStructType st = (JmiStructType) targetType;
                RefStruct rs = st.createStruct((OclTuple) oclObject);
                
                if (arrayWrap){
                     throw new OclException("JMI Type Mapping: reconvert: inout parameters are not supported for structure types.");
            
//                    result = java.lang.reflect.Array.newInstance(rs.getClass(),1);
//                    java.lang.reflect.Array.set(result, 0, rs);
                }
                else{
                    result = rs;
                }
            }
        }
        if(result == null){
            throw new OclException("JMI Type Mapping: Could not reconvert "+ oclObject + " to "+ targetType);
        }
        return result;
    }
    
    
    public OclEnumType getOclEnumTypeFor(String pathname) {
        OclEnumType result = typemanager.getEnumType(pathname);
        if(result==null){
            throw new OclException("Type "+pathname+ " not found.");
        }
        return result;
    }
    
    //gets the wrapper for a MOF structure type. Note that the fieldnames and fieldtypes can not be determined
    //from the model, and thus must be stated explicitly
    public JmiStructType getJmiStructTypeFor(String pathname, String [] fieldnames, JmiType [] fieldtypes){
        JmiStructType result = typemanager.findJmiStructType(pathname, fieldnames, fieldtypes);
        
        return result;
    }
    
}
