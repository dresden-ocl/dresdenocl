package tudresden.ocl20.testautomation.builder;

import cb.jdynamite.JDynamiTe;
import tudresden.ocl20.testautomation.builder.templates.SimpleInvarianceTemplate;
import tudresden.ocl20.testautomation.exceptions.TestCreationException;
import tudresden.ocl20.testautomation.tools.StatementDefinition;

public class SimpleInvarianceBuilder extends StaticTestBuilder {

	public SimpleInvarianceBuilder() throws TestCreationException {

		super();

	}

	@Override
	protected Class<?> getTemplateClass() {

		return SimpleInvarianceTemplate.class;
	}

	@Override
	protected void createStatementTest(JDynamiTe templateBuilder,
			StatementDefinition stmt) {

		String convertedStmt =
				this.convertToMultilineJavaString(stmt.getStatement());
		String functionIdentifier = this.convertToJavaIdentifier(stmt.getName());
		templateBuilder.setVariable("constraint", convertedStmt);
		templateBuilder.setVariable("testName", functionIdentifier);
		templateBuilder.parseDynElem("invTest");

	}

}
