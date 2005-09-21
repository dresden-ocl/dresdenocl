/*
 * ASTGenerator.java
 *
 * Created on 3. August 2004, 12:28
 *
 * Copyright (c) 2004, 2005 Ansgar Konermann
 * Contact: <konermann@itikko.net>
 *
 * This file is part of the OCL2.0 parser and compiler libraries
 * created at Technische Universitaet Dresden (TUD), Germany.
 * Visit http://dresden-ocl.sourceforge.net/ for details.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston,
 * MA  02111-1307  USA
 *
 */

package tudresden.ocl20.core.parser.astgen;




import java.util.*;

import tudresden.ocl20.core.OclModel;
import tudresden.ocl20.core.OclModelHelper;
import tudresden.ocl20.core.parser.astlib.*;
import tudresden.ocl20.core.parser.sablecc.analysis.*;
import tudresden.ocl20.core.parser.sablecc.node.*;
import tudresden.ocl20.core.parser.util.SimpleMessageSink;
import tudresden.ocl20.core.jmi.ocl.commonmodel.Package;


/**
 * Generates an OCL2.0 abstract syntax tree from a SableCC concrete syntax tree
 * of the OCL2.0 expression.
 *
 * @author Ansgar Konermann
 * @deprecated This file is broken and was already replaced by LAttrAstGenerator. Don't use this any more.
 * @version
 */
public class ASTGenerator extends DepthFirstAdapter {
    
    private SimpleMessageSink logger = null;
    
    private OclModel model = null;
    private OclModelHelper helper = null;
    private tudresden.ocl20.core.jmi.ocl.commonmodel.Package topPackage = null;
    
    /**
     * Creates new ASTGenerator 
     *
     * @param context The OclModel instance which forms the context of the 
     *          OCL expression for which the AST should be computed.
     *
     */
    public ASTGenerator(OclModel context) {
        this.model = context;
        this.helper = new OclModelHelper(model);
        this.topPackage = this.model.getTopPackage();

    }
    
    public void setMessageSink(SimpleMessageSink sink) {
        logger = sink;
    }
    
    private void log(String msg) {
        if ( logger != null ) {
            logger.processMessage(msg);
        }        
    }
    
    private Set unimplementedOutMethods = new HashSet();
    
    public void defaultOut(Node node) {
        String nodeName = node.getClass().getName();
        if ( ! this.unimplementedOutMethods.contains(nodeName) ) {
            this.unimplementedOutMethods.add(nodeName);
            log("Unimplemented out method for class " + nodeName);
        }
    }
    
    //
    // ===== AST generation methods =====
    //
    
    
    // ==== PackagedConstraint
//    /**
//     * Computes the environment for a packaged constraint AST node,
//     * depending on the environment of the parent's environment and
//     * direct child nodes in the <b>concrete</b> syntax tree.
//     */
//    public void caseAPackagedConstraint(APackagedConstraint node) {
//        inAPackagedConstraint(node);
//        if(node.getPackage() != null)
//        {
//            node.getPackage().apply(this);
//        }
//        PPathNameCs pathNameNode = node.getPathNameCs();
//        if(pathNameNode != null)
//        {
//            pathNameNode.apply(this);
//        }
//        // we now have the path name as a list of strings in out(pathName)
//        List pathName = (List) getOut(pathNameNode);
//        
//        // determine environment based on package's path name
//        Package contextualPackage = topPackage.findPackage(pathName);
//        System.out.println("Found contextual package " + contextualPackage.getNameA());
//        
//        Namespace contextualNamespace = NamespaceUtility.createNamespaceFor(contextualPackage);
//        // this environment has no parents
//        Environment env = contextualNamespace.getEnvironmentWithoutParents();
//        
//        // per convention, the "in" map contains all inherited attributes
//        // for a node (here: "env" only), and the out map contains all the
//        // synthesized attributes of a node (here: "ast" only).
//        //
//        // That means: env is getIn(node), ast is getOut(node)
//        //
//        setIn(node, env);
//        
//        PContextDeclarationListCs contextDeclarationList = node.getContextDeclarationListCs();
//        if( contextDeclarationList != null)
//        {
//            contextDeclarationList.apply(this);
//        }
//        
//        OclPackagedConstraint astNode = new OclPackagedConstraint(pathName);
//        List constraints = (List) getOut(contextDeclarationList);
//        astNode.setContextDeclarationList(constraints);
//        
//        
//        if(node.getEndpackage() != null)
//        {
//            node.getEndpackage().apply(this);
//        }
//        // don't call out-method, since we already set the ast node inside
//        // this method
//        // outAPackagedConstraint(node);
//    }        
    /**
     * Empty implementation (does nothing)
     */
//    public void outAPackagedConstraint(APackagedConstraint node) { }
    
    // ==== ContextDeclarationListCs 
//    public void outAContextDeclarationListCs(AContextDeclarationListCs node) {
//        
//    }
    
    // ==== ContextDeclarationCs 
//    public void caseAContextDeclarationCs(AContextDeclarationCs node) {
//        // handle context types here
//        inAContextDeclarationCs(node);
//        if(node.getContext() != null)
//        {
//            node.getContext().apply(this);
//        }
//        if(node.getPathNameCs() != null)
//        {
//            node.getPathNameCs().apply(this);
//        }
//
//        PPathNameCs contextNameNode = node.getPathNameCs();
//        List contextName = (List) getOut(contextNameNode);
//        
//        // add contents of context namespace to environment
//        // @@TODO@@
//        
//        
//        // now descend into the context tail with the env
//        // initialized 
//        
//        if(node.getContextTailCs() != null)
//        {
//            node.getContextTailCs().apply(this);
//        }
//        outAContextDeclarationCs(node);
//        
//        
//        
//    }
    
    // ==== ContextTailCs 
    
//    public void outAAttrOrAssocContextTailCs(AAttrOrAssocContextTailCs node) {
//        // attr_or_assoc_context_decl_cs = colon type_specifier init_or_der_value_cs 
//        OclAttrOrAssocContextTailCs ast = new OclAttrOrAssocContextTailCs();
//        
//        // implement me
//        
//        setOut(node, ast);
//    }
//    public void outAClassifierContextTailCs(AClassifierContextTailCs node) {
//        OclClassifierContextTailCs ast = new OclClassifierContextTailCs();
//        
//        // implement me 
//        
//        setOut(node, ast);
//    }
//    public void outAOperationContextTailCs(AOperationContextTailCs node) {
//        OclOperationContextDecl ast = new OclOperationContextDecl();
//        
//        // implement me 
//        
//        setOut(node, ast);
//    }
    
    
    // ==== PathNameCs   
    /**
     * Creates an AST node for the default alternative of production
     * path_name_cs. The node is placed into the <b>out</b> map.
     * @mapto ArrayList(String)
     */
//    public void outAPathNameCs(APathNameCs node) {
//        // log("outAPathNameCs.begin");
//
//        // retrieve synthesized attributes of child node(s)
//        LinkedList qualifier = node.getQualifier();
//                
//        // create AST representation of path name's qualifier
//        ArrayList pathNameAst = new ArrayList(qualifier.size() + 1);
//        Iterator i = qualifier.iterator();
//        while ( i.hasNext() ) {
//            // retrieve child node's attributes for each path name head
//            PPathNameHeadCs qualifierPart = (PPathNameHeadCs) i.next();
//            if ( qualifierPart == null ) {
//                log("outAPathNameCs: WARN: qualifierPart is <null>");
//            }
//            String qualifierPartStr = (String) getOut(qualifierPart);
//            pathNameAst.add(qualifierPartStr);
//        }
//        // retrieve synthesized attribute of child node "name"
//        PIdentifierCs ident = node.getName();
//        String identifierStr = (String) getOut(ident);
//        // log("outAPathNameCs: ...::" + identifierStr );
//        pathNameAst.add(identifierStr);
//        
//        // store AST representation of PathNameCs as synthesized attribute
//        setOut(node, pathNameAst);
//        
//        // log("outAPathNameCs: " + pathNameAst.toString());        
//        // log("outAPathNameCs.end");
//    }
    
    /**
     * Creates an AST node for the default alternative of production
     * path_name_head_cs. The node is placed into the <b>out</b> map.
     * @mapto String
     */
//    public void outAPathNameHeadCs(APathNameHeadCs node) {
//        // log("outAPathNameHeadCs.begin");
//        
//        // retrieve synthesized attributes of child node(s) and
//        // create AST representation
//        TSimpleName simpleName = node.getSimpleName();
//        String simpleNameStr = (String) getOut(simpleName);        
//        // store AST representation of PathNameHeadCs as synthesized attribute
//        setOut(node, simpleNameStr);
//        
//        // log("outAPathNameHeadCs: " + simpleNameStr);
//        // log("outAPathNameHeadCs.end");
//    }
    
    //
    // ===== AST generation methods for parser production identifier_cs =====
    //
    /**
     * Creates an AST node for alternative "simple" of production
     * identifier_cs. The node is placed into the <b>out</b> map.
     * @mapto String
     */
    public void outASimpleIdentifierCs(ASimpleIdentifierCs node) {
        // retrieve synthesized attributes of child node(s)
        TSimpleName simple = node.getSimpleName();
        // create AST representation
        String identStr = (String) getOut(simple);       
        // store AST representation as synthesized attribute in OUT map
        setOut(node, identStr);
        // log("outASimpleIdentifierCs: " + identStr);        
    }
    
    /**
     * Creates an AST node for alternative "iterate" of production
     * identifier_cs. The node is placed into the <b>out</b> map.
     * @mapto String
     */
    public void outAIterateIdentifierCs(AIterateIdentifierCs node) {
        TIterate tokIterate = node.getIterate();
        String tokIterateStr = (String) getOut(tokIterate);
        setOut(node, tokIterateStr);
    }
    
    //
    // ===== AST generation methods for literal productions =====
    //
    
    // === boolean_literal_exp_cs
    /**
     * Creates an AST node for alternative 'true' of production 
     * boolean_literal_exp_cs. The node is placed into the <b>out</b> map.
     * @mapto java.lang.Boolean (true)
     */
    public void outATrueBooleanLiteralExpCs(ATrueBooleanLiteralExpCs node) {
        TTrue trueToken = node.getTrue();
        Boolean booleanLiteralAst = (Boolean) getOut(trueToken);
        setOut(node, booleanLiteralAst);
    }
    /**
     * Creates an AST node for alternative 'false' of production
     * boolean_literal_exp_cs. The node is placed into the <b>out</b> map.
     * @mapto java.lang.Boolean (false)
     */
    public void outAFalseBooleanLiteralExpCs(AFalseBooleanLiteralExpCs node) {
        TFalse falseToken = node.getFalse();
        Boolean booleanLiteralAst = (Boolean) getOut(falseToken);
        setOut(node, booleanLiteralAst);
    }
    
    
    
    //
    // ===== AST generation methods for tokens =====
    //
    
    /**
     * Creates an AST node for a simple name token. The token node is
     * put into the <b>out</b> map of this AnalysisAdapter.
     * @mapto String
     */
    public void caseTSimpleName(TSimpleName node) {
        createStringAstNodeForToken(node);
    }
    
    /**
     * Creates an AST node for an 'iterate' token. The token node is
     * put into the <b>out</b> map of this AnalysisAdapter.
     * @mapto String
     */
    public void caseTIterate(TIterate node) {
        createStringAstNodeForToken(node);
    }
        
    // boolean literals
    /**
     * Creates an AST node for a 'true' boolean literal. The token node is
     * put into the <b>out</b> map of this AnalysisAdapter.
     * @mapto java.lang.Boolean (true)
     */
    public void caseTTrue(TTrue node) {
        Boolean tokenValue = java.lang.Boolean.TRUE;
        setOut(node, tokenValue);
    }
    
    /**
     * Creates an AST node for a 'false' boolean literal. The token node is
     * put into the <b>out</b> map of this AnalysisAdapter.
     * @mapto java.lang.Boolean (false)
     */
    public void caseTFalse(TFalse node) {
        Boolean tokenValue = java.lang.Boolean.FALSE;
        setOut(node, tokenValue);
    }
    
    
    //
    // ===== helper methods =====
    //    
    /**
     * Creates a String containing the token text as AST node for the
     * given token. The AST node is put into the <b>out</b> map.
     */
    private void createStringAstNodeForToken(Token t) {
        String tokenText = t.getText();
        setOut(t, tokenText);
    }
    
}
