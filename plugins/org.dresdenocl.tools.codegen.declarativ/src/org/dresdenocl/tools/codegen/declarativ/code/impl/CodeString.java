package org.dresdenocl.tools.codegen.declarativ.code.impl;

import org.dresdenocl.tools.codegen.declarativ.code.ICode;

public class CodeString implements ICode {

	private String code;

	public CodeString(String text) {

		code = text;
	}

	public String getResult() {

		return toString();
	}

	public String toString() {

		return code;
	}

}
