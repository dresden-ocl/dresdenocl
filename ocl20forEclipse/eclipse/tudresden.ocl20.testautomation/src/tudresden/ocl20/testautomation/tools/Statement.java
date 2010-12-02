package tudresden.ocl20.testautomation.tools;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.testautomation.exceptions.TestFileParseException;

/**
 * Stores information about a single statement
 * 
 * @TODO: the statementdefinition should also contain its type like
 *        INV/PRE/POST/DEF etc. in order to avoid external type handling which
 *        is currently done when parsing the statements
 */
public class Statement {

	private static Logger log = Logger.getLogger(Statement.class);

	// @ package name as used in ocl (e.g.
	// tudresden::ocl20::benchmark::testdata::b1)
	protected String packageName;

	// @ full ocl specification
	protected String specification;

	// @ name (which is included in the specification)
	// notice that this name can be null. It will be specified
	// when creating the test
	protected String name;

	// @ buffer for qualified name --> call getQualifiedName() to retrieve it
	protected String qualifiedName;

	// @ buffer for the namespace which can be used to find a constraint
	protected String namespace;

	// @ buffer for the nested namespaces in a list
	protected List<String> namespaceList;

	protected List<String> topComments;
	protected List<String> specificationLines;

	// contains the constraint definition
	// (i.e. everything from context to the first `:`)
	// this is used to get important things like
	// model, name, type, parameters etc..
	protected String constraintDef;

	protected TestOptions options;

	/**
	 * Patterns to read the constraint definition
	 */
	protected static Pattern globalPattern;

	protected static Pattern contextPattern;

	public static enum StereoTypes {
		INVARIANT, DEFINITION
	}

	protected StereoTypes stereoType;

	// protected static Pattern stmtPattern;

	static {
		String stereoType = "(inv|pre|post|body|init|derive|def|static def)";

		// matches context <name> [:] <stereotype> [<name>] :
		globalPattern =
				Pattern.compile("context\\s+(.*?)(?:(?:\\s*:\\s*)|\\s+)" + stereoType
						+ "(?:\\s+([^:\\s]+))?\\s*:.*?");

		String paramList = "(?:\\(([^\\)]*)\\))?"; // (..)
		String model = "((?:::|\\w)+)"; // package::model (everything except `(` and
		// a space)
		String type = "(?::\\s*([\\w]+))?"; // [: Type] --> for return or property
		// types

		String context = model + "\\s*" + paramList + "\\s*" + type;

		contextPattern = Pattern.compile(context);
	}

	/**
	 * Instantiates a new statement definition.
	 * 
	 * @param pack
	 * @param name
	 * @param spec
	 */
	public Statement() {

		this.topComments = new LinkedList<String>();
	}

	/**
	 * 
	 * @param packageDekl
	 *          file's package declaration
	 * 
	 * @param fileOptions
	 *          copy of file's options. All options set for the statement override
	 *          the file's options
	 */
	public Statement(String packageDekl, TestOptions fileOptions) {

		this();

		this.packageName = packageDekl;
		this.options = fileOptions;
	}

	public void setStatementLines(List<String> lines) {

		this.specificationLines = lines;
	}

	public void parse() throws TestFileParseException {

		// update my options with my local comments
		this.options.parse(this.topComments);

		String con = this.getConstraintLine();
		Matcher globMatcher = globalPattern.matcher(con);

		if (!globMatcher.find() || globMatcher.groupCount() != 3) {
			throw new TestFileParseException(
					"Couldn't recognize Constraint definition for \n" + con);
		}

		String model = globMatcher.group(1);
		String stereoType = globMatcher.group(2);
		this.name = globMatcher.group(3);

		// (inv|pre|post|body|init|derive|def|static def)
		if (stereoType.equals("inv")) {
			// no special handling needed
			this.stereoType = StereoTypes.INVARIANT;

		}
		else if (stereoType.equals("def")) {
			this.stereoType = StereoTypes.DEFINITION;
		}
		else {
			// throw new
			// TestFileParseException("constraint stereo type not supported yet.");
			log.error("constraint stereo type " + stereoType + " not supported yet.");
		}

	}

	public String getConstraintLine() {

		// better use a stringbuilder, we never know if the constraint
		// has 100 lines :)
		StringBuilder builder = new StringBuilder();
		for (String line : this.specificationLines) {
			// if line contains comment
			int commentStart = line.indexOf("--");
			if (commentStart != -1) {
				line = line.substring(0, commentStart);
			}
			builder.append(line);
			builder.append(' ');
		}

		return builder.toString();
	}

	public void addTopComment(String comment) {

		// add to the TOP since we're moving upwards
		this.topComments.add(0, comment);
	}

	/**
	 * Gets the full statement.
	 * 
	 * @return the full statement
	 */
	public String getStatement() {

		return "package " + this.packageName + "\n" + this.getSpecification()
				+ "\nendpackage";
	}

	private String getSpecification() {

		if (this.specification == null) {
			StringBuilder builder = new StringBuilder();
			for (String line : this.topComments) {
				builder.append("-- ");
				builder.append(line);
				builder.append('\n');
			}

			for (String line : this.specificationLines) {
				builder.append(line);
				builder.append('\n');
			}

			this.specification = builder.toString();
		}

		return this.specification;
	}

	/**
	 * Gets the qualified name.
	 * 
	 * @return the qualified name
	 */
	public String getQualifiedName() {

		if (this.qualifiedName == null) {
			this.qualifiedName = "root::" + this.packageName + "::" + this.name;
		}
		return this.qualifiedName;
	}

	/**
	 * Gets the namespace.
	 * 
	 * @return the namespace
	 */
	public String getNamespace() {

		if (this.namespace == null) {
			this.namespace = "root::" + this.packageName;
		}
		return this.namespace;
	}

	/**
	 * Gets the namespace list.
	 * 
	 * @return the namespace list
	 */
	public List<String> getNamespaceList() {

		if (this.namespaceList == null) {
			String ns = this.getNamespace();
			String[] nsArray = ns.split("::");
			this.namespaceList = new ArrayList<String>(nsArray.length);
			for (String item : nsArray) {
				this.namespaceList.add(item);
			}
		}

		return this.namespaceList;
	}

	/**
	 * Returns the (non qualified) name
	 * 
	 */
	public String getName() {

		return this.name;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object rhs) {

		if (rhs instanceof Statement) {
			Statement rhsStmt = (Statement) rhs;
			return this.getQualifiedName().equals(rhsStmt.getQualifiedName());
		}
		else if (rhs instanceof String) {
			return this.getQualifiedName().equals(rhs.toString());
		}

		return false;
	}

	/**
	 * Checks if this statement is the source of the passed constraint. This
	 * assumes that statement names are not used twice as only the names are
	 * compared.
	 * 
	 * @param rhs
	 * 
	 * @return true, if is source of
	 */
	public boolean isSourceOf(Constraint rhs) {

		return this.getQualifiedName().equals(rhs.getQualifiedName());
	}

	public TestOptions getOptions() {

		return this.options;
	}

	public void setName(String name) {

		this.name = name;
	}

	public StereoTypes getStereoType() {

		return this.stereoType;
	}

}