package tudresden.ocl20.benchmark.sql.car;

import tudresden.ocl20.benchmark.sql.util.MySqlPerformer;

public abstract class OCL2SqlCarPerformer extends MySqlPerformer implements
		ICarPerformer {

	private String name;

	protected OCL2SqlCarPerformer(String name, String file, String fileStop) {

		super(file, fileStop);
		this.name = name;
	}

	public String getName() {

		return "OCL2Sql " + name;
	}

}
