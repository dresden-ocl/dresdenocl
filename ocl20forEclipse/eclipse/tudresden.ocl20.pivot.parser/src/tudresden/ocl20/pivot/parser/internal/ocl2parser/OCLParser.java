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
package tudresden.ocl20.pivot.parser.internal.ocl2parser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import tudresden.ocl20.pivot.ocl2parser.parser.OCL2Parser;
import tudresden.ocl20.pivot.ocl2parser.parser.exceptions.BuildingASTException;
import tudresden.ocl20.pivot.ocl2parser.parser.exceptions.LexException;
import tudresden.ocl20.pivot.ocl2parser.parser.exceptions.ParsingException;
import tudresden.ocl20.pivot.ocl2parser.parser.exceptions.SemanticException;
import tudresden.ocl20.pivot.parser.AbstractOclParser;
import tudresden.ocl20.pivot.parser.ParseException;

/**
 * This class extends the {@link AbstractOclParser} class
 * to implement an ocl parser.
 * @author nils
 *
 */
public class OCLParser extends AbstractOclParser {

	/**
	 * Make the parsing of the given url. If an error occurred
	 * while parsing a {@link ParseException} is thrown.
	 */
	@Override
	public void doParse(URL url) throws ParseException {
		InputStreamReader reader = null;
		try {
			reader = new InputStreamReader(url.openStream());
		} catch(IOException ex) {
			
		} 
		
		OCL2Parser parser = new OCL2Parser(getModel(), reader);
		
		try {
			parser.parse();
		} catch(IOException ex) {
			
		} catch (ParsingException e) {
			throw new ParseException(e.getMessage());
		} catch (LexException e) {
			throw new ParseException(e.getMessage());
		} catch (BuildingASTException e) {
			throw new ParseException(e.getMessage());
		} catch (SemanticException e) {
			throw new ParseException(e.getMessage());
		}
	}

}
