package org.dresdenocl.tools.transformation.pivot2sql.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import orgomg.cwm.objectmodel.core.ModelElement;


public class Pivot2CWMtwoSchemaTest extends Pivot2CWMtypedTest {

	
	@BeforeClass
	public static void setUp_class() {
		modus = MODUS_TYPED;
		schema = true;
	}
	
	public void testClass() {

		super.testClass();
		assertEquals("More than one class is generated", 1, catalog.getOwnedElement().size());
	}
	
	
	public void testMultipleSchema() {

		super.testMultipleSchema();	
		assertEquals("Not three schemas generated", 3, catalog.getOwnedElement().size());
		List<String> nameSchema = new ArrayList<String>();
		for (ModelElement me : catalog.getOwnedElement()) {
			nameSchema.add(me.getName());
		}
		List<String> expected = Arrays.asList("university_complex","test2","test");
		for (String s : expected) {
			assertTrue("Missing schema university_complex",nameSchema.contains(s));
		}
		
	}
}
