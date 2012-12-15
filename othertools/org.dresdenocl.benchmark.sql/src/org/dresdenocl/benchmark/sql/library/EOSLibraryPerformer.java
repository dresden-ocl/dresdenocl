package tudresden.ocl20.benchmark.sql.library;

import tudresden.ocl20.benchmark.sql.util.EOSPerformer;
import core.IEOS;

/**
 * 
 * @author Miguel Angel Garcia de Dios
 */
public class EOSLibraryPerformer extends EOSPerformer implements
		ILibraryPerformer {

	public EOSLibraryPerformer() {

		createLibraryState();
	}

	// for using the tool in evaluator mode
	private void createLibraryState() {

		// 1A.- Create an instance of EOS.
		ieos = new IEOS();

		// 2.- Create a model
		ieos.createClassDiagram();

		// 3.- Insert each element into the model.
		this.ieos.insertClass("Book");
		this.ieos.insertAttribute("Book", "title", "String");
		this.ieos.insertClass("Writer");
		this.ieos.insertAssociation("Book", "books", "*", "*", "author", "Writer");

		// 4.- Close the model
		ieos.closeClassDiagram();

		// 5.- Create an scenario
		ieos.createObjectDiagram();

	}

	public void addBook(String bookName, String title) {

		this.ieos.insertObject("Book", bookName);
		this.ieos.insertValue("Book", "title", bookName, title);
	}

	public void addWriter(String personName) {

		this.ieos.insertObject("Writer", personName);

	}

	public void addAssociation(String writer, String bookName) {

		this.ieos.insertLink("Book", bookName, "books", "author", writer, "Writer");
	}

}
