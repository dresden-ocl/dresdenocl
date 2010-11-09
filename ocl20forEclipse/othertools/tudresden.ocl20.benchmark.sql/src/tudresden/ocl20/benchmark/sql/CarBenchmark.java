package tudresden.ocl20.benchmark.sql;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import tudresden.ocl20.benchmark.sql.car.EOSCarPerformer;
import tudresden.ocl20.benchmark.sql.car.ICarPerformer;
import tudresden.ocl20.benchmark.sql.car.OCL2MySQLCarPerformer;
import tudresden.ocl20.benchmark.sql.car.OCL2SqlCarPerformer;
import tudresden.ocl20.benchmark.sql.util.IBenchmark;

public class CarBenchmark extends Benchmark<ICarPerformer> {

	private int NUM_CARS_PER_PERSONS;

	private int NUM_PERSONS = 1000;

	private List<ICarPerformer> notAddData;

	/**
	 * Create a new default benchmark for the model car;
	 */
	public CarBenchmark() {

		this(1000, 10);
	}

	/**
	 * Run the benchmark with other parameters
	 * 
	 * @param person
	 *          number of persons
	 * @param cars
	 *          number of cars per persons
	 */
	public CarBenchmark(int person, int cars) {

		super("car.txt");
		notAddData = new LinkedList<ICarPerformer>();
		NUM_PERSONS = person;
		NUM_CARS_PER_PERSONS = cars;
		ICarPerformer eos = new EOSCarPerformer();
		ICarPerformer ocl2MySql =
				new OCL2MySQLCarPerformer("sql/car/ocl2mysql-start.sql",
						"sql/car/ocl2mysql-stop.sql");
		ICarPerformer ocl2Sql =
				new OCL2SqlCarPerformer("typed", "sql/car/ocl2sql-start.sql",
						"sql/car/ocl2sql-stop.sql", new String[] { "T", "ASS" });
		ICarPerformer ocl2Sql_nop =
				new OCL2SqlCarPerformer("typed nop", "sql/car/ocl2sql-start-nop.sql",
						"sql/car/ocl2sql-stop-nop.sql", new String[] { "TO", "ASO" });
		performer.add(eos);
		performer.add(ocl2Sql);
		performer.add(ocl2MySql);
		performer.add(ocl2Sql_nop);
		constraints.add("Car.allInstances()->size()");
		eos.addQueryString(constraints.getLast(), constraints.getLast(),
				new Integer(NUM_CARS_PER_PERSONS * NUM_PERSONS).toString());
		ocl2MySql.addQueryString(constraints.getLast(), "SELECT COUNT(*) FROM Car",
				new Integer(NUM_CARS_PER_PERSONS * NUM_PERSONS).toString());
		ocl2Sql.addQueryString(constraints.getLast(), "SELECT IF(COUNT(*)="
				+ new Integer(NUM_CARS_PER_PERSONS * NUM_PERSONS).toString()
				+ ",0,1)FROM T_Car;",
				new Integer(NUM_CARS_PER_PERSONS * NUM_PERSONS).toString());
		ocl2Sql_nop.addQueryString(constraints.getLast(), "SELECT IF(COUNT(*)="
				+ new Integer(NUM_CARS_PER_PERSONS * NUM_PERSONS).toString()
				+ ",0,1) FROM TO_Car;",
				new Integer(NUM_CARS_PER_PERSONS * NUM_PERSONS).toString());

		constraints.add("Car.allInstances().owner.ownedCars->size()");
		eos.addQueryString(constraints.getLast(), constraints.getLast(),
				new Integer(NUM_CARS_PER_PERSONS * NUM_PERSONS * NUM_CARS_PER_PERSONS)
						.toString());
		ocl2MySql
				.addQueryString(
						constraints.getLast(),
						"SELECT COUNT(*) AS value FROM (SELECT ownership.ownedCars AS value FROM (SELECT ownership.owner AS value FROM (SELECT pk AS value FROM Car) AS temp1 LEFT JOIN ownership ON temp1.value = ownership.ownedCars WHERE ownership.owner IS NOT NULL) AS temp0 LEFT JOIN ownership ON temp0.value = ownership.owner WHERE ownership.ownedCars IS NOT NULL) AS temp2;",
						new Integer(NUM_CARS_PER_PERSONS * NUM_PERSONS
								* NUM_CARS_PER_PERSONS).toString());
		ocl2Sql.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM carOcl01;", new Integer(NUM_CARS_PER_PERSONS
						* NUM_PERSONS * NUM_CARS_PER_PERSONS).toString());
		ocl2Sql_nop.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM OcarOcl01;", new Integer(NUM_CARS_PER_PERSONS
						* NUM_PERSONS * NUM_CARS_PER_PERSONS).toString());

		constraints
				.add("Car.allInstances().owner.ownedCars->collect(x|x.color)->size()");
		eos.addQueryString(constraints.getLast(), constraints.getLast(),
				new Integer(NUM_CARS_PER_PERSONS * NUM_PERSONS * NUM_CARS_PER_PERSONS)
						.toString());
		ocl2MySql.addQueryString(constraints.getLast(),
				"call collect00(); SELECT COUNT(*) FROM collect00;", new Integer(
						NUM_CARS_PER_PERSONS * NUM_PERSONS * NUM_CARS_PER_PERSONS)
						.toString());
		ocl2Sql.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM carOcl02;", new Integer(NUM_CARS_PER_PERSONS
						* NUM_PERSONS * NUM_CARS_PER_PERSONS).toString());
		ocl2Sql_nop.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM OcarOcl02;", new Integer(NUM_CARS_PER_PERSONS
						* NUM_PERSONS * NUM_CARS_PER_PERSONS).toString());

		constraints
				.add("Car.allInstances().owner.ownedCars->collect(x|x.color <> 'black')->size()");
		eos.addQueryString(constraints.getLast(), constraints.getLast(),
				new Integer(NUM_CARS_PER_PERSONS * NUM_PERSONS * NUM_CARS_PER_PERSONS)
						.toString());
		ocl2MySql.addQueryString(constraints.getLast(),
				"call collect01(); SELECT COUNT(*) FROM collect00;", new Integer(
						NUM_CARS_PER_PERSONS * NUM_PERSONS * NUM_CARS_PER_PERSONS)
						.toString());
		ocl2Sql.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM carOcl03;", new Integer(NUM_CARS_PER_PERSONS
						* NUM_PERSONS * NUM_CARS_PER_PERSONS).toString());
		ocl2Sql_nop.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM OcarOcl03;", new Integer(NUM_CARS_PER_PERSONS
						* NUM_PERSONS * NUM_CARS_PER_PERSONS).toString());

		constraints
				.add("Car.allInstances().owner.ownedCars->collect(x|x.owner.ownedCars)->size()");
		eos.addQueryString(constraints.getLast(), constraints.getLast(),
				new Integer(NUM_CARS_PER_PERSONS * NUM_CARS_PER_PERSONS * NUM_PERSONS
						* NUM_CARS_PER_PERSONS).toString());
		ocl2MySql.addQueryString(constraints.getLast(),
				"call collect02(); SELECT COUNT(*) FROM collect00;", new Integer(
						NUM_CARS_PER_PERSONS * NUM_CARS_PER_PERSONS * NUM_PERSONS
								* NUM_CARS_PER_PERSONS).toString());
		ocl2Sql.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM carOcl04;",
				new Integer(NUM_CARS_PER_PERSONS * NUM_CARS_PER_PERSONS * NUM_PERSONS
						* NUM_CARS_PER_PERSONS).toString());
		ocl2Sql_nop.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM OcarOcl04;",
				new Integer(NUM_CARS_PER_PERSONS * NUM_CARS_PER_PERSONS * NUM_PERSONS
						* NUM_CARS_PER_PERSONS).toString());

		constraints
				.add("Car.allInstances().owner.ownedCars->collect(x|x.owner.ownedCars->includes(x))->size()");
		eos.addQueryString(constraints.getLast(), constraints.getLast(),
				new Integer(NUM_CARS_PER_PERSONS * NUM_PERSONS * NUM_CARS_PER_PERSONS)
						.toString());
		ocl2MySql.addQueryString(constraints.getLast(),
				"call collect03(); SELECT COUNT(*) FROM collect00;", new Integer(
						NUM_CARS_PER_PERSONS * NUM_PERSONS * NUM_CARS_PER_PERSONS)
						.toString());
		ocl2Sql.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM carOcl04;",
				new Integer(NUM_CARS_PER_PERSONS * NUM_CARS_PER_PERSONS * NUM_PERSONS
						* NUM_CARS_PER_PERSONS).toString());
		ocl2Sql_nop.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM OcarOcl04;",
				new Integer(NUM_CARS_PER_PERSONS * NUM_CARS_PER_PERSONS * NUM_PERSONS
						* NUM_CARS_PER_PERSONS).toString());

		constraints
				.add("Car.allInstances().owner.ownedCars->forAll(x|x.owner.ownedCars->includes(x))");
		eos.addQueryString(constraints.getLast(), constraints.getLast(), "true");
		ocl2MySql.addQueryString(constraints.getLast(),
				"call forAll0(); SELECT COUNT(*) FROM forAll0;", "" + 1);
		ocl2Sql.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM carOcl06;", "" + 0);
		ocl2Sql_nop.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM OcarOcl06;", "" + 0);

		constraints
				.add("Car.allInstances().owner.ownedCars->select(x|x.owner.ownedCars->includes(x))->size()");
		eos.addQueryString(constraints.getLast(), constraints.getLast(),
				new Integer(NUM_CARS_PER_PERSONS * NUM_PERSONS * NUM_CARS_PER_PERSONS)
						.toString());
		ocl2MySql.addQueryString(constraints.getLast(),
				"call select00(); SELECT COUNT(*) FROM select00;", new Integer(
						NUM_CARS_PER_PERSONS * NUM_PERSONS * NUM_CARS_PER_PERSONS)
						.toString());
		ocl2Sql.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM carOcl07;", new Integer(NUM_CARS_PER_PERSONS
						* NUM_PERSONS * NUM_CARS_PER_PERSONS).toString());
		ocl2Sql_nop.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM OcarOcl07;", new Integer(NUM_CARS_PER_PERSONS
						* NUM_PERSONS * NUM_CARS_PER_PERSONS).toString());

		constraints
				.add("Car.allInstances().owner.ownedCars->collect(x|x.owner.ownedCars.color)->size()");
		eos.addQueryString(constraints.getLast(), constraints.getLast(),
				new Integer(NUM_CARS_PER_PERSONS * NUM_CARS_PER_PERSONS * NUM_PERSONS
						* NUM_CARS_PER_PERSONS).toString());
		ocl2MySql.addQueryString(constraints.getLast(),
				"call collect04(); SELECT COUNT(*) FROM collect00;", new Integer(
						NUM_CARS_PER_PERSONS * NUM_CARS_PER_PERSONS * NUM_PERSONS
								* NUM_CARS_PER_PERSONS).toString());
		ocl2Sql.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM carOcl08;",
				new Integer(NUM_CARS_PER_PERSONS * NUM_CARS_PER_PERSONS * NUM_PERSONS
						* NUM_CARS_PER_PERSONS).toString());
		ocl2Sql_nop.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM OcarOcl08;",
				new Integer(NUM_CARS_PER_PERSONS * NUM_CARS_PER_PERSONS * NUM_PERSONS
						* NUM_CARS_PER_PERSONS).toString());

		constraints
				.add("Car.allInstances().owner.ownedCars->collect(x|x.owner.ownedCars.color->size())->sum()");
		eos.addQueryString(constraints.getLast(), constraints.getLast(),
				new Integer(NUM_CARS_PER_PERSONS * NUM_CARS_PER_PERSONS * NUM_PERSONS
						* NUM_CARS_PER_PERSONS).toString());
		ocl2MySql
				.addQueryString(
						constraints.getLast(),
						"call collect05(); SELECT SUM(value) AS value FROM (SELECT * FROM collect00) AS temp6",
						new Integer(NUM_CARS_PER_PERSONS * NUM_CARS_PER_PERSONS
								* NUM_PERSONS * NUM_CARS_PER_PERSONS).toString());
		ocl2Sql.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM carOcl09;",
				new Integer(NUM_CARS_PER_PERSONS * NUM_CARS_PER_PERSONS * NUM_PERSONS
						* NUM_CARS_PER_PERSONS).toString());
		ocl2Sql_nop.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM OcarOcl09;",
				new Integer(NUM_CARS_PER_PERSONS * NUM_CARS_PER_PERSONS * NUM_PERSONS
						* NUM_CARS_PER_PERSONS).toString());

		constraints
				.add("Car.allInstances().owner.ownedCars->forAll(x|x.owner.ownedCars.color->excludes('black'))");
		eos.addQueryString(constraints.getLast(), constraints.getLast(), "true");
		ocl2MySql.addQueryString(constraints.getLast(),
				"call forAll1(); SELECT COUNT(*) FROM forAll0;", "" + 1);
		ocl2Sql.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM carOcl10;", "" + 0);
		ocl2Sql_nop.addQueryString(constraints.getLast(),
				"SELECT COUNT(*) FROM OcarOcl10;", "" + 0);

		try {
			writer.write("Number of Persons: " + NUM_PERSONS + "\n");
			writer
					.write("Number of Cars per Persons: " + NUM_CARS_PER_PERSONS + "\n");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	/**
	 * Fill the database of carPerformer
	 * 
	 * @param carPerformer
	 *          the performer which database is filled
	 */
	protected void addDataToPerformer(ICarPerformer carPerformer) {

		if (!notAddData.contains(carPerformer)) {

			for (int i = 0; i < NUM_PERSONS; i++) {
				if ((i % 20) == 1) {
					carPerformer.runAdd();
				}

				String person = "" + (i + 1);
				int age = (i % 100);
				carPerformer.addPerson(person, age, i);

				for (int k = 0; k < NUM_CARS_PER_PERSONS; k++) {
					String car = "" + (k + 1 + i * NUM_CARS_PER_PERSONS);
					carPerformer.addCar(car, car, car);
					carPerformer.addAssociation(person, car);
				}
			}
			carPerformer.runAdd();
		}
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
		IBenchmark cb = null;
		if (args.length == 4) {
			cb = new CarBenchmark();
		}
		else {
			cb =
					new CarBenchmark(Integer.parseInt(args[4]), Integer.parseInt(args[5]));
		}
		List<String> performer = cb.getPerformer();
		List<String> constraints = cb.getConstraints();
		cb.clean();
		(new File("car.txt")).delete();
		List<String> runPerformer = new LinkedList<String>();
		runPerformer.addAll(performer);
		// String s = constraints.get(0);
		for (String s : constraints) {
			if (args.length == 4) {
				cb = new CarBenchmark();
			}
			else {
				cb =
						new CarBenchmark(Integer.parseInt(args[4]),
								Integer.parseInt(args[5]));
			}
			cb.init(runPerformer);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			cb.run(s, runPerformer);
			cb.clean();
		}
	}

}
