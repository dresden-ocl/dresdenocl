package tudresden.ocl20.benchmark.sql;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import tudresden.ocl20.benchmark.sql.library.EOSLibraryPerformer;
import tudresden.ocl20.benchmark.sql.library.ILibraryPerformer;
import tudresden.ocl20.benchmark.sql.library.OCL2SqlLibraryPerformer;
import tudresden.ocl20.benchmark.sql.util.IBenchmark;

public class LibraryBenchmark extends Benchmark<ILibraryPerformer> {

	private int NUM_WRITERS;

	private int NUM_BOOKS_PER_WRITER;

	/**
	 * Create a new default benchmark for the model library
	 */
	public LibraryBenchmark() {

		this(1000, 10);

	}

	/**
	 * Create a new benchmark with other parameters
	 * 
	 * @param writer
	 *          number of writers
	 * @param books
	 *          number of books per writer
	 */
	public LibraryBenchmark(int writer, int books) {

		super("library.txt");
		this.NUM_WRITERS = writer;
		this.NUM_BOOKS_PER_WRITER = books;

		try {
			this.writer.write("Number of Writers: " + this.NUM_WRITERS + "\n");
			this.writer.write("Number of Books per Writer: "
					+ this.NUM_BOOKS_PER_WRITER + "\n");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		ILibraryPerformer eos = new EOSLibraryPerformer();
		ILibraryPerformer ocl2Sql =
				new OCL2SqlLibraryPerformer("", "sql/library/ocl2sql-start.sql",
						"sql/library/ocl2sql-stop.sql");
		performer.add(eos);
		performer.add(ocl2Sql);

		constraints.add("Book.allInstances()->size()");
		eos.addQueryString(constraints.getLast(), constraints.getLast(),
				new Integer(NUM_BOOKS_PER_WRITER * NUM_WRITERS).toString());
		ocl2Sql.addQueryString(constraints.getLast(), "SELECT IF(COUNT(*)="
				+ new Integer(NUM_BOOKS_PER_WRITER * NUM_WRITERS).toString()
				+ ",0,1)FROM T_Book;",
				new Integer(NUM_BOOKS_PER_WRITER * NUM_WRITERS).toString());

		constraints
				.add("Book.allInstances().author.books->collect(x|x.title)->size()");
		eos.addQueryString(constraints.getLast(), constraints.getLast(),
				new Integer(NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER * NUM_WRITERS)
						.toString());
		ocl2Sql.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM oclinvp11;", new Integer(NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_WRITERS).toString());

		constraints
				.add("Book.allInstances().author.books->collect(x|x.title <> 'Hobbit')->size()");
		eos.addQueryString(constraints.getLast(), constraints.getLast(),
				new Integer(NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER * NUM_WRITERS)
						.toString());
		ocl2Sql.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM oclinvp12;", new Integer(NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_WRITERS).toString());

		constraints
				.add("Book.allInstances().author.books->collect(x|x.author.books)->size()");
		eos.addQueryString(constraints.getLast(), constraints.getLast(),
				new Integer(NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_WRITERS).toString());
		ocl2Sql.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM oclinvp13;",
				new Integer(NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_WRITERS).toString());

		constraints
				.add("Book.allInstances().author.books->collect(x|x.author.books->includes(x))->size()");
		eos.addQueryString(constraints.getLast(), constraints.getLast(),
				new Integer(NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER * NUM_WRITERS)
						.toString());
		ocl2Sql.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM oclinvp14;", new Integer(NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_WRITERS).toString());

		constraints
				.add("Book.allInstances().author.books->forAll(x|x.author.books->includes(x))");
		eos.addQueryString(constraints.getLast(), constraints.getLast(), "true");
		ocl2Sql.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM oclinvp15;", "" + 0);

		constraints
				.add("Book.allInstances().author.books->select(x|x.author.books->includes(x))->size()");
		eos.addQueryString(constraints.getLast(), constraints.getLast(),
				new Integer(NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER * NUM_WRITERS)
						.toString());
		ocl2Sql.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM oclinvp16;", new Integer(NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_WRITERS).toString());

		constraints
				.add("Book.allInstances().author.books->collect(x|x.author.books.title)->size()");
		eos.addQueryString(constraints.getLast(), constraints.getLast(),
				new Integer(NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_WRITERS).toString());
		ocl2Sql.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM oclinvp17;",
				new Integer(NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_WRITERS).toString());

		constraints
				.add("Book.allInstances().author.books->collect(x|x.author.books.title->size())->sum()");
		eos.addQueryString(constraints.getLast(), constraints.getLast(),
				new Integer(NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_WRITERS).toString());
		ocl2Sql.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM oclinvp18;",
				new Integer(NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_WRITERS).toString());

		constraints
				.add("Book.allInstances().author.books->forAll(x|x.author.books.title->excludes('Hobbit'))");
		eos.addQueryString(constraints.getLast(), constraints.getLast(), "true");
		ocl2Sql.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM oclinvp19;", "" + 0);

		constraints
				.add("Book.allInstances().author.books.author.books->collect(x|x.title)->size()");
		eos.addQueryString(constraints.getLast(), constraints.getLast(),
				new Integer(NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_WRITERS).toString());
		ocl2Sql.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM oclinvp21;",
				new Integer(NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_WRITERS).toString());

		constraints
				.add("Book.allInstances().author.books.author.books->collect(x|x.title <> 'Hobbit')->size()");
		eos.addQueryString(constraints.getLast(), constraints.getLast(),
				new Integer(NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_WRITERS).toString());
		ocl2Sql.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM oclinvp22;",
				new Integer(NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_WRITERS).toString());

		constraints
				.add("Book.allInstances().author.books.author.books->collect(x|x.author.books)->size()");
		eos.addQueryString(constraints.getLast(), constraints.getLast(),
				new Integer(NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER * NUM_WRITERS)
						.toString());
		ocl2Sql.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM oclinvp23;", new Integer(NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_WRITERS).toString());

		constraints
				.add("Book.allInstances().author.books.author.books->collect(x|x.author.books->includes(x))->size()");
		eos.addQueryString(constraints.getLast(), constraints.getLast(),
				new Integer(NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_WRITERS).toString());
		ocl2Sql.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM oclinvp24;",
				new Integer(NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_WRITERS).toString());

		constraints
				.add("Book.allInstances().author.books.author.books->forAll(x|x.author.books->includes(x))");
		eos.addQueryString(constraints.getLast(), constraints.getLast(), "true");
		ocl2Sql.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM oclinvp25;", "" + 0);

		constraints
				.add("Book.allInstances().author.books.author.books->select(x|x.author.books->includes(x))->size()");
		eos.addQueryString(constraints.getLast(), constraints.getLast(),
				new Integer(NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_WRITERS).toString());
		ocl2Sql.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM oclinvp26;",
				new Integer(NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_WRITERS).toString());

		constraints
				.add("Book.allInstances().author.books.author.books->collect(x|x.author.books.title)->size()");
		eos.addQueryString(constraints.getLast(), constraints.getLast(),
				new Integer(NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER * NUM_WRITERS)
						.toString());
		ocl2Sql.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM oclinvp27;", new Integer(NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_WRITERS).toString());

		constraints
				.add("Book.allInstances().author.books.author.books->collect(x|x.author.books.title->size())->sum()");
		eos.addQueryString(constraints.getLast(), constraints.getLast(),
				new Integer(NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER * NUM_WRITERS)
						.toString());
		ocl2Sql.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM oclinvp28;", new Integer(NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_WRITERS).toString());

		constraints
				.add("Book.allInstances().author.books.author.books->forAll(x|x.author.books.title->excludes('Hobbit'))");
		eos.addQueryString(constraints.getLast(), constraints.getLast(), "true");
		ocl2Sql.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM oclinvp29;", "" + 0);

		constraints
				.add("Book.allInstances().author.books.author.books.author.books->collect(x|x.title)->size()");
		eos.addQueryString(constraints.getLast(), constraints.getLast(),
				new Integer(NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER * NUM_WRITERS)
						.toString());
		ocl2Sql.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM oclinvp31;", new Integer(NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_WRITERS).toString());

		constraints
				.add("Book.allInstances().author.books.author.books.author.books->collect(x|x.title <> 'Hobbit')->size()");
		eos.addQueryString(constraints.getLast(), constraints.getLast(),
				new Integer(NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER * NUM_WRITERS)
						.toString());
		ocl2Sql.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM oclinvp32;", new Integer(NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_WRITERS).toString());

		constraints
				.add("Book.allInstances().author.books.author.books.author.books->collect(x|x.author.books)->size()");
		eos.addQueryString(constraints.getLast(), constraints.getLast(),
				new Integer(NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_WRITERS).toString());
		ocl2Sql.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM oclinvp33;",
				new Integer(NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_WRITERS).toString());

		constraints
				.add("Book.allInstances().author.books.author.books.author.books->collect(x|x.author.books->includes(x))->size()");
		eos.addQueryString(constraints.getLast(), constraints.getLast(),
				new Integer(NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER * NUM_WRITERS)
						.toString());
		ocl2Sql.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM oclinvp34;", new Integer(NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_WRITERS).toString());

		constraints
				.add("Book.allInstances().author.books.author.books.author.books->forAll(x|x.author.books->includes(x))");
		eos.addQueryString(constraints.getLast(), constraints.getLast(), "true");
		ocl2Sql.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM oclinvp35;", "" + 0);

		constraints
				.add("Book.allInstances().author.books.author.books.author.books->select(x|x.author.books->includes(x))->size()");
		eos.addQueryString(constraints.getLast(), constraints.getLast(),
				new Integer(NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER * NUM_WRITERS)
						.toString());
		ocl2Sql.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM oclinvp36;", new Integer(NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_WRITERS).toString());

		constraints
				.add("Book.allInstances().author.books.author.books.author.books->collect(x|x.author.books.title)->size()");
		eos.addQueryString(constraints.getLast(), constraints.getLast(),
				new Integer(NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_WRITERS).toString());
		ocl2Sql.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM oclinvp37;",
				new Integer(NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_WRITERS).toString());

		constraints
				.add("Book.allInstances().author.books.author.books.author.books->collect(x|x.author.books.title->size())->sum()");
		eos.addQueryString(constraints.getLast(), constraints.getLast(),
				new Integer(NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_WRITERS).toString());
		ocl2Sql.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM oclinvp38;",
				new Integer(NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_BOOKS_PER_WRITER
						* NUM_BOOKS_PER_WRITER * NUM_WRITERS).toString());

		constraints
				.add("Book.allInstances().author.books.author.books.author.books->forAll(x|x.author.books.title->excludes('Hobbit'))");
		eos.addQueryString(constraints.getLast(), constraints.getLast(), "true");
		ocl2Sql.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM oclinvp39;", "" + 0);

	}

	/**
	 * Fill the database of libraryPerformer
	 * 
	 * @param libraryPerformer
	 *          the performer which database is filled
	 */
	protected void addDataToPerformer(ILibraryPerformer libraryPerformer) {

		// 6.- Insert each component into the scenario
		for (int i = 0; i < NUM_WRITERS; i++) {
			if ((i % 20) == 1) {
				libraryPerformer.runAdd();
			}
			String writer = "writer" + (i + 1);
			libraryPerformer.addWriter(writer);
			for (int j = 0; j < NUM_BOOKS_PER_WRITER; j++) {
				String book = "book" + (j + 1 + i * NUM_BOOKS_PER_WRITER);
				libraryPerformer.addBook(book, book);
				libraryPerformer.addAssociation(writer, book);
			}
		}
		libraryPerformer.runAdd();
	}

	/**
	 * @param args
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
		IBenchmark lb = null;
		if (args.length == 4) {
			lb = new LibraryBenchmark();
		}
		else {
			lb =
					new LibraryBenchmark(Integer.parseInt(args[4]),
							Integer.parseInt(args[5]));
		}
		List<String> performer = lb.getPerformer();
		List<String> constraints = lb.getConstraints();
		lb.clean();
		(new File("library.txt")).delete();
		List<String> runPerformer = new LinkedList<String>();
		runPerformer.addAll(performer);
		// String s = constraints.get(0);
		for (String s : constraints) {
			if (args.length == 4) {
				lb = new LibraryBenchmark();
			}
			else {
				lb =
						new LibraryBenchmark(Integer.parseInt(args[4]),
								Integer.parseInt(args[5]));
			}
			lb.init(runPerformer);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			lb.run(s, runPerformer);
			lb.clean();
		}
	}

}
