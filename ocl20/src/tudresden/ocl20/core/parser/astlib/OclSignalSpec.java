/*
 * OclSignalSpecExp.java
 *
 * Created on 20. Oktober 2004, 00:48
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

import java.util.List;

/**
 *
 * Temporary AST node for a message/signal specification containing a simple
 * name and the list of message parameters.
 *
 * @author Ansgar Konermann
 * @version
 */
public class OclSignalSpec extends Object {
    
    /**
     * Holds value of property parameters, contains instances of OclMessageArg.
     */
    private List parameters;
    
    /**
     * Holds value of property name.
     */
    private String name;
    
    /** Creates new OclSignalSpecExp */
    public OclSignalSpec() {
    }
    
    /**
     * Getter for property parameters, which contains instances of OclMessageArg.
     * @return Value of property parameters.
     */
    public List getParameters() {
        return this.parameters;
    }
    
    /**
     * Setter for property parameters, which contains instances of OclMessageArg.
     * @param parameters New value of property parameters.
     */
    public void setParameters(List parameters) {
        this.parameters = parameters;
    }
    
    /**
     * Getter for property name.
     * @return Value of property name.
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Setter for property name.
     * @param name New value of property name.
     */
    public void setName(String name) {
        this.name = name;
    }
    
}
