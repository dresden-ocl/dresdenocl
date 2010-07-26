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

import tudresden.ocl20.testautomation.exceptions.TestCreationException;
import tudresden.ocl20.testautomation.tools.JavaTemplateAnalyser;
import tudresden.ocl20.testautomation.tools.StatementDefinition;
import tudresden.ocl20.testautomation.tools.StatementFileUnit;
import tudresden.ocl20.testautomation.tools.TestingUnit;

import cb.jdynamite.JDynamiTe;

abstract public class StaticTestBuilder {

	private String outputDir;

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

	public void generate(TestingUnit unit) throws TestCreationException {

		for (StatementFileUnit file : unit.getFileUnits()) {

			this.generate(unit, file);
		}
	}

	public void generate(List<TestingUnit> units) throws TestCreationException {

		for (TestingUnit unit : units) {
			this.generate(unit);
		}
	}

	private void generate(TestingUnit unit, StatementFileUnit file)
			throws TestCreationException {

		String modelFile = unit.getModelFile();
		String modelInstanceFile = unit.getModelInstanceFile();
		String testName = "Test" + file.getName();
		String classPackage = this.createClassPackage(unit, file);
		File testFilePath = this.createTestFilePath(unit, file);

		try {
			assert (testFilePath.exists() && testFilePath.isFile());

			PrintStream out = new PrintStream(testFilePath);

			JDynamiTe templateBuilder = this.createTemplateBuilder();

			// set global template variables (like package, classname etc.)
			this.setGlobalTemplateVariables(templateBuilder, classPackage, testName,
					modelFile, modelInstanceFile);

			// create template specific tests for every statement
			for (StatementDefinition stmt : file.getStatements()) {
				this.createStatementTest(templateBuilder, stmt);
			}

			templateBuilder.parse();
			out.print(templateBuilder.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private JDynamiTe createTemplateBuilder() throws TestCreationException {

		try {
			BufferedReader templateReader =
					this.openTemplateClass(this.getTemplateClass());

			JDynamiTe builder = new JDynamiTe();
			// ITemplateAnalyser
			builder.setAnalyser(new JavaTemplateAnalyser());
			builder.setInput(templateReader);

			return builder;
		} catch (IOException e) {
			throw new TestCreationException(e);
		}
	}

	protected String createClassPackage(TestingUnit unit, StatementFileUnit file) {

		String unitName = unit.getName();
		String subdir = file.getTestSubDir().trim();

		String classPackage = unitName.toLowerCase();
		if (!subdir.isEmpty()) {
			classPackage += "." + subdir.replace("/", ".");
		}
		classPackage = classPackage.replace("..", ".");

		return classPackage;
	}

	protected File createTestFilePath(TestingUnit unit, StatementFileUnit file)
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
			String classPackage, String testName, String modelFile,
			String modelInstanceFile) {

		templateBuilder.setDynElemValue("package", "package " + classPackage + ";");
		templateBuilder.setDynElemValue("classname", testName + " ");
		templateBuilder.setDynElemValue("constructor", testName + " ");

		templateBuilder.setVariable("modelFile", modelFile);
		templateBuilder.setVariable("modelInstanceFile", modelInstanceFile);

	}

	protected abstract Class<?> getTemplateClass();

	protected abstract void createStatementTest(JDynamiTe templateBuilder,
			StatementDefinition statement);

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

		multiline = multiline.trim();
		// convert windows newlines to single newline
		multiline.replace("\r\n", "\n");
		return multiline.replace("\n", "\\n\"+\n\t\t\"");
	}

	private static Pattern nonIdentifier;
	private static String replacement;
	static {
		nonIdentifier = Pattern.compile("[^\\w_]+");
		replacement = "_";
	}

	protected String convertToJavaIdentifier(String value) {

		Matcher matcher = nonIdentifier.matcher(value);

		return matcher.replaceAll(replacement);

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
}
