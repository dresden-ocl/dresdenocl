package ${packagename}.compare;

import ${packagename}.compare.stringTree.StringNode;

public class CompareException extends Exception {
	private String message;
	private StringNode node;
	
	public CompareException(String message, StringNode node) {
		this.message = message;
		this.node = node;
	}
	
	public String getMessage() {
		return message;
	}
}
