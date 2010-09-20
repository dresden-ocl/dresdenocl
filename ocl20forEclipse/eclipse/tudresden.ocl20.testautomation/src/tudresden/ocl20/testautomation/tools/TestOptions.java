package tudresden.ocl20.testautomation.tools;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * There is a pirority of complement properties if they're both in an options
 * set (which is a programming error in general, actually):
 * 
 * interpret (fail) > parseonly success (default) > fail
 * 
 * @author franz
 * 
 */
public class TestOptions {

	private static Logger log = Logger.getLogger(TestOptions.class);

	// default: parse and interpret
	private boolean parseOnly = false;

	// default: don't fail
	private boolean fail = false;

	private static Pattern basicOptionsPattern;

	static {
		basicOptionsPattern =
				Pattern.compile(".*?@(?:O|o)ptions\\s*\\{([\\w\\s,]*)\\}");
	}

	public TestOptions() {

	}

	public void parse(List<String> optionLines) {


		for (String line : optionLines) {

			Matcher boMatcher = basicOptionsPattern.matcher(line);

			if (boMatcher.find()) {
				// get option's contents
				String optionString = boMatcher.group(1);
				this.evaluateOptionsList(optionString);
			}
		}


	}
	
	protected void evaluateOptionsList(String optionString)
	{String[] options = optionString.split("(\\s+|\\s*,\\s*)");
	for (int i = 0; i < options.length; ++i) {
		String option = options[i];
		if (option == null || option.isEmpty()) {
			continue;
		}

		if (option.equals("parseonly")) {
			this.parseOnly = true;
		}
		else if (option.equals("interpret")) {
			this.parseOnly = false;
		}
		else if (option.equals("fail")) {
			this.fail = true;
		}
		else if (option.equals("success")) {
			this.fail = false;
		}
		else {
			log.warn("unrecognized option " + option + " will be ignored.");
		}
	}
		
	}


	public boolean fail() {

		return this.fail;

	}

	public boolean parseOnly() {

		return this.parseOnly;
	}
	
	public TestOptions clone()
	{
		TestOptions clone = new TestOptions();
		
		clone.fail = this.fail;
		clone.parseOnly = this.parseOnly;

		return clone;
	}

}
