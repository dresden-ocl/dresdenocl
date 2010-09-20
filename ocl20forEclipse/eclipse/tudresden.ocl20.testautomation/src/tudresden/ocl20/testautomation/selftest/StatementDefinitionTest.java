package tudresden.ocl20.testautomation.selftest;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;

import tudresden.ocl20.testautomation.selftest.mocks.StatementDefinitionMock;
import junit.framework.TestCase;

public class StatementDefinitionTest extends TestCase {

	public void testGlobalPattern1() {

		Matcher matcher =
				StatementDefinitionMock
						.matchGlobal("context model inv name: somedefinition...");
		assertTrue(matcher.find());

		assertEquals("model", matcher.group(1));
		assertEquals("inv", matcher.group(2));
		assertEquals("name", matcher.group(3));
	}

	public void testGlobalPattern2() {

		Matcher matcher =
				StatementDefinitionMock
						.matchGlobal("context \t   model\tinv name          : .:§!§$?()/invsomedefinition...");
		assertTrue(matcher.find());

		assertEquals("model", matcher.group(1));
		assertEquals("inv", matcher.group(2));
		assertEquals("name", matcher.group(3));
	}

	public void testGlobalPattern3() {

		Matcher matcher =
				StatementDefinitionMock
						.matchGlobal("context model:inv name: .:§!§$?()/invsomedefinition...");
		assertTrue(matcher.find());

		assertEquals("model", matcher.group(1));
		assertEquals("inv", matcher.group(2));
		assertEquals("name", matcher.group(3));
	}

	public void testGlobalPattern4() {

		Matcher matcher =
				StatementDefinitionMock
						.matchGlobal("context model inv: .:§!§$?()/invsomedefinition...");
		assertTrue(matcher.find());

		assertEquals("model", matcher.group(1));
		assertEquals("inv", matcher.group(2));
		assertNull(matcher.group(3));
	}

	public void testGlobalPattern5() {

		Matcher matcher =
				StatementDefinitionMock
						.matchGlobal("context package::model inv: >>> what follows here is not important. : funky stuff");
		assertTrue(matcher.find());

		assertEquals("package::model", matcher.group(1));
		assertEquals("inv", matcher.group(2));
		assertNull(matcher.group(3));
	}

	public void testGlobalPattern6() {

		Matcher matcher =
				StatementDefinitionMock
						.matchGlobal("context model::method(param1:type1 , param2:type2,param3:type3): inv:");
		assertTrue(matcher.find());

		assertEquals("model::method(param1:type1 , param2:type2,param3:type3)",
				matcher.group(1));
		assertEquals("inv", matcher.group(2));
		assertNull(matcher.group(3));
	}

	public void testGlobalPattern7() {

		Matcher matcher =
				StatementDefinitionMock
						.matchGlobal("context model::property : Integer : init:");
		assertTrue(matcher.find());

		assertEquals("model::property : Integer", matcher.group(1));
		assertEquals("init", matcher.group(2));
		assertNull(matcher.group(3));
	}

	public void testContextPattern1() {

		Matcher matcher = StatementDefinitionMock.matchContext("package::model");

		assertTrue(matcher.find());
		assertEquals(3, matcher.groupCount());

		assertEquals("package::model", matcher.group(1));
		assertEquals(null, matcher.group(2));
		assertEquals(null, matcher.group(3));
	}

	public void testContextPattern2() {

		Matcher matcher =
				StatementDefinitionMock
						.matchContext("package::model::property : propType");

		assertTrue(matcher.find());
		assertEquals(3, matcher.groupCount());

		assertEquals("package::model::property", matcher.group(1));
		assertEquals(null, matcher.group(2));
		assertEquals("propType", matcher.group(3));

	}

	public void testContextPattern3() {

		Matcher matcher =
				StatementDefinitionMock
						.matchContext("package::model::property(): propType");

		assertTrue(matcher.find());
		assertEquals(3, matcher.groupCount());

		assertEquals("package::model::property", matcher.group(1));
		assertEquals("", matcher.group(2));
		assertEquals("propType", matcher.group(3));

	}

	public void testContextPattern4() {

		Matcher matcher =
				StatementDefinitionMock
						.matchContext("package::model::property(param1:type1,param2:type2): propType");

		assertTrue(matcher.find());
		assertEquals(3, matcher.groupCount());

		assertEquals("package::model::property", matcher.group(1));
		assertEquals("param1:type1,param2:type2", matcher.group(2));
		assertEquals("propType", matcher.group(3));

	}

	public void testContextPattern5() {

		Matcher matcher =
				StatementDefinitionMock
						.matchContext("package::model::property(param1:type1,param2:type2)");

		assertTrue(matcher.find());
		assertEquals(3, matcher.groupCount());

		assertEquals("package::model::property", matcher.group(1));
		assertEquals("param1:type1,param2:type2", matcher.group(2));
		assertEquals(null, matcher.group(3));

	}

	public void testContextPattern6() {

		Matcher matcher =
				StatementDefinitionMock.matchContext("package::model::property ()");

		assertTrue(matcher.find());
		assertEquals(3, matcher.groupCount());

		assertEquals("package::model::property", matcher.group(1));
		assertEquals("", matcher.group(2));
		assertEquals(null, matcher.group(3));

	}
}
