/*
 * ModelTreeBuilder.java
 *
 * Created on 20. Dezember 2004, 17:46
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

package tudresden.ocl20.parser.util;

import tudresden.ocl20.ModelManager;
import tudresden.ocl20.jmi.uml15.modelmanagement.Package;
import tudresden.ocl20.jmi.uml15.core.Association;
import tudresden.ocl20.jmi.uml15.core.AssociationEnd;
// import tudresden.ocl20.jmi.ocl.commonmodel.AssociationEnd;
import tudresden.ocl20.jmi.ocl.commonmodel.Classifier;
import tudresden.ocl20.jmi.ocl.commonmodel.Attribute;
import tudresden.ocl20.jmi.ocl.commonmodel.Operation;
import tudresden.ocl20.jmi.ocl.commonmodel.Parameter;
import tudresden.ocl20.jmi.ocl.commonmodel.ModelElement;

import javax.jmi.reflect.*;
import javax.swing.*;
import javax.swing.tree.*;
import java.util.*;

/**
 *
 * Creates a textual tree representation of a MOF model instance in the
 * repository suitable to be displayed by a Java Swing JTree component.
 *
 * @author Ansgar Konermann
 * @version
 */
public class TextualModelTreeBuilder {
    
    private ModelManager mm = null;
    
    /** Creates new ModelTreeBuilder */
    public TextualModelTreeBuilder(ModelManager mmgr) {
        this.mm = mmgr;
    }
    
    public DefaultMutableTreeNode caseTopPackage( tudresden.ocl20.jmi.ocl.commonmodel.Package topPackage ) {
        return caseGenericPackage(topPackage, "[TOP-PKG]");
    }
    
    public DefaultMutableTreeNode casePackage( tudresden.ocl20.jmi.ocl.commonmodel.Package pkg ) {
        return caseGenericPackage( pkg, "[PKG]" );
    }
        
    private DefaultMutableTreeNode caseGenericPackage( tudresden.ocl20.jmi.ocl.commonmodel.Package pkg, String prefix ) {
        String name = pkg.getNameA();
        DefaultMutableTreeNode dmtn = new DefaultMutableTreeNode(prefix + " " + name);
        if ( pkg instanceof Package ) {
            Package pk = (Package) pkg;
            Collection elements = pk.getOwnedElement();
            Iterator it = elements.iterator();
            while ( it.hasNext() ) {
                ModelElement me = (ModelElement) it.next();
                DefaultMutableTreeNode newNode = null;
                if ( me instanceof Association ) {
                    newNode = caseAssociation( (Association) me);
                } else  if ( me instanceof Classifier ) {
                    newNode = caseClassifier( (Classifier) me);
                } else if ( me instanceof Package ) {
                    newNode = casePackage( (Package) me);
                } else {
                    newNode = new DefaultMutableTreeNode("[???] " + me.getNameA() + " (" + me.getClass().getName() + ")");
                }
                if ( dmtn != null ) {
                    dmtn.add(newNode);
                }                
            }
        } else {
            throw new RuntimeException("Only UML models supported");
        }
        return dmtn;
    }
    
    protected DefaultMutableTreeNode caseClassifier( Classifier cls ) {
        String name = cls.getNameA();
        DefaultMutableTreeNode dmtn = new DefaultMutableTreeNode("[CLS] " + name);
        
        Iterator it = null;
        // add attributes
        List attrs = cls.allAttributes();
        it = attrs.iterator();
        while ( it.hasNext() ) {
            dmtn.add( caseAttribute( (Attribute) it.next() ));                        
        }
        
        // add operations
        List opers = cls.allOperations();
        it = opers.iterator();
        while ( it.hasNext() ) {
            dmtn.add( caseOperation( (Operation) it.next() ));
        }
        
        // add association ends?
        // add association classes?
        
        return dmtn;        
    }
    
    protected DefaultMutableTreeNode caseAssociation( Association assoc ) {
        String name = assoc.getNameA();
        DefaultMutableTreeNode dmtn = new DefaultMutableTreeNode("[ASSOC] " + name);
        
        List connections = assoc.getConnection();
        Iterator it = null;
        it = connections.iterator();
        while ( it.hasNext() ) {
            dmtn.add(caseAssociationEnd( (AssociationEnd) it.next() ));
        }
        return dmtn;
    }
    
    protected DefaultMutableTreeNode caseAssociationEnd(AssociationEnd assocend ) {
        String name = assocend.getNameA();
        String type = assocend.getTypeA().getNameA();
        DefaultMutableTreeNode dmtn = new DefaultMutableTreeNode("[ASSOC-END] " + name + " (" + type + ")");
        return dmtn;
    }
    
    protected DefaultMutableTreeNode caseAttribute( Attribute attr ) {
        String name = attr.getNameA();
        Classifier type = attr.getTypeA();
        DefaultMutableTreeNode dmtn = new DefaultMutableTreeNode("[ATTR]" + name + " (" + type.getNameA() + ")");
        return dmtn;
    }

    protected DefaultMutableTreeNode caseOperation( Operation oper ) {
        String name = oper.getNameA();
        Parameter paramRet = oper.getReturnParameterA();
        String paramRetName = paramRet.getNameA();
        String paramRetType = paramRet.getTypeA().getNameA();
        DefaultMutableTreeNode dmtn = new DefaultMutableTreeNode("[OPER]" + name + " (->" + paramRetName + ":" + paramRetType + ")");
        return dmtn;
    }
}
