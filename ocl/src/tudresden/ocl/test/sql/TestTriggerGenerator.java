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

*/
package tudresden.ocl.test.sql;

import junit.framework.*;
import tudresden.ocl.sql.*;

public class TestTriggerGenerator extends TestCase {
    
        TriggerGenerator triGen;
        SQLBuilder theSQLBuilder;
    
        protected void setUp() {
            theSQLBuilder = new OracleSQLBuilder();
            triGen = new TriggerGenerator(theSQLBuilder);
        }
           	
        public TestTriggerGenerator(String n) {
		super(n);
	}
        
        public void testAssertionReplacement() {
            String it[] = {"PERSON", "STUDENT", "EMPLOYEE"};
            String tmp[] = triGen.getAssertionReplacement("tudocl_", "Integrity error !", "OV_PERSON", it);   
            
            assertTrue(tmp.length == 3);
  
            theSQLBuilder.reset();
            theSQLBuilder.createAssertionReplacement("tudocl_OV_PERSON_on_PERSON", "PERSON", "OV_PERSON", "Integrity error !");
            assertTrue(theSQLBuilder.getCode().equals(tmp[0]));
            
            theSQLBuilder.reset();
            theSQLBuilder.createAssertionReplacement("tudocl_OV_PERSON_on_STUDENT", "STUDENT", "OV_PERSON", "Integrity error !");
            assertTrue(theSQLBuilder.getCode().equals(tmp[1]));
            
            theSQLBuilder.reset();
            theSQLBuilder.createAssertionReplacement("tudocl_OV_PERSON_on_EMPLOYEE", "EMPLOYEE", "OV_PERSON", "Integrity error !");
            assertTrue(theSQLBuilder.getCode().equals(tmp[2]));
        }
        
        public void testECATriggerTemplate() {
            String it[] = {"PERSON", "STUDENT", "EMPLOYEE"};
            String tmp[] = triGen.getECATriggerTemplate("tudocl_", "OV_PERSON", it);   
            
            assertTrue(tmp.length == 3);
  
            theSQLBuilder.reset();
            theSQLBuilder.createECATriggerTemplate("tudocl_OV_PERSON_on_PERSON", "PERSON", "OV_PERSON");
            assertTrue(theSQLBuilder.getCode().equals(tmp[0]));
            
            theSQLBuilder.reset();
            theSQLBuilder.createECATriggerTemplate("tudocl_OV_PERSON_on_STUDENT", "STUDENT", "OV_PERSON");
            assertTrue(theSQLBuilder.getCode().equals(tmp[1]));
            
            theSQLBuilder.reset();
            theSQLBuilder.createECATriggerTemplate("tudocl_OV_PERSON_on_EMPLOYEE", "EMPLOYEE", "OV_PERSON");
            assertTrue(theSQLBuilder.getCode().equals(tmp[2]));
        }
        
        public static Test suite() {
		TestSuite t=new TestSuite();

    		t.addTest(new TestTriggerGenerator("testAssertionReplacement"));
    		t.addTest(new TestTriggerGenerator("testECATriggerTemplate"));

    		return t;
	}
}

