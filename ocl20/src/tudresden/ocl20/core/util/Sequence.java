/*
 * Sequence.java
 *
 * Created on 6. Dezember 2004, 16:53
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

package tudresden.ocl20.util;

/**
 *
 * Generator for variable names with a defined name prefix. Names are created
 * by appending ascending, non-negative numbers to the prefix. Numbering starts
 * with 0.
 *
 * @author Ansgar Konermann
 * @version
 */
public class Sequence {
    
    private int currentValue = 0;
    private String namePrefix = "";
    
    /** 
     * Creates new Sequence with the given name prefix.
     */
    public Sequence(String prefix) {
        namePrefix = prefix;
    }
    
    /**
     * Returns current counter value and increases counter.
     */
    public int getNext() {
        return currentValue++;
    }
    
    /**
     * Returns the next name string and increases the internal counter.
     */
    public String getNextAsString() {
        int value = currentValue++;
        return namePrefix + "_" + value;
    }
}
