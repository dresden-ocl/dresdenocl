/*
 * CmpModelElementByName.java
 *
 * Created on 6. Dezember 2004, 15:10
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

import tudresden.ocl20.core.jmi.ocl.commonmodel.ModelElement;
import java.util.Comparator;

/**
 *
 * Comparator which compares two model elements by their name.
 *
 * @author Ansgar W. Konermann
 * @version
 */
public class CmpModelElementByName implements Comparator {
    
    /** Creates new CmpModelElementByName */
    public CmpModelElementByName() {
    }
    
    public int compare(Object o1, Object o2) {
        ModelElement me1 = (ModelElement) o1;
        ModelElement me2 = (ModelElement) o2;
        String name1 = me1.getNameA();
        String name2 = me2.getNameA();
        return name1.compareTo(name2);
    }
}
