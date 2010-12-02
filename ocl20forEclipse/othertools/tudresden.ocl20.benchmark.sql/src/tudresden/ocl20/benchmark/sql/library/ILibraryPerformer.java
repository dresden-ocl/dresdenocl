package tudresden.ocl20.benchmark.sql.library;

import tudresden.ocl20.benchmark.sql.util.IPerformer;

public interface ILibraryPerformer extends IPerformer {

	/**
	 * add a new book to database
	 * 
	 * @param bookName
	 *          name of book
	 * @param title
	 *          title of book
	 */
	public void addBook(String bookName, String title);

	/**
	 * add a new writer
	 * 
	 * @param personName
	 *          the primary key of writer
	 */
	public void addWriter(String personName);

	/**
	 * add a new association between writer and book
	 * 
	 * @param writer
	 *          primary key of writer
	 * @param bookName
	 *          primary key of book
	 */
	public void addAssociation(String writer, String bookName);

}
