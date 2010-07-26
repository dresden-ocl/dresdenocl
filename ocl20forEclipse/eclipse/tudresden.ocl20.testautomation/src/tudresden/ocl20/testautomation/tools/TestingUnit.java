package tudresden.ocl20.testautomation.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import tudresden.ocl20.testautomation.exceptions.TestCreationException;

/**
 * 
 * @author franz
 * 
 */
public class TestingUnit {

	private String modelFile;
	private String modelInstanceFile;

	private String name;

	// this path can be a file or a directory
	private File absoluteBasePath;

	private static String IGNORED[] = { ".svn" };
	private static String INCLUDE[] = { ".ocl" };

	private static StatementFileParser parser;

	private List<StatementFileUnit> fileUnits;

	static {
		parser = new StatementFileParser();
	}

	private TestingUnit() {

		this.fileUnits = new LinkedList<StatementFileUnit>();
	}

	public static TestingUnit createTestingUnit(String name, String modelFile,
			String modelInstanceFile, String statementPath)
			throws TestCreationException {

		TestingUnit tmp = new TestingUnit();
		tmp.name = name;
		tmp.modelFile = modelFile;
		tmp.modelInstanceFile = modelInstanceFile;
		tmp.absoluteBasePath = new File(statementPath);

		tmp.createStatementTestUnits();

		return tmp;
	}

	public List<StatementFileUnit> getFileUnits() {

		return this.fileUnits;
	}

	private void createStatementTestUnits() throws TestCreationException {

		try {
			this.handleStatementPath("");
		} catch (FileNotFoundException e) {
			throw new TestCreationException(e);
		}
	}

	private void handleStatementPath(String subdir) throws FileNotFoundException,
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

	private boolean includeFile(String file) {

		// exclude ignored entries
		for (String exclude : IGNORED) {
			if (file.contains(exclude)) {
				return false;
			}
		}

		// continue only if included
		for (String include : INCLUDE) {
			if (file.endsWith(include)) {
				return true;
			}
		}
		return false;

	}

	private void handleStatementFile(String subdir) throws FileNotFoundException {

		File currentDir = this.resolveSubdirAgainstBase(subdir);
		assert (currentDir.isFile());

		String absolutePath = currentDir.getAbsolutePath();
		
		if(!this.includeFile(absolutePath)){
			return;
		}

		StatementFileUnit unit = parser.parseConstraintFile(absolutePath);
		// the statementfileunit wants to know its own relative file path (if there
		// is one) for smarter test creation
		// TODO: find better way to get relative sub directory without file name
		unit.setTestFilePath(subdir);
		this.fileUnits.add(unit);
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
