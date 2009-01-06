package ${packagename}.compare.stringTree;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the inner node of the string tree
 * structure. The class has a list of children and implements
 * the methods to add a child and get the children.
 * @author Nils
 * @version 0.1
 *
 */
public class StringInnerNode extends StringNode {
	/**
	 * The list that holds the children.
	 */
	private List<StringNode> children;
	
	/**
	 * With the constructor we make sure to
	 * initialize the children list.
	 */
	public StringInnerNode() {
		children = new ArrayList<StringNode>();
	}
	
	@Override
	/**
	 * Add a child to the children list
	 * only when the parameter <i>node</i> is not
	 * null. If the parameter is null, nothing
	 * will happen.
	 * @param node the node to be added
	 */
	public void addChild(StringNode node) {
		if (node != null) children.add(node);		
	}

	@Override
	/**
	 * Returns the children list.
	 * @return the children list
	 */
	public List<StringNode> getChildren() {
		return children;
	}
	
	protected void makeTreeGraphic(List<Integer> delimiter, List<String> graphicList, boolean last) {
		// If the delimiter list or the graphic list is null we will thrown an IllegalArgumentException.
		if ((delimiter == null) || (graphicList == null)) new IllegalArgumentException();
		
		/*
		 * We want to build a new line. So we make the delimiter line, append
		 * a dash and the text of this node.
		 */
		String newLine = makeDelimiter(delimiter) + "-" + getText();
		
		/*
		 * If this node is in addition an error node,
		 * we append the string 'ERROR' at the end of the line.
		 * So the user can see at which position the error
		 * occurred.
		 */
		if (isError()) {
			newLine = newLine + "  ERROR";
		}
		
		// The new line is added to the graphic.
		graphicList.add(newLine);
		
		// If this node has no child we will return.
		if ((children == null) || (children.size() == 0)) return;
		
		/*
		 * We must set the new delimiter. It is simply the
		 * length of the text of this node.
		 */ 
		int newDelimiter = getText().length();
		
		/*
		 * Eventually we need create a new delimiter list,
		 * so we introduce a new variable.
		 */ 
		List<Integer> integerList = delimiter;
		
		/*
		 * If this is the last child node of the
		 * parent node, we must build the ascii tree
		 * in a slightly different matter.
		 */
		if (last == true) {
			// The last delimiter of the delimiter list.
			int lastDelimiter = 0;
			
			// The position of the delimiter to be set.
			int setPosition = 0;
			
			/*
			 * If the delimiter list contains more than zero
			 * elements, we get the last delimiter and add one. 
			 * The position in the list is the position of the last delimiter.
			 * If the list contains no elements then we have
			 * the first node of the list and this node is at the
			 * same time the last node.
			 */
			if (delimiter.size() != 0) {
				lastDelimiter = delimiter.get(delimiter.size() -1) + 1;
				setPosition = delimiter.size() -1;
			} else {
				delimiter.add(new Integer(0));
			}
			
			/*
			 * If we have the last child we must overwrite the last
			 * delimiter position to avoid a vertical line at the left
			 * side. The example will illustrate this.
			 * First, an ascii picture with vertical bars on the left side,
			 * A
			 * |-B
			 * |-C
			 * |-D
			 * | |-a1
			 * | |-a2
			 * | |-a3
			 * 
			 * Next, an ascii picture without vertical bars on the left side.
			 * A
			 * |-B
			 * |-C
			 * |-D
			 *   |-a1
			 *   |-a2
			 *   |-a3
			 *  
			 * The last picture is more aesthetic as the first one. Note that
			 * the vertical bar in the second picture is only moved to the right
			 * position. So we must overwrite the last position of the last
			 * delimiter of the parent delimiter's list.
			 */
			newDelimiter = lastDelimiter + getText().length();
			
			/*
			 * We must copy the list because the references show to the
			 * parents delimiter's positions.
			 */
			integerList = cloneIntegerList(delimiter);
			
			/*
			 * Here the delimiter of the parent will be overwritten.
			 */
			integerList.set(setPosition, newDelimiter);
		}
		
		/*
		 * This lines is unattractive because the cloning is not
		 * always necessary. If this node is a last child node it is
		 * a duplicate operation because we have already copy the list.
		 * But if this node isn't the last child node, the variable
		 * 'integerList' references the original delimiter list,
		 * so in that case we must copy.
		 */
		List<Integer> newDelimiterList = cloneIntegerList(integerList);
		
		/*
		 * If this node isn't the last child node, we
		 * add a new delimiter the next level. For example: consider
		 * the following ascii picture:
		 * A
		 * |-B
		 * | |-a1
		 * | |-a2
		 * | |-a3
		 * |-C
		 * |-D
		 * 
		 * In this case we have two vertical bars before the text 'a1'. We
		 * now stay at the node B so the childs must have two vertical bars at
		 * the begin of the line. But this is not the case if B would be
		 * the last node.
		 * Suppose we have the following ascii picture:
		 * A
		 * |-B
		 * |-C
		 * |-D
		 *   |-a1
		 *   |-a2
		 *   |-a3
		 * 
		 * You see that we only shift the vertical bar to the right. That
		 * have we already done. So don't need to add a new delimiter.  
		 */
		if(!last) {
			newDelimiterList.add(newDelimiter);
		}
		
		/*
		 * Now we can recursively traverse the children.
		 */
		for (int i = 0; i < children.size(); i++) {
			/*
			 * If the current child in this traverse is the
			 * last child node, we indicate this with the last parameter of
			 * the method by setting it to 'true'
			 */
			if (i == children.size() -1) {
				children.get(i).makeTreeGraphic(newDelimiterList, graphicList, true);
			} else {
				children.get(i).makeTreeGraphic(newDelimiterList, graphicList, false);
				
			}
		}
	}
	
	/**
	 * Deletes the last node of the children if
	 * any exist, otherwise nothing will happen.
	 */
	public void deleteLastNode() {
		if (children.size() > 0) {
			children.remove(children.size()-1);
		}
	}
}
