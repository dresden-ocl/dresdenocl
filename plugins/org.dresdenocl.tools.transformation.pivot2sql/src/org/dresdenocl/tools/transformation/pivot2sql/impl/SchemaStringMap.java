package org.dresdenocl.tools.transformation.pivot2sql.impl;

import java.util.HashMap;

import orgomg.cwm.resource.relational.Schema;


public class SchemaStringMap extends HashMap<Schema,StringBuilder> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9105946704583487413L;
	
	/**
	 * 
	 * @return
	 */
	public String toFullString() {
		StringBuilder sb = new StringBuilder();
		for (StringBuilder s : values()) {
			sb.append(s);
		}
		return sb.toString();
	}

}
