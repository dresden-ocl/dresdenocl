package tudresden.ocl20.benchmark.sql.car;

import java.sql.SQLException;

import tudresden.ocl20.benchmark.sql.util.Ocl2SqlPerformer;

public class OCL2SqlCarPerformer extends Ocl2SqlPerformer implements
		ICarPerformer {

	private String[] prefix;

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
	 * @param prefix
	 *          Is a Array with two fields, the first is the prefix for table, the
	 *          second prefix is for association
	 */
	public OCL2SqlCarPerformer(String name, String file, String fileStop,
			String[] prefix) {

		super(name, file, fileStop);
		this.prefix = prefix;
	}

	public void addCar(String carName, String model, String color) {

		String sql =
				"INSERT INTO " + prefix[0] + "_car (PK_car,model, color) VALUES ('";
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
				"INSERT INTO " + prefix[0]
						+ "_person (PK_person,name, age,phoneno) VALUES ('";
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
				"INSERT INTO " + prefix[1]
						+ "_ownedcars_owner (FK_owner, FK_ownedCars) VALUES ('";
		sql += personName + "','" + carName + "');";
		try {
			stmt.addBatch(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
