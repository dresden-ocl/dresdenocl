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
package tudresden.ocl20.pivot.ocl2parser.testcasegenerator.util;

import java.io.File;

public class FilenameContainer {
	private String elementName;
	private File file;
	
	public FilenameContainer(File file, String elementName) {
		this.elementName = elementName;
		this.file = file;
	}
	
	public String getElementName() {
		return elementName;
	}
	public void setElementName(String elementName) {
		this.elementName = elementName;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	
	public boolean equals(Object obj) {
		if (!(obj instanceof FilenameContainer)) return false;
		
		FilenameContainer con = (FilenameContainer) obj;
		
		if (con.getElementName().equals(elementName)) {
			if (con.getFile().equals(file)) return true;
		}
		
		return false;
	}
}
