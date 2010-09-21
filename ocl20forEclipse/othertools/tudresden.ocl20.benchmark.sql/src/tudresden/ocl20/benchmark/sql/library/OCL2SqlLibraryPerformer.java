package tudresden.ocl20.benchmark.sql.library;

import tudresden.ocl20.benchmark.sql.util.MySqlPerformer;

public abstract class OCL2SqlLibraryPerformer extends MySqlPerformer implements
		ILibraryPerformer {

	private String name;

	protected OCL2SqlLibraryPerformer(String name, String file, String fileStop) {

		super(file, fileStop);
		this.name = name;
	}

	public String getName() {

		return "OCL2Sql " + name;
	}

}
