/*
 * OclMessageOperator.java
 *
 * Created on 10. Dezember 2004, 09:55
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

/**
 *
 * Enumeration type for the two message operators "^" and "^^".
 *
 * @author Ansgar Konermann
 * @version
 */
public class OclMessageOperator extends Object {
    
    public static final OclMessageOperator SINGLE_CARET = new OclMessageOperator(1);
    public static final OclMessageOperator DOUBLE_CARET = new OclMessageOperator(2);
    
    private int code;
    
    /** Creates new OclMessageOperator */
    private OclMessageOperator(int code) {
        this.code = code;
    }
    
    public int getCode() { return this.code; }
    
    public boolean equals(Object o) {
        OclMessageOperator op = (OclMessageOperator) o;
        return op.code == this.code;
    }
    
}
