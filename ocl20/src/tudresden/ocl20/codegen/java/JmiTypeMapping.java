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
package tudresden.ocl20.codegen;

import tudresden.ocl20.jmi.mof14.model.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;

/**
 * Generates code for uniquely identifying the types of operation parameters in the model.
 * This is needed as argument (NonOclType) for the renconvert() method in {@link tudresden.ocl20.lib.JmiOclLibrary JmiOclLibrary}
 *
 * @author  Stefan Ocke
 */
public class JmiTypeMapping{
    
    CodeGenerator cg;
    
    /** Creates a new instance of JmiTypeMapping */
    public JmiTypeMapping(CodeGenerator cg) {
        this.cg = cg;
    }
    
    private String getPackagePrefix(){
        return cg.getPkgPrefix();
    }
    
    public String getNonOclType(tudresden.ocl20.jmi.ocl.commonmodel.Parameter parameter){
        if(!(parameter instanceof Parameter)){
            throw new RuntimeException("JmiTypeMapping can only be applied to MOF parameters!");
        }
        StringBuffer result = new StringBuffer();
        appendNonOclType((Parameter)parameter, result);
        return result.toString();
    }
    
    private void appendNonOclType(Parameter parameter, StringBuffer result){
        appendNonOclType(parameter.getType(), result);
        int upper = parameter.getMultiplicity().getUpper();
        if(upper > 1 || upper == -1){
            if (parameter.getMultiplicity().isOrdered()){
                result.append(".getListType()");
            } else {
                result.append(".getCollectionType()");
            }
        }
    }
    
    
    private void appendNonOclType(Classifier type, StringBuffer result){
        if(type instanceof DataType){
            if(type instanceof AliasType){
                appendNonOclType(((AliasType)type).getType(),result);
            }
            
            else if(type instanceof PrimitiveType){
                String name = type.getName();
                //like "JmiType.INTEGER"
                result.append(getPackagePrefix());
                result.append("JmiType.");
                result.append(name.toUpperCase());
            }
            else if(type instanceof StructureType){
                StructureType st = (StructureType) type;
                appendJmiStructType(st, result);
            }
            else if(type instanceof EnumerationType){
                result.append(getPackagePrefix());
                result.append("JmiType.ENUMTYPE");
            }
            
            else if(type instanceof CollectionType){
                appendNonOclType(((CollectionType)type).getType(), result);
                if (((CollectionType)type).getMultiplicity().isOrdered()){
                    result.append(".getListType()");
                } else {
                    result.append(".getCollectionType()");
                }
            }
            else {
                throw new RuntimeException("JmiTypeMapping Unknown DataType "+type);
            }
            
        } else if(type instanceof MofClass){
            result.append(getPackagePrefix());
            result.append("JmiType.MODELTYPE");
        } else {
                throw new RuntimeException("JmiTypeMapping Unknown Classifier "+type);
        }
        
        
    }
    
    //like: factory.getJmiStructTypeFor(pathname, new String [] {"field1","field2"}, new JmiType[]{...,...} )
    private void appendJmiStructType(StructureType st, StringBuffer result){
        String pckPrfx = this.getPackagePrefix();
        String factoryId = cg.getFactoryId();
        
        String pathname= st.getPathNameA() ;
        StringBuffer names=new StringBuffer();
        StringBuffer types=new StringBuffer();
        Iterator it = st.getContents().iterator();
        boolean isFirst = true;
        while(it.hasNext()){
            ModelElement me = (ModelElement) it.next();
            if(me instanceof StructureField){
                StructureField sf = (StructureField) me;
                if(!isFirst){
                    names.append(',');
                    types.append(',');
                }
                names.append('"');
                names.append(sf.getName());
                names.append('"');
                appendNonOclType(sf.getType(), types);
                isFirst = false;
            }
        }
        
        result.append(factoryId);
        result.append(".getJmiStructTypeFor(\"");
        result.append(pathname);
        result.append("\", new String[]{");
        result.append(names);
        result.append("}, new ");
        result.append(pckPrfx);
        result.append("JmiType[]{");
        result.append(types);
        result.append("})");
        
    }
}
