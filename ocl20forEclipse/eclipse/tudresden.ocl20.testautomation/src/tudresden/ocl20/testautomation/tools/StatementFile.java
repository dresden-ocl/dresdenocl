package tudresden.ocl20.testautomation.tools;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import tudresden.ocl20.testautomation.exceptions.TestFileParseException;

public class StatementFile {

	private static Logger log = Logger.getLogger(StatementFile.class);

	// package deklaration
	private String packageDekl;

	private String sourceFile;

	private List<Statement> statements;

	// subdirectory where the source file name was found
	private String testSubDir;

	// pure filename without extension
	private String name;

	private List<String> fileComments;

	private TestOptions fileOptions;

	public StatementFile() {

		this.fileComments = new LinkedList<String>();

		this.statements = new LinkedList<Statement>();

		this.fileOptions = new TestOptions();
	}

	public void addFileComment(String comment) {

		this.fileComments.add(comment);
	}

	public void setTestFilePath(String path) {

		this.testSubDir = PathHelper.getPathFromPath(path).toLowerCase();
		this.name =
				MiscHelper.capitalizeFirstLetter(PathHelper
						.getPureFileNameFromPath(path));
	}

	public String getTestSubDir() {

		return this.testSubDir;
	}

	public String getName() {

		return this.name;
	}

	public void parseHeadComments() {

		this.fileOptions.parse(this.fileComments);

	}

	public void addStatement(Statement statement) {

		try {
			statement.parse();
			this.statements.add(statement);

		} catch (TestFileParseException e) {
			log.error("Error parsing StatementFile " + this.sourceFile, e);
		}

	}

	public List<Statement> getStatements() {

		return this.statements;

	}

	public void setSourceFile(String fileName) {

		this.sourceFile = fileName;

	}

	public String getSourceFile() {

		return this.sourceFile;
	}

	public Statement createStatement() {

		// TODO Auto-generated method stub
		Statement stmt = new Statement(this.packageDekl, this.fileOptions.clone());

		return stmt;
	}

	public void setPackage(String packageDekl) {

		this.packageDekl = packageDekl;
	}

	public String getFileCommentsAsString() {

		StringBuilder builder = new StringBuilder();
		for (String line : this.fileComments) {
			builder.append(line);
			builder.append('\n');
		}

		return builder.toString();
	}

}
