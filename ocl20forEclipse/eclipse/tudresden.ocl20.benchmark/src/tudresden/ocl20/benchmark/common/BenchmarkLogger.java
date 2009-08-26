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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.pivotmodel.Constraint;

/**
 * Logger to document success and failure of parsing and interpretation
 */
public class BenchmarkLogger {

	// @ name of current string
	private String name;

	// @ output file
	private FileWriter out;

	// @ Shared Test data
	private TestEnvironment env;

	// @ 0=Success, 1=Failure
	private int[] statParsing = new int[2];

	// @ 0=Success, 1=Failure, 2=Skipped (not matching types)
	private int[] statInterpretation = new int[3];

	// Stub which is used as output filename
	private static String outputFileBase = "results/BenchmarkResult_%s.txt";

	/**
	 * Instantiates a new benchmark logger.
	 * 
	 * @param env
	 *            Shared testdata used by the performer and the logger
	 */
	public BenchmarkLogger(TestEnvironment env) {
		this.name = env.testName;

		this.env = env;

		this.initTestFile();

	}

	/**
	 * open testfile
	 * 
	 * @throws RuntimeException
	 */
	private void initTestFile() throws RuntimeException {
		try {
			File fh = new File(String.format(outputFileBase, this.name));

			this.out = new FileWriter(fh);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		this.outHead1("Log for test " + this.name);

	}

	/**
	 * Close output file
	 * 
	 * @throws IOException
	 */
	public void close() throws IOException {
		this.out.close();
	}

	/**
	 * Headline 1
	 * 
	 * @param text
	 */
	public void outHead1(String text) {
		this.outLine("\n");
		this.outRuler1();
		this.outLine(text);
		this.outRuler1();
	}

	/**
	 * Headline 2
	 * 
	 * @param text
	 */
	public void outHead2(String text) {
		this.print("\n");
		this.outLine(text);
		this.outRuler2();
	}

	/**
	 * Headline 3
	 * 
	 * @param text
	 */
	public void outHead3(String text) {
		this.print("\n");
		this.outLine(text);
		this.outRuler3();
	}

	/**
	 * Just one line that is printed
	 * 
	 * @param value
	 */
	public void outLine(String value) {
		this.print(value);
		this.print("\n");
	}

	/**
	 * print formatted string
	 * 
	 * @param format
	 * @param args
	 */
	public void printf(String format, Object... args) {
		this.outLine(String.format(format, args));
	}

	/**
	 * Prints eventually the specified text.
	 * 
	 * @param value
	 */
	private void print(String value) {
		try {
			this.out.write(value);
			// this.out.flush();
			System.out.print(value);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ruler 1
	 */
	private void outRuler1() {
		this.outLine("==========================================");
	}

	/**
	 * Ruler 2
	 */
	private void outRuler2() {
		this.outLine("------------------------------------------");
	}

	/**
	 * Ruler 3
	 */
	private void outRuler3() {
		this.outLine("..........................................");
	}

	/**
	 * Report Parse Error
	 * 
	 * @param oclStatement
	 * @param e
	 */
	public void oclParseError(String oclStatement, Exception e) {
		this.outLine("OCL Parse Error");
		this.printResult(e);
		this.outLine("Code:");
		this.outLine(oclStatement);

		// count as error
		this.statParsing[1] += 1;
	}

	/**
	 * report parse success
	 * 
	 * @param oclStatement
	 */
	public void oclParseSuccess(String oclStatement) {
		this.outLine(oclStatement.trim().replaceAll("\n", "\n\t"));
		this.outLine(">> OK");
		this.outRuler3();

		// count as success
		this.statParsing[0] += 1;

	}

	/**
	 * print statistics
	 * 
	 * @param type
	 */
	public void printParseStats() {
		this.outHead2("Statistics:");
		this.printf("  successful\t: %d ", this.statParsing[0]);
		this.printf("  failed	 \t: %d ", this.statParsing[1]);
	}

	/**
	 * Prints the interpretation stats.
	 */
	public void printInterpretationStats() {
		this.outHead2("Interpretation Statistics:");
		this.printf("  successful\t: %d ", this.statInterpretation[0]);
		this.printf("  failed	 \t: %d ", this.statInterpretation[1]);
		this.printf("  skipped	 \t: %d ", this.statInterpretation[2]);
		this.printf("  total	 \t: %d ", this.statInterpretation[0]
				+ this.statInterpretation[1] + this.statInterpretation[2]);
	}

	// Log Interpretation Failure
	/**
	 * Interpretation error.
	 * 
	 * @param con
	 * @param obj
	 * @param result
	 */
	public void interpretationError(Constraint con, IModelObject obj,
			Object result) {
		this.statInterpretation[1] += 1;

		this.outLine("-->failed:");
		this.outLine("Constraint Specification: ");
		String conName = con.getQualifiedName();
		if (this.env.loadedConstraints.containsKey(conName)) {
			this.outLine(this.env.loadedConstraints.get(conName));
		} else {
			this.outLine("<Not Found>");
		}
		// this.outLine("on Object: <TODO: get some object information...>");
		this.printResult(result);
		this.outRuler3();
	}

	/**
	 * Skip interpretation.
	 */
	public void skipInterpretation() {
		this.outLine("--> Types don't match.");
		this.statInterpretation[2] += 1;
		this.outRuler3();
	}

	/**
	 * Interpretation success.
	 * 
	 * @param con
	 * @param obj
	 */
	public void interpretationSuccess(Constraint con, IModelObject obj) {
		this.statInterpretation[0] += 1;
		this.outLine("-->OK.");
		this.outRuler3();
	}

	/**
	 * Prints a result. If throwable, print type and location where thrown If
	 * not, convert to string.
	 * 
	 * @param result
	 */
	private void printResult(Object result) {
		if (result == null) {
			this.outLine("Result: NULL");
			return;
		}

		if (result instanceof Throwable) {
			Throwable res = (Throwable) result;

			this.outLine(res.toString());
			this.outLine("	thrown in: " + res.getStackTrace()[0].toString());

			if (res.getMessage() != null) {
				this.outLine("Message/Type: " + res.getMessage());
			}
		} else {
			this.outLine("Result: " + result.toString());
		}
	}

}
