/*
 * ORMappinGScheme.java
 * 
 * Copyright (c) 2001 Sten Loecher
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

import tudresden.ocl20.codegen.sql.codegen.MappedClass;

/**
 *  This interface allows to adapt various object relational mapping
 *  schemes to the  SQL code generator.
 *
 *  @author Sten Loecher
 *  @deprecated See tudresden.ocl20.codegen.decl.tools.sql
 */
public interface ORMappingScheme {

	/**
	 *  @param name the name of an application type
	 *  @return a MappedObject containing all necessary information to generate SQL code
	 */
	public MappedClass getMappedClass(String name);
}