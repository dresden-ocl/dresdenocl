/*
 * DuplicateNameException.java
 *
 * Created on 22. November 2004, 15:00
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

/**
 * Exception thrown by WritableEnvironment implementations whenever a new
 * model element is inserted into an environment which already contains a model
 * element with a conflicting name.
 *
 * @author Ansgar Konermann
 */

public class DuplicateNameException extends java.lang.Exception {
    
    private String name;
    
    public String getName() { return name; }
    
    /**
     * Creates a new instance of <code>DuplicateNameException</code> without detail message.
     */
    public DuplicateNameException() { }
    
    /**
     * Creates a new instance of <code>DuplicateNameException</code> without detail message
     * but for a specific conflicting name.
     */
    public DuplicateNameException(String name) {
        this.name = name;
    }
    
    /**
     * Constructs an instance of <code>DuplicateNameException</code> with the specified detail 
     * message.
     * @param msg the detail message.
     */
    public DuplicateNameException(String name, String msg) {
        super(msg);
        this.name = name;
    }
}
