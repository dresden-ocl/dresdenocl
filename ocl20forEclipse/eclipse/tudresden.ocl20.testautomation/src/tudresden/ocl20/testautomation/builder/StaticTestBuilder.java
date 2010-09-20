package tudresden.ocl20.testautomation.builder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tudresden.ocl20.testautomation.builder.templates.TestTemplate;
import tudresden.ocl20.testautomation.exceptions.TestCreationException;
import tudresden.ocl20.testautomation.tools.JavaTemplateAnalyser;
import tudresden.ocl20.testautomation.tools.StatementFile;
import tudresden.ocl20.testautomation.tools.Statement;
import tudresden.ocl20.testautomation.tools.TestGroup;
import tudresden.ocl20.testautomation.tools.TestOptions;
import tudresden.ocl20.testautomation.tools.Statement.StereoTypes;

import cb.jdynamite.JDynamiTe;

public class StaticTestBuilder {

	private String outputDir;

	private static Class<?> templateClass = TestTemplate.class;

	public StaticTestBuilder() throws TestCreationException {

		// default!
		this("generated");
	}

	public StaticTestBuilder(String outputDir) throws TestCreationException {

		this.outputDir = outputDir;
		this.prepareOutputDirectory();
	}

	private void prepareOutputDirectory() throws TestCreationException {

		File outDir = new File(this.outputDir);
		if (outDir.exists() && outDir.isFile()) {
			throw new TestCreationException(
					"Output path must be a directory, but is an existing file!");
		}

		// TODO: logging if directory already existed etc.
		outDir.mkdirs();
	}

	@SuppressWarnings("unused")
	private void cleanOutputDirectory() {

		// TODO implement
	}

	public void generate(TestGroup unit) throws TestCreationException {

		for (StatementFile file : unit.getFileUnits()) {

			this.generate(unit, file);
		}
	}

	public void generate(List<TestGroup> units) throws TestCreationException {

		for (TestGroup unit : units) {
			this.generate(unit);
		}
	}

	private void generate(TestGroup unit, StatementFile file)
			throws TestCreationException {

		File testFilePath = this.createTestFilePath(unit, file);
		try {
			assert (testFilePath.exists() && testFilePath.isFile());

			PrintStream out = new PrintStream(testFilePath);

			JDynamiTe templateBuilder = this.createTemplateBuilder();

			new StatementTestBuilder(file, templateBuilder);

			// set global template variables (like package, classname etc.)
			this.setGlobalTemplateVariables(templateBuilder, unit, file);

			templateBuilder.parse();
			out.print(templateBuilder.toString());

		} catch (FileNotFoundException e) {
			throw new TestCreationException(e);
		}

	}

	private JDynamiTe createTemplateBuilder() throws TestCreationException {

		try {
			BufferedReader templateReader = this.openTemplateClass(templateClass);

			JDynamiTe builder = new JDynamiTe();
			// ITemplateAnalyser
			builder.setAnalyser(new JavaTemplateAnalyser());
			builder.setInput(templateReader);

			return builder;
		} catch (IOException e) {
			throw new TestCreationException(e);
		}
	}

	protected String createClassPackage(TestGroup unit, StatementFile file) {

		String unitName = unit.getName();
		String subdir = file.getTestSubDir().trim();

		String classPackage = unitName.toLowerCase();
		if (!subdir.isEmpty()) {
			classPackage += "." + subdir.replace("/", ".");
		}
		classPackage = classPackage.replace("..", ".");

		return classPackage;
	}

	protected File createTestFilePath(TestGroup unit, StatementFile file)
			throws TestCreationException {

		String unitName = unit.getName().toLowerCase();
		String subdir = file.getTestSubDir();
		String testName = "Test" + file.getName();

		File testFilePath =
				new File(this.outputDir + "/" + unitName + "/" + subdir + "/"
						+ testName + ".java");
		this.createSubdirsAndFile(testFilePath);
		return testFilePath;
	}

	protected void setGlobalTemplateVariables(JDynamiTe templateBuilder,
			TestGroup unit, StatementFile file) {

		String modelFile = unit.getModelFile();
		String modelInstanceFile = unit.getModelInstanceFile();
		String testName = "Test" + file.getName();
		String classPackage = this.createClassPackage(unit, file);
		String fileHeader = "/*\n" + file.getFileCommentsAsString() + "\n*/";
		String sourceFile = file.getSourceFile();

		templateBuilder.setDynElemValue("package", "package " + classPackage + ";");
		templateBuilder.setDynElemValue("classname", testName + " ");
		templateBuilder.setDynElemValue("constructor", testName + " ");

		templateBuilder.setVariable("modelFile", modelFile);
		templateBuilder.setVariable("modelInstanceFile", modelInstanceFile);
		templateBuilder.setVariable("testcaseName", testName);
		templateBuilder.setVariable("fileheader", fileHeader);
		templateBuilder.setVariable("sourcefile", sourceFile);

	}

	private BufferedReader openTemplateClass(Class<?> templateClass)
			throws TestCreationException {

		File templateFile =
				new File("src/" + templateClass.getCanonicalName().replace(".", "/")
						+ ".java");
		try {
			if (!templateFile.exists()) {
				throw new FileNotFoundException("Template file doesnt exist.");
			}

			BufferedReader templateReader =
					new BufferedReader(new FileReader(templateFile));

			return templateReader;
		} catch (FileNotFoundException e) {
			throw new TestCreationException(e);
		}
	}

	private void createSubdirsAndFile(File absolutePath)
			throws TestCreationException {

		try {
			File parentPath = new File(absolutePath.getParent());
			if (parentPath != null) {
				parentPath.mkdirs();
			}
			if (absolutePath.exists()) {
				absolutePath.delete();
			}
			if (!absolutePath.createNewFile()) {
				throw new TestCreationException("couldn't create file " + absolutePath);
			}
		} catch (IOException e) {
			throw new TestCreationException(e);
		} catch (TestCreationException e) {
			throw e;
		}
	}

	private static class StatementTestBuilder {

		private static Pattern nonIdentifier;
		private static String replacement;
		static {
			nonIdentifier = Pattern.compile("[^\\w]+");
			replacement = "_";
		}

		private StatementFile fileUnit;
		private JDynamiTe template;
		private int statementCounter;

		public StatementTestBuilder(StatementFile fileUnit, JDynamiTe template)
				throws TestCreationException {

			this.fileUnit = fileUnit;
			this.template = template;

			// we don't want statement names starting at 0
			this.statementCounter = 1;

			this.generateAllStatements();
		}

		public void generateAllStatements() throws TestCreationException {

			// create template specific tests for every statement
			for (Statement stmt : this.fileUnit.getStatements()) {

				// get the statement's name or generate one
				this.prepareStatementName(stmt);

				this.createStatementTest(stmt);
			}

		}

		protected void createStatementTest(Statement stmt)
				throws TestCreationException {

			switch (stmt.getStereoType()) {
			case INVARIANT:
				this.createInvariantTest(stmt);
				break;

			case DEFINITION:
				this.createDefinitionTest(stmt);
				break;

			default:
				throw new TestCreationException(
						"unknown stereotype when creating statement" + stmt.getName());
			}

		}

		private void createDefinitionTest(Statement stmt) {

			String convertedStmt =
					this.convertToMultilineJavaString(stmt.getStatement());
			String functionIdentifier =
					"def" + this.convertToJavaIdentifier(stmt.getName());
			this.template.setVariable("constraint", convertedStmt);
			this.template.setVariable("testName", functionIdentifier);
			this.template.parseDynElem("prepareParse");
			
			this.addSetupCall("setUpPrepareParse", functionIdentifier);
		}

		/**
		 * 
		 * @param string
		 * @param string2
		 */
		private void addSetupCall(String dynElement, String addDynValue) {

			this.template.setVariable("value", addDynValue);
			this.template.parseDynElem(dynElement);
		}

		private void createInvariantTest(Statement stmt) {

			TestOptions options = stmt.getOptions();
			String testMethod;

			if (options.parseOnly()) {
				if (options.fail()) {
					testMethod = "parseNegativeTest";
				}
				else {
					testMethod = "parseTest";
				}
			}
			else {
				if (options.fail()) {
					testMethod = "invNegativeTest";
				}
				else {
					testMethod = "invTest";
				}
			}
			String convertedStmt =
					this.convertToMultilineJavaString(stmt.getStatement());
			String functionIdentifier = this.convertToJavaIdentifier(stmt.getName());
			this.template.setVariable("constraint", convertedStmt);
			this.template.setVariable("testName", functionIdentifier);
			this.template.parseDynElem(testMethod);

		}

		/**
		 * Creates a new statement name if there was none (null or empty) If already
		 * there, make sure it's a proper Java-Identifier-Name (without ugly
		 * characters)
		 * 
		 * @param stmt
		 */
		private void prepareStatementName(Statement stmt) {

			String name = stmt.getName();
			if (name == null || name.isEmpty()) {
				name = this.fileUnit.getName() + '_' + this.statementCounter++;
			}

			stmt.setName(this.convertToJavaIdentifier(name));
		}

		/**
		 * converts a string containing newlines into single strings so they're
		 * parsable for the java compiler
		 * 
		 * e.g. the string """ hello world """
		 * 
		 * will be
		 * 
		 * """ hello\n"+ "world """
		 * 
		 * @param multiline
		 * @return
		 */
		protected String convertToMultilineJavaString(String multiline) {

			assert (multiline != null);
			multiline = multiline.trim();
			// convert windows newlines to single newline
			multiline = multiline.replace("\r\n", "\n");
			multiline = multiline.replace("\"", "\\\"");
			return multiline.replace("\n", "\\n\"+\n\t\t\"");
		}

		/**
		 * 
		 * @param value
		 * @return
		 */
		protected String convertToJavaIdentifier(String value) {

			assert (value != null);
			Matcher matcher = nonIdentifier.matcher(value);

			return matcher.replaceAll(replacement);

		}

	}
}
