package tudresden.ocl20.testautomation.selftest.mocks;

import java.util.regex.Matcher;

import tudresden.ocl20.testautomation.tools.Statement;
import tudresden.ocl20.testautomation.tools.StatementFileParser;

public class StatementDefinitionMock extends
		Statement {

	public static Matcher matchGlobal(String probe) {

		return globalPattern.matcher(probe);
	}

	public static Matcher matchContext(String probe) {

		return contextPattern.matcher(probe);
	}
}
