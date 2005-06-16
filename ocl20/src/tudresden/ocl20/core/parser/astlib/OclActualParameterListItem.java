/*
 * OclActualParameterListItem.java
 *
 * Created on 10. Dezember 2004, 19:30
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

package tudresden.ocl20.parser.astlib;

import tudresden.ocl20.jmi.ocl.commonmodel.Classifier;
import tudresden.ocl20.jmi.ocl.expressions.OclExpression;
import java.util.List;

/**
 *
 * Intermediate object representing an actual parameter list item. Since
 * actual parameter lists are also used for iterator variable declarations,
 * this class faciliates handling the different uses of syntactically
 * recognized list items.
 *
 * @author Ansgar Konermann
 * @version
 */
public class OclActualParameterListItem extends Object {
    
    /**
     * Holds value of property simpleName.
     */
    private boolean simpleName;
    
    /**
     * Holds value of property fullExpression.
     */
    private boolean fullExpression;
    
    /**
     * Holds value of property formalParameter.
     */
    private boolean formalParameter;
    
    /**
     * Holds value of property typeSpecifier.
     */
    private boolean typeSpecifier;
    
    
    /** Creates new OclActualParameterListItem */
    public OclActualParameterListItem() {
    }
    
    /**
     * Getter for property isSimpleName.
     * @return Value of property isSimpleName.
     */
    public boolean isSimpleName() {
        return this.simpleName;
    }
    
    /**
     * Setter for property isSimpleName.
     * @param isSimpleName New value of property isSimpleName.
     */
    public void setSimpleName(boolean simpleName) {
        this.simpleName = simpleName;
    }
    
    /**
     * Getter for property isFullExpression.
     * @return Value of property isFullExpression.
     */
    public boolean isFullExpression() {
        return this.fullExpression;
    }
    
    /**
     * Setter for property isFullExpression.
     * @param isFullExpression New value of property isFullExpression.
     */
    public void setFullExpression(boolean fullExpression) {
        this.fullExpression = fullExpression;
    }
    
    private OclExpression fullExpressionValue;
    public void setFullExpressionValue(OclExpression exp) {
        this.fullExpressionValue = exp;
        this.fullExpression = true;
        this.simpleName = false;
        this.formalParameter = false;
        this.typeSpecifier = false;
    }
    public OclExpression getFullExpressionValue() {
        return this.fullExpressionValue;
    }
    
    private String simpleNameValue;
    public void setSimpleNameValue(String name) {
        this.simpleNameValue = name;
        this.simpleName = true;
        this.fullExpression = false;
        this.formalParameter = false;
        this.typeSpecifier = false;
    }
    public String getSimpleNameValue() {
        return this.simpleNameValue;
    }
    
    private OclFormalParameter formalParameterValue;
    
    /**
     * Holds value of property typeSpecifierValue.
     */
    private Classifier typeSpecifierValue;
    
    public void setFormalParameterValue(OclFormalParameter param) {
        this.formalParameterValue = param;
        this.formalParameter = true;
        this.fullExpression = false;
        this.simpleName = false;
        this.typeSpecifier = false;
    }
    public OclFormalParameter getFormalParameterValue() {
        return this.formalParameterValue;
    }
    
    /**
     * Getter for property isFormalParameter.
     * @return Value of property isFormalParameter.
     */
    public boolean isFormalParameter() {
        return this.formalParameter;
    }
    
    /**
     * Setter for property isFormalParameter.
     * @param isFormalParameter New value of property isFormalParameter.
     */
    public void setFormalParameter(boolean formalParameter) {
        this.formalParameter = formalParameter;
    }
    
    /**
     * Getter for property typeName.
     * @return Value of property typeName.
     */
    public boolean isTypeSpecifier() {
        return this.typeSpecifier;
    }
    
    /**
     * Setter for property typeName.
     * @param typeName New value of property typeName.
     */
    public void setTypeSpecifier(boolean typeSpecifier) {
        this.typeSpecifier = typeSpecifier;
    }
    
    /**
     * Getter for property typeNameValue.
     * @return Value of property typeNameValue.
     */
    public Classifier getTypeSpecifierValue() {
        return this.typeSpecifierValue;
    }
    
    /**
     * Setter for property typeNameValue.
     * @param typeNameValue New value of property typeNameValue.
     */
    public void setTypeSpecifierValue(Classifier typeNameValue) {
        this.typeSpecifierValue = typeNameValue;
        this.formalParameter = false;
        this.fullExpression = false;
        this.simpleName = false;
        this.typeSpecifier = true;
    }
    
}
