/*
Copyright (C) 2009 by Franz Eichhorn (franz.eichhorn@gmx.de)

This file is part of the OCL Interpreter Test Suite of Dresden OCL2 for
Eclipse.

Dresden OCL2 for Eclipse is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL2 for Eclipse is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>.
 */
package tudresden.ocl20.benchmark.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


// TODO: Auto-generated Javadoc
public class StatementParsingHelper {

	/**
	 * used to specify the content of an ocl-file so that the performer knows how
	 * to pre-parse the files correctly. However QUERY is not used yet and the
	 * other two have exactly the same behavior.
	 */
	public static enum constraintFileType {
		STATEMENT, // complete statement containing context
		QUERY, // only query
		PREPOST
		// only pre/post, context will be attached
	};

	// @ Regex-Pattern to get a constraint's name
	private static Pattern patternStatementName;

	public String curStatementName;

	// @ independent number to assign as a constraint name
	public int curStatementId;

	protected TestEnvironment testEnv;

	static {
		patternStatementName =
				Pattern.compile("(.*?)(inv|pre|post|def)\\s*([\\(\\)\\w]*?)\\s*:(.*)");
	}

	/**
	 * Instantiates a new statement parsing helper.
	 * 
	 * @param testEnv
	 */
	public StatementParsingHelper(TestEnvironment testEnv) {

		this.testEnv = testEnv;

	}

	/**
	 * Safe load query file.
	 * 
	 * @param queryFile
	 */
	public void safeLoadQueryFile(String queryFile) {

		this.safeLoadConstraintFile(queryFile, constraintFileType.QUERY);
	}

	/**
	 * Safe load of OCL-File which contains Pre/Post values.
	 * 
	 * @param oclFile
	 */
	public void safeLoadPrePostFile(String oclFile) {

		this.safeLoadConstraintFile(oclFile, constraintFileType.PREPOST);
	}

	/**
	 * loads an Ocl file by loading each statement separately to avoid abort due
	 * to parser exceptions Statements that could not be loaded, will be stored as
	 * well as the error messages.
	 * 
	 * @param oclFileName
	 * 
	 * @throws RuntimeException
	 */
	public void safeLoadStatementFile(String oclFileName) throws RuntimeException {

		this.safeLoadConstraintFile(oclFileName, constraintFileType.STATEMENT);
	}

	/**
	 * Loads an ocl file either as query or normal invariant file...
	 * 
	 * @param fileName
	 * @param type
	 */
	private void safeLoadConstraintFile(String fileName, constraintFileType type) {

		// start the curConstraintID at 1 for each file
		this.curStatementId = 1;
		FileReader oclFileReader = this.safeOpenFileReader(fileName);
		BufferedReader bufReader = new BufferedReader(oclFileReader);

		// package information to be copied
		// at the beginning of each statement
		String packageInformation = this.getPackageFromReader(bufReader);

		switch (type) {
		case STATEMENT:
			this.handleStatements(bufReader, packageInformation, fileName);
			break;

		case QUERY:
			this.handleQueries(bufReader, packageInformation);
			break;

		case PREPOST:
			this.handleStatements(bufReader, packageInformation, fileName);
			break;
		}

	}

	/**
	 * Handle statements.
	 * 
	 * @param source
	 * @param packageInfo
	 * @param fileName
	 */
	private void handleStatements(BufferedReader source, String packageInfo,
			String fileName) {

		fileName = Helper.getFileNameFromPath(fileName);

		String strLine;
		StringBuilder oclStatementString = new StringBuilder();

		while ((strLine = this.nextLine(source)) != null) {

			// if line starts with context or endpackage
			// --> parse the LAST statement
			if ((strLine.startsWith("endpackage") || strLine.startsWith("context"))
					&& oclStatementString.length() > 0) {

				this.testEnv.storeStatementDefinition(packageInfo, //
						oclStatementString.toString(), //
						this.curStatementName);

				// empty ocl string
				oclStatementString.setLength(0);
			}

			// EOF
			if (strLine.startsWith("endpackage")) {
				break;
			}

			// try to extract the constraint's name
			strLine = this.getOrSetConstraintName(strLine, fileName);

			oclStatementString.append(strLine);
			oclStatementString.append("\n");

		}
	}

	/**
	 * Handles a source which contains queries. The source is expected to be in
	 * the USE-Format, queries start with `?´. All queries are encapsulated into
	 * OCL-statements which are then parsed. Expected Results start with `!´.
	 * They're mostly encapsulated into invariants
	 * 
	 * @param source
	 *          reader supplying source
	 * @param packageInfo
	 *          namespace (must conform to current model)
	 */
	private void handleQueries(BufferedReader source, String packageInfo) {

		String strLine;
		StringBuilder statementBuilder = new StringBuilder();

		while ((strLine = this.nextLine(source)) != null
				|| statementBuilder.length() > 0) {

			// reset ocl statement
			statementBuilder.setLength(0);

			if (strLine.startsWith("?")) {
				String methodName = "query_" + (this.curStatementId++);

				statementBuilder.append("context Person\n");
				statementBuilder.append("def: ");
				statementBuilder.append(methodName);
				statementBuilder.append(": "); // String =

				statementBuilder.append(strLine.substring(1));

				this.testEnv.storeStatementDefinition(packageInfo, //
						statementBuilder.toString(), //
						this.curStatementName);

				// the next ! is considered to be the expected result
			}
			else if (strLine.startsWith("!")) {
				String methodName = "query_" + (this.curStatementId);
				statementBuilder.setLength(0);
				statementBuilder.append("context Person\n");
				statementBuilder.append("inv: self.");
				statementBuilder.append(methodName);
				statementBuilder.append(" = " + strLine.substring(1));

				this.testEnv.storeStatementDefinition(packageInfo, //
						statementBuilder.toString(), //
						this.curStatementName);
			}
		}
	}

	/**
	 * returns the next line of the reader by omitting comments, empty lines etc.
	 * It's throwing RuntimeException when the file seems corrupt.
	 * 
	 * @param source
	 * 
	 * @return next line or null in case of end
	 */
	private String nextLine(BufferedReader source) {

		String line;
		try {
			// as long as there are lines to read
			while ((line = source.readLine()) != null) {
				line = line.trim();
				if (line.length() == 0 || line.startsWith("--")) {
					continue;
				}

				if (line.startsWith("package")) {
					throw new RuntimeException(
							"Package information should be only once in an Ocl File");
				}

				return line;
			}

		} catch (IOException e) {
			throw new RuntimeException("Error Reading Source File.", e);
		}

		// file empty or EOF
		return null;
	}

	/**
	 * Safe open file.
	 * 
	 * @param fileName
	 * 
	 * @return the file
	 * 
	 * @throws RuntimeException
	 */
	private File safeOpenFile(String fileName) throws RuntimeException {

		File tmpFile = new File(fileName);

		// error reporting if it doesnt exists
		if (!tmpFile.exists()) {
			String msg;

			msg = "The requested file ";
			msg += fileName;
			msg += " doesn't exists.";

			throw new RuntimeException(msg);
		}

		return tmpFile;
	}

	/**
	 * Safe open file reader.
	 * 
	 * @param fileName
	 * 
	 * @return the file reader
	 * 
	 * @throws RuntimeException
	 */
	private FileReader safeOpenFileReader(String fileName)
			throws RuntimeException {

		File tmpFile = this.safeOpenFile(fileName);

		try {
			FileReader tmpReader = new FileReader(tmpFile);
			return tmpReader;
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Error opening file " + fileName
					+ " (it exists, though)");
		}
	}

	/**
	 * Returns the package (is expected to be the first line) of a BufferedReader.
	 * 
	 * @param reader
	 *          Input reader that contains package statement
	 * 
	 * @return
	 * 
	 * @throws RuntimeException
	 */
	private String getPackageFromReader(BufferedReader reader)
			throws RuntimeException {

		String line = "";
		try {
			while ((line = reader.readLine()) != null) {
				line = line.trim();
				// empty line or comment at the beginning
				if (line.equals("") || line.startsWith("--")) {
					continue;
				}

				if (line.startsWith("package")) {
					return line.substring(7).trim();
				}

				throw new RuntimeException(
						"Cannot find the package-statement in the reader. This has to be the first statement!");
			}

			throw new RuntimeException(
					"Unexpected End of Reader, did not find a package");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * analyzes a linen from the input and extracts the constraint's name, if
	 * specified. If no name is given, a new name is generated and integrated inh
	 * the line
	 * 
	 * @param line
	 *          Line to be analyzed
	 * @param fileName
	 *          current scanned filename that is used to generate the new
	 *          constraint name
	 * 
	 * @return
	 */
	private String getOrSetConstraintName(String line, String fileName) {

		// try to capture the invariant's name
		Matcher match = patternStatementName.matcher(line);

		String conName;

		if (match.matches()) {
			// if pre or post add it to the name
			String prefix;
			if (match.group(2).equals("pre") || match.group(2).equals("post")
					|| match.group(2).equals("def")) {
				prefix = match.group(2) + "_";
			}
			else {
				prefix = "";
			}
			// create or modify the testname to get a unique name
			// specially identify pre and posts to test them separately
			conName = match.group(3);

			if (conName != null && conName.length() > 0) {
				this.curStatementName = prefix + fileName + "_" + match.group(3);
			}
			else {
				this.curStatementName =
						prefix + fileName + "_" + (this.curStatementId++);
			}

			return match.group(1) + " " + match.group(2) + " "
					+ this.curStatementName + ":" + match.group(4);

		}
		else {
			return line;
		}
	}

}
