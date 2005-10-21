/*
 * Heritage.java
 *
 * Created on 22. November 2004, 10:58
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

import tudresden.ocl20.core.jmi.ocl.expressions.OclExpression;
import tudresden.ocl20.core.jmi.ocl.expressions.VariableDeclaration;
import tudresden.ocl20.core.jmi.ocl.commonmodel.ModelElement;
import tudresden.ocl20.core.jmi.ocl.commonmodel.Classifier;
import tudresden.ocl20.core.jmi.ocl.commonmodel.Package;


import java.util.List;

/**
 *
 * Data container for inherited attributes passed around in the abstract syntax
 * tree under construciton.
 *
 * @author Ansgar Konermann
 * @version
 */
public class Heritage extends Object implements Cloneable {
    
    /** 
     * Environment of the OclExpression for which attribute evaluation is
     * being performed.
     */
    private Environment env;
    
    private Classifier contextualClassifier;
    private Package currentPackage;
    
    /**
     * Holds value of property atPreAllowed, default value is true.
     */
    private boolean atPreAllowed = true;
    
    /**
     * Holds value of property constrainedElement.
     */
    private ModelElement constrainedElement;
    
    /**
     * Holds value of property currentPackageName, which consists of a list of String
     * instances. Together they for the path name of the current package.
     */
    private List currentPackageName;
    
    /**
     * Indicates whether the syntactic form / part of concrete syntax tree currently
     * being under attribute evaluation is used in a context made up of a postfix
     * expression.
     */
    private boolean contextIsPropertyPrimary = false;
    
    /**
     * Indicates whether the syntactic form / part of concrete syntax tree currently
     * being under attribute evaluation is used in a context made up of a primary
     * expression.
     */
    private boolean contextIsPropPostfixTail = false;
    
    /**
     * Holds value of property currentSourceExpression. Only valid in context
     * postfix_exp_cs with a tail (i. e. no simple chain rule).
     */
    private OclExpression currentSourceExpression;
    
    /**
     * Holds value of property currentLeftSibling.
     */
    private OclExpression currentLeftSibling;
    
    /**
     * Indicates whether the syntactic form (part of concrete syntax tree) currently
     * being under attribute evaluation is used in a context made up of an OCL operation
     * with type argument, such as oclAsType, oclIsKindOf, oclIsTypeOf.
     */
    private boolean contextIsIteratorVarDecl;
    
    /**
     * Holds value of property resultVariable. Used to store a result variable for
     * operation constraints. The result variable needs to be transported down the
     * tree external to the environment, since it's not clear whether it may be 
     * actually used until we know the stereotype name (pre, post or body) for the
     * operation constraint.
     * Null means: no result variable available.
     */
    private VariableDeclaration resultVariable;
    
    /**
     * Holds value of property contextIsOclOpWithTypeArg.
     */
    private boolean contextIsOclOpWithTypeArg;
    
    /** Creates new Heritage */
    public Heritage() {
        this.env = EnvironmentFactory.EMPTY_ENV(0);
    }
    
    public Heritage copy() {
        Heritage result = null;
        try {
            result = (Heritage) super.clone();
        } catch ( CloneNotSupportedException cnse ) {
            throw new RuntimeException("Class Heritage and all its superclasses" +
                " are required to support the clone() method");
        }
        // modify result if necessary (modify deep structures etc.)
        return result;
    }
    
    /**
     * Getter for property contextualClassifier.
     * @return Value of property contextualClassifier.
     */
    public Classifier getContextualClassifier() {
        return this.contextualClassifier;
    }
    
    /**
     * Setter for property contextualClassifier.
     * @param contextualClassifier New value of property contextualClassifier. 
     *        Null means not set/not available.
     */
    public void setContextualClassifier(Classifier contextualClassifier) {
        this.contextualClassifier = contextualClassifier;
    }
    
    /**
     * Getter for property currentPackage.
     * @return Value of property currentPackage.
     */
    public Package getCurrentPackage() {
        return this.currentPackage;
    }
    
    /**
     * Setter for property currentPackage.
     * @param currentPackage New value of property currentPackage. Null means
     *        not set/not available.
     */
    public void setCurrentPackage(Package currentPackage) {
        this.currentPackage = currentPackage;
    }
    
    /**
     * Getter for property atPreAllowed.
     * @return Value of property atPreAllowed.
     */
    public boolean isAtPreAllowed() {
        return this.atPreAllowed;
    }
    
    /**
     * Setter for property atPreAllowed.
     * @param atPreAllowed New value of property atPreAllowed.
     */
    public void setAtPreAllowed(boolean atPreAllowed) {
        this.atPreAllowed = atPreAllowed;
    }
    
    /**
     * Getter for property env.
     * @return Value of property env.
     */
    public Environment getEnv() {
        return this.env;
    }
    
    /**
     * Setter for property env.
     * @param env New value of property env.
     */
    public void setEnv(Environment env) {
        this.env = env;
    }
    
    /**
     * Getter for property constrainedElement.
     * @return Value of property constrainedElement.
     */
    public ModelElement getConstrainedElement() {
        return this.constrainedElement;
    }
    
    /**
     * Setter for property constrainedElement.
     * @param constrainedElement New value of property constrainedElement.
     */
    public void setConstrainedElement(ModelElement constrainedElement) {
        this.constrainedElement = constrainedElement;
    }
    
    /**
     * Getter for property currentPackageName.
     * @return Value of property currentPackageName.
     */
    public List getCurrentPackageName() {
        return this.currentPackageName;
    }
    
    /**
     * Setter for property currentPackageName.
     * @param currentPackageName New value of property currentPackageName.
     */
    public void setCurrentPackageName(List currentPackageName) {
        this.currentPackageName = currentPackageName;
    }
    
    /**
     * Getter for property isInsidePostfixExp.
     * @return Value of property isInsidePostfixExp.
     */
    public boolean isContextIsPropertyPrimary() {
        return this.contextIsPropertyPrimary;
    }
    
    /**
     * Setter for property isInsidePostfixExp.
     * @param isInsidePostfixExp New value of property isInsidePostfixExp.
     */
    public void setContextIsPropertyPrimary(boolean contextIsPropertyPrimary) {
        this.contextIsPropertyPrimary = contextIsPropertyPrimary;
    }
    
    /**
     * Getter for property insidePrimaryExp.
     * @return Value of property insidePrimaryExp.
     */
    public boolean isContextIsPropPostfixTail() {
        return this.contextIsPropPostfixTail;
    }
    
    /**
     * Setter for property insidePrimaryExp.
     * @param insidePrimaryExp New value of property insidePrimaryExp.
     */
    public void setContextIsPropPostfixTail(boolean contextIsPropPostfixTail) {
        this.contextIsPropPostfixTail = contextIsPropPostfixTail;
    }
    
    /**
     * Getter for property currentSourceExpression.
     * @return Value of property currentSourceExpression.
     */
    public OclExpression getCurrentSourceExpression() {
        return this.currentSourceExpression;
    }
    
    /**
     * Setter for property currentSourceExpression.
     * @param currentSourceExpression New value of property currentSourceExpression.
     */
    public void setCurrentSourceExpression(OclExpression currentSourceExpression) {
        this.currentSourceExpression = currentSourceExpression;
    }
    
    /**
     * Getter for property currentLeftSibling.
     * @return Value of property currentLeftSibling.
     */
    public OclExpression getCurrentLeftSibling() {
        return this.currentLeftSibling;
    }
    
    /**
     * Setter for property currentLeftSibling.
     * @param currentLeftSibling New value of property currentLeftSibling.
     */
    public void setCurrentLeftSibling(OclExpression currentLeftSibling) {
        this.currentLeftSibling = currentLeftSibling;
    }
    
    /**
     * Getter for property contextIsIteratorVarDecl.
     * @return Value of property contextIsIteratorVarDecl.
     */
    public boolean isContextIsIteratorVarDecl() {
        return this.contextIsIteratorVarDecl;
    }
    
    /**
     * Setter for property contextIsIteratorVarDecl.
     * @param contextIsIteratorVarDecl New value of property contextIsIteratorVarDecl.
     */
    public void setContextIsIteratorVarDecl(boolean contextIsIteratorVarDecl) {
        this.contextIsIteratorVarDecl = contextIsIteratorVarDecl;
    }
    
    /**
     * Getter for property resultVariable.
     * @return Value of property resultVariable.
     */
    public VariableDeclaration getResultVariable() {
        return this.resultVariable;
    }
    
    /**
     * Setter for property resultVariable.
     * @param resultVariable New value of property resultVariable.
     */
    public void setResultVariable(VariableDeclaration resultVariable) {
        this.resultVariable = resultVariable;
    }
    
    /**
     * Getter for property contextIsOclOpWithTypeArg.
     * @return Value of property contextIsOclOpWithTypeArg.
     */
    public boolean isContextIsOclOpWithTypeArg() {
        return this.contextIsOclOpWithTypeArg;
    }
    
    /**
     * Setter for property contextIsOclOpWithTypeArg.
     * @param contextIsOclOpWithTypeArg New value of property contextIsOclOpWithTypeArg.
     */
    public void setContextIsOclOpWithTypeArg(boolean contextIsOclOpWithTypeArg) {
        this.contextIsOclOpWithTypeArg = contextIsOclOpWithTypeArg;
    }
    
}
