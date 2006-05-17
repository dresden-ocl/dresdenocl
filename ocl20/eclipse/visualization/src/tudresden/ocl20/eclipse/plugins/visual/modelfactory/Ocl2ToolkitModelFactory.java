package tudresden.ocl20.eclipse.plugins.visual.modelfactory;

import java.io.File;
import java.io.PushbackReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import tudresden.ocl20.core.MetaModelConst;
import tudresden.ocl20.core.ModelManager;
import tudresden.ocl20.core.OclModel;
import tudresden.ocl20.core.parser.astgen.Heritage;
import tudresden.ocl20.core.parser.astgen.LAttrAstGenerator;
import tudresden.ocl20.core.parser.sablecc.lexer.Lexer;
import tudresden.ocl20.core.parser.sablecc.node.Start;
import tudresden.ocl20.core.parser.sablecc.parser.Parser;

/**
 * ModelFactory for the Dresden OCL2 Toolkit. Provides loading of OCL and
 * XMI-Files with automatic parsing.
 * 
 * @author Kai-Uwe Gärtner
 * Parts taken from ParserGUI by Ansgar Konermann 
 * 
 */
public abstract class Ocl2ToolkitModelFactory extends ConfigurableModelFactory {

	protected OclModel model = null;

	private Start cst = null;

	/**
	 * Constructor. Adds some methods which should not be called. Uses
	 * "getNameA" as labelMethod.
	 * 
	 */
	public Ocl2ToolkitModelFactory() {
		super();
		doNotCall.add("getBagType");
		doNotCall.add("getCollectionType");
		doNotCall.add("getSequenceType");
		doNotCall.add("getSetType");

		// excludeClasses.add(tudresden.ocl20.core.jmi.uml15.core.ModelElement.class);
		// excludeClasses.add(UmlClass.class);

		labelMethod = "getNameA";

	}

	/**
	 * Loads and parses an ocl-file.
	 * 
	 * @param file
	 *            the ocl-file
	 * @return true, if successful (will be replaced with an exception ;-))
	 */
	public boolean loadOclFile(java.io.File file) {
		String path = file.getAbsolutePath();
		boolean canRead = file.canRead();
		String ret = "";
		if (!canRead) {
			System.err.println("Cannot read file " + path + "! Aborting load.");
		} else {
			try {
				java.io.BufferedReader br = new java.io.BufferedReader(
						new java.io.InputStreamReader(
								new java.io.FileInputStream(path)));
				String line = br.readLine();
				while (line != null) {
					ret += line;
					ret += "\n";
					line = br.readLine();
				}
				br.close();

			} catch (java.io.IOException ex) {
				System.err.println("Error reading file " + path
						+ "! Check system error stream for details.");
				ex.printStackTrace(System.err);
				return false;
			}
		}
		Lexer lexer = new Lexer(new PushbackReader(new StringReader(ret), 1024));
		Parser parser = new Parser(lexer);
		try {
			cst = parser.parse();
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return false;
		}
		return true;
	}

	/**
	 * Loads a xmi-file.
	 * 
	 * @param xmifile
	 *            the xmi-file
	 * @return true, if successful (will be replaced with an exception ;-))
	 */

	public boolean loadXmiFile(File xmifile) {
		System.out.println("Loading model: " + xmifile.getAbsolutePath());
		String modelUrl = "file:" + xmifile.getAbsolutePath();
		try {
			/*Collection models = ModelManager.getInstance().getAllModelNames();
			Collection temp = new ArrayList();
			temp.addAll(models);
			System.out.println(models.size());
			Iterator it = temp.iterator();
			while (it.hasNext()) {
				ModelManager.getInstance()
						.deleteModel(
								ModelManager.getInstance().getModel(
										(String) it.next()));
			}*/
			System.out.println(ModelManager.getInstance().getAllModelNames());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			model = new OclModel(MetaModelConst.UML15, modelUrl);
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return false;
		}
		return true;
	}

	/**
	 * Generates the AST (also known as ASM).
	 * 
	 * @return true, if sucessful
	 */
	public boolean generateAst() {
		if ((model == null) || (cst == null))
			return false;
		LAttrAstGenerator astgen = new LAttrAstGenerator(model);
		Heritage hrtg = new Heritage();
		try {
			model.beginTrans(true);
			cst.apply(astgen, hrtg);
			model.endTrans(false);
			System.err.println("Attribute evaluation completed successfully.");
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.eclipse.plugins.visual.modelfactory.ModelFactory#loadRessource(java.io.File)
	 */
	public void loadRessource(File file) {
		if (file.getName().toUpperCase().contains(".OCL")) {
			loadOclFile(file);
		} else if (file.getName().toUpperCase().contains(".XMI")) {
			loadXmiFile(file);
		}
		generateAst();

	}

}
