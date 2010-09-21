package tudresden.ocl20.benchmark.sql.car;

import java.sql.SQLException;

public class OCL2Sql_verticalCarPerformer extends OCL2SqlCarPerformer implements
		ICarPerformer {

	/**
	 * create the performer for ocl2sql(Dresden OCL) for vertical with prefix:
	 * Table: TV_ Association: ASSV_
	 * 
	 * @param name
	 *          the name of the ocl2sql performer
	 * @param file
	 *          the file with the schema
	 * @param fileStop
	 *          the file to clean the database after running
	 */
	public OCL2Sql_verticalCarPerformer(String name, String file, String fileStop) {

		super("vertical " + name, file, fileStop);
	}

	public void addCar(String carName, String model, String color) {

		String sql = "INSERT INTO TV_car (PK_car,model, color) VALUES ('";
		sql += carName + "','" + model + "','" + color + "');";
		try {
			stmt.addBatch(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addPerson(String personName, int age, int phoneno) {

		String sql =
				"INSERT INTO TV_person (PK_person,name, age,phoneno) VALUES ('";
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

		String sql =
				"INSERT INTO ASSV_ownedcars_owner (FK_owner, FK_ownedCars) VALUES ('";
		sql += personName + "','" + carName + "');";
		try {
			stmt.addBatch(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
