package ${packagename}.compare.stringTree;

import java.util.List;

/**
 * This class represents the leaf node of the string tree
 * structure.
 * @author Nils
 * @version 0.1
 */
public class StringLeaf extends StringNode {

	@Override
	/**
	 * Because this is a leaf this method will do nothing.
	 */
	public void addChild(StringNode node) {
		return;
		
	}

	@Override
	/**
	 * This method will always return null.
	 */
	public List<StringNode> getChildren() {
		return null;
	}

	@Override
	/**
	 * Makes an ascii tree of the leaf. That is simple. We first must create a new
	 * delimiter line and append a dash and the text of the leaf. If the node is
	 * an error node we append in addition the string 'ERROR' to help the user
	 * to find the error position.
	 *
	 */
	protected void makeTreeGraphic(List<Integer> delimiter,	List<String> graphicList, boolean last) {
		if ((delimiter == null) || (graphicList == null)) new IllegalArgumentException();
		
		String newLine = makeDelimiter(delimiter) + "-" + getText();
		if (isError()) {
			newLine = newLine + "  ERROR";
		}
		graphicList.add(newLine);
		return;
		
	}
	
	/**
	 * Because that is leaf we can't delete a child. So we do nothing.
	 */
	public void deleteLastNode() {
		
	}

}
