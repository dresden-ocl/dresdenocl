package tudresden.ocl20.benchmark.sql;

import tudresden.ocl20.benchmark.sql.car.EOSCarPerformer;
import tudresden.ocl20.benchmark.sql.car.ICarPerformer;
import tudresden.ocl20.benchmark.sql.car.OCL2MySQLCarPerformer;
import tudresden.ocl20.benchmark.sql.car.OCL2Sql_typedCarPerformer;

public class CarBenchmark extends Benchmark<ICarPerformer> {

	private int NUM_CARS_PER_PERSONS = 1000;

	private int NUM_PERSONS = 10;

	/**
	 * Create a new default benchmark for the model car;
	 */
	public CarBenchmark() {

		super("car.txt");
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

		this();
		this.NUM_PERSONS = person;
		this.NUM_CARS_PER_PERSONS = cars;
	}

	/**
	 * Initialize the database with data and add all constraints for running.
	 */
	public void init() {

		ICarPerformer eos = new EOSCarPerformer();
		ICarPerformer ocl2MySql =
				new OCL2MySQLCarPerformer("sql/car/ocl2mysql-start.sql",
						"sql/car/ocl2mysql-stop.sql");
		ICarPerformer ocl2Sql_t =
				new OCL2Sql_typedCarPerformer("", "sql/car/ocl2sql-start.sql", null);
		ICarPerformer ocl2Sql_to =
				new OCL2Sql_typedCarPerformer(" optimize", null,
						"sql/car/ocl2sql-stop.sql");
		performer.add(eos);
		performer.add(ocl2MySql);
		performer.add(ocl2Sql_t);
		performer.add(ocl2Sql_to);
		time.put(eos, new Long(0));
		time.put(ocl2MySql, new Long(0));
		time.put(ocl2Sql_t, new Long(0));
		time.put(ocl2Sql_to, new Long(0));
		addDataToICarCheck(eos);
		addDataToICarCheck(ocl2MySql);
		addDataToICarCheck(ocl2Sql_t);
		for (ICarPerformer cc : time.keySet()) {
			System.out.print(cc.getName() + ":");
			System.out.println(time.get(cc) / 1000 + "s");
		}
		constraints.add("Car.allInstances().owner.ownedCars->size()");
		eos.addQueryString(constraints.get(0), constraints.get(0));
		ocl2MySql
				.addQueryString(
						constraints.get(0),
						"SELECT COUNT(*) AS value FROM (SELECT ownership.ownedCars AS value FROM (SELECT ownership.owner AS value FROM (SELECT pk AS value FROM Car) AS temp1 LEFT JOIN ownership ON temp1.value = ownership.ownedCars WHERE ownership.owner IS NOT NULL) AS temp0 LEFT JOIN ownership ON temp0.value = ownership.owner WHERE ownership.ownedCars IS NOT NULL) AS temp2;");
		ocl2Sql_t.addQueryString(constraints.get(0),
				"SELECT COUNT(*) FROM carocl01;");
		ocl2Sql_to.addQueryString(constraints.get(0),
				"SELECT COUNT(*) FROM carocl01_optimize;");

		constraints
				.add("Car.allInstances().owner.ownedCars->collect(x|x.color)->size()");
		eos.addQueryString(constraints.get(1), constraints.get(1));
		ocl2MySql.addQueryString(constraints.get(1), "call collect00();");
		ocl2Sql_t.addQueryString(constraints.get(1),
				"SELECT COUNT(*) FROM carocl02;");
		ocl2Sql_to.addQueryString(constraints.get(1),
				"SELECT COUNT(*) FROM carocl02_optimize;");

		constraints
				.add("Car.allInstances().owner.ownedCars->collect(x|x.color <> 'black')->size()");
		eos.addQueryString(constraints.get(2), constraints.get(2));
		ocl2MySql.addQueryString(constraints.get(2), "call collect01();");
		ocl2Sql_t.addQueryString(constraints.get(2),
				"SELECT COUNT(*) FROM carocl03;");
		ocl2Sql_to.addQueryString(constraints.get(2),
				"SELECT COUNT(*) FROM carocl03_optimize;");

		constraints
				.add("Car.allInstances().owner.ownedCars->collect(x|x.owner.ownedCars)->size()");
		eos.addQueryString(constraints.get(3), constraints.get(3));
		ocl2MySql.addQueryString(constraints.get(3), "call collect02();");
		ocl2Sql_t.addQueryString(constraints.get(3),
				"SELECT COUNT(*) FROM carocl04;");
		ocl2Sql_to.addQueryString(constraints.get(3),
				"SELECT COUNT(*) FROM carocl04_optimize;");

		constraints
				.add("Car.allInstances().owner.ownedCars->collect(x|x.owner.ownedCars->includes(x))->size()");
		eos.addQueryString(constraints.get(4), constraints.get(4));
		ocl2MySql.addQueryString(constraints.get(4), "call collect03();");
		ocl2Sql_t.addQueryString(constraints.get(4),
				"SELECT COUNT(*) FROM carocl05;");
		ocl2Sql_to.addQueryString(constraints.get(4),
				"SELECT COUNT(*) FROM carocl05_optimize;");
		constraints
				.add("Car.allInstances().owner.ownedCars->forAll(x|x.owner.ownedCars->includes(x))");
		eos.addQueryString(constraints.get(5), constraints.get(5));
		ocl2MySql.addQueryString(constraints.get(5), "call forAll0();");
		ocl2Sql_t.addQueryString(constraints.get(5),
				"SELECT COUNT(*) FROM carocl06;");
		ocl2Sql_to.addQueryString(constraints.get(5),
				"SELECT COUNT(*) FROM carocl06_optimize;");

		constraints
				.add("Car.allInstances().owner.ownedCars->select(x|x.owner.ownedCars->includes(x))->size()");
		eos.addQueryString(constraints.get(6), constraints.get(6));
		ocl2MySql.addQueryString(constraints.get(6), "call select00();");
		ocl2Sql_t.addQueryString(constraints.get(6),
				"SELECT COUNT(*) FROM carocl07;");
		ocl2Sql_to.addQueryString(constraints.get(6),
				"SELECT COUNT(*) FROM carocl07_optimize;");

		constraints
				.add("Car.allInstances().owner.ownedCars->collect(x|x.owner.ownedCars.color)->size()");
		eos.addQueryString(constraints.get(7), constraints.get(7));
		ocl2MySql.addQueryString(constraints.get(7), "call collect04();");
		ocl2Sql_t.addQueryString(constraints.get(7),
				"SELECT COUNT(*) FROM carocl08;");
		ocl2Sql_to.addQueryString(constraints.get(7),
				"SELECT COUNT(*) FROM carocl08_optimize;");

		constraints
				.add("Car.allInstances().owner.ownedCars->collect(x|x.owner.ownedCars.color->size())->sum()");
		eos.addQueryString(constraints.get(8), constraints.get(8));
		ocl2MySql.addQueryString(constraints.get(8), "call collect05();");
		ocl2Sql_t.addQueryString(constraints.get(8),
				"SELECT COUNT(*) FROM carocl09;");
		ocl2Sql_to.addQueryString(constraints.get(8),
				"SELECT COUNT(*) FROM carocl09_optimize;");

		constraints
				.add("Car.allInstances().owner.ownedCars->forAll(x|x.owner.ownedCars.color->excludes('black'))");
		eos.addQueryString(constraints.get(9), constraints.get(9));
		ocl2MySql.addQueryString(constraints.get(9), "call forAll1();");
		ocl2Sql_t.addQueryString(constraints.get(9),
				"SELECT COUNT(*) FROM carocl10;");
		ocl2Sql_to.addQueryString(constraints.get(9),
				"SELECT COUNT(*) FROM carocl10_optimize;");
	}

	/**
	 * Fill the database of carPerformer
	 * 
	 * @param carPerformer
	 *          the performer which database is filled
	 */
	private void addDataToICarCheck(ICarPerformer carPerformer) {

		for (int i = 0; i < NUM_PERSONS; i++) {
			if ((i % 20) == 1) {
				carPerformer.runAdd();
			}

			String person = "" + (i + 1);
			int age = (i % 100);
			carPerformer.addPerson(person, age, i);

			for (int k = 0; k < NUM_CARS_PER_PERSONS; k++) {
				long start;
				long end;
				String car = "" + (k + 1 + i * NUM_CARS_PER_PERSONS);
				carPerformer.addCar(car, car, car);
				start = System.currentTimeMillis();
				carPerformer.addAssociation(person, car);
				end = System.currentTimeMillis();
				time.put(carPerformer, new Long(time.get(carPerformer) + (end - start)));
			}
		}
		carPerformer.runAdd();
	}

	/**
	 * @param args
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
		CarBenchmark cb = null;
		if (args.length == 4) {
			cb = new CarBenchmark();
		}
		else {
			cb =
					new CarBenchmark(Integer.parseInt(args[4]), Integer.parseInt(args[5]));
		}
		cb.init();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		cb.run();
		cb.clean();
	}

}
