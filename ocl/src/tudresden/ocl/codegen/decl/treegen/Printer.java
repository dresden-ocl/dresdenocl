/*
Copyright (C) 2002  Sten Loecher

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

*/
package tudresden.ocl.codegen.decl.treegen;

import java.util.*;
import tudresden.ocl.codegen.decl.*;
import tudresden.ocl.codegen.decl.treegen.node.*;
import tudresden.ocl.codegen.decl.treegen.analysis.*;

/**
	The base class for all printers. A printer is a class that generates
	plain SQL code from a SQL tree. Actual printers must 
	inherit from this class. An example for such a printer
	implementation is Oracle8iPrinter.
	
	@see tudresden.ocl.codegen.decl.treegen.Oracle8iPrinter
	@author Sten Loecher
*/
public class Printer extends DepthFirstAdapter {
	
	protected StringBuffer code;
		
	public String getCode() {
		return code.toString();
	}
	
	public void inStart(Start node) {
        code = new StringBuffer();
    }	    
    	
}