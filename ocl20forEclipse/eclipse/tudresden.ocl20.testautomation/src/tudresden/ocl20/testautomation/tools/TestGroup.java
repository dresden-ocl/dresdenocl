package tudresden.ocl20.testautomation.tools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import tudresden.ocl20.testautomation.exceptions.TestCreationException;
import tudresden.ocl20.testautomation.exceptions.TestFileParseException;

/**
 * 
 * @author franz
 * 
 */
public class TestGroup {

	private String modelFile;
	private String modelInstanceFile;

	private String name;

	// this path can be a file or a directory
	private File absoluteBasePath;

	// if the path starts with a slash `/` it is treated as a
	// absolute subpath
	// otherwise it's treated as a name which has to exist in the
	// current subpath
	private List<String> ignoredPaths;

	// all extensions in this list are included by default
	// all others are ignored
	private List<String> includedExtensions;

	private static StatementFileParser parser;

	private List<StatementFile> fileUnits;

	static {
		parser = new StatementFileParser();
	}

	public TestGroup(String name, String modelFile, String modelInstanceFile,
			String statementPath) {

		this.ignoredPaths = new ArrayList<String>();
		this.includedExtensions = new ArrayList<String>();

		this.initIncludeExclude();
		this.fileUnits = new LinkedList<StatementFile>();

		this.name = name;
		this.modelFile = modelFile;
		this.modelInstanceFile = modelInstanceFile;
		this.absoluteBasePath = new File(statementPath);
	}

	/**
	 * initializes arrays for including and excluding directories etc.
	 */
	protected void initIncludeExclude() {

		this.ignoredPaths.add(".svn");
		this.ignoredPaths.add(".metadata");
		this.ignoredPaths.add(".settings");

		this.includedExtensions.add(".ocl");

	}

	public List<StatementFile> getFileUnits() {

		return this.fileUnits;
	}

	public void createStatementTestUnits() throws TestCreationException {

		try {
			this.handleStatementPath("");
		} catch (IOException e) {
			throw new TestCreationException(e);
		}
	}

	private void handleStatementPath(String subdir) throws IOException,
			TestCreationException {

		File currentDir = this.resolveSubdirAgainstBase(subdir);

		if (!currentDir.exists()) {
			throw new TestCreationException("Path `" + currentDir + "` doesnt exist!");
		}

		if (currentDir.isFile()) {
			this.handleStatementFile(subdir);
		}
		else {
			assert (currentDir.isDirectory());

			for (String path : currentDir.list()) {
				this.handleStatementPath(subdir + "/" + path);
			}
		}
	}

	/**
	 * adds a path to be ignored when creating the tests
	 * 
	 * @param ignore
	 *          if this starts with a slash `/` it marks an absolute subdir,
	 *          otherwise it is used as a name which is ignored whenever it occurs
	 *          in a path
	 */
	public void addPathToIgnore(String ignore) {

		this.ignoredPaths.add(ignore);
	}

	private boolean includeFile(String file) {

		if (!file.startsWith("/")) {
			file = "/" + file;
		}
		// exclude ignored entries
		for (String exclude : this.ignoredPaths) {
			if (exclude.startsWith("/") && file.startsWith(exclude)) {
				return false;
			}
			else if (file.contains(exclude)) {
				return false;
			}
		}

		// continue only if included
		for (String include : this.includedExtensions) {
			if (file.endsWith(include)) {
				return true;
			}
		}
		return false;

	}

	private void handleStatementFile(String subdir) throws IOException,
			TestCreationException {

		File currentDir = this.resolveSubdirAgainstBase(subdir);
		assert (currentDir.isFile());

		String absolutePath = currentDir.getAbsolutePath();

		// ignore/include with subdir, not absolute path!
		if (!this.includeFile(currentDir.getPath())) {
			return;
		}
		try {
			StatementFile unit = parser.parseConstraintFile(absolutePath);
			// the statementfileunit wants to know its own relative file path (if there
			// is one) for smarter test creation
			// TODO: find better way to get relative sub directory without file name
			unit.setTestFilePath(subdir);
			this.fileUnits.add(unit);
		} catch (TestFileParseException e) {
			throw new TestCreationException("Error parsing file " + absolutePath, e);
		}

	}

	private File resolveSubdirAgainstBase(String subdir) {

		File resolved = this.absoluteBasePath;
		if (subdir != null && !subdir.isEmpty()) {
			resolved = new File(resolved + subdir);
		}

		return resolved;

	}

	public String getName() {

		return this.name;

	}

	public String getModelFile() {

		return this.modelFile;
	}

	public String getModelInstanceFile() {

		return this.modelInstanceFile;

	}

}
