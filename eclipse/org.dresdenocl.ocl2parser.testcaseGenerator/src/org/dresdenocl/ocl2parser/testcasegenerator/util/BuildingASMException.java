/*
    Copyright (C) 2008  Nils (s0006383@inf.tu-dresden.de)

    This file is part of the testcase generator for OCL parser of the Dresden OCL2 for Eclipse.

    The testcase generator is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    The testcase generator is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with the testcase generator.  If not, see <http://www.gnu.org/licenses/>.
.
*/
package org.dresdenocl.ocl2parser.testcasegenerator.util;

import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.ITokenAS;

public class BuildingASMException extends Exception {
	private ITokenAS errorToken;
	
	public BuildingASMException(String message, ITokenAS token) {
		super(message);
		errorToken = token;
		
	}
	
	public BuildingASMException(String message) {
		super(message);
	}
	
	public BuildingASMException(String message, ITokenAS token, Throwable cause) {
		super(message, cause);
		errorToken = token;
	}
	
	public BuildingASMException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public String getMessage() {
		String msg = super.getMessage();
		if (errorToken != null) {
			msg = msg + " The error occured at line " + errorToken.getLine()
			+ " and column " + errorToken.getColumn() + " at the token " + errorToken.getValue() + ".";
			errorToken = errorToken;
		}
		
		return msg;
	}
	
	public String toString() {
		return getMessage();
	}
}
