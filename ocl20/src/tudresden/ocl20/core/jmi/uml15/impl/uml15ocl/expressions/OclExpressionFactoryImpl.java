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

package tudresden.ocl20.jmi.uml15.impl.uml15ocl.expressions;

import tudresden.ocl20.jmi.uml15.uml15ocl.expressions.*;
import tudresden.ocl20.jmi.uml15.uml15.Uml15Package;
import tudresden.ocl20.jmi.uml15.datatypes.VisibilityKindEnum;


import org.netbeans.mdr.handlers.InstanceHandler;
import org.netbeans.mdr.storagemodel.StorableObject;
/**
 *
 * @author  Administrator
 */
public abstract class OclExpressionFactoryImpl extends InstanceHandler implements OclExpressionFactory{
    
    /** Creates a new instance of OclExpressionFactoryImpl */
    protected OclExpressionFactoryImpl(StorableObject storable) {
        super(storable);
    }
    
    private ExpressionsPackage getExpPackage(){
        return (ExpressionsPackage) refImmediatePackage();
    }
    
    public tudresden.ocl20.jmi.ocl.expressions.VariableExp createVariableExp() {
        return getExpPackage().getVariableExp().createVariableExp();
    }
    
    public tudresden.ocl20.jmi.ocl.expressions.VariableDeclaration createVariableDeclaration() {
        return getExpPackage().getVariableDeclaration().createVariableDeclaration();
    }
    
    public tudresden.ocl20.jmi.ocl.expressions.UnspecifiedValueExp createUnspecifiedValueExp() {
        return getExpPackage().getUnspecifiedValueExp().createUnspecifiedValueExp();
    }
    
    public tudresden.ocl20.jmi.ocl.expressions.TupleLiteralExp createTupleLiteralExp() {
        return getExpPackage().getTupleLiteralExp().createTupleLiteralExp();
    }
    
    public tudresden.ocl20.jmi.ocl.expressions.StringLiteralExp createStringLiteralExp() {
        return getExpPackage().getStringLiteralExp().createStringLiteralExp();
    }
    
    public tudresden.ocl20.jmi.ocl.expressions.RealLiteralExp createRealLiteralExp() {
        return getExpPackage().getRealLiteralExp().createRealLiteralExp();
    }
    
    public tudresden.ocl20.jmi.ocl.expressions.OperationCallExp createOperationCallExp() {
        return getExpPackage().getOperationCallExp().createOperationCallExp();
    }
    
    public tudresden.ocl20.jmi.ocl.expressions.OclOperationWithTypeArgExp createOclOperationWithTypeArgExp() {
        return getExpPackage().getOclOperationWithTypeArgExp().createOclOperationWithTypeArgExp();
    }
    
    public tudresden.ocl20.jmi.ocl.expressions.OclMessageExp createOclMessageExp() {
        return getExpPackage().getOclMessageExp().createOclMessageExp();
    }
    
    public tudresden.ocl20.jmi.ocl.expressions.OclMessageArg createOclMessageArg() {
        return getExpPackage().getOclMessageArg().createOclMessageArg();
    }
    
    public tudresden.ocl20.jmi.ocl.expressions.LetExp createLetExp() {
        return getExpPackage().getLetExp().createLetExp();
    }
    
    public tudresden.ocl20.jmi.ocl.expressions.IteratorExp createIteratorExp() {
        return getExpPackage().getIteratorExp().createIteratorExp();
    }
    
    public tudresden.ocl20.jmi.ocl.expressions.IterateExp createIterateExp() {
        return getExpPackage().getIterateExp().createIterateExp();
    }
    
    public tudresden.ocl20.jmi.ocl.expressions.IntegerLiteralExp createIntegerLiteralExp() {
        return getExpPackage().getIntegerLiteralExp().createIntegerLiteralExp();
    }
    
    public tudresden.ocl20.jmi.ocl.expressions.IfExp createIfExp() {
        return getExpPackage().getIfExp().createIfExp();
    }
    
    public tudresden.ocl20.jmi.ocl.expressions.EnumLiteralExp createEnumLiteralExp() {
        return getExpPackage().getEnumLiteralExp().createEnumLiteralExp();
    }
    
    public tudresden.ocl20.jmi.ocl.expressions.CollectionRange createCollectionRange() {
        return getExpPackage().getCollectionRange().createCollectionRange();
    }
    
    public tudresden.ocl20.jmi.ocl.expressions.CollectionLiteralExp createCollectionLiteralExp() {
        return getExpPackage().getCollectionLiteralExp().createCollectionLiteralExp();
    }
    
    public tudresden.ocl20.jmi.ocl.expressions.CollectionItem createCollectionItem() {
        return getExpPackage().getCollectionItem().createCollectionItem();
    }
    
    public tudresden.ocl20.jmi.ocl.expressions.BooleanLiteralExp createBooleanLiteralExp() {
        return getExpPackage().getBooleanLiteralExp().createBooleanLiteralExp();    
    }
    
    public tudresden.ocl20.jmi.ocl.expressions.AttributeCallExp createAttributeCallExp() {
        return getExpPackage().getAttributeCallExp().createAttributeCallExp();
    }
    
    public tudresden.ocl20.jmi.ocl.expressions.AssociationEndCallExp createAssociationEndCallExp() {
        return getExpPackage().getAssociationEndCallExp().createAssociationEndCallExp();
    }
    
    public tudresden.ocl20.jmi.ocl.expressions.AssociationClassCallExp createAssociationClassCallExp() {
        return getExpPackage().getAssociationClassCallExp().createAssociationClassCallExp();
    }
    
    public tudresden.ocl20.jmi.ocl.expressions.ExpressionInOcl createExpressionInOcl() {
        return getExpPackage().getExpressionInOcl().createExpressionInOcl(); 
    }
    
    public tudresden.ocl20.jmi.ocl.commonmodel.Constraint createConstraint() {
        return ((Uml15Package)this.refOutermostPackage()).getCore().getConstraint().createConstraint("", VisibilityKindEnum.VK_PUBLIC, false, null);
    }
}
