 /*
Copyright (C) 2000  Sten Loecher

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

To submit a bug report, send a comment, or get the latest news on
this project, please see the contactReadme.txt file in this package.
*/ 
package tudresden.ocl.test.sql;

import junit.framework.*;
import tudresden.ocl.sql.*;
import tudresden.ocl.check.types.xmifacade.*;
import org.xml.sax.SAXException;
import java.io.*;

public class TestORMappingSchemeImp extends TestCase {
	
	public TestORMappingSchemeImp(String n) {
		super(n);
	}
	
	public void testInitialCase()
	throws SAXException, IOException {
		String url = TestORMappingSchemeImp.class.getResource("royloy.xmi").toString();
		ORMapping orm = new ORMappingImp(XmiParser.createRoughModel(url, url));
		ORMappingSchemeImp ormsi = new ORMappingSchemeImp(orm);		
	}
	
	public static Test suit() {
		TestSuite t=new TestSuite();
    		
    		t.addTest(new TestTable("testInitialCase"));
	  	
    		return t;
	}
}
