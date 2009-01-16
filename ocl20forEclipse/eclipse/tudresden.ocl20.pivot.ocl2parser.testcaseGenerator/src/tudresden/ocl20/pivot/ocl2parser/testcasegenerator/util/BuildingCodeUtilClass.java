/*
    Copyright (C) 2008  Nils (s0006383@inf.tu-dresden.de)

    This file is part of the testcase generator for OCL parser of the Dresden OCL2 for Eclipse.

    The testcase generator is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    The testcase generator is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with the testcase generator.  If not, see <http://www.gnu.org/licenses/>.
.
*/
package tudresden.ocl20.pivot.ocl2parser.testcasegenerator.util;

import java.util.ArrayList;
import java.util.List;

import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IListElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IModelExpression;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IVariable;

public class BuildingCodeUtilClass {
	static private BuildingCodeUtilClass instance;
	
	private BuildingCodeUtilClass() {
		
	}
	
	static public BuildingCodeUtilClass getDefault() {
		if (instance == null) {
			instance = new BuildingCodeUtilClass();
			return instance;
		}
		
		return instance;
	}
	
	/**
	 * This method transforms a path name, where the elements are separted by '::',
	 * into a list of single elements without '::'. 
	 * @param pathName the string of a path name with the delmiter '::'
	 * @return a list of the elements without '::'
	 */
	static public List<String> transformPathName2List(String pathName){
		java.util.List elementList = new ArrayList();
		
		String delimiter = "::";
		
		int index = 0;
		int length = pathName.length();
		int lastPosition = 0;
		
		while(index < length) {
			index = pathName.indexOf(delimiter, lastPosition);
			if (index == -1) {
				String subString = pathName.substring(lastPosition, length);
				elementList.add(subString);
				break;
			}
			String subString = pathName.substring(lastPosition, index);
			elementList.add(subString);
			lastPosition = index + delimiter.length();
		}
		
		return elementList;
	}

	/**
	 * Takes a list of strings and produce a string with commas in between.
	 * For example: Suppose we have the list [a,b,c] then a string "a,b,c" will
	 * be constructed. If <i>elements</i> is null or it has zero elements
	 * an empty string will be returned.
	 * @param elements the list from which to construct a string
	 * @return the string with commas
	 */
	static public String concatElements(List<String> elements) {
		String result = new String();
		if (elements == null) return result;
		
		if (elements.size() == 0) return result;
		
		result = elements.get(0);
		for(int i = 1; i < elements.size(); i++) {
			result = result + ", " + elements.get(i);
		}
		
		return result;
	}
	
	/**
	 * Takes a list of strings and produce a string with double colons in between.
	 * For example: Suppose we have the list [a,b,c] then a string "a::b::c" will
	 * be constructed. If <i>elements</i> is null or it has zero elements
	 * an empty string will be returned.
	 * @param elements the list from which to construct a string
	 * @return the string with commas
	 */
	static public String concatElementsWithDblColon(List<String> elements) {
		String result = new String();
		if (elements == null) return result;
		
		if (elements.size() == 0) return result;
		
		result = elements.get(0);
		for(int i = 1; i < elements.size(); i++) {
			result = result + ":: " + elements.get(i);
		}
		
		return result;
	}
	
	/**
	 * Takes a list of string buffers and produce a string with commas in between.
	 * For example: Suppose we have the list [a,b,c] then a string "a,b,c" will
	 * be constructed. If <i>elements</i> is null or it has zero elements
	 * an empty string will be returned.
	 * @param elements the list from which to construct a string
	 * @return the string with commas
	 */
	static public String concatElementsOfStringBufferList(List<StringBuffer> elements) {
		String result = new String();
		if (elements == null) return result;
		
		if (elements.size() == 0) return result;
		
		List<String> list = new ArrayList<String>();
		for(StringBuffer buf : elements) {
			list.add(buf.toString());
		}
		
		return concatElements(list);
	}

	/**
	 * This method returns the last element of a variable chain.
	 * @param a variable
	 * @return the last element of a variable chain
	 */
	static public IModelExpression transferLastElementOfVariableChainList(IVariable exp) {
		// This is the potenially run variable.
		IModelExpression runVar = null;
		
		/*
		 *  If the exp is of type IVariabel then we take the reference of
		 *  exp and make it to the run variable
		 */
		if (exp instanceof IVariable) {
			runVar = ((IVariable)exp).getReference();
		} else
		/*
		 * If exp is not a variable we return this exp.
		 */
		return exp;
				
		// We make an endless loop.
		while(true) {
			/*
			 * If the run variable is a variable then we take
			 * their reference a make this reference to the actual
			 * run variable.
			 */  
			if (runVar instanceof IVariable) {
				IModelExpression ref = ((IVariable) runVar).getReference();
				runVar = ref;
				continue;
			} else return runVar; // If the run variable isn't of type variable we return this expression.
		}
	}

	/**
	 * This method returns the last variable of a variable chain.
	 * @param a variable
	 * @return the last variable of a variable chain
	 */
	static public IVariable transferLastVariableOfVariableChainList(IVariable exp) {
		// This is the potentially run variable.
		IVariable runVar = null;
		
		/*
		 *  If the given variable exp has a reference to an element
		 *  that is not a variable then exp is the last variable in the chain
		 *  and we return exp. If the reference of exp is a variable then the
		 *  chain is not fully traversed and we make this reference to the
		 *  run variable.
		 */
		if (!(exp.getReference() instanceof IVariable)) return exp;
		runVar = (IVariable) exp.getReference();
				
		// We make an endless loop.
		while(true) {
			/*
			 * If the reference of the run variable is not a variable
			 * then we know that the variable is the last variable in the
			 * chain and we return this. Otherwise we set the run variable
			 * to the reference.
			 */ 
			if (!(runVar.getReference() instanceof IVariable)) return runVar;
			runVar = (IVariable) runVar.getReference();
		}
	}

	/**
	 * This method returns always a list or null. A list is returned if
	 * <i>exp</i> is of type {@link IListElement} or of type {@link IVariable}
	 * if the last element is list. In the other cases the method will return null.
	 * @param a {@link IModelExpression}
	 * @return a list element if <i>exp</i> is of type {@link IListElement} or of type {@link IVariable}
	 * with its last element of type {@link IListElement}.
	 */
	static public IListElement transferList(IModelExpression exp) {
		// This is the potenially run variable.
		IModelExpression runVar = null;
		
		/*
		 *  If the exp is of type IVariabel then we take the reference of
		 *  exp and make it to the run variable
		 */
		if (exp instanceof IVariable) {
			runVar = ((IVariable)exp).getReference();
		} else
		/*
		 * If exp is of type list we return this exp.
		 */
		if (exp instanceof IListElement) {
			return (IListElement)exp;
		} else // If exp is neither a variable nor a list we return null. 
			return null;
				
		// We make an endless loop.
		while(true) {
			/*
			 * If the run variable is a variable then we take
			 * their reference a make this reference to the actual
			 * run variable.
			 */  
			if (runVar instanceof IVariable) {
				IModelExpression ref = ((IVariable) runVar).getReference();
				runVar = ref;
				continue;
			}
			
			/*
			 * If the run variable is a list we return this list.
			 */
			if (runVar instanceof IListElement) return (IListElement) runVar;
			
			/*
			 * If the run variable is either a list nor a variable
			 * then the last element is not list and we return null.
			 */
			return null;
		}
	}
	
	/**
	 * Deletes all occurrences of <code>deleteChar</code> in <code>string</code>. For example:
	 * Suppose we have the string 'abcb' and the call of this method is deleteChar('abc','b') the
	 * result will be 'ac'.
	 * If <code>deleteChar</i> is not part of <code>string</code> the original string will be returned.
	 * If <code>string</code> is null, an empty string will be returned.
	 * @param string the string from which the character should deleted
	 * @param deleteChar the character that will be removed
	 * @return the string removed by <code>deleteChar</code>
	 */
	static public String deleteChar(String string, char deleteChar) {
		if (string == null) return new String();
		StringBuffer stringCopy = new StringBuffer(string);
		String deleteCharacter = Character.toString(deleteChar);
		
		int indexChar = stringCopy.indexOf(deleteCharacter);
		while(indexChar >= 0) {
			stringCopy.deleteCharAt(indexChar);
			indexChar = stringCopy.indexOf(deleteCharacter);
		}
		
		return stringCopy.toString();
		
		
	}
	
	/**
	 * Gets a string with implicit new lines and makes a string with explicit new lines.
	 * For example. We have the following string:
	 * 
	 * Hallo
	 * Test
	 * 
	 * The method transforms this string into:
	 * "Hallo\n"+
	 * "Test"
	 * 
	 * If <code>naturalString</code> is null, the method returns an empty string.
	 * 
	 * @param naturalString String to be transformed
	 * @return transformed string
	 */
	static public String transformStringToExplicitNewLines(String naturalString) {
		if (naturalString == null) return new String();
		
		// A string buffer that save us the result.
		StringBuffer buf = new StringBuffer();
		
		buf.append("\"");
		
		// We iterate over each character of the string.
		for(int i = 0; i < naturalString.length() - 1 ; i++) {
			char charAtPosition = naturalString.charAt(i);
						
			// If the current character is a new line, we make this explicit, append quotes and a new line.
			if (charAtPosition == '\n') {
				buf.append("\\n\"\n+\"");
			} else { // The current character isn't a new line, so we simply append this to the buffer.
				buf.append(charAtPosition);
			}
		}
		buf.append(naturalString.charAt(naturalString.length()-1) + "\"");
		
		return buf.toString();
	}
	
}
