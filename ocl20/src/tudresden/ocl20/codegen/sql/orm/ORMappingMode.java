/*
 * ORMappingMode.java
 * 
 * Copyright (c) 2005 Florian Heidenreich
 * Contact: <mail@fheidenreich.de>
 *
 * This file is part of the Dresden OCL2.0 Toolkit
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

package tudresden.ocl20.codegen.sql.orm;

/**
 * This enum encapsulates the possible object-relational mapping options
 * 
 * @author Florian Heidenreich
 * @deprecated See tudresden.ocl20.codegen.decl.tools.sql
 *
 */
public enum ORMappingMode {
	
	/**
	 * Use this option to use one table per class (which is the default)
	 */
	oneTablePerClass,
	
	/**
	 * Use this option to use one table per leaf class
	 */
	oneTablePerLeafClass,
	
	/**
	 * Use this option to use one table for an entire class hierarchy
	 */
	oneTablePerHierarchy
}
