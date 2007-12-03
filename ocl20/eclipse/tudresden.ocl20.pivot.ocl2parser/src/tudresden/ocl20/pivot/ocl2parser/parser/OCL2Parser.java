/*
    Copyright (C) 2007  Nils (s0006383@inf.tu-dresden.de)

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package tudresden.ocl20.pivot.ocl2parser.parser;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.util.List;

import tudresden.ocl20.pivot.modelbus.IModel;
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
import tudresden.ocl20.pivot.pivotmodel.Namespace;

public class OCL2Parser {
	protected IModel model;
	protected Reader oclSource;
	
	
	public OCL2Parser(IModel model, Reader oclSource) {
		this.model = model;
		this.oclSource = oclSource;	
	}
	
	public void parse() throws ParsingException, LexException, IOException, BuildingASTException, SemanticException {
		PushbackReader pushbackReader = new PushbackReader(oclSource, 1000);
		Lexer lexer = new Lexer(pushbackReader);
		Parser parser = new Parser(lexer);
		
		
		Start startNode;
		try {
			startNode = parser.parse();
		} catch (LexerException e) {
			throw new LexException(e.getMessage());
		} catch(ParserException ex) {
			throw new ParsingException(ex.getMessage(), ex.getToken().getText(), ex.getToken().getLine(), ex.getToken().getPos());
		}
		
		
		Cs2AsOcl2 transformer = new Cs2AsOcl2();
		Heritage hrtg = new Heritage();
		OclFileAS oclFile;
		try {
			oclFile = (OclFileAS) startNode.apply(transformer, hrtg);
		} catch (AttrEvalException e) {
			throw new BuildingASTException(e.getMessage());
		}
		AtPreEnvironment atPreEnv = new AtPreEnvironment();
		try {
			oclFile.resolveAtPre(atPreEnv);
		} catch (AtPreException e) {
			throw new SemanticException(e.getMessage());
		}
		
		Environment env = new Environment(model);
		try {
			List<Namespace> namespaceList = oclFile.computeASM(env);
		} catch (BuildingASMException e) {
			throw new SemanticException(e.getMessage());
		}
		
	}
}
