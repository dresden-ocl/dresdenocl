package tudresden.ocl20.benchmark.sql;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import tudresden.ocl20.benchmark.sql.util.IPerformer;

public abstract class Benchmark<T extends IPerformer> {

	protected List<T> performer;

	protected Map<T, Long> time;

	protected List<String> constraints;

	private BufferedWriter writer;

	protected Benchmark(String file) {

		performer = new LinkedList<T>();
		constraints = new LinkedList<String>();
		time = new HashMap<T, Long>();
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
			try {
				writer.write(s + "\n");
				run(s);
			} catch (IOException e) {
				e.printStackTrace();
			}
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
	private void run(String constraint) throws IOException {

		long start;
		long end;
		for (T t : performer) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			try {
				start = System.currentTimeMillis();
				t.sendQuery(constraint);
				end = System.currentTimeMillis();
			} catch (Exception e) {
				continue;
			}
			writer.write(t.getName() + ":");
			writer.write((end - start) + "ms\n");
			writer.flush();
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

	/**
	 * 
	 * @param args
	 *          host, db user, pw
	 */
	public static void main(String[] args) {

		if (args.length != 4 || args.length != 6) {
			System.out.println("The program needs four parameter(host,db,user,pw).");
			return;
		}
		System.setProperty("sqlbenchmark_host", args[0]);
		System.setProperty("sqlbenchmark_db", args[1]);
		System.setProperty("sqlbenchmark_user", args[2]);
		System.setProperty("sqlbenchmark_pw", args[3]);
		// CarSimplePerformer.main(args);
		CarBenchmark.main(args);
		LibraryBenchmark.main(args);

	}

}
