package org.dresdenocl.benchmark.sql.util;

import java.sql.SQLException;

public abstract class Ocl2SqlPerformer extends MySqlPerformer implements
		IPerformer {

	private String name;

	protected Ocl2SqlPerformer(String name, String file, String fileStop) {

		super(file, fileStop, true);
		this.name = name;
	}

	public void addQueryString(String oclString, String runningString,
			String solution) {

		super.addQueryString(oclString, runningString, "" + 0);
		try {
			if (oclConstraints.size() - 2 >= 0)
				stmt.execute(statements.get(oclConstraints.size() - 2).replaceFirst(
						"VALUESTMT", solution));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getName() {

		return "OCL2Sql " + name;
	}

}
