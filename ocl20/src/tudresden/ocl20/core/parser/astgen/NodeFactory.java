/*
 * NodeFactory
 *
 * Created on 25. August 2004, 00:09
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

package tudresden.ocl20.parser.astgen;


import tudresden.ocl20.parser.astlib.*;

import tudresden.ocl20.OclModel;
import tudresden.ocl20.OclModelHelper;
import tudresden.ocl20.jmi.ocl.commonmodel.*;
import tudresden.ocl20.jmi.ocl.expressions.*;
import tudresden.ocl20.jmi.ocl.types.*;

import java.util.*;


/**
 *
 * Abstract factory which creates instances of AST nodes specified
 * by name.
 *
 * @author Ansgar Konermann
 * @version
 */
public class NodeFactory extends Object {
    
    private OclModelHelper helper = null;
    private OclModel model = null;
    private OclExpressionFactory f = null;
       
    public NodeFactory(OclModelHelper helper, OclModel model) {
        this.helper = helper;
        this.model = model;
        this.f = model.getOclExpressionFactory();
        initialize();
    }
    
    private abstract static class Ctor {
        public abstract Object cr();
    }
    
    private Map creatorsByType = new HashMap();
    private void initialize() {
        Map c = creatorsByType;
        c.put("Object",                 new Ctor() { public Object cr() { return new Object(); } } );
        c.put("CollectionItem",         new Ctor() { public Object cr() { return f.createCollectionItem(); } } );
        c.put("IfExp",                  new Ctor() { public Object cr() { return f.createIfExp(); } } );
        c.put("LetExp",                 new Ctor() { public Object cr() { return f.createLetExp(); } } );
        c.put("OperationCallExp",       new Ctor() { public Object cr() { return f.createOperationCallExp(); } } );
        c.put("VariableDeclaration",    new Ctor() { public Object cr() { return f.createVariableDeclaration(); } } );
        c.put("IntegerLiteralExp",      new Ctor() { public Object cr() { return f.createIntegerLiteralExp(); } } );
        c.put("BooleanLiteralExp",      new Ctor() { public Object cr() { return f.createBooleanLiteralExp(); } } );
        c.put("StringLiteralExp",       new Ctor() { public Object cr() { return f.createStringLiteralExp(); } } );
        c.put("RealLiteralExp",         new Ctor() { public Object cr() { return f.createRealLiteralExp(); } } );
        c.put("List",                   new Ctor() { public Object cr() { return new java.util.LinkedList(); } } ); 

        c.put("OclFormalParameter",     new Ctor() { public Object cr() { return new OclFormalParameter(); } } );        
        c.put("OclSignalSpec",              new Ctor() { public Object cr() { return new OclSignalSpec(); } } );
        c.put("OclBinaryExpTail",           new Ctor() { public Object cr() { return new OclBinaryExpTail(); } } );
        c.put("OclOperationSignature",      new Ctor() { public Object cr() { return new OclOperationSignature(); } } );
        c.put("OclOperationConstraint",     new Ctor() { public Object cr() { return new OclOperationConstraint(); } } );
        c.put("OclOperationContextDecl",    new Ctor() { public Object cr() { return new OclOperationContextDecl(); } } );
        c.put("OclAttributeDefinedEntityDecl",  new Ctor() { public Object cr() { return new OclAttributeDefinedEntityDecl(); } } );
        c.put("OclOperationDefinedEntityDecl",  new Ctor() { public Object cr() { return new OclOperationDefinedEntityDecl(); } } );
        c.put("OclClassifierContextDecl",           new Ctor() { public Object cr() { return new OclClassifierContextDecl(); } } );
        c.put("OclDefinitionClassifierConstraint",  new Ctor() { public Object cr() { return new OclDefinitionClassifierConstraint(); } } );
        c.put("OclInvariantClassifierConstraint",   new Ctor() { public Object cr() { return new OclInvariantClassifierConstraint(); } } );
        c.put("OclDefinitionConstraint",    new Ctor() { public Object cr() { return new OclDefinitionConstraint(); } } );
        c.put("OclPackagedConstraintList",  new Ctor() { public Object cr() { return new OclPackagedConstraintList(); } } );
        c.put("OclAttrOrAssocContextDecl",  new Ctor() { public Object cr() { return new OclAttrOrAssocContextDecl(); } } );
        c.put("OclInitConstraint",          new Ctor() { public Object cr() { return new OclInitConstraint(); } } );
        c.put("OclDeriveConstraint",        new Ctor() { public Object cr() { return new OclDeriveConstraint(); } } );
        c.put("OclActualParameterListItem", new Ctor() { public Object cr() { return new OclActualParameterListItem(); } } );
        
        c.put("VariableExp",                new Ctor() { public Object cr() { return f.createVariableExp(); } } );
        c.put("AttributeCallExp",           new Ctor() { public Object cr() { return f.createAttributeCallExp(); } } );
        c.put("AssociationEndCallExp",      new Ctor() { public Object cr() { return f.createAssociationEndCallExp(); } } );
        c.put("AssociationClassCallExp",    new Ctor() { public Object cr() { return f.createAssociationClassCallExp(); } } );
        c.put("EnumLiteralExp",             new Ctor() { public Object cr() { return f.createEnumLiteralExp(); } } );
        c.put("IteratorExp",                new Ctor() { public Object cr() { return f.createIteratorExp(); } } );
        // c.put("EnumLiteralExp",             new Ctor() { public Object cr() { return f.createEnumLiteralExp(); } } );
    }

    public Object createNode(String typeName) {        
        Ctor cr = (Ctor) creatorsByType.get(typeName);
        if ( cr == null ) {
            throw new RuntimeException("No creator for type '" + typeName + "' in abstract factory " + this.getClass().getName());
        }
        return cr.cr();
    }    
}
