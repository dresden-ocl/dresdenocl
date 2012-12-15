package org.dresdenocl.benchmark.sql.util;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import core.IEOS;

public abstract class EOSPerformer implements IPerformer {

	protected IEOS ieos;

	private Map<String, String> oclConstraints;

	protected EOSPerformer() {

		oclConstraints = new HashMap<String, String>();
	}

	protected void finalize() throws Throwable {

		ieos.exit();
	}

	public boolean sendQuery(String query) throws Exception {

		if (!oclConstraints.containsKey(query))
			throw new NoSuchElementException();
		return ieos.query(query).equals(oclConstraints.get(query));
	}

	public void addQueryString(String oclString, String runningString,
			String result) {

		oclConstraints.put(oclString, result);

	}

	public void clean() {

	}

	public void runAdd() {

	}

	public String getName() {

		return "EOSPerformer";
	}

}
