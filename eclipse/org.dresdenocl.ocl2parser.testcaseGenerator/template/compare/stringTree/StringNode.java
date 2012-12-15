package ${packagename}.compare.stringTree;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the root node of the composite pattern that
 * is used here to build a string tree structure. This tree structure
 * will contain a string representation of the ocl expression that is
 * compared with the comparator. This representation is not complete. It
 * contains only the names of the expressions part like PropertyCallExp etc.
 * If an error is encountered while comparing two ocl models the attribute
 * 'error' of the last node is set to indicate that at this point the two
 * models are different.
 * Each node has a text. This text is used to recognize the sub structure
 * (for example a 'source') and the corresponding expression type such as
 * PropertyCallExp.
 * 
 * @author Nils
 * @version 0.1
 *
 */
abstract public class StringNode {
	/**
	 * The text that each node has.
	 */
	private String text;
	
	/**
	 * An attribute to indicate that this node
	 * represents the error place.
	 */
	private boolean error;
	
	/**
	 * If the error flag is set then the node
	 * has an error type.
	 */
	private ErrorFlag errorType;
	
	/**
	 * Gets the text of the node.
	 * @return text of the node
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * Set the text of the node only if parameter
	 * <i>text</i> is not null. If the parameter is null
	 * nothing will change.
	 * @param text the text to be set
	 */
	public void setText(String text) {
		if (text == null) return;
		this.text = text;
	}
	
	/**
	 * Gets the value of the error attribute.
	 * @return true if the error tag was set, otherwise false
	 */
	public boolean isError() {
		return error;
	}
	
	/**
	 * Sets the value of the error attribute.
	 * @param error
	 */
	public void setError(boolean error) {
		this.error = error;
	}
	
	/**
	 * Add a new child to the node.
	 * @param node child to be added
	 */
	abstract public void addChild(StringNode node);
	
	/**
	 * Gets a list of all children.
	 * @return list of all children
	 */
	abstract public List<StringNode> getChildren();

	/**
	 * Get the type of the error if the error flag is
	 * set. If the error flag isn't set, null will be returned.
	 * @return the error flag
	 */
	public ErrorFlag getErrorType() {
		return errorType;
	}

	/**
	 * Set the type of the error.
	 * @param errorType error type to be set
	 */
	public void setErrorType(ErrorFlag errorType) {
		this.errorType = errorType;
	}
	
	/**
	 * This method creates an ascii tree picture of the tree that
	 * is represented by this compositum structure. The first parameter
	 * contains a list of integers that indicate the positions of the
	 * vertical bars of each line. For example: suppose we have a list
	 * with the entries [1,4,2]. That means that we have a vertical bar at
	 * the first position of a line. After that we count 2 positions plus the
	 * number of characters of the current text length and we the position
	 * of the next vertical bar and so on.
	 * The parameter <i>graphicList</i> contains the whole graphic at the end.
	 * While computing the graphic each line will be stored in that list.
	 * The last parameter <i>last</i> is set to true if the computation is
	 * performed on the last child node. In this case a special handling is necessary.
	 * @param delimiter a list of delimiter positions
	 * @param graphicList the graphic that is build
	 * @param last should be true if the last child of the node is computed
	 */
	abstract protected void makeTreeGraphic(List<Integer> delimiter, List<String> graphicList, boolean last);
	
	/**
	 * This method get a delimiter list and make string of this delimiters.
	 * Suppose we have a list of two integers: [1,4]. The method will
	 * give us the following: "|    |". The first vertical bar is at the first
	 * position of the line. The next vertical bar is four positions away of the first
	 * position. That means that this positions are relatively.  
	 * @param delimeter a list of delimiter positions
	 * @return a string that contains vertical bars at the delimiter positions
	 */
	protected StringBuffer makeDelimiter(List<Integer> delimeter) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < delimeter.size(); i++) {
			result.append(makeSpaces(delimeter.get(i)) + "|");
		}
		
		return result;
	}
	
	/**
	 * This simple method creates strings with <i>numberOfSpaces</i>
	 * blank.
	 * @param numberOfSpaces number of blanks to be created
	 * @return a string with <i>numberOfSpaces</i> blanks
	 */
	protected StringBuffer makeSpaces(int numberOfSpaces) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < numberOfSpaces; i++) {
			result.append(" ");
		}
		
		return result;
	}
	
	/**
	 * This method clones a list of integers. It makes a deep copy.
	 * @param list list to be cloned.
	 * @return a deep copy of <i>list</i>
	 */
	protected List<Integer> cloneIntegerList(List<Integer> list) {
		List<Integer> result = new ArrayList<Integer>();
		if (list == null) return result;
		
		for (int i = 0; i < list.size(); i++) {
			Integer newInteger = new Integer(list.get(i));
			result.add(newInteger);
		}
		
		return result;
	}
	
	/**
	 * Makes a string representation of the string tree. This
	 * representation will be an ascii tree.
	 * @return ascii tree
	 */
	public String toString() {
		List<Integer> delimeterList = new ArrayList<Integer>();
		List<String> resultList = new ArrayList<String>();
		makeTreeGraphic(delimeterList, resultList, false);
		
		StringBuffer buffer = new StringBuffer();
		for(String string : resultList) {
			buffer.append(string);
			buffer.append("\n");
		}
		
		return buffer.toString();
	}
	
	/**
	 * This method will return the last child node if
	 * any exists. Otherwise the method will do nothing.
	 */
	abstract public void deleteLastNode();
	
}
