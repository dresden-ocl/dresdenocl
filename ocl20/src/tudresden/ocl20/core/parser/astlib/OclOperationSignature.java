/*
 * OclOperationSignature.java
 *
 * Created on 27. Oktober 2004, 03:56
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

package tudresden.ocl20.core.parser.astlib;

import tudresden.ocl20.core.jmi.ocl.commonmodel.Classifier;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * AST node class representing an OCL operation signature comprising a list
 * of formal parameters and a return type specifier.
 *
 * @author Ansgar Konermann
 * @version
 */
public class OclOperationSignature extends Object {
    
    /**
     * Holds value of property formalParameters, which is a list of OclFormalParameter instances.
     */
    private List formalParameters;
    
    /**
     * Holds value of property returnType.
     */
    private Classifier returnType;
    
    /**
     * Holds value of property operationName
     */
    private String operationName;
    
    /** Creates new OclOperationSignature */
    public OclOperationSignature() {
    }
    
    /**
     * Getter for property formalParameters, which is a list of OclFormalParameter instances.
     * @return Value of property formalParameters.
     */
    public List getFormalParameters() {
        if ( this.formalParameters == null ) {
            this.formalParameters = new LinkedList();
        }
        return this.formalParameters;
    }
    
    /**
     * Convenience operation returning a list of all parameter types of this operation.
     * Returns a list of Classifier instances
     */
    public List getFormalParameterTypes() {
        if ( this.formalParameters == null ) {
            return new ArrayList(0);
        }
        Iterator it = formalParameters.iterator();
        List result = new ArrayList(formalParameters.size());
        while ( it.hasNext() ) {
            OclFormalParameter param = (OclFormalParameter) it.next();
            Classifier type = param.getType();
            assert ( type != null ):
                "Type of formal parameter must not be null";
            result.add(type);
        }
        return result;
    }
    
    /**
     * Convenience operation returning a list of all parameter names of this
     * operation. Returns a list of String instances
     */
    public List getFormalParameterNames() {
        if ( this.formalParameters == null ) {
            return new ArrayList(0);
        }
        Iterator it = formalParameters.iterator();
        List result = new ArrayList(formalParameters.size());
        while ( it.hasNext() ) {
            OclFormalParameter param = (OclFormalParameter) it.next();
            String name = param.getName();
            assert ( name != null ):
                "Name of formal parameter must not be null";
            assert ( ! "".equals(name) ):
            	"Name of formal parameter must not be empty";
            result.add(name);
        }
        return result;
    }
    
    
    
    /**
     * Setter for property formalParameters, which is a list of OclFormalParameter instances.
     * @param formalParameters New value of property formalParameters.
     */
    public void setFormalParameters(List formalParameters) {
        this.formalParameters = formalParameters;
    }
    
    /**
     * Getter for property returnType.
     * @return Value of property returnType.
     */
    public Classifier getReturnType() {
        return this.returnType;
    }
    
    /**
     * Setter for property returnType.
     * @param returnType New value of property returnType.
     */
    public void setReturnType(Classifier returnType) {
        this.returnType = returnType;
    }
    
    /**
     * Getter for property operationName.
     * @return Value of property operationName
     */
    public String getOperationName() {
    	return this.operationName;
    }
    
    /**
     * Setter for property operationName.
     * @param operationName New value of property operationName.
     */
    public void setOperationName(String operationName) {
    	this.operationName = operationName;
    }
    
}
