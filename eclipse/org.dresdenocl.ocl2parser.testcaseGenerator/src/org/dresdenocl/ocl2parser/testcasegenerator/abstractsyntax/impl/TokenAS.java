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
package org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.impl;

import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.ITokenAS;

public class TokenAS implements ITokenAS {
	private String value;
	private int line;
	private int column;
	
	public TokenAS() {
		super();
	}
	
	public TokenAS(String value, int line, int column) {
		super();
		this.value = value;
		this.line = line;
		this.column = column;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	
	/**
	 * The method returns true if this and obj are equal.
	 * Two TokenAS instances are equal, if the line numbers,
	 * column numbers and values are equal. If the instances
	 * are not equal, false will be returned.
	 * 
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof TokenAS)) return false;
		
		TokenAS param = (TokenAS) obj;
		
		if (column != param.getColumn()) return false;
		if (line != param.getLine()) return false;
		if (!(value.equals(param.getValue()))) return false;
		
		return true;
	}
	
	
}
