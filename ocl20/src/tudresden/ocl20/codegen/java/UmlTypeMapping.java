/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * OCL Compiler                                                      *
 * Copyright (C) 2005, 2006 Ronny Brandt (Ronny_Brandt@gmx.de).      *
 * All rights reserved.                                              *
 *                                                                   *
 * This work was done as a diploma project at the Chair for Software *
 * Technology, Dresden University Of Technology, Germany             *
 * (http://www-st.inf.tu-dresden.de).  It is understood that any     *
 * modification not identified as such is not covered by the         *
 * preceding statement.                                              *
 *                                                                   *
 * This work is free software; you can redistribute it and/or        *
 * modify it under the terms of the GNU Library General Public       *
 * License as published by the Free Software Foundation; either      *
 * version 2 of the License, or (at your option) any later version.  *
 *                                                                   *
 * This work is distributed in the hope that it will be useful,      *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of    *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU *
 * Library General Public License for more details.                  *
 *                                                                   *
 * You should have received a copy of the GNU Library General Public *
 * License along with this library; if not, write to the             *
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,      *
 * Boston, MA  02111-1307, USA.                                      *
 *                                                                   *
 * To submit a bug report, send a comment, or get the latest news on *
 * this project and other projects, please visit the web site:       *
 * http://www-st.inf.tu-dresden.de/ (Chair home page) or             *
 * http://www-st.inf.tu-dresden.de/ocl/ (project home page)          *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package tudresden.ocl20.codegen.java;

import tudresden.ocl20.core.jmi.uml15.core.*;
import tudresden.ocl20.core.lib.UmlType;

public class UmlTypeMapping {
	
	CodeGenerator cg;
	
	public UmlTypeMapping(CodeGenerator cg) {
		this.cg = cg;
	}
	
	private String getPackagePrefix() {
		return cg.getPkgPrefix();
	}
	
	public String getNonOclType(tudresden.ocl20.core.jmi.ocl.commonmodel.Parameter parameter) {
		if (!(parameter instanceof Parameter)) {
			throw new RuntimeException("UmlTypeMapping can only be applied to UML parameters!");
		}
		
		StringBuffer result = new StringBuffer();
		appendNonOclType((Parameter)parameter, result);
		return result.toString();
	}
	
	private void appendNonOclType(Parameter parameter, StringBuffer result) {
		appendNonOclType(parameter.getType(), result);
	}
	
	//TODO: Check for different DataTypes (Collection, ...)
	private void appendNonOclType(Classifier type, StringBuffer result) {
		if (type instanceof DataType) {
			String name = type.getName();
			result.append(getPackagePrefix());
			result.append("UmlType.");
			result.append(name.toUpperCase());
			//result.append("Parametertyp: "+((DataType)type).getClass()+" name: "+type.getNameA());
		}
		else if (type instanceof UmlClass) {
			result.append(getPackagePrefix());
			result.append("UmlType.");
			String name = type.getName();
			if (name.toUpperCase().equals("BOOLEAN") || 
					name.toUpperCase().equals("DOUBLE") || 
					name.toUpperCase().equals("FLOAT") || 
					name.toUpperCase().equals("INT") || 
					name.toUpperCase().equals("LONG") || 
					name.toUpperCase().equals("STRING"))
				result.append(name.toUpperCase());
			else
				result.append("MODELTYPE");
			//System.err.println("Parametertyp: "+((UmlClass)type).getName()+"\n");
			//result.append("Parametertyp: "+((UmlClass)type).getName()+"\n");
		}
		else
			throw new RuntimeException("UmlTypeMapping unknown classifier " + type);
	}

}
