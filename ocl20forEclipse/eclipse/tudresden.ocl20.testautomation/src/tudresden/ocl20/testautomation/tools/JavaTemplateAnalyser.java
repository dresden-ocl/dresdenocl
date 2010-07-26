package tudresden.ocl20.testautomation.tools;

import cb.jdynamite.analyser.DefaultAnalyser;
import cb.jdynamite.analyser.ITemplateAnalyser;

public class JavaTemplateAnalyser extends DefaultAnalyser implements
		ITemplateAnalyser {

	private static final String JAVA_VARIABLE_TAG;

	private static final String JAVA_BEGIN_DYNAMIC_TAG;
	private static final String JAVA_END_DYNAMIC_TAG;

	static {
		String regexStart = "\\/\\*\\s*\\{\\s*";
		String regexEnd = "\\s*\\}\\s*\\*\\/";
		String identifier = "\\s*([\\w]+)\\s*";

		JAVA_VARIABLE_TAG = regexStart + identifier + regexEnd;
		JAVA_BEGIN_DYNAMIC_TAG =
				regexStart + "dynbegin\\s*:" + identifier + regexEnd;
		JAVA_END_DYNAMIC_TAG = regexStart += "dynend\\s*:" + identifier + regexEnd;

	}

	public JavaTemplateAnalyser() {

		super();

		// actually we would have to call these methods
		// this.setVariableRegExp(VARIABLE_TAG);
		// but they're overwritten when invoking analyze. --> probably a bug.

		// so we assign the values directly
		DefaultAnalyser.HTML_VARIABLE_TAG = JAVA_VARIABLE_TAG;
		DefaultAnalyser.HTML_BEGIN_DYNAMIC_TAG = JAVA_BEGIN_DYNAMIC_TAG;
		DefaultAnalyser.HTML_END_DYNAMIC_TAG = JAVA_END_DYNAMIC_TAG;
	}
}
