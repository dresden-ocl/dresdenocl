package org.dresdenocl.benchmark.sql.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public abstract class MySqlPerformer implements IPerformer {

	protected Statement stmt;

	protected Map<String, String[]> oclConstraints;

	protected List<String> statements;

	protected String fileStop;

	protected boolean constraint;

	protected MySqlPerformer(String file, String fileStop, boolean constraint) {

		oclConstraints = new HashMap<String, String[]>();
		statements = new LinkedList<String>();
		this.fileStop = fileStop;
		this.constraint = constraint;
		try {
			stmt =
					DriverManager.getConnection(
							"jdbc:mysql://" + System.getProperty("sqlbenchmark_host") + "/"
									+ System.getProperty("sqlbenchmark_db") + "?" + "user="
									+ System.getProperty("sqlbenchmark_user") + "&password="
									+ System.getProperty("sqlbenchmark_pw")).createStatement();
			parse(file, stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean sendQuery(String query) throws Exception {

		if (!this.oclConstraints.containsKey(query))
			throw new NoSuchElementException();
		ResultSet rs = null;
		for (String s : this.oclConstraints.get(query)[0].split(";")) {
			if (s == null)
				continue;
			rs = stmt.executeQuery(s);
		}
		rs.next();
		// System.out.println(this.getName()+":"+rs.getString(1));
		return rs.getString(1).equals(this.oclConstraints.get(query)[1]);
	}

	public void addQueryString(String oclString, String runningString,
			String solution) {

		this.oclConstraints
				.put(oclString, new String[] { runningString, solution });
	}

	public void runAdd() {

		try {
			stmt.executeBatch();
			stmt.clearBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void clean() {

		try {
			parse(fileStop, stmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void parse(String file, Statement stmt) throws SQLException {

		if (file == null)
			return;
		String delimiter = ";";
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			String zeile = null;
			String temp = "";
			while ((zeile = in.readLine()) != null) {
				if (zeile.equals(""))
					continue;
				if (zeile.startsWith("-- Context:")) {
					continue;
				}
				if (zeile.startsWith("--"))
					continue;
				if (zeile.startsWith("delimiter")) {
					delimiter = zeile.replace("delimiter ", "");
					continue;
				}
				else if (zeile.endsWith(delimiter)) {
					if (constraint && (temp.contains("CREATE OR REPLACE"))) {
						statements.add(temp + zeile.replace(delimiter, ""));
					}
					else {
						stmt.addBatch(temp + zeile.replace(delimiter, ""));
					}
					temp = "";
				}
				else {
					temp += " " + zeile;
				}

			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		stmt.executeBatch();
		stmt.clearBatch();

	}

}
