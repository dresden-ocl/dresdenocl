package tudresden.ocl20.benchmark.sql.library;

import java.sql.SQLException;

public class OCL2Sql_typedLibraryPerformer extends OCL2SqlLibraryPerformer
		implements ILibraryPerformer {

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
	public OCL2Sql_typedLibraryPerformer(String name, String file, String fileStop) {

		super("typed " + name, file, fileStop);
	}

	public void addBook(String bookName, String title) {

		String sql = "INSERT INTO T_Book (PK_Book,title) VALUES ('";
		sql += bookName + "','" + title + "');";
		try {
			stmt.addBatch(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addWriter(String writer) {

		String sql = "INSERT INTO T_Writer (PK_Writer) VALUES ('";
		sql += writer + "');";
		try {
			stmt.addBatch(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addAssociation(String writer, String book) {

		String sql = "INSERT INTO ASS_author_books (FK_books, FK_author) VALUES ('";
		sql += book + "','" + writer + "');";
		try {
			stmt.addBatch(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
