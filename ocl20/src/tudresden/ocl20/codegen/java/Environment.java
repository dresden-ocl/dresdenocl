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

import java.util.*;
import tudresden.ocl20.jmi.ocl.*;
import tudresden.ocl20.jmi.ocl.expressions.*;
import tudresden.ocl20.jmi.ocl.types.*;
import tudresden.ocl20.jmi.ocl.commonmodel.*;

/**
 *
 * @author  Administrator
 *
 * manages identifier creation for a CodeGenerator
 */


class Environment {
    
    Map expressions = new HashMap();
    Map variables = new HashMap();
    Map types = new HashMap();
    
    private Map idNmbrs = new HashMap();
    
    private static final String IDPREFIX = "tudOcl20";
    
    private static final String FACT = IDPREFIX + "Fact";
    private static final String EXP = IDPREFIX + "Exp";
    private static final String VAR = IDPREFIX + "Var";
    private static final String TYPE = IDPREFIX + "Type";
    private static final String PARAM = IDPREFIX + "Param";
    private static final String ITER = IDPREFIX + "Iter";
    private static final String ACCU = IDPREFIX + "Accu";
    private static final String EVAL = IDPREFIX + "Eval";
        
    private String factoryId = null;
        
    /** Creates a new instance of Environment */
    public Environment() {
    }
    
    /**
     *Creates an unique Id with prefix prfx.
     */
    public String createId(String prfx){
        Integer idNmbr = (Integer) idNmbrs.get(prfx);
        if(idNmbr == null){
            idNmbr = new Integer(0);
        }
        String id = prfx + idNmbr;
        idNmbr = new Integer(idNmbr.intValue()+1);
        idNmbrs.put(prfx, idNmbr);
        return id;
    }
     
    public String getFactoryId() {
        return this.factoryId;
    }
    
    public String createFactoryId() {
        factoryId = createId(FACT);
        return factoryId;
    }
       
    public String getExpId(OclExpression exp){
        return (String) expressions.get(exp);
    }
    
    public String createExpId(OclExpression exp){
        String id = createId(EXP);
        expressions.put(exp, id);
        return id;
    }
    
    private void setExpId(OclExpression exp, String id){
        expressions.put(exp,id);
    }
    
    /**
     * sets the id of a variable expression to the one of 
     * the referred variable 
     */
    public void bind(VariableExp exp){
        setExpId(exp, getVarId(exp.getReferredVariable()));
    }
    
    public String getVarId(VariableDeclaration vd){
        return (String) variables.get(vd);
    }
    
    public String createVarId(VariableDeclaration vd){
        String id = createId(VAR);
        variables.put(vd, id);
        return id;
    }
    
    private void setVarId(VariableDeclaration vd, String id){
        variables.put(vd,id);
    }
    
    /**
     * sets the id of a variable to the one of its init expression
     */
    public void bind(VariableDeclaration vd, OclExpression exp){
        setVarId(vd, getExpId(exp));
    }
    
    
    public String getTypeId(Classifier type){
        return (String) types.get(type);
    }
    
    public String createTypeId(Classifier type){
        String id = createId(TYPE);
        types.put(type, id);
        return id;
    }
    
    protected String createParamId(){
        String id = createId(PARAM);
        return id;
    }
    
    protected String createIteratorId(){
        String id = createId(ITER);
        return id;
    }
    
    protected String createAccuId(){
        String id = createId(ACCU);
        return id;
    }
    
    protected String createEvalId(){
        String id = createId(EVAL);
        return id;
    }
    
}
