/*
 * OclOperationConstraintStereotype.java
 *
 * Created on 27. Oktober 2004, 04:05
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

/**
 *
 * Java enumaration class representing different kinds of OCL operation
 * stereotypes, namely "pre", "post" and "body".
 *
 * @author Ansgar Konermann
 * @version
 */
public class OclOperationConstraintStereotype extends Object {
    
    private int code;
    private String name;
    
    /** Creates new OclOperationConstraintStereotype */
    private OclOperationConstraintStereotype(int code, String name) {
        this.code = code;
        this.name = name;
    }
    
    public String getName() { return this.name; }
    
    public static final OclOperationConstraintStereotype PRE  = new OclOperationConstraintStereotype(1, "pre");
    public static final OclOperationConstraintStereotype POST = new OclOperationConstraintStereotype(2, "post");
    public static final OclOperationConstraintStereotype BODY = new OclOperationConstraintStereotype(3, "body");    
}
