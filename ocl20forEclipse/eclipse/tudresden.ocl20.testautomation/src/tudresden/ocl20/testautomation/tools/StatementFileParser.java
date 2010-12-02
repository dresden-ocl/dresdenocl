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
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import tudresden.ocl20.testautomation.exceptions.TestFileParseException;

public class StatementFileParser {

	private static Logger log = Logger.getLogger(StatementFileParser.class);

	private StatementFile currentUnit;

	protected List<String> fileLines;

	/**
	 * needs to be instanciated, although it doesn't safe much since it works like
	 * a statemachine. For every parsing operation it saves curStatementName and
	 * curStatementId etc.
	 * 
	 * @throws IOException
	 * @throws TestFileParseException
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
	 * @throws TestFileParseException
	 * @throws IOException
	 */
	public synchronized StatementFile parseConstraintFile(String fileName)
			throws TestFileParseException {

		try {
			this.init(fileName);

			this.currentUnit.setSourceFile(fileName);

			int lastParsedLine = this.readPackageAndHead();

			this.parseStatements(lastParsedLine);

			return this.currentUnit;
		} finally {
			this.deInit();
		}
	}

	private void init(String fileName) throws TestFileParseException {

		log.debug("Start parsing file " + fileName);
		this.fileLines = new LinkedList<String>();
		this.currentUnit = new StatementFile();

		this.readLines(fileName);

	}

	private void readLines(String fileName) throws TestFileParseException {

		try {
			BufferedReader reader = this.safeOpenFileReader(fileName);

			String line;
			while ((line = reader.readLine()) != null) {
				this.fileLines.add(line.trim());
			}

			if (this.fileLines.isEmpty()) {
				throw new TestFileParseException("Test file " + fileName + " is empty!");
			}

			reader.close();
		} catch (IOException e) {
			throw new TestFileParseException("IOError opening or reading file "
					+ fileName, e);
		}

	}

	private void deInit() {

		this.currentUnit = null;
		this.fileLines = null;
	}

	/**
	 * Handle statements.
	 * 
	 * @param source
	 * @param packageInfo
	 * @param fileName
	 * @throws TestFileParseException
	 */
	private void parseStatements(int startLine) throws TestFileParseException {

		String line;

		for (int i = startLine; i < this.fileLines.size(); ++i) {
			line = this.fileLines.get(i);
			if (line.startsWith("context")) {
				this.handleStatement(i);
				continue;
			}

			if (line.startsWith("endpackage") || line.startsWith("endpackage")) {
				return;
			}
		}
		throw new TestFileParseException(
				"Expected `endpackage` at the end of the file");
	}

	private void handleStatement(int contextLine) {

		// paranoia :)
		assert (this.fileLines.get(contextLine).startsWith("context"));
		assert (contextLine < this.fileLines.size());

		Statement statement = this.currentUnit.createStatement();
		// get comments above this line
		for (int i = contextLine - 1; i >= 0; --i) {
			String line = this.fileLines.get(i);
			String comment = this.lineIsComment(line);
			if (comment != null) {
				statement.addTopComment(comment);
			}
			else {
				break;
			}
		}

		LinkedList<String> statementLines = new LinkedList<String>();

		// get all the following lines until we see the next context or endpackage
		for (int i = contextLine; i < this.fileLines.size(); ++i) {
			String line = this.fileLines.get(i);

			// take everything until
			if (i != contextLine
					&& (line.startsWith("context") || line.startsWith("endpackage"))) {
				break;
			}

			// add the lines just raw
			statementLines.add(line);
		}

		// remove the last lines that begin with a comment as they
		// proably belong to the next context until there's a new line
		while (statementLines.getLast().startsWith("--")) {
			statementLines.removeLast();
		}

		// now finally add the lines to new statement
		statement.setStatementLines(statementLines);

		this.currentUnit.addStatement(statement);

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
	private BufferedReader safeOpenFileReader(String fileName)
			throws FileNotFoundException {

		File tmpFile = this.safeOpenFile(fileName);

		BufferedReader tmpReader = new BufferedReader(new FileReader(tmpFile));
		return tmpReader;

	}

	/**
	 * Returns the package (is expected to be the first line) of a BufferedReader.
	 * 
	 * @param reader
	 *          Input reader that contains package statement
	 * 
	 * @return line number of package. the constraint searcher doesn't need to
	 *         parse above this line to avoid confusion
	 * 
	 * @throws TestFileParseException
	 */
	private int readPackageAndHead() throws TestFileParseException {

		String comment;
		String line;

		for (int i = 0; i < this.fileLines.size(); ++i) {
			line = this.fileLines.get(i);

			if (line.isEmpty()) {
				continue;
			}

			// handle comments
			comment = this.lineIsComment(line);
			if (comment != null) {
				this.currentUnit.addFileComment(comment);
				continue;
			}

			// handle package deklaration
			if (line.startsWith("package")) {
				this.currentUnit.setPackage(line.substring(8).trim());

				this.currentUnit.parseHeadComments();
				return i + 1;
			}

			break;
		}

		// package must be the first statement!
		throw new TestFileParseException(
				"Expected package declaration to be the first statement!");
	}

	private String lineIsComment(String line) {

		if (line.startsWith("--")) {
			return line.substring(2).trim();
		}
		return null;
	}

}
