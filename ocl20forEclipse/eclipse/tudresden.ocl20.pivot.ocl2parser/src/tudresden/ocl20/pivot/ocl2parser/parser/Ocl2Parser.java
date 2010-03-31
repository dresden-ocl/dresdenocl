/*
    Copyright (C) 2008  Nils (s0006383@inf.tu-dresden.de)

    This file is part of the OCL parser of the Dresden OCL2 for Eclipse.

    The OCL parser is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    The OCL parser is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with the OCL parser.  If not, see <http://www.gnu.org/licenses/>.
.
 */

package tudresden.ocl20.pivot.ocl2parser.parser;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.ocl2parser.gen.ocl2as.OclFileAS;
import tudresden.ocl20.pivot.ocl2parser.gen.parserfiles.analysis.AttrEvalException;
import tudresden.ocl20.pivot.ocl2parser.gen.parserfiles.lexer.Lexer;
import tudresden.ocl20.pivot.ocl2parser.gen.parserfiles.lexer.LexerException;
import tudresden.ocl20.pivot.ocl2parser.gen.parserfiles.node.Start;
import tudresden.ocl20.pivot.ocl2parser.gen.parserfiles.parser.Parser;
import tudresden.ocl20.pivot.ocl2parser.gen.parserfiles.parser.ParserException;
import tudresden.ocl20.pivot.ocl2parser.internal.AtPreEnvironment;
import tudresden.ocl20.pivot.ocl2parser.internal.Environment;
import tudresden.ocl20.pivot.ocl2parser.internal.Heritage;
import tudresden.ocl20.pivot.ocl2parser.internal.exception.AtPreException;
import tudresden.ocl20.pivot.ocl2parser.internal.exception.BuildingASMException;
import tudresden.ocl20.pivot.ocl2parser.ocl2Transformer.Cs2AsOcl2;
import tudresden.ocl20.pivot.ocl2parser.parser.exceptions.BuildingASTException;
import tudresden.ocl20.pivot.ocl2parser.parser.exceptions.LexException;
import tudresden.ocl20.pivot.ocl2parser.parser.exceptions.ParsingException;
import tudresden.ocl20.pivot.ocl2parser.parser.exceptions.SemanticException;
import tudresden.ocl20.pivot.parser.IOclParser;
import tudresden.ocl20.pivot.parser.ParseException;
import tudresden.ocl20.pivot.pivotmodel.Constraint;

/**
 * <p>
 * The Parser implementation to parse OCL2 constraints.
 * </p>
 * 
 * @author Nils Thieme
 * @author Claas Wilke (refactoring and documentation)
 */
public class Ocl2Parser implements IOclParser {

	/** The singleton instance of the {@link Ocl2Parser}. */
	public static Ocl2Parser INSTANCE = new Ocl2Parser();

	/**
	 * <p>
	 * Private constructor for singleton pattern.
	 * </p>
	 */
	private Ocl2Parser() {
		/* Remains empty. */
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.parser.AbstractOclParser#doParse(java.net.URL)
	 */
	public List<Constraint> doParse(IModel model, Reader reader)
			throws ParseException {

		return this.doParse(model, reader, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.parser.IOclParser#doParse(tudresden.ocl20.pivot
	 * .modelbus.model.IModel, java.io.Reader, boolean)
	 */
	public List<Constraint> doParse(IModel model, Reader reader,
			boolean addToModel) throws ParseException {

		List<Constraint> result;
		Collection<Constraint> oldConstraints;

		try {
			oldConstraints = model.getConstraints();

			try {
				this.parse(model, reader);

				result = new ArrayList<Constraint>(model.getConstraints());
				result.removeAll(oldConstraints);

				/* Probably remove the constraints from the model again. */
				if (!addToModel) {
					model.removeConstraints(result);
				}

				/*
				 * Else probably notify listeners of the model that constraints
				 * have been added.
				 */
				else {
					if (result.size() > 0) {
						model.setChanged();
						model.notifiyListeners();
					}
				}
			}

			catch (ParseException e) {
				/*
				 * Remove newly added constraints and definitions from the model
				 * again.
				 */
				result = new ArrayList<Constraint>(model.getConstraints());
				result.removeAll(oldConstraints);

				if (result.size() > 0) {
					model.removeConstraints(result);
				}
				// no else.

				throw e;
			}
		}

		catch (ModelAccessException e) {

			/* Constraints have not to be removed, have not been added at all. */
			throw new ParseException(e.getMessage(), e);
		}

		return result;
	}

	/**
	 * <p>
	 * Parses the source ({@link Reader}) in respect to the {@link IModel} of
	 * this {@link Ocl2Parser}.
	 * </p>
	 * 
	 * @param model
	 *            The {@link IModel} for that the OCL expressions shall be
	 *            parsed.
	 * @param reader
	 *            A {@link Reader} from where to load OCL expressions.
	 * 
	 * @throws ParsingException
	 */
	protected void parse(IModel model, Reader reader) throws ParseException {

		/*
		 * TODO Claas: The size of the pushbackBuffer is arbitrary. Probably we
		 * should improve this?
		 */
		PushbackReader pushbackReader;
		pushbackReader = new PushbackReader(reader, 10000);

		Lexer lexer;
		lexer = new Lexer(pushbackReader);

		Parser parser;
		parser = new Parser(lexer);

		/* Parse the AST. */
		Start startNode;

		try {
			startNode = parser.parse();
		}

		catch (LexerException e) {
			throw new LexException(e.getMessage(), e);
		}

		catch (ParserException ex) {
			throw new ParsingException(ex.getMessage(),
					ex.getToken().getText(), ex.getToken().getLine(), ex
							.getToken().getPos());
		}

		catch (IOException e) {
			throw new ParseException(e.getMessage(), e);
		}

		/* Transform the AST. */
		Cs2AsOcl2 transformer;
		transformer = new Cs2AsOcl2();

		Heritage hrtg;
		hrtg = new Heritage();

		OclFileAS oclFile;
		try {
			oclFile = (OclFileAS) startNode.apply(transformer, hrtg);
		}

		catch (AttrEvalException e) {
			throw new BuildingASTException(e.getMessage(), e);
		}

		AtPreEnvironment atPreEnv;
		atPreEnv = new AtPreEnvironment();

		try {
			oclFile.resolveAtPre(atPreEnv);
		}

		catch (AtPreException e) {
			throw new SemanticException(e.getMessage(), e);
		}

		Environment env;
		env = new Environment(model);

		/* Try to compute the Abstract Syntax Model. */
		try {
			oclFile.computeASM(env);
		}

		catch (BuildingASMException e) {
			throw new SemanticException(e.getMessage(), e);
		}
	}
}