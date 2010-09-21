package tudresden.ocl20.benchmark.sql.car;

import java.sql.SQLException;

public class OCL2Sql_typedCarPerformer extends OCL2SqlCarPerformer implements
		ICarPerformer {

	/**
	 * create the performer for ocl2sql(Dresden OCL) for typed with prefix: Table:
	 * T_ Association: ASS_
	 * 
	 * @param name
	 *          the name of the ocl2sql performer
	 * @param file
	 *          the file with the schema
	 * @param fileStop
	 *          the file to clean the database after running
	 */
	public OCL2Sql_typedCarPerformer(String name, String file, String fileStop) {

		super("typed " + name, file, fileStop);
	}

	public void addCar(String carName, String model, String color) {

		String sql = "INSERT INTO T_Car (PK_car,model, color) VALUES ('";
		sql += carName + "','" + model + "','" + color + "');";
		try {
			stmt.addBatch(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addPerson(String personName, int age, int phoneno) {

		String sql = "INSERT INTO T_Person (PK_person,name, age,phoneno) VALUES ('";
		sql +=
				personName + "','" + personName + "','" + age + "','" + phoneno + "');";
		try {
			stmt.addBatch(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addAssociation(String personName, String carName) {

		String sql =
				"INSERT INTO ASS_ownedCars_owner (FK_owner, FK_ownedCars) VALUES ('";
		sql += personName + "','" + carName + "');";
		try {
			stmt.addBatch(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
