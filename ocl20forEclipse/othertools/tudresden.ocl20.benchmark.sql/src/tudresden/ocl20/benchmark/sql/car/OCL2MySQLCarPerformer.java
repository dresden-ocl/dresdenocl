package tudresden.ocl20.benchmark.sql.car;

import java.sql.SQLException;

import tudresden.ocl20.benchmark.sql.util.MySqlPerformer;

public class OCL2MySQLCarPerformer extends MySqlPerformer implements
		ICarPerformer {

	/**
	 * create the performer for ocl2mysql
	 * 
	 * @param file
	 *          the file with the schema
	 * @param fileStop
	 *          the file to clean the database after running
	 */
	public OCL2MySQLCarPerformer(String file, String fileStop) {

		super(file, fileStop, false);
	}

	public void addCar(String carName, String model, String color) {

		String sql = "INSERT INTO Car (model, color) VALUES ('";
		sql += model + "','" + color + "');";
		try {
			stmt.addBatch(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addPerson(String personName, int age, int phoneno) {

		String sql = "INSERT INTO Person (pk,name, age,phoneno) VALUES ('";
		sql +=
				personName + "','" + personName + "','" + age + "','" + phoneno + "');";
		try {
			stmt.addBatch(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addAssociation(String personName, String carName) {

		String sql = "INSERT INTO ownership (owner, ownedCars) VALUES ('";
		sql += personName + "','" + carName + "');";
		try {
			stmt.addBatch(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getName() {

		return "Ocl2MySQL";
	}

}
