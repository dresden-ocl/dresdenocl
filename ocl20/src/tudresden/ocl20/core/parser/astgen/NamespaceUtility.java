/*
 * NamespaceFactory.java
 *
 * Created on 10. August 2004, 14:14
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

/**
 *
 * Library class providing utility methods to manage specialized namespace
 * objects for subclasses of UML or MOF namespaces. The code in this library
 * should be moved to specific implementations of astgen.Namespace when 
 * namespace handling is added to the tudresden.ocl20.core.jmi.ocl.commonmodel
 * package.
 *
 * @author Ansgar Konermann
 * @version
 */
public class NamespaceUtility {
    
    public static Namespace createNamespaceFor(Object o) {
        
        Namespace result = null;
        
        if ( o instanceof tudresden.ocl20.core.jmi.uml15.core.Classifier ) {
            return new UML15ClassifierNamespace((tudresden.ocl20.core.jmi.uml15.core.Classifier) o);
        }
        if ( o instanceof tudresden.ocl20.core.jmi.uml15.modelmanagement.Package ) {
            return new UML15PackageNamespace((tudresden.ocl20.core.jmi.uml15.modelmanagement.Package) o);
        }
        throw new RuntimeException("Namespace kind not implemented: " + o.getClass().getName());        
    }
    
    public static boolean isKindOfNamespace(Object o) {
        if ( o instanceof tudresden.ocl20.core.jmi.uml15.core.Classifier ) {
            return true;
        }
        if ( o instanceof tudresden.ocl20.core.jmi.uml15.modelmanagement.Package ) {
            return true;
        }
        // other namespace subclasses not supported yet, so we cannot handle them
        // as namespaces.
        return false;
    }

    
}
