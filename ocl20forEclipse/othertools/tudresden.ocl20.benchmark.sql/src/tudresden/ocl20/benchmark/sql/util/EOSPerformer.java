package tudresden.ocl20.benchmark.sql.util;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import core.IEOS;

public abstract class EOSPerformer implements IPerformer {

	protected IEOS ieos;

	private List<String> oclConstraints;

	protected EOSPerformer() {

		oclConstraints = new LinkedList<String>();
	}

	protected void finalize() throws Throwable {

		ieos.exit();
	}

	public String sendQuery(String query) throws Exception {

		if (!this.oclConstraints.contains(query))
			throw new NoSuchElementException();
		String queryResult = ieos.query(query);
		return queryResult;
	}

	public void addQueryString(String oclString, String runningString) {

		this.oclConstraints.add(oclString);

	}

	public void clean() {

	}

	public void runAdd() {

	}

	public String getName() {

		return "EOSPerformer";
	}

}
