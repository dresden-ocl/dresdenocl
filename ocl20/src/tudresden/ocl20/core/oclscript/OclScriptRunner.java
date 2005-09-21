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

package tudresden.ocl20.core.oclscript;

import tudresden.ocl20.*;
import tudresden.ocl20.core.OclModel;
import tudresden.ocl20.core.OclModelHelper;
import tudresden.ocl20.core.WellFormednessException;
import tudresden.ocl20.core.jmi.ocl.commonmodel.*;
import tudresden.ocl20.core.jmi.ocl.expressions.*;

import java.io.*;
import java.util.*;

/**
 * A parser for a simple script language (OCL script) for creating OCL expressions within an {@link tudresden.ocl20.core.OclModel OclModel}
 * This was helpful to create OCL expressions in abstract syntax as long as no parser for "real OCL Syntax" did exist.
 * @author  Stefan Ocke
 */
public class OclScriptRunner {
    
    private OclModel model;
    private OclModelHelper mh;
    
    private Map identifiers=new LinkedHashMap();
    
    
    public static class ParseException extends Exception{
        private int lineNumber;
        private String wrongToken;
        
        public ParseException(int lineNumber, String wrongToken){
            super("Unexpected: "+wrongToken);
            this.lineNumber = lineNumber;
            this.wrongToken = wrongToken;
        }
        
        public int getLineNumber(){
            return lineNumber;
        }
        
        public String getWrongToken(){
            return wrongToken;
        }
        
    }
    
    /** Creates a new instance of Parser */
    public OclScriptRunner(OclModel model) {
        this.model = model;
        this.mh = new OclModelHelper(model);
    }
    
    /**Parses an OCL-Script (*.os) an creates instances of OclExpression
    and Constraint as described in the script*/
    
    public void runScript(InputStream is) throws IOException, ParseException, WellFormednessException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StreamTokenizer st = new StreamTokenizer(br);
        st.slashSlashComments(true);
        
        try{
            mh.beginTrans(true);
            boolean inBlock = false;
            
            while(st.ttype != StreamTokenizer.TT_EOF){
                int token = st.nextToken();
                
                //System.out.println(st.ttype+"  "+st.toString()+"  "+st.sval+"  "+st.nval);
                
                
                if(!inBlock && token != StreamTokenizer.TT_EOF){
                    if(token != (int)'{'){
                        throw new ParseException(st.lineno(), st.toString());
                    }
                    inBlock=true;
                }
                else if(token != StreamTokenizer.TT_EOF){
                    if(token == (int)'}'){
                        identifiers.clear();
                        inBlock=false;
                    }
                    else if (token == StreamTokenizer.TT_WORD){
                        String keyword = st.sval;
                        nextToken(st, StreamTokenizer.TT_WORD);
                        String id = st.sval;
                        nextToken(st, '=');
                        
                        System.out.println(keyword+" "+id);
                        
                        ModelElement me=null;
                        if(keyword.equals("Classifier")){
                            nextToken(st, '"');
                            String pathname = st.sval;
                            
                            Classifier c = mh.findClassifier(pathname);
                            me=c;
                            System.out.println("Found Classifier "+c.getPathNameA());
                            
                            nextToken(st, ';');
                        }
                        else if(keyword.equals("OpCall")){

                            ModelElement source = getForId(st);
                            String name = getString(st);
                            
                            OclExpression [] args;
                            OperationCallExp oce;
                            
                            
                            List argList = new ArrayList();
                            do{
                                OclExpression exp = getExpForIdOrEndOfStmt(st);
                                if(exp!=null){argList.add(exp);}
                            } while (st.ttype != ';');
                            
                            args=(OclExpression []) argList.toArray(new OclExpression[argList.size()]);
                            
                            if(source instanceof OclExpression){
                                oce=mh.createOperationCall((OclExpression)source, name, args);
                                System.out.println("Created operation call: "+name);
                            } else if(source instanceof Classifier){
                                //a class operation
                                oce = mh.createClassOperationCall((Classifier) source, name, args);
                                System.out.println("Created class operation call: "+name);
                            } else {
                                throw new ParseException(st.lineno(), name); //wrong type 
                            }
                            
                            me=oce;
                        }
                        else if(keyword.equals("AttrCall")){

                            ModelElement source = getForId(st);
                            String name = getString(st);
                                                       
                            AttributeCallExp ace;
             
                            if(source instanceof OclExpression){
                                ace=mh.createAttributeCall((OclExpression)source, name);
                                System.out.println("Created operation call: "+name);
                            } else if(source instanceof Classifier){
                                //a class operation
                                ace = mh.createClassAttributeCall((Classifier) source, name);
                                System.out.println("Created class operation call: "+name);
                            } else {
                                throw new ParseException(st.lineno(), name); //wrong type 
                            }
                            
                            me=ace;
                            nextToken(st, ';');
                        }
                        else if(keyword.equals("IteratorVar") || keyword.equals("ParamVar")){
                            String name = getString(st);
                            ModelElement type = getForId(st);
                            if(!(type instanceof Classifier)){
                                throw new ParseException(st.lineno(), keyword); //wrong type
                            }
                            me = mh.createUninitializedVar(name, (Classifier)type);
                            
                            nextToken(st, ';');
                        }
                        else if(keyword.equals("Var")){
                            String name = getString(st);
                            Classifier type = this.getClassifierForId(st);
                            OclExpression exp = getExpForId(st);
                            me = mh.createInitializedVar(name, type, exp);
                            nextToken(st, ';');
                        }
                        else if(keyword.equals("VarExp")){
                            VariableDeclaration vd = getVarDeclForId(st);
                            me = mh.createVariableExp((VariableDeclaration)vd);
                            nextToken(st, ';');
                        }
                        else if(keyword.equals("AssocEndCall")){
                            OclExpression source = getExpForId(st);
                            String name = getString(st);
                            me = mh.createAssociationEndCall(source, name);
                            nextToken(st, ';');
                        }
                        else if(keyword.equals("String")){
                            String s = getString(st);
                            me = mh.createStringLiteral(s);
                            nextToken(st, ';');
                        }
                        else if(keyword.equals("Integer")){
                            nextToken(st, StreamTokenizer.TT_NUMBER);
                            me = mh.createIntegerLiteral(new Double(st.nval).intValue());
                            nextToken(st, ';');
                        }
                        else if(keyword.equals("Real")){
                            nextToken(st, StreamTokenizer.TT_NUMBER);
                            me = mh.createRealLiteral(st.nval);
                            nextToken(st, ';');
                        }
                        else if(keyword.equals("Boolean")){
                            nextToken(st, StreamTokenizer.TT_WORD);
                            me = mh.createBooleanLiteral(Boolean.valueOf(st.sval).booleanValue());
                            nextToken(st, ';');
                        }
                        else if(keyword.equals("EnumLiteral")){
                            String s = getString(st);
                            me = mh.createEnumLiteral(s);
                            nextToken(st, ';');
                        }
                        else if(keyword.equals("IsKindOf") || keyword.equals("IsTypeOf") || keyword.equals("AsType")){
                            OclExpression source = getExpForId(st);
                            String typename = getString(st);
                            me = mh.createOclOperationWithTypeArgExp(source, "ocl"+keyword, typename);
                            nextToken(st, ';');
                        }
                        else if(keyword.equals("Iterator")){
                            OclExpression source = getExpForId(st);
                            String name = getString(st);
                            VariableDeclaration itVar = this.getVarDeclForId(st);
                            OclExpression body = getExpForId(st);
                            
                            me = mh.createIteratorExp(source, name, itVar, body);
                            nextToken(st, ';');
                        }
                        else if(keyword.equals("Iterate")){
                            OclExpression source = getExpForId(st);
                            VariableDeclaration itVar = this.getVarDeclForId(st);
                            VariableDeclaration accVar = this.getVarDeclForId(st);
                            OclExpression body = getExpForId(st);                           
                            me = mh.createIterate(source,itVar,accVar,body);
                            nextToken(st, ';');
                        }
                        else if(keyword.equals("IfThenElse")){
                            OclExpression cond = getExpForId(st);
                            OclExpression thenExp = getExpForId(st);
                            OclExpression elseExp = getExpForId(st);
                            me = mh.createIfThenElse(cond, thenExp, elseExp);
                            nextToken(st, ';');
                        }
                        else if(keyword.equals("Invariant")){
                            Classifier context = getClassifierForId(st);
                            String name = getString(st);
                            OclExpression bodyExp = getExpForId(st);
                            String body = getString(st);
                                             
                            nextToken(st, ';');
                            me = mh.createInvariant(context, name, bodyExp, body);
                        }
                        
                        else if (keyword.equals("OpDef")){
                            Classifier context = getClassifierForId(st);
                            String name = getString(st);
                            Classifier resultType = getClassifierForId(st);
                            OclExpression bodyExp = getExpForId(st);
                            
                            List paramVarList = new ArrayList();
                            VariableDeclaration vd = null;
                            do{
                                vd = this.getVarDeclForIdOrNull(st);
                                if(vd!=null){
                                    paramVarList.add(vd);
                                }
                            } while (vd != null);
                            nextToken(st, ';');
                            
                            me = mh.createOperationDef(context, name+"OperationDef", name, resultType, paramVarList, bodyExp, "opBody");
                        }                     
                        else{
                            throw new ParseException(st.lineno(), keyword); //unknown keyword
                        }
                        
                        identifiers.put(id, me);
                        
                        
                    }
                    else {
                        throw new ParseException(st.lineno(), st.toString());
                    }
                }
                
                
                
            }
        } catch (ParseException pe){
            mh.endTrans(true);
            
            throw pe;
        }
        mh.endTrans(false);
        br.close();
    }
    
    private int nextToken(StreamTokenizer st, int expectedToken) throws IOException, ParseException{
        int token = st.nextToken();
        
        
        if(token != expectedToken){
            
            System.out.println("expected: "+expectedToken+"   got:"+st.ttype+"  "+st.toString()+"  "+st.sval+"  "+st.nval);
            throw new ParseException(st.lineno(), st.toString());
        }
        return token;
    }
    
    private ModelElement getForId(StreamTokenizer st)throws IOException, ParseException{
        nextToken(st, '$');
        nextToken(st, StreamTokenizer.TT_WORD);
        ModelElement result = (ModelElement) identifiers.get(st.sval);
        if(result == null){
            throw new ParseException(st.lineno(), st.toString()); //unknown identifier
        }
        return result;
    }
    
    private OclExpression getExpForId(StreamTokenizer st)throws IOException, ParseException{
        ModelElement me = getForId(st);
        if(!(me instanceof OclExpression)){
             throw new ParseException(st.lineno(), st.toString()); //wrong type
        }
        return (OclExpression) me;
    }
    
     private Classifier getClassifierForId(StreamTokenizer st)throws IOException, ParseException{
        ModelElement me = getForId(st);
        if(!(me instanceof Classifier)){
             throw new ParseException(st.lineno(), st.toString()); //wrong type
        }
        return (Classifier) me;
    }
    
    private VariableDeclaration getVarDeclForId(StreamTokenizer st)throws IOException, ParseException{
        ModelElement me = getForId(st);
        if(!(me instanceof VariableDeclaration)){
             throw new ParseException(st.lineno(), st.toString()); //wrong type
        }
        return (VariableDeclaration) me;
    }
    
    private VariableDeclaration getVarDeclForIdOrNull(StreamTokenizer st)throws IOException, ParseException{
        try{
            ModelElement me = getForId(st);
            return (VariableDeclaration) me;
        }
        catch (ParseException pe){
            st.pushBack();
            return null;
        }     
    }
    
    
        
    private OclExpression getExpForIdOrEndOfStmt(StreamTokenizer st)throws IOException, ParseException{
        int token = st.nextToken(); 
        if(token == ';'){return null;}
        if(token != '$'){throw new ParseException(st.lineno(), st.toString());}
        nextToken(st, StreamTokenizer.TT_WORD);
        ModelElement result = (ModelElement) identifiers.get(st.sval);
        if(result == null || !(result instanceof OclExpression)){
            throw new ParseException(st.lineno(), st.toString()); //unknown identifier
        }
        return (OclExpression)result;
    }
    
    
    private String getString(StreamTokenizer st)throws IOException, ParseException{
        nextToken(st, '"');
        return st.sval;
    }
    
    public static void main(String[] s) throws Exception{
        OclScriptRunner osr = new OclScriptRunner(null);
        
        java.net.URL dir = (ClassLoader.getSystemClassLoader().getResource("OCLScript/"));
        java.net.URL inputURL = new java.net.URL(dir, "OclWFR.os");
        
        InputStream is = new FileInputStream(inputURL.getPath());
        osr.runScript(is);
    }
}
