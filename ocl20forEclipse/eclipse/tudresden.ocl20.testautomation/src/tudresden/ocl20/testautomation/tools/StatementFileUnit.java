package tudresden.ocl20.testautomation.tools;

import java.util.LinkedList;
import java.util.List;


/**
 * A set of constraints from a file which correspond to a model and a model
 * instance and therefore can be executed within the same environment
 * 
 * @author franz
 * 
 */
public class StatementFileUnit {

	// package deklaration
	private String packDekl;

	private List<StatementDefinition> statements;

	// subdirectory where the source file name was found
	private String testSubDir;
	
	// pure filename without extension
	private String name;

	public StatementFileUnit() {

		this.statements = new LinkedList<StatementDefinition>();
	}

	public void setTestFilePath(String path) {

		this.testSubDir = PathHelper.getPathFromPath(path).toLowerCase();
		this.name = PathHelper.getPureFileNameFromPath(path);
	}

	public String getTestSubDir() {

		return this.testSubDir;
	}
	
	public String getName(){
		return this.name;
	}

	public void setPackage(String packDekl) {

		this.packDekl = packDekl;
	}
	
	public void addStatementDefinition(String name, String specification){
		StatementDefinition tmp = new StatementDefinition(this.packDekl, name, specification);
		this.statements.add(tmp);
	}

	
	public List<StatementDefinition> getStatements()
	{
		return this.statements;
		
	}

}
