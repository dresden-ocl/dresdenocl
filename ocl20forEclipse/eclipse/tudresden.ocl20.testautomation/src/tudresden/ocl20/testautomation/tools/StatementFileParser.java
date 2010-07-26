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
package tudresden.ocl20.testautomation.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StatementFileParser {

	// @ Regex-Pattern to get a constraint's name
	private static Pattern patternStatementName;

	private String curStatementName;

	// @ independent number to assign as a constraint name
	private int curStatementId;

	private StatementFileUnit currentUnit;
	// protected TestEnvironment testEnv;

	private BufferedReader currentReader;

	static {
		patternStatementName =
				Pattern.compile("(.*?)(inv|pre|post|def)\\s*([\\(\\)\\w]*?)\\s*:(.*)");
	}

	/**
	 * needs to be instanciated, although it doesn't safe much since it works like
	 * a statemachine. For every parsing operation it saves curStatementName and
	 * curStatementId etc.
	 */
	public StatementFileParser() {

	}

	/**
	 * Loads an ocl file either as query or normal invariant file...
	 * 
	 * Since this object works like a statemachine we cannot run this method with
	 * multiple threads
	 * 
	 * @param fileName
	 * @param type
	 * @throws FileNotFoundException
	 */
	public synchronized StatementFileUnit parseConstraintFile(String fileName)
			throws FileNotFoundException {

		this.initParseRun(fileName);

		// package information to be copied
		// at the beginning of each statement
		String packageInformation = this.getPackageFromReader();

		this.currentUnit.setPackage(packageInformation);

		this.parseStatements(fileName);

		return this.currentUnit;
	}

	private void initParseRun(String fileName) throws FileNotFoundException {

		this.currentUnit = new StatementFileUnit();

		// start the curConstraintID at 1 for each file
		this.curStatementId = 1;

		FileReader oclFileReader = this.safeOpenFileReader(fileName);
		this.currentReader = new BufferedReader(oclFileReader);
	}

	/**
	 * Handle statements.
	 * 
	 * @param source
	 * @param packageInfo
	 * @param fileName
	 */
	private void parseStatements(String fileName) {

		fileName = PathHelper.getFileNameFromPath(fileName);

		String strLine;
		StringBuilder oclStatementString = new StringBuilder();

		while ((strLine = this.nextLine()) != null) {

			// if line starts with context or endpackage
			// --> parse the LAST statement
			if ((strLine.startsWith("endpackage") || strLine.startsWith("context"))
					&& oclStatementString.length() > 0) {

				this.currentUnit.addStatementDefinition(this.curStatementName, oclStatementString.toString());

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
	 * returns the next line of the reader by omitting comments, empty lines etc.
	 * It's throwing RuntimeException when the file seems corrupt.
	 * 
	 * @param source
	 * 
	 * @return next line or null in case of end
	 */
	private String nextLine() {

		String line;
		try {
			// as long as there are lines to read
			while ((line = this.currentReader.readLine()) != null) {
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
	 * @throws FileNotFoundException
	 * 
	 * @throws RuntimeException
	 */
	private File safeOpenFile(String fileName) throws FileNotFoundException {

		File tmpFile = new File(fileName);

		// error reporting if it doesnt exists
		if (!tmpFile.exists()) {
			throw new FileNotFoundException(//
					String.format("file %s doesnt exist", fileName));
		}

		return tmpFile;
	}

	/**
	 * Safe open file reader.
	 * 
	 * @param fileName
	 * 
	 * @return the file reader
	 * @throws FileNotFoundException
	 * 
	 * @throws RuntimeException
	 */
	private FileReader safeOpenFileReader(String fileName)
			throws FileNotFoundException {

		File tmpFile = this.safeOpenFile(fileName);

		FileReader tmpReader = new FileReader(tmpFile);
		return tmpReader;

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
	private String getPackageFromReader() throws RuntimeException {

		String line = "";
		try {
			while ((line = this.currentReader.readLine()) != null) {
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
