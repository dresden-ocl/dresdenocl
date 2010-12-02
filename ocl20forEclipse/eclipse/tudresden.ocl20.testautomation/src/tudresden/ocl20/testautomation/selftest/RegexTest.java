package tudresden.ocl20.testautomation.selftest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.TestCase;

public class RegexTest extends TestCase {

	public void testRegex1() {

		String pattern = "@options\\{(\\w+)?(?:\\s+(?:(\\w+)))*\\s*\\}";
		Pattern first = Pattern.compile(pattern);

		Matcher matcher = first.matcher("@options{hello asdf asef}");
		assertTrue(matcher.find());

		for(int i=1; i<= matcher.groupCount(); ++i){
			System.out.println(matcher.group(i));
		}


	}
}
