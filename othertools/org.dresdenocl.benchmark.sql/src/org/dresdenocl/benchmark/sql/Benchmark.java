package org.dresdenocl.benchmark.sql;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.dresdenocl.benchmark.sql.util.IBenchmark;
import org.dresdenocl.benchmark.sql.util.IPerformer;

public abstract class Benchmark<T extends IPerformer> implements IBenchmark {

	protected List<T> performer;

	protected LinkedList<String> constraints;

	protected BufferedWriter writer;

	protected Benchmark(String file) {

		performer = new LinkedList<T>();
		constraints = new LinkedList<String>();
		try {
			writer = new BufferedWriter(new FileWriter(file, true));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Run the Benchmark with all constraints
	 */
	public void run() {

		for (String s : constraints) {
			run(s, getPerformer());
		}
	}

	/**
	 * Run a single Constraint for all performer;
	 * 
	 * @param constraint
	 *          the constraint
	 * @throws IOException
	 *           if throws if a problem with writing in the file.
	 */
	public void run(String constraint, List<String> performer) {

		long start;
		long end;
		try {
			writer.write(constraint + "\n");
			for (T t : getPerformerList(performer)) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				boolean result = false;
				try {
					start = System.currentTimeMillis();
					result = t.sendQuery(constraint);
					end = System.currentTimeMillis();
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
				writer.write(t.getName() + ":");
				writer.write((end - start) + "ms (Value:" + result + ")\n");
				writer.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Clean up the database of all performer.
	 */
	public void clean() {

		try {
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (T cp : performer) {
			cp.clean();
		}
	}

	public List<String> getPerformer() {

		List<String> result = new ArrayList<String>();
		for (T t : performer) {
			result.add(t.getName());
		}
		return result;
	}

	protected List<T> getPerformerList(List<String> performerList) {

		List<T> tList = new ArrayList<T>();
		for (String s : performerList) {
			for (T tp : performer) {
				if (tp.getName().equals(s)) {
					tList.add(tp);
				}
			}
		}
		return tList;
	}

	protected T getPerformer(String s) {

		for (T tp : performer) {
			if (tp.getName().equals(s)) {
				return tp;
			}
		}

		return null;
	}

	public List<String> getConstraints() {

		return constraints;
	}

	/**
	 * Initialize the database with data and add all constraints for running.
	 */
	public void init(List<String> performer) {

		for (String s : performer) {
			T t = getPerformer(s);
			if (t != null) {
				addDataToPerformer(t);
			}
		}
	}

	protected abstract void addDataToPerformer(T t);

	/**
	 * 
	 * @param args
	 *          host, db user, pw
	 */
	public static void main(String[] args) {

		if (!(args.length == 4 || args.length == 6)) {
			System.out.println("The program needs four parameter(host,db,user,pw).");
			return;
		}
		System.setProperty("sqlbenchmark_host", args[0]);
		System.setProperty("sqlbenchmark_db", args[1]);
		System.setProperty("sqlbenchmark_user", args[2]);
		System.setProperty("sqlbenchmark_pw", args[3]);
		CarBenchmark.main(args);
		LibraryBenchmark.main(args);

	}

}
